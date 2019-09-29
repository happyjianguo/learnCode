package cn.com.jansh.util.spring;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 平台工具类
 *
 */
public class AppUtil {

	private static final Logger logger = LogManager.getLogger(AppUtil.class);

	/**
	 * 系统参数属性beanName常量
	 */
	public static final String PROPERTIES_BEANNAME = "configProperties";

	/**
	 * 获取Spring 管理的 Bean
	 * 
	 * @param key
	 * @return
	 */
	public static Object getBean(String key) {
		return AppContext.getBean(key);
	}

	/**
	 * 获取属性文件
	 * 
	 * @param key
	 * @return
	 */
	public static String getProperties(String key) {
		Properties properties = (Properties) AppContext.getBean(PROPERTIES_BEANNAME);
		return properties.getProperty(key);
	}

	/**
	 * 设置当前线程的强制退出到登录页面
	 * 
	 * @param request
	 */
	public static void setReturnFlag() {
		HttpSession httpSession = AppUtil.getSession();
		if (httpSession != null) {
			httpSession.setAttribute("_USR_SET_WORKFLOW_RETURN_LOGIN", "1");
		}
	}

	/**
	 * 获取当前线程是否强制退出标志
	 * 
	 * @param request
	 */
	public static boolean isReturnFlag() {
		HttpSession httpSession = AppUtil.getSession();
		if (httpSession != null) {
			if (httpSession.getAttribute("_USR_SET_WORKFLOW_RETURN_LOGIN") != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 获取系统登陆后的Session
	 * 
	 * @return
	 */
	public static HttpSession getSession() {
		return null;
	}

	/**
	 * 销毁系统登陆后指定用户的Session
	 * 
	 * @return
	 */
	public static void logout(String userName) {
		try {
			HttpSession httpSession = getSession(userName);
			if (httpSession != null) {
				httpSession.invalidate();
			}
		} catch (Exception ee) {
			logger.error(ee);
		}
	}

	/**
	 * 获取系统登陆后指定用户的Session
	 * 
	 * @return
	 */
	public static HttpSession getSession(String userName) {
		return null;
	}

	/**
	 * 设置Web系统的最大用户数量,超出将报警,此方法可以在交易文件中直接使用
	 * 
	 * @param maxNum
	 */
	public static void setMaxUserNumber(int maxNum) {
	}

	/**
	 * 从数据库获取信息,对cronTriggerBeanManager管理的跑批的时间进行刷新
	 * 
	 * @throws Exception
	 */
	public static void jobRefresh() throws Exception {
	}

	/**
	 * 通过HttpSession获取UserIp
	 * 
	 * @param httpSession
	 * @return
	 */
	public static String getUserIp(HttpSession httpSession) {
		return null;
	}

	/**
	 * 通过线程获取UserIp
	 * 
	 * @return
	 */
	public static String getUserIp() {
		return null;
	}

	/**
	 * 直接对指定的跑批程序进行跑批的时间初始化
	 * 
	 * @throws Exception
	 */
	public static void jobInit(String cronTriggerBeanName, String originConExpression) throws Exception {
	}

	/**
	 * 直接对指定的跑批程序进行删除
	 * 
	 * @throws Exception
	 */
	public static void jobRemove(String cronTriggerBeanName) throws Exception {
	}

	/**
	 * 将Locale对象转换成如"zh_CN"、"en"、"zh_TW"等的字符串
	 * 
	 * @param locale
	 * @return
	 */
	public static String getLocaleStr(Locale locale) {
		return null;
	}

	/**
	 * 获取客户端IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getPreUrI(HttpServletRequest request) {

		String requestURI = request.getRequestURI();
		requestURI = requestURI.substring(request.getContextPath().length() + 1);
		StringBuffer subFirstDir = new StringBuffer();

		int index = requestURI.indexOf("/");
		while (index >= 0) {
			String tmp = requestURI.substring(0, index);
			requestURI = requestURI.substring(index + 1);
			subFirstDir.append("/").append(tmp);
			index = requestURI.indexOf("/");
		}
		return subFirstDir.toString();
	}

	/**
	 * 获取系统的IP地址
	 * 
	 * @return
	 */
	public static String getInetAddress() {
		String netAddress = "127.0.0.1";
		try {
			InetAddress[] inetAdds = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			if (inetAdds.length > 0) {
				netAddress = inetAdds[0].getHostAddress();
			}
			if (inetAdds.length > 1) {
				netAddress = netAddress + ";";
				inetAdds[1].getHostAddress();
			}
			if (inetAdds.length > 2) {
				netAddress = netAddress + ";";
				inetAdds[2].getHostAddress();
			}
		} catch (UnknownHostException e) {
			logger.error("getInetAddress is error:" + e);
		}
		return netAddress;

	}

	/**
	 * 根据参数将页面跳转到错误页面,错误页面需要为交易处理器,这样可以将错误页面处理为同目录级别的页面 使用Session作为错误信息传递的页面
	 * 
	 * @param request
	 * @param response
	 * @param sessionErrorID
	 *            session的存储ID,获取后需要删除session中的errorid
	 * @param errorMsg
	 *            存储的错误消息
	 * @param sendPageUrl
	 *            错误页面的URL
	 * @param backPageURL
	 *            错误页面的返回页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void sendRedirect(HttpServletRequest request, HttpServletResponse response, String sendPageUrl) throws ServletException, IOException {
		/**
		 * 对于response方式的直接跳转，需要做以下设置，否则WebSphere 6.1对跳转后出现的页面有字符集问题，需要增加下列语句
		 */
		response.setLocale(request.getLocale());
		if (sendPageUrl.startsWith("/")) {
			sendPageUrl = request.getContextPath() + sendPageUrl;
		} else {
			sendPageUrl = request.getContextPath() + "/" + sendPageUrl;
		}
		logger.debug("response sendRedirect url:" + sendPageUrl);
		response.sendRedirect(response.encodeRedirectURL(sendPageUrl));

	}

	/**
	 * 根据参数将页面跳转到错误页面,错误页面需要为交易处理器,这样可以将错误页面处理为同目录级别的页面 使用Session作为错误信息传递的页面
	 * 
	 * @param request
	 * @param response
	 * @param sessionErrorID
	 *            session的存储ID,获取后需要删除session中的errorid
	 * @param errorMsg
	 *            存储的错误消息
	 * @param errorPageUrl
	 *            错误页面的URL
	 * @param backPageURL
	 *            错误页面的返回页面
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void errorSendRedirect(HttpServletRequest request, HttpServletResponse response, String sessionErrorID, String errorMsg, String errorPageUrl, String backPageURL) throws ServletException, IOException {
		/**
		 * 对于response方式的直接跳转，需要做以下设置，否则WebSphere 6.1对跳转后出现的页面有字符集问题，需要增加下列语句
		 */
		response.setLocale(request.getLocale());
		request.getSession().setAttribute(sessionErrorID, errorMsg);
		String subFirstDir = getPreUrI(request);

		if ((backPageURL != null) && (!backPageURL.equals(""))) {
			request.getSession().setAttribute("backPageURL", backPageURL);
		}

		errorPageUrl = subFirstDir + errorPageUrl;
		if (errorPageUrl.startsWith("/")) {
			errorPageUrl = request.getContextPath() + errorPageUrl;
		} else {
			errorPageUrl = request.getContextPath() + "/" + errorPageUrl;
		}
		logger.debug("response sendRedirect url:" + errorPageUrl);
		response.sendRedirect(response.encodeRedirectURL(errorPageUrl));

	}

	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static Object getBean(String beanName, ServletContext context) {
		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(context);
		if (wac != null) {
			return wac.getBean(beanName);
		} else {
			return null;
		}
	}

	public static String getText(String msgKey, Object[] values, HttpServletRequest request) {
		try {
			Locale locale = request.getLocale();
			MessageSource messagesource = (MessageSource) AppUtil.getBean("messageSource");
			return messagesource.getMessage(msgKey.trim(), values, locale);
		} catch (Exception e) {
			return "???" + msgKey + "???";
		}
	}

//	public static String getText(String msgKey, Object[] values) {
//		try {
//			Locale locale = null;
//			Object obj = threadLocale.get();
//			if (obj != null) {
//				locale = Locale.SIMPLIFIED_CHINESE;
//			}
//			MessageSource messagesource = (MessageSource) AppUtils.getBean("messageSource");
//			return messagesource.getMessage(msgKey.trim(), values, locale);
//		} catch (Exception e) {
//			return "???" + msgKey + "???";
//		}
//	}

//	public static String getText(String msgKey) {
//		try {
//			Locale locale = null;
//			Object obj = threadLocale.get();
//			if (obj != null) {
//				locale = Locale.SIMPLIFIED_CHINESE;
//			}
//			MessageSource messagesource = (MessageSource) AppUtils.getBean("messageSource");
//			return messagesource.getMessage(msgKey.trim(), null, locale);
//		} catch (Exception e) {
//			return "???" + msgKey + "???";
//		}
//	}

	public static String getText(String msgKey, Object[] values, Locale locale) {
		try {
			MessageSource messagesource = (MessageSource) AppUtil.getBean("messageSource");
			return messagesource.getMessage(msgKey.trim(), values, locale);
		} catch (Exception e) {
			return "???" + msgKey + "???";
		}
	}

	public static String getText(String msgKey, Locale locale) {
		try {
			MessageSource messagesource = (MessageSource) AppUtil.getBean("messageSource");
			return messagesource.getMessage(msgKey.trim(), null, locale);
		} catch (Exception e) {
			return "???" + msgKey + "???";
		}
	}

	public static String getText(String msgKey, HttpServletRequest request) {
		try {
			Locale locale = request.getLocale();
			MessageSource messagesource = (MessageSource) AppUtil.getBean("messageSource");
			return messagesource.getMessage(msgKey.trim(), null, locale);
		} catch (Exception e) {
			return "???" + msgKey + "???";
		}
	}

	public static String getText(ObjectError err, Locale locale) {
		if (locale == null) {
			locale = Locale.SIMPLIFIED_CHINESE;
		}
		String tmp = "???" + err.toString() + "???";
		try {
			tmp = AppContext.getApplicationContext().getMessage(err, locale);
		} catch (Exception ee) {
		}
		return tmp;
	}
}
