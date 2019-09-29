package cn.yufu.posp.terminalmanager.web.action;

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

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcBlackLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;
import cn.yufu.posp.terminalmanager.web.form.EdcBlackForm;

/**
 * @author zhouya �ն������趨
 * 
 */
public class EdcBlackDispatchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("edcTerminal");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public EdcBlackDispatchAction() {
	}

	/*** ��ѯ�ն������趨 **/
	public org.apache.struts.action.ActionForward queryAll(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			log.info("EdcBlackDispatchAction.queryAll()��ʼ���ã����ҷ��������ļ�¼��");

			// �õ�Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");

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
					pageInfo.setOrderField("merchantId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("terminalId");
			}

			// ���ò�ѯ����
			EdcBlack queryModel = new EdcBlack();
			// ���̻���Ų�ѯ
			String merchantId = request.getParameter("_merchantId");
			if (merchantId != null) {
				if (!merchantId.trim().equals("")) {
					queryModel.setMerchantId(merchantId);

				}

			}
			// ���ն˱�Ų�ѯ
			String terminalId = request.getParameter("_terminalId");
			if (terminalId != null) {
				if (!terminalId.trim().equals("")) {
					queryModel.setTerminalId(terminalId);
				}

			}

			pageInfo = logic.queryAllItem(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("EdcBlackDispatchAction.queryAll()�������ã����ҷ��������ļ�¼��");
		} catch (Exception e) {
			log.info("EdcBlackDispatchAction.queryAll()���ҷ��������ļ�¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return new ForwardingActionForward(pathForward);

	}

	/** ɾ���ն˻������� */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		List<EdcBlack> keysList = new ArrayList<EdcBlack>();
		try {
			log.info("EdcBlackDispatchAction.deleteItem()��ʼ���ã�ɾ����¼��");

			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');

			for (int i = 0; i < InfoIdStrs.length; i++) {
				String strId = InfoIdStrs[i];
				String[] tt = strId.split("#");

				log.info("�ն˺�������ɾ����¼ ��������==" + strId);

				EdcBlack edcTerminal = new EdcBlack();
				edcTerminal.setMerchantId(tt[0].trim());
				edcTerminal.setTerminalId(tt[1].trim());

				keysList.add(edcTerminal);
			}
			if (keysList.size() > 0) {
				EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
				logic.deleteItem(keysList, ud);
			}

			log.info("EdcBlackDispatchAction.deleteItem()�������ã�ɾ����¼��");
		} catch (Exception e) {

			log.info("EdcBlackDispatchAction.deleteItem()ɾ����¼�������쳣��");
			e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * ����
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("EdcBlackDispatchAction.createItem()��ʼ���ã�������");

			UserData ud = getSessionUserData(request);
			// �õ�Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
			// �õ�Form
			EdcBlackForm edcTerminalForm = (EdcBlackForm) form;
			// �½�һ��Model
			EdcBlack edcTerminal = new EdcBlack();

			if (ud != null) {
				edcTerminalForm.setUpdateOper(ud.getUserId());
				edcTerminalForm.setUpdateTime(sdf.format(new Date()));
				edcTerminalForm.setVersion(sdf.format(new Date()));
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);

				logic.createItem(edcTerminal, ud);
			}

			log.info("EdcBlackDispatchAction.createItem()�������ã�������");

		} catch (Exception e) {
			log.info("EdcBlackDispatchAction.createItem()�����������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * ��ʾ�޸Ľ���
	 */
	@SuppressWarnings("unchecked")
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		try {
			log.info("EdcBlackDispatchAction.findItem()��ʼ���ã���ʾ�޸Ľ��档");
			EdcBlackForm newForm = (EdcBlackForm) form;
			//System.out.println("form::" + form);
			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			// �õ�ƴ�Ӳ���
			String strId = request.getParameter("_id");
			// �ֽ����
			String[] tt = strId.split("#");

			// �õ�Form
			// EdcTerminalForm edcTerminalForm = (EdcTerminalForm) form;
			EdcBlack edcTerminal = new EdcBlack();

			if (tt.length == 16) {
				edcTerminal.setMerchantId(tt[0].trim());
				edcTerminal.setTerminalId(tt[1].trim());
			}

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
			// �õ�Form
			EdcBlackForm edcTerminalForm = (EdcBlackForm) form;

			map = logic.findItemByKey(edcTerminal, ud);

			EdcBlack model = (EdcBlack) map.get("Infolist");

			BeanUtils.copyProperties(edcTerminalForm, model);

			log.info("EdcBlackDispatchAction.findItem()�������ã���ʾ�޸Ľ��档");

		} catch (Exception e) {

			log.info("EdcBlackDispatchAction.findItem()��ʾ�޸Ľ��棬�����쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("edit");
	}

	/**
	 * �޸��ն������趨
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		// �½�һ��Model
		EdcBlack edcTerminal = new EdcBlack();
		try {
			log.info("EdcBlackDispatchAction.saveItem()��ʼ���ã��޸��ն������趨��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			EdcBlackLogicInterface logic = (EdcBlackLogicInterface) getBean("edcBlackInfoLogic");
			// �õ�Form
			EdcBlackForm edcTerminalForm = (EdcBlackForm) form;

			if (ud != null) {

				edcTerminalForm.setUpdateOper(ud.getUserId());
				edcTerminalForm.setUpdateTime(sdf.format(new Date()));
				edcTerminalForm.setVersion(sdf.format(new Date()));
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);
				BeanUtils.copyProperties(edcTerminal, edcTerminalForm);
				edcTerminal.setUpdateOper(ud.getUserId());
				logic.saveItem(edcTerminal, ud);
			}

			log.info("EdcBlackDispatchAction.saveItem()�������ã��޸��ն������趨��");
		} catch (Exception e) {
			log.info("EdcBlackDispatchAction.saveItem()�޸��ն������趨�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("editToQuery");
	}

}
