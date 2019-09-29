/**
 * GlobalPreperties.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:duanmuyn 2016年10月20日
 */
package cn.com.jansh.component.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * global配置文件属性
 * @author duanmuyn
 * @version 1.0
 */
@Service
public class GlobalProperties {
	
	@Value("${cfqueryPackurl}")
	private String cfqueryPackurl;
	
	@Value("${cforderurl}")
	private String cforderurl;
	
	@Value("${cfuserid}")
	private String cfuserid;
	
	@Value("${cfapiKey}")
	private String cfapiKey;
	
	@Value("${cfqueryorder}")
	private String cfqueryorder;
	
	@Value("${SmsIp}")
	private String SmsIp;
	
	@Value("${SmsHost}")
	private String SmsHost;
	
	@Value("${AcountSid}")
	private String AcountSid;
	
	@Value("${AuthToken}")
	private String AuthToken;
	
	@Value("${AppId}")
	private String AppId;
	
	@Value("${api_weixin_url}")
	private String apiWeixinURL;// 向微信发送请求的url
	@Value("${wx.create.menus.url}")
	private String menusUrl;
	
	/*********************************************场景化云营销平台调用参数***************************************************/
	
	@Value("${iconServer}")
	private String iconServer;
	
	@Value("${uploadIconPath}")
	private String uploadIconPath;
	
	@Value("${uploadLicensePath}")
	private String uploadLicensePath;
	
	@Value("${defaultRoleid}")
	private String defaultRoleid;
	
	@Value("${sendInterval}")
	private String sendInterval;
	
	@Value("${registerValidModel}")
	private String registerValidModel;
	
	@Value("${retrieveValidModel}")
	private String retrieveValidModel;
	
	@Value("${registerMailModel}")
	private String registerMailModel;
	
	@Value("${modifyValidModel}")
	private String modifyValidModel;
	
	@Value("${registerMailTitle}")
	private String registerMailTitle;
	
	@Value("${downOrderRecordsPath}")
	private String downOrderRecordsPath;
	
	@Value("${pageRecordCount}")
	private String pageRecordCount;
	
	@Value("${imageMaxSize}")
	private String imageMaxSize;
	
	@Value("${imageDefaultName}")
	private String imageDefaultName;
	
	/*********************************************营销活动模板制作***************************************************/
	
	
	/*********************************************营销活动链接二维码制作***************************************************/
	
	@Value("${appLogoPath}")
	private String appLogoPath;
	
	@Value("${gameQRPath}")
	private String gameQRPath;
	/*********************************************游戏系统URI***************************************************/
	
	@Value("${dzpURI}")
	private String dzpURI;
	
	@Value("${gameURI}")
	private String gameURI;
	
	/*********************************************微信第三方平台调用参数***************************************************/
	
	@Value("${authRedirectURI}")
	private String authRedirectURI;
	
	/*********************************************微信系统对接调用参数***************************************************/
	
	@Value("${tokenSecret}")
	private String tokenSecret;
	
	/*********************************************银联支付系统调用参数***************************************************/
	
	@Value("${backURL}")
	private String backURL;
	
	@Value("${currency_pay_url}")
	private String currency_pay_url;
	
	@Value("${currency_query_url}")
	private String currency_query_url;
	
	@Value("${iconExchageRate}")
	private String iconExchageRate;
	
	/****************数据管理-单个活动数据-接口地址*********************************************************/
	//验证URL
	@Value("${acquire}")
	private String acquire;
	
	public String getAcquire() {
		return acquire;
	}

	public String getApiWeixinURL() {
		return apiWeixinURL;
	}

	public String getMenusUrl() {
		return menusUrl;
	}

	public void setMenusUrl(String menusUrl) {
		this.menusUrl = menusUrl;
	}

	/**
	 * @return the cfqueryorder
	 */
	public String getCfqueryorder() {
		return cfqueryorder;
	}

	/**
	 * @param cfqueryorder the cfqueryorder to set
	 */
	public void setCfqueryorder(String cfqueryorder) {
		this.cfqueryorder = cfqueryorder;
	}
	
	/**
	 * @return the cforderurl
	 */
	public String getCforderurl() {
		return cforderurl;
	}

	/**
	 * @return the smsIp
	 */
	public String getSmsIp() {
		return SmsIp;
	}

	/**
	 * @param smsIp the smsIp to set
	 */
	public void setSmsIp(String smsIp) {
		SmsIp = smsIp;
	}

	/**
	 * @return the smsHost
	 */
	public String getSmsHost() {
		return SmsHost;
	}

	/**
	 * @param smsHost the smsHost to set
	 */
	public void setSmsHost(String smsHost) {
		SmsHost = smsHost;
	}

	/**
	 * @return the acountSid
	 */
	public String getAcountSid() {
		return AcountSid;
	}

	/**
	 * @param acountSid the acountSid to set
	 */
	public void setAcountSid(String acountSid) {
		AcountSid = acountSid;
	}

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return AuthToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(String authToken) {
		AuthToken = authToken;
	}

	/**
	 * @return the appId
	 */
	public String getAppId() {
		return AppId;
	}

	/**
	 * @param appId the appId to set
	 */
	public void setAppId(String appId) {
		AppId = appId;
	}

	/**
	 * @param cforderurl the cforderurl to set
	 */
	public void setCforderurl(String cforderurl) {
		this.cforderurl = cforderurl;
	}
	
	/**
	 * @return the cfqueryPackurl
	 */
	public String getCfqueryPackurl() {
		return cfqueryPackurl;
	}

	/**
	 * @param cfqueryPackurl the cfqueryPackurl to set
	 */
	public void setCfqueryPackurl(String cfqueryPackurl) {
		this.cfqueryPackurl = cfqueryPackurl;
	}

	/**
	 * @return the cfuserid
	 */
	public String getCfuserid() {
		return cfuserid;
	}

	/**
	 * @param cfuserid the cfuserid to set
	 */
	public void setCfuserid(String cfuserid) {
		this.cfuserid = cfuserid;
	}

	/**
	 * @return the cfapiKey
	 */
	public String getCfapiKey() {
		return cfapiKey;
	}

	/**
	 * @param cfapiKey the cfapiKey to set
	 */
	public void setCfapiKey(String cfapiKey) {
		this.cfapiKey = cfapiKey;
	}

	public String getUploadIconPath() {
		return uploadIconPath;
	}

	public void setUploadIconPath(String uploadIconPath) {
		this.uploadIconPath = uploadIconPath;
	}

	public String getUploadLicensePath() {
		return uploadLicensePath;
	}

	public void setUploadLicensePath(String uploadLicensePath) {
		this.uploadLicensePath = uploadLicensePath;
	}

	public String getAuthRedirectURI() {
		return authRedirectURI;
	}

	public void setAuthRedirectURI(String authRedirectURI) {
		this.authRedirectURI = authRedirectURI;
	}

	public String getTokenSecret() {
		return tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}

	public String getBackURL() {
		return backURL;
	}

	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}

	public String getCurrency_pay_url() {
		return currency_pay_url;
	}

	public void setCurrency_pay_url(String currency_pay_url) {
		this.currency_pay_url = currency_pay_url;
	}

	public String getIconExchageRate() {
		return iconExchageRate;
	}

	public void setIconExchageRate(String iconExchageRate) {
		this.iconExchageRate = iconExchageRate;
	}

	public String getDefaultRoleid() {
		return defaultRoleid;
	}

	public void setDefaultRoleid(String defaultRoleid) {
		this.defaultRoleid = defaultRoleid;
	}

	public String getSendInterval() {
		return sendInterval;
	}

	public void setSendInterval(String sendInterval) {
		this.sendInterval = sendInterval;
	}

	public String getRegisterValidModel() {
		return registerValidModel;
	}

	public void setRegisterValidModel(String registerValidModel) {
		this.registerValidModel = registerValidModel;
	}

	public String getRegisterMailModel() {
		return registerMailModel;
	}

	public void setRegisterMailModel(String registerMailModel) {
		this.registerMailModel = registerMailModel;
	}

	public String getRegisterMailTitle() {
		return registerMailTitle;
	}

	public void setRegisterMailTitle(String registerMailTitle) {
		this.registerMailTitle = registerMailTitle;
	}

	public String getRetrieveValidModel() {
		return retrieveValidModel;
	}

	public void setRetrieveValidModel(String retrieveValidModel) {
		this.retrieveValidModel = retrieveValidModel;
	}

	public String getDownOrderRecordsPath() {
		return downOrderRecordsPath;
	}

	public void setDownOrderRecordsPath(String downOrderRecordsPath) {
		this.downOrderRecordsPath = downOrderRecordsPath;
	}

	public String getPageRecordCount() {
		return pageRecordCount;
	}

	public void setPageRecordCount(String pageRecordCount) {
		this.pageRecordCount = pageRecordCount;
	}

	public String getImageMaxSize() {
		return imageMaxSize;
	}

	public void setImageMaxSize(String imageMaxSize) {
		this.imageMaxSize = imageMaxSize;
	}

	public String getImageDefaultName() {
		return imageDefaultName;
	}

	public void setImageDefaultName(String imageDefaultName) {
		this.imageDefaultName = imageDefaultName;
	}

	public String getCurrency_query_url() {
		return currency_query_url;
	}

	public void setCurrency_query_url(String currency_query_url) {
		this.currency_query_url = currency_query_url;
	}


	public String getGameURI() {
		return gameURI;
	}

	public void setGameURI(String gameURI) {
		this.gameURI = gameURI;
	}

	public String getModifyValidModel() {
		return modifyValidModel;
	}

	public void setModifyValidModel(String modifyValidModel) {
		this.modifyValidModel = modifyValidModel;
	}
	
	public String getAppLogoPath() {
		return appLogoPath;
	}

	public void setAppLogoPath(String appLogoPath) {
		this.appLogoPath = appLogoPath;
	}

	public String getGameQRPath() {
		return gameQRPath;
	}

	public void setGameQRPath(String gameQRPath) {
		this.gameQRPath = gameQRPath;
	}
	public String getIconServer() {
		return iconServer;
	}
	public void setIconServer(String iconServer) {
		this.iconServer = iconServer;
	}
	public String getDzpURI() {
		return dzpURI;
	}
	public void setDzpURI(String dzpURI) {
		this.dzpURI = dzpURI;
	}
	
}
