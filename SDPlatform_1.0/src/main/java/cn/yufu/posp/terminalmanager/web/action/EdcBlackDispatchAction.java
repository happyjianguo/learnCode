package cn.yufu.posp.terminalmanager.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import cn.yufu.posp.terminalmanager.domain.logic.EdcBlackLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;
import cn.yufu.posp.terminalmanager.web.form.EdcBlackForm;

/**
 * @author zhouya 终端资料设定
 * 
 */
public class EdcBlackDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public EdcBlackDispatchAction() {
	}

	/*** 查询终端资料设定 **/
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcBlackDispatchAction.queryAll()开始调用：查找符合条件的记录。");

			// 得到Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");

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
			EdcBlack queryModel = new EdcBlack();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);

				}

			}
			// 按终端编号查询
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}

			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcBlackDispatchAction.queryAll()结束调用：查找符合条件的记录。");
		} catch (Exception e) {
			log.info("EdcBlackDispatchAction.queryAll()查找符合条件的记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除终端基本资料 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcBlack> keysList = new ArrayList<EdcBlack>();
		try {
			log.info("EdcBlackDispatchAction.deleteItem()开始调用：删除记录。");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("终端黑名单：删除记录 条件参数==" + strId);

				EdcBlack edcTerminal = new EdcBlack();
				edcTerminal.setMerchantId(tt[0].trim());
				edcTerminal.setTerminalId(tt[1].trim());

				keysList.add(edcTerminal);
			}
			if (keysList.size() > 0) {
				EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcBlackDispatchAction.deleteItem()结束调用：删除记录。");
		} catch (Exception e) {

			log.info("EdcBlackDispatchAction.deleteItem()删除记录，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcBlackDispatchAction.createItem()开始调用：创建。");

			UserData ud = getSessionUserData(request);
			// 得到Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
			// 得到Form
			EdcBlackForm edcTerminalForm = (EdcBlackForm) form;
			// 新建一个Model
			EdcBlack edcTerminal = new EdcBlack();

			if (ud != null) {
				edcTerminalForm.setUpdateOper(ud.getUserId());
				edcTerminalForm.setUpdateTime(sdf.format(new Date()));
				edcTerminalForm.setVersion(sdf.format(new Date()));
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcBlackDispatchAction.createItem()结束调用：创建。");

		} catch (Exception e) {
			log.info("EdcBlackDispatchAction.createItem()创建，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcBlackDispatchAction.findItem()开始调用：显示修改界面。");
			EdcBlackForm newForm = (EdcBlackForm) form;
			//System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");

			// 得到Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcBlack edcTerminal = new EdcBlack();

			if (tt.length == 16) {
				edcTerminal.setMerchantId(tt[0].trim());
				edcTerminal.setTerminalId(tt[1].trim());
			}

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
			// 得到Form
			EdcBlackForm edcTerminalForm = (EdcBlackForm) form;

			map = logic.findItemByKey(edcTerminal, ud);

			EdcBlack model = (EdcBlack) map.get("Infolist");

			BeanUtils.copyProperties(edcTerminalForm, model);

			log.info("EdcBlackDispatchAction.findItem()结束调用：显示修改界面。");

		} catch (Exception e) {

			log.info("EdcBlackDispatchAction.findItem()显示修改界面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * 修改终端资料设定
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// 新建一个Model
		EdcBlack edcTerminal = new EdcBlack();
		try {
			log.info("EdcBlackDispatchAction.saveItem()开始调用：修改终端资料设定。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
			// 得到Form
			EdcBlackForm edcTerminalForm = (EdcBlackForm) form;

			if (ud != null) {

				edcTerminalForm.setUpdateOper(ud.getUserId());
				edcTerminalForm.setUpdateTime(sdf.format(new Date()));
				edcTerminalForm.setVersion(sdf.format(new Date()));
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);
				edcTerminal.setUpdateOper(ud.getUserId());
				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcBlackDispatchAction.saveItem()结束调用：修改终端资料设定。");
		} catch (Exception e) {
			log.info("EdcBlackDispatchAction.saveItem()修改终端资料设定，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

}
