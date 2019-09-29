package cn.yufu.posp.route.domain.model;

import java.math.BigDecimal;

/**
 * DefaultRouteId entity. @author MyEclipse Persistence Tools
 */

public class DefaultRouteId implements java.io.Serializable {

	// Fields
    private String rcvBankName;
    public String getRcvBankName() {
		return rcvBankName;
	}

	public void setRcvBankName(String rcvBankName) {
		this.rcvBankName = rcvBankName;
	}
	private String rcvBankId;
	private String rcvHostId;
	private BigDecimal moduleId;
	private String updateOper;
	private String updateDate;
	private String updateTime;

	// Constructors

	/** default constructor */
	public DefaultRouteId() {
	}

	/** minimal constructor */
	public DefaultRouteId(String rcvBankId, String rcvHostId,
			BigDecimal moduleId) {
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.moduleId = moduleId;
	}

	/** full constructor */
	public DefaultRouteId(String rcvBankId, String rcvHostId,
			BigDecimal moduleId, String updateOper, String updateDate,
			String updateTime) {
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.moduleId = moduleId;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

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
		if (!(other instanceof DefaultRouteId))
			return false;
		DefaultRouteId castOther = (DefaultRouteId) other;

		return ((this.getRcvBankId() == castOther.getRcvBankId()) || (this
				.getRcvBankId() != null
				&& castOther.getRcvBankId() != null && this.getRcvBankId()
				.equals(castOther.getRcvBankId())))
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