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
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = merchantInfoDao.getCount(merchantInfoForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<MerchantInfo> merchantInfoList = merchantInfoDao.getMerchantInfoList(pageBean, merchantInfoForm);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			if (merchantInfoList != null && !merchantInfoList.isEmpty()) {
				request.setAttribute("merchantInfoList", merchantInfoList);
			}
			//�̻��������
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			//��ȡʡ
			List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
			//��ȡ��
			List<AreaBean> city_noList = null;
			if (merchantInfoForm.getQprovince() != null) {
				city_noList = merchantInfoDao.getCityByFid(merchantInfoForm.getQprovince());
			}
			//��ȡ��
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
			
			//�̻��������
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			
			//��ȡʡ
			List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinceList", provinList);
			}
			//��ȡ��
	        if (null != merchantInfo.getProvince() && !merchantInfo.getProvince().equals("")) {
	        	List<AreaBean> city_noList = merchantInfoDao.getCityByFid(merchantInfo.getProvince());
	        	if (city_noList != null && !city_noList.isEmpty()) {
	    			request.setAttribute("cityList", city_noList);
	    		}
	        }
	        //��ȡ��
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
	 * ��ʼ�������̻�ҳ��
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
		
		//�̻��������
		SysParameterDao sysParameterDao=new SysParameterDao();
		List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
		if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
			request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
		}
		
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		//��ȡʡ
		List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
		//��ȡ��
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
	 * ����̻���Ϣ
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
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "closewindow";
		
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			try{

				MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
				MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
				int result = merchantInfoDao.addMerchantInfo(merchantInfoForm);
				if (result >= 0) {
					info = "�̻�("+merchantInfoForm.getId()+")��ӳɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣���̻�("+merchantInfoForm.getId()+")���ʧ�ܣ�";
					flushdo = "javascript:history.go(-1)";
					request.setAttribute("result", "1");
				}
				request.setAttribute("info", info);
				request.setAttribute("flushdo", flushdo);
			} catch (Exception e) {
				logger.error("MerchantInfoAction--addMerchantInfo--Exception:", e);
			}
			
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        
		return mapping.findForward("result.jsp");
	}

	/**
	 * @TODO ׼���޸��̻���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
	 */
	public ActionForward preModMerchantInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		MerchantInfo merchantInfo = merchantInfoDao.getTMerchantInfoByID(id);
		if (merchantInfo != null) {
			
			//�̻��������
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			if(consump_categoryList!=null&&!consump_categoryList.isEmpty()){
				request.setAttribute("cardbatch_mer_typeList", consump_categoryList);
			}
			
			//��ȡʡ
			List<AreaBean> provinList = merchantInfoDao.getCityByFid("0");
			if (provinList != null && !provinList.isEmpty()) {
				request.setAttribute("provinceList", provinList);
			}
			//��ȡ��
	        if (null != merchantInfo.getProvince() && !merchantInfo.getProvince().equals("")) {
	        	List<AreaBean> city_noList = merchantInfoDao.getCityByFid(merchantInfo.getProvince());
	        	if (city_noList != null && !city_noList.isEmpty()) {
	    			request.setAttribute("cityList", city_noList);
	    		}
	        }
	        //��ȡ��
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
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo="/merchantInfo.do?method=getMerchantInfoList";
		//String flushdo = "closewindow";
		//�̻�״̬����Ϊ �����ã�1�� ʱ�������̻��������ն�״̬����Ϊ �����ã�1��
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
			info = "�̻�("+merchantInfoForm.getId()+")�޸ĳɹ���";
			request.setAttribute("result", "0");
			request.setAttribute("flushdo", "javascript:history.go(-1)");
		} else {
			info = "���ݿ��쳣���̻�("+merchantInfoForm.getId()+")�޸�ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("flushdo", flushdo);
		request.setAttribute("info", info);
		return mapping.findForward("resultOfUpdate.jsp");
	}
	
	/**
	 * ɾ������ǰ̨�����ݲ��ṩɾ�����ܣ����Դ˹�����δ��ɣ�����ֱ��ʹ�á�
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
		//ɾ���ӱ�
		
		
		//ɾ��ĸ��
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		int result = merchantInfoDao.delMerchantInfo(id); 
		
		if (result >= 0) {
			info = "�̻�("+id+")ɾ���ɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣���̻�("+id+")ɾ��ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetMerchantInfoList");
	}
	
	public ActionForward exportExcelOfMerchantInfo(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		// ����̻���Ϣ�б����м�¼
        MerchantInfoForm merchantInfoForm = (MerchantInfoForm)form;
        List<MerchantInfoExcel> list = merchantInfoDao.getMerchantInfoList(merchantInfoForm);
		
		//�̻��������
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
        String fileName = "�̻���Ϣͳ��-�̻���Ϣ" + datetime + ".xls";
        ExportExcel<MerchantInfoExcel> expexl = new ExportExcel<MerchantInfoExcel>();			
        String filePath =null;
		OutputStream out = null;
		try {
	       
	        fileName = URLEncoder.encode(fileName,"UTF-8");
			filePath = docsPath + FILE_SEPARATOR + fileName;
			System.out.println("[download file path=]"+filePath);
			out = new FileOutputStream(filePath);
			
			String[] headers = { "�̻����","�̻�����","�̻���˾����","�̻�����","�̻�״̬","ʡ","��","��"};
			expexl.exportExcel("�̻���Ϣͳ��-�̻���Ϣ", headers, list, out, "yyyy-MM-dd");			
			
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
	 * �����޸��̻���Ϣ��Ӧ���ն���Ϣ
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
        //�̻���Ϣ�ֶ�ֵ��Ϊ���ַ��������ڴ��������޸ĵ��ն���Ϣ�ֶ�ֵ
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
		
		//ͨ�������̻���Ż�ȡ�ն˱��
		TerminalInfoDao terminalInfoDao = new TerminalInfoDao();
		List<TerminalInfo> terminalInfoList = terminalInfoDao.getTerminalInfoListByMerchantInfoId(id);
		if(terminalInfoList != null && !terminalInfoList.isEmpty()){
			request.setAttribute("terminalInfoList", terminalInfoList);
		}
		
		SysParameterDao sysParameterDao=new SysParameterDao();
		
		//POS�ͺ�
		List<SysParameterBean> pos_modelList=sysParameterDao.getSysParameterList("POS_MODEL");
		if(pos_modelList!=null&&!pos_modelList.isEmpty()){
			request.setAttribute("pos_modelList", pos_modelList);
		}
		
		//POS����
		List<SysParameterBean> pos_typeList=sysParameterDao.getSysParameterList("POS_TYPE");
		if(pos_typeList!=null&&!pos_typeList.isEmpty()){
			request.setAttribute("pos_typeList", pos_typeList);
		}
		
        return mapping.findForward("modTerminalInfos");
	}
	
	/**
	 * �����޸��̻���Ϣ��Ӧ���ն���Ϣ
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
				info = "�̻���Ϣ��Ӧ�ն���Ϣ�޸ĳɹ���";
				request.setAttribute("result", "0");
			} else if (result == -2) {
				info = "��������쳣���̻���Ϣ��Ӧ�ն���Ϣ�޸�ʧ�ܣ�";
				request.setAttribute("result", "1");
			} else {
				info = "���ݿ��쳣���̻���Ϣ��Ӧ�ն���Ϣ�޸�ʧ�ܣ�";
				request.setAttribute("result", "1");
			}
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
	}
	
	// ����
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		log.info("��ʼ����excelģ��");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("�밴ģ���������ݣ��̻����͡��̻�״̬�����������ұ�����ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 8));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "�̻���ţ�15λ���֣�", "�̻�����", "�̻���˾����", "�̻����ͣ���ѡ������", "�̻�״̬����ѡ������", "ʡ�����룩", "�У����룩", "�������룩" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "�����̻�����");
			POIUtils.createTextCell(book, row, (short) 2, "�����̻���˾����");
			
			//�̻��������
			SysParameterDao sysParameterDao=new SysParameterDao();
			List<SysParameterBean> consump_categoryList=sysParameterDao.getSysParameterList("CARDBATCH_MER_TYPE");
			ArrayList<String> typeList = new ArrayList<String>();
			for (SysParameterBean sysParameterBean : consump_categoryList) {
				typeList.add(sysParameterBean.getParam_value() + "-" + sysParameterBean.getParam_name());
			}
			//�̻����� Ĭ��ֵ
			POIUtils.createTextCell(book, row, (short) 3, typeList.get(0));
			//�̻����� �������б�
			POIUtils.creatDropDownLists(sheet, 2, 3, typeList);
			
			ArrayList<String> statusList = new ArrayList<String>();
			statusList.add("0-����");
			statusList.add("1-������");
			statusList.add("2-������");
			//�̻�״̬ Ĭ��ֵ
			POIUtils.createTextCell(book, row, (short) 4, statusList.get(0));
			//�̻�״̬ �������б�
			POIUtils.creatDropDownLists(sheet, 2, 4, statusList);
			
			//ʡ Ĭ��ֵ ����
			POIUtils.createTextCell(book, row, (short) 5, "1");
			//�� Ĭ��ֵ ������
			POIUtils.createTextCell(book, row, (short) 6, "3275");
			//�� Ĭ��ֵ ������
			POIUtils.createTextCell(book, row, (short) 7, "398");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�̻���Ϣ����ģ��", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantInfoAction.downloadTemplate()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
		}
		return null;
	}
	
	// �ϴ�
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		log.info("��ʼ��������");
		//�õ�Dao
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		int number = 0;
		Set merchantSet = new HashSet();
		try {
			writer = response.getWriter();
			MerchantInfoUploadForm uf = (MerchantInfoUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				Sheet sheet = POIUtils.readExcel(byteFile);
				
				//ʡid����
				List<AreaBean> provinceList = merchantInfoDao.getCityByFid("0");
				ArrayList<String> provinceArr = new ArrayList<String>();
				if (null != provinceList && 0 < provinceList.size()) {
					for (AreaBean areaBean : provinceList) {
						provinceArr.add(areaBean.getAid());
					}
				}
				
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// �½�һ��Model
					MerchantInfoForm merchantInfoForm = new MerchantInfoForm();
					
					String id = POIUtils.getStringFromExcelCell(row.getCell(0));
					boolean isNumeric = org.apache.commons.lang.StringUtils.isNumeric(id);
					String isExist = merchantInfoDao.checkMerchantInfoPK(id);
					//id ���� - 15λ���֡������ظ�
					if (!isNumeric || id.length() != 15 || isExist.equals("1")) {
						merchantSet.add("<br />��" + index + "�У��̻����Ϊ" + id + "������15λ���ֻ��Ѿ����ڣ�<br />");
						break;
					}
					merchantInfoForm.setId(id);
					
					String name = POIUtils.getStringFromExcelCell(row.getCell(1));
					merchantInfoForm.setName(name);
					
					String fullname = POIUtils.getStringFromExcelCell(row.getCell(2));
					merchantInfoForm.setFullname(fullname);
					
					//type �̻����� - ��ȡ�̻�������
					String type = POIUtils.getStringFromExcelCell(row.getCell(3));
					String[] typeCodeArr = type.split("-");
					String typeCode = typeCodeArr[0];
					merchantInfoForm.setType(typeCode);
					
					//status �̻�״̬ - ��ȡ�̻�״̬��
					String status = POIUtils.getStringFromExcelCell(row.getCell(4));
					String[] statusCodeArr = status.split("-");
					String statusCode = statusCodeArr[0];
					merchantInfoForm.setStatus(statusCode);
					
					//province ʡ - ����
					String province = POIUtils.getStringFromExcelCell(row.getCell(5));
					boolean provinceExist = provinceArr.contains(province);
					if (!provinceExist) {
						merchantSet.add("<br />��" + index + "�У��̻����Ϊ" + id + "��ʡ�����룩�����ڣ�<br />");
						break;
					}
					merchantInfoForm.setProvince(province);
					
					//city �� - ���ڣ���ʡ��ȷ
					String city = POIUtils.getStringFromExcelCell(row.getCell(6));
					//��id����
					List<AreaBean> cityList = merchantInfoDao.getCityByFid(province);
					ArrayList<String> cityArr = new ArrayList<String>();
					if (null != cityList && 0 < cityList.size()) {
						for (AreaBean areaBean : cityList) {
							cityArr.add(areaBean.getAid());
						}
					}
					boolean cityExist = cityArr.contains(city);
					if (!cityExist) {
						merchantSet.add("<br />��" + index + "�У��̻����Ϊ" + id + "���У����룩" + city + "������ʡ�����룩" + province + "��<br />");
						break;
					}
					merchantInfoForm.setCity(city);
					
					//area �� - ���ڣ�������ȷ
					String area = POIUtils.getStringFromExcelCell(row.getCell(7));
					//��id����
					List<AreaBean> areaList = merchantInfoDao.getCityByFid(city);
					ArrayList<String> areaArr = new ArrayList<String>();
					if (null != areaList && 0 < areaList.size()) {
						for (AreaBean areaBean : areaList) {
							areaArr.add(areaBean.getAid());
						}
					}
					boolean areaExist = areaArr.contains(area);
					if (!areaExist) {
						merchantSet.add("<br />��" + index + "�У��̻����Ϊ" + id + "���������룩" + area + "�������У����룩" + city + "��<br />");
						break;
					}
					merchantInfoForm.setArea(area);
					
					// �����µ���Ϣ
					try {
						int result = merchantInfoDao.addMerchantInfo(merchantInfoForm);
						if (result == -1) {  //ʧ��
							throw new Exception("SQLException: addMerchantInfo();");
						} else {  //�ɹ�
							number++;
						}
					} catch (Exception e) {
						merchantSet.add("<br>��" + index + "��,�̻����Ϊ" + id + "�쳣:<br>" + e.getMessage());
						log.error("���������̻���Ϣ�쳣: ", e);
					}
				}
				String returnVal = "�����ɹ��� �ѳɹ����� " + number + " ����¼��<br>";
				if (merchantSet.size() > 0) {
					returnVal += "�̻���Ϣʧ�ܼ�¼��" + merchantSet + "<br>";
				}
				// ����
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("���������̻���Ϣ����Excel�ļ�Action�쳣: {}", e);
			try {
				jsonObj.put("result", "����ʧ�ܣ�");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("���������̻���Ϣ����Excel�ļ�Action�쳣: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("���������̻���Ϣ����Excel�ļ�,�ر�IO��");
				} catch (Exception e) {
					log.error("���������̻���Ϣ����Excel�ļ�Action�쳣: {}", e);
				}
			}
		}
		return null;
	}
	
}
