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

import com.pay.query.bean.AcctBean;
import com.pay.query.bean.AcctStatusBean;
import com.pay.query.bean.AcctTypeBean;
import com.pay.query.dao.AcctDao;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.query.struts.form.AcctForm;
import com.pay.util.Constant;
import com.pay.util.PageBean;

public class QueryAcct extends BaseDispatchAction {
	private static final Logger logger = Logger.getLogger(QueryAcct.class);
	public ActionForward getAcctList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
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
	        AcctForm acctForm = (AcctForm)form;
	        AcctBean acctBean = new AcctBean();
			BeanUtils.copyProperties(acctBean,acctForm);
			
	        AcctDao acctDao = new AcctDao();
			int count = acctDao.getCount(acctBean,session);
			
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			List acctList = null;
			if(count>0)
				acctList = acctDao.getAcctList(pageBean, acctBean);
			if (acctList != null && !acctList.isEmpty()) {
				request.setAttribute("acctList", acctList);
			}
			//枚举值
			CommonDao comDao = new CommonDao();
			AcctStatusBean acctStatusBean = new AcctStatusBean();
			List acctStatusList = comDao.getAcctStatusBeanList(null, acctStatusBean  );
			request.setAttribute("acctStatusList", acctStatusList);
			AcctTypeBean acctTypeBean = new AcctTypeBean();
			List acctTypeList = comDao.getAcctTypeBeanList(null, acctTypeBean  );
			request.setAttribute("acctTypeList", acctTypeList);

		} catch (Exception e) {
			logger.error("QueryAcct--getAcctList--Exception:", e);
		}
        return mapping.findForward("acctList");
    }
	public ActionForward getAcctById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = (String) request.getParameter("id");
	        AcctForm acctForm = (AcctForm)form;
	        if(!"".equals(id)&&id!=null)
	        	acctForm.setId(id);
	        AcctBean acctBean = new AcctBean();
			BeanUtils.copyProperties(acctBean,acctForm);
	        AcctDao acctDao = new AcctDao();
			List acctList = acctDao.getAcctList(null, acctBean);
			acctBean = new AcctBean();
			AcctBean acctBean2 = (AcctBean) acctList.get(0);
			acctBean.setAccno(acctBean2.getAccno().substring(0,acctBean2.getAccno().length()-2)+"%");
			acctList = acctDao.getAcctList(null, acctBean);
			double sum=0;
			for (int i = 0; i < acctList.size(); i++) {
				acctBean = (AcctBean) acctList.get(i);
				sum+=Double.valueOf(acctBean.getAvlbal());
			}
			request.setAttribute("acctList", acctList);
			request.setAttribute("sum", sum);
		} catch (Exception e) {
			logger.error("QueryAcct--getAcctById--Exception:", e);
		}
        return mapping.findForward("showAcct");
    }
	

}
