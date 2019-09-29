package cn.yufu.posp.merchant.web.form;

import cn.yufu.core.web.form.BaseForm;

public class MerchantBoForm extends BaseForm {

	// Fields

	private String merchantId;
	private String mcc;
	private String merchantCname;
	private String merchantEname;
	private String abbrCname;
	private String abbrEname;
	private String addressChn;
	private String addressEng;
	private String cityCname;
	private String cityEname;
	private String telephone;
	private String postCode;
	private String fax;
	private String manager;
	private String settleMerchId;
	private String signBankId;
	private String signHostId;
	private String zbank;
	private String ccyType;
	private String lockMode;
	private String signDate;
	private String merchantStat;
	private String settleMode;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	private String status;
	private String reason;

	/**发送行名称*/
	private String sndName;

	/**发送行帐号*/
	private String sndAcct;

	/**发送行开户行*/
	private String sndBank;

	/**接收行名称*/
	private String rcvName;

	/**接收行帐号1*/
	private String rcvAcct1;

	/**接收行帐号2*/
	private String rcvAcct2;

	/**接收行开户行*/
	private String rcvBank;
	
	
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMerchantCname() {
		return this.merchantCname;
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}

	public String getMerchantEname() {
		return this.merchantEname;
	}

	public void setMerchantEname(String merchantEname) {
		this.merchantEname = merchantEname;
	}

	public String getAbbrCname() {
		return this.abbrCname;
	}

	public void setAbbrCname(String abbrCname) {
		this.abbrCname = abbrCname;
	}

	public String getAbbrEname() {
		return this.abbrEname;
	}

	public void setAbbrEname(String abbrEname) {
		this.abbrEname = abbrEname;
	}

	public String getAddressChn() {
		return this.addressChn;
	}

	public void setAddressChn(String addressChn) {
		this.addressChn = addressChn;
	}

	public String getAddressEng() {
		return this.addressEng;
	}

	public void setAddressEng(String addressEng) {
		this.addressEng = addressEng;
	}

	public String getCityCname() {
		return this.cityCname;
	}

	public void setCityCname(String cityCname) {
		this.cityCname = cityCname;
	}

	public String getCityEname() {
		return this.cityEname;
	}

	public void setCityEname(String cityEname) {
		this.cityEname = cityEname;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostCode() {
		return this.postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getSettleMerchId() {
		return this.settleMerchId;
	}

	public void setSettleMerchId(String settleMerchId) {
		this.settleMerchId = settleMerchId;
	}

	public String getSignBankId() {
		return this.signBankId;
	}

	public void setSignBankId(String signBankId) {
		this.signBankId = signBankId;
	}

	public String getSignHostId() {
		return this.signHostId;
	}

	public void setSignHostId(String signHostId) {
		this.signHostId = signHostId;
	}

	public String getZbank() {
		return this.zbank;
	}

	public void setZbank(String zbank) {
		this.zbank = zbank;
	}

	public String getCcyType() {
		return this.ccyType;
	}

	public void setCcyType(String ccyType) {
		this.ccyType = ccyType;
	}

	public String getLockMode() {
		return this.lockMode;
	}

	public void setLockMode(String lockMode) {
		this.lockMode = lockMode;
	}

	public String getSignDate() {
		return this.signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getMerchantStat() {
		return this.merchantStat;
	}

	public void setMerchantStat(String merchantStat) {
		this.merchantStat = merchantStat;
	}

	public String getSettleMode() {
		return this.settleMode;
	}

	public void setSettleMode(String settleMode) {
		this.settleMode = settleMode;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSndName() {
		return sndName;
	}

	public void setSndName(String sndName) {
		this.sndName = sndName;
	}

	public String getSndAcct() {
		return sndAcct;
	}

	public void setSndAcct(String sndAcct) {
		this.sndAcct = sndAcct;
	}

	public String getSndBank() {
		return sndBank;
	}

	public void setSndBank(String sndBank) {
		this.sndBank = sndBank;
	}

	public String getRcvName() {
		return rcvName;
	}

	public void setRcvName(String rcvName) {
		this.rcvName = rcvName;
	}

	public String getRcvAcct1() {
		return rcvAcct1;
	}

	public void setRcvAcct1(String rcvAcct1) {
		this.rcvAcct1 = rcvAcct1;
	}

	public String getRcvAcct2() {
		return rcvAcct2;
	}

	public void setRcvAcct2(String rcvAcct2) {
		this.rcvAcct2 = rcvAcct2;
	}

	public String getRcvBank() {
		return rcvBank;
	}

	public void setRcvBank(String rcvBank) {
		this.rcvBank = rcvBank;
	}

}
