package cn.com.jansh.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.comm.util.DateUtil;
import cn.com.jansh.constant.WXConstant;
import cn.com.jansh.dao.entity.AuthAccount;
import cn.com.jansh.dao.entity.AuthorizerAccessToken;
import cn.com.jansh.dao.entity.ComponentAccessToken;
import cn.com.jansh.dao.entity.PubsSysBase;
import cn.com.jansh.dao.mapper.PubsSysBaseMapper;
import cn.com.jansh.dao.mapper.WxbCloudComponentAuthMapper;
import cn.com.jansh.service.WxAuthService;
import cn.com.jansh.utils.HttpClientUtil;

/**
 * 获取accesstoken serviceImpl
 * @author gll
 *
 */
@Service
public class WxAuthServiceImpl implements WxAuthService {

	@Autowired
	private WxbCloudComponentAuthMapper wxbCloudComponentAuthMapper;	//获取accesstoken接口
	
	@Autowired
	private  PubsSysBaseMapper systemBaseMapper;	//平台常量
	
	private static final Logger logger = LogManager.getLogger(WxAuthServiceImpl.class);
	@Override
	public String getAuthAccessToken(String appid) {
		AuthAccount authAccount = wxbCloudComponentAuthMapper.getAuthAccessToken(appid);;
		LocalDateTime updateTime = LocalDateTime.parse(authAccount.getUpdatetime(), DateUtil.formatter_DateTimestamp);
		LocalDateTime currentTime = DateUtil.getLocalDateTime();
		//90单位为分钟，60单位为秒
		if(Duration.between(updateTime, currentTime).getSeconds() > 60*90){
			PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);
			String component_access_token = getComponentAccessToken();
			
			AuthorizerAccessToken authRefreshToken = getAuthRefreshToken(component_access_token, component_appid.getSysBaseValue(),appid, authAccount.getAuth_refresh_token());
			authAccount.setAuth_access_token(authRefreshToken.getAuthorizer_access_token());
			authAccount.setAuth_refresh_token(authRefreshToken.getAuthorizer_refresh_token());
			wxbCloudComponentAuthMapper.update(authAccount);
			return authRefreshToken.getAuthorizer_access_token();
		}else{
			return authAccount.getAuth_access_token();
		}
	}
	
	private AuthorizerAccessToken getAuthRefreshToken(String component_access_token, String component_appid,
			String authorizer_appid, String authorizer_refresh_token) {
		logger.info("获取授权公众号的刷新凭据");
		String auth_refresh_url="https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token="+component_access_token;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> auth_refresh_param = new HashMap<>();
		auth_refresh_param.put("component_appid", component_appid);
		auth_refresh_param.put("authorizer_appid", authorizer_appid);
		auth_refresh_param.put("authorizer_refresh_token", authorizer_refresh_token);
		try {
			String authorizer_info_json = HttpClientUtil.httpsPost(auth_refresh_url, mapper.writeValueAsString(auth_refresh_param));
			logger.info("更新用户accesstoken: "+authorizer_info_json);
			AuthorizerAccessToken authAccessToken = mapper.readValue(authorizer_info_json, AuthorizerAccessToken.class);	
			logger.info(authAccessToken.toString());
			return authAccessToken;
		} catch (Exception e) {
			logger.error("获取授权公众号的刷新凭据异常",e);
		}
		return null;
	}
	
	private String getComponentAccessToken() {
		Date current = new Date();
		long currentNum = current.getTime();
		/**
		 * 获取上次刷新时间
		 */
		PubsSysBase component_tokenupdate = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TOKENUPDATE_KEY);
		long updateNum = Long.valueOf(component_tokenupdate.getSysBaseValue());
		/**
		 * 时间超过1.5个小时就重新请求accesstoken
		 */
		if(currentNum - updateNum > 1000*3600*1.5){
			/**
			 * 获取第三方平台信息
			 */
			PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);
			PubsSysBase component_appSecret = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPSECRET_KEY);
			PubsSysBase component_ticket = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TICKET_KEY);
			PubsSysBase component_access_token = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TOKEN_KEY);
			/**
			 * 请求accesstoken
			 */
			String componentAccessToken = getComponentAccessToken(component_appid.getSysBaseValue(), component_appSecret.getSysBaseValue(), 
					component_ticket.getSysBaseValue());
			/**
			 * 数据库更新accesstoken、更新时间
			 */
			component_access_token.setSysBaseValue(componentAccessToken);
			component_tokenupdate.setSysBaseValue(String.valueOf(currentNum));
			systemBaseMapper.update(component_access_token);
			systemBaseMapper.update(component_tokenupdate);
			return componentAccessToken;
		}else{
			/**
			 * 不超时直接拿数据
			 */
			PubsSysBase component_access_token = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TOKEN_KEY);
			return component_access_token.getSysBaseValue();
		}
	}
	
	private static String getComponentAccessToken(String component_appId, String component_appSecret, String verifyTicket) {
		logger.info("获取第三方公众号接口调用凭据");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> component_token_param = new HashMap<>();
		component_token_param.put("component_appid", component_appId);
		component_token_param.put("component_appsecret", component_appSecret);
		component_token_param.put("component_verify_ticket", verifyTicket);
		String component_token_url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
		try {
			String component_access_token = HttpClientUtil.httpsPost(component_token_url, mapper.writeValueAsString(component_token_param));
			ComponentAccessToken accessTokenObj = mapper.readValue(component_access_token, ComponentAccessToken.class);
			component_access_token = accessTokenObj.getComponent_access_token();
			logger.info("第三方平台接口调用权限: "+component_access_token);
			return component_access_token;
		} catch (Exception e) {
			logger.error("获取第三方公众号接口调用凭据异常{}",e);
		}
		return null;
	}
	
}


