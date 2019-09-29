package com.pay.merchant.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class Mrch_acc_xBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String inst_id;
	private String mrchno;
	private String accno;
	private String acc_name;
	private String bank_no;
	private String bank_name;
	private String acc_add_date;					//
	private String acc_nick_name;
	private String short_nick_name;
	private String merchant_id;
	private String individual;
	private String last_settle_date;				//
	private String acc_introd;
	private String account_id;

	private String is_bj_acct;	//是否是北京开户行:0.否；1.是
	private String bis;			//结算银行 (bank for international settlements)
	private String merchant_x_acc_type1;//结算账户类型

	private String pay_account_type;//	PAY_ACCOUNT_TYPE	VARCHAR2(2) default '00'	1、收款账户属性（00-对公账户，01-对私账户）
	private String acc_x_province;//	ACC_X_PROVINCE      NUMBER(10) ,	2、开户行所在省份
	private String acc_x_city_no;//		ACC_X_CITY_NO       NUMBER(10) ,	3、开户行所在市
	private String acc_x_text;//		ACC_X_TEXT			VARCHAR2(1000),	4、附言（福卡款2018.08.21店名）再形成一个代付表。代付表格式如附件
	
	public Mrch_acc_xBean(HashMap record) throws Exception{
		for (Object key : record.keySet()) {
			//System.out.println("--------key=[" + key + "]" + record.get(key));
			
			Class[] cargs = new Class[1];
			String realArgs = "";
			cargs[0] = realArgs.getClass();
			Method method = this.getClass().getMethod(
					"set"
							+ StringUtils.toUpperCaseFirstOne(((String) key)
									.toLowerCase()), cargs);
			Object arglist[] = new Object[1];
			if (record.get(key) == null) {
				arglist[0] = "";
			} else {
				if (
					((String)key).equalsIgnoreCase("acc_add_date")
					||((String)key).equalsIgnoreCase("last_settle_date")) {
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy H:m:s");
					Date date = null;
					try {
						if (((String) record.get(key)) != null && !((String) record.get(key)).equals("") ) {
							date = format.parse((String) record.get(key));
							SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
							
							arglist[0] = StringUtils.innerToOuter(format1.format(date))
									.trim();
						} else {
							arglist[0] = StringUtils.innerToOuter((String) record.get(key))
									.trim();
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} else {
					arglist[0] = StringUtils.innerToOuter((String) record.get(key))
						.trim();
				}
			}
			method.invoke(this, arglist);
		}
	}
	
	@Override
	public String toString() {
		return "Mrch_acc_xBean [id=" + id + ", inst_id=" + inst_id + ", mrchno=" + mrchno + ", accno=" + accno
				+ ", acc_name=" + acc_name + ", bank_no=" + bank_no + ", bank_name=" + bank_name + ", acc_add_date="
				+ acc_add_date + ", acc_nick_name=" + acc_nick_name + ", short_nick_name=" + short_nick_name
				+ ", merchant_id=" + merchant_id + ", individual=" + individual + ", last_settle_date="
				+ last_settle_date + ", acc_introd=" + acc_introd + ", account_id=" + account_id + ", is_bj_acct="
				+ is_bj_acct + ", bis=" + bis + ", merchant_x_acc_type1=" + merchant_x_acc_type1 + ", pay_account_type="
				+ pay_account_type + ", acc_x_province=" + acc_x_province + ", acc_x_city_no=" + acc_x_city_no
				+ ", acc_x_text=" + acc_x_text + "]";
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

	public String getShort_nick_name() {
		return short_nick_name;
	}
	public void setShort_nick_name(String short_nick_name) {
		this.short_nick_name = short_nick_name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public String getBank_no() {
		return bank_no;
	}
	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAcc_add_date() {
		return acc_add_date;
	}
	public void setAcc_add_date(String acc_add_date) {
		this.acc_add_date = acc_add_date;
	}
	public String getAcc_nick_name() {
		return acc_nick_name;
	}
	public void setAcc_nick_name(String acc_nick_name) {
		this.acc_nick_name = acc_nick_name;
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
	public void setLast_settle_date(String last_settle_date) {
		this.last_settle_date = last_settle_date;
	}
	
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getAcc_introd() {
		return acc_introd;
	}
	public void setAcc_introd(String accIntrod) {
		acc_introd = accIntrod;
	}
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String accountId) {
		account_id = accountId;
	}
	public Mrch_acc_xBean() {
		
	}

	public String getIs_bj_acct() {
		return is_bj_acct;
	}

	public void setIs_bj_acct(String isBjAcct) {
		is_bj_acct = isBjAcct;
	}

	public String getBis() {
		return bis;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMerchant_x_acc_type1() {
		return merchant_x_acc_type1;
	}

	public void setMerchant_x_acc_type1(String merchant_x_acc_type1) {
		this.merchant_x_acc_type1 = merchant_x_acc_type1;
	}
}
