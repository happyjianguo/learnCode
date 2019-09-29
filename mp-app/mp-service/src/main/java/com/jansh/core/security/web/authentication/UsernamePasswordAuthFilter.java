package com.jansh.core.security.web.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

/**
 * 登录认证
 * 
 * @author nie
 *
 */
public class UsernamePasswordAuthFilter extends AbstractAuthenticationProcessingFilter {

	private static final Logger logger = LogManager.getLogger(UsernamePasswordAuthFilter.class);

	public static final String ONE_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String ONE_SECURITY_FORM_PASSWORD_KEY = "password";

	private String usernameParameter = ONE_SECURITY_FORM_USERNAME_KEY;
	private String passwordParameter = ONE_SECURITY_FORM_PASSWORD_KEY;

	private boolean postOnly = true;

	public UsernamePasswordAuthFilter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(messages.getMessage("authenticationmethod.post",
					"Authentication method not supported: " + request.getMethod()));
		}

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		logger.info("username {} login", username);
		// 验证otppwd
		// String otppwd = request.getParameter("otppwd");
		// int resultCode = oTPAuth.auth(username, otppwd);
		// if(resultCode != 0){
		// throw new
		// BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badotppwd",
		// "动态密码错误"));
		// }
		// if(StringUtils.isBlank(checking)||StringUtils.isBlank(code)||
		// !checking.equals(code)){
		// throw new
		// BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badCaptcha",
		// "验证码错误"));
		// }

		// String checking = request.getParameter("checking");
		// String code = (String)
		// request.getSession().getAttribute("IMAGECODE");
		// if(StringUtils.isBlank(checking)||StringUtils.isBlank(code)||
		// !checking.equals(code)){
		// throw new
		// BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badCaptcha",
		// "验证码错误"));
		// }

		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	protected String obtainPassword(HttpServletRequest request) {
		String password = request.getParameter(passwordParameter);
		return password == null ? "" : password;
	}

	protected String obtainUsername(HttpServletRequest request) {
		String username = request.getParameter(usernameParameter);
		return username == null ? "" : username;
	}

	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsernameParameter() {
		return usernameParameter;
	}

	public final String getPasswordParameter() {
		return passwordParameter;
	}

	/**
	 * successHandlerUrl
	 * 
	 * @param defaultTargetUrl
	 */
	// public void setSuccessHandlerUrl(String defaultTargetUrl) {
	// Assert.notNull(defaultTargetUrl, "successHandlerUrl cannot be null");
	// SavedRequestAwareAuthenticationSuccessHandler successHandler = new
	// SavedRequestAwareAuthenticationSuccessHandler();
	// successHandler.setDefaultTargetUrl(defaultTargetUrl);
	// super.setAuthenticationSuccessHandler(successHandler);
	// }

	/**
	 * successHandlerUrl
	 * 
	 * @param defaultTargetUrl
	 */
	// public void setFailureHandlerUrl(String failureHandlerUrl) {
	// Assert.notNull(failureHandlerUrl, "failureHandlerUrl cannot be null");
	// SimpleUrlAuthenticationFailureHandler failureHandler = new
	// SimpleUrlAuthenticationFailureHandler(
	// failureHandlerUrl);
	// super.setAuthenticationFailureHandler(failureHandler);
	// }
}
