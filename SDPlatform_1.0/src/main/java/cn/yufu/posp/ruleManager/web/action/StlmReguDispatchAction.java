package cn.yufu.posp.ruleManager.web.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MerchantLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.web.form.MerchantForm;
import cn.yufu.posp.ruleManager.domain.logic.StlmReguLogicInterface;
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;
import cn.yufu.posp.ruleManager.web.form.TblStlmReguForm;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.terminalmanager.domain.logic.SysParameterLogicInterface;
import cn.yufu.posp.terminalmanager.domain.model.Cardtype;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;
import cn.yufu.posp.terminalmanager.domain.model.SysParameterId;

public class StlmReguDispatchAction extends OABaseDispatchAction {

	private static final Log log = LogFactory.getLog("rule");

	public StlmReguDispatchAction() {

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
			log.info("StlmReguDispatchAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");

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
			TblStlmRegu queryModel = new TblStlmRegu();
			// 按商户编号查询
			String _mchtNo = request.getParameter("_mchtNo");
			if (_mchtNo != null)
				queryModel.setMchtNo(_mchtNo);

			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("StlmReguDispatchAction.queryAll()结束调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.queryAll()调用出现异常。");
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
			log.info("StlmReguDispatchAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("StlmReguDispatchAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("StlmReguDispatchAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
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
			log.info("StlmReguDispatchAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");

			// 初始化机构
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			TblStlmReguForm newForm = (TblStlmReguForm) form;
			// 查枚举值
			SysParameterLogicInterface tranTypeLogic = (SysParameterLogicInterface) getBean("sysParameterInfoLogic");
			SysParameter sysParameter = new SysParameter();
			SysParameterId id = new SysParameterId();
			id.setParamType("TRAN_TYPE");
			sysParameter.setId(id);
			List tranTypeList = tranTypeLogic.queryAllItem(sysParameter, getSessionUserData(request));
			request.setAttribute("tranTypeList", tranTypeList);
			EdcCommonLogicInterface commonLogic = (EdcCommonLogicInterface) getBean("edcCommonInfoLogic");
			List<Cardtype> cardtypeList = commonLogic.queryAllCardtypeItem(null, null);
			request.setAttribute("cardTypeList", cardtypeList);

			if ("".equals(newForm.getRuleNo()) || newForm.getRuleNo() == null) {
				// newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getRuleNo(), ud);

			// 新建一个Model
			TblStlmRegu model = (TblStlmRegu) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("StlmReguDispatchAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
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
			log.info("StlmReguDispatchAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");
			// 得到Form
			TblStlmReguForm newForm = (TblStlmReguForm) form;

			TblStlmRegu model = new TblStlmRegu();
			BeanUtils.copyProperties(model, newForm);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = new Date();
			model.setUpdDatetime(sdf.format(d));
			model.setOperId(ud.getUserId());
			//
			model.setMcc(newForm.getMcc().toUpperCase());
			model.setMchtNo(newForm.getMchtNo().toUpperCase());

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("StlmReguDispatchAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.saveItem()调用出现异常。");
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
			log.info("StlmReguDispatchAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			StlmReguLogicInterface logic = (StlmReguLogicInterface) getBean("stlmReguLogic");
			// 得到Form
			TblStlmReguForm newForm = (TblStlmReguForm) form;

			// 新建一个Model
			TblStlmRegu model = new TblStlmRegu();

			// 基本资料
			// model.setMerchantId(newForm.getMerchantId());
			// model.setMcc(newForm.getMcc());
			// model.setAbbrCname(newForm.getAbbrCname());
			// model.setAbbrEname(newForm.getAbbrEname());
			// model.setAddressChn(newForm.getAddressChn());
			// model.setAddressEng(newForm.getAddressEng());

			BeanUtils.copyProperties(model, newForm);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = new Date();
			model.setCrtDatetime(sdf.format(d));
			model.setUpdDatetime(sdf.format(d));
			model.setOperId(ud.getUserId());
			//
			model.setMcc(newForm.getMcc().toUpperCase());
			model.setMchtNo(newForm.getMchtNo().toUpperCase());

			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("StlmReguDispatchAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDispatchAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

	/**
	 * ajax查询签约行号和主机号
	 */
	public org.apache.struts.action.ActionForward findBankandHost(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) {
		UserData ud = getSessionUserData(request);

		// 得到Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// 得到Form
		MerchantForm newForm = (MerchantForm) form;
		String bankId = newForm.getSignBankId();
		List<MerchantBaseModel> bankList = logic.findSignBankInfo(bankId,ud);
		if (bankList.size() > 0) {
			PrintWriter out;
			try {

				// response.setHeader("Content-Type",
				// "text/html;charset=GB2312");
				response.setContentType("text/xml;charset=GBK");
				response.setHeader("Cache-Control", "no-cache");

				response.setCharacterEncoding("GBK");
				out = response.getWriter();
				String results = "";
				results = results + "<?xml version='1.0' encoding='GBK'?><response>";
				for (int i = 0; i < bankList.size(); i++) {
					MerchantBaseModel vo = (MerchantBaseModel) bankList.get(i);

					results = results + "<name>" + vo.getSignBankId().replaceAll(" ", "") + "-" + vo.getSignHostId().replaceAll(" ", "") + "-"
							+ vo.getBankName().replaceAll(" ", "") + "</name>";
				}
				//
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

}
