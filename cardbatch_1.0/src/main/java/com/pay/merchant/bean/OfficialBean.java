package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class OfficialBean {
	
	private String official;
	private String descr;
	
	public OfficialBean(){
		
	}
	
	public OfficialBean (HashMap record){
		if(record.get("official") == null){
			this.setOfficial("");
		} else {
			this.setOfficial(StringUtils.innerToOuter((String)record.get("official")).trim());
		}
		if(record.get("descr") == null){
			this.setDescr("");
		} else {
			this.setDescr(StringUtils.innerToOuter((String)record.get("descr")).trim());
		}
	}
	
	public String getOfficial() {
		return official;
	}
	public void setOfficial(String official) {
		this.official = official;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
