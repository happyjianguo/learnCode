package cn.yufu.posp.ruleManager.web.form;

import java.util.List;

public class RuleTemplateForm {

	// Fields

	private String ruleTempNo;
	private String ruleTempDesc;
	private String ruleTempReg;
	private String status;
	private String version;
	private String remark;
	private List<RuleTempParamForm> ruleTempParam;

	// Property accessors

	public String getRuleTempNo() {
		return this.ruleTempNo;
	}

	public void setRuleTempNo(String ruleTempNo) {
		this.ruleTempNo = ruleTempNo;
	}

	public String getRuleTempDesc() {
		return this.ruleTempDesc;
	}

	public void setRuleTempDesc(String ruleTempDesc) {
		this.ruleTempDesc = ruleTempDesc;
	}

	public String getRuleTempReg() {
		return this.ruleTempReg;
	}

	public void setRuleTempReg(String ruleTempReg) {
		this.ruleTempReg = ruleTempReg;
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

	public List<RuleTempParamForm> getRuleTempParam() {
		return ruleTempParam;
	}

	public void setRuleTempParam(List<RuleTempParamForm> ruleTempParam) {
		this.ruleTempParam = ruleTempParam;
	}

}
