/**
 *����:com.pay.batch.bflowlog.struts.action
 *����:package com.pay.batch.bflowlog.struts.action;
 */
package com.pay.batch.bflowlog.struts.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.batch.bflowlog.bean.CrdformatMapBean;
import com.pay.batch.bflowlog.bean.IidPinChkBean;
import com.pay.batch.bflowlog.bean.SixBFlowLogBean;
import com.pay.batch.bflowlog.dao.CrdformatMapDao;
import com.pay.batch.bflowlog.dao.IidPinChkDao;
import com.pay.batch.bflowlog.struts.form.CrdformatMapForm;
import com.pay.batch.bflowlog.struts.form.IidPinChkForm;
import com.pay.query.struts.BaseDispatchAction;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

import cn.yufu.system.common.shiro.UserUtils;

/**
 * IidPinChkAction.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��7��4��
 * ����:�����������
 */
public class IidPinChkAction extends BaseDispatchAction {

    private static final Logger logger = Logger.getLogger(IidPinChkAction.class);

    
    public ActionForward getIidPinChkList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
		// ������ҳǰ���Ѳ�ѯ��������Ϊ��
		try {
			// ������ҳǰ���Ѳ�ѯ��������Ϊ��
			HttpSession session = request.getSession();
			IidPinChkDao iidPinChkDao = new IidPinChkDao();
			// ����ն���Ϣ�б��¼
			IidPinChkForm iidPinChkForm = (IidPinChkForm) form;
	        // ���õ�ǰҳ��
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
			}
			int count = iidPinChkDao.getCount(iidPinChkForm, session);
			PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize,cPage);
			request.setAttribute("pageBean", pageBean);
			List iidPinChkList = null;
			if (count > 0){
				iidPinChkList = iidPinChkDao.getIidPinChkList(pageBean, iidPinChkForm);
			}
			if (iidPinChkList != null && !iidPinChkList.isEmpty()) {
				request.setAttribute("iidPinChkList", iidPinChkList);
			}
		} catch (Exception e) {
			logger.error("IidPinChkAction--getIidPinChkList--Exception:", e);
		}
		return mapping.findForward("showIidPinChkList");
	}
    
    public ActionForward delIidPinChk(ActionMapping mapping, ActionForm form,
    		HttpServletRequest request, HttpServletResponse response) {
		String info = "";
		// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
		IidPinChkDao iidPinChkDao = new IidPinChkDao();
		String iid= ParamUtils.getParameter(request, "iid");

		int result = iidPinChkDao.delIidPinChk(iid);
		if (result >= 0) {
			info = "��bin("+iid+")��������ӳ��ɾ���ɹ���";
			request.setAttribute("result", "0");
		} else {
			info = "���ݿ��쳣����bin("+iid+")��������ӳ��ɾ��ʧ�ܣ�";
			request.setAttribute("result", "1");
		}
		request.setAttribute("info", info);
		return mapping.findForward("toGetIidPinChkList");
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
	public ActionForward preAddIidPinChk(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("addIidPinChk.jsp");
	}
	
	@SuppressWarnings("rawtypes")
	public ActionForward addIidPinChk(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String info = "";
			String flushdo = "closewindow";
			// ���������ɺ󣬹ر���ʾ��Ϣҳ��ʱ�����õĲ�ѯ����
			IidPinChkDao iidPinChkDao = new IidPinChkDao();
			IidPinChkForm iidPinChkForm = (IidPinChkForm) form;
			String iid = iidPinChkForm.getIid();
			// �жϿ�Biniid�Ƿ��ظ����Ƿ����
			if(StringUtils.isNotEmptyStr(iid)){
				IidPinChkForm iForm = new IidPinChkForm();
				iForm.setQueryiid(iid);
				List li = iidPinChkDao.getIidPinChkList(null,iForm);
				if((null == li || li.size() ==0)){
					CrdformatMapDao CrdformatMapDao = new CrdformatMapDao();
					CrdformatMapForm crdformatMapForm = new CrdformatMapForm();
					crdformatMapForm.setQueryIid(iid);
					List<CrdformatMapBean> lii = CrdformatMapDao.getCrdformatMapList(null,crdformatMapForm);
					boolean lib = !(null == lii || lii.size() ==0);
					CrdformatMapDao crdformatMapDao = new CrdformatMapDao();
					List<SixBFlowLogBean> lst = crdformatMapDao.getSixCrdformatMapListByKabin(null, iid);
					boolean lsb = !(null == lst || lst.size() ==0);
					if(lib || lsb){
						IidPinChkBean IidPinChkBean = new IidPinChkBean();
						IidPinChkBean.setIid(iid);
						IidPinChkBean.setIvrpinfalg(iidPinChkForm.getIvrpinfalg());
						IidPinChkBean.setPospinfalg(iidPinChkForm.getPospinfalg());
						IidPinChkBean.setAdddatetime(DateUtils.getInstance().format(new Date(), "yyyyMMddHHmmss"));
						IidPinChkBean.setOper(UserUtils.getUserName());
						
						if (UserUtils.getUserName() != null) {
							int result = iidPinChkDao.addIidPinChk(IidPinChkBean);
							if (result >= 0) {
								info = "��bin(" + iid + ")��������ӳ����ӳɹ���";
								request.setAttribute("result", "0");
							} else {
								info = "���ݿ��쳣����bin(" + iid + ")��������ӳ�����ʧ�ܣ�";
								request.setAttribute("result", "1");
							}
							request.setAttribute("info", StringUtils.outerToInner(info));
							request.setAttribute("flushdo", flushdo);
						} else {
							request.setAttribute("info", "������ʱ�������µ�¼��");
							request.setAttribute("flushdo", flushdo);
							request.setAttribute("result", "1");
						}
					
					}else{
						//��BINӳ�䲻����
						info = "��bin(" + iid + ")ӳ�䲻���ڣ�������ӿ�BINӳ��������⿨BINӳ�䣡";
						request.setAttribute("result", "1");
						request.setAttribute("info", StringUtils.outerToInner(info));
						request.setAttribute("flushdo", flushdo);
						//���⿨BINӳ�䲻����
					}
					/*if((null == lii || lii.size() ==0)){
						//��BINӳ�䲻����
						info = "��bin(" + iid + ")ӳ�䲻���ڣ�������ӿ�BINӳ�䣡";
						request.setAttribute("result", "1");
						request.setAttribute("info", StringUtils.outerToInner(info));
						request.setAttribute("flushdo", flushdo);
					}else if((null == lst || lst.size() ==0)){
						//���⿨BINӳ�䲻����
						info = "���⿨bin(" + iid + ")ӳ�䲻���ڣ�����������⿨BINӳ�䣡";
						request.setAttribute("result", "1");
						request.setAttribute("info", StringUtils.outerToInner(info));
						request.setAttribute("flushdo", flushdo);
					}else{
						IidPinChkBean IidPinChkBean = new IidPinChkBean();
						IidPinChkBean.setIid(iid);
						IidPinChkBean.setIvrpinfalg(iidPinChkForm.getIvrpinfalg());
						IidPinChkBean.setPospinfalg(iidPinChkForm.getPospinfalg());
						IidPinChkBean.setAdddatetime(DateUtils.getInstance().format(new Date(), "yyyyMMddHHmmss"));
						IidPinChkBean.setOper(UserUtils.getUserName());
						
						if (UserUtils.getUserName() != null) {
							int result = iidPinChkDao.addIidPinChk(IidPinChkBean);
							if (result >= 0) {
								info = "��bin(" + iid + ")��������ӳ����ӳɹ���";
								request.setAttribute("result", "0");
							} else {
								info = "���ݿ��쳣����bin(" + iid + ")��������ӳ�����ʧ�ܣ�";
								request.setAttribute("result", "1");
							}
							request.setAttribute("info", StringUtils.outerToInner(info));
							request.setAttribute("flushdo", flushdo);
						} else {
							request.setAttribute("info", "������ʱ�������µ�¼��");
							request.setAttribute("flushdo", flushdo);
							request.setAttribute("result", "1");
						}
					}*/
				}else{
					//��Ϣ�Ѵ���
					info = "��bin(" + iid + ")���������Ѿ����ڣ������ظ���ӣ�";
					request.setAttribute("result", "1");
					request.setAttribute("info", StringUtils.outerToInner(info));
					request.setAttribute("flushdo", flushdo);
				}
			}else{
				//��binΪ��
				info = "��bin(" + iid + ")Ϊ�գ���˶���Ϣ��";
				request.setAttribute("result", "1");
				request.setAttribute("info", StringUtils.outerToInner(info));
				request.setAttribute("flushdo", flushdo);
			}
		} catch (Exception e) {
			logger.error("CrdformatMapAction--addCrdformatMapInfo--Exception:", e);
		}
		/*return mapping.findForward("result.jsp");*/
		return mapping.findForward("submitinfo");
	}
}
