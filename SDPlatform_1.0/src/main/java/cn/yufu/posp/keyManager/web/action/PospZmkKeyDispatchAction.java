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

	/*** 查询 **/
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("PospZmkKeyDispatchAction.queryAll()开始调用：查找符合条件的记录。");

			// 得到Logic
			PospZmkKeyLogicInterface logic = (PospZmkKeyLogicInterface) getBean("PospZmkKeyLogic");

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
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// 设置查询条件
			PospZmkKey queryModel = new PospZmkKey();
			// 按商户编号查询
			String moduleId = request.getParameter("_moduleId");
			if (moduleId != null) {
				if (!moduleId.trim().equals("")) {
					queryModel.setModuleId(moduleId);
				}
			}
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("PospZmkKeyDispatchAction.queryAll()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("PospZmkKeyDispatchAction.queryAll()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * 显示修改界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("PospZmkKeyDispatchAction.findItem()开始调用：显示修改界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// 分解参数
			String[] tt = strId.split("#");

			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			PospZmkKey btsKey = new PospZmkKey();

			UserData ud = getSessionUserData(request);

			// 得到Logic
			PospZmkKeyLogicInterface logic = (PospZmkKeyLogicInterface) getBean("PospZmkKeyLogic");
			// 得到Form
			PospZmkKeyForm btsKeyForm = (PospZmkKeyForm) form;

			map = logic.findItemByKey(btsKey, ud);

			BtsKey model = (BtsKey) map.get("Infolist");

			BeanUtils.copyProperties(btsKeyForm, model);

			log.info("PospZmkKeyDispatchAction.findItem()结束调用：显示修改界面。");

		} catch (Exception e) {

			log.info("PospZmkKeyDispatchAction.findItem()显示修改界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("PospZmkKeyDispatchAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			PospZmkKeyLogicInterface logic = (PospZmkKeyLogicInterface) getBean("PospZmkKeyLogic");
			// 得到Form
			PospZmkKeyForm newForm = (PospZmkKeyForm) form;
			// 新建一个Model
			PospZmkKey model = new PospZmkKey();
			BeanUtils.copyProperties(model, newForm);
			

			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("PospZmkKeyDispatchAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PospZmkKeyDispatchAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
}
