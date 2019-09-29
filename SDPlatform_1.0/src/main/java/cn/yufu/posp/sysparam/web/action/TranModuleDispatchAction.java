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
	 * 查找
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
			log.info("TranModuleDispatchAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");

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
			TranModuleInf queryModel = new TranModuleInf();
			// 按_moduleId查询
			String _moduleId = request.getParameter("_moduleId");
			if (_moduleId != null && !"".equals(_moduleId))
				queryModel.setModuleId(_moduleId);

			// 按_flag查询
			String _flag = request.getParameter("_flag");
			if (_flag != null && !"".equals(_flag))
				queryModel.setFlag(_flag);
			
			// 按_tranType查询
			String _tranType = request.getParameter("_tranType");
			if (_tranType != null && !"".equals(_tranType))
				queryModel.setTranType(_tranType);
			
			// 按_tranName查询
			String _tranName = request.getParameter("_tranName");
			if (_tranName != null && !"".equals(_tranName))
				queryModel.setTranName(_tranName);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TranModuleDispatchAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TranModuleDispatchAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				// 分解参数
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
				// 得到Logic
				TranModuleLogicInterface tranModuleLogicInterface = (TranModuleLogicInterface) getBean("TranModuleLogic");

				tranModuleLogicInterface.deleteItem(keysList, ud);
			}
			log.info("TranModuleDispatchAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("TranModuleDispatchAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
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
			log.info("TranModuleDispatchAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");
			// 得到Form
			TranModuleInfForm newForm = (TranModuleInfForm) form;
			// 新建一个Model
			TranModuleInf model = new TranModuleInf();

			BeanUtils.copyProperties(model, newForm);
			
			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("TranModuleDispatchAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("TranModuleDispatchAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");

			TranModuleInfForm newForm = (TranModuleInfForm) form;

			TranModuleInf id = new TranModuleInf();
			// 得到拼接参数
			String strId = request.getParameter("_id");
			if ("".equals(strId) || strId == null) {
				return mapping.findForward("add");
			}
			// 分解参数
			String[] tt = strId.split("#");

			if (tt.length == 5) {
				id.setModuleId(tt[0].trim());
				id.setTranType(tt[1].trim());
				id.setVoidTranType(tt[2].trim());
				id.setVoidOldTranType(tt[3].trim());
				
				UserData ud = getSessionUserData(request);
				
				hashMap = logic.findItem(id, ud);

				// 新建一个Model
				TranModuleInf model = (TranModuleInf) hashMap.get("Infolist");
				BeanUtils.copyProperties(newForm, model);
			}

			log.info("TranModuleDispatchAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.findItem()显示修改界面,调用出现异常。");
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
			log.info("TranModuleDispatchAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TranModuleLogicInterface logic = (TranModuleLogicInterface) getBean("TranModuleLogic");
			// 得到Form
			TranModuleInfForm newForm = (TranModuleInfForm) form;
			// 新建一个Model
			TranModuleInf model = new TranModuleInf();

			BeanUtils.copyProperties(model, newForm);
			
			logic.saveItem(model, ud);

			log.info("TranModuleDispatchAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDispatchAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}
}
