package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearRegardSalesBook {
    private String id;

    private String genDt;

    private String tranDate;

    private BigDecimal tranNum;

    private BigDecimal tranAmt;

    private String salesProvince;

    private String salesCity;
    private String salesCityName;

    private String salesZone;

    private String consumProvince;

    private String consumCity;
    private String consumCityName;

    private String consumZone;

    private String comments;
    
    private String startDt;
    
    private String endDt;  
    private String crdproduct;
    private String cardTypeName;

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

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate == null ? null : tranDate.trim();
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

    public String getSalesCity() {
        return salesCity;
    }

    public void setSalesCity(String salesCity) {
        this.salesCity = salesCity == null ? null : salesCity.trim();
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

    public String getConsumCity() {
        return consumCity;
    }

    public void setConsumCity(String consumCity) {
        this.consumCity = consumCity == null ? null : consumCity.trim();
    }

    public String getConsumZone() {
        return consumZone;
    }

    public void setConsumZone(String consumZone) {
        this.consumZone = consumZone == null ? null : consumZone.trim();
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
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

	public String getCrdproduct() {
		return crdproduct;
	}

	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
    
    
}