package cn.yufu.posp.merchant.web.action;

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

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MccParamLogicInterface;
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.web.form.MccParamForm;

public class MccParamAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MccParamAction() {

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
			log.info("MccParamAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			MccParamLogicInterface logic = (MccParamLogicInterface) getBean("mccLogic");

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
			MccParamModel queryModel = new MccParamModel();
			// ��mcc��Ų�ѯ
			String search = request.getParameter("queryMcc");
			if (search != null)
				queryModel.setQueryMcc(search);

			// ��mccName
			String search1 = request.getParameter("queryMccName");
			if (search1 != null)
				queryModel.setQueryMccName(search1);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("MccParamAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamAction.queryAll()���ó����쳣��");
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
			log.info("MccParamAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				MccParamLogicInterface logic = (MccParamLogicInterface) getBean("mccLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MccParamAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MccParamAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
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
			log.info("MccParamAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			MccParamLogicInterface logic = (MccParamLogicInterface) getBean("mccLogic");

			MccParamForm newForm = (MccParamForm) form;

			if ("".equals(newForm.getMcc()) || newForm.getMcc() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getMcc(), ud);

			// �½�һ��Model
			MccParamModel model = (MccParamModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MccParamAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("MccParamAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MccParamLogicInterface logic = (MccParamLogicInterface) getBean("mccLogic");
			// �õ�Form
			MccParamForm newForm = (MccParamForm) form;
			// �½�һ��Model
			MccParamModel model = new MccParamModel();

			model.setMcc(newForm.getMcc());
			model.setMccName(newForm.getMccName());
			model.setMccEname(newForm.getMccEname());
			model.setMccType(newForm.getMccType());

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("MccParamAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
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
			log.info("MccParamAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MccParamLogicInterface logic = (MccParamLogicInterface) getBean("mccLogic");
			// �õ�Form
			MccParamForm newForm = (MccParamForm) form;
			// �½�һ��Model
			MccParamModel model = new MccParamModel();

			model.setMcc(newForm.getMcc());
			model.setMccName(newForm.getMccName());
			model.setMccEname(newForm.getMccEname());
			model.setMccType(newForm.getMccType());

			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("MccParamAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
}
