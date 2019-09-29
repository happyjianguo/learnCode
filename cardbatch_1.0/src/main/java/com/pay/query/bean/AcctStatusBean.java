package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class AcctStatusBean implements Serializable {
	private String statcode;// 状态码
	private String descr;// 状态描述
	private String sysdef;// 是否系统预定义: 0: 用户自定义 1: 系统预定义
	private String priority;// 优先级。高优先级的状态会覆盖低优先级的状态
	private String actioncode;// 针对该状态，交易产生的操作码
	private String rspcode;// 针对该状态，交易产生的响应码
	private String crdstatus;// 当账户处于该状态，卡片需要更新成的状态。
	private String gen_stmt;//
	private String chg_interest;//
	private String suspend;// 当账户处于该状态是否挂起交易。0. 不挂起1. 挂起
	public AcctStatusBean() {

	}
	public AcctStatusBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, AcctStatusBean.class, this);
		recordMethod.recordset();

	}
	public String getStatcode() {
		return statcode;
	}
	public void setStatcode(String statcode) {
		this.statcode = statcode;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getSysdef() {
		return sysdef;
	}
	public void setSysdef(String sysdef) {
		this.sysdef = sysdef;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
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
	public String getCrdstatus() {
		return crdstatus;
	}
	public void setCrdstatus(String crdstatus) {
		this.crdstatus = crdstatus;
	}
	public String getGen_stmt() {
		return gen_stmt;
	}
	public void setGen_stmt(String genStmt) {
		gen_stmt = genStmt;
	}
	public String getChg_interest() {
		return chg_interest;
	}
	public void setChg_interest(String chgInterest) {
		chg_interest = chgInterest;
	}
	public String getSuspend() {
		return suspend;
	}
	public void setSuspend(String suspend) {
		this.suspend = suspend;
	}
}
