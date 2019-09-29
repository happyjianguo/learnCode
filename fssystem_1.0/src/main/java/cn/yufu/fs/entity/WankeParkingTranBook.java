package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class WankeParkingTranBook {
    private String id;

    private String merNo;

    private String merName;

    private String terNo;

    private String cardnumber;

    private String cardType;

    private String transactiontype;
    
    //此字段WankeParkingTranBookExample未处理
    private String transactiontypedesc;

    private BigDecimal transactionmoney;

    private String transactiondate;

    private String transactiontime;

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

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype == null ? null : transactiontype.trim();
    }

    public String getTransactiontypedesc() {
        return transactiontypedesc;
    }

    public void setTransactiontypedesc(String transactiontypedesc) {
        this.transactiontypedesc = transactiontypedesc == null ? null : transactiontypedesc.trim();
    }
    
    
    public BigDecimal getTransactionmoney() {
        return transactionmoney;
    }

    public void setTransactionmoney(BigDecimal transactionmoney) {
        this.transactionmoney = transactionmoney;
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
}