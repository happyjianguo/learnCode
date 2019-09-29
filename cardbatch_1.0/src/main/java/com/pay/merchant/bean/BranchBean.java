package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class BranchBean {
	
	private String brncode;
	private String descr;
	
	public BranchBean(){
		
	}
	
	public BranchBean (HashMap record){
		if(record.get("brncode") == null){
			this.setBrncode("");
		} else {
			this.setBrncode(StringUtils.innerToOuter((String)record.get("brncode")).trim());
		}
		if(record.get("descr") == null){
			this.setDescr("");
		} else {
			this.setDescr(StringUtils.innerToOuter((String)record.get("descr")).trim());
		}
	}

	public String getBrncode() {
		return brncode;
	}

	public void setBrncode(String brnCode) {
		this.brncode = brnCode;
	}

	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
