package cn.yufu.posp.cardtype.domain.model;

import java.math.BigDecimal;

/**
 * CardTypeId entity. @author MyEclipse Persistence Tools
 */

public class CardTypeId implements java.io.Serializable {

	// Fields

	private String cardId;
	private BigDecimal cardLen;
	private String cardNoId;
	private String cardType;
	private String cardName;
	private String bankType;
	private String bankCode;
	private String cardMode;
	private BigDecimal cardIdTrack;
	private BigDecimal cardIdOff;
	private BigDecimal cardNoTrack;
	private BigDecimal cardNoOff;
	private BigDecimal expDateOff;
	private BigDecimal pinMode;
	private BigDecimal inputMode;
	private String chkCardValid;
	private String ifLocal;
	private String ifOffline;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	private String bankTypeName;
	private String cardTypeName;
	// Constructors

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

	/** default constructor */
	public CardTypeId() {
	}

	/** minimal constructor */
	public CardTypeId(String cardId, BigDecimal cardLen, String cardType,
			BigDecimal cardIdTrack, BigDecimal cardIdOff,
			BigDecimal cardNoTrack, String ifLocal, String ifOffline) {
		this.cardId = cardId;
		this.cardLen = cardLen;
		this.cardType = cardType;
		this.cardIdTrack = cardIdTrack;
		this.cardIdOff = cardIdOff;
		this.cardNoTrack = cardNoTrack;
		this.ifLocal = ifLocal;
		this.ifOffline = ifOffline;
	}

	/** full constructor */
	public CardTypeId(String cardId, BigDecimal cardLen, String cardNoId,
			String cardType, String cardName, String bankType, String bankCode,
			String cardMode, BigDecimal cardIdTrack, BigDecimal cardIdOff,
			BigDecimal cardNoTrack, BigDecimal cardNoOff,
			BigDecimal expDateOff, BigDecimal pinMode, BigDecimal inputMode,
			String chkCardValid, String ifLocal, String ifOffline,
			String updateOper, String updateDate, String updateTime) {
		this.cardId = cardId;
		this.cardLen = cardLen;
		this.cardNoId = cardNoId;
		this.cardType = cardType;
		this.cardName = cardName;
		this.bankType = bankType;
		this.bankCode = bankCode;
		this.cardMode = cardMode;
		this.cardIdTrack = cardIdTrack;
		this.cardIdOff = cardIdOff;
		this.cardNoTrack = cardNoTrack;
		this.cardNoOff = cardNoOff;
		this.expDateOff = expDateOff;
		this.pinMode = pinMode;
		this.inputMode = inputMode;
		this.chkCardValid = chkCardValid;
		this.ifLocal = ifLocal;
		this.ifOffline = ifOffline;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getCardId() {
		return this.cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public BigDecimal getCardLen() {
		return this.cardLen;
	}

	public void setCardLen(BigDecimal cardLen) {
		this.cardLen = cardLen;
	}

	public String getCardNoId() {
		return this.cardNoId;
	}

	public void setCardNoId(String cardNoId) {
		this.cardNoId = cardNoId;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardName() {
		return this.cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCardMode() {
		return this.cardMode;
	}

	public void setCardMode(String cardMode) {
		this.cardMode = cardMode;
	}

	public BigDecimal getCardIdTrack() {
		return this.cardIdTrack;
	}

	public void setCardIdTrack(BigDecimal cardIdTrack) {
		this.cardIdTrack = cardIdTrack;
	}

	public BigDecimal getCardIdOff() {
		return this.cardIdOff;
	}

	public void setCardIdOff(BigDecimal cardIdOff) {
		this.cardIdOff = cardIdOff;
	}

	public BigDecimal getCardNoTrack() {
		return this.cardNoTrack;
	}

	public void setCardNoTrack(BigDecimal cardNoTrack) {
		this.cardNoTrack = cardNoTrack;
	}

	public BigDecimal getCardNoOff() {
		return this.cardNoOff;
	}

	public void setCardNoOff(BigDecimal cardNoOff) {
		this.cardNoOff = cardNoOff;
	}

	public BigDecimal getExpDateOff() {
		return this.expDateOff;
	}

	public void setExpDateOff(BigDecimal expDateOff) {
		this.expDateOff = expDateOff;
	}

	public BigDecimal getPinMode() {
		return this.pinMode;
	}

	public void setPinMode(BigDecimal pinMode) {
		this.pinMode = pinMode;
	}

	public BigDecimal getInputMode() {
		return this.inputMode;
	}

	public void setInputMode(BigDecimal inputMode) {
		this.inputMode = inputMode;
	}

	public String getChkCardValid() {
		return this.chkCardValid;
	}

	public void setChkCardValid(String chkCardValid) {
		this.chkCardValid = chkCardValid;
	}

	public String getIfLocal() {
		return this.ifLocal;
	}

	public void setIfLocal(String ifLocal) {
		this.ifLocal = ifLocal;
	}

	public String getIfOffline() {
		return this.ifOffline;
	}

	public void setIfOffline(String ifOffline) {
		this.ifOffline = ifOffline;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CardTypeId))
			return false;
		CardTypeId castOther = (CardTypeId) other;

		return ((this.getCardId() == castOther.getCardId()) || (this
				.getCardId() != null
				&& castOther.getCardId() != null && this.getCardId().equals(
				castOther.getCardId())))
				&& ((this.getCardLen() == castOther.getCardLen()) || (this
						.getCardLen() != null
						&& castOther.getCardLen() != null && this.getCardLen()
						.equals(castOther.getCardLen())))
				&& ((this.getCardNoId() == castOther.getCardNoId()) || (this
						.getCardNoId() != null
						&& castOther.getCardNoId() != null && this
						.getCardNoId().equals(castOther.getCardNoId())))
				&& ((this.getCardType() == castOther.getCardType()) || (this
						.getCardType() != null
						&& castOther.getCardType() != null && this
						.getCardType().equals(castOther.getCardType())))
				&& ((this.getCardName() == castOther.getCardName()) || (this
						.getCardName() != null
						&& castOther.getCardName() != null && this
						.getCardName().equals(castOther.getCardName())))
				&& ((this.getBankType() == castOther.getBankType()) || (this
						.getBankType() != null
						&& castOther.getBankType() != null && this
						.getBankType().equals(castOther.getBankType())))
				&& ((this.getBankCode() == castOther.getBankCode()) || (this
						.getBankCode() != null
						&& castOther.getBankCode() != null && this
						.getBankCode().equals(castOther.getBankCode())))
				&& ((this.getCardMode() == castOther.getCardMode()) || (this
						.getCardMode() != null
						&& castOther.getCardMode() != null && this
						.getCardMode().equals(castOther.getCardMode())))
				&& ((this.getCardIdTrack() == castOther.getCardIdTrack()) || (this
						.getCardIdTrack() != null
						&& castOther.getCardIdTrack() != null && this
						.getCardIdTrack().equals(castOther.getCardIdTrack())))
				&& ((this.getCardIdOff() == castOther.getCardIdOff()) || (this
						.getCardIdOff() != null
						&& castOther.getCardIdOff() != null && this
						.getCardIdOff().equals(castOther.getCardIdOff())))
				&& ((this.getCardNoTrack() == castOther.getCardNoTrack()) || (this
						.getCardNoTrack() != null
						&& castOther.getCardNoTrack() != null && this
						.getCardNoTrack().equals(castOther.getCardNoTrack())))
				&& ((this.getCardNoOff() == castOther.getCardNoOff()) || (this
						.getCardNoOff() != null
						&& castOther.getCardNoOff() != null && this
						.getCardNoOff().equals(castOther.getCardNoOff())))
				&& ((this.getExpDateOff() == castOther.getExpDateOff()) || (this
						.getExpDateOff() != null
						&& castOther.getExpDateOff() != null && this
						.getExpDateOff().equals(castOther.getExpDateOff())))
				&& ((this.getPinMode() == castOther.getPinMode()) || (this
						.getPinMode() != null
						&& castOther.getPinMode() != null && this.getPinMode()
						.equals(castOther.getPinMode())))
				&& ((this.getInputMode() == castOther.getInputMode()) || (this
						.getInputMode() != null
						&& castOther.getInputMode() != null && this
						.getInputMode().equals(castOther.getInputMode())))
				&& ((this.getChkCardValid() == castOther.getChkCardValid()) || (this
						.getChkCardValid() != null
						&& castOther.getChkCardValid() != null && this
						.getChkCardValid().equals(castOther.getChkCardValid())))
				&& ((this.getIfLocal() == castOther.getIfLocal()) || (this
						.getIfLocal() != null
						&& castOther.getIfLocal() != null && this.getIfLocal()
						.equals(castOther.getIfLocal())))
				&& ((this.getIfOffline() == castOther.getIfOffline()) || (this
						.getIfOffline() != null
						&& castOther.getIfOffline() != null && this
						.getIfOffline().equals(castOther.getIfOffline())))
				&& ((this.getUpdateOper() == castOther.getUpdateOper()) || (this
						.getUpdateOper() != null
						&& castOther.getUpdateOper() != null && this
						.getUpdateOper().equals(castOther.getUpdateOper())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this
						.getUpdateDate() != null
						&& castOther.getUpdateDate() != null && this
						.getUpdateDate().equals(castOther.getUpdateDate())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCardId() == null ? 0 : this.getCardId().hashCode());
		result = 37 * result
				+ (getCardLen() == null ? 0 : this.getCardLen().hashCode());
		result = 37 * result
				+ (getCardNoId() == null ? 0 : this.getCardNoId().hashCode());
		result = 37 * result
				+ (getCardType() == null ? 0 : this.getCardType().hashCode());
		result = 37 * result
				+ (getCardName() == null ? 0 : this.getCardName().hashCode());
		result = 37 * result
				+ (getBankType() == null ? 0 : this.getBankType().hashCode());
		result = 37 * result
				+ (getBankCode() == null ? 0 : this.getBankCode().hashCode());
		result = 37 * result
				+ (getCardMode() == null ? 0 : this.getCardMode().hashCode());
		result = 37
				* result
				+ (getCardIdTrack() == null ? 0 : this.getCardIdTrack()
						.hashCode());
		result = 37 * result
				+ (getCardIdOff() == null ? 0 : this.getCardIdOff().hashCode());
		result = 37
				* result
				+ (getCardNoTrack() == null ? 0 : this.getCardNoTrack()
						.hashCode());
		result = 37 * result
				+ (getCardNoOff() == null ? 0 : this.getCardNoOff().hashCode());
		result = 37
				* result
				+ (getExpDateOff() == null ? 0 : this.getExpDateOff()
						.hashCode());
		result = 37 * result
				+ (getPinMode() == null ? 0 : this.getPinMode().hashCode());
		result = 37 * result
				+ (getInputMode() == null ? 0 : this.getInputMode().hashCode());
		result = 37
				* result
				+ (getChkCardValid() == null ? 0 : this.getChkCardValid()
						.hashCode());
		result = 37 * result
				+ (getIfLocal() == null ? 0 : this.getIfLocal().hashCode());
		result = 37 * result
				+ (getIfOffline() == null ? 0 : this.getIfOffline().hashCode());
		result = 37
				* result
				+ (getUpdateOper() == null ? 0 : this.getUpdateOper()
						.hashCode());
		result = 37
				* result
				+ (getUpdateDate() == null ? 0 : this.getUpdateDate()
						.hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		return result;
	}

}