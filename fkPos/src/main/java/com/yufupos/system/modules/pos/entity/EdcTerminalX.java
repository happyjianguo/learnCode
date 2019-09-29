package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 终端补充信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class EdcTerminalX extends DataEntity<EdcTerminalX> {
	
	private static final long serialVersionUID = 1L;
	private String terminalId;		// 终端号
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
	
	public EdcTerminalX() {
		super();
	}

	public EdcTerminalX(String id){
		super(id);
	}

	@Length(min=1, max=16, message="终端号长度必须介于 1 和 16 之间")
	@ExcelField(title="终端号", align=2, sort=10)	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	@Length(min=0, max=8, message="升级日期长度必须介于 0 和 8 之间")
	@ExcelField(title="升级日期", align=2, sort=20)	
	public String getUpgradeDate() {
		return upgradeDate;
	}

	public void setUpgradeDate(String upgradeDate) {
		this.upgradeDate = upgradeDate;
	}
	
	@Length(min=0, max=8, message="升级版本号长度必须介于 0 和 8 之间")
	@ExcelField(title="升级版本号", align=2, sort=30)	
	public String getUpgradeVersion() {
		return upgradeVersion;
	}

	public void setUpgradeVersion(String upgradeVersion) {
		this.upgradeVersion = upgradeVersion;
	}
	
	@Length(min=0, max=200, message="门店联系人长度必须介于 0 和 200 之间")
	@ExcelField(title="门店联系人", align=2, sort=40)	
	public String getStoreContacts() {
		return storeContacts;
	}

	public void setStoreContacts(String storeContacts) {
		this.storeContacts = storeContacts;
	}
	
	@Length(min=0, max=16, message="门店电话长度必须介于 0 和 16 之间")
	@ExcelField(title="门店电话", align=2, sort=50)	
	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}
	
	@Length(min=0, max=64, message="商户顾问长度必须介于 0 和 64 之间")
	@ExcelField(title="商户顾问", align=2, sort=60)	
	public String getMerchantAdvisor() {
		return merchantAdvisor;
	}

	public void setMerchantAdvisor(String merchantAdvisor) {
		this.merchantAdvisor = merchantAdvisor;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	@ExcelField(title="区域", align=2, sort=70,dictType="MERCHANT_AREA")	
	public String getTerminalArea() {
		return terminalArea;
	}

	public void setTerminalArea(String terminalArea) {
		this.terminalArea = terminalArea;
	}
	
	@Length(min=0, max=20, message="省份长度必须介于 0 和 20 之间")
	@ExcelField(title="省份", align=2, sort=80)	
	public String getTerminalProvince() {
		return terminalProvince;
	}

	public void setTerminalProvince(String terminalProvince) {
		this.terminalProvince = terminalProvince;
	}
	
	@Length(min=0, max=20, message="城市长度必须介于 0 和 20 之间")
	@ExcelField(title="城市", align=2, sort=90)	
	public String getTerminalCity() {
		return terminalCity;
	}

	public void setTerminalCity(String terminalCity) {
		this.terminalCity = terminalCity;
	}
	
	@Length(min=0, max=20, message="区长度必须介于 0 和 20 之间")
	@ExcelField(title="区", align=2, sort=100)	
	public String getTerminalZone() {
		return terminalZone;
	}

	public void setTerminalZone(String terminalZone) {
		this.terminalZone = terminalZone;
	}
	
	@Length(min=0, max=200, message="终端装机地址长度必须介于 0 和 200 之间")
	@ExcelField(title="终端装机地址", align=2, sort=110)	
	public String getTerminalPosition() {
		return terminalPosition;
	}

	public void setTerminalPosition(String terminalPosition) {
		this.terminalPosition = terminalPosition;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
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
	
}