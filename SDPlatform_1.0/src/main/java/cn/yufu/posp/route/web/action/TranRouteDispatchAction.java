package cn.yufu.posp.route.web.action;

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
import cn.yufu.posp.route.domain.model.TranRoute;
import cn.yufu.posp.route.domain.model.TranRouteId;
import cn.yufu.posp.route.web.form.TranRouteForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;

/**
 * @author ���� ��������
 * 
 */
public class TranRouteDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("route");

	/**
	 * @roseuid 44BAF7150119
	 */
	public TranRouteDispatchAction() {

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
	public org.apache.struts.action.ActionForward queryTranRoute(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("��ʼ���ã����ҽ���·��");

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
					pageInfo.setOrderField("id.bankType");
				if (sortField.equals("3"))
					pageInfo.setOrderField("id.rcvBankId");
				if (sortField.equals("4"))
					pageInfo.setOrderField("id.rcvHostId");

			}

			// ���ò�ѯ����
			List tjList = new ArrayList();
			StringBuffer bSql = new StringBuffer();
			String bankType = request.getParameter("_bankType");
			if (bankType != null & !"".equals(bankType)) {

				bSql.append(" and id.bankType like'%");
				bSql.append(bankType);
				bSql.append("%'");
				tjList.add(Restrictions.like("id.bankType", bankType, MatchMode.ANYWHERE));
			}
			String rcvBankId = request.getParameter("_rcvBankId");
			if (rcvBankId != null & !"".equals(rcvBankId)) {
				bSql.append(" and id.rcvBankId like'%");
				bSql.append(rcvBankId);
				bSql.append("%'");
				tjList.add(Restrictions.like("id.rcvBankId", rcvBankId, MatchMode.ANYWHERE));
			}

			List list = new ArrayList();
			list.add("cn.yufu.posp.route.domain.model.TranRoute");
			list.add(tjList);
			list.add(bSql);
			pageInfo = jgLogic.queryRoute(list, pageInfo, getSessionUserData(request));

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("showTranRoute");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
			BankInfoId bi = null;
			HashMap mapName = new HashMap();
			for (int i = 0; i < bankInfoList.size(); i++) {

				bi = (BankInfoId) bankInfoList.get(i);
				mapName.put(bi.getBankId() + "_" + bi.getHostId(), bi.getBankName());
			}
			List allist = new ArrayList();

			TranRoute newModel = null;
			Banktype banktype = null;

			for (int i = 0; i < pageInfo.getResultItems().size(); i++) {
				newModel = (TranRoute) pageInfo.getResultItems().get(i);

				for (int k = 0; k < banktypeList.size(); k++) {
					banktype = (Banktype) banktypeList.get(k);
					if (banktype.getBankType().trim().equals(newModel.getId().getBankType().trim()))
						newModel.getId().setBankTypeName(banktype.getTypeName().trim());
				}
				newModel.getId().setRcvBankName((String) mapName.get(newModel.getId().getRcvBankId() + "_" + newModel.getId().getRcvHostId()));

				allist.add(newModel);
			}
			pageInfo.getResultItems().clear();
			pageInfo.getResultItems().addAll(allist);
			// List<Cardtype>
			// cardtypeList=commonLogic.queryAllCardtypeItem(null, null);
			request.setAttribute("bankTypeList", banktypeList);
			request.setAttribute("pageInfoResult", pageInfo);
			// request.setAttribute("cardTypeList", cardtypeList);
			log.info("��ʼ���ã����ҽ���·����Ϣ");
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
			TranRoute tr = null;
			TranRouteId tri = null;
			for (int i = 0; i < InfoIdStrs.length; i++) {
				tri = new TranRouteId();
				tr = new TranRoute();
				String[] str = InfoIdStrs[i].split("_");
				tri.setTranBit(str[0]);
				tri.setBankType(str[1]);
				tri.setRcvBankId(str[2]);
				tri.setRcvHostId(str[3]);
				// System.out.println(str[5]);
				tri.setModuleId(new Integer(str[4]));
				tri.setUpdateOper(str[5]);
				tri.setUpdateDate(str[6]);
				tri.setUpdateTime(str[7]);
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
		return mapping.findForward("creaTranRoute");
	}

	/**
	 * ��ת������ҳ��
	 */
	public org.apache.struts.action.ActionForward showSr(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

		List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
		// List<Cardtype> cardtypeList=commonLogic.queryAllCardtypeItem(null,
		// null);
		request.setAttribute("bankTypeList", banktypeList);
		List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);
		request.setAttribute("bankInfoList", bankInfoList);
		// request.setAttribute("cardTypeList", cardtypeList);

		return mapping.findForward("addTranRoute");
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
			TranRouteForm jgForm = (TranRouteForm) form;
			// �½�һ��Model
			TranRouteId jgModelid = new TranRouteId();
			TranRoute jgModel = new TranRoute();
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

			log.info("��������:�����µĽ���·����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDispatchAction.createJg()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("creaTranRoute");
	}

	/**
	 * ��ʾ�޸Ļ�����Ϣ����
	 */
	public org.apache.struts.action.ActionForward queryTranRouteByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String id = request.getParameter("jgId");
		TranRouteForm jgForm = (TranRouteForm) form;
		jgForm.setIid(id);
		if (id != null) {
			String[] str = id.split("_");
			jgForm.setTranBit(str[0]);
			jgForm.setBankType(str[1]);
			jgForm.setRcvBankId(str[2]);
			jgForm.setRcvHostId(str[3]);
			jgForm.setModuleId(str[4]);

		}
		EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

		List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
		// List<Cardtype> cardtypeList=commonLogic.queryAllCardtypeItem(null,
		// null);
		List<BankInfoId> bankInfoList = commonLogic.queryAllBankinfo(null, null);

		List<Banktype> blist = new ArrayList<Banktype>();
		List<BankInfoId> bilist = new ArrayList<BankInfoId>();
		Banktype bbb = null;
		BankInfoId bi = null;
		boolean bb = true;
		for (int i = 0; i < banktypeList.size(); i++) {
			bbb = (Banktype) banktypeList.get(i);
			if (bb) {
				if (bbb.getBankType().trim().equals(jgForm.getBankType().trim())) {
					bb = false;
					blist.add(0, bbb);
				} else {
					blist.add(bbb);
				}
			} else {
				blist.add(bbb);
			}
		}
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
		request.setAttribute("bankTypeList", blist);
		// request.setAttribute("cardTypeList", clist);

		return mapping.findForward("showModifyTranRoute");
	}

	/**
	 * ���潻��·����Ϣ
	 */
	public org.apache.struts.action.ActionForward saveTranRoute(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		RouteLogicInterface jgLogic = (RouteLogicInterface) getBean("routeLogic");
		TranRoute tr = new TranRoute();

		try {
			UserData ud = getSessionUserData(request);
			log.info("��ʼ����:�޸Ľ���·����Ϣ��");
			TranRouteId tri = new TranRouteId();

			String id = request.getParameter("iid");
			String[] str = id.split("_");
			tri.setTranBit(str[0]);
			tri.setBankType(str[1]);
			tri.setRcvBankId(str[2]);
			tri.setRcvHostId(str[3]);
			tri.setModuleId(new Integer(str[4]));
			tri.setUpdateOper(str[5]);
			tri.setUpdateDate(str[6]);
			tri.setUpdateTime(str[7]);
			tr.setId(tri);
			jgLogic.deleteRoute(tr, ud);

			TranRouteForm jgForm = (TranRouteForm) form;
			// �½�һ��Model
			TranRouteId jgModelid = new TranRouteId();
			TranRoute jgModel = new TranRoute();
			BeanUtils.copyProperties(jgModelid, jgForm);

			// ��¼ά����Ϣ�������ֶΣ�ÿ�����У�
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat df1 = new SimpleDateFormat("hhmmss");
			Date dt = new Date();
			jgModelid.setUpdateOper(ud.getUserId());
			jgModelid.setUpdateDate(df.format(dt));
			jgModelid.setUpdateTime(df1.format(dt));
			jgModel.setId(jgModelid);

			// ������Ϣ
			jgLogic.createRoute(jgModel, ud);

			log.info("����·�ɹ�����޸ı���");
		} catch (Exception e) {
			jgLogic.createRoute(tr, null);
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("�޸Ľ���·�ɳ����쳣��");
			log.error(e, e.fillInStackTrace());
			return mapping.findForward("adderr");
			// throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("creaTranRoute");
	}

}
