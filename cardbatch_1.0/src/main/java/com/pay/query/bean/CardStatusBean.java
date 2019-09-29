package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class CardStatusBean implements Serializable{
	private String statcode;
	private String sysdef;
	private String descr;
	private String actioncode;
	private String rspcode;
	private String canceled;
	public CardStatusBean() {

	}
	public CardStatusBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, CardStatusBean.class, this);
		recordMethod.recordset();
	}
	public String getStatcode() {
		return statcode;
	}
	public void setStatcode(String statcode) {
		this.statcode = statcode;
	}
	public String getSysdef() {
		return sysdef;
	}
	public void setSysdef(String sysdef) {
		this.sysdef = sysdef;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getActioncode() {
		return actioncode;
	}
	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}
	public String getRspcode() {
		return rspcode;
	}
	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}
	public String getCanceled() {
		return canceled;
	}
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

}
