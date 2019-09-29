package cn.yufu.posp.entity;

import java.util.Date;

public class EdcTerminalX {
    private String terminalId;

    private String upgradeDate;

    private String upgradeVersion;

    private String storeContacts;

    private String storePhone;

    private String expandAdviser;

    private String maintainAdviser;

    private String merchantAdvisor;

    private String terminalArea;

    private String terminalProvince;

    private String terminalCity;

    private String terminalZone;

    private String terminalPosition;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    private Object remarks;

    private String delFlag;

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId == null ? null : terminalId.trim();
    }

    public String getUpgradeDate() {
        return upgradeDate;
    }

    public void setUpgradeDate(String upgradeDate) {
        this.upgradeDate = upgradeDate == null ? null : upgradeDate.trim();
    }

    public String getUpgradeVersion() {
        return upgradeVersion;
    }

    public void setUpgradeVersion(String upgradeVersion) {
        this.upgradeVersion = upgradeVersion == null ? null : upgradeVersion.trim();
    }

    public String getStoreContacts() {
        return storeContacts;
    }

    public void setStoreContacts(String storeContacts) {
        this.storeContacts = storeContacts == null ? null : storeContacts.trim();
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    public String getExpandAdviser() {
        return expandAdviser;
    }

    public void setExpandAdviser(String expandAdviser) {
        this.expandAdviser = expandAdviser == null ? null : expandAdviser.trim();
    }

    public String getMaintainAdviser() {
        return maintainAdviser;
    }

    public void setMaintainAdviser(String maintainAdviser) {
        this.maintainAdviser = maintainAdviser == null ? null : maintainAdviser.trim();
    }

    public String getMerchantAdvisor() {
        return merchantAdvisor;
    }

    public void setMerchantAdvisor(String merchantAdvisor) {
        this.merchantAdvisor = merchantAdvisor == null ? null : merchantAdvisor.trim();
    }

    public String getTerminalArea() {
        return terminalArea;
    }

    public void setTerminalArea(String terminalArea) {
        this.terminalArea = terminalArea == null ? null : terminalArea.trim();
    }

    public String getTerminalProvince() {
        return terminalProvince;
    }

    public void setTerminalProvince(String terminalProvince) {
        this.terminalProvince = terminalProvince == null ? null : terminalProvince.trim();
    }

    public String getTerminalCity() {
        return terminalCity;
    }

    public void setTerminalCity(String terminalCity) {
        this.terminalCity = terminalCity == null ? null : terminalCity.trim();
    }

    public String getTerminalZone() {
        return terminalZone;
    }

    public void setTerminalZone(String terminalZone) {
        this.terminalZone = terminalZone == null ? null : terminalZone.trim();
    }

    public String getTerminalPosition() {
        return terminalPosition;
    }

    public void setTerminalPosition(String terminalPosition) {
        this.terminalPosition = terminalPosition == null ? null : terminalPosition.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Object getRemarks() {
        return remarks;
    }

    public void setRemarks(Object remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }

	@Override
	public String toString() {
		return "EdcTerminalX [terminalId=" + terminalId + ", upgradeDate=" + upgradeDate + ", upgradeVersion="
				+ upgradeVersion + ", storeContacts=" + storeContacts + ", storePhone=" + storePhone
				+ ", expandAdviser=" + expandAdviser + ", maintainAdviser=" + maintainAdviser + ", merchantAdvisor="
				+ merchantAdvisor + ", terminalArea=" + terminalArea + ", terminalProvince=" + terminalProvince
				+ ", terminalCity=" + terminalCity + ", terminalZone=" + terminalZone + ", terminalPosition="
				+ terminalPosition + ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", remarks=" + remarks + ", delFlag=" + delFlag + "]";
	}
}