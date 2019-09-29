package cn.com.jansh.service.wechat.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jansh.comm.util.DateUtil;
import com.jansh.comm.util.JsonUtil;
import com.jansh.core.entity.sys.PubsSysBase;
import com.jansh.core.exception.AppException;
import com.jansh.core.security.userdetails.UserDetail;
import com.jansh.core.web.util.AppUtil;

import cn.com.jansh.component.common.GlobalProperties;
import cn.com.jansh.constant.AppErrorCode;
import cn.com.jansh.constant.WXConstant;
import cn.com.jansh.entity.system.IMUserN;
import cn.com.jansh.entity.wechat.AuthAccount;
import cn.com.jansh.mapper.system.IMUserMapper;
import cn.com.jansh.mapper.system.PubsSysBaseMapper;
import cn.com.jansh.mapper.wechat.AuthAccountMapper;
import cn.com.jansh.model.wechat.AuthorizationResult;
import cn.com.jansh.model.wechat.Authorization_Info;
import cn.com.jansh.model.wechat.AuthorizerAccessToken;
import cn.com.jansh.model.wechat.ComponentAccessToken;
import cn.com.jansh.model.wechat.PreAuthCode;
import cn.com.jansh.model.wxdecode.WXBizMsgCrypt;
import cn.com.jansh.service.wechat.WxAuthService;
import cn.com.jansh.utils.HttpClientUtil;
import cn.com.jansh.vo.AjaxObj;

/**
 * 第三方公众号授权服务
 * @author Mr.wong
 */
@Service
public class WxAuthServiceImpl implements WxAuthService {

	private final static Logger logger = LogManager.getLogger(WxAuthServiceImpl.class);
	@Autowired
	private PubsSysBaseMapper systemBaseMapper;
	@Autowired
	private AuthAccountMapper authAccountMapper;
	@Autowired
	private IMUserMapper imusermapper;
	@Autowired
	private GlobalProperties globalProperties;

	@Override
	public String getVerifyTicket(String component_token, String encodingAesKey, String component_appId,
			String msgSignature, String timestamp, String nonce, String formXML) throws AppException {
		logger.info("获取微信推送的票据");
		try {
			// 获取明文
			WXBizMsgCrypt pc = new WXBizMsgCrypt(component_token, encodingAesKey, component_appId);
			String decryptMsg = pc.decryptMsg(msgSignature, timestamp, nonce, formXML);
			// 将明文中的票据取出
			String ticket = getXMLdata(decryptMsg, "ComponentVerifyTicket");
			logger.info("获取到的ticket: " + ticket);
			return ticket;
		} catch (Exception e) {
			logger.error("获取微信推送的票据异常{}", e);
			throw new AppException(AppErrorCode.E210000);
		}
	}

	@Override
	public String getComponentAccessToken(String component_appId, String component_appSecret, String verifyTicket) throws AppException {
		logger.info("获取第三方公众号接口调用凭据");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> component_token_param = new HashMap<>();
		component_token_param.put("component_appid", component_appId);
		component_token_param.put("component_appsecret", component_appSecret);
		component_token_param.put("component_verify_ticket", verifyTicket);
		String component_token_url = "https://api.weixin.qq.com/cgi-bin/component/api_component_token";
		try {
			String component_access_token = HttpClientUtil.httpsPost(component_token_url,
					mapper.writeValueAsString(component_token_param));
			ComponentAccessToken accessTokenObj = mapper.readValue(component_access_token, ComponentAccessToken.class);
			component_access_token = accessTokenObj.getComponent_access_token();
			logger.info("第三方平台接口调用权限: " + component_access_token);
			return component_access_token;
		} catch (Exception e) {
			logger.error("获取第三方公众号接口调用凭据异常{}", e);
			throw new AppException(AppErrorCode.E200000);
		}
	}

	@Override
	public String getPreAuthCode(String component_access_token, String component_appid) throws AppException {
		logger.info("获取预授权码 和 授权URL");
		String pre_Auth_Url = "https://api.weixin.qq.com/cgi-bin/component/api_create_preauthcode?component_access_token="
				+ component_access_token;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> component_pre_param = new HashMap<>();
		component_pre_param.put("component_appid", component_appid);

		try {
			String pre_Auth_xml = HttpClientUtil.httpsPost(pre_Auth_Url,
					mapper.writeValueAsString(component_pre_param));
			PreAuthCode fromJson = mapper.readValue(pre_Auth_xml, PreAuthCode.class);
			String pre_Auth_code = fromJson.getPre_auth_code();
			logger.info("预授权码是: " + pre_Auth_code);
			return pre_Auth_code;
		} catch (Exception e) {
			logger.error("获取预授权码异常{}", e);
			throw new AppException(AppErrorCode.E200000);
		}
	}

	@Override
	public String getAuthURL() throws AppException {
		AjaxObj ajaxObj = new AjaxObj();
		/**
		 * 从数据库中获取第三方平台参数 component_appid
		 */
		PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);

		/**
		 * 获取登录用户的id
		 */
		UserDetail userDetail = AppUtil.getUserDetail();
		String userid = userDetail.getUserid();
		IMUserN user = imusermapper.selectNewByUserid(userid);
		/**
		 * 获取accesstoken和预授权码
		 */
		String component_access_token = getComponentAccessToken();
		String pre_auth_code = getPreAuthCode(component_access_token, component_appid.getSysBaseValue());
		logger.info("获取授权URL");
		/**
		 * 授权URL 格式 "https://mp.weixin.qq.com/cgi-bin/componentloginpage?" +
		 * "component_appid="+component_appid+"&pre_auth_code="+pre_auth_code+"&redirect_uri="+redirect_uri;
		 */
		StringBuilder builder = new StringBuilder();
		builder.append("https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=");
		builder.append(component_appid.getSysBaseValue());
		builder.append("&pre_auth_code=");
		builder.append(pre_auth_code);
		builder.append("&redirect_uri=");
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		builder.append(globalProperties.getAuthRedirectURI()+request.getContextPath()+"/wxmanage/forwardauthover?orgid=");
		builder.append(user.getOrgid());
		String user_Auth_Url = builder.toString();
		logger.info("授权页网址是: " + user_Auth_Url);
		ajaxObj.setSuccess(true);
		ajaxObj.setResult(1);
		ajaxObj.setObj(user_Auth_Url);
		return user_Auth_Url;
	}

	@Override
	public AuthorizationResult getClientAccessToken(String component_access_token, String component_appid,
			String authorization_code) throws AppException {
		logger.info("拿到用户的授权码获取用户的accesstoken");
		String client_token_url = "https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token="
				+ component_access_token;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> client_token_param = new HashMap<>();
		client_token_param.put("component_appid", component_appid);
		client_token_param.put("authorization_code", authorization_code);

		try {
			String client_token_json = HttpClientUtil.httpsPost(client_token_url,
					mapper.writeValueAsString(client_token_param));
			logger.info("客户公众号信息: " + client_token_json);
			AuthorizationResult authorizationInfo = mapper.readValue(client_token_json, AuthorizationResult.class);
			return authorizationInfo;
		} catch (Exception e) {
			logger.error("获取用户的accesstoken异常{}", e);
			throw new AppException(AppErrorCode.E200000);
		}
	}

	@Override
	public Map<String, Object> getAuthorizerInfo(String component_access_token, String component_appid,
			String authorizer_appid) throws AppException {
		logger.info("获取授权公众号的基本信息");
		String authorizer_info_url = "https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token="
				+ component_access_token;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> authorizer_info_param = new HashMap<>();
		authorizer_info_param.put("component_appid", component_appid);
		authorizer_info_param.put("authorizer_appid", authorizer_appid);

		try {
			String authorizer_info_json = HttpClientUtil.httpsPost(authorizer_info_url,
					mapper.writeValueAsString(authorizer_info_param));
			logger.info("客户公众号基本信息: " + authorizer_info_json);
			Map<String, Object> readMapObject = JsonUtil.readMapObject(authorizer_info_json);
			logger.info(readMapObject.toString());
			return readMapObject;
		} catch (Exception e) {
			logger.error("获取授权公众号的基本信息异常{}	", e);
			throw new AppException(AppErrorCode.E200000);
		}
	}

	@Override
	public AuthorizerAccessToken getAuthRefreshToken(String component_access_token, String component_appid,
			String authorizer_appid, String authorizer_refresh_token) throws AppException {
		logger.info("获取授权公众号的刷新凭据");
		String auth_refresh_url = "https://api.weixin.qq.com/cgi-bin/component/api_authorizer_token?component_access_token="
				+ component_access_token;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> auth_refresh_param = new HashMap<>();
		auth_refresh_param.put("component_appid", component_appid);
		auth_refresh_param.put("authorizer_appid", authorizer_appid);
		auth_refresh_param.put("authorizer_refresh_token", authorizer_refresh_token);
		try {
			String authorizer_info_json = HttpClientUtil.httpsPost(auth_refresh_url,
					mapper.writeValueAsString(auth_refresh_param));
			logger.info("更新用户accesstoken: " + authorizer_info_json);
			AuthorizerAccessToken authAccessToken = mapper.readValue(authorizer_info_json, AuthorizerAccessToken.class);
			logger.info(authAccessToken.toString());
			return authAccessToken;
		} catch (Exception e) {
			logger.error("获取授权公众号的刷新凭据异常", e);
			throw new AppException(AppErrorCode.E200000);
		}
	}

	@Override
	public String getComponentAuthOnline(String authorizer_appid, String redirect_uri, String state,
			String component_appid) {
		/**
		 * URL 格式
		 * "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+authorizer_appid+
		 * "&redirect_uri="+redirect_uri+"&response_type=code&scope=snsapi_userinfo&state="+state+
		 * "&component_appid="+component_appid+"#wechat_redirect";
		 */
		StringBuilder builder = new StringBuilder();
		builder.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		builder.append(authorizer_appid);
		builder.append("&redirect_uri=");
		builder.append(redirect_uri);
		builder.append("&response_type=code&scope=snsapi_userinfo&state=");
		builder.append(state);
		builder.append("&component_appid=");
		builder.append(component_appid);
		builder.append("#wechat_redirect");
		return builder.toString();
	}

	@Override
	public String getXMLdata(String xmldata, String key) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmldata);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);
			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName(key);
			return nodelist1.item(0).getTextContent();
	}

	@Override
	public void updateTicket(String msgSignature, String timestamp, String nonce, String encrypt) throws AppException {
		/**
		 * 从数据库获取第三方平台基本信息参数
		 */
		PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);
		PubsSysBase component_token = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_NTOKEN_KEY);
		PubsSysBase component_encodaes = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_ENCODAES_KEY);
		PubsSysBase component_ticket = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TICKET_KEY);
		/**
		 * 拿到基本信息
		 */
		String encodingAesKey = component_encodaes.getSysBaseValue();
		String token = component_token.getSysBaseValue();
		String appId = component_appid.getSysBaseValue();
		String format = WXConstant.ENCRYPTFORMAT;
		String formXML = String.format(format, encrypt);
		/**
		 * 获取票据
		 */
		String verifyTicket = getVerifyTicket(token, encodingAesKey, appId, msgSignature, timestamp, nonce, formXML);
		/**
		 * 更新票据对象、更新数据库参数
		 */
		if (StringUtils.isNotBlank(verifyTicket)) {
			component_ticket.setSysBaseValue(verifyTicket);
			systemBaseMapper.update(component_ticket);
		}
	}

	@Override
	public String getComponentAccessToken() throws AppException  {
		Date current = new Date();
		long currentNum = current.getTime();
		/**
		 * 获取上次刷新时间
		 */
		PubsSysBase component_tokenupdate = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TOKENUPDATE_KEY);
		long updateNum = Long.valueOf(component_tokenupdate.getSysBaseValue());
		/**
		 * 时间超过0.5个小时就重新请求accesstoken
		 */
		if (currentNum - updateNum > 1000 * 3600 * 0.5) {
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
			String componentAccessToken = getComponentAccessToken(component_appid.getSysBaseValue(),
					component_appSecret.getSysBaseValue(), component_ticket.getSysBaseValue());
			logger.info("获取最新accesstoken："+componentAccessToken);
			/**
			 * 如果获取accesstoken 为空则返回以前的accesstoken
			 * 如果accesstoken 不为空
			 * 数据库更新accesstoken、更新时间
			 */
			if (StringUtils.isNotBlank(componentAccessToken)) {
				component_access_token.setSysBaseValue(componentAccessToken);
				component_tokenupdate.setSysBaseValue(String.valueOf(currentNum));
				systemBaseMapper.update(component_access_token);
				systemBaseMapper.update(component_tokenupdate);
				return componentAccessToken;
			}else{
				logger.info("获取第三方接口调用权限错误！");
				throw new AppException(AppErrorCode.E200000);
			}
			
		} else {
			/**
			 * 不超时直接拿数据
			 */
			PubsSysBase component_access_token = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_TOKEN_KEY);
			return component_access_token.getSysBaseValue();
		}
	}

	public String getAuthAccessToken(String appid) throws AppException {

		AuthAccount authAccount = authAccountMapper.selectOneByAppid(appid);
		LocalDateTime updateTime = LocalDateTime.parse(authAccount.getUpdatetime(), DateUtil.formatter_DateTimestamp);
		LocalDateTime currentTime = DateUtil.getLocalDateTime();
		// 90单位为分钟，60单位为秒
		if (Duration.between(updateTime, currentTime).getSeconds() > 60 * 90) {
			PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);
			String component_access_token = getComponentAccessToken();
			AuthorizerAccessToken authRefreshToken = getAuthRefreshToken(component_access_token,
					component_appid.getSysBaseValue(), appid, authAccount.getAuth_refresh_token());
			if (authRefreshToken != null) {
				authAccount.setAuth_access_token(authRefreshToken.getAuthorizer_access_token());
				authAccount.setAuth_refresh_token(authRefreshToken.getAuthorizer_refresh_token());
				authAccountMapper.update(authAccount);
				return authRefreshToken.getAuthorizer_access_token();
			} else {
				logger.error("获取公众号接口调用权限错误!");
				throw new AppException(AppErrorCode.E200000);
			}
		} else {
			return authAccount.getAuth_access_token();
		}
	}

	/**
	 * 用户授权后获取授权公众号的基本信息并存到数据库
	 * @throws AppException 
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void getAuthAccountInfo(String auth_code, String orgid) throws AppException {

		/**
		 * 数据库获取第三方公众号基本信息
		 */
		PubsSysBase component_appid = systemBaseMapper.selectOneByBaseId(WXConstant.COMPONENT_APPID_KEY);
		String component_access_token = getComponentAccessToken();
		/**
		 * 获取授权公众号信息
		 */
		AuthorizationResult authResult = getClientAccessToken(component_access_token,component_appid.getSysBaseValue(), auth_code);
		if (authResult != null) {
			Authorization_Info authorization_info = authResult.getAuthorization_info();
			String auth_access_token = authorization_info.getAuthorizer_access_token();
			String auth_appid = authorization_info.getAuthorizer_appid();
			String auth_refresh_token = authorization_info.getAuthorizer_refresh_token();
			String timetamp = DateUtil.getDateTimestamp();
			Map<String,Object> authorizerInfo = getAuthorizerInfo(component_access_token, component_appid.getSysBaseValue(), auth_appid);
			logger.info("第三方平台信息是: " + authResult);
			AuthAccount existApp = authAccountMapper.selectOneByAppid(auth_appid);
			if (existApp != null && StringUtils.isNotBlank(existApp.getAppid())) {
				Map<String,Object> authorizer_info = (Map<String, Object>) authorizerInfo.get("authorizer_info");
				Map<String,Object> serviceType_info = (Map<String,Object>)authorizer_info.get("service_type_info");
				Map<String,Object> verifyType_info = (Map<String,Object>)authorizer_info.get("verify_type_info");
				int serviceType = (int) serviceType_info.get("id");
				int verifyType = (int) verifyType_info.get("id");

				existApp.setOrgid(orgid);
				existApp.setAuth_code(auth_code);
				existApp.setAuth_access_token(auth_access_token);
				existApp.setAuth_refresh_token(auth_refresh_token);
				existApp.setUpdatetime(timetamp);

				existApp.setNickName((String) authorizer_info.get("nick_name"));
				existApp.setHeadImg((String)authorizer_info.get("head_img"));
				existApp.setServiceType(String.valueOf(serviceType));
				existApp.setVerifyType(String.valueOf(verifyType));
				existApp.setUserName((String)authorizer_info.get("user_name"));
				existApp.setAlias((String)authorizer_info.get("alias"));
				existApp.setQrcodeUrl((String)authorizer_info.get("qrcode_url"));
				existApp.setStatus("1");
				authAccountMapper.update(existApp);
				createAppLogo(auth_appid, (String)authorizer_info.get("head_img"));
			} else {
				Map<String, Object> authorizer_info = (Map<String, Object>) authorizerInfo.get("authorizer_info");
				Map<String,Object> serviceType_info = (Map<String,Object>)authorizer_info.get("service_type_info");
				Map<String,Object> verifyType_info = (Map<String,Object>)authorizer_info.get("verify_type_info");
				
				int serviceType = (int) serviceType_info.get("id");
				int verifyType = (int) verifyType_info.get("id");
				
				AuthAccount authAccount = new AuthAccount();
				authAccount.setAppid(auth_appid);
				authAccount.setOrgid(orgid);
				authAccount.setAuth_code(auth_code);
				authAccount.setAuth_access_token(auth_access_token);
				authAccount.setAuth_refresh_token(auth_refresh_token);
				authAccount.setCreatetime(timetamp);
				authAccount.setUpdatetime(timetamp);

				authAccount.setNickName((String) authorizer_info.get("nick_name"));
				authAccount.setHeadImg((String)authorizer_info.get("head_img"));
				authAccount.setServiceType(String.valueOf(serviceType));
				authAccount.setVerifyType(String.valueOf(verifyType));
				authAccount.setUserName((String)authorizer_info.get("user_name"));
				authAccount.setAlias((String)authorizer_info.get("alias"));
				authAccount.setQrcodeUrl((String)authorizer_info.get("qrcode_url"));
				authAccount.setStatus("1");
				authAccountMapper.insert(authAccount);
				createAppLogo(auth_appid, (String)authorizer_info.get("head_img"));
			}
			
		}
	}
	private boolean createAppLogo(String appid, String headImg) {
		logger.info("开始创建公众号logo" + appid);
		StringBuilder builder = new StringBuilder();
		builder.append(globalProperties.getAppLogoPath());
		builder.append(appid);
		builder.append(".png");
		String logoPath = builder.toString();
		logger.info("公众号logo路径" + logoPath);
		File dir = new File(globalProperties.getAppLogoPath());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File logo = new File(logoPath);
		if (!logo.exists()) {
			try {
				logo.createNewFile();
				boolean downflag = downloadImage(headImg, logoPath);
				if (downflag) {
					logger.info("logo下载成功");
					return true;
				} else {
					logger.error("logo图片下载失败" + headImg);
					return false;
				}
			} catch (IOException e) {
				logger.error("创建文件异常", e);
				return false;
			}
		}

		return false;
	}

	private boolean downloadImage(String fromUrl, String toPath) {
		try {
			URL url = new URL(fromUrl);
			File outFile = new File(toPath);
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
			return true;
		} catch (Exception e) {
			logger.error("图片下载失败", e);
			return false;
		}
	}
}
