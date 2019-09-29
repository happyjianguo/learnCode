package cn.yufu.posp.terminalmanager.domain.model;

/**
 * EdcSwitchId entity. @author MyEclipse Persistence Tools
 */

public class EdcSwitchId implements java.io.Serializable {

	// Fields

	/**商户编号*/
	private String merchantId;
	
	/**终端编号*/
	private String terminalId;
	
	/**银行类型-编号*/
	private String bankType;
	
	

	
	
	/**商户名称*/
	private String merchantName;
	
	/**银行类型-名称*/
	private String bankTypeName;

	// Constructors

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getBankTypeName() {
		return bankTypeName;
	}

	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}

	/** default constructor */
	public EdcSwitchId() {
	}

	/** full constructor */
	public EdcSwitchId(String merchantId, String terminalId, String bankType
			) {//String othTerminalId
		this.merchantId = merchantId;
		this.terminalId = terminalId;
		this.bankType = bankType;
		//this.othTerminalId = othTerminalId;
	}

	// Property accessors

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

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

//	public String getOthTerminalId() {
//		return this.othTerminalId;
//	}
//
//	public void setOthTerminalId(String othTerminalId) {
//		this.othTerminalId = othTerminalId;
//	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof EdcSwitchId))
			return false;
		EdcSwitchId castOther = (EdcSwitchId) other;

		return ((this.getMerchantId() == castOther.getMerchantId()) || (this
				.getMerchantId() != null
				&& castOther.getMerchantId() != null && this.getMerchantId()
				.equals(castOther.getMerchantId())))
				&& ((this.getTerminalId() == castOther.getTerminalId()) || (this
						.getTerminalId() != null
						&& castOther.getTerminalId() != null && this
						.getTerminalId().equals(castOther.getTerminalId())))
				&& ((this.getBankType() == castOther.getBankType()) || (this
						.getBankType() != null
						&& castOther.getBankType() != null && this
						.getBankType().equals(castOther.getBankType())))
//				&& ((this.getOthTerminalId() == castOther.getOthTerminalId()) || (this
//						.getOthTerminalId() != null
//						&& castOther.getOthTerminalId() != null && this
//						.getOthTerminalId()
//						.equals(castOther.getOthTerminalId())))
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
		result = 37 * result
				+ (getBankType() == null ? 0 : this.getBankType().hashCode());
//		result = 37
//				* result
//				+ (getOthTerminalId() == null ? 0 : this.getOthTerminalId()
//						.hashCode());
		return result;
	}

}