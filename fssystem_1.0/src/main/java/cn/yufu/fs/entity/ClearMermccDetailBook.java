package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class ClearMermccDetailBook {
    private String id;

    private String genDt;

    private String merNo;

    private String merName;

    private String tranDate;
    private String startDt;
    private String endDt;

    private String consumType;
    private String consumTypeName;

    private String crdproduct;

    private String cardTypeName;

    private BigDecimal tranNum;

    private BigDecimal tranAmt;

    private String consumProvince;

    private Long consumCity;
    private String consumCityName;

    private String consumZone;

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

    public String getConsumType() {
        return consumType;
    }

    public void setConsumType(String consumType) {
        this.consumType = consumType == null ? null : consumType.trim();
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

    public String getConsumProvince() {
        return consumProvince;
    }

    public void setConsumProvince(String consumProvince) {
        this.consumProvince = consumProvince;
    }

    public Long getConsumCity() {
        return consumCity;
    }

    public void setConsumCity(Long consumCity) {
        this.consumCity = consumCity;
    }

    public String getConsumZone() {
        return consumZone;
    }

    public void setConsumZone(String consumZone) {
        this.consumZone = consumZone;
    }

    public String getComments() {
        return comments;
    }

    public void setCLongs(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

	public String getConsumCityName() {
		return consumCityName;
	}

	public void setConsumCityName(String consumCityName) {
		this.consumCityName = consumCityName;
	}

	public String getConsumTypeName() {
		return consumTypeName;
	}

	public void setConsumTypeName(String consumTypeName) {
		this.consumTypeName = consumTypeName;
	}

	public void setComments(String comments) {
		this.comments = comments;
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