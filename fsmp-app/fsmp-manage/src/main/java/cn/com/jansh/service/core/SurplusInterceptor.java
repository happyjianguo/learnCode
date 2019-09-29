/**
 * SurplusInterceptor.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月16日
 */
package cn.com.jansh.service.core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jansh.core.web.servlet.interceptor.SecurityRequestInterceptor;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.entity.component.CloudATSurplusEntitiy;
import cn.com.jansh.service.component.CyInterfaceService;

/**
 * 账户余额信息拦截器
 * @author Mr.wong
 * @version 1.0
 */
public class SurplusInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LogManager.getLogger(SecurityRequestInterceptor.class);
	
	@Autowired
	public CyInterfaceService cyService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("========preHandle");
		if(AppUtil.getUserDetail()!=null){
			CloudATSurplusEntitiy accountSurplus = cyService.getAccountSurplus();
			long currentMoney = 0;
			long totalMoney = 0;
			if(accountSurplus != null){
				currentMoney = accountSurplus.getCurrentMoney().longValue();
				totalMoney = accountSurplus.getTotalMoney().longValue();
			}
			request.getSession(false).setAttribute("currentMoney", formatNumber(String.valueOf(currentMoney)));
			request.getSession(false).setAttribute("totalMoney", formatNumber(String.valueOf(totalMoney)));
			
		}
		return true;
	}
	
	private String formatNumber(String str) {
	    if(str.length() <= 3){
	        return str;
	    } else {
	        return formatNumber(str.substring(0,str.length()-3))+','+str.substring(str.length()-3);
	    }
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("========postHandle");
		
		
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.debug("========afterCompletion");
	}
}
