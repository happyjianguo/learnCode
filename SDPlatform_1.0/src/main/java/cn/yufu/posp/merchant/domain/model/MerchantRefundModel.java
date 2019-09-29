package cn.yufu.posp.merchant.domain.model;

/**
 * MerchantRefundId entity. @author MyEclipse Persistence Tools
 */

public class MerchantRefundModel implements java.io.Serializable {

	// Fields

	private String merchantId;
	/**退货限制金额*/
	private String refundLimit;
	/**退货额检查*/
	private String refundCheck;
	/**更新柜员*/
	private String updateOper;
	/**更新日期*/
	private String updateDate;
	/**更新时间*/
	private String updateTime;

	// Constructors

	/** default constructor */
	public MerchantRefundModel() {
	}

	/** minimal constructor */
	public MerchantRefundModel(String merchantId, String refundLimit,
			String refundCheck) {
		this.merchantId = merchantId;
		this.refundLimit = refundLimit;
		this.refundCheck = refundCheck;
	}

	/** full constructor */
	public MerchantRefundModel(String merchantId, String refundLimit,
			String refundCheck, String updateOper, String updateDate,
			String updateTime) {
		this.merchantId = merchantId;
		this.refundLimit = refundLimit;
		this.refundCheck = refundCheck;
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

	public String getRefundLimit() {
		return this.refundLimit;
	}

	public void setRefundLimit(String refundLimit) {
		this.refundLimit = refundLimit;
	}

	public String getRefundCheck() {
		return this.refundCheck;
	}

	public void setRefundCheck(String refundCheck) {
		this.refundCheck = refundCheck;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MerchantRefundModel))
			return false;
		MerchantRefundModel castOther = (MerchantRefundModel) other;

		return ((this.getMerchantId() == castOther.getMerchantId()) || (this
				.getMerchantId() != null
				&& castOther.getMerchantId() != null && this.getMerchantId()
				.equals(castOther.getMerchantId())))
				&& ((this.getRefundLimit() == castOther.getRefundLimit()) || (this
						.getRefundLimit() != null
						&& castOther.getRefundLimit() != null && this
						.getRefundLimit().equals(castOther.getRefundLimit())))
				&& ((this.getRefundCheck() == castOther.getRefundCheck()) || (this
						.getRefundCheck() != null
						&& castOther.getRefundCheck() != null && this
						.getRefundCheck().equals(castOther.getRefundCheck())))
				&& ((this.getUpdateOper() == castOther.getUpdateOper()) || (this
						.getUpdateOper() != null
						&& castOther.getUpdateOper() != null && this
						.getUpdateOper().equals(castOther.getUpdateOper())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this
						.getUpdateDate() != null
						&& castOther.getUpdateDate() != null && this
						.getUpdateDate().equals(castOther.getUpdateDate())))
				&& ((this.getUpdateTime() == castOther.getUpdateTime()) || (this
						.getUpdateTime() != null
						&& castOther.getUpdateTime() != null && this
						.getUpdateTime().equals(castOther.getUpdateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getMerchantId() == null ? 0 : this.getMerchantId()
						.hashCode());
		result = 37
				* result
				+ (getRefundLimit() == null ? 0 : this.getRefundLimit()
						.hashCode());
		result = 37
				* result
				+ (getRefundCheck() == null ? 0 : this.getRefundCheck()
						.hashCode());
		result = 37
				* result
				+ (getUpdateOper() == null ? 0 : this.getUpdateOper()
						.hashCode());
		result = 37
				* result
				+ (getUpdateDate() == null ? 0 : this.getUpdateDate()
						.hashCode());
		result = 37
				* result
				+ (getUpdateTime() == null ? 0 : this.getUpdateTime()
						.hashCode());
		return result;
	}

}