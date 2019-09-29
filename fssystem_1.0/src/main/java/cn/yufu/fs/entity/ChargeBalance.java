package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ChargeBalance {
    private String id;

    private String merNo;

    private String merName;

    private String terminalId;

    private String cardNo;

    private BigDecimal voidTraceNo;

    private String voidSysDate;

    private String voidSysTime;

    private BigDecimal voidAmt;

    private String voidTraceCode;

    private String voidRespCode;

    private BigDecimal chargeAmt;

    private String chargeFlag;

    private BigDecimal traceNo;

    private String respCode;
    
    private String respMsg;

    private String picRoute;

    private String createBy;

    private String createDate;

    private String remarks;

    private String delFlag = "0";
    
    private String tlogId;
    
    private String startChargeDate;
    
    private String endChargeDate;
    
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

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public BigDecimal getVoidTraceNo() {
        return voidTraceNo;
    }

    public void setVoidTraceNo(BigDecimal voidTraceNo) {
        this.voidTraceNo = voidTraceNo;
    }

    public String getVoidSysDate() {
        return voidSysDate;
    }

    public void setVoidSysDate(String voidSysDate) {
        this.voidSysDate = voidSysDate == null ? null : voidSysDate.trim();
    }

    public String getVoidSysTime() {
        return voidSysTime;
    }

    public void setVoidSysTime(String voidSysTime) {
        this.voidSysTime = voidSysTime == null ? null : voidSysTime.trim();
    }

    public BigDecimal getVoidAmt() {
        return voidAmt;
    }

    public void setVoidAmt(BigDecimal voidAmt) {
        this.voidAmt = voidAmt;
    }

    public String getVoidTraceCode() {
        return voidTraceCode;
    }

    public void setVoidTraceCode(String voidTraceCode) {
        this.voidTraceCode = voidTraceCode == null ? null : voidTraceCode.trim();
    }

    public String getVoidRespCode() {
        return voidRespCode;
    }

    public void setVoidRespCode(String voidRespCode) {
        this.voidRespCode = voidRespCode == null ? null : voidRespCode.trim();
    }

    public BigDecimal getChargeAmt() {
        return chargeAmt;
    }

    public void setChargeAmt(BigDecimal chargeAmt) {
        this.chargeAmt = chargeAmt;
    }

    public String getChargeFlag() {
        return chargeFlag;
    }

    public void setChargeFlag(String chargeFlag) {
        this.chargeFlag = chargeFlag == null ? null : chargeFlag.trim();
    }

    public BigDecimal getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(BigDecimal traceNo) {
        this.traceNo = traceNo;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode == null ? null : respCode.trim();
    }

    public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg == null ? null : respMsg.trim();
	}

	public String getPicRoute() {
        return picRoute;
    }

    public void setPicRoute(String picRoute) {
        this.picRoute = picRoute == null ? null : picRoute.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
    	this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

	public String getTlogId() {
		return tlogId;
	}

	public void setTlogId(String tlogId) {
		this.tlogId = tlogId == null ? null : tlogId.trim();
	}

	public String getStartChargeDate() {
		return startChargeDate;
	}

	public void setStartChargeDate(String startChargeDate) {
		this.startChargeDate = startChargeDate == null ? null : startChargeDate.trim();
	}

	public String getEndChargeDate() {
		return endChargeDate;
	}

	public void setEndChargeDate(String endChargeDate) {
		this.endChargeDate = endChargeDate == null ? null : endChargeDate.trim();
	}

}