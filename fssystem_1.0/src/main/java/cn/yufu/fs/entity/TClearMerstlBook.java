package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class TClearMerstlBook {
    private String id;

    private String genDt;

    private String merNo;

    private String merName;

    private String fmrchNo;

    private String fmrName;

    private String stlDate;

    private String startStlDate;

    private String endStlDate;

    private String scene;

    private BigDecimal consumNum;

    private BigDecimal consumAmt;

    private BigDecimal tranAmt;

    private BigDecimal netAmt;

    private BigDecimal fee;

    private BigDecimal feeOrder;

    private String feestlType;

    private String status;

    private Long stlaccId;

    private String accNo;

    private String accName;

    private String bankName;

    private String bankCode;

    private String branchName;

    private String unionNo;

    private String beijingFlag;

    private String isConsumpCategory;

    private String amtConsumpCategory;

    private String groupFlag;

    private String finalStlId;

    private String finalStlFlag;

    private String comments;

    private Long provinceCode;

    private String provinceName;

    private String bjFlag;

    private String cardType;

    private String cardTypeName;

    private String exelusiveCardFlag;

    private String stlNeedFlag;

    private Long cityCode;

    private String cityName;

    private String mrchSnippet;

    private String mergeMoneyFlag;

    private String rate; //费率(来自备库)

    private String mrchstat; //商户状态(来自备库)

    private String reservedField2;

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

    public String getFmrName() {
        return fmrName;
    }

    public void setFmrName(String fmrName) {
        this.fmrName = fmrName == null ? null : fmrName.trim();
    }

    public String getStlDate() {
        return stlDate;
    }

    public void setStlDate(String stlDate) {
        this.stlDate = stlDate == null ? null : stlDate.trim();
    }

    public String getStartStlDate() {
        return startStlDate;
    }

    public void setStartStlDate(String startStlDate) {
        this.startStlDate = startStlDate == null ? null : startStlDate.trim();
    }

    public String getEndStlDate() {
        return endStlDate;
    }

    public void setEndStlDate(String endStlDate) {
        this.endStlDate = endStlDate == null ? null : endStlDate.trim();
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public BigDecimal getConsumNum() {
        return consumNum;
    }

    public void setConsumNum(BigDecimal consumNum) {
        this.consumNum = consumNum;
    }

    public BigDecimal getConsumAmt() {
        return consumAmt;
    }

    public void setConsumAmt(BigDecimal consumAmt) {
        this.consumAmt = consumAmt;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public BigDecimal getNetAmt() {
        return netAmt;
    }

    public void setNetAmt(BigDecimal netAmt) {
        this.netAmt = netAmt;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getFeeOrder() {
        return feeOrder;
    }

    public void setFeeOrder(BigDecimal feeOrder) {
        this.feeOrder = feeOrder;
    }

    public String getFeestlType() {
        return feestlType;
    }

    public void setFeestlType(String feestlType) {
        this.feestlType = feestlType == null ? null : feestlType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Long getStlaccId() {
        return stlaccId;
    }

    public void setStlaccId(Long stlaccId) {
        this.stlaccId = stlaccId;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo == null ? null : accNo.trim();
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName == null ? null : accName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getUnionNo() {
        return unionNo;
    }

    public void setUnionNo(String unionNo) {
        this.unionNo = unionNo == null ? null : unionNo.trim();
    }

    public String getBeijingFlag() {
        return beijingFlag;
    }

    public void setBeijingFlag(String beijingFlag) {
        this.beijingFlag = beijingFlag == null ? null : beijingFlag.trim();
    }

    public String getIsConsumpCategory() {
        return isConsumpCategory;
    }

    public void setIsConsumpCategory(String isConsumpCategory) {
        this.isConsumpCategory = isConsumpCategory == null ? null : isConsumpCategory.trim();
    }

    public String getAmtConsumpCategory() {
        return amtConsumpCategory;
    }

    public void setAmtConsumpCategory(String amtConsumpCategory) {
        this.amtConsumpCategory = amtConsumpCategory == null ? null : amtConsumpCategory.trim();
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag == null ? null : groupFlag.trim();
    }

    public String getFinalStlId() {
        return finalStlId;
    }

    public void setFinalStlId(String finalStlId) {
        this.finalStlId = finalStlId == null ? null : finalStlId.trim();
    }

    public String getFinalStlFlag() {
        return finalStlFlag;
    }

    public void setFinalStlFlag(String finalStlFlag) {
        this.finalStlFlag = finalStlFlag == null ? null : finalStlFlag.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    public String getBjFlag() {
        return bjFlag;
    }

    public void setBjFlag(String bjFlag) {
        this.bjFlag = bjFlag == null ? null : bjFlag.trim();
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    public String getExelusiveCardFlag() {
        return exelusiveCardFlag;
    }

    public void setExelusiveCardFlag(String exelusiveCardFlag) {
        this.exelusiveCardFlag = exelusiveCardFlag == null ? null : exelusiveCardFlag.trim();
    }

    public String getStlNeedFlag() {
        return stlNeedFlag;
    }

    public void setStlNeedFlag(String stlNeedFlag) {
        this.stlNeedFlag = stlNeedFlag == null ? null : stlNeedFlag.trim();
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getMrchSnippet() {
        return mrchSnippet;
    }

    public void setMrchSnippet(String mrchSnippet) {
        this.mrchSnippet = mrchSnippet == null ? null : mrchSnippet.trim();
    }

    public String getMergeMoneyFlag() {
        return mergeMoneyFlag;
    }

    public void setMergeMoneyFlag(String mergeMoneyFlag) {
        this.mergeMoneyFlag = mergeMoneyFlag == null ? null : mergeMoneyFlag.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getMrchstat() {
        return mrchstat;
    }

    public void setMrchstat(String mrchstat) {
        this.mrchstat = mrchstat == null ? null : mrchstat.trim();
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2 == null ? null : reservedField2.trim();
    }

	@Override
	public String toString() {
		return "TClearMerstlBook [merNo=" + merNo + ", merName=" + merName + ", stlDate=" + stlDate + ", startStlDate="
				+ startStlDate + ", endStlDate=" + endStlDate + ", consumNum=" + consumNum + ", tranAmt=" + tranAmt
				+ ", provinceName=" + provinceName + ", cityCode=" + cityCode + ", cityName=" + cityName + ", rate="
				+ rate + "]";
	}
    
}