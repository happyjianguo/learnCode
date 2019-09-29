package com.pay.bank.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class TBankInfoBean {
	private String bank_code;
	private String bank_name;
	private String bank_short_name;
	private String logo;
	
	public TBankInfoBean(){
		
	}
	
	public TBankInfoBean(HashMap record){
		if(record.get("bank_code") == null){
			this.setBank_code("");
		} else {
			this.setBank_code(StringUtils.innerToOuter((String)record.get("bank_code")).trim());
		}
		if(record.get("bank_name") == null){
			this.setBank_name("");
		} else {
			this.setBank_name(StringUtils.innerToOuter((String)record.get("bank_name")).trim());
		}
		if(record.get("bank_short_name") == null){
			this.setBank_short_name("");
		} else {
			this.setBank_short_name(StringUtils.innerToOuter((String)record.get("bank_short_name")).trim());
		}
		if(record.get("logo") == null){
			this.setLogo("");
		} else {
			this.setLogo(StringUtils.innerToOuter((String)record.get("logo")).trim());
		}	
	}
	
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
	
}
