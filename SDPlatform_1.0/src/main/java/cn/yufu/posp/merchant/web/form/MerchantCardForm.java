package cn.yufu.posp.merchant.web.form;

import cn.yufu.core.web.form.BaseForm;

public class MerchantCardForm extends BaseForm{
	
	private String merchantId;//商户编号
	/**受理卡类*/
	private String cardType;
	/**银行类型*/
	private String bankType;
	/**受理卡回扣率*/
	private String cardDiscount;
	private String cardPiece;
	private String cardFeeMax;
	private String cardFeeMin;
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
	
	//参数
	private String queryMerchant;
	private String queryBankType;
	private String queryCardType;
	
	public String getQueryCardType() {
		return queryCardType;
	}

	public void setQueryCardType(String queryCardType) {
		this.queryCardType = queryCardType;
	}

	public String getQueryMerchant() {
		return queryMerchant;
	}

	public void setQueryMerchant(String queryMerchant) {
		this.queryMerchant = queryMerchant;
	}

	public String getQueryBankType() {
		return queryBankType;
	}

	public void setQueryBankType(String queryBankType) {
		this.queryBankType = queryBankType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType.trim();
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getCardDiscount() {
		return cardDiscount;
	}

	public void setCardDiscount(String cardDiscount) {
		this.cardDiscount = cardDiscount;
	}

	public String getCardPiece() {
		return cardPiece;
	}

	public void setCardPiece(String cardPiece) {
		this.cardPiece = cardPiece;
	}

	public String getCardFeeMax() {
		return cardFeeMax;
	}

	public void setCardFeeMax(String cardFeeMax) {
		this.cardFeeMax = cardFeeMax;
	}

	public String getCardFeeMin() {
		return cardFeeMin;
	}

	public void setCardFeeMin(String cardFeeMin) {
		this.cardFeeMin = cardFeeMin;
	}

	public String getCardStat() {
		return cardStat;
	}

	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
	}

	public String getUpdateOper() {
		return updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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
