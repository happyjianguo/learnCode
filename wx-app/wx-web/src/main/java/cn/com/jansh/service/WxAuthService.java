package cn.com.jansh.service;

/**
 * 第三方公众号授权接口
 */
public interface WxAuthService {
	/**
	 * 获取授权公众号accesstoken
	 * @return
	 * 
	 */
	public String getAuthAccessToken(String appid);
}
