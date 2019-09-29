package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class OldCardTranDaily {
    private String id;

    private BigDecimal transactionmoney;	//老卡每日交易金额

    private BigDecimal transactionmoneyAcc;	//老卡交易累积金额

    private BigDecimal provisionsAmt;		//每日备付金余额：实时备付金-老卡每日累积金额-配置金额(T_MERSTL_ERROR_SET)

    private String transactiondate;			//交易日期

    private BigDecimal cardSum;				//卡张数：实时卡张数(T_CLEAR_STAT_DAILY_ACCDET)-配置张数(T_MERSTL_ERROR_SET)
    
    private String begainDailyDate;
    
    private String endDailyDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public BigDecimal getTransactionmoney() {
        return transactionmoney;
    }

    public void setTransactionmoney(BigDecimal transactionmoney) {
        this.transactionmoney = transactionmoney;
    }

    public BigDecimal getTransactionmoneyAcc() {
        return transactionmoneyAcc;
    }

    public void setTransactionmoneyAcc(BigDecimal transactionmoneyAcc) {
        this.transactionmoneyAcc = transactionmoneyAcc;
    }

    public BigDecimal getProvisionsAmt() {
        return provisionsAmt;
    }

    public void setProvisionsAmt(BigDecimal provisionsAmt) {
        this.provisionsAmt = provisionsAmt;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }

    public BigDecimal getCardSum() {
        return cardSum;
    }

    public void setCardSum(BigDecimal cardSum) {
        this.cardSum = cardSum;
    }

	public String getBegainDailyDate() {
		return begainDailyDate;
	}

	public void setBegainDailyDate(String begainDailyDate) {
		this.begainDailyDate = begainDailyDate;
	}

	public String getEndDailyDate() {
		return endDailyDate;
	}

	public void setEndDailyDate(String endDailyDate) {
		this.endDailyDate = endDailyDate;
	}
}