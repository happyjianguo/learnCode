package cn.yufu.posp.merchant.web.form;

import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.core.web.form.BaseForm;

public class MerchantForm extends BaseForm{
	
	/**商户编号*/
	private String merchantId;

	/**商户类型*/
	private String mcc;

	/**中文全称*/
	private String merchantCname;

	/**英文全称*/
	private String merchantEname;

	/**中文简称*/
	private String abbrCname;

	/**英文简称*/
	private String abbrEname;

	/**商户中文地址*/
	private String addressChn;

	/**商户英文地址*/
	private String addressEng;

	/**商户所在城市中文名*/
	private String cityCname;
	
	/**商户所在城市英文名*/
	private String cityEname;

	/**电话号码*/
	private String telephone;

	/**邮政编码*/
	private String postCode;

	/**传真*/
	private String fax;

	/**联系人*/
	private String manager;

	/**清算商户编号*/
	private String settleMerchId;

	/**签约行行号*/
	private String signBankId;

	/**签约行主机号*/
	private String signHostId;

	/**所属机构*/
	private String zbank;

	/**签约日期*/
	private String signDate;

	/**商户状态*/
	private String merchantStat;
	
	/**清算模式*/
	private String settleMode;

	/**更新柜员*/
	private String updateOper;

	/**更新日期*/
	private String updateDate;

	/**更新时间*/
	private String updateTime;
	private String ccyType;
	private String lockMode;
	
	public String getSettleMerchId() {
		return settleMerchId;
	}

	public void setSettleMerchId(String settleMerchId) {
		this.settleMerchId = settleMerchId;
	}

	public String getLockMode() {
		return lockMode;
	}

	public void setLockMode(String lockMode) {
		this.lockMode = lockMode;
	}

	public String getCcyType() {
		return ccyType;
	}

	public void setCcyType(String ccyType) {
		this.ccyType = ccyType;
	}



	//查询条件
	private String queryMerid;
	private String queryMerstat;

	//附加资料
	private MerchantExtraModel eModel;
	public MerchantExtraModel geteModel() {
		return eModel;
	}

	public void seteModel(MerchantExtraModel eModel) {
		this.eModel = eModel;
	}
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
	

	//退货信息
	private String refundLimit;/*退货限制金额*/
	private String refundCheck;/*退货额检查*/
	
	public String getQueryMerid() {
		return queryMerid;
	}

	public void setQueryMerid(String queryMerid) {
		this.queryMerid = queryMerid;
	}

	public String getQueryMerstat() {
		return queryMerstat;
	}

	public void setQueryMerstat(String queryMerstate) {
		this.queryMerstat = queryMerstate;
	}

	
	
	//签约机构号
	private String bankId;
	//签约行主机号
	private String hostId;
	//签约机构名称
	private String bankName;
	
	//商户类型名称
	private String mccName;
	
	//终端号的数量
	private int tid;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMerchantCname() {
		return merchantCname;
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}

	public String getMerchantEname() {
		return merchantEname;
	}

	public void setMerchantEname(String merchantEname) {
		this.merchantEname = merchantEname;
	}

	public String getAbbrCname() {
		return abbrCname;
	}

	public void setAbbrCname(String abbrCname) {
		this.abbrCname = abbrCname;
	}

	public String getAbbrEname() {
		return abbrEname;
	}

	public void setAbbrEname(String abbrEname) {
		this.abbrEname = abbrEname;
	}

	public String getAddressChn() {
		return addressChn;
	}

	public void setAddressChn(String addressChn) {
		this.addressChn = addressChn;
	}

	public String getAddressEng() {
		return addressEng;
	}

	public void setAddressEng(String addressEng) {
		this.addressEng = addressEng;
	}

	public String getCityCname() {
		return cityCname;
	}

	public void setCityCname(String cityCname) {
		this.cityCname = cityCname;
	}

	public String getCityEname() {
		return cityEname;
	}

	public void setCityEname(String cityEname) {
		this.cityEname = cityEname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	

	public String getSignBankId() {
		return signBankId;
	}

	public void setSignBankId(String signBankId) {
		this.signBankId = signBankId;
	}

	public String getSignHostId() {
		return signHostId;
	}

	public void setSignHostId(String signHostId) {
		this.signHostId = signHostId;
	}

	public String getZbank() {
		return zbank;
	}

	public void setZbank(String zbank) {
		this.zbank = zbank;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getMerchantStat() {
		return merchantStat;
	}

	public void setMerchantStat(String merchantStat) {
		this.merchantStat = merchantStat;
	}

	public String getSettleMode() {
		return settleMode;
	}

	public void setSettleMode(String settleMode) {
		this.settleMode = settleMode;
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

	

	public String getRefundCheck() {
		return refundCheck;
	}

	public void setRefundCheck(String refundCheck) {
		this.refundCheck = refundCheck;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getHostId() {
		return hostId;
	}

	public void setHostId(String hostId) {
		this.hostId = hostId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMccName() {
		return mccName;
	}

	public void setMccName(String mccName) {
		this.mccName = mccName;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getRefundLimit() {
		return refundLimit;
	}

	public void setRefundLimit(String refundLimit) {
		this.refundLimit = refundLimit;
	}
}
