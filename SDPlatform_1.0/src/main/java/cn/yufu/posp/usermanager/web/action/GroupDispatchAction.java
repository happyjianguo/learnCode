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
	 * ��ѯ��ʾ�û���Ϣ
	 */
	public org.apache.struts.action.ActionForward queryUserGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		// ForwardingActionForward redirect = null;
		try {
			log.info("GroupDispatchAction.queryNotice()��ʼ���ã���ѯ�û���Ϣ�б�");
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();

			String dParams[] = getDisplayParams();

			// ���õ�ǰҳ��
			int cPage = 1;
			if (request.getParameter(dParams[0]) != null && !"null".equals(request.getParameter(dParams[0])) && !"".equals(request.getParameter(dParams[0]))) {

				cPage = Integer.parseInt(request.getParameter(dParams[0]));
				pageInfo.setCurrentPage(cPage);
			}

			// ��������ʽ
			String orderType = request.getParameter(dParams[1]);
			if (orderType != null) {
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_ASC))
					pageInfo.setOrderType("asc");
				if (orderType.equals(SystemVars.DISPLAYTAG_ORDER_DESC))
					pageInfo.setOrderType("desc");
			}

			// ���������ֶ�
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

			// ���ò�ѯ����
			OaGroupInfo queryModel = new OaGroupInfo();

			// // ��ʼʱ��
			// String sStartDate = request.getParameter("searchStartDate");
			// if(StringUtils.isNotEmpty(sStartDate))
			// {
			// Date startDate = DateTimeUtil.parse(sStartDate);
			// queryModel.setStartDate(startDate);
			// }
			//
			// //����ʱ��
			// String sEndDate = request.getParameter("searchEndDate");
			// if(StringUtils.isNotEmpty(sEndDate))
			// {
			// Date endDate = DateTimeUtil.parse(sEndDate);
			// queryModel.setEndDate(endDate);
			// }
			//					
			// �û�����
			String groupId = request.getParameter("_groupId");
			if (groupId != null)
				queryModel.setCreateUser(groupId);
			//					
			// // ��������־��ѯ
			// String search_updata_flag =
			// request.getParameter("search_updata_flag");
			// if (search_updata_flag != null)
			// queryModel.setUpdata_flag(search_updata_flag);
			//					
			// �����˲�ѯ
			// queryModel.setUser_id(ud.getYhid());

			// ȡmodel
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));
			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��===============================================================

			ActionForward actionForward = mapping.findForward("showAllUserGroup");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("GroupDispatchAction.queryNotice()�������ã���ѯ�û���Ϣ�б�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.queryNotice()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
	}

	/**
	 * ��ʾ�½�ҳ��
	 */
	public org.apache.struts.action.ActionForward showcreateGroupScreen(org.apache.struts.action.ActionMapping mapping,
			org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
			throws OAException {
		try {
			log.info("GroupDispatchAction. showcreateGroupScreen()��ʼ����:��ʾ�½�ҳ��	��");

			UserData ud = getSessionUserData(request);

			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			String str = noticeLogic.getMenu(ud);
			request.setAttribute("allmenu", str);
			// System.out.println(str);
			List deptVO = CommonUtil.getDepartmentValueBean(ud.getSsdwdm(), ud.getSsdwmc());

			request.setAttribute("deptVO", deptVO);

			log.info("GroupDispatchAction. showInitialInputCollectAccidentScreen()��������:��ʾ�½�ҳ��	��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CollectAccidentDispatchAction.showInitialInputCollectAccidentScreen()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("showcreateUserGroup");
	}

	/**
	 * �����µ��û���Ϣ
	 */
	public org.apache.struts.action.ActionForward createGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		ForwardingActionForward redirect = null;
		try {
			log.info("GroupDispatchAction.createNotice()��ʼ����:�����µ��û���Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			UserGroupLogicInterface userLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");
			// �õ�Form
			OaGroupForm pForm = (OaGroupForm) form;
			OaGroupInfo noticeModel = new OaGroupInfo();

			// form��Ϣ���Ƶ� model
			BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setUpdateTimestamp(new Date());
			noticeModel.setCreateTimestamp(new Date());
			// noticeModel.set
			// ���Լ�����
			// ud.setClientId("HX");

			noticeModel.setId(new OaGroupInfoId(ud.getClientId(), pForm.getGroupId()));

			// ��set����

			// �����µ��û���Ϣ
			userLogic.createUser(noticeModel, ud);
			// �õ�Ȩ���б�
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

			log.info("UserGroupDispatchAction.createGroup()��������:�����µĽ�ɫ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.createNotice()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_GROUP_ADD);
		}
		return mapping.findForward("creatUserGroup");
	}

	/**
	 * ��ʾ�༭�û���Ϣ�Ľ���
	 */
	public org.apache.struts.action.ActionForward queryGroupByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserGroupDispatchAction.queryGroupByKey()��ʼ���ã���ʾ�༭��ɫ��Ϣ");
			// �õ�Logic
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
			log.info("UserGroupDispatchAction.queryGroupByKey()�������ã���ʾ�༭��ɫ��Ϣ");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.queryNoticeByKey()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("showModifyUserGroup");
	}

	/**
	 *�����û���Ϣ
	 */
	public org.apache.struts.action.ActionForward saveGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("GroupDispatchAction.saveNotice()��ʼ����:�����û���Ϣ��");

			UserData ud = getSessionUserData(request);

			// �õ�Logic
			UserGroupLogicInterface userLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");
			// �õ�Form
			OaGroupForm pForm = (OaGroupForm) form;
			OaGroupInfo noticeModel = new OaGroupInfo();

			// form��Ϣ���Ƶ� model
			// BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setGroupDescription(pForm.getGroupDescription());
			noticeModel.setUpdateUser(ud.getUserId());
			noticeModel.setUpdateTimestamp(new Date());
			noticeModel.setCreateTimestamp(new Date());

			// ���Լ�����
			// ud.setClientId("HX");

			noticeModel.setId(new OaGroupInfoId(ud.getClientId(), pForm.getGroupId()));

			// ��set����

			// �����µ��û���Ϣ
			userLogic.saveUser(noticeModel, ud);
			// �õ�Ȩ���б�
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
			log.info("GroupDispatchAction.saveNotice()��������:�����û���Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("GroupDispatchAction.saveNotice()�����û���Ϣ,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("creatUserGroup");
	}

	/**
	 * ɾ���û���Ϣ
	 */
	public org.apache.struts.action.ActionForward deleteGroup(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("GroupDispatchAction.deleteNotice()��ʼ���ã�ɾ���û���");
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
				// �õ�Logic
				oid = new OaGroupInfoId();
				// ud.setClientId("HX");
				oid.setClientId(ud.getClientId());
				oid.setGroupId((String) keysList.get(i));
				UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");
				noticeLogic.deleteUser(oid, ud);

			}
			log.info("GroupDispatchAction.deleteNotice()�������ã�ɾ���û���");
		} catch (Exception e) {
			log.error("GroupDispatchAction.deleteNotice()�������ã�ɾ���û��������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteUserGroup");
	}

}
