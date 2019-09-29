package com.pay.merInfoStatistics.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.merInfoStatistics.bean.MerchantInfo;
import com.pay.merInfoStatistics.bean.TerminalInfo;
import com.pay.merInfoStatistics.bean.TerminalInfoExcel;
import com.pay.merInfoStatistics.dao.MerchantInfoDao;
import com.pay.merInfoStatistics.dao.TerminalInfoDao;
import com.pay.merInfoStatistics.struts.form.TerminalInfoForm;
import com.pay.merInfoStatistics.struts.form.TerminalInfoUploadForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.util.Constant;
import com.pay.util.ExportExcel;
import com.pay.util.POIUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtil;


public class TerminalInfoAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(TerminalInfoAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	public ActionForward getTerminalInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TerminalInfoDao dao = new TerminalInfoDao();
			TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = dao.getCount(terminalInfoForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<TerminalInfo> terminalInfoList = dao.getTerminalInfoList(pageBean, terminalInfoForm);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if (terminalInfoList != null && !terminalInfoList.isEmpty()) {
				request.setAttribute("terminalInfoList", terminalInfoList);
			}
			
			SysParameterDao sysParameterDao=new SysParameterDao();
			
			//POS型号
			List<SysParameterBean> pos_modelList=sysParameterDao.getSysParameterList("POS_MODEL");
			if(pos_modelList!=null&&!pos_modelList.isEmpty()){
				request.setAttribute("pos_modelList", pos_modelList);
			}
			
			//POS类型
			List<SysParameterBean> pos_typeList=sysParameterDao.getSysParameterList("POS_TYPE");
			if(pos_typeList!=null&&!pos_typeList.isEmpty()){
				request.setAttribute("pos_typeList", pos_typeList);
			}
		} catch (Exception e) {
			logger.error("TerminalInfoAction--getTerminalInfoList--Exception:", e);
		}
		return mapping.findForward("showTerminalInfoList");
	}

	public ActionForward preQueryTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		TerminalInfoDao dao = new TerminalInfoDao();
		TerminalInfo terminalInfo = dao.getTerminalInfoByID(id);
		if (terminalInfo != null) {
			
			SysParameterDao sysParameterDao=new SysParameterDao();
			
			//POS型号
			List<SysParameterBean> pos_modelList=sysParameterDao.getSysParameterList("POS_MODEL");
			if(pos_modelList!=null&&!pos_modelList.isEmpty()){
				request.setAttribute("pos_modelList", pos_modelList);
			}
			
			//POS类型
			List<SysParameterBean> pos_typeList=sysParameterDao.getSysParameterList("POS_TYPE");
			if(pos_typeList!=null&&!pos_typeList.isEmpty()){
				request.setAttribute("pos_typeList", pos_typeList);
			}
			
			TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
			try {
				BeanUtils.copyProperties(terminalInfoForm, terminalInfo);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}
		return mapping.findForward("showTerminalInfo");
	}

	/**
	 * 初始化增加终端页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		SysParameterDao sysParameterDao=new SysParameterDao();
		
		//POS型号
		List<SysParameterBean> pos_modelList=sysParameterDao.getSysParameterList("POS_MODEL");
		if(pos_modelList!=null&&!pos_modelList.isEmpty()){
			request.setAttribute("pos_modelList", pos_modelList);
		}
		
		//POS类型
		List<SysParameterBean> pos_typeList=sysParameterDao.getSysParameterList("POS_TYPE");
		if(pos_typeList!=null&&!pos_typeList.isEmpty()){
			request.setAttribute("pos_typeList", pos_typeList);
		}
		
		return mapping.findForward("addTerminalInfo");
	}

	/**
	 * 添加终端信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String info = "";
			//用于添加完成后，关闭提示信息页面时，调用的查询方法
	        String flushdo = "/terminalInfo.do?method=getTerminalInfoList";
			TerminalInfoDao dao = new TerminalInfoDao();
			TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
			int result = dao.addTerminalInfo(terminalInfoForm);
			if (result >= 0) {
				info = "终端("+terminalInfoForm.getId()+")添加成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，终端("+terminalInfoForm.getId()+")添加失败！";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", info);
			request.setAttribute("flushdo", flushdo);
			
			SysParameterDao sysParameterDao=new SysParameterDao();
			//POS型号
			List<SysParameterBean> pos_modelList=sysParameterDao.getSysParameterList("POS_MODEL");
			if(pos_modelList!=null&&!pos_modelList.isEmpty()){
				request.setAttribute("pos_modelList", pos_modelList);
			}
			
			//POS类型
			List<SysParameterBean> pos_typeList=sysParameterDao.getSysParameterList("POS_TYPE");
			if(pos_typeList!=null&&!pos_typeList.isEmpty()){
				request.setAttribute("pos_typeList", pos_typeList);
			}
		} catch (Exception e) {
			logger.error("TerminalInfoAction--addTerminalInfo--Exception:", e);
		}
		
		return mapping.findForward("addTerminalInfo");
	}

	/**
	 * @TODO 准备修改终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 跳转路径
	 */
	public ActionForward preModTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		TerminalInfoDao dao = new TerminalInfoDao();
		TerminalInfo terminalInfo = dao.getTerminalInfoByID(id);
		if (terminalInfo != null) {
			
			SysParameterDao sysParameterDao=new SysParameterDao();
			//POS型号
			List<SysParameterBean> pos_modelList=sysParameterDao.getSysParameterList("POS_MODEL");
			if(pos_modelList!=null&&!pos_modelList.isEmpty()){
				request.setAttribute("pos_modelList", pos_modelList);
			}
			
			//POS类型
			List<SysParameterBean> pos_typeList=sysParameterDao.getSysParameterList("POS_TYPE");
			if(pos_typeList!=null&&!pos_typeList.isEmpty()){
				request.setAttribute("pos_typeList", pos_typeList);
			}
			
			//所属商户列表
			MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
			MerchantInfo merchantInfo = merchantInfoDao.getTMerchantInfoByID(terminalInfo.getMerchantid());
			merchantInfo.setName(merchantInfo.getId() + "(" + merchantInfo.getName() + ")");
			List<MerchantInfo> merchantInfos = new ArrayList<MerchantInfo>();
			merchantInfos.add(merchantInfo);
			request.setAttribute("merchantInfos", merchantInfos);
			
			TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
			try {
				BeanUtils.copyProperties(terminalInfoForm, terminalInfo);
				terminalInfoForm.setQmerchantidname(terminalInfo.getMerchantid());
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}		
		return mapping.findForward("editTerminalInfo");
	}

	public ActionForward modTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		TerminalInfoDao dao = new TerminalInfoDao();
		TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
		int result = dao.updTerminalInfo(terminalInfoForm);
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "/terminalInfo.do?method=getTerminalInfoList";
		//String flushdo = "closewindow";
		if (result >= 0) {
			info = "终端("+terminalInfoForm.getId()+")修改成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，终端("+terminalInfoForm.getId()+")修改失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("flushdo", flushdo);
		request.setAttribute("info", info);
		return mapping.findForward("resultOfUpdate.jsp");
	}
	
	/**
	 * 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String id = ParamUtils.getParameter(request, "id");
		
		TerminalInfoDao dao = new TerminalInfoDao();
		int result = dao.delTerminalInfo(id); 
		
		if (result >= 0) {
			info = "终端("+id+")删除成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，终端("+id+")删除失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTerminalInfoList");
	}
	
	public ActionForward exportExcelOfTerminalInfo(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TerminalInfoDao dao = new TerminalInfoDao();
		// 获得终端信息列表所有记录
        TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
        List<TerminalInfoExcel> list = dao.getTerminalInfoList(terminalInfoForm);
		
        SysParameterDao sysParameterDao=new SysParameterDao();
        
		//POS型号
		Map<String,String> pos_modelMap=sysParameterDao.getSysParamMap("POS_MODEL");
		//POS类型
		Map<String,String> pos_typeMap=sysParameterDao.getSysParamMap("POS_TYPE");
		
		TerminalInfoExcel fb = null;
		if(pos_modelMap != null && pos_modelMap.size() > 0 && pos_typeMap != null && pos_typeMap.size() > 0){
			for(int i=0; i<list.size(); i++){
				fb = (TerminalInfoExcel)list.get(i);
				if(fb.getModel() != null && !"".equals(fb.getModel())){
					fb.setModel(pos_modelMap.get(fb.getModel()).toString());
				}
				if(fb.getType() != null && !"".equals(fb.getType())){
					fb.setType(pos_typeMap.get(fb.getType()).toString());
				}
			}					
		}
        String docsPath = session.getServletContext().getRealPath("docs");
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "商户信息统计-终端信息" + datetime + ".xls";
		
		String filePath =null;
		ExportExcel<TerminalInfoExcel> expexl = new ExportExcel<TerminalInfoExcel>();			

		OutputStream out = null;
		try {
			fileName = URLEncoder.encode(fileName,"UTF-8");
			filePath  = docsPath + FILE_SEPARATOR + fileName;
			System.out.println("[download file path=]"+filePath);
			out = new FileOutputStream(filePath);
						String[] headers = { "终端编号","所属商户","商户装机地址","店铺及款台地址","POS型号","POS类型","无线POS手机号","POS机S/N号","安装日期","停用日期","升级日期","联系人","门店电话","POS机状态","POS押金（元）"};
			expexl.exportExcel("商户信息统计-终端信息", headers, list, out, "yyyy-MM-dd");			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		expexl.download(filePath, response);
		return null;
	}
	
	// 导出（下载excel模板）
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
		log.info("开始下载excel模板");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
	    	
		try {
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("请按模板整理数据！终端编号、所属商户、商户装机地址、店铺及款台地址、POS型号、POS类型、POS机S/N号、安装日期、停用日期、升级日期、联系人、门店电话、POS机状态、POS押金（元）必填且所属商户编号必须存在！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 15));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "终端编号（8位数字，首位可为A）","所属商户编号（15位数字）","商户装机地址","店铺及款台地址","POS型号（单选下拉框）","POS类型（单选下拉框）","无线POS手机号（非必填，若填写需为数字）","POS机S/N号","安装日期（yyyyMMdd）","停用日期（yyyyMMdd）","升级日期（yyyyMMdd）","联系人","门店电话（数字）","POS机状态（单选下拉框）","POS押金（数字，单位：元）" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			
			//第三行
			row = sheet.createRow(2);
			POIUtils.createCell(row, (short) 0, "A1234567");
			POIUtils.createCell(row, (short) 1, "123456789012345");
			POIUtils.createCell(row, (short) 2, "商户装机地址");
			POIUtils.createCell(row, (short) 3, "店铺及款台地址");
			
	        SysParameterDao sysParameterDao=new SysParameterDao();
			//POS型号码表
			Map<String,String> pos_modelMap=sysParameterDao.getSysParamMap("POS_MODEL");
			ArrayList<String> modelList = new ArrayList<String>();
			for (Map.Entry<String, String> entry :pos_modelMap.entrySet()) {
				modelList.add(entry.getKey() + "-" + entry.getValue());
			}
			//POS型号 默认值
			POIUtils.createTextCell(book, row, (short) 4, modelList.get(0));
			//POS型号 下拉框列表
			POIUtils.creatDropDownLists(sheet, 2, 4, modelList);
			
			//POS类型码表
			Map<String,String> pos_typeMap=sysParameterDao.getSysParamMap("POS_TYPE");
			ArrayList<String> typeList = new ArrayList<String>();
			for (Map.Entry<String, String> entry :pos_typeMap.entrySet()) {
				typeList.add(entry.getKey() + "-" + entry.getValue());
			}
			//POS类型 默认值
			POIUtils.createTextCell(book, row, (short) 5, typeList.get(0));
			//POS类型 下拉框列表
			POIUtils.creatDropDownLists(sheet, 2, 5, typeList);
			
			POIUtils.createCell(row, (short) 6, "12345678901");
			POIUtils.createCell(row, (short) 7, "POS机S/N号");
			POIUtils.createCell(row, (short) 8, "20160407");
			POIUtils.createCell(row, (short) 9, "20160407");
			POIUtils.createCell(row, (short) 10, "20160407");
			POIUtils.createCell(row, (short) 11, "联系人");
			POIUtils.createCell(row, (short) 12, "12345678901");
			
			ArrayList<String> statusList = new ArrayList<String>();
			statusList.add("0-可用");
			statusList.add("1-不可用");
			statusList.add("2-黑名单");
			//POS机状态 默认值
			POIUtils.createTextCell(book, row, (short) 13, statusList.get(0));
			//POS机状态 下拉框列表
			POIUtils.creatDropDownLists(sheet, 2, 13, statusList);
			
			POIUtils.createCell(row, (short) 14, "100");

			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("终端信息导入模板", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TerminalInfoAction.downloadTemplate()调用出现异常。");
			log.error(e, e.fillInStackTrace());
		}
		return null;
	}
		
	// 上传(导入到数据库)
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
		log.info("开始导入数据");
		TerminalInfoDao terminalInfoDao = new TerminalInfoDao();
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		int number = 0;
		Set terminalSet = new HashSet();
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		try {
			writer = response.getWriter();
			TerminalInfoUploadForm uf = (TerminalInfoUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			List<String> list=new ArrayList<String>();

			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
			} else {
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// 新建一个Model
					TerminalInfoForm terminalInfo = new TerminalInfoForm();
					String id = POIUtils.getStringFromExcelCell(row.getCell(0));
					//id 主键 - 8位数字首字母可为A、不能重复
					if (StringUtil.isNull(id) || id.length() != 8) {
						terminalSet.add("<br />第" + index + "行，终端编号为" + id + "，不是首字母可为A的8位字符串或已经存在！<br />");
						break;
					}
					String id1 = id.substring(0, 1);
					String id2to8 = id.substring(1);
					String isExist = terminalInfoDao.checkTerminalInfoPK(id);
					//id 主键 - 8位数字首字母可为A、不能重复
					if ((!id1.equals("A") && !org.apache.commons.lang.StringUtils.isNumeric(id)) || !org.apache.commons.lang.StringUtils.isNumeric(id2to8) || isExist.equals("1")) {
						terminalSet.add("<br />第" + index + "行，终端编号为" + id + "，不是首字母可为A的8位字符串或已经存在！<br />");
						break;
					} 
					terminalInfo.setId(id);
					
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(1));
					boolean isNumeric = org.apache.commons.lang.StringUtils.isNumeric(merchantId);
					String isExist2 = merchantInfoDao.checkMerchantInfoPK(merchantId);
					//merchantId 主键 - 15位数字、不能重复
					if (!isNumeric || merchantId.length() != 15 || !isExist2.equals("1")) {
						terminalSet.add("<br />第" + index + "行，所属商户编号为" + merchantId + "，不是15位数字或已经存在！<br />");
						break;
					}
					terminalInfo.setMerchantid(POIUtils.getStringFromExcelCell(row.getCell(1)));
					
					terminalInfo.setAddress(POIUtils.getStringFromExcelCell(row.getCell(2)));
					terminalInfo.setDetailaddress(POIUtils.getStringFromExcelCell(row.getCell(3)));
					
					//model POS型号 - 截取POS型号码
					String[] modelCodeArr = (POIUtils.getStringFromExcelCell(row.getCell(4))).split("-");
					String modelCode = modelCodeArr[0];
					terminalInfo.setModel(modelCode);
					
					//type POS类型 - 截取POS类型码
					String[] typeCodeArr = (POIUtils.getStringFromExcelCell(row.getCell(5))).split("-");
					String typeCode = typeCodeArr[0];
					terminalInfo.setType(typeCode);
					
					terminalInfo.setMobilenumber(POIUtils.getStringFromExcelCell(row.getCell(6)));
					terminalInfo.setSnnumber(POIUtils.getStringFromExcelCell(row.getCell(7)));
					
					//时间 - yyyyMMdd
					String installdate = POIUtils.getStringFromExcelCell(row.getCell(8));
					String disabledate = POIUtils.getStringFromExcelCell(row.getCell(9));
					String updatedate = POIUtils.getStringFromExcelCell(row.getCell(10));
					if (!isValidDate(installdate) || !isValidDate(disabledate) || !isValidDate(updatedate)) {
						terminalSet.add("<br />第" + index + "行，终端编号为" + id + "，安装、升级或停用日期格式不正确！<br />");
						break;
					}
					terminalInfo.setInstalldate(installdate);
					terminalInfo.setDisabledate(disabledate);
					terminalInfo.setUpdatedate(updatedate);
					
					terminalInfo.setName(POIUtils.getStringFromExcelCell(row.getCell(11)));
					terminalInfo.setPhonenumber(POIUtils.getStringFromExcelCell(row.getCell(12)));
					
					//status POS机状态 - 截取POS机状态码
					String[] statusCodeArr=POIUtils.getStringFromExcelCell(row.getCell(13)).split("-");
					String statusCode = statusCodeArr[0];
					terminalInfo.setStatus(statusCode);
					
					String deposite = POIUtils.getStringFromExcelCell(row.getCell(14));
					boolean isNumeric2 = org.apache.commons.lang.StringUtils.isNumeric(deposite);
					if (!isNumeric2) {
						terminalSet.add("<br />第" + index + "行，终端编号为" + id + "，POS押金（元）格式不正确！<br />");
						break;
					}
					terminalInfo.setDeposite(deposite);
					
					// 创建新的信息
					try {
						int result = terminalInfoDao.addTerminalInfo(terminalInfo);
						if (result == -1) {  //失败
							throw new Exception("SQLException: addTerminalInfo();");
						} else {  //成功
							number++;
						}
					} catch (Exception e) {
						terminalSet.add("<br />第" + index + "行，终端编号为" + id + "异常：<br />" + e.getMessage());
						log.error("批量增加终端信息异常: ", e);
					}
				}
				String returnVal = "操作成功！ 已成功导入 " + number + " 条记录！<br />";
				if (terminalSet.size() > 0) {
					returnVal += "终端信息失败记录：" + terminalSet + "<br />";
				}
				// 保存
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("批量增加终端信息保存Excel文件Action异常: {}", e);
			try {
				jsonObj.put("result", "操作失败！");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("批量增加终端信息保存Excel文件Action异常: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("批量增加终端信息保存Excel文件,关闭IO流");
				} catch (Exception e) {
					log.error("批量增加终端信息保存Excel文件Action异常: {}", e);
				}
			}
		}
		return null;
	}
	
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// 指定日期格式为四位年两位月份两位日期，注意yyyyMMdd区分大小写；
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			// 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如20070229会被接受，并转换成20070301
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// 如果throw java.text.ParseException 或者 NullPointerException，就说明格式不对
			convertSuccess=false;
		} 
		return convertSuccess;
	}
}
