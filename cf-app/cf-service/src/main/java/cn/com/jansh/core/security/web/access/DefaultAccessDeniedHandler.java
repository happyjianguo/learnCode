/**
 * DefaultAccessDeniedHandler.java
 * 2016年2月4日 上午11:09:37
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package cn.com.jansh.core.security.web.access;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;

/**
 * @author nie
 *
 */
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger logger = LogManager.getLogger(DefaultAccessDeniedHandler.class);

	private String errorPage;

	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
			throws IOException, ServletException {
		if (!response.isCommitted()) {
			if (errorPage != null) {
				// 退出
				if (accessDeniedException instanceof MissingCsrfTokenException) {
					logger.error("MissingCsrfTokenException,sendRedirect：{}/login", request.getContextPath());
					response.sendRedirect(request.getContextPath() + "/login");
				} else
				// session失效
				if (accessDeniedException instanceof InvalidCsrfTokenException) {
					logger.error("InvalidCsrfTokenException,sendRedirect：{}/login", request.getContextPath());
					response.sendRedirect(request.getContextPath() + "/login");
				} else {

					// Put exception into request scope (perhaps of use to a
					// view)
					request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

					// Set the 403 status code.
					response.setStatus(HttpServletResponse.SC_FORBIDDEN);

					// forward to error page.
					RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
					dispatcher.forward(request, response);
				}
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
		}
	}

	/**
	 * The error page to use. Must begin with a "/" and is interpreted relative
	 * to the current context root.
	 *
	 * @param errorPage
	 *            the dispatcher path to display
	 *
	 * @throws IllegalArgumentException
	 *             if the argument doesn't comply with the above limitations
	 */
	public void setErrorPage(String errorPage) {
		if ((errorPage != null) && !errorPage.startsWith("/")) {
			throw new IllegalArgumentException("errorPage must begin with '/'");
		}

		this.errorPage = errorPage;
	}
}
