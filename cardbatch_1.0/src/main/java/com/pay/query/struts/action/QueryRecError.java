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

import com.pay.query.bean.RecErrorBean;
import com.pay.query.dao.RecErrorDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.query.struts.form.RecErrorForm;
import com.pay.util.Constant;
import com.pay.util.PageBean;

public class QueryRecError extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(QueryRecError.class);
	public ActionForward getRecErrorList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
			RecErrorForm recErrorForm = (RecErrorForm) form;
			RecErrorBean recErrorBean = new RecErrorBean();
			BeanUtils.copyProperties(recErrorBean, recErrorForm);

			RecErrorDao recErrorDao = new RecErrorDao();
			int count = recErrorDao.getCount(recErrorBean, session);

			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List recErrorList = null;
			if (count > 0)
				recErrorList = recErrorDao.getRecErrorList(pageBean, recErrorBean);
			if (recErrorList != null && !recErrorList.isEmpty()) {
				request.setAttribute("recErrorList", recErrorList);
			}

		} catch (Exception e) {
			logger.error("QueryRecError--getRecErrorList--Exception:", e);
		}
		return mapping.findForward("recErrorList");
	}
}
