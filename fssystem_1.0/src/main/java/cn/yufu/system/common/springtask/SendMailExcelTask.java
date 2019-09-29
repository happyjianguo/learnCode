package cn.yufu.system.common.springtask;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import com.jcraft.jsch.Session;

import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearMerStlBook;
import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.fs.service.ClearMerStlBookService;
import cn.yufu.fs.service.ViewStlBookDetailService;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.pdf.SendMailExcelUtil;
import cn.yufu.system.common.utils.sftp.LoginSFTP;
import cn.yufu.system.common.utils.sftp.UploadFileSFTP;
import cn.yufu.system.modules.sys.utils.DictUtils;

/**
 * 结算初表线下
 * 自动给商户发送对账明细(EXCEL版)邮件定时任务
 * @author ZQK 2018-10-09
 *
 */
@Component
@Lazy(false)
public class SendMailExcelTask {
	
	private ClearMerStlBookService clearMerStlBookService;	//结算初表Service
	
	private ViewStlBookDetailService viewStlBookDetailService;	//结算初表对应的对账明细Service
	
	private CortexService cortexService;	//交易类型Service
	
	private String now = "";	
	
	//private String now = "20171026";	//测试先写死 20180327
	
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
		if ("true".equalsIgnoreCase(isSendBilldetail)) {
			getBean();
			//数据准备     生成EXCEL 上传至SFTP 发送邮件
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
				int createExcel = createExcel(viewStlBookDetailList, viewStlBookDetail, sb);
				if (0 == createExcel) {
					success++;
				}
			}else{
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 不发送对账明细邮件信息\n");
			}
		}
		System.out.println(sb.toString());
		if (0 != success) {
			System.out.println("成功生成" + success + "个文件");
			boolean result = uploadFileSFTP();	//上传EXCEL至SFTP
			if (result) {
				System.out.println("-----------EXCEL文件上传至SFTP服务器成功-----------");
			} else {
				System.out.println("-----------EXCEL文件上传至SFTP服务器失败-----------");
			}
		}	
	}
	
	/**
	 * 生成PDF
	 *	0 成功  1失败
	 */
	private int createExcel(List<ViewStlBookDetail> list, ViewStlBookDetail viewStlBookDetail,
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
			//生成EXCEL
			int result = SendMailExcelUtil.createExcel(list, viewStlBookDetail, tranAmt, fee, now);
			//更新表状态
			if (0 == result) {
				res = 0;
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成EXCEL成功----------\n");
				//更新结算初表生成PDF时间字段
				ClearMerStlBook queryModel = new ClearMerStlBook();
				queryModel.setPdfFlag("1");		// 1  已生成PDF 0 默认-->未生成
				queryModel.setId(viewStlBookDetail.getId()); //ID
				queryModel.setMerNo(merNo); //商户号
				queryModel.setStlDate(now);	//结算日期
				clearMerStlBookService.sendMailUpdate(queryModel);
				//发送邮件
				//sendMailAndPDF(mailPDFUtil, viewStlBookDetail);
			} else {
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成EXCEL失败----------\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 上传EXCEL文件至SFTP服务器
	 *
	 */
	private boolean uploadFileSFTP() {
		boolean result = false;
		String localPath = Global.getConfig("SEND_MAIL_EXCEL_LOCAL");
		localPath = localPath + "/" + now;
		String targetPath = DictUtils.getDictValue("上传FTP服务器目录路径", "FTP_UPLOAD_PATH", "/home/testftp/fssystem_pdf/");
		targetPath = targetPath + now;
		String uploadIp = DictUtils.getDictValue("上传FTP服务器IP", "FTP_UPLOAD_IP", "192.168.10.75");
		String uploadPortStr = DictUtils.getDictValue("上传FTP服务器PORT", "FTP_UPLOAD_PORT", "22");
		int uploadPort = Integer.parseInt(uploadPortStr);
		String uploadUsername = DictUtils.getDictValue("上传FTP服务器用户名", "FTP_UPLOAD_USERNAME", "testftp");
		String uploadPassword = DictUtils.getDictValue("上传FTP服务器密码", "FTP_UPLOAD_PASSWORD", "testftp");
		
		Session session = LoginSFTP.login(uploadIp, uploadUsername, uploadPassword, uploadPort, "", "");
		//Session session = LoginSFTP.login("192.168.6.135", "rwusr", "rw@admin123", 22, "", ""); 
		UploadFileSFTP instance = UploadFileSFTP.getInstance();
		result = instance.uploadFile(session, localPath, targetPath, "", true);
		
		return result;
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
