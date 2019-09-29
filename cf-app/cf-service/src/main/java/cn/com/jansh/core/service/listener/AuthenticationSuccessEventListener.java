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
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

import cn.com.jansh.core.entity.sys.IMUser;
import cn.com.jansh.core.entity.sys.IMUserLog;
import cn.com.jansh.core.security.userdetails.UserDetail;
import cn.com.jansh.core.security.web.authentication.MyWebAuthenticationDetails;
import cn.com.jansh.core.service.sys.DefUserLogService;
import cn.com.jansh.core.service.sys.DefUserService;
import cn.com.jansh.core.util.DateUtil;
import cn.com.jansh.core.util.IDUtils;

/**
 * 登录成功监听
 * 
 * @author nie
 *
 */
@Service
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

	private static final Logger logger = LogManager.getLogger(AuthenticationSuccessEventListener.class);
	@Autowired
	private DefUserLogService logService;
	@Autowired
	private DefUserService userService;

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		MyWebAuthenticationDetails auth = (MyWebAuthenticationDetails) event.getAuthentication().getDetails();
		logger.info("登录认证成功：{}", event.getAuthentication().getName());
		
		//获取用户id，如果获取失败，则使用用户名记录日志
		String userId = null;
		try{
			userId = ((UserDetail)event.getAuthentication().getPrincipal()).getUserid();
		}catch(Exception e){
			logger.error("记录登录日志，获取用户id异常，{}",e);
		}
		if(StringUtils.isBlank(userId)){
			userId = event.getAuthentication().getName();
		}
		
		IMUserLog imUserLog = new IMUserLog();
		imUserLog.setLogId(IDUtils.getTimeRandon());
		imUserLog.setRemoteIP(auth.getRemoteAddress());
		imUserLog.setStatus("1");
		imUserLog.setTransName("登录");
		imUserLog.setTransTime(DateUtil.getTimestamp());
		imUserLog.setUserId(userId);
		try {
			logService.insertLog(imUserLog);
		} catch (Exception e) {
			logger.error("插入日志异常{}",e);
		}
		IMUser imUser = new IMUser();
		imUser.setUserid(userId);
		imUser.setUpdatetime(DateUtil.getTimestamp());
		imUser.setPwderrnum("0");
		userService.updatePwdErr(imUser);
	}

}
