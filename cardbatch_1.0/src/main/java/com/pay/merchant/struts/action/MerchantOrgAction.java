package com.pay.merchant.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.merchant.bean.MerchantOrgBean;
import com.pay.merchant.dao.MerchantOrgDao;
import com.pay.merchant.dao.Merchantdao;
import com.pay.merchant.struts.form.MerchantOrgForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

import cn.yufu.system.common.shiro.UserUtils;

public class MerchantOrgAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(MerchantOrgAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	/**
	 * 获取商户机构信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getMerchantOrgList(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
		try {
	        // 进入主页前，把查询条件设置为空
			HttpSession session = request.getSession();
			String orgName = ParamUtils.getParameter(request, "orgName").trim();
			MerchantOrgDao merchantOrgDao = new MerchantOrgDao();
			
			// 获得商户信息列表所有记录
	        MerchantOrgForm merchantOrgForm = (MerchantOrgForm)form;
	        merchantOrgForm.setOrgName(orgName);
	        
	        // 设置当前页码
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
	        
			int count = merchantOrgDao.getCount(merchantOrgForm,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			
			// 保存分页对象信息
			request.setAttribute("pageBean", pageBean);
			
			List<MerchantOrgBean> merchantLOrgist = null;
			if(count > 0){
				merchantLOrgist = merchantOrgDao.getMerchantOrgList(pageBean, merchantOrgForm);
			}		
			if (merchantLOrgist != null && !merchantLOrgist.isEmpty()) {
				request.setAttribute("merchantOrgList", merchantLOrgist);
			}
		} catch (Exception e) {
			logger.error("MerchantOrgAction--getMerchantOrgList--Exception:", e);
		}
		return mapping.findForward("merchantOrgList");
	}
	
	/**
     * @TODO 准备修改商户信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     */
    public ActionForward preModMerchantOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	MerchantOrgForm merchantOrgForm = (MerchantOrgForm)form;
        String orgId = ParamUtils.getParameter(request, "orgId");
        request.setAttribute("orgId", orgId);
        MerchantOrgDao merchantOrgDao = new MerchantOrgDao();
        
        MerchantOrgBean merchantOrgBean = merchantOrgDao.getMerchantOrgById(orgId);
        
        try {
			BeanUtils.copyProperties(merchantOrgForm, merchantOrgBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
        
        return mapping.findForward("modifyMerchantOrg.jsp");
    }
    
    /**
     * @TODO 修改商户信息
     */
    public ActionForward modMerchantOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
		
		String info = "";
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "/merchantOrg.do?method=getMerchantOrgList";
		
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
        	MerchantOrgDao merchantOrgDao = new MerchantOrgDao();
        	MerchantOrgForm merchantOrgForm = (MerchantOrgForm)form;
    		
    		//商户机构信息
    		MerchantOrgBean merchantOrgBean = new MerchantOrgBean();
    		filledMerchantOrgBean(merchantOrgForm,merchantOrgBean);
    		//当修改机构状态为锁定时，进行商户解绑操作
    		if ("1".equals(merchantOrgBean.getOrgStat())) {
    			Merchantdao merchantDao = new Merchantdao();
    			merchantDao.deleteMerchantOrd(merchantOrgBean.getOrgId());
			}
    		
    		int result = merchantOrgDao.updateMerchantOrgInfo(merchantOrgBean);
    		if (result >= 0) {
	            info = "商户机构信息修改成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "数据库异常，商户机构信息修改失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("resultOfUpdate.jsp");
	}
    
    /**
	 * 初始化增加商户机构页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddMerchantOrg(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addMerchantOrg.jsp");
	}
    
    /**
	 * 添加商户机构信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addMerchantOrgBeanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		String info = "";
		//用于添加完成后，关闭提示信息页面时，调用的查询方法
		String flushdo = "closewindow";
        
        if (UserUtils.getUserName()!=null && !"".equals(UserUtils.getUserName())) {
        	MerchantOrgDao merchantOrgDao = new MerchantOrgDao();
        	MerchantOrgForm merchantOrgForm = (MerchantOrgForm)form;
    		
        	MerchantOrgBean merchantOrgBean = new MerchantOrgBean();
        	filledMerchantOrgBean(merchantOrgForm,merchantOrgBean);
        	
    		//调用merchantdao的添加商户信息的方法
    		int result = merchantOrgDao.addMerchantOrgInfo(merchantOrgBean);
    		if (result >= 0) {
	            info = "商户机构信息添加成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "数据库异常，商户机构信息添加失败！";
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

	private void filledMerchantOrgBean(MerchantOrgForm merchantOrgForm, MerchantOrgBean merchantOrgBean) {
		merchantOrgBean.setOrgId(merchantOrgForm.getOrgId());
		merchantOrgBean.setOrgName(merchantOrgForm.getOrgName());
		
		String orgRatioStr = merchantOrgForm.getOrgRatio();
		if (StringUtils.isNotEmptyStr(orgRatioStr)) {
			String[] orgRatio = orgRatioStr.split("%");
			String str = (Integer.parseInt(orgRatio[0]) * 0.01) + "";
			if (str.length() != 4) {
				str += "0";
			}
			merchantOrgBean.setOrgRatio(str);
		}
		merchantOrgBean.setOrgSingleAmt(merchantOrgForm.getOrgSingleAmt());
		merchantOrgBean.setOrgStat(merchantOrgForm.getOrgStat());
		merchantOrgBean.setCreateBy(UserUtils.getPrincipal().getId());
		merchantOrgBean.setCreateDate(DateUtils.getInstance().format(new Date(), "yyyyMMdd"));
		merchantOrgBean.setUpdateBy(UserUtils.getPrincipal().getId());
		merchantOrgBean.setUpdateDate(DateUtils.getInstance().format(new Date(), "yyyyMMdd"));
	}
	
}
