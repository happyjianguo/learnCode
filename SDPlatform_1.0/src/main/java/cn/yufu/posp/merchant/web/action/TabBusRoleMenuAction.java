package cn.yufu.posp.merchant.web.action;

import java.io.PrintWriter;
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

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.TabBusRoleMenuLogicInterface;
import cn.yufu.posp.merchant.domain.logic.TabBusinessRatesLogicInterface;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;
import cn.yufu.posp.merchant.web.form.TabBusRoleMenuForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.EdcTerminalLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public class TabBusRoleMenuAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public TabBusRoleMenuAction() {

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
			log.info("TabBusRoleMenuAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");

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
			TabBusRoleMenuModel queryModel = new TabBusRoleMenuModel();
			
			// ���̻���Ų�ѯ
			String busRoleId = request.getParameter("_busRoleId");
			if (busRoleId != null)
				queryModel.setBusRoleId(busRoleId);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TabBusRoleMenuAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.queryAll()���ó����쳣��");
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
			log.info("TabBusRoleMenuAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�ҵ���ɫLogic
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
			// �õ�ҵ�񳡾�����Logic
			TabBusinessRatesLogicInterface tabBusRatesLogic = (TabBusinessRatesLogicInterface) getBean("tabBusinessRatesLogic");
			List<TabBusinessRatesModel> tabBusinessRatesList = tabBusRatesLogic.queryAllTabBusinessRates();
			request.setAttribute("tabBusinessRatesList", tabBusinessRatesList);	

			TabBusRoleMenuForm newForm = (TabBusRoleMenuForm) form;

			if ("".equals(newForm.getBusRoleId()) || newForm.getBusRoleId() == null) {
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getBusRoleId(), ud);

			// �½�һ��Model
			TabBusRoleMenuModel model = (TabBusRoleMenuModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TabBusRoleMenuAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("TabBusRoleMenuAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");

			TabBusRoleMenuForm newForm = (TabBusRoleMenuForm) form;

			TabBusRoleMenuModel model = new TabBusRoleMenuModel();
			// ���û�������
			model.setBusRoleId(newForm.getBusRoleId());
			model.setBusRoleName(newForm.getBusRoleName());
			model.setMenuList(newForm.getMenuList());
			//���ø����ߺ͸�������ʱ��
			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
			model.setUpdateOper(ud.getUserId());
			
			logic.saveItem(model, ud);

			log.info("TabBusRoleMenuAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.saveItem()���ó����쳣��");
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
			log.info("TabBusRoleMenuAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
			// �õ�Form
			TabBusRoleMenuForm newForm = (TabBusRoleMenuForm) form;

			// �½�һ��Model
			TabBusRoleMenuModel model = new TabBusRoleMenuModel();
			// ���û�������
			model.setBusRoleId(newForm.getBusRoleId());
			model.setBusRoleName(newForm.getBusRoleName());
			model.setMenuList(newForm.getMenuList());
			//���ø����ߺ͸�������ʱ��
			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
			model.setUpdateOper(ud.getUserId());
			// ����
			logic.createItem(model, ud);

			log.info("TabBusRoleMenuAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
	
	
	/***
	 * ��֤ҵ���ɫ�������Ƶ�Ψһ��
	 * <p>
	 * ��������򷵻�false
	 * </p>
	 * ����������򷵻�true
	 */
	public org.apache.struts.action.ActionForward findBusRoleNameKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TabBusRoleMenuAction.findBusRoleNameKey()��ʼ���ã���֤ҵ���ɫ�������Ƶ�Ψһ�ԡ�");

			String busRoleName = request.getParameter("busRoleName");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (busRoleName != null && !("".equals(busRoleName))) {
				TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
				TabBusRoleMenuModel tabBusRoleMenuModel = new TabBusRoleMenuModel();
				tabBusRoleMenuModel.setBusRoleName(busRoleName);

				List<TabBusRoleMenuModel> list = logic.findBusRoleNameKey(tabBusRoleMenuModel, null);

				if (list.size() > 0){// ����
					out.print(false);
				}else{
					// ������
					out.print(true);
				}
			} else{
				out.print(true);
			}
			log.info("TabBusRoleMenuAction.findBusRoleNameKey()�������ã���֤ҵ���ɫ�������Ƶ�Ψһ�ԡ�");
		} catch (Exception e) {
			log.info("TabBusRoleMenuAction.findBusRoleNameKey()��֤ҵ���ɫ�������Ƶ�Ψһ�ԣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
		
}
