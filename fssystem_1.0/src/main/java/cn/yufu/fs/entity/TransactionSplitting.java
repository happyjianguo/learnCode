package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class TransactionSplitting {
    private String id;

    private String merchantnumber;
    
    private String merchantName;

    private String cardBin;
    
    private String cardBin2;

    private String transactiondate;  //日期

    private BigDecimal transactionmoney;  //消费金额

    private BigDecimal perfee;  //手续费金额

    private BigDecimal feeOrder;  //手续费费率
    
    private BigDecimal feeOrderCard;  //分润比例

    private BigDecimal splitAmt; //分润金额

    private String comments;	//笔数

    private String comments1;

    private String comments2;

    private String comments3;
    
    private String begainTransactiondate;
    
    private String endTransactiondate;
    
    private String minPaymentDay; //账期
    
    private String maxPaymentDay;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerchantnumber() {
        return merchantnumber;
    }

    public void setMerchantnumber(String merchantnumber) {
        this.merchantnumber = merchantnumber == null ? null : merchantnumber.trim();
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin == null ? null : cardBin.trim();
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }

    public BigDecimal getTransactionmoney() {
        return transactionmoney;
    }

    public void setTransactionmoney(BigDecimal transactionmoney) {
        this.transactionmoney = transactionmoney;
    }

    public BigDecimal getPerfee() {
        return perfee;
    }

    public void setPerfee(BigDecimal perfee) {
        this.perfee = perfee;
    }

    public BigDecimal getFeeOrder() {
        return feeOrder;
    }

    public void setFeeOrder(BigDecimal feeOrder) {
        this.feeOrder = feeOrder;
    }

    public BigDecimal getSplitAmt() {
        return splitAmt;
    }

    public void setSplitAmt(BigDecimal splitAmt) {
        this.splitAmt = splitAmt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getComments1() {
        return comments1;
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1 == null ? null : comments1.trim();
    }

    public String getComments2() {
        return comments2;
    }

    public void setComments2(String comments2) {
        this.comments2 = comments2 == null ? null : comments2.trim();
    }

    public String getComments3() {
        return comments3;
    }

    public void setComments3(String comments3) {
        this.comments3 = comments3 == null ? null : comments3.trim();
    }

	public String getBegainTransactiondate() {
		return begainTransactiondate;
	}

	public void setBegainTransactiondate(String begainTransactiondate) {
		this.begainTransactiondate = ((begainTransactiondate == null || begainTransactiondate.trim().length() == 0)? null : begainTransactiondate.trim());
	}

	public String getEndTransactiondate() {
		return endTransactiondate;
	}

	public void setEndTransactiondate(String endTransactiondate) {
		this.endTransactiondate = ((endTransactiondate == null || endTransactiondate.trim().length() == 0)? null : endTransactiondate.trim());
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName == null ? null : merchantName.trim();
	}

	public BigDecimal getFeeOrderCard() {
		return feeOrderCard;
	}

	public void setFeeOrderCard(BigDecimal feeOrderCard) {
		this.feeOrderCard = feeOrderCard;
	}

	public String getMinPaymentDay() {
		return minPaymentDay;
	}

	public void setMinPaymentDay(String minPaymentDay) {
		this.minPaymentDay = minPaymentDay;
	}

	public String getMaxPaymentDay() {
		return maxPaymentDay;
	}

	public void setMaxPaymentDay(String maxPaymentDay) {
		this.maxPaymentDay = maxPaymentDay;
	}

	public String getCardBin2() {
		return cardBin2;
	}

	public void setCardBin2(String cardBin2) {
		this.cardBin2 = cardBin2;
	}
	
}