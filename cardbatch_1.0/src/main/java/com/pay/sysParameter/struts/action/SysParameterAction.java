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
			// �����ҳ����
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = SysParameterDao.getCount(SysParameterForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<SysParameterBean> SysParameterList = SysParameterDao.getSysParameterList(
					pageBean, SysParameterForm);
			// �����ҳ������Ϣ
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
	 * ��ʼ�������̻�ҳ��
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
	 * ����̻���Ϣ
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
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/sysParameter.do?method=getSysParameterList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			List<SysParameterBean> list = SysParameterDao.getSysParameterIsEnablementLists(SysParameterForm);
			if(null == list || list.size() == 0){
				int result = SysParameterDao.addSysParameter(SysParameterForm);
				if (result >= 0) {
					info = "����(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")��ӳɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣������(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")���ʧ�ܣ�";
					request.setAttribute("result", "1");
				}
			}else{
				info = "����("+ SysParameterForm.getParam_value() +")�Ѵ���,���ʧ�ܣ�";
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
			// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
			SysParameterDao SysParameterDao = new SysParameterDao();
			SysParameterForm SysParameterForm = (SysParameterForm) form;
			List<SysParameterBean> list = SysParameterDao.getSysParameterIsEnablementListsupdate(SysParameterForm);
			if(null == list || list.size() == 0){
				int result = SysParameterDao.updSysParameter(SysParameterForm);
				if (result >= 0) {
					info = "����(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")�޸ĳɹ���";
					request.setAttribute("result", "0");
				} else {
					info = "���ݿ��쳣������(" + SysParameterForm.getParam_type() + ","
							+ SysParameterForm.getParam_name() + ")�޸�ʧ�ܣ�";
					flushdo = "javascript:history.go(-1)";
					request.setAttribute("result", "1");
				}
			}else{
				info = "����(" + SysParameterForm.getParam_type() + ","
						+ SysParameterForm.getParam_name() + ")�Ѵ��ڣ�";
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
				info = "����(���Ϊ" + id + ")ɾ���ɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣������(���Ϊ" + id + ")ɾ��ʧ�ܣ�";
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
