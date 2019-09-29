package cn.yufu.posp.merchant.domain.model;

/**
 * MerchantSwitch entity. @author MyEclipse Persistence Tools
 */

public class MerchantSwitchModel implements java.io.Serializable {

	// Fields

	private String merchantId;//�̻����
	private String bankType;//��������
	private String othMerchantId;//�����̻����
	private String othMcc;//�����̻�����
	
	//�̻�����
	private String merchantName;
	//������������
	private String bankTypeName;
	//��ѯ����
	private String queryMerId;
	private String queryBank;
	
	//����
	private String bankId;

	// Constructors

	/** default constructor */
	public MerchantSwitchModel() {
	}

	/** minimal constructor */
	public MerchantSwitchModel(String merchantId) {
		this.merchantId = merchantId;
	}

	/** full constructor */
	public MerchantSwitchModel(String merchantId, String bankType,
			String othMerchantId, String othMcc) {
		this.merchantId = merchantId;
		this.bankType = bankType;
		this.othMerchantId = othMerchantId;
		this.othMcc = othMcc;
	}

	// Property accessors

	public String getQueryMerId() {
		return queryMerId;
	}

	public void setQueryMerId(String queryMerId) {
		this.queryMerId = queryMerId;
	}

	public String getQueryBank() {
		return queryBank;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public void setQueryBank(String queryBank) {
		this.queryBank = queryBank;
	}

	/*public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}*/

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getOthMerchantId() {
		return this.othMerchantId;
	}

	public void setOthMerchantId(String othMerchantId) {
		this.othMerchantId = othMerchantId;
	}

	public String getOthMcc() {
		return this.othMcc;
	}

	public void setOthMcc(String othMcc) {
		this.othMcc = othMcc;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

}