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
	 * 查询显示用户信息
	 */
	public org.apache.struts.action.ActionForward queryUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		String pathForward = "";
		// ForwardingActionForward redirect = null;
		try {
			log.info("UserDispatchAction.queryUser()开始调用：查询用户信息列表。");
			UserData ud = getSessionUserData(request);
			// 得到Logic
			UserLogicInterface noticeLogic = (UserLogicInterface) getBean("UserLogic");

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
				if (sortField.equals("1"))
					pageInfo.setOrderField("userName");
				if (sortField.equals("2"))
					pageInfo.setOrderField("groupId");

				if (sortField.equals("3"))
					pageInfo.setOrderField("status");
			}

			// 设置查询条件
			OaUserInfo queryModel = new OaUserInfo();

			// 用户标题
			String userId = request.getParameter("_userId");
			if (userId != null)
				queryModel.setUserId(userId);

			// 按发布标志查询
			String userName = request.getParameter("_userName");
			if (userName != null)
				queryModel.setUserName(userName);

			// 按个人查询
			// queryModel.setUser_id(ud.getYhid());

			// 取model
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));

			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			// pageInfo=new PageInfoModel();
			request.setAttribute("pageInfoResult", pageInfo);

			// 取得路径===============================================================

			ActionForward actionForward = mapping.findForward("showAllUser");
			String fPath = actionForward.getPath();
			pathForward = fPath + "?" + dParams[0] + "=" + pageInfo.getCurrentPage();

			log.info("UserDispatchAction.queryUser()结束调用：查询用户信息列表。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.queryUser()查询用户信息列表，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return new ForwardingActionForward(pathForward);
		// return new ForwardingActionForward(pathForward);
	}

	/**
	 * 显示新建页面
	 */
	public org.apache.struts.action.ActionForward showcreateUserScreen(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserDispatchAction.showcreateUserScreen()开始调用:显示新建页面	。");

			UserData ud = getSessionUserData(request);
			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			// 得到分页的信息
			PageInfoModel pageInfo = new PageInfoModel();
			OaGroupInfo queryModel = new OaGroupInfo();
			HashMap pageMap = noticeLogic.queryUser(queryModel, pageInfo, getSessionUserData(request));
			pageInfo = (PageInfoModel) pageMap.get("pageInfo");
			request.setAttribute("pageInfoResult", pageInfo.getResultItems());

			List deptVO = CommonUtil.getDepartmentValueBean(ud.getSsdwdm(), ud.getSsdwmc());

			request.setAttribute("deptVO", deptVO);
			log.info("UserDispatchAction.showcreateUserScreen()结束调用:显示新建页面	。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.showcreateUserScreen()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("showcreateUser");
	}

	/**
	 * 显示修改密码界面
	 */
	public org.apache.struts.action.ActionForward showUserUpdatePwd(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		return mapping.findForward("showUserUpdatePwd");
	}

	/**
	 * 用户修改密码
	 */

	public org.apache.struts.action.ActionForward saveUserPwd(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			// 得到Logic
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
			// System.out.println("【pForm.getPassword()==" + pForm.getPassword()
			// + "】");
			// System.out.println("【model.getPassword()==" + model.getPassword()
			// + "】");
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
	 * 创建新的用户信息
	 */
	public org.apache.struts.action.ActionForward createUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		ForwardingActionForward redirect = null;
		try {
			log.info("UserDispatchAction.createUser()开始调用:创建新的用户信息。");

			UserData ud = getSessionUserData(request);
			// String Ud="";
			// 得到Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// 得到Form
			OaUserForm pForm = (OaUserForm) form;
			// 新建一个Model
			OaUserInfo noticeModel = new OaUserInfo();

			// ======创建附件信息和附件============

			// form信息复制到 model
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
			// 测试假数据
			// ud.setClientId("HX");
			noticeModel.setUserType("03");
			noticeModel.setId(new OaUserInfoId(ud.getClientId(), pForm.getUserId()));

			// 单set日期

			// 创建新的用户信息
			userLogic.createUser(noticeModel, ud);

			log.info("UserDispatchAction.createUser()结束调用:创建新的用户信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.createUser()创建新的用户信息，调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_USER_ADD);
		}
		return mapping.findForward("creatUser");
		// return redirectcreatUser;
	}

	/**
	 * 显示编辑用户信息的界面
	 */
	public org.apache.struts.action.ActionForward queryUserByKey(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form,
			javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws OAException {
		String str = "showModifyUser";
		try {
			log.info("UserDispatchAction.queryUserByKey()开始调用：显示编辑用户信息");
			// 得到Logic
			UserData ud = getSessionUserData(request);
			// HashMap hm = ud.getGroupMap();
			// if(hm.get("")!=null){}
			// 得到Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// 得到Form
			OaUserForm pForm = (OaUserForm) form;
			// 新建一个Model
			OaUserInfo noticeModel = new OaUserInfo();
			// ud
			// ud.setClientId("HX");

			noticeModel = userLogic.queryUserByKey(new OaUserInfoId(ud.getClientId(), pForm.getId()), ud);
			UserGroupLogicInterface noticeLogic = (UserGroupLogicInterface) getBean("UserGroupLogic");

			// 得到分页的信息
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

			log.info("UserDispatchAction.queryUserByKey()结束调用：显示编辑用户信息");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.queryUserByKey()调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward(str);
	}

	/**
	 * 保存用户信息
	 */
	public org.apache.struts.action.ActionForward saveUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {

		try {
			log.info("UserDispatchAction.saveNotice()开始调用:保存用户信息。");
			UserData ud = getSessionUserData(request);

			// 得到Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// 得到Form
			OaUserForm pForm = (OaUserForm) form;
			// 新建一个Model
			OaUserInfo noticeModel = new OaUserInfo();

			// ======创建附件信息和附件============

			// form信息复制到 model
			// BeanUtils.copyProperties(noticeModel, pForm);
			noticeModel.setUserName(pForm.getUserName());
			noticeModel.setGroupId(pForm.getGroupId());
			// "】===【pForm.getPassword()==" + pForm.getPassword() + "】");
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

			// 创建新的用户信息
			userLogic.saveUser(noticeModel, ud);

			log.info("UserDispatchAction.saveNotice()结束调用:保存用户信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.saveNotice()保存用户信息,调用出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward("saveNotice");
	}

	/**
	 * 删除用户信息
	 */
	public org.apache.struts.action.ActionForward deleteUser(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserDispatchAction.deleteUser()开始调用：删除用户信息。");
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
				// 得到Logic
				oid = new OaUserInfoId();
				oid.setClientId(ud.getClientId());
				oid.setUserId((String) keysList.get(i));
				UserLogicInterface noticeLogic = (UserLogicInterface) getBean("UserLogic");
				noticeLogic.deleteUser(oid, ud);
			}
			log.info("UserDispatchAction.deleteUser()结束调用：删除用户信息。");
		} catch (Exception e) {
			log.error("UserDispatchAction.deleteUser()结束调用：删除用户信息。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("deleteUser");
	}

	/**
	 * 菜单查询
	 */
	public org.apache.struts.action.ActionForward getMenu(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		try {
			log.info("UserDispatchAction.getMenu()开始调用：得到菜单。");
			UserData ud = getSessionUserData(request);
			UserLogicInterface noticeLogic = (UserLogicInterface) getBean("UserLogic");
			List list = noticeLogic.getMenu(ud);
			request.setAttribute("list", list);
			log.info("UserDispatchAction.getMenu()结束 调用：得到菜单。");
		} catch (Exception e) {
			log.error("UserDispatchAction.getMenu()结束调用：得到菜单。出现异常！(" + e.getMessage() + ")。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}
		return mapping.findForward("tree");
	}

	/**
	 * 登录方法
	 */

	public org.apache.struts.action.ActionForward userLogin(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		String str = "sucess";
		try {
			log.info("UserDispatchAction.userLogin()开始调用：用户登录。");
			// 得到Logic
			UserData ud = new UserData();

			// 得到Logic
			UserLogicInterface userLogic = (UserLogicInterface) getBean("UserLogic");
			// 得到Form
			OaUserForm pForm = (OaUserForm) form;
			// 新建一个Model
			OaUserInfo noticeModel = new OaUserInfo();
			// ud
			ud.setClientId(pForm.getClientId());

			noticeModel = userLogic.queryUserByKey(new OaUserInfoId(ud.getClientId(), pForm.getUserId()), ud);
			// System.out.println(" noticeModel:" + noticeModel);
			// 不等于空就是你能查出用户
			if (noticeModel != null) {
				// 口令认证开关
				if ("YES".equals(CommonDomain.CONST_CHECK_KOULING)) {
					// 0100|07000|时间|会员号|动态口令|商户号|商户密码
					// key用；112233Fdefw0iqRoPUyG4Mv3UmcgYrWo
					// 返回数据：0100|07000|时间|返回码|返回详细信息
					String inParam = "0100|07000|20140821|654321|123456|000000000000005|1234";
					log.info("webservices调用动态口令接口");
					inParam = CryptoTools.encryptMode("112233Fdefw0iqRoPUyG4Mv3UmcgYrWo", inParam);
					// 调用 webservice
					// 创建一个服务(service)调用(call)
					Service service = new Service();
					Call call = (Call) service.createCall();// 通过service创建call对象
					// 设置service所在URL
					call.setTargetEndpointAddress(new java.net.URL(CommonDomain.CONST_CHECK_URL));

					call.setOperationName(new QName(CommonDomain.CONST_CHECK_SOAPACTION, CommonDomain.CONST_CHECK_OPERATION));
					// Add 是net 那边的方法 "http://tempuri.org/" 这个也要注意Namespace
					// 的地址,不带也会报错
					call.addParameter(new QName(CommonDomain.CONST_CHECK_SOAPACTION, "InData"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
					// 这就是我搞了一天的原因所在,"test" 这个就是传参数的变量,也就是NET方面的参数,一定不要带错了
					// 我当初不知道 ,以为这个参数是自己随便写的,结果总是报NULL,真是气死人了
					call.setUseSOAPAction(true);
					call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
					// 返回参数的类型
					call.setSOAPActionURI(CommonDomain.CONST_CHECK_SOAPACTION + CommonDomain.CONST_CHECK_OPERATION); // 这个也要注意
					// 就是要加上要调用的方法Add,不然也会报错

					call.setTimeout(new Integer(30000));
					// Object 数组封装了参数，参数为"This is Test!",调用processService(String
					// arg)
					String ret = (String) call.invoke(new Object[]{inParam});
					ret = CryptoTools.encryptMode("112233Fdefw0iqRoPUyG4Mv3UmcgYrWo", ret);
					request.getSession().setAttribute("error", ret);
					str = "error";
				}
				// 密码相同
				if (MD5.hmacSign(pForm.getPassword()).equals(noticeModel.getPassword())) {
					// 如果过期日期在当前日期之前
					if (noticeModel.getPasswordExpiry().before(new Date())) {
						request.getSession().setAttribute("error", "用户已过期！");
						str = "error";
					}
					// 用户未启用
					else if (!"A".equals(noticeModel.getStatus())) {
						request.getSession().setAttribute("error", "用户未启用！");
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
					request.getSession().setAttribute("error", "用户密码错误！");
					str = "error";
				}
			} else {
				request.getSession().setAttribute("error", "用户不存在！");
				str = "error";
			}
			log.info("UserDispatchAction.userLogin()结束调用：用户登录。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDispatchAction.userLogin().登陆出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_STRUTSACTION);
		}

		return mapping.findForward(str);
	}

	/**
	 * 登出方法
	 */
	public org.apache.struts.action.ActionForward userLogout(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		request.getSession().setAttribute(SystemConstants.CURRENT_USER_DATA, new UserData());
		request.getSession().removeAttribute("error");
		return mapping.findForward("error");
	}

	/**
	 * 新登录
	 */
	public org.apache.struts.action.ActionForward login(org.apache.struts.action.ActionMapping mapping, org.apache.struts.action.ActionForm form, javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response) throws OAException {
		String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
		UserData ud = new UserData();
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误。";
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = "用户名/密码错误。";
		} else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
			error = "用户授权错误。";
		} else if (exceptionClassName != null) {
			error = "其他错误：" + exceptionClassName;
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
