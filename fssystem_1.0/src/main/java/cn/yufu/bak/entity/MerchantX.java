package cn.yufu.bak.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantX {
    private BigDecimal instId;

    private String mrchno;

    private String mrchtName;

    private String address;

    private BigDecimal addDate;

    private BigDecimal cityNo;

    private BigDecimal province;

    private BigDecimal zone;

    private BigDecimal state;

    private BigDecimal typeYf;

    private String agent;

    private BigDecimal idType;

    private String idNo;

    private Date idValidity;

    private String busLicNo;

    private Date busLicValidity;

    private String taxId;

    private Date taxIdValidity;

    private String orgId;

    private Date orgValidity;

    private String telno1;

    private String postcode;

    private String contact3;

    private String accno;

    private BigDecimal merchantId;

    private String busLicPic;

    private String taxIdPic;

    private String orgIdPic;

    private String legalRep;

    private BigDecimal lrIdType;

    private String lrIdNo;

    private Date lrIdValidity;
    
    private String mrchstat; //商户状态，来自备库
    
    public BigDecimal getInstId() {
        return instId;
    }

    public void setInstId(BigDecimal instId) {
        this.instId = instId;
    }

    public String getMrchno() {
        return mrchno;
    }

    public void setMrchno(String mrchno) {
        this.mrchno = mrchno == null ? null : mrchno.trim();
    }

    public String getMrchtName() {
        return mrchtName;
    }

    public void setMrchtName(String mrchtName) {
        this.mrchtName = mrchtName == null ? null : mrchtName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getAddDate() {
        return addDate;
    }

    public void setAddDate(BigDecimal addDate) {
        this.addDate = addDate;
    }

    public BigDecimal getCityNo() {
        return cityNo;
    }

    public void setCityNo(BigDecimal cityNo) {
        this.cityNo = cityNo;
    }

    public BigDecimal getProvince() {
        return province;
    }

    public void setProvince(BigDecimal province) {
        this.province = province;
    }

    public BigDecimal getZone() {
        return zone;
    }

    public void setZone(BigDecimal zone) {
        this.zone = zone;
    }

    public BigDecimal getState() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }

    public BigDecimal getTypeYf() {
        return typeYf;
    }

    public void setTypeYf(BigDecimal typeYf) {
        this.typeYf = typeYf;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent == null ? null : agent.trim();
    }

    public BigDecimal getIdType() {
        return idType;
    }

    public void setIdType(BigDecimal idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public Date getIdValidity() {
        return idValidity;
    }

    public void setIdValidity(Date idValidity) {
        this.idValidity = idValidity;
    }

    public String getBusLicNo() {
        return busLicNo;
    }

    public void setBusLicNo(String busLicNo) {
        this.busLicNo = busLicNo == null ? null : busLicNo.trim();
    }

    public Date getBusLicValidity() {
        return busLicValidity;
    }

    public void setBusLicValidity(Date busLicValidity) {
        this.busLicValidity = busLicValidity;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId == null ? null : taxId.trim();
    }

    public Date getTaxIdValidity() {
        return taxIdValidity;
    }

    public void setTaxIdValidity(Date taxIdValidity) {
        this.taxIdValidity = taxIdValidity;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Date getOrgValidity() {
        return orgValidity;
    }

    public void setOrgValidity(Date orgValidity) {
        this.orgValidity = orgValidity;
    }

    public String getTelno1() {
        return telno1;
    }

    public void setTelno1(String telno1) {
        this.telno1 = telno1 == null ? null : telno1.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getContact3() {
        return contact3;
    }

    public void setContact3(String contact3) {
        this.contact3 = contact3 == null ? null : contact3.trim();
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno == null ? null : accno.trim();
    }

    public BigDecimal getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(BigDecimal merchantId) {
        this.merchantId = merchantId;
    }

    public String getBusLicPic() {
        return busLicPic;
    }

    public void setBusLicPic(String busLicPic) {
        this.busLicPic = busLicPic == null ? null : busLicPic.trim();
    }

    public String getTaxIdPic() {
        return taxIdPic;
    }

    public void setTaxIdPic(String taxIdPic) {
        this.taxIdPic = taxIdPic == null ? null : taxIdPic.trim();
    }

    public String getOrgIdPic() {
        return orgIdPic;
    }

    public void setOrgIdPic(String orgIdPic) {
        this.orgIdPic = orgIdPic == null ? null : orgIdPic.trim();
    }

    public String getLegalRep() {
        return legalRep;
    }

    public void setLegalRep(String legalRep) {
        this.legalRep = legalRep == null ? null : legalRep.trim();
    }

    public BigDecimal getLrIdType() {
        return lrIdType;
    }

    public void setLrIdType(BigDecimal lrIdType) {
        this.lrIdType = lrIdType;
    }

    public String getLrIdNo() {
        return lrIdNo;
    }

    public void setLrIdNo(String lrIdNo) {
        this.lrIdNo = lrIdNo == null ? null : lrIdNo.trim();
    }

    public Date getLrIdValidity() {
        return lrIdValidity;
    }

    public void setLrIdValidity(Date lrIdValidity) {
        this.lrIdValidity = lrIdValidity;
    }

	public String getMrchstat() {
		return mrchstat;
	}

	public void setMrchstat(String mrchstat) {
		this.mrchstat = mrchstat;
	}

}