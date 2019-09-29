package com.pay.sysParameter.struts.form;

import org.apache.struts.action.ActionForm;

public class SysParameterForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String param_id;
	private String param_type;
	private String param_name;
	private String param_value;
	private String param_notes;
	private String parent_id;
	private String enable;
	private String is_enablement;		//选择销售点后可对其编辑是否开卡，默认是“是:1,否:0”
	
	private String queryParamType;
	private String queryParamName;	
	private String queryParentId;
	private String queryEnable;
	private String queryIs_enablement;
	
	
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
		return parent_id;
	}
	public void setParent_id(String parentId) {
		parent_id = parentId;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getQueryParamType() {
		return queryParamType;
	}
	public void setQueryParamType(String queryParamType) {
		this.queryParamType = queryParamType;
	}
	public String getQueryParamName() {
		return queryParamName;
	}
	public void setQueryParamName(String queryParamName) {
		this.queryParamName = queryParamName;
	}
	public String getQueryParentId() {
		return queryParentId;
	}
	public void setQueryParentId(String queryParentId) {
		this.queryParentId = queryParentId;
	}
	public String getQueryEnable() {
		return queryEnable;
	}
	public void setQueryEnable(String queryEnable) {
		this.queryEnable = queryEnable;
	}
	public String getIs_enablement() {
		return is_enablement;
	}
	public void setIs_enablement(String is_enablement) {
		this.is_enablement = is_enablement;
	}
	public String getQueryIs_enablement() {
		return queryIs_enablement;
	}
	public void setQueryIs_enablement(String queryIs_enablement) {
		this.queryIs_enablement = queryIs_enablement;
	}
}
