package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class AreaBean {
	
	private String aid;
	private String province_city;
	
	public AreaBean(){
		
	}
	
	public AreaBean(HashMap record){
		if(record.get("aid") == null){
			this.setAid("");
		} else {
			this.setAid(StringUtils.innerToOuter((String)record.get("aid")).trim());
		}
		if(record.get("province_city") == null){
			this.setProvince_city("");
		} else {
			this.setProvince_city(StringUtils.innerToOuter((String)record.get("province_city")).trim());
		}
	}
	
	public String getProvince_city() {
		return province_city;
	}
	public void setProvince_city(String province_city) {
		this.province_city = province_city;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}
	
	
}
