package com.pay.terminal.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class Termposbean implements Serializable {
private static final long serialVersionUID = 1L;
	
	private String verno_ctx  ;
	private String id         ;
	private String typeid     ;
	private String termcode   ;
	private String testflag   ;
	private String statusid   ;
	private String currcode   ;
	private String termno     ;
	private String location   ;
	private String conaccno   ;
	private String concur     ;
	private String poschic    ;
	private String poschac    ;
	private String poscrc     ;
	private String posoe      ;
	private String poscdoc    ;
	private String postoc     ;
	private String pospcc     ;
	private String timezone   ;
	private String cat_params ;
	private String merchant_id;
	private String termtype   ;
	
	public Termposbean(){
		
	}
	
	public Termposbean (HashMap record) throws Exception{
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
				arglist[0] = StringUtils.innerToOuter((String) record.get(key))
						.trim();
			}
			method.invoke(this, arglist);
		}
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}

	public String getTestflag() {
		return testflag;
	}

	public void setTestflag(String testflag) {
		this.testflag = testflag;
	}

	public String getStatusid() {
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public String getCurrcode() {
		return currcode;
	}

	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}

	public String getTermno() {
		return termno;
	}

	public void setTermno(String termno) {
		this.termno = termno;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getConaccno() {
		return conaccno;
	}

	public void setConaccno(String conaccno) {
		this.conaccno = conaccno;
	}

	public String getConcur() {
		return concur;
	}

	public void setConcur(String concur) {
		this.concur = concur;
	}

	public String getPoschic() {
		return poschic;
	}

	public void setPoschic(String poschic) {
		this.poschic = poschic;
	}

	public String getPoschac() {
		return poschac;
	}

	public void setPoschac(String poschac) {
		this.poschac = poschac;
	}

	public String getPoscrc() {
		return poscrc;
	}

	public void setPoscrc(String poscrc) {
		this.poscrc = poscrc;
	}

	public String getPosoe() {
		return posoe;
	}

	public void setPosoe(String posoe) {
		this.posoe = posoe;
	}

	public String getPoscdoc() {
		return poscdoc;
	}

	public void setPoscdoc(String poscdoc) {
		this.poscdoc = poscdoc;
	}

	public String getPostoc() {
		return postoc;
	}

	public void setPostoc(String postoc) {
		this.postoc = postoc;
	}

	public String getPospcc() {
		return pospcc;
	}

	public void setPospcc(String pospcc) {
		this.pospcc = pospcc;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getCat_params() {
		return cat_params;
	}

	public void setCat_params(String cat_params) {
		this.cat_params = cat_params;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getTermtype() {
		return termtype;
	}

	public void setTermtype(String termtype) {
		this.termtype = termtype;
	}
	
}
