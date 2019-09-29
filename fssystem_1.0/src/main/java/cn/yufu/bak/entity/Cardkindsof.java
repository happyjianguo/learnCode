package cn.yufu.bak.entity;

/**
 * 卡类型相关信息 包括 来源数据库类型
 * @author Administrator ZQK 2018-01-25
 * */
public class Cardkindsof {
    private String cardnumber;	//卡类型编号

    private String cardkindname;	//卡类型名称

    private String remark;	//备注
    
    private String isExclusive;	//是否专属卡
    
    private String stlFlag;	//是否参与结算 0-不参与 1-参与
    
    private String dataBaseSourceName;	//数据库来源名称
    
    private String dataSourceId;	//数据来源ID
    
    private String oldKindId;	//旧库的卡类型
    
    private String oldDbcArdName;	//旧库卡类型名称
    
    private String dataBaseType;	//数据库来源类型区分：1-yufu库,2-旧库2,3-tlog

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    public String getCardkindname() {
        return cardkindname;
    }

    public void setCardkindname(String cardkindname) {
        this.cardkindname = cardkindname == null ? null : cardkindname.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getIsExclusive() {
		return isExclusive;
	}

	public void setIsExclusive(String isExclusive) {
		this.isExclusive = isExclusive;
	}

	public String getStlFlag() {
		return stlFlag;
	}

	public void setStlFlag(String stlFlag) {
		this.stlFlag = stlFlag;
	}

	public String getDataBaseSourceName() {
		return dataBaseSourceName;
	}

	public void setDataBaseSourceName(String dataBaseSourceName) {
		this.dataBaseSourceName = dataBaseSourceName;
	}

	public String getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(String dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public String getOldKindId() {
		return oldKindId;
	}

	public void setOldKindId(String oldKindId) {
		this.oldKindId = oldKindId;
	}

	public String getOldDbcArdName() {
		return oldDbcArdName;
	}

	public void setOldDbcArdName(String oldDbcArdName) {
		this.oldDbcArdName = oldDbcArdName;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}
    
}