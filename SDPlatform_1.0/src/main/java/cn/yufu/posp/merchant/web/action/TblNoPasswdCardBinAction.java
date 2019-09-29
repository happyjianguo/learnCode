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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MerchantCardLogicInterface;
import cn.yufu.posp.merchant.domain.logic.TblNoPasswdCardBinLogicInterface;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.merchant.web.form.TblNoPasswdCardBinForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class TblNoPasswdCardBinAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public TblNoPasswdCardBinAction() {

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
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");

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
			TblNoPasswdCardBinModel queryModel = new TblNoPasswdCardBinModel();
			// ����BIN��ѯ
			String search = request.getParameter("queryCardBin");
			if (search != null){
				queryModel.setQueryCardBin(search);
			}

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TblNoPasswdCardBinAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.queryAll()���ó����쳣��");
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
			log.info("TblNoPasswdCardBinAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
			// �õ�Logic
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");

			TblNoPasswdCardBinForm newForm = (TblNoPasswdCardBinForm) form;

			if ("".equals(newForm.getCardBinInfo()) || newForm.getCardBinInfo() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getCardBinInfo(), ud);

			// �½�һ��Model
			TblNoPasswdCardBinModel model = (TblNoPasswdCardBinModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TblNoPasswdCardBinAction.findItem()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.findItem()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
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
			log.info("TblNoPasswdCardBinAction.saveItem()��ʼ����:�޸�һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");
			// �õ�Form
			TblNoPasswdCardBinForm newForm = (TblNoPasswdCardBinForm) form;

			// �½�һ��Model
			TblNoPasswdCardBinModel model = new TblNoPasswdCardBinModel();

			
			model.setFirstCardBin(newForm.getFirstCardBin());
			model.setLastCardBin(newForm.getLastCardBin());
			model.setCardBinInfo(newForm.getCardBinInfo());
			model.setFlag(newForm.getFlag());
			model.setDataLength(newForm.getDataLength());
			model.setUpdateOper(ud.getUserId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			// ����
			logic.saveItem(model, ud);

			log.info("TblNoPasswdCardBinAction.saveItem()��������:�޸�һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.saveItem()���ó����쳣��");
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
			log.info("TblNoPasswdCardBinAction.createItem()��ʼ����:����һ����¼��" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");
			// �õ�Form
			TblNoPasswdCardBinForm newForm = (TblNoPasswdCardBinForm) form;
			// �½�һ��Model
			TblNoPasswdCardBinModel model = new TblNoPasswdCardBinModel();
			
			model.setCardBinInfo(newForm.getFirstCardBin());
			if(newForm.getFirstCardBin().length()==9){
				model.setFirstCardBin(newForm.getFirstCardBin()+"00");
				model.setLastCardBin(newForm.getFirstCardBin()+"99");
			}
			if(newForm.getFirstCardBin().length()==10){
				model.setFirstCardBin(newForm.getFirstCardBin()+"0");
				model.setLastCardBin(newForm.getFirstCardBin()+"9");
			}
			if(newForm.getFirstCardBin().length()==11){
				model.setFirstCardBin(newForm.getFirstCardBin());
				model.setLastCardBin(newForm.getFirstCardBin());
			}
			model.setFlag(newForm.getFlag());
			model.setDataLength(String.valueOf(newForm.getFirstCardBin().length()));
			model.setUpdateOper(ud.getUserId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
		
			// ����
			logic.createItem(model, ud);

			log.info("TblNoPasswdCardBinAction.createItem()��������:����һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
	
	
	
	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TblNoPasswdCardBinAction.deleteItem()��ʼ���ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// �õ�Logic
				TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerCardAction.deleteItem()�������ã�ɾ��һ����¼��" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerCardAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}
	
	// ��֤��BIN�Ƿ��Ѿ�����
	public org.apache.struts.action.ActionForward checkFirstCardBin(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TblNoPasswdCardBinAction.checkFirstCardBin()��ʼ���ã��жϿ���Ϣ�Ƿ���ڡ�");
			String flag = "0";
			String firstCardBin = request.getParameter("firstCardBin");
			if (firstCardBin != null && firstCardBin != "") {
				// �õ�Logic
				TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");
				// �½�һ��Model
				TblNoPasswdCardBinModel model = new TblNoPasswdCardBinModel();
				if(firstCardBin.length()==9){
					model.setFirstCardBin(firstCardBin+"00");
				}
				if(firstCardBin.length()==10){
					model.setFirstCardBin(firstCardBin+"0");
				}
				if(firstCardBin.length()==11){
					model.setFirstCardBin(firstCardBin);
				}
				model.setDataLength(String.valueOf(firstCardBin.length()));
				flag = logic.findfirstCardBinByKey(model, null);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if ("0".equals(flag)){
				out.print(true);// ������
			}else{
				out.print(false);// ����
			}
				
			log.info("TblNoPasswdCardBinAction.checkFirstCardBin()�������ã��жϿ���Ϣ�Ƿ���ڡ�");
		} catch (Exception e) {
			log.info("TblNoPasswdCardBinAction.checkFirstCardBin()�жϿ���Ϣ�Ƿ���ڣ������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);

	}
}
