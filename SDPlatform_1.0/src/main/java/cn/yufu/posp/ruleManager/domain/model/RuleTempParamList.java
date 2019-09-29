package cn.yufu.posp.ruleManager.domain.model;

/**
 * RuleTempParamList entity. @author MyEclipse Persistence Tools
 */

public class RuleTempParamList implements java.io.Serializable {

	// Fields

	private String ruleTempParamId;
	private String key;
	private String value;
	private String status;
	private String version;
	private String remark;

	// Constructors

	/** default constructor */
	public RuleTempParamList() {
	}

	/** minimal constructor */
	public RuleTempParamList(String ruleTempParamId, String key,
			String value, String status, String version) {
		this.ruleTempParamId = ruleTempParamId;
		this.key = key;
		this.value = value;
		this.status = status;
		this.version = version;
	}

	/** full constructor */
	public RuleTempParamList(String ruleTempParamId, String key,
			String value, String status, String version, String remark) {
		this.ruleTempParamId = ruleTempParamId;
		this.key = key;
		this.value = value;
		this.status = status;
		this.version = version;
		this.remark = remark;
	}

	// Property accessors

	public String getRuleTempParamId() {
		return this.ruleTempParamId;
	}

	public void setRuleTempParamId(String ruleTempParamId) {
		this.ruleTempParamId = ruleTempParamId;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
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



}