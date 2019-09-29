package com.pay.terminal.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class TerminalBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//终端表
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
	
	//ENCKEY表
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
	private String enckey_id        ;
	
	//终端信息补充表
	private String termpos_id;
	private String pos_tel           ;
	private String batch_no          ;
	private String add_date          ;
	private String x_location          ;
	private String state             ;
	private String city_no           ;
	private String province          ;
	private String zone              ;
	private String settle_mrch_acc_id;
	private String x_termcode          ;
	private String x_timezone          ;
	private String inst_id           ;
	private String mrchno            ;
	private String term_stat	     ;//终端状态
	private String consump_category  ;//消费场景
	private String disabled_date;//停用时间
	private String enable_date;	//启用时间
	
	private String verno_ctx;
	
	private String m_acc_id;//商户账户ID
	
	public String getM_acc_id() {
		return m_acc_id;
	}

	public void setM_acc_id(String m_acc_id) {
		this.m_acc_id = m_acc_id;
	}

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String vernoCtx) {
		verno_ctx = vernoCtx;
	}

	public TerminalBean(){
		
	}
	
	public TerminalBean (HashMap record) throws Exception{
		for (Object key : record.keySet()) {
			//System.out.println("--------key=[" + key + "]" + record.get(key));
			
			Class[] cargs = new Class[1];
			String realArgs = "";
			cargs[0] = realArgs.getClass();
			Method method =null;
			if (((String)key).equalsIgnoreCase("location_1")) {
				method = this.getClass().getMethod(
						"setX_location", cargs);
			} else if (((String)key).equalsIgnoreCase("termcode_1")){
				method = this.getClass().getMethod(
						"setX_termcode", cargs);
			} else if (((String)key).equalsIgnoreCase("timezone_1")){
				method = this.getClass().getMethod(
						"setX_timezone", cargs);
			} else {
				method = this.getClass().getMethod(
						"set"
								+ StringUtils.toUpperCaseFirstOne(((String) key)
										.toLowerCase()), cargs);
			}
			Object arglist[] = new Object[1];
			if (record.get(key) == null) {
				arglist[0] = "";
			} else {
				if (
					((String)key).equalsIgnoreCase("add_date")
					||((String)key).equalsIgnoreCase("actdate")
					||((String)key).equalsIgnoreCase("disabled_date")
					||((String)key).equalsIgnoreCase("enable_date")) {
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

	public String getEnckey_id() {
		return enckey_id;
	}

	public void setEnckey_id(String enckey_id) {
		this.enckey_id = enckey_id;
	}

	public String getTermpos_id() {
		return termpos_id;
	}

	public void setTermpos_id(String termpos_id) {
		this.termpos_id = termpos_id;
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

	public String getX_location() {
		return x_location;
	}

	public void setX_location(String x_location) {
		this.x_location = x_location;
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

	public String getX_termcode() {
		return x_termcode;
	}

	public void setX_termcode(String x_termcode) {
		this.x_termcode = x_termcode;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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
