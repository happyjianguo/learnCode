package cn.yufu.posp.logManager.web.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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
import cn.yufu.posp.logManager.domain.logic.LogLogicInterface;
import cn.yufu.posp.logManager.domain.model.OperateLog;
import cn.yufu.posp.logManager.web.form.OperateLogForm;

public class LogAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public LogAction() {

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
			log.info("LogAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			LogLogicInterface logic = (LogLogicInterface) getBean("LogLogic");

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
			OperateLog queryModel = new OperateLog();
			// 按商户编号查询
			String logType = request.getParameter("logType");
			if (logType != null)
				queryModel.setType(logType);

			// 按商户状态查询
			String logDate = request.getParameter("logDate");
			if (logDate != null)
				queryModel.setCreateDate(logDate);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("LogAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward view(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		HashMap hashMap = new HashMap();
		try {
			log.info("LogAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到Logic
			LogLogicInterface logic = (LogLogicInterface) getBean("LogLogic");

			// 初始化机构
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			OperateLogForm newForm = (OperateLogForm) form;

			hashMap = logic.findItem(newForm.getLogId(), ud);

			// 新建一个Model
			OperateLog model = (OperateLog) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("LogAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("LogAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("view");
	}

}
