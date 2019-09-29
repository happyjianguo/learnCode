package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class MerchantExportBean {
	private String mrchno; // 商户号
	private String name; // 商户名称
	private String mrch_snippet; // 商户摘要
	private String type_yf; //商户类型
	private String mrchstat; // 商户的状态：

	private String acptbus;//IOS对应MCC码
	private String contact3; // 注册地址联系人
	private String telno1; // 邮寄地址联系电话
	private String add_date; // 格式为YYYYMMDDHHMMSS
	private String disabled_date;//停用时间
	private String enable_date;	//启用时间
	//"商户号","商户名称","商户摘要","商户类型","商户状态","IOS对应MCC码","联系人","电话","增加时间","停用时间","启用时间"
	public MerchantExportBean() {

	}

	public MerchantExportBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,
				MerchantExportBean.class, this);
		recordMethod.recordset();
	}

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType_yf() {
		return type_yf;
	}

	public void setType_yf(String typeYf) {
		type_yf = typeYf;
	}

	public String getMrchstat() {
		return mrchstat;
	}

	public void setMrchstat(String mrchstat) {
		this.mrchstat = mrchstat;
	}

	public String getAcptbus() {
		return acptbus;
	}

	public void setAcptbus(String acptbus) {
		this.acptbus = acptbus;
	}

	public String getContact3() {
		return contact3;
	}

	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}

	public String getTelno1() {
		return telno1;
	}

	public void setTelno1(String telno1) {
		this.telno1 = telno1;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String addDate) {
		add_date = addDate;
	}

	public String getDisabled_date() {
		return disabled_date;
	}

	public void setDisabled_date(String disabledDate) {
		disabled_date = disabledDate;
	}

	public String getEnable_date() {
		return enable_date;
	}

	public void setEnable_date(String enableDate) {
		enable_date = enableDate;
	}

	public String getMrch_snippet() {
		return mrch_snippet;
	}

	public void setMrch_snippet(String mrch_snippet) {
		this.mrch_snippet = mrch_snippet;
	}

	@Override
	public String toString() {
		return "MerchantExportBean [mrchno=" + mrchno + ", name=" + name + ", type_yf=" + type_yf + ", mrchstat="
				+ mrchstat + ", mrch_snippet=" + mrch_snippet + ", acptbus=" + acptbus + ", contact3=" + contact3
				+ ", telno1=" + telno1 + ", add_date=" + add_date + ", disabled_date=" + disabled_date
				+ ", enable_date=" + enable_date + "]";
	}
}
