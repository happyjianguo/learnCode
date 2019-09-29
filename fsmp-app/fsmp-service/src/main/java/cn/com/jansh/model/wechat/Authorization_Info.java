package cn.com.jansh.model.wechat;

import java.util.ArrayList;

import cn.com.jansh.model.wxdecode.FuncScope;

/**
 * 授权账号信息实体
 * @author Mr.wong
 * @version 1.0
 */
 
public class Authorization_Info {

	//授权账号的原始ID
	private String authorizer_appid;
	//授权账号的接口调用权限
	private String authorizer_access_token;
	//有效时间
	private int expires_in;
	//刷新用的token
	private String authorizer_refresh_token;
	//可以调用的权限
	private ArrayList<FuncScope> func_info;
	
	public Authorization_Info() {
	}
	public String getAuthorizer_appid() {
		return authorizer_appid;
	}
	public void setAuthorizer_appid(String authorizer_appid) {
		this.authorizer_appid = authorizer_appid;
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
	public ArrayList<FuncScope> getFunc_info() {
		return func_info;
	}
	public void setFunc_info(ArrayList<FuncScope> func_info) {
		this.func_info = func_info;
	}
	@Override
	public String toString() {
		return "[authorizer_appid=" + authorizer_appid + ", authorizer_access_token="
				+ authorizer_access_token + ", expires_in=" + expires_in + ", authorizer_refresh_token="
				+ authorizer_refresh_token + ", func_info=" + func_info + "]";
	}
	
	
	
}
