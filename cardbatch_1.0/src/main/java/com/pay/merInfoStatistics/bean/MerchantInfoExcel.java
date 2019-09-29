package com.pay.merInfoStatistics.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

/**
 * 商户信息导出为Excel表的bean
 * @author 龙
 *
 */
public class MerchantInfoExcel {
	
	private String id;  //商户编号
	private String name;  //商户名称
	private String fullname;  //商户公司名称
	private String type_desc;  //商户类型 中文描述
	private String status_desc;  //商户状态 中文描述
	private String province_cn;  //省 中文
	private String city_cn;  //市 中文
	private String area_cn;  //区 中文
	
	public MerchantInfoExcel(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,MerchantInfoExcel.class, this);
		recordMethod.recordset();
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
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
	public String getStatus_desc() {
		return status_desc;
	}
	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
	public String getProvince_cn() {
		return province_cn;
	}
	public void setProvince_cn(String province_cn) {
		this.province_cn = province_cn;
	}
	public String getCity_cn() {
		return city_cn;
	}
	public void setCity_cn(String city_cn) {
		this.city_cn = city_cn;
	}
	public String getArea_cn() {
		return area_cn;
	}
	public void setArea_cn(String area_cn) {
		this.area_cn = area_cn;
	}
	
}
