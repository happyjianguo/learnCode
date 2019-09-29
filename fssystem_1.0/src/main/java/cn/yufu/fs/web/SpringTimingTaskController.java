package cn.yufu.fs.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jcraft.jsch.Session;

import cn.yufu.cortex.service.CortexService;
import cn.yufu.fs.entity.ClearMerStlBook;
import cn.yufu.fs.entity.ViewStlBookDetail;
import cn.yufu.fs.service.ClearMerStlBookService;
import cn.yufu.fs.service.ViewStlBookDetailService;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.utils.DateUtil;
import cn.yufu.system.common.utils.Log;
import cn.yufu.system.common.utils.StringUtils;
import cn.yufu.system.common.utils.entity.FTPAndSFTP;
import cn.yufu.system.common.utils.pdf.SendMailExcelUtil;
import cn.yufu.system.common.utils.pdf.SendMailPDFUtil;
import cn.yufu.system.common.utils.sftp.LoginSFTP;
import cn.yufu.system.common.utils.sftp.UploadFileSFTP;
import cn.yufu.system.modules.sys.utils.DictUtils;

/***
 * 财务计算定时任务手动调用Controller
 * 
 * @author ZQK 2018-04-04
 * */

@Controller
@RequestMapping(value = "/time")
public class SpringTimingTaskController {
	
	Log log = Log.getLog(SpringTimingTaskController.class);
	
	@Autowired
	@Qualifier("fs.ClearMerStlBookService")	
	private ClearMerStlBookService clearMerStlBookService;
	
	@Autowired
	@Qualifier("cortex.CortexService")	
	private CortexService cortexService;

	@Autowired
	@Qualifier("fs.ViewStlBookDetailService")	
	private ViewStlBookDetailService viewStlBookDetailService;	
	
	//private String now = "20171026";	//测试先写死  20180327
	
	/**
	 * 财务结算 -- 结算初表线下发送对账明细单初始页面
	 * 
	 * */
	@RequestMapping(value = "/clearMerStl/page")
	public String page(HttpServletRequest req, HttpServletResponse resp, 
			Model model) {
		model.addAttribute("now", getNowDt("yyyyMMdd", 0));
		return "modules/fs/springTimingTask/clearMerStlSendFile";
	}
	
	/**
	 * 财务结算 -- 结算初表线下发送对账明细单
	 * */
	@RequestMapping(value = "/clearMerStl/sendFile")
	public String sendFile(HttpServletRequest req, HttpServletResponse resp,
			Model model, String now) {
		System.out.println("结算日期：" + now);
		//是否需要发送对账明细
		String isSendBilldetail = Global.getSendDetail();
		StringBuilder sb = new StringBuilder();
		if ("true".equalsIgnoreCase(isSendBilldetail)) {
			//数据准备     生成PDF 上传至FTP 发送邮件
			sendDataPrepare(sb, now);
			System.out.println(sb.toString());
			if (StringUtils.isEmpty(sb.toString())) {
				model.addAttribute("result", "今天未有结算信息.");
			}else{
				model.addAttribute("result", sb.toString());
			}
		}
		model.addAttribute("now", now);
		return "modules/fs/springTimingTask/clearMerStlSendFile";
	}
	
	/**
	 * 获取对账明细单
	 *
	 */
	private void sendDataPrepare(StringBuilder sb, String now){
		ClearMerStlBook queryModel = new ClearMerStlBook();
		queryModel.setFlagOnline("0"); 	//线下标志
		queryModel.setStlDate(now);
		//需要发送邮件的结算单信息
		List<ClearMerStlBook> list = clearMerStlBookService.queryList(queryModel);
		if (list == null || list.size() == 0) return;	//空 --> 无需发送
		sb.append("结算初表线下(" + DateUtil.getNow("yyyMMdd HH:mm:ss") + ")已结算条目数: --> " + list.size() + "<br />\n");
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
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 其邮箱格式不正确，不发送对账明细邮件信息<br />\n");
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
				int createPDF = createPDF(viewStlBookDetailList, viewStlBookDetail, sb, now);
				if (0 == createPDF) {
					success++;
				}
			}else{
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 不发送对账明细邮件信息<br />\n");
			}
		}
		if (0 != success) {
			sb.append("成功生成" + success + "个文件<br />\n");
			pdfUploadToFTP(sb, now);	//上传PDF至FTP
		}
	}
	
	/**
	 * 生成PDF
	 * 0 成功 1失败
	 */
	private int createPDF(List<ViewStlBookDetail> list, 
			ViewStlBookDetail viewStlBookDetail, StringBuilder sb, String now){
		int res = 1;
		try {
			String merNo = viewStlBookDetail.getMerNo();
			if (StringUtils.isEmpty(merNo)) {
				sb.append("结算初表ID: " + viewStlBookDetail.getId() + " 的商户号为空！<br />\n");
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
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成PDF成功----------<br />\n");
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
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成PDF失败----------<br />\n");
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
	private void pdfUploadToFTP(StringBuilder sb, String now) {
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
				sb.append("-----------PDF文件上传至FTP服务器成功-----------<br />\n");
				//sendMailAndPDF(mailPDFUtil, viewStlBookDetail);
			}else {
				sb.append("-----------PDF文件上传至FTP服务器失败-----------<br />\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/************************************************************************/
	/**
	 * 财务结算 -- 结算初表线下发送对账明细单
	 * */
	@RequestMapping(value = "/clearMerStl/sendFileExcel")
	public String sendFileExcel(HttpServletRequest req, HttpServletResponse resp,
			Model model, String now) {
		System.out.println("结算日期：" + now);
		//是否需要发送对账明细
		String isSendBilldetail = Global.getSendDetail();
		StringBuilder sb = new StringBuilder();
		if ("true".equalsIgnoreCase(isSendBilldetail)) {
			//数据准备     生成EXCEL 上传至FTP 发送邮件
			sendDataExcelPrepare(sb, now);
			System.out.println(sb.toString());
			if (StringUtils.isEmpty(sb.toString())) {
				model.addAttribute("result", "今天未有结算信息.");
			}else{
				model.addAttribute("result", sb.toString());
			}
		}
		model.addAttribute("now", now);
		return "modules/fs/springTimingTask/clearMerStlSendFile";
	}
	
	/**
	 * 获取对账明细单
	 *
	 */
	private void sendDataExcelPrepare(StringBuilder sb, String now){
		ClearMerStlBook queryModel = new ClearMerStlBook();
		queryModel.setFlagOnline("0"); 	//线下标志
		queryModel.setStlDate(now);
		//需要发送邮件的结算单信息
		List<ClearMerStlBook> list = clearMerStlBookService.queryList(queryModel);
		if (list == null || list.size() == 0) return;	//空 --> 无需发送
		sb.append("结算初表线下(" + DateUtil.getNow("yyyMMdd HH:mm:ss") + ")已结算条目数: --> " + list.size() + "<br />\n");
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
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 其邮箱格式不正确，不发送对账明细邮件信息<br />\n");
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
				int createPDF = createExcel(viewStlBookDetailList, viewStlBookDetail, sb, now);
				if (0 == createPDF) {
					success++;
				}
			}else{
				sb.append("商户号：" + clearMerStlBook.getMerNo() + ", 初表ID: " + clearMerStlBook.getId() + ", 不发送对账明细邮件信息<br />\n");
			}
		}
		if (0 != success) {
			sb.append("成功生成" + success + "个文件<br />\n");
			boolean result = uploadFileSFTP(now);	//上传EXCEL至SFTP
			if (result) {
				System.out.println("-----------EXCEL文件上传至SFTP服务器成功-----------");
			} else {
				System.out.println("-----------EXCEL文件上传至SFTP服务器失败-----------");
			}
		}	
	}
	
	/**
	 * 生成PDF
	 * 0 成功 1失败
	 */
	private int createExcel(List<ViewStlBookDetail> list, 
			ViewStlBookDetail viewStlBookDetail, StringBuilder sb, String now){
		int res = 1;
		try {
			String merNo = viewStlBookDetail.getMerNo();
			if (StringUtils.isEmpty(merNo)) {
				sb.append("结算初表ID: " + viewStlBookDetail.getId() + " 的商户号为空！<br />\n");
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
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成EXCEL成功----------<br />\n");
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
				sb.append("商户号：" + merNo + ", 初表ID: " + viewStlBookDetail.getId() + "------------生成EXCEL失败----------<br />\n");
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
	private boolean uploadFileSFTP(String now) {
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
	
	/**
	 * 获取当前时间
	 * @param pattern	时间格式
	 * @return
	 */
	public String getNowDt(String pattern,int flag){
		if(pattern==null||"".equals(pattern)){
			pattern="yyyyMMddHHmmss";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, flag);
		Date date = calendar.getTime();
    	SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
    	return df.format(date);
	}
}
