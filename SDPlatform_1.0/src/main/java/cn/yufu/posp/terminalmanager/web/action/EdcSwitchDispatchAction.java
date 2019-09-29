package cn.yufu.posp.terminalmanager.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
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
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.EdcSwitchLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitchId;
import cn.yufu.posp.terminalmanager.web.form.EdcSwitchForm;

/**
 * @author zhouya �ն�ת����Ϣ
 * 
 */
public class EdcSwitchDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcSwitch");

	public EdcSwitchDispatchAction() {
	}

	/*** ��ѯ�ն˻���������Ϣ **/
	public org.apache.struts.action.ActionForward queryEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcSwitchDispatchAction.queryEdcSwitch()��ʼ���ã������ն�TPDU��Ϣ��");

			// �õ�Logic
			EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");

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
					pageInfo.setOrderField("id.merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.terminalId");
				if (sortField.equals("3"))
					pageInfo.setOrderField("id.bankType");
				if (sortField.equals("4"))
					pageInfo.setOrderField("othTerminalId");
			}

			// ���ò�ѯ����
			EdcSwitch queryModel = new EdcSwitch();
			EdcSwitchId id = new EdcSwitchId();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					id.setMerchantId(merchantId);
				}

			}
			// ���������Ͳ�ѯ
			String bankType = request.getParameter("_bankType");
			if (bankType != null) {
				if (!bankType.trim().equals("")) {
					id.setBankType(bankType);
				}

			}

			queryModel.setId(id);

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> list = commonLogic.queryAllBanktypeItem(null, null);

			request.setAttribute("bankTypeList", list);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllEdcSwitch");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcSwitchDispatchAction.queryEdcSwitch()�������ã������ն�TPDU��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchDispatchAction.queryEdcSwitch()�����ն�TPDU��Ϣ�����ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն˻������� */
	public org.apache.struts.action.ActionForward deleteEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcSwitch> keysList = new ArrayList<EdcSwitch>();
		try {
			log.info("EdcSwitchDispatchAction.deleteEdcSwitch()��ʼ���ã�ɾ���ն�TPDU��Ϣ��");

			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			UserData ud = getSessionUserData(request);

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("�ն�ת�� ɾ����¼ ��������==" + strId);

				if (tt.length == 5) {
					EdcSwitch edcSwitch = new EdcSwitch();
					EdcSwitchId id = new EdcSwitchId();
					id.setMerchantId(tt[0].trim());
					id.setBankType(tt[1].trim());
					id.setTerminalId(tt[2].trim());
					edcSwitch.setOthTerminalId(tt[3].trim());
					edcSwitch.setId(id);
					keysList.add(edcSwitch);
				}
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcSwitchDispatchAction.deleteEdcSwitch()�������ã�ɾ���ն�TPDU��Ϣ��");
		} catch (Exception e) {
			log.info("EdcSwitchDispatchAction.deleteEdcSwitch()ɾ���ն�TPDU��Ϣ�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcSwitch");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcSwitchDispatchAction.showSr()��ʼ���ã���ת������ҳ�档");

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> list = commonLogic.queryAllBanktypeItem(null, null);

			request.setAttribute("bankTypeList", list);

			log.info("EdcSwitchDispatchAction.showSr()�������ã���ת������ҳ�档");
		} catch (Exception e) {
			log.info("EdcSwitchDispatchAction.showSr()��ת������ҳ�棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edcSwitchSr");
	}

	/**
	 * ����һ����Ϣ
	 */
	public org.apache.struts.action.ActionForward createEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcSwitchDispatchAction.createEdcSwitch()��ʼ����:�����µ��ն�TPDU��Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
			// �õ�Form
			EdcSwitchForm edcSwitchForm = (EdcSwitchForm) form;
			// �½�һ��Model
			EdcSwitch edcSwitch = new EdcSwitch();
			BeanUtils.copyProperties(edcSwitch, edcSwitchForm);

			logic.createItem(edcSwitch, ud);

			log.info("EdcSwitchDispatchAction.createEdcSwitch()��������:�����µ��ն�TPDU��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDispatchAction.createEdcSwitch()�����µ��ն�TPDU��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcSwitch");
	}

	/**
	 * ��ʾ�޸��ն�ת����Ϣ��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcSwitchByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		EdcSwitch edcSwitch = new EdcSwitch();
		EdcSwitchId id = new EdcSwitchId();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcSwitchDispatchAction.queryEdcSwitchByKey()��ʼ���ã���ʾ�޸��ն�TPDU��Ϣ��Ϣ���档");

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");

			if (tt.length == 5) {
				id.setMerchantId(tt[0].trim());
				id.setBankType(tt[1].trim());
				id.setTerminalId(tt[2].trim());
				edcSwitch.setOthTerminalId(tt[3].trim());
				;
				edcSwitch.setId(id);

				UserData ud = getSessionUserData(request);
				// �õ�Logic
				EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
				map = logic.findItemByKey(edcSwitch, ud);
				// �õ�Form
				EdcSwitchForm edcSwitchForm = (EdcSwitchForm) form;
				map = logic.findItemByKey(edcSwitch, ud);
				EdcSwitch model = (EdcSwitch) map.get("Infolist");
				BeanUtils.copyProperties(edcSwitchForm, model);

			}

			log.info("EdcSwitchDispatchAction.queryEdcSwitchByKey()�������ã���ʾ�޸��ն�TPDU��Ϣ��Ϣ���档");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDispatchAction.queryEdcSwitchByKey()��ʾ�޸��ն�TPDU��Ϣ��Ϣ���棬���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("queryEdcSwitchByKey");
	}

	/**
	 * �����ն�ת����Ϣ
	 */
	public org.apache.struts.action.ActionForward saveEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcSwitchDispatchAction.saveEdcSwitch()��ʼ���ã������ն�TPDU��Ϣ��Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
			// �õ�Form
			EdcSwitchForm edcSwitchForm = (EdcSwitchForm) form;
			// �½�һ��Model
			EdcSwitch edcSwitch = new EdcSwitch();

			BeanUtils.copyProperties(edcSwitch, edcSwitchForm);

			// �����ն���Ϣ
			logic.saveItem(edcSwitch, ud);

			log.info("=EdcSwitchDispatchAction.saveEdcSwitch()�������ã������ն�TPDU��Ϣ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("=EdcSwitchDispatchAction.saveEdcSwitch()�����ն�TPDU��Ϣ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcSwitch");
	}

}
