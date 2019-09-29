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
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = dao.getCount(terminalInfoForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<TerminalInfo> terminalInfoList = dao.getTerminalInfoList(pageBean, terminalInfoForm);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			if (terminalInfoList != null && !terminalInfoList.isEmpty()) {
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
	 * ��ʼ�������ն�ҳ��
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
		
		return mapping.findForward("addTerminalInfo");
	}

	/**
	 * ����ն���Ϣ
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
			//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
	        String flushdo = "/terminalInfo.do?method=getTerminalInfoList";
			TerminalInfoDao dao = new TerminalInfoDao();
			TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
			int result = dao.addTerminalInfo(terminalInfoForm);
			if (result >= 0) {
				info = "�ն�("+terminalInfoForm.getId()+")��ӳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣���ն�("+terminalInfoForm.getId()+")���ʧ�ܣ�";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", info);
			request.setAttribute("flushdo", flushdo);
			
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
		} catch (Exception e) {
			logger.error("TerminalInfoAction--addTerminalInfo--Exception:", e);
		}
		
		return mapping.findForward("addTerminalInfo");
	}

	/**
	 * @TODO ׼���޸��ն���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
	 */
	public ActionForward preModTerminalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		TerminalInfoDao dao = new TerminalInfoDao();
		TerminalInfo terminalInfo = dao.getTerminalInfoByID(id);
		if (terminalInfo != null) {
			
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
			
			//�����̻��б�
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
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/terminalInfo.do?method=getTerminalInfoList";
		//String flushdo = "closewindow";
		if (result >= 0) {
			info = "�ն�("+terminalInfoForm.getId()+")�޸ĳɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣���ն�("+terminalInfoForm.getId()+")�޸�ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("flushdo", flushdo);
		request.setAttribute("info", info);
		return mapping.findForward("resultOfUpdate.jsp");
	}
	
	/**
	 * ɾ��
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
			info = "�ն�("+id+")ɾ���ɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣���ն�("+id+")ɾ��ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTerminalInfoList");
	}
	
	public ActionForward exportExcelOfTerminalInfo(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TerminalInfoDao dao = new TerminalInfoDao();
		// ����ն���Ϣ�б����м�¼
        TerminalInfoForm terminalInfoForm = (TerminalInfoForm)form;
        List<TerminalInfoExcel> list = dao.getTerminalInfoList(terminalInfoForm);
		
        SysParameterDao sysParameterDao=new SysParameterDao();
        
		//POS�ͺ�
		Map<String,String> pos_modelMap=sysParameterDao.getSysParamMap("POS_MODEL");
		//POS����
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
		String fileName = "�̻���Ϣͳ��-�ն���Ϣ" + datetime + ".xls";
		
		String filePath =null;
		ExportExcel<TerminalInfoExcel> expexl = new ExportExcel<TerminalInfoExcel>();			

		OutputStream out = null;
		try {
			fileName = URLEncoder.encode(fileName,"UTF-8");
			filePath  = docsPath + FILE_SEPARATOR + fileName;
			System.out.println("[download file path=]"+filePath);
			out = new FileOutputStream(filePath);
						String[] headers = { "�ն˱��","�����̻�","�̻�װ����ַ","���̼���̨��ַ","POS�ͺ�","POS����","����POS�ֻ���","POS��S/N��","��װ����","ͣ������","��������","��ϵ��","�ŵ�绰","POS��״̬","POSѺ��Ԫ��"};
			expexl.exportExcel("�̻���Ϣͳ��-�ն���Ϣ", headers, list, out, "yyyy-MM-dd");			
			
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
	
	// ����������excelģ�壩
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
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
			cell.setCellValue("�밴ģ���������ݣ��ն˱�š������̻����̻�װ����ַ�����̼���̨��ַ��POS�ͺš�POS���͡�POS��S/N�š���װ���ڡ�ͣ�����ڡ��������ڡ���ϵ�ˡ��ŵ�绰��POS��״̬��POSѺ��Ԫ�������������̻���ű�����ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 15));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "�ն˱�ţ�8λ���֣���λ��ΪA��","�����̻���ţ�15λ���֣�","�̻�װ����ַ","���̼���̨��ַ","POS�ͺţ���ѡ������","POS���ͣ���ѡ������","����POS�ֻ��ţ��Ǳ������д��Ϊ���֣�","POS��S/N��","��װ���ڣ�yyyyMMdd��","ͣ�����ڣ�yyyyMMdd��","�������ڣ�yyyyMMdd��","��ϵ��","�ŵ�绰�����֣�","POS��״̬����ѡ������","POSѺ�����֣���λ��Ԫ��" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			
			//������
			row = sheet.createRow(2);
			POIUtils.createCell(row, (short) 0, "A1234567");
			POIUtils.createCell(row, (short) 1, "123456789012345");
			POIUtils.createCell(row, (short) 2, "�̻�װ����ַ");
			POIUtils.createCell(row, (short) 3, "���̼���̨��ַ");
			
	        SysParameterDao sysParameterDao=new SysParameterDao();
			//POS�ͺ����
			Map<String,String> pos_modelMap=sysParameterDao.getSysParamMap("POS_MODEL");
			ArrayList<String> modelList = new ArrayList<String>();
			for (Map.Entry<String, String> entry :pos_modelMap.entrySet()) {
				modelList.add(entry.getKey() + "-" + entry.getValue());
			}
			//POS�ͺ� Ĭ��ֵ
			POIUtils.createTextCell(book, row, (short) 4, modelList.get(0));
			//POS�ͺ� �������б�
			POIUtils.creatDropDownLists(sheet, 2, 4, modelList);
			
			//POS�������
			Map<String,String> pos_typeMap=sysParameterDao.getSysParamMap("POS_TYPE");
			ArrayList<String> typeList = new ArrayList<String>();
			for (Map.Entry<String, String> entry :pos_typeMap.entrySet()) {
				typeList.add(entry.getKey() + "-" + entry.getValue());
			}
			//POS���� Ĭ��ֵ
			POIUtils.createTextCell(book, row, (short) 5, typeList.get(0));
			//POS���� �������б�
			POIUtils.creatDropDownLists(sheet, 2, 5, typeList);
			
			POIUtils.createCell(row, (short) 6, "12345678901");
			POIUtils.createCell(row, (short) 7, "POS��S/N��");
			POIUtils.createCell(row, (short) 8, "20160407");
			POIUtils.createCell(row, (short) 9, "20160407");
			POIUtils.createCell(row, (short) 10, "20160407");
			POIUtils.createCell(row, (short) 11, "��ϵ��");
			POIUtils.createCell(row, (short) 12, "12345678901");
			
			ArrayList<String> statusList = new ArrayList<String>();
			statusList.add("0-����");
			statusList.add("1-������");
			statusList.add("2-������");
			//POS��״̬ Ĭ��ֵ
			POIUtils.createTextCell(book, row, (short) 13, statusList.get(0));
			//POS��״̬ �������б�
			POIUtils.creatDropDownLists(sheet, 2, 13, statusList);
			
			POIUtils.createCell(row, (short) 14, "100");

			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("�ն���Ϣ����ģ��", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TerminalInfoAction.downloadTemplate()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
		}
		return null;
	}
		
	// �ϴ�(���뵽���ݿ�)
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response){
		log.info("��ʼ��������");
		TerminalInfoDao terminalInfoDao = new TerminalInfoDao();
		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		int number = 0;
		Set terminalSet = new HashSet();
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		try {
			writer = response.getWriter();
			TerminalInfoUploadForm uf = (TerminalInfoUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			List<String> list=new ArrayList<String>();

			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
			} else {
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// �½�һ��Model
					TerminalInfoForm terminalInfo = new TerminalInfoForm();
					String id = POIUtils.getStringFromExcelCell(row.getCell(0));
					//id ���� - 8λ��������ĸ��ΪA�������ظ�
					if (StringUtil.isNull(id) || id.length() != 8) {
						terminalSet.add("<br />��" + index + "�У��ն˱��Ϊ" + id + "����������ĸ��ΪA��8λ�ַ������Ѿ����ڣ�<br />");
						break;
					}
					String id1 = id.substring(0, 1);
					String id2to8 = id.substring(1);
					String isExist = terminalInfoDao.checkTerminalInfoPK(id);
					//id ���� - 8λ��������ĸ��ΪA�������ظ�
					if ((!id1.equals("A") && !org.apache.commons.lang.StringUtils.isNumeric(id)) || !org.apache.commons.lang.StringUtils.isNumeric(id2to8) || isExist.equals("1")) {
						terminalSet.add("<br />��" + index + "�У��ն˱��Ϊ" + id + "����������ĸ��ΪA��8λ�ַ������Ѿ����ڣ�<br />");
						break;
					} 
					terminalInfo.setId(id);
					
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(1));
					boolean isNumeric = org.apache.commons.lang.StringUtils.isNumeric(merchantId);
					String isExist2 = merchantInfoDao.checkMerchantInfoPK(merchantId);
					//merchantId ���� - 15λ���֡������ظ�
					if (!isNumeric || merchantId.length() != 15 || !isExist2.equals("1")) {
						terminalSet.add("<br />��" + index + "�У������̻����Ϊ" + merchantId + "������15λ���ֻ��Ѿ����ڣ�<br />");
						break;
					}
					terminalInfo.setMerchantid(POIUtils.getStringFromExcelCell(row.getCell(1)));
					
					terminalInfo.setAddress(POIUtils.getStringFromExcelCell(row.getCell(2)));
					terminalInfo.setDetailaddress(POIUtils.getStringFromExcelCell(row.getCell(3)));
					
					//model POS�ͺ� - ��ȡPOS�ͺ���
					String[] modelCodeArr = (POIUtils.getStringFromExcelCell(row.getCell(4))).split("-");
					String modelCode = modelCodeArr[0];
					terminalInfo.setModel(modelCode);
					
					//type POS���� - ��ȡPOS������
					String[] typeCodeArr = (POIUtils.getStringFromExcelCell(row.getCell(5))).split("-");
					String typeCode = typeCodeArr[0];
					terminalInfo.setType(typeCode);
					
					terminalInfo.setMobilenumber(POIUtils.getStringFromExcelCell(row.getCell(6)));
					terminalInfo.setSnnumber(POIUtils.getStringFromExcelCell(row.getCell(7)));
					
					//ʱ�� - yyyyMMdd
					String installdate = POIUtils.getStringFromExcelCell(row.getCell(8));
					String disabledate = POIUtils.getStringFromExcelCell(row.getCell(9));
					String updatedate = POIUtils.getStringFromExcelCell(row.getCell(10));
					if (!isValidDate(installdate) || !isValidDate(disabledate) || !isValidDate(updatedate)) {
						terminalSet.add("<br />��" + index + "�У��ն˱��Ϊ" + id + "����װ��������ͣ�����ڸ�ʽ����ȷ��<br />");
						break;
					}
					terminalInfo.setInstalldate(installdate);
					terminalInfo.setDisabledate(disabledate);
					terminalInfo.setUpdatedate(updatedate);
					
					terminalInfo.setName(POIUtils.getStringFromExcelCell(row.getCell(11)));
					terminalInfo.setPhonenumber(POIUtils.getStringFromExcelCell(row.getCell(12)));
					
					//status POS��״̬ - ��ȡPOS��״̬��
					String[] statusCodeArr=POIUtils.getStringFromExcelCell(row.getCell(13)).split("-");
					String statusCode = statusCodeArr[0];
					terminalInfo.setStatus(statusCode);
					
					String deposite = POIUtils.getStringFromExcelCell(row.getCell(14));
					boolean isNumeric2 = org.apache.commons.lang.StringUtils.isNumeric(deposite);
					if (!isNumeric2) {
						terminalSet.add("<br />��" + index + "�У��ն˱��Ϊ" + id + "��POSѺ��Ԫ����ʽ����ȷ��<br />");
						break;
					}
					terminalInfo.setDeposite(deposite);
					
					// �����µ���Ϣ
					try {
						int result = terminalInfoDao.addTerminalInfo(terminalInfo);
						if (result == -1) {  //ʧ��
							throw new Exception("SQLException: addTerminalInfo();");
						} else {  //�ɹ�
							number++;
						}
					} catch (Exception e) {
						terminalSet.add("<br />��" + index + "�У��ն˱��Ϊ" + id + "�쳣��<br />" + e.getMessage());
						log.error("���������ն���Ϣ�쳣: ", e);
					}
				}
				String returnVal = "�����ɹ��� �ѳɹ����� " + number + " ����¼��<br />";
				if (terminalSet.size() > 0) {
					returnVal += "�ն���Ϣʧ�ܼ�¼��" + terminalSet + "<br />";
				}
				// ����
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("���������ն���Ϣ����Excel�ļ�Action�쳣: {}", e);
			try {
				jsonObj.put("result", "����ʧ�ܣ�");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("���������ն���Ϣ����Excel�ļ�Action�쳣: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("���������ն���Ϣ����Excel�ļ�,�ر�IO��");
				} catch (Exception e) {
					log.error("���������ն���Ϣ����Excel�ļ�Action�쳣: {}", e);
				}
			}
		}
		return null;
	}
	
	public static boolean isValidDate(String str) {
		boolean convertSuccess=true;
		// ָ�����ڸ�ʽΪ��λ����λ�·���λ���ڣ�ע��yyyyMMdd���ִ�Сд��
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			// ����lenientΪfalse. ����SimpleDateFormat��ȽϿ��ɵ���֤���ڣ�����20070229�ᱻ���ܣ���ת����20070301
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// ���throw java.text.ParseException ���� NullPointerException����˵����ʽ����
			convertSuccess=false;
		} 
		return convertSuccess;
	}
}
