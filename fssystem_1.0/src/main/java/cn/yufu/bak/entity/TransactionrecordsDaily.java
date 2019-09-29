package cn.yufu.bak.entity;

import java.math.BigDecimal;

public class TransactionrecordsDaily {
    private String merchantnumber;

    private String transactiondate;

    private BigDecimal transactionmoneySum;
    
    private BigDecimal tranNum;

    private String momments1;

    private String momments2;

    private String momments3;
    
    private String startDt;
    
    private String endDt;  

    private String merchantName;

    public String getMerchantnumber() {
        return merchantnumber;
    }

    public void setMerchantnumber(String merchantnumber) {
        this.merchantnumber = merchantnumber == null ? null : merchantnumber.trim();
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }

    public BigDecimal getTransactionmoneySum() {
        return transactionmoneySum;
    }

    public void setTransactionmoneySum(BigDecimal transactionmoneySum) {
        this.transactionmoneySum = transactionmoneySum;
    }
    
    public BigDecimal getTranNum() {
        return tranNum;
    }

    public void setTranNum(BigDecimal tranNum) {
        this.tranNum = tranNum;
    }

    public String getMomments1() {
        return momments1;
    }

    public void setMomments1(String momments1) {
        this.momments1 = momments1 == null ? null : momments1.trim();
    }

    public String getMomments2() {
        return momments2;
    }

    public void setMomments2(String momments2) {
        this.momments2 = momments2 == null ? null : momments2.trim();
    }

    public String getMomments3() {
        return momments3;
    }

    public void setMomments3(String momments3) {
        this.momments3 = momments3 == null ? null : momments3.trim();
    }

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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
    
}