package com.jansh.core.security.authentication.dao;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.util.Assert;

import com.jansh.core.security.userdetails.UserDetail;

/**
 * 
 * @author nie
 *
 */
public class LoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private PasswordEncoder passwordEncoder;

	private UserDetailsService userDetailsService;

//	private final String passwdprefix = "E17~uZEza^ydfEG&";
//	private final String passwdinfix = "wfd!E^&Ho2MV!vct";
//	private final String passwdpostfix = "ktIv$ieh6f!YaYX8";
//	private final String passwdsecret = "E17~uZEza^ydfEG&";
	
	private final String passwdprefix = "E17~uZEza^ydfEG&";
	private final String passwdinfix = "wfd!E^&Ho2MV!vct";
	private final String passwdpostfix = "ktIv$ieh6f!YaYX8";
	private final String passwdsalt = "ec58b566fe84f018f3194cd9aaa30531";
	/**
	 * niefengxin 20160824
	 */
//	private final String passwdsalt = "ec58b566fe84f018f3194cd9aaa30531";

	public LoginAuthenticationProvider() {
		setPasswordEncoder(new StandardPasswordEncoder(passwdsalt));
//		setPasswordEncoder(new StandardPasswordEncoder(passwdsecret));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));

			// throw new BadCredentialsException(messages.getMessage(
			// "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad
			// credentials"), userDetails);
		}

		String presentedPassword = authentication.getCredentials().toString();
		String username = "";
		if (userDetails instanceof UserDetail) {
			// 使用userid加密密码
			username = ((UserDetail) userDetails).getUserid();
		} else {
			username = authentication.getName();
		}
		presentedPassword = proccessPasswd(username, presentedPassword);

		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
			// throw new BadCredentialsException(messages.getMessage(
			// "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad
			// credentials"), userDetails);
		}

		logger.debug("===================>" + userDetails);
	}

	/**
	 * 预处理加密字符串
	 * 
	 * @param username
	 * @param passwd
	 * @return
	 */
	public String proccessPasswd(String username, String passwd) {
		return passwdprefix + username + passwdinfix + passwd + passwdpostfix;
//		String[] usernameArr = splitMiddle(username);
//		String[] passwdArr = splitMiddle(passwd);
//		StringBuffer resb = new StringBuffer();
//		resb.append(usernameArr[1]);
//		resb.append(passwdArr[0]);
//		resb.append(passwdsalt);
//		resb.append(usernameArr[0]);
//		resb.append(passwdArr[1]);
//		return resb.toString();
	}
	
	/**
	 * 中间拆分字符串
	 * @param str
	 * @return
	 */
	private String[] splitMiddle(String str) {
		if (str == null) {
			return new String[] { "", "" };
		}
		int strMiddle = str.length() / 2;
		return new String[] { str.substring(0, strMiddle), str.substring(strMiddle) };
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
	}

	@Override
	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
		} catch (UsernameNotFoundException notFound) {
			// if(authentication.getCredentials() != null) {
			// String presentedPassword =
			// authentication.getCredentials().toString();
			// passwordEncoder.isPasswordValid(userNotFoundEncodedPassword,
			// presentedPassword, null);
			// }
			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
