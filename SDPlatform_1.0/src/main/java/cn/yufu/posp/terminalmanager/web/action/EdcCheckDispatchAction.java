package cn.yufu.posp.terminalmanager.web.action;

import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;
import cn.yufu.posp.terminalmanager.domain.model.EdcCardId;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitchId;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;

public class EdcCheckDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcCommon");

	public EdcCheckDispatchAction() {
	}

	/***
	 * 根据商户编号判断商户编号是否存在商户资料列表中
	 * <p>
	 * 如果存在则返回商户名称
	 * </p>
	 * 如果不存在则返回false
	 **/
	public org.apache.struts.action.ActionForward checkMerchantId(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkMerchantId()开始调用：判断商户编号是否存在。");
			String merchantName = "false";
			String merchandId = request.getParameter("merchantId");

			if (merchandId != null && merchandId != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				merchantName = logic.findMerchantNameByKey(merchandId, null);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (merchantName != null && merchantName != "")// 存在
				out.print(merchantName);
			else
				// 不存在
				out.print(false);
			log.info("EdcCheckDispatchAction.checkMerchantId()结束调用：判断商户编号是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkMerchantId()判断商户编号是否存在，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);

	}

	/***
	 * 判断终端编号是否存终端资料设定表中
	 * <p>
	 * 如果存在则返回true
	 * </p>
	 * 如果不存在则返回false
	 **/
	public org.apache.struts.action.ActionForward checkTerminalId(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkTerminalId()开始调用：判断终端编号是否存在。");

			String terminalId = request.getParameter("terminalId");

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (terminalId != null && terminalId != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				EdcTerminal edcTerminal = new EdcTerminal();
				EdcTerminalId id = new EdcTerminalId();
				id.setTerminalId(terminalId);
				edcTerminal.setId(id);
				List<EdcTerminal> list = logic.findEdcTerminalItemByKey(edcTerminal, null);

				if (list.size() > 0)// 存在
					out.print(false);
				else
					// 不存在
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkTerminalId()结束调用：判断终端编号是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkTerminalId()判断终端编号是否存在，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
	
	/***
	 * 判断终端编号是否存商户终端资料设定表中
	 * <p>
	 * 如果存在则返回true
	 * </p>
	 * 如果不存在则返回false
	 **/
	public org.apache.struts.action.ActionForward checkMerchantTerminalId(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkMerchantTerminalId()开始调用：终端编号是否存在商户终端。");
			String merchantId = request.getParameter("merchantId");
			String terminalId = request.getParameter("terminalId");

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (terminalId != null && terminalId != "" && merchantId != null && merchantId != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				EdcTerminal edcTerminal = new EdcTerminal();
				EdcTerminalId id = new EdcTerminalId();
				id.setTerminalId(terminalId);
				id.setMerchantId(merchantId);
				edcTerminal.setId(id);
				List<EdcTerminal> list = logic.findEdcTerminalItemByKey(edcTerminal, null);

				if (list.size() > 0) {// 存在
					MerchantCardModel key = new MerchantCardModel();
					key.setMerchantId(merchantId);
					List<Banktype> banktypeList = logic.queryAllMerchantBanktypeItemByKey(key, null);
					List<Cardtype> cardtypeList = logic.queryAllMerchantCardtypeItemByKey(key, null);
					String strBankTypeItem = "";
					String strCardTypeItem = "";
					for (int i = 0; i < banktypeList.size(); i++) {
						Banktype banktype = banktypeList.get(i);
						strBankTypeItem = strBankTypeItem + banktype.getTypeName().trim() + "|" + banktype.getBankType().trim() + "|";
					}
					for (int i = 0; i < cardtypeList.size(); i++) {
						Cardtype cardtype = cardtypeList.get(i);
						strCardTypeItem = strCardTypeItem + cardtype.getId().getTypeName().trim() + "|" + cardtype.getId().getCardType().trim() + "|";
					}
					out.print(strBankTypeItem + "end#" + strCardTypeItem + "end#end");

				} else
					// 不存在
					out.print(false);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkMerchantTerminalId()结束调用：终端编号是否存在商户终端。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkMerchantTerminalId()终端编号是否存在商户终端，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * 检查要添加的终端TPDU信息是否已经存在数据库中
	 * <p>
	 * 如果存在则返回false
	 * </p>
	 * 如果不存在则返回true
	 */
	public org.apache.struts.action.ActionForward checkEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkEdcTpdu()开始调用：终端TPDU信息是否存在。");

			String tpdu = request.getParameter("tpdu");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (tpdu != null && tpdu != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				TPreTpdu key = new TPreTpdu();
				key.setTpdu(tpdu);
				List<TPreTpdu> list = logic.findEdcTPreTpduItemByKey(key, null);

				if (list.size() > 0)
					out.print(false);
				else
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkEdcTpdu()结束调用：终端TPDU信息是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkEdcTpdu()终端TPDU信息是否存在，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * 检查要添加的终端转换信息是否已经存在数据库中
	 * <p>
	 * 如果存在则返回false
	 * </p>
	 * 如果不存在则返回true
	 */
	public org.apache.struts.action.ActionForward checkEdcSwitich(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkEdcSwitich()开始调用：终端转换信息是否存在。");

			String merchantId = request.getParameter("merchantId");
			String terminalId = request.getParameter("terminalId");
			String bankType = request.getParameter("bankType");

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (merchantId != null && merchantId != "" && terminalId != null && terminalId != "" && bankType != null && bankType != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

				EdcSwitch edcSwitch = new EdcSwitch();
				EdcSwitchId id = new EdcSwitchId();
				id.setMerchantId(merchantId);
				id.setTerminalId(terminalId);
				id.setBankType(bankType);
				edcSwitch.setId(id);
				List<EdcSwitch> list = logic.findEdcSwitchItemByKey(edcSwitch, null);

				if (list.size() > 0)// 存在
					out.print(false);
				else
					// 不存在
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkEdcSwitich()结束调用：终端转换信息是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkEdcSwitich()终端转换信息是否存在，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * 检查要添加的终端受理卡类信息是否已经存在数据库中
	 * <p>
	 * 如果存在则返回false
	 * </p>
	 * 如果不存在则返回true
	 */
	public org.apache.struts.action.ActionForward checkEdcCard(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkEdcCard()开始调用：终端受理卡类是否存在。");

			String merchantId = request.getParameter("merchantId");
			String terminalId = request.getParameter("terminalId");
			String cardType = request.getParameter("cardType");

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();

			if (merchantId != null && merchantId != "" && terminalId != null && terminalId != "" && cardType != null && cardType != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

				EdcCard edcCard = new EdcCard();
				EdcCardId id = new EdcCardId();
				id.setMerchantId(merchantId);
				id.setTerminalId(terminalId);
				id.setCardType(cardType);
				edcCard.setId(id);
				List<EdcCard> list = logic.findEdcCardItemByKey(edcCard, null);

				if (list.size() > 0)// 存在
					out.print(false);
				else
					// 不存在
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkEdcCard()结束调用：终端受理卡类是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkEdcCard()终端受理卡类是否存在，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * 检查要添加的卡号是否已经存在卡账号中
	 * <p>
	 * 如果存在则返回false
	 * </p>
	 * 如果不存在则返回true
	 */
	public org.apache.struts.action.ActionForward checkCardNo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkCardNo()开始调用：卡号是否存在。");

			String cardNo = request.getParameter("cardNo");
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (cardNo != null && cardNo != "") {

				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				List<CardAcct> list = logic.findCardAcctItemByKey(cardNo, null);

				if (list.size() > 0)
					out.print(false);
				else
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkCardNo()结束调用：卡号是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkCardNo()卡号是否存在，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * 检查要添加的参数名称和类型是否已经存在其他参数管理列表中
	 * <p>
	 * 如果存在则返回false
	 * </p>
	 * 如果不存在则返回true
	 */
	public org.apache.struts.action.ActionForward checkSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkSysParameter()开始调用：参数名称和类型是否存在。");

			String paramName = request.getParameter("paramName");
			String paramType = request.getParameter("paramType");

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (paramType != null && paramType != "" && paramName != null && paramName != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				SysParameter key = new SysParameter();
				SysParameterId id = new SysParameterId();
				id.setParamName(paramName);
				id.setParamType(paramType);
				key.setId(id);
				List<SysParameter> list = logic.findSysParameterItemByKey(key, null);

				if (list.size() > 0)
					out.print(false);
				else
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkSysParameter()结束调用：参数名称和类型是否存在。");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkSysParameter()参数名称和类型是否存在，出现异常调用。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
}
