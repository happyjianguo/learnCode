package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class InstBean {
	
	private String id;
	private String descr;

	public InstBean(){
		
	}
	
	public InstBean (HashMap record){
		if(record.get("id") == null){
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String)record.get("id")).trim());
		}
		if(record.get("descr") == null){
			this.setDescr("");
		} else {
			this.setDescr(StringUtils.innerToOuter((String)record.get("descr")).trim());
		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
