package cn.yufu.system.modules.cortexs.entity;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.DataEntity;

public class Opencrdbatch extends DataEntity<Opencrdbatch>{
	private static final long serialVersionUID = 1L;
	
	private BigDecimal opencrdbatchId;
	
    private Long vernoCtx;

    private String txnsrc;

    private Integer txncode;

    private String time;

    private Integer stan;

    private String panStart;

    private String panEnd;

    private Long panCount;

    private BigDecimal amtEachCrd;

    private String fatherOrder;

    private String childrenOrder;

    private String orgFOrder;

    private String orgCOrder;

    private String acctPeriod;

    private Integer payType;

    private String payDesc;

    private String payerName;

    private String salesPoint;

    private String area;

    private String isopenFlag;

    private String summary;

    private String operator;

    private String mrchtId;

    private String batchStat;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String descr;

    private String curtxn;

    private BigDecimal amttxn;

    private BigDecimal rateset;

    private String currbill;

    private String crdproduct;
    
    public BigDecimal getOpencrdbatchId() {
		return opencrdbatchId;
	}

	public void setOpencrdbatchId(BigDecimal opencrdbatchId) {
		this.opencrdbatchId = opencrdbatchId;
	}

	public Long getVernoCtx() {
        return vernoCtx;
    }

    public void setVernoCtx(Long vernoCtx) {
        this.vernoCtx = vernoCtx;
    }

    public String getTxnsrc() {
        return txnsrc;
    }

    public void setTxnsrc(String txnsrc) {
        this.txnsrc = txnsrc == null ? null : txnsrc.trim();
    }

    public Integer getTxncode() {
        return txncode;
    }

    public void setTxncode(Integer txncode) {
        this.txncode = txncode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getStan() {
        return stan;
    }

    public void setStan(Integer stan) {
        this.stan = stan;
    }

    public String getPanStart() {
        return panStart;
    }

    public void setPanStart(String panStart) {
        this.panStart = panStart == null ? null : panStart.trim();
    }

    public String getPanEnd() {
        return panEnd;
    }

    public void setPanEnd(String panEnd) {
        this.panEnd = panEnd == null ? null : panEnd.trim();
    }

    public Long getPanCount() {
        return panCount;
    }

    public void setPanCount(Long panCount) {
        this.panCount = panCount;
    }

    public BigDecimal getAmtEachCrd() {
        return amtEachCrd;
    }

    public void setAmtEachCrd(BigDecimal amtEachCrd) {
        this.amtEachCrd = amtEachCrd;
    }

    public String getFatherOrder() {
        return fatherOrder;
    }

    public void setFatherOrder(String fatherOrder) {
        this.fatherOrder = fatherOrder == null ? null : fatherOrder.trim();
    }

    public String getChildrenOrder() {
        return childrenOrder;
    }

    public void setChildrenOrder(String childrenOrder) {
        this.childrenOrder = childrenOrder == null ? null : childrenOrder.trim();
    }

    public String getOrgFOrder() {
        return orgFOrder;
    }

    public void setOrgFOrder(String orgFOrder) {
        this.orgFOrder = orgFOrder == null ? null : orgFOrder.trim();
    }

    public String getOrgCOrder() {
        return orgCOrder;
    }

    public void setOrgCOrder(String orgCOrder) {
        this.orgCOrder = orgCOrder == null ? null : orgCOrder.trim();
    }

    public String getAcctPeriod() {
        return acctPeriod;
    }

    public void setAcctPeriod(String acctPeriod) {
        this.acctPeriod = acctPeriod == null ? null : acctPeriod.trim();
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayDesc() {
        return payDesc;
    }

    public void setPayDesc(String payDesc) {
        this.payDesc = payDesc == null ? null : payDesc.trim();
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName == null ? null : payerName.trim();
    }

    public String getSalesPoint() {
        return salesPoint;
    }

    public void setSalesPoint(String salesPoint) {
        this.salesPoint = salesPoint == null ? null : salesPoint.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getIsopenFlag() {
        return isopenFlag;
    }

    public void setIsopenFlag(String isopenFlag) {
        this.isopenFlag = isopenFlag == null ? null : isopenFlag.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getMrchtId() {
        return mrchtId;
    }

    public void setMrchtId(String mrchtId) {
        this.mrchtId = mrchtId == null ? null : mrchtId.trim();
    }

    public String getBatchStat() {
        return batchStat;
    }

    public void setBatchStat(String batchStat) {
        this.batchStat = batchStat == null ? null : batchStat.trim();
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

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr == null ? null : descr.trim();
    }

    public String getCurtxn() {
        return curtxn;
    }

    public void setCurtxn(String curtxn) {
        this.curtxn = curtxn == null ? null : curtxn.trim();
    }

    public BigDecimal getAmttxn() {
        return amttxn;
    }

    public void setAmttxn(BigDecimal amttxn) {
        this.amttxn = amttxn;
    }

    public BigDecimal getRateset() {
        return rateset;
    }

    public void setRateset(BigDecimal rateset) {
        this.rateset = rateset;
    }

    public String getCurrbill() {
        return currbill;
    }

    public void setCurrbill(String currbill) {
        this.currbill = currbill == null ? null : currbill.trim();
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }
}