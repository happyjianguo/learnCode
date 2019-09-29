package cn.yufu.posp.client.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.client.domain.logic.OaClientComputerInfoLogicInterface;
import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.client.web.form.OaClientComputerInfoForm;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

/**
 * @author ʯ�� �ͻ��˹���
 * 
 */
public class OaClientComputerInfoAction extends OABaseDispatchAction {

	public OaClientComputerInfoAction() {

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
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("OaClientComputerInfoAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");

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
			OaClientComputerInfoModel queryModel = new OaClientComputerInfoModel();
			// ��MAC��ַ��ѯ
			String search = request.getParameter("mac_addr");
			if (search != null)
				queryModel.setMacAddr(search);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("OaClientComputerInfoAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("OaClientComputerInfoAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				OaClientComputerInfoLogicInterface OaClientComputerInfoLogic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");

				OaClientComputerInfoLogic.deleteItem(keysList, ud);
			}
			log.info("OaClientComputerInfoAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("OaClientComputerInfoAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("OaClientComputerInfoAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");
			// �õ�Form
			OaClientComputerInfoForm newForm = (OaClientComputerInfoForm) form;
			// �½�һ��Model
			OaClientComputerInfoModel model = new OaClientComputerInfoModel();

			model.setMacAddr(newForm.getMacAddr());
			model.setIpAddr(newForm.getIpAddr());
			model.setHostName(newForm.getHostName());
			model.setUserId(newForm.getUserId());
			model.setCreateUser(ud.getUserId());
			model.setCreateTimestamp(new Date());
			model.setClientId(ud.getClientId());

			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("OaClientComputerInfoAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_Client_ADD);
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
			log.info("OaClientComputerInfoAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");

			OaClientComputerInfoForm newForm = (OaClientComputerInfoForm) form;

			if ("".equals(newForm.getMacAddr()) || newForm.getMacAddr() == null) {
				newForm.setUserId(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getMacAddr(), ud);

			// �½�һ��Model
			OaClientComputerInfoModel model = (OaClientComputerInfoModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("OaClientComputerInfoAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("OaClientComputerInfoAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			OaClientComputerInfoLogicInterface logic = (OaClientComputerInfoLogicInterface) getBean("OaClientComputerInfoLogic");
			// �õ�Form
			OaClientComputerInfoForm newForm = (OaClientComputerInfoForm) form;
			// �½�һ��Model
			OaClientComputerInfoModel model = new OaClientComputerInfoModel();

			model.setMacAddr(newForm.getMacAddr());
			model.setIpAddr(newForm.getIpAddr());
			model.setHostName(newForm.getHostName());
			model.setUserId(newForm.getUserId());
			model.setCreateUser(newForm.getCreateUser());

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setCreateTimestamp(sdf.parse(newForm.getCreateTimestamp()));

			model.setClientId(newForm.getClientId());
			model.setUpdateUser(ud.getUserId());
			model.setUpdateTimestamp(new Date());

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("OaClientComputerInfoAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

}
