package cn.yufu.posp.merchant.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
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

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.TblMerchantTranParamLogicInterface;
import cn.yufu.posp.merchant.domain.logic.TblNoPasswdCardBinLogicInterface;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.merchant.web.form.TblMerchantTranParamForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;

public class TblMerchantTranParamAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public TblMerchantTranParamAction() {

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
			// �õ�Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");

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
			// ���ò�ѯ����
			TblMerchantTranParamModel queryModel = new TblMerchantTranParamModel();
			// ���̻���Ų�ѯ
			String search = request.getParameter("_merchantId");
			if (search != null)
				queryModel.setMerchantId(search);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TblMerchantTranParamAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("TblMerchantTranParamAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");

			TblMerchantTranParamForm newForm = (TblMerchantTranParamForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// �½�һ��Model
			TblMerchantTranParamModel model = (TblMerchantTranParamModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TblMerchantTranParamAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("TblMerchantTranParamAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");
			// �õ�Form
			TblMerchantTranParamForm newForm = (TblMerchantTranParamForm) form;

			// �½�һ��Model
			TblMerchantTranParamModel model = new TblMerchantTranParamModel();
			
			model.setMerchantId(newForm.getMerchantId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
			model.setWaveFlag(newForm.getWaveFlag());
			model.setSwipeFlag(newForm.getSwipeFlag());
			model.setTranBitmap(newForm.getTranBitmap());
			model.setFlag(newForm.getFlag());
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			model.setUpdateOper(ud.getUserId());
			model.setScanFlag(newForm.getScanFlag());

			// ����
			logic.saveItem(model, ud);

			log.info("TblMerchantTranParamAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.saveItem()���ó����쳣��");
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
			log.info("TblMerchantTranParamAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");
			// �õ�Form
			TblMerchantTranParamForm newForm = (TblMerchantTranParamForm) form;
			// �½�һ��Model
			TblMerchantTranParamModel model = new TblMerchantTranParamModel();
			model.setMerchantId(newForm.getMerchantId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
//			model.setWaveFlag("1");
//			model.setSwipeFlag("1");
			model.setWaveFlag(newForm.getWaveFlag());
			model.setSwipeFlag(newForm.getSwipeFlag());
			model.setTranBitmap("11111111111111111111111111111111");
			model.setFlag(newForm.getFlag());
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			model.setScanFlag(newForm.getScanFlag());
			model.setUpdateOper(ud.getUserId());


			// ����
			logic.createItem(model, ud);

			log.info("TblMerchantTranParamAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	// ��֤�̻��ŷ��Ѿ�����
		public org.apache.struts.action.ActionForward checkMerchantId(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
				javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
			try {
				log.info("TblMerchantTranParamAction.checkMerchantId()��ʼ���ã��ж��̻�����Ƿ���ڡ�");
				String flag = "";
				String merchantId = request.getParameter("merchantId");

				if (merchantId != null && merchantId != "") {
				// �õ�Logic
				TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");
				flag = logic.checkMerchantId(merchantId, null);	
				}
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
								
				if ("1".equals(flag)){
					out.print(1);//��ȷ
				}
				if ("2".equals(flag)){
					out.print(2);//���󣺴��̻�������̻����в�����
				}
				if ("3".equals(flag)){
					out.print(3);//���󣺴��̻�������̻����ײ������д���
				}
				log.info("TblMerchantTranParamAction.checkMerchantId()�������ã��ж��̻�����Ƿ���ڡ�");
			} catch (Exception e) {
				log.info("TblMerchantTranParamAction.checkMerchantId()�ж��̻�����Ƿ���ڣ������쳣");
				if (log.isDebugEnabled())
					e.printStackTrace();
				log.error(e, e.fillInStackTrace());
				throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
			}

			return mapping.findForward(null);
		}
}
