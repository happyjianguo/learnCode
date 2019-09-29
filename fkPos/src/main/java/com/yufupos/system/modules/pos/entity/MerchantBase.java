package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 商户基本信息Entity
 * @author llg
 * @version 2017-04-19
 */
public class MerchantBase extends DataEntity<MerchantBase> {
	
	private static final long serialVersionUID = 1L;
	private String merchantId;		// 商户编号
	private String mcc;		// 商户类型
	private String merchantCname;		// 商户名称
	private String merchantEname;		// 英文名
	private String abbrCname;		// 中文简称
	private String abbrEname;		// 英文简称
	private String addressChn;		// 地址
	private String addressEng;		// 英文地址
	private String cityCname;		// 城市
	private String cityEname;		// 英文城市名
	private String telephone;		// 电话
	private String postCode;		// 邮编
	private String fax;		// 传真
	private String manager;		// 联系人
	private String settleMerchId;		// 清算商户编号
	private String signBankId;		// 签约行行号
	private String signHostId;		// 签约行主机号
	private String zbank;		// 所属机构
	private String ccyType;		// ccy_type
	private String lockMode;		// lock_mode
	private String signDate;		// 签约日期
	private String merchantStat;		// 商户状态  Y正常  N冻结
	private String settleMode;		// 清算模式
	private String updateOper;		// 更新者
	private String updateDateStr;		//更新日期
	private String updateTime;		// 更新时间
	
	//merchantX 扩张信息
	private String contractStartDate;		// 合同签订日期
	private String contractEndDate;         // 合同结束日期  
	private String contractRenewalDate;		// 合同续约日期
	private String merchantCompanyName;		// 商户公司名称
	private String renewalType;		// 续约方式
	private String guaranteeLastDate;		// 保函到期日期
	private Double guaranteeAmt;		// 保函金额
	private String marketContactPerson;		// 市场联系人
	private String marketContactMobile;		// 市场联系人电话
	private String financialContactPerson;		// 财务联系人
	private String financialContactMobile;		// 财务联系人电话
	private Double merchantDeposit;		// 押金
	private String merchantArea;		// 区域
	private String storeManager;		// 门店负责人
	private String merchantAdvisor;		// 商户顾问
	private String disableDate;         //商户停用日期
	private String usableDate;         //商户启用日期
	private String allotDate;          //分配日期
	private String sharer;             //分配者
	
	private String beginContractStartDate;		// 开始 合同签订日期
	private String endContractStartDate;		// 结束 合同签订日期
	private String beginContractRenewalDate;		// 开始 合同续约日期
	private String endContractRenewalDate;		// 结束 合同续约日期
	
	private String beginAllotDate;		// 开始 分配日期
	private String endAllotDate;		// 结束 分配日期
	private String merchantAreaAllot; //分配时的区域属性
	private String storeManagerAllot; //分配时的门店负责人属性
	private String merchantAdvisorAllot; //分配时的商户顾问属性
	
	private String contractflag; //是否到合同终止日期标记
	private String contractTime; //查询时间
	private String flagMerchantX; //是否是由补充信息页面跳转过来的
	
	public MerchantBase() {
		super();
	}

	public MerchantBase(String id){
		super(id);
	}

	@Length(min=1, max=15, message="商户编号长度必须介于 1 和 15 之间")
	@ExcelField(title="商户编号", align=2, sort=10)	
	public String getMerchantId() {
		return this.merchantId = merchantId == null ? null : merchantId.trim();
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=1, max=4, message="商户类型长度必须介于 1 和 4 之间")
	@ExcelField(title="商户类型", align=2, sort=20)	
	public String getMcc() {
		return this.mcc = mcc == null ? null : mcc.trim();
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Length(min=0, max=40, message="商户名称长度必须介于 0 和 40 之间")
	@ExcelField(title="商户名称", align=2, sort=30)	
	public String getMerchantCname() {
		return this.merchantCname = merchantCname == null ? null : merchantCname.trim();
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}
	
	@Length(min=0, max=20, message="英文名长度必须介于 0 和 20 之间")
	@ExcelField(title="英文名", align=2, sort=40)	
	public String getMerchantEname() {
		return this.merchantEname = merchantEname == null ? null : merchantEname.trim();
	}

	public void setMerchantEname(String merchantEname) {
		this.merchantEname = merchantEname;
	}
	
	@Length(min=0, max=12, message="中文简称长度必须介于 0 和 12 之间")
	@ExcelField(title="中文简称", align=2, sort=50)	
	public String getAbbrCname() {
		return this.abbrCname = abbrCname == null ? null : abbrCname.trim();
	}

	public void setAbbrCname(String abbrCname) {
		this.abbrCname = abbrCname;
	}
	
	@Length(min=0, max=8, message="英文简称长度必须介于 0 和 8 之间")
	@ExcelField(title="英文简称", align=2, sort=60)	
	public String getAbbrEname() {
		return this.abbrEname = abbrEname == null ? null : abbrEname.trim();
	}

	public void setAbbrEname(String abbrEname) {
		this.abbrEname = abbrEname;
	}
	
	@Length(min=0, max=30, message="地址长度必须介于 0 和 30 之间")
	@ExcelField(title="地址", align=2, sort=70)	
	public String getAddressChn() {
		return this.addressChn = addressChn == null ? null : addressChn.trim();
	}

	public void setAddressChn(String addressChn) {
		this.addressChn = addressChn;
	}
	
	@Length(min=0, max=20, message="英文地址长度必须介于 0 和 20 之间")
	@ExcelField(title="英文地址", align=2, sort=80)	
	public String getAddressEng() {
		return this.addressEng = addressEng == null ? null : addressEng.trim();
	}

	public void setAddressEng(String addressEng) {
		this.addressEng = addressEng;
	}
	
	@Length(min=0, max=30, message="城市长度必须介于 0 和 30 之间")
	@ExcelField(title="城市", align=2, sort=90)	
	public String getCityCname() {
		return this.cityCname = cityCname == null ? null : cityCname.trim();
	}

	public void setCityCname(String cityCname) {
		this.cityCname = cityCname;
	}
	
	@Length(min=0, max=20, message="英文城市名长度必须介于 0 和 20 之间")
	@ExcelField(title="英文城市名", align=2, sort=100)	
	public String getCityEname() {
		return this.cityEname = cityEname == null ? null : cityEname.trim();
	}

	public void setCityEname(String cityEname) {
		this.cityEname = cityEname;
	}
	
	@Length(min=0, max=20, message="电话长度必须介于 0 和 20 之间")
	@ExcelField(title="电话", align=2, sort=110)	
	public String getTelephone() {
		return this.telephone = telephone == null ? null : telephone.trim();
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Length(min=0, max=6, message="邮编长度必须介于 0 和 6 之间")
	@ExcelField(title="邮编", align=2, sort=120)	
	public String getPostCode() {
		return this.postCode = postCode == null ? null : postCode.trim();
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@Length(min=0, max=20, message="传真长度必须介于 0 和 20 之间")
	@ExcelField(title="传真", align=2, sort=130)	
	public String getFax() {
		return this.fax = fax == null ? null : fax.trim();
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Length(min=0, max=8, message="联系人长度必须介于 0 和 8 之间")
	@ExcelField(title="联系人", align=2, sort=140)	
	public String getManager() {
		return this.manager = manager == null ? null : manager.trim();
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Length(min=0, max=15, message="清算商户编号长度必须介于 0 和 15 之间")
	@ExcelField(title="清算商户编号", align=2, sort=150)	
	public String getSettleMerchId() {
		return this.settleMerchId = settleMerchId == null ? null : settleMerchId.trim();
	}

	public void setSettleMerchId(String settleMerchId) {
		this.settleMerchId = settleMerchId;
	}
	
	@Length(min=1, max=11, message="签约行行号长度必须介于 1 和 11 之间")
	@ExcelField(title="签约行行号", align=2, sort=160)	
	public String getSignBankId() {
		return this.signBankId = signBankId == null ? null : signBankId.trim();
	}

	public void setSignBankId(String signBankId) {
		this.signBankId = signBankId;
	}
	
	@Length(min=1, max=2, message="签约行主机号长度必须介于 1 和 2 之间")
	@ExcelField(title="签约行主机号", align=2, sort=170)	
	public String getSignHostId() {
		return this.signHostId = signHostId == null ? null : signHostId.trim();
	}

	public void setSignHostId(String signHostId) {
		this.signHostId = signHostId;
	}
	
	@Length(min=0, max=11, message="所属机构长度必须介于 0 和 11 之间")
	@ExcelField(title="所属机构", align=2, sort=180)	
	public String getZbank() {
		return this.zbank = zbank == null ? null : zbank.trim();
	}

	public void setZbank(String zbank) {
		this.zbank = zbank;
	}
	
	@Length(min=0, max=3, message="ccy_type长度必须介于 0 和 3 之间")
	@ExcelField(title="ccy_type", align=2, sort=190)	
	public String getCcyType() {
		return this.ccyType = ccyType == null ? null : ccyType.trim();
	}

	public void setCcyType(String ccyType) {
		this.ccyType = ccyType;
	}
	
	@Length(min=0, max=1, message="lock_mode长度必须介于 0 和 1 之间")
	@ExcelField(title="lock_mode", align=2, sort=200)	
	public String getLockMode() {
		return this.lockMode = lockMode == null ? null : lockMode.trim();
	}

	public void setLockMode(String lockMode) {
		this.lockMode = lockMode;
	}
	
	@Length(min=0, max=8, message="签约日期长度必须介于 0 和 8 之间")
	@ExcelField(title="签约日期", align=2, sort=210)	
	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}
	
	@Length(min=0, max=1, message="商户状态长度必须介于 0 和 1 之间")
	@ExcelField(title="商户状态", align=2, sort=220,dictType="MERCHANT_STAT")	
	public String getMerchantStat() {
		return this.merchantStat = merchantStat == null ? null : merchantStat.trim();
	}

	public void setMerchantStat(String merchantStat) {
		this.merchantStat = merchantStat;
	}
	
	@Length(min=0, max=1, message="清算模式长度必须介于 0 和 1 之间")
	@ExcelField(title="清算模式", align=2, sort=230,dictType="MRCH_SETTLE_MODE")	
	public String getSettleMode() {
		return this.settleMode = settleMode == null ? null : settleMode.trim();
	}

	public void setSettleMode(String settleMode) {
		this.settleMode = settleMode;
	}
	
	@Length(min=0, max=6, message="更新者长度必须介于 0 和 6 之间")
	@ExcelField(title="更新者", align=2, sort=240)	
	public String getUpdateOper() {
		return this.updateOper = updateOper == null ? null : updateOper.trim();
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}
	
	@Length(min=0, max=6, message="更新时间长度必须介于 0 和 6 之间")
	@ExcelField(title="更新时间", align=2, sort=260)	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
	//扩张信息
	@Length(min=0, max=8, message="合同签订日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同签订日期", align=2, sort=320)	
	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@Length(min=0, max=8, message="合同终止日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同终止日期", align=2, sort=330)	
	public String getContractEndDate() {
		return this.contractEndDate = contractEndDate == null ? null : contractEndDate.trim();
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	@Length(min=0, max=8, message="合同续约日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同续约日期", align=2, sort=340)	
	public String getContractRenewalDate() {
		return contractRenewalDate;
	}

	public void setContractRenewalDate(String contractRenewalDate) {
		this.contractRenewalDate = contractRenewalDate;
	}
	
	@Length(min=0, max=200, message="商户公司名称长度必须介于 0 和 200 之间")
	@ExcelField(title="商户公司名称", align=2, sort=350)	
	public String getMerchantCompanyName() {
		return this.merchantCompanyName = merchantCompanyName == null ? null : merchantCompanyName.trim();
	}

	public void setMerchantCompanyName(String merchantCompanyName) {
		this.merchantCompanyName = merchantCompanyName;
	}
	
	@Length(min=0, max=10, message="续约方式长度必须介于 0 和 10 之间")
	@ExcelField(title="续约方式", align=2, sort=360,dictType="RENEWAL_TYPE")	
	public String getRenewalType() {
		return this.renewalType = renewalType == null ? null : renewalType.trim();
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	
	@Length(min=0, max=8, message="保函到期日期长度必须介于 0 和 8 之间")
	@ExcelField(title="保函到期日期", align=2, sort=370)	
	public String getGuaranteeLastDate() {
		return guaranteeLastDate;
	}

	public void setGuaranteeLastDate(String guaranteeLastDate) {
		this.guaranteeLastDate = guaranteeLastDate;
	}
	
	@ExcelField(title="保函金额", align=2, sort=380)	
	public Double getGuaranteeAmt() {
		return guaranteeAmt;
	}

	public void setGuaranteeAmt(Double guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}
	
	@Length(min=0, max=200, message="市场联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="市场联系人", align=2, sort=390)	
	public String getMarketContactPerson() {
		return this.marketContactPerson = marketContactPerson == null ? null : marketContactPerson.trim();
	}

	public void setMarketContactPerson(String marketContactPerson) {
		this.marketContactPerson = marketContactPerson;
	}
	
	@Length(min=0, max=14, message="市场联系人电话长度必须介于 0 和 14 之间")
	@ExcelField(title="市场联系人电话", align=2, sort=400)	
	public String getMarketContactMobile() {
		return marketContactMobile;
	}

	public void setMarketContactMobile(String marketContactMobile) {
		this.marketContactMobile = marketContactMobile;
	}
	
	@Length(min=0, max=200, message="财务联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="财务联系人", align=2, sort=410)	
	public String getFinancialContactPerson() {
		return this.financialContactPerson = financialContactPerson == null ? null : financialContactPerson.trim();
	}

	public void setFinancialContactPerson(String financialContactPerson) {
		this.financialContactPerson = financialContactPerson;
	}
	
	@Length(min=0, max=14, message="财务联系人电话长度必须介于 0 和 14 之间")
	@ExcelField(title="财务联系人电话", align=2, sort=420)	
	public String getFinancialContactMobile() {
		return financialContactMobile;
	}

	public void setFinancialContactMobile(String financialContactMobile) {
		this.financialContactMobile = financialContactMobile;
	}
	
	@ExcelField(title="押金", align=2, sort=430)	
	public Double getMerchantDeposit() {
		return merchantDeposit;
	}

	public void setMerchantDeposit(Double merchantDeposit) {
		this.merchantDeposit = merchantDeposit;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	@ExcelField(title="区域", align=2, sort=440,dictType="MERCHANT_AREA")	
	public String getMerchantArea() {
		return this.merchantArea = merchantArea == null ? null : merchantArea.trim();
	}

	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	
	@Length(min=0, max=64, message="门店负责人长度必须介于 0 和 64 之间")
	@ExcelField(title="门店负责人", align=2, sort=450)	
	public String getStoreManager() {
		return this.storeManager = storeManager == null ? null : storeManager.trim();
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}
	
	@Length(min=0, max=64, message="商户顾问长度必须介于 0 和 64 之间")
	@ExcelField(title="商户顾问", align=2, sort=460)	
	public String getMerchantAdvisor() {
		return this.merchantAdvisor = merchantAdvisor == null ? null : merchantAdvisor.trim();
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	
	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getUsableDate() {
		return usableDate;
	}

	public void setUsableDate(String usableDate) {
		this.usableDate = usableDate;
	}

	public String getBeginContractStartDate() {
		return beginContractStartDate;
	}

	public void setBeginContractStartDate(String beginContractStartDate) {
		this.beginContractStartDate = beginContractStartDate;
	}
	
	public String getEndContractStartDate() {
		return endContractStartDate;
	}

	public void setEndContractStartDate(String endContractStartDate) {
		this.endContractStartDate = endContractStartDate;
	}
		
	public String getBeginContractRenewalDate() {
		return beginContractRenewalDate;
	}

	public void setBeginContractRenewalDate(String beginContractRenewalDate) {
		this.beginContractRenewalDate = beginContractRenewalDate;
	}
	
	public String getEndContractRenewalDate() {
		return endContractRenewalDate;
	}

	public void setEndContractRenewalDate(String endContractRenewalDate) {
		this.endContractRenewalDate = endContractRenewalDate;
	}

	public String getStoreManagerAllot() {
		return storeManagerAllot;
	}

	public void setStoreManagerAllot(String storeManagerAllot) {
		this.storeManagerAllot = storeManagerAllot;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	public String getMerchantAreaAllot() {
		return this.merchantAreaAllot = merchantAreaAllot == null ? null : merchantAreaAllot.trim();
	}

	public void setMerchantAreaAllot(String merchantAreaAllot) {
		this.merchantAreaAllot = merchantAreaAllot;
	}

	public String getMerchantAdvisorAllot() {
		return this.merchantAdvisorAllot = merchantAdvisorAllot == null ? null : merchantAdvisorAllot.trim();
	}

	public void setMerchantAdvisorAllot(String merchantAdvisorAllot) {
		this.merchantAdvisorAllot = merchantAdvisorAllot;
	}

	public String getContractflag() {
		return contractflag;
	}

	public void setContractflag(String contractflag) {
		this.contractflag = (contractflag == null || contractflag.trim().length() == 0) ? null : contractflag.trim();
	}

	public String getContractTime() {
		return contractTime;
	}

	public void setContractTime(String contractTime) {
		this.contractTime = contractTime == null ? null : contractTime.trim();
	}

	public String getAllotDate() {
		return allotDate;
	}

	public void setAllotDate(String allotDate) {
		this.allotDate = allotDate;
	}

	public String getSharer() {
		return sharer;
	}

	public void setSharer(String sharer) {
		this.sharer = sharer;
	}

	public String getBeginAllotDate() {
		return beginAllotDate;
	}

	public void setBeginAllotDate(String beginAllotDate) {
		this.beginAllotDate = beginAllotDate;
	}

	public String getEndAllotDate() {
		return endAllotDate;
	}

	public void setEndAllotDate(String endAllotDate) {
		this.endAllotDate = endAllotDate;
	}

	public String getUpdateDateStr() {
		return updateDateStr;
	}

	public void setUpdateDateStr(String updateDateStr) {
		this.updateDateStr = updateDateStr;
	}

	public String getFlagMerchantX() {
		return flagMerchantX;
	}

	public void setFlagMerchantX(String flagMerchantX) {
		this.flagMerchantX = flagMerchantX;
	}
	
}