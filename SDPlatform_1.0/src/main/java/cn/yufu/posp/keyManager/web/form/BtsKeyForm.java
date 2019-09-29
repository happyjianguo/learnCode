package cn.yufu.posp.keyManager.web.form;

import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

/**
 * BtsKey entity. @author MyEclipse Persistence Tools
 */

public class BtsKeyForm extends BaseForm implements java.io.Serializable {

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

	private String querySettleFlag;
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getQuerySettleFlag() {
		return querySettleFlag;
	}

	public void setQuerySettleFlag(String querySettleFlag) {
		this.querySettleFlag = querySettleFlag;
	}

}