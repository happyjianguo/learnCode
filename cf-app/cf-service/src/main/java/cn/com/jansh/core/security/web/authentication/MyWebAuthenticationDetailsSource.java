/**
 * MyWebAuthenticationDetailsSource.java
 * 2016年1月20日 上午11:34:32
 * 
 */
package cn.com.jansh.core.security.web.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;

/**
 * @author nie
 *
 */
public class MyWebAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, MyWebAuthenticationDetails> {

	public MyWebAuthenticationDetails buildDetails(HttpServletRequest context) {
		return new MyWebAuthenticationDetails(context);
	}

}
