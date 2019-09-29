package com.pay.bank.struts.form;

import org.apache.struts.action.ActionForm;

public class TBankInfoForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String bank_code;
	private String bank_name;
	private String bank_short_name;
	private String logo;
	
	
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_short_name() {
		return bank_short_name;
	}
	public void setBank_short_name(String bank_short_name) {
		this.bank_short_name = bank_short_name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
}
