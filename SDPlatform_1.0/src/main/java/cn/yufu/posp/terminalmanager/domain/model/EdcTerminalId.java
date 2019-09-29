package cn.yufu.posp.terminalmanager.domain.model;


/**
 * EdcTerminalId entity. @author MyEclipse Persistence Tools
 */

public class EdcTerminalId implements java.io.Serializable {

	// Fields

	/**�̻����<p>�����е��̻�ͳһ��š�����������̻��������ϱ��С�</p>*/
    private String merchantId;
    
    /**�ն˱��<p>ȫ��ͳһ��š�������ATM�Ƚ���ʱ�����ն�</p>*/
    private String terminalId;
    
   
    
    

    /**�̻�����    ---ҳ����ʾ��----*/
    private String merchanName;
    
    

    // Constructors
   


	

	/** default constructor */
	public EdcTerminalId() {
	}
	/** full constructor */
	public EdcTerminalId(String merchantId, String terminalId) {
		super();
		this.merchantId = merchantId;
		this.terminalId = terminalId;
	}
	public String getMerchanName() {
		return merchanName;
	}

	public void setMerchanName(String merchanName) {
		this.merchanName = merchanName;
	}
	
	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String toString() {
		return "EdcTerminalId [merchantId=" + merchantId + ", terminalId=" + terminalId + ", merchanName=" + merchanName
				+ "]";
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EdcTerminalId))
			return false;
		EdcTerminalId castOther = (EdcTerminalId) other;

		return ((this.getMerchantId() == castOther.getMerchantId()) || (this
				.getMerchantId() != null
				&& castOther.getMerchantId() != null && this.getMerchantId()
				.equals(castOther.getMerchantId())))
				&& ((this.getTerminalId() == castOther.getTerminalId()) || (this
						.getTerminalId() != null
						&& castOther.getTerminalId() != null && this
						.getTerminalId().equals(castOther.getTerminalId())))
				;
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMerchantId() == null ? 0 : this.getMerchantId()
						.hashCode());
		result = 37
				* result
				+ (getTerminalId() == null ? 0 : this.getTerminalId()
						.hashCode());
		return result;
	}


}