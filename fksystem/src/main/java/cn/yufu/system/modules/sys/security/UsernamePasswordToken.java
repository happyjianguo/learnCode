/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.security;

/**
 * 用户和密码（包含验证码）令牌类
 * @author king
 * @version 2013-5-19
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String dynamicpassword;
	private String captcha;
	private boolean mobileLogin;
	
	public UsernamePasswordToken() {
		super();
	}

	public UsernamePasswordToken(String username, char[] password,
			boolean rememberMe, String host, String captcha,String dynamicpassword, boolean mobileLogin) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.dynamicpassword = dynamicpassword;
		this.mobileLogin = mobileLogin;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getDynamicpassword() {
		return dynamicpassword;
	}

	public void setDynamicpassword(String dynamicpassword) {
		this.dynamicpassword = dynamicpassword;
	}
	
	public boolean isMobileLogin() {
		return mobileLogin;
	}
	
}