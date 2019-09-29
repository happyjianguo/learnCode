//package cn.com.jansh.component.common;
//
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import cn.com.jansh.ims.basic.util.HttpClientUtil;
//import cn.com.jansh.ims.basic.util.JsonUtil;
//import cn.com.jansh.ims.entity.wx.WXBAccessToken;
//import cn.com.jansh.ims.mapper.wx.IAccessTokenMapper;
//import cn.com.jansh.security.userdetails.UserDetail;
//
//public class AccessTokenTicket {
//
//	private static final Logger logger = LogManager.getLogger(AccessTokenTicket.class);
//
//	@Autowired
//	private IAccessTokenMapper accessTokenMapper;
//
//	/*
//	 * 是否为远程获取AccessTokenTicket，默认为远程
//	 */
//	private String isRemote = "true";
//
//	/*
//	 * 远程获取AccessTokenTicket的URL
//	 */
//	private String accessTokenRemoteUrl = "";
//	/*
//	 * 远程获取Ticket的URL
//	 */
//	private String ticketRemoteUrl = "";
//
//	/**
//	 * 获取当前用户的AccessToken（本地）
//	 * 
//	 * @param appid
//	 * @return
//	 */
//	public String getCurrAccessToken() throws Exception {
//		UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String appid = userDetail.getAppid();
//		String accesstoken = "";
//		if (StringUtils.isBlank(appid)) {
//			return accesstoken;
//		}
//		// 本地获取
//		accesstoken = getAccessTokenLocal(appid);
//
//		return accesstoken;
//	}
//
//	/**
//	 * 获取AccessToken
//	 * 
//	 * @param appid
//	 * @return
//	 */
//	public String getAccessToken(String appid) throws Exception {
//		String accesstoken = "";
//		if (StringUtils.isBlank(appid)) {
//			return accesstoken;
//		}
//		if ("true".equals(isRemote)) {
//			// 远程获取
//			accesstoken = getAccessTokenRemote(appid);
//		} else {
//			// 本地获取
//			accesstoken = getAccessTokenLocal(appid);
//		}
//		return accesstoken;
//	}
//
//	/**
//	 * 获取Ticket
//	 * 
//	 * @param appid
//	 * @return
//	 */
//	public String getTicket(String appid) throws Exception {
//		String ticket = "";
//		if (StringUtils.isBlank(appid)) {
//			return ticket;
//		}
//		if ("true".equals(isRemote)) {
//			// 远程获取
//			ticket = getTicketRemote(appid);
//		} else {
//			// 本地获取
//			ticket = getTicketLocal(appid);
//		}
//		return ticket;
//	}
//
//	/**
//	 * 获取AccessToken
//	 * 
//	 * @param appid
//	 * @return
//	 */
//	public String getAccessTokenLocal(String appid) {
//		logger.info("获取appid为{}的AccessToken...", appid);
//		String result = "";
//		if (StringUtils.isNotBlank(appid)) {
//			// 从数据库中获取openid
//			WXBAccessToken accessToken = accessTokenMapper.getAccessTokenByAppid(appid);
//			if (accessToken != null) {
//				result = accessToken.getAccessToken();
//			} else {
//				logger.warn("未知的appid：{}", appid);
//			}
//		}
//		logger.info("获取appid为{}的AccessToken:{}", appid, result);
//		return result;
//	}
//
//	/**
//	 * 获取AccessToken
//	 * 
//	 * @param appid
//	 * @return
//	 * @throws Exception
//	 */
//	private String getAccessTokenRemote(String appid) throws Exception {
//		logger.info("远程获取appid为{}的AccessToken...", appid);
//		String result = "";
//		if (StringUtils.isBlank(accessTokenRemoteUrl)) {
//			logger.info("远程获取accessToken的URL(accessTokenRemoteUrl)为空");
//			return result;
//		}
//		String accessTokenJson = HttpClientUtil.httpPost(accessTokenRemoteUrl, "{\"appid\":\"" + appid + "\"}");
//		Map<String, String> accessTokenMap = null;
//		try {
//			accessTokenMap = JsonUtil.readMapString(accessTokenJson);
//		} catch (Exception e) {
//			logger.error("accessTokenJson报文返回格式错误：{}", e);
//			throw e;
//		}
//		if (accessTokenMap != null) {
//			result = accessTokenMap.get("accesstoken");
//		} else {
//			result = "";
//		}
//		return result;
//	}
//
//	/**
//	 * 获取Ticket
//	 * 
//	 * @param appid
//	 * @return
//	 */
//	public String getTicketLocal(String appid) {
//		logger.info("获取appid为{}的ticket...", appid);
//		String result = "";
//		if (StringUtils.isNotBlank(appid)) {
//			// 从数据库中获取openid
//			WXBAccessToken accessToken = accessTokenMapper.getAccessTokenByAppid(appid);
//			if (accessToken != null) {
//				result = accessToken.getTicket();
//			} else {
//				logger.warn("未知的appid：{}", appid);
//			}
//		}
//		logger.info("获取appid为{}的ticket:{}", appid, result);
//		return result;
//	}
//
//	/**
//	 * 远程获取Ticket
//	 * 
//	 * @param appid
//	 * @return
//	 */
//	private String getTicketRemote(String appid) throws Exception {
//		logger.info("远程获取appid为{}的ticket...", appid);
//		String result = "";
//		if (StringUtils.isBlank(ticketRemoteUrl)) {
//			logger.info("远程获取ticket的URL(ticketRemoteUrl)为空");
//			return result;
//		}
//		String ticketJson = HttpClientUtil.httpPost(ticketRemoteUrl, "{\"appid\":\"" + appid + "\"}");
//		Map<String, String> ticketMap = null;
//		try {
//			ticketMap = JsonUtil.readMapString(ticketJson);
//		} catch (Exception e) {
//			logger.error("accessTokenJson报文返回格式错误：{}", e);
//			throw e;
//		}
//		if (ticketMap != null) {
//			result = ticketMap.get("ticket");
//		} else {
//			result = "";
//		}
//		return result;
//	}
//
//	public String getIsRemote() {
//		return isRemote;
//	}
//
//	public void setIsRemote(String isRemote) {
//		this.isRemote = isRemote;
//	}
//
//	public String getAccessTokenRemoteUrl() {
//		return accessTokenRemoteUrl;
//	}
//
//	public void setAccessTokenRemoteUrl(String accessTokenRemoteUrl) {
//		this.accessTokenRemoteUrl = accessTokenRemoteUrl;
//	}
//
//	public String getTicketRemoteUrl() {
//		return ticketRemoteUrl;
//	}
//
//	public void setTicketRemoteUrl(String ticketRemoteUrl) {
//		this.ticketRemoteUrl = ticketRemoteUrl;
//	}
//
//}