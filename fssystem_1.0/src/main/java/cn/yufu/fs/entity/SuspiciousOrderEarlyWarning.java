package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class SuspiciousOrderEarlyWarning {
    private String ordercode; //订单号

    private String purchaseDate; //购卡日期

    private BigDecimal purchaseAmt; //购卡总额

    private String suspiciousDate; //预警日期

    private BigDecimal provisions; //备付金余额

    private BigDecimal provisionsRate; //备付金占比

    private String beginDate; //预警起始日期

    private String endDate; //预警结束日期

    private String freeField5;

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode == null ? null : ordercode.trim();
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate == null ? null : purchaseDate.trim();
    }

    public BigDecimal getPurchaseAmt() {
        return purchaseAmt;
    }

    public void setPurchaseAmt(BigDecimal purchaseAmt) {
        this.purchaseAmt = purchaseAmt;
    }

    public String getSuspiciousDate() {
        return suspiciousDate;
    }

    public void setSuspiciousDate(String suspiciousDate) {
        this.suspiciousDate = suspiciousDate == null ? null : suspiciousDate.trim();
    }

    public BigDecimal getProvisions() {
        return provisions;
    }

    public void setProvisions(BigDecimal provisions) {
        this.provisions = provisions;
    }

    public BigDecimal getProvisionsRate() {
        return provisionsRate;
    }

    public void setProvisionsRate(BigDecimal provisionsRate) {
        this.provisionsRate = provisionsRate;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate == null ? null : beginDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getFreeField5() {
        return freeField5;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5 == null ? null : freeField5.trim();
    }
}