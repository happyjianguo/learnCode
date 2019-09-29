package cn.yufu.system.common.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.subject.Subject;

public class UserUtils {
	
	/**
	 * 获取当前用户名称
	 * 
	 */
	public static String getUserName() {
		PrincipalBean principal = getPrincipal();
		if (principal != null) {
			return principal.getName();
		}
		return null;
	}
	
	/**
	 * 获取当前用户有登录名
	 * 
	 */
	public static String getLoginName() {
		PrincipalBean principal = getPrincipal();
		if (principal != null) {
			return principal.getLoginName();
		}
		return null;
	}

	/**
	 * 获取当前登录者对象
	 */
	public static PrincipalBean getPrincipal() {
		try {
			Subject subject = SecurityUtils.getSubject();
			PrincipalBean principal = (PrincipalBean) subject.getPrincipal();
			if (principal != null) {
				return principal;
			}
			// subject.logout();
		} catch (UnavailableSecurityManagerException e) {

		} catch (InvalidSessionException e) {

		}
		return null;
	}

}
