package cn.yufu.posp.bank.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.bank.domain.logic.BanktypeLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.bank.web.form.BanktypeForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

public class BanktypeAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("bankInfo");

	public BanktypeAction() {

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

			log.info("BanktypeAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");

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
			Banktype queryModel = new Banktype();
			// ���̻���Ų�ѯ
			String bankType = request.getParameter("_bankType");
			if (bankType != null && !"".equals(bankType)) {
				queryModel.setBankType(bankType);
			}

			// ���������Ͳ�ѯ
			String typeName = request.getParameter("_typeName");
			if (typeName != null && !"".equals(typeName)) {
				queryModel.setTypeName(typeName);
			}

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("BanktypeAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("BanktypeAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			log.info("selectItems��" + keyStr);
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("BanktypeAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("BanktypeAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {

			log.info("BanktypeAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");

			BanktypeForm newForm = (BanktypeForm) form;

			if ("".equals(newForm.getBankType()) || newForm.getBankType() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getBankType(), ud);

			// �½�һ��Model
			Banktype model = (Banktype) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("BanktypeAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.findItem()���ó����쳣��");
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
			log.info("BanktypeAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");
			// �õ�Form
			BanktypeForm newForm = (BanktypeForm) form;
			// �½�һ��Model
			Banktype model = new Banktype();
			
			BeanUtils.copyProperties(model, newForm);

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("BanktypeAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.saveItem()���ó����쳣��");
			// log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("BanktypeAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");
			// �õ�Form
			BanktypeForm newForm = (BanktypeForm) form;
			// �½�һ��Model
			Banktype model = new Banktype();

			BeanUtils.copyProperties(model, newForm);

			// �����µİ칫��Ʒ��Ʒ���
			try {
				logic.createItem(model, ud);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException("������������ʱ�����쳣");
			}

			log.info("BanktypeAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

}
