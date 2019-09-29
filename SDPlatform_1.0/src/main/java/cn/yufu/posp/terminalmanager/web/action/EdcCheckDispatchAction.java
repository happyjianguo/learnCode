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
	 * �����̻�����ж��̻�����Ƿ�����̻������б���
	 * <p>
	 * ��������򷵻��̻�����
	 * </p>
	 * ����������򷵻�false
	 **/
	public org.apache.struts.action.ActionForward checkMerchantId(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkMerchantId()��ʼ���ã��ж��̻�����Ƿ���ڡ�");
			String merchantName = "false";
			String merchandId = request.getParameter("merchantId");

			if (merchandId != null && merchandId != "") {
				EdcCommonLogicInterface logic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
				merchantName = logic.findMerchantNameByKey(merchandId, null);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (merchantName != null && merchantName != "")// ����
				out.print(merchantName);
			else
				// ������
				out.print(false);
			log.info("EdcCheckDispatchAction.checkMerchantId()�������ã��ж��̻�����Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkMerchantId()�ж��̻�����Ƿ���ڣ������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);

	}

	/***
	 * �ж��ն˱���Ƿ���ն������趨����
	 * <p>
	 * ��������򷵻�true
	 * </p>
	 * ����������򷵻�false
	 **/
	public org.apache.struts.action.ActionForward checkTerminalId(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkTerminalId()��ʼ���ã��ж��ն˱���Ƿ���ڡ�");

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

				if (list.size() > 0)// ����
					out.print(false);
				else
					// ������
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkTerminalId()�������ã��ж��ն˱���Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkTerminalId()�ж��ն˱���Ƿ���ڣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
	
	/***
	 * �ж��ն˱���Ƿ���̻��ն������趨����
	 * <p>
	 * ��������򷵻�true
	 * </p>
	 * ����������򷵻�false
	 **/
	public org.apache.struts.action.ActionForward checkMerchantTerminalId(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkMerchantTerminalId()��ʼ���ã��ն˱���Ƿ�����̻��նˡ�");
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

				if (list.size() > 0) {// ����
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
					// ������
					out.print(false);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkMerchantTerminalId()�������ã��ն˱���Ƿ�����̻��նˡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkMerchantTerminalId()�ն˱���Ƿ�����̻��նˣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * ���Ҫ��ӵ��ն�TPDU��Ϣ�Ƿ��Ѿ��������ݿ���
	 * <p>
	 * ��������򷵻�false
	 * </p>
	 * ����������򷵻�true
	 */
	public org.apache.struts.action.ActionForward checkEdcTpdu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkEdcTpdu()��ʼ���ã��ն�TPDU��Ϣ�Ƿ���ڡ�");

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

			log.info("EdcCheckDispatchAction.checkEdcTpdu()�������ã��ն�TPDU��Ϣ�Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkEdcTpdu()�ն�TPDU��Ϣ�Ƿ���ڣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * ���Ҫ��ӵ��ն�ת����Ϣ�Ƿ��Ѿ��������ݿ���
	 * <p>
	 * ��������򷵻�false
	 * </p>
	 * ����������򷵻�true
	 */
	public org.apache.struts.action.ActionForward checkEdcSwitich(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkEdcSwitich()��ʼ���ã��ն�ת����Ϣ�Ƿ���ڡ�");

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

				if (list.size() > 0)// ����
					out.print(false);
				else
					// ������
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkEdcSwitich()�������ã��ն�ת����Ϣ�Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkEdcSwitich()�ն�ת����Ϣ�Ƿ���ڣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * ���Ҫ��ӵ��ն���������Ϣ�Ƿ��Ѿ��������ݿ���
	 * <p>
	 * ��������򷵻�false
	 * </p>
	 * ����������򷵻�true
	 */
	public org.apache.struts.action.ActionForward checkEdcCard(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkEdcCard()��ʼ���ã��ն��������Ƿ���ڡ�");

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

				if (list.size() > 0)// ����
					out.print(false);
				else
					// ������
					out.print(true);

			} else
				out.print(false);

			log.info("EdcCheckDispatchAction.checkEdcCard()�������ã��ն��������Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkEdcCard()�ն��������Ƿ���ڣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * ���Ҫ��ӵĿ����Ƿ��Ѿ����ڿ��˺���
	 * <p>
	 * ��������򷵻�false
	 * </p>
	 * ����������򷵻�true
	 */
	public org.apache.struts.action.ActionForward checkCardNo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkCardNo()��ʼ���ã������Ƿ���ڡ�");

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

			log.info("EdcCheckDispatchAction.checkCardNo()�������ã������Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkCardNo()�����Ƿ���ڣ������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}

	/**
	 * ���Ҫ��ӵĲ������ƺ������Ƿ��Ѿ������������������б���
	 * <p>
	 * ��������򷵻�false
	 * </p>
	 * ����������򷵻�true
	 */
	public org.apache.struts.action.ActionForward checkSysParameter(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCheckDispatchAction.checkSysParameter()��ʼ���ã��������ƺ������Ƿ���ڡ�");

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

			log.info("EdcCheckDispatchAction.checkSysParameter()�������ã��������ƺ������Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("EdcCheckDispatchAction.checkSysParameter()�������ƺ������Ƿ���ڣ������쳣���á�");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);
	}
}
