package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class CurrencyBean {
	
	private String currcode;
	private String descr;
	
	public CurrencyBean(){
		
	}
	
	public CurrencyBean (HashMap record){
		if(record.get("currcode") == null){
			this.setCurrcode("");
		} else {
			this.setCurrcode(StringUtils.innerToOuter((String)record.get("currcode")).trim());
		}
		if(record.get("descr") == null){
			this.setDescr("");
		} else {
			this.setDescr(StringUtils.innerToOuter((String)record.get("descr")).trim());
		}
	}

	public String getCurrcode() {
		return currcode;
	}

	public String getDescr() {
		return descr;
	}

	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	
}
