package cn.yufu.system.modules.cortexs.entity;

import java.io.Serializable;



public class SysParameterBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String paramId;
	private String paramType;
	private String paramName;
	private String paramValue;
	private String paramNotes;
	private String parentId;
	private String enable;
	
	public SysParameterBean(){
		
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamNotes() {
		return paramNotes;
	}

	public void setParamNotes(String paramNotes) {
		this.paramNotes = paramNotes;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	
}
