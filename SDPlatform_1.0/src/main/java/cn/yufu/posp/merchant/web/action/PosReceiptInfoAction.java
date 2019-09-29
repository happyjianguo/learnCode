package cn.yufu.posp.merchant.web.action;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
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

import cn.yufu.posp.merchant.domain.logic.PosReceiptInfoLogicInterface;

import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

import cn.yufu.posp.merchant.web.form.PosReceiptInfoForm;


public class PosReceiptInfoAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public PosReceiptInfoAction() {

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
			// 得到Logic
			PosReceiptInfoLogicInterface logic = (PosReceiptInfoLogicInterface) getBean("PosReceiptInfoLogic");

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
			PosReceiptInfoModel queryModel = new PosReceiptInfoModel();


			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("PosReceiptInfoAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		HashMap hashMap = new HashMap();
		try {
			

			log.info("PosReceiptInfoAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			PosReceiptInfoLogicInterface logic = (PosReceiptInfoLogicInterface) getBean("PosReceiptInfoLogic");

			PosReceiptInfoForm newForm = (PosReceiptInfoForm) form;

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getStatus(), ud);

			// 新建一个Model
			PosReceiptInfoModel model = (PosReceiptInfoModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("PosReceiptInfoAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoAction.findItem()显示修改界面,调用出现异常。");
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
			log.info("PosReceiptInfoAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			PosReceiptInfoLogicInterface logic = (PosReceiptInfoLogicInterface) getBean("PosReceiptInfoLogic");
			// 得到Form
			PosReceiptInfoForm newForm = (PosReceiptInfoForm) form;
			// 新建一个Model
			PosReceiptInfoModel model = new PosReceiptInfoModel();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			model.setHotline(newForm.getHotline());
			model.setTelSupport(newForm.getTelSupport());
			model.setAdInfo(newForm.getAdInfo().replace("\r\n",""));
			model.setAdYesNoFlag(newForm.getAdYesNoFlag());
			model.setStatus(newForm.getStatus());
			model.setCreateDate(newForm.getCreateDate());
			model.setUpdateOper(ud.getUserId());
			model.setUpdateDate(sdf.format(new Date()));
	

			// 创建新的POS签购单要素设置类别
			logic.saveItem(model, ud);

			log.info("PosReceiptInfoAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}


}
