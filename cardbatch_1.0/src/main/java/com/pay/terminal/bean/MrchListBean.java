package com.pay.terminal.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class MrchListBean {
	
	private String id;
	private String name;
	private String inst_id;
	private String mrchno;
	
	public MrchListBean(){
		
	}
	
	public MrchListBean (HashMap record){
		if(record.get("id") == null){
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String)record.get("id")).trim());
		}
		if(record.get("name") == null){
			this.setName("");
		} else {
			this.setName(StringUtils.innerToOuter((String)record.get("name")).trim());
		}
		if(record.get("inst_id") == null){
			this.setInst_id("");
		} else {
			this.setInst_id(StringUtils.innerToOuter((String)record.get("inst_id")).trim());
		}
		if(record.get("mrchno") == null){
			this.setMrchno("");
		} else {
			this.setMrchno(StringUtils.innerToOuter((String)record.get("mrchno")).trim());
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInst_id() {
		return inst_id;
	}

	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}
	
	
}
