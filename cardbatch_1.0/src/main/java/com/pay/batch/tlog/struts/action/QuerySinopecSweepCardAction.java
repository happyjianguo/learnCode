/**
 *����:com.pay.batch.tlog.struts.action
 *����:package com.pay.batch.tlog.struts.action;
 */
package com.pay.batch.tlog.struts.action;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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

import com.pay.batch.tlog.bean.QuerySinopecSweepCardBean;
import com.pay.batch.tlog.dao.QuerySinopecSweepCardDao;
import com.pay.batch.tlog.struts.form.SinopecSweepCardUploadForm;
import com.pay.merInfoStatistics.bean.MerchantInfo;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.POIUtils;
import com.pay.util.PageBean;

/**
 * QuerySinopecSweepCardAction.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��5��16��
 * ����:��ʯ��ɨ��ˢ��ͳ��
 */
public class QuerySinopecSweepCardAction extends BaseDispatchAction{
	
	private static final Logger logger = Logger.getLogger(QuerySinopecSweepCardAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	
	public ActionForward toQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = 0;
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<MerchantInfo> merchantInfoList = new LinkedList<MerchantInfo>();
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			request.setAttribute("info", "�뵼���ѯģ����в�ѯ");
			if (merchantInfoList != null && !merchantInfoList.isEmpty()) {
				request.setAttribute("merchantInfoList", merchantInfoList);
			}
		} catch (Exception e) {
			logger.error("QuerySinopecSweepCardAction--toQuery--Exception:", e);
		}
		return mapping.findForward("showSinopecSweepCardList");
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
			cell.setCellValue("�밴ģ���������ݣ��̻��š��ն˺š���ֹʱ������ұ�����ڣ�ע�⣺��ֹʱ�䲻�ܳ���31�죬ʱ���ʽΪYYYYMMDD");
//			cell.setCellValue("�밴ģ���������ݣ��̻����͡��̻�״̬�����������ұ�����ڣ�");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 4));
			// �и�
			sheet.setDefaultRowHeightInPoints(15);
			// �п��
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "�̻���ţ�15λ���֣�", "�ն˱��", "��ʼʱ��", "����ʱ��"};
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "12345678");
			POIUtils.createTextCell(book, row, (short) 2, "20180101");
			POIUtils.createTextCell(book, row, (short) 3, "20180131");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode("��ʯ��ɨ��ˢ��ͳ�Ʋ�ѯģ��", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("QuerySinopecSweepCardAction.downloadTemplate()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
		}
		return null;
	}
	
	// �ϴ�ActionForward
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		log.info("��ʼ��������");
		//�õ�Dao
		String info = "";
//		MerchantInfoDao merchantInfoDao = new MerchantInfoDao();
		QuerySinopecSweepCardDao dao = new QuerySinopecSweepCardDao();
		response.setContentType("text/html;charset=GBK");
		int maxFileSize = 5 * 1048576;// �ļ���С���ܳ���5M
		List<SinopecSweepCardUploadForm> sinopecSweepCardUploadList = new LinkedList<SinopecSweepCardUploadForm>();
		try {
//			writer = response.getWriter();
			SinopecSweepCardUploadForm uf = (SinopecSweepCardUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
//				jsonObj.put("success", "�ϴ�ʧ�ܣ�");
//				jsonObj.put("msg", "�ļ�����ʧ��,�ļ���С���ܳ���5M!");
				info = "�ļ�����ʧ��,�ļ���С���ܳ���5M!";
			} else {
				List<SinopecSweepCardUploadForm> listUpload = new LinkedList<SinopecSweepCardUploadForm>();
				Sheet sheet = POIUtils.readExcel(byteFile);
				//1��ֵ����
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					//��ȡ������
//					int columnNum=row.getPhysicalNumberOfCells();
					// �½�һ��Model
					SinopecSweepCardUploadForm sinopecSweepCardUploadForm = new SinopecSweepCardUploadForm();
					sinopecSweepCardUploadForm.setMerchant_no(POIUtils.getStringFromExcelCell(row.getCell(0)));
					sinopecSweepCardUploadForm.setTermial_no(POIUtils.getStringFromExcelCell(row.getCell(1)));
					sinopecSweepCardUploadForm.setStarttime(POIUtils.getStringFromExcelCell(row.getCell(2)));
					sinopecSweepCardUploadForm.setEndtime(POIUtils.getStringFromExcelCell(row.getCell(3)));
					if(!isAllFieldNull(sinopecSweepCardUploadForm)){
						listUpload.add(sinopecSweepCardUploadForm);
					}
				}
				//2���жϲ�ѯģ���и���������Ч��
					//һ�������Ƿ�����
					//�̻����Ƿ�Ϲ棨�̻�����һ�£�
					//�ն˺��Ƿ��ظ�
					//��ֹʱ���Ƿ����󣨲��ܳ���31�죩
				Map<String,Object> isflag = checkSinopecSweepCard(listUpload);
				boolean os =(Boolean)isflag.get("flag");
				if(os){
					//���е���������
					//3����ѯ
					//3��1��ѯ��������
					SinopecSweepCardUploadForm querycondition = new SinopecSweepCardUploadForm();
					StringBuilder st = new StringBuilder();
					for(int k=0;k<listUpload.size();k++){
						if(k==0){
							st.append("'"+listUpload.get(k).getTermial_no()+"'");
						}else{
							st.append(","+"'"+listUpload.get(k).getTermial_no()+"'");
						}
					}
					querycondition.setMerchant_no(listUpload.get(0).getMerchant_no());
					querycondition.setTermial_no(st.toString());
					querycondition.setStarttime(listUpload.get(0).getStarttime());
					querycondition.setEndtime(listUpload.get(0).getEndtime());
					//3��2��ѯ
					List<QuerySinopecSweepCardBean> lst = dao.getSinopecSweepCard(querycondition);
					for(int q=0;q<lst.size();q++){
						SinopecSweepCardUploadForm frm = new SinopecSweepCardUploadForm();
						frm.setTradingType(lst.get(q).getTradingtype().equals("SM")?"ɨ��":"ˢ��");
						frm.setTradingNumber(lst.get(q).getTradingnumber());
						frm.setTradingAmt(lst.get(q).getTradingamt());
						sinopecSweepCardUploadList.add(frm);
					}
					
					info = (String) isflag.get("content");
				}else{
					//�˳�����֪�û���ѯʧ��ԭ��
					info = (String) isflag.get("content");
				}
				//3����ѯ
				//4����ʾ
			}
		} catch (Exception e) {
			info = "��ʯ��ɨ��ˢ��ͳ��Excel�ļ���ѯAction�쳣: {}"+ e;
		}
		 // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = 0;
		if (sinopecSweepCardUploadList != null && !sinopecSweepCardUploadList.isEmpty()) {
			request.setAttribute("sinopecSweepCardUploadList", sinopecSweepCardUploadList);
			count = sinopecSweepCardUploadList.size();
		}
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("info", info);
		return mapping.findForward("showSinopecSweepCardList");
	}


	private Map<String, Object> checkSinopecSweepCard(List<SinopecSweepCardUploadForm> listUpload) {
		Map<String, Object> map = new HashMap<String, Object>();
		Boolean b = true;
		String content = "У��ͨ��";
		//һ�������Ƿ�����
		//�̻����Ƿ�Ϲ棨�̻�����һ�£�
		//�ն˺��Ƿ��ظ�
		//��ֹʱ���Ƿ����󣨲��ܳ���31�죩
		//С��1000�����������ԡ�������Ч��
		if(listUpload.size() > 1000){
			b = false;
			content = "���ܳ���1000���ն˺�";
		}else if(listUpload.size() == 0){
			b = false;
			content = "excelΪ��";
		}else{
			String merchant_no = listUpload.get(0).getMerchant_no(); //�̻��� ;
			String termial_no = listUpload.get(0).getTermial_no(); //�ն˺� ;
			String starttime = listUpload.get(0).getStarttime(); //��ʼʱ�� ;
			String endtime = listUpload.get(0).getEndtime(); //����ʱ�� ;
			for(int i=0;i<listUpload.size();i++){
				SinopecSweepCardUploadForm sinopecSweepCardUploadForm = listUpload.get(i);
				if(StringUtils.isNotBlank(sinopecSweepCardUploadForm.getMerchant_no()) 
						&& StringUtils.isNotBlank(sinopecSweepCardUploadForm.getTermial_no()) 
						&& StringUtils.isNotBlank(sinopecSweepCardUploadForm.getStarttime()) 
						&& StringUtils.isNotBlank(sinopecSweepCardUploadForm.getEndtime())){
					/*******************************************************************************************/
					if(!merchant_no.equals(sinopecSweepCardUploadForm.getMerchant_no())){
						b = false;
						content = "���̻��ţ�"+sinopecSweepCardUploadForm.getMerchant_no()+"�������̻��Ų�һ�£�����ϸ���";
						break;
					}
					if(i != 0){
						if(termial_no.equals(sinopecSweepCardUploadForm.getTermial_no())){
							b = false;
							content = "���ն˺ţ�"+sinopecSweepCardUploadForm.getTermial_no()+"�����У����������ն˺�һ�£�����ϸ���";
							break;
						}
					}
					if(!starttime.equals(sinopecSweepCardUploadForm.getStarttime())){
						b = false;
						content = "��ʼʱ�䣺"+sinopecSweepCardUploadForm.getStarttime()+"�����У��������п�ʼʱ�䲻һ�£�����ϸ���";
						break;
					}
					if(!endtime.equals(sinopecSweepCardUploadForm.getEndtime())){
						b = false;
						content = "����ʱ�䣺"+sinopecSweepCardUploadForm.getEndtime()+"�����У��������н���ʱ�䲻һ�£�����ϸ���";
						break;
					}
					/*******************************************************************************************/
					if(!isNumeric(sinopecSweepCardUploadForm.getMerchant_no(),15)){
						b = false;
						content = "���̻��ţ�"+sinopecSweepCardUploadForm.getMerchant_no()+"������15λ���֣�����ϸ���";
						break;
					}
					if(sinopecSweepCardUploadForm.getTermial_no().length() != 8){
						b = false;
						content = "���ն˺ţ�"+sinopecSweepCardUploadForm.getTermial_no()+"������8λ������ϸ���";
						break;
					}
					if(!isNumeric(sinopecSweepCardUploadForm.getStarttime(),8)){
						b = false;
						content = "��ʼʱ�䣺"+sinopecSweepCardUploadForm.getStarttime()+"������8λ���֣�����ϸ���";
						break;
					}
					if(!isNumeric(sinopecSweepCardUploadForm.getEndtime(),8)){
						b = false;
						content = "����ʱ�䣺"+sinopecSweepCardUploadForm.getEndtime()+"������8λ���֣�����ϸ���";
						break;
					}
					/*******************************************************************************************/
					if(!isValidDate(sinopecSweepCardUploadForm.getStarttime())){
						b = false;
						content = "��ʼʱ�䣺"+sinopecSweepCardUploadForm.getStarttime()+"��ʽ��������ϸ���";
						break;
					}
					if(!isValidDate(sinopecSweepCardUploadForm.getEndtime())){
						b = false;
						content = "����ʱ�䣺"+sinopecSweepCardUploadForm.getEndtime()+"��ʽ��������ϸ���";
						break;
					}
					/*******************************************************************************************/
				}else{
					b = false;
					content = "�ڣ�"+(i+1)+"�����ݲ�����";
					break;
				}
			}
			//��ֹʱ���Ƿ����󣨲��ܳ���31�죩
			if(b){
				if(compareNum(starttime,endtime)==0){
					b = false;
					content = "������������������";
				}else if(compareNum(starttime,endtime)  < 0){
					b = false;
					content = "��ʼʱ����ڽ���ʱ��";
				}else if(compareNum(starttime,endtime)  > 31){
					b = false;
					content = "���ܲ�ѯ����31�������";
				}
			}
		}
		map.put("flag", b);
		map.put("content", content);
		return map;
	}
	/*****************************************************����С����***************************************************/
	//�жϸö����Ƿ�: ����ture��ʾ��������Ϊnull  ����false��ʾ�����������Զ���null
    public static boolean isAllFieldNull(Object obj) throws Exception{
        Class stuCla = (Class) obj.getClass();// �õ������
        Field[] fs = stuCla.getDeclaredFields();//�õ����Լ���
        boolean flag = true;
        for (Field f : fs) {//��������
            f.setAccessible(true); // ���������ǿ��Է��ʵ�(˽�е�Ҳ����)
            Object val = f.get(obj);// �õ������Ե�ֵ
            if(val!=null && val!="") {//ֻҪ��1�����Բ�Ϊ��,��ô�Ͳ������е�����ֵ��Ϊ��
                flag = false;
                break;
            }
        }
        return flag;
    }
	public static Long compareNum(String starttime,String endtime) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		long d = 0;
		try {
			d = (sf.parse(endtime).getTime()-sf.parse(starttime).getTime())/1000/60/60/24;//��
		} catch (ParseException e) {
			System.out.println(d+"��");
		}
		return d;
	}
	public static boolean isValidDate(String str) {
		boolean convertSuccess = true;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		try {
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			convertSuccess = false;
		}
		return convertSuccess;
	}
	//�ж��ַ����Ƿ�Ϊ���֣���У��λ��
	public static boolean isNumeric(String str,int pos){
	   for (int i = str.length();--i>=0;){  
	       if (!Character.isDigit(str.charAt(i))){
	           return false;
	       }
	   }
	   if(str.length() != pos){
		   return false;
	   }
	   return true;
	}
	/*****************************************************����С����***************************************************/
}
