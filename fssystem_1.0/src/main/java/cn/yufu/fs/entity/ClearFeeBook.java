package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearFeeBook {
    private String id;

    private String genDate;

    private String merNo;

    private String merName;

    private String headOffice;

    private BigDecimal tranAmt;

    private String startDate;

    private String endDate;

    private String stlDate;

    private BigDecimal fee;

    private String status;

    private String expressDate;

    private String expressNo;

    private String officeNo;

    private String comments;

    private String isKp;
    
    private String updateDT;
    private String operNo;
    
    private BigDecimal realFee;//实际手续费金额
    private BigDecimal checkFee;//调整金额


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGenDate() {
        return genDate;
    }

    public void setGenDate(String genDate) {
        this.genDate = genDate == null ? null : genDate.trim();
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

    public String getHeadOffice() {
        return headOffice;
    }

    public void setHeadOffice(String headOffice) {
        this.headOffice = headOffice == null ? null : headOffice.trim();
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getStlDate() {
        return stlDate;
    }

    public void setStlDate(String stlDate) {
        this.stlDate = stlDate == null ? null : stlDate.trim();
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getExpressDate() {
        return expressDate;
    }

    public void setExpressDate(String expressDate) {
        this.expressDate = expressDate == null ? null : expressDate.trim();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo == null ? null : expressNo.trim();
    }

    public String getOfficeNo() {
        return officeNo;
    }

    public void setOfficeNo(String officeNo) {
        this.officeNo = officeNo == null ? null : officeNo.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public String getIsKp() {
        return isKp;
    }

    public void setIsKp(String isKp) {
        this.isKp = isKp == null ? null : isKp.trim();
    }

	public String getUpdateDT() {
		return updateDT;
	}

	public void setUpdateDT(String updateDT) {
		this.updateDT = updateDT;
	}

	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public BigDecimal getRealFee() {
		return realFee;
	}

	public void setRealFee(BigDecimal realFee) {
		this.realFee = realFee;
	}

	public BigDecimal getCheckFee() {
		return checkFee;
	}

	public void setCheckFee(BigDecimal checkFee) {
		this.checkFee = checkFee;
	}
    
    
}