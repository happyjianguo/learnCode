package com.yufupos.system.modules.cortex.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.yufupos.system.common.persistence.DataEntity;

public class MerchantXPlatform extends DataEntity<MerchantXPlatform>{
	private static final long serialVersionUID = 1L;

	private Long instId;

    private String mrchno;

    private String mrchtName;

    private String address;

    private Long addDate;

    private Long cityNo;

    private Long province;

    private Long zone;

    private Integer state;

    private Integer typeYf;

    private String agent;

    private Integer idType;

    private String idNo;

    private Date idValidity;

    private String busLicNo;

    private Date busLicValidity;

    private String taxId;

    private Date taxIdValidity;

    private String orgId;

    private Date orgValidity;

    private String telno1;

    private String postcode;

    private String contact3;

    private String accno;

    private Long merchantId;

    private String busLicPic;

    private String taxIdPic;

    private String orgIdPic;

    private String legalRep;

    private Integer lrIdType;

    private String lrIdNo;

    private Date lrIdValidity;

    private String fsCycle;

    private String fsCycleParam;

    private Date lastSettleDate;

    private String isConsumpCategory;

    private String amtConsumpCategory;

    private String isFmrchnoDecide;

    private String fsKpCycle;

    private String fsKpCycleParam;

    private Date lastKpDate;

    private String isBj;

    private String isCardTypeGroup;

    private String isKp;

    private Date disabledDate;

    private Date enableDate;

    private String mergeMoneyFlag;

    private String mrchSnippet;
    
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
    
    private String merchantDeposit;
    
    private String merchantArea;
    
    private String storeManager;
    
    private String merchantAdvisor;
    
    private String merchantOrg;
    
    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getMrchtName() {
        return mrchtName;
    }

    public void setMrchtName(String mrchtName) {
        this.mrchtName = mrchtName == null ? null : mrchtName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getAddDate() {
        return addDate;
    }

    public void setAddDate(Long addDate) {
        this.addDate = addDate;
    }

    public Long getCityNo() {
        return cityNo;
    }

    public void setCityNo(Long cityNo) {
        this.cityNo = cityNo;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getZone() {
        return zone;
    }

    public void setZone(Long zone) {
        this.zone = zone;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getTypeYf() {
        return typeYf;
    }

    public void setTypeYf(Integer typeYf) {
        this.typeYf = typeYf;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public Date getIdValidity() {
        return idValidity;
    }

    public void setIdValidity(Date idValidity) {
        this.idValidity = idValidity;
    }

    public String getBusLicNo() {
        return busLicNo;
    }

    public void setBusLicNo(String busLicNo) {
        this.busLicNo = busLicNo == null ? null : busLicNo.trim();
    }

    public Date getBusLicValidity() {
        return busLicValidity;
    }

    public void setBusLicValidity(Date busLicValidity) {
        this.busLicValidity = busLicValidity;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId == null ? null : taxId.trim();
    }

    public Date getTaxIdValidity() {
        return taxIdValidity;
    }

    public void setTaxIdValidity(Date taxIdValidity) {
        this.taxIdValidity = taxIdValidity;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Date getOrgValidity() {
        return orgValidity;
    }

    public void setOrgValidity(Date orgValidity) {
        this.orgValidity = orgValidity;
    }

    public String getTelno1() {
        return telno1;
    }

    public void setTelno1(String telno1) {
        this.telno1 = telno1 == null ? null : telno1.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getContact3() {
        return contact3;
    }

    public void setContact3(String contact3) {
        this.contact3 = contact3 == null ? null : contact3.trim();
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno == null ? null : accno.trim();
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getBusLicPic() {
        return busLicPic;
    }

    public void setBusLicPic(String busLicPic) {
        this.busLicPic = busLicPic == null ? null : busLicPic.trim();
    }

    public String getTaxIdPic() {
        return taxIdPic;
    }

    public void setTaxIdPic(String taxIdPic) {
        this.taxIdPic = taxIdPic == null ? null : taxIdPic.trim();
    }

    public String getOrgIdPic() {
        return orgIdPic;
    }

    public void setOrgIdPic(String orgIdPic) {
        this.orgIdPic = orgIdPic == null ? null : orgIdPic.trim();
    }

    public String getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep == null ? null : legalRep.trim();
    }

    public Integer getLrIdType() {
        return lrIdType;
    }

    public void setLrIdType(Integer lrIdType) {
        this.lrIdType = lrIdType;
    }

    public String getLrIdNo() {
        return lrIdNo;
    }

    public void setLrIdNo(String lrIdNo) {
        this.lrIdNo = lrIdNo == null ? null : lrIdNo.trim();
    }

    public Date getLrIdValidity() {
        return lrIdValidity;
    }

    public void setLrIdValidity(Date lrIdValidity) {
        this.lrIdValidity = lrIdValidity;
    }

    public String getFsCycle() {
        return fsCycle;
    }

    public void setFsCycle(String fsCycle) {
        this.fsCycle = fsCycle == null ? null : fsCycle.trim();
    }

    public String getFsCycleParam() {
        return fsCycleParam;
    }

    public void setFsCycleParam(String fsCycleParam) {
        this.fsCycleParam = fsCycleParam == null ? null : fsCycleParam.trim();
    }

    public Date getLastSettleDate() {
        return lastSettleDate;
    }

    public void setLastSettleDate(Date lastSettleDate) {
        this.lastSettleDate = lastSettleDate;
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

    public String getIsFmrchnoDecide() {
        return isFmrchnoDecide;
    }

    public void setIsFmrchnoDecide(String isFmrchnoDecide) {
        this.isFmrchnoDecide = isFmrchnoDecide == null ? null : isFmrchnoDecide.trim();
    }

    public String getFsKpCycle() {
        return fsKpCycle;
    }

    public void setFsKpCycle(String fsKpCycle) {
        this.fsKpCycle = fsKpCycle == null ? null : fsKpCycle.trim();
    }

    public String getFsKpCycleParam() {
        return fsKpCycleParam;
    }

    public void setFsKpCycleParam(String fsKpCycleParam) {
        this.fsKpCycleParam = fsKpCycleParam == null ? null : fsKpCycleParam.trim();
    }

    public Date getLastKpDate() {
        return lastKpDate;
    }

    public void setLastKpDate(Date lastKpDate) {
        this.lastKpDate = lastKpDate;
    }

    public String getIsBj() {
        return isBj;
    }

    public void setIsBj(String isBj) {
        this.isBj = isBj == null ? null : isBj.trim();
    }

    public String getIsCardTypeGroup() {
        return isCardTypeGroup;
    }

    public void setIsCardTypeGroup(String isCardTypeGroup) {
        this.isCardTypeGroup = isCardTypeGroup == null ? null : isCardTypeGroup.trim();
    }

    public String getIsKp() {
        return isKp;
    }

    public void setIsKp(String isKp) {
        this.isKp = isKp == null ? null : isKp.trim();
    }

    public Date getDisabledDate() {
        return disabledDate;
    }

    public void setDisabledDate(Date disabledDate) {
        this.disabledDate = disabledDate;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public String getMergeMoneyFlag() {
        return mergeMoneyFlag;
    }

    public void setMergeMoneyFlag(String mergeMoneyFlag) {
        this.mergeMoneyFlag = mergeMoneyFlag == null ? null : mergeMoneyFlag.trim();
    }

    public String getMrchSnippet() {
        return mrchSnippet;
    }

    public void setMrchSnippet(String mrchSnippet) {
        this.mrchSnippet = mrchSnippet == null ? null : mrchSnippet.trim();
    }

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractRenewalDate() {
		return contractRenewalDate;
	}

	public void setContractRenewalDate(String contractRenewalDate) {
		this.contractRenewalDate = contractRenewalDate;
	}

	public String getMerchantCompanyName() {
		return merchantCompanyName;
	}

	public void setMerchantCompanyName(String merchantCompanyName) {
		this.merchantCompanyName = merchantCompanyName;
	}

	public String getRenewalType() {
		return renewalType;
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}

	public String getGuaranteeLastDate() {
		return guaranteeLastDate;
	}

	public void setGuaranteeLastDate(String guaranteeLastDate) {
		this.guaranteeLastDate = guaranteeLastDate;
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
		this.marketContactPerson = marketContactPerson;
	}

	public String getMarketContactMobile() {
		return marketContactMobile;
	}

	public void setMarketContactMobile(String marketContactMobile) {
		this.marketContactMobile = marketContactMobile;
	}

	public String getFinancialContactPerson() {
		return financialContactPerson;
	}

	public void setFinancialContactPerson(String financialContactPerson) {
		this.financialContactPerson = financialContactPerson;
	}

	public String getFinancialContactMobile() {
		return financialContactMobile;
	}

	public void setFinancialContactMobile(String financialContactMobile) {
		this.financialContactMobile = financialContactMobile;
	}

	public String getMerchantDeposit() {
		return merchantDeposit;
	}

	public void setMerchantDeposit(String merchantDeposit) {
		this.merchantDeposit = merchantDeposit;
	}

	public String getMerchantArea() {
		return merchantArea;
	}

	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}

	public String getStoreManager() {
		return storeManager;
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}

	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}

	public String getMerchantOrg() {
		return merchantOrg;
	}

	public void setMerchantOrg(String merchantOrg) {
		this.merchantOrg = merchantOrg;
	}
    
}