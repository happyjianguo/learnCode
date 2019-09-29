package com.pay.batch.tlog.struts.action;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.tlog.bean.TlogBean;
import com.pay.batch.tlog.dao.TlogDao;
import com.pay.batch.tlog.struts.form.TlogForm;
import com.pay.query.bean.CardProductBean;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.ExportExcel;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;

public class TlogAction extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(TlogAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	//304 menu
	public ActionForward queryTLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		PageBean pageBean = new PageBean(0, Constant.getInstance().PageSize,cPage);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", null);
		//ö��ֵ
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List<CardProductBean> cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogList");
	}
	//304query
	public ActionForward selectqueryTLogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TlogDao dao = new TlogDao();
		TlogForm tlogForm = (TlogForm) form;
		TlogBean querybean = new TlogBean();

		if (tlogForm.getCrdacptid()!=null&&tlogForm.getCrdacptid().trim().length()>0)
			querybean.setCrdacptid(tlogForm.getCrdacptid());
		if (tlogForm.getTermcode()!=null&&tlogForm.getTermcode().trim().length()>0)
			querybean.setTermcode(tlogForm.getTermcode());
		if (tlogForm.getPan()!=null&&tlogForm.getPan().trim().length()>0)
			querybean.setPan(tlogForm.getPan());
		if (tlogForm.getRrn()!=null&&tlogForm.getRrn().trim().length()>0)
			querybean.setRrn(tlogForm.getRrn());

		String queryDTStart=tlogForm.getQueryDTStart();
		if(queryDTStart==null||"".equals(queryDTStart)){
			queryDTStart=this.CurentDayTime("-1");
		}
		querybean.setQueryDTStart(queryDTStart);
		
		String queryDTEnd=tlogForm.getQueryDTEnd();
		if(queryDTEnd==null||"".equals(queryDTEnd)){
			queryDTEnd=this.CurentDayTime(null);
		}
		querybean.setQueryDTEnd(queryDTEnd);
		
		//���ӽ������Ͳ�ѯ
		String queryTxnType=tlogForm.getQueryTxnType();
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			querybean.setQueryTxnType(queryTxnType);
		}
		
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			String[]  strs=queryTxnType.split("-");
			if(strs.length==3){
				querybean.setFncode(strs[0]);
				querybean.setTxncode(strs[1]);
				querybean.setSub_txncode(strs[2]);
			}
		}
		if (tlogForm.getTxncode()!=null&&tlogForm.getTxncode().trim().length()>0)
			querybean.setTxncode(tlogForm.getTxncode());
		
		if (tlogForm.getTxnstatus()!=null&&tlogForm.getTxnstatus().trim().length()>0)
			querybean.setTxnstatus(tlogForm.getTxnstatus());
		if (tlogForm.getAiid()!=null&&tlogForm.getAiid().trim().length()>0)
			querybean.setAiid(tlogForm.getAiid());
		if (tlogForm.getCrdproduct()!=null&&tlogForm.getCrdproduct().trim().length()>0)
			querybean.setCrdproduct(tlogForm.getCrdproduct());
		if (tlogForm.getTxnsrc()!=null&&tlogForm.getTxnsrc().trim().length()>0)
			querybean.setTxnsrc(tlogForm.getTxnsrc());		
		
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = dao.getCount(querybean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List<TlogBean> lst = dao.getTlogList(pageBean, querybean);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", lst);
		//ö��ֵ
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List<CardProductBean> cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogList");
	}
	//306 menu
	public ActionForward queryTLogListCurrent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		PageBean pageBean = new PageBean(0, Constant.getInstance().PageSize,cPage);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", null);
		//ö��ֵ
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List<CardProductBean> cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogListCurrent");
	}
	//306 query
	public ActionForward selectqueryTLogListCurrent(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TlogDao dao = new TlogDao();
		TlogForm tlogForm = (TlogForm) form;
		TlogBean querybean = new TlogBean();

		if (tlogForm.getCrdacptid()!=null&&tlogForm.getCrdacptid().trim().length()>0)
			querybean.setCrdacptid(tlogForm.getCrdacptid());
		if (tlogForm.getTermcode()!=null&&tlogForm.getTermcode().trim().length()>0)
			querybean.setTermcode(tlogForm.getTermcode());
		if (tlogForm.getPan()!=null&&tlogForm.getPan().trim().length()>0)
			querybean.setPan(tlogForm.getPan());
		if (tlogForm.getRrn()!=null&&tlogForm.getRrn().trim().length()>0)
			querybean.setRrn(tlogForm.getRrn());

		String queryDTStart=tlogForm.getQueryDTStart();
		if(queryDTStart==null||"".equals(queryDTStart)){
			queryDTStart=this.CurentDayTime("-1");
		}
		querybean.setQueryDTStart(queryDTStart);
		
		String queryDTEnd=tlogForm.getQueryDTEnd();
		if(queryDTEnd==null||"".equals(queryDTEnd)){
			queryDTEnd=this.CurentDayTime(null);
		}
		querybean.setQueryDTEnd(queryDTEnd);
		
		//���ӽ������Ͳ�ѯ
		String queryTxnType=tlogForm.getQueryTxnType();
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			querybean.setQueryTxnType(queryTxnType);
		}
		
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			String[]  strs=queryTxnType.split("-");
			if(strs.length==3){
				querybean.setFncode(strs[0]);
				querybean.setTxncode(strs[1]);
				querybean.setSub_txncode(strs[2]);
			}
		}
		if (tlogForm.getTxncode()!=null&&tlogForm.getTxncode().trim().length()>0)
			querybean.setTxncode(tlogForm.getTxncode());
		
		if (tlogForm.getTxnstatus()!=null&&tlogForm.getTxnstatus().trim().length()>0)
			querybean.setTxnstatus(tlogForm.getTxnstatus());
		if (tlogForm.getAiid()!=null&&tlogForm.getAiid().trim().length()>0)
			querybean.setAiid(tlogForm.getAiid());
		if (tlogForm.getCrdproduct()!=null&&tlogForm.getCrdproduct().trim().length()>0)
			querybean.setCrdproduct(tlogForm.getCrdproduct());
		if (tlogForm.getTxnsrc()!=null&&tlogForm.getTxnsrc().trim().length()>0)
			querybean.setTxnsrc(tlogForm.getTxnsrc());		
		
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = dao.getCountCurrent(querybean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List<TlogBean> lst = dao.getTlogListCurrent(pageBean, querybean);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", lst);
		//ö��ֵ
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogListCurrent");
	
	}
	
	/**
	 * 307
	 * ��������ͳ�� �˵�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward queryTLogListRollingPoor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		PageBean pageBean = new PageBean(0, Constant.getInstance().PageSize,cPage);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", null);
		request.setAttribute("h", "0");
		request.setAttribute("b", "0");
		request.setAttribute("c", "0");
		request.setAttribute("d", "0");
		request.setAttribute("e", "0");
		request.setAttribute("f", "0");
		request.setAttribute("g", "0");
		//ö��ֵ
		CommonDao comDao = new CommonDao();	
		CardProductBean cardProductBean = new CardProductBean();
		List<CardProductBean> cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogListRollingPoor");
	}
	/**
	 * 307
	 * ��������ͳ�� query
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward selectqueryTLogListRollingPoor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		TlogDao dao = new TlogDao();
		TlogForm tlogForm = (TlogForm) form;
		TlogBean querybean = new TlogBean();
		
		if (tlogForm.getCrdacptid()!=null&&tlogForm.getCrdacptid().trim().length()>0)
			querybean.setCrdacptid(tlogForm.getCrdacptid());
		if (tlogForm.getTermcode()!=null&&tlogForm.getTermcode().trim().length()>0)
			querybean.setTermcode(tlogForm.getTermcode());
		if (tlogForm.getPan()!=null&&tlogForm.getPan().trim().length()>0)
			querybean.setPan(tlogForm.getPan());
		String queryDTStart=tlogForm.getQueryDTStart();
		if(queryDTStart==null||"".equals(queryDTStart)){
			queryDTStart=this.CurentDayTime("-1");
		}
		querybean.setQueryDTStart(queryDTStart);
		
		String queryDTEnd=tlogForm.getQueryDTEnd();
		if(queryDTEnd==null||"".equals(queryDTEnd)){
			queryDTEnd=this.CurentDayTime(null);
		}
		querybean.setQueryDTEnd(queryDTEnd);
		// ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = dao.getCountRollingPoor(querybean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List<TlogBean> lst = dao.getTlogListRollingPoor(pageBean, querybean);
		Map<String,String> maptotal = dao.getTlogListRollingPoortotal(null, querybean);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", lst);
		request.setAttribute("h", maptotal.get("h"));
		request.setAttribute("b", maptotal.get("b"));
		request.setAttribute("c", maptotal.get("c"));
		request.setAttribute("d", maptotal.get("d"));
		request.setAttribute("e", maptotal.get("e"));
		request.setAttribute("f", maptotal.get("f"));
		request.setAttribute("g", maptotal.get("g"));
		//ö��ֵ
		CommonDao comDao = new CommonDao();	
		CardProductBean cardProductBean = new CardProductBean();
		List<CardProductBean> cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogListRollingPoor");
		
	}
	//305 menu
	public ActionForward queryTLogListBak(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		PageBean pageBean = new PageBean(0, Constant.getInstance().PageSize,cPage);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", null);
		//ö��ֵ
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List<CardProductBean> cardProductList = comDao.getCardProductBeanListBak(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogList2");
	}
	//305 query
	public ActionForward selectqueryTLogListBak(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TlogDao dao = new TlogDao();
		TlogForm tlogForm = (TlogForm) form;
		TlogBean querybean = new TlogBean();

		if (tlogForm.getCrdacptid()!=null&&tlogForm.getCrdacptid().trim().length()>0)
			querybean.setCrdacptid(tlogForm.getCrdacptid());
		if (tlogForm.getTermcode()!=null&&tlogForm.getTermcode().trim().length()>0)
			querybean.setTermcode(tlogForm.getTermcode());
		if (tlogForm.getPan()!=null&&tlogForm.getPan().trim().length()>0)
			querybean.setPan(tlogForm.getPan());
		if (tlogForm.getRrn()!=null&&tlogForm.getRrn().trim().length()>0)
			querybean.setRrn(tlogForm.getRrn());

		String queryDTStart=tlogForm.getQueryDTStart();
		if(queryDTStart==null||"".equals(queryDTStart)){
			queryDTStart=this.CurentDayTime(null);
		}
		querybean.setQueryDTStart(queryDTStart);
		
		String queryDTEnd=tlogForm.getQueryDTEnd();
		if(queryDTEnd==null||"".equals(queryDTEnd)){
			queryDTEnd=this.CurentDayTime(null);
		}
		querybean.setQueryDTEnd(queryDTEnd);
		
		//���ӽ������Ͳ�ѯ
		String queryTxnType=tlogForm.getQueryTxnType();
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			querybean.setQueryTxnType(queryTxnType);
		}
		
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			String[]  strs=queryTxnType.split("-");
			if(strs.length==3){
				querybean.setFncode(strs[0]);
				querybean.setTxncode(strs[1]);
				querybean.setSub_txncode(strs[2]);
			}
		}
		if (tlogForm.getTxncode()!=null&&tlogForm.getTxncode().trim().length()>0)
			querybean.setTxncode(tlogForm.getTxncode());
		
		if (tlogForm.getTxnstatus()!=null&&tlogForm.getTxnstatus().trim().length()>0)
			querybean.setTxnstatus(tlogForm.getTxnstatus());
		if (tlogForm.getAiid()!=null&&tlogForm.getAiid().trim().length()>0)
			querybean.setAiid(tlogForm.getAiid());
		if (tlogForm.getCrdproduct()!=null&&tlogForm.getCrdproduct().trim().length()>0)
			querybean.setCrdproduct(tlogForm.getCrdproduct());
		if (tlogForm.getTxnsrc()!=null&&tlogForm.getTxnsrc().trim().length()>0)
			querybean.setTxnsrc(tlogForm.getTxnsrc());		
		
        // ���õ�ǰҳ��
		String dParams[] = getDisplayParams();
		int cPage = 1;
		if (request.getParameter(dParams[0]) != null) {
			cPage = Integer.parseInt(request.getParameter(dParams[0]));
		}
		int count = dao.getCountBak(querybean);
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
		List<TlogBean> lst = dao.getTlogListBak(pageBean, querybean);
		// �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("tlogList", lst);
		//ö��ֵ
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List cardProductList = comDao.getCardProductBeanListBak(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		return mapping.findForward("showTLogList2");
	}
	
	public ActionForward exportExcelRollingPoor(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TlogDao dao = new TlogDao();
		TlogForm tlogForm = (TlogForm) form;
		TlogBean querybean = new TlogBean();
		
		if (tlogForm.getCrdacptid()!=null&&tlogForm.getCrdacptid().trim().length()>0)
			querybean.setCrdacptid(tlogForm.getCrdacptid());
		if (tlogForm.getTermcode()!=null&&tlogForm.getTermcode().trim().length()>0)
			querybean.setTermcode(tlogForm.getTermcode());
		if (tlogForm.getPan()!=null&&tlogForm.getPan().trim().length()>0)
			querybean.setPan(tlogForm.getPan());
		String queryDTStart=tlogForm.getQueryDTStart();
		if(queryDTStart==null||"".equals(queryDTStart)){
			queryDTStart=this.CurentDayTime("-1");
		}
		querybean.setQueryDTStart(queryDTStart);
		
		String queryDTEnd=tlogForm.getQueryDTEnd();
		if(queryDTEnd==null||"".equals(queryDTEnd)){
			queryDTEnd=this.CurentDayTime(null);
		}
		querybean.setQueryDTEnd(queryDTEnd);
		
		List<TlogBean> lst = dao.getTlogListRollingPoor(null, querybean);
		Map<String,String> maptotal = dao.getTlogListRollingPoortotal(null, querybean);
		
		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = "��������ͳ��"+System.currentTimeMillis()+".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
			String[] headers = { "����", "�̻���", "�ն˺�", "��������","���׽��","��������","����ʱ��"};
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headers[i]);
			}
			for (int i = 0; i < lst.size(); i++) {
				TlogBean btsKey = (TlogBean) lst.get(i);
				row = sheet.createRow(i + 1);
				createCell(row, (short) 0, btsKey.getPan());
				createCell(row, (short) 1, btsKey.getCrdacptid());
				createCell(row, (short) 2, btsKey.getTermcode());
				createCell(row, (short) 3, btsKey.getTxncode());
				createCell(row, (short) 4, btsKey.getAmttxn());
				createCell(row, (short) 5, btsKey.getDatelocal());
				createCell(row, (short) 6, btsKey.getTimelocal());
			}
			row = sheet.createRow(lst.size() + 2 + 1);
			createCell(row, (short) 0, "�˻��ܱ�����");
			createCell(row, (short) 1, maptotal.get("e"));
			createCell(row, (short) 2, "�˻��ܽ�");
			createCell(row, (short) 3, maptotal.get("f"));
			createCell(row, (short) 4, "");
			createCell(row, (short) 5, "");
			createCell(row, (short) 6, "");
			row = sheet.createRow(lst.size() + 2 + 2);
			createCell(row, (short) 0, "�����ܱ�����");
			createCell(row, (short) 1, maptotal.get("c"));
			createCell(row, (short) 2, "�����ܽ�");
			createCell(row, (short) 3, maptotal.get("d"));
			createCell(row, (short) 4, "");
			createCell(row, (short) 5, "");
			createCell(row, (short) 6, "");
			row = sheet.createRow(lst.size() + 2 + 3);
			createCell(row, (short) 0, "�����ܱ�����");
			createCell(row, (short) 1, maptotal.get("h"));
			createCell(row, (short) 2, "�����ܽ�");
			createCell(row, (short) 3, maptotal.get("b"));
			createCell(row, (short) 4, "������");
			createCell(row, (short) 5, maptotal.get("g"));
			createCell(row, (short) 6, "");
			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TlogAction.exportExcelRollingPoor()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
		}
		ExportExcel.deleteFile(filePath);
		return null;
		
	}
	public ActionForward exportExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TlogDao dao = new TlogDao();
		TlogForm tlogForm = (TlogForm) form;
		TlogBean querybean = new TlogBean();

		if (tlogForm.getCrdacptid()!=null&&tlogForm.getCrdacptid().trim().length()>0)
			querybean.setCrdacptid(tlogForm.getCrdacptid());
		if (tlogForm.getTermcode()!=null&&tlogForm.getTermcode().trim().length()>0)
			querybean.setTermcode(tlogForm.getTermcode());
		if (tlogForm.getPan()!=null&&tlogForm.getPan().trim().length()>0)
			querybean.setPan(tlogForm.getPan());
		if (tlogForm.getRrn()!=null&&tlogForm.getRrn().trim().length()>0)
			querybean.setRrn(tlogForm.getRrn());

		String queryDTStart=tlogForm.getQueryDTStart();
		if(queryDTStart==null||"".equals(queryDTStart)){
			queryDTStart=this.CurentDayTime("-1");
		}
		querybean.setQueryDTStart(queryDTStart);
		
		String queryDTEnd=tlogForm.getQueryDTEnd();
		if(queryDTEnd==null||"".equals(queryDTEnd)){
			queryDTEnd=this.CurentDayTime(null);
		}
		querybean.setQueryDTEnd(queryDTEnd);
		
		
		
		//���ӽ������Ͳ�ѯ
		String queryTxnType=tlogForm.getQueryTxnType();
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			querybean.setQueryTxnType(queryTxnType);
		}
		
		if (queryTxnType!=null&&queryTxnType.trim().length()>0){
			String[]  strs=queryTxnType.split("-");
			if(strs.length==3){
				querybean.setFncode(strs[0]);
				querybean.setTxncode(strs[1]);
				querybean.setSub_txncode(strs[2]);
			}
		}
		if (tlogForm.getTxncode()!=null&&tlogForm.getTxncode().trim().length()>0)
			querybean.setTxncode(tlogForm.getTxncode());
		
		
		if (tlogForm.getTxnstatus()!=null&&tlogForm.getTxnstatus().trim().length()>0)
			querybean.setTxnstatus(tlogForm.getTxnstatus());
		if (tlogForm.getAiid()!=null&&tlogForm.getAiid().trim().length()>0)
			querybean.setAiid(tlogForm.getAiid());
		if (tlogForm.getCrdproduct()!=null&&tlogForm.getCrdproduct().trim().length()>0)
			querybean.setCrdproduct(tlogForm.getCrdproduct());
		if (tlogForm.getTxnsrc()!=null&&tlogForm.getTxnsrc().trim().length()>0)
			querybean.setTxnsrc(tlogForm.getTxnsrc());	
		List<TlogBean> list = dao.getTlogList(null, querybean);

		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = "��ˮ��ѯ"+System.currentTimeMillis()+".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		OutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
			// //��������
			// //����excel����
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// ���ɱ�����Ŀ
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = null;
//			String[] headers = { "ID","ԭʼ������ˮ��", "������ˮ��", "pos��ˮ��", "�̻���", "�ն˺�", "����", "�յ���������", "����ʱ��", "������Դ��" , 
//			"������","�Ӵ�����","����״̬","���׽��","����������","Ӧ����","ԭ�����","����Ʒ","Pos��ַ"};
			String[] headers = { "ID","ԭʼ������ˮ��", "������ˮ��", "pos��ˮ��", "�̻���", "�ն˺�", "����", "����ʱ��", "������Դ","������", 
			"��������","����״̬","���׽��","Ӧ����","����Ʒ"};
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell((short) i);
				cell.setCellValue(headers[i]);
			}
			
			String getQueryTxnType="";
			String getQueryTxnTypeName="";
			String getTxnstatus="";
			String getTxnstatusName="";
			String getTxnsrc="";
			String getTxnsrcName="";

			for (int i = 0; i < list.size(); i++) {
				TlogBean btsKey = (TlogBean) list.get(i);
				row = sheet.createRow(i + 1);
				createCell(row, (short) 0, btsKey.getId());
				createCell(row, (short) 1, btsKey.getStanorg());
				createCell(row, (short) 2, btsKey.getRrn());
				createCell(row, (short) 3, btsKey.getTermseq());
				createCell(row, (short) 4, btsKey.getCrdacptid());
				createCell(row, (short) 5, btsKey.getTermcode());
				createCell(row, (short) 6, btsKey.getPan());
//				createCell(row, (short) 7, btsKey.getAiid());
				createCell(row, (short) 7, btsKey.getDatelocal());
				
				getTxnsrc=btsKey.getTxnsrc();
				if("2".equals(getTxnsrc))
					getTxnsrcName="POS";
				if("4".equals(getTxnsrc))
					getTxnsrcName="SALE";
				if("6".equals(getTxnsrc))
					getTxnsrcName="IC-POS";
				if("8".equals(getTxnsrc))
					getTxnsrcName="PAY";
				if("9".equals(getTxnsrc))
					getTxnsrcName="����̨";
				createCell(row, (short) 8, getTxnsrcName);
				
				createCell(row, (short) 9, btsKey.getTxncode());
				
				getQueryTxnType=btsKey.getQueryTxnType();
				if("200-0-00".equals(getQueryTxnType))
					getQueryTxnTypeName="����";
				if("200-20-00".equals(getQueryTxnType))
					getQueryTxnTypeName="����";
				if("200-20-01".equals(getQueryTxnType))
					getQueryTxnTypeName="�˻�";
				if("200-20-99".equals(getQueryTxnType))
					getQueryTxnTypeName="�˵�";
				if("200-28-04".equals(getQueryTxnType))
					getQueryTxnTypeName="PAY��ֵ";
				if("200-30-00".equals(getQueryTxnType))
					getQueryTxnTypeName="����ѯ";
				if("200-40-00".equals(getQueryTxnType))
					getQueryTxnTypeName="ת��";
				if("200-90-00".equals(getQueryTxnType))
					getQueryTxnTypeName="����";
				if("400-0-02".equals(getQueryTxnType))
					getQueryTxnTypeName="���ѳ���";
				if("400-20-01".equals(getQueryTxnType))
					getQueryTxnTypeName="�˻�����";
				if("400-20-02".equals(getQueryTxnType))
					getQueryTxnTypeName="��������";
				if("500-0-93".equals(getQueryTxnType))
					getQueryTxnTypeName="���";
				if("500-0-94".equals(getQueryTxnType))
					getQueryTxnTypeName="�������";
				if("500-20-92".equals(getQueryTxnType))
					getQueryTxnTypeName="��ֵ����";
				if("500-20-98".equals(getQueryTxnType))
					getQueryTxnTypeName="��ʵʱ�˿�";
				if("500-20-99".equals(getQueryTxnType))
					getQueryTxnTypeName="ʵʱ�˿�";
				if("500-28-96".equals(getQueryTxnType))
					getQueryTxnTypeName="������ֵ";
				if("500-28-97".equals(getQueryTxnType))
					getQueryTxnTypeName="SALE��ֵ";
				if("500-28-98".equals(getQueryTxnType))
					getQueryTxnTypeName="��ʵʱ����";
				if("500-28-99".equals(getQueryTxnType))
					getQueryTxnTypeName="ʵʱ����";
				createCell(row, (short) 10, getQueryTxnTypeName);
				
//				createCell(row, (short) 10, btsKey.getTxncode());
//				createCell(row, (short) 11, btsKey.getSub_txncode());
				getTxnstatus=btsKey.getTxnstatus();
				if("7".equals(getTxnstatus))
					getTxnstatusName="�ɹ�";
				if("8".equals(getTxnstatus))
					getTxnstatusName="������";
				if(!"7".equals(getTxnstatus)&&!"8".equals(getTxnstatus))
					getTxnstatusName="ʧ��";
				createCell(row, (short) 11, getTxnstatusName);
				
				createCell(row, (short) 12, btsKey.getAmttxn());	
//				createCell(row, (short) 12, btsKey.getAmttxnfee());	
				createCell(row, (short) 13, btsKey.getRspcode());	
//				createCell(row, (short) 16, btsKey.getReasoncode());	
				createCell(row, (short) 14, btsKey.getCrdproduct());	
//				createCell(row, (short) 18, btsKey.getPoscdic());	
			}

			// ���ļ������б���
			String templateName = java.net.URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// �õ������
			out = response.getOutputStream();
			book.write(out);
			// //ˢ�������
			out.flush();
			
			//expexl.download(filePath, response);
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
		}
		ExportExcel.deleteFile(filePath);
		return null;
	}
	
	public static void createCell(HSSFRow row, short colNum, String value) {
		HSSFCell cell = row.createCell(colNum);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING); 
		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(value);
	}
	
	public ActionForward showTlogInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		TlogDao dao = new TlogDao();
		TlogBean bean = dao.getTlogInfo(id);
		request.setAttribute("tlogBean", bean);
		return mapping.findForward("showTlogInfo");
	}
	
	/**
	 * ��ȡ������ʱ����
	 * @param flag -1�ϸ��±���ʱ�䣬null����ʱ��
	 * @return
	 */
	public String CurentDayTime(String flag){
		Calendar a=Calendar.getInstance();
		int year = a.get(Calendar.YEAR);	//��
		int month =a.get(Calendar.MONTH) + 1; //��
		if(flag!=null&&flag=="-1"){
			month =a.get(Calendar.MONTH) ; //��
		}		
		int day =a.get(Calendar.DATE); 		//��
		int hour = a.get(Calendar.HOUR_OF_DAY);		//ʱ
		int minute = a.get(Calendar.MINUTE);	//��
		int second = a.get(Calendar.SECOND);	//��
		String clock = year + "";
		if (month < 10)
			clock += "0";
		clock += month + "";
		if (day < 10)
			clock += "0";
		clock += day + "";
	 	if(hour<10){
	 		clock += "0";
	 	}
		clock += hour + "";
		if(minute<10){
			clock += "0";
		}
		clock += minute + "";
		if(second<10){
			clock += "0";
		}
		clock += second + "";
		return clock;
	}
}
