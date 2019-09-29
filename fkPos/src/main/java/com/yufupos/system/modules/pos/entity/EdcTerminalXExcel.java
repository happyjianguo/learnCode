package com.yufupos.system.modules.pos.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.utils.excel.annotation.ExcelField;

public class EdcTerminalXExcel implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String terminalId;		// 终端号
	private String storeContacts;		// 门店联系人
	private String storePhone;		// 门店电话
	private String terminalArea;		// 区域
	private String merchantAdvisor;		// 商户顾问
	private String terminalProvince;		// 省份
	private String terminalCity;		// 城市
	private String terminalZone;		// 区
	private String terminalPosition;		// 终端装机地址
	
	public EdcTerminalXExcel() {
		super();
	}
	
	@Length(min=0, max=8, message="终端号长度必须介于 0 和 8 之间")
	@ExcelField(title="终端号", align=2, sort=20)
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId == null ? null : terminalId.trim();
	}
	
	@Length(min=0, max=200, message="门店联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="门店联系人", align=2, sort=30)
	public String getStoreContacts() {
		return storeContacts;
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts == null ? null : storeContacts.trim();
	}
	
	@Length(min=0, max=16, message="门店电话长度必须介于 0 和 16 之间")
	@ExcelField(title="门店电话", align=2, sort=40)
	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone == null ? null : storePhone.trim();
	}
	
	@ExcelField(title="区域", align=2, sort=50)	
	public String getTerminalArea() {
		return terminalArea;
	}

	public void setTerminalArea(String terminalArea) {
		this.terminalArea = terminalArea;
	}
	
	@ExcelField(title="商户顾问", align=2, sort=60)	
	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	
	@ExcelField(title="省份", align=2, sort=70)
	public String getTerminalProvince() {
		return terminalProvince;
	}

	public void setTerminalProvince(String terminalProvince) {
		this.terminalProvince = terminalProvince;
	}
	
	@ExcelField(title="城市", align=2, sort=80)
	public String getTerminalCity() {
		return terminalCity;
	}

	public void setTerminalCity(String terminalCity) {
		this.terminalCity = terminalCity;
	}
	
	@ExcelField(title="区", align=2, sort=90)
	public String getTerminalZone() {
		return terminalZone;
	}

	public void setTerminalZone(String terminalZone) {
		this.terminalZone = terminalZone;
	}
	
	@ExcelField(title="终端装机地址", align=2, sort=100)
	public String getTerminalPosition() {
		return terminalPosition;
	}

	public void setTerminalPosition(String terminalPosition) {
		this.terminalPosition = terminalPosition == null ? null : terminalPosition.trim();
	}
	
}
