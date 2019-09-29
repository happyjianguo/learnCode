package cn.yufu.cortex.entity;

import java.util.Date;

public class TermPosX {
    private Long id;

    private String posTel;

    private Long batchNo;

    private Date addDate;

    private String location;

    private Integer state;

    private Long cityNo;

    private Long province;

    private Long zone;

    private Long settleMrchAccId;

    private String termcode;

    private String timezone;

    private Long instId;

    private String mrchno;

    private String termStat;

    private String consumpCategory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosTel() {
        return posTel;
    }

    public void setPosTel(String posTel) {
        this.posTel = posTel == null ? null : posTel.trim();
    }

    public Long getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(Long batchNo) {
        this.batchNo = batchNo;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCityNo() {
        return cityNo;
    }

    public void setCityNo(Long cityNo) {
        this.cityNo = cityNo;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public Long getZone() {
        return zone;
    }

    public void setZone(Long zone) {
        this.zone = zone;
    }

    public Long getSettleMrchAccId() {
        return settleMrchAccId;
    }

    public void setSettleMrchAccId(Long settleMrchAccId) {
        this.settleMrchAccId = settleMrchAccId;
    }

    public String getTermcode() {
        return termcode;
    }

    public void setTermcode(String termcode) {
        this.termcode = termcode == null ? null : termcode.trim();
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone == null ? null : timezone.trim();
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

    public String getTermStat() {
        return termStat;
    }

    public void setTermStat(String termStat) {
        this.termStat = termStat == null ? null : termStat.trim();
    }

    public String getConsumpCategory() {
        return consumpCategory;
    }

    public void setConsumpCategory(String consumpCategory) {
        this.consumpCategory = consumpCategory == null ? null : consumpCategory.trim();
    }
}