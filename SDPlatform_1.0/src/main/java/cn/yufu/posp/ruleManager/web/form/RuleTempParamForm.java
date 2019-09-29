package cn.yufu.posp.ruleManager.web.form;

import java.util.List;

public class RuleTempParamForm {

	// Fields

	private String ruleTempNo;
	private String ruleTempReg;
	private String ruleTempRegType;
	private String ruleTempRegDefault;
	private String ruleTempRegList;
	private String ruleTempParamId;
	private String status;
	private String version;
	private String remark;
	private List<RuleTempParamListForm> ruleTempParamList;

	// Property accessors

	public String getRuleTempNo() {
		return this.ruleTempNo;
	}

	public void setRuleTempNo(String ruleTempNo) {
		this.ruleTempNo = ruleTempNo;
	}

	public String getRuleTempReg() {
		return this.ruleTempReg;
	}

	public void setRuleTempReg(String ruleTempReg) {
		this.ruleTempReg = ruleTempReg;
	}

	public String getRuleTempRegType() {
		return this.ruleTempRegType;
	}

	public void setRuleTempRegType(String ruleTempRegType) {
		this.ruleTempRegType = ruleTempRegType;
	}

	public String getRuleTempRegDefault() {
		return this.ruleTempRegDefault;
	}

	public void setRuleTempRegDefault(String ruleTempRegDefault) {
		this.ruleTempRegDefault = ruleTempRegDefault;
	}

	public String getRuleTempRegList() {
		return this.ruleTempRegList;
	}

	public void setRuleTempRegList(String ruleTempRegList) {
		this.ruleTempRegList = ruleTempRegList;
	}

	public String getRuleTempParamId() {
		return this.ruleTempParamId;
	}

	public void setRuleTempParamId(String ruleTempParamId) {
		this.ruleTempParamId = ruleTempParamId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<RuleTempParamListForm> getRuleTempParamList() {
		return ruleTempParamList;
	}

	public void setRuleTempParamList(
			List<RuleTempParamListForm> ruleTempParamList) {
		this.ruleTempParamList = ruleTempParamList;
	}

}
