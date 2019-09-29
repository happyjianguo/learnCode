package cn.yufu.posp.route.web.form;

import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

public class CardRouteForm extends BaseForm {
	private String iid;
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	private String _cardType;
	private String _rcvBankId;
	public String get_cardType() {
		return _cardType;
	}
	public void set_cardType(String cardType) {
		_cardType = cardType;
	}
	public String get_rcvBankId() {
		return _rcvBankId;
	}
	public void set_rcvBankId(String rcvBankId) {
		_rcvBankId = rcvBankId;
	}
	private String firstCardNo;
	private String lastCardNo;
	private String tranBit;
	private String cardType;
	private String rcvBankId;
	private String rcvHostId;
	private String moduleId;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	public String getFirstCardNo() {
		return firstCardNo;
	}
	public void setFirstCardNo(String firstCardNo) {
		this.firstCardNo = firstCardNo;
	}
	public String getLastCardNo() {
		return lastCardNo;
	}
	public void setLastCardNo(String lastCardNo) {
		this.lastCardNo = lastCardNo;
	}
	public String getTranBit() {
		return tranBit;
	}
	public void setTranBit(String tranBit) {
		this.tranBit = tranBit;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType.trim();
	}
	public String getRcvBankId() {
		return rcvBankId;
	}
	public void setRcvBankId(String rcvBankId) {
		this.rcvBankId = rcvBankId;
	}
	public String getRcvHostId() {
		return rcvHostId;
	}
	public void setRcvHostId(String rcvHostId) {
		this.rcvHostId = rcvHostId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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
