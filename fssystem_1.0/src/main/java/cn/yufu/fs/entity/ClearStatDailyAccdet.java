package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearStatDailyAccdet {
    private String dailyDate;

    private String genDt;

    private String cardBin;

    private String crdproduct;

    private String cardTypeName;

    private BigDecimal cardCnt;

    private BigDecimal cardBal;

    private BigDecimal trueBal;

    private BigDecimal instBal;
    
    private BigDecimal aCC;

    private BigDecimal totalBal;
    
    private String exelusiveCardFlag;
    private String stlNeedFlag;

    private String cardStatus;
    private String cardStatusDesc;
    
    private String begainDailyDate;
    private String endDailyDate;
    
    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate == null ? null : dailyDate.trim();
    }

    public String getGenDt() {
        return genDt;
    }

    public void setGenDt(String genDt) {
        this.genDt = genDt == null ? null : genDt.trim();
    }

    public String getCardBin() {
        return cardBin;
    }

    public void setCardBin(String cardBin) {
        this.cardBin = cardBin == null ? null : cardBin.trim();
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public BigDecimal getCardCnt() {
        return cardCnt;
    }

    public void setCardCnt(BigDecimal cardCnt) {
        this.cardCnt = cardCnt;
    }

    public BigDecimal getCardBal() {
        return cardBal;
    }

    public void setCardBal(BigDecimal cardBal) {
        this.cardBal = cardBal;
    }

    public BigDecimal getTrueBal() {
        return trueBal;
    }

    public void setTrueBal(BigDecimal trueBal) {
        this.trueBal = trueBal;
    }

    public BigDecimal getInstBal() {
        return instBal;
    }

    public void setInstBal(BigDecimal instBal) {
        this.instBal = instBal;
    }
    

    public BigDecimal getaCC() {
		return aCC;
	}

	public void setaCC(BigDecimal aCC) {
		this.aCC = aCC;
	}

	public BigDecimal getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(BigDecimal totalBal) {
        this.totalBal = totalBal;
    }

	public String getExelusiveCardFlag() {
		return exelusiveCardFlag;
	}

	public void setExelusiveCardFlag(String exelusiveCardFlag) {
		this.exelusiveCardFlag = exelusiveCardFlag;
	}

	public String getStlNeedFlag() {
		return stlNeedFlag;
	}

	public void setStlNeedFlag(String stlNeedFlag) {
		this.stlNeedFlag = stlNeedFlag;
	}

	public String getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(String cardStatus) {
		this.cardStatus = cardStatus;
	}

	public String getCardStatusDesc() {
		return cardStatusDesc;
	}

	public void setCardStatusDesc(String cardStatusDesc) {
		this.cardStatusDesc = cardStatusDesc;
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