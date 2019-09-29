package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearMerClearBook {
    private String srcId;

    private String srcFlag;

    private String genDt;

    private String merNo;

    private String merName;

    private String fmrchNo;

    private String fmrchName;

    private String tranDate;

    private String scene;

    private String crdproduct;

    private String cardTypeName;

    private BigDecimal tranNum;

    private BigDecimal tranAmt;

    private BigDecimal refNum;

    private BigDecimal refAmt;

    private BigDecimal fee;

    private String feeType;

    private BigDecimal netAmt;

    private String stlFlag;

    private String stlBookId;

    private BigDecimal stlAmt;

    private String stlDate;

    private String feeStlId;

    private String feeFlag;

    private String comments;

    private String startDt;
    
    private String endDt;    
    
    private String sceneName;
    
    public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

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

	public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId == null ? null : srcId.trim();
    }

    public String getSrcFlag() {
        return srcFlag;
    }

    public void setSrcFlag(String srcFlag) {
        this.srcFlag = srcFlag == null ? null : srcFlag.trim();
    }

    public String getGenDt() {
        return genDt;
    }

    public void setGenDt(String genDt) {
        this.genDt = genDt == null ? null : genDt.trim();
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

    public String getFmrchNo() {
        return fmrchNo;
    }

    public void setFmrchNo(String fmrchNo) {
        this.fmrchNo = fmrchNo == null ? null : fmrchNo.trim();
    }

    public String getFmrchName() {
        return fmrchName;
    }

    public void setFmrchName(String fmrchName) {
        this.fmrchName = fmrchName == null ? null : fmrchName.trim();
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public BigDecimal getTranNum() {
        return tranNum;
    }

    public void setTranNum(BigDecimal tranNum) {
        this.tranNum = tranNum;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public BigDecimal getRefNum() {
        return refNum;
    }

    public void setRefNum(BigDecimal refNum) {
        this.refNum = refNum;
    }

    public BigDecimal getRefAmt() {
        return refAmt;
    }

    public void setRefAmt(BigDecimal refAmt) {
        this.refAmt = refAmt;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }

    public BigDecimal getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(BigDecimal netAmt) {
        this.netAmt = netAmt;
    }

    public String getStlFlag() {
        return stlFlag;
    }

    public void setStlFlag(String stlFlag) {
        this.stlFlag = stlFlag == null ? null : stlFlag.trim();
    }

    public String getStlBookId() {
        return stlBookId;
    }

    public void setStlBookId(String stlBookId) {
        this.stlBookId = stlBookId == null ? null : stlBookId.trim();
    }

    public BigDecimal getStlAmt() {
        return stlAmt;
    }

    public void setStlAmt(BigDecimal stlAmt) {
        this.stlAmt = stlAmt;
    }

    public String getStlDate() {
        return stlDate;
    }

    public void setStlDate(String stlDate) {
        this.stlDate = stlDate == null ? null : stlDate.trim();
    }

    public String getFeeStlId() {
        return feeStlId;
    }

    public void setFeeStlId(String feeStlId) {
        this.feeStlId = feeStlId == null ? null : feeStlId.trim();
    }

    public String getFeeFlag() {
        return feeFlag;
    }

    public void setFeeFlag(String feeFlag) {
        this.feeFlag = feeFlag == null ? null : feeFlag.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }
}