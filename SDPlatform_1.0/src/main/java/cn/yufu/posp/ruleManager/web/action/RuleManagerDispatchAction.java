package cn.yufu.posp.ruleManager.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
import cn.yufu.posp.ruleManager.domain.logic.RuleManagerLogicInterface;
import cn.yufu.posp.ruleManager.domain.model.RuleConfig;
import cn.yufu.posp.ruleManager.domain.model.RuleConfigParam;
import cn.yufu.posp.ruleManager.domain.model.RuleTemplate;
import cn.yufu.posp.ruleManager.web.form.RuleConfigForm;

public class RuleManagerDispatchAction extends OABaseDispatchAction {

	private static final Log log = LogFactory.getLog("rule");

	public org.apache.struts.action.ActionForward queryTempAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			log.info("RuleManagerDispatchAction.queryTempAll()开始调用：查找模板");

			// 得到Logic
			RuleManagerLogicInterface ruleManagerLogic = (RuleManagerLogicInterface) getBean("ruleManagerLogic");

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
					pageInfo.setOrderField("ruleTempNo");
				else if (sortField.equals("2"))
					pageInfo.setOrderField("ruleTempDesc");
				else if (sortField.equals("3"))
					pageInfo.setOrderField("status");

			}

			// 设置查询条件
			RuleTemplate queryModel = new RuleTemplate();
			// 按车查询
			String ruleTempNo = request.getParameter("_ruleTempNo");
			if (ruleTempNo != null) {
				if (!ruleTempNo.trim().equals("")) {
					queryModel.setRuleTempNo(ruleTempNo);
				}

			}
			String status = request.getParameter("_status");
			if (status != null) {
				if (!status.trim().equals("")) {
					queryModel.setStatus(status);
				}

			}
			pageInfo = ruleManagerLogic.queryTempAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("queryTempAll");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("RuleManagerDispatchAction.queryTempAll()开始调用：查找模板");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDispatchAction.queryTempAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * 显示界面
	 */
	public org.apache.struts.action.ActionForward findRuleItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			// EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface)
			// getBean("edcCommonInfoLogic");

			// List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(
			// null, null);
			// List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(
			// null, null);
			//
			// request.setAttribute("bankTypeList", banktypeList);
			// request.setAttribute("cardTypeList", cardtypeList);

			log.info("RuleManagerDispatchAction.findRuleItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			RuleManagerLogicInterface ruleManagerLogic = (RuleManagerLogicInterface) getBean("ruleManagerLogic");
			// 准备数据start
			RuleTemplate queryModel = new RuleTemplate();
			// 按车查询
			String ruleTempNo = request.getParameter("_ruleTempNo");
			if (ruleTempNo != null) {
				if (!ruleTempNo.trim().equals("")) {
					queryModel.setRuleTempNo(ruleTempNo);
				}

			}
			UserData ud = getSessionUserData(request);
			List list = ruleManagerLogic.queryTemplateByNo(queryModel, ud);
			// 准备数据end
			if (list.size() > 0) {
				request.setAttribute("ruleTemp", list.get(0));
				return mapping.findForward("addRule");
			} else {
				throw new OAException("RuleManagerDispatchAction.findRuleItem():\n<br> 1、准备模板数据失败\n<br>2、当前模板状态不可新增");
			}

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDispatchAction.findRuleItem()开始调用：显示修改的界面。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		// return new ForwardingActionForward("edit");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerCardAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());
			// 得到Logic
			RuleManagerLogicInterface ruleManagerLogic = (RuleManagerLogicInterface) getBean("ruleManagerLogic");
			UserData ud = getSessionUserData(request);
			RuleConfig ruleConfig = new RuleConfig();
			String configId = request.getParameter("configId");
			ruleConfig.setConfigId(configId);
			String params = request.getParameter("param");
			String status = request.getParameter("status");
			ruleConfig.setStatus(status);
			String ruleTempNo = request.getParameter("ruleTempNo");
			ruleConfig.setRuleTempNo(ruleTempNo);
			String configCycle = request.getParameter("configCycle");
			ruleConfig.setConfigCycle(configCycle);
			String cycleType = request.getParameter("cycleType");
			ruleConfig.setCycleType(cycleType);
			String configTitle = request.getParameter("configTitle");
			ruleConfig.setConfigTitle(configTitle);
			String actionStatus = request.getParameter("actionStatus");
			ruleConfig.setActionStatus(actionStatus);
			String agingMode = request.getParameter("agingMode");
			ruleConfig.setAgingMode(agingMode);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			ruleConfig.setVersion(sdf.format(d));
			String desc = request.getParameter("desc");
			ruleConfig.setConfigDesc(desc);
			String param[] = params.split(";");
			List confParamlist = new ArrayList();
			for (int i = 0; i < param.length; i++) {
				RuleConfigParam configParam = new RuleConfigParam();
				configParam.setConfigParamId(configId + i);
				if (param[i] != null && !"".equals(param[i])) {
					String keys[] = param[i].split("\\|");
					if ("OTHER".equals(keys[1])) {
						String value = request.getParameter(keys[0] + "txt");
						configParam.setOther(value);
					}
					configParam.setRuleTempReg(keys[0]);
					configParam.setValue(keys[1]);
				}
				configParam.setRuleTempNo(ruleTempNo);
				configParam.setConfigId(configId);
				confParamlist.add(configParam);
			}
			// 创建新的办公用品物品类别
			ruleManagerLogic.createItem(ruleConfig, confParamlist, ud);
			log.info("MerCardAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerCardAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	public org.apache.struts.action.ActionForward queryRuleList(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			log.info("RuleManagerDispatchAction.queryRuleList()开始调用：查找规则");

			// 得到Logic
			RuleManagerLogicInterface ruleManagerLogic = (RuleManagerLogicInterface) getBean("ruleManagerLogic");

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
					pageInfo.setOrderField("ruleTempNo");
				else if (sortField.equals("2"))
					pageInfo.setOrderField("ruleTempDesc");
				else if (sortField.equals("3"))
					pageInfo.setOrderField("status");

			}

			// 设置查询条件
			RuleConfig queryModel = new RuleConfig();
			// 按车查询
			String ruleTempNo = request.getParameter("_ruleTempNo");
			if (ruleTempNo != null) {
				if (!ruleTempNo.trim().equals("")) {
					queryModel.setRuleTempNo(ruleTempNo);
				}

			}
			String configId = request.getParameter("_configId");
			if (configId != null) {
				if (!configId.trim().equals("")) {
					queryModel.setConfigId(configId);
				}

			}
			String status = request.getParameter("_status");
			if (status != null) {
				if (!status.trim().equals("")) {
					queryModel.setStatus(status);
				}

			}
			pageInfo = ruleManagerLogic.queryRuleList(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("queryRuleAll");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("RuleManagerDispatchAction.queryRuleList()开始调用：查找规则");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("RuleManagerDispatchAction.queryRuleList()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteRule(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("RuleManagerDispatchAction.deleteRule()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				RuleManagerLogicInterface ruleManagerLogic = (RuleManagerLogicInterface) getBean("ruleManagerLogic");

				ruleManagerLogic.deleteRule(keysList, ud);
			}
			log.info("RuleManagerDispatchAction.deleteRule()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("RuleManagerDispatchAction.deleteRule()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 查看一条记录
	 */
	public org.apache.struts.action.ActionForward queryRuleOne(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("RuleManagerDispatchAction.queryRuleOne()开始调用：查一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);

			String configId = request.getParameter("_configId");
			// 得到Logic
			RuleManagerLogicInterface ruleManagerLogic = (RuleManagerLogicInterface) getBean("ruleManagerLogic");

			List list = ruleManagerLogic.queryRuleOne(configId, ud);

			RuleTemplate queryModel = new RuleTemplate();
			queryModel.setRuleTempNo(((RuleConfigForm) list.get(0)).getRuleTempNo());
			List tempList = ruleManagerLogic.queryTemplateByNo(queryModel, ud);
			// 准备数据end
			if (list.size() > 0) {
				request.setAttribute("rule", list.get(0));
				request.setAttribute("ruleTemp", tempList.get(0));
			} else {
				throw new OAException("RuleManagerDispatchAction.findRuleItem():准备数据失败");
			}
			log.info("RuleManagerDispatchAction.queryRuleOne()结束调用：查一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("RuleManagerDispatchAction.queryRuleOne()结束调用：查一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("queryOne");
	}
}
