package cn.yufu.cortex.entity;

import java.math.BigDecimal;

public class CortexViewAccdetStat {
    private String cardBin;

    private String crdproduct;

    private String cardTypeName;

    private String cardStatus;

    private String cardStatusName;

    private BigDecimal cardCnt;

    private BigDecimal cardBal;

    private BigDecimal trueBal;

    private BigDecimal instBal;
    
    private BigDecimal instBal9;

    private BigDecimal totalBal;
    
    private String isExclusive;
    private String stlFlag;

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

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    public String getCardStatusName() {
        return cardStatusName;
    }

    public void setCardStatusName(String cardStatusName) {
        this.cardStatusName = cardStatusName == null ? null : cardStatusName.trim();
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

    public BigDecimal getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(BigDecimal totalBal) {
        this.totalBal = totalBal;
    }

	public String getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(String isExclusive) {
		this.isExclusive = isExclusive;
	}

	public String getStlFlag() {
		return stlFlag;
	}

	public void setStlFlag(String stlFlag) {
		this.stlFlag = stlFlag;
	}

	public BigDecimal getInstBal9() {
		return instBal9;
	}

	public void setInstBal9(BigDecimal instBal9) {
		this.instBal9 = instBal9;
	}
    
}