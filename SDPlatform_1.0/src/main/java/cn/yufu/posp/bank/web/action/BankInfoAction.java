package cn.yufu.posp.bank.web.action;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.bank.domain.logic.BankInfoLogicInterface;
import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.bank.web.form.BankInfoForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;

public class BankInfoAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("bankInfo");

	public BankInfoAction() {

	}

	/**
	 * ����
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
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);

			request.setAttribute("bankTypeList", banktypeList);

			log.error("BankInfoAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");

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
			// if (sortField != null)
			// {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("sort");
			// if (sortField.equals("2"))
			// pageInfo.setOrderField("memo");
			//				
			// }

			// ���ò�ѯ����
			BankInfoId queryModel = new BankInfoId();
			// ���̻���Ų�ѯ
			String search = request.getParameter("queryBankId");
			if (search != null && !"".equals(search)) {
				queryModel.setQueryBankId(search);
			}

			// ���������Ͳ�ѯ
			String search1 = request.getParameter("queryHostId");
			if (search1 != null && !"".equals(search1)) {
				int i = search1.length();
				if (i < 2) {
					for (int j = 1; j <= 2 - i; j++) {
						search1 += " ";
					}
				}
				queryModel.setQueryHostId(search1);
			}

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("BankInfoAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("BankInfoAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			log.info("selectItems��" + keyStr);
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("BankInfoAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("BankInfoAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);

			request.setAttribute("bankTypeList", banktypeList);

			log.info("BankInfoAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");

			BankInfoForm newForm = (BankInfoForm) form;

			if ("".equals(newForm.getBankId()) || newForm.getBankId() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getBankId(), ud);

			// �½�һ��Model
			BankInfoId model = (BankInfoId) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("BankInfoAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.findItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("BankInfoAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");
			// �õ�Form
			BankInfoForm newForm = (BankInfoForm) form;
			// �½�һ��Model
			BankInfoId model = new BankInfoId();

			model.setBankId(newForm.getBankId());

			model.setAddress(newForm.getAddress());
			model.setAdmBankId(newForm.getAdmBankId());
			model.setAdmHostId(newForm.getAdmHostId());
			model.setAuthMngName(newForm.getAuthMngName());
			model.setAuthMngTel(newForm.getAuthMngTel());
			model.setAuthTel(newForm.getAuthTel());

			model.setBankName(newForm.getBankName());
			model.setBankType(newForm.getBankType());

			model.setCardMngName(newForm.getCardMngName());
			model.setCardMngTel(newForm.getCardMngTel());

			model.setEmail(newForm.getEmail());
			model.setFax(newForm.getFax());
			model.setGmName(newForm.getGmName());
			model.setGmTel(newForm.getGmTel());
			model.setHostId(newForm.getHostId());
			model.setMngName1(newForm.getMngName1());
			model.setMngName2(newForm.getMngName2());
			model.setMngName3(newForm.getMngName3());
			model.setMngTel1(newForm.getMngTel1());
			model.setMngTel2(newForm.getMngTel2());
			model.setMngTel3(newForm.getMngTel3());
			model.setNasMngName(newForm.getNasMngName());
			model.setNasMngTel(newForm.getNasMngTel());
			model.setPostCode(newForm.getPostCode());
			model.setSettleTel(newForm.getSettleTel());
			model.setSettMngName(newForm.getSettMngName());
			model.setSettMngTel(newForm.getSettMngTel());
			model.setTelex(newForm.getTelex());

			model.setUpdateOper(ud.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("BankInfoAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.saveItem()���ó����쳣��");
			// log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("BankInfoAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");
			// �õ�Form
			BankInfoForm newForm = (BankInfoForm) form;
			// �½�һ��Model
			BankInfoId model = new BankInfoId();

			model.setBankId(newForm.getBankId());

			model.setAddress(newForm.getAddress());
			model.setAdmBankId(newForm.getAdmBankId());
			model.setAdmHostId(newForm.getAdmHostId());
			model.setAuthMngName(newForm.getAuthMngName());
			model.setAuthMngTel(newForm.getAuthMngTel());
			model.setAuthTel(newForm.getAuthTel());

			model.setBankName(newForm.getBankName());
			model.setBankType(newForm.getBankType());

			model.setCardMngName(newForm.getCardMngName());
			model.setCardMngTel(newForm.getCardMngTel());

			model.setEmail(newForm.getEmail());
			model.setFax(newForm.getFax());
			model.setGmName(newForm.getGmName());
			model.setGmTel(newForm.getGmTel());
			model.setHostId(newForm.getHostId());
			model.setMngName1(newForm.getMngName1());
			model.setMngName2(newForm.getMngName2());
			model.setMngName3(newForm.getMngName3());
			model.setMngTel1(newForm.getMngTel1());
			model.setMngTel2(newForm.getMngTel2());
			model.setMngTel3(newForm.getMngTel3());
			model.setNasMngName(newForm.getNasMngName());
			model.setNasMngTel(newForm.getNasMngTel());
			model.setPostCode(newForm.getPostCode());
			model.setSettleTel(newForm.getSettleTel());
			model.setSettMngName(newForm.getSettMngName());
			model.setSettMngTel(newForm.getSettMngTel());
			model.setTelex(newForm.getTelex());

			model.setUpdateOper(ud.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));

			// �����µİ칫��Ʒ��Ʒ���
			try {
				logic.createItem(model, ud);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException(e.getMessage());
			}

			log.info("BankInfoAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

}
