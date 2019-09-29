package cn.yufu.cortex.entity;

import java.math.BigDecimal;
import java.util.Date;

public class CortexMrchAccX {
    private Long id;

    private Long instId;

    private String mrchno;

    private String accno;

    private String accName;

    private Long bankNo;

    private String bankName;

    private Date accAddDate;

    private String accNickName;

    private String shortNickName;

    private String individual;

    private Date lastSettleDate;

    private Long merchantId;

    private String accIntrod;

    private BigDecimal accountId;

    private String isBjAcct;

    private String bis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno == null ? null : accno.trim();
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName == null ? null : accName.trim();
    }

    public Long getBankNo() {
        return bankNo;
    }

    public void setBankNo(Long bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Date getAccAddDate() {
        return accAddDate;
    }

    public void setAccAddDate(Date accAddDate) {
        this.accAddDate = accAddDate;
    }

    public String getAccNickName() {
        return accNickName;
    }

    public void setAccNickName(String accNickName) {
        this.accNickName = accNickName == null ? null : accNickName.trim();
    }

    public String getShortNickName() {
        return shortNickName;
    }

    public void setShortNickName(String shortNickName) {
        this.shortNickName = shortNickName == null ? null : shortNickName.trim();
    }

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual == null ? null : individual.trim();
    }

    public Date getLastSettleDate() {
        return lastSettleDate;
    }

    public void setLastSettleDate(Date lastSettleDate) {
        this.lastSettleDate = lastSettleDate;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getAccIntrod() {
        return accIntrod;
    }

    public void setAccIntrod(String accIntrod) {
        this.accIntrod = accIntrod == null ? null : accIntrod.trim();
    }

    public BigDecimal getAccountId() {
        return accountId;
    }

    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public String getIsBjAcct() {
        return isBjAcct;
    }

    public void setIsBjAcct(String isBjAcct) {
        this.isBjAcct = isBjAcct == null ? null : isBjAcct.trim();
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis == null ? null : bis.trim();
    }
}