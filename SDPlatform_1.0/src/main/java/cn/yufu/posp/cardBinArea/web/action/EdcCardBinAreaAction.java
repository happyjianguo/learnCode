package cn.yufu.posp.cardBinArea.web.action;

import java.io.PrintWriter;
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

import cn.yufu.posp.cardBinArea.domain.logic.EdcCardBinAreaLogicInterface;
import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.cardBinArea.web.form.EdcCardBinAreaForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;
import cn.yufu.posp.terminalmanager.domain.logic.EdcTerminalLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public class EdcCardBinAreaAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("EdcCardBinArea");

	public EdcCardBinAreaAction() {

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
	public ActionForward queryAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws OAException {
		String pathForward = "";
		try {
			log.error("EdcCardBinAreaAction.queryAll()��ʼ���ã�����"
					+ getSessionUserData(request).getUserId());

			// �õ�Logic
			EdcCardBinAreaLogicInterface logic = (EdcCardBinAreaLogicInterface) getBean("EdcCardBinAreaLogic");

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
			EdcCardBinArea queryModel = new EdcCardBinArea();
			String search = request.getParameter("queryCardBin");
			if (search != null && !"".equals(search)) {
				queryModel.setCardBin(search);
			}
			String search1 = request.getParameter("queryMerchantId");
			if (search1 != null && !"".equals(search1)) {
				queryModel.setMerchantId(search1);
			}
			String search2 = request.getParameter("queryStatus");
			if (search2 != null && !"".equals(search2)) {
				queryModel.setStatus(search2);
			}
			String search3 = request.getParameter("queryTerminal");
			if (search3 != null && !"".equals(search3)) {
				queryModel.setTerminalId(search3);
			}
			
			pageInfo = logic.queryAll(queryModel, pageInfo,
					getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "="
					+ pageInfo.getCurrentPage();

			log.info("EdcCardBinAreaAction.queryAll()��ʼ���ã�����"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * ɾ��һ����¼
	 */
	public org.apache.struts.action.ActionForward deleteItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCardBinAreaAction.deleteItem()��ʼ���ã�ɾ��һ����¼��"
					+ getSessionUserData(request).getUserId());
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
				EdcCardBinAreaLogicInterface logic = (EdcCardBinAreaLogicInterface) getBean("EdcCardBinAreaLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("EdcCardBinAreaAction.deleteItem()�������ã�ɾ��һ����¼��"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("EdcCardBinAreaAction.deleteItem()�������ã�ɾ��һ����¼�������쳣��("
					+ e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸ĵĽ���
	 */
	public org.apache.struts.action.ActionForward findItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("EdcCardBinAreaAction.findItem()��ʼ���ã���ʾ�޸ĵĽ���"
					+ getSessionUserData(request).getUserId());
			// �õ�Logic
			EdcCardBinAreaLogicInterface logic = (EdcCardBinAreaLogicInterface) getBean("EdcCardBinAreaLogic");

			EdcCardBinAreaForm newForm = (EdcCardBinAreaForm) form;

			if ("".equals(newForm.getEdcCardBinAreaId()) || newForm.getEdcCardBinAreaId() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getEdcCardBinAreaId(), ud);

			// �½�һ��Model
			EdcCardBinArea model = (EdcCardBinArea) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("EdcCardBinAreaAction.findItem()�������ã���ʾ�޸ĵĽ���"
					+ getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaAction.findItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * �޸�һ����¼
	 */
	public org.apache.struts.action.ActionForward saveItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCardBinAreaAction.saveItem()��ʼ����:�޸�һ����¼��"
					+ getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcCardBinAreaLogicInterface logic = (EdcCardBinAreaLogicInterface) getBean("EdcCardBinAreaLogic");
			// �õ�Form
			EdcCardBinAreaForm newForm = (EdcCardBinAreaForm) form;
			// �½�һ��Model
			EdcCardBinArea model = new EdcCardBinArea();
			model.setCardBin(newForm.getCardBin());
			model.setMerchantId(newForm.getMerchantId());
			model.setTerminalId(newForm.getTerminalId());
			model.setStatus(newForm.getStatus());

			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateOper(ud.getUserId());

			// �����µİ칫��Ʒ��Ʒ���
			logic.saveItem(model, ud);

			log.info("EdcCardBinAreaAction.saveItem()��������:�޸�һ����¼��"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaAction.saveItem()���ó����쳣��");
			// log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ����һ����¼
	 */
	public org.apache.struts.action.ActionForward createItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcCardBinAreaAction.createItem()��ʼ����:����һ����¼��"
					+ getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcCardBinAreaLogicInterface logic = (EdcCardBinAreaLogicInterface) getBean("EdcCardBinAreaLogic");
			// �õ�Form
			EdcCardBinAreaForm newForm = (EdcCardBinAreaForm) form;
			
			//��ȡ�ն�listStr
			String terminalIds = request.getParameter("merchantIds");		
			// �����µİ칫��Ʒ��Ʒ���
			try {
				if(terminalIds!=null&&!"".equals(terminalIds)){
					EdcTerminalLogicInterface edcTerminalLogicInterface = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
					if(edcTerminalLogicInterface.checkTerminalIsNotMechants(newForm.getMerchantId(), terminalIds, ud)){
						throw new OAException("ѡ�е��ն�"+terminalIds+"�в����̻�"+newForm.getMerchantId()+"���նˣ�������ѡ��");
					}
					String[] terminalIdStr=terminalIds.split(",");
					EdcCardBinArea model =null;
					for(int i=0;i<terminalIdStr.length;i++){						
						// �½�һ��Model
						model = new EdcCardBinArea();
						model.setCardBin(newForm.getCardBin());
						model.setMerchantId(newForm.getMerchantId());
						model.setTerminalId(terminalIdStr[i]);
						model.setStatus(newForm.getStatus());

						model.setCreateDate(String.format("%1$tY%1$tm%1$td", new Date()));
						model.setCreateOper(ud.getUserId());

						model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
						model.setUpdateOper(ud.getUserId());
						
						logic.createItem(model, ud);
					}					
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException(e.getMessage());
			}

			log.info("EdcCardBinAreaAction.createItem()��������:����һ����¼��"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaAction.createItem()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
	
	public org.apache.struts.action.ActionForward getTerminalListByMerchantId(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			UserData ud = getSessionUserData(request);
			log.info("EdcCardBinAreaAction.getTerminalListByMerchantId()��ʼ���á�");
			String orgCode = request.getParameter("merchantId");
			String cardBin = request.getParameter("cardBin");

			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if (orgCode != null && !"".equals(orgCode)&&cardBin!=null&&!"".equals(cardBin)) {
				EdcTerminalLogicInterface logic = (EdcTerminalLogicInterface) getBean("edcTerminalInfoLogic");
				List<EdcTerminal> list = logic.queryTerminalListByMerchantId(orgCode,cardBin, ud);
				String areaStr = "";
				for (int i = 0; i < list.size(); i++) {
					EdcTerminal areaCodeInfo = list.get(i);
					areaStr = areaStr + areaCodeInfo.getId().getTerminalId().trim()+ "|";
				}
				out.print(areaStr);

			} else
				// ������
				out.print(false);

			log.info("EdcCardBinAreaAction.getTerminalListByMerchantId()�������á�");
		} catch (Exception e) {
			log.info("EdcCardBinAreaAction.getTerminalListByMerchantId()�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
		}
		return mapping.findForward(null);
	}

}
