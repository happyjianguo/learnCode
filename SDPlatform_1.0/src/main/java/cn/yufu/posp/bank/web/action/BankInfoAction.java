package cn.yufu.posp.bank.web.action;

import java.sql.Time;
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

import cn.yufu.posp.bank.domain.logic.BankInfoLogicInterface;
import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.bank.web.form.BankInfoForm;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.terminalmanager.domain.logic.EdcCommonLogicInterface;
import cn.yufu.posp.bank.domain.model.Banktype;

public class BankInfoAction extends OABaseDispatchAction {
	private static final Log log = LogFactory.getLog("bankInfo");

	public BankInfoAction() {

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

			request.setAttribute("bankTypeList", banktypeList);

			log.error("BankInfoAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());

			// 得到Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");

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
			BankInfoId queryModel = new BankInfoId();
			// 按商户编号查询
			String search = request.getParameter("queryBankId");
			if (search != null && !"".equals(search)) {
				queryModel.setQueryBankId(search);
			}

			// 按银行类型查询
			String search1 = request.getParameter("queryHostId");
			if (search1 != null && !"".equals(search1)) {
				int i = search1.length();
				if (i < 2) {
					for (int j = 1; j <= 2 - i; j++) {
						search1 += " ";
					}
				}
				queryModel.setQueryHostId(search1);
			}

			//
			pageInfo = logic.queryAll(queryModel, pageInfo, getSessionUserData(request));

			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径
			ActionForward actionForward = mapping.findForward("query");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("BankInfoAction.queryAll()开始调用：查找" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.queryAll()调用出现异常。");
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
			log.info("BankInfoAction.deleteItem()开始调用：删除一条记录。" + getSessionUserData(request).getUserId());
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
				BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");

				logic.deleteItem(keysList, ud);
			}
			log.info("BankInfoAction.deleteItem()结束调用：删除一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			log.error("BankInfoAction.deleteItem()结束调用：删除一条记录。出现异常！(" + e.getMessage() + ")。");
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

			request.setAttribute("bankTypeList", banktypeList);

			log.info("BankInfoAction.findItem()开始调用：显示修改的界面" + getSessionUserData(request).getUserId());
			// 得到Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");

			BankInfoForm newForm = (BankInfoForm) form;

			if ("".equals(newForm.getBankId()) || newForm.getBankId() == null) {
				return mapping.findForward("add");
			}

			UserData ud = getSessionUserData(request);

			hashMap = logic.findItem(newForm.getBankId(), ud);

			// 新建一个Model
			BankInfoId model = (BankInfoId) hashMap.get("Infolist");
			BeanUtils.copyProperties(newForm, model);

			log.info("BankInfoAction.findItem()结束调用：显示修改的界面" + getSessionUserData(request).getUserId());

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.findItem()调用出现异常。");
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
			log.info("BankInfoAction.saveItem()开始调用:修改一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");
			// 得到Form
			BankInfoForm newForm = (BankInfoForm) form;
			// 新建一个Model
			BankInfoId model = new BankInfoId();

			model.setBankId(newForm.getBankId());

			model.setAddress(newForm.getAddress());
			model.setAdmBankId(newForm.getAdmBankId());
			model.setAdmHostId(newForm.getAdmHostId());
			model.setAuthMngName(newForm.getAuthMngName());
			model.setAuthMngTel(newForm.getAuthMngTel());
			model.setAuthTel(newForm.getAuthTel());

			model.setBankName(newForm.getBankName());
			model.setBankType(newForm.getBankType());

			model.setCardMngName(newForm.getCardMngName());
			model.setCardMngTel(newForm.getCardMngTel());

			model.setEmail(newForm.getEmail());
			model.setFax(newForm.getFax());
			model.setGmName(newForm.getGmName());
			model.setGmTel(newForm.getGmTel());
			model.setHostId(newForm.getHostId());
			model.setMngName1(newForm.getMngName1());
			model.setMngName2(newForm.getMngName2());
			model.setMngName3(newForm.getMngName3());
			model.setMngTel1(newForm.getMngTel1());
			model.setMngTel2(newForm.getMngTel2());
			model.setMngTel3(newForm.getMngTel3());
			model.setNasMngName(newForm.getNasMngName());
			model.setNasMngTel(newForm.getNasMngTel());
			model.setPostCode(newForm.getPostCode());
			model.setSettleTel(newForm.getSettleTel());
			model.setSettMngName(newForm.getSettMngName());
			model.setSettMngTel(newForm.getSettMngTel());
			model.setTelex(newForm.getTelex());

			model.setUpdateOper(ud.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));

			// 创建新的办公用品物品类别
			logic.saveItem(model, ud);

			log.info("BankInfoAction.saveItem()结束调用:修改一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.saveItem()调用出现异常。");
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
			log.info("BankInfoAction.createItem()开始调用:创建一条记录。" + getSessionUserData(request).getUserId());

			UserData ud = getSessionUserData(request);

			// 得到Logic
			BankInfoLogicInterface logic = (BankInfoLogicInterface) getBean("bankInfoLogic");
			// 得到Form
			BankInfoForm newForm = (BankInfoForm) form;
			// 新建一个Model
			BankInfoId model = new BankInfoId();

			model.setBankId(newForm.getBankId());

			model.setAddress(newForm.getAddress());
			model.setAdmBankId(newForm.getAdmBankId());
			model.setAdmHostId(newForm.getAdmHostId());
			model.setAuthMngName(newForm.getAuthMngName());
			model.setAuthMngTel(newForm.getAuthMngTel());
			model.setAuthTel(newForm.getAuthTel());

			model.setBankName(newForm.getBankName());
			model.setBankType(newForm.getBankType());

			model.setCardMngName(newForm.getCardMngName());
			model.setCardMngTel(newForm.getCardMngTel());

			model.setEmail(newForm.getEmail());
			model.setFax(newForm.getFax());
			model.setGmName(newForm.getGmName());
			model.setGmTel(newForm.getGmTel());
			model.setHostId(newForm.getHostId());
			model.setMngName1(newForm.getMngName1());
			model.setMngName2(newForm.getMngName2());
			model.setMngName3(newForm.getMngName3());
			model.setMngTel1(newForm.getMngTel1());
			model.setMngTel2(newForm.getMngTel2());
			model.setMngTel3(newForm.getMngTel3());
			model.setNasMngName(newForm.getNasMngName());
			model.setNasMngTel(newForm.getNasMngTel());
			model.setPostCode(newForm.getPostCode());
			model.setSettleTel(newForm.getSettleTel());
			model.setSettMngName(newForm.getSettMngName());
			model.setSettMngTel(newForm.getSettMngTel());
			model.setTelex(newForm.getTelex());

			model.setUpdateOper(ud.getUserId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date d = new Date();
			model.setUpdateDate(sdf.format(d));
			Time now = new Time(System.currentTimeMillis());
			sdf = new SimpleDateFormat("HHmmss");
			model.setUpdateTime(sdf.format(now));

			// 创建新的办公用品物品类别
			try {
				logic.createItem(model, ud);
			} catch (Exception e) {
				e.printStackTrace();
				throw new OAException(e.getMessage());
			}

			log.info("BankInfoAction.createItem()结束调用:创建一条记录。" + getSessionUserData(request).getUserId());
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoAction.createItem()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
		return mapping.findForward("editToQuery");

	}

}
