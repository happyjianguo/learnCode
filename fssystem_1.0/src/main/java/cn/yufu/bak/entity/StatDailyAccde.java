package cn.yufu.bak.entity;

import java.math.BigDecimal;

public class StatDailyAccde {
    private String dailyDate;

    private String genDt;

    private String cardBin;

    private String crdproduct;

    private String cardTypeName;

    private String cardStatus;

    private String cardStatusDesc;

    private BigDecimal cardCnt;

    private BigDecimal acc01;

    private BigDecimal acc02;

    private BigDecimal acc03;

    private BigDecimal acc04;

    private BigDecimal acc05;

    private BigDecimal acc06;

    private BigDecimal acc07;

    private BigDecimal acc08;

    private BigDecimal acc09;

    private BigDecimal totalBal;

    private String abFlag;
    
    private String startDate;

    private String endDate;

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

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus == null ? null : cardStatus.trim();
    }

    public String getCardStatusDesc() {
        return cardStatusDesc;
    }

    public void setCardStatusDesc(String cardStatusDesc) {
        this.cardStatusDesc = cardStatusDesc == null ? null : cardStatusDesc.trim();
    }

    public BigDecimal getCardCnt() {
        return cardCnt;
    }

    public void setCardCnt(BigDecimal cardCnt) {
        this.cardCnt = cardCnt;
    }

    public BigDecimal getAcc01() {
        return acc01;
    }

    public void setAcc01(BigDecimal acc01) {
        this.acc01 = acc01;
    }

    public BigDecimal getAcc02() {
        return acc02;
    }

    public void setAcc02(BigDecimal acc02) {
        this.acc02 = acc02;
    }

    public BigDecimal getAcc03() {
        return acc03;
    }

    public void setAcc03(BigDecimal acc03) {
        this.acc03 = acc03;
    }

    public BigDecimal getAcc04() {
        return acc04;
    }

    public void setAcc04(BigDecimal acc04) {
        this.acc04 = acc04;
    }

    public BigDecimal getAcc05() {
        return acc05;
    }

    public void setAcc05(BigDecimal acc05) {
        this.acc05 = acc05;
    }

    public BigDecimal getAcc06() {
        return acc06;
    }

    public void setAcc06(BigDecimal acc06) {
        this.acc06 = acc06;
    }

    public BigDecimal getAcc07() {
        return acc07;
    }

    public void setAcc07(BigDecimal acc07) {
        this.acc07 = acc07;
    }

    public BigDecimal getAcc08() {
        return acc08;
    }

    public void setAcc08(BigDecimal acc08) {
        this.acc08 = acc08;
    }

    public BigDecimal getAcc09() {
        return acc09;
    }

    public void setAcc09(BigDecimal acc09) {
        this.acc09 = acc09;
    }

    public BigDecimal getTotalBal() {
        return totalBal;
    }

    public void setTotalBal(BigDecimal totalBal) {
        this.totalBal = totalBal;
    }

    public String getAbFlag() {
        return abFlag;
    }

    public void setAbFlag(String abFlag) {
        this.abFlag = abFlag == null ? null : abFlag.trim();
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
    
}