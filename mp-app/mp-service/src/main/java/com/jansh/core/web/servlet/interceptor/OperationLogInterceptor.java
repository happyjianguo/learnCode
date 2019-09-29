package com.jansh.core.web.servlet.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.core.annotation.OperationLog;
import com.jansh.core.constant.Operation;
import com.jansh.core.entity.sys.IMUserLog;
import com.jansh.core.menu.MenuAuths;
import com.jansh.core.security.userdetails.UserDetail;
import com.jansh.core.service.listener.AuthenticationService;
import com.jansh.core.web.util.AppUtil;

/**
 * 记录操作日志
 * <p>
 * OperationLog
 * 
 * @author nie
 *
 */
public class OperationLogInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(OperationLogInterceptor.class);

	private static final String OPERATION_LOG_STATUS_SUCCESS = "1";

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("====== preHandle");

		return true;
	}

	/**
	 * 增加提示信息拦截器
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("postHandle - start");

//		if (modelAndView == null || modelAndView.getModel() == null) {
//			logger.trace("model is null");
//			return;
//		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			OperationLog operationLog = handlerMethod.getMethodAnnotation(OperationLog.class);
			String resultMsg = "";
			String custMsg = "";
			if (operationLog != null) {
				if (StringUtils.isNotBlank(operationLog.key())) {
					// 自定义日志信息
					custMsg = getKeyMsg(operationLog.key(), request);
				} else {
					// 统一日志信息
					resultMsg = getOperationName(operationLog.value());
				}
				// 记录操作日志
				insertUserLog(resultMsg, custMsg);
			}
		}

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
	 * 获取ResultMsg中文信息
	 * 
	 * @param resultMsg
	 * @param request
	 * @return
	 */
	private String getKeyMsg(String key, HttpServletRequest request) {

		if (key == null) {
			return "??????";
		}

		String msg = "";
		try {
			WebApplicationContext ac = RequestContextUtils.findWebApplicationContext(request);
			msg = ac.getMessage(key, null, RequestContextUtils.getLocale(request));
		} catch (Exception e) {
			msg = "???" + key + "???";
			logger.error("key {} get message exception,{}", key, e);
		}
		logger.info("==========> msgkey:{},msg:{}", key, msg);
		return msg;
		// request.setAttribute("resultmsg", resultMsg);
	}

	/**
	 * 记录操作日志
	 * 
	 * @param transInfo
	 */
	private void insertUserLog(String transInfo, String custMsg) {

		UserDetail userDetail = AppUtil.getUserDetail();
		if (userDetail == null) {
			logger.error("获取登录用户信息为null，无法记录操作日志");
			return;
		}
		String userid = userDetail.getUserid();

		String bizName = "";
		if (StringUtils.isBlank(custMsg)) {
			// 使用菜单记录日志信息
			List<MenuAuths> menuList = userDetail.getMenuList();
			// 获取选中的菜单
			MenuAuths menu = getSelectedMenu(menuList);
			if (menu == null) {
				logger.warn("获取登录用户选中的菜单为null，无法记录操作日志");
				return;
			} else {
				if (StringUtils.isBlank(transInfo)) {
					bizName = menu.getCnname();
				} else {
					bizName = menu.getCnname() + "-" + transInfo;
				}
			}
		} else {
			// 使用自定义日志信息
			bizName = custMsg;
		}

		IMUserLog imUserLog = new IMUserLog();
		imUserLog.setLogId(IDUtils.getTimeRandon());
		imUserLog.setRemoteIP(AppUtil.getUserRemoteAddress());
		imUserLog.setStatus(OPERATION_LOG_STATUS_SUCCESS);
		imUserLog.setTransName(bizName);
		imUserLog.setTransTime(DateUtil.getDateTimestamp());
		imUserLog.setUserId(userid);
		try {
			authenticationService.insertLog(imUserLog);
		} catch (Exception e) {
			logger.error("插入日志异常{}", e);
		}
	}

	/**
	 * 获取选中的菜单
	 * 
	 * @param menuList
	 * @return
	 */
	private MenuAuths getSelectedMenu(List<MenuAuths> menuList) {
		if (menuList == null) {
			return null;
		}
		for (MenuAuths menu : menuList) {
			if ("1".equals(menu.getSelected())) {
				if (menu.getChild() != null && !menu.getChild().isEmpty()) {
					return getSelectedMenu(menu.getChild());
				} else {
					return menu;
				}
			}
		}
		return null;
	}

	/**
	 * 获取操作中文名
	 * 
	 * @param operation
	 * @return
	 */
	private String getOperationName(Operation operation) {
		switch (operation) {
		case CREATE:
			return "新增";
		case DELETE:
			return "删除";
		case UPDATE:
			return "编辑";
		case RETRIEVE:
			return "";
		case DEFULT:
			return "";
		default:
			return "";
		}
	}
}
