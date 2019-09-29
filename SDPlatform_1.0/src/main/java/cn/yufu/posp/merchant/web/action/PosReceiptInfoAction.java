package cn.yufu.posp.merchant.web.action;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

import cn.yufu.posp.merchant.domain.logic.PosReceiptInfoLogicInterface;

import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

import cn.yufu.posp.merchant.web.form.PosReceiptInfoForm;


public class PosReceiptInfoAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public PosReceiptInfoAction() {

	}

	/**
	 * ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public ActionForward queryAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			// �õ�Logic
			PosReceiptInfoLogicInterface logic = (PosReceiptInfoLogicInterface) getBean("PosReceiptInfoLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}


			// ���ò�ѯ����
			PosReceiptInfoModel queryModel = new PosReceiptInfoModel();


			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("PosReceiptInfoAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			

			log.info("PosReceiptInfoAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			PosReceiptInfoLogicInterface logic = (PosReceiptInfoLogicInterface) getBean("PosReceiptInfoLogic");

			PosReceiptInfoForm newForm = (PosReceiptInfoForm) form;

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getStatus(), ud);

			// �½�һ��Model
			PosReceiptInfoModel model = (PosReceiptInfoModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("PosReceiptInfoAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoAction.findItem()��ʾ�޸Ľ���,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("PosReceiptInfoAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			PosReceiptInfoLogicInterface logic = (PosReceiptInfoLogicInterface) getBean("PosReceiptInfoLogic");
			// �õ�Form
			PosReceiptInfoForm newForm = (PosReceiptInfoForm) form;
			// �½�һ��Model
			PosReceiptInfoModel model = new PosReceiptInfoModel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			model.setHotline(newForm.getHotline());
			model.setTelSupport(newForm.getTelSupport());
			model.setAdInfo(newForm.getAdInfo().replace("\r\n",""));
			model.setAdYesNoFlag(newForm.getAdYesNoFlag());
			model.setStatus(newForm.getStatus());
			model.setCreateDate(newForm.getCreateDate());
			model.setUpdateOper(ud.getUserId());
			model.setUpdateDate(sdf.format(new Date()));
	

			// �����µ�POSǩ����Ҫ���������
			logic.saveItem(model, ud);

			log.info("PosReceiptInfoAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}


}
