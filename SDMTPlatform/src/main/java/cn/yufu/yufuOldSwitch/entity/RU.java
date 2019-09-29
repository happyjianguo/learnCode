package cn.yufu.yufuOldSwitch.entity;

import java.util.Date;

public class RU {
    private String lruid;

    private String sru;

    private String scontact;

    private String sphone;

    private String saddr1;

    private String scode;

    private Date dtime;

    private String passwd;

    private String sta;

    private String traffic;

    private String guidepost;

    private String picture;

    private Integer cityid;

    private Integer ruflag;

    private Integer zshflag;

    private Integer fid;

    private Integer rutype;

    private String explanation;

    public String getLruid() {
        return lruid;
    }

    public void setLruid(String lruid) {
        this.lruid = lruid == null ? null : lruid.trim();
    }

    public String getSru() {
        return sru;
    }

    public void setSru(String sru) {
        this.sru = sru == null ? null : sru.trim();
    }

    public String getScontact() {
        return scontact;
    }

    public void setScontact(String scontact) {
        this.scontact = scontact == null ? null : scontact.trim();
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone == null ? null : sphone.trim();
    }

    public String getSaddr1() {
        return saddr1;
    }

    public void setSaddr1(String saddr1) {
        this.saddr1 = saddr1 == null ? null : saddr1.trim();
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode == null ? null : scode.trim();
    }

    public Date getDtime() {
        return dtime;
    }

    public void setDtime(Date dtime) {
        this.dtime = dtime;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta == null ? null : sta.trim();
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic == null ? null : traffic.trim();
    }

    public String getGuidepost() {
        return guidepost;
    }

    public void setGuidepost(String guidepost) {
        this.guidepost = guidepost == null ? null : guidepost.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getRuflag() {
        return ruflag;
    }

    public void setRuflag(Integer ruflag) {
        this.ruflag = ruflag;
    }

    public Integer getZshflag() {
        return zshflag;
    }

    public void setZshflag(Integer zshflag) {
        this.zshflag = zshflag;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public Integer getRutype() {
        return rutype;
    }

    public void setRutype(Integer rutype) {
        this.rutype = rutype;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation == null ? null : explanation.trim();
    }
}