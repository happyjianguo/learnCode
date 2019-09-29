package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearMerStlBook {
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

    private String sceneName;
    
    private String payout_date;
    
    private String seq_no;
    
    private String final_comments;
    
    private String flagOnline;//线上线下商户标志 1--线上   其他线下
    
    private String mergeMoneyFlag;//普通卡和专属卡是否合并打款
    
    private String merchantEmail;	//邮箱
    
    private String isSendBillDetail;	//是否发送对账明细，0否，1是
    
    private String pdfFlag;	//是否已经生成PDF文件，默认是否 -0
    
    private BigDecimal sendEmailTimes;	//发送邮件的失败次数,界面添加 最多5次
    
    private String sendEmailDate;	//发送邮件时间，界面添加
    
    public String getPayout_date() {
		return payout_date;
	}

	public void setPayout_date(String payoutDate) {
		payout_date = payoutDate;
	}

	public String getSeq_no() {
		return seq_no;
	}

	public void setSeq_no(String seqNo) {
		seq_no = seqNo;
	}

	public String getFinal_comments() {
		return final_comments;
	}

	public void setFinal_comments(String finalComments) {
		final_comments = finalComments;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
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

	public String getFlagOnline() {
		return flagOnline;
	}

	public void setFlagOnline(String flagOnline) {
		this.flagOnline = flagOnline;
	}

	public String getMergeMoneyFlag() {
		return mergeMoneyFlag;
	}

	public void setMergeMoneyFlag(String mergeMoneyFlag) {
		this.mergeMoneyFlag = mergeMoneyFlag;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getIsSendBillDetail() {
		return isSendBillDetail;
	}

	public void setIsSendBillDetail(String isSendBillDetail) {
		this.isSendBillDetail = isSendBillDetail;
	}

	public String getPdfFlag() {
		return pdfFlag;
	}

	public void setPdfFlag(String pdfFlag) {
		this.pdfFlag = pdfFlag;
	}

	public BigDecimal getSendEmailTimes() {
		return sendEmailTimes;
	}

	public void setSendEmailTimes(BigDecimal sendEmailTimes) {
		this.sendEmailTimes = sendEmailTimes;
	}

	public String getSendEmailDate() {
		return sendEmailDate;
	}

	public void setSendEmailDate(String sendEmailDate) {
		this.sendEmailDate = sendEmailDate;
	}

}