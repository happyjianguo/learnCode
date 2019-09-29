package cn.yufu.posp.keyManager.domain.model;

import java.math.BigDecimal;

/**
 * BtsKey entity. @author MyEclipse Persistence Tools
 */

public class BtsKey implements java.io.Serializable {

	// Fields

	private String merchantId;
	private String merchantName;
	private String terminalId;
	private String operNo;
	private String operPasswd;
	private String masterKey;
	private String pinKey;
	private String macKey;
	private String posKey;
	private String logonKey;
	private String settleFlag;
	private String batchNo;
	private String tmkmasterKey;

	// Constructors

	/** default constructor */
	public BtsKey() {
	}

	/** minimal constructor */
	public BtsKey(String merchantId, String terminalId) {
		this.merchantId = merchantId;
		this.terminalId = terminalId;
	}

	/** full constructor */
	public BtsKey(String merchantId, String terminalId, String operNo, String operPasswd, String masterKey, String pinKey, String macKey, String posKey,
			String logonKey, String settleFlag, String batchNo, String tmkmasterKey) {
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.operNo = operNo;
		this.operPasswd = operPasswd;
		this.masterKey = masterKey;
		this.pinKey = pinKey;
		this.macKey = macKey;
		this.posKey = posKey;
		this.logonKey = logonKey;
		this.settleFlag = settleFlag;
		this.batchNo = batchNo;
		this.tmkmasterKey = tmkmasterKey;
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

	public String getOperNo() {
		return this.operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getOperPasswd() {
		return this.operPasswd;
	}

	public void setOperPasswd(String operPasswd) {
		this.operPasswd = operPasswd;
	}

	public String getMasterKey() {
		return this.masterKey;
	}

	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}

	public String getPinKey() {
		return this.pinKey;
	}

	public void setPinKey(String pinKey) {
		this.pinKey = pinKey;
	}

	public String getMacKey() {
		return this.macKey;
	}

	public void setMacKey(String macKey) {
		this.macKey = macKey;
	}

	public String getPosKey() {
		return this.posKey;
	}

	public void setPosKey(String posKey) {
		this.posKey = posKey;
	}

	public String getLogonKey() {
		return this.logonKey;
	}

	public void setLogonKey(String logonKey) {
		this.logonKey = logonKey;
	}

	public String getSettleFlag() {
		return this.settleFlag;
	}

	public void setSettleFlag(String settleFlag) {
		this.settleFlag = settleFlag;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getTmkmasterKey() {
		return this.tmkmasterKey;
	}

	public void setTmkmasterKey(String tmkmasterKey) {
		this.tmkmasterKey = tmkmasterKey;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof BtsKey))
			return false;
		BtsKey castOther = (BtsKey) other;

		return ((this.getMerchantId() == castOther.getMerchantId()) || (this.getMerchantId() != null && castOther.getMerchantId() != null && this
				.getMerchantId().equals(castOther.getMerchantId())))
				&& ((this.getTerminalId() == castOther.getTerminalId()) || (this.getTerminalId() != null && castOther.getTerminalId() != null && this
						.getTerminalId().equals(castOther.getTerminalId())))
				&& ((this.getOperNo() == castOther.getOperNo()) || (this.getOperNo() != null && castOther.getOperNo() != null && this.getOperNo().equals(
						castOther.getOperNo())))
				&& ((this.getOperPasswd() == castOther.getOperPasswd()) || (this.getOperPasswd() != null && castOther.getOperPasswd() != null && this
						.getOperPasswd().equals(castOther.getOperPasswd())))
				&& ((this.getMasterKey() == castOther.getMasterKey()) || (this.getMasterKey() != null && castOther.getMasterKey() != null && this
						.getMasterKey().equals(castOther.getMasterKey())))
				&& ((this.getPinKey() == castOther.getPinKey()) || (this.getPinKey() != null && castOther.getPinKey() != null && this.getPinKey().equals(
						castOther.getPinKey())))
				&& ((this.getMacKey() == castOther.getMacKey()) || (this.getMacKey() != null && castOther.getMacKey() != null && this.getMacKey().equals(
						castOther.getMacKey())))
				&& ((this.getPosKey() == castOther.getPosKey()) || (this.getPosKey() != null && castOther.getPosKey() != null && this.getPosKey().equals(
						castOther.getPosKey())))
				&& ((this.getLogonKey() == castOther.getLogonKey()) || (this.getLogonKey() != null && castOther.getLogonKey() != null && this.getLogonKey()
						.equals(castOther.getLogonKey())))
				&& ((this.getSettleFlag() == castOther.getSettleFlag()) || (this.getSettleFlag() != null && castOther.getSettleFlag() != null && this
						.getSettleFlag().equals(castOther.getSettleFlag())))
				&& ((this.getBatchNo() == castOther.getBatchNo()) || (this.getBatchNo() != null && castOther.getBatchNo() != null && this.getBatchNo().equals(
						castOther.getBatchNo())))
				&& ((this.getTmkmasterKey() == castOther.getTmkmasterKey()) || (this.getTmkmasterKey() != null && castOther.getTmkmasterKey() != null && this
						.getTmkmasterKey().equals(castOther.getTmkmasterKey())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMerchantId() == null ? 0 : this.getMerchantId().hashCode());
		result = 37 * result + (getTerminalId() == null ? 0 : this.getTerminalId().hashCode());
		result = 37 * result + (getOperNo() == null ? 0 : this.getOperNo().hashCode());
		result = 37 * result + (getOperPasswd() == null ? 0 : this.getOperPasswd().hashCode());
		result = 37 * result + (getMasterKey() == null ? 0 : this.getMasterKey().hashCode());
		result = 37 * result + (getPinKey() == null ? 0 : this.getPinKey().hashCode());
		result = 37 * result + (getMacKey() == null ? 0 : this.getMacKey().hashCode());
		result = 37 * result + (getPosKey() == null ? 0 : this.getPosKey().hashCode());
		result = 37 * result + (getLogonKey() == null ? 0 : this.getLogonKey().hashCode());
		result = 37 * result + (getSettleFlag() == null ? 0 : this.getSettleFlag().hashCode());
		result = 37 * result + (getBatchNo() == null ? 0 : this.getBatchNo().hashCode());
		result = 37 * result + (getTmkmasterKey() == null ? 0 : this.getTmkmasterKey().hashCode());
		return result;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

}