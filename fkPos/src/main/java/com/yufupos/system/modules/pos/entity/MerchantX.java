package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 商户补充信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class MerchantX extends DataEntity<MerchantX> {
	
	private static final long serialVersionUID = 1L;
	private String merchantId;		// 商户编号
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
	
	private String beginAllotDate;		// 开始 分配日期
	private String endAllotDate;		// 结束 分配日期
	private String beginContractStartDate;		// 开始 合同签订日期
	private String endContractStartDate;		// 结束 合同签订日期
	private String beginContractRenewalDate;		// 开始 合同续约日期
	private String endContractRenewalDate;		// 结束 合同续约日期
	
	public MerchantX() {
		super();
	}

	public MerchantX(String id){
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
	
	@Length(min=0, max=8, message="合同签订日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同签订日期", align=2, sort=20)	
	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@Length(min=0, max=8, message="合同终止日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同终止日期", align=2, sort=30)	
	public String getContractEndDate() {
		return this.contractEndDate = contractEndDate == null ? null : contractEndDate.trim();
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	@Length(min=0, max=8, message="合同续约日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同续约日期", align=2, sort=40)	
	public String getContractRenewalDate() {
		return contractRenewalDate;
	}

	public void setContractRenewalDate(String contractRenewalDate) {
		this.contractRenewalDate = contractRenewalDate;
	}
	
	@Length(min=0, max=200, message="商户公司名称长度必须介于 0 和 200 之间")
	@ExcelField(title="商户公司名称", align=2, sort=50)	
	public String getMerchantCompanyName() {
		return this.merchantCompanyName = merchantCompanyName == null ? null : merchantCompanyName.trim();
	}

	public void setMerchantCompanyName(String merchantCompanyName) {
		this.merchantCompanyName = merchantCompanyName;
	}
	
	@Length(min=0, max=10, message="续约方式长度必须介于 0 和 10 之间")
	@ExcelField(title="续约方式", align=2, sort=60,dictType="RENEWAL_TYPE")	
	public String getRenewalType() {
		return this.renewalType = renewalType == null ? null : renewalType.trim();
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	
	@Length(min=0, max=8, message="保函到期日期长度必须介于 0 和 8 之间")
	@ExcelField(title="保函到期日期", align=2, sort=70)	
	public String getGuaranteeLastDate() {
		return guaranteeLastDate;
	}

	public void setGuaranteeLastDate(String guaranteeLastDate) {
		this.guaranteeLastDate = guaranteeLastDate;
	}
	
	@ExcelField(title="保函金额", align=2, sort=80)	
	public Double getGuaranteeAmt() {
		return guaranteeAmt;
	}

	public void setGuaranteeAmt(Double guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}
	
	@Length(min=0, max=200, message="市场联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="市场联系人", align=2, sort=90)	
	public String getMarketContactPerson() {
		return this.marketContactPerson = marketContactPerson == null ? null : marketContactPerson.trim();
	}

	public void setMarketContactPerson(String marketContactPerson) {
		this.marketContactPerson = marketContactPerson;
	}
	
	@Length(min=0, max=14, message="市场联系人电话长度必须介于 0 和 14 之间")
	@ExcelField(title="市场联系人电话", align=2, sort=100)	
	public String getMarketContactMobile() {
		return this.marketContactMobile = marketContactMobile == null ? null : marketContactMobile.trim();
	}

	public void setMarketContactMobile(String marketContactMobile) {
		this.marketContactMobile = marketContactMobile;
	}
	
	@Length(min=0, max=200, message="财务联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="财务联系人", align=2, sort=110)	
	public String getFinancialContactPerson() {
		return this.financialContactPerson = financialContactPerson == null ? null : financialContactPerson.trim();
	}

	public void setFinancialContactPerson(String financialContactPerson) {
		this.financialContactPerson = financialContactPerson;
	}
	
	@Length(min=0, max=14, message="财务联系人电话长度必须介于 0 和 14 之间")
	@ExcelField(title="财务联系人电话", align=2, sort=120)	
	public String getFinancialContactMobile() {
		return this.financialContactMobile = financialContactMobile == null ? null : financialContactMobile.trim();
	}

	public void setFinancialContactMobile(String financialContactMobile) {
		this.financialContactMobile = financialContactMobile;
	}
	
	@ExcelField(title="押金", align=2, sort=130)	
	public Double getMerchantDeposit() {
		return merchantDeposit;
	}

	public void setMerchantDeposit(Double merchantDeposit) {
		this.merchantDeposit = merchantDeposit;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	@ExcelField(title="区域", align=2, sort=140,dictType="MERCHANT_AREA")	
	public String getMerchantArea() {
		return this.merchantArea = merchantArea == null ? null : merchantArea.trim();
	}

	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	
	@Length(min=0, max=64, message="门店负责人长度必须介于 0 和 64 之间")
	@ExcelField(title="门店负责人", align=2, sort=150)	
	public String getStoreManager() {
		return storeManager;
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}
	
	@Length(min=0, max=64, message="商户顾问长度必须介于 0 和 64 之间")
	@ExcelField(title="商户顾问", align=2, sort=160)	
	public String getMerchantAdvisor() {
		return this.merchantAdvisor = merchantAdvisor == null ? null : merchantAdvisor.trim();
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=170)
	public Date getCreateDate() {
		return createDate;
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
	
}