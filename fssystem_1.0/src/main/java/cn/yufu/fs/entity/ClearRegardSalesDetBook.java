package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearRegardSalesDetBook {
    private String id;

    private String genDt;

    private String merNo;

    private String merName;

    private String tranDate;
    private String startDt;
    private String endDt;

    private String crdproduct;

    private String cardTypeName;

    private BigDecimal tranNum;

    private BigDecimal tranAmt;

    private String salesProvince;

    private Long salesCity;
    private String salesCityName;

    private String salesZone;

    private String consumProvince;

    private Long consumCity;
    private String consumCityName;

    private String consumZone;

    private String cardNo;

    private String comments;

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

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
    }

    public String getCrdproduct() {
        return crdproduct;
    }

    public void setCrdproduct(String crdproduct) {
        this.crdproduct = crdproduct == null ? null : crdproduct.trim();
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
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

    public String getSalesProvince() {
        return salesProvince;
    }

    public void setSalesProvince(String salesProvince) {
        this.salesProvince = salesProvince == null ? null : salesProvince.trim();
    }

    public Long getSalesCity() {
        return salesCity;
    }

    public void setSalesCity(Long salesCity) {
        this.salesCity = salesCity == null ? null : salesCity;
    }

    public String getSalesZone() {
        return salesZone;
    }

    public void setSalesZone(String salesZone) {
        this.salesZone = salesZone == null ? null : salesZone.trim();
    }

    public String getConsumProvince() {
        return consumProvince;
    }

    public void setConsumProvince(String consumProvince) {
        this.consumProvince = consumProvince == null ? null : consumProvince.trim();
    }

    public Long getConsumCity() {
        return consumCity;
    }

    public void setConsumCity(Long consumCity) {
        this.consumCity = consumCity == null ? null : consumCity;
    }

    public String getConsumZone() {
        return consumZone;
    }

    public void setConsumZone(String consumZone) {
        this.consumZone = consumZone == null ? null : consumZone.trim();
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

	public String getSalesCityName() {
		return salesCityName;
	}

	public void setSalesCityName(String salesCityName) {
		this.salesCityName = salesCityName;
	}

	public String getConsumCityName() {
		return consumCityName;
	}

	public void setConsumCityName(String consumCityName) {
		this.consumCityName = consumCityName;
	}

	public String getStartDt() {
		return startDt;
	}

	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}

	public String getEndDt() {
		return endDt;
	}

	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
    
	
    
}