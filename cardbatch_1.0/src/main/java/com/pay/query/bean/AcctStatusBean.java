package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class AcctStatusBean implements Serializable {
	private String statcode;// ״̬��
	private String descr;// ״̬����
	private String sysdef;// �Ƿ�ϵͳԤ����: 0: �û��Զ��� 1: ϵͳԤ����
	private String priority;// ���ȼ��������ȼ���״̬�Ḳ�ǵ����ȼ���״̬
	private String actioncode;// ��Ը�״̬�����ײ����Ĳ�����
	private String rspcode;// ��Ը�״̬�����ײ�������Ӧ��
	private String crdstatus;// ���˻����ڸ�״̬����Ƭ��Ҫ���³ɵ�״̬��
	private String gen_stmt;//
	private String chg_interest;//
	private String suspend;// ���˻����ڸ�״̬�Ƿ�����ס�0. ������1. ����
	public AcctStatusBean() {

	}
	public AcctStatusBean(HashMap record) {
		// ����java�������Ϊjavabean��ֵ
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
