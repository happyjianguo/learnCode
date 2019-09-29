package cn.com.jansh.model.wechat;

/**   
 * 预授权码实体
 * @author Mr.wong
 * @version 1.0
 */
 
public class PreAuthCode {

	//预授权码
	private String pre_auth_code;
	//有效时间
	private String expires_in;
	public String getPre_auth_code() {
		return pre_auth_code;
	}
	public void setPre_auth_code(String pre_auth_code) {
		this.pre_auth_code = pre_auth_code;
	}
	public String getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}
	public PreAuthCode() {
	}
	public PreAuthCode(String pre_auth_code, String expires_in) {
		super();
		this.pre_auth_code = pre_auth_code;
		this.expires_in = expires_in;
	}
	
	
	
}
