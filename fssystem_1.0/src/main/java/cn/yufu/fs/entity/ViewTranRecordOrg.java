package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ViewTranRecordOrg {
    private String merchantnumber;

    private String mrchtName;

    private BigDecimal transactionmoney;

    private String transactiondate;

    private BigDecimal feeOrder;		//单笔手续费费率

    private BigDecimal perfee;			//单笔手续费金额

    private BigDecimal feeRetention;	//留底手续费率

    private BigDecimal amtRetention;	//留底手续费金额

    private String orgNo;				//分润机构

    private String orgName;				//分润机构名称

    private String orgBin;				//分润卡BIN
    
    private BigDecimal amtShare;		//分润金额

    private String tranTotal;			//交易笔数	
    
	private String transactiondateStart;// 交易起始日期
	
	private String transactiondateEnd;// 交易截止日期
	
    public String getMerchantnumber() {
        return merchantnumber;
    }

    public void setMerchantnumber(String merchantnumber) {
        this.merchantnumber = merchantnumber == null ? null : merchantnumber.trim();
    }

    public String getMrchtName() {
        return mrchtName;
    }

    public void setMrchtName(String mrchtName) {
        this.mrchtName = mrchtName == null ? null : mrchtName.trim();
    }

    public BigDecimal getTransactionmoney() {
        return transactionmoney;
    }

    public void setTransactionmoney(BigDecimal transactionmoney) {
        this.transactionmoney = transactionmoney;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }

    public BigDecimal getFeeOrder() {
        return feeOrder;
    }

    public void setFeeOrder(BigDecimal feeOrder) {
        this.feeOrder = feeOrder;
    }

    public BigDecimal getPerfee() {
        return perfee;
    }

    public void setPerfee(BigDecimal perfee) {
        this.perfee = perfee;
    }

    public BigDecimal getFeeRetention() {
        return feeRetention;
    }

    public void setFeeRetention(BigDecimal feeRetention) {
        this.feeRetention = feeRetention;
    }

    public BigDecimal getAmtRetention() {
        return amtRetention;
    }

    public void setAmtRetention(BigDecimal amtRetention) {
        this.amtRetention = amtRetention;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo == null ? null : orgNo.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgBin() {
        return orgBin;
    }

    public void setOrgBin(String orgBin) {
        this.orgBin = orgBin == null ? null : orgBin.trim();
    }

	public BigDecimal getAmtShare() {
		return amtShare;
	}

	public void setAmtShare(BigDecimal amtShare) {
		this.amtShare = amtShare;
	}

	public String getTranTotal() {
		return tranTotal;
	}

	public void setTranTotal(String tranTotal) {
		this.tranTotal = tranTotal == null ? null : tranTotal.trim();
	}

	public String getTransactiondateStart() {
		return transactiondateStart;
	}

	public void setTransactiondateStart(String transactiondateStart) {
		this.transactiondateStart = transactiondateStart;
	}

	public String getTransactiondateEnd() {
		return transactiondateEnd;
	}

	public void setTransactiondateEnd(String transactiondateEnd) {
		this.transactiondateEnd = transactiondateEnd;
	}
    
}