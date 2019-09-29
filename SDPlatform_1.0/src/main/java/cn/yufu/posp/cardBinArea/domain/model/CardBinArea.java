package cn.yufu.posp.cardBinArea.domain.model;

/**
 * CardBinArea entity. @author MyEclipse Persistence Tools
 */

public class CardBinArea implements java.io.Serializable {

	// Fields

	private String cardBin;
	private String areaCode;
	private String status;
	private String createOper;
	private String createDate;
	private String updateOper;
	private String updateDate;

	// ²éÑ¯Ìõ¼þ
	private String queryCardBin;
	private String queryAreaCode;
	private String queryStatus;
	
	private String areaCodeName;

	// Constructors

	/** default constructor */
	public CardBinArea() {
	}

	/** minimal constructor */
	public CardBinArea(String cardBin, String areaCode, String status) {
		this.cardBin = cardBin;
		this.areaCode = areaCode;
		this.status = status;
	}

	/** full constructor */
	public CardBinArea(String cardBin, String areaCode, String status,
			String createOper, String createDate, String updateOper,
			String updateDate) {
		this.cardBin = cardBin;
		this.areaCode = areaCode;
		this.status = status;
		this.createOper = createOper;
		this.createDate = createDate;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
	}

	// Property accessors

	public String getCardBin() {
		return this.cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getAreaCode() {
		return this.areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateOper() {
		return this.createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getQueryCardBin() {
		return queryCardBin;
	}

	public void setQueryCardBin(String queryCardBin) {
		this.queryCardBin = queryCardBin;
	}

	public String getQueryAreaCode() {
		return queryAreaCode;
	}

	public void setQueryAreaCode(String queryAreaCode) {
		this.queryAreaCode = queryAreaCode;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getAreaCodeName() {
		return areaCodeName;
	}

	public void setAreaCodeName(String areaCodeName) {
		this.areaCodeName = areaCodeName;
	}

}