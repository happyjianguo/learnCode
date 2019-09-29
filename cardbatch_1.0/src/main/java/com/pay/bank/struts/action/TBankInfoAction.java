package com.pay.bank.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.bank.bean.TBankInfoBean;
import com.pay.bank.bean.TBranchInfoBean;
import com.pay.bank.dao.TBankInfoDao;
import com.pay.bank.dao.TBranchInfoDao;
import com.pay.bank.struts.form.TBankInfoFullForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;

public class TBankInfoAction extends BaseDispatchAction {

	private static final Logger logger = Logger.getLogger(TBankInfoAction.class);

	public ActionForward getTBankInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			TBankInfoDao TBankInfoDao = new TBankInfoDao();
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = TBankInfoDao.getCount(TBankInfoFullForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<TBankInfoBean> TBankInfoList = TBankInfoDao.getTBankInfoList(
					pageBean, TBankInfoFullForm);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if (TBankInfoList != null && !TBankInfoList.isEmpty()) {
				request.setAttribute("TBankInfoList", TBankInfoList);
			}
		} catch (Exception e) {
			logger.error("TBankInfoAction--getTBankInfoList--Exception:",
					e);
		}
		return mapping.findForward("showTBankInfoList");
	}

	public ActionForward preQueryTBankInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		TBankInfoDao TBankInfoDao = new TBankInfoDao();
		TBankInfoBean TBankInfoBean = TBankInfoDao.getTBankInfoByID(id);		
		if (TBankInfoBean != null) {
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			try {
				BeanUtils.copyProperties(TBankInfoFullForm, TBankInfoBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}
		return mapping.findForward("showTBankInfo");
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
	public ActionForward preAddTBankInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addTBankInfo");
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
	public ActionForward addTBankInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String info = "";
			// 用于添加完成后，关闭提示信息页面时，调用的查询方法
			TBankInfoDao TBankInfoDao = new TBankInfoDao();
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			int result = TBankInfoDao.addTBankInfo(TBankInfoFullForm);
			if (result >= 0) {
				info = "银行("+TBankInfoFullForm.getBank_code()+")添加成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，银行("+TBankInfoFullForm.getBank_code()+")添加失败！";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", info);
		} catch (Exception e) {
			logger.error(
					"TBankInfoAction--addTBankInfoInfo--Exception:", e);
		}
		return mapping.findForward("toGetTBankInfoList");
	}

	/**
	 * @TODO 准备修改终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 跳转路径
	 */
	public ActionForward preModTBankInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		TBankInfoDao TBankInfoDao = new TBankInfoDao();
		TBankInfoBean TBankInfoBean = TBankInfoDao.getTBankInfoByID(id);
		if (TBankInfoBean != null) {
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			try {
				BeanUtils.copyProperties(TBankInfoFullForm, TBankInfoBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}		
		return mapping.findForward("editTBankInfo");
	}

	public ActionForward modTBankInfoInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
		TBankInfoDao TBankInfoDao = new TBankInfoDao();
		TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
		int result = TBankInfoDao.updTBankInfo(TBankInfoFullForm);
		if (result >= 0) {
			info = "银行("+TBankInfoFullForm.getBank_code()+")修改成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，银行("+TBankInfoFullForm.getBank_code()+")修改失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTBankInfoList");
	}
	
	public ActionForward delTBankInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String id = ParamUtils.getParameter(request, "id");
		//先删除子表，再删除母表
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		int result = TBranchInfoDao.delTBranchInfo(null,id);
		TBankInfoDao TBankInfoDao = new TBankInfoDao();
		result = TBankInfoDao.delTBankInfo(id);
		if (result >= 0) {
			info = "银行("+id+")删除成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，银行("+id+")删除失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTBankInfoList");
	}
	
	/**************************************************银行分支行***************************************************/
	public ActionForward getTBranchInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String id = ParamUtils.getParameter(request, "id");
			if(id!=null&&!"".equals(id)&&id.contains("#")){
				id=id.split("#")[0];
			}
			TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			TBankInfoFullForm.setBank_code(id);
			// 构造分页对象
			int count = TBranchInfoDao.getCount(TBankInfoFullForm, null);
			PageBean pageBean = new PageBean(count,
					Constant.getInstance().PageSize, ParamUtils
							.getIntParameter(request, "currentPage", 1));
			List<TBranchInfoBean> TBranchInfoList = TBranchInfoDao.getTBranchInfoList(
					pageBean, TBankInfoFullForm);
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			if (TBranchInfoList != null && !TBranchInfoList.isEmpty()) {
				request.setAttribute("TBranchInfoList", TBranchInfoList);
			}
			request.setAttribute("id", id);
		} catch (Exception e) {
			logger.error("TBranchInfoAction--getTBankInfoList--Exception:",
					e);
		}
		return mapping.findForward("showTBranchInfoList");
	}

	public ActionForward preQueryTBranchInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String bankCodeAndBranchCode = ParamUtils.getParameter(request, "bankCodeAndBranchCode");
		String branchCode="";
		if(bankCodeAndBranchCode!=null&&!"".equals(bankCodeAndBranchCode)&&bankCodeAndBranchCode.contains("#")){
			branchCode=bankCodeAndBranchCode.split("#")[1];
		}
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		TBranchInfoBean TBranchInfoBean = TBranchInfoDao.getTBranchInfoByID(branchCode);		
		if (TBranchInfoBean != null) {
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			try {
				BeanUtils.copyProperties(TBankInfoFullForm, TBranchInfoBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}
		return mapping.findForward("showTBranchInfo");
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
	public ActionForward preAddTBranchInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String id = ParamUtils.getParameter(request, "id");
		if(id!=null&&!"".equals(id)){
			if(id.contains("#")){
				id=id.split("#")[0];
			}
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			TBankInfoFullForm.setBank_code(id);		
			request.setAttribute("id", id);
		}
		return mapping.findForward("addTBranchInfo");
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
	public ActionForward addTBranchInfoInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try{
			String info = "";
			String id = ParamUtils.getParameter(request, "id");
			request.setAttribute("id", id);
			// 用于添加完成后，关闭提示信息页面时，调用的查询方法
			TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			int result = TBranchInfoDao.addTBranchInfo(TBankInfoFullForm);
			if (result >= 0) {
				info = "银行支行("+TBankInfoFullForm.getBranch_code()+")添加成功！";
				request.setAttribute("result", "0");
			} else {
				info = "数据库异常，银行支行("+TBankInfoFullForm.getBranch_code()+")添加失败！";
				request.setAttribute("result", "1");
			}
			request.setAttribute("info", info);
		} catch (Exception e) {
			logger.error(
					"TBranchInfoAction--addTBranchInfoInfo--Exception:", e);
		}
		return mapping.findForward("toGetTBranchInfoList");
	}

	/**
	 * @TODO 准备修改终端信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return 跳转路径
	 */
	public ActionForward preModTBranchInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String bankCodeAndBranchCode = ParamUtils.getParameter(request, "bankCodeAndBranchCode");
		String branchCode="";
		if(bankCodeAndBranchCode!=null&&!"".equals(bankCodeAndBranchCode)&&bankCodeAndBranchCode.contains("#")){
			branchCode=bankCodeAndBranchCode.split("#")[1];
		}
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		TBranchInfoBean TBranchInfoBean = TBranchInfoDao.getTBranchInfoByID(branchCode);
		if (TBranchInfoBean != null) {
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			try {
				BeanUtils.copyProperties(TBankInfoFullForm, TBranchInfoBean);
			} catch (IllegalAccessException e) {
				logger.error("IllegalAccessException", e);
			} catch (InvocationTargetException e) {
				logger.error("InvocationTargetException", e);
			}
		}		
		return mapping.findForward("editTBranchInfo");
	}

	public ActionForward modTBranchInfoInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		// 用于添加完成后，关闭提示信息页面时，调用的查询方法
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
		int result = TBranchInfoDao.updTBranchInfo(TBankInfoFullForm);
		if (result >= 0) {
			info = "银行支行("+TBankInfoFullForm.getBranch_code()+")修改成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，银行支行("+TBankInfoFullForm.getBranch_code()+")修改失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTBranchInfoList");
	}
	
	public ActionForward delTBranchInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String bankCodeAndBranchCode = ParamUtils.getParameter(request, "id");
		String bankCode="";
		String branchCode="";
		if(bankCodeAndBranchCode!=null&&!"".equals(bankCodeAndBranchCode)&&bankCodeAndBranchCode.contains("#")){
			bankCode=bankCodeAndBranchCode.split("#")[0];
			branchCode=bankCodeAndBranchCode.split("#")[1];
			request.setAttribute("id", bankCode);
		}
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		int result = TBranchInfoDao.delTBranchInfo(branchCode,bankCode);
		if (result >= 0) {
			info = "银行支行("+branchCode+")删除成功！";
			request.setAttribute("result", "0");
		} else {
			info = "数据库异常，银行支行("+branchCode+")删除失败！";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTBranchInfoList");
	}
}
