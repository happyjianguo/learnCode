package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearCheckErr {
    private String id;

    private String genDt;

    private String checkTime;

    private String genType;

    private String cardNo;

    private String merNo;

    private String merName;

    private String termNo;

    private String tranDate;

    private String tranTime;

    private String tranType;

    private BigDecimal corebatNo;

    private BigDecimal corejonlNo;

    private String coreRrn;

    private BigDecimal coretranAmt;

    private BigDecimal acqbatNo;

    private BigDecimal acqjonlNo;

    private String acqRrn;

    private BigDecimal acqtranAmt;

    private BigDecimal fee;

    private String chkFlag;

    private String status;

    private String adjustFlag;

    private String dcFlag;

    private String stlFlag;

    private BigDecimal stlAmt;

    private String errbookId;

    private String operor1;

    private String operDt1;

    private String comments1;

    private String operor2;

    private String operDt2;

    private String comments2;

    private String comments;

    private String consumeType;
    
        //时间段查询条件
    private String startDt;
    
    private String endDt;   
    
    private String termCode;
    
    private String tranTypeDesc;
    

    public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}

	public String getTermCode() {
		return termCode;
	}

	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}

	public String getTranTypeDesc() {
		return tranTypeDesc;
	}

	public void setTranTypeDesc(String tranTypeDesc) {
		this.tranTypeDesc = tranTypeDesc;
	}

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

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime == null ? null : checkTime.trim();
    }

    public String getGenType() {
        return genType;
    }

    public void setGenType(String genType) {
        this.genType = genType == null ? null : genType.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
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

    public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo == null ? null : termNo.trim();
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

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType == null ? null : tranType.trim();
    }

    public BigDecimal getCorebatNo() {
        return corebatNo;
    }

    public void setCorebatNo(BigDecimal corebatNo) {
        this.corebatNo = corebatNo;
    }

    public BigDecimal getCorejonlNo() {
        return corejonlNo;
    }

    public void setCorejonlNo(BigDecimal corejonlNo) {
        this.corejonlNo = corejonlNo;
    }

    public String getCoreRrn() {
        return coreRrn;
    }

    public void setCoreRrn(String coreRrn) {
        this.coreRrn = coreRrn == null ? null : coreRrn.trim();
    }

    public BigDecimal getCoretranAmt() {
        return coretranAmt;
    }

    public void setCoretranAmt(BigDecimal coretranAmt) {
        this.coretranAmt = coretranAmt;
    }

    public BigDecimal getAcqbatNo() {
        return acqbatNo;
    }

    public void setAcqbatNo(BigDecimal acqbatNo) {
        this.acqbatNo = acqbatNo;
    }

    public BigDecimal getAcqjonlNo() {
        return acqjonlNo;
    }

    public void setAcqjonlNo(BigDecimal acqjonlNo) {
        this.acqjonlNo = acqjonlNo;
    }

    public String getAcqRrn() {
        return acqRrn;
    }

    public void setAcqRrn(String acqRrn) {
        this.acqRrn = acqRrn == null ? null : acqRrn.trim();
    }

    public BigDecimal getAcqtranAmt() {
        return acqtranAmt;
    }

    public void setAcqtranAmt(BigDecimal acqtranAmt) {
        this.acqtranAmt = acqtranAmt;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getChkFlag() {
        return chkFlag;
    }

    public void setChkFlag(String chkFlag) {
        this.chkFlag = chkFlag == null ? null : chkFlag.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAdjustFlag() {
        return adjustFlag;
    }

    public void setAdjustFlag(String adjustFlag) {
        this.adjustFlag = adjustFlag == null ? null : adjustFlag.trim();
    }

    public String getDcFlag() {
        return dcFlag;
    }

    public void setDcFlag(String dcFlag) {
        this.dcFlag = dcFlag == null ? null : dcFlag.trim();
    }

    public String getStlFlag() {
        return stlFlag;
    }

    public void setStlFlag(String stlFlag) {
        this.stlFlag = stlFlag == null ? null : stlFlag.trim();
    }

    public BigDecimal getStlAmt() {
        return stlAmt;
    }

    public void setStlAmt(BigDecimal stlAmt) {
        this.stlAmt = stlAmt;
    }

    public String getErrbookId() {
        return errbookId;
    }

    public void setErrbookId(String errbookId) {
        this.errbookId = errbookId == null ? null : errbookId.trim();
    }

    public String getOperor1() {
        return operor1;
    }

    public void setOperor1(String operor1) {
        this.operor1 = operor1 == null ? null : operor1.trim();
    }

    public String getOperDt1() {
        return operDt1;
    }

    public void setOperDt1(String operDt1) {
        this.operDt1 = operDt1 == null ? null : operDt1.trim();
    }

    public String getComments1() {
        return comments1;
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1 == null ? null : comments1.trim();
    }

    public String getOperor2() {
        return operor2;
    }

    public void setOperor2(String operor2) {
        this.operor2 = operor2 == null ? null : operor2.trim();
    }

    public String getOperDt2() {
        return operDt2;
    }

    public void setOperDt2(String operDt2) {
        this.operDt2 = operDt2 == null ? null : operDt2.trim();
    }

    public String getComments2() {
        return comments2;
    }

    public void setComments2(String comments2) {
        this.comments2 = comments2 == null ? null : comments2.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(String consumeType) {
        this.consumeType = consumeType == null ? null : consumeType.trim();
    }
}