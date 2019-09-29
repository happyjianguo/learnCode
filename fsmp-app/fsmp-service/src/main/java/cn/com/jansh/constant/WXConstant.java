package cn.com.jansh.constant;
/**
 * 第三方平台常量
 * 
 * @author Mr.wong
 */
public class WXConstant {
	
	/*第三方平台appid的key*/
	public static final String COMPONENT_APPID_KEY = "component_appid";
	/*第三方平台secret的key*/
	public static final String COMPONENT_APPSECRET_KEY = "component_appsecret";
	/*第三方平台票据的key*/
	public static final String COMPONENT_TICKET_KEY = "component_ticket";
	/*第三方平台接口调用权限更新时间的key*/
	public static final String COMPONENT_TOKENUPDATE_KEY = "component_token_update";
	/*第三方平台接口调用权限的key*/
	public static final String COMPONENT_TOKEN_KEY  = "component_access_token";
	/*第三方平台token的key*/
	public static final String COMPONENT_NTOKEN_KEY = "component_token";
	/*第三方平台aes秘钥的key*/
	public static final String COMPONENT_ENCODAES_KEY = "component_encodaes";
	/*加密字符串模板*/
	public static final String ENCRYPTFORMAT  = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";

}
