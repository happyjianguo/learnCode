package cn.yufu.posp.keyManager.web.action;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.keyManager.domain.logic.PospZmkKeyLogicInterface;
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;
import cn.yufu.posp.keyManager.web.form.PospZmkKeyForm;

public class PospZmkKeyDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("key");

	/*** ��ѯ **/
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("PospZmkKeyDispatchAction.queryAll()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			PospZmkKeyLogicInterface logic = (PospZmkKeyLogicInterface) getBean("PospZmkKeyLogic");

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

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// ���ò�ѯ����
			PospZmkKey queryModel = new PospZmkKey();
			// ���̻���Ų�ѯ
			String moduleId = request.getParameter("_moduleId");
			if (moduleId != null) {
				if (!moduleId.trim().equals("")) {
					queryModel.setModuleId(moduleId);
				}
			}
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("PospZmkKeyDispatchAction.queryAll()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("PospZmkKeyDispatchAction.queryAll()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * ��ʾ�޸Ľ���
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("PospZmkKeyDispatchAction.findItem()��ʼ���ã���ʾ�޸Ľ��档");

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			PospZmkKey btsKey = new PospZmkKey();

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			PospZmkKeyLogicInterface logic = (PospZmkKeyLogicInterface) getBean("PospZmkKeyLogic");
			// �õ�Form
			PospZmkKeyForm btsKeyForm = (PospZmkKeyForm) form;

			map = logic.findItemByKey(btsKey, ud);

			BtsKey model = (BtsKey) map.get("Infolist");

			BeanUtils.copyProperties(btsKeyForm, model);

			log.info("PospZmkKeyDispatchAction.findItem()�������ã���ʾ�޸Ľ��档");

		} catch (Exception e) {

			log.info("PospZmkKeyDispatchAction.findItem()��ʾ�޸Ľ��棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("PospZmkKeyDispatchAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			PospZmkKeyLogicInterface logic = (PospZmkKeyLogicInterface) getBean("PospZmkKeyLogic");
			// �õ�Form
			PospZmkKeyForm newForm = (PospZmkKeyForm) form;
			// �½�һ��Model
			PospZmkKey model = new PospZmkKey();
			BeanUtils.copyProperties(model, newForm);
			

			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("PospZmkKeyDispatchAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PospZmkKeyDispatchAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
}
