package cn.com.jansh.interceptors;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.jansh.component.common.AccessTokenTicket;
import cn.com.jansh.util.DateUtil;
import cn.com.jansh.util.spring.AppUtil;

public class WeixinShareInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LogManager.getLogger(WeixinShareInterceptor.class);

	@Autowired
	private AccessTokenTicket accessTokenTicket;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Map<String, String> jsApi = new HashMap<String, String>();
		String timestamp = DateUtil.getTimeStemp();
		String noncestr = RandomStringUtils.randomAlphanumeric(32);
		String appid = AppUtil.getProperties("APPID");
		String callBackDomain = AppUtil.getProperties("CALLBACKDOMAIN");
		String jsapi_ticket = accessTokenTicket.getTicket(appid);
		String link = request.getScheme()+"://" + callBackDomain + request.getContextPath() + "/explain/init";
		String imgUrl = request.getScheme()+"://" + callBackDomain + request.getContextPath() + "/resources/img/logo_changan.png";//WeiXinConstant.getDomain() + request.getContextPath() + "/img/share.jpg";
		String url = request.getRequestURL().toString();
		if(url.startsWith(request.getScheme()+"://"+callBackDomain)){
			url = url + (StringUtils.isBlank(request.getQueryString()) ? "" : ("?" + request.getQueryString()));
		}else{
			url = url.replace(url.substring(0, url.indexOf("/", url.indexOf("//")+2)), request.getScheme()+"://"+callBackDomain)+ (StringUtils.isBlank(request.getQueryString()) ? "" : ("?" + request.getQueryString()));
		}
		String signature = DigestUtils.sha1Hex("jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url);
		jsApi.put("jsapi_ticket", jsapi_ticket);
		jsApi.put("url", url);
		jsApi.put("appid", appid);
		jsApi.put("timestamp", timestamp);
		jsApi.put("noncestr", noncestr);
		jsApi.put("signature", signature);
		jsApi.put("link", link);
		jsApi.put("imgUrl", imgUrl);
		logger.info("jsApi:" + jsApi.toString());
		request.getSession().setAttribute("jsApi", jsApi);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}
}
