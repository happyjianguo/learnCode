package cn.yufu.bak.entity;

import java.math.BigDecimal;

public class ViewTLogMrchno {
    private BigDecimal id;

    private String mrchno;

    private String mrchtName;

    private String termcode;

    private String pan;

    private String stan;

    private String rrn;

    private BigDecimal amttxn;

    private String transactiondate;

    private String transactiontime;

    private BigDecimal txnstatus;

    private BigDecimal txncode;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getMrchtName() {
        return mrchtName;
    }

    public void setMrchtName(String mrchtName) {
        this.mrchtName = mrchtName == null ? null : mrchtName.trim();
    }

    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public String getStan() {
        return stan;
    }

    public void setStan(String stan) {
        this.stan = stan == null ? null : stan.trim();
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn == null ? null : rrn.trim();
    }

    public BigDecimal getAmttxn() {
        return amttxn;
    }

    public void setAmttxn(BigDecimal amttxn) {
        this.amttxn = amttxn;
    }

    public String getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(String transactiondate) {
        this.transactiondate = transactiondate == null ? null : transactiondate.trim();
    }

    public String getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(String transactiontime) {
        this.transactiontime = transactiontime == null ? null : transactiontime.trim();
    }

    public BigDecimal getTxnstatus() {
        return txnstatus;
    }

    public void setTxnstatus(BigDecimal txnstatus) {
        this.txnstatus = txnstatus;
    }

    public BigDecimal getTxncode() {
        return txncode;
    }

    public void setTxncode(BigDecimal txncode) {
        this.txncode = txncode;
    }
}