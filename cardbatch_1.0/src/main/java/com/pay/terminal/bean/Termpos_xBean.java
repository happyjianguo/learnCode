package com.pay.terminal.bean;

import java.io.Serializable;

public class Termpos_xBean  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id                ;
	private String pos_tel           ;
	private String batch_no          ;
	private String add_date          ;
	private String location          ;
	private String state             ;
	private String city_no           ;
	private String province          ;
	private String zone              ;
	private String settle_mrch_acc_id;
	private String termcode          ;
	private String x_timezone          ;
	private String inst_id           ;
	private String mrchno            ;
	private String term_stat	     ;//终端状态
	private String consump_category  ;//消费场景
	private String disabled_date;//停用时间
	private String enable_date;	//启用时间
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPos_tel() {
		return pos_tel;
	}

	public void setPos_tel(String pos_tel) {
		this.pos_tel = pos_tel;
	}

	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity_no() {
		return city_no;
	}

	public void setCity_no(String city_no) {
		this.city_no = city_no;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getSettle_mrch_acc_id() {
		return settle_mrch_acc_id;
	}

	public void setSettle_mrch_acc_id(String settle_mrch_acc_id) {
		this.settle_mrch_acc_id = settle_mrch_acc_id;
	}

	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}

	public String getX_timezone() {
		return x_timezone;
	}

	public void setX_timezone(String x_timezone) {
		this.x_timezone = x_timezone;
	}

	public String getInst_id() {
		return inst_id;
	}

	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}
	public String getTerm_stat() {
		return term_stat;
	}
	public void setTerm_stat(String termStat) {
		term_stat = termStat;
	}

	public String getConsump_category() {
		return consump_category;
	}

	public void setConsump_category(String consumpCategory) {
		consump_category = consumpCategory;
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
