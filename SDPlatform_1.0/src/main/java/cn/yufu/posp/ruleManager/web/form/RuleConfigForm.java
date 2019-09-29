package cn.yufu.posp.ruleManager.web.form;

import java.util.List;

public class RuleConfigForm {

	// Fields

	private String configId;
	private String ruleTempNo;
	private String configTitle;
	private String configDesc;
	private String status;
	private String version;
	private String remark;
	private String configCycle;
	private String cycleType;
	private String actionStatus ;
	private String agingMode;
	private List<RuleConfigParamForm> list ;
	
	// Property accessors

	public String getConfigId() {
		return this.configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getRuleTempNo() {
		return this.ruleTempNo;
	}

	public void setRuleTempNo(String ruleTempNo) {
		this.ruleTempNo = ruleTempNo;
	}

	public String getConfigTitle() {
		return this.configTitle;
	}

	public void setConfigTitle(String configTitle) {
		this.configTitle = configTitle;
	}

	public String getConfigDesc() {
		return this.configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
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

	public String getConfigCycle() {
		return this.configCycle;
	}

	public void setConfigCycle(String configCycle) {
		this.configCycle = configCycle;
	}

	public String getCycleType() {
		return this.cycleType;
	}

	public void setCycleType(String cycleType) {
		this.cycleType = cycleType;
	}

	public List<RuleConfigParamForm> getList() {
		return list;
	}

	public void setList(List<RuleConfigParamForm> list) {
		this.list = list;
	}

	public String getActionStatus() {
		return actionStatus;
	}

	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	public String getAgingMode() {
		return agingMode;
	}

	public void setAgingMode(String agingMode) {
		this.agingMode = agingMode;
	}

}
