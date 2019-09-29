package cn.yufu.posp.ruleManager.domain.model;

/**
 * RuleConfig entity. @author MyEclipse Persistence Tools
 */

public class RuleConfig implements java.io.Serializable {

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
	private String actionStatus;
	private String agingMode;

	// Constructors

	/** default constructor */
	public RuleConfig() {
	}

	/** minimal constructor */
	public RuleConfig(String configId, String ruleTempNo, String configTitle,
			String status, String version, String configCycle,
			String actionStatus,String agingMode) {
		this.configId = configId;
		this.ruleTempNo = ruleTempNo;
		this.configTitle = configTitle;
		this.status = status;
		this.version = version;
		this.configCycle = configCycle;
		this.actionStatus = actionStatus;
		this.agingMode = agingMode;
	}

	/** full constructor */
	public RuleConfig(String configId, String ruleTempNo, String configTitle,
			String configDesc, String status, String version, String remark,
			String configCycle, String cycleType, String actionStatus,
			String agingMode) {
		this.configId = configId;
		this.ruleTempNo = ruleTempNo;
		this.configTitle = configTitle;
		this.configDesc = configDesc;
		this.status = status;
		this.version = version;
		this.remark = remark;
		this.configCycle = configCycle;
		this.cycleType = cycleType;
		this.actionStatus = actionStatus;
		this.agingMode = agingMode;
	}

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

	public String getActionStatus() {
		return this.actionStatus;
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