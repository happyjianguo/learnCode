package cn.yufu.posp.merchant.domain.model;

/**
 * MerchantExtraId entity. @author MyEclipse Persistence Tools
 */

public class MerchantExtraModel implements java.io.Serializable {

	// Fields
	
	private String merchantId;
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

	// Constructors

	/** default constructor */
	public MerchantExtraModel() {
	}

	/** minimal constructor */
	public MerchantExtraModel(String merchantId) {
		this.merchantId = merchantId;
	}

	/** full constructor */
	public MerchantExtraModel(String merchantId, String sndName, String sndAcct,
			String sndBank, String rcvName, String rcvAcct1, String rcvAcct2,
			String rcvBank) {
		this.merchantId = merchantId;
		this.sndName = sndName;
		this.sndAcct = sndAcct;
		this.sndBank = sndBank;
		this.rcvName = rcvName;
		this.rcvAcct1 = rcvAcct1;
		this.rcvAcct2 = rcvAcct2;
		this.rcvBank = rcvBank;
	}

	// Property accessors

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getSndName() {
		return this.sndName;
	}

	public void setSndName(String sndName) {
		this.sndName = sndName;
	}

	public String getSndAcct() {
		return this.sndAcct;
	}

	public void setSndAcct(String sndAcct) {
		this.sndAcct = sndAcct;
	}

	public String getSndBank() {
		return this.sndBank;
	}

	public void setSndBank(String sndBank) {
		this.sndBank = sndBank;
	}

	public String getRcvName() {
		return this.rcvName;
	}

	public void setRcvName(String rcvName) {
		this.rcvName = rcvName;
	}

	public String getRcvAcct1() {
		return this.rcvAcct1;
	}

	public void setRcvAcct1(String rcvAcct1) {
		this.rcvAcct1 = rcvAcct1;
	}

	public String getRcvAcct2() {
		return this.rcvAcct2;
	}

	public void setRcvAcct2(String rcvAcct2) {
		this.rcvAcct2 = rcvAcct2;
	}

	public String getRcvBank() {
		return this.rcvBank;
	}

	public void setRcvBank(String rcvBank) {
		this.rcvBank = rcvBank;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MerchantExtraModel))
			return false;
		MerchantExtraModel castOther = (MerchantExtraModel) other;

		return ((this.getMerchantId() == castOther.getMerchantId()) || (this
				.getMerchantId() != null
				&& castOther.getMerchantId() != null && this.getMerchantId()
				.equals(castOther.getMerchantId())))
				&& ((this.getSndName() == castOther.getSndName()) || (this
						.getSndName() != null
						&& castOther.getSndName() != null && this.getSndName()
						.equals(castOther.getSndName())))
				&& ((this.getSndAcct() == castOther.getSndAcct()) || (this
						.getSndAcct() != null
						&& castOther.getSndAcct() != null && this.getSndAcct()
						.equals(castOther.getSndAcct())))
				&& ((this.getSndBank() == castOther.getSndBank()) || (this
						.getSndBank() != null
						&& castOther.getSndBank() != null && this.getSndBank()
						.equals(castOther.getSndBank())))
				&& ((this.getRcvName() == castOther.getRcvName()) || (this
						.getRcvName() != null
						&& castOther.getRcvName() != null && this.getRcvName()
						.equals(castOther.getRcvName())))
				&& ((this.getRcvAcct1() == castOther.getRcvAcct1()) || (this
						.getRcvAcct1() != null
						&& castOther.getRcvAcct1() != null && this
						.getRcvAcct1().equals(castOther.getRcvAcct1())))
				&& ((this.getRcvAcct2() == castOther.getRcvAcct2()) || (this
						.getRcvAcct2() != null
						&& castOther.getRcvAcct2() != null && this
						.getRcvAcct2().equals(castOther.getRcvAcct2())))
				&& ((this.getRcvBank() == castOther.getRcvBank()) || (this
						.getRcvBank() != null
						&& castOther.getRcvBank() != null && this.getRcvBank()
						.equals(castOther.getRcvBank())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMerchantId() == null ? 0 : this.getMerchantId()
						.hashCode());
		result = 37 * result
				+ (getSndName() == null ? 0 : this.getSndName().hashCode());
		result = 37 * result
				+ (getSndAcct() == null ? 0 : this.getSndAcct().hashCode());
		result = 37 * result
				+ (getSndBank() == null ? 0 : this.getSndBank().hashCode());
		result = 37 * result
				+ (getRcvName() == null ? 0 : this.getRcvName().hashCode());
		result = 37 * result
				+ (getRcvAcct1() == null ? 0 : this.getRcvAcct1().hashCode());
		result = 37 * result
				+ (getRcvAcct2() == null ? 0 : this.getRcvAcct2().hashCode());
		result = 37 * result
				+ (getRcvBank() == null ? 0 : this.getRcvBank().hashCode());
		return result;
	}

}