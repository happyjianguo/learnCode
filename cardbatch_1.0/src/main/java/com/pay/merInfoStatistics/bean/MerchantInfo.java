package com.pay.merInfoStatistics.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class MerchantInfo {
	
	
	private String id;  //商户编号
	private String name;  //商户名称
	private String fullname;  //商户公司名称
	private String type;  //商户类型
	private String status;  //商户状态
	private String province;  //地区-省
	private String city;  //地区-市
	private String area;  //地区-区
	
	public MerchantInfo(HashMap record) {
		if(record.get("id") == null){
			this.setId("");
		} else {
			this.setId(StringUtils.innerToOuter((String)record.get("id")).trim());
		}
		if(record.get("name") == null){
			this.setName("");
		} else {
			this.setName(StringUtils.innerToOuter((String)record.get("name")).trim());
		}
		if(record.get("fullname") == null){
			this.setFullname("");
		} else {
			this.setFullname(StringUtils.innerToOuter((String)record.get("fullname")).trim());
		}
		if(record.get("type") == null){
			this.setType("");
		} else {
			this.setType(StringUtils.innerToOuter((String)record.get("type")).trim());
		}
		if(record.get("status") == null){
			this.setStatus("");
		} else {
			this.setStatus(StringUtils.innerToOuter((String)record.get("status")).trim());
		}
		if(record.get("province") == null){
			this.setProvince("");
		} else {
			this.setProvince(StringUtils.innerToOuter((String)record.get("province")).trim());
		}
		if(record.get("city") == null){
			this.setCity("");
		} else {
			this.setCity(StringUtils.innerToOuter((String)record.get("city")).trim());
		}
		if(record.get("area") == null){
			this.setArea("");
		} else {
			this.setArea(StringUtils.innerToOuter((String)record.get("area")).trim());
		}
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullName) {
		this.fullname = fullName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	
}
