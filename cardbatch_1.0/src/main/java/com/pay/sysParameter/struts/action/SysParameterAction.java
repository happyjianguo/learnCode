package com.pay.sysParameter.struts.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.pay.query.struts.BaseDispatchAction;
import com.pay.sysParameter.bean.SysParameterBean;
import com.pay.sysParameter.dao.SysParameterDao;
import com.pay.sysParameter.struts.form.SysParameterForm;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class SysParameterAction extends BaseDispatchAction {

	private static final Logger logger = Logger.getLogger(SysParameterAction.class);

	public ActionForward getSysParameterList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			// 构造分页对象
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = SysParameterDao.getCount(SysParameterForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<SysParameterBean> SysParameterList = SysParameterDao.getSysParameterList(
					pageBean, SysParameterForm);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if (SysParameterList != null && !SysParameterList.isEmpty()) {
				request.setAttribute("SysParameterList", SysParameterList);
			}
			List<SysParameterBean> sysParameterTypeList = SysParameterDao.getSysParameterTypeList();
			if(sysParameterTypeList!=null&&!sysParameterTypeList.isEmpty()){
				request.setAttribute("sysParameterTypeList", sysParameterTypeList);
			}
		} catch (Exception e) {
			logger.error("SysParameterAction--getSysParameterList--Exception:",
					e);
		}
		return mapping.findForward("showSysParameterList");
	}

	public ActionForward preQuerySysParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		SysParameterDao SysParameterDao = new SysParameterDao();
		SysParameterBean SysParameterBean = SysParameterDao.getSysParameterByID(id);		
		if (SysParameterBean != null) {
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			try {
				BeanUtils.copyProperties(SysParameterForm, SysParameterBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}
		return mapping.findForward("showSysParameter");
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
	public ActionForward preAddSysParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addSysParameter");
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
	public ActionForward addSysParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "/sysParameter.do?method=getSysParameterList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			List<SysParameterBean> list = SysParameterDao.getSysParameterIsEnablementLists(SysParameterForm);
			if(null == list || list.size() == 0){
				int result = SysParameterDao.addSysParameter(SysParameterForm);
				if (result >= 0) {
					info = "参数(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")添加成功！";
					request.setAttribute("result", "0");
				} else {
					info = "数据库异常，参数(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")添加失败！";
					request.setAttribute("result", "1");
				}
			}else{
				info = "参数("+ SysParameterForm.getParam_value() +")已存在,添加失败！";
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
	public ActionForward preModSysParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		SysParameterDao SysParameterDao = new SysParameterDao();
		SysParameterBean SysParameterBean = SysParameterDao.getSysParameterByID(id);
		if (SysParameterBean != null) {
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			try {
				BeanUtils.copyProperties(SysParameterForm, SysParameterBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}		
		return mapping.findForward("editSysParameter");
	}

	public ActionForward modSysParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/sysParameter.do?method=getSysParameterList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			// 用于添加完成后，关闭提示信息页面时，调用的查询方法
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			List<SysParameterBean> list = SysParameterDao.getSysParameterIsEnablementListsupdate(SysParameterForm);
			if(null == list || list.size() == 0){
				int result = SysParameterDao.updSysParameter(SysParameterForm);
				if (result >= 0) {
					info = "参数(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")修改成功！";
					request.setAttribute("result", "0");
				} else {
					info = "数据库异常，参数(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")修改失败！";
					flushdo = "javascript:history.go(-1)";
					request.setAttribute("result", "1");
				}
			}else{
				info = "参数(" + SysParameterForm.getParam_type() + ","
						+ SysParameterForm.getParam_name() + ")已存在！";
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
	
	public void sysParameterHasChild (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		String id = ParamUtils.getParameter(request, "id");
			
		SysParameterDao SysParameterDao = new SysParameterDao();
		int count = SysParameterDao.sysParameterHasChild(id);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(count);
		out.flush();
		out.close();
	}
	
	
	public ActionForward delSysParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/sysParameter.do?method=getSysParameterList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			String id = ParamUtils.getParameter(request, "id");
			SysParameterDao SysParameterDao = new SysParameterDao();
			int result = SysParameterDao.delSysParameter(id);
			if (result >= 0) {
				info = "参数(编号为" + id + ")删除成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，参数(编号为" + id + ")删除失败！";
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
