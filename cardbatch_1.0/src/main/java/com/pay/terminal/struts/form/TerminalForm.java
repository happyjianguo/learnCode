package com.pay.terminal.struts.form;

import org.apache.struts.action.ActionForm;

public class TerminalForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String termcodeQ;
	private String mrchnoSelQ;
	private String mrchnoSelJSQ;//结算商户输入条件模糊查询
	private String merchantJS_id;//结算商户，为了关联出结算账号
	private String mrchnoQ;
	private String mrchNameQ;
	private String addDT_startdate;
	private String addDT_enddate;
	private String accIsNull;//有无结算账号
	private String timeZoneQ;//费率
	
	public void setAddDT_enddate(String addDTEnddate) {
		addDT_enddate = addDTEnddate;
	}

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
	private String verno_ctx;
	


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
	
	private String m_acc_id;//商户账户ID
	
	//万科商户类型
	private String yard_mer_type;
	private String mrchnoSelQBak;
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getAddDT_startdate() {
		return addDT_startdate;
	}

	public String getM_acc_id() {
		return m_acc_id;
	}

	public void setM_acc_id(String m_acc_id) {
		this.m_acc_id = m_acc_id;
	}

	public void setAddDT_startdate(String addDTStartdate) {
		addDT_startdate = addDTStartdate;
	}


	public String getAddDT_enddate() {
		return addDT_enddate;
	}
	public String getMrchnoQ() {
		return mrchnoQ;
	}
	public void setMrchnoQ(String mrchnoQ) {
		this.mrchnoQ = mrchnoQ;
	}
	public String getMrchNameQ() {
		return mrchNameQ;
	}
	public void setMrchNameQ(String mrchNameQ) {
		this.mrchNameQ = mrchNameQ;
	}
	public String getTermcodeQ() {
		return termcodeQ;
	}
	public void setTermcodeQ(String termcodeQ) {
		this.termcodeQ = termcodeQ;
	}
	public String getMrchnoSelQ() {
		return mrchnoSelQ;
	}
	public void setMrchnoSelQ(String mrchnoSelQ) {
		this.mrchnoSelQ = mrchnoSelQ;
	}
	public String getMerchantJS_id() {
		return merchantJS_id;
	}
	public void setMerchantJS_id(String merchantJSId) {
		merchantJS_id = merchantJSId;
	}
	public String getMrchnoSelJSQ() {
		return mrchnoSelJSQ;
	}
	public void setMrchnoSelJSQ(String mrchnoSelJSQ) {
		this.mrchnoSelJSQ = mrchnoSelJSQ;
	}
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		if(typeid==null||"".equals(typeid)){
			typeid="1";
		}
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
		if(testflag==null||"".equals(testflag)){
			testflag="0";
		}
		return testflag;
	}

	public void setTestflag(String testflag) {
		this.testflag = testflag;
	}

	public String getStatusid() {
		if(statusid==null||"".equals(statusid)){
			statusid="0";
		}
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public String getCurrcode() {
		if(currcode==null||"".equals(currcode)){
			currcode="156";
		}
		return currcode;
	}

	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	//取当前商户下终端该值最大值加1
	public String getTermno() {
		return termno;
	}

	public void setTermno(String termno) {
		this.termno = termno;
	}

	public String getLocation() {
		if(location==null||"".equals(location)){
			location="TEST Location";
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getConaccno() {
		if(conaccno==null||"".equals(conaccno)){
			conaccno=" ";
		}
		return conaccno;
	}

	public void setConaccno(String conaccno) {
		this.conaccno = conaccno;
	}

	public String getConcur() {
		if(concur==null||"".equals(concur)){
			concur=" ";
		}
		return concur;
	}

	public void setConcur(String concur) {
		this.concur = concur;
	}

	public String getPoschic() {
		if(poschic==null||"".equals(poschic)){
			poschic="5";
		}
		return poschic;
	}

	public void setPoschic(String poschic) {
		this.poschic = poschic;
	}

	public String getPoschac() {
		if(poschac==null||"".equals(poschac)){
			poschac="0";
		}
		return poschac;
	}

	public void setPoschac(String poschac) {
		this.poschac = poschac;
	}

	public String getPoscrc() {
		if(poscrc==null||"".equals(poscrc)){
			poscrc="0";
		}
		return poscrc;
	}

	public void setPoscrc(String poscrc) {
		this.poscrc = poscrc;
	}

	public String getPosoe() {
		if(posoe==null||"".equals(posoe)){
			posoe="1";
		}
		return posoe;
	}

	public void setPosoe(String posoe) {
		this.posoe = posoe;
	}

	public String getPoscdoc() {
		if(poscdoc==null||"".equals(poscdoc)){
			poscdoc="1";
		}
		return poscdoc;
	}

	public void setPoscdoc(String poscdoc) {
		this.poscdoc = poscdoc;
	}

	public String getPostoc() {
		if(postoc==null||"".equals(postoc)){
			postoc="4";
		}
		return postoc;
	}

	public void setPostoc(String postoc) {
		this.postoc = postoc;
	}

	public String getPospcc() {
		if(pospcc==null||"".equals(pospcc)){
			pospcc="1";
		}
		return pospcc;
	}

	public void setPospcc(String pospcc) {
		this.pospcc = pospcc;
	}

	public String getTimezone() {
		if(timezone==null||"".equals(timezone)){
			timezone="5";
		}
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getCat_params() {
		if(cat_params==null||"".equals(cat_params)){
			cat_params=" ";
		}
		return cat_params;
	}

	public void setCat_params(String cat_params) {
		this.cat_params = cat_params;
	}

	public String getMerchant_id() {
		if(merchant_id==null||"".equals(merchant_id)){
			merchant_id=" ";
		}
		return merchant_id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getTermtype() {
		if(termtype==null||"".equals(termtype)){
			termtype="0";
		}
		return termtype;
	}

	public void setTermtype(String termtype) {
		this.termtype = termtype;
	}
	public String getVerno_ctx() {
		if(verno_ctx==null||"".equals(verno_ctx)){
			verno_ctx="1";
		}else{
			verno_ctx=(Integer.valueOf(verno_ctx)+1)+""; 
		}
		return verno_ctx;
	}
	public void setVerno_ctx(String vernoCtx) {
		verno_ctx = vernoCtx;
	}
	
	

	public String getKeytype() {
		if(keytype==null||"".equals(keytype)){
			keytype="3";
		}
		return keytype;
	}

	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}

	public String getKeystatus() {
		if(keystatus==null||"".equals(keystatus)){
			keystatus="0";
		}
		return keystatus;
	}

	public void setKeystatus(String keystatus) {
		this.keystatus = keystatus;
	}

	public String getTstflag() {
		if(tstflag==null||"".equals(tstflag)){
			tstflag="0";
		}
		return tstflag;
	}

	public void setTstflag(String tstflag) {
		this.tstflag = tstflag;
	}

	public String getRefcode() {
		if(refcode==null||"".equals(refcode)){
			refcode="POS"+getTermcode();
		}
		return refcode;
	}

	public void setRefcode(String refcode) {
		this.refcode = refcode;
	}

	public String getKeyseqno() {
		if(keyseqno==null||"".equals(keyseqno)){
			keyseqno="0";
		}
		return keyseqno;
	}

	public void setKeyseqno(String keyseqno) {
		this.keyseqno = keyseqno;
	}

	public String getKeyvalue() {
		if(keyvalue==null||"".equals(keyvalue)){
			keyvalue="260ADEB6F102825B62D13FE21E56ED2D";
		}
		return keyvalue;
	}

	public void setKeyvalue(String keyvalue) {
		this.keyvalue = keyvalue;
	}

	public String getPrevalue() {
		if(prevalue==null||"".equals(prevalue)){
			prevalue="260ADEB6F102825B62D13FE21E56ED2D";
		}
		return prevalue;
	}

	public void setPrevalue(String prevalue) {
		this.prevalue = prevalue;
	}

	public String getCheckvalue() {
		if(checkvalue==null||"".equals(checkvalue)){
			checkvalue="7A9EE0BA6D0350A0";
		}
		return checkvalue;
	}

	public void setCheckvalue(String checkvalue) {
		this.checkvalue = checkvalue;
	}

	public String getActdate() {
		if(actdate==null||"".equals(actdate)){
			actdate="2014-04-21";
		}
		return actdate;
	}

	public void setActdate(String actdate) {
		this.actdate = actdate;
	}

	public String getActtime() {
		if(acttime==null||"".equals(acttime)){
			acttime="181037";
		}
		return acttime;
	}

	public void setActtime(String acttime) {
		this.acttime = acttime;
	}

	public String getExpiry() {
		if(expiry==null||"".equals(expiry)){
			expiry="2099/12/31";
		}
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getLongkeyval() {
		if(longkeyval==null||"".equals(longkeyval)){
			longkeyval=" ";
		}
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
		if(x_timezone==null||"".equals(x_timezone)){
			x_timezone="5";
		}
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

	public String getAccIsNull() {
		return accIsNull;
	}

	public void setAccIsNull(String accIsNull) {
		this.accIsNull = accIsNull;
	}

	public String getTimeZoneQ() {
		return timeZoneQ;
	}

	public void setTimeZoneQ(String timeZoneQ) {
		this.timeZoneQ = timeZoneQ;
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
	
	public String getYard_mer_type() {
		return yard_mer_type;
	}

	public void setYard_mer_type(String yard_mer_type) {
		this.yard_mer_type = yard_mer_type;
	}

	public String getMrchnoSelQBak() {
		return mrchnoSelQBak;
	}

	public void setMrchnoSelQBak(String mrchnoSelQBak) {
		this.mrchnoSelQBak = mrchnoSelQBak;
	}
	
}