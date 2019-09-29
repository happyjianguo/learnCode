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
import cn.yufu.posp.terminalmanager.domain.logic.EdcTpduLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;
import cn.yufu.posp.terminalmanager.web.form.EdcTpduForm;

/**
 * @author zhouya �ն�TPDU��Ϣ
 * 
 */
public class EdcTpduDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTpdu");

	public EdcTpduDispatchAction() {
	}

	/*** ��ѯ�ն�TPDU��Ϣ **/
	public org.apache.struts.action.ActionForward queryEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcTpduDispatchAction.queryEdcTpdu()��ʼ���ã������ն�TPDU��Ϣ��");

			// �õ�Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");

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
					pageInfo.setOrderField("tpdu");
				if (sortField.equals("2"))
					pageInfo.setOrderField("posLinkType");
				if (sortField.equals("3"))
					pageInfo.setOrderField("chnlno");
				if (sortField.equals("4"))
					pageInfo.setOrderField("moduleId");
				if (sortField.equals("5"))
					pageInfo.setOrderField("packType");
				if (sortField.equals("6"))
					pageInfo.setOrderField("packTypeno");
			}

			// ���ò�ѯ����
			TPreTpdu queryModel = new TPreTpdu();
			// ��ID��ѯ
			String tpdu = request.getParameter("_tpdu");
			if (tpdu != null) {
				if (!tpdu.trim().equals("")) {
					queryModel.setTpdu(tpdu.trim());

				}

			}
			// ����������
			String posLinkType = request.getParameter("_posLinkType");
			if (posLinkType != null) {
				if (!posLinkType.trim().equals("")) {
					queryModel.setPosLinkType(posLinkType.trim());
				}

			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllEdcTpdu");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcTpduDispatchAction.queryEdcTpdu()�������ã������ն�TPDU��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcTpduDispatchAction.queryEdcTpdu()�����ն�TPDU��Ϣ�����ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն�TPDU��Ϣ */
	public org.apache.struts.action.ActionForward deleteEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTpduDispatchAction.deleteEdcTpdu()��ʼ���ã�ɾ���ն�TPDU��Ϣ��");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<String> keysList = new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
				logic.deleteItem(keysList, ud);
			}
			log.info("EdcTpduDispatchAction.deleteEdcTpdu()�������ã�ɾ���ն�TPDU��Ϣ��");
		} catch (Exception e) {
			log.info("EdcTpduDispatchAction.deleteEdcTpdu()ɾ���ն�TPDU��Ϣ�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcTpdu");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		// ��ö��ֵ
		SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
		SysParameter sysParameter = new SysParameter();
		SysParameterId id = new SysParameterId();
		id.setParamType("MODULE_ID");
		sysParameter.setId(id);
		List moduleIdList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
		request.setAttribute("moduleIdList", moduleIdList);
		return mapping.findForward("edcTpduSr");
	}

	/**
	 * �����ն�TPDU��Ϣ
	 */
	public org.apache.struts.action.ActionForward createEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTpduDispatchAction.createEdcTpdu()��ʼ����:�����µ��ն�TPDU��Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
			// �õ�Form
			EdcTpduForm edcTpduForm = (EdcTpduForm) form;
			// �½�һ��Model
			TPreTpdu tPreTpdu = new TPreTpdu();
			BeanUtils.copyProperties(tPreTpdu, edcTpduForm);

			// �����µ��ն�TPDU��Ϣ
			logic.createItem(tPreTpdu, ud);

			log.info("EdcTpduDispatchAction.createEdcTpdu()��������:�����µ��ն�TPDU��Ϣ��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcTpduDispatchAction.createEdcTpdu()�����µ��ն�TPDU��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcTpdu");
	}

	/**
	 * ��ʾ�޸��ն�TPDU��Ϣ��Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcTpduByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcTpduDispatchAction.queryEdcTpduByKey()��ʼ���ã���ʾ�޸��ն�TPDU��Ϣ��Ϣ���档");
			// �õ�Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
			// �õ�Form
			EdcTpduForm edcTpduForm = (EdcTpduForm) form;

			UserData ud = getSessionUserData(request);

			map = logic.findItemByKey(edcTpduForm.getTpdu(), ud);

			// �½�һ��Model
			TPreTpdu model = (TPreTpdu) map.get("Infolist");

			BeanUtils.copyProperties(edcTpduForm, model);
			// ��ö��ֵ
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("MODULE_ID");
			sysParameter.setId(id);
			List moduleIdList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("moduleIdList", moduleIdList);
			log.info("EdcTpduDispatchAction.queryEdcTpduByKey()�������ã���ʾ�޸��ն�TPDU��Ϣ��Ϣ���档");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcTpduDispatchAction.queryEdcTpduByKey()��ʾ�޸��ն�TPDU��Ϣ��Ϣ���棬���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("queryEdcTpduByKey");
	}

	/**
	 * �����ն�TPDU��Ϣ
	 */
	public org.apache.struts.action.ActionForward saveEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcTpduDispatchAction.saveEdcTpdu()�����ն�TPDU��Ϣ��Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcTpduLogicInterface logic = (EdcTpduLogicInterface) getBean("edcTpduInfoLogic");
			// �õ�Form
			EdcTpduForm edcTpduForm = (EdcTpduForm) form;
			// �½�һ��Model
			TPreTpdu tPreTpdu = new TPreTpdu();

			tPreTpdu.setModuleId(edcTpduForm.getModuleId());
			tPreTpdu.setPackType(edcTpduForm.getPackType());
			tPreTpdu.setPackTypeno(edcTpduForm.getPackTypeno());
			tPreTpdu.setPosLinkType(edcTpduForm.getPosLinkType());
			tPreTpdu.setChnlno(edcTpduForm.getChnlno());
			tPreTpdu.setTpdu(edcTpduForm.getTpdu());

			// BeanUtils.copyProperties(tPreTpdu,edcTpduForm);

			// �����ն���Ϣ
			logic.saveItem(tPreTpdu, ud);

			log.info("EdcTpduDispatchAction.saveEdcTpdu()�������ã������ն�TPDU��Ϣ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcTpduDispatchAction.saveEdcTpdu()�����ն�TPDU��Ϣ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcTpdu");
	}

}
