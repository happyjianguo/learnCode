package cn.yufu.posp.merchant.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import cn.yufu.posp.merchant.domain.logic.MerchantSwitchLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantSwitchModel;
import cn.yufu.posp.merchant.web.form.MerchantSwitchForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;

public class MerchantSwitchAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MerchantSwitchAction() {

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
	public ActionForward queryAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";

		try {
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

			request.setAttribute("bankTypeList", banktypeList);
			request.setAttribute("cardTypeList", cardtypeList);

			log.info("MerchantSwitchAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			MerchantSwitchLogicInterface logic = (MerchantSwitchLogicInterface) getBean("MerSwitchLogic");

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
			// if (sortField != null)
			// {
			// if (sortField.equals("1"))
			// pageInfo.setOrderField("sort");
			// if (sortField.equals("2"))
			// pageInfo.setOrderField("memo");
			//				
			// }

			// 设置查询条件
			MerchantSwitchModel queryModel = new MerchantSwitchModel();
			// 按商户编号查询
			String search = request.getParameter("queryMerId");
			if (search != null)
				queryModel.setQueryMerId(search);

			// 按银行类型查询
			String search1 = request.getParameter("queryBank");
			if (search1 != null)
				queryModel.setQueryBank(search1);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("MerchantSwitchAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantSwitchAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				MerchantSwitchLogicInterface logic = (MerchantSwitchLogicInterface) getBean("MerSwitchLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerchantSwitchAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerchantSwitchAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {

			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");

			List<Banktype> banktypeList = commonLogic.queryAllBanktypeItem(null, null);
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);

			request.setAttribute("bankTypeList", banktypeList);
			request.setAttribute("cardTypeList", cardtypeList);

			log.info("MerchantSwitchAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			MerchantSwitchLogicInterface logic = (MerchantSwitchLogicInterface) getBean("MerSwitchLogic");

			MerchantSwitchForm newForm = (MerchantSwitchForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// 新建一个Model
			MerchantSwitchModel model = (MerchantSwitchModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantSwitchAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantSwitchAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantSwitchLogicInterface logic = (MerchantSwitchLogicInterface) getBean("MerSwitchLogic");
			// 得到Form
			MerchantSwitchForm newForm = (MerchantSwitchForm) form;
			// 新建一个Model
			MerchantSwitchModel model = new MerchantSwitchModel();

			model.setMerchantId(newForm.getMerchantId());
			model.setBankType(newForm.getBankType());
			model.setOthMerchantId(newForm.getOthMerchantId());
			model.setOthMcc(newForm.getOthMcc());
			model.setBankId(newForm.getBankId());

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("MerchantSwitchAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantSwitchAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantSwitchLogicInterface logic = (MerchantSwitchLogicInterface) getBean("MerSwitchLogic");
			// 得到Form
			MerchantSwitchForm newForm = (MerchantSwitchForm) form;
			// 新建一个Model
			MerchantSwitchModel model = new MerchantSwitchModel();

			model.setMerchantId(newForm.getMerchantId());
			model.setBankType(newForm.getBankType());
			model.setOthMerchantId(newForm.getOthMerchantId());
			model.setOthMcc(newForm.getOthMcc());

			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("MerchantSwitchAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	/**
	 * ajax显示商户名称
	 */
	public org.apache.struts.action.ActionForward findMerchantName(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {

		PrintWriter out;
		MerchantSwitchLogicInterface logic = (MerchantSwitchLogicInterface) getBean("MerSwitchLogic");
		// 得到Form
		MerchantSwitchForm newForm = (MerchantSwitchForm) form;
		UserData ud = getSessionUserData(request);
		String merchantName = logic.findMerchantName(newForm.getMerchantId(), ud);
		request.setAttribute("merName", merchantName);
		try {
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			if (merchantName != null && !"".equals(merchantName)) {
				out.print(merchantName);
			}
			out.flush();
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
