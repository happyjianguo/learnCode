package com.pay.merInfoStatistics.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

/**
 * �̻���Ϣ����ΪExcel���bean
 * @author ��
 *
 */
public class MerchantInfoExcel {
	
	private String id;  //�̻����
	private String name;  //�̻�����
	private String fullname;  //�̻���˾����
	private String type_desc;  //�̻����� ��������
	private String status_desc;  //�̻�״̬ ��������
	private String province_cn;  //ʡ ����
	private String city_cn;  //�� ����
	private String area_cn;  //�� ����
	
	public MerchantInfoExcel(HashMap record) {
		// ����java�������Ϊjavabean��ֵ
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
