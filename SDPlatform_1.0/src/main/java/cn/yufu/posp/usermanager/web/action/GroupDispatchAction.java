package cn.yufu.posp.usermanager.web.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.CommonUtil;
import cn.yufu.posp.common.common.util.SystemConstants;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.usermanager.domain.logic.UserGroupLogicInterface;
import cn.yufu.posp.usermanager.domain.logic.UserLogicInterface;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccessId;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;
import cn.yufu.posp.usermanager.web.form.OaGroupForm;

public class GroupDispatchAction extends OABaseDispatchAction {

	private static final Log log = LogFactory.getLog("user");

	public GroupDispatchAction() {

	}

	/**
	 * 查询显示用户信息
	 */
	public org.apache.struts.action.ActionForward queryUserGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		// ForwardingActionForward redirect = null;
		try {
			log.info("GroupDispatchAction.queryNotice()开始调用：查询用户信息列表。");
			UserData ud = getSessionUserData(request);
			// 得到Logic
			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// 设置当前页码
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null && !"null".equals(request.getParameter(dParams[0])) && !"".equals(request.getParameter(dParams[0]))) {

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
			if (sortField != null) {
				// if (sortField.equals("1"))
				// pageInfo.setOrderField("createTimestamp");
				// if (sortField.equals("2"))
				// pageInfo.setOrderField("id.clientId");
				// if (sortField.equals("3"))
				// pageInfo.setOrderField("id.userId");
				// if (sortField.equals("4"))
				// pageInfo.setOrderField("userType");
				if (sortField.equals("1"))
					pageInfo.setOrderField("id.groupId");
				if (sortField.equals("2"))
					pageInfo.setOrderField("groupDescription");
				if (sortField.equals("3"))
					pageInfo.setOrderField("id.clientId");
			}

			// 设置查询条件
			OaGroupInfo queryModel = new OaGroupInfo();

			// // 起始时间
			// String sStartDate = request.getParameter("searchStartDate");
			// if(StringUtils.isNotEmpty(sStartDate))
			// {
			// Date startDate = DateTimeUtil.parse(sStartDate);
			// queryModel.setStartDate(startDate);
			// }
			//
			// //结束时间
			// String sEndDate = request.getParameter("searchEndDate");
			// if(StringUtils.isNotEmpty(sEndDate))
			// {
			// Date endDate = DateTimeUtil.parse(sEndDate);
			// queryModel.setEndDate(endDate);
			// }
			//					
			// 用户标题
			String groupId = request.getParameter("_groupId");
			if (groupId != null)
				queryModel.setCreateUser(groupId);
			//					
			// // 按发布标志查询
			// String search_updata_flag =
			// request.getParameter("search_updata_flag");
			// if (search_updata_flag != null)
			// queryModel.setUpdata_flag(search_updata_flag);
			//					
			// 按个人查询
			// queryModel.setUser_id(ud.getYhid());

			// 取model
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));
			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径===============================================================

			ActionForward actionForward = mapping.findForward("showAllUserGroup");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("GroupDispatchAction.queryNotice()结束调用：查询用户信息列表。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.queryNotice()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * 显示新建页面
	 */
	public org.apache.struts.action.ActionForward showcreateGroupScreen(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("GroupDispatchAction. showcreateGroupScreen()开始调用:显示新建页面	。");

			UserData ud = getSessionUserData(request);

			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			String str = noticeLogic.getMenu(ud);
			request.setAttribute("allmenu", str);
			// System.out.println(str);
			List deptVO = CommonUtil.getDepartmentValueBean(ud.getSsdwdm(), ud.getSsdwmc());

			request.setAttribute("deptVO", deptVO);

			log.info("GroupDispatchAction. showInitialInputCollectAccidentScreen()结束调用:显示新建页面	。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CollectAccidentDispatchAction.showInitialInputCollectAccidentScreen()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("showcreateUserGroup");
	}

	/**
	 * 创建新的用户信息
	 */
	public org.apache.struts.action.ActionForward createGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		ForwardingActionForward redirect = null;
		try {
			log.info("GroupDispatchAction.createNotice()开始调用:创建新的用户信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			UserGroupLogicInterface userLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");
			// 得到Form
			OaGroupForm pForm = (OaGroupForm) form;
			OaGroupInfo noticeModel = new OaGroupInfo();

			// form信息复制到 model
			BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setUpdateTimestamp(new Date());
			noticeModel.setCreateTimestamp(new Date());
			// noticeModel.set
			// 测试假数据
			// ud.setClientId("HX");

			noticeModel.setId(new OaGroupInfoId(ud.getClientId(), pForm.getGroupId()));

			// 单set日期

			// 创建新的用户信息
			userLogic.createUser(noticeModel, ud);
			// 得到权限列表
			String[] allid = request.getParameterValues("text");
			OaGroupAccessId ogai = null;
			OaGroupAccess oga = null;
			String[] str = null;
			if (allid != null) {
				for (int i = 0; i < allid.length; i++) {
					str = allid[i].split("_");
					ogai = new OaGroupAccessId();
					oga = new OaGroupAccess();
					ogai.setClientId(ud.getClientId());
					oga.setCreateTimestamp(new Date());
					ogai.setGroupId(pForm.getGroupId());
					ogai.setMenuId(str[0].split(";")[str[0].split(";").length - 1]);
					ogai.setScreenId(str[1]);
					oga.setUseCorporateDataOnly("N");
					oga.setCreateUser(ud.getUserId());
					oga.setId(ogai);
					userLogic.createUserGroupAccess(oga, ud);
					// System.out.println("id====="+allid[i]);
				}
			}

			log.info("UserGroupDispatchAction.createGroup()结束调用:创建新的角色信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.createNotice()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_GROUP_ADD);
		}
		return mapping.findForward("creatUserGroup");
	}

	/**
	 * 显示编辑用户信息的界面
	 */
	public org.apache.struts.action.ActionForward queryGroupByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserGroupDispatchAction.queryGroupByKey()开始调用：显示编辑角色信息");
			// 得到Logic
			UserGroupLogicInterface userLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			UserData ud = getSessionUserData(request);

			OaGroupForm noticeForm = (OaGroupForm) form;

			OaGroupInfo noticeModel = new OaGroupInfo();
			// ud
			// ud.setClientId("HX");

			noticeModel = userLogic.queryUserByKey(new OaGroupInfoId(ud.getClientId(), noticeForm.getId()), ud);
			// BeanUtils.copyProperties(noticeForm,noticeModel);
			// System.out.println("aaaaaaaaaaaa"+noticeModel);
			noticeForm.setClientId(noticeModel.getId().getClientId());
			noticeForm.setGroupId(noticeModel.getId().getGroupId());
			noticeForm.setGroupDescription(noticeModel.getGroupDescription());
			noticeForm.setUpdateUser(noticeModel.getUpdateUser());
			noticeForm.setUpdateTimestamp(noticeModel.getUpdateTimestamp() + "");
			UserData uda = new UserData();
			uda.setClientId(noticeModel.getId().getClientId());
			uda.setGroupId(noticeModel.getId().getGroupId());
			// UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface)
			// getBean("UserGroupLogic");

			String str = userLogic.getZjMenu(uda);
			request.setAttribute("allmenu", str);
			log.info("UserGroupDispatchAction.queryGroupByKey()结束调用：显示编辑角色信息");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.queryNoticeByKey()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("showModifyUserGroup");
	}

	/**
	 *保存用户信息
	 */
	public org.apache.struts.action.ActionForward saveGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("GroupDispatchAction.saveNotice()开始调用:保存用户信息。");

			UserData ud = getSessionUserData(request);

			// 得到Logic
			UserGroupLogicInterface userLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");
			// 得到Form
			OaGroupForm pForm = (OaGroupForm) form;
			OaGroupInfo noticeModel = new OaGroupInfo();

			// form信息复制到 model
			// BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setGroupDescription(pForm.getGroupDescription());
			noticeModel.setUpdateUser(ud.getUserId());
			noticeModel.setUpdateTimestamp(new Date());
			noticeModel.setCreateTimestamp(new Date());

			// 测试假数据
			// ud.setClientId("HX");

			noticeModel.setId(new OaGroupInfoId(ud.getClientId(), pForm.getGroupId()));

			// 单set日期

			// 创建新的用户信息
			userLogic.saveUser(noticeModel, ud);
			// 得到权限列表
			String[] allid = request.getParameterValues("text");
			OaGroupAccessId ogai = null;
			OaGroupAccess oga = null;
			String[] str = null;
			if (allid != null) {
				for (int i = 0; i < allid.length; i++) {
					str = allid[i].split("_");
					ogai = new OaGroupAccessId();
					oga = new OaGroupAccess();
					ogai.setClientId(ud.getClientId());
					oga.setCreateTimestamp(new Date());
					ogai.setGroupId(pForm.getGroupId());
					ogai.setMenuId(str[0].split(";")[str[0].split(";").length - 1]);
					ogai.setScreenId(str[1]);
					oga.setUseCorporateDataOnly("N");
					oga.setCreateUser(ud.getUserId());
					oga.setId(ogai);
					userLogic.createUserGroupAccess(oga, ud);
					// System.out.println("id====="+allid[i]);
				}
			}
			UserLogicInterface ruserLogic = (UserLogicInterface) getBean("UserLogic");
			HashMap map = ruserLogic.getGroupAccessMap(ud);

			ud.setGroupMap(map);
			request.getSession().setAttribute(SystemConstants.CURRENT_USER_DATA, ud);
			log.info("GroupDispatchAction.saveNotice()结束调用:保存用户信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.saveNotice()保存用户信息,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("creatUserGroup");
	}

	/**
	 * 删除用户信息
	 */
	public org.apache.struts.action.ActionForward deleteGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("GroupDispatchAction.deleteNotice()开始调用：删除用户。");
			UserData ud = getSessionUserData(request);
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			String NoticeIdStrs[] = StringUtils.split(keyStr, '|');
			OaGroupInfoId oid = null;
			for (int i = 0; i < NoticeIdStrs.length; i++) {
				keysList.add(NoticeIdStrs[i]);
			}
			for (int i = 0; i < keysList.size(); i++) {
				// System.out.println("aaaaaaaaaa"+keysList.get(i));
				// 得到Logic
				oid = new OaGroupInfoId();
				// ud.setClientId("HX");
				oid.setClientId(ud.getClientId());
				oid.setGroupId((String) keysList.get(i));
				UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");
				noticeLogic.deleteUser(oid, ud);

			}
			log.info("GroupDispatchAction.deleteNotice()结束调用：删除用户。");
		} catch (Exception e) {
			log.error("GroupDispatchAction.deleteNotice()结束调用：删除用户。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteUserGroup");
	}

}
