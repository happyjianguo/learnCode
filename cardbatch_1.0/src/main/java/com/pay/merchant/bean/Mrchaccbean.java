package com.pay.merchant.bean;

import java.io.Serializable;

public class Mrchaccbean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String  verno_ctx;// /number
	private String  merchant_id;// /number
	private String currcode;
	private String date_last_stmt;// //date///
	private String closing_bal;// ///float////
	private String current_bal;// ///float
	private String last_post_bal;// ///float
	private String last_post_com;// ///float
	private String last_post_tax;// ///float
	public String getVerno_ctx() {
		return verno_ctx;
	}
	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getCurrcode() {
		return currcode;
	}
	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	public String getDate_last_stmt() {
		return date_last_stmt;
	}
	public void setDate_last_stmt(String date_last_stmt) {
		this.date_last_stmt = date_last_stmt;
	}
	public String getClosing_bal() {
		return closing_bal;
	}
	public void setClosing_bal(String closing_bal) {
		this.closing_bal = closing_bal;
	}
	public String getCurrent_bal() {
		return current_bal;
	}
	public void setCurrent_bal(String current_bal) {
		this.current_bal = current_bal;
	}
	public String getLast_post_bal() {
		return last_post_bal;
	}
	public void setLast_post_bal(String last_post_bal) {
		this.last_post_bal = last_post_bal;
	}
	public String getLast_post_com() {
		return last_post_com;
	}
	public void setLast_post_com(String last_post_com) {
		this.last_post_com = last_post_com;
	}
	public String getLast_post_tax() {
		return last_post_tax;
	}
	public void setLast_post_tax(String last_post_tax) {
		this.last_post_tax = last_post_tax;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
