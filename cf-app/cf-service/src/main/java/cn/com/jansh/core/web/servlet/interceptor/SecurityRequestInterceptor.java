package cn.com.jansh.core.web.servlet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.jansh.core.annotation.SecurityRequest;
import cn.com.jansh.core.exception.SysErrorCode;
import cn.com.jansh.core.exception.SysException;

/**
 * 安全请求拦截器
 * 
 * @author nie
 *
 */
public class SecurityRequestInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(SecurityRequestInterceptor.class);

	public static String REPEAT_REQUEST_TOKEN = "_rrtoken";

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("====== preHandle");
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (responseBody != null) {
				//忽略ajax请求
				return true;
			}
		}
		
		boolean rrflag = false;
		HttpSession session = request.getSession(false);
		if (session == null) {
			return true;
		}
		String serverToken = (String) session.getAttribute(REPEAT_REQUEST_TOKEN);
		if (serverToken == null) {
			// 如果服务端token为空，则设置新的token
			request.getSession(false).setAttribute(REPEAT_REQUEST_TOKEN, RandomStringUtils.randomAlphanumeric(8));
		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			SecurityRequest securityRequest = handlerMethod.getMethodAnnotation(SecurityRequest.class);

			if (securityRequest != null) {
				boolean repeatRequest = securityRequest.repeatRequest();
				if (repeatRequest) {
					if (isRepeatRequest(request)) {
						logger.error("重复提交或网页已过期");
						rrflag = true;
						// return false;
					}
				}
			}
			request.getSession(false).setAttribute(REPEAT_REQUEST_TOKEN, RandomStringUtils.randomAlphanumeric(8));
			if (rrflag) {
				throw new SysException(SysErrorCode.E110006);
			}
		}

		return true;
	}

	/**
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("postHandle - start");

		logger.debug("postHandle - end");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("====== afterCompletion");
	}

	/**
	 * This implementation is empty.
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("====== afterConcurrentHandlingStarted");
	}

	/**
	 * 验证是否是重复提交
	 * 
	 * @param request
	 * @return
	 */
	private boolean isRepeatRequest(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute(REPEAT_REQUEST_TOKEN);
		if (serverToken == null) {
			request.getSession(false).setAttribute(REPEAT_REQUEST_TOKEN, RandomStringUtils.randomAlphanumeric(8));
			logger.error("serverToken is null");
			return true;
		}
		String clinetToken = request.getParameter(REPEAT_REQUEST_TOKEN);
		if (clinetToken == null) {
			request.getSession(false).setAttribute(REPEAT_REQUEST_TOKEN, RandomStringUtils.randomAlphanumeric(8));
			logger.error("clinetToken is null");
			return true;
		}
		if (!serverToken.equals(clinetToken)) {
			request.getSession(false).setAttribute(REPEAT_REQUEST_TOKEN, RandomStringUtils.randomAlphanumeric(8));
			logger.error("serverToken not equals clinetToken");
			return true;
		}
		request.getSession(false).setAttribute(REPEAT_REQUEST_TOKEN, RandomStringUtils.randomAlphanumeric(8));
		return false;
	}
}
