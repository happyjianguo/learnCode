package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class FukaCompanys {
    private BigDecimal cid;

    private BigDecimal parentid;

    private BigDecimal layer;

    private String parentidlist;

    private String companyName;

    private BigDecimal status;

    private BigDecimal subcompanycount;

    private String freeField1;

    private String freeField2;

    private String freeField3;

    private String freeField4;

    private String freeField5;

    public BigDecimal getCid() {
        return cid;
    }

    public void setCid(BigDecimal cid) {
        this.cid = cid;
    }

    public BigDecimal getParentid() {
        return parentid;
    }

    public void setParentid(BigDecimal parentid) {
        this.parentid = parentid;
    }

    public BigDecimal getLayer() {
        return layer;
    }

    public void setLayer(BigDecimal layer) {
        this.layer = layer;
    }

    public String getParentidlist() {
        return parentidlist;
    }

    public void setParentidlist(String parentidlist) {
        this.parentidlist = parentidlist == null ? null : parentidlist.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public BigDecimal getSubcompanycount() {
        return subcompanycount;
    }

    public void setSubcompanycount(BigDecimal subcompanycount) {
        this.subcompanycount = subcompanycount;
    }

    public String getFreeField1() {
        return freeField1;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1 == null ? null : freeField1.trim();
    }

    public String getFreeField2() {
        return freeField2;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2 == null ? null : freeField2.trim();
    }

    public String getFreeField3() {
        return freeField3;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3 == null ? null : freeField3.trim();
    }

    public String getFreeField4() {
        return freeField4;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4 == null ? null : freeField4.trim();
    }

    public String getFreeField5() {
        return freeField5;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5 == null ? null : freeField5.trim();
    }
}