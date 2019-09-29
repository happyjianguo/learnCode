/**
 * OfferTokenModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月31日
 */
package cn.com.jansh.model.wechat;

/**
 * 对外提供信息model
 * @author Mr.wong
 * @version 1.0
 */
public class OfferTokenModel {

	/** 游戏绑定公众号id **/ 
	private String appid;
	/** 游戏绑定公众号token **/ 
	private String authAccessToken;
	/** 第三方公众平台原始id **/ 
	private String component_appid;
	/** 第三方平台接口调用权限 **/ 
	private String accesstoken;
	
	
	public OfferTokenModel() {
		
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public String getComponent_appid() {
		return component_appid;
	}
	public void setComponent_appid(String component_appid) {
		this.component_appid = component_appid;
	}
	public String getAuthAccessToken() {
		return authAccessToken;
	}
	public void setAuthAccessToken(String authAccessToken) {
		this.authAccessToken = authAccessToken;
	}
	@Override
	public String toString() {
		return "OfferTokenModel [appid=" + appid + ", accesstoken=" + accesstoken + "]";
	}
}
