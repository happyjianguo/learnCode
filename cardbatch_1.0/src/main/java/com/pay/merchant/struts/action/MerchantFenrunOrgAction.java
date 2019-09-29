/**
 *����:com.pay.merchant.struts.action
 *����:package com.pay.merchant.struts.action;
 */
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

import com.pay.merchant.bean.MerchantFenrunOrgBean;
import com.pay.merchant.dao.MerchantFenrunOrgDao;
import com.pay.merchant.dao.Merchantdao;
import com.pay.merchant.struts.form.MerchantFenrunOrgForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

import cn.yufu.system.common.shiro.UserUtils;
/**
 * MerchantFenrunOrgAction.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��4��26��
 * ����:�������
 */
public class MerchantFenrunOrgAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(MerchantOrgAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	/**
	 * ��ȡ�̻�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getMerchantOrgList(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
		try {
	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
			String orgName = ParamUtils.getParameter(request, "orgName").trim();
			MerchantFenrunOrgDao merchantFenrunOrgDao = new MerchantFenrunOrgDao();
			
			// ����̻���Ϣ�б����м�¼
			MerchantFenrunOrgForm merchantOrgForm = (MerchantFenrunOrgForm)form;
	        merchantOrgForm.setOrgName(orgName);
	        
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
	        
			int count = merchantFenrunOrgDao.getCount(merchantOrgForm,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			
			List<MerchantFenrunOrgBean> merchantLOrgist = null;
			if(count > 0){
				merchantLOrgist = merchantFenrunOrgDao.getMerchantOrgList(pageBean, merchantOrgForm);
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
     * @TODO ׼���޸��̻���Ϣ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     */
    public ActionForward preModMerchantOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	MerchantFenrunOrgForm merchantOrgForm = (MerchantFenrunOrgForm)form;
        String orgId = ParamUtils.getParameter(request, "orgId");
        request.setAttribute("orgId", orgId);
        MerchantFenrunOrgDao merchantOrgDao = new MerchantFenrunOrgDao();
        
        MerchantFenrunOrgBean merchantOrgBean = merchantOrgDao.getMerchantOrgById(orgId);
        
        try {
			BeanUtils.copyProperties(merchantOrgForm, merchantOrgBean);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
        
        return mapping.findForward("modifyMerchantOrg.jsp");
    }
    
    /**
     * @TODO �޸��̻���Ϣ
     */
    public ActionForward modMerchantOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
		
		String info = "";
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "/merchantFenrunOrg.do?method=getMerchantOrgList";
		
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
        	MerchantFenrunOrgDao merchantOrgDao = new MerchantFenrunOrgDao();
        	MerchantFenrunOrgForm merchantOrgForm = (MerchantFenrunOrgForm)form;
    		
    		//�̻�������Ϣ
        	MerchantFenrunOrgBean merchantOrgBean = new MerchantFenrunOrgBean();
    		filledMerchantOrgBean(merchantOrgForm,merchantOrgBean);
    		//���޸Ļ���״̬Ϊ����ʱ�������̻�������
    		if ("1".equals(merchantOrgBean.getOrgStat())) {
    			Merchantdao merchantDao = new Merchantdao();
    			merchantDao.deleteMerchantOrd(merchantOrgBean.getOrgId());
			}
    		
    		int result = merchantOrgDao.updateMerchantOrgInfo(merchantOrgBean);
    		if (result >= 0) {
	            info = "�̻�������Ϣ�޸ĳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "���ݿ��쳣���̻�������Ϣ�޸�ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("resultOfUpdate.jsp");
	}
    
    /**
	 * ��ʼ�������̻�����ҳ��
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
	 * ����̻�������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addMerchantOrgBeanInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		String info = "";
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "closewindow";
        
        if (UserUtils.getUserName()!=null && !"".equals(UserUtils.getUserName())) {
        	MerchantFenrunOrgDao merchantOrgDao = new MerchantFenrunOrgDao();
        	MerchantFenrunOrgForm merchantOrgForm = (MerchantFenrunOrgForm)form;
    		
        	MerchantFenrunOrgBean merchantOrgBean = new MerchantFenrunOrgBean();
        	filledMerchantOrgBean(merchantOrgForm,merchantOrgBean);
        	
    		//����merchantdao������̻���Ϣ�ķ���
    		int result = merchantOrgDao.addMerchantOrgInfo(merchantOrgBean);
    		if (result >= 0) {
	            info = "�̻�������Ϣ��ӳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "���ݿ��쳣���̻�������Ϣ���ʧ�ܣ�";
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

	private void filledMerchantOrgBean(MerchantFenrunOrgForm merchantOrgForm, MerchantFenrunOrgBean merchantOrgBean) {
		merchantOrgBean.setOrgId(merchantOrgForm.getOrgId());
		merchantOrgBean.setOrgName(merchantOrgForm.getOrgName());
		merchantOrgBean.setOrgBin(merchantOrgForm.getOrgBin());
		merchantOrgBean.setOrgPurposeAmt(merchantOrgForm.getOrgPurposeAmt());
//		String orgRatioStr = merchantOrgForm.getOrgRatio();
//		if (StringUtils.isNotEmptyStr(orgRatioStr)) {
//			String[] orgRatio = orgRatioStr.split("%");
//			String str = (Integer.parseInt(orgRatio[0]) * 0.01) + "";
//			if (str.length() != 4) {
//				str += "0";
//			}
//			merchantOrgBean.setOrgRatio(str);
//		}
//		merchantOrgBean.setOrgSingleAmt(merchantOrgForm.getOrgSingleAmt());
		merchantOrgBean.setOrgStat(merchantOrgForm.getOrgStat());
		merchantOrgBean.setCreateBy(UserUtils.getPrincipal().getId());
		merchantOrgBean.setCreateDate(DateUtils.getInstance().format(new Date(), "yyyyMMdd"));
		merchantOrgBean.setUpdateBy(UserUtils.getPrincipal().getId());
		merchantOrgBean.setUpdateDate(DateUtils.getInstance().format(new Date(), "yyyyMMdd"));
	}
	
}

