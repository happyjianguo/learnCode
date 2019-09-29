package com.pay.batch.tlog.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class SalestlogBean {
	
	private String id            ;
	private String descr         ;
	private String pan           ;
	private String amttxn        ;	//	清算金额
	private String stxnstat      ;
	private String father_order  ;
	private String children_order;
	private String org_f_order   ;
	private String org_c_order   ;
	private String verifier      ;
	private String verifytime    ;
	
	private String txncode   	 ;
	private String sub_txncode   ;
	private String tlog_id		 ;
	private String org_tlog_id   ;
	private String starttime     ;
	private String endtime       ;
	private String acctdata      ;
	private String reserved1     ;
	private String reserved2     ;
	private String reserved3     ;
	
	private String curtxn;		//	交易币种
	private String rateset;		//	交易汇率
	private String currbill;	//	清算币种
	private String crdproduct;	//	卡类型
	private String amttxn_jy;	// 	交易金额
	
	public String getAmttxn_jy() {
		return amttxn_jy;
	}
	public void setAmttxn_jy(String amttxnJy) {
		amttxn_jy = amttxnJy;
	}
	public String getCurtxn() {
		return curtxn;
	}
	public void setCurtxn(String curtxn) {
		this.curtxn = curtxn;
	}
	public String getRateset() {
		return rateset;
	}
	public void setRateset(String rateset) {
		this.rateset = rateset;
	}
	public String getCurrbill() {
		return currbill;
	}
	public void setCurrbill(String currbill) {
		this.currbill = currbill;
	}
	public String getCrdproduct() {
		return crdproduct;
	}
	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}
	public SalestlogBean(){
		
	}
	public SalestlogBean(HashMap record) {
		//利用java反射机制为javabean赋值
		RecordMethod  recordMethod=new RecordMethod(record,SalestlogBean.class,this);
		recordMethod.recordset();
	
	}
	
	public String getAcctdata() {
		return acctdata;
	}
	public void setAcctdata(String acctdata) {
		this.acctdata = acctdata;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
	public void setTlog_id(String tlog_id) {
		this.tlog_id = tlog_id;
	}
	public String getOrg_tlog_id() {
		return org_tlog_id;
	}
	public void setOrg_tlog_id(String org_tlog_id) {
		this.org_tlog_id = org_tlog_id;
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
	public String getStxnstat() {
		return stxnstat;
	}
	public void setStxnstat(String stxnstat) {
		this.stxnstat = stxnstat;
	}
	public String getFather_order() {
		return father_order;
	}
	public void setFather_order(String father_order) {
		this.father_order = father_order;
	}
	public String getChildren_order() {
		return children_order;
	}
	public void setChildren_order(String children_order) {
		this.children_order = children_order;
	}
	public String getOrg_f_order() {
		return org_f_order;
	}
	public void setOrg_f_order(String org_f_order) {
		this.org_f_order = org_f_order;
	}
	public String getOrg_c_order() {
		return org_c_order;
	}
	public void setOrg_c_order(String org_c_order) {
		this.org_c_order = org_c_order;
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
	
	public String getTxncode() {
		return txncode;
	}
	public void setTxncode(String txncode) {
		this.txncode = txncode;
	}
	public String getSub_txncode() {
		return sub_txncode;
	}
	public void setSub_txncode(String sub_txncode) {
		this.sub_txncode = sub_txncode;
	}
	
	
	

}
