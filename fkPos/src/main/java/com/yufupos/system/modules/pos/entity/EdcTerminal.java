package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 终端信息Entity
 * @author llg
 * @version 2017-04-19
 */
public class EdcTerminal extends DataEntity<EdcTerminal> {
	
	private static final long serialVersionUID = 1L;
	private String merchantId;		// 商户号
	private String terminalId;		// 终端号
	private String terminalStat;		// 终端状态
	private String edcType;		// 终端类型
	private String edcDoc;		// edc_doc
	private String printerType;		// 打印机类型
	private String pinpadType;		// PIN PAD类型
	private String softVer;		// 终端类型
	private String downloadFlag;		// 参数下载标志
	private Long downloadMode;		// 参数下载模式
	private String setDate;		// 安装日期
	private String setAddr;		// 安装地点
	private String updateOper;		// 更新者
	private String updateDateStr;    //更新日期
	private String updateTime;		// 更新时间
	
	//扩张信息
	private String upgradeDate;		// 升级日期
	private String upgradeVersion;		// 升级版本号
	private String historyVersion;      //历史版本号
	private String storeContacts;		// 门店联系人
	private String storePhone;		// 门店电话
	private String merchantAdvisor;		// 商户顾问
	private String terminalArea;		// 区域
	private String terminalProvince;		// 省份
	private String terminalCity;		// 城市
	private String terminalZone;		// 区
	private String terminalPosition;		// 位置
	private String disableDate;         //终端停用日期
	private String usableDate;         //终端启用日期
	
	//POS信息
	private String posSn;		// SN码
	private String factoryId;		// 厂商ID
	private String factoryName;		// 厂商名称
	private String modelId;		// 型号
	private String posType;		// 设备类型
	private String posStatus;		// 设备状态
	private String posScanFlag;		// 是否支持扫描
	private String layOutFlag;		// 布放类型
	
	private String flagMerchantX; //是否是由补充信息页面跳转过来的
	
	public EdcTerminal() {
		super();
	}

	public EdcTerminal(String id){
		super(id);
	}

	@Length(min=1, max=15, message="商户号长度必须介于 1 和 15 之间")
	@ExcelField(title="商户号", align=2, sort=10)	
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
	
	@Length(min=1, max=1, message="终端状态长度必须介于 1 和 1 之间")
	@ExcelField(title="终端状态", align=2, sort=30,dictType="TERMINAL_STAT")	
	public String getTerminalStat() {
		return this.terminalStat = terminalStat == null ? null : terminalStat.trim();
	}

	public void setTerminalStat(String terminalStat) {
		this.terminalStat = terminalStat;
	}
	
	@Length(min=1, max=16, message="终端类型长度必须介于 1 和 16 之间")
	@ExcelField(title="终端类型", align=2, sort=40)	
	public String getEdcType() {
		return this.edcType = edcType == null ? null : edcType.trim();
	}

	public void setEdcType(String edcType) {
		this.edcType = edcType;
	}
	
	@Length(min=0, max=50, message="edc_doc长度必须介于 0 和 50 之间")
	@ExcelField(title="edc_doc", align=2, sort=50)	
	public String getEdcDoc() {
		return edcDoc;
	}

	public void setEdcDoc(String edcDoc) {
		this.edcDoc = edcDoc;
	}
	
	@Length(min=0, max=8, message="打印机类型长度必须介于 0 和 8 之间")
	@ExcelField(title="打印机类型", align=2, sort=60)	
	public String getPrinterType() {
		return printerType;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}
	
	@Length(min=0, max=8, message="PIN PAD类型长度必须介于 0 和 8 之间")
	@ExcelField(title="PIN PAD类型", align=2, sort=70)	
	public String getPinpadType() {
		return pinpadType;
	}

	public void setPinpadType(String pinpadType) {
		this.pinpadType = pinpadType;
	}
	
	@Length(min=0, max=10, message="终端类型长度必须介于 0 和 10 之间")
	@ExcelField(title="终端类型", align=2, sort=80,dictType="TERM_SOFT_VER")	
	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
	
	@Length(min=0, max=1, message="参数下载标志长度必须介于 0 和 1 之间")
	@ExcelField(title="参数下载标志", align=2, sort=90,dictType="TERM_DOWNLOAD_FLAG")	
	public String getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}
	
	@ExcelField(title="参数下载模式", align=2, sort=100)	
	public Long getDownloadMode() {
		return downloadMode;
	}

	public void setDownloadMode(Long downloadMode) {
		this.downloadMode = downloadMode;
	}
	
	@Length(min=0, max=8, message="安装日期长度必须介于 0 和 8 之间")
	@ExcelField(title="安装日期", align=2, sort=110)	
	public String getSetDate() {
		return this.setDate = setDate == null ? null : setDate.trim();
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}
	
	@Length(min=0, max=30, message="安装地点长度必须介于 0 和 30 之间")
	@ExcelField(title="安装地点", align=2, sort=120)	
	public String getSetAddr() {
		return this.setAddr = setAddr == null ? null : setAddr.trim();
	}

	public void setSetAddr(String setAddr) {
		this.setAddr = setAddr;
	}
	
	@Length(min=0, max=6, message="更新者长度必须介于 0 和 6 之间")
	public String getUpdateOper() {
		return updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}
	
	@Length(min=0, max=6, message="更新时间长度必须介于 0 和 6 之间")
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
	@Length(min=0, max=8, message="升级日期长度必须介于 0 和 8 之间")
	@ExcelField(title="升级日期", align=2, sort=220)	
	public String getUpgradeDate() {
		return this.upgradeDate = upgradeDate == null ? null : upgradeDate.trim();
	}

	public void setUpgradeDate(String upgradeDate) {
		this.upgradeDate = upgradeDate;
	}
	
	@Length(min=0, max=8, message="升级版本号长度必须介于 0 和 8 之间")
	@ExcelField(title="升级版本号", align=2, sort=230)	
	public String getUpgradeVersion() {
		return this.upgradeVersion = upgradeVersion == null ? null : upgradeVersion.trim();
	}

	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}
	
	@Length(min=0, max=200, message="门店联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="门店联系人", align=2, sort=240)	
	public String getStoreContacts() {
		return this.storeContacts = storeContacts == null ? null : storeContacts.trim();
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts;
	}
	
	@Length(min=0, max=16, message="门店电话长度必须介于 0 和 16 之间")
	@ExcelField(title="门店电话", align=2, sort=250)	
	public String getStorePhone() {
		return this.storePhone = storePhone == null ? null : storePhone.trim();
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	
	@Length(min=0, max=64, message="商户顾问长度必须介于 0 和 64 之间")
	@ExcelField(title="商户顾问", align=2, sort=260)	
	public String getMerchantAdvisor() {
		return this.merchantAdvisor = merchantAdvisor == null ? null : merchantAdvisor.trim();
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	@ExcelField(title="区域", align=2, sort=270,dictType="MERCHANT_AREA")	
	public String getTerminalArea() {
		return this.terminalArea = terminalArea == null ? null : terminalArea.trim();
	}

	public void setTerminalArea(String terminalArea) {
		this.terminalArea = terminalArea;
	}
	
	@Length(min=0, max=20, message="省份长度必须介于 0 和 20 之间")
	@ExcelField(title="省份", align=2, sort=280)	
	public String getTerminalProvince() {
		return this.terminalProvince = terminalProvince == null ? null : terminalProvince.trim();
	}

	public void setTerminalProvince(String terminalProvince) {
		this.terminalProvince = terminalProvince;
	}
	
	@Length(min=0, max=20, message="城市长度必须介于 0 和 20 之间")
	@ExcelField(title="城市", align=2, sort=290)	
	public String getTerminalCity() {
		return this.terminalCity = terminalCity == null ? null : terminalCity.trim();
	}

	public void setTerminalCity(String terminalCity) {
		this.terminalCity = terminalCity;
	}
	
	@Length(min=0, max=20, message="区长度必须介于 0 和 20 之间")
	@ExcelField(title="区", align=2, sort=300)	
	public String getTerminalZone() {
		return this.terminalZone = terminalZone == null ? null : terminalZone.trim();
	}

	public void setTerminalZone(String terminalZone) {
		this.terminalZone = terminalZone;
	}
	
	@Length(min=0, max=200, message="终端装机地址长度必须介于 0 和 200 之间")
	@ExcelField(title="终端装机地址", align=2, sort=310)	
	public String getTerminalPosition() {
		return this.terminalPosition = terminalPosition == null ? null : terminalPosition.trim();
	}

	public void setTerminalPosition(String terminalPosition) {
		this.terminalPosition = terminalPosition;
	}
	
	//POS信息
	@Length(min=1, max=50, message="SN码长度必须介于 1 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=410)	
	public String getPosSn() {
		return this.posSn = posSn == null ? null : posSn.trim();
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=64, message="厂商ID长度必须介于 0 和 64 之间")
	@ExcelField(title="厂商ID", align=2, sort=420)	
	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	
	@Length(min=0, max=200, message="厂商名称长度必须介于 0 和 200 之间")
	@ExcelField(title="厂商名称", align=2, sort=430)	
	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	@Length(min=0, max=64, message="型号长度必须介于 0 和 64 之间")
	@ExcelField(title="型号", align=2, sort=440)	
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Length(min=0, max=10, message="设备类型长度必须介于 0 和 10 之间")
	@ExcelField(title="设备类型", align=2, sort=450,dictType="POS_TYPE")	
	public String getPosType() {
		return posType;
	}

	public void setPosType(String posType) {
		this.posType = posType;
	}
	
	@Length(min=1, max=10, message="设备状态长度必须介于 1 和 10 之间")
	@ExcelField(title="设备状态", align=2, sort=460,dictType="POS_STATUS")	
	public String getPosStatus() {
		return posStatus;
	}

	public void setPosStatus(String posStatus) {
		this.posStatus = posStatus;
	}
	
	@Length(min=0, max=1, message="是否支持扫描长度必须介于 0 和 1 之间")
	@ExcelField(title="是否支持扫描", align=2, sort=470,dictType="POS_SCAN_FLAG")	
	public String getPosScanFlag() {
		return posScanFlag;
	}

	public void setPosScanFlag(String posScanFlag) {
		this.posScanFlag = posScanFlag;
	}
	
	@Length(min=0, max=10, message="布放类型长度必须介于 0 和 10 之间")
	@ExcelField(title="布放类型", align=2, sort=480,dictType="LAY_OUT_FLAG")	
	public String getLayOutFlag() {
		return layOutFlag;
	}

	public void setLayOutFlag(String layOutFlag) {
		this.layOutFlag = layOutFlag;
	}
	
	@Length(min=0, max=8, message="升级版本号长度必须介于 0 和 8 之间")
	@ExcelField(title="升级版本号", align=2, sort=30)
	public String getHistoryVersion() {
		return historyVersion;
	}

	public void setHistoryVersion(String historyVersion) {
		this.historyVersion = historyVersion;
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