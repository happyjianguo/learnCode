package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearUnMerStlBook {
    private String id;

    private String genDt;

    private String merNo;

    private String merName;

    private String laststlDate;

    private String startunstlDate;

    private String endunstlDate;

    private BigDecimal tranNum;

    private BigDecimal tranAmt;

    private String comments;
    private String stlNeedFlag;
   
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGenDt() {
        return genDt;
    }

    public void setGenDt(String genDt) {
        this.genDt = genDt == null ? null : genDt.trim();
    }

    public String getMerNo() {
        return merNo;
    }

    public void setMerNo(String merNo) {
        this.merNo = merNo == null ? null : merNo.trim();
    }

    public String getMerName() {
        return merName;
    }

    public void setMerName(String merName) {
        this.merName = merName == null ? null : merName.trim();
    }

    public String getLaststlDate() {
        return laststlDate;
    }

    public void setLaststlDate(String laststlDate) {
        this.laststlDate = laststlDate == null ? null : laststlDate.trim();
    }

    public String getStartunstlDate() {
        return startunstlDate;
    }

    public void setStartunstlDate(String startunstlDate) {
        this.startunstlDate = startunstlDate == null ? null : startunstlDate.trim();
    }

    public String getEndunstlDate() {
        return endunstlDate;
    }

    public void setEndunstlDate(String endunstlDate) {
        this.endunstlDate = endunstlDate == null ? null : endunstlDate.trim();
    }

    public BigDecimal getTranNum() {
        return tranNum;
    }

    public void setTranNum(BigDecimal tranNum) {
        this.tranNum = tranNum;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

	public String getStlNeedFlag() {
		return stlNeedFlag;
	}

	public void setStlNeedFlag(String stlNeedFlag) {
		this.stlNeedFlag = stlNeedFlag == null ? null : stlNeedFlag.trim();
	}

}