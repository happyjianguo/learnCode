package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearMerStlFinalBook {
    private String id;

    private String genDt;

    private String termMerNo;

    private String merNo;

    private String scene;

    private String merName;

    private String stlDate;
    //为结算 日期查询 期间添加
    private String stlDate1;

    private String startStlDate;

    private String endStlDate;

    private BigDecimal consumNum;

    private BigDecimal consumAmt;

    private BigDecimal netAmt;

    private BigDecimal fee;

    private BigDecimal tranAmt;

    private Long stlaccId;

    private String accNo;			//收款账号

    private String accBankName;		//开户行银行名称

    private String accountName;		//开户行

    private String bankUnion;

    private String bankCode;

    private String beijingFlag;

    private String payoutDate;		//打款日期

    private String payoutJunl;		//收款账户名

    private String seqNo;			//凭证号

    private String payoutStatus;	//打款状态:0-未打款(默认) 1-审核通过 2-打款中 3-打款成功 4-打款失败 5-发送失败 8-作废 

    private String comments;

    private Long provinceCode;

    private String provinceName;

    private String bjFlag;

    private String cardType;

    private String exelusiveCardFlag;

    private String stlNeedFlag;

    private String cardTypeName;

    private Long cityCode;

    private String cityName;
    
    private String flagOnline;			//线上线下商户标志 1--线上   其他线下
    
    private String mergeMoneyFlag;		//普卡专属卡是否合并打款
    
    private String merchantOrg;  		//分润机构
    
    private String merchantAmt; 		//分润金额
    
    private String consumAmtTotal;		//打印支出凭单时，显示的算法及其卡类型名称
    
    private String settlementPerson;	//结算员
    
    private String payAccountType;		//收款账户属性（00-对公账户，01-对私账户）
    
    private String accProvinceName;		//开户行所在省份
    
    private String accCityName;			//开户行所在市
    
    private String accXText;			//附言
 
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

    public String getTermMerNo() {
        return termMerNo;
    }

    public void setTermMerNo(String termMerNo) {
        this.termMerNo = termMerNo == null ? null : termMerNo.trim();
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public String getStlDate() {
        return stlDate;
    }

    public void setStlDate(String stlDate) {
        this.stlDate = stlDate == null ? null : stlDate.trim();
    }
    
    public String getStlDate1() {
		return stlDate1;
	}

	public void setStlDate1(String stlDate1) {
		this.stlDate1 = stlDate1;
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

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
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

    public String getAccBankName() {
        return accBankName;
    }

    public void setAccBankName(String accBankName) {
        this.accBankName = accBankName == null ? null : accBankName.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getBankUnion() {
        return bankUnion;
    }

    public void setBankUnion(String bankUnion) {
        this.bankUnion = bankUnion == null ? null : bankUnion.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBeijingFlag() {
        return beijingFlag;
    }

    public void setBeijingFlag(String beijingFlag) {
        this.beijingFlag = beijingFlag == null ? null : beijingFlag.trim();
    }

    public String getPayoutDate() {
        return payoutDate;
    }

    public void setPayoutDate(String payoutDate) {
        this.payoutDate = payoutDate == null ? null : payoutDate.trim();
    }

    public String getPayoutJunl() {
        return payoutJunl;
    }

    public void setPayoutJunl(String payoutJunl) {
        this.payoutJunl = payoutJunl == null ? null : payoutJunl.trim();
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    public String getPayoutStatus() {
        return payoutStatus;
    }

    public void setPayoutStatus(String payoutStatus) {
        this.payoutStatus = payoutStatus == null ? null : payoutStatus.trim();
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

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
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

	public String getMerchantOrg() {
		return merchantOrg;
	}

	public void setMerchantOrg(String merchantOrg) {
		this.merchantOrg = merchantOrg == null ? null : merchantOrg.trim();
	}

	public String getMerchantAmt() {
		return merchantAmt;
	}

	public void setMerchantAmt(String merchantAmt) {
		this.merchantAmt = merchantAmt == null ? null : merchantAmt.trim();
	}

	public String getConsumAmtTotal() {
		return consumAmtTotal;
	}

	public void setConsumAmtTotal(String consumAmtTotal) {
		this.consumAmtTotal = consumAmtTotal;
	}

	public String getSettlementPerson() {
		return settlementPerson;
	}

	public void setSettlementPerson(String settlementPerson) {
		this.settlementPerson = settlementPerson;
	}

	public String getPayAccountType() {
		return payAccountType;
	}

	public void setPayAccountType(String payAccountType) {
		this.payAccountType = payAccountType;
	}

	public String getAccProvinceName() {
		return accProvinceName;
	}

	public void setAccProvinceName(String accProvinceName) {
		this.accProvinceName = accProvinceName;
	}

	public String getAccCityName() {
		return accCityName;
	}

	public void setAccCityName(String accCityName) {
		this.accCityName = accCityName;
	}

	public String getAccXText() {
		return accXText;
	}

	public void setAccXText(String accXText) {
		this.accXText = accXText;
	}
	
}