package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class MrchAccExportBean {
	private String acc_name;
	private String accno;
	private String mrchno;
	private String acc_nick_name;
	private String short_nick_name;	
	private String bank_no;
	private String bank_name;
	private String acc_add_date;					
	private String individual;
	private String last_settle_date;	
	private String merchant_x_acc_type1;//结算账户类型
	private String pay_account_type;//	PAY_ACCOUNT_TYPE	VARCHAR2(2) default '00'	1、收款账户属性（00-对公账户，01-对私账户）
	private String acc_x_province;//	ACC_X_PROVINCE      NUMBER(10) ,	2、开户行所在省份
	private String acc_x_city_no;//		ACC_X_CITY_NO       NUMBER(10) ,	3、开户行所在市
	private String acc_x_text;//

	public MrchAccExportBean() {

	}

	public MrchAccExportBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,
				MrchAccExportBean.class, this);
		recordMethod.recordset();
	}

	public String getAcc_name() {
		return acc_name;
	}

	public void setAcc_name(String accName) {
		acc_name = accName;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}

	public String getAcc_nick_name() {
		return acc_nick_name;
	}

	public void setAcc_nick_name(String accNickName) {
		acc_nick_name = accNickName;
	}

	public String getShort_nick_name() {
		return short_nick_name;
	}

	public void setShort_nick_name(String shortNickName) {
		short_nick_name = shortNickName;
	}

	public String getBank_no() {
		return bank_no;
	}

	public void setBank_no(String bankNo) {
		bank_no = bankNo;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bankName) {
		bank_name = bankName;
	}

	public String getAcc_add_date() {
		return acc_add_date;
	}

	public void setAcc_add_date(String accAddDate) {
		acc_add_date = accAddDate;
	}

	public String getIndividual() {
		return individual;
	}

	public void setIndividual(String individual) {
		this.individual = individual;
	}

	public String getLast_settle_date() {
		return last_settle_date;
	}

	public void setLast_settle_date(String lastSettleDate) {
		last_settle_date = lastSettleDate;
	}

	public String getMerchant_x_acc_type1() {
		return merchant_x_acc_type1;
	}

	public void setMerchant_x_acc_type1(String merchant_x_acc_type1) {
		this.merchant_x_acc_type1 = merchant_x_acc_type1;
	}

	public String getPay_account_type() {
		return pay_account_type;
	}

	public void setPay_account_type(String pay_account_type) {
		this.pay_account_type = pay_account_type;
	}

	public String getAcc_x_province() {
		return acc_x_province;
	}

	public void setAcc_x_province(String acc_x_province) {
		this.acc_x_province = acc_x_province;
	}

	public String getAcc_x_city_no() {
		return acc_x_city_no;
	}

	public void setAcc_x_city_no(String acc_x_city_no) {
		this.acc_x_city_no = acc_x_city_no;
	}

	public String getAcc_x_text() {
		return acc_x_text;
	}

	public void setAcc_x_text(String acc_x_text) {
		this.acc_x_text = acc_x_text;
	}

	@Override
	public String toString() {
		return "MrchAccExportBean [acc_name=" + acc_name + ", accno=" + accno + ", mrchno=" + mrchno
				+ ", acc_nick_name=" + acc_nick_name + ", short_nick_name=" + short_nick_name + ", bank_no=" + bank_no
				+ ", bank_name=" + bank_name + ", acc_add_date=" + acc_add_date + ", individual=" + individual
				+ ", last_settle_date=" + last_settle_date + ", merchant_x_acc_type1=" + merchant_x_acc_type1
				+ ", pay_account_type=" + pay_account_type + ", acc_x_province=" + acc_x_province + ", acc_x_city_no="
				+ acc_x_city_no + ", acc_x_text=" + acc_x_text + "]";
	}
}
