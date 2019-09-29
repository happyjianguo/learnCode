package cn.yufu.posp.cardtype.web.form;

import cn.yufu.core.web.form.BaseForm;

public class CardTypeForm extends BaseForm {
	private String iid;
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	private String _cardId;
	private String _cardName;
	public String get_cardId() {
		return _cardId;
	}
	public void set_cardId(String cardId) {
		_cardId = cardId;
	}
	public String get_cardName() {
		return _cardName;
	}
	public void set_cardName(String cardName) {
		_cardName = cardName;
	}
	private String cardId;
	private String cardLen;
	private String cardNoId;
	private String cardType;
	private String cardName;
	private String bankType;
	private String bankCode;
	private String cardMode;
	private String cardIdTrack;
	private String cardIdOff;
	private String cardNoTrack;
	private String cardNoOff;
	private String expDateOff;
	private String pinMode;
	private String inputMode;
	private String chkCardValid;
	private String ifLocal;
	private String ifOffline;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	public String getCardId() {
		return this.cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardLen() {
		
		return cardLen;
	}
	public void setCardLen(String cardLen) {
		this.cardLen = cardLen;
	}
	public String getCardNoId() {
		return cardNoId;
	}
	public void setCardNoId(String cardNoId) {
		this.cardNoId = cardNoId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType.trim();
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getCardMode() {
		return cardMode;
	}
	public void setCardMode(String cardMode) {
		this.cardMode = cardMode;
	}
	public String getCardIdTrack() {
		return cardIdTrack;
	}
	public void setCardIdTrack(String cardIdTrack) {
		this.cardIdTrack = cardIdTrack;
	}
	public String getCardIdOff() {
		return cardIdOff;
	}
	public void setCardIdOff(String cardIdOff) {
		this.cardIdOff = cardIdOff;
	}
	public String getCardNoTrack() {
		return cardNoTrack;
	}
	public void setCardNoTrack(String cardNoTrack) {
		this.cardNoTrack = cardNoTrack;
	}
	public String getCardNoOff() {
		return cardNoOff;
	}
	public void setCardNoOff(String cardNoOff) {
		this.cardNoOff = cardNoOff;
	}
	public String getExpDateOff() {
		return expDateOff;
	}
	public void setExpDateOff(String expDateOff) {
		this.expDateOff = expDateOff;
	}
	public String getPinMode() {
		return pinMode;
	}
	public void setPinMode(String pinMode) {
		this.pinMode = pinMode;
	}
	public String getInputMode() {
		return inputMode;
	}
	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}
	public String getChkCardValid() {
		return chkCardValid;
	}
	public void setChkCardValid(String chkCardValid) {
		this.chkCardValid = chkCardValid;
	}
	public String getIfLocal() {
		return ifLocal;
	}
	public void setIfLocal(String ifLocal) {
		this.ifLocal = ifLocal;
	}
	public String getIfOffline() {
		return ifOffline;
	}
	public void setIfOffline(String ifOffline) {
		this.ifOffline = ifOffline;
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
}
