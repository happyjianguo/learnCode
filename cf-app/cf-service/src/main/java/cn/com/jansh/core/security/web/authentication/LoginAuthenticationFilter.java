package cn.com.jansh.core.security.web.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.Assert;

import cn.com.jansh.core.security.otp.OTPAuth;

public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

//	private static final Logger logger = LogManager.getLogger(LoginAuthenticationFilter.class);

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";

	private String usernameParameter = USERNAME_KEY;
	private String passwordParameter = PASSWORD_KEY;

//	@Autowired
//	private MenuConfig menuConfig;
	@Autowired
	private OTPAuth oTPAuth;
	
	private boolean postOnly = true;

	public LoginAuthenticationFilter() {
		super("/logincheck");
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(messages.getMessage("authenticationmethod.post", "Authentication method not supported: " + request.getMethod()));
		}

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		//验证otppwd
		String otppwd = request.getParameter("otppwd");
		int resultCode = oTPAuth.auth(username, otppwd);
		if(resultCode != 0){
			throw new BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badotppwd", "动态密码错误"));
		}
//		if(StringUtils.isBlank(checking)||StringUtils.isBlank(code)|| !checking.equals(code)){
//			throw new BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badCaptcha", "验证码错误"));
//		}
		
//		String checking = request.getParameter("checking");
//		String code = (String) request.getSession().getAttribute("IMAGECODE");
//		if(StringUtils.isBlank(checking)||StringUtils.isBlank(code)|| !checking.equals(code)){
//			throw new BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badCaptcha", "验证码错误"));
//		}
		
		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		/*if(!authRequest.isAuthenticated()&& "admin".equals(username)){
			authRequest.setAuthenticated(true);
		}*/
		
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
	public void setSuccessHandlerUrl(String defaultTargetUrl) {
		Assert.notNull(defaultTargetUrl, "successHandlerUrl cannot be null");
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(defaultTargetUrl);
		super.setAuthenticationSuccessHandler(successHandler);
	}

	/**
	 * successHandlerUrl
	 * 
	 * @param defaultTargetUrl
	 */
	public void setFailureHandlerUrl(String failureHandlerUrl) {
		Assert.notNull(failureHandlerUrl, "failureHandlerUrl cannot be null");
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler(failureHandlerUrl);
		super.setAuthenticationFailureHandler(failureHandler);
	}

	/**
	 * 加载菜单
	 
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
			throws IOException, ServletException {
		logger.info("successfulAuthentication......");
		UserDetail userDetail = (UserDetail) authResult.getPrincipal();
//		// 菜单列表
		List<MenuAuths> menuList = menuConfig.getMenuList();
//		// 过滤有权限的菜单
		List<MenuAuths> userMenuList = new ArrayList<MenuAuths>();
		boolean userflag = false;
		for (MenuAuths menu : menuList) {
			userflag = false;
			if (menu.getAuths() == null || menu.getAuths().size() == 0) {
				userMenuList.add(menu);
				continue;
			}
			for (ConfigAttribute configAttribute : menu.getAuths()) {
//				// 访问所请求资源所需要的权限
				String needPermission = configAttribute.getAttribute();
//				// 用户所拥有的权限authentication
				for (GrantedAuthority ga : userDetail.getAuthorities()) {
					if (needPermission.equals(ga.getAuthority())) {
						userMenuList.add(menu);
						userflag = true;
						break;
					}
				}
				if (userflag) {
					break;
				}
			}
		}
		List<MenuAuths> userMenu = MenuUtils.list2Tree(MenuUtils.cloneMenuList(userMenuList));
		logger.debug("user menu : " + userMenu);
		userDetail.setMenuList(userMenu);
		super.successfulAuthentication(request, response, chain, authResult);
	}
*/
}
