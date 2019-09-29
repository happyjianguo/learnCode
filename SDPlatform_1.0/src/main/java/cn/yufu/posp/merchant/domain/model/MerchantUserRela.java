package cn.yufu.posp.merchant.domain.model;

/**
 * MerchantUserRela entity. @author MyEclipse Persistence Tools
 */

public class MerchantUserRela implements java.io.Serializable {

	// Fields

	private String merchantUserId;
	private String merchantId;

	// Constructors

	/** default constructor */
	public MerchantUserRela() {
	}

	/** full constructor */
	public MerchantUserRela(String merchantUserId, String merchantId) {
		this.merchantUserId = merchantUserId;
		this.merchantId = merchantId;
	}

	// Property accessors

	public String getMerchantUserId() {
		return this.merchantUserId;
	}

	public void setMerchantUserId(String merchantUserId) {
		this.merchantUserId = merchantUserId;
	}

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MerchantUserRela))
			return false;
		MerchantUserRela castOther = (MerchantUserRela) other;

		return ((this.getMerchantUserId() == castOther.getMerchantUserId()) || (this.getMerchantUserId() != null && castOther.getMerchantUserId() != null && this
				.getMerchantUserId().equals(castOther.getMerchantUserId())))
				&& ((this.getMerchantId() == castOther.getMerchantId()) || (this.getMerchantId() != null && castOther.getMerchantId() != null && this
						.getMerchantId().equals(castOther.getMerchantId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getMerchantUserId() == null ? 0 : this.getMerchantUserId().hashCode());
		result = 37 * result + (getMerchantId() == null ? 0 : this.getMerchantId().hashCode());
		return result;
	}

}