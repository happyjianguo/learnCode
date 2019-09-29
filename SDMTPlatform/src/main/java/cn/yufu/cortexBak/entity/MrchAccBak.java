package cn.yufu.cortexBak.entity;

import java.util.Date;

public class MrchAccBak {
    private Long vernoCtx;

    private Long merchantId;

    private String currcode;

    private Date dateLastStmt;

    private Double closingBal;

    private Double currentBal;

    private Double lastPostBal;

    private Double lastPostCom;

    private Double lastPostTax;

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getCurrcode() {
        return currcode;
    }

    public void setCurrcode(String currcode) {
        this.currcode = currcode == null ? null : currcode.trim();
    }

    public Date getDateLastStmt() {
        return dateLastStmt;
    }

    public void setDateLastStmt(Date dateLastStmt) {
        this.dateLastStmt = dateLastStmt;
    }

    public Double getClosingBal() {
        return closingBal;
    }

    public void setClosingBal(Double closingBal) {
        this.closingBal = closingBal;
    }

    public Double getCurrentBal() {
        return currentBal;
    }

    public void setCurrentBal(Double currentBal) {
        this.currentBal = currentBal;
    }

    public Double getLastPostBal() {
        return lastPostBal;
    }

    public void setLastPostBal(Double lastPostBal) {
        this.lastPostBal = lastPostBal;
    }

    public Double getLastPostCom() {
        return lastPostCom;
    }

    public void setLastPostCom(Double lastPostCom) {
        this.lastPostCom = lastPostCom;
    }

    public Double getLastPostTax() {
        return lastPostTax;
    }

    public void setLastPostTax(Double lastPostTax) {
        this.lastPostTax = lastPostTax;
    }
}