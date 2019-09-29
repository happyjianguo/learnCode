package com.pay.merInfoStatistics.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.Region;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.merInfoStatistics.bean.MerchantInfo;
import com.pay.merInfoStatistics.bean.MerchantInfoExcel;
import com.pay.merInfoStatistics.bean.TerminalInfo;
import com.pay.merInfoStatistics.dao.MerchantInfoDao;
import com.pay.merInfoStatistics.dao.TerminalInfoDao;
import com.pay.merInfoStatistics.struts.form.MerchantInfoForm;
import com.pay.merInfoStatistics.struts.form.MerchantInfoUploadForm;
import com.pay.merchant.bean.AreaBean;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.util.Constant;
import com.pay.util.ExportExcel;
import com.pay.util.POIUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class MerchantInfoAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(MerchantInfoAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	public ActionForward getMerchantInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
			MerchantInfoForm merchantInfoForm = (MerchantInfoForm) form;
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = merchantInfoDao.getCount(merchantInfoForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<MerchantInfo> merchantInfoList = merchantInfoDao.getMerchantInfoList(pageBean, merchantInfoForm);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if (merchantInfoList != null && !merchantInfoList.isEmpty()) {
				request.setAttribute("merchantInfoList", merchantInfoList);
			}
			//商户类型码表
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			//获取省
			List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
			//获取市
			List<AreaBean> city_noList = null;
			if (merchantInfoForm.getQprovince() != null) {
				city_noList = merchantInfoDao.getCityByFid(merchantInfoForm.getQprovince());
			}
			//获取区
			List<AreaBean> areaList = null;
			if (merchantInfoForm.getQcity() != null) {
				areaList = merchantInfoDao.getCityByFid(merchantInfoForm.getQcity());
			}
			
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinList", provinList);
			}
			if (city_noList != null && !city_noList.isEmpty()) {
				request.setAttribute("city_noList", city_noList);
			}
			if (areaList != null && !areaList.isEmpty()) {
				request.setAttribute("areaList", areaList);
			}
		} catch (Exception e) {
			logger.error("MerchantInfoAction--getMerchantInfoList--Exception:",
					e);
		}
		return mapping.findForward("showMerchantInfoList");
	}

	public ActionForward preQueryMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		MerchantInfo merchantInfo = merchantInfoDao.getTMerchantInfoByID(id);		
		if (merchantInfo != null) {
			
			//商户类型码表
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			
			//获取省
			List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinceList", provinList);
			}
			//获取市
	        if (null != merchantInfo.getProvince() && !merchantInfo.getProvince().equals("")) {
	        	List<AreaBean> city_noList = merchantInfoDao.getCityByFid(merchantInfo.getProvince());
	        	if (city_noList != null && !city_noList.isEmpty()) {
	    			request.setAttribute("cityList", city_noList);
	    		}
	        }
	        //获取区
	        if (null != merchantInfo.getCity() && !merchantInfo.getCity().equals("")) {
	        	List<AreaBean> zoneList = merchantInfoDao.getCityByFid(merchantInfo.getCity());
	        	if (zoneList != null && !zoneList.isEmpty()) {
	     			request.setAttribute("areaList", zoneList);
	     		}
	        }
			
			MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
			try {
				BeanUtils.copyProperties(merchantInfoForm, merchantInfo);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}
		return mapping.findForward("showMerchantInfo");
	}

	/**
	 * 初始化增加商户页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		//商户类型码表
		SysParameterDao sysParameterDao=new SysParameterDao();
		List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
		if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
			request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
		}
		
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		//获取省
		List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
		//获取市
		List<AreaBean> city_noList = merchantInfoDao.getCityByFid("1");
		if(city_noList==null){
			city_noList = merchantInfoDao.getBJCity();
		}
		if (provinList != null && !provinList.isEmpty()) {
			request.setAttribute("provinList", provinList);
		}
		if (city_noList != null && !city_noList.isEmpty()) {
			request.setAttribute("city_noList", city_noList);
		}
		
		return mapping.findForward("addMerchantInfo");
	}

	/**
	 * 添加商户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		String info = "";
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "closewindow";
		
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			try{

				MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
				MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
				int result = merchantInfoDao.addMerchantInfo(merchantInfoForm);
				if (result >= 0) {
					info = "商户("+merchantInfoForm.getId()+")添加成功！";
					request.setAttribute("result", "0");
				} else {
					info = "数据库异常，商户("+merchantInfoForm.getId()+")添加失败！";
					flushdo = "javascript:history.go(-1)";
					request.setAttribute("result", "1");
				}
				request.setAttribute("info", info);
				request.setAttribute("flushdo", flushdo);
			} catch (Exception e) {
				logger.error("MerchantInfoAction--addMerchantInfo--Exception:", e);
			}
			
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        
		return mapping.findForward("result.jsp");
	}

	/**
	 * @TODO 准备修改商户信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 跳转路径
	 */
	public ActionForward preModMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		MerchantInfo merchantInfo = merchantInfoDao.getTMerchantInfoByID(id);
		if (merchantInfo != null) {
			
			//商户类型码表
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			
			//获取省
			List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinceList", provinList);
			}
			//获取市
	        if (null != merchantInfo.getProvince() && !merchantInfo.getProvince().equals("")) {
	        	List<AreaBean> city_noList = merchantInfoDao.getCityByFid(merchantInfo.getProvince());
	        	if (city_noList != null && !city_noList.isEmpty()) {
	    			request.setAttribute("cityList", city_noList);
	    		}
	        }
	        //获取区
	        if (null != merchantInfo.getCity() && !merchantInfo.getCity().equals("")) {
	        	List<AreaBean> zoneList = merchantInfoDao.getCityByFid(merchantInfo.getCity());
	        	if (zoneList != null && !zoneList.isEmpty()) {
	     			request.setAttribute("areaList", zoneList);
	     		}
	        }
			
			MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
			try {
				BeanUtils.copyProperties(merchantInfoForm, merchantInfo);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}		
		return mapping.findForward("editMerchantInfo");
	}

	public ActionForward modMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
		int result = merchantInfoDao.updMerchantInfo(merchantInfoForm);
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo="/merchantInfo.do?method=getMerchantInfoList";
		//String flushdo = "closewindow";
		//商户状态设置为 不可用（1） 时，所属商户的所以终端状态设置为 不可用（1）
		if (merchantInfoForm.getStatus() != null && "1".equals(merchantInfoForm.getStatus())) {
			TerminalInfoDao terminalInfoDao = new TerminalInfoDao();
			List<TerminalInfo> list = terminalInfoDao.getTerminalInfoListByMerchantInfoId(merchantInfoForm.getId());
			String ids = "";
			for (int i = 0; i < list.size(); i++) {
				if (i != 0) {
					ids += ",";
				}
				ids += list.get(i).getId();
			}
			if (!"".equals(ids)) {
				result = terminalInfoDao.updTerminalInfo(merchantInfoForm.getId(), null, null, null, null, null, null, null, null, null, null, null,"1", null, ids);
			}
		}
		
		if (result >= 0) {
			info = "商户("+merchantInfoForm.getId()+")修改成功！";
			request.setAttribute("result", "0");
			request.setAttribute("flushdo", "javascript:history.go(-1)");
		} else {
			info = "数据库异常，商户("+merchantInfoForm.getId()+")修改失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("flushdo", flushdo);
		request.setAttribute("info", info);
		return mapping.findForward("resultOfUpdate.jsp");
	}
	
	/**
	 * 删除，因前台界面暂不提供删除功能，所以此功能尚未完成，不可直接使用。
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String id = ParamUtils.getParameter(request, "id");
		//删除子表
		
		
		//删除母表
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		int result = merchantInfoDao.delMerchantInfo(id); 
		
		if (result >= 0) {
			info = "商户("+id+")删除成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，商户("+id+")删除失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetMerchantInfoList");
	}
	
	public ActionForward exportExcelOfMerchantInfo(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		// 获得商户信息列表所有记录
        MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
        List<MerchantInfoExcel> list = merchantInfoDao.getMerchantInfoList(merchantInfoForm);
		
		//商户类型码表
		SysParameterDao sysParameterDao=new SysParameterDao();
		Map<String,String> consumpCategoryMap=sysParameterDao.getSysParamMap("CARDBATCH_MER_TYPE");
		MerchantInfoExcel fb = null;
		if(consumpCategoryMap!=null&&consumpCategoryMap.size()>0){
			for(int i=0;i<list.size();i++){
				fb = (MerchantInfoExcel)list.get(i);
				if(fb.getType_desc()!=null&&!"".equals(fb.getType_desc())){
					fb.setType_desc(consumpCategoryMap.get(fb.getType_desc())==null?"":consumpCategoryMap.get(fb.getType_desc()));
				}
			}					
		} 
		String docsPath = session.getServletContext().getRealPath("docs");
        String datetime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String fileName = "商户信息统计-商户信息" + datetime + ".xls";
        ExportExcel<MerchantInfoExcel> expexl = new ExportExcel<MerchantInfoExcel>();			
        String filePath =null;
		OutputStream out = null;
		try {
	       
	        fileName = URLEncoder.encode(fileName,"UTF-8");
			filePath = docsPath + FILE_SEPARATOR + fileName;
			System.out.println("[download file path=]"+filePath);
			out = new FileOutputStream(filePath);
			
			String[] headers = { "商户编号","商户名称","商户公司名称","商户类型","商户状态","省","市","区"};
			expexl.exportExcel("商户信息统计-商户信息", headers, list, out, "yyyy-MM-dd");			
			
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
	
	/**
	 * 批量修改商户信息对应的终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preModTerminalInfos(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
        MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
        String id = ParamUtils.getParameter(request, "id");  
        request.setAttribute("id", id);
        MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
        MerchantInfo merchantInfo = merchantInfoDao.getTMerchantInfoByID(id);
        //商户信息字段值设为空字符串，用于带回批量修改的终端信息字段值
        merchantInfo.setFullname("");
        merchantInfo.setType("");
        merchantInfo.setStatus("");
        merchantInfo.setProvince("");
        merchantInfo.setCity("");
        merchantInfo.setArea("");
        try {
            if (merchantInfo != null) {
                BeanUtils.copyProperties(merchantInfoForm, merchantInfo);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
		
		//通过所属商户编号获取终端编号
		TerminalInfoDao terminalInfoDao = new TerminalInfoDao();
		List<TerminalInfo> terminalInfoList = terminalInfoDao.getTerminalInfoListByMerchantInfoId(id);
		if(terminalInfoList != null && !terminalInfoList.isEmpty()){
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
		
        return mapping.findForward("modTerminalInfos");
	}
	
	/**
	 * 批量修改商户信息对应的终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward modTerminalInfos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){		
		String info = "";
		
		String flushdo="/merchantInfo.do?method=getMerchantInfoList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
		
        	TerminalInfoDao terminalInfoDao = new TerminalInfoDao();
    		MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
    		int result = terminalInfoDao.updTerminalInfo(merchantInfoForm.getId().trim(),
    				merchantInfoForm.getQprovince().trim(),merchantInfoForm.getQcity().trim(),merchantInfoForm.getQarea().trim(),
    				merchantInfoForm.getQtype().trim(),merchantInfoForm.getArea().trim(),merchantInfoForm.getFullname().trim(),
    				merchantInfoForm.getType().trim(),merchantInfoForm.getQname().trim(),merchantInfoForm.getQfullname().trim(),
    				merchantInfoForm.getStatus().trim(),merchantInfoForm.getProvince().trim(),merchantInfoForm.getQstatus().trim(),
    				merchantInfoForm.getCity().trim(),
    				merchantInfoForm.getQid().trim());
			if (result >= 0) {
				info = "商户信息对应终端信息修改成功！";
				request.setAttribute("result", "0");
			} else if (result == -2) {
				info = "传入参数异常，商户信息对应终端信息修改失败！";
				request.setAttribute("result", "1");
			} else {
				info = "数据库异常，商户信息对应终端信息修改失败！";
				request.setAttribute("result", "1");
			}
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
	}
	
	// 导出
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
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
			cell.setCellValue("请按模板整理数据！商户类型、商户状态、地区必填且必须存在！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 8));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "商户编号（15位数字）", "商户名称", "商户公司名称", "商户类型（单选下拉框）", "商户状态（单选下拉框）", "省（代码）", "市（代码）", "区（代码）" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "测试商户名称");
			POIUtils.createTextCell(book, row, (short) 2, "测试商户公司名称");
			
			//商户类型码表
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			ArrayList<String> typeList = new ArrayList<String>();
			for (SysParameterBean sysParameterBean : consump_categoryList) {
				typeList.add(sysParameterBean.getParam_value() + "-" + sysParameterBean.getParam_name());
			}
			//商户类型 默认值
			POIUtils.createTextCell(book, row, (short) 3, typeList.get(0));
			//商户类型 下拉框列表
			POIUtils.creatDropDownLists(sheet, 2, 3, typeList);
			
			ArrayList<String> statusList = new ArrayList<String>();
			statusList.add("0-可用");
			statusList.add("1-不可用");
			statusList.add("2-黑名单");
			//商户状态 默认值
			POIUtils.createTextCell(book, row, (short) 4, statusList.get(0));
			//商户状态 下拉框列表
			POIUtils.creatDropDownLists(sheet, 2, 4, statusList);
			
			//省 默认值 北京
			POIUtils.createTextCell(book, row, (short) 5, "1");
			//市 默认值 北京市
			POIUtils.createTextCell(book, row, (short) 6, "3275");
			//区 默认值 东城区
			POIUtils.createTextCell(book, row, (short) 7, "398");
			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("商户信息导入模板", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantInfoAction.downloadTemplate()调用出现异常。");
			log.error(e, e.fillInStackTrace());
		}
		return null;
	}
	
	// 上传
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		log.info("开始导入数据");
		//得到Dao
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		int number = 0;
		Set merchantSet = new HashSet();
		try {
			writer = response.getWriter();
			MerchantInfoUploadForm uf = (MerchantInfoUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
			} else {
				Sheet sheet = POIUtils.readExcel(byteFile);
				
				//省id集合
				List<AreaBean> provinceList = merchantInfoDao.getCityByFid("0");
				ArrayList<String> provinceArr = new ArrayList<String>();
				if (null != provinceList && 0 < provinceList.size()) {
					for (AreaBean areaBean : provinceList) {
						provinceArr.add(areaBean.getAid());
					}
				}
				
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// 新建一个Model
					MerchantInfoForm merchantInfoForm = new MerchantInfoForm();
					
					String id = POIUtils.getStringFromExcelCell(row.getCell(0));
					boolean isNumeric = org.apache.commons.lang.StringUtils.isNumeric(id);
					String isExist = merchantInfoDao.checkMerchantInfoPK(id);
					//id 主键 - 15位数字、不能重复
					if (!isNumeric || id.length() != 15 || isExist.equals("1")) {
						merchantSet.add("<br />第" + index + "行，商户编号为" + id + "，不是15位数字或已经存在！<br />");
						break;
					}
					merchantInfoForm.setId(id);
					
					String name = POIUtils.getStringFromExcelCell(row.getCell(1));
					merchantInfoForm.setName(name);
					
					String fullname = POIUtils.getStringFromExcelCell(row.getCell(2));
					merchantInfoForm.setFullname(fullname);
					
					//type 商户类型 - 截取商户类型码
					String type = POIUtils.getStringFromExcelCell(row.getCell(3));
					String[] typeCodeArr = type.split("-");
					String typeCode = typeCodeArr[0];
					merchantInfoForm.setType(typeCode);
					
					//status 商户状态 - 截取商户状态码
					String status = POIUtils.getStringFromExcelCell(row.getCell(4));
					String[] statusCodeArr = status.split("-");
					String statusCode = statusCodeArr[0];
					merchantInfoForm.setStatus(statusCode);
					
					//province 省 - 存在
					String province = POIUtils.getStringFromExcelCell(row.getCell(5));
					boolean provinceExist = provinceArr.contains(province);
					if (!provinceExist) {
						merchantSet.add("<br />第" + index + "行，商户编号为" + id + "，省（代码）不存在！<br />");
						break;
					}
					merchantInfoForm.setProvince(province);
					
					//city 市 - 存在，且省正确
					String city = POIUtils.getStringFromExcelCell(row.getCell(6));
					//市id集合
					List<AreaBean> cityList = merchantInfoDao.getCityByFid(province);
					ArrayList<String> cityArr = new ArrayList<String>();
					if (null != cityList && 0 < cityList.size()) {
						for (AreaBean areaBean : cityList) {
							cityArr.add(areaBean.getAid());
						}
					}
					boolean cityExist = cityArr.contains(city);
					if (!cityExist) {
						merchantSet.add("<br />第" + index + "行，商户编号为" + id + "，市（代码）" + city + "不属于省（代码）" + province + "！<br />");
						break;
					}
					merchantInfoForm.setCity(city);
					
					//area 区 - 存在，且市正确
					String area = POIUtils.getStringFromExcelCell(row.getCell(7));
					//区id集合
					List<AreaBean> areaList = merchantInfoDao.getCityByFid(city);
					ArrayList<String> areaArr = new ArrayList<String>();
					if (null != areaList && 0 < areaList.size()) {
						for (AreaBean areaBean : areaList) {
							areaArr.add(areaBean.getAid());
						}
					}
					boolean areaExist = areaArr.contains(area);
					if (!areaExist) {
						merchantSet.add("<br />第" + index + "行，商户编号为" + id + "，区（代码）" + area + "不属于市（代码）" + city + "！<br />");
						break;
					}
					merchantInfoForm.setArea(area);
					
					// 创建新的信息
					try {
						int result = merchantInfoDao.addMerchantInfo(merchantInfoForm);
						if (result == -1) {  //失败
							throw new Exception("SQLException: addMerchantInfo();");
						} else {  //成功
							number++;
						}
					} catch (Exception e) {
						merchantSet.add("<br>第" + index + "行,商户编号为" + id + "异常:<br>" + e.getMessage());
						log.error("批量增加商户信息异常: ", e);
					}
				}
				String returnVal = "操作成功！ 已成功导入 " + number + " 条记录！<br>";
				if (merchantSet.size() > 0) {
					returnVal += "商户信息失败记录：" + merchantSet + "<br>";
				}
				// 保存
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("批量增加商户信息保存Excel文件Action异常: {}", e);
			try {
				jsonObj.put("result", "操作失败！");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("批量增加商户信息保存Excel文件Action异常: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("批量增加商户信息保存Excel文件,关闭IO流");
				} catch (Exception e) {
					log.error("批量增加商户信息保存Excel文件Action异常: {}", e);
				}
			}
		}
		return null;
	}
	
}
