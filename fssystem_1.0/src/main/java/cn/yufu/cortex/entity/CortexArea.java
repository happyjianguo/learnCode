package cn.yufu.cortex.entity;

import java.util.Date;

public class CortexArea {
    private Long id;

    private String provinceCity;

    private String enprovinceCity;

    private Long fid;

    private String parentpath;

    private Short depth;

    private Long orderid;

    private Long child;

    private String isuse;

    private Date adddate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvinceCity() {
        return provinceCity;
    }

    public void setProvinceCity(String provinceCity) {
        this.provinceCity = provinceCity == null ? null : provinceCity.trim();
    }

    public String getEnprovinceCity() {
        return enprovinceCity;
    }

    public void setEnprovinceCity(String enprovinceCity) {
        this.enprovinceCity = enprovinceCity == null ? null : enprovinceCity.trim();
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getParentpath() {
        return parentpath;
    }

    public void setParentpath(String parentpath) {
        this.parentpath = parentpath == null ? null : parentpath.trim();
    }

    public Short getDepth() {
        return depth;
    }

    public void setDepth(Short depth) {
        this.depth = depth;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public Long getChild() {
        return child;
    }

    public void setChild(Long child) {
        this.child = child;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }
}