package cn.yufu.posp.cardBinArea.web.action;

import java.util.ArrayList;
import java.util.Date;
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

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.cardBinArea.domain.logic.CardBinAreaLogicInterface;
import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.cardBinArea.web.form.CardBinAreaForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;

public class CardBinAreaAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("CardBinArea");

	public CardBinAreaAction() {

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
	public ActionForward queryAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws OAException {
		String pathForward = "";
		try {
			log.error("CardBinAreaAction.queryAll()��ʼ���ã�����"
					+ getSessionUserData(request).getUserId());

			// �õ�Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");

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

			// ���ò�ѯ����
			CardBinArea queryModel = new CardBinArea();
			String search = request.getParameter("queryCardBin");
			if (search != null && !"".equals(search)) {
				queryModel.setCardBin(search);
			}
			String search1 = request.getParameter("queryAreaCode");
			if (search1 != null && !"".equals(search1)) {
				queryModel.setAreaCode(search1);
			}
			String search2 = request.getParameter("queryStatus");
			if (search2 != null && !"".equals(search2)) {
				queryModel.setStatus(search2);
			}
			pageInfo = logic.queryAll(queryModel, pageInfo,
					getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			if(request.getParameter("model")!=null&&"sel".equals(request.getParameter("model"))){
				actionForward = mapping.findForward("sel");			
			}
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "="
					+ pageInfo.getCurrentPage();

			log.info("CardBinAreaAction.queryAll()��ʼ���ã�����"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardBinAreaAction.deleteItem()��ʼ���ã�ɾ��һ����¼��"
					+ getSessionUserData(request).getUserId());
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
				CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("CardBinAreaAction.deleteItem()�������ã�ɾ��һ����¼��"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("CardBinAreaAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��("
					+ e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("CardBinAreaAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���"
					+ getSessionUserData(request).getUserId());
			// �õ�Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");

			CardBinAreaForm newForm = (CardBinAreaForm) form;

			if ("".equals(newForm.getCardBin()) || newForm.getCardBin() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getCardBin(), ud);

			// �½�һ��Model
			CardBinArea model = (CardBinArea) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("CardBinAreaAction.findItem()�������ã���ʾ�޸ĵĽ���"
					+ getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.findItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardBinAreaAction.saveItem()��ʼ����:�޸�һ����¼��"
					+ getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");
			// �õ�Form
			CardBinAreaForm newForm = (CardBinAreaForm) form;
			// �½�һ��Model
			CardBinArea model = new CardBinArea();
			model.setCardBin(newForm.getCardBin());
			model.setAreaCode(newForm.getAreaCode());
			model.setStatus(newForm.getStatus());

			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateOper(ud.getUserId());

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("CardBinAreaAction.saveItem()��������:�޸�һ����¼��"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.saveItem()���ó����쳣��");
			// log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardBinAreaAction.createItem()��ʼ����:����һ����¼��"
					+ getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");
			// �õ�Form
			CardBinAreaForm newForm = (CardBinAreaForm) form;
			// �½�һ��Model
			CardBinArea model = new CardBinArea();
			model.setAreaCode(newForm.getAreaCode());
			model.setCardBin(newForm.getCardBin());
			model.setStatus(newForm.getStatus());

			model.setCreateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setCreateOper(ud.getUserId());

			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateOper(ud.getUserId());
			// �����µİ칫��Ʒ��Ʒ���
			try {
				logic.createItem(model, ud);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException(e.getMessage());
			}

			log.info("CardBinAreaAction.createItem()��������:����һ����¼��"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

}
