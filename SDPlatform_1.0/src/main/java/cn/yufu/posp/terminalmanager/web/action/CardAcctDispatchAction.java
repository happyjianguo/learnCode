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
import cn.yufu.posp.terminalmanager.domain.logic.CardAcctLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;
import cn.yufu.posp.terminalmanager.web.form.CardAcctForm;

/**
 * @author zhouya ���˻�����
 * 
 */
public class CardAcctDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("zdTPDUxx");

	public CardAcctDispatchAction() {
	}

	/*** ��ѯ���˻�������Ϣ **/
	public org.apache.struts.action.ActionForward queryCardAcct(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("CardAcctDispatchAction.queryCardAcct()��ʼ���ã����ҿ��˻�����");

			// �õ�Logic
			CardAcctLogicInterface logic = (CardAcctLogicInterface) getBean("cardAcctInfoLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
			String sortField = request.getParameter(dParams[2]);
			if (sortField != null) {
				if (sortField.equals("1"))
					pageInfo.setOrderField("cardNo");
				if (sortField.equals("2"))
					pageInfo.setOrderField("cardStat");
				if (sortField.equals("3"))
					pageInfo.setOrderField("masterCardNo");
				if (sortField.equals("4"))
					pageInfo.setOrderField("acctNo");
				if (sortField.equals("5"))
					pageInfo.setOrderField("depositAmt");
				if (sortField.equals("6"))
					pageInfo.setOrderField("cardAmt");
			}

			// ���ò�ѯ����
			CardAcct queryModel = new CardAcct();
			// �����Ų�ѯ
			String cardNo = request.getParameter("_cardNo");
			if (cardNo != null) {
				if (!cardNo.trim().equals("")) {
					queryModel.setCardNo(cardNo.trim());

				}

			}
			// ������״̬����
			String cardStat = request.getParameter("_cardStat");
			if (cardStat != null) {
				if (!cardStat.trim().equals("")) {
					queryModel.setCardStat(cardStat.trim());
				}

			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllCardAcct");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("CardAcctDispatchAction.queryCardAcct()�������ã����ҿ��˻�����");
		} catch (Exception e) {
			log.info("CardAcctDispatchAction.queryCardAcct()���ҿ��˻����������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ�����˻����� */
	public org.apache.struts.action.ActionForward deleteCardAcct(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardAcctDispatchAction.deleteCardAcct()��ʼ���ã�ɾ�����˻�����");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List<String> keysList = new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				CardAcctLogicInterface logic = (CardAcctLogicInterface) getBean("cardAcctInfoLogic");
				logic.deleteItem(keysList, ud);
			}
			log.info("CardAcctDispatchAction.deleteCardAcct()�������ã�ɾ�����˻�����");
		} catch (Exception e) {

			log.info("CardAcctDispatchAction.deleteCardAcct()ɾ�����˻����������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteCardAcct");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("cardAcctSr");
	}

	/**
	 * �������˻�����
	 */
	public org.apache.struts.action.ActionForward createCardAcct(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardAcctDispatchAction.createCardAcct()��ʼ���ã��������˻�����");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			CardAcctLogicInterface logic = (CardAcctLogicInterface) getBean("cardAcctInfoLogic");
			// �õ�Form
			CardAcctForm cardAcctForm = (CardAcctForm) form;
			// �½�һ��Model
			CardAcct cardAcct = new CardAcct();
			BeanUtils.copyProperties(cardAcct, cardAcctForm);

			cardAcct.setUpdateOper(ud.getUserId());
			cardAcct.setUpdateDate(String.format("%1$tY%1$te%1$tm", new Date()));
			cardAcct.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

			// �����µĿ��˻�����
			logic.createItem(cardAcct, ud);

			log.info("CardAcctDispatchAction.createCardAcct()�������ã��������˻�����");

		} catch (Exception e) {
			log.info("CardAcctDispatchAction.createCardAcct()�������˻����������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardAcct");
	}

	/**
	 * ��ʾ�޸Ŀ��˻�������Ϣ����
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward queryCardAcctByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("CardAcctDispatchAction.queryCardAcctByKey()��ʼ���ã���ʾ�޸Ŀ��˻�������Ϣ���档");
			// �õ�Logic
			CardAcctLogicInterface logic = (CardAcctLogicInterface) getBean("cardAcctInfoLogic");
			// �õ�Form
			CardAcctForm cardAcctForm = (CardAcctForm) form;

			UserData ud = getSessionUserData(request);

			map = logic.findItemByKey(cardAcctForm.getCardNo(), ud);

			// �½�һ��Model
			CardAcct model = (CardAcct) map.get("Infolist");

			BeanUtils.copyProperties(cardAcctForm, model);

			log.info("CardAcctDispatchAction.queryCardAcctByKey()�������ã���ʾ�޸Ŀ��˻�������Ϣ���档");

		} catch (Exception e) {
			log.info("CardAcctDispatchAction.queryCardAcctByKey()��ʾ�޸Ŀ��˻�������Ϣ���棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("queryCardAcctByKey");
	}

	/**
	 * ���濨�˻�����
	 */
	public org.apache.struts.action.ActionForward saveCardAcct(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardAcctDispatchAction.saveCardAccty()��ʼ���ã��޸Ŀ��˻�����");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			CardAcctLogicInterface logic = (CardAcctLogicInterface) getBean("cardAcctInfoLogic");
			// �õ�Form
			CardAcctForm cardAcctForm = (CardAcctForm) form;
			// �½�һ��Model
			CardAcct cardAcct = new CardAcct();

			cardAcct.setAcctNo(cardAcctForm.getCardNo());
			cardAcct.setAddress(cardAcctForm.getAddress());
			cardAcct.setAttachFlag(cardAcctForm.getAttachFlag());
			// cardAcct.setAuthAmt(cardAcctForm.getAuthAmt());
			cardAcct.setAuthCnt(cardAcctForm.getAuthCnt());
			// cardAcct.setAuthTotal(cardAcctForm.getAuthTotal());
			cardAcct.setCardNo(cardAcctForm.getCardNo());
			// cardAcct.setCardAmt(cardAcctForm.getCardAmt());
			cardAcct.setCardStat(cardAcctForm.getCardStat());
			cardAcct.setJob(cardAcctForm.getJob());
			cardAcct.setExpire(cardAcctForm.getExpire());
			// cardAcct.setBalance(cardAcctForm.getBalance());
			cardAcct.setOwner(cardAcctForm.getOwner());
			cardAcct.setPersonId(cardAcctForm.getPersonId());
			cardAcct.setTelephone(cardAcctForm.getTelephone());
			cardAcct.setMasterCardNo(cardAcctForm.getMasterCardNo());
			cardAcct.setMasterStat(cardAcctForm.getMasterStat());
			// cardAcct.setDepositAmt(cardAcctForm.getDepositAmt());
			cardAcct.setDepositCnt(cardAcctForm.getDepositCnt());
			cardAcct.setUpdateOper(ud.getUserId());
			cardAcct.setUpdateDate(String.format("%1$tY%1$te%1$tm", new Date()));
			cardAcct.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));

			if (cardAcctForm.getCh_authAmt() != null && cardAcctForm.getCh_authAmt() != "")
				cardAcct.setAuthAmt(Double.parseDouble(cardAcctForm.getCh_authAmt().trim()));

			if (cardAcctForm.getCh_authTotal() != null && cardAcctForm.getCh_authTotal() != "")
				cardAcct.setAuthTotal(Double.parseDouble(cardAcctForm.getCh_authTotal().trim()));

			if (cardAcctForm.getCh_balance() != null && cardAcctForm.getCh_balance() != "")
				cardAcct.setBalance(Double.parseDouble(cardAcctForm.getCh_balance().trim()));

			if (cardAcctForm.getCh_cardAmt() != null && cardAcctForm.getCh_cardAmt() != "")
				cardAcct.setCardAmt(Double.parseDouble(cardAcctForm.getCh_cardAmt().trim()));

			if (cardAcctForm.getCh_depositAmt() != null && cardAcctForm.getCh_depositAmt() != "")
				cardAcct.setDepositAmt(Double.parseDouble(cardAcctForm.getCh_depositAmt().trim()));

			// �����ն���Ϣ
			logic.saveItem(cardAcct, ud);

			log.info("CardAcctDispatchAction.saveCardAccty()�������ã��޸Ŀ��˻�����");
		} catch (Exception e) {
			log.info("CardAcctDispatchAction.saveCardAccty()�޸Ŀ��˻����������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveCardAcct");
	}

}
