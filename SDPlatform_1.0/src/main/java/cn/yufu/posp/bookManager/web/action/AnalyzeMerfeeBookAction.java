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

import cn.yufu.posp.bookManager.domain.logic.AnalyzeMerfeeBookLogicInterface;
import cn.yufu.posp.bookManager.domain.model.AnalyzeMerfeeBook;
import cn.yufu.posp.bookManager.web.form.AnalyzeMerfeeBookForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

/**
 * 商户手续费应收单Action
 * 
 * @author King
 * 
 */
public class AnalyzeMerfeeBookAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("book");

	/**
	 * 查询列表
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
			log.info("AnalyzeMerfeeBookAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			AnalyzeMerfeeBookLogicInterface logic = (AnalyzeMerfeeBookLogicInterface) getBean("AnalyzeMerfeeBookLogic");

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

			// 设置查询条件
			AnalyzeMerfeeBook queryModel = new AnalyzeMerfeeBook();

			// 按商户编号查询
			String _merno = request.getParameter("_merno");
			if (_merno != null)
				queryModel.setMerno(_merno);
			
			String _bookdate = request.getParameter("_bookdate");
			if (_bookdate != null)
				queryModel.setBookdate(_bookdate);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("AnalyzeMerfeeBookAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeMerfeeBookAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * 查询明细
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
			log.info("AnalyzeMerfeeBookAction.queryDetail()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());

			AnalyzeMerfeeBookLogicInterface logic = (AnalyzeMerfeeBookLogicInterface) getBean("AnalyzeMerfeeBookLogic");
			AnalyzeMerfeeBookForm newForm = (AnalyzeMerfeeBookForm) form;
			String id = request.getParameter("id");
			UserData ud = getSessionUserData(request);
			AnalyzeMerfeeBook model = logic.queryDetail(id, ud);

			BeanUtils.copyProperties(newForm, model);

			log.info("AnalyzeMerfeeBookAction.queryDetail()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeMerfeeBookAction.queryDetail()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("detail");
	}
}
