package com.yufupos.system.modules.pos.entity;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * ReportShowEntity
 * @author zqk
 * @version 2017-06-09
 */
public class ReportShow extends DataEntity<ReportShow>{
	private static final long serialVersionUID = 1L;
	
	// MERCHANT_BASE
	private String merchantId;		// 商户编号
	private String mcc;		// mcc
	private String merchantCname;		// 商户名称
	private String merchantStat;		// 商户状态  Y正常  N冻结
	
	//merchantX 扩张信息   MERCHANT_X
	private String contractStartDate;		// 合同签约日期
	private String contractEndDate;         // 合同结束日期  
	private String contractRenewalDate;		// 合同续约日期
	private String merchantCompanyName;		// 商户公司名称
	private String renewalType;		// 续约方式
	private String guaranteeLastDate;		// 保函到期日期
	private BigDecimal guaranteeAmt;		// 保函金额
	private String marketContactPerson;		// 市场联系人
	private String marketContactMobile;		// 市场联系人电话
	private String financialContactPerson;		// 财务联系人
	private String financialContactMobile;		// 财务联系人电话
	private BigDecimal merchantDeposit;		// 押金(元)
	private String merchantArea;		// 区域
	private String storeManager;		// 门店负责人
	private String merchantAdvisor;		// 门店负责人
	private Integer typeYf;  //商户类型
	private String merchantType;  //商户类型
	private String createTime;  //创建时间
	private String beginCreateDate;  //开始创建时间
	private String endCreateDate;  //结束创建时间
	
	private String beginContractStartDate;		// 开始 合同签约日期
	private String endContractStartDate;		// 结束 合同签约日期
	private String beginContractRenewalDate;		// 开始 合同续约日期
	private String endContractRenewalDate;		// 结束 合同续约日期

	// EDC_TERMINAL
	private String terminalId;		// 终端号
	private String terminalStat;		// 终端状态
	private String edcType;		// 终端类型
	private String setDate;		// 安装日期
	private String setAddr;		// 安装地点
	private String beginSetDate;		// 安装日期
	private String endSetDate;		// 安装日期
	
	//扩张信息    EDC_TERMINAL_X
	private String upgradeDate;		// 升级日期
	private String upgradeVersion;		// 升级版本号
	private String storeContacts;		// 门店联系人
	private String storePhone;		// 门店电话
	private String terminalPosition;		// 店铺及款台位置
	private String disableDate;         //终端停用日期
	private String usableDate;         //终端启用日期
	private String terminalProvince;		// 省份
	private String terminalCity;		// 城市
	private String terminalZone;		// 区
	
	//POS信息 P_POS
	private String posSn;		// SN码
	private String factoryName;		// 厂商名称
	private String modelId;		// POS型号
	private String posType;		// POS类别
	private String posStatus;		// POS机状态
	private String layOutFlag;
	
	//  TERMPOS_X
    private String timezone;   // 费率
    private String termStat;
    private String consumpCategory;
    //private String disabledDate;   // 停用日期
    //private String enableDate;   // 启用日期
    
    //P_SIM
    private String pSimId;
    private String simCommunication;		// 运营商
	private String phoneNumber;		// 手机号
	private String serialNumber;		// SIM卡串号
	private String simStatus;		// SIM卡状态
	private BigDecimal simDeposit;      //SIM卡押金(元)
	
	private String contractflag; //是否到合同终止日期标记
	private String contractTime; //查询时间(合同终止时间)
	private String aplipayWeChat; //是否支持支付宝/微信
	
    public ReportShow() {
		super();
	}

	public ReportShow(String id){
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
	
	@Length(min=1, max=8, message="终端号长度必须介于 1 和 8 之间")
	@ExcelField(title="终端号", align=2, sort=20)	
	public String getTerminalId() {
		return this.terminalId = terminalId == null ? null : terminalId.trim();
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	@Length(min=1, max=4, message="商户类型长度必须介于 1 和 4 之间")
	public Integer getTypeYf() {
		return typeYf;
	}

	public void setTypeYf(Integer typeYf) {
		this.typeYf = typeYf;
	}
	
	@ExcelField(title="商户类型", align=2, sort=30)
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	@Length(min=1, max=200, message="mcc长度必须介于 1 和 200 之间")
	@ExcelField(title="mcc", align=2, sort=40)	
	public String getMcc() {
		return this.mcc = mcc == null ? null : mcc.trim();
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Length(min=0, max=40, message="商户名称长度必须介于 0 和 40 之间")
	@ExcelField(title="商户名称", align=2, sort=50)	
	public String getMerchantCname() {
		return this.merchantCname = merchantCname == null ? null : merchantCname.trim();
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}
	
	@Length(min=0, max=200, message="商户公司名称长度必须介于 0 和 200 之间")
	@ExcelField(title="商户公司名称", align=2, sort=60)	
	public String getMerchantCompanyName() {
		return this.merchantCompanyName = merchantCompanyName == null ? null : merchantCompanyName.trim();
	}

	public void setMerchantCompanyName(String merchantCompanyName) {
		this.merchantCompanyName = merchantCompanyName;
	}
	
	@Length(min=1, max=50, message="SN码长度必须介于 1 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=70)	
	public String getPosSn() {
		return this.posSn = posSn == null ? null : posSn.trim();
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=64, message="POS型号长度必须介于 0 和 64 之间")
	@ExcelField(title="POS型号", align=2, sort=80)	
	public String getModelId() {
		return this.modelId = modelId == null ? null : modelId.trim();
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Length(min=0, max=10, message="POS类别长度必须介于 0 和 10 之间")
	@ExcelField(title="POS类别", align=2, sort=90,dictType="POS_TYPE")	
	public String getPosType() {
		return this.posType = posType == null ? null : posType.trim();
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}
	
	@Length(min=1, max=10, message="POS机状态长度必须介于 1 和 10 之间")
	@ExcelField(title="POS机状态", align=2, sort=100,dictType="POS_STATUS")	
	public String getPosStatus() {
		return this.posStatus = posStatus == null ? null : posStatus.trim();
	}

	public void setPosStatus(String posStatus) {
		this.posStatus = posStatus;
	}
	
	@Length(min=1, max=16, message="终端类型长度必须介于 1 和 16 之间")
	@ExcelField(title="终端类型", align=2, sort=110)	
	public String getEdcType() {
		return this.edcType = edcType == null ? null : edcType.trim();
	}

	public void setEdcType(String edcType) {
		this.edcType = edcType;
	}
	
	@Length(min=0, max=30, message="店铺及款台位置长度必须介于 0 和 30 之间")
	@ExcelField(title="店铺及款台位置", align=2, sort=120)	
	public String getSetAddr() {
		return this.setAddr = setAddr == null ? null : setAddr.trim();
	}

	public void setSetAddr(String setAddr) {
		this.setAddr = setAddr;
	}

	@Length(min=0, max=200, message="门店联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="门店联系人", align=2, sort=130)	
	public String getStoreContacts() {
		return this.storeContacts = storeContacts == null ? null : storeContacts.trim();
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts;
	}
	
	@Length(min=0, max=16, message="门店联系电话长度必须介于 0 和 16 之间")
	@ExcelField(title="门店联系电话", align=2, sort=140)	
	public String getStorePhone() {
		return this.storePhone = storePhone == null ? null : storePhone.trim();
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	
	@Length(min=0, max=200, message="商户装机地址长度必须介于 0 和 200 之间")
	@ExcelField(title="商户装机地址", align=2, sort=150)	
	public String getTerminalPosition() {
		return this.terminalPosition = terminalPosition == null ? null : terminalPosition.trim();
	}

	public void setTerminalPosition(String terminalPosition) {
		this.terminalPosition = terminalPosition;
	}
	
	@ExcelField(title="押金(元)", align=2, sort=160)	
	public BigDecimal getMerchantDeposit() {
		return merchantDeposit;
	}

	public void setMerchantDeposit(BigDecimal merchantDeposit) {
		this.merchantDeposit = merchantDeposit;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	@ExcelField(title="区域", align=2, sort=170,dictType="MERCHANT_AREA")	
	public String getMerchantArea() {
		return this.merchantArea = merchantArea == null ? null : merchantArea.trim();
	}

	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	
	@Length(min=0, max=64, message="门店负责人长度必须介于 0 和 64 之间")
	@ExcelField(title="门店负责人", align=2, sort=180)	
	public String getStoreManager() {
		return this.storeManager;
	}

	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager == null ? null : storeManager.trim();
	}
	
	@Length(min=0, max=64, message="商户顾问长度必须介于 0 和 64 之间")
	@ExcelField(title="商户顾问", align=2, sort=190)	
	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor == null ? null : merchantAdvisor.trim();
	}

	@ExcelField(title="费率", align=2, sort=210)
    public String getTimezone() {
		return this.timezone = timezone == null ? null : timezone.trim();
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    
    @Length(min=0, max=200, message="市场联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="市场联系人", align=2, sort=220)	
	public String getMarketContactPerson() {
		return this.marketContactPerson = marketContactPerson == null ? null : marketContactPerson.trim();
	}

	public void setMarketContactPerson(String marketContactPerson) {
		this.marketContactPerson = marketContactPerson;
	}
	
	@Length(min=0, max=200, message="财务联系人长度必须介于 0 和 200 之间")
	public String getFinancialContactPerson() {
		return this.financialContactPerson = financialContactPerson == null ? null : financialContactPerson.trim();
	}

	public void setFinancialContactPerson(String financialContactPerson) {
		this.financialContactPerson = financialContactPerson;
	}
    
    @Length(min=0, max=10, message="续约方式长度必须介于 0 和 10 之间")
	@ExcelField(title="续约方式", align=2, sort=230,dictType="RENEWAL_TYPE")	
	public String getRenewalType() {
		return this.renewalType = renewalType == null ? null : renewalType.trim();
	}

	public void setRenewalType(String renewalType) {
		this.renewalType = renewalType;
	}
	
	@Length(min=0, max=16, message="手机号长度必须介于 0 和 16 之间")
	@ExcelField(title="手机号", align=2, sort=240)	
	public String getPhoneNumber() {
		return this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=100, message="SIM卡串号长度必须介于 0 和 100 之间")
	@ExcelField(title="SIM卡串号", align=2, sort=250)	
	public String getSerialNumber() {
		return this.serialNumber = serialNumber == null ? null : serialNumber.trim();
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	@ExcelField(title="SIM卡押金(元)", align=16, sort=260)	
	public BigDecimal getSimDeposit() {
		return simDeposit;
	}

	public void setSimDeposit(BigDecimal simDeposit) {
		this.simDeposit = simDeposit;
	}

	@ExcelField(title="保函金额", align=2, sort=270)	
	public BigDecimal getGuaranteeAmt() {
		return guaranteeAmt;
	}

	public void setGuaranteeAmt(BigDecimal guaranteeAmt) {
		this.guaranteeAmt = guaranteeAmt;
	}
	
	@Length(min=0, max=8, message="保函到期日期长度必须介于 0 和 8 之间")
	@ExcelField(title="保函到期日期", align=2, sort=280)	
	public String getGuaranteeLastDate() {
		return this.guaranteeLastDate = guaranteeLastDate == null ? null : guaranteeLastDate.trim();
	}

	public void setGuaranteeLastDate(String guaranteeLastDate) {
		this.guaranteeLastDate = guaranteeLastDate;
	}
	
    @Length(min=0, max=8, message="合同签约日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同签约日期", align=2, sort=300)	
	public String getContractStartDate() {
		return this.contractStartDate = contractStartDate == null ? null : contractStartDate.trim();
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	
	@Length(min=0, max=8, message="合同终止日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同终止日期", align=2, sort=310)	
	public String getContractEndDate() {
		return this.contractEndDate = contractEndDate == null ? null : contractEndDate.trim();
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	
	@Length(min=0, max=8, message="合同续约日期长度必须介于 0 和 8 之间")
	@ExcelField(title="合同续约日期", align=2, sort=320)
	public String getContractRenewalDate() {
		return this.contractRenewalDate = contractRenewalDate == null ? null : contractRenewalDate.trim();
	}

	public void setContractRenewalDate(String contractRenewalDate) {
		this.contractRenewalDate = contractRenewalDate;
	}
	
	@Length(min=0, max=8, message="终端录入日期长度必须介于 0 和 8 之间")
	@ExcelField(title="终端录入日期", align=2, sort=330)	
	public String getSetDate() {
		return this.setDate = setDate == null ? null : setDate.trim();
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	
	@Length(min=0, max=8, message="升级日期长度必须介于 0 和 8 之间")
	@ExcelField(title="升级日期", align=2, sort=350)	
	public String getUpgradeDate() {
		return this.upgradeDate = upgradeDate == null ? null : upgradeDate.trim();
	}

	public void setUpgradeDate(String upgradeDate) {
		this.upgradeDate = upgradeDate;
	}
    
    @Length(min=0, max=8, message="终端停用日期长度必须介于 0 和 8 之间")
	@ExcelField(title="终端停用日期", align=2, sort=360)
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

	public String getTermStat() {
        return termStat = termStat == null ? null : termStat.trim();
    }

    public void setTermStat(String termStat) {
        this.termStat = termStat;
    }

    public String getConsumpCategory() {
        return consumpCategory = consumpCategory == null ? null : consumpCategory.trim();
    }

    public void setConsumpCategory(String consumpCategory) {
        this.consumpCategory = consumpCategory;
    }
    
	@Length(min=0, max=1, message="商户状态长度必须介于 0 和 1 之间")
	public String getMerchantStat() {
		return this.merchantStat = merchantStat == null ? null : merchantStat.trim();
	}

	public void setMerchantStat(String merchantStat) {
		this.merchantStat = merchantStat;
	}
	
	@Length(min=0, max=14, message="市场联系人电话长度必须介于 0 和 14 之间")
	public String getMarketContactMobile() {
		return marketContactMobile;
	}

	public void setMarketContactMobile(String marketContactMobile) {
		this.marketContactMobile = marketContactMobile;
	}
	
	@Length(min=0, max=14, message="财务联系人电话长度必须介于 0 和 14 之间")
	public String getFinancialContactMobile() {
		return financialContactMobile;
	}

	public void setFinancialContactMobile(String financialContactMobile) {
		this.financialContactMobile = financialContactMobile;
	}
	
	@Length(min=1, max=1, message="终端状态长度必须介于 1 和 1 之间")
	public String getTerminalStat() {
		return terminalStat;
	}

	public void setTerminalStat(String terminalStat) {
		this.terminalStat = terminalStat;
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
	
	@Length(min=0, max=8, message="升级版本号长度必须介于 0 和 8 之间")
	public String getUpgradeVersion() {
		return upgradeVersion;
	}

	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}
	
	//POS信息
	@Length(min=0, max=200, message="厂商名称长度必须介于 0 和 200 之间")
	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	@Length(min=0, max=20, message="运营商长度必须介于 0 和 20 之间")
	public String getSimCommunication() {
		return simCommunication;
	}

	public void setSimCommunication(String simCommunication) {
		this.simCommunication = simCommunication;
	}
	
	@Length(min=0, max=10, message="SIM卡状态长度必须介于 0 和 10 之间")
	public String getSimStatus() {
		return simStatus;
	}

	public void setSimStatus(String simStatus) {
		this.simStatus = simStatus;
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

	public String getLayOutFlag() {
		return layOutFlag;
	}

	public void setLayOutFlag(String layOutFlag) {
		this.layOutFlag = (layOutFlag == null || layOutFlag.trim().length() == 0) ? null : layOutFlag.trim();
	}

	public String getAplipayWeChat() {
		return aplipayWeChat;
	}

	public void setAplipayWeChat(String aplipayWeChat) {
		this.aplipayWeChat = (aplipayWeChat == null || aplipayWeChat.trim().length() == 0) ? null : aplipayWeChat.trim();
	}

	public String getpSimId() {
		return pSimId;
	}

	public void setpSimId(String pSimId) {
		this.pSimId = pSimId;
	}

	public String getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(String beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}

	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}
	
	@ExcelField(title="商户创建日期", align=2, sort=380)
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=20, message="省份长度必须介于 0 和 20 之间")
	@ExcelField(title="省份", align=2, sort=390)	
	public String getTerminalProvince() {
		return terminalProvince;
	}

	public void setTerminalProvince(String terminalProvince) {
		this.terminalProvince = terminalProvince;
	}
	
	@Length(min=0, max=20, message="城市长度必须介于 0 和 20 之间")
	@ExcelField(title="城市", align=2, sort=400)	
	public String getTerminalCity() {
		return terminalCity;
	}

	public void setTerminalCity(String terminalCity) {
		this.terminalCity = terminalCity;
	}
	
	@Length(min=0, max=20, message="区长度必须介于 0 和 20 之间")
	@ExcelField(title="区", align=2, sort=410)	
	public String getTerminalZone() {
		return terminalZone;
	}

	public void setTerminalZone(String terminalZone) {
		this.terminalZone = terminalZone;
	}

	public String getBeginSetDate() {
		return beginSetDate;
	}

	public void setBeginSetDate(String beginSetDate) {
		this.beginSetDate = beginSetDate;
	}

	public String getEndSetDate() {
		return endSetDate;
	}

	public void setEndSetDate(String endSetDate) {
		this.endSetDate = endSetDate;
	}
	
}
