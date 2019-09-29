package com.pay.query.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.query.bean.LsBean;
import com.pay.query.dao.LsDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.query.struts.form.LsForm;
import com.pay.util.Constant;
import com.pay.util.PageBean;

public class QueryLs extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(QueryCard.class);
	public ActionForward getLsList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			// 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			// 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			LsForm lsForm = (LsForm) form;
			LsBean lsBean = new LsBean();
			BeanUtils.copyProperties(lsBean, lsForm);

			LsDao lsDao = new LsDao();
			int count = lsDao.getCount(lsBean, session);

			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List lsList = null;
			if (count > 0)
				lsList = lsDao.getLsList(pageBean, lsBean);
			if (lsList != null && !lsList.isEmpty()) {
				request.setAttribute("lsList", lsList);
			}
			// 枚举值
			// CommonDao comDao = new CommonDao();
			// LsProductBean lsProductBean = new LsProductBean();
			// List lsProductList = comDao.getLsProductBeanList(null,
			// lsProductBean );
			// request.setAttribute("lsProductList", lsProductList);

		} catch (Exception e) {
			logger.error("QueryLs--getLsList--Exception:", e);
		}
		return mapping.findForward("lsList");
	}
	public ActionForward getLsById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			LsForm lsForm = (LsForm) form;
			LsBean lsBean = new LsBean();
			BeanUtils.copyProperties(lsBean, lsForm);
			LsDao lsDao = new LsDao();
			List lsList = lsDao.getLsList(null, lsBean);
			if (lsList.size() > 0) {
				BeanUtils.copyProperties(lsForm, lsList.get(0));
			}
		} catch (Exception e) {
			logger.error("QueryLs--getLsById--Exception:", e);
		}
		return mapping.findForward("showLs");
	}

}
