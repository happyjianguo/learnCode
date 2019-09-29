package cn.yufu.posp.usermanager.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ForwardingActionForward;

import cn.yufu.core.common.util.CommonDomain;
import cn.yufu.core.common.util.ConstManager;
import cn.yufu.core.common.util.ConstManagerFactory;
import cn.yufu.core.common.util.CryptoTools;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.common.util.CommonUtil;
import cn.yufu.posp.common.common.util.MD5;
import cn.yufu.posp.common.common.util.SystemConstants;
import cn.yufu.posp.common.common.util.SystemVars;
import cn.yufu.posp.common.domain.model.PageInfoModel;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.common.web.action.OABaseDispatchAction;
import cn.yufu.posp.usermanager.domain.logic.UserGroupLogicInterface;
import cn.yufu.posp.usermanager.domain.logic.UserLogicInterface;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;
import cn.yufu.posp.usermanager.web.form.OaUserForm;

public class UserDispatchAction extends OABaseDispatchAction {

	private static final Log log = LogFactory.getLog("user");
	public static final ConstManager CONST_MANAGER = ConstManagerFactory.getConstManagerImpl();

	public UserDispatchAction() {

	}

	/**
	 * ��ѯ��ʾ�û���Ϣ
	 */
	public org.apache.struts.action.ActionForward queryUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		// ForwardingActionForward redirect = null;
		try {
			log.info("UserDispatchAction.queryUser()��ʼ���ã���ѯ�û���Ϣ�б�");
			UserData ud = getSessionUserData(request);
			// �õ�Logic
			UserLogicInterface noticeLogic = (UserLogicInterface) getBean("UserLogic");

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
				if (sortField.equals("1"))
					pageInfo.setOrderField("userName");
				if (sortField.equals("2"))
					pageInfo.setOrderField("groupId");

				if (sortField.equals("3"))
					pageInfo.setOrderField("status");
			}

			// ���ò�ѯ����
			OaUserInfo queryModel = new OaUserInfo();

			// �û�����
			String userId = request.getParameter("_userId");
			if (userId != null)
				queryModel.setUserId(userId);

			// ��������־��ѯ
			String userName = request.getParameter("_userName");
			if (userName != null)
				queryModel.setUserName(userName);

			// �����˲�ѯ
			// queryModel.setUser_id(ud.getYhid());

			// ȡmodel
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));

			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			// pageInfo=new PageInfoModel();
			request.setAttribute("pageInfoResult", pageInfo);

			// ȡ��·��===============================================================

			ActionForward actionForward = mapping.findForward("showAllUser");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("UserDispatchAction.queryUser()�������ã���ѯ�û���Ϣ�б�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.queryUser()��ѯ�û���Ϣ�б����ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
		// return new ForwardingActionForward(pathForward);
	}

	/**
	 * ��ʾ�½�ҳ��
	 */
	public org.apache.struts.action.ActionForward showcreateUserScreen(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserDispatchAction.showcreateUserScreen()��ʼ����:��ʾ�½�ҳ��	��");

			UserData ud = getSessionUserData(request);
			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();
			OaGroupInfo queryModel = new OaGroupInfo();
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));
			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			request.setAttribute("pageInfoResult", pageInfo.getResultItems());

			List deptVO = CommonUtil.getDepartmentValueBean(ud.getSsdwdm(), ud.getSsdwmc());

			request.setAttribute("deptVO", deptVO);
			log.info("UserDispatchAction.showcreateUserScreen()��������:��ʾ�½�ҳ��	��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.showcreateUserScreen()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("showcreateUser");
	}

	/**
	 * ��ʾ�޸��������
	 */
	public org.apache.struts.action.ActionForward showUserUpdatePwd(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		return mapping.findForward("showUserUpdatePwd");
	}

	/**
	 * �û��޸�����
	 */

	public org.apache.struts.action.ActionForward saveUserPwd(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			// �õ�Logic
			UserLogicInterface logic = (UserLogicInterface) getBean("UserLogic");
			OaUserForm pForm = (OaUserForm) form;
			UserData ud = getSessionUserData(request);
			OaUserInfo model = new OaUserInfo();
			OaUserInfoId id = new OaUserInfoId();
			id.setUserId(ud.getUserId());
			id.setClientId(ud.getClientId());
			model = logic.queryUserByKey(id, ud);
			model.setPassword(MD5.hmacSign(pForm.getPassword()));
			// model.setPassword(pForm.getPassword());
			// System.out.println("��pForm.getPassword()==" + pForm.getPassword()
			// + "��");
			// System.out.println("��model.getPassword()==" + model.getPassword()
			// + "��");
			if (pForm.getPassword() != null && pForm.getPassword() != "" && pForm.getPassword().equals(pForm.getPwdsub())) {
				logic.saveUser(model, ud);
			} else {
				throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
			}

		} catch (Exception e) {
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("saveUserPwd");
	}

	/**
	 * �����µ��û���Ϣ
	 */
	public org.apache.struts.action.ActionForward createUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		ForwardingActionForward redirect = null;
		try {
			log.info("UserDispatchAction.createUser()��ʼ����:�����µ��û���Ϣ��");

			UserData ud = getSessionUserData(request);
			// String Ud="";
			// �õ�Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// �õ�Form
			OaUserForm pForm = (OaUserForm) form;
			// �½�һ��Model
			OaUserInfo noticeModel = new OaUserInfo();

			// ======����������Ϣ�͸���============

			// form��Ϣ���Ƶ� model
			String ssss = pForm.getPasswordExpiry();
			pForm.setPasswordExpiry(null);
			BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setPassword(MD5.hmacSign(pForm.getPassword()));
			noticeModel.setLastPasswordReset(new Date());
			noticeModel.setCreateTimestamp(new Date());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			noticeModel.setPasswordExpiry(df.parse(ssss));
			// noticeModel.setPasswordExpiry(new
			// Date(pForm.getPasswordExpiry()));
			noticeModel.setCreateUser(ud.getUserId());
			// ���Լ�����
			// ud.setClientId("HX");
			noticeModel.setUserType("03");
			noticeModel.setId(new OaUserInfoId(ud.getClientId(), pForm.getUserId()));

			// ��set����

			// �����µ��û���Ϣ
			userLogic.createUser(noticeModel, ud);

			log.info("UserDispatchAction.createUser()��������:�����µ��û���Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.createUser()�����µ��û���Ϣ�����ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_USER_ADD);
		}
		return mapping.findForward("creatUser");
		// return redirectcreatUser;
	}

	/**
	 * ��ʾ�༭�û���Ϣ�Ľ���
	 */
	public org.apache.struts.action.ActionForward queryUserByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String str = "showModifyUser";
		try {
			log.info("UserDispatchAction.queryUserByKey()��ʼ���ã���ʾ�༭�û���Ϣ");
			// �õ�Logic
			UserData ud = getSessionUserData(request);
			// HashMap hm = ud.getGroupMap();
			// if(hm.get("")!=null){}
			// �õ�Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// �õ�Form
			OaUserForm pForm = (OaUserForm) form;
			// �½�һ��Model
			OaUserInfo noticeModel = new OaUserInfo();
			// ud
			// ud.setClientId("HX");

			noticeModel = userLogic.queryUserByKey(new OaUserInfoId(ud.getClientId(), pForm.getId()), ud);
			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			// �õ���ҳ����Ϣ
			PageInfoModel pageInfo = new PageInfoModel();
			OaGroupInfo queryModel = new OaGroupInfo();
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));
			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			List list = pageInfo.getResultItems();
			List list1 = new ArrayList();
			OaGroupInfo ou = null;
			for (int i = 0; i < list.size(); i++) {
				ou = (OaGroupInfo) list.get(i);
				if (ou != null) {
					if (noticeModel.getGroupId().equals(ou.getId().getGroupId())) {
						list1.add(0, ou);
					} else {
						list1.add(ou);
					}
				}
			}
			request.setAttribute("pageInfoResult", list1);
			BeanUtils.copyProperties(pForm, noticeModel);
			// System.out.println(noticeModel.getId().getUserId());
			pForm.setUserId(noticeModel.getId().getUserId());
			pForm.setId(noticeModel.getId().getUserId());

			// request.setAttribute("ModelResult", pForm);

			log.info("UserDispatchAction.queryUserByKey()�������ã���ʾ�༭�û���Ϣ");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.queryUserByKey()���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward(str);
	}

	/**
	 * �����û���Ϣ
	 */
	public org.apache.struts.action.ActionForward saveUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("UserDispatchAction.saveNotice()��ʼ����:�����û���Ϣ��");
			UserData ud = getSessionUserData(request);

			// �õ�Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// �õ�Form
			OaUserForm pForm = (OaUserForm) form;
			// �½�һ��Model
			OaUserInfo noticeModel = new OaUserInfo();

			// ======����������Ϣ�͸���============

			// form��Ϣ���Ƶ� model
			// BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setUserName(pForm.getUserName());
			noticeModel.setGroupId(pForm.getGroupId());
			// "��===��pForm.getPassword()==" + pForm.getPassword() + "��");
			if ("#111111111111111111#".equals(pForm.getPwdsub().trim()))
				noticeModel.setPassword(pForm.getPassword());
			else
				noticeModel.setPassword(MD5.hmacSign(pForm.getPwdsub()));
			noticeModel.setStatus(pForm.getStatus());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			noticeModel.setPasswordExpiry(df.parse(pForm.getPasswordExpiry()));
			// pForm.get
			noticeModel.setId(new OaUserInfoId(ud.getClientId(), pForm.getUserId()));

			if (pForm.getPasswordAttempts() != null)
				noticeModel.setPasswordAttempts(pForm.getPasswordAttempts());
			else
				noticeModel.setPasswordAttempts(0l);

			if (pForm.getPasswordAttemptsLimit() != null)
				noticeModel.setPasswordAttemptsLimit(pForm.getPasswordAttemptsLimit());
			else
				noticeModel.setPasswordAttemptsLimit(9l);

			// �����µ��û���Ϣ
			userLogic.saveUser(noticeModel, ud);

			log.info("UserDispatchAction.saveNotice()��������:�����û���Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.saveNotice()�����û���Ϣ,���ó����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveNotice");
	}

	/**
	 * ɾ���û���Ϣ
	 */
	public org.apache.struts.action.ActionForward deleteUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserDispatchAction.deleteUser()��ʼ���ã�ɾ���û���Ϣ��");
			UserData ud = getSessionUserData(request);
			// ud.setClientId("HX");
			String keyStr = request.getParameter("selectItems");
			List keysList = new ArrayList();
			OaUserInfoId oid = null;
			String NoticeIdStrs[] = StringUtils.split(keyStr, '|');
			for (int i = 0; i < NoticeIdStrs.length; i++) {
				keysList.add(NoticeIdStrs[i]);
			}
			for (int i = 0; i < keysList.size(); i++) {
				// �õ�Logic
				oid = new OaUserInfoId();
				oid.setClientId(ud.getClientId());
				oid.setUserId((String) keysList.get(i));
				UserLogicInterface noticeLogic = (UserLogicInterface) getBean("UserLogic");
				noticeLogic.deleteUser(oid, ud);
			}
			log.info("UserDispatchAction.deleteUser()�������ã�ɾ���û���Ϣ��");
		} catch (Exception e) {
			log.error("UserDispatchAction.deleteUser()�������ã�ɾ���û���Ϣ�������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteUser");
	}

	/**
	 * �˵���ѯ
	 */
	public org.apache.struts.action.ActionForward getMenu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserDispatchAction.getMenu()��ʼ���ã��õ��˵���");
			UserData ud = getSessionUserData(request);
			UserLogicInterface noticeLogic = (UserLogicInterface) getBean("UserLogic");
			List list = noticeLogic.getMenu(ud);
			request.setAttribute("list", list);
			log.info("UserDispatchAction.getMenu()���� ���ã��õ��˵���");
		} catch (Exception e) {
			log.error("UserDispatchAction.getMenu()�������ã��õ��˵��������쳣��(" + e.getMessage() + ")��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("tree");
	}

	/**
	 * ��¼����
	 */

	public org.apache.struts.action.ActionForward userLogin(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		String str = "sucess";
		try {
			log.info("UserDispatchAction.userLogin()��ʼ���ã��û���¼��");
			// �õ�Logic
			UserData ud = new UserData();

			// �õ�Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// �õ�Form
			OaUserForm pForm = (OaUserForm) form;
			// �½�һ��Model
			OaUserInfo noticeModel = new OaUserInfo();
			// ud
			ud.setClientId(pForm.getClientId());

			noticeModel = userLogic.queryUserByKey(new OaUserInfoId(ud.getClientId(), pForm.getUserId()), ud);
			// System.out.println(" noticeModel:" + noticeModel);
			// �����ڿվ������ܲ���û�
			if (noticeModel != null) {
				// ������֤����
				if ("YES".equals(CommonDomain.CONST_CHECK_KOULING)) {
					// 0100|07000|ʱ��|��Ա��|��̬����|�̻���|�̻�����
					// key�ã�112233Fdefw0iqRoPUyG4Mv3UmcgYrWo
					// �������ݣ�0100|07000|ʱ��|������|������ϸ��Ϣ
					String inParam = "0100|07000|20140821|654321|123456|000000000000005|1234";
					log.info("webservices���ö�̬����ӿ�");
					inParam = CryptoTools.encryptMode("112233Fdefw0iqRoPUyG4Mv3UmcgYrWo", inParam);
					// ���� webservice
					// ����һ������(service)����(call)
					Service service = new Service();
					Call call = (Call) service.createCall();// ͨ��service����call����
					// ����service����URL
					call.setTargetEndpointAddress(new java.net.URL(CommonDomain.CONST_CHECK_URL));

					call.setOperationName(new QName(CommonDomain.CONST_CHECK_SOAPACTION, CommonDomain.CONST_CHECK_OPERATION));
					// Add ��net �Ǳߵķ��� "http://tempuri.org/" ���ҲҪע��Namespace
					// �ĵ�ַ,����Ҳ�ᱨ��
					call.addParameter(new QName(CommonDomain.CONST_CHECK_SOAPACTION, "InData"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
					// ������Ҹ���һ���ԭ������,"test" ������Ǵ������ı���,Ҳ����NET����Ĳ���,һ����Ҫ������
					// �ҵ�����֪�� ,��Ϊ����������Լ����д��,������Ǳ�NULL,������������
					call.setUseSOAPAction(true);
					call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
					// ���ز���������
					call.setSOAPActionURI(CommonDomain.CONST_CHECK_SOAPACTION + CommonDomain.CONST_CHECK_OPERATION); // ���ҲҪע��
					// ����Ҫ����Ҫ���õķ���Add,��ȻҲ�ᱨ��

					call.setTimeout(new Integer(30000));
					// Object �����װ�˲���������Ϊ"This is Test!",����processService(String
					// arg)
					String ret = (String) call.invoke(new Object[]{inParam});
					ret = CryptoTools.encryptMode("112233Fdefw0iqRoPUyG4Mv3UmcgYrWo", ret);
					request.getSession().setAttribute("error", ret);
					str = "error";
				}
				// ������ͬ
				if (MD5.hmacSign(pForm.getPassword()).equals(noticeModel.getPassword())) {
					// ������������ڵ�ǰ����֮ǰ
					if (noticeModel.getPasswordExpiry().before(new Date())) {
						request.getSession().setAttribute("error", "�û��ѹ��ڣ�");
						str = "error";
					}
					// �û�δ����
					else if (!"A".equals(noticeModel.getStatus())) {
						request.getSession().setAttribute("error", "�û�δ���ã�");
						str = "error";
					} else {
						ud.setGroupId(noticeModel.getGroupId());
						ud.setUserName(noticeModel.getUserName());
						ud.setUserId(pForm.getUserId());
						// List list = userLogic.getGroupAccess(ud);
						HashMap map = userLogic.getGroupAccessMap(ud);
						ud.setGroupMap(map);
						request.getSession().setAttribute(SystemConstants.CURRENT_USER_DATA, ud);
						request.getSession().removeAttribute("error");
						str = "sucess";
					}
				} else {
					request.getSession().setAttribute("error", "�û��������");
					str = "error";
				}
			} else {
				request.getSession().setAttribute("error", "�û������ڣ�");
				str = "error";
			}
			log.info("UserDispatchAction.userLogin()�������ã��û���¼��");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.userLogin().��½�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(str);
	}

	/**
	 * �ǳ�����
	 */
	public org.apache.struts.action.ActionForward userLogout(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		request.getSession().setAttribute(SystemConstants.CURRENT_USER_DATA, new UserData());
		request.getSession().removeAttribute("error");
		return mapping.findForward("error");
	}

	/**
	 * �µ�¼
	 */
	public org.apache.struts.action.ActionForward login(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		UserData ud = new UserData();
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "�û���/�������";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "�û���/�������";
		} else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
			error = "�û���Ȩ����";
		} else if (exceptionClassName != null) {
			error = "��������" + exceptionClassName;
		} else {
			Subject currentUser = SecurityUtils.getSubject();
			if (currentUser.isAuthenticated()) {
				UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
				OaUserInfo userInfo = userLogic.queryUserByKey(new OaUserInfoId("HX", (String) currentUser.getPrincipal()), null);
				ud.setGroupId(userInfo.getGroupId());
				ud.setClientId(userInfo.getId().getClientId());
				ud.setUserName(userInfo.getUserName());
				ud.setUserId((String) currentUser.getPrincipal());
				HashMap map = userLogic.getGroupAccessMap(ud);
				ud.setGroupMap(map);
				request.getSession().setAttribute(SystemConstants.CURRENT_USER_DATA, ud);
				request.getSession().removeAttribute("error");
				return mapping.findForward("sucess");
			}
		}
		request.getSession().setAttribute("error", error);
		return mapping.findForward("error");
	}
}
