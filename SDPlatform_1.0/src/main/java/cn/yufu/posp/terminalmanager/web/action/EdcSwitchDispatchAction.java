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
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.EdcSwitchLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitchId;
import cn.yufu.posp.terminalmanager.web.form.EdcSwitchForm;

/**
 * @author zhouya 终端转换信息
 * 
 */
public class EdcSwitchDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcSwitch");

	public EdcSwitchDispatchAction() {
	}

	/*** 查询终端基本资料信息 **/
	public org.apache.struts.action.ActionForward queryEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcSwitchDispatchAction.queryEdcSwitch()开始调用：查找终端TPDU信息。");

			// 得到Logic
			EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");

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
					pageInfo.setOrderField("id.merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("id.terminalId");
				if (sortField.equals("3"))
					pageInfo.setOrderField("id.bankType");
				if (sortField.equals("4"))
					pageInfo.setOrderField("othTerminalId");
			}

			// 设置查询条件
			EdcSwitch queryModel = new EdcSwitch();
			EdcSwitchId id = new EdcSwitchId();
			// 按商户编号查询
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					id.setMerchantId(merchantId);
				}

			}
			// 按银行类型查询
			String bankType = request.getParameter("_bankType");
			if (bankType != null) {
				if (!bankType.trim().equals("")) {
					id.setBankType(bankType);
				}

			}

			queryModel.setId(id);

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> list = commonLogic.queryAllBanktypeItem(null, null);

			request.setAttribute("bankTypeList", list);

			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllEdcSwitch");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcSwitchDispatchAction.queryEdcSwitch()结束调用：查找终端TPDU信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcSwitchDispatchAction.queryEdcSwitch()查找终端TPDU信息，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除终端基本资料 */
	public org.apache.struts.action.ActionForward deleteEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcSwitch> keysList = new ArrayList<EdcSwitch>();
		try {
			log.info("EdcSwitchDispatchAction.deleteEdcSwitch()开始调用：删除终端TPDU信息。");

			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			UserData ud = getSessionUserData(request);

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("终端转换 删除记录 条件参数==" + strId);

				if (tt.length == 5) {
					EdcSwitch edcSwitch = new EdcSwitch();
					EdcSwitchId id = new EdcSwitchId();
					id.setMerchantId(tt[0].trim());
					id.setBankType(tt[1].trim());
					id.setTerminalId(tt[2].trim());
					edcSwitch.setOthTerminalId(tt[3].trim());
					edcSwitch.setId(id);
					keysList.add(edcSwitch);
				}
			}
			if (keysList.size() > 0) {
				// 得到Logic
				EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcSwitchDispatchAction.deleteEdcSwitch()结束调用：删除终端TPDU信息。");
		} catch (Exception e) {
			log.info("EdcSwitchDispatchAction.deleteEdcSwitch()删除终端TPDU信息，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcSwitch");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcSwitchDispatchAction.showSr()开始调用：跳转到输入页面。");

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> list = commonLogic.queryAllBanktypeItem(null, null);

			request.setAttribute("bankTypeList", list);

			log.info("EdcSwitchDispatchAction.showSr()结束调用：跳转到输入页面。");
		} catch (Exception e) {
			log.info("EdcSwitchDispatchAction.showSr()跳转到输入页面，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edcSwitchSr");
	}

	/**
	 * 创建一条信息
	 */
	public org.apache.struts.action.ActionForward createEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcSwitchDispatchAction.createEdcSwitch()开始调用:创建新的终端TPDU信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
			// 得到Form
			EdcSwitchForm edcSwitchForm = (EdcSwitchForm) form;
			// 新建一个Model
			EdcSwitch edcSwitch = new EdcSwitch();
			BeanUtils.copyProperties(edcSwitch, edcSwitchForm);

			logic.createItem(edcSwitch, ud);

			log.info("EdcSwitchDispatchAction.createEdcSwitch()结束调用:创建新的终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDispatchAction.createEdcSwitch()创建新的终端TPDU信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcSwitch");
	}

	/**
	 * 显示修改终端转换信息信息界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcSwitchByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		EdcSwitch edcSwitch = new EdcSwitch();
		EdcSwitchId id = new EdcSwitchId();
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcSwitchDispatchAction.queryEdcSwitchByKey()开始调用：显示修改终端TPDU信息信息界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");

			if (tt.length == 5) {
				id.setMerchantId(tt[0].trim());
				id.setBankType(tt[1].trim());
				id.setTerminalId(tt[2].trim());
				edcSwitch.setOthTerminalId(tt[3].trim());
				;
				edcSwitch.setId(id);

				UserData ud = getSessionUserData(request);
				// 得到Logic
				EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
				map = logic.findItemByKey(edcSwitch, ud);
				// 得到Form
				EdcSwitchForm edcSwitchForm = (EdcSwitchForm) form;
				map = logic.findItemByKey(edcSwitch, ud);
				EdcSwitch model = (EdcSwitch) map.get("Infolist");
				BeanUtils.copyProperties(edcSwitchForm, model);

			}

			log.info("EdcSwitchDispatchAction.queryEdcSwitchByKey()结束调用：显示修改终端TPDU信息信息界面。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcSwitchDispatchAction.queryEdcSwitchByKey()显示修改终端TPDU信息信息界面，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("queryEdcSwitchByKey");
	}

	/**
	 * 保存终端转换信息
	 */
	public org.apache.struts.action.ActionForward saveEdcSwitch(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcSwitchDispatchAction.saveEdcSwitch()开始调用：创建终端TPDU信息信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcSwitchLogicInterface logic = (EdcSwitchLogicInterface) getBean("edcSwitchInfoLogic");
			// 得到Form
			EdcSwitchForm edcSwitchForm = (EdcSwitchForm) form;
			// 新建一个Model
			EdcSwitch edcSwitch = new EdcSwitch();

			BeanUtils.copyProperties(edcSwitch, edcSwitchForm);

			// 创建终端信息
			logic.saveItem(edcSwitch, ud);

			log.info("=EdcSwitchDispatchAction.saveEdcSwitch()结束调用：创建终端TPDU信息信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("=EdcSwitchDispatchAction.saveEdcSwitch()创建终端TPDU信息信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcSwitch");
	}

}
