package cn.yufu.fs.entity;

public class TCardTypeConversion {
    private Integer cardtype;

    private String cardtypename;

    private Integer cardtypeId;

    private String cardtypeIdName;
    
    private String flag; //标识 新增/修改

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
        this.cardtype = cardtype;
    }

    public String getCardtypename() {
        return cardtypename;
    }

    public void setCardtypename(String cardtypename) {
        this.cardtypename = cardtypename == null ? null : cardtypename.trim();
    }

    public Integer getCardtypeId() {
        return cardtypeId;
    }

    public void setCardtypeId(Integer cardtypeId) {
        this.cardtypeId = cardtypeId;
    }

    public String getCardtypeIdName() {
        return cardtypeIdName;
    }

    public void setCardtypeIdName(String cardtypeIdName) {
        this.cardtypeIdName = cardtypeIdName == null ? null : cardtypeIdName.trim();
    }

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
}