package cn.yufu.yufuOld2.entity;

import java.util.Date;

public class TerminalYufuOld {
    private String sserialnum;

    private String ttype;

    private String commode;

    private String manuf;

    private String tel;

    private String ref;

    private String invnum;

    private String mainm;

    private String lruid;

    private Date employdate;

    private String batchno;

    private String comalg;

    private String tpdu;

    private Integer termid;

    private Integer comid;

    private String teraddress;

    private Integer bstate;

    private String pinKey;

    private String pinCheckvalue;

    private String workingKey;

    private String workingCheckvalue;

    private Integer accountsflag;

    private Date accountsdate;

    private String sserialnumpassword;

    private Integer cardareaid;

    private Integer cardprefectureid;

    private String shihuashopid;

    private Integer rugroupid;

    private Date stoptime;

    private Integer cardcityid;

    public String getSserialnum() {
        return sserialnum;
    }

    public void setSserialnum(String sserialnum) {
        this.sserialnum = sserialnum == null ? null : sserialnum.trim();
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype == null ? null : ttype.trim();
    }

    public String getCommode() {
        return commode;
    }

    public void setCommode(String commode) {
        this.commode = commode == null ? null : commode.trim();
    }

    public String getManuf() {
        return manuf;
    }

    public void setManuf(String manuf) {
        this.manuf = manuf == null ? null : manuf.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref == null ? null : ref.trim();
    }

    public String getInvnum() {
        return invnum;
    }

    public void setInvnum(String invnum) {
        this.invnum = invnum == null ? null : invnum.trim();
    }

    public String getMainm() {
        return mainm;
    }

    public void setMainm(String mainm) {
        this.mainm = mainm == null ? null : mainm.trim();
    }

    public String getLruid() {
        return lruid;
    }

    public void setLruid(String lruid) {
        this.lruid = lruid == null ? null : lruid.trim();
    }

    public Date getEmploydate() {
        return employdate;
    }

    public void setEmploydate(Date employdate) {
        this.employdate = employdate;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno == null ? null : batchno.trim();
    }

    public String getComalg() {
        return comalg;
    }

    public void setComalg(String comalg) {
        this.comalg = comalg == null ? null : comalg.trim();
    }

    public String getTpdu() {
        return tpdu;
    }

    public void setTpdu(String tpdu) {
        this.tpdu = tpdu == null ? null : tpdu.trim();
    }

    public Integer getTermid() {
        return termid;
    }

    public void setTermid(Integer termid) {
        this.termid = termid;
    }

    public Integer getComid() {
        return comid;
    }

    public void setComid(Integer comid) {
        this.comid = comid;
    }

    public String getTeraddress() {
        return teraddress;
    }

    public void setTeraddress(String teraddress) {
        this.teraddress = teraddress == null ? null : teraddress.trim();
    }

    public Integer getBstate() {
        return bstate;
    }

    public void setBstate(Integer bstate) {
        this.bstate = bstate;
    }

    public String getPinKey() {
        return pinKey;
    }

    public void setPinKey(String pinKey) {
        this.pinKey = pinKey == null ? null : pinKey.trim();
    }

    public String getPinCheckvalue() {
        return pinCheckvalue;
    }

    public void setPinCheckvalue(String pinCheckvalue) {
        this.pinCheckvalue = pinCheckvalue == null ? null : pinCheckvalue.trim();
    }

    public String getWorkingKey() {
        return workingKey;
    }

    public void setWorkingKey(String workingKey) {
        this.workingKey = workingKey == null ? null : workingKey.trim();
    }

    public String getWorkingCheckvalue() {
        return workingCheckvalue;
    }

    public void setWorkingCheckvalue(String workingCheckvalue) {
        this.workingCheckvalue = workingCheckvalue == null ? null : workingCheckvalue.trim();
    }

    public Integer getAccountsflag() {
        return accountsflag;
    }

    public void setAccountsflag(Integer accountsflag) {
        this.accountsflag = accountsflag;
    }

    public Date getAccountsdate() {
        return accountsdate;
    }

    public void setAccountsdate(Date accountsdate) {
        this.accountsdate = accountsdate;
    }

    public String getSserialnumpassword() {
        return sserialnumpassword;
    }

    public void setSserialnumpassword(String sserialnumpassword) {
        this.sserialnumpassword = sserialnumpassword == null ? null : sserialnumpassword.trim();
    }

    public Integer getCardareaid() {
        return cardareaid;
    }

    public void setCardareaid(Integer cardareaid) {
        this.cardareaid = cardareaid;
    }

    public Integer getCardprefectureid() {
        return cardprefectureid;
    }

    public void setCardprefectureid(Integer cardprefectureid) {
        this.cardprefectureid = cardprefectureid;
    }

    public String getShihuashopid() {
        return shihuashopid;
    }

    public void setShihuashopid(String shihuashopid) {
        this.shihuashopid = shihuashopid == null ? null : shihuashopid.trim();
    }

    public Integer getRugroupid() {
        return rugroupid;
    }

    public void setRugroupid(Integer rugroupid) {
        this.rugroupid = rugroupid;
    }

    public Date getStoptime() {
        return stoptime;
    }

    public void setStoptime(Date stoptime) {
        this.stoptime = stoptime;
    }

    public Integer getCardcityid() {
        return cardcityid;
    }

    public void setCardcityid(Integer cardcityid) {
        this.cardcityid = cardcityid;
    }
}