package cn.yufu.system.modules.cortexs.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.DataEntity;
import cn.yufu.system.common.utils.excel.annotation.ExcelField;

public class CrdFeeRefundOrder extends DataEntity<CrdFeeRefundOrder>{
	private static final long serialVersionUID = 1L;

	private String crdfeelogId;

    private String pan;

    private String merchantno;

    private String termcode;

    private BigDecimal fee;

    private BigDecimal tlogId;

    private String tranDate;

    private String tranTime;

    private BigDecimal stan;

    private String rrn;

    private Integer txncode;

    private String txnsrc;

    private String rspcode;

    private Integer txnstatus;

    private String adddate;

    private String addtime;

    private Integer refundflag;

    private String oper;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
    
    private String begainTranDate;		//原交易开始时间
    
    private String endTranDate;			//原交易结束时间
    
    private String begainAdddate;		//退款开始时间
    
    private String endAdddate;			//退款结束时间

    @ExcelField(title="扣款明细ID", align=2, sort=10)	
    public String getCrdfeelogId() {
        return crdfeelogId;
    }

    public void setCrdfeelogId(String crdfeelogId) {
        this.crdfeelogId = crdfeelogId;
    }

    @ExcelField(title="退款商户号", align=2, sort=30)	
    public String getMerchantno() {
        return merchantno;
    }
    
    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno == null ? null : merchantno.trim();
    }

    @ExcelField(title="退款终端号", align=2, sort=40)	
    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    @ExcelField(title="退款卡号", align=2, sort=50)	
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }
    
    @ExcelField(title="退款金额", align=2, sort=80)	
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTlogId() {
        return tlogId;
    }

    public void setTlogId(BigDecimal tlogId) {
        this.tlogId = tlogId;
    }

    @ExcelField(title="原交易日期", align=2, sort=90)	
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

    @ExcelField(title="原流水号", align=2, sort=100)	
    public BigDecimal getStan() {
        return stan;
    }

    public void setStan(BigDecimal stan) {
        this.stan = stan;
    }

    @ExcelField(title="原参考号", align=2, sort=120)	
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

    @ExcelField(title="退款状态", align=2, sort=130, dictType="REFUND_STATE")	
    public Integer getRefundflag() {
        return refundflag;
    }

    public void setRefundflag(Integer refundflag) {
        this.refundflag = refundflag;
    }
    
    @ExcelField(title="退款日期", align=2, sort=150)	
    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate == null ? null : adddate.trim();
    }

    @ExcelField(title="退款时间", align=2, sort=160)	
    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    @ExcelField(title="操作人", align=2, sort=200)	
    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper == null ? null : oper.trim();
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

	public String getBegainTranDate() {
		return begainTranDate;
	}

	public void setBegainTranDate(String begainTranDate) {
		this.begainTranDate = begainTranDate;
	}

	public String getEndTranDate() {
		return endTranDate;
	}

	public void setEndTranDate(String endTranDate) {
		this.endTranDate = endTranDate;
	}

	public String getBegainAdddate() {
		return begainAdddate;
	}

	public void setBegainAdddate(String begainAdddate) {
		this.begainAdddate = begainAdddate;
	}

	public String getEndAdddate() {
		return endAdddate;
	}

	public void setEndAdddate(String endAdddate) {
		this.endAdddate = endAdddate;
	}
    
}