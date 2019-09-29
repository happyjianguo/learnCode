package cn.yufu.posp.merchant.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.POIUtils;
import cn.yufu.core.common.util.StringUtil;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.merchant.domain.logic.MerchantLogicInterface;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.web.form.MerchantBoForm;
import cn.yufu.posp.merchant.web.form.MerchantForm;
import cn.yufu.posp.merchant.web.form.MerchantUploadForm;
import cn.yufu.posp.sysparam.domain.model.AreaCodeInfo;

public class MerchantAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("merchant");

	public MerchantAction() {

	}

	/**
	 * 初始页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws OAException
	 */
	public ActionForward page(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws OAException {
		String pathForward = "";
		try {
			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath;

			log.info("MerchantAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

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
			log.info("MerchantAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");

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
			MerchantBaseModel queryModel = new MerchantBaseModel();
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

			log.info("MerchantAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.queryAll()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return new ForwardingActionForward(pathForward);

	}

	/**
	 * 删除一条记录
	 */
	public org.apache.struts.action.ActionForward deleteItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String InfoIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < InfoIdStrs.length; i++) {
				keysList.add(InfoIdStrs[i]);
			}
			if (keysList.size() > 0) {
				// 得到Logic
				MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("MerchantAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("MerchantAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");
	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward findItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		HashMap hashMap = new HashMap();
		try {
			log.info("MerchantAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");

			// 初始化机构
			// List list = logic.findAllJGs(ud);
			// request.setAttribute("jgList", list);

			MerchantForm newForm = (MerchantForm) form;

			if ("".equals(newForm.getMerchantId()) || newForm.getMerchantId() == null) {
				newForm.setUpdateOper(getSessionUserData(request).getUserId());
				return mapping.findForward("add");
			}

			hashMap = logic.findItem(newForm.getMerchantId(), ud);

			// 新建一个Model
			MerchantBaseModel model = (MerchantBaseModel) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("MerchantAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.findItem()显示修改办公用品物品类别界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("edit");
	}

	/**
	 * 修改一条记录
	 */
	public org.apache.struts.action.ActionForward saveItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// 得到Form
			MerchantForm newForm = (MerchantForm) form;

			String limit = newForm.getRefundLimit();
			if (limit == null || "".equals(limit)) {
				limit = "0";
			}
			newForm.setRefundLimit(limit);
			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// 新建一个Model
			MerchantBaseModel model = new MerchantBaseModel();

			// 基本资料
			model.setMerchantId(newForm.getMerchantId());
			model.setMcc(newForm.getMcc());
			model.setAbbrCname(newForm.getAbbrCname());
			model.setAbbrEname(newForm.getAbbrEname());
			model.setAddressChn(newForm.getAddressChn());
			model.setAddressEng(newForm.getAddressEng());
			model.setCityCname(newForm.getCityCname());
			model.setCityEname(newForm.getCityEname());
			model.setMerchantCname(newForm.getMerchantCname());
			model.setMerchantEname(newForm.getMerchantEname());
			model.setManager(newForm.getManager());
			model.setPostCode(newForm.getPostCode());
			model.setTelephone(newForm.getTelephone());
			model.setFax(newForm.getFax());
			model.setSettleMerchId(newForm.getSettleMerchId());
			model.setZbank(newForm.getZbank());
			model.setSignBankId(newForm.getSignBankId());
			model.setSettleMode(newForm.getSettleMode());
			model.setSignDate(newForm.getSignDate());
			model.setMerchantStat(newForm.getMerchantStat());
			model.setCcyType(newForm.getCcyType());
			model.setSignHostId(newForm.getSignHostId());
			model.setLockMode(newForm.getLockMode());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			// 附加资料
			model.setSndName(newForm.getSndName());
			model.setSndAcct(newForm.getSndAcct());
			model.setSndBank(newForm.getSndBank());
			model.setRcvName(newForm.getRcvName());
			model.setRcvBank(newForm.getRcvBank());
			model.setRcvAcct1(newForm.getRcvAcct1());
			model.setRcvAcct2(newForm.getRcvAcct2());
			// 退货
			model.setRefundLimit(newForm.getRefundLimit());
			model.setRefundCheck(newForm.getRefundCheck());

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("MerchantAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.saveItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}

		return mapping.findForward("editToQuery");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward createItem(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// 得到Form
			MerchantForm newForm = (MerchantForm) form;

			String limit = newForm.getRefundLimit();
			if (limit == null || "".equals(limit)) {
				limit = "0";
			}
			newForm.setRefundLimit(limit);
			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// 新建一个Model
			MerchantBaseModel model = new MerchantBaseModel();

			// 基本资料
			model.setMerchantId(newForm.getMerchantId());
			model.setMcc(newForm.getMcc());
			model.setAbbrCname(newForm.getAbbrCname());
			model.setAbbrEname(newForm.getAbbrEname());
			model.setAddressChn(newForm.getAddressChn());
			model.setAddressEng(newForm.getAddressEng());
			model.setCityCname(newForm.getCityCname());
			model.setCityEname(newForm.getCityEname());
			model.setMerchantCname(newForm.getMerchantCname());
			model.setMerchantEname(newForm.getMerchantEname());
			model.setManager(newForm.getManager());
			model.setPostCode(newForm.getPostCode());
			model.setTelephone(newForm.getTelephone());
			model.setFax(newForm.getFax());
			model.setSettleMerchId(newForm.getSettleMerchId());
			model.setZbank(newForm.getZbank());
			model.setSignBankId(newForm.getSignBankId());
			model.setSettleMode(newForm.getSettleMode());
			model.setSignDate(newForm.getSignDate());
			model.setMerchantStat(newForm.getMerchantStat());
			model.setCcyType(newForm.getCcyType());
			model.setSignHostId(newForm.getSignHostId());
			model.setLockMode(newForm.getLockMode());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			// 附加资料
			model.setSndName(newForm.getSndName());
			model.setSndAcct(newForm.getSndAcct());
			model.setSndBank(newForm.getSndBank());
			model.setRcvName(newForm.getRcvName());
			model.setRcvBank(newForm.getRcvBank());
			model.setRcvAcct1(newForm.getRcvAcct1());
			model.setRcvAcct2(newForm.getRcvAcct2());
			// 退货
			model.setRefundLimit(newForm.getRefundLimit());
			model.setRefundCheck(newForm.getRefundCheck());

			// 创建新的办公用品物品类别
			logic.createItem(model, ud);

			log.info("MerchantAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.createItem()调用出现异常。");
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
		log.info("MerchantAction.findMccName()开始调用：ajax查询签约行号和主机号");

		UserData ud = getSessionUserData(request);
		// 得到Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// 得到Form
		MerchantForm newForm = (MerchantForm) form;
		String bankId = newForm.getSignBankId();
		List<MerchantBaseModel> bankList = logic.findSignBankInfo(bankId, ud);
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

					results = results + "<name>" + vo.getSignBankId().replaceAll(" ", "") + "-" + vo.getSignHostId().replaceAll(" ", "") + "-" + vo.getBankName().replaceAll(" ", "") + "</name>";
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

	/**
	 * ajax查询商户类型
	 */
	public org.apache.struts.action.ActionForward findMccName(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) {
		log.info("MerchantAction.findMccName()开始调用：ajax查询商户类型");
		UserData ud = getSessionUserData(request);
		// 得到Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// 得到Form
		MerchantForm newForm = (MerchantForm) form;
		String mcc = newForm.getMcc();
		List<MerchantBaseModel> bankList = logic.findMccInfo(mcc, ud);
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

					results = results + "<param>" + vo.getMcc().replaceAll(" ", "") + "-" + vo.getMccName().replaceAll(" ", "") + "</param>";
				}
				//
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();
				log.info("MerchantAction.findMccName()结束调用：ajax查询商户类型");
			} catch (IOException e) {
				log.info("MerchantAction.findMccName()ajax查询商户类型，调用异常！");
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

	/**
	 * ajax查询商户类型
	 * 
	 * @throws Exception
	 */
	public org.apache.struts.action.ActionForward findArea(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws Exception {
		log.info("MerchantAction.findMccName()开始调用：ajax查询商户类型");
		UserData ud = getSessionUserData(request);
		// 得到Logic
		MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
		// 得到Form
		String areaCode = request.getParameter("AreaCode");
		String AreaName = request.getParameter("AreaName");
		AreaCodeInfo area = new AreaCodeInfo();
		area.setAreaCode(areaCode);
		area.setAreaName(AreaName);
		List<AreaCodeInfo> areaList = logic.findArea(area, ud);
		if (areaList.size() > 0) {
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
				for (int i = 0; i < areaList.size(); i++) {
					AreaCodeInfo vo = (AreaCodeInfo) areaList.get(i);
					results = results + "<param>" + vo.getAreaCode().replaceAll(" ", "") + "            -            " + "" + vo.getAreaName().replaceAll(" ", "") + "</param>";
				}
				results = results + "</response>";
				out.print(results);
				out.flush();
				out.close();
				log.info("MerchantAction.findMccName()结束调用：ajax查询商户类型");
			} catch (IOException e) {
				log.info("MerchantAction.findMccName()ajax查询商户类型，调用异常！");
				e.printStackTrace();
			}
		} else {
			return null;
		}
		return null;

	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward baseInfo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("MerchantAction.baseInfo()开始调用：显示商户基本信息的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);
			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			MerchantBaseBo merchantBaseBo = logic.findBaseInfoById(ud.getUserId(), ud);
			if (merchantBaseBo != null) {
				MerchantBoForm merchantBoForm = (MerchantBoForm) form;
				BeanUtils.copyProperties(merchantBoForm, merchantBaseBo);
			}
			log.info("MerchantAction.baseInfo()结束调用：显示商户基本信息的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.baseInfo()显示商户基本信息的界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("baseInfo");
	}

	/**
	 * 提交审核
	 */
	public org.apache.struts.action.ActionForward submitBaseInfo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("MerchantAction.baseInfo()开始调用：显示商户基本信息的界面" + getSessionUserData(request).getUserId());
			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// 得到Form
			MerchantBoForm newForm = (MerchantBoForm) form;

			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// 新建一个Model
			MerchantBaseBo model = new MerchantBaseBo();

			BeanUtils.copyProperties(model, newForm);
			// 基本资料
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));
			model.setStatus("C");// C:审核中 N:不通过 Y;通过

			// 创建
			logic.saveBaseInfo(model, ud);
			log.info("MerchantAction.baseInfo()结束调用：显示商户基本信息的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.baseInfo()显示商户基本信息的界面,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("query");
	}

	/**
	 * 创建一条记录
	 */
	public org.apache.struts.action.ActionForward saveBaseInfo(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("MerchantAction.saveBaseInfo()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			MerchantLogicInterface logic = (MerchantLogicInterface) getBean("MerchantLogic");
			// 得到Form
			MerchantBoForm newForm = (MerchantBoForm) form;

			String newDate = newForm.getSignDate();
			newDate = newDate.replaceAll("-", "");
			newForm.setSignDate(newDate);
			// 新建一个Model
			MerchantBaseBo model = new MerchantBaseBo();

			BeanUtils.copyProperties(model, newForm);
			// 基本资料
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			model.setUpdateOper(ud.getUserId());
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));

			// 创建
			logic.saveBaseInfo(model, ud);

			log.info("MerchantAction.saveBaseInfo()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantAction.saveBaseInfo()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("query");

	}

	/**
	 * 显示修改的界面
	 */
	public org.apache.struts.action.ActionForward showFindAreaPage(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		return mapping.findForward("selArea");
	}

	// 导出
	public org.apache.struts.action.ActionForward downloadTemplate(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("开始下载excel模板");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		ServletOutputStream out = null;
		try {
			// //导出处理
			// //构造excel对象
			HSSFWorkbook book = new HSSFWorkbook();
			HSSFSheet sheet = book.createSheet();
			// 生成标题栏目
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			cell.setCellValue("请按模板整理数据！商户类型、签约行号、签约行主机号必填且必须存在！");
			cell.setCellStyle(POIUtils.getRedFontStyle(book));
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 9));
			// 行高
			sheet.setDefaultRowHeightInPoints(15);
			// 列款宽
			sheet.setDefaultColumnWidth((short) 15);
			row = sheet.createRow(1);
			cell = null;
			String[] head1 = { "商户编号", "商户类型", "商户名称(中)", "商户简称(中)", "商户名称(英)", "商户简称(英)", "签约行号", "签约行主机号", "城市代码" };
			for (int i = 0; i < head1.length; i++) {
				cell = row.createCell((short) i);
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				cell.setCellValue(head1[i]);
				cell.setCellStyle(POIUtils.getBlackFontStyle(book));
			}
			row = sheet.createRow(2);
			POIUtils.createTextCell(book, row, (short) 0, "123456789012345");
			POIUtils.createTextCell(book, row, (short) 1, "5411");
			POIUtils.createTextCell(book, row, (short) 2, "测试商户名称");
			POIUtils.createTextCell(book, row, (short) 3, "简称中");
			POIUtils.createTextCell(book, row, (short) 4, "MerchantName");
			POIUtils.createTextCell(book, row, (short) 5, "Abbr");
			POIUtils.createTextCell(book, row, (short) 6, "000001");
			POIUtils.createTextCell(book, row, (short) 7, "00");
			POIUtils.createTextCell(book, row, (short) 8, "1000");
			// 对文件名进行编码
			String templateName = java.net.URLEncoder.encode("商户基本资料导入模板", "UTF-8");
			response.setHeader("Content-Disposition", "attachment;filename=" + templateName + ".xls");
			// 得到输出流
			out = response.getOutputStream();
			book.write(out);
			// //刷新输出流
			out.flush();
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsDispatchAction.queryDetail()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return null;
	}

	// 上传
	public org.apache.struts.action.ActionForward fileUpload(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		log.info("开始导入数据");
		// 得到Logic
		MerchantLogicInterface merchantLogic = (MerchantLogicInterface) getBean("MerchantLogic");

		response.setContentType("text/html;charset=GBK");
		JSONObject jsonObj = new JSONObject();
		UserData ud = getSessionUserData(request);
		Writer writer = null;
		int maxFileSize = 5 * 1048576;// 文件大小不能超过5M
		int number = 0;
		Set merchantSet = new HashSet();
		try {
			writer = response.getWriter();
			MerchantUploadForm uf = (MerchantUploadForm) form;
			byte[] byteFile = POIUtils.convertFileToByte(uf.getUpload());
			if (byteFile.length > maxFileSize) {
				jsonObj.put("success", "上传失败！");
				jsonObj.put("msg", "文件解析失败,文件大小不能超过5M!");
			} else {
				Sheet sheet = POIUtils.readExcel(byteFile);
				for (int index = 2; index < sheet.getLastRowNum() + 1; index++) {
					Row row = sheet.getRow(index);
					// 新建一个Model
					MerchantBaseModel newModel = new MerchantBaseModel();
					newModel.setUpdateOper(ud.getUserId());
					newModel.setUpdateDate(String.format("%1$tY%1$tm%1$td", new Date()));
					newModel.setUpdateTime(String.format("%1$tH%1$tM%1$tS", new Date()));
					String merchantId = POIUtils.getStringFromExcelCell(row.getCell(0));
					if (StringUtil.isNull(merchantId)) {
						break;
					}
					newModel.setMerchantId(merchantId);
					newModel.setMcc(POIUtils.getStringFromExcelCell(row.getCell(1)));
					String cname = POIUtils.getStringFromExcelCell(row.getCell(2));
					newModel.setMerchantCname(cname.length() > 20 ? cname.substring(0, 20) : cname);
					String abbrC = POIUtils.getStringFromExcelCell(row.getCell(3));
					newModel.setAbbrCname(abbrC.length() > 6 ? abbrC.substring(0, 6) : abbrC);
					String ename = POIUtils.getStringFromExcelCell(row.getCell(4));
					newModel.setMerchantEname(ename.length() > 12 ? ename.substring(0, 12) : ename);
					String abbrE = POIUtils.getStringFromExcelCell(row.getCell(5));
					newModel.setAbbrEname(abbrE.length() > 8 ? abbrE.substring(0, 8) : abbrE);
					newModel.setSignBankId(POIUtils.getStringFromExcelCell(row.getCell(6)));
					newModel.setSignHostId(POIUtils.getStringFromExcelCell(row.getCell(7)));
					newModel.setCityCname(POIUtils.getStringFromExcelCell(row.getCell(8)));
					// 默认
					newModel.setRefundCheck("N");
					newModel.setMerchantStat("Y");
					newModel.setRefundLimit("0");
					newModel.setSettleMode("2");
					newModel.setZbank("000001");
					newModel.setSettleMerchId(merchantId);
					// 创建新的信息
					try {
						merchantLogic.createItem(newModel, ud);
						number++;
					} catch (Exception e) {
						merchantSet.add("<br>第" + index + "行,商户编号为" + merchantId + "异常:<br>" + e.getMessage());
						log.error("批量增加段路由异常: ", e);
					}
				}
				String returnVal = "操作成功！ 已成功导入 " + number + " 条记录！<br><br><br>";
				if (merchantSet.size() > 0) {
					returnVal += "商户信息失败记录：" + merchantSet + "<br>";
				}
				// 保存
				jsonObj.put("result", returnVal);
			}
			writer.write(jsonObj.toString());
			writer.flush();
		} catch (Exception e) {
			log.error("批量增加商户保存Excel文件Action异常: {}", e);
			try {
				jsonObj.put("result", "操作失败！");
				jsonObj.put("msg", "" + e.getMessage());
				writer.write(jsonObj.toString());
				writer.flush();
			} catch (Exception e1) {
				log.error("批量增加商户保存Excel文件Action异常: {}", e1);
			}
		} finally {
			if (writer != null) {
				try {
					writer.close();
					log.debug("批量增加商户保存Excel文件,关闭IO流");
				} catch (Exception e) {
					log.error("批量增加商户保存Excel文件Action异常: {}", e);
				}
			}
		}
		return null;
	}
}
