/**
 * ComponentAccessToken.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月18日
 */
package cn.com.jansh.dao.entity;

/**
 * 第三方平台接口调用权限实体
 * @author Mr.wong
 * @version 1.0
 */
public class ComponentAccessToken {

	String component_access_token;
	int expires_in;
	
	/**
	 * 
	 */
	public ComponentAccessToken() {
	}

	public String getComponent_access_token() {
		return component_access_token;
	}

	public void setComponent_access_token(String component_access_token) {
		this.component_access_token = component_access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	@Override
	public String toString() {
		return "ComponentAccessToken [component_access_token=" + component_access_token + ", expires_in=" + expires_in
				+ "]";
	}
	
}
