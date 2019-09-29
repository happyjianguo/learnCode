package cn.yufu.system.modules.cortexs.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.DataEntity;

public class CrdfeelogBefore extends DataEntity<CrdfeelogBefore>{
	private static final long serialVersionUID = 1L;

	private Long crdFeeLogId;

    private Long vernoCtx;

    private String pan;   //卡号

    private String turenamefalg;  //实名标识 0未实名 1实名
    
    private String nearlyDate;  //最近一次修改日期

    private String localDate;	//扣款日期

    private String merchantno;	//扣款商户号

    private String termcode;  //扣款终端号

    private BigDecimal avlbal;   //扣款前账户余额

    private String rate;  //费率  百分比

    private BigDecimal fee;  //手续费

    private Long feeRule;  //手续费收取规则ID

    private Long tlogId;	//tlog_id

    private String tranDate;	//交易日期

    private String tranTime;	//交易时间

    private Long stan;	//流水

    private String rrn;	//系统参考号

    private Integer txncode;	//交易码

    private String txnsrc;	//交易渠道

    private String rspcode;	//返回码

    private Integer txnstatus;	//交易状态   7 成功

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
    
    private String year;  //年
    
    private String month;  //月
    
    private String begainLocalDate;	//扣款日期
    
    private String endLocalDate;	//扣款日期
    
    private String noTranYear;  //无交易年数

    public Long getCrdFeeLogId() {
        return crdFeeLogId;
    }

    public void setCrdFeeLogId(Long crdFeeLogId) {
        this.crdFeeLogId = crdFeeLogId;
    }

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    public String getTurenamefalg() {
        return turenamefalg;
    }

    public void setTurenamefalg(String turenamefalg) {
        this.turenamefalg = turenamefalg == null ? null : turenamefalg.trim();
    }
    
    public String getNearlyDate() {
		return nearlyDate;
	}

	public void setNearlyDate(String nearlyDate) {
		this.nearlyDate = nearlyDate == null ? null : nearlyDate.trim();
	}

	public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate == null ? null : localDate.trim();
    }

    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno == null ? null : merchantno.trim();
    }

    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    public BigDecimal getAvlbal() {
        return avlbal;
    }

    public void setAvlbal(BigDecimal avlbal) {
        this.avlbal = avlbal;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Long getFeeRule() {
        return feeRule;
    }

    public void setFeeRule(Long feeRule) {
        this.feeRule = feeRule;
    }

    public Long getTlogId() {
        return tlogId;
    }

    public void setTlogId(Long tlogId) {
        this.tlogId = tlogId;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime == null ? null : tranTime.trim();
    }

    public Long getStan() {
        return stan;
    }

    public void setStan(Long stan) {
        this.stan = stan;
    }

    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn == null ? null : rrn.trim();
    }

    public Integer getTxncode() {
        return txncode;
    }

    public void setTxncode(Integer txncode) {
        this.txncode = txncode;
    }

    public String getTxnsrc() {
        return txnsrc;
    }

    public void setTxnsrc(String txnsrc) {
        this.txnsrc = txnsrc == null ? null : txnsrc.trim();
    }

    public String getRspcode() {
        return rspcode;
    }

    public void setRspcode(String rspcode) {
        this.rspcode = rspcode == null ? null : rspcode.trim();
    }

    public Integer getTxnstatus() {
        return txnstatus;
    }

    public void setTxnstatus(Integer txnstatus) {
        this.txnstatus = txnstatus;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public String getReserved4() {
        return reserved4;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4 == null ? null : reserved4.trim();
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