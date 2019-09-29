package cn.yufu.posp.merchant.web.form;

import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.core.web.form.BaseForm;

public class MerchantForm extends BaseForm{
	
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

	/**��������*/
	private String zbank;

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



	//��ѯ����
	private String queryMerid;
	private String queryMerstat;

	//��������
	private MerchantExtraModel eModel;
	public MerchantExtraModel geteModel() {
		return eModel;
	}

	public void seteModel(MerchantExtraModel eModel) {
		this.eModel = eModel;
	}
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

	
	
	//ǩԼ������
	private String bankId;
	//ǩԼ��������
	private String hostId;
	//ǩԼ��������
	private String bankName;
	
	//�̻���������
	private String mccName;
	
	//�ն˺ŵ�����
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
