package com.pay.merInfoStatistics.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class TerminalInfoExcel {
	
	private String id;  //终端编号
	private String merchantid;  //所属商户编号
	private String address;  //商户装机地址
	private String detailaddress;  //店铺及款台地址
	private String model;  //POS型号
	private String type;  //POS类型
	private String mobilenumber;  //无线POS手机号
	private String snnumber;  //POS机S/N号
	private String installdate;  //安装日期
	private String disabledate;  //停用日期
	private String updatedate;  //升级日期
	private String name;  //联系人
	private String phonenumber;  //门店电话
	private String status;  //POS机状态
	private String deposite;  //POS押金（元）
	
	public TerminalInfoExcel(HashMap record) {		
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,TerminalInfoExcel.class, this);
		recordMethod.recordset();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getSnnumber() {
		return snnumber;
	}
	public void setSnnumber(String snnumber) {
		this.snnumber = snnumber;
	}
	public String getInstalldate() {
		return installdate;
	}
	public void setInstalldate(String installdate) {
		this.installdate = installdate;
	}
	public String getDisabledate() {
		return disabledate;
	}
	public void setDisabledate(String disabledate) {
		this.disabledate = disabledate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeposite() {
		return deposite;
	}
	public void setDeposite(String deposite) {
		this.deposite = deposite;
	}
	
}
