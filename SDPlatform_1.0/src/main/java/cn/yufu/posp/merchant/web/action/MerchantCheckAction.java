package cn.yufu.posp.merchant.web.action;

import java.sql.Time;
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
import cn.yufu.posp.merchant.domain.logic.MerchantCheckLogicInterface;
import cn.yufu.posp.merchant.domain.logic.MerchantLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.web.form.MerchantBoForm;
import cn.yufu.posp.merchant.web.form.MerchantForm;
import cn.yufu.posp.usermanager.domain.logic.UserLogicInterface;
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;
import cn.yufu.core.common.util.CommonDomain;

/**
 * 商户审核操作
 * 
 * @author King
 */
public class MerchantCheckAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCheckAction() {

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
			log.info("MerchantCheckAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			MerchantCheckLogicInterface logic = (MerchantCheckLogicInterface) getBean("MerchantCheckLogic");

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
			MerchantBaseBo queryModel = new MerchantBaseBo();
			// 按商户编号查询
			String search = request.getParameter("queryMerid");
			if (search != null)
				queryModel.setMerchantId(search);

			// 按商户状态查询
			String search1 = request.getParameter("queryMerstat");
			if (search1 != null)
				queryModel.setMerchantStat(search1);

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("MerchantCheckAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckAction.queryAll()调用出现异常。");
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
			log.info("MerchantCheckAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到Logic
			MerchantCheckLogicInterface logic = (MerchantCheckLogicInterface) getBean("MerchantCheckLogic");
			MerchantBoForm newForm = (MerchantBoForm) form;
			hashMap = logic.findItem(newForm.getMerchantId(), ud);
			// 新建一个Model
			MerchantBaseBo model = (MerchantBaseBo) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantCheckAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("check");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward check(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantCheckAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);
			// 得到Logic
			MerchantCheckLogicInterface merchantCheckLogic = (MerchantCheckLogicInterface) getBean("MerchantCheckLogic");
			// 得到Form
			MerchantBoForm newForm = (MerchantBoForm) form;
			// 新建一个Model
			MerchantBaseBo model = new MerchantBaseBo();

			BeanUtils.copyProperties(model, newForm);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			
			merchantCheckLogic.saveItem(model, ud);
			//审核通过进入
			if("Y".equals(model.getStatus())){
				//商户信息入表
				MerchantLogicInterface merchantLogic = (MerchantLogicInterface) getBean("MerchantLogic");
				MerchantBaseModel merchant = new MerchantBaseModel();
				merchant.setMerchantId(model.getMerchantId());
				merchant.setMcc(model.getMcc());
				merchant.setAbbrCname(model.getAbbrCname());
				merchant.setAbbrEname(model.getAbbrEname());
				merchant.setAddressChn(model.getAddressChn());
				merchant.setAddressEng(model.getAddressEng());
				merchant.setCityCname(model.getCityCname());
				merchant.setCityEname(model.getCityEname());
				merchant.setMerchantCname(model.getMerchantCname());
				merchant.setMerchantEname(model.getMerchantEname());
				merchant.setManager(model.getManager());
				merchant.setPostCode(model.getPostCode());
				merchant.setTelephone(model.getTelephone());
				merchant.setFax(model.getFax());
				merchant.setSettleMerchId(model.getSettleMerchId());
				merchant.setZbank(model.getZbank());
				merchant.setSignBankId(model.getSignBankId());
				merchant.setSettleMode(model.getSettleMode());
				merchant.setSignDate(model.getSignDate());
				merchant.setMerchantStat(model.getMerchantStat());
				merchant.setCcyType(model.getCcyType());
				merchant.setSignHostId(model.getSignHostId());
				merchant.setLockMode(model.getLockMode());
				merchant.setUpdateDate(model.getUpdateDate());
				merchant.setUpdateOper(model.getUpdateOper());
				merchant.setUpdateTime(model.getUpdateTime());
				
				merchant.setRefundLimit("0");
				merchant.setRefundCheck("N");
				
				merchantLogic.createItem(merchant , ud);
				
				//商户改变权限
				
//				UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
//				OaUserInfo noticeModel = new OaUserInfo();
//				noticeModel = userLogic.queryUserByKey(new OaUserInfoId(ud.getClientId(),merchant.getMerchantId().trim()), ud);
//				noticeModel.setGroupId(CommonDomain.CONST_MERCHANT_GROUP_ID);//
//				userLogic.saveUser(noticeModel, ud);
			}
			
			log.info("MerchantCheckAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("checkToQuery");
	}

}
