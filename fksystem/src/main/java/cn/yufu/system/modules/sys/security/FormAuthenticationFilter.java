/**
 * Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import cn.yufu.system.common.utils.StringUtils;

/**
 * 表单验证（包含验证码）过滤类
 * 
 * @author king
 * @version 2014-5-19
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {

	public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
	public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
	public static final String DEFAULT_MESSAGE_PARAM = "message";
	public static final String DEFAULT_DYNAMICPASSWORD_PARAM = "dynamicpassword";
	public static final String DEFAULT_MESSAGEDYNAMICPASSWORD_PARAM = "messagedynamicpassword";
	
	private String dynamicpasswordParam = DEFAULT_DYNAMICPASSWORD_PARAM;

	private String captchaParam = DEFAULT_CAPTCHA_PARAM;
	private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
	private String messageParam = DEFAULT_MESSAGE_PARAM;
	private String messagedynamicpasswordParam = DEFAULT_MESSAGEDYNAMICPASSWORD_PARAM;

	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		//动态口令
		String dynamicpassword = getDynamicpassword(request);
				
		boolean rememberMe = isRememberMe(request);
		String host = StringUtils.getRemoteAddr((HttpServletRequest) request);
		String captcha = getCaptcha(request);
		boolean mobile = isMobileLogin(request);
		//return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, mobile);
		return new UsernamePasswordToken(username, password.toCharArray(), rememberMe, host, captcha, dynamicpassword,mobile);
	}

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	public String getDynamicpasswordParam() {
		return dynamicpasswordParam;
	}

	protected String getDynamicpassword(ServletRequest request) {
		return WebUtils.getCleanParam(request, getDynamicpasswordParam());
	}
	
	public String getMobileLoginParam() {
		return mobileLoginParam;
	}

	protected boolean isMobileLogin(ServletRequest request) {
		return WebUtils.isTrue(request, getMobileLoginParam());
	}

	public String getMessageParam() {
		return messageParam;
	}

	public String getMessagedynamicpasswordParam() {
		return messagedynamicpasswordParam;
	}
	
	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		super.issueSuccessRedirect(request, response);
	}

	/**
	 * 登录失败调用事件
	 */
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		String className = e.getClass().getName(), message = "", messagedynamicpassword = "";
		if (IncorrectCredentialsException.class.getName().equals(className) || UnknownAccountException.class.getName().equals(className)) {
			message = "用户或密码错误, 请重试.";
		} else if (e.getMessage() != null && StringUtils.startsWith(e.getMessage(), "msg:")) {
			message = StringUtils.replace(e.getMessage(), "msg:", "");
		} else if (LockedAuthenticationException.class.getName().equals(className)){
			messagedynamicpassword = "动态口令错误, 请重试.";
			e.printStackTrace(); // 输出到控制台
		}else {
			message = "系统出现点问题，请稍后再试！";
			e.printStackTrace(); // 输出到控制台
		}
		request.setAttribute(getFailureKeyAttribute(), className);
		request.setAttribute(getMessageParam(), message);
		
		//动态口令提示框
		request.setAttribute(getMessagedynamicpasswordParam(), messagedynamicpassword);			
		return true;
	}

}