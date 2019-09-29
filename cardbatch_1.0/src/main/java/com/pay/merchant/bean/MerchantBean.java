package com.pay.merchant.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class MerchantBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String mrchno;// 商户号
	private String inst_id;
	private String head_office;
	private String name;
	private String trading_as;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String postcode;
	private String countrycode;
	private String phy_address1;
	private String phy_address2;
	private String phy_city;
	private String phy_state;
	private String phy_postcode;
	private String phy_countrycode;
	private String reg_address1;
	private String reg_address2;
	private String reg_city;
	private String reg_state;
	private String reg_postcode;
	private String reg_countrycode;
	private String currcode;
	private String mrchtype;
	private String acptbus;
	private String contact1;
	private String contact2;
	private String contact3;
	private String telno1;
	private String telno2;
	private String telno3;
	private String faxno;
	private String email;
	private String telex;
	private String sortcode;
	private String brncode;
	private String accno;
	private String accnm;
	private String taxcode;
	private String stmtto;
	private String stmtto_ho;
	private String paymtype;
	private String paymto;
	private String posflag;
	private String vipflag;//这个没有写，少一个商户类型
	private String msc_charge;
	private String msc_calc;
	private String msc_table;
	private String msc;//
	private String contrno;
	private String contrdate;
	private String contrsign;
	private String contrcnx;
	private String official;
	private String mrchstat;
	private String rnc;
	private String taxreg;
	private String retenamt;
	private String retenpc;
	private String zonegeog;
	private String zonecomer;
	private String zonepostal;
	private String usrdata1;
	private String usrdata2;
	private String usrdata3;
	private String memo;//
	private String cycbegin;
	private String cyclen;
	private String conaccno;
	private String no_imprntrs;
	private String concur;
	private String payoffset;
	private String allowinst;
	private String pppymntattr;
	private String cat_params;
	private String id;
	private String verno_ctx;
	private String stmtfreq;

	public MerchantBean(){
		
	}
	
	public MerchantBean (HashMap record) throws Exception{
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
	
	public String getMrchno() {
		return mrchno;
	}
	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}
	public String getInst_id() {
		return inst_id;
	}

	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	public String getHead_office() {
		return head_office;
	}

	public void setHead_office(String head_office) {
		this.head_office = head_office;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTrading_as() {
		return trading_as;
	}

	public void setTrading_as(String trading_as) {
		this.trading_as = trading_as;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getPhy_address1() {
		return phy_address1;
	}

	public void setPhy_address1(String phy_address1) {
		this.phy_address1 = phy_address1;
	}

	public String getPhy_address2() {
		return phy_address2;
	}

	public void setPhy_address2(String phy_address2) {
		this.phy_address2 = phy_address2;
	}

	public String getPhy_city() {
		return phy_city;
	}

	public void setPhy_city(String phy_city) {
		this.phy_city = phy_city;
	}

	public String getPhy_state() {
		return phy_state;
	}

	public void setPhy_state(String phy_state) {
		this.phy_state = phy_state;
	}

	public String getPhy_postcode() {
		return phy_postcode;
	}

	public void setPhy_postcode(String phy_postcode) {
		this.phy_postcode = phy_postcode;
	}

	public String getPhy_countrycode() {
		return phy_countrycode;
	}

	public void setPhy_countrycode(String phy_countrycode) {
		this.phy_countrycode = phy_countrycode;
	}

	public String getReg_address1() {
		return reg_address1;
	}

	public void setReg_address1(String reg_address1) {
		this.reg_address1 = reg_address1;
	}

	public String getReg_address2() {
		return reg_address2;
	}

	public void setReg_address2(String reg_address2) {
		this.reg_address2 = reg_address2;
	}

	public String getReg_city() {
		return reg_city;
	}

	public void setReg_city(String reg_city) {
		this.reg_city = reg_city;
	}

	public String getReg_state() {
		return reg_state;
	}

	public void setReg_state(String reg_state) {
		this.reg_state = reg_state;
	}

	public String getReg_postcode() {
		return reg_postcode;
	}

	public void setReg_postcode(String reg_postcode) {
		this.reg_postcode = reg_postcode;
	}

	public String getReg_countrycode() {
		return reg_countrycode;
	}

	public void setReg_countrycode(String reg_countrycode) {
		this.reg_countrycode = reg_countrycode;
	}

	public String getCurrcode() {
		return currcode;
	}

	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}

	public String getMrchtype() {
		return mrchtype;
	}

	public void setMrchtype(String mrchtype) {
		this.mrchtype = mrchtype;
	}

	public String getAcptbus() {
		return acptbus;
	}

	public void setAcptbus(String acptbus) {
		this.acptbus = acptbus;
	}

	public String getContact1() {
		return contact1;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public String getContact3() {
		return contact3;
	}

	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}

	public String getTelno1() {
		return telno1;
	}

	public void setTelno1(String telno1) {
		this.telno1 = telno1;
	}

	public String getTelno2() {
		return telno2;
	}

	public void setTelno2(String telno2) {
		this.telno2 = telno2;
	}

	public String getTelno3() {
		return telno3;
	}

	public void setTelno3(String telno3) {
		this.telno3 = telno3;
	}

	public String getFaxno() {
		return faxno;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelex() {
		return telex;
	}

	public void setTelex(String telex) {
		this.telex = telex;
	}

	public String getSortcode() {
		return sortcode;
	}

	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}

	public String getBrncode() {
		return brncode;
	}

	public void setBrncode(String brncode) {
		this.brncode = brncode;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public String getAccnm() {
		return accnm;
	}

	public void setAccnm(String accnm) {
		this.accnm = accnm;
	}

	public String getTaxcode() {
		return taxcode;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public String getStmtto() {
		return stmtto;
	}

	public void setStmtto(String stmtto) {
		this.stmtto = stmtto;
	}

	public String getStmtto_ho() {
		return stmtto_ho;
	}

	public void setStmtto_ho(String stmtto_ho) {
		this.stmtto_ho = stmtto_ho;
	}

	public String getPaymtype() {
		return paymtype;
	}

	public void setPaymtype(String paymtype) {
		this.paymtype = paymtype;
	}

	public String getPaymto() {
		return paymto;
	}

	public void setPaymto(String paymto) {
		this.paymto = paymto;
	}

	public String getPosflag() {
		return posflag;
	}

	public void setPosflag(String posflag) {
		this.posflag = posflag;
	}

	public String getVipflag() {
		return vipflag;
	}

	public void setVipflag(String vipflag) {
		this.vipflag = vipflag;
	}

	public String getMsc_charge() {
		return msc_charge;
	}

	public void setMsc_charge(String msc_charge) {
		this.msc_charge = msc_charge;
	}

	public String getMsc_calc() {
		return msc_calc;
	}

	public void setMsc_calc(String msc_calc) {
		this.msc_calc = msc_calc;
	}

	public String getMsc_table() {
		return msc_table;
	}

	public void setMsc_table(String msc_table) {
		this.msc_table = msc_table;
	}

	public String getMsc() {
		return msc;
	}

	public void setMsc(String msc) {
		this.msc = msc;
	}

	public String getContrno() {
		return contrno;
	}

	public void setContrno(String contrno) {
		this.contrno = contrno;
	}

	public String getContrdate() {
		return contrdate;
	}

	public void setContrdate(String contrdate) {
		this.contrdate = contrdate;
	}

	public String getContrsign() {
		return contrsign;
	}

	public void setContrsign(String contrsign) {
		this.contrsign = contrsign;
	}

	public String getContrcnx() {
		return contrcnx;
	}

	public void setContrcnx(String contrcnx) {
		this.contrcnx = contrcnx;
	}

	public String getOfficial() {
		return official;
	}

	public void setOfficial(String official) {
		this.official = official;
	}

	public String getMrchstat() {
		return mrchstat;
	}

	public void setMrchstat(String mrchstat) {
		this.mrchstat = mrchstat;
	}

	public String getRnc() {
		return rnc;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public String getTaxreg() {
		return taxreg;
	}

	public void setTaxreg(String taxreg) {
		this.taxreg = taxreg;
	}

	public String getRetenamt() {
		return retenamt;
	}

	public void setRetenamt(String retenamt) {
		this.retenamt = retenamt;
	}

	public String getRetenpc() {
		return retenpc;
	}

	public void setRetenpc(String retenpc) {
		this.retenpc = retenpc;
	}

	public String getZonegeog() {
		return zonegeog;
	}

	public void setZonegeog(String zonegeog) {
		this.zonegeog = zonegeog;
	}

	public String getZonecomer() {
		return zonecomer;
	}

	public void setZonecomer(String zonecomer) {
		this.zonecomer = zonecomer;
	}

	public String getZonepostal() {
		return zonepostal;
	}

	public void setZonepostal(String zonepostal) {
		this.zonepostal = zonepostal;
	}

	public String getUsrdata1() {
		return usrdata1;
	}

	public void setUsrdata1(String usrdata1) {
		this.usrdata1 = usrdata1;
	}

	public String getUsrdata2() {
		return usrdata2;
	}

	public void setUsrdata2(String usrdata2) {
		this.usrdata2 = usrdata2;
	}

	public String getUsrdata3() {
		return usrdata3;
	}

	public void setUsrdata3(String usrdata3) {
		this.usrdata3 = usrdata3;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCycbegin() {
		return cycbegin;
	}

	public void setCycbegin(String cycbegin) {
		this.cycbegin = cycbegin;
	}

	public String getCyclen() {
		return cyclen;
	}

	public void setCyclen(String cyclen) {
		this.cyclen = cyclen;
	}

	public String getConaccno() {
		return conaccno;
	}

	public void setConaccno(String conaccno) {
		this.conaccno = conaccno;
	}

	public String getNo_imprntrs() {
		return no_imprntrs;
	}

	public void setNo_imprntrs(String no_imprntrs) {
		this.no_imprntrs = no_imprntrs;
	}

	public String getConcur() {
		return concur;
	}

	public void setConcur(String concur) {
		this.concur = concur;
	}

	public String getPayoffset() {
		return payoffset;
	}

	public void setPayoffset(String payoffset) {
		this.payoffset = payoffset;
	}

	public String getAllowinst() {
		return allowinst;
	}

	public void setAllowinst(String allowinst) {
		this.allowinst = allowinst;
	}

	public String getPppymntattr() {
		return pppymntattr;
	}

	public void setPppymntattr(String pppymntattr) {
		this.pppymntattr = pppymntattr;
	}

	public String getCat_params() {
		return cat_params;
	}

	public void setCat_params(String cat_params) {
		this.cat_params = cat_params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}

	public String getStmtfreq() {
		return stmtfreq;
	}

	public void setStmtfreq(String stmtfreq) {
		this.stmtfreq = stmtfreq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}