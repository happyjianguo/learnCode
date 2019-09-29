package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClientAccBalance {
    private String id;

    private String genDt;

    private String dailyDate;

    private BigDecimal cardBal;

    private BigDecimal waitStlAmt;

    private BigDecimal errAmt;

    private BigDecimal custTotlAmt;

    private String comments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGenDt() {
        return genDt;
    }

    public void setGenDt(String genDt) {
        this.genDt = genDt == null ? null : genDt.trim();
    }

    public String getDailyDate() {
        return dailyDate;
    }

    public void setDailyDate(String dailyDate) {
        this.dailyDate = dailyDate == null ? null : dailyDate.trim();
    }

    public BigDecimal getCardBal() {
        return cardBal;
    }

    public void setCardBal(BigDecimal cardBal) {
        this.cardBal = cardBal;
    }

    public BigDecimal getWaitStlAmt() {
        return waitStlAmt;
    }

    public void setWaitStlAmt(BigDecimal waitStlAmt) {
        this.waitStlAmt = waitStlAmt;
    }

    public BigDecimal getErrAmt() {
        return errAmt;
    }

    public void setErrAmt(BigDecimal errAmt) {
        this.errAmt = errAmt;
    }

    public BigDecimal getCustTotlAmt() {
        return custTotlAmt;
    }

    public void setCustTotlAmt(BigDecimal custTotlAmt) {
        this.custTotlAmt = custTotlAmt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}