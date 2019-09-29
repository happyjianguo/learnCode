package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class OldCrdfeelogBefore {
	private String id;

    private String merchantnumber;

    private String terminalnumber;

    private String pan;

    private String loacleDate;

    private String transactiondate;

    private String transactiontime;

    private BigDecimal fee;

    private BigDecimal avlbal;

    private Long tlogid;

    private BigDecimal serialnumber;

    private BigDecimal lotno;

    private String referencenumber;

    private String comments;

    private String comments1;

    private String comments2;

    private String comments3;
    
    private String year;  //年
    
    private String month;  //月
    
    private String begainLocalDate;	//扣款日期
    
    private String endLocalDate;	//扣款日期
    
    private String noTranYear;  //无交易年数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMerchantnumber() {
        return merchantnumber;
    }

    public void setMerchantnumber(String merchantnumber) {
        this.merchantnumber = merchantnumber == null ? null : merchantnumber.trim();
    }

    public String getTerminalnumber() {
        return terminalnumber;
    }

    public void setTerminalnumber(String terminalnumber) {
        this.terminalnumber = terminalnumber == null ? null : terminalnumber.trim();
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public String getLoacleDate() {
        return loacleDate;
    }

    public void setLoacleDate(String loacleDate) {
        this.loacleDate = loacleDate == null ? null : loacleDate.trim();
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getAvlbal() {
        return avlbal;
    }

    public void setAvlbal(BigDecimal avlbal) {
        this.avlbal = avlbal;
    }

    public Long getTlogid() {
        return tlogid;
    }

    public void setTlogid(Long tlogid) {
        this.tlogid = tlogid;
    }

    public BigDecimal getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(BigDecimal serialnumber) {
        this.serialnumber = serialnumber;
    }

    public BigDecimal getLotno() {
        return lotno;
    }

    public void setLotno(BigDecimal lotno) {
        this.lotno = lotno;
    }

    public String getReferencenumber() {
        return referencenumber;
    }

    public void setReferencenumber(String referencenumber) {
        this.referencenumber = referencenumber == null ? null : referencenumber.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getComments1() {
        return comments1;
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1 == null ? null : comments1.trim();
    }

    public String getComments2() {
        return comments2;
    }

    public void setComments2(String comments2) {
        this.comments2 = comments2 == null ? null : comments2.trim();
    }

    public String getComments3() {
        return comments3;
    }

    public void setComments3(String comments3) {
        this.comments3 = comments3 == null ? null : comments3.trim();
    }
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year == null ? null : year.trim();
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month == null ? null : month.trim();
	}

	public String getBegainLocalDate() {
		return begainLocalDate;
	}

	public void setBegainLocalDate(String begainLocalDate) {
		this.begainLocalDate = begainLocalDate;
	}

	public String getEndLocalDate() {
		return endLocalDate;
	}

	public void setEndLocalDate(String endLocalDate) {
		this.endLocalDate = endLocalDate;
	}

	public String getNoTranYear() {
		return noTranYear;
	}

	public void setNoTranYear(String noTranYear) {
		this.noTranYear = noTranYear;
	}
}