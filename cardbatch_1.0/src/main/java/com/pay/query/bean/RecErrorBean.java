package com.pay.query.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class RecErrorBean {
	private String table_name;
	private String father_order;
	private String children_order;
	private String acct_period;
	private String errdesc;
	private String errcode;

	public RecErrorBean() {

	}
	public RecErrorBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, RecErrorBean.class, this);
		recordMethod.recordset();

	}

	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String tableName) {
		table_name = tableName;
	}
	public String getFather_order() {
		return father_order;
	}
	public void setFather_order(String fatherOrder) {
		father_order = fatherOrder;
	}
	public String getChildren_order() {
		return children_order;
	}
	public void setChildren_order(String childrenOrder) {
		children_order = childrenOrder;
	}
	public String getAcct_period() {
		return acct_period;
	}
	public void setAcct_period(String acctPeriod) {
		acct_period = acctPeriod;
	}
	public String getErrdesc() {
		return errdesc;
	}
	public void setErrdesc(String errdesc) {
		this.errdesc = errdesc;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

}
