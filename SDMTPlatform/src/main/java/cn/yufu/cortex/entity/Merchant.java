package cn.yufu.cortex.entity;

import java.util.Date;

public class Merchant {
    private Long id;

    private Long instId;

    private String mrchno;

    private String headOffice;

    private String name;

    private String tradingAs;

    private String address1;

    private String address2;

    private String city;

    private String state;

    private String postcode;

    private String countrycode;

    private String phyAddress1;

    private String phyAddress2;

    private String phyCity;

    private String phyState;

    private String phyPostcode;

    private String phyCountrycode;

    private String regAddress1;

    private String regAddress2;

    private String regCity;

    private String regState;

    private String regPostcode;

    private String regCountrycode;

    private String currcode;

    private Integer mrchtype;

    private Integer acptbus;

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

    private String stmtfreq;

    private Integer stmtto;

    private Integer stmttoHo;

    private String paymtype;

    private String paymto;

    private String posflag;

    private String vipflag;

    private String mscCharge;

    private String mscCalc;

    private Integer mscTable;

    private Double msc;

    private String contrno;

    private Date contrdate;

    private Date contrcnx;

    private String contrsign;

    private String official;

    private Integer mrchstat;

    private String rnc;

    private String taxreg;

    private Double retenamt;

    private Double retenpc;

    private String zonegeog;

    private String zonecomer;

    private String zonepostal;

    private String usrdata1;

    private String usrdata2;

    private String usrdata3;

    private Date cycbegin;

    private Integer cyclen;

    private Integer noImprntrs;

    private String conaccno;

    private String concur;

    private Integer payoffset;

    private Integer allowinst;

    private Integer pppymntattr;

    private String catParams;

    private Long vernoCtx;

    private String memo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(String headOffice) {
        this.headOffice = headOffice == null ? null : headOffice.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTradingAs() {
        return tradingAs;
    }

    public void setTradingAs(String tradingAs) {
        this.tradingAs = tradingAs == null ? null : tradingAs;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode == null ? null : countrycode.trim();
    }

    public String getPhyAddress1() {
        return phyAddress1;
    }

    public void setPhyAddress1(String phyAddress1) {
        this.phyAddress1 = phyAddress1 == null ? null : phyAddress1.trim();
    }

    public String getPhyAddress2() {
        return phyAddress2;
    }

    public void setPhyAddress2(String phyAddress2) {
        this.phyAddress2 = phyAddress2 == null ? null : phyAddress2.trim();
    }

    public String getPhyCity() {
        return phyCity;
    }

    public void setPhyCity(String phyCity) {
        this.phyCity = phyCity == null ? null : phyCity.trim();
    }

    public String getPhyState() {
        return phyState;
    }

    public void setPhyState(String phyState) {
        this.phyState = phyState == null ? null : phyState.trim();
    }

    public String getPhyPostcode() {
        return phyPostcode;
    }

    public void setPhyPostcode(String phyPostcode) {
        this.phyPostcode = phyPostcode == null ? null : phyPostcode.trim();
    }

    public String getPhyCountrycode() {
        return phyCountrycode;
    }

    public void setPhyCountrycode(String phyCountrycode) {
        this.phyCountrycode = phyCountrycode == null ? null : phyCountrycode.trim();
    }

    public String getRegAddress1() {
        return regAddress1;
    }

    public void setRegAddress1(String regAddress1) {
        this.regAddress1 = regAddress1 == null ? null : regAddress1.trim();
    }

    public String getRegAddress2() {
        return regAddress2;
    }

    public void setRegAddress2(String regAddress2) {
        this.regAddress2 = regAddress2 == null ? null : regAddress2.trim();
    }

    public String getRegCity() {
        return regCity;
    }

    public void setRegCity(String regCity) {
        this.regCity = regCity == null ? null : regCity.trim();
    }

    public String getRegState() {
        return regState;
    }

    public void setRegState(String regState) {
        this.regState = regState == null ? null : regState.trim();
    }

    public String getRegPostcode() {
        return regPostcode;
    }

    public void setRegPostcode(String regPostcode) {
        this.regPostcode = regPostcode == null ? null : regPostcode.trim();
    }

    public String getRegCountrycode() {
        return regCountrycode;
    }

    public void setRegCountrycode(String regCountrycode) {
        this.regCountrycode = regCountrycode == null ? null : regCountrycode.trim();
    }

    public String getCurrcode() {
        return currcode;
    }

    public void setCurrcode(String currcode) {
        this.currcode = currcode == null ? null : currcode.trim();
    }

    public Integer getMrchtype() {
        return mrchtype;
    }

    public void setMrchtype(Integer mrchtype) {
        this.mrchtype = mrchtype;
    }

    public Integer getAcptbus() {
        return acptbus;
    }

    public void setAcptbus(Integer acptbus) {
        this.acptbus = acptbus;
    }

    public String getContact1() {
        return contact1;
    }

    public void setContact1(String contact1) {
        this.contact1 = contact1 == null ? null : contact1.trim();
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2 == null ? null : contact2.trim();
    }

    public String getContact3() {
        return contact3;
    }

    public void setContact3(String contact3) {
        this.contact3 = contact3 == null ? null : contact3.trim();
    }

    public String getTelno1() {
        return telno1;
    }

    public void setTelno1(String telno1) {
        this.telno1 = telno1 == null ? null : telno1.trim();
    }

    public String getTelno2() {
        return telno2;
    }

    public void setTelno2(String telno2) {
        this.telno2 = telno2 == null ? null : telno2.trim();
    }

    public String getTelno3() {
        return telno3;
    }

    public void setTelno3(String telno3) {
        this.telno3 = telno3 == null ? null : telno3.trim();
    }

    public String getFaxno() {
        return faxno;
    }

    public void setFaxno(String faxno) {
        this.faxno = faxno == null ? null : faxno.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelex() {
        return telex;
    }

    public void setTelex(String telex) {
        this.telex = telex == null ? null : telex.trim();
    }

    public String getSortcode() {
        return sortcode;
    }

    public void setSortcode(String sortcode) {
        this.sortcode = sortcode == null ? null : sortcode.trim();
    }

    public String getBrncode() {
        return brncode;
    }

    public void setBrncode(String brncode) {
        this.brncode = brncode == null ? null : brncode.trim();
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno == null ? null : accno.trim();
    }

    public String getAccnm() {
        return accnm;
    }

    public void setAccnm(String accnm) {
        this.accnm = accnm == null ? null : accnm.trim();
    }

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode == null ? null : taxcode.trim();
    }

    public String getStmtfreq() {
        return stmtfreq;
    }

    public void setStmtfreq(String stmtfreq) {
        this.stmtfreq = stmtfreq == null ? null : stmtfreq.trim();
    }

    public Integer getStmtto() {
        return stmtto;
    }

    public void setStmtto(Integer stmtto) {
        this.stmtto = stmtto;
    }

    public Integer getStmttoHo() {
        return stmttoHo;
    }

    public void setStmttoHo(Integer stmttoHo) {
        this.stmttoHo = stmttoHo;
    }

    public String getPaymtype() {
        return paymtype;
    }

    public void setPaymtype(String paymtype) {
        this.paymtype = paymtype == null ? null : paymtype.trim();
    }

    public String getPaymto() {
        return paymto;
    }

    public void setPaymto(String paymto) {
        this.paymto = paymto == null ? null : paymto.trim();
    }

    public String getPosflag() {
        return posflag;
    }

    public void setPosflag(String posflag) {
        this.posflag = posflag == null ? null : posflag.trim();
    }

    public String getVipflag() {
        return vipflag;
    }

    public void setVipflag(String vipflag) {
        this.vipflag = vipflag == null ? null : vipflag.trim();
    }

    public String getMscCharge() {
        return mscCharge;
    }

    public void setMscCharge(String mscCharge) {
        this.mscCharge = mscCharge == null ? null : mscCharge.trim();
    }

    public String getMscCalc() {
        return mscCalc;
    }

    public void setMscCalc(String mscCalc) {
        this.mscCalc = mscCalc == null ? null : mscCalc.trim();
    }

    public Integer getMscTable() {
        return mscTable;
    }

    public void setMscTable(Integer mscTable) {
        this.mscTable = mscTable;
    }

    public Double getMsc() {
        return msc;
    }

    public void setMsc(Double msc) {
        this.msc = msc;
    }

    public String getContrno() {
        return contrno;
    }

    public void setContrno(String contrno) {
        this.contrno = contrno == null ? null : contrno.trim();
    }

    public Date getContrdate() {
        return contrdate;
    }

    public void setContrdate(Date contrdate) {
        this.contrdate = contrdate;
    }

    public Date getContrcnx() {
        return contrcnx;
    }

    public void setContrcnx(Date contrcnx) {
        this.contrcnx = contrcnx;
    }

    public String getContrsign() {
        return contrsign;
    }

    public void setContrsign(String contrsign) {
        this.contrsign = contrsign == null ? null : contrsign.trim();
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official == null ? null : official.trim();
    }

    public Integer getMrchstat() {
        return mrchstat;
    }

    public void setMrchstat(Integer mrchstat) {
        this.mrchstat = mrchstat;
    }

    public String getRnc() {
        return rnc;
    }

    public void setRnc(String rnc) {
        this.rnc = rnc == null ? null : rnc.trim();
    }

    public String getTaxreg() {
        return taxreg;
    }

    public void setTaxreg(String taxreg) {
        this.taxreg = taxreg == null ? null : taxreg.trim();
    }

    public Double getRetenamt() {
        return retenamt;
    }

    public void setRetenamt(Double retenamt) {
        this.retenamt = retenamt;
    }

    public Double getRetenpc() {
        return retenpc;
    }

    public void setRetenpc(Double retenpc) {
        this.retenpc = retenpc;
    }

    public String getZonegeog() {
        return zonegeog;
    }

    public void setZonegeog(String zonegeog) {
        this.zonegeog = zonegeog == null ? null : zonegeog.trim();
    }

    public String getZonecomer() {
        return zonecomer;
    }

    public void setZonecomer(String zonecomer) {
        this.zonecomer = zonecomer == null ? null : zonecomer.trim();
    }

    public String getZonepostal() {
        return zonepostal;
    }

    public void setZonepostal(String zonepostal) {
        this.zonepostal = zonepostal == null ? null : zonepostal.trim();
    }

    public String getUsrdata1() {
        return usrdata1;
    }

    public void setUsrdata1(String usrdata1) {
        this.usrdata1 = usrdata1 == null ? null : usrdata1.trim();
    }

    public String getUsrdata2() {
        return usrdata2;
    }

    public void setUsrdata2(String usrdata2) {
        this.usrdata2 = usrdata2 == null ? null : usrdata2.trim();
    }

    public String getUsrdata3() {
        return usrdata3;
    }

    public void setUsrdata3(String usrdata3) {
        this.usrdata3 = usrdata3 == null ? null : usrdata3.trim();
    }

    public Date getCycbegin() {
        return cycbegin;
    }

    public void setCycbegin(Date cycbegin) {
        this.cycbegin = cycbegin;
    }

    public Integer getCyclen() {
        return cyclen;
    }

    public void setCyclen(Integer cyclen) {
        this.cyclen = cyclen;
    }

    public Integer getNoImprntrs() {
        return noImprntrs;
    }

    public void setNoImprntrs(Integer noImprntrs) {
        this.noImprntrs = noImprntrs;
    }

    public String getConaccno() {
        return conaccno;
    }

    public void setConaccno(String conaccno) {
        this.conaccno = conaccno == null ? null : conaccno.trim();
    }

    public String getConcur() {
        return concur;
    }

    public void setConcur(String concur) {
        this.concur = concur == null ? null : concur.trim();
    }

    public Integer getPayoffset() {
        return payoffset;
    }

    public void setPayoffset(Integer payoffset) {
        this.payoffset = payoffset;
    }

    public Integer getAllowinst() {
        return allowinst;
    }

    public void setAllowinst(Integer allowinst) {
        this.allowinst = allowinst;
    }

    public Integer getPppymntattr() {
        return pppymntattr;
    }

    public void setPppymntattr(Integer pppymntattr) {
        this.pppymntattr = pppymntattr;
    }

    public String getCatParams() {
        return catParams;
    }

    public void setCatParams(String catParams) {
        this.catParams = catParams == null ? null : catParams.trim();
    }

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}