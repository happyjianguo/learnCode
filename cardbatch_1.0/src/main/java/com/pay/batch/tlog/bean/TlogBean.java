package com.pay.batch.tlog.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class TlogBean {
	private String  verno_ctx                  ;
	private String  id                         ;
	private String  msghdr                     ;
	private String  bitmap                     ;
	private String  stan                       ;
	private String  stanorg                    ;
	private String  rrn                        ;
	private String  termtype                   ;
	private String  termseq                    ;
	private String  privdata1                  ;
	private String  route_iid                  ;
	private String  crdseqno                   ;
	private String  pan                        ;
	private String  panext                     ;
	private String  dateexp                    ;
	private String  svccode                    ;
	private String  t1                         ;
	private String  t2                         ;
	private String  t3                         ;
	private String  crdacptloc                 ;
	private String  crdacptbus                 ;
	private String  crdacptid                  ;
	private String  termcode                   ;
	private String  poschic                    ;
	private String  poschac                    ;
	private String  poscrc                     ;
	private String  posoe                      ;
	private String  poschp                     ;
	private String  poscp                      ;
	private String  poscdim                    ;
	private String  poscham                    ;
	private String  poscha                     ;
	private String  poschad                    ;
	private String  possd                      ;
	private String  pospcc                     ;
	private String  poscc_89                   ;
	private String  aiid                       ;
	private String  acqcountry                 ;
	private String  datexmit                   ;
	private String  timexmit                   ;
	private String  datelocal                  ;
	private String  timelocal                  ;
	private String  datexmitorg                ;
	private String  timexmitorg                ;
	private String  msgcls                     ;
	private String  msgclsorg                  ;
	private String  msgfn                      ;
	private String  msgfnorg                   ;
	private String  txnsrc                     ;
	private String  txnsrcorg                  ;
	private String  fncode                     ;
	private String  txncode                    ;
	private String  txnstatus                  ;
	private String  rspsrc                     ;
	private String  stipreason                 ;
	private String  rejreason                  ;
	private String  curtxn                     ;
	private String  amttxn                     ;
	private String  amttxnfee                  ;
	private String  amtprocfee                 ;
	private String  amttxnorg                  ;
	private String  amttxncb                   ;
	private String  actype1                    ;
	private String  acnum1                     ;
	private String  actype2                    ;
	private String  acnum2                     ;
	private String  actioncode                 ;
	private String  rspcode                    ;
	private String  reasoncode                 ;
	private String  aprvlcode                  ;
	private String  vercodes                   ;
	private String  authlife                   ;
	private String  fiid                       ;
	private String  fiidcountry                ;
	private String  riid                       ;
	private String  riidcountry                ;
	private String  siid                       ;
	private String  siidcountry                ;
	private String  dateset                    ;
	private String  amtset                     ;
	private String  rateset                    ;
	private String  curset                     ;
	private String  inbtchid                   ;
	private String  outbtchid                  ;
	private String  ctxdate                    ;
	private String  indaterec                  ;
	private String  inrecbtch                  ;
	private String  outdaterec                 ;
	private String  outrecbtch                 ;
	private String  inbtchseq                  ;
	private String  afe                        ;
	private String  ife                        ;
	private String  outbtchtype                ;
	private String  curbill                    ;
	private String  amtbill                    ;
	private String  amtbillcb                  ;
	private String  ratebill                   ;
	private String  adddata                    ;
	private String  scheme                     ;
	private String  amttax                     ;
	private String  ratetax                    ;
	private String  amtcom                     ;
	private String  vcps                       ;
	private String  vmscflds                   ;
	private String  cleared                    ;
	private String  pmcid                      ;
	private String  cur_traded                 ;
	private String  amt_traded                 ;
	private String  amtorg_traded              ;
	private String  amtcb_traded               ;
	private String  pan_display                ;
	private String  crdproduct                 ;
	private String  crddet_id                  ;
	private String  crddet_real_id             ;
	private String  accdet1_id                 ;
	private String  accdet2_id                 ;
	private String  cat_params                 ;
	private String  group_id                   ;
	private String  trn                        ;
	private String  arn                        ;
	private String  amt_issuer_com             ;
	private String  cur_issuer_com             ;
	private String  amt_acq_com                ;
	private String  cur_acq_com                ;
	private String  amt_reimbursement          ;
	private String  cur_reimbursement          ;
	private String  curtxnfee                  ;
	private String  curprocfee                 ;
	private String  curtxnorg                  ;
	private String  curtax                     ;
	private String  curcom                     ;
	private String  amttxn_auth                ;
	private String  amttxncb_auth              ;
	private String  atc                        ;
	private String  ucaf                       ;
	private String  aavars                     ;
	private String  mc_private                 ;
	private String  src_iid                    ;
	private String  src_iid_country            ;
	private String  dest_iid                   ;
	private String  dest_iid_country           ;
	private String  when_created               ;
	private String  acq_inst_id                ;
	private String  iss_inst_id                ;
	private String  net_mrch_region            ;
	private String  net_iss_region             ;
	private String  net_mcc_group              ;
	private String  net_crdproduct             ;
	private String  net_mrch_id                ;
	private String  net_rrn                    ;
	private String  poscap                     ;
	private String  poscdic                    ;
	private String  crdacptloc_name            ;
	private String  crdacptloc_street          ;
	private String  crdacptloc_city            ;
	private String  crdacptloc_postcode        ;
	private String  crdacptloc_region          ;
	private String  crdacptloc_country         ;
	private String  auth_expdate               ;
	private String  verresults                 ;
	private String  sub_txncode                ;
	
	//添加时间查询条件
	private String queryDTStart;
	private String queryDTEnd;
	private String queryTxnType;
	public String getQueryDTStart() {
		return queryDTStart;
	}
	public void setQueryDTStart(String queryDTStart) {
		this.queryDTStart = queryDTStart;
	}
	public String getQueryDTEnd() {
		return queryDTEnd;
	}
	public void setQueryDTEnd(String queryDTEnd) {
		this.queryDTEnd = queryDTEnd;
	}
	
	public String getQueryTxnType() {
		return queryTxnType;
	}
	public void setQueryTxnType(String queryTxnType) {
		this.queryTxnType = queryTxnType;
	}
	public TlogBean(){
		
	}
	public TlogBean(HashMap record) {
		//利用java反射机制为javabean赋值
		RecordMethod  recordMethod=new RecordMethod(record,TlogBean.class,this);
		recordMethod.recordset();	
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
	public String getMsghdr() {
		return msghdr;
	}
	public void setMsghdr(String msghdr) {
		this.msghdr = msghdr;
	}
	public String getBitmap() {
		return bitmap;
	}
	public void setBitmap(String bitmap) {
		this.bitmap = bitmap;
	}
	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public String getStanorg() {
		return stanorg;
	}
	public void setStanorg(String stanorg) {
		this.stanorg = stanorg;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getTermtype() {
		return termtype;
	}
	public void setTermtype(String termtype) {
		this.termtype = termtype;
	}
	public String getTermseq() {
		return termseq;
	}
	public void setTermseq(String termseq) {
		this.termseq = termseq;
	}
	public String getPrivdata1() {
		return privdata1;
	}
	public void setPrivdata1(String privdata1) {
		this.privdata1 = privdata1;
	}
	public String getRoute_iid() {
		return route_iid;
	}
	public void setRoute_iid(String route_iid) {
		this.route_iid = route_iid;
	}
	public String getCrdseqno() {
		return crdseqno;
	}
	public void setCrdseqno(String crdseqno) {
		this.crdseqno = crdseqno;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPanext() {
		return panext;
	}
	public void setPanext(String panext) {
		this.panext = panext;
	}
	public String getDateexp() {
		return dateexp;
	}
	public void setDateexp(String dateexp) {
		this.dateexp = dateexp;
	}
	public String getSvccode() {
		return svccode;
	}
	public void setSvccode(String svccode) {
		this.svccode = svccode;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	public String getT2() {
		return t2;
	}
	public void setT2(String t2) {
		this.t2 = t2;
	}
	public String getT3() {
		return t3;
	}
	public void setT3(String t3) {
		this.t3 = t3;
	}
	public String getCrdacptloc() {
		return crdacptloc;
	}
	public void setCrdacptloc(String crdacptloc) {
		this.crdacptloc = crdacptloc;
	}
	public String getCrdacptbus() {
		return crdacptbus;
	}
	public void setCrdacptbus(String crdacptbus) {
		this.crdacptbus = crdacptbus;
	}
	public String getCrdacptid() {
		return crdacptid;
	}
	public void setCrdacptid(String crdacptid) {
		this.crdacptid = crdacptid;
	}
	public String getTermcode() {
		return termcode;
	}
	public void setTermcode(String termcode) {
		this.termcode = termcode;
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
	public String getPoschp() {
		return poschp;
	}
	public void setPoschp(String poschp) {
		this.poschp = poschp;
	}
	public String getPoscp() {
		return poscp;
	}
	public void setPoscp(String poscp) {
		this.poscp = poscp;
	}
	public String getPoscdim() {
		return poscdim;
	}
	public void setPoscdim(String poscdim) {
		this.poscdim = poscdim;
	}
	public String getPoscham() {
		return poscham;
	}
	public void setPoscham(String poscham) {
		this.poscham = poscham;
	}
	public String getPoscha() {
		return poscha;
	}
	public void setPoscha(String poscha) {
		this.poscha = poscha;
	}
	public String getPoschad() {
		return poschad;
	}
	public void setPoschad(String poschad) {
		this.poschad = poschad;
	}
	public String getPossd() {
		return possd;
	}
	public void setPossd(String possd) {
		this.possd = possd;
	}
	public String getPospcc() {
		return pospcc;
	}
	public void setPospcc(String pospcc) {
		this.pospcc = pospcc;
	}
	public String getPoscc_89() {
		return poscc_89;
	}
	public void setPoscc_89(String poscc_89) {
		this.poscc_89 = poscc_89;
	}
	public String getAiid() {
		return aiid;
	}
	public void setAiid(String aiid) {
		this.aiid = aiid;
	}
	public String getAcqcountry() {
		return acqcountry;
	}
	public void setAcqcountry(String acqcountry) {
		this.acqcountry = acqcountry;
	}
	public String getDatexmit() {
		return datexmit;
	}
	public void setDatexmit(String datexmit) {
		this.datexmit = datexmit;
	}
	public String getTimexmit() {
		return timexmit;
	}
	public void setTimexmit(String timexmit) {
		this.timexmit = timexmit;
	}
	public String getDatelocal() {
		return datelocal;
	}
	public void setDatelocal(String datelocal) {
		this.datelocal = datelocal;
	}
	public String getTimelocal() {
		return timelocal;
	}
	public void setTimelocal(String timelocal) {
		this.timelocal = timelocal;
	}
	public String getDatexmitorg() {
		return datexmitorg;
	}
	public void setDatexmitorg(String datexmitorg) {
		this.datexmitorg = datexmitorg;
	}
	public String getTimexmitorg() {
		return timexmitorg;
	}
	public void setTimexmitorg(String timexmitorg) {
		this.timexmitorg = timexmitorg;
	}
	public String getMsgcls() {
		return msgcls;
	}
	public void setMsgcls(String msgcls) {
		this.msgcls = msgcls;
	}
	public String getMsgclsorg() {
		return msgclsorg;
	}
	public void setMsgclsorg(String msgclsorg) {
		this.msgclsorg = msgclsorg;
	}
	public String getMsgfn() {
		return msgfn;
	}
	public void setMsgfn(String msgfn) {
		this.msgfn = msgfn;
	}
	public String getMsgfnorg() {
		return msgfnorg;
	}
	public void setMsgfnorg(String msgfnorg) {
		this.msgfnorg = msgfnorg;
	}
	public String getTxnsrc() {
		return txnsrc;
	}
	public void setTxnsrc(String txnsrc) {
		this.txnsrc = txnsrc;
	}
	public String getTxnsrcorg() {
		return txnsrcorg;
	}
	public void setTxnsrcorg(String txnsrcorg) {
		this.txnsrcorg = txnsrcorg;
	}
	public String getFncode() {
		return fncode;
	}
	public void setFncode(String fncode) {
		this.fncode = fncode;
	}
	public String getTxncode() {
		return txncode;
	}
	public void setTxncode(String txncode) {
		this.txncode = txncode;
	}
	public String getTxnstatus() {
		return txnstatus;
	}
	public void setTxnstatus(String txnstatus) {
		this.txnstatus = txnstatus;
	}
	public String getRspsrc() {
		return rspsrc;
	}
	public void setRspsrc(String rspsrc) {
		this.rspsrc = rspsrc;
	}
	public String getStipreason() {
		return stipreason;
	}
	public void setStipreason(String stipreason) {
		this.stipreason = stipreason;
	}
	public String getRejreason() {
		return rejreason;
	}
	public void setRejreason(String rejreason) {
		this.rejreason = rejreason;
	}
	public String getCurtxn() {
		return curtxn;
	}
	public void setCurtxn(String curtxn) {
		this.curtxn = curtxn;
	}
	public String getAmttxn() {
		return amttxn;
	}
	public void setAmttxn(String amttxn) {
		this.amttxn = amttxn;
	}
	public String getAmttxnfee() {
		return amttxnfee;
	}
	public void setAmttxnfee(String amttxnfee) {
		this.amttxnfee = amttxnfee;
	}
	public String getAmtprocfee() {
		return amtprocfee;
	}
	public void setAmtprocfee(String amtprocfee) {
		this.amtprocfee = amtprocfee;
	}
	public String getAmttxnorg() {
		return amttxnorg;
	}
	public void setAmttxnorg(String amttxnorg) {
		this.amttxnorg = amttxnorg;
	}
	public String getAmttxncb() {
		return amttxncb;
	}
	public void setAmttxncb(String amttxncb) {
		this.amttxncb = amttxncb;
	}
	public String getActype1() {
		return actype1;
	}
	public void setActype1(String actype1) {
		this.actype1 = actype1;
	}
	public String getAcnum1() {
		return acnum1;
	}
	public void setAcnum1(String acnum1) {
		this.acnum1 = acnum1;
	}
	public String getActype2() {
		return actype2;
	}
	public void setActype2(String actype2) {
		this.actype2 = actype2;
	}
	public String getAcnum2() {
		return acnum2;
	}
	public void setAcnum2(String acnum2) {
		this.acnum2 = acnum2;
	}
	public String getActioncode() {
		return actioncode;
	}
	public void setActioncode(String actioncode) {
		this.actioncode = actioncode;
	}
	public String getRspcode() {
		return rspcode;
	}
	public void setRspcode(String rspcode) {
		this.rspcode = rspcode;
	}
	public String getReasoncode() {
		return reasoncode;
	}
	public void setReasoncode(String reasoncode) {
		this.reasoncode = reasoncode;
	}
	public String getAprvlcode() {
		return aprvlcode;
	}
	public void setAprvlcode(String aprvlcode) {
		this.aprvlcode = aprvlcode;
	}
	public String getVercodes() {
		return vercodes;
	}
	public void setVercodes(String vercodes) {
		this.vercodes = vercodes;
	}
	public String getAuthlife() {
		return authlife;
	}
	public void setAuthlife(String authlife) {
		this.authlife = authlife;
	}
	public String getFiid() {
		return fiid;
	}
	public void setFiid(String fiid) {
		this.fiid = fiid;
	}
	public String getFiidcountry() {
		return fiidcountry;
	}
	public void setFiidcountry(String fiidcountry) {
		this.fiidcountry = fiidcountry;
	}
	public String getRiid() {
		return riid;
	}
	public void setRiid(String riid) {
		this.riid = riid;
	}
	public String getRiidcountry() {
		return riidcountry;
	}
	public void setRiidcountry(String riidcountry) {
		this.riidcountry = riidcountry;
	}
	public String getSiid() {
		return siid;
	}
	public void setSiid(String siid) {
		this.siid = siid;
	}
	public String getSiidcountry() {
		return siidcountry;
	}
	public void setSiidcountry(String siidcountry) {
		this.siidcountry = siidcountry;
	}
	public String getDateset() {
		return dateset;
	}
	public void setDateset(String dateset) {
		this.dateset = dateset;
	}
	public String getAmtset() {
		return amtset;
	}
	public void setAmtset(String amtset) {
		this.amtset = amtset;
	}
	public String getRateset() {
		return rateset;
	}
	public void setRateset(String rateset) {
		this.rateset = rateset;
	}
	public String getCurset() {
		return curset;
	}
	public void setCurset(String curset) {
		this.curset = curset;
	}
	public String getInbtchid() {
		return inbtchid;
	}
	public void setInbtchid(String inbtchid) {
		this.inbtchid = inbtchid;
	}
	public String getOutbtchid() {
		return outbtchid;
	}
	public void setOutbtchid(String outbtchid) {
		this.outbtchid = outbtchid;
	}
	public String getCtxdate() {
		return ctxdate;
	}
	public void setCtxdate(String ctxdate) {
		this.ctxdate = ctxdate;
	}
	public String getIndaterec() {
		return indaterec;
	}
	public void setIndaterec(String indaterec) {
		this.indaterec = indaterec;
	}
	public String getInrecbtch() {
		return inrecbtch;
	}
	public void setInrecbtch(String inrecbtch) {
		this.inrecbtch = inrecbtch;
	}
	public String getOutdaterec() {
		return outdaterec;
	}
	public void setOutdaterec(String outdaterec) {
		this.outdaterec = outdaterec;
	}
	public String getOutrecbtch() {
		return outrecbtch;
	}
	public void setOutrecbtch(String outrecbtch) {
		this.outrecbtch = outrecbtch;
	}
	public String getInbtchseq() {
		return inbtchseq;
	}
	public void setInbtchseq(String inbtchseq) {
		this.inbtchseq = inbtchseq;
	}
	public String getAfe() {
		return afe;
	}
	public void setAfe(String afe) {
		this.afe = afe;
	}
	public String getIfe() {
		return ife;
	}
	public void setIfe(String ife) {
		this.ife = ife;
	}
	public String getOutbtchtype() {
		return outbtchtype;
	}
	public void setOutbtchtype(String outbtchtype) {
		this.outbtchtype = outbtchtype;
	}
	public String getCurbill() {
		return curbill;
	}
	public void setCurbill(String curbill) {
		this.curbill = curbill;
	}
	public String getAmtbill() {
		return amtbill;
	}
	public void setAmtbill(String amtbill) {
		this.amtbill = amtbill;
	}
	public String getAmtbillcb() {
		return amtbillcb;
	}
	public void setAmtbillcb(String amtbillcb) {
		this.amtbillcb = amtbillcb;
	}
	public String getRatebill() {
		return ratebill;
	}
	public void setRatebill(String ratebill) {
		this.ratebill = ratebill;
	}
	public String getAdddata() {
		return adddata;
	}
	public void setAdddata(String adddata) {
		this.adddata = adddata;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getAmttax() {
		return amttax;
	}
	public void setAmttax(String amttax) {
		this.amttax = amttax;
	}
	public String getRatetax() {
		return ratetax;
	}
	public void setRatetax(String ratetax) {
		this.ratetax = ratetax;
	}
	public String getAmtcom() {
		return amtcom;
	}
	public void setAmtcom(String amtcom) {
		this.amtcom = amtcom;
	}
	public String getVcps() {
		return vcps;
	}
	public void setVcps(String vcps) {
		this.vcps = vcps;
	}
	public String getVmscflds() {
		return vmscflds;
	}
	public void setVmscflds(String vmscflds) {
		this.vmscflds = vmscflds;
	}
	public String getCleared() {
		return cleared;
	}
	public void setCleared(String cleared) {
		this.cleared = cleared;
	}
	public String getPmcid() {
		return pmcid;
	}
	public void setPmcid(String pmcid) {
		this.pmcid = pmcid;
	}
	public String getCur_traded() {
		return cur_traded;
	}
	public void setCur_traded(String cur_traded) {
		this.cur_traded = cur_traded;
	}
	public String getAmt_traded() {
		return amt_traded;
	}
	public void setAmt_traded(String amt_traded) {
		this.amt_traded = amt_traded;
	}
	public String getAmtorg_traded() {
		return amtorg_traded;
	}
	public void setAmtorg_traded(String amtorg_traded) {
		this.amtorg_traded = amtorg_traded;
	}
	public String getAmtcb_traded() {
		return amtcb_traded;
	}
	public void setAmtcb_traded(String amtcb_traded) {
		this.amtcb_traded = amtcb_traded;
	}
	public String getPan_display() {
		return pan_display;
	}
	public void setPan_display(String pan_display) {
		this.pan_display = pan_display;
	}
	public String getCrdproduct() {
		return crdproduct;
	}
	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}
	public String getCrddet_id() {
		return crddet_id;
	}
	public void setCrddet_id(String crddet_id) {
		this.crddet_id = crddet_id;
	}
	public String getCrddet_real_id() {
		return crddet_real_id;
	}
	public void setCrddet_real_id(String crddet_real_id) {
		this.crddet_real_id = crddet_real_id;
	}
	public String getAccdet1_id() {
		return accdet1_id;
	}
	public void setAccdet1_id(String accdet1_id) {
		this.accdet1_id = accdet1_id;
	}
	public String getAccdet2_id() {
		return accdet2_id;
	}
	public void setAccdet2_id(String accdet2_id) {
		this.accdet2_id = accdet2_id;
	}
	public String getCat_params() {
		return cat_params;
	}
	public void setCat_params(String cat_params) {
		this.cat_params = cat_params;
	}
	public String getGroup_id() {
		return group_id;
	}
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	public String getTrn() {
		return trn;
	}
	public void setTrn(String trn) {
		this.trn = trn;
	}
	public String getArn() {
		return arn;
	}
	public void setArn(String arn) {
		this.arn = arn;
	}
	public String getAmt_issuer_com() {
		return amt_issuer_com;
	}
	public void setAmt_issuer_com(String amt_issuer_com) {
		this.amt_issuer_com = amt_issuer_com;
	}
	public String getCur_issuer_com() {
		return cur_issuer_com;
	}
	public void setCur_issuer_com(String cur_issuer_com) {
		this.cur_issuer_com = cur_issuer_com;
	}
	public String getAmt_acq_com() {
		return amt_acq_com;
	}
	public void setAmt_acq_com(String amt_acq_com) {
		this.amt_acq_com = amt_acq_com;
	}
	public String getCur_acq_com() {
		return cur_acq_com;
	}
	public void setCur_acq_com(String cur_acq_com) {
		this.cur_acq_com = cur_acq_com;
	}
	public String getAmt_reimbursement() {
		return amt_reimbursement;
	}
	public void setAmt_reimbursement(String amt_reimbursement) {
		this.amt_reimbursement = amt_reimbursement;
	}
	public String getCur_reimbursement() {
		return cur_reimbursement;
	}
	public void setCur_reimbursement(String cur_reimbursement) {
		this.cur_reimbursement = cur_reimbursement;
	}
	public String getCurtxnfee() {
		return curtxnfee;
	}
	public void setCurtxnfee(String curtxnfee) {
		this.curtxnfee = curtxnfee;
	}
	public String getCurprocfee() {
		return curprocfee;
	}
	public void setCurprocfee(String curprocfee) {
		this.curprocfee = curprocfee;
	}
	public String getCurtxnorg() {
		return curtxnorg;
	}
	public void setCurtxnorg(String curtxnorg) {
		this.curtxnorg = curtxnorg;
	}
	public String getCurtax() {
		return curtax;
	}
	public void setCurtax(String curtax) {
		this.curtax = curtax;
	}
	public String getCurcom() {
		return curcom;
	}
	public void setCurcom(String curcom) {
		this.curcom = curcom;
	}
	public String getAmttxn_auth() {
		return amttxn_auth;
	}
	public void setAmttxn_auth(String amttxn_auth) {
		this.amttxn_auth = amttxn_auth;
	}
	public String getAmttxncb_auth() {
		return amttxncb_auth;
	}
	public void setAmttxncb_auth(String amttxncb_auth) {
		this.amttxncb_auth = amttxncb_auth;
	}
	public String getAtc() {
		return atc;
	}
	public void setAtc(String atc) {
		this.atc = atc;
	}
	public String getUcaf() {
		return ucaf;
	}
	public void setUcaf(String ucaf) {
		this.ucaf = ucaf;
	}
	public String getAavars() {
		return aavars;
	}
	public void setAavars(String aavars) {
		this.aavars = aavars;
	}
	public String getMc_private() {
		return mc_private;
	}
	public void setMc_private(String mc_private) {
		this.mc_private = mc_private;
	}
	public String getSrc_iid() {
		return src_iid;
	}
	public void setSrc_iid(String src_iid) {
		this.src_iid = src_iid;
	}
	public String getSrc_iid_country() {
		return src_iid_country;
	}
	public void setSrc_iid_country(String src_iid_country) {
		this.src_iid_country = src_iid_country;
	}
	public String getDest_iid() {
		return dest_iid;
	}
	public void setDest_iid(String dest_iid) {
		this.dest_iid = dest_iid;
	}
	public String getDest_iid_country() {
		return dest_iid_country;
	}
	public void setDest_iid_country(String dest_iid_country) {
		this.dest_iid_country = dest_iid_country;
	}
	public String getWhen_created() {
		return when_created;
	}
	public void setWhen_created(String when_created) {
		this.when_created = when_created;
	}
	public String getAcq_inst_id() {
		return acq_inst_id;
	}
	public void setAcq_inst_id(String acq_inst_id) {
		this.acq_inst_id = acq_inst_id;
	}
	public String getIss_inst_id() {
		return iss_inst_id;
	}
	public void setIss_inst_id(String iss_inst_id) {
		this.iss_inst_id = iss_inst_id;
	}
	public String getNet_mrch_region() {
		return net_mrch_region;
	}
	public void setNet_mrch_region(String net_mrch_region) {
		this.net_mrch_region = net_mrch_region;
	}
	public String getNet_iss_region() {
		return net_iss_region;
	}
	public void setNet_iss_region(String net_iss_region) {
		this.net_iss_region = net_iss_region;
	}
	public String getNet_mcc_group() {
		return net_mcc_group;
	}
	public void setNet_mcc_group(String net_mcc_group) {
		this.net_mcc_group = net_mcc_group;
	}
	public String getNet_crdproduct() {
		return net_crdproduct;
	}
	public void setNet_crdproduct(String net_crdproduct) {
		this.net_crdproduct = net_crdproduct;
	}
	public String getNet_mrch_id() {
		return net_mrch_id;
	}
	public void setNet_mrch_id(String net_mrch_id) {
		this.net_mrch_id = net_mrch_id;
	}
	public String getNet_rrn() {
		return net_rrn;
	}
	public void setNet_rrn(String net_rrn) {
		this.net_rrn = net_rrn;
	}
	public String getPoscap() {
		return poscap;
	}
	public void setPoscap(String poscap) {
		this.poscap = poscap;
	}
	public String getPoscdic() {
		return poscdic;
	}
	public void setPoscdic(String poscdic) {
		this.poscdic = poscdic;
	}
	public String getCrdacptloc_name() {
		return crdacptloc_name;
	}
	public void setCrdacptloc_name(String crdacptloc_name) {
		this.crdacptloc_name = crdacptloc_name;
	}
	public String getCrdacptloc_street() {
		return crdacptloc_street;
	}
	public void setCrdacptloc_street(String crdacptloc_street) {
		this.crdacptloc_street = crdacptloc_street;
	}
	public String getCrdacptloc_city() {
		return crdacptloc_city;
	}
	public void setCrdacptloc_city(String crdacptloc_city) {
		this.crdacptloc_city = crdacptloc_city;
	}
	public String getCrdacptloc_postcode() {
		return crdacptloc_postcode;
	}
	public void setCrdacptloc_postcode(String crdacptloc_postcode) {
		this.crdacptloc_postcode = crdacptloc_postcode;
	}
	public String getCrdacptloc_region() {
		return crdacptloc_region;
	}
	public void setCrdacptloc_region(String crdacptloc_region) {
		this.crdacptloc_region = crdacptloc_region;
	}
	public String getCrdacptloc_country() {
		return crdacptloc_country;
	}
	public void setCrdacptloc_country(String crdacptloc_country) {
		this.crdacptloc_country = crdacptloc_country;
	}
	public String getAuth_expdate() {
		return auth_expdate;
	}
	public void setAuth_expdate(String auth_expdate) {
		this.auth_expdate = auth_expdate;
	}
	public String getVerresults() {
		return verresults;
	}
	public void setVerresults(String verresults) {
		this.verresults = verresults;
	}
	public String getSub_txncode() {
		return sub_txncode;
	}
	public void setSub_txncode(String sub_txncode) {
		this.sub_txncode = sub_txncode;
	}
	

	
}
