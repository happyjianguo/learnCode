package cn.yufu.system.modules.cortexs.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.DataEntity;
import cn.yufu.system.common.utils.excel.annotation.ExcelField;

public class CrdfeeCkjfFlog extends DataEntity<CrdfeeCkjfFlog>{
	private static final long serialVersionUID = 8580903837565106471L;

	private String primayKey;
	
	private BigDecimal vernoCtx;

    private String pan;			//卡号

    private String localDate;	//扣款日期

    private String merchantno;	//扣款商户号

    private String termcode;	//扣款终端号

    private String accflag;		//扣款账户类型  04购物返积分  09联名卡送积分

    private BigDecimal avlbal;		//扣款前账户余额 元

    private BigDecimal tlogId;		//tlog id

    private BigDecimal amttxn;		//扣款金额  元

    private String tranDate;	//交易日期

    private String tranTime;	//交易时间

    private BigDecimal stan;			//流水

    private String rrn;			//系统参考号

    private BigDecimal txncode;	//交易码

    private String txnsrc;		//交易渠道

    private String rspcode;		//返回码

    private BigDecimal txnstatus;	//交易状态

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
    
    private String begainTranDate;	//开始交易日期
    
    private String endTranDate;		//结束交易日期

    @ExcelField(title="过期积分表ID", align=2, sort=10)	
    public String getPrimayKey() {
		return primayKey;
	}

	public void setPrimayKey(String primayKey) {
		this.primayKey = primayKey;
	}

	public BigDecimal getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(BigDecimal vernoCtx) {
        this.vernoCtx = vernoCtx;
    }
    
    @ExcelField(title="TLOG_ID", align=2, sort=20)	
    public BigDecimal getTlogId() {
        return tlogId;
    }

    public void setTlogId(BigDecimal tlogId) {
        this.tlogId = tlogId;
    }

    @ExcelField(title="卡号", align=2, sort=30)	
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan == null ? null : pan.trim();
    }

    @ExcelField(title="扣款商户号", align=2, sort=40)	
    public String getMerchantno() {
        return merchantno;
    }

    public void setMerchantno(String merchantno) {
        this.merchantno = merchantno == null ? null : merchantno.trim();
    }

    @ExcelField(title="扣款终端号", align=2, sort=50)	
    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    @ExcelField(title="扣款账户类型", align=2, sort=60, dictType="CRDFEE_TYPE")	
    public String getAccflag() {
        return accflag;
    }

    public void setAccflag(String accflag) {
        this.accflag = accflag == null ? null : accflag.trim();
    }
    
    @ExcelField(title="扣款前账户余额(元)", align=2, sort=100)	
    public BigDecimal getAvlbal() {
        return avlbal;
    }

    public void setAvlbal(BigDecimal avlbal) {
        this.avlbal = avlbal;
    }
    
    @ExcelField(title="扣款金额(元)", align=2, sort=110)	
    public BigDecimal getAmttxn() {
        return amttxn;
    }

    public void setAmttxn(BigDecimal amttxn) {
        this.amttxn = amttxn;
    }
    
    @ExcelField(title="扣款日期", align=2, sort=120)	
    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate == null ? null : localDate.trim();
    }

    @ExcelField(title="交易日期", align=2, sort=130)	
    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    @ExcelField(title="交易时间", align=2, sort=140)	
    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime == null ? null : tranTime.trim();
    }

    @ExcelField(title="流水", align=2, sort=160)	
    public BigDecimal getStan() {
        return stan;
    }

    public void setStan(BigDecimal stan) {
        this.stan = stan;
    }

    @ExcelField(title="系统参考号", align=2, sort=170)	
    public String getRrn() {
        return rrn;
    }

    public void setRrn(String rrn) {
        this.rrn = rrn == null ? null : rrn.trim();
    }

    @ExcelField(title="交易码", align=2, sort=180)	
    public BigDecimal getTxncode() {
        return txncode;
    }

    public void setTxncode(BigDecimal txncode) {
        this.txncode = txncode;
    }

    @ExcelField(title="交易渠道", align=2, sort=200)	
    public String getTxnsrc() {
        return txnsrc;
    }

    public void setTxnsrc(String txnsrc) {
        this.txnsrc = txnsrc == null ? null : txnsrc.trim();
    }

    @ExcelField(title="返回码", align=2, sort=210)	
    public String getRspcode() {
        return rspcode;
    }

    public void setRspcode(String rspcode) {
        this.rspcode = rspcode == null ? null : rspcode.trim();
    }

    @ExcelField(title="交易状态", align=2, sort=230)	
    public BigDecimal getTxnstatus() {
        return txnstatus;
    }

    public void setTxnstatus(BigDecimal txnstatus) {
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
    
}