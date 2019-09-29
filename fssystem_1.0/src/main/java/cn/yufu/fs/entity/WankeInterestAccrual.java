package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class WankeInterestAccrual {
    private String id;  //主键

    private BigDecimal cardaccount;	//账户总余额

    private BigDecimal stockFund;	//上交备付金

    private String transactiondate;	//统计日期

    private BigDecimal rechargeAmt;	//当日充值金额

    private BigDecimal fixedDeposit;	//定期存款金额

    private BigDecimal netAmount;	//净额

    private BigDecimal littleAmount;	//留底金额--如果净额大于100000，留底金额=100000，否则是0

    private BigDecimal currentInterestBase;	//活期计息基数-如果净额小于100,000,则活期计息基数=净额，否则是0

    private BigDecimal agreementDepositBase;//协定存款计息基数-如果净额大于100,000,则协定存款计息基数=净额-100,000，否则是0

    private BigDecimal agreementCurrentInterest;//留底和活期的利息金额=留底金额*0.35%\360+活期计息基数*0.35%/360-利率主动获取

    private BigDecimal agreementDepositInterest;//协定存款利息金额=协定存款计息基数*1.15%/360-利率主动获取

    private BigDecimal grossInterest;//总计息=留底和活期的利息金额+协定存款利息金额

    private String coments;

    private String coments1;
    
    private String begainTransactiondate;	//起始统计日期
    
    private String endTransactiondate;	//截止统计日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getCardaccount() {
        return cardaccount;
    }

    public void setCardaccount(BigDecimal cardaccount) {
        this.cardaccount = cardaccount;
    }

    public BigDecimal getStockFund() {
        return stockFund;
    }

    public void setStockFund(BigDecimal stockFund) {
        this.stockFund = stockFund;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }

    public BigDecimal getRechargeAmt() {
        return rechargeAmt;
    }

    public void setRechargeAmt(BigDecimal rechargeAmt) {
        this.rechargeAmt = rechargeAmt;
    }

    public BigDecimal getFixedDeposit() {
        return fixedDeposit;
    }

    public void setFixedDeposit(BigDecimal fixedDeposit) {
        this.fixedDeposit = fixedDeposit;
    }

    public BigDecimal getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(BigDecimal netAmount) {
        this.netAmount = netAmount;
    }

    public BigDecimal getLittleAmount() {
        return littleAmount;
    }

    public void setLittleAmount(BigDecimal littleAmount) {
        this.littleAmount = littleAmount;
    }

    public BigDecimal getCurrentInterestBase() {
        return currentInterestBase;
    }

    public void setCurrentInterestBase(BigDecimal currentInterestBase) {
        this.currentInterestBase = currentInterestBase;
    }

    public BigDecimal getAgreementDepositBase() {
        return agreementDepositBase;
    }

    public void setAgreementDepositBase(BigDecimal agreementDepositBase) {
        this.agreementDepositBase = agreementDepositBase;
    }

    public BigDecimal getAgreementCurrentInterest() {
        return agreementCurrentInterest;
    }

    public void setAgreementCurrentInterest(BigDecimal agreementCurrentInterest) {
        this.agreementCurrentInterest = agreementCurrentInterest;
    }

    public BigDecimal getAgreementDepositInterest() {
        return agreementDepositInterest;
    }

    public void setAgreementDepositInterest(BigDecimal agreementDepositInterest) {
        this.agreementDepositInterest = agreementDepositInterest;
    }

    public BigDecimal getGrossInterest() {
        return grossInterest;
    }

    public void setGrossInterest(BigDecimal grossInterest) {
        this.grossInterest = grossInterest;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments == null ? null : coments.trim();
    }

    public String getComents1() {
        return coments1;
    }

    public void setComents1(String coments1) {
        this.coments1 = coments1 == null ? null : coments1.trim();
    }

	public String getBegainTransactiondate() {
		return begainTransactiondate;
	}

	public void setBegainTransactiondate(String begainTransactiondate) {
		this.begainTransactiondate = begainTransactiondate;
	}

	public String getEndTransactiondate() {
		return endTransactiondate;
	}

	public void setEndTransactiondate(String endTransactiondate) {
		this.endTransactiondate = endTransactiondate;
	}
    
}