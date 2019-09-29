package com.pay.merInfoStatistics.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class TerminalInfo {
	
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
	
	public TerminalInfo(HashMap record) {
		if(record.get("id") == null){
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String)record.get("id")).trim());
		}
		if(record.get("merchantid") == null){
			this.setMerchantid("");
		} else {
			this.setMerchantid(StringUtils.innerToOuter((String)record.get("merchantid")).trim());
		}
		if(record.get("address") == null){
			this.setAddress("");
		} else {
			this.setAddress(StringUtils.innerToOuter((String)record.get("address")).trim());
		}
		if(record.get("detailaddress") == null){
			this.setDetailaddress("");
		} else {
			this.setDetailaddress(StringUtils.innerToOuter((String)record.get("detailaddress")).trim());
		}
		if(record.get("model") == null){
			this.setModel("");
		} else {
			this.setModel(StringUtils.innerToOuter((String)record.get("model")).trim());
		}
		if(record.get("type") == null){
			this.setType("");
		} else {
			this.setType(StringUtils.innerToOuter((String)record.get("type")).trim());
		}
		if(record.get("mobilenumber") == null){
			this.setMobilenumber("");
		} else {
			this.setMobilenumber(StringUtils.innerToOuter((String)record.get("mobilenumber")).trim());
		}
		if(record.get("snnumber") == null){
			this.setSnnumber("");
		} else {
			this.setSnnumber(StringUtils.innerToOuter((String)record.get("snnumber")).trim());
		}
		if(record.get("installdate") == null){
			this.setInstalldate("");
		} else {
			this.setInstalldate(StringUtils.innerToOuter((String)record.get("installdate")).trim());
		}
		if(record.get("disabledate") == null){
			this.setDisabledate("");
		} else {
			this.setDisabledate(StringUtils.innerToOuter((String)record.get("disabledate")).trim());
		}
		if(record.get("updatedate") == null){
			this.setUpdatedate("");
		} else {
			this.setUpdatedate(StringUtils.innerToOuter((String)record.get("updatedate")).trim());
		}
		if(record.get("name") == null){
			this.setName("");
		} else {
			this.setName(StringUtils.innerToOuter((String)record.get("name")).trim());
		}
		if(record.get("phonenumber") == null){
			this.setPhonenumber("");
		} else {
			this.setPhonenumber(StringUtils.innerToOuter((String)record.get("phonenumber")).trim());
		}
		if(record.get("deposite") == null){
			this.setDeposite("");
		} else {
			this.setDeposite(StringUtils.innerToOuter((String)record.get("deposite")).trim());
		}
		if(record.get("status") == null){
			this.setStatus("");
		} else {
			this.setStatus(StringUtils.innerToOuter((String)record.get("status")).trim());
		}
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
