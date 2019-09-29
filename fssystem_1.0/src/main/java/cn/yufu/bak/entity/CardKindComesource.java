package cn.yufu.bak.entity;

public class CardKindComesource {
	private String id;					//主键  
	
    private String cardnumber;			//CARDKINDSOF 此表 ID
    
    private String cardkindname;		//卡类型名称

    private String databasesourcename;	//数据来源名称

    private String datasourceid;		

    private String dataBaseType;		//数据来源	

    private String oldKindid;			//卡产品  eq
    
    private String oldKindidLike;		//卡产品  模糊查询

    private String oldDBCardName;		//卡产品对应名称 默认空

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber == null ? null : cardnumber.trim();
    }

    public String getDatabasesourcename() {
        return databasesourcename;
    }

    public void setDatabasesourcename(String databasesourcename) {
        this.databasesourcename = databasesourcename == null ? null : databasesourcename.trim();
    }

    public String getDatasourceid() {
        return datasourceid;
    }

    public void setDatasourceid(String datasourceid) {
        this.datasourceid = datasourceid == null ? null : datasourceid.trim();
    }

    public String getDataBaseType() {
        return dataBaseType;
    }

    public void setDataBaseType(String dataBaseType) {
        this.dataBaseType = dataBaseType == null ? null : dataBaseType.trim();
    }

    public String getOldKindid() {
        return oldKindid;
    }

    public void setOldKindid(String oldKindid) {
        this.oldKindid = oldKindid == null ? null : oldKindid.trim();
    }

    public String getOldDBCardName() {
        return oldDBCardName;
    }

    public void setOldDBCardName(String oldDBCardName) {
        this.oldDBCardName = oldDBCardName == null ? null : oldDBCardName.trim();
    }

	public String getOldKindidLike() {
		return oldKindidLike;
	}

	public void setOldKindidLike(String oldKindidLike) {
		this.oldKindidLike = oldKindidLike == null ? null : oldKindidLike.trim();
    }

	public String getCardkindname() {
		return cardkindname;
	}

	public void setCardkindname(String cardkindname) {
		this.cardkindname = cardkindname;
	}

}