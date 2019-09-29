/**
 *����:com.pay.merchant.struts.action
 *����:package com.pay.merchant.struts.action;
 */
package com.pay.merchant.struts.action;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.merchant.bean.MerchantBean;
import com.pay.merchant.bean.TCardMerInfoBean;
import com.pay.merchant.dao.Merchantdao;
import com.pay.merchant.dao.TCardMerInfoDao;
import com.pay.merchant.struts.form.TCardMerInfoForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * TCardMerInfoAction.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��6��22��
 * ����:�̻�������� 
 */
public class TCardMerInfoAction extends BaseDispatchAction {
	
	private static final Logger logger = Logger.getLogger(TCardMerInfoAction.class);
	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
	/**
	 * ��ȡ�̻�������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward getTCardMerInfoList(ActionMapping mapping, ActionForm form,  
			HttpServletRequest request, HttpServletResponse response) {
		try {
	        // ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
			String cardNo = ParamUtils.getParameter(request, "cardNo").trim();//����
			String merNo = ParamUtils.getParameter(request, "merNo").trim();//�̻���
			TCardMerInfoDao tCardMerInfoDao = new TCardMerInfoDao();
			TCardMerInfoForm tCardMerInfoForm = (TCardMerInfoForm)form;
			tCardMerInfoForm.setCardNo(cardNo);
			tCardMerInfoForm.setMerNo(merNo);
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = tCardMerInfoDao.getCount(tCardMerInfoForm,session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			// �����ҳ������Ϣ
			request.setAttribute("pageBean", pageBean);
			List<TCardMerInfoBean> tCardMerInfoBeanist = null;
			if(count > 0){
				tCardMerInfoBeanist = tCardMerInfoDao.getTCardMerInfoList(pageBean, tCardMerInfoForm);
			}		
			if (tCardMerInfoBeanist != null && !tCardMerInfoBeanist.isEmpty()) {
				request.setAttribute("merchantOrgList", tCardMerInfoBeanist);
			}
		} catch (Exception e) {
			logger.error("TCardMerInfoAction--getTCardMerInfoList--Exception:", e);
		}
		return mapping.findForward("tCardMerInfoBeanist");
	}
    
	/**
	 * ɾ���̻����ǰ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preDeletetCardMerInfo (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String merNo = ParamUtils.getParameter(request, "merNo");
		String cardNo = ParamUtils.getParameter(request, "cardNo");
		request.setAttribute("merNo", merNo);
		request.setAttribute("cardNo", cardNo);
		return mapping.findForward("delTCardMerInfo.jsp");
	}
	/**
	 * ɾ���̻����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deltCardMerInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		String flushdo = "/tCardMerInfo.do?method=getTCardMerInfoList";
		if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
			String merNo = ParamUtils.getParameter(request, "merNo");
			String cardNo = ParamUtils.getParameter(request, "cardNo");
			TCardMerInfoDao tCardMerInfoDao = new TCardMerInfoDao();
			TCardMerInfoForm tCardMerInfoForm = (TCardMerInfoForm)form;
			tCardMerInfoForm.setCardNo(cardNo);
			tCardMerInfoForm.setMerNo(merNo);
			int result = tCardMerInfoDao.delTCardMerInfo(tCardMerInfoForm, null);
			if (result >= 0) {
				info = "ɾ���̻����(�̻��ţ�" + merNo + "����" +cardNo+")�ɹ���";
				request.setAttribute("result", "0");
			} else {
				info = "���ݿ��쳣��ɾ���̻����(�̻��ţ�" + merNo + "����"+cardNo+")ʧ�ܣ�";
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
	 * ��ʼ�������̻����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAddTCardMerInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addTCardMerInfo.jsp");
	}
    /**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward addTCardMerInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String info = "";
		//���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		String flushdo = "closewindow";
        if (UserUtils.getUserName()!=null && !"".equals(UserUtils.getUserName())) {
        	TCardMerInfoDao tCardMerInfoDao = new TCardMerInfoDao();
        	Merchantdao merchantdao = new Merchantdao();
			TCardMerInfoForm tCardMerInfoForm = (TCardMerInfoForm)form;
			TCardMerInfoBean tCardMerInfoBean = new TCardMerInfoBean();
        	filledTCardMerInfoBean(tCardMerInfoForm,tCardMerInfoBean);
        	String startno = tCardMerInfoBean.getStartcardNo();
    		String endno = tCardMerInfoBean.getEndcardNo();
    		String merno = tCardMerInfoBean.getMerNo();
    		BigDecimal sno = new BigDecimal(startno); 
			BigDecimal eno = new BigDecimal(endno); 	
//    		�ж��̻��ź���ʼ�����Ƿ�Ϸ�
    		if(isInteger(startno) && isInteger(endno) && isInteger(merno)){
    			//�жϽ��������Ƿ���ڿ�ʼ����
    			if(eno.compareTo(sno) >= 0 || eno.subtract(sno).intValue()+1 > 1000){
    				int smatch = eno.subtract(sno).intValue()+1;
    				//�ж��̻����Ƿ����
    				List<MerchantBean> li = merchantdao.getMerchantBeanListByMrchNoOrName(merno, null, null, null, null, null);
    				//�жϿ����Ƿ����
    				int cu = tCardMerInfoDao.getTCardCount(startno,endno);
    				if(null == li || li.size() ==0 || cu != smatch){
    					info = "�̻��Ż���ʼ���������������ڣ��̻������Ϣ���ʧ�ܣ�";
        	            flushdo = "javascript:history.go(-1)";
        	            request.setAttribute("result", "1");
    				}else{
    					//�ж��̻������Ϣ�Ƿ����
    					List<TCardMerInfoBean> lis = tCardMerInfoDao.getTCardMerInfoList(null,tCardMerInfoForm);
    					if(null == lis || lis.size() ==0 ){
    						int result = tCardMerInfoDao.addTCardMerInfo(tCardMerInfoBean);
    						if (result >= 0) {
    							info = smatch +"���̻������Ϣ��ӳɹ���";
    							request.setAttribute("result", "0");
    						} else {
    							info = "���ݿ��쳣���̻������Ϣ���ʧ�ܣ�";
    							flushdo = "javascript:history.go(-1)";
    							request.setAttribute("result", "1");
    						}
    					}else{
    						info = "���̻�����˿����������̻������Ϣ�Ѿ����ڣ��̻������Ϣ���ʧ�ܣ�";
							flushdo = "javascript:history.go(-1)";
							request.setAttribute("result", "1");
    					}
    				}
    			}else{
    				info = "��������С�ڿ�ʼ���Ż������俨�η�Χ����1000���̻������Ϣ���ʧ�ܣ�";
    	            flushdo = "javascript:history.go(-1)";
    	            request.setAttribute("result", "1");
    			}
    		}else{
    			info = "�̻��Ż���ʼ���Ų��Ϸ����̻������Ϣ���ʧ�ܣ�";
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

	private void filledTCardMerInfoBean(TCardMerInfoForm tCardMerInfoForm, TCardMerInfoBean tCardMerInfoBean) {
		tCardMerInfoBean.setEndcardNo(tCardMerInfoForm.getEndcardNo());
		tCardMerInfoBean.setStartcardNo(tCardMerInfoForm.getStartcardNo());
		tCardMerInfoBean.setMerNo(tCardMerInfoForm.getMerNo());
		tCardMerInfoBean.setAddUser(UserUtils.getUserName());
		tCardMerInfoBean.setTimeStamp(DateUtils.getInstance().format(new Date(), "yyyyMMddHHmmss"));
	}
	/*
	 * �Ƽ����ٶ���� �ж��Ƿ�Ϊ����
	 * @param str ������ַ���
	 * @return ����������true,���򷵻�false
	 */
	public static boolean isInteger(String str) {
		if(org.apache.commons.lang.StringUtils.isBlank(str)){
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}
}
