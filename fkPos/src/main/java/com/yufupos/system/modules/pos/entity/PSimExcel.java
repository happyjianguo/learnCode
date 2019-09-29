package com.yufupos.system.modules.pos.entity;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * SIM卡信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PSimExcel {
	
	private String simCommunication;		// 运营商
	private String phoneNumber;		// 手机号
	private String serialNumber;		// SIM卡串号
	private String purchaseDate;		// 采购日期
	private String purchaseBy;		// 采购者
	private String instockBatch;		// 入库批次号	
	private String simDeposit;      //押金(元)
	private String remarks;	// 备注
	
	public PSimExcel() {
		super();
	}
	
	@Length(min=0, max=20, message="运营商长度必须介于 0 和 20 之间")
	@ExcelField(title="运营商", align=2, sort=20)	
	public String getSimCommunication() {
		return simCommunication;
	}

	public void setSimCommunication(String simCommunication) {
		this.simCommunication = simCommunication;
	}
	
	@Length(min=0, max=16, message="手机号长度必须介于 0 和 16 之间")
	@ExcelField(title="手机号", align=2, sort=30)	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=100, message="SIM卡串号长度必须介于 0 和 100 之间")
	@ExcelField(title="SIM卡串号", align=2, sort=40)	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
	@Length(min=0, max=8, message="采购日期长度必须介于 0 和 8 之间")
	@ExcelField(title="采购日期", align=2, sort=70)	
	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@Length(min=0, max=64, message="采购者不存在")
	@ExcelField(title="采购者", align=2, sort=80)	
	public String getPurchaseBy() {
		return purchaseBy;
	}

	public void setPurchaseBy(String purchaseBy) {
		this.purchaseBy = purchaseBy;
	}
	
	@Length(min=0, max=20, message="入库批次号长度必须介于 0 和 20 之间")
	@ExcelField(title="入库批次号", align=2, sort=90)	
	public String getInstockBatch() {
		return instockBatch;
	}

	public void setInstockBatch(String instockBatch) {
		this.instockBatch = instockBatch;
	}
		
	
	@Length(min=0, max=255)
	@ExcelField(title="备注", align=2, sort=110)	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@ExcelField(title="押金(元)", align=16, sort=100)	
	public String getSimDeposit() {
		return simDeposit;
	}

	public void setSimDeposit(String simDeposit) {
		this.simDeposit = simDeposit;
	}
}