package com.pay.terminal.struts.form;

import org.apache.struts.action.ActionForm;

public class EnckeyForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private int verno_ctx;// 商户所属机构的机构
	private int keytype;
	private int keystatus;
	private int tstflag;
	private String refcode;
	private int keyseqno;
	private String keyvalue;
	private String prevalue;
	private String checkvalue;
	private String actdate;
	private int acttime;
	private String expiry;
	private String longkeyval;
	private int id;
	public int getVerno_ctx() {
		return verno_ctx;
	}
	public void setVerno_ctx(int verno_ctx) {
		this.verno_ctx = verno_ctx;
	}
	public int getKeytype() {
		return keytype;
	}
	public void setKeytype(int keytype) {
		this.keytype = keytype;
	}
	public int getKeystatus() {
		return keystatus;
	}
	public void setKeystatus(int keystatus) {
		this.keystatus = keystatus;
	}
	public int getTstflag() {
		return tstflag;
	}
	public void setTstflag(int tstflag) {
		this.tstflag = tstflag;
	}
	public String getRefcode() {
		return refcode;
	}
	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}
	public int getKeyseqno() {
		return keyseqno;
	}
	public void setKeyseqno(int keyseqno) {
		this.keyseqno = keyseqno;
	}
	public String getKeyvalue() {
		return keyvalue;
	}
	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}
	public String getPrevalue() {
		return prevalue;
	}
	public void setPrevalue(String prevalue) {
		this.prevalue = prevalue;
	}
	public String getCheckvalue() {
		return checkvalue;
	}
	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}
	public String getActdate() {
		return actdate;
	}
	public void setActdate(String actdate) {
		this.actdate = actdate;
	}
	public int getActtime() {
		return acttime;
	}
	public void setActtime(int acttime) {
		this.acttime = acttime;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getLongkeyval() {
		return longkeyval;
	}
	public void setLongkeyval(String longkeyval) {
		this.longkeyval = longkeyval;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
