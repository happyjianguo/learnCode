package cn.yufu.posp.terminalmanager.domain.model;

/**
 * EdcCardId entity. @author MyEclipse Persistence Tools
 */

public class EdcCardId implements java.io.Serializable {

	// Fields

	/**商户编号*/
	private String merchantId;
	
	/**终端编号*/
	private String terminalId;
	
	/**受理卡类型*/
	private String cardType;
	
	/**银行类型*/
	private String bankType;
	
	
	
	
	/**受理卡类型名称--页面显示用*/
	private String cardTypeName;
	
	/**商户名称--页面显示用*/
	private String merchantName;
	
	

	/**银行类型名称--页面显示用*/
	private String bankTypeName;
	
	
    
	// Constructors

	public String getMerchantName() {
		return merchantName;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	

	/** default constructor */
	public EdcCardId() {
	}

	/** minimal constructor */
	public EdcCardId(String merchantId, String terminalId) {
		this.merchantId = merchantId;
		this.terminalId = terminalId;
	}

	/** full constructor */
	public EdcCardId(String merchantId, String terminalId, String cardType,
			String bankType) {//, String cardStat, String updateOper,String updateDate, String updateTime
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.cardType = cardType;
		this.bankType = bankType;
		//this.cardStat = cardStat;
//		this.updateOper = updateOper;
//		this.updateDate = updateDate;
//		this.updateTime = updateTime;
	}

	// Property accessors

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		    this.cardType = cardType;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EdcCardId))
			return false;
		EdcCardId castOther = (EdcCardId) other;

		return ((this.getMerchantId() == castOther.getMerchantId()) || (this
				.getMerchantId() != null
				&& castOther.getMerchantId() != null && this.getMerchantId()
				.equals(castOther.getMerchantId())))
				&& ((this.getTerminalId() == castOther.getTerminalId()) || (this
						.getTerminalId() != null
						&& castOther.getTerminalId() != null && this
						.getTerminalId().equals(castOther.getTerminalId())))
				&& ((this.getCardType() == castOther.getCardType()) || (this
						.getCardType() != null
						&& castOther.getCardType() != null && this
						.getCardType().equals(castOther.getCardType())))
				&& ((this.getBankType() == castOther.getBankType()) || (this
						.getBankType() != null
						&& castOther.getBankType() != null && this
						.getBankType().equals(castOther.getBankType())))
//				&& ((this.getCardStat() == castOther.getCardStat()) || (this
//						.getCardStat() != null
//						&& castOther.getCardStat() != null && this
//						.getCardStat().equals(castOther.getCardStat())))
//				&& ((this.getUpdateOper() == castOther.getUpdateOper()) || (this
//						.getUpdateOper() != null
//						&& castOther.getUpdateOper() != null && this
//						.getUpdateOper().equals(castOther.getUpdateOper())))
//				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this
//						.getUpdateDate() != null
//						&& castOther.getUpdateDate() != null && this
//						.getUpdateDate().equals(castOther.getUpdateDate())))
//				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
//						.getUpdateTime() != null
//						&& castOther.getUpdateTime() != null && this
//						.getUpdateTime().equals(castOther.getUpdateTime())))
						;
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMerchantId() == null ? 0 : this.getMerchantId()
						.hashCode());
		result = 37
				* result
				+ (getTerminalId() == null ? 0 : this.getTerminalId()
						.hashCode());
		result = 37 * result
				+ (getCardType() == null ? 0 : this.getCardType().hashCode());
		result = 37 * result
				+ (getBankType() == null ? 0 : this.getBankType().hashCode());
//		result = 37 * result
//				+ (getCardStat() == null ? 0 : this.getCardStat().hashCode());
//		result = 37
//				* result
//				+ (getUpdateOper() == null ? 0 : this.getUpdateOper()
//						.hashCode());
//		result = 37
//				* result
//				+ (getUpdateDate() == null ? 0 : this.getUpdateDate()
//						.hashCode());
//		result = 37
//				* result
//				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
//						.hashCode());
		return result;
	}

}