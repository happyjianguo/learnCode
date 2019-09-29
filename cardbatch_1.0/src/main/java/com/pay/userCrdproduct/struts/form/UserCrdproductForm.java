package com.pay.userCrdproduct.struts.form;

import org.apache.struts.action.ActionForm;

public class UserCrdproductForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String user_code;
	private String user_name;
	private String crdproduct;
	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
		
}
