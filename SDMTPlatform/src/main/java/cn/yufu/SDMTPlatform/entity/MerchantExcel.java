/**
 *包名:cn.yufu.SDMTPlatform.entity
 *描述:package cn.yufu.SDMTPlatform.entity;
 */
package cn.yufu.SDMTPlatform.entity;

import cn.yufu.utils.excel.annotation.ExcelField;

/**
 * MerchantExcel.java 版权所有(C) 2017 裕福控股有限公司 创建:gll 时间:2017年6月27日 描述:商户Excel
 */
public class MerchantExcel {

	private String merchantId;//商户编号

	private String mrchtName;// 商户名称
	
	private String merchantEname;//商户名称英文

	private String abbrCname;//简称中文
	
	private String abbrEname;//简称英文
	
	private String mccId;//mcc

	private String address;// 地址

	private String province;//省份

	private String cityNo;//城市

	private String zone;//区

	private String telephone;//联系电话
	
	private String manager;//联系人

	private String accName;// 结算账户开户名

	private String accNo;//结算帐号

	private String isBjAcct;//是否是北京开户行

	private String bis;//结算银行
	
	private String bankName;// 开户银行名称

	private String accNickName;//商户帐号全程
	
	private String shortNickName;//商户帐号简称

	private String bankNo;//开户银行行号

	private String individual;//是否单独结算

	private String lastSettleDate;//上次结算日期
	
	private String typeYf;//商户类型
	
	private String agent;//代办手续经办人姓名
	
	private String idType;//代办手续经办人证件类型
	
	private String idNo;//代办手续经办人证件号码
	
	private String idValidity;//代办手续经办人证件有效期
	
	private String legalRep;//法定代表人姓名
	
	private String lrIdType;//法定代表人证件类型
	
	private String lrIdNo;//法定代表人证件号码
	
	private String lrIdValidity;//法定代表人证件有效期
	
	private String busLicNo;//营业执照号
	
	private String busLicValidity;//营业执照年检时间
	
	private String taxId;//税务登记证编号
	
	private String taxIdValidity;//税务登记证年检时间
	
	private String orgId;//组织机构证编号
	
	private String orgValidity;//组织结构证年检时间
	
	private String enterpriseNo;//企业帐号

	// merchantX 扩展信息
	private String contractStartDate; // 合同签订日期
	private String contractRenewalDate; // 合同续约日期
	private String merchantCompanyName; // 商户公司名称
	private String renewalType; // 续约方式
	private String guaranteeLastDate; // 保函到期日期
	private String guaranteeAmt; // 保函金额
	private String marketContactPerson; // 市场联系人
	private String marketContactMobile; // 市场联系人电话
	private String merchantDeposit; // 押金
	private String merchantArea; // 区域
	private String merchantAdvisor; // 门店负责人
	private String storeManager; // 商户顾问
	private String contractEndDate; // 合同结束日期
	private String fmrchno;//父商户号
	private String accXName;//商户营业执照的名称
	private String manName;//控股股东或实际控制人姓名
    private String idType1;//控股股东或实际控制人证件种类
    private String idNo1;//控股股东或实际控制人证件号码
    private String idDeadline1;//控股股东或实际控制人证件有效期截止日
    
    private String merchant_x_operate;	//企业经营范围
	private String merchant_x_type;	//商户分类:11：自然人，12：单位，默认单位
	private String merchant_x_reg_amt;	//注册资本金--NUMBER(18,2)
	private String merchant_x_code;	//注册资本金币种:人民币-RMB，美元-USD，日元-JPY，欧元-EUR，英镑-GBP，德国马克-DEM，瑞士法郎-CHF，法国法郎-FRF，默认：人民币-RMB														 
	private String merchant_x_acc_type1;//结算账户类型
    
	public MerchantExcel() {
		super();
	}
	

	public void setFmrchno(String fmrchno) {
		this.fmrchno = fmrchno;
	}

	@ExcelField(title="商户编号", align=2, sort=10)	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@ExcelField(title="商户名称", align=2, sort=20)	
	public String getMrchtName() {
		return mrchtName;
	}
	public void setMrchtName(String mrchtName) {
		this.mrchtName = mrchtName;
	}
	@ExcelField(title="商户名称英文", align=2, sort=30)	
	public String getMerchantEname() {
		return merchantEname;
	}
	public void setMerchantEname(String merchantEname) {
		this.merchantEname = merchantEname;
	}
	@ExcelField(title="简称中文", align=2, sort=40)	
	public String getAbbrCname() {
		return abbrCname;
	}
	public void setAbbrCname(String abbrCname) {
		this.abbrCname = abbrCname;
	}
	@ExcelField(title="简称英文", align=2, sort=50)	
	public String getAbbrEname() {
		return abbrEname;
	}
	public void setAbbrEname(String abbrEname) {
		this.abbrEname = abbrEname;
	}
	@ExcelField(title="mcc", align=2, sort=60)	
	public String getMccId() {
		return mccId;
	}
	public void setMccId(String mccId) {
		this.mccId = mccId;
	}
	@ExcelField(title="地址", align=2, sort=70)	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@ExcelField(title="省份", align=2, sort=80)	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@ExcelField(title="城市", align=2, sort=90)	
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	@ExcelField(title="区", align=2, sort=100)	
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	@ExcelField(title="联系电话", align=2, sort=110)	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@ExcelField(title="联系人", align=2, sort=120)	
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	@ExcelField(title="结算账户开户名", align=2, sort=130)	
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	@ExcelField(title="结算帐号", align=2, sort=140)	
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	@ExcelField(title="是否是北京开户行", align=2, sort=150)	
	public String getIsBjAcct() {
		return isBjAcct;
	}
	public void setIsBjAcct(String isBjAcct) {
		this.isBjAcct = isBjAcct;
	}
	@ExcelField(title="结算银行", align=2, sort=160)	
	public String getBis() {
		return bis;
	}
	public void setBis(String bis) {
		this.bis = bis;
	}
	@ExcelField(title="开户银行名称", align=2, sort=170)	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@ExcelField(title="商户帐号全程", align=2, sort=180)	
	public String getAccNickName() {
		return accNickName;
	}
	public void setAccNickName(String accNickName) {
		this.accNickName = accNickName;
	}
	@ExcelField(title="商户帐号简称", align=2, sort=190)	
	public String getShortNickName() {
		return shortNickName;
	}
	public void setShortNickName(String shortNickName) {
		this.shortNickName = shortNickName;
	}
	@ExcelField(title="开户银行行号", align=2, sort=200)	
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	@ExcelField(title="是否单独结算", align=2, sort=210)
	public String getIndividual() {
		return individual;
	}
	public void setIndividual(String individual) {
		this.individual = individual;
	}
	@ExcelField(title="上次结算日期", align=2, sort=220)
	public String getLastSettleDate() {
		return lastSettleDate;
	}
	public void setLastSettleDate(String lastSettleDate) {
		this.lastSettleDate = lastSettleDate;
	}
	@ExcelField(title="商户类型", align=2, sort=230)
	public String getTypeYf() {
		return typeYf;
	}
	public void setTypeYf(String typeYf) {
		this.typeYf = typeYf;
	}
	@ExcelField(title="代办手续经办人姓名", align=2, sort=240)
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	@ExcelField(title="代办手续经办人证件类型", align=2, sort=250)
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	@ExcelField(title="代办手续经办人证件号码", align=2, sort=260)
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	@ExcelField(title="代办手续经办人证件有效期", align=2, sort=270)
	public String getIdValidity() {
		return idValidity;
	}
	public void setIdValidity(String idValidity) {
		this.idValidity = idValidity;
	}
	@ExcelField(title="法定代表人姓名", align=2, sort=280)
	public String getLegalRep() {
		return legalRep;
	}
	public void setLegalRep(String legalRep) {
		this.legalRep = legalRep;
	}
	@ExcelField(title="法定代表人证件类型", align=2, sort=290)
	public String getLrIdType() {
		return lrIdType;
	}
	public void setLrIdType(String lrIdType) {
		this.lrIdType = lrIdType;
	}
	@ExcelField(title="法定代表人证件号码", align=2, sort=300)
	public String getLrIdNo() {
		return lrIdNo;
	}
	public void setLrIdNo(String lrIdNo) {
		this.lrIdNo = lrIdNo;
	}
	@ExcelField(title="法定代表人证件有效期", align=2, sort=310)
	public String getLrIdValidity() {
		return lrIdValidity;
	}
	public void setLrIdValidity(String lrIdValidity) {
		this.lrIdValidity = lrIdValidity;
	}
	@ExcelField(title="营业执照号", align=2, sort=320)
	public String getBusLicNo() {
		return busLicNo;
	}
	public void setBusLicNo(String busLicNo) {
		this.busLicNo = busLicNo;
	}
	@ExcelField(title="营业执照年检时间", align=2, sort=330)
	public String getBusLicValidity() {
		return busLicValidity;
	}
	public void setBusLicValidity(String busLicValidity) {
		this.busLicValidity = busLicValidity;
	}
	@ExcelField(title="税务登记证编号", align=2, sort=340)
	public String getTaxId() {
		return taxId;
	}
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	@ExcelField(title="税务登记证年检时间", align=2, sort=350)
	public String getTaxIdValidity() {
		return taxIdValidity;
	}
	public void setTaxIdValidity(String taxIdValidity) {
		this.taxIdValidity = taxIdValidity;
	}
	@ExcelField(title="组织机构证编号", align=2, sort=360)
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@ExcelField(title="组织结构证年检时间", align=2, sort=370)
	public String getOrgValidity() {
		return orgValidity;
	}
	public void setOrgValidity(String orgValidity) {
		this.orgValidity = orgValidity;
	}
	@ExcelField(title="企业帐号", align=2, sort=380)
	public String getEnterpriseNo() {
		return enterpriseNo;
	}
	public void setEnterpriseNo(String enterpriseNo) {
		this.enterpriseNo = enterpriseNo;
	}
	@ExcelField(title="合同签订日期", align=2, sort=390)
	public String getContractStartDate() {
		return contractStartDate;
	}
	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	@ExcelField(title="合同续约日期", align=2, sort=400)
	public String getContractRenewalDate() {
		return contractRenewalDate;
	}
	public void setContractRenewalDate(String contractRenewalDate) {
		this.contractRenewalDate = contractRenewalDate;
	}
	@ExcelField(title="商户公司名称", align=2, sort=410)
	public String getMerchantCompanyName() {
		return merchantCompanyName;
	}
	public void setMerchantCompanyName(String merchantCompanyName) {
		this.merchantCompanyName = merchantCompanyName;
	}
	@ExcelField(title="续约方式", align=2, sort=420)
	public String getRenewalType() {
		return renewalType;
	}
	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	@ExcelField(title="保函到期日期", align=2, sort=430)
	public String getGuaranteeLastDate() {
		return guaranteeLastDate;
	}
	public void setGuaranteeLastDate(String guaranteeLastDate) {
		this.guaranteeLastDate = guaranteeLastDate;
	}
	@ExcelField(title="保函金额", align=2, sort=440)
	public String getGuaranteeAmt() {
		return guaranteeAmt;
	}
	public void setGuaranteeAmt(String guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}
	@ExcelField(title="市场联系人", align=2, sort=450)
	public String getMarketContactPerson() {
		return marketContactPerson;
	}
	public void setMarketContactPerson(String marketContactPerson) {
		this.marketContactPerson = marketContactPerson;
	}
	@ExcelField(title="市场联系人电话", align=2, sort=460)
	public String getMarketContactMobile() {
		return marketContactMobile;
	}
	public void setMarketContactMobile(String marketContactMobile) {
		this.marketContactMobile = marketContactMobile;
	}
	@ExcelField(title="押金", align=2, sort=470)
	public String getMerchantDeposit() {
		return merchantDeposit;
	}
	public void setMerchantDeposit(String merchantDeposit) {
		this.merchantDeposit = merchantDeposit;
	}
	@ExcelField(title="区域", align=2, sort=480)
	public String getMerchantArea() {
		return merchantArea;
	}
	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	@ExcelField(title="商户顾问", align=2, sort=490)
	public String getStoreManager() {
		return storeManager;
	}
	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}
	@ExcelField(title="门店负责人", align=2, sort=500)
	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}
	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	@ExcelField(title="合同结束日期", align=2, sort=510)
	public String getContractEndDate() {
		return contractEndDate;
	}
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	@ExcelField(title="父商户号", align=2, sort=520)
	public String getFmrchno() {
		return fmrchno;
	}
	@ExcelField(title="营业执照名称", align=2, sort=530)
	public String getAccXName() {
		return accXName;
	}

	public void setAccXName(String accXName) {
		this.accXName = accXName;
	}
	@ExcelField(title="实际控制人姓名", align=2, sort=540)
	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}
	@ExcelField(title="实际控制人证件种类", align=2, sort=550)
	public String getIdType1() {
		return idType1;
	}

	public void setIdType1(String idType1) {
		this.idType1 = idType1;
	}
	@ExcelField(title="实际控制人证件号码", align=2, sort=560)
	public String getIdNo1() {
		return idNo1;
	}

	public void setIdNo1(String idNo1) {
		this.idNo1 = idNo1;
	}
	@ExcelField(title="实际控制人证件有效期", align=2, sort=570)
	public String getIdDeadline1() {
		return idDeadline1;
	}

	public void setIdDeadline1(String idDeadline1) {
		this.idDeadline1 = idDeadline1;
	}
	@ExcelField(title="企业经营范围", align=2, sort=580)
	public String getMerchant_x_operate() {
		return merchant_x_operate;
	}


	public void setMerchant_x_operate(String merchant_x_operate) {
		this.merchant_x_operate = merchant_x_operate;
	}

	@ExcelField(title="商户分类", align=2, sort=590)
	public String getMerchant_x_type() {
		return merchant_x_type;
	}


	public void setMerchant_x_type(String merchant_x_type) {
		this.merchant_x_type = merchant_x_type;
	}

	@ExcelField(title="注册资本金", align=2, sort=600)
	public String getMerchant_x_reg_amt() {
		return merchant_x_reg_amt;
	}


	public void setMerchant_x_reg_amt(String merchant_x_reg_amt) {
		this.merchant_x_reg_amt = merchant_x_reg_amt;
	}

	@ExcelField(title="注册资本金币种", align=2, sort=610)
	public String getMerchant_x_code() {
		return merchant_x_code;
	}


	public void setMerchant_x_code(String merchant_x_code) {
		this.merchant_x_code = merchant_x_code;
	}

	@ExcelField(title="结算账户类型", align=2, sort=620)
	public String getMerchant_x_acc_type1() {
		return merchant_x_acc_type1;
	}


	public void setMerchant_x_acc_type1(String merchant_x_acc_type1) {
		this.merchant_x_acc_type1 = merchant_x_acc_type1;
	}


	@Override
	public String toString() {
		return "MerchantExcel [merchantId=" + merchantId + ", mrchtName=" + mrchtName + ", merchantEname="
				+ merchantEname + ", abbrCname=" + abbrCname + ", abbrEname=" + abbrEname + ", mccId=" + mccId
				+ ", address=" + address + ", province=" + province + ", cityNo=" + cityNo + ", zone=" + zone
				+ ", telephone=" + telephone + ", manager=" + manager + ", accName=" + accName + ", accNo=" + accNo
				+ ", isBjAcct=" + isBjAcct + ", bis=" + bis + ", bankName=" + bankName + ", accNickName=" + accNickName
				+ ", shortNickName=" + shortNickName + ", bankNo=" + bankNo + ", individual=" + individual
				+ ", lastSettleDate=" + lastSettleDate + ", typeYf=" + typeYf + ", agent=" + agent + ", idType="
				+ idType + ", idNo=" + idNo + ", idValidity=" + idValidity + ", legalRep=" + legalRep + ", lrIdType="
				+ lrIdType + ", lrIdNo=" + lrIdNo + ", lrIdValidity=" + lrIdValidity + ", busLicNo=" + busLicNo
				+ ", busLicValidity=" + busLicValidity + ", taxId=" + taxId + ", taxIdValidity=" + taxIdValidity
				+ ", orgId=" + orgId + ", orgValidity=" + orgValidity + ", enterpriseNo=" + enterpriseNo
				+ ", contractStartDate=" + contractStartDate + ", contractRenewalDate=" + contractRenewalDate
				+ ", merchantCompanyName=" + merchantCompanyName + ", renewalType=" + renewalType
				+ ", guaranteeLastDate=" + guaranteeLastDate + ", guaranteeAmt=" + guaranteeAmt
				+ ", marketContactPerson=" + marketContactPerson + ", marketContactMobile=" + marketContactMobile
				+ ", merchantDeposit=" + merchantDeposit + ", merchantArea=" + merchantArea + ", merchantAdvisor="
				+ merchantAdvisor + ", storeManager=" + storeManager + ", contractEndDate=" + contractEndDate
				+ ", fmrchno=" + fmrchno + ", accXName=" + accXName + ", manName=" + manName + ", idType1=" + idType1
				+ ", idNo1=" + idNo1 + ", idDeadline1=" + idDeadline1 + ", merchant_x_operate=" + merchant_x_operate
				+ ", merchant_x_type=" + merchant_x_type + ", merchant_x_reg_amt=" + merchant_x_reg_amt
				+ ", merchant_x_code=" + merchant_x_code + ", merchant_x_acc_type1=" + merchant_x_acc_type1 + "]";
	}
}
