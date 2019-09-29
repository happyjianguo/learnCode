package cn.yufu.posp.merchant.domain.model;

/**
 * MerchantCardId entity. @author MyEclipse Persistence Tools
 */

public class MerchantCardModel implements java.io.Serializable {

	// Fields

	private String merchantId;//商户编号
	/**受理卡类*/
	private String cardType;
	/**银行类型*/
	private String bankType;
	/**受理卡回扣率*/
	private Double cardDiscount;
	private Double cardPiece;
	private Double cardFeeMax;
	private Double cardFeeMin;
	/**受理卡状态*/
	private String cardStat;
	/**更新柜员*/
	private String updateOper;
	/**更新日期*/
	private String updateDate;
	/**更新时间*/
	private String updateTime;
	
	//商户名称
	private String merchantName;
	
	
	//银行类型名称
	private String bankTypeName;
	//卡类型名称
	private String cardTypeName;
	
	//查询条件
	private String qMerId;
	private String qMerBank;
	
	private String queryMerchant;
	private String queryBankType;
	private String queryCardType;

	// Constructors

	public String getQueryCardType() {
		return queryCardType;
	}

	public void setQueryCardType(String queryCardType) {
		this.queryCardType = queryCardType;
	}

	/** default constructor */
	public MerchantCardModel() {
	}

	/** minimal constructor */
	public MerchantCardModel(String merchantId) {
		this.merchantId = merchantId;
	}

	/** full constructor */
	public MerchantCardModel(String merchantId, String cardType, String bankType,
			Double cardDiscount, Double cardPiece, Double cardFeeMax,
			Double cardFeeMin, String cardStat, String updateOper,
			String updateDate, String updateTime) {
		this.merchantId = merchantId;
		this.cardType = cardType;
		this.bankType = bankType;
		this.cardDiscount = cardDiscount;
		this.cardPiece = cardPiece;
		this.cardFeeMax = cardFeeMax;
		this.cardFeeMin = cardFeeMin;
		this.cardStat = cardStat;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankType() {
		/*if(this.bankType != null){
			this.bankType = this.bankType.replaceAll(" ", "");
			 
		}*/
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public Double getCardDiscount() {
		return this.cardDiscount;
	}

	public void setCardDiscount(Double cardDiscount) {
		this.cardDiscount = cardDiscount;
	}

	public Double getCardPiece() {
		return this.cardPiece;
	}

	public void setCardPiece(Double cardPiece) {
		this.cardPiece = cardPiece;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Double getCardFeeMax() {
		return this.cardFeeMax;
	}

	public void setCardFeeMax(Double cardFeeMax) {
		this.cardFeeMax = cardFeeMax;
	}

	public Double getCardFeeMin() {
		return this.cardFeeMin;
	}

	public void setCardFeeMin(Double cardFeeMin) {
		this.cardFeeMin = cardFeeMin;
	}

	public String getCardStat() {
		return this.cardStat;
	}

	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
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

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getQueryMerchant() {
		return queryMerchant;
	}

	public void setQueryMerchant(String queryMerchant) {
		this.queryMerchant = queryMerchant;
	}

	public String getQueryBankType() {
		/*if(queryBankType != null){
			queryBankType = queryBankType.replaceAll(" ", "");
		}*/
		return queryBankType;
	}

	public void setQueryBankType(String queryBankType) {
		this.queryBankType = queryBankType;
	}

	public String getqMerId() {
		return qMerId;
	}

	public void setqMerId(String qMerId) {
		this.qMerId = qMerId;
	}

	public String getqMerBank() {
		return qMerBank;
	}

	public void setqMerBank(String qMerBank) {
		this.qMerBank = qMerBank;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	

	

}