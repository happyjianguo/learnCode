/**
 * Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.security;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.axis2.AxisFault;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.xerces.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import cn.yufu.system.common.config.Global;
import cn.yufu.system.common.servlet.ValidateCodeServlet;
import cn.yufu.system.common.shiro.PrincipalBean;
import cn.yufu.system.common.utils.Encodes;
import cn.yufu.system.common.utils.SpringContextHolder;
import cn.yufu.system.common.web.Servlets;
import cn.yufu.system.modules.sys.entity.Menu;
import cn.yufu.system.modules.sys.entity.Role;
import cn.yufu.system.modules.sys.entity.User;
import cn.yufu.system.modules.sys.service.SystemService;
import cn.yufu.system.modules.sys.utils.LogUtils;
import cn.yufu.system.modules.sys.utils.UserUtils;
import cn.yufu.system.modules.sys.web.LoginController;

/**
 * 系统安全认证实现类
 * 
 * @author king
 * @version 2014-7-5
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private SystemService systemService;

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		int activeSessionSize = getSystemService().getSessionDao()
				.getActiveSessions(false).size();
		if (logger.isDebugEnabled()) {
			logger.debug("login submit, active session size: {}, username: {}",
					activeSessionSize, token.getUsername());
		}

		// 校验登录验证码
		if (LoginController.isValidateCodeLogin(token.getUsername(), false,
				false)) {
			Session session = UserUtils.getSession();
			String code = (String) session
					.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
			if (token.getCaptcha() == null
					|| !token.getCaptcha().toUpperCase().equals(code)) {
				throw new AuthenticationException("msg:验证码错误, 请重试.");
			}
		}

		// 校验登录动态口令(关键处)
		String dynamicPassword_flag = Global.getConfig("dynamicPassword_flag");
		if (!"system".equalsIgnoreCase(token.getUsername())) {
			if ("YES".equals(dynamicPassword_flag)) {
				String dynamicPassword = token.getDynamicpassword();
				String usercode = token.getUsername();
				String dpStr = "0100|07000|"
						+ new java.text.SimpleDateFormat("yyyyMMddHHmmss")
								.format(new java.util.Date()) + "|" + usercode
						+ "|" + dynamicPassword + "|111111111111115|222333";

				byte[] key;
				try {
					key = new BASE64Decoder().decodeBuffer(Global
							.getConfig("dynamicPassword_miyao"));
					byte[] data = dpStr.getBytes("UTF-8");

					// System.out.println("ECB加密解密:");
					byte[] str3 = Des3.des3EncodeECB(key, data);
					// System.out.println(new BASE64Encoder().encode(str3));
					dpStr = new BASE64Encoder().encode(str3);
					// System.out.println("*****************************************");
				} catch (Exception e) {
					e.printStackTrace();
				}

				String ws_ret = "";
				try {
					ws_ret = AxisClientOfDynamicPswd.CltCall2("CheckIkeyCode",
							Global.getConfig("dynamicPassword_ws_url"), Global
									.getConfig("sales_ws_ns"), dpStr);
					// System.out.println("ws_ret:" + ws_ret);
				} catch (AxisFault e) {
					e.printStackTrace();
				}

				byte[] str4;
				String responseStr = "";
				try {
					key = new BASE64Decoder().decodeBuffer(Global
							.getConfig("dynamicPassword_miyao"));
					str4 = Des3.ees3DecodeECB(key, Base64.decode(ws_ret));
					responseStr = new String(str4, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (responseStr != null && !"".equals(responseStr)
						&& responseStr.contains("|")) {
					String[] respStrList = responseStr.split("\\|");
					if (respStrList.length == 5) {
						String respCode = respStrList[3];
						if (respCode == null || !"0".equals(respCode)) {
							throw new LockedAuthenticationException(
									"msg:动态口令错误, 请重试.");
						}
					} else {
						throw new LockedAuthenticationException(
								"msg:动态口令错误, 请重试.");
					}
				} else {
					throw new LockedAuthenticationException("msg:动态口令错误, 请重试.");
				}
			}
		}

		// 校验用户名密码
		User user = getSystemService().getUserByLoginName(token.getUsername());
		if (user != null) {
			if (Global.NO.equals(user.getLoginFlag())) {
				throw new AuthenticationException("msg:该已帐号禁止登录.");
			}
			byte[] salt = Encodes
					.decodeHex(user.getPassword().substring(0, 16));
			return new SimpleAuthenticationInfo(new PrincipalBean(user, token
					.isMobileLogin()), user.getPassword().substring(16),
					ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		PrincipalBean principal = (PrincipalBean) getAvailablePrincipal(principals);
		// 获取当前已登录的用户
		/*if (!Global.TRUE.equalsIgnoreCase(Global.getConfig("user.multiAccountLogin"))) {
			Collection<Session> sessions = getSystemService().getSessionDao()
					.getActiveSessions(true, principal, UserUtils.getSession());
			if (sessions.size() > 0) {
				// 如果是登录进来的，则踢出已在线用户
				if (UserUtils.getSubject().isAuthenticated()) {
					for (Session session : sessions) {
						getSystemService().getSessionDao().delete(session);
					}
				}
				// 记住我进来的，并且当前用户已登录，则退出当前用户提示信息。
				//else {
					UserUtils.getSubject().logout();
					throw new AuthenticationException("msg:该账户已在其他地方登录，请您稍后再试！");
				//}
			}
		}*/
		User user = getSystemService().getUserByLoginName(
				principal.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Menu> list = UserUtils.getMenuList();
			for (Menu menu : list) {
				if (StringUtils.isNotBlank(menu.getPermission())) {
					// 添加基于Permission的权限信息
					for (String permission : StringUtils.split(menu
							.getPermission(), ",")) {
						info.addStringPermission(permission);
					}
				}
			}
			// 添加用户权限
			info.addStringPermission("user");
			// 添加用户角色信息
			for (Role role : user.getRoleList()) {
				info.addRole(role.getEnname());
			}
			// 更新登录IP和时间
			getSystemService().updateUserLoginInfo(user);
			// 记录登录日志
			LogUtils.saveLog(Servlets.getRequest(), "系统登录");
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 验证用户名和密码 更新用户登录失败次数
	 */
	protected void assertCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) throws AuthenticationException {
		CredentialsMatcher cm = getCredentialsMatcher();
		User user = null;
		if (cm != null) {
			if (!cm.doCredentialsMatch(token, info)) {
				// not successful - throw an exception to indicate this:
				String msg = "Submitted credentials for token [" + token
						+ "] did not match the expected credentials.";
				// 密码错误次数加1
				PrincipalBean p = (PrincipalBean) info.getPrincipals()
						.getPrimaryPrincipal();
				user = p.getUser();
				if (p != null && !"system".equals(p.getLoginName())) {

					int loginFailNum = user.getLoginFailNum() + 1;
					if (user != null) {
						user.setLoginFailNum(loginFailNum);
					}
				}
				systemService.saveUser(user);
				throw new IncorrectCredentialsException(msg);
			}
			// 密码错误次数清零
			// user.setLoginFailNum(0);
			// systemService.saveUser(user);
		} else {
			throw new AuthenticationException(
					"A CredentialsMatcher must be configured in order to verify "
							+ "credentials during authentication.  If you do not wish for credentials to be examined, you "
							+ "can configure an "
							+ AllowAllCredentialsMatcher.class.getName()
							+ " instance.");
		}
	}

	@Override
	protected void checkPermission(Permission permission, AuthorizationInfo info) {
		authorizationValidate(permission);
		super.checkPermission(permission, info);
	}

	@Override
	protected boolean[] isPermitted(List<Permission> permissions,
			AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermitted(permissions, info);
	}

	@Override
	public boolean isPermitted(PrincipalCollection principals,
			Permission permission) {
		authorizationValidate(permission);
		return super.isPermitted(principals, permission);
	}

	@Override
	protected boolean isPermittedAll(Collection<Permission> permissions,
			AuthorizationInfo info) {
		if (permissions != null && !permissions.isEmpty()) {
			for (Permission permission : permissions) {
				authorizationValidate(permission);
			}
		}
		return super.isPermittedAll(permissions, info);
	}

	/**
	 * 授权验证方法
	 * 
	 * @param permission
	 */
	private void authorizationValidate(Permission permission) {
		// 模块授权预留接口
	}

	/**
	 * 设定密码校验的Hash算法与迭代次数
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(
				SystemService.HASH_ALGORITHM);
		matcher.setHashIterations(SystemService.HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	// /**
	// * 清空用户关联权限认证，待下次使用时重新加载
	// */
	// public void clearCachedAuthorizationInfo(Principal principal) {
	// SimplePrincipalCollection principals = new
	// SimplePrincipalCollection(principal, getName());
	// clearCachedAuthorizationInfo(principals);
	// }

	/**
	 * 清空所有关联认证
	 * 
	 * @Deprecated 不需要清空，授权缓存保存到session中
	 */
	@Deprecated
	public void clearAllCachedAuthorizationInfo() {
		// Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		// if (cache != null) {
		// for (Object key : cache.keys()) {
		// cache.remove(key);
		// }
		// }
	}

	/**
	 * 获取系统业务对象
	 */
	public SystemService getSystemService() {
		if (systemService == null) {
			systemService = SpringContextHolder.getBean(SystemService.class);
		}
		return systemService;
	}

}
