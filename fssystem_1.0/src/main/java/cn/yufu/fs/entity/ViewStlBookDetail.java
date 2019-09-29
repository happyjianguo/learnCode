package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ViewStlBookDetail {
    private String id;

    private String detailId;

    private String cardNo;

    private String merNo;
    
    private String fmrchNo; 	//父商户

    private String termNo;

    private String timezone;

    private BigDecimal fee;

    private String merName;

    private String tranType;

    private String tranDate;

    private String tranTime;

    private BigDecimal tranAmt;
    
    private String tranTypeDesc;
    
    private String stlDate;	//交易日期
    
    private String merchantEmail;	//邮箱
    
    private String isSendBillDetail;	//是否发送对账明细，0否，1是
    
    private String startStlDate;	//结算开始日期

    private String endStlDate;	//结算结束日期

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

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId == null ? null : detailId.trim();
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

    public String getFmrchNo() {
		return fmrchNo;
	}

	public void setFmrchNo(String fmrchNo) {
		this.fmrchNo = fmrchNo == null ? null : fmrchNo.trim();
	}

	public String getTermNo() {
        return termNo;
    }

    public void setTermNo(String termNo) {
        this.termNo = termNo == null ? null : termNo.trim();
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone == null ? null : timezone.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType == null ? null : tranType.trim();
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

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

	public String getStlDate() {
		return stlDate;
	}

	public void setStlDate(String stlDate) {
		this.stlDate = stlDate;
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

	public String getStartStlDate() {
		return startStlDate;
	}

	public void setStartStlDate(String startStlDate) {
		this.startStlDate = startStlDate;
	}

	public String getEndStlDate() {
		return endStlDate;
	}

	public void setEndStlDate(String endStlDate) {
		this.endStlDate = endStlDate;
	}
    
}