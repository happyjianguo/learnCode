package cn.com.jansh.service.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.Assert;

import cn.com.jansh.model.system.GeetestConfig;
import cn.com.jansh.model.system.GeetestLib;

/**
 * 登录认证
 *
 * @author fengxin
 * @since 1.0
 * @deprecated as of 2.0, in favor of
 * {@link com.jansh.core.security.web.authentication.UsernamePasswordAuthFilter UsernamePasswordAuthFilter}
 */
@Deprecated
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

//	private static final Logger logger = LogManager.getLogger(LoginAuthenticationFilter.class);

	public static final String USERNAME_KEY = "username";
	public static final String PASSWORD_KEY = "password";
	public static final String CHLLENGE_KEY = "challenge";
	public static final String VALIDATE_KEY = "validate";
	public static final String SECCODE_KEY = "seccode";

	private String usernameParameter = USERNAME_KEY;
	private String passwordParameter = PASSWORD_KEY;
	private String challengeParameter = CHLLENGE_KEY;
	private String validateParameter = VALIDATE_KEY;
	private String seccodeParameter = SECCODE_KEY;

//	@Autowired
//	private MenuConfig menuConfig;
//	@Autowired
//	private OTPAuth oTPAuth;
	
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
		
		String challenge = obtainChallenge(request).trim();
		String validate = obtainValidate(request).trim();
		String seccode = obtainSeccode(request).trim();
		
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getCaptcha_id(), GeetestConfig.getPrivate_key());
		//从session中获取gt-server状态
		int gt_server_status_code = (Integer) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
		//从session中获取userid
		String userid = (String)request.getSession().getAttribute("userid");
		int gtResult = 0;
		if (gt_server_status_code == 1) {
			//gt-server正常，向gt-server进行二次验证
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, userid);
		} else {
			// gt-server非正常情况下，进行failback模式验证
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
		}
		if (gtResult != 1) {
			throw new BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badCaptcha", "验证失败"));
		}
		//验证otppwd
//		String otppwd = request.getParameter("otppwd");
//		int resultCode = oTPAuth.auth(username, otppwd);
//		if(resultCode != 0){
//			throw new BadCredentialsException(messages.getMessage("LoginAuthenticationFilter.badotppwd", "动态密码错误"));
//		}
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

		// 允许子类设置详细属性
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}
	protected String obtainSeccode(HttpServletRequest request) {
		String seccode = request.getParameter(seccodeParameter);
		return seccode == null ? "" : seccode;
	}
	
	protected String obtainValidate(HttpServletRequest request) {
		String validate = request.getParameter(validateParameter);
		return validate == null ? "" : validate;
	}
	
	protected String obtainChallenge(HttpServletRequest request) {
		String challenge = request.getParameter(challengeParameter);
		return challenge == null ? "" : challenge;
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
