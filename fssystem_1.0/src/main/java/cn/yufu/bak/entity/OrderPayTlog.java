package cn.yufu.bak.entity;

import java.math.BigDecimal;

public class OrderPayTlog {
	
	private String id;
	
	private String merchantId;
	
	private String orderId;
	
	private String orgOrderId;
	
	private String rrn;
	
	private String pan;
	
	private String termcode;
	
	private String transactionDate;
	
	private String transactionTime;
	
	private BigDecimal amttxn;
	
	private String txtstatus;
	
	private String fncode;
	
	private String txncode;
	
	private String subTxncode;
	
	private String cashSource;
	
	private String typeId;
	
	private String tranTypeDesc;
	
	private String clearFlag;	//是否参与结算 0  否 1 是
	
    private String begainPan;  //开始卡号
    
    private String endPan;  	//结束卡号
    
    private String begainDate;  //开始日期
    
    private String endDate;  //结束日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrgOrderId() {
		return orgOrderId;
	}

	public void setOrgOrderId(String orgOrderId) {
		this.orgOrderId = orgOrderId;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public BigDecimal getAmttxn() {
		return amttxn;
	}

	public void setAmttxn(BigDecimal amttxn) {
		this.amttxn = amttxn;
	}

	public String getTxtstatus() {
		return txtstatus;
	}

	public void setTxtstatus(String txtstatus) {
		this.txtstatus = txtstatus;
	}

	public String getFncode() {
		return fncode;
	}

	public void setFncode(String fncode) {
		this.fncode = fncode;
	}

	public String getTxncode() {
		return txncode;
	}

	public void setTxncode(String txncode) {
		this.txncode = txncode;
	}

	public String getSubTxncode() {
		return subTxncode;
	}

	public void setSubTxncode(String subTxncode) {
		this.subTxncode = subTxncode;
	}

	public String getCashSource() {
		return cashSource;
	}

	public void setCashSource(String cashSource) {
		this.cashSource = cashSource;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTranTypeDesc() {
		return tranTypeDesc;
	}

	public void setTranTypeDesc(String tranTypeDesc) {
		this.tranTypeDesc = tranTypeDesc;
	}

	public String getClearFlag() {
		return clearFlag;
	}

	public void setClearFlag(String clearFlag) {
		this.clearFlag = clearFlag;
	}

	public String getBegainPan() {
		return begainPan;
	}

	public void setBegainPan(String begainPan) {
		this.begainPan = begainPan;
	}

	public String getEndPan() {
		return endPan;
	}

	public void setEndPan(String endPan) {
		this.endPan = endPan;
	}

	public String getBegainDate() {
		return begainDate;
	}

	public void setBegainDate(String begainDate) {
		this.begainDate = begainDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}