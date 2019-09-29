package cn.com.jansh.service.wechat;

import java.util.Map;

import com.jansh.core.exception.AppException;

import cn.com.jansh.model.wechat.AuthorizationResult;
import cn.com.jansh.model.wechat.AuthorizerAccessToken;
/**
 * 第三方公众号授权接口
 * @author Mr.wong
 */
public interface WxAuthService {

	/**
	 * 第一步
	 * 通过以上参数获取票据
	 * @param 第三方公众号Token component_token
	 * @param 第三方公众号加密串 encodingAesKey
	 * @param 第三方公众号原始ID component_appId
	 * @param 消息签名 msgSignature
	 * @param 时间戳 timestamp
	 * @param 随机数 nonce
	 * @param 格式化加密信息 formXML
	 * @return 
	 * @throws AppException 
	 */
	public String getVerifyTicket(String component_token,String encodingAesKey,String component_appId,
			String msgSignature,String timestamp,String nonce,
			String formXML) throws AppException;
	/**
	 * 第二步
	 * 获取component_access_token
	 * @param 第三方公众号原始ID component_appId
	 * @param 第三方公众号秘钥 component_appSecret
	 * @param 微信推送票据 verifyTicket
	 * @return 
	 * @throws AppException 
	 */
	public String getComponentAccessToken(String component_appId,String component_appSecret, String verifyTicket) throws AppException;
	/**
	 * 
	 * 第三步
	 * 通过第三方accesstoken 获取预授权码 和 授权URL
	 * 
	 * @param 第三方公众号接口调用凭据 component_access_token
	 * @param 第三方公众号原始ID component_appid
	 * @return
	 * @throws AppException 
	 **/
	public String getPreAuthCode(String component_access_token,String component_appid) throws AppException;
	/**
	 * 第四步 
	 * 通过回调uri获取用户公众号授权code
	 * 已经在WxAuthController中实现
	 * 
	 * @param 回调URI redirect_uri
	 * @return
	 * @throws AppException 
	 **/
	public String getAuthURL() throws AppException;
	/**
	 * 第五步
	 * 拿到用户的授权码获取用户的accesstoken
	 * @param 第三方公众号接口调用权限 component_access_token
	 * @param 第三方公众号原始ID component_appid
	 * @param 授权码 authorization_code
	 * @return
	 * @throws AppException 
	 **/
	public AuthorizationResult getClientAccessToken(String component_access_token,String component_appid,
			String authorization_code) throws AppException;
	/**
	 * 第六步
	 * 获取公众号的基本信息
	 * @param 第三方公众号接口调用凭据component_access_token
	 * @param 第三方公众号原始ID component_appid
	 * @param 授权公众号的原始id authorizer_appid
	 * @return
	 * @throws AppException 
	 **/
	public Map<?, ?> getAuthorizerInfo(String component_access_token,String component_appid,
			String authorizer_appid) throws AppException;
	/**
	 * 第七步
	 * 刷新公众号的accesstoken
	 * @param 第三方公众号接口调用权限 component_access_token
	 * @param 第三方公众号原始ID component_appid
	 * @param 授权公众号的原始id authorizer_appid
	 * @param 授权公众号刷新凭据 authorizer_refresh_token
	 * @return
	 * @throws AppException 
	 **/
	public AuthorizerAccessToken getAuthRefreshToken(String component_access_token,String component_appid,
			String authorizer_appid,String authorizer_refresh_token) throws AppException;
	/**
	 * 第三方网上授权
	 * @param 授权公众号的原始id authorizer_appid
	 * @param 授权回调URI redirect_uri
	 * @param 随机数 state
	 * @param 第三方公众号原始id component_appid
	 * @return
	 * 
	 */
	public String getComponentAuthOnline(String authorizer_appid,String redirect_uri,
			String state ,String component_appid);
	/**
	 * 解析XML，获取信息
	 * @param XML字符串 xmldata
	 * @param 字符串节点 key
	 * @return
	 * @throws Exception 
	 * 
	 */
	public String getXMLdata(String xmldata,String key) throws Exception;
	/**
	 * 插入更新微信推送票据
	 * @param 票据值
	 * @return
	 * @throws AppException 
	 * 
	 */
	public void updateTicket(String msgSignature, String timestamp, String nonce,String encrypt) throws AppException;
	/**
	 * 获取第三方平台accesstoken
	 * @return
	 * @throws AppException 
	 * 
	 */
	public String getComponentAccessToken() throws AppException;
	/**
	 * 获取授权公众号信息
	 * @return
	 * @throws AppException 
	 * 
	 */
	public void getAuthAccountInfo(String auth_code,String orgid) throws AppException;
	/**
	 * 获取授权公众号accesstoken
	 * @return
	 * @throws AppException 
	 * 
	 */
	public String getAuthAccessToken(String appid) throws AppException;
}
