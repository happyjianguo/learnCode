/**
 * AuthenticationFailureListener.java
 * 2016年1月12日 下午1:20:29
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package com.jansh.core.service.listener;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.entity.sys.IMUserLog;
import com.jansh.core.security.userdetails.UserDetail;
import com.jansh.core.security.web.authentication.MyWebAuthenticationDetails;

/**
 * 登录成功监听
 * 
 * @author nie
 *
 */
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private static final Logger logger = LogManager.getLogger(AuthenticationSuccessEventListener.class);

	private AuthenticationService authenticationService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		MyWebAuthenticationDetails auth = (MyWebAuthenticationDetails) event.getAuthentication().getDetails();
		logger.info("登录认证成功：{}", event.getAuthentication().getName());

		// 获取用户id，如果获取失败，则使用用户名记录日志
		String userId = null;
		try {
			userId = ((UserDetail) event.getAuthentication().getPrincipal()).getUserid();
		} catch (Exception e) {
			logger.error("记录登录日志，获取用户id异常，{}", e);
		}
		if (StringUtils.isBlank(userId)) {
			userId = event.getAuthentication().getName();
		}

		// 用户日志信息
		IMUserLog imUserLog = new IMUserLog();
		imUserLog.setLogId(IDUtils.getTimeRandon());
		imUserLog.setRemoteIP(auth.getRemoteAddress());
		imUserLog.setStatus("1");
		imUserLog.setTransName("登录");
		imUserLog.setTransTime(DateUtil.getDateTimestamp());
		imUserLog.setUserId(userId);

		// 用户信息
		IMUser imUser = new IMUser();
		imUser.setUserid(userId);
		imUser.setUpdatetime(DateUtil.getDateTimestamp());
		imUser.setPwderrnum("0");

		if (authenticationService != null) {
			try {
				// 记录用户日志
				authenticationService.insertLog(imUserLog);
			} catch (Exception e) {
				logger.error("插入日志异常{}", e);
			}
			// 更新用户信息
			authenticationService.updatePwdErr(imUser);
		} else {
			logger.info("authenticationService is null. imUserLog:{}; imUser:{}", imUserLog, imUser);
		}
		// logService.insertLog(imUserLog);

		// IMUser imUser = new IMUser();
		// imUser.setUserid(userId);
		// imUser.setUpdatetime(DateUtil.getDateTimestamp());
		// imUser.setPwderrnum("0");
		// userService.updatePwdErr(imUser);
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
}
