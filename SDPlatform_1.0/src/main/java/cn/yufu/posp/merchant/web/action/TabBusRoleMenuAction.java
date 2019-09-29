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
	 * 查找
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
			log.info("TabBusRoleMenuAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);
			// if (sortField != null)
			// {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("sort");
			// if (sortField.equals("2"))
			// pageInfo.setOrderField("memo");
			//				
			// }

			// 设置查询条件
			TabBusRoleMenuModel queryModel = new TabBusRoleMenuModel();
			
			// 按商户编号查询
			String busRoleId = request.getParameter("_busRoleId");
			if (busRoleId != null)
				queryModel.setBusRoleId(busRoleId);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TabBusRoleMenuAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		HashMap hashMap = new HashMap();
		try {
			log.info("TabBusRoleMenuAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到业务角色Logic
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
			// 得到业务场景费率Logic
			TabBusinessRatesLogicInterface tabBusRatesLogic = (TabBusinessRatesLogicInterface) getBean("tabBusinessRatesLogic");
			List<TabBusinessRatesModel> tabBusinessRatesList = tabBusRatesLogic.queryAllTabBusinessRates();
			request.setAttribute("tabBusinessRatesList", tabBusinessRatesList);	

			TabBusRoleMenuForm newForm = (TabBusRoleMenuForm) form;

			if ("".equals(newForm.getBusRoleId()) || newForm.getBusRoleId() == null) {
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getBusRoleId(), ud);

			// 新建一个Model
			TabBusRoleMenuModel model = (TabBusRoleMenuModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TabBusRoleMenuAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TabBusRoleMenuAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");

			TabBusRoleMenuForm newForm = (TabBusRoleMenuForm) form;

			TabBusRoleMenuModel model = new TabBusRoleMenuModel();
			// 设置基本资料
			model.setBusRoleId(newForm.getBusRoleId());
			model.setBusRoleName(newForm.getBusRoleName());
			model.setMenuList(newForm.getMenuList());
			//设置更新者和更新日期时间
			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
			model.setUpdateOper(ud.getUserId());
			
			logic.saveItem(model, ud);

			log.info("TabBusRoleMenuAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TabBusRoleMenuAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
			// 得到Form
			TabBusRoleMenuForm newForm = (TabBusRoleMenuForm) form;

			// 新建一个Model
			TabBusRoleMenuModel model = new TabBusRoleMenuModel();
			// 设置基本资料
			model.setBusRoleId(newForm.getBusRoleId());
			model.setBusRoleName(newForm.getBusRoleName());
			model.setMenuList(newForm.getMenuList());
			//设置更新者和更新日期时间
			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
			model.setUpdateOper(ud.getUserId());
			// 创建
			logic.createItem(model, ud);

			log.info("TabBusRoleMenuAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
	
	
	/***
	 * 验证业务角色中文名称的唯一性
	 * <p>
	 * 如果存在则返回false
	 * </p>
	 * 如果不存在则返回true
	 */
	public org.apache.struts.action.ActionForward findBusRoleNameKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TabBusRoleMenuAction.findBusRoleNameKey()开始调用：验证业务角色中文名称的唯一性。");

			String busRoleName = request.getParameter("busRoleName");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (busRoleName != null && !("".equals(busRoleName))) {
				TabBusRoleMenuLogicInterface logic = (TabBusRoleMenuLogicInterface) getBean("tabBusRoleMenuLogic");
				TabBusRoleMenuModel tabBusRoleMenuModel = new TabBusRoleMenuModel();
				tabBusRoleMenuModel.setBusRoleName(busRoleName);

				List<TabBusRoleMenuModel> list = logic.findBusRoleNameKey(tabBusRoleMenuModel, null);

				if (list.size() > 0){// 存在
					out.print(false);
				}else{
					// 不存在
					out.print(true);
				}
			} else{
				out.print(true);
			}
			log.info("TabBusRoleMenuAction.findBusRoleNameKey()结束调用：验证业务角色中文名称的唯一性。");
		} catch (Exception e) {
			log.info("TabBusRoleMenuAction.findBusRoleNameKey()验证业务角色中文名称的唯一性，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
		
}
