package com.jansh.core.web.servlet.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.jansh.core.annotation.OperationResult;
import com.jansh.core.model.sys.ResultMsg;

/**
 * 处理操作结果，提示信息
 * 
 * @author nie
 *
 */
public class ResultMessageInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ResultMessageInterceptor.class);

	// @Autowired
	// private AuthenticationService authenticationService;

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

		if (modelAndView == null || modelAndView.getModel() == null) {
			logger.trace("model is null");
			return;
		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
			if (responseBody == null) {
				OperationResult operationResult = handlerMethod.getMethodAnnotation(OperationResult.class);
				ResultMsg resultMsg = null;
				if (operationResult != null) {
					if (StringUtils.isNotBlank(operationResult.msgCode())) {
						// 自定义返回信息
						resultMsg = new ResultMsg(operationResult.msgCode(), null);
					} else {
						// 统一返回信息
						resultMsg = new ResultMsg(operationResult.value());
					}
					// 记录操作日志
					// insertUserLog(getOperationName(operationResult.value()));
					// 将ResultMsg放入modelAndView 中
					modelAndView.addObject("resultmsg", requestResultMsg(resultMsg, request));
				}
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
	private ResultMsg requestResultMsg(ResultMsg resultMsg, HttpServletRequest request) {

		if (resultMsg == null) {
			return resultMsg;
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
		return resultMsg;
		// request.setAttribute("resultmsg", resultMsg);
	}

	/**
	 * 记录操作日志
	 * 
	 * @param transInfo
	 */
	// private void insertUserLog(String transInfo) {
	//
	// UserDetail userDetail = AppUtil.getUserDetail();
	// if (userDetail == null) {
	// logger.error("获取登录用户信息为null，无法记录操作日志");
	// return;
	// }
	// String userid = userDetail.getUserid();
	//
	// List<MenuAuths> menuList = userDetail.getMenuList();
	// // 获取选中的菜单
	// MenuAuths menu = getSelectedMenu(menuList);
	// if (menu == null) {
	// logger.error("获取登录用户的菜单列表为null，无法记录操作日志");
	// return;
	// }
	//
	// IMUserLog imUserLog = new IMUserLog();
	// imUserLog.setLogId(IDUtils.getTimeRandon());
	// imUserLog.setRemoteIP(AppUtil.getUserRemoteAddress());
	// imUserLog.setStatus("1");
	// imUserLog.setTransName(menu.getCnname() + transInfo);
	// imUserLog.setTransTime(DateUtil.getDateTimestamp());
	// imUserLog.setUserId(userid);
	// try {
	// authenticationService.insertLog(imUserLog);
	// } catch (Exception e) {
	// logger.error("插入日志异常{}", e);
	// }
	// }

	/**
	 * 获取选中的菜单
	 * 
	 * @param menuList
	 * @return
	 */
	// private MenuAuths getSelectedMenu(List<MenuAuths> menuList) {
	// if (menuList == null) {
	// return null;
	// }
	// for (MenuAuths menu : menuList) {
	// if ("1".equals(menu.getSelected())) {
	// if (menu.getChild() != null && !menu.getChild().isEmpty()) {
	// return getSelectedMenu(menu.getChild());
	// } else {
	// return menu;
	// }
	// }
	// }
	// return null;
	// }

	/**
	 * 获取操作中文名
	 * 
	 * @param operation
	 * @return
	 */
	// private String getOperationName(Operation operation) {
	// switch (operation) {
	// case CREATE:
	// return "-新增";
	// case DELETE:
	// return "-删除";
	// case UPDATE:
	// return "-编辑";
	// case RETRIEVE:
	// return "";
	// case DEFULT:
	// return "";
	// default:
	// return "";
	// }
	// }
}
