package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.core.web.form.BaseForm;

/**
 * EdcBlack entity. @author MyEclipse Persistence Tools
 */

public class EdcBlackForm extends BaseForm {

	// Fields

	private String merchantId;
	private String terminalId;
	private String status;
	private String updateOper;
	private String updateTime;
	private String reason;
	private String version;
	private String remark;

	private String merchantName ;
	// Constructors

	/** default constructor */
	public EdcBlackForm() {
	}

	/** minimal constructor */
	public EdcBlackForm(String merchantId, String terminalId, String status,
			String updateOper, String updateTime, String version) {
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.status = status;
		this.updateOper = updateOper;
		this.updateTime = updateTime;
		this.version = version;
	}

	/** full constructor */
	public EdcBlackForm(String merchantId, String terminalId, String status,
			String updateOper, String updateTime, String reason,
			String version, String remark) {
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.status = status;
		this.updateOper = updateOper;
		this.updateTime = updateTime;
		this.reason = reason;
		this.version = version;
		this.remark = remark;
	}

	// Property accessors

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}



}