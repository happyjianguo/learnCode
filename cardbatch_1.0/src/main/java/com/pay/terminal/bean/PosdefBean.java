package com.pay.terminal.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class PosdefBean {
	
	private String typeid;
	private String vendor;
	private String termtype;
	
	public PosdefBean(){
		
	}
	
	public PosdefBean (HashMap record){
		if(record.get("typeid") == null){
			this.setTypeid("");
		} else {
			this.setTypeid(StringUtils.innerToOuter((String)record.get("typeid")).trim());
		}
		if(record.get("vendor") == null){
			this.setVendor("");
		} else {
			this.setVendor(StringUtils.innerToOuter((String)record.get("vendor")).trim());
		}
		if(record.get("termtype") == null){
			this.setTermtype("");
		} else {
			this.setTermtype(StringUtils.innerToOuter((String)record.get("termtype")).trim());
		}
	}
	public String getTypeid() {
		return typeid;
	}

	public String getVendor() {
		return vendor;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getTermtype() {
		return termtype;
	}

	public void setTermtype(String termtype) {
		this.termtype = termtype;
	}
	
	
}
