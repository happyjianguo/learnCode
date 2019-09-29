package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class LsBean implements Serializable {
	private String sales_type;
	private String id;
	private String tlog_id;
	private String pan;
	private String amttxn;
	private String father_order;
	private String children_order;
	private String verifier;
	private String verifytime;
	private String stxnstat;
	private String sales_point;
	private String area;
	private String batch_stat;
	public LsBean() {

	}
	public LsBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, LsBean.class, this);
		recordMethod.recordset();

	}
	public String getSales_type() {
		return sales_type;
	}
	public void setSales_type(String salesType) {
		sales_type = salesType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTlog_id() {
		return tlog_id;
	}
	public void setTlog_id(String tlogId) {
		tlog_id = tlogId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAmttxn() {
		return amttxn;
	}
	public void setAmttxn(String amttxn) {
		this.amttxn = amttxn;
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
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}
	public String getVerifytime() {
		return verifytime;
	}
	public void setVerifytime(String verifytime) {
		this.verifytime = verifytime;
	}
	public String getStxnstat() {
		return stxnstat;
	}
	public void setStxnstat(String stxnstat) {
		this.stxnstat = stxnstat;
	}
	public String getSales_point() {
		return sales_point;
	}
	public void setSales_point(String salesPoint) {
		sales_point = salesPoint;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBatch_stat() {
		return batch_stat;
	}
	public void setBatch_stat(String batchStat) {
		batch_stat = batchStat;
	}

}
