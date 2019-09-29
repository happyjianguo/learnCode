/**
 * AuthenticationFailureListener.java
 * 2016年1月12日 下午1:20:29
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package cn.com.jansh.core.service.listener;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.entity.sys.IMUserLog;
import cn.com.jansh.core.mapper.SysCoreMapper;
import cn.com.jansh.core.security.web.authentication.MyWebAuthenticationDetails;
import cn.com.jansh.core.service.sys.DefUserLogService;
import cn.com.jansh.core.service.sys.DefUserService;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;

/**
 * 登录失败监听
 * 
 * @author nie
 *
 */
@Service
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	private static final Logger logger = LogManager.getLogger(AuthenticationFailureListener.class);
	@Autowired
	private DefUserLogService logService;
	@Autowired
	private DefUserService userService;
	@Autowired
	private SysCoreMapper sysCoreMapper;

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

		String operadate = DateUtil.getTimestamp();
		IMUserLog imUserLog = new IMUserLog();
		imUserLog.setLogId(IDUtils.getTimeRandon());
		imUserLog.setRemoteIP(auth.getRemoteAddress());
		imUserLog.setStatus("0");
		imUserLog.setTransName("登录");
		imUserLog.setTransTime(operadate);
		imUserLog.setUserId(userId);
		try {
			logService.insertLog(imUserLog);
		} catch (Exception e) {
			logger.error("插入日志异常{}", e);
		}
		try {
			userService.updateErrNumSafe(userId,operadate);
		} catch (Exception e) {
			logger.error("更新密码错误次数异常：{}", e);
		}

	}

}
