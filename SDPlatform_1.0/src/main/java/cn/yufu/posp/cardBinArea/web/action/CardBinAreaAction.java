package cn.yufu.posp.cardBinArea.web.action;

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

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.cardBinArea.domain.logic.CardBinAreaLogicInterface;
import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.cardBinArea.web.form.CardBinAreaForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;

public class CardBinAreaAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("CardBinArea");

	public CardBinAreaAction() {

	}

	/**
	 * 查找
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
			log.error("CardBinAreaAction.queryAll()开始调用：查找"
					+ getSessionUserData(request).getUserId());

			// 得到Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null) {
				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// 设置排序方式
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// 设置排序字段
			String sortField = request.getParameter(dParams[2]);

			// 设置查询条件
			CardBinArea queryModel = new CardBinArea();
			String search = request.getParameter("queryCardBin");
			if (search != null && !"".equals(search)) {
				queryModel.setCardBin(search);
			}
			String search1 = request.getParameter("queryAreaCode");
			if (search1 != null && !"".equals(search1)) {
				queryModel.setAreaCode(search1);
			}
			String search2 = request.getParameter("queryStatus");
			if (search2 != null && !"".equals(search2)) {
				queryModel.setStatus(search2);
			}
			pageInfo = logic.queryAll(queryModel, pageInfo,
					getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			if(request.getParameter("model")!=null&&"sel".equals(request.getParameter("model"))){
				actionForward = mapping.findForward("sel");			
			}
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "="
					+ pageInfo.getCurrentPage();

			log.info("CardBinAreaAction.queryAll()开始调用：查找"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardBinAreaAction.deleteItem()开始调用：删除一条记录。"
					+ getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			log.info("selectItems：" + keyStr);
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("CardBinAreaAction.deleteItem()结束调用：删除一条记录。"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("CardBinAreaAction.deleteItem()结束调用：删除一条记录。出现异常！("
					+ e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			log.info("CardBinAreaAction.findItem()开始调用：显示修改的界面"
					+ getSessionUserData(request).getUserId());
			// 得到Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");

			CardBinAreaForm newForm = (CardBinAreaForm) form;

			if ("".equals(newForm.getCardBin()) || newForm.getCardBin() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getCardBin(), ud);

			// 新建一个Model
			CardBinArea model = (CardBinArea) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("CardBinAreaAction.findItem()结束调用：显示修改的界面"
					+ getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.findItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward saveItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardBinAreaAction.saveItem()开始调用:修改一条记录。"
					+ getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");
			// 得到Form
			CardBinAreaForm newForm = (CardBinAreaForm) form;
			// 新建一个Model
			CardBinArea model = new CardBinArea();
			model.setCardBin(newForm.getCardBin());
			model.setAreaCode(newForm.getAreaCode());
			model.setStatus(newForm.getStatus());

			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateOper(ud.getUserId());

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("CardBinAreaAction.saveItem()结束调用:修改一条记录。"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.saveItem()调用出现异常。");
			// log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(
			org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("CardBinAreaAction.createItem()开始调用:创建一条记录。"
					+ getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			CardBinAreaLogicInterface logic = (CardBinAreaLogicInterface) getBean("CardBinAreaLogic");
			// 得到Form
			CardBinAreaForm newForm = (CardBinAreaForm) form;
			// 新建一个Model
			CardBinArea model = new CardBinArea();
			model.setAreaCode(newForm.getAreaCode());
			model.setCardBin(newForm.getCardBin());
			model.setStatus(newForm.getStatus());

			model.setCreateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setCreateOper(ud.getUserId());

			model.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
			model.setUpdateOper(ud.getUserId());
			// 创建新的办公用品物品类别
			try {
				logic.createItem(model, ud);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException(e.getMessage());
			}

			log.info("CardBinAreaAction.createItem()结束调用:创建一条记录。"
					+ getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

}
