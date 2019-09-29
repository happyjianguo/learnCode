package cn.yufu.posp.route.domain.model;

import java.math.BigDecimal;

/**
 * AdvRouteId entity. @author MyEclipse Persistence Tools
 */

public class AdvRouteId implements java.io.Serializable {

	// Fields
    private String rcvBankName;
    public String getRcvBankName() {
		return rcvBankName;
	}

	public void setRcvBankName(String rcvBankName) {
		this.rcvBankName = rcvBankName;
	}
	private BigDecimal tranType;
	private String cardType;
	private String rcvBankId;
	private String rcvHostId;
	private BigDecimal moduleId;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	private String cardTypeName;
	// Constructors

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	/** default constructor */
	public AdvRouteId() {
	}

	/** minimal constructor */
	public AdvRouteId(BigDecimal tranType, String cardType, String rcvBankId,
			String rcvHostId, BigDecimal moduleId) {
		this.tranType = tranType;
		this.cardType = cardType;
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.moduleId = moduleId;
	}

	/** full constructor */
	public AdvRouteId(BigDecimal tranType, String cardType, String rcvBankId,
			String rcvHostId, BigDecimal moduleId, String updateOper,
			String updateDate, String updateTime) {
		this.tranType = tranType;
		this.cardType = cardType;
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.moduleId = moduleId;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public BigDecimal getTranType() {
		return this.tranType;
	}

	public void setTranType(BigDecimal tranType) {
		this.tranType = tranType;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getRcvBankId() {
		return this.rcvBankId;
	}

	public void setRcvBankId(String rcvBankId) {
		this.rcvBankId = rcvBankId;
	}

	public String getRcvHostId() {
		return this.rcvHostId;
	}

	public void setRcvHostId(String rcvHostId) {
		this.rcvHostId = rcvHostId;
	}

	public BigDecimal getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(BigDecimal moduleId) {
		this.moduleId = moduleId;
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
		if (!(other instanceof AdvRouteId))
			return false;
		AdvRouteId castOther = (AdvRouteId) other;

		return ((this.getTranType() == castOther.getTranType()) || (this
				.getTranType() != null
				&& castOther.getTranType() != null && this.getTranType()
				.equals(castOther.getTranType())))
				&& ((this.getCardType() == castOther.getCardType()) || (this
						.getCardType() != null
						&& castOther.getCardType() != null && this
						.getCardType().equals(castOther.getCardType())))
				&& ((this.getRcvBankId() == castOther.getRcvBankId()) || (this
						.getRcvBankId() != null
						&& castOther.getRcvBankId() != null && this
						.getRcvBankId().equals(castOther.getRcvBankId())))
				&& ((this.getRcvHostId() == castOther.getRcvHostId()) || (this
						.getRcvHostId() != null
						&& castOther.getRcvHostId() != null && this
						.getRcvHostId().equals(castOther.getRcvHostId())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this
						.getModuleId() != null
						&& castOther.getModuleId() != null && this
						.getModuleId().equals(castOther.getModuleId())))
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

		result = 37 * result
				+ (getTranType() == null ? 0 : this.getTranType().hashCode());
		result = 37 * result
				+ (getCardType() == null ? 0 : this.getCardType().hashCode());
		result = 37 * result
				+ (getRcvBankId() == null ? 0 : this.getRcvBankId().hashCode());
		result = 37 * result
				+ (getRcvHostId() == null ? 0 : this.getRcvHostId().hashCode());
		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
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