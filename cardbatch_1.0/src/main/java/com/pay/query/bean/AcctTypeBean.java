package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class AcctTypeBean implements Serializable {
	private String inst_id;// ����ID
	private String typecode;// ���ͱ���
	private String classid;// �˻�����: 1: ����˻� 2: �����˻� 3: �����˻�
	private String isocode;// �˻���ISO����
	private String currcode;// �����Ҵ���
	private String currcode2;// ���û��Ҵ���
	private String srvid;//
	private String fmtid;//
	private String descr;// ����
	private String bankacccode;//
	private String now;//
	private String acclen;// �˺ŵĳ���
	private String allowsvc;// ���˻���������Ľ����б�
	public AcctTypeBean() {

	}
	public AcctTypeBean(HashMap record) {
		// ����java�������Ϊjavabean��ֵ
		RecordMethod recordMethod = new RecordMethod(record, AcctTypeBean.class, this);
		recordMethod.recordset();

	}
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String instId) {
		inst_id = instId;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getIsocode() {
		return isocode;
	}
	public void setIsocode(String isocode) {
		this.isocode = isocode;
	}
	public String getCurrcode() {
		return currcode;
	}
	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	public String getCurrcode2() {
		return currcode2;
	}
	public void setCurrcode2(String currcode2) {
		this.currcode2 = currcode2;
	}
	public String getSrvid() {
		return srvid;
	}
	public void setSrvid(String srvid) {
		this.srvid = srvid;
	}
	public String getFmtid() {
		return fmtid;
	}
	public void setFmtid(String fmtid) {
		this.fmtid = fmtid;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getBankacccode() {
		return bankacccode;
	}
	public void setBankacccode(String bankacccode) {
		this.bankacccode = bankacccode;
	}
	public String getNow() {
		return now;
	}
	public void setNow(String now) {
		this.now = now;
	}
	public String getAcclen() {
		return acclen;
	}
	public void setAcclen(String acclen) {
		this.acclen = acclen;
	}
	public String getAllowsvc() {
		return allowsvc;
	}
	public void setAllowsvc(String allowsvc) {
		this.allowsvc = allowsvc;
	}

}
