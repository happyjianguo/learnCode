package com.pay.sysParameter.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class SysParameterBean {
	private String param_id;
	private String param_type;
	private String param_name;
	private String param_value;
	private String param_notes;
	private String parent_id;
	private String enable;
	private String is_enablement;		//选择销售点后可对其编辑是否开卡，默认是“是:1,否:0”
	
	public SysParameterBean(){
		
	}
	
	public SysParameterBean(HashMap record){
		if(record.get("param_id") == null){
			this.setParam_id("");
		} else {
			this.setParam_id(StringUtils.innerToOuter((String)record.get("param_id")).trim());
		}
		if(record.get("param_type") == null){
			this.setParam_type("");
		} else {
			this.setParam_type(StringUtils.innerToOuter((String)record.get("param_type")).trim());
		}
		if(record.get("param_name") == null){
			this.setParam_name("");
		} else {
			this.setParam_name(StringUtils.innerToOuter((String)record.get("param_name")).trim());
		}
		if(record.get("param_value") == null){
			this.setParam_value("");
		} else {
			this.setParam_value(StringUtils.innerToOuter((String)record.get("param_value")).trim());
		}	
		if(record.get("param_notes") == null){
			this.setParam_notes("");
		} else {
			this.setParam_notes(StringUtils.innerToOuter((String)record.get("param_notes")).trim());
		}
		if(record.get("parent_id") == null){
			this.setParent_id("");
		} else {
			this.setParent_id(StringUtils.innerToOuter((String)record.get("parent_id")).trim());
		}			
		if(record.get("enable") == null){
			this.setEnable("");
		} else {
			this.setEnable(StringUtils.innerToOuter((String)record.get("enable")).trim());
		}	
		if(record.get("is_enablement") == null){
			this.setIs_enablement("");
		} else {
			this.setIs_enablement(StringUtils.innerToOuter((String)record.get("is_enablement")).trim());
		}	
	}

	public String getParam_id() {
		return param_id;
	}

	public void setParam_id(String paramId) {
		param_id = paramId;
	}

	public String getParam_type() {
		return param_type;
	}

	public void setParam_type(String paramType) {
		param_type = paramType;
	}

	public String getParam_name() {
		return param_name;
	}

	public void setParam_name(String paramName) {
		param_name = paramName;
	}

	public String getParam_value() {
		return param_value;
	}

	public void setParam_value(String paramValue) {
		param_value = paramValue;
	}

	public String getParam_notes() {
		return param_notes;
	}

	public void setParam_notes(String paramNotes) {
		param_notes = paramNotes;
	}

	public String getParent_id() {
		if(parent_id==null){
			parent_id="";
		}
		return parent_id;
	}

	public void setParent_id(String parentId) {
		if(parentId==null){
			parentId="";
		}
		parent_id = parentId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getIs_enablement() {
		return is_enablement;
	}

	public void setIs_enablement(String is_enablement) {
		this.is_enablement = is_enablement;
	}
	
}
