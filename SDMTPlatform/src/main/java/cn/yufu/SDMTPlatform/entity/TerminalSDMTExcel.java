/**
 *包名:cn.yufu.SDMTPlatform.entity
 *描述:package cn.yufu.SDMTPlatform.entity;
 */
package cn.yufu.SDMTPlatform.entity;

import cn.yufu.utils.excel.annotation.ExcelField;

/**
 * TerminalSDMTExcel.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年6月29日
 * 描述:终端Excel
 */
public class TerminalSDMTExcel {
	private String merchantId;//商户编号
    private String termCode;//终端编号
	private String edcType;//终端设备型号
	private String softVer;//终端软件版本
    private String downloadFlag;//参数下载标志
    private String termAddress;//终端所在位置
    private String province;//省份
    private String cityNo;//城市
    private String zone;//区
    private String actDate;//密钥生效日期
    private String actTime;//密钥生效时间
    private String addDate;//增加日期
    private String termTel;//终端使用的电话号码
    private String state;//裕福原有状态
    private String xTimezone;//费率(单位：%)
    private String consumpCategory;//消费场景
    
	private String upgradeDate;		// 升级日期
	private String storeContacts;		// 门店联系人
	private String storePhone;		// 门店电话
	private String terminalArea;		// 区域
	private String merchantAdvisor;		// 商户顾问
	private String terminalPosition;		// 终端装机地址

	public TerminalSDMTExcel() {
		super();
	}
	
	@ExcelField(title="商户编号", align=2, sort=10)
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	@ExcelField(title="终端编号", align=2, sort=20)
	public String getTermCode() {
		return termCode;
	}
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	@ExcelField(title="终端设备型号", align=2, sort=30)
	public String getEdcType() {
		return edcType;
	}
	public void setEdcType(String edcType) {
		this.edcType = edcType;
	}
	@ExcelField(title="终端软件版本", align=2, sort=40)
	public String getSoftVer() {
		return softVer;
	}
	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
	@ExcelField(title="参数下载标志", align=2, sort=50)
	public String getDownloadFlag() {
		return downloadFlag;
	}
	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}
	@ExcelField(title="终端所在位置", align=2, sort=60)
	public String getTermAddress() {
		return termAddress;
	}
	public void setTermAddress(String termAddress) {
		this.termAddress = termAddress;
	}
	@ExcelField(title="省份", align=2, sort=70)
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@ExcelField(title="城市", align=2, sort=80)
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	@ExcelField(title="区", align=2, sort=90)
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	@ExcelField(title="密钥生效日期", align=2, sort=100)
	public String getActDate() {
		return actDate;
	}
	public void setActDate(String actDate) {
		this.actDate = actDate;
	}
	@ExcelField(title="密钥生效时间", align=2, sort=110)
	public String getActTime() {
		return actTime;
	}
	public void setActTime(String actTime) {
		this.actTime = actTime;
	}
	@ExcelField(title="增加时间", align=2, sort=120)
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	@ExcelField(title="终端使用的电话号码", align=2, sort=130)
	public String getTermTel() {
		return termTel;
	}
	public void setTermTel(String termTel) {
		this.termTel = termTel;
	}
	@ExcelField(title="裕福原有状态", align=2, sort=140)
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@ExcelField(title="费率(单位：%)", align=2, sort=150)
	public String getxTimezone() {
		return xTimezone;
	}
	public void setxTimezone(String xTimezone) {
		this.xTimezone = xTimezone;
	}
	@ExcelField(title="消费场景", align=2, sort=160)
	public String getConsumpCategory() {
		return consumpCategory;
	}
	public void setConsumpCategory(String consumpCategory) {
		this.consumpCategory = consumpCategory;
	}
	@ExcelField(title="升级日期", align=2, sort=170)
	public String getUpgradeDate() {
		return upgradeDate;
	}
	public void setUpgradeDate(String upgradeDate) {
		this.upgradeDate = upgradeDate;
	}
	@ExcelField(title="门店电话", align=2, sort=180)
	public String getStoreContacts() {
		return storeContacts;
	}
	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts;
	}
	@ExcelField(title="门店联系人", align=2, sort=190)
	public String getStorePhone() {
		return storePhone;
	}
	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	@ExcelField(title="区域", align=2, sort=200)
	public String getTerminalArea() {
		return terminalArea;
	}
	public void setTerminalArea(String terminalArea) {
		this.terminalArea = terminalArea;
	}
	@ExcelField(title="商户顾问", align=2, sort=210)
	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}
	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	@ExcelField(title="终端装机地址", align=2, sort=220)
	public String getTerminalPosition() {
		return terminalPosition;
	}

	public void setTerminalPosition(String terminalPosition) {
		this.terminalPosition = terminalPosition;
	}
}
