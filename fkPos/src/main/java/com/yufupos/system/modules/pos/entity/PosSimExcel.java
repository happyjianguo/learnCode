package com.yufupos.system.modules.pos.entity;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * POS机关联SIM卡entity
 * 
 * @author zqk
 * @version 2017-07-18
 */
public class PosSimExcel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String simCommunication;		// 运营商
	private String phoneNumber;		// 手机号
	private String serialNumber;		// SIM卡串号
	private String posSn; // SN码
	private String outstockDate; // 出库时间
	private String outstockBy; // 出库者

	public PosSimExcel() {
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

	@Length(min = 1, max = 50, message = "SN码长度必须介于 1 和 50 之间")
	@ExcelField(title = "SN码", align = 2, sort = 50)
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}

	@Length(min = 0, max = 8, message = "出库时间长度必须介于 0 和 8 之间")
	@ExcelField(title = "出库时间", align = 2, sort = 60)
	public String getOutstockDate() {
		return outstockDate;
	}

	public void setOutstockDate(String outstockDate) {
		this.outstockDate = outstockDate;
	}

	@Length(min = 0, max = 64, message = "出库者长度必须介于 0 和 64 之间")
	@ExcelField(title = "出库者", align = 2, sort = 70)
	public String getOutstockBy() {
		return outstockBy;
	}

	public void setOutstockBy(String outstockBy) {
		this.outstockBy = outstockBy;
	}

}