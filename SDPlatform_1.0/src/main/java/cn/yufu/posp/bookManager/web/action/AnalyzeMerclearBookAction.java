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

import cn.yufu.posp.bookManager.domain.logic.AnalyzeMerclearBookLogicInterface;
import cn.yufu.posp.bookManager.domain.model.AnalyzeMerclearBook;
import cn.yufu.posp.bookManager.web.form.AnalyzeMerclearBookForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

/**
 * �̻���ֵ�Action
 * 
 * @author King
 * 
 */
public class AnalyzeMerclearBookAction extends OABaseDispatchAction {
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
			log.info("AnalyzeMerclearBookAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());

			AnalyzeMerclearBookLogicInterface logic = (AnalyzeMerclearBookLogicInterface) getBean("AnalyzeMerclearBookLogic");

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
			AnalyzeMerclearBook queryModel = new AnalyzeMerclearBook();

			// ���̻���Ų�ѯ
			String _merno = request.getParameter("_merno");
			if (_merno != null)
				queryModel.setMerno(_merno);

			// ���������ڲ�ѯ
			String _trandate = request.getParameter("_trandate");
			if (_trandate != null)
				queryModel.setTrandate(_trandate);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("AnalyzeMerclearBookAction.queryAll()��ʼ���ã�����" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeMerclearBookAction.queryAll()���ó����쳣��");
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
			log.info("AnalyzeMerclearBookAction.queryDetail()��ʼ���ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());

			AnalyzeMerclearBookLogicInterface logic = (AnalyzeMerclearBookLogicInterface) getBean("AnalyzeMerclearBookLogic");
			AnalyzeMerclearBookForm newForm = (AnalyzeMerclearBookForm) form;

			String id = request.getParameter("id");
			UserData ud = getSessionUserData(request);
			AnalyzeMerclearBook model = logic.queryDetail(id, ud);

			BeanUtils.copyProperties(newForm, model);

			log.info("AnalyzeMerclearBookAction.queryDetail()�������ã���ʾ�޸ĵĽ���" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeMerclearBookAction.queryDetail()��ʾ�޸İ칫��Ʒ��Ʒ������,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("detail");
	}
}
