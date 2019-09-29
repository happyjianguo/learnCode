package cn.yufu.posp.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantX {
	private String manName;//控股股东或实际控制人姓名
    private BigDecimal idType1;//控股股东或实际控制人证件种类
    private String idNo1;//控股股东或实际控制人证件号码
    private BigDecimal idDeadline1;//控股股东或实际控制人证件有效期截止日
    private String accXName;//商户营业执照的名称
    
    private String merchantId;

    private String contractStartDate;

    private String contractRenewalDate;

    private String merchantCompanyName;

    private String renewalType;

    private String guaranteeLastDate;

    private BigDecimal guaranteeAmt;

    private String marketContactPerson;

    private String marketContactMobile;

    private String financialContactPerson;

    private String financialContactMobile;

    private BigDecimal merchantDeposit;

    private String merchantArea;

    private String storeManager;

    private String merchantAdvisor;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Object remarks;

    private String delFlag;
    private String merchantCname;
    private String addressChn;
    private String rcvName;
    private String rcvBank;
    private String contractEndDate; 	//合同结束日期
    
    private Integer typeYf;//商户类型

    public Integer getTypeYf() {
		return typeYf;
	}

	public void setTypeYf(Integer typeYf) {
		this.typeYf = typeYf;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	@Override
	public String toString() {
		return "MerchantX [manName=" + manName + ", idType1=" + idType1 + ", idNo1=" + idNo1 + ", idDeadline1="
				+ idDeadline1 + ", accXName=" + accXName + ", merchantId=" + merchantId + ", contractStartDate="
				+ contractStartDate + ", contractRenewalDate=" + contractRenewalDate + ", merchantCompanyName="
				+ merchantCompanyName + ", renewalType=" + renewalType + ", guaranteeLastDate=" + guaranteeLastDate
				+ ", guaranteeAmt=" + guaranteeAmt + ", marketContactPerson=" + marketContactPerson
				+ ", marketContactMobile=" + marketContactMobile + ", financialContactPerson=" + financialContactPerson
				+ ", financialContactMobile=" + financialContactMobile + ", merchantDeposit=" + merchantDeposit
				+ ", merchantArea=" + merchantArea + ", storeManager=" + storeManager + ", merchantAdvisor="
				+ merchantAdvisor + ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", remarks=" + remarks + ", delFlag=" + delFlag + ", merchantCname="
				+ merchantCname + ", addressChn=" + addressChn + ", rcvName=" + rcvName + ", rcvBank=" + rcvBank
				+ ", contractEndDate=" + contractEndDate + ", typeYf=" + typeYf + "]";
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public BigDecimal getIdType1() {
		return idType1;
	}

	public void setIdType1(BigDecimal idType1) {
		this.idType1 = idType1;
	}

	public String getIdNo1() {
		return idNo1;
	}

	public void setIdNo1(String idNo1) {
		this.idNo1 = idNo1;
	}

	public BigDecimal getIdDeadline1() {
		return idDeadline1;
	}

	public void setIdDeadline1(BigDecimal idDeadline1) {
		this.idDeadline1 = idDeadline1;
	}

	public String getAccXName() {
		return accXName;
	}

	public void setAccXName(String accXName) {
		this.accXName = accXName;
	}

	public String getMerchantCname() {
		return merchantCname;
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}

	public String getAddressChn() {
		return addressChn;
	}

	public void setAddressChn(String addressChn) {
		this.addressChn = addressChn;
	}

	public String getRcvName() {
		return rcvName;
	}

	public void setRcvName(String rcvName) {
		this.rcvName = rcvName;
	}

	public String getRcvBank() {
		return rcvBank;
	}

	public void setRcvBank(String rcvBank) {
		this.rcvBank = rcvBank;
	}

	public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        this.contractStartDate = contractStartDate == null ? null : contractStartDate.trim();
    }

    public String getContractRenewalDate() {
        return contractRenewalDate;
    }

    public void setContractRenewalDate(String contractRenewalDate) {
        this.contractRenewalDate = contractRenewalDate == null ? null : contractRenewalDate.trim();
    }

    public String getMerchantCompanyName() {
        return merchantCompanyName;
    }

    public void setMerchantCompanyName(String merchantCompanyName) {
        this.merchantCompanyName = merchantCompanyName == null ? null : merchantCompanyName.trim();
    }

    public String getRenewalType() {
        return renewalType;
    }

    public void setRenewalType(String renewalType) {
        this.renewalType = renewalType == null ? null : renewalType.trim();
    }

    public String getGuaranteeLastDate() {
        return guaranteeLastDate;
    }

    public void setGuaranteeLastDate(String guaranteeLastDate) {
        this.guaranteeLastDate = guaranteeLastDate == null ? null : guaranteeLastDate.trim();
    }

    public BigDecimal getGuaranteeAmt() {
        return guaranteeAmt;
    }

    public void setGuaranteeAmt(BigDecimal guaranteeAmt) {
        this.guaranteeAmt = guaranteeAmt;
    }

    public String getMarketContactPerson() {
        return marketContactPerson;
    }

    public void setMarketContactPerson(String marketContactPerson) {
        this.marketContactPerson = marketContactPerson == null ? null : marketContactPerson.trim();
    }

    public String getMarketContactMobile() {
        return marketContactMobile;
    }

    public void setMarketContactMobile(String marketContactMobile) {
        this.marketContactMobile = marketContactMobile == null ? null : marketContactMobile.trim();
    }

    public String getFinancialContactPerson() {
        return financialContactPerson;
    }

    public void setFinancialContactPerson(String financialContactPerson) {
        this.financialContactPerson = financialContactPerson == null ? null : financialContactPerson.trim();
    }

    public String getFinancialContactMobile() {
        return financialContactMobile;
    }

    public void setFinancialContactMobile(String financialContactMobile) {
        this.financialContactMobile = financialContactMobile == null ? null : financialContactMobile.trim();
    }

    public BigDecimal getMerchantDeposit() {
        return merchantDeposit;
    }

    public void setMerchantDeposit(BigDecimal merchantDeposit) {
        this.merchantDeposit = merchantDeposit;
    }

    public String getMerchantArea() {
        return merchantArea;
    }

    public void setMerchantArea(String merchantArea) {
        this.merchantArea = merchantArea == null ? null : merchantArea.trim();
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager == null ? null : storeManager.trim();
    }

    public String getMerchantAdvisor() {
        return merchantAdvisor;
    }

    public void setMerchantAdvisor(String merchantAdvisor) {
        this.merchantAdvisor = merchantAdvisor == null ? null : merchantAdvisor.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}