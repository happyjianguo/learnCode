package com.pay.terminal.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class TerminalExportBean {
	private String id         ;
	private String termcode   ;
	private String termno     ;
	private String location   ;
	private String term_stat   ;
	private String mrchno;
	private String settle_mrch_acc_id;
	private String x_timezone          ;
	private String add_date          ;
	private String disabled_date;//停用时间
	private String enable_date;	//启用时间	
	public TerminalExportBean() {

	}

	public TerminalExportBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,
				TerminalExportBean.class, this);
		recordMethod.recordset();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}

	public String getTermno() {
		return termno;
	}

	public void setTermno(String termno) {
		this.termno = termno;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTerm_stat() {
		return term_stat;
	}

	public void setTerm_stat(String termStat) {
		term_stat = termStat;
	}

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}

	public String getSettle_mrch_acc_id() {
		return settle_mrch_acc_id;
	}

	public void setSettle_mrch_acc_id(String settleMrchAccId) {
		settle_mrch_acc_id = settleMrchAccId;
	}

	public String getX_timezone() {
		return x_timezone;
	}

	public void setX_timezone(String xTimezone) {
		x_timezone = xTimezone;
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
	
}
