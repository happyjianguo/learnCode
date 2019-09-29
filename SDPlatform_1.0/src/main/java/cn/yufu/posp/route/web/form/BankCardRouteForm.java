package cn.yufu.posp.route.web.form;

import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

public class BankCardRouteForm extends BaseForm {
	private String iid;
	public String getIid() {
		return iid;
	}
	public void setIid(String iid) {
		this.iid = iid;
	}
	private String _bankType;
	private String _rcvBankId;
	public String get_bankType() {
		return _bankType;
	}
	public void set_bankType(String bankType) {
		_bankType = bankType;
	}
	public String get_rcvBankId() {
		return _rcvBankId;
	}
	public void set_rcvBankId(String rcvBankId) {
		_rcvBankId = rcvBankId;
	}
	private String bankType;
	private String cardType;
	private String rcvBankId;
	private String rcvHostId;
	private String moduleId;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
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
