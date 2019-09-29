package cn.yufu.posp.cardtype.web.action;

import java.util.HashMap;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.cardtype.domain.logic.Card1TypeLogicInterface;
import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.cardtype.domain.model.CardtypeId1;
import cn.yufu.posp.cardtype.web.form.Card1TypeForm;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

/**
 * @author �ｭ� �����͹���
 * 
 */
public class Card1TypeDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF7150119
	 */
	public Card1TypeDispatchAction() {

	}

	/**
	 * ���ҿ�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("Card1TypeDispatchAction.queryCardType()��ʼ���ã����ҿ�����");

			// �õ�Logic
			Card1TypeLogicInterface jgLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");

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
				pageInfo.setOrderField("id.cardType");
				/*
				 * if (sortField.equals("1"))
				 * pageInfo.setOrderField("id.cardId"); else if
				 * (sortField.equals("2"))
				 * pageInfo.setOrderField("id.cardNoId"); else if
				 * (sortField.equals("3"))
				 * pageInfo.setOrderField("id.cardName"); else if
				 * (sortField.equals("4"))
				 * pageInfo.setOrderField("id.cardType"); else if
				 * (sortField.equals("5"))
				 * pageInfo.setOrderField("id.bankType"); else if
				 * (sortField.equals("6"))
				 * pageInfo.setOrderField("id.bankCode");
				 */

			}

			// ���ò�ѯ����
			Cardtype1 queryModel = new Cardtype1();
			CardtypeId1 queryModelId = new CardtypeId1();
			// �������ͺŲ�ѯ
			String jgId = request.getParameter("queryCardType");
			if (jgId != null && !"".equals(jgId)) {
				int i = jgId.length();
				if (i < 4) {
					for (int j = 1; j <= 4 - i; j++) {
						jgId += " ";
					}
				}
				queryModelId.setQueryCardType(jgId);

			}
			String jgName = request.getParameter("queryTypeName");
			if (jgName != null) {
				if (!jgName.trim().equals("")) {
					queryModelId.setQueryTypeName(jgName);
				}

			}
			queryModel.setId(queryModelId);

			pageInfo = jgLogic.queryCardType(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllCardType");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("Card1TypeDispatchAction.queryCardType()��ʼ���ã����һ�����Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.queryCardType()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ����������Ϣ
	 */
	public org.apache.struts.action.ActionForward deleteCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("Card1TypeDispatchAction.deleteCardType()��ʼ���ã�ɾ��������Ϣ��");
			UserData ud = getSessionUserData(request);
			Card1TypeLogicInterface jgLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");
			String keyStr = request.getParameter("selectItems");
			// List<String> keysList= new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			// System.out.println(InfoIdStrs.length);
			Cardtype1 tr = null;
			CardtypeId1 tri = null;
			for (int i = 0; i < InfoIdStrs.length; i++) {
				tri = new CardtypeId1();
				tr = new Cardtype1();
				String[] str = InfoIdStrs[i].split("_");

				tri.setCardType(str[0]);
				tri.setTypeName(str[1]);
				tri.setTypeEname(str[2]);
				tr.setId(tri);
				// System.out.println(tr);
				jgLogic.deleteCardType(tr, ud);
				// keysList.add(InfoIdStrs[i]);
			}
			// if(keysList.size()>0){
			// �õ�Logic

			// }
			log.info("Card1TypeDispatchAction.deleteCardType()�������ã�ɾ��������Ϣ��");
		} catch (Exception e) {
			log.error("Card1TypeDispatchAction.deleteCardType()�������ã�ɾ��������Ϣ�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("createCardType");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("Card1TypeDispatchAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			Card1TypeLogicInterface logic = (Card1TypeLogicInterface) getBean("card1TypeLogic");

			Card1TypeForm newForm = (Card1TypeForm) form;

			if ("".equals(newForm.getCardType()) || newForm.getCardType() == null) {
				return mapping.findForward("addCardType");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.queryCardTypeByKey(newForm.getCardType(), ud);

			// �½�һ��Model
			Cardtype1 model = (Cardtype1) hashMap.get("Infolist");
			if (model != null) {
				BeanUtils.copyProperties(newForm, model.getId());
			} else {
				throw new OAException("��Ҫ�޸ĵ���Ϣ�����ڣ�");
			}

			log.info("Card1TypeDispatchAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("showModifyCardType");
	}

	/**
	 * �����µĿ�������Ϣ
	 */
	public org.apache.struts.action.ActionForward createCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("Card1TypeDispatchAction.createCardType()��ʼ����:�����µĻ�����Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			Card1TypeLogicInterface officeSuppliesLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");
			// �õ�Form
			Card1TypeForm jgForm = (Card1TypeForm) form;
			// �½�һ��Model
			Cardtype1 jgModel = new Cardtype1();
			CardtypeId1 jgModelid = new CardtypeId1();
			BeanUtils.copyProperties(jgModelid, jgForm);
			jgModel.setId(jgModelid);
			// �����µĻ�����Ϣ
			officeSuppliesLogic.createCardType(jgModel, ud);

			log.info("��������:�����µĻ�����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.createCardType()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// /throw new
			// OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardType");
	}

	/**
	 * �޸Ŀ�������Ϣ
	 */
	public org.apache.struts.action.ActionForward saveCardType(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("��ʼ����:�޸Ŀ�������Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			Card1TypeLogicInterface jgLogic = (Card1TypeLogicInterface) getBean("card1TypeLogic");
			// �õ�Form
			Card1TypeForm jgForm = (Card1TypeForm) form;
			CardtypeId1 tri = new CardtypeId1();
			Cardtype1 tr = new Cardtype1();
			tri.setCardType(jgForm.getCardType());
			tri.setTypeName(jgForm.getTypeName());
			tri.setTypeEname(jgForm.getTypeEname());
			tr.setId(tri);
			jgLogic.saveCardType(tr, ud);

			log.info("�����͹�����޸ı���");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDispatchAction.saveCardType()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardType");
	}

}
