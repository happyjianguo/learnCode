package cn.yufu.posp.merchant.web.action;

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

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.MD5;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MerchantUserLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantUser;
import cn.yufu.posp.merchant.web.form.MerchantUserForm;

public class MerchantUserAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MerchantUserAction() {

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
			log.info("MerchantUserAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			MerchantUserLogicInterface logic = (MerchantUserLogicInterface) getBean("MerchantUserLogic");

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
			MerchantUser queryModel = new MerchantUser();
			// ���̻���Ų�ѯ
			// String search = request.getParameter("queryMerid");
			// if (search != null)
			// queryModel.setMerchantUserId(search);
			//
			// // ���̻�״̬��ѯ
			// String search1 = request.getParameter("queryMerstat");
			// if (search1 != null)
			// queryModel.setMerchantUserStat(search1);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("MerchantUserAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserAction.queryAll()���ó����쳣��");
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
			log.info("MerchantUserAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				MerchantUserLogicInterface logic = (MerchantUserLogicInterface) getBean("MerchantUserLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerchantUserAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerchantUserAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
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
			log.info("MerchantUserAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			MerchantUserLogicInterface logic = (MerchantUserLogicInterface) getBean("MerchantUserLogic");

			// ��ʼ������
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			MerchantUserForm newForm = (MerchantUserForm) form;

			if ("".equals(newForm.getLoginId()) || newForm.getLoginId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getLoginId(), ud);

			// �½�һ��Model
			MerchantUser model = (MerchantUser) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantUserAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("MerchantUserAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			MerchantUserLogicInterface logic = (MerchantUserLogicInterface) getBean("MerchantUserLogic");

			MerchantUserForm newForm = (MerchantUserForm) form;

			MerchantUser model = new MerchantUser();
			BeanUtils.copyProperties(model, newForm);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			model.setLoginPassword(MD5.hmacSign(model.getLoginPassword()));
			if ("#111111111111111111#".equals(newForm.getPwdsub().trim()))
				model.setLoginPassword(newForm.getLoginPassword());
			else
				model.setLoginPassword(MD5.hmacSign(newForm.getPwdsub()));
			logic.saveItem(model, ud);

			log.info("MerchantUserAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserAction.saveItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
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
			log.info("MerchantUserAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			MerchantUserLogicInterface logic = (MerchantUserLogicInterface) getBean("MerchantUserLogic");
			// �õ�Form
			MerchantUserForm newForm = (MerchantUserForm) form;

			// �½�һ��Model
			MerchantUser model = new MerchantUser();
			BeanUtils.copyProperties(model, newForm);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			model.setLoginPassword(MD5.hmacSign(model.getLoginPassword()));
			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("MerchantUserAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantUserAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
}
