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
			// �����ҳ����
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = UserCrdproductDao.getCount(UserCrdproductForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<UserCrdproductBean> UserCrdproductList = UserCrdproductDao.getUserCrdproductList(
					pageBean, UserCrdproductForm);
			// �����ҳ������Ϣ
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
			//����ƷLIST
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
	 * ��ʼ�������̻�ҳ��
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
		//����ƷLIST
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
	 * ����̻���Ϣ
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
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/userCrdproduct.do?method=getUserCrdproductList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			int result = UserCrdproductDao.addUserCrdproduct(UserCrdproductForm);
			if (result >= 0) {
				info = "�û�����Ʒӳ��(" + UserCrdproductForm.getUser_code() + ")��ӳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣���û�����Ʒӳ��(" + UserCrdproductForm.getUser_code() + ")���ʧ�ܣ�";
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

	/**
	 * @TODO ׼���޸��ն���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
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
		//����ƷLIST
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
			// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
			UserCrdproductDao UserCrdproductDao = new UserCrdproductDao();
			UserCrdproductForm UserCrdproductForm = (UserCrdproductForm) form;
			int result = UserCrdproductDao.updUserCrdproduct(UserCrdproductForm);
			if (result >= 0) {
				info = "�û�����Ʒӳ��(" + UserCrdproductForm.getUser_code() + ")�޸ĳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣���û�����Ʒӳ��(" + UserCrdproductForm.getUser_code() + ")�޸�ʧ�ܣ�";
				flushdo = "javascript:history.go(-1)";
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
				info = "�û�����Ʒӳ��(���Ϊ" + id + ")ɾ���ɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣���û�����Ʒӳ��(���Ϊ" + id + ")ɾ��ʧ�ܣ�";
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
}
