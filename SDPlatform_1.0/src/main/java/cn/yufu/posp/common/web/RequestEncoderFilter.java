package cn.yufu.posp.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.yufu.posp.common.common.util.SystemConstants;
import cn.yufu.posp.common.domain.model.UserData;

public class RequestEncoderFilter implements Filter {
	// private static final Log log = LogFactory.getLog("operLog");

	String charset;

	public RequestEncoderFilter() {
		charset = null;
	}

	public void init(FilterConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		if (charset == null)
			charset = "GBK";
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(charset);
		if (chain != null) {
			// if(log.isInfoEnabled()){
			// HttpServletRequest req = (HttpServletRequest)request;
			// UserData ud = getSessionUserData(req);
			// if(ud.getUserId() != null &&
			// req.getRequestURL().toString().endsWith(".do")){
			// Map map = new HashMap();
			// Iterator it = req.getParameterMap().entrySet().iterator();
			// while(it.hasNext()){
			// Map.Entry entry = (Map.Entry) it.next();
			// Object key = entry.getKey();
			// Object[] val = (Object[]) entry.getValue();
			// map.put(key, val[0]);
			// }
			// log.info("用户："+ud.getUserId()+" - "+ud.getUserName()+
			// " 请求："+req.getRequestURL()+" 方法："+map.get("method") +" 参数："+map);
			// }
			// }
			chain.doFilter(request, response);
		}
	}

	public void destroy() {
	}

	/**
	 * 获取当前用户信息
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return UserData
	 */
	protected UserData getSessionUserData(HttpServletRequest request) {
		UserData ud = null;
		HttpSession session = request.getSession(false);
		if (null != session && session.getAttribute(SystemConstants.CURRENT_USER_DATA) != null)
			ud = (UserData) session.getAttribute(SystemConstants.CURRENT_USER_DATA);
		else
			ud = new UserData();

		// ud.set

		return ud;
	}
}
