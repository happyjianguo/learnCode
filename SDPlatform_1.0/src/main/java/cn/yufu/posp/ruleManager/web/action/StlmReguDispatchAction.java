package cn.yufu.posp.ruleManager.web.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MerchantLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.web.form.MerchantForm;
import cn.yufu.posp.ruleManager.domain.logic.StlmReguLogicInterface;
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;
import cn.yufu.posp.ruleManager.web.form.TblStlmReguForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;

public class StlmReguDispatchAction extends OABaseDispatchAction {

	private static final Log log = LogFactory.getLog("rule");

	public StlmReguDispatchAction() {

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
			log.info("StlmReguDispatchAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			// �õ�Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");

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
			TblStlmRegu queryModel = new TblStlmRegu();
			// ���̻���Ų�ѯ
			String _mchtNo = request.getParameter("_mchtNo");
			if (_mchtNo != null)
				queryModel.setMchtNo(_mchtNo);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("StlmReguDispatchAction.queryAll()�������ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.queryAll()���ó����쳣��");
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
			log.info("StlmReguDispatchAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("StlmReguDispatchAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("StlmReguDispatchAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
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
			log.info("StlmReguDispatchAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");

			// ��ʼ������
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			TblStlmReguForm newForm = (TblStlmReguForm) form;
			// ��ö��ֵ
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);
			request.setAttribute("cardTypeList", cardtypeList);

			if ("".equals(newForm.getRuleNo()) || newForm.getRuleNo() == null) {
				// newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getRuleNo(), ud);

			// �½�һ��Model
			TblStlmRegu model = (TblStlmRegu) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("StlmReguDispatchAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("StlmReguDispatchAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");
			// �õ�Form
			TblStlmReguForm newForm = (TblStlmReguForm) form;

			TblStlmRegu model = new TblStlmRegu();
			BeanUtils.copyProperties(model, newForm);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = new Date();
			model.setUpdDatetime(sdf.format(d));
			model.setOperId(ud.getUserId());
			//
			model.setMcc(newForm.getMcc().toUpperCase());
			model.setMchtNo(newForm.getMchtNo().toUpperCase());

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("StlmReguDispatchAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.saveItem()���ó����쳣��");
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
			log.info("StlmReguDispatchAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");
			// �õ�Form
			TblStlmReguForm newForm = (TblStlmReguForm) form;

			// �½�һ��Model
			TblStlmRegu model = new TblStlmRegu();

			// ��������
			// model.setMerchantId(newForm.getMerchantId());
			// model.setMcc(newForm.getMcc());
			// model.setAbbrCname(newForm.getAbbrCname());
			// model.setAbbrEname(newForm.getAbbrEname());
			// model.setAddressChn(newForm.getAddressChn());
			// model.setAddressEng(newForm.getAddressEng());

			BeanUtils.copyProperties(model, newForm);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = new Date();
			model.setCrtDatetime(sdf.format(d));
			model.setUpdDatetime(sdf.format(d));
			model.setOperId(ud.getUserId());
			//
			model.setMcc(newForm.getMcc().toUpperCase());
			model.setMchtNo(newForm.getMchtNo().toUpperCase());

			// �����µİ칫��Ʒ��Ʒ���
			logic.createItem(model, ud);

			log.info("StlmReguDispatchAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	/**
	 * ajax��ѯǩԼ�кź�������
	 */
	public org.apache.struts.action.ActionForward findBankandHost(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		UserData ud = getSessionUserData(request);

		// �õ�Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// �õ�Form
		MerchantForm newForm = (MerchantForm) form;
		String bankId = newForm.getSignBankId();
		List<MerchantBaseModel> bankList = logic.findSignBankInfo(bankId,ud);
		if (bankList.size() > 0) {
			PrintWriter out;
			try {

				// response.setHeader("Content-Type",
				// "text/html;charset=GB2312");
				response.setContentType("text/xml;charset=GBK");
				response.setHeader("Cache-Control", "no-cache");

				response.setCharacterEncoding("GBK");
				out = response.getWriter();
				String results = "";
				results = results + "<?xml version='1.0' encoding='GBK'?><response>";
				for (int i = 0; i < bankList.size(); i++) {
					MerchantBaseModel vo = (MerchantBaseModel) bankList.get(i);

					results = results + "<name>" + vo.getSignBankId().replaceAll(" ", "") + "-" + vo.getSignHostId().replaceAll(" ", "") + "-"
							+ vo.getBankName().replaceAll(" ", "") + "</name>";
				}
				//
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

}
