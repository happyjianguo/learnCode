package com.jansh.core.web.servlet.handler;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.jansh.core.annotation.ExceptionHandle;
import com.jansh.core.exception.AppException;
import com.jansh.core.exception.SysException;

/**
 * 全局异常处理
 * 
 * @author nie
 *
 */
public class GlobalExceptionResolver extends AbstractHandlerExceptionResolver {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionResolver.class);
	/**
	 * 错误页面
	 */
	private String errorView = "error/apperror";

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (ex instanceof AppException) {
			return handleAppException((AppException) ex, request, response, (HandlerMethod) handler);
		} else if (ex instanceof SysException) {
			return handleSysException((SysException) ex, request, response, (HandlerMethod) handler);
		} else if (ex instanceof Exception) {
			return handleException(ex, request, response, (HandlerMethod) handler);
		}
		return null;
	}

	/**
	 * AppException异常处理
	 * 
	 * @param ex
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 */
	private ModelAndView handleAppException(AppException appException, HttpServletRequest request,
			HttpServletResponse response, HandlerMethod handlerMethod) {

		logger.error("handlerMethod has AppException. {}", handlerMethod);
		logger.error(appException);
		String errorCode = "error.error";
		String errorMsg = "系统忙，请稍后再试";
		String toUrl = "";
		if (appException != null) {
			errorCode = appException.getErrCode();
			toUrl = appException.getToUrl();
		}

		try {
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			errorMsg = ac.getMessage(errorCode, null, RequestContextUtils.getLocale(request));
		} catch (Exception e) {
			errorMsg = "???" + errorCode + "???";
		}
		logger.error("==========> errorCode:{},errorMsg:{}", errorCode, errorMsg);

		ModelAndView mv = new ModelAndView();
		String viewName = "";
		if (handlerMethod != null) {
			ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (responseBody != null) {
				// 返回json格式数据
				MappingJackson2JsonView view = new MappingJackson2JsonView();
				Map<String, String> map = new HashMap<String, String>();
				map.put("errorCode", errorCode);
				map.put("errorMsg", errorMsg);
				if (StringUtils.isNotBlank(toUrl)) {
					map.put("toUrl", toUrl);
				}
				view.setAttributesMap(map);
				// 返回json数据
				mv.setView(view);
				return mv;
			} else {
				ExceptionHandle exceptionView = handlerMethod.getMethodAnnotation(ExceptionHandle.class);
				if (exceptionView != null) {
					if (StringUtils.isNotBlank(exceptionView.value())) {
						viewName = exceptionView.value();
					}
				}
			}
		}

		mv.addObject("errorCode", errorCode);
		mv.addObject("errorMsg", errorMsg);
		if (StringUtils.isNotBlank(toUrl)) {
			mv.addObject("toUrl", toUrl);
		}
		if (StringUtils.isNotBlank(viewName)) {
			// 返回指定的controller
			Enumeration<String> attributeNames = request.getParameterNames();
			while (attributeNames.hasMoreElements()) {
				String name = attributeNames.nextElement();
				if ("_csrf".equals(name)) {
					continue;
				}
				mv.addObject(name, request.getParameter(name));
			}
			mv.setViewName("forward:" + viewName);
		} else {
			mv.setViewName(this.errorView);
		}
		return mv;
	}

	/**
	 * SysException 系统异常处理，返回公共错误页面
	 * 
	 * @param ex
	 * @param request
	 * @param response
	 * @param handlerMethod
	 * @return
	 */
	private ModelAndView handleSysException(SysException ex, HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod) {
		logger.error("handlerMethod has SysException. {}", handlerMethod);
		logger.error(ex);
		String errorCode = "error.error";
		String errorMsg = "系统忙，请稍后再试";
		String backUrl = "";
		if (ex != null) {
			errorCode = ex.getErrCode();
		}

		try {
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			errorMsg = ac.getMessage(errorCode, null, RequestContextUtils.getLocale(request));
		} catch (Exception e) {
			errorMsg = "???" + errorCode + "???";
		}
		logger.error("==========> errorCode:{},errorMsg:{}", errorCode, errorMsg);

		ModelAndView mv = new ModelAndView();
		if (handlerMethod != null) {
			ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (responseBody != null) {
				// 返回json格式数据
				MappingJackson2JsonView view = new MappingJackson2JsonView();
				Map<String, String> map = new HashMap<String, String>();
				map.put("errorCode", errorCode);
				map.put("errorMsg", errorMsg);
				if (StringUtils.isNotBlank(backUrl)) {
					map.put("backUrl", backUrl);
				}
				view.setAttributesMap(map);
				// 返回json数据
				mv.setView(view);
				return mv;
			}
		}

		mv.addObject("errorCode", errorCode);
		mv.addObject("errorMsg", errorMsg);
		if (StringUtils.isNotBlank(backUrl)) {
			mv.addObject("backUrl", backUrl);
		}
		mv.setViewName(this.errorView);
		return mv;
	}

	/**
	 * Exception 系统异常处理，返回公共错误页面
	 * 
	 * @param ex
	 * @param request
	 * @param response
	 * @param handlerMethod
	 * @return
	 */
	private ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod) {
		logger.error("handlerMethod has Exception. {}", handlerMethod);
		logger.error(ex);

		String errorCode = "error.error";
		String errorMsg = "系统忙，请稍后再试";
		String backUrl = "";

		ModelAndView mv = new ModelAndView();

		if (handlerMethod != null) {
			ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (responseBody != null) {
				// 返回json格式数据
				MappingJackson2JsonView view = new MappingJackson2JsonView();
				Map<String, String> map = new HashMap<String, String>();
				map.put("errorCode", errorCode);
				map.put("errorMsg", errorMsg);
				if (StringUtils.isNotBlank(backUrl)) {
					map.put("backUrl", backUrl);
				}
				view.setAttributesMap(map);
				// 返回json数据
				mv.setView(view);
				return mv;
			}
		}

		mv.addObject("errorCode", errorCode);
		mv.addObject("errorMsg", errorMsg);
		if (StringUtils.isNotBlank(backUrl)) {
			mv.addObject("backUrl", backUrl);
		}
		mv.setViewName(this.errorView);
		return mv;
	}

	public String getErrorView() {
		return errorView;
	}

	public void setErrorView(String errorView) {
		this.errorView = errorView;
	}

}
