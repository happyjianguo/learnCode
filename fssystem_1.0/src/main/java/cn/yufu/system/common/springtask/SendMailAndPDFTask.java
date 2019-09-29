package cn.yufu.system.common.springtask;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearMerStlBook;
import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.fs.service.ClearMerStlBookService;
import cn.yufu.fs.service.ViewStlBookDetailService;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.entity.FTPAndSFTP;
import cn.yufu.system.common.utils.pdf.SendMailPDFUtil;

/**
 * 结算初表线下
 * 自动给商户发送对账明细(PDF版)邮件定时任务
 * @author ZQK 2018-02-23
 *
 */
@Component
@Lazy(false)
public class SendMailAndPDFTask {
	
	private ClearMerStlBookService clearMerStlBookService;	//结算初表Service
	
	private ViewStlBookDetailService viewStlBookDetailService;	//结算初表对应的对账明细Service
	
	private CortexService cortexService;	//交易类型Service
	
	private String now = "";	
	
	//private String now = "20180327";	//测试先写死 20171026
	
	private void getBean(){
		//获取上下文环境信息
		ApplicationContext ac = ContextLoader.getCurrentWebApplicationContext();
		
		clearMerStlBookService = (ClearMerStlBookService)ac.getBean("fs.ClearMerStlBookService");
		viewStlBookDetailService = (ViewStlBookDetailService)ac.getBean("fs.ViewStlBookDetailService");
		cortexService = (CortexService)ac.getBean("cortex.CortexService");
	}
	
	//@Scheduled(cron = "0/60 * * * * ?")	//测试  每 1分钟执行一次
	@Scheduled(cron = "0 0 7 * * ?")	//每天上午7点执行
	public void sendMailAndPDFAndDay() {
		//是否需要发送对账明细
		String isSendBilldetail = Global.getSendDetail();
		//String isSendBilldetail = DictUtils.getDictValue("是否需要发送对账明细", "IS_SEND_BILLDETAIL", "false");
		if ("true".equalsIgnoreCase(isSendBilldetail)) {
			getBean();
			//数据准备     生成PDF 上传至FTP 发送邮件
			sendDataPrepare();
		}
	}
	
	/**
	 * 获取对账明细单
	 *
	 */
	private void sendDataPrepare(){
		now = DateUtil.getNow("yyyMMdd");
		System.out.println("结算日期：" + now);
		ClearMerStlBook queryModel = new ClearMerStlBook();
		queryModel.setFlagOnline("0"); 	//线下标志
		queryModel.setStlDate(now);
		//需要发送邮件的结算单信息
		List<ClearMerStlBook> list = clearMerStlBookService.queryList(queryModel);
		StringBuilder sb = new StringBuilder();
		if (list == null || list.size() == 0) return;	//空 --> 无需发送
		sb.append("结算初表线下(" + DateUtil.getNow("yyyMMdd HH:mm:ss") + ")已结算条目: --> " + list.size() + "\n");
		ViewStlBookDetail viewStlBookDetail = null;
		List<ViewStlBookDetail> viewStlBookDetailList = null;
		int success = 0;
		for (ClearMerStlBook clearMerStlBook : list) {
			//验证邮箱号
			if (StringUtils.isEmpty(clearMerStlBook.getMerchantEmail())) {
				continue;
			}
			//验证父商户号
			if (StringUtils.isEmpty(clearMerStlBook.getFmrchNo())) {
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 其父商户为空，不发送对账明细邮件信息\n");
				continue;
			}
			Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
			Matcher matcher = pattern.matcher(clearMerStlBook.getMerchantEmail());
			if (!matcher.find()) {
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 其邮箱格式不正确，不发送对账明细邮件信息\n");
				continue;
			}
			//判断是否需要发送邮件  1 发送 0 不发送
			if ("1".equals(clearMerStlBook.getIsSendBillDetail())) {
				viewStlBookDetail = new ViewStlBookDetail();
				//需要发送 --> 得到该结算信息所对应的对账明细单
				viewStlBookDetail.setId(clearMerStlBook.getId());
				viewStlBookDetailList = viewStlBookDetailService.queryList(viewStlBookDetail);
				if (viewStlBookDetailList == null || viewStlBookDetailList.size() == 0) continue;
				viewStlBookDetail = viewStlBookDetailList.get(0);	//包括商户号，邮箱，是否需要发送,结算周期等标识
				setTranType(viewStlBookDetailList);	//设置交易类型
				//生成PDF
				int createPDF = createPDF(viewStlBookDetailList, viewStlBookDetail, sb);
				if (0 == createPDF) {
					success++;
				}
			}else{
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 不发送对账明细邮件信息\n");
			}
		}
		System.out.println(sb.toString());
		if (0 != success) {
			System.out.println("成功生成" + success + "个文件");
			pdfUploadToFTP();	//上传PDF至FTP
		}	
	}
	
	/**
	 * 生成PDF
	 *	0 成功  1失败
	 */
	private int createPDF(List<ViewStlBookDetail> list, ViewStlBookDetail viewStlBookDetail,
			StringBuilder sb){
		int res = 1;
		try {
			String merNo = viewStlBookDetail.getMerNo();
			if (StringUtils.isEmpty(merNo)) {
				sb.append("结算初表ID: " + viewStlBookDetail.getId() + " 的商户号为空！\n");
				return res;
			}
			
			//合计
			String tranAmt = "0";
			String fee = "0";		
			String sumAmt = viewStlBookDetailService.getSumAmt(viewStlBookDetail.getId());
			if(sumAmt != null && !"".equals(sumAmt) && sumAmt.contains("#")){
				String[] arr = sumAmt.split("#");
				tranAmt = arr[0].toString();
				fee = arr[1].toString();
			}
			
			SendMailPDFUtil mailPDFUtil = SendMailPDFUtil.getInstance(viewStlBookDetail, now);
			int result = mailPDFUtil.setTableData(list, tranAmt, fee);
			
			if (0 == result) {
				res = 0;
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成PDF成功----------\n");
				//更新结算初表生成PDF时间字段
				ClearMerStlBook queryModel = new ClearMerStlBook();
				queryModel.setPdfFlag("1");		// 1  已生成PDF 0 默认-->未生成
				queryModel.setId(viewStlBookDetail.getId()); //ID
				queryModel.setMerNo(merNo); //商户号
				queryModel.setStlDate(now);	//结算日期
				clearMerStlBookService.sendMailUpdate(queryModel);
				//发送邮件
				//sendMailAndPDF(mailPDFUtil, viewStlBookDetail);
			}else{
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成PDF失败----------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 上传PDF文件至FTP服务器
	 *
	 */
	private void pdfUploadToFTP() {
		//PDF已全部生成，上传至FTP服务器
		try {
			//使用此UTIL上传文件，sourcePath路径已写死
			SendMailPDFUtil mailPDFUtil = SendMailPDFUtil.getInstance(now);
			//上传文件实体类
			FTPAndSFTP instance = FTPAndSFTP.getInstance();
			//ftp上传
			//int result = mailPDFUtil.pdfUploadToFTP(instance);
			//sftp上传
			int result = mailPDFUtil.sftpFileUpload(instance);
			if (0 == result) {
				System.out.println("-----------PDF文件上传至FTP服务器成功-----------");
				//sendMailAndPDF(mailPDFUtil, viewStlBookDetail);
			}else {
				System.out.println("-----------PDF文件上传至FTP服务器失败-----------");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送邮件
	 *
	 */
	@SuppressWarnings("unused")
	private void sendMailAndPDF(SendMailPDFUtil mailPDFUtil, ViewStlBookDetail viewStlBookDetail){
		int result = -1;
		int i = 0;
		String merNo = viewStlBookDetail.getMerNo();
		String email = viewStlBookDetail.getMerchantEmail();
		do {
			String[] receive = {email}; 	//接收方邮箱
			result = mailPDFUtil.sendMailAndPDF(receive);
			if (0 == result) {
				System.out.println("商户号：" + merNo + "------------发送邮件成功----------");
			} else {
				i++;	//默认循环发送5次  5次不成功不再发送
				System.out.println("商户号：" + merNo + "------发送邮件失败,失败次数：------ " + i + " 次");
			} 
		} while (0 != result && i < 5);
		
		//更新状态 发送邮件的时间 及 失败次数
		ClearMerStlBook queryModel = new ClearMerStlBook();
		queryModel.setId(viewStlBookDetail.getId()); //ID
		queryModel.setMerNo(merNo); //商户号
		queryModel.setStlDate(now);	//结算日期
		clearMerStlBookService.sendMailUpdate(queryModel);
		queryModel.setSendEmailDate(DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));
		if (i != 0) {
			queryModel.setSendEmailTimes(new BigDecimal(i));
		}else if (i == 0) {
			queryModel.setSendEmailTimes(new BigDecimal(0));
		}
		clearMerStlBookService.sendMailUpdate(queryModel);
	}
	
	/**
	 * 为对账明细单设置交易类型相应描述
	 *
	 */
	private void setTranType(List<ViewStlBookDetail> list) {
		//设置交易类型名称
		Map<String,String> consumpCategoryMap = cortexService.getSysParamMapByParamType("FS_TRAN_TYPE");		
		String tranType = "";
		if(consumpCategoryMap != null && consumpCategoryMap.size() > 0){
			for(ViewStlBookDetail cmcb : list){
				tranType = cmcb.getTranType();
				if(tranType != null && !"".equals(tranType)){
					cmcb.setTranTypeDesc(consumpCategoryMap.get(tranType));
				}
			}	
		}
	}
	
}
