package cn.yufu.posp.merchant.domain.model;

/**
 * <p>Title: MerchantBaseVO �̻�</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * @author lf
 * @version 1.0
 */
public class MerchantBaseModel  implements java.io.Serializable {
	
	// Fields

	/**�̻����*/
	private String merchantId;
	/**�̻�����*/
	private String mcc;
	/**����ȫ��*/
	private String merchantCname;
	/**Ӣ��ȫ��*/
	private String merchantEname;
	/**���ļ��*/
	private String abbrCname;
	/**Ӣ�ļ��*/
	private String abbrEname;
	/**�̻����ĵ�ַ*/
	private String addressChn;
	/**�̻�Ӣ�ĵ�ַ*/
	private String addressEng;
	/**�̻����ڳ���������*/
	private String cityCname;
	/**�̻����ڳ���Ӣ����*/
	private String cityEname;
	/**�绰����*/
	private String telephone;
	/**��������*/
	private String postCode;
	/**����*/
	private String fax;
	/**��ϵ��*/
	private String manager;
	/**�����̻����*/
	private String settleMerchId;
	/**ǩԼ���к�*/
	private String signBankId;
	/**ǩԼ��������*/
	private String signHostId;
	
	private String ccyType;
	private String lockMode;
	/**ǩԼ����*/
	private String signDate;
	/**�̻�״̬*/
	private String merchantStat;
	/**����ģʽ*/
	private String settleMode;
	/**���¹�Ա*/
	private String updateOper;
	/**��������*/
	private String updateDate;
	/**����ʱ��*/
	private String updateTime;
	
	//��Ʒ��������
	private String mccName;
	//ǩԼ������
	private String bankName;
	
	//��������
	private String zbank;

	/**����������*/
	private String sndName;

	/**�������ʺ�*/
	private String sndAcct;

	/**�����п�����*/
	private String sndBank;

	/**����������*/
	private String rcvName;

	/**�������ʺ�1*/
	private String rcvAcct1;

	/**�������ʺ�2*/
	private String rcvAcct2;

	/**�����п�����*/
	private String rcvBank;
	

	//�˻���Ϣ
	private String refundLimit;/*�˻����ƽ��*/
	private String refundCheck;/*�˻�����*/
	
	
	
	
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
