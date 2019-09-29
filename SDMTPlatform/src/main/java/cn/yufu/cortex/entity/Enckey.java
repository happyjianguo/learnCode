package cn.yufu.cortex.entity;

import java.util.Date;

public class Enckey {
    private Long id;

    private Long vernoCtx;

    private Integer keytype;

    private Integer keystatus;

    private Integer tstflag;

    private String refcode;

    private Integer keyseqno;

    private String keyvalue;

    private String prevalue;

    private String checkvalue;

    private Date actdate;

    private Long acttime;

    private Date expiry;

    private String longkeyval;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public Integer getKeytype() {
        return keytype;
    }

    public void setKeytype(Integer keytype) {
        this.keytype = keytype;
    }

    public Integer getKeystatus() {
        return keystatus;
    }

    public void setKeystatus(Integer keystatus) {
        this.keystatus = keystatus;
    }

    public Integer getTstflag() {
        return tstflag;
    }

    public void setTstflag(Integer tstflag) {
        this.tstflag = tstflag;
    }

    public String getRefcode() {
        return refcode;
    }

    public void setRefcode(String refcode) {
        this.refcode = refcode == null ? null : refcode.trim();
    }

    public Integer getKeyseqno() {
        return keyseqno;
    }

    public void setKeyseqno(Integer keyseqno) {
        this.keyseqno = keyseqno;
    }

    public String getKeyvalue() {
        return keyvalue;
    }

    public void setKeyvalue(String keyvalue) {
        this.keyvalue = keyvalue == null ? null : keyvalue.trim();
    }

    public String getPrevalue() {
        return prevalue;
    }

    public void setPrevalue(String prevalue) {
        this.prevalue = prevalue == null ? null : prevalue;
    }

    public String getCheckvalue() {
        return checkvalue;
    }

    public void setCheckvalue(String checkvalue) {
        this.checkvalue = checkvalue == null ? null : checkvalue.trim();
    }

    public Date getActdate() {
        return actdate;
    }

    public void setActdate(Date actdate) {
        this.actdate = actdate;
    }

    public Long getActtime() {
        return acttime;
    }

    public void setActtime(Long acttime) {
        this.acttime = acttime;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getLongkeyval() {
        return longkeyval;
    }

    public void setLongkeyval(String longkeyval) {
        this.longkeyval = longkeyval == null ? null : longkeyval;
    }
}