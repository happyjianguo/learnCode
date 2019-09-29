package cn.yufu.posp.ruleManager.domain.model;

/**
 * RuleConfigParam entity. @author MyEclipse Persistence Tools
 */

public class RuleConfigParam implements java.io.Serializable {

	// Fields

	private String configParamId;
	private String ruleTempNo;
	private String ruleTempReg;
	private String value;
	private String configId;
	private String other;

	// Constructors

	/** default constructor */
	public RuleConfigParam() {
	}

	/** minimal constructor */
	public RuleConfigParam(String configParamId, String ruleTempNo,
			String ruleTempReg, String value, String configId) {
		this.configParamId = configParamId;
		this.ruleTempNo = ruleTempNo;
		this.ruleTempReg = ruleTempReg;
		this.value = value;
		this.configId = configId;
	}

	/** full constructor */
	public RuleConfigParam(String configParamId, String ruleTempNo,
			String ruleTempReg, String value, String configId, String other) {
		this.configParamId = configParamId;
		this.ruleTempNo = ruleTempNo;
		this.ruleTempReg = ruleTempReg;
		this.value = value;
		this.configId = configId;
		this.other = other;
	}

	// Property accessors

	public String getConfigParamId() {
		return this.configParamId;
	}

	public void setConfigParamId(String configParamId) {
		this.configParamId = configParamId;
	}

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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getConfigId() {
		return this.configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getOther() {
		return this.other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}