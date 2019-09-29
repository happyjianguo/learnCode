package cn.com.jansh.model.wechat;

/**
 * 授权结果实体
 * @author Mr.wong
 * @version 1.0
 */
public class AuthorizationResult {

	private Authorization_Info authorization_info;

	public AuthorizationResult() {
	}
	
	public Authorization_Info getAuthorization_info() {
		return authorization_info;
	}

	public void setAuthorization_info(Authorization_Info authorization_info) {
		this.authorization_info = authorization_info;
	}

	@Override
	public String toString() {
		return "[authorization_info=" + authorization_info + "]";
	}
	
	
	
}
