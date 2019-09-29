package com.pay.userCrdproduct.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.query.bean.CardProductBean;
import com.pay.query.dao.CommonDao;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.system.user.bean.UserBean;
import com.pay.system.user.dao.UserDao;
import com.pay.userCrdproduct.bean.UserCrdproductBean;
import com.pay.userCrdproduct.dao.UserCrdproductDao;
import com.pay.userCrdproduct.struts.form.UserCrdproductForm;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class UserCrdproductAction extends BaseDispatchAction{

	private static final Logger logger = Logger.getLogger(UserCrdproductAction.class);

	public ActionForward getUserCrdproductList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			// 构造分页对象
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = UserCrdproductDao.getCount(UserCrdproductForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<UserCrdproductBean> UserCrdproductList = UserCrdproductDao.getUserCrdproductList(
					pageBean, UserCrdproductForm);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if (UserCrdproductList != null && !UserCrdproductList.isEmpty()) {
				request.setAttribute("userCrdproductList", UserCrdproductList);
			}	
			
			UserDao ud=new UserDao();
			List<UserBean> userList=(List<UserBean>)ud.showAllUserOfFkList();
			request.setAttribute("userList", userList);	
		} catch (Exception e) {
			logger.error("UserCrdproductAction--getUserCrdproductList--Exception:",
					e);
		}
		return mapping.findForward("showUserCrdproductList");
	}

	public ActionForward preQueryUserCrdproduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
		UserCrdproductBean UserCrdproductBean = UserCrdproductDao.getUserCrdproductByID(id);		
		if (UserCrdproductBean != null) {
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			try {
				BeanUtils.copyProperties(UserCrdproductForm, UserCrdproductBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
			//卡产品LIST
			CommonDao comDao = new CommonDao();
			CardProductBean cardProductBean = new CardProductBean();
			List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
			request.setAttribute("cardProductList", cardProductList);		
			
			UserDao ud=new UserDao();
			List<UserBean> userList=(List<UserBean>)ud.showAllUserOfFkList();
			request.setAttribute("userList", userList);		
			
			request.setAttribute("crdProStr", UserCrdproductBean.getCrdproduct());
		}
		return mapping.findForward("showUserCrdproduct");
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
	public ActionForward preAddUserCrdproduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//卡产品LIST
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		
		UserDao ud=new UserDao();
		//List<UserBean> userList=(List<UserBean>)ud.showCrdproductUserList();
		List<UserBean> userList=(List<UserBean>)ud.showCrdproductUserOfFkList();

		request.setAttribute("userList", userList);		

		return mapping.findForward("addUserCrdproduct");
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
	public ActionForward addUserCrdproduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "/userCrdproduct.do?method=getUserCrdproductList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			int result = UserCrdproductDao.addUserCrdproduct(UserCrdproductForm);
			if (result >= 0) {
				info = "用户卡产品映射(" + UserCrdproductForm.getUser_code() + ")添加成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，用户卡产品映射(" + UserCrdproductForm.getUser_code() + ")添加失败！";
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

	/**
	 * @TODO 准备修改终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 跳转路径
	 */
	public ActionForward preModUserCrdproduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
		UserCrdproductBean UserCrdproductBean = UserCrdproductDao.getUserCrdproductByID(id);
		if (UserCrdproductBean != null) {
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			try {
				BeanUtils.copyProperties(UserCrdproductForm, UserCrdproductBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}		
		//卡产品LIST
		CommonDao comDao = new CommonDao();
		CardProductBean cardProductBean = new CardProductBean();
		List cardProductList = comDao.getCardProductBeanList(null, cardProductBean );
		request.setAttribute("cardProductList", cardProductList);		
		
		UserDao ud=new UserDao();
		//List<UserBean> userList=(List<UserBean>)ud.showAllUserList();
		List<UserBean> userList=(List<UserBean>)ud.showAllUserOfFkList();
		request.setAttribute("userList", userList);		
		
		request.setAttribute("crdProStr", UserCrdproductBean.getCrdproduct());
		return mapping.findForward("editUserCrdproduct");
	}

	public ActionForward modUserCrdproduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/userCrdproduct.do?method=getUserCrdproductList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			// 用于添加完成后，关闭提示信息页面时，调用的查询方法
			UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			int result = UserCrdproductDao.updUserCrdproduct(UserCrdproductForm);
			if (result >= 0) {
				info = "用户卡产品映射(" + UserCrdproductForm.getUser_code() + ")修改成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，用户卡产品映射(" + UserCrdproductForm.getUser_code() + ")修改失败！";
				flushdo = "javascript:history.go(-1)";
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
	
	public ActionForward delUserCrdproduct(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/userCrdproduct.do?method=getUserCrdproductList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			String id = ParamUtils.getParameter(request, "id");
			UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
			int result = UserCrdproductDao.delUserCrdproduct(id);
			if (result >= 0) {
				info = "用户卡产品映射(编号为" + id + ")删除成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，用户卡产品映射(编号为" + id + ")删除失败！";
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
}
