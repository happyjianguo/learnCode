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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MerchantCardLogicInterface;
import cn.yufu.posp.merchant.domain.logic.TblNoPasswdCardBinLogicInterface;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;
import cn.yufu.posp.merchant.web.form.TblNoPasswdCardBinForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalId;

public class TblNoPasswdCardBinAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public TblNoPasswdCardBinAction() {

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
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");

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
			TblNoPasswdCardBinModel queryModel = new TblNoPasswdCardBinModel();
			// 按卡BIN查询
			String search = request.getParameter("queryCardBin");
			if (search != null){
				queryModel.setQueryCardBin(search);
			}

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("TblNoPasswdCardBinAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.queryAll()调用出现异常。");
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
			log.info("TblNoPasswdCardBinAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");

			TblNoPasswdCardBinForm newForm = (TblNoPasswdCardBinForm) form;

			if ("".equals(newForm.getCardBinInfo()) || newForm.getCardBinInfo() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getCardBinInfo(), ud);

			// 新建一个Model
			TblNoPasswdCardBinModel model = (TblNoPasswdCardBinModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("TblNoPasswdCardBinAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
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
			log.info("TblNoPasswdCardBinAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");
			// 得到Form
			TblNoPasswdCardBinForm newForm = (TblNoPasswdCardBinForm) form;

			// 新建一个Model
			TblNoPasswdCardBinModel model = new TblNoPasswdCardBinModel();

			
			model.setFirstCardBin(newForm.getFirstCardBin());
			model.setLastCardBin(newForm.getLastCardBin());
			model.setCardBinInfo(newForm.getCardBinInfo());
			model.setFlag(newForm.getFlag());
			model.setDataLength(newForm.getDataLength());
			model.setUpdateOper(ud.getUserId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
			// 创建
			logic.saveItem(model, ud);

			log.info("TblNoPasswdCardBinAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.saveItem()调用出现异常。");
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
			log.info("TblNoPasswdCardBinAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");
			// 得到Form
			TblNoPasswdCardBinForm newForm = (TblNoPasswdCardBinForm) form;
			// 新建一个Model
			TblNoPasswdCardBinModel model = new TblNoPasswdCardBinModel();
			
			model.setCardBinInfo(newForm.getFirstCardBin());
			if(newForm.getFirstCardBin().length()==9){
				model.setFirstCardBin(newForm.getFirstCardBin()+"00");
				model.setLastCardBin(newForm.getFirstCardBin()+"99");
			}
			if(newForm.getFirstCardBin().length()==10){
				model.setFirstCardBin(newForm.getFirstCardBin()+"0");
				model.setLastCardBin(newForm.getFirstCardBin()+"9");
			}
			if(newForm.getFirstCardBin().length()==11){
				model.setFirstCardBin(newForm.getFirstCardBin());
				model.setLastCardBin(newForm.getFirstCardBin());
			}
			model.setFlag(newForm.getFlag());
			model.setDataLength(String.valueOf(newForm.getFirstCardBin().length()));
			model.setUpdateOper(ud.getUserId());
			model.setNopasswdMaxamt(new BigDecimal(newForm.getNopasswdMaxamt()).setScale(2,BigDecimal.ROUND_DOWN));
			model.setUpdateDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			model.setUpdateTime(new SimpleDateFormat("HHmmss").format(new Date()));
		
			// 创建
			logic.createItem(model, ud);

			log.info("TblNoPasswdCardBinAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}
	
	
	
	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TblNoPasswdCardBinAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerCardAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerCardAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}
	
	// 验证卡BIN是否已经存在
	public org.apache.struts.action.ActionForward checkFirstCardBin(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("TblNoPasswdCardBinAction.checkFirstCardBin()开始调用：判断卡信息是否存在。");
			String flag = "0";
			String firstCardBin = request.getParameter("firstCardBin");
			if (firstCardBin != null && firstCardBin != "") {
				// 得到Logic
				TblNoPasswdCardBinLogicInterface logic = (TblNoPasswdCardBinLogicInterface) getBean("tblNoPasswdCardBinLogic");
				// 新建一个Model
				TblNoPasswdCardBinModel model = new TblNoPasswdCardBinModel();
				if(firstCardBin.length()==9){
					model.setFirstCardBin(firstCardBin+"00");
				}
				if(firstCardBin.length()==10){
					model.setFirstCardBin(firstCardBin+"0");
				}
				if(firstCardBin.length()==11){
					model.setFirstCardBin(firstCardBin);
				}
				model.setDataLength(String.valueOf(firstCardBin.length()));
				flag = logic.findfirstCardBinByKey(model, null);
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if ("0".equals(flag)){
				out.print(true);// 不存在
			}else{
				out.print(false);// 存在
			}
				
			log.info("TblNoPasswdCardBinAction.checkFirstCardBin()结束调用：判断卡信息是否存在。");
		} catch (Exception e) {
			log.info("TblNoPasswdCardBinAction.checkFirstCardBin()判断卡信息是否存在，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(null);

	}
}
