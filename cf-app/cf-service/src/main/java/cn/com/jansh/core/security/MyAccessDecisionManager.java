package cn.com.jansh.core.security;

import java.util.Collection;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 * 
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 * 
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * 
 * @PreAuthorize("hasRole('ROLE_SUPER')") <!-- 用户是否拥有所请求资源的权限 -->
 * 
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	/**
	 * 参数说明： 1、configAttributes 装载了请求的url允许的角色数组 。
	 * 这里是从MySecurityMetadataSource里的loadResourceDefine方法里的atts对象取出的角色数据赋予给了configAttributes对象
	 * 2、authentication 装载了从数据库读出来的角色数据。
	 * 这里是从MyUserDetailsService里的loadUserByUsername方法里的auths对象的值传过来给authentication
	 * 对象
	 * 
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (configAttributes == null) {
			return;
		}
		// 所请求的资源拥有的权限(一个资源对多个权限)
		for (ConfigAttribute configAttribute : configAttributes) {
			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();
			// 用户所拥有的权限authentication
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		// 没有权限
		throw new AccessDeniedException("Access Denied");
	}

	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}