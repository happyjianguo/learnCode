package cn.com.jansh.model.wechat;

/**
 * 授权公众号基本信息实体
 * @author Mr.wong
 * @version 1.0
 */
public class Authorizer {
	
	private AuthorizerInfo authorizer_info;
	private Authorization_Info authorization_info;
	
	public Authorizer() {
		
	}

	public AuthorizerInfo getAuthorizer_info() {
		return authorizer_info;
	}

	public void setAuthorizer_info(AuthorizerInfo authorizer_info) {
		this.authorizer_info = authorizer_info;
	}

	public Authorization_Info getAuthorization_info() {
		return authorization_info;
	}

	public void setAuthorization_info(Authorization_Info authorization_info) {
		this.authorization_info = authorization_info;
	}

	@Override
	public String toString() {
		return "[authorizer_info=" + authorizer_info + ", authorization_info="
				+ authorization_info + "]";
	}
	
	
	
}
