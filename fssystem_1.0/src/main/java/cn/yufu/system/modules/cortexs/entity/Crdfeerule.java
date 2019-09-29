package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

public class Crdfeerule extends DataEntity<Crdfeerule>{
	private static final long serialVersionUID = 1L;

	private Long crdfeeruleId;

    private Long vernoCtx;

    private String iid;   //卡BIN

    private String crdproduct; //卡产品
    
    private String descr;    //卡描述

    private String beginyear;   //大于起始年多少年 1-10

    private String rate;  //费率

    private String feeflag;  //是否收取手续费   0 不收  1收

    private String truenameflag;  //实名标识 0  非实名  1实名

    private String adddate;  //添加日期

    private String updatedate;  //更新日期

    private String oper;  //操作员

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String reserved4;
    
    public Long getCrdfeeruleId() {
		return crdfeeruleId;
	}

	public void setCrdfeeruleId(Long crdfeeruleId) {
		this.crdfeeruleId = crdfeeruleId;
	}

	public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid == null ? null : iid.trim();
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }
    
    public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr == null ? null : descr.trim();
	}

	public String getBeginyear() {
        return beginyear;
    }

    public void setBeginyear(String beginyear) {
        this.beginyear = beginyear == null ? null : beginyear.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getFeeflag() {
        return feeflag;
    }

    public void setFeeflag(String feeflag) {
        this.feeflag = feeflag == null ? null : feeflag.trim();
    }

    public String getTruenameflag() {
        return truenameflag;
    }

    public void setTruenameflag(String truenameflag) {
        this.truenameflag = truenameflag == null ? null : truenameflag.trim();
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate == null ? null : adddate.trim();
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate == null ? null : updatedate.trim();
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper == null ? null : oper.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }

    public String getReserved4() {
        return reserved4;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4 == null ? null : reserved4.trim();
    }
}