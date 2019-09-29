/**
 * AppUtil.java
 * 2016年1月20日 上午10:47:05
 * 
 */
package cn.com.jansh.core.web.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.model.sys.ResultMsg;
import cn.com.jansh.core.security.userdetails.UserDetail;
import cn.com.jansh.core.security.web.authentication.MyWebAuthenticationDetails;

/**
 * @author nie
 *
 */
public class AppUtil {

	private static final Logger logger = LogManager.getLogger(AppUtil.class);

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemoteIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		logger.info("RemoteIpAddr:{}", ip);
		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ip.split(",");
		String ipString = "";
		for (String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ipString = str;
			}
			break;
		}
		return ipString;
	}

	/**
	 * 获取当前登录用户信息
	 * 
	 * @return
	 * @throws AppException
	 */
	public static UserDetail getUserDetail() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication auth = null;
		Object principal = null;
		if (sc != null) {
			auth = sc.getAuthentication();
		}
		if (auth != null) {
			principal = auth.getPrincipal();
		}
		UserDetail userDetail = null;
		if (principal instanceof UserDetail) {
			userDetail = (UserDetail) principal;
		}
		if (userDetail == null) {
			return null;
			// throw new AppException(SysErrorCode.E100001);
		}
		return userDetail;
	}

	/**
	 * 获取当前登录用户的ip地址，如果无法获取，则返回空字符串
	 * 
	 * @return
	 * @throws AppException
	 */
	public static String getUserRemoteAddress() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication auth = null;
		Object details = null;
		if (sc != null) {
			auth = sc.getAuthentication();
		}
		if (auth != null) {
			details = auth.getDetails();
		}
		MyWebAuthenticationDetails wdetails = null;
		if (details instanceof MyWebAuthenticationDetails) {
			wdetails = (MyWebAuthenticationDetails) details;
		}
		if (wdetails == null) {
			return "";
		}
		return wdetails.getRemoteAddress();
	}

	/**
	 * 返回操作信息
	 * 
	 * @param resultMsg
	 */
	public static void requestAttribute(ResultMsg resultMsg) {

		if (resultMsg == null) {
			return;
		}
		HttpServletRequest request = null;
		if (resultMsg.getRequest() == null) {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}

		if (request == null) {
			return;
		}

		String msgcode = resultMsg.getMsgcode();
		String msg = "";
		try {
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			msg = ac.getMessage(msgcode, null, RequestContextUtils.getLocale(request));
		} catch (Exception e) {
			msg = "???" + msgcode + "???";
		}
		logger.error("==========> msgcode:{},msg:{},msgargs{}", msgcode, msg, resultMsg.getMsgargs());
		resultMsg.setMsg(msg);
		RequestContextHolder.getRequestAttributes().setAttribute("resultmsg", resultMsg,
				RequestAttributes.SCOPE_REQUEST);
	}

	/**
	 * 返回操作信息
	 * 
	 * @param resultMsg
	 */
	public static String getMessage(String msgcode, HttpServletRequest request) {
		if (msgcode == null) {
			return "";
		}
		if (request == null) {
			//大部分情况是好使的，个别情况无法获取request，如multipart/form-data
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}

		if (request == null) {
			return "";
		}

		String msg = "";
		try {
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			msg = ac.getMessage(msgcode, null, RequestContextUtils.getLocale(request));
		} catch (Exception e) {
			logger.error(e);
			msg = "???" + msgcode + "???";
		}
		logger.info("==========> msgcode:{},msg:{}", msgcode, msg);
		return msg;
	}

	/*
	 * 设置返回信息
	 * 
	 * @param msgcode 返回码
	 * 
	 * @param args 返回码参数
	 */
	// public static void resultMsg(String msgcode, String[] args) {
	// requestAttribute(new ResultMsg(msgcode, args));
	// }

}
