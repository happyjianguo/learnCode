package cn.yufu.posp.route.web.action;

import java.math.BigDecimal;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.route.domain.logic.RouteLogicInterface;
import cn.yufu.posp.route.domain.model.CardRoute;
import cn.yufu.posp.route.domain.model.CardRouteId;
import cn.yufu.posp.route.web.form.CardRouteForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

/**
 * @author ���� ��������
 * 
 */
public class CardRouteDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("route");

	/**
	 * @roseuid 44BAF7150119
	 */
	public CardRouteDispatchAction() {

	}

	/**
	 * ���һ�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryCardRoute(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("��ʼ���ã���ѯ����·��");

			// �õ�Logic
			RouteLogicInterface jgLogic = (RouteLogicInterface) getBean("routeLogic");

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
					pageInfo.setOrderField("id.tranBit");

				if (sortField.equals("2"))
					pageInfo.setOrderField("id.firstCardNo");
				if (sortField.equals("3"))
					pageInfo.setOrderField("id.lastCardNo");

				if (sortField.equals("4"))
					pageInfo.setOrderField("id.rcvBankId");
				if (sortField.equals("5"))
					pageInfo.setOrderField("id.rcvHostId");

			}

			// ���ò�ѯ����
			List tjList = new ArrayList();
			StringBuffer bSql = new StringBuffer();
			String bankType = request.getParameter("_cardType");
			if (bankType != null & !"".equals(bankType)) {

				bSql.append(" and id.cardType like'%");
				bSql.append(bankType.trim());
				bSql.append("%'");
				tjList.add(Restrictions.like("id.cardType", bankType.trim(), MatchMode.ANYWHERE));
			}
			String rcvBankId = request.getParameter("_rcvBankId");
			if (rcvBankId != null & !"".equals(rcvBankId)) {
				bSql.append(" and id.rcvHostId like'%");
				bSql.append(rcvBankId);
				bSql.append("%'");
				tjList.add(Restrictions.like("id.rcvHostId", rcvBankId, MatchMode.ANYWHERE));
			}

			List list = new ArrayList();
			list.add("cn.yufu.posp.route.domain.model.CardRoute");
			list.add(tjList);
			list.add(bSql);
			pageInfo = jgLogic.queryRoute(list, pageInfo, getSessionUserData(request));

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showAllCardRoute");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
			List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
			BankInfoId bi = null;
			HashMap mapName = new HashMap();
			for (int i = 0; i < bankInfoList.size(); i++) {

				bi = (BankInfoId) bankInfoList.get(i);
				mapName.put(bi.getBankId() + "_" + bi.getHostId(), bi.getBankName());
			}
			// List<Banktype>
			// banktypeList=commonLogic.queryAllBanktypeItem(null, null);
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);
			List allist = new ArrayList();
			CardRoute newModel = null;
			Cardtype cardtype = null;
			for (int i = 0; i < pageInfo.getResultItems().size(); i++) {
				newModel = (CardRoute) pageInfo.getResultItems().get(i);

				for (int j = 0; j < cardtypeList.size(); j++) {
					cardtype = (Cardtype) cardtypeList.get(j);
					if (cardtype.getId().getCardType().trim().equals(newModel.getId().getCardType()))
						newModel.getId().setCardTypeName(cardtype.getId().getTypeName());
				}
				newModel.getId().setRcvBankName((String) mapName.get(newModel.getId().getRcvBankId() + "_" + newModel.getId().getRcvHostId()));
				allist.add(newModel);
			}
			pageInfo.getResultItems().clear();
			pageInfo.getResultItems().addAll(allist);
			// request.setAttribute("bankTypeList", banktypeList);
			request.setAttribute("cardTypeList", cardtypeList);
			request.setAttribute("pageInfoResult", pageInfo);
			log.info("JgDispatchAction.JgDispatchAction()��ʼ���ã����һ�����Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.JgDispatchAction()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��������Ϣ
	 */
	public org.apache.struts.action.ActionForward deleteTranRoute(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.deleteJg()��ʼ���ã�ɾ��������Ϣ��");
			UserData ud = getSessionUserData(request);
			RouteLogicInterface jgLogic = (RouteLogicInterface) getBean("routeLogic");
			String keyStr = request.getParameter("selectItems");
			// List<String> keysList= new ArrayList<String>();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			// System.out.println(InfoIdStrs.length);
			CardRoute tr = null;
			CardRouteId tri = null;
			for (int i = 0; i < InfoIdStrs.length; i++) {
				tri = new CardRouteId();
				tr = new CardRoute();
				String[] str = InfoIdStrs[i].split("_");
				tri.setTranBit(str[0]);
				tri.setCardType(str[1]);
				tri.setFirstCardNo(str[2]);
				tri.setLastCardNo(str[3]);
				tri.setRcvBankId(str[4]);
				tri.setRcvHostId(str[5]);
				tri.setModuleId(new BigDecimal(str[6]));
				tri.setUpdateOper(str[7]);
				tri.setUpdateDate(str[8]);
				tri.setUpdateTime(str[9]);
				tr.setId(tri);
				// System.out.println(tr);
				jgLogic.deleteRoute(tr, ud);
				// keysList.add(InfoIdStrs[i]);
			}
			// if(keysList.size()>0){
			// �õ�Logic

			// }
			log.info("JgDispatchAction.deleteJg()�������ã�ɾ��������Ϣ��");
		} catch (Exception e) {
			log.error("JgDispatchAction.deleteJg()�������ã�ɾ��������Ϣ�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("createCardRoute");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

		// List<Banktype> banktypeList=commonLogic.queryAllBanktypeItem(null,
		// null);
		List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);
		// request.setAttribute("bankTypeList", banktypeList);
		request.setAttribute("cardTypeList", cardtypeList);
		List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
		request.setAttribute("bankInfoList", bankInfoList);

		return mapping.findForward("addCardRoute");
	}

	/**
	 * �����µĻ�����Ϣ
	 */
	public org.apache.struts.action.ActionForward createTranRoute(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("JgDispatchAction.createJg()��ʼ����:�����µĻ�����Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			RouteLogicInterface officeSuppliesLogic = (RouteLogicInterface) getBean("routeLogic");
			// �õ�Form
			CardRouteForm jgForm = (CardRouteForm) form;
			// �½�һ��Model
			CardRouteId jgModelid = new CardRouteId();
			CardRoute jgModel = new CardRoute();
			BeanUtils.copyProperties(jgModelid, jgForm);

			// ��¼ά����Ϣ�������ֶΣ�ÿ�����У�
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
			Date dt = new Date();
			jgModelid.setUpdateOper(ud.getUserId());
			jgModelid.setUpdateDate(df.format(dt));
			jgModelid.setUpdateTime(df1.format(dt));
			jgModel.setId(jgModelid);

			// �����µĻ�����Ϣ
			officeSuppliesLogic.createRoute(jgModel, ud);
			log.info("JgDispatchAction.createJg()��������:�����µĻ�����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.createJg()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardRoute");
	}

	/**
	 * ��ʾ�޸Ļ�����Ϣ����
	 */
	public org.apache.struts.action.ActionForward queryTranRouteByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String id = request.getParameter("jgId");
		CardRouteForm jgForm = (CardRouteForm) form;
		jgForm.setIid(id);
		if (id != null) {
			String[] str = id.split("_");
			jgForm.setTranBit(str[0]);
			jgForm.setCardType(str[1]);
			jgForm.setFirstCardNo(str[2]);
			jgForm.setLastCardNo(str[3]);
			jgForm.setRcvBankId(str[4]);
			jgForm.setRcvHostId(str[5]);
			jgForm.setModuleId(str[6]);

		}
		EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
		List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
		// List<Banktype> banktypeList=commonLogic.queryAllBanktypeItem(null,
		// null);
		List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

		List<Cardtype> clist = new ArrayList<Cardtype>();
		Cardtype ccc = null;
		boolean bb = true;
		for (int i = 0; i < cardtypeList.size(); i++) {
			ccc = (Cardtype) cardtypeList.get(i);
			if (bb) {
				if (ccc.getId().getCardType().trim().equals(jgForm.getCardType().trim())) {
					clist.add(0, ccc);
				} else {
					clist.add(ccc);
				}
			} else {
				clist.add(ccc);
			}
		}
		BankInfoId bi = null;
		List<BankInfoId> bilist = new ArrayList<BankInfoId>();
		bb = true;
		for (int i = 0; i < bankInfoList.size(); i++) {

			bi = (BankInfoId) bankInfoList.get(i);
			if (bb) {
				if (bi.getBankId().trim().equals(jgForm.getRcvBankId().trim())) {
					bb = false;
					bilist.add(0, bi);
				} else {
					bilist.add(bi);
				}
			} else {
				bilist.add(bi);
			}
		}
		request.setAttribute("bankInfoList", bilist);

		request.setAttribute("cardTypeList", clist);
		return mapping.findForward("showModifyCardRoute");
	}

	/**
	 * ���������Ϣ
	 */
	public org.apache.struts.action.ActionForward saveTranRoute(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		RouteLogicInterface jgLogic = (RouteLogicInterface) getBean("routeLogic");

		CardRouteId tri = new CardRouteId();
		CardRoute tr = new CardRoute();
		try {
			log.info("JgDispatchAction.saveJg()��ʼ����:�����µĻ�����Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic

			String id = request.getParameter("iid");
			String[] str = id.split("_");
			tri.setTranBit(str[0]);
			tri.setCardType(str[1]);
			tri.setFirstCardNo(str[2]);
			tri.setLastCardNo(str[3]);
			tri.setRcvBankId(str[4]);
			tri.setRcvHostId(str[5]);
			tri.setModuleId(new BigDecimal(str[6]));
			tri.setUpdateOper(str[7]);
			tri.setUpdateDate(str[8]);
			tri.setUpdateTime(str[9]);
			tr.setId(tri);
			jgLogic.deleteRoute(tr, ud);

			CardRouteForm jgForm = (CardRouteForm) form;
			// �½�һ��Model
			CardRouteId jgModelid = new CardRouteId();
			CardRoute jgModel = new CardRoute();
			BeanUtils.copyProperties(jgModelid, jgForm);

			// ��¼ά����Ϣ�������ֶΣ�ÿ�����У�
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
			Date dt = new Date();
			jgModelid.setUpdateOper(ud.getUserId());
			jgModelid.setUpdateDate(df.format(dt));
			jgModelid.setUpdateTime(df1.format(dt));
			jgModel.setId(jgModelid);

			// �����µĻ�����Ϣ
			jgLogic.createRoute(jgModel, ud);
			log.info("����·�ɹ�����޸ı���");
		} catch (Exception e) {
			jgLogic.createRoute(tr, null);
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.saveJg()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("createCardRoute");
	}

}
