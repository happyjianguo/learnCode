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

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.TblMerchantTranParamLogicInterface;
import cn.yufu.posp.merchant.domain.logic.TblNoPasswdCardBinLogicInterface;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.merchant.web.form.TblMerchantTranParamForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;

public class TblMerchantTranParamAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public TblMerchantTranParamAction() {

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
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");

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
			TblMerchantTranParamModel queryModel = new TblMerchantTranParamModel();
			// 按商户编号查询
			String search = request.getParameter("_merchantId");
			if (search != null)
				queryModel.setMerchantId(search);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TblMerchantTranParamAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.queryAll()调用出现异常。");
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
			log.info("TblMerchantTranParamAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");

			TblMerchantTranParamForm newForm = (TblMerchantTranParamForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// 新建一个Model
			TblMerchantTranParamModel model = (TblMerchantTranParamModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TblMerchantTranParamAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
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
			log.info("TblMerchantTranParamAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");
			// 得到Form
			TblMerchantTranParamForm newForm = (TblMerchantTranParamForm) form;

			// 新建一个Model
			TblMerchantTranParamModel model = new TblMerchantTranParamModel();
			
			model.setMerchantId(newForm.getMerchantId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
			model.setWaveFlag(newForm.getWaveFlag());
			model.setSwipeFlag(newForm.getSwipeFlag());
			model.setTranBitmap(newForm.getTranBitmap());
			model.setFlag(newForm.getFlag());
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			model.setUpdateOper(ud.getUserId());
			model.setScanFlag(newForm.getScanFlag());

			// 创建
			logic.saveItem(model, ud);

			log.info("TblMerchantTranParamAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.saveItem()调用出现异常。");
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
			log.info("TblMerchantTranParamAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");
			// 得到Form
			TblMerchantTranParamForm newForm = (TblMerchantTranParamForm) form;
			// 新建一个Model
			TblMerchantTranParamModel model = new TblMerchantTranParamModel();
			model.setMerchantId(newForm.getMerchantId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
//			model.setWaveFlag("1");
//			model.setSwipeFlag("1");
			model.setWaveFlag(newForm.getWaveFlag());
			model.setSwipeFlag(newForm.getSwipeFlag());
			model.setTranBitmap("11111111111111111111111111111111");
			model.setFlag(newForm.getFlag());
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			model.setScanFlag(newForm.getScanFlag());
			model.setUpdateOper(ud.getUserId());


			// 创建
			logic.createItem(model, ud);

			log.info("TblMerchantTranParamAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	// 验证商户号否已经存在
		public org.apache.struts.action.ActionForward checkMerchantId(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
				javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
			try {
				log.info("TblMerchantTranParamAction.checkMerchantId()开始调用：判断商户编号是否存在。");
				String flag = "";
				String merchantId = request.getParameter("merchantId");

				if (merchantId != null && merchantId != "") {
				// 得到Logic
				TblMerchantTranParamLogicInterface logic = (TblMerchantTranParamLogicInterface) getBean("tblMerchantTranParamLogic");
				flag = logic.checkMerchantId(merchantId, null);	
				}
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
								
				if ("1".equals(flag)){
					out.print(1);//正确
				}
				if ("2".equals(flag)){
					out.print(2);//错误：此商户编号在商户表中不存在
				}
				if ("3".equals(flag)){
					out.print(3);//错误：此商户编号在商户交易参数表中存在
				}
				log.info("TblMerchantTranParamAction.checkMerchantId()结束调用：判断商户编号是否存在。");
			} catch (Exception e) {
				log.info("TblMerchantTranParamAction.checkMerchantId()判断商户编号是否存在，出现异常");
				if (log.isDebugEnabled())
					e.printStackTrace();
				log.error(e, e.fillInStackTrace());
				throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
			}

			return mapping.findForward(null);
		}
}
