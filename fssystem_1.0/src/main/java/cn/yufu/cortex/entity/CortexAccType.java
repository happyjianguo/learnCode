package cn.yufu.cortex.entity;

import java.math.BigDecimal;

public class CortexAccType extends CortexAccTypeKey {
    private Long vernoCtx;

    private Integer classid;

    private Integer isocode;

    private String currcode;

    private String currcode2;

    private Integer srvid;

    private Integer fmtid;

    private String descr;

    private String bankacccode;

    private Integer now;

    private Integer acclen;

    private String allowsvc;

    private String catParams;

    private Integer brktype;

    private Long brkoffset;

    private Long catAcctmrkupId;

    private Long acclogging;

    private BigDecimal maxovrrdamt;

    private Double maxovrrdcrlmprcnt;

    private Long catAcctriskId;

    private Integer genaccnum;

    private Long acctypeseq;

    private String accnumfrmt;

    public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Integer getIsocode() {
        return isocode;
    }

    public void setIsocode(Integer isocode) {
        this.isocode = isocode;
    }

    public String getCurrcode() {
        return currcode;
    }

    public void setCurrcode(String currcode) {
        this.currcode = currcode == null ? null : currcode.trim();
    }

    public String getCurrcode2() {
        return currcode2;
    }

    public void setCurrcode2(String currcode2) {
        this.currcode2 = currcode2 == null ? null : currcode2.trim();
    }

    public Integer getSrvid() {
        return srvid;
    }

    public void setSrvid(Integer srvid) {
        this.srvid = srvid;
    }

    public Integer getFmtid() {
        return fmtid;
    }

    public void setFmtid(Integer fmtid) {
        this.fmtid = fmtid;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getBankacccode() {
        return bankacccode;
    }

    public void setBankacccode(String bankacccode) {
        this.bankacccode = bankacccode == null ? null : bankacccode.trim();
    }

    public Integer getNow() {
        return now;
    }

    public void setNow(Integer now) {
        this.now = now;
    }

    public Integer getAcclen() {
        return acclen;
    }

    public void setAcclen(Integer acclen) {
        this.acclen = acclen;
    }

    public String getAllowsvc() {
        return allowsvc;
    }

    public void setAllowsvc(String allowsvc) {
        this.allowsvc = allowsvc == null ? null : allowsvc.trim();
    }

    public String getCatParams() {
        return catParams;
    }

    public void setCatParams(String catParams) {
        this.catParams = catParams == null ? null : catParams.trim();
    }

    public Integer getBrktype() {
        return brktype;
    }

    public void setBrktype(Integer brktype) {
        this.brktype = brktype;
    }

    public Long getBrkoffset() {
        return brkoffset;
    }

    public void setBrkoffset(Long brkoffset) {
        this.brkoffset = brkoffset;
    }

    public Long getCatAcctmrkupId() {
        return catAcctmrkupId;
    }

    public void setCatAcctmrkupId(Long catAcctmrkupId) {
        this.catAcctmrkupId = catAcctmrkupId;
    }

    public Long getAcclogging() {
        return acclogging;
    }

    public void setAcclogging(Long acclogging) {
        this.acclogging = acclogging;
    }

    public BigDecimal getMaxovrrdamt() {
        return maxovrrdamt;
    }

    public void setMaxovrrdamt(BigDecimal maxovrrdamt) {
        this.maxovrrdamt = maxovrrdamt;
    }

    public Double getMaxovrrdcrlmprcnt() {
        return maxovrrdcrlmprcnt;
    }

    public void setMaxovrrdcrlmprcnt(Double maxovrrdcrlmprcnt) {
        this.maxovrrdcrlmprcnt = maxovrrdcrlmprcnt;
    }

    public Long getCatAcctriskId() {
        return catAcctriskId;
    }

    public void setCatAcctriskId(Long catAcctriskId) {
        this.catAcctriskId = catAcctriskId;
    }

    public Integer getGenaccnum() {
        return genaccnum;
    }

    public void setGenaccnum(Integer genaccnum) {
        this.genaccnum = genaccnum;
    }

    public Long getAcctypeseq() {
        return acctypeseq;
    }

    public void setAcctypeseq(Long acctypeseq) {
        this.acctypeseq = acctypeseq;
    }

    public String getAccnumfrmt() {
        return accnumfrmt;
    }

    public void setAccnumfrmt(String accnumfrmt) {
        this.accnumfrmt = accnumfrmt == null ? null : accnumfrmt.trim();
    }
}