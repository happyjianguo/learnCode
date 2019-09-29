package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearStatDailyAccdetBak {
    private String dailyDate;

    private BigDecimal cardSum;

    private BigDecimal cardBal;
    
    private String begainDailyDate;
    private String endDailyDate;

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate == null ? null : dailyDate.trim();
    }

    public BigDecimal getCardSum() {
        return cardSum;
    }

    public void setCardSum(BigDecimal cardSum) {
        this.cardSum = cardSum;
    }

    public BigDecimal getCardBal() {
        return cardBal;
    }

    public void setCardBal(BigDecimal cardBal) {
        this.cardBal = cardBal;
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