package cn.yufu.posp.sysparam.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.sysparam.domain.logic.TranModuleLogicInterface;
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;
import cn.yufu.posp.sysparam.web.form.TranModuleInfForm;

public class TranModuleDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("sysparam");

	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("TranModuleDispatchAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");

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
			// if (sortField != null)
			// {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("sort");
			// if (sortField.equals("2"))
			// pageInfo.setOrderField("memo");
			//				
			// }

			// ���ò�ѯ����
			TranModuleInf queryModel = new TranModuleInf();
			// ��_moduleId��ѯ
			String _moduleId = request.getParameter("_moduleId");
			if (_moduleId != null && !"".equals(_moduleId))
				queryModel.setModuleId(_moduleId);

			// ��_flag��ѯ
			String _flag = request.getParameter("_flag");
			if (_flag != null && !"".equals(_flag))
				queryModel.setFlag(_flag);
			
			// ��_tranType��ѯ
			String _tranType = request.getParameter("_tranType");
			if (_tranType != null && !"".equals(_tranType))
				queryModel.setTranType(_tranType);
			
			// ��_tranName��ѯ
			String _tranName = request.getParameter("_tranName");
			if (_tranName != null && !"".equals(_tranName))
				queryModel.setTranName(_tranName);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TranModuleDispatchAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.queryAll()���ó����쳣��");
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
			log.info("TranModuleDispatchAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				// �ֽ����
				String[] tt = InfoIdStrs[i].split("#");
				if (tt.length == 5) {
					TranModuleInf id = new TranModuleInf();
					id.setModuleId(tt[0].trim());
					id.setTranType(tt[1].trim());
					id.setVoidTranType(tt[2].trim());
					id.setVoidOldTranType(tt[3].trim());
					keysList.add(id);
				}
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				TranModuleLogicInterface tranModuleLogicInterface = (TranModuleLogicInterface) getBean("TranModuleLogic");

				tranModuleLogicInterface.deleteItem(keysList, ud);
			}
			log.info("TranModuleDispatchAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("TranModuleDispatchAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
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
			log.info("TranModuleDispatchAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");
			// �õ�Form
			TranModuleInfForm newForm = (TranModuleInfForm) form;
			// �½�һ��Model
			TranModuleInf model = new TranModuleInf();

			BeanUtils.copyProperties(model, newForm);
			
			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("TranModuleDispatchAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
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
			log.info("TranModuleDispatchAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");

			TranModuleInfForm newForm = (TranModuleInfForm) form;

			TranModuleInf id = new TranModuleInf();
			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// �ֽ����
			String[] tt = strId.split("#");

			if (tt.length == 5) {
				id.setModuleId(tt[0].trim());
				id.setTranType(tt[1].trim());
				id.setVoidTranType(tt[2].trim());
				id.setVoidOldTranType(tt[3].trim());
				
				UserData ud = getSessionUserData(request);
				
				hashMap = logic.findItem(id, ud);

				// �½�һ��Model
				TranModuleInf model = (TranModuleInf) hashMap.get("Infolist");
				BeanUtils.copyProperties(newForm, model);
			}

			log.info("TranModuleDispatchAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.findItem()��ʾ�޸Ľ���,���ó����쳣��");
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
			log.info("TranModuleDispatchAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");
			// �õ�Form
			TranModuleInfForm newForm = (TranModuleInfForm) form;
			// �½�һ��Model
			TranModuleInf model = new TranModuleInf();

			BeanUtils.copyProperties(model, newForm);
			
			logic.saveItem(model, ud);

			log.info("TranModuleDispatchAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}
}
