package cn.yufu.posp.merchant.domain.model;

/**
 * <p>Title: MerchantBaseVO 商户</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * @author lf
 * @version 1.0
 */
public class MerchantBaseModel  implements java.io.Serializable {
	
	// Fields

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
	
	private String ccyType;
	private String lockMode;
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
	
	//商品类型名称
	private String mccName;
	//签约行名称
	private String bankName;
	
	//所属机构
	private String zbank;

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
	
	
	
	
	// Constructors

	

	public String getMccName() {
		return mccName;
	}

	public void setMccName(String mccName) {
		this.mccName = mccName;
	}

	/** default constructor */
	public MerchantBaseModel() {
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/** minimal constructor */
	public MerchantBaseModel(String merchantId, String mcc, String signBankId,
			String signHostId) {
		this.merchantId = merchantId;
		this.mcc = mcc;
		this.signBankId = signBankId;
		this.signHostId = signHostId;
	}

	/** full constructor */
	public MerchantBaseModel(String merchantId, String mcc, String merchantCname,
			String merchantEname, String abbrCname, String abbrEname,
			String addressChn, String addressEng, String cityCname,
			String cityEname, String telephone, String postCode, String fax,
			String manager, String settleMerchId, String signBankId,
			String signHostId, String zbank, String ccyType, String lockMode,
			String signDate, String merchantStat, String settleMode,
			String updateOper, String updateDate, String updateTime) {
		this.merchantId = merchantId;
		this.mcc = mcc;
		this.merchantCname = merchantCname;
		this.merchantEname = merchantEname;
		this.abbrCname = abbrCname;
		this.abbrEname = abbrEname;
		this.addressChn = addressChn;
		this.addressEng = addressEng;
		this.cityCname = cityCname;
		this.cityEname = cityEname;
		this.telephone = telephone;
		this.postCode = postCode;
		this.fax = fax;
		this.manager = manager;
		this.settleMerchId = settleMerchId;
		this.signBankId = signBankId;
		this.signHostId = signHostId;
		this.zbank = zbank;
		this.ccyType = ccyType;
		this.lockMode = lockMode;
		this.signDate = signDate;
		this.merchantStat = merchantStat;
		this.settleMode = settleMode;
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

	public String getRefundLimit() {
		return refundLimit;
	}

	public void setRefundLimit(String refundLimit) {
		this.refundLimit = refundLimit;
	}

	public String getRefundCheck() {
		return refundCheck;
	}

	public void setRefundCheck(String refundCheck) {
		this.refundCheck = refundCheck;
	}
	
	
}
