package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearTransactionAccount {
    private String merNo;

    private String merName;

    private String crdproduct;

    private String descr;

    private String iid;

    private BigDecimal tranAmt;

    private BigDecimal tranNum;

    private BigDecimal cardaccountmoney;

    private BigDecimal trueaccountmoney;

    private BigDecimal integrationaccountmoney;

    private BigDecimal coBrandedAccountmoney;

    private String transactiondate;
    
    
    //时间段查询条件
    private String startDt;

    private String endDt;
 
    public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public BigDecimal getTranNum() {
        return tranNum;
    }

    public void setTranNum(BigDecimal tranNum) {
        this.tranNum = tranNum;
    }

    public BigDecimal getCardaccountmoney() {
        return cardaccountmoney;
    }

    public void setCardaccountmoney(BigDecimal cardaccountmoney) {
        this.cardaccountmoney = cardaccountmoney;
    }

    public BigDecimal getTrueaccountmoney() {
        return trueaccountmoney;
    }

    public void setTrueaccountmoney(BigDecimal trueaccountmoney) {
        this.trueaccountmoney = trueaccountmoney;
    }

    public BigDecimal getIntegrationaccountmoney() {
        return integrationaccountmoney;
    }

    public void setIntegrationaccountmoney(BigDecimal integrationaccountmoney) {
        this.integrationaccountmoney = integrationaccountmoney;
    }

    public BigDecimal getCoBrandedAccountmoney() {
        return coBrandedAccountmoney;
    }

    public void setCoBrandedAccountmoney(BigDecimal coBrandedAccountmoney) {
        this.coBrandedAccountmoney = coBrandedAccountmoney;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }
}