package cn.yufu.posp.terminalmanager.web.action;

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
import cn.yufu.posp.terminalmanager.domain.logic.EdcCardLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;
import cn.yufu.posp.terminalmanager.domain.model.EdcCardId;
import cn.yufu.posp.terminalmanager.web.form.EdcCardForm;

/**
 * @author zhouya 终端卡类信息
 * 
 */
public class EdcCardDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcCard");

	public EdcCardDispatchAction() {
	}

	/*** 查询信息 **/
	public org.apache.struts.action.ActionForward queryEdcCard(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcCardDispatchAction.queryEdcCard()开始调用:查找终端TPDU信息。");

			// 得到Logic
			EdcCardLogicInterface logic = (EdcCardLogicInterface) getBean("edcCardInfoLogic");

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
					pageInfo.setOrderField("id.cardType");
				if (sortField.equals("4"))
					pageInfo.setOrderField("cardStat");
				if (sortField.equals("5"))
					pageInfo.setOrderField("id.bankType");
				if (sortField.equals("6"))
					pageInfo.setOrderField("updateOper");
				if (sortField.equals("7"))
					pageInfo.setOrderField("updateDate");
			}

			// 设置查询条件
			EdcCard queryModel = new EdcCard();
			EdcCardId id = new EdcCardId();
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

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

			request.setAttribute("bankTypeList", banktypeList);
			request.setAttribute("cardTypeList", cardtypeList);
			// 取得路径
			ActionForward actionForward = mapping.findForward("showAllEdcCard");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcCardDispatchAction.queryEdcCard()结束调用:查找终端TPDU信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardDispatchAction.queryEdcCard()查找终端TPDU信息，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** 删除一条信息 */
	public org.apache.struts.action.ActionForward deleteEdcCard(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcCard> keysList = new ArrayList<EdcCard>();
		try {
			log.info("EdcCardDispatchAction.deleteEdcCard()开始调用：删除终端TPDU信息。");

			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			UserData ud = getSessionUserData(request);

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("终端卡类：删除记录 条件参数==" + strId);

				if (tt.length == 9) {
					EdcCard edcCard = new EdcCard();
					EdcCardId id = new EdcCardId();
					id.setMerchantId(tt[0].trim());
					id.setBankType(tt[1].trim());
					id.setTerminalId(tt[2].trim());
					edcCard.setCardStat(tt[3].trim());
					id.setCardType(tt[4].trim());
					edcCard.setUpdateOper(tt[5].trim());
					edcCard.setUpdateDate(tt[6].trim());
					edcCard.setUpdateTime(tt[7].trim());

					edcCard.setId(id);
					keysList.add(edcCard);
				}
			}
			if (keysList.size() > 0) {
				// 得到Logic
				EdcCardLogicInterface logic = (EdcCardLogicInterface) getBean("edcCardInfoLogic");
				logic.deleteItem(keysList, ud);
			}
			log.info("EdcCardDispatchAction.deleteEdcCard()结束调用：删除终端TPDU信息。");
		} catch (Exception e) {
			log.info("EdcCardDispatchAction.deleteEdcCard()删除终端TPDU信息，出现异常。");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteEdcCard");
	}

	/**
	 * 跳转到输入页面
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("edcCardSr");
	}

	/**
	 * 新增一条信息
	 */
	public org.apache.struts.action.ActionForward createEdcCard(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCardDispatchAction.createEdcCard()开始调用:创建新的终端TPDU信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcCardLogicInterface logic = (EdcCardLogicInterface) getBean("edcCardInfoLogic");
			// 得到Form
			EdcCardForm edcCardForm = (EdcCardForm) form;

			edcCardForm.setUpdateOper(ud.getUserId());
			edcCardForm.setUpdateDate(String.format("%1$tY%1$te%1$tm", new Date()));
			edcCardForm.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

			// 新建一个Model
			EdcCard edcCard = new EdcCard();
			BeanUtils.copyProperties(edcCard, edcCardForm);
			logic.createItem(edcCard, ud);

			log.info("EdcCardDispatchAction.createEdcCard()结束调用:创建新的终端TPDU信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcCardDispatchAction.createEdcCard()创建新的终端TPDU信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createEdcCard");
	}

	/**
	 * 显示修改终端基本资料信息信息界面
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryEdcCardByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		EdcCard edcCard = new EdcCard();
		EdcCardId id = new EdcCardId();
		try {
			log.info("EdcCardDispatchAction.queryEdcCardByKey()开始调用：显示修改终端TPDU信息信息界面。");

			// 得到拼接参数
			String strId = request.getParameter("_id");
			// 分解参数
			String[] tt = strId.split("#");

			if (tt.length == 9) {
				id.setMerchantId(tt[0].trim());
				id.setBankType(tt[1].trim());
				id.setTerminalId(tt[2].trim());
				edcCard.setCardStat(tt[3].trim());
				id.setCardType(tt[4].trim());
				edcCard.setUpdateOper(tt[5].trim());
				edcCard.setUpdateDate(tt[6].trim());
				edcCard.setUpdateTime(tt[7].trim());
				edcCard.setId(id);

				UserData ud = getSessionUserData(request);
				// 得到Logic
				EdcCardLogicInterface logic = (EdcCardLogicInterface) getBean("edcCardInfoLogic");
				map = logic.findItemByKey(edcCard, ud);
				// 得到Form
				EdcCardForm edcCardForm = (EdcCardForm) form;
				map = logic.findItemByKey(edcCard, ud);
				EdcCard model = (EdcCard) map.get("Infolist");
				BeanUtils.copyProperties(edcCardForm, model);

			}
			log.info("EdcCardDispatchAction.queryEdcCardByKey()结束调用：显示修改终端TPDU信息信息界面。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcCardDispatchAction.queryEdcCardByKey()显示修改终端TPDU信息信息界面，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("queryEdcCardByKey");
	}

	/**
	 * 保存终端基本资料信息
	 */
	public org.apache.struts.action.ActionForward saveEdcCard(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCardDispatchAction.saveEdcCard()创建终端TPDU信息信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			EdcCardLogicInterface logic = (EdcCardLogicInterface) getBean("edcCardInfoLogic");
			// 得到Form
			EdcCardForm edcCardForm = (EdcCardForm) form;
			// 新建一个Model
			EdcCard edcCard = new EdcCard();
			EdcCardId id = new EdcCardId();
			id.setBankType(edcCardForm.getId().getBankType());
			id.setCardType(edcCardForm.getId().getCardType());
			id.setMerchantId(edcCardForm.getId().getMerchantId());
			id.setTerminalId(edcCardForm.getId().getTerminalId());

			edcCard.setId(id);
			edcCard.setCardStat(edcCardForm.getCardStat());
			edcCard.setUpdateOper(ud.getUserId());
			edcCard.setUpdateDate(String.format("%1$tY%1$te%1$tm", new Date()));
			edcCard.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

			// 创建终端信息
			logic.saveItem(edcCard, ud);

			log.info("EdcCardDispatchAction.saveEdcCard()结束调用：创建终端TPDU信息信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.info("EdcCardDispatchAction.saveEdcCard()创建终端TPDU信息信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveEdcCard");
	}

}
