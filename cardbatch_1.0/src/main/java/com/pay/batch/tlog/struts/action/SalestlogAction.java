package com.pay.batch.tlog.struts.action;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pay.batch.tlog.bean.SalestlogBean;
import com.pay.batch.tlog.dao.SalestlogDao;
import com.pay.batch.tlog.struts.form.SalestlogForm;
import com.pay.query.bean.CardProductBean;
import com.pay.query.dao.CommonDao;
import com.pay.util.Constant;
import com.pay.util.ExportExcel;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.RecordMethod;

public class SalestlogAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(SalestlogAction.class);
	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");

	public ActionForward getSalestlogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		SalestlogDao dao = new SalestlogDao();
		HttpSession session = request.getSession();
		new RecordMethod().sessionRemove(session, SalestlogBean.class);
		String txtype=request.getParameter("txtype");
		if(null!=txtype&&txtype.trim().length()>0){
			txtype=txtype.replaceAll(" ", "");
			request.setAttribute("txtype",txtype);
		}
		// 构造分页对象
		int count = dao.getCount(null, txtype);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List<SalestlogBean> lst = dao.getSalestlogList(pageBean, null, txtype);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("salestlogList", lst);
		}
		request.setAttribute("queryflag", "0"); // 表示不是查询
		//枚举值:卡产品列表
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);	
		
		return mapping.findForward("showSalestlogList");
	}

	public ActionForward preQuerySalestlogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		SalestlogForm salestlogForm = (SalestlogForm) form;
		HttpSession session = request.getSession();
		
		new RecordMethod().sessionSet(session, SalestlogForm.class, salestlogForm);
		return mapping.findForward("querySalestlogList");

	}

	public ActionForward querySalestlogList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		SalestlogDao dao = new SalestlogDao();
		HttpSession session = request.getSession();
		SalestlogForm salestlogForm = (SalestlogForm) form;
		SalestlogBean querybean = new SalestlogBean();
		new RecordMethod().sessionGet(session, SalestlogBean.class, querybean);
		try {
			BeanUtils.copyProperties(salestlogForm, querybean);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		String txtype=request.getParameter("txtype");
		if(null!=txtype&&txtype.trim().length()>0){
			txtype=txtype.replaceAll(" ", "");
			request.setAttribute("txtype",txtype);
		}
		// 构造分页对象
		int count = dao.getCount(querybean, txtype);
		PageBean pageBean = new PageBean(count,
				Constant.getInstance().PageSize, ParamUtils.getIntParameter(
						request, "currentPage", 1));
		List<SalestlogBean> lst = dao.getSalestlogList(pageBean, querybean, txtype);
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
		if (lst != null && !lst.isEmpty()) {
			request.setAttribute("salestlogList", lst);
		}
		request.setAttribute("queryflag", "1"); // 表示不是查询
		//枚举值:卡产品列表
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);	
		new RecordMethod().sessionRemove(session, SalestlogBean.class);
		return mapping.findForward("showSalestlogList");
	}
	
	public ActionForward exportExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		SalestlogDao dao = new SalestlogDao();
		HttpSession session = request.getSession();
		SalestlogForm salestlogForm = (SalestlogForm) form;
		SalestlogBean querybean = new SalestlogBean();
		new RecordMethod().sessionGet(session, SalestlogBean.class, querybean);
		try {
			BeanUtils.copyProperties(querybean,salestlogForm);
		} catch (IllegalAccessException e) {
			logger.error("IllegalAccessException", e);
		} catch (InvocationTargetException e) {
			logger.error("InvocationTargetException", e);
		}
		List<SalestlogBean> lst = dao.getSalestlogList(querybean, request.getParameter("txtype"));
		
		String docsPath = request.getSession().getServletContext()
				.getRealPath("docs");
		String fileName = request.getParameter("txtype")+"_salestlog"+System.currentTimeMillis()+".xls";
		String filePath = docsPath + FILE_SEPARATOR + fileName;
		System.out.println("[download file path=]"+filePath);
		OutputStream out = null;
		ExportExcel<SalestlogBean> expexl = new ExportExcel<SalestlogBean>();
		try {
			out = new FileOutputStream(filePath);
			
			String[] headers = { "ID","交易类型", "卡号", "金额", "状态", "父订单号", "子订单号", "原父订单号", "原子订单号", "操作员" , "日期","交易类型","扩展交易类型"};
			expexl.exportExcel("销售日志表", headers, lst, out, "yyyy-MM-dd");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		expexl.download(filePath, response);
		return null;
	}

	public ActionForward showSalestlogInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {		
        SalestlogDao dao = new SalestlogDao();        
        String id = ParamUtils.getParameter(request, "id");
        request.setAttribute("id", id);        
        SalestlogBean salestlogBean = dao.getSalestlogByID(id);        
        request.setAttribute("salestlogBean", salestlogBean);
        return mapping.findForward("showSalestlogInfo.jsp");
    }
}
