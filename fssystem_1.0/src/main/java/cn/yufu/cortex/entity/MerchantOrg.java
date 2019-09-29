package cn.yufu.cortex.entity;

import java.math.BigDecimal;

public class MerchantOrg {
    private String orgId;

    private String orgName;

    private BigDecimal orgRatio;

    private BigDecimal orgSingleAmt;

    private String orgStat;

    private String reservedField;

    private String reservedField1;

    private String reservedField2;

    private String createBy;

    private String createDate;

    private String updateBy;

    private String updateDate;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public BigDecimal getOrgRatio() {
        return orgRatio;
    }

    public void setOrgRatio(BigDecimal orgRatio) {
        this.orgRatio = orgRatio;
    }

    public BigDecimal getOrgSingleAmt() {
        return orgSingleAmt;
    }

    public void setOrgSingleAmt(BigDecimal orgSingleAmt) {
        this.orgSingleAmt = orgSingleAmt;
    }

    public String getOrgStat() {
        return orgStat;
    }

    public void setOrgStat(String orgStat) {
        this.orgStat = orgStat == null ? null : orgStat.trim();
    }

    public String getReservedField() {
        return reservedField;
    }

    public void setReservedField(String reservedField) {
        this.reservedField = reservedField == null ? null : reservedField.trim();
    }

    public String getReservedField1() {
        return reservedField1;
    }

    public void setReservedField1(String reservedField1) {
        this.reservedField1 = reservedField1 == null ? null : reservedField1.trim();
    }

    public String getReservedField2() {
        return reservedField2;
    }

    public void setReservedField2(String reservedField2) {
        this.reservedField2 = reservedField2 == null ? null : reservedField2.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }
}