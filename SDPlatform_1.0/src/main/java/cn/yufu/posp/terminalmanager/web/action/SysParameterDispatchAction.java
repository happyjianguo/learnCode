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
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.web.form.SysParameterForm;

/**
 * @author zhouya 其他参数管理
 * 
 */
public class SysParameterDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("sysParameter");

	public SysParameterDispatchAction() {
	}

	/*** 查询其他参数管理信息 **/
	public org.apache.struts.action.ActionForward querySysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("SysParameterDispatchAction.querySysParameter()开始调用:查找其他参数管理。");

			// 得到Logic
			SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");

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
					pageInfo.setOrderField("id.paramType");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.paramName");
				if (sortField.equals("3"))
					pageInfo.setOrderField("paramChinese");
				if (sortField.equals("4"))
					pageInfo.setOrderField("paramValue");
				if (sortField.equals("5"))
					pageInfo.setOrderField("paramNotes");
				if (sortField.equals("6"))
					pageInfo.setOrderField("enable");
			}

			// 设置查询条件
			SysParameter queryModel = new SysParameter();
			SysParameterId id = new SysParameterId();

			// 参数名称查询
			String paramName = request.getParameter("_paramName");
			if (paramName != null) {
				if (!paramName.trim().equals("")) {
					id.setParamName(paramName);
				}
			}
			// 参数名称类型
			String paramType = request.getParameter("_paramType");
			if (paramType != null) {
				if (!paramType.trim().equals("")) {
					id.setParamType(paramType);
				}
			}
			// 按启用标志查询
			String enable = request.getParameter("_enable");
			if (enable != null) {
				if (!enable.trim().equals("")) {
					queryModel.setEnable(enable);
				}
			}
			queryModel.setId(id);
			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllSysParameter");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("SysParameterDispatchAction.querySysParameter()结束调用:查找其他参数管理。");
		} catch (Exception e) {
			log.info("SysParameterDispatchAction.querySysParameter()查找其他参数管理，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除其他参数管理 */
	public org.apache.struts.action.ActionForward deleteSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysParameterDispatchAction.deleteSysParameter()开始调用:删除其他参数管理。");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<SysParameter> keysList = new ArrayList<SysParameter>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {

				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("删除记录条件参数==" + strId + "。");

				if (tt.length == 3) {
					SysParameter model = new SysParameter();
					SysParameterId id = new SysParameterId();
					id.setParamType(tt[0].trim());
					id.setParamName(tt[1].trim());

					model.setId(id);
					keysList.add(model);
				}
			}
			if (keysList.size() > 0) {
				SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
				logic.deleteItem(keysList, ud);
			}
			log.info("SysParameterDispatchAction.deleteSysParameter()结束调用:删除其他参数管理。");
		} catch (Exception e) {

			log.info("SysParameterDispatchAction.deleteSysParameter()删除其他参数管理，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteSysParameter");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("sysParameterSr");
	}

	/**
	 * 创建其他参数管理
	 */
	public org.apache.struts.action.ActionForward createSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysParameterDispatchAction.createSysParameter()开始调用:创建其他参数管理。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			// 得到Form
			SysParameterForm sysParameterForm = (SysParameterForm) form;
			// 新建一个Model
			SysParameter sysParameter = new SysParameter();
			BeanUtils.copyProperties(sysParameter, sysParameterForm);

			// 创建新的其他参数管理
			logic.createItem(sysParameter, ud);

			log.info("SysParameterDispatchAction.createSysParameter()结束调用:创建其他参数管理。");

		} catch (Exception e) {
			log.info("SysParameterDispatchAction.createSysParameter()创建其他参数管理，出现异常。。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createSysParameter");
	}

	/**
	 * 显示修改其他参数管理信息界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward querySysParameterByKey(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		SysParameter queryModel = new SysParameter();
		SysParameterId id = new SysParameterId();
		try {
			log.info("SysParameterDispatchAction.querySysParameterByKey()开始调用：显示修改其他参数管理信息界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");

			if (tt.length == 3) {
				id.setParamType(tt[0].trim());
				id.setParamName(tt[1].trim());
				queryModel.setId(id);

				UserData ud = getSessionUserData(request);
				// 得到Logic
				SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
				// 得到Form
				SysParameterForm sysParameterForm = (SysParameterForm) form;

				map = logic.findItemByKey(queryModel, ud);
				// 新建一个Model
				SysParameter model = (SysParameter) map.get("Infolist");

				BeanUtils.copyProperties(sysParameterForm, model);

			}

			log.info("SysParameterDispatchAction.querySysParameterByKey()结束调用：显示修改其他参数管理信息界面。");

		} catch (Exception e) {
			log.info("SysParameterDispatchAction.querySysParameterByKey()显示修改其他参数管理信息界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("querySysParameterByKey");
	}

	/**
	 * 保存其他参数管理
	 */
	public org.apache.struts.action.ActionForward saveSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("SysParameterDispatchAction.saveSysParametery()开始调用：修改其他参数管理。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			SysParameterLogicInterface logic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			// 得到Form
			SysParameterForm sysParameterForm = (SysParameterForm) form;
			// 新建一个Model
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamName(sysParameterForm.getId().getParamName());
			id.setParamType(sysParameterForm.getId().getParamType());

			sysParameter.setId(id);
			sysParameter.setEnable(sysParameterForm.getEnable());
			sysParameter.setParamChinese(sysParameterForm.getParamChinese());
			sysParameter.setParamNotes(sysParameterForm.getParamNotes());
			sysParameter.setParamValue(sysParameterForm.getParamValue());

			// 创建终端信息
			logic.saveItem(sysParameter, ud);

			log.info("SysParameterDispatchAction.saveSysParametery()结束调用：修改其他参数管理。");
		} catch (Exception e) {
			log.info("SysParameterDispatchAction.saveSysParametery()修改其他参数管理，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveSysParameter");
	}

}
