package com.pay.userCrdproduct.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class UserCrdproductBean {
	private String user_code;
	private String user_name;
	private String crdproduct;
	
	public UserCrdproductBean(){
		
	}
	
	public UserCrdproductBean(HashMap record){
		if(record.get("user_code") == null){
			this.setUser_code("");
		} else {
			this.setUser_code(StringUtils.innerToOuter((String)record.get("user_code")).trim());
		}
		if(record.get("user_name") == null){
			this.setUser_name("");
		} else {
			this.setUser_name(StringUtils.innerToOuter((String)record.get("user_name")).trim());
		}
		if(record.get("crdproduct") == null){
			this.setCrdproduct("");
		} else {
			this.setCrdproduct(StringUtils.innerToOuter((String)record.get("crdproduct")).trim());
		}
	}
	
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String userCode) {
		user_code = userCode;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getCrdproduct() {
		return crdproduct;
	}
	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}

	
}
