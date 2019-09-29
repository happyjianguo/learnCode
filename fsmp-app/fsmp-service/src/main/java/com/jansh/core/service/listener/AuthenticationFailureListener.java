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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;

import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.IDUtils;
import com.jansh.core.entity.sys.IMUser;
import com.jansh.core.entity.sys.IMUserLog;
import com.jansh.core.mapper.SysCoreMapper;
import com.jansh.core.security.web.authentication.MyWebAuthenticationDetails;

/**
 * 登录失败监听
 * 
 * @author nie
 *
 */
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	private static final Logger logger = LogManager.getLogger(AuthenticationFailureListener.class);

	@Autowired
	private SysCoreMapper sysCoreMapper;

	private AuthenticationService authenticationService;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		MyWebAuthenticationDetails auth = (MyWebAuthenticationDetails) event.getAuthentication().getDetails();
		logger.info("登录认证失败：{}", event.getAuthentication().getName());

		String userId = null;
		try {
			// 查询用户
			IMUser user = sysCoreMapper.selectUserByUserName(event.getAuthentication().getName());
			if (user != null) {
				userId = user.getUserid();
			}
		} catch (Exception e) {
			logger.error("记录登录日志，获取用户id异常，{}", e);
		}
		if (StringUtils.isBlank(userId)) {
			logger.error("记录登录日志，获取用户id失败，使用userId记录日志：{}", userId);
			userId = event.getAuthentication().getName();
		}

		String operadate = DateUtil.getDateTimestamp();
		IMUserLog imUserLog = new IMUserLog();
		imUserLog.setLogId(IDUtils.getTimeRandon());
		imUserLog.setRemoteIP(auth.getRemoteAddress());
		imUserLog.setStatus("0");
		imUserLog.setTransName("登录");
		imUserLog.setTransTime(operadate);
		imUserLog.setUserId(userId);

		if (authenticationService != null) {
			try {
				// 记录用户日志
				authenticationService.insertLog(imUserLog);
			} catch (Exception e) {
				logger.error("插入日志异常{}", e);
			}
			// 更新用户信息
			authenticationService.updateErrNumSafe(userId, operadate);
		} else {
			logger.info("authenticationService is null. imUserLog:{}; userId:{}", imUserLog, userId);
		}
	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

}
