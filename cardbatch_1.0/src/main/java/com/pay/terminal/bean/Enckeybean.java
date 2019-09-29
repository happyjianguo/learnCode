package com.pay.terminal.bean;

import java.io.Serializable;

public class Enckeybean implements Serializable {
	
	private String verno_ctx ;
	private String keytype   ;
	private String keystatus ;
	private String tstflag   ;
	private String refcode   ;
	private String keyseqno  ;
	private String keyvalue  ;
	private String prevalue  ;
	private String checkvalue;
	private String actdate   ;
	private String acttime   ;
	private String expiry    ;
	private String longkeyval;
	private String id        ;
	
	public String getVerno_ctx() {
		return verno_ctx;
	}
	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}
	public String getKeytype() {
		return keytype;
	}
	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}
	public String getKeystatus() {
		return keystatus;
	}
	public void setKeystatus(String keystatus) {
		this.keystatus = keystatus;
	}
	public String getTstflag() {
		return tstflag;
	}
	public void setTstflag(String tstflag) {
		this.tstflag = tstflag;
	}
	public String getRefcode() {
		return refcode;
	}
	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}
	public String getKeyseqno() {
		return keyseqno;
	}
	public void setKeyseqno(String keyseqno) {
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
	public String getActtime() {
		return acttime;
	}
	public void setActtime(String acttime) {
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
