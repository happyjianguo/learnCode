package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class TaxBean {

	private String taxcode;
	private String descr;
	
	public TaxBean(){
		
	}
	
	public TaxBean (HashMap record){
		if(record.get("taxcode") == null){
			this.setTaxcode("");
		} else {
			this.setTaxcode(StringUtils.innerToOuter((String)record.get("taxcode")).trim());
		}
		if(record.get("descr") == null){
			this.setDescr("");
		} else {
			this.setDescr(StringUtils.innerToOuter((String)record.get("descr")).trim());
		}
	}
	
	public String getTaxcode() {
		return taxcode;
	}
	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
