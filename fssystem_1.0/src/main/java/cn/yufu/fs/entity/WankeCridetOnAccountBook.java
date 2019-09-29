package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class WankeCridetOnAccountBook {
    private String id;

    private String merNo;

    private String merName;

    private String terNo;

    private String pan;

    private String cardType;

    private String credetDate;

    private String credetTime;

    private BigDecimal creditAmt;

    private BigDecimal repayAmt;

    private String repayDate;

    private String repayTime;

    private BigDecimal debtAmt;

    private String icSaleCodeid;

    private String icSaleCode;

    private String icSaleName;

    private String icItemCodeid;

    private String icItemCode;

    private String icItemName;

    

    private String cardOwner;

    private String telphone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getTerNo() {
        return terNo;
    }

    public void setTerNo(String terNo) {
        this.terNo = terNo == null ? null : terNo.trim();
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCredetDate() {
        return credetDate;
    }

    public void setCredetDate(String credetDate) {
        this.credetDate = credetDate == null ? null : credetDate.trim();
    }

    public String getCredetTime() {
        return credetTime;
    }

    public void setCredetTime(String credetTime) {
        this.credetTime = credetTime == null ? null : credetTime.trim();
    }

    public BigDecimal getCreditAmt() {
        return creditAmt;
    }

    public void setCreditAmt(BigDecimal creditAmt) {
        this.creditAmt = creditAmt;
    }

    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate == null ? null : repayDate.trim();
    }

    public String getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(String repayTime) {
        this.repayTime = repayTime == null ? null : repayTime.trim();
    }

    public BigDecimal getDebtAmt() {
        return debtAmt;
    }

    public void setDebtAmt(BigDecimal debtAmt) {
        this.debtAmt = debtAmt;
    }

    public String getIcSaleCodeid() {
        return icSaleCodeid;
    }

    public void setIcSaleCodeid(String icSaleCodeid) {
        this.icSaleCodeid = icSaleCodeid == null ? null : icSaleCodeid.trim();
    }

    public String getIcSaleCode() {
        return icSaleCode;
    }

    public void setIcSaleCode(String icSaleCode) {
        this.icSaleCode = icSaleCode == null ? null : icSaleCode.trim();
    }

    public String getIcSaleName() {
        return icSaleName;
    }

    public void setIcSaleName(String icSaleName) {
        this.icSaleName = icSaleName == null ? null : icSaleName.trim();
    }

    public String getIcItemCodeid() {
        return icItemCodeid;
    }

    public void setIcItemCodeid(String icItemCodeid) {
        this.icItemCodeid = icItemCodeid == null ? null : icItemCodeid.trim();
    }

    public String getIcItemCode() {
        return icItemCode;
    }

    public void setIcItemCode(String icItemCode) {
        this.icItemCode = icItemCode == null ? null : icItemCode.trim();
    }

    public String getIcItemName() {
        return icItemName;
    }

    public void setIcItemName(String icItemName) {
        this.icItemName = icItemName == null ? null : icItemName.trim();
    }

   

    public String getCardOwner() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner = cardOwner == null ? null : cardOwner.trim();
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }
}