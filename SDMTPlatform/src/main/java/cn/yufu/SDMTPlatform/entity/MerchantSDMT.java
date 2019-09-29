package cn.yufu.SDMTPlatform.entity;

import java.math.BigDecimal;

public class MerchantSDMT {
	private String merchantId;//商户编号

	private String mccId;//mcc

	private String mrchtName;// 商户名称

	private String abbrCname;//简称中文

	private String merchantEname;//商户名称英文
	
	private String abbrEname;//简称英文

	private String address;// 地址

	private String province;//省份

	private String cityNo;//城市

	private String zone;//区

	private String manager;//联系人

	private String telephone;//联系电话

	private Long addDate;//增加时间 

	private Long mrchAccXId;//帐号补充表编号

	private String accName;// 结算账户开户名

	private String accNo;//结算帐号

	private String bankName;// 开户银行名称

	private String shortNickName;//商户帐号简称

	private String accNickName;//商户帐号全程

	private String bankNo;//开户银行行号

	private String individual;//是否单独结算

	private Integer lastSettleDate;//上次结算日期

	private Long instId;//商户所属机构

	private String brnCode;//分支结构编号

	private String official;//署名方

	private Integer typeYf;//商户类型

	private String agent;//代办手续经办人姓名

	private Integer idType;//代办手续经办人证件类型

	private String idNo;//代办手续经办人证件号码

	private Integer idValidity;//代办手续经办人证件有效期

	private String legalRep;//法定代表人姓名

	private Integer lrIdType;//法定代表人证件类型

	private String lrIdNo;//法定代表人证件号码

	private Integer lrIdValidity;//法定代表人证件有效期

	private String busLicNo;//营业执照号

	private Integer busLicValidity;//营业执照年检时间

	private String taxId;//税务登记证编号

	private Integer taxIdValidity;//税务登记证年检时间

	private String orgId;//组织机构证编号

	private Integer orgValidity;//组织结构证年检时间

	private String enterpriseNo;//企业帐号

	private String sdFlag;//收单系统同步标识

	private String sdError;//收单系统同步失败原因

	private String xFlag;//X平台同步标识

	private String xError;//X平台同步失败原因

	private String yufuFlag;//裕福系统同步标识

	private String yufuError;//裕福系统同步失败原因

	private String xBakFlag;//X平台备库同步标识

	private String xBakError;//备库失败原因

	private Long seqMrchId;

	private Long seqMrchAccXId;

	private String isBjAcct;//是否是北京开户行

	private String bis;//结算银行

	// merchantX 扩展信息
	private String contractStartDate; // 合同签订日期
	private String contractRenewalDate; // 合同续约日期
	private String merchantCompanyName; // 商户公司名称
	private String renewalType; // 续约方式
	private String guaranteeLastDate; // 保函到期日期
	private BigDecimal guaranteeAmt; // 保函金额
	private String marketContactPerson; // 市场联系人
	private String marketContactMobile; // 市场联系人电话
	private String financialContactPerson; // 财务联系人
	private String financialContactMobile; // 财务联系人电话
	private BigDecimal merchantDeposit; // 押金
	private String merchantArea; // 区域
	private String storeManager; // 门店负责人
	private String merchantAdvisor; // 商户顾问
	private String contractEndDate; // 合同结束日期
	private String fmrchno;//父商户号
	private String isTerminalAddFlag;//是否是通过终端同步添加的老商户
	private String manName;//控股股东或实际控制人姓名
    private BigDecimal idType1;//控股股东或实际控制人证件种类
    private String idNo1;//控股股东或实际控制人证件号码
    private BigDecimal idDeadline1;//控股股东或实际控制人证件有效期截止日
    private String accXName;//商户营业执照的名称
    private String merchant_x_operate;	//企业经营范围
	private String merchant_x_type;	//商户分类:11：自然人，12：单位，默认单位
	private BigDecimal merchant_x_reg_amt;	//注册资本金--NUMBER(18,2)
	private String merchant_x_code;	//注册资本金币种:人民币-RMB，美元-USD，日元-JPY，欧元-EUR，英镑-GBP，德国马克-DEM，瑞士法郎-CHF，法国法郎-FRF，默认：人民币-RMB														 
	private String merchant_x_acc_type1;//结算账户类型
  	
    public String getIsTerminalAddFlag() {
		return isTerminalAddFlag;
	}

	public void setIsTerminalAddFlag(String isTerminalAddFlag) {
		this.isTerminalAddFlag = isTerminalAddFlag;
	}

	public String getFmrchno() {
		return fmrchno;
	}

	public void setFmrchno(String fmrchno) {
		this.fmrchno = fmrchno;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
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

	public BigDecimal getGuaranteeAmt() {
		return guaranteeAmt;
	}

	public void setGuaranteeAmt(BigDecimal guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
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

	//query
    private Long addDateEnd;    
        public Long getAddDateEnd() {
		return addDateEnd;
	}

	public void setAddDateEnd(Long addDateEnd) {
		this.addDateEnd = addDateEnd;
	}
    
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getMccId() {
        return mccId;
    }

    public void setMccId(String mccId) {
        this.mccId = mccId == null ? null : mccId.trim();
    }

    public String getMrchtName() {
        return mrchtName;
    }

    public void setMrchtName(String mrchtName) {
        this.mrchtName = mrchtName == null ? null : mrchtName.trim();
    }

    public String getAbbrCname() {
        return abbrCname;
    }

    public void setAbbrCname(String abbrCname) {
        this.abbrCname = abbrCname == null ? null : abbrCname.trim();
    }

    public String getMerchantEname() {
        return merchantEname;
    }

    public void setMerchantEname(String merchantEname) {
        this.merchantEname = merchantEname == null ? null : merchantEname.trim();
    }

    public String getAbbrEname() {
        return abbrEname;
    }

    public void setAbbrEname(String abbrEname) {
        this.abbrEname = abbrEname == null ? null : abbrEname.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone == null ? null : zone.trim();
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager == null ? null : manager.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Long getAddDate() {
        return addDate;
    }

    public void setAddDate(Long addDate) {
        this.addDate = addDate;
    }

    public Long getMrchAccXId() {
        return mrchAccXId;
    }

    public void setMrchAccXId(Long mrchAccXId) {
        this.mrchAccXId = mrchAccXId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName == null ? null : accName.trim();
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo == null ? null : accNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getShortNickName() {
        return shortNickName;
    }

    public void setShortNickName(String shortNickName) {
        this.shortNickName = shortNickName == null ? null : shortNickName.trim();
    }

    public String getAccNickName() {
        return accNickName;
    }

    public void setAccNickName(String accNickName) {
        this.accNickName = accNickName == null ? null : accNickName.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getIndividual() {
        return individual;
    }

    public void setIndividual(String individual) {
        this.individual = individual == null ? null : individual.trim();
    }

    public Integer getLastSettleDate() {
        return lastSettleDate;
    }

    public void setLastSettleDate(Integer lastSettleDate) {
        this.lastSettleDate = lastSettleDate;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getBrnCode() {
        return brnCode;
    }

    public void setBrnCode(String brnCode) {
        this.brnCode = brnCode == null ? null : brnCode.trim();
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official == null ? null : official.trim();
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

    public Integer getIdValidity() {
        return idValidity;
    }

    public void setIdValidity(Integer idValidity) {
        this.idValidity = idValidity;
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

    public Integer getLrIdValidity() {
        return lrIdValidity;
    }

    public void setLrIdValidity(Integer lrIdValidity) {
        this.lrIdValidity = lrIdValidity;
    }

    public String getBusLicNo() {
        return busLicNo;
    }

    public void setBusLicNo(String busLicNo) {
        this.busLicNo = busLicNo == null ? null : busLicNo.trim();
    }

    public Integer getBusLicValidity() {
        return busLicValidity;
    }

    public void setBusLicValidity(Integer busLicValidity) {
        this.busLicValidity = busLicValidity;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId == null ? null : taxId.trim();
    }

    public Integer getTaxIdValidity() {
        return taxIdValidity;
    }

    public void setTaxIdValidity(Integer taxIdValidity) {
        this.taxIdValidity = taxIdValidity;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getOrgValidity() {
        return orgValidity;
    }

    public void setOrgValidity(Integer orgValidity) {
        this.orgValidity = orgValidity;
    }

    public String getEnterpriseNo() {
        return enterpriseNo;
    }

    public void setEnterpriseNo(String enterpriseNo) {
        this.enterpriseNo = enterpriseNo == null ? null : enterpriseNo.trim();
    }

    public String getSdFlag() {
        return sdFlag;
    }

    public void setSdFlag(String sdFlag) {
        this.sdFlag = sdFlag == null ? null : sdFlag.trim();
    }

    public String getSdError() {
        return sdError;
    }

    public void setSdError(String sdError) {
        this.sdError = sdError == null ? null : sdError.trim();
    }

    public String getxFlag() {
        return xFlag;
    }

    public void setxFlag(String xFlag) {
        this.xFlag = xFlag == null ? null : xFlag.trim();
    }

    public String getxError() {
        return xError;
    }

    public void setxError(String xError) {
        this.xError = xError == null ? null : xError.trim();
    }

    public String getYufuFlag() {
        return yufuFlag;
    }

    public void setYufuFlag(String yufuFlag) {
        this.yufuFlag = yufuFlag == null ? null : yufuFlag.trim();
    }

    public String getYufuError() {
        return yufuError;
    }

    public void setYufuError(String yufuError) {
        this.yufuError = yufuError == null ? null : yufuError.trim();
    }

    public String getxBakFlag() {
        return xBakFlag;
    }

    public void setxBakFlag(String xBakFlag) {
        this.xBakFlag = xBakFlag == null ? null : xBakFlag.trim();
    }

    public String getxBakError() {
        return xBakError;
    }

    public void setxBakError(String xBakError) {
        this.xBakError = xBakError == null ? null : xBakError.trim();
    }

    public Long getSeqMrchId() {
        return seqMrchId;
    }

    public void setSeqMrchId(Long seqMrchId) {
        this.seqMrchId = seqMrchId;
    }

    public Long getSeqMrchAccXId() {
        return seqMrchAccXId;
    }

    public void setSeqMrchAccXId(Long seqMrchAccXId) {
        this.seqMrchAccXId = seqMrchAccXId;
    }

    public String getIsBjAcct() {
        return isBjAcct;
    }

    public void setIsBjAcct(String isBjAcct) {
        this.isBjAcct = isBjAcct == null ? null : isBjAcct.trim();
    }

    public String getBis() {
        return bis;
    }

    public void setBis(String bis) {
        this.bis = bis == null ? null : bis.trim();
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

	public String getMerchant_x_operate() {
		return merchant_x_operate;
	}

	public void setMerchant_x_operate(String merchant_x_operate) {
		this.merchant_x_operate = merchant_x_operate;
	}

	public String getMerchant_x_type() {
		return merchant_x_type;
	}

	public void setMerchant_x_type(String merchant_x_type) {
		this.merchant_x_type = merchant_x_type;
	}

	public BigDecimal getMerchant_x_reg_amt() {
		return merchant_x_reg_amt;
	}

	public void setMerchant_x_reg_amt(BigDecimal merchant_x_reg_amt) {
		this.merchant_x_reg_amt = merchant_x_reg_amt;
	}

	public String getMerchant_x_code() {
		return merchant_x_code;
	}

	public void setMerchant_x_code(String merchant_x_code) {
		this.merchant_x_code = merchant_x_code;
	}

	public String getMerchant_x_acc_type1() {
		return merchant_x_acc_type1;
	}

	public void setMerchant_x_acc_type1(String merchant_x_acc_type1) {
		this.merchant_x_acc_type1 = merchant_x_acc_type1;
	}
}