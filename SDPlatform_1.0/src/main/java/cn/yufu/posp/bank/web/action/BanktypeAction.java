package cn.yufu.posp.bank.web.action;

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

import cn.yufu.posp.bank.domain.logic.BanktypeLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.bank.web.form.BanktypeForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;

public class BanktypeAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("bankInfo");

	public BanktypeAction() {

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

			log.info("BanktypeAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");

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

			// 设置查询条件
			Banktype queryModel = new Banktype();
			// 按商户编号查询
			String bankType = request.getParameter("_bankType");
			if (bankType != null && !"".equals(bankType)) {
				queryModel.setBankType(bankType);
			}

			// 按银行类型查询
			String typeName = request.getParameter("_typeName");
			if (typeName != null && !"".equals(typeName)) {
				queryModel.setTypeName(typeName);
			}

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("BanktypeAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.queryAll()调用出现异常。");
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
			log.info("BanktypeAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
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
				BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("BanktypeAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("BanktypeAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
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

			log.info("BanktypeAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");

			BanktypeForm newForm = (BanktypeForm) form;

			if ("".equals(newForm.getBankType()) || newForm.getBankType() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getBankType(), ud);

			// 新建一个Model
			Banktype model = (Banktype) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("BanktypeAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.findItem()调用出现异常。");
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
			log.info("BanktypeAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");
			// 得到Form
			BanktypeForm newForm = (BanktypeForm) form;
			// 新建一个Model
			Banktype model = new Banktype();
			
			BeanUtils.copyProperties(model, newForm);

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("BanktypeAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.saveItem()调用出现异常。");
			// log.error(e, e.fillInStackTrace());
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
			log.info("BanktypeAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			BanktypeLogicInterface logic = (BanktypeLogicInterface) getBean("banktypeLogic");
			// 得到Form
			BanktypeForm newForm = (BanktypeForm) form;
			// 新建一个Model
			Banktype model = new Banktype();

			BeanUtils.copyProperties(model, newForm);

			// 创建新的办公用品物品类别
			try {
				logic.createItem(model, ud);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException("增加银行类型时出现异常");
			}

			log.info("BanktypeAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

}
