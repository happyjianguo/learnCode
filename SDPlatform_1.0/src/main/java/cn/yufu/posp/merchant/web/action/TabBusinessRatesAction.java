package cn.yufu.posp.merchant.web.action;

import java.sql.Time;
import java.text.SimpleDateFormat;
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

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.TabBusinessRatesLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;
import cn.yufu.posp.merchant.web.form.TabBusinessRatesForm;

public class TabBusinessRatesAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public TabBusinessRatesAction() {

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
			log.info("TabBusinessRatesAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			TabBusinessRatesLogicInterface logic = (TabBusinessRatesLogicInterface) getBean("tabBusinessRatesLogic");

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
			TabBusinessRatesModel queryModel = new TabBusinessRatesModel();
			
			// ���̻���Ų�ѯ
			String businessid = request.getParameter("_businessid");
			if (businessid != null)
				queryModel.setBusinessid(businessid);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TabBusinessRatesAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesAction.queryAll()���ó����쳣��");
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
			log.info("TabBusinessRatesAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			TabBusinessRatesLogicInterface logic = (TabBusinessRatesLogicInterface) getBean("tabBusinessRatesLogic");


			TabBusinessRatesForm newForm = (TabBusinessRatesForm) form;

			if ("".equals(newForm.getBusinessid()) || newForm.getBusinessid() == null) {
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getBusinessid(), ud);

			// �½�һ��Model
			TabBusinessRatesModel model = (TabBusinessRatesModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TabBusinessRatesAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("TabBusinessRatesAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TabBusinessRatesLogicInterface logic = (TabBusinessRatesLogicInterface) getBean("tabBusinessRatesLogic");
			// �½�һ��Form
			TabBusinessRatesForm newForm = (TabBusinessRatesForm) form;
			// �½�һ��Model
			TabBusinessRatesModel model = new TabBusinessRatesModel();
			// ���û�������
			model.setBusinessid(newForm.getBusinessid());
			model.setBusinessname(newForm.getBusinessname());
			model.setRate(newForm.getRate());
			model.setBusinessNameDetail(newForm.getBusinessNameDetail());
			//���ø����ߺ͸�������ʱ��
			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
			model.setUpdateOper(ud.getUserId());	
			
			logic.saveItem(model, ud);

			log.info("TabBusinessRatesAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesAction.saveItem()���ó����쳣��");
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
			log.info("TabBusinessRatesAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			TabBusinessRatesLogicInterface logic = (TabBusinessRatesLogicInterface) getBean("tabBusinessRatesLogic");
			// �õ�Form
			TabBusinessRatesForm newForm = (TabBusinessRatesForm) form;

			// �½�һ��Model
			TabBusinessRatesModel model = new TabBusinessRatesModel();
			// ���û�������
			model.setBusinessid(newForm.getBusinessid());
			model.setBusinessname(newForm.getBusinessname());
			model.setRate(newForm.getRate());
			model.setBusinessNameDetail(newForm.getBusinessNameDetail());
			//���ø����ߺ͸�������ʱ��

			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
			model.setUpdateOper(ud.getUserId());
			
			
			// ����
			logic.createItem(model, ud);

			log.info("TabBusinessRatesAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
}
