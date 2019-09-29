package cn.yufu.posp.bookManager.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.bookManager.domain.logic.AnalyzeCupcheckBookLogicInterface;
import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckBook;
import cn.yufu.posp.bookManager.web.form.AnalyzeCupcheckBookForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

public class AnalyzeCupcheckBookAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("book");

	/**
	 * ��ѯ�б�
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
			log.info("AnalyzeCupcheckBookAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			AnalyzeCupcheckBookLogicInterface logic = (AnalyzeCupcheckBookLogicInterface) getBean("AnalyzeCupcheckBookLogic");

			PageInfoModel pageInfo = new PageInfoModel();
			String dParams[] = getDisplayParams();
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���ò�ѯ����
			AnalyzeCupcheckBook queryModel = new AnalyzeCupcheckBook();

			String _filedate = request.getParameter("_filedate");
			if (_filedate != null)
				queryModel.setFiledate(_filedate);

			String _checkdate = request.getParameter("_checkdate");
			if (_checkdate != null)
				queryModel.setCheckdate(_checkdate);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("AnalyzeCupcheckBookAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckBookAction.queryAll()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * ��ѯ��ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public org.apache.struts.action.ActionForward queryDetail(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		Map hashMap = new HashMap();
		try {
			log.info("AnalyzeCupcheckBookAction.queryDetail()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

			AnalyzeCupcheckBookLogicInterface logic = (AnalyzeCupcheckBookLogicInterface) getBean("AnalyzeCupcheckBookLogic");
			AnalyzeCupcheckBookForm newForm = (AnalyzeCupcheckBookForm) form;

			String id = request.getParameter("bookid");
			UserData ud = getSessionUserData(request);
			AnalyzeCupcheckBook model = logic.queryDetail(id, ud);

			BeanUtils.copyProperties(newForm, model);

			log.info("AnalyzeCupcheckBookAction.queryDetail()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckBookAction.queryDetail()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("detail");
	}
}
