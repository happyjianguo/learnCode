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
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = TBankInfoDao.getCount(TBankInfoFullForm, null);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, cPage);
			List<TBankInfoBean> TBankInfoList = TBankInfoDao.getTBankInfoList(
					pageBean, TBankInfoFullForm);
			// �����ҳ������Ϣ
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
	 * ��ʼ�������̻�ҳ��
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
	 * ����̻���Ϣ
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
			// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
			TBankInfoDao TBankInfoDao = new TBankInfoDao();
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			int result = TBankInfoDao.addTBankInfo(TBankInfoFullForm);
			if (result >= 0) {
				info = "����("+TBankInfoFullForm.getBank_code()+")��ӳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣������("+TBankInfoFullForm.getBank_code()+")���ʧ�ܣ�";
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
	 * @TODO ׼���޸��ն���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
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
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		TBankInfoDao TBankInfoDao = new TBankInfoDao();
		TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
		int result = TBankInfoDao.updTBankInfo(TBankInfoFullForm);
		if (result >= 0) {
			info = "����("+TBankInfoFullForm.getBank_code()+")�޸ĳɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣������("+TBankInfoFullForm.getBank_code()+")�޸�ʧ�ܣ�";
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
		//��ɾ���ӱ���ɾ��ĸ��
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		int result = TBranchInfoDao.delTBranchInfo(null,id);
		TBankInfoDao TBankInfoDao = new TBankInfoDao();
		result = TBankInfoDao.delTBankInfo(id);
		if (result >= 0) {
			info = "����("+id+")ɾ���ɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣������("+id+")ɾ��ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTBankInfoList");
	}
	
	/**************************************************���з�֧��***************************************************/
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
			// �����ҳ����
			int count = TBranchInfoDao.getCount(TBankInfoFullForm, null);
			PageBean pageBean = new PageBean(count,
					Constant.getInstance().PageSize, ParamUtils
							.getIntParameter(request, "currentPage", 1));
			List<TBranchInfoBean> TBranchInfoList = TBranchInfoDao.getTBranchInfoList(
					pageBean, TBankInfoFullForm);
			// �����ҳ������Ϣ
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
	 * ��ʼ�������̻�ҳ��
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
	 * ����̻���Ϣ
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
			// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
			TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
			TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
			int result = TBranchInfoDao.addTBranchInfo(TBankInfoFullForm);
			if (result >= 0) {
				info = "����֧��("+TBankInfoFullForm.getBranch_code()+")��ӳɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣������֧��("+TBankInfoFullForm.getBranch_code()+")���ʧ�ܣ�";
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
	 * @TODO ׼���޸��ն���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ��ת·��
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
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		TBranchInfoDao TBranchInfoDao = new TBranchInfoDao();
		TBankInfoFullForm TBankInfoFullForm = (TBankInfoFullForm) form;
		int result = TBranchInfoDao.updTBranchInfo(TBankInfoFullForm);
		if (result >= 0) {
			info = "����֧��("+TBankInfoFullForm.getBranch_code()+")�޸ĳɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣������֧��("+TBankInfoFullForm.getBranch_code()+")�޸�ʧ�ܣ�";
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
			info = "����֧��("+branchCode+")ɾ���ɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣������֧��("+branchCode+")ɾ��ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetTBranchInfoList");
	}
}
