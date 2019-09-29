package cn.com.jansh.dao.entity;

/**
 * 授权公众号接口调用权限实体
 * @author Mr.wong
 * @version 1.0
 */
public class AuthorizerAccessToken {

	//授权公众号接口调用权限
	private String authorizer_access_token;
	//有效时间
	private int expires_in;
	//接口刷新权限
	private String authorizer_refresh_token;
	
	public AuthorizerAccessToken() {
		
	}

	public String getAuthorizer_access_token() {
		return authorizer_access_token;
	}

	public void setAuthorizer_access_token(String authorizer_access_token) {
		this.authorizer_access_token = authorizer_access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public String getAuthorizer_refresh_token() {
		return authorizer_refresh_token;
	}

	public void setAuthorizer_refresh_token(String authorizer_refresh_token) {
		this.authorizer_refresh_token = authorizer_refresh_token;
	}

	@Override
	public String toString() {
		return "[authorizer_access_token=" + authorizer_access_token + ", expires_in="
				+ expires_in + ", authorizer_refresh_token=" + authorizer_refresh_token + "]";
	}
	
	
}
