package cn.yufu.posp.route.domain.model;

import java.math.BigDecimal;

/**
 * CardRouteId entity. @author MyEclipse Persistence Tools
 */

public class CardRouteId implements java.io.Serializable {

	// Fields
    private String rcvBankName;
    public String getRcvBankName() {
		return rcvBankName;
	}

	public void setRcvBankName(String rcvBankName) {
		this.rcvBankName = rcvBankName;
	}
	private String firstCardNo;
	private String lastCardNo;
	private String tranBit;
	private String cardType;
	private String cardTypeName;
	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	private String rcvBankId;
	private String rcvHostId;
	private BigDecimal moduleId;
	private String updateOper;
	private String updateDate;
	private String updateTime;

	// Constructors

	/** default constructor */
	public CardRouteId() {
	}

	/** minimal constructor */
	public CardRouteId(String firstCardNo, String lastCardNo, String tranBit,
			String cardType, String rcvBankId, String rcvHostId,
			BigDecimal moduleId) {
		this.firstCardNo = firstCardNo;
		this.lastCardNo = lastCardNo;
		this.tranBit = tranBit;
		this.cardType = cardType;
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.moduleId = moduleId;
	}

	/** full constructor */
	public CardRouteId(String firstCardNo, String lastCardNo, String tranBit,
			String cardType, String rcvBankId, String rcvHostId,
			BigDecimal moduleId, String updateOper, String updateDate,
			String updateTime) {
		this.firstCardNo = firstCardNo;
		this.lastCardNo = lastCardNo;
		this.tranBit = tranBit;
		this.cardType = cardType;
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.moduleId = moduleId;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getFirstCardNo() {
		return this.firstCardNo;
	}

	public void setFirstCardNo(String firstCardNo) {
		this.firstCardNo = firstCardNo;
	}

	public String getLastCardNo() {
		return this.lastCardNo;
	}

	public void setLastCardNo(String lastCardNo) {
		this.lastCardNo = lastCardNo;
	}

	public String getTranBit() {
		return this.tranBit;
	}

	public void setTranBit(String tranBit) {
		this.tranBit = tranBit;
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
		if (!(other instanceof CardRouteId))
			return false;
		CardRouteId castOther = (CardRouteId) other;

		return ((this.getFirstCardNo() == castOther.getFirstCardNo()) || (this
				.getFirstCardNo() != null
				&& castOther.getFirstCardNo() != null && this.getFirstCardNo()
				.equals(castOther.getFirstCardNo())))
				&& ((this.getLastCardNo() == castOther.getLastCardNo()) || (this
						.getLastCardNo() != null
						&& castOther.getLastCardNo() != null && this
						.getLastCardNo().equals(castOther.getLastCardNo())))
				&& ((this.getTranBit() == castOther.getTranBit()) || (this
						.getTranBit() != null
						&& castOther.getTranBit() != null && this.getTranBit()
						.equals(castOther.getTranBit())))
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

		result = 37
				* result
				+ (getFirstCardNo() == null ? 0 : this.getFirstCardNo()
						.hashCode());
		result = 37
				* result
				+ (getLastCardNo() == null ? 0 : this.getLastCardNo()
						.hashCode());
		result = 37 * result
				+ (getTranBit() == null ? 0 : this.getTranBit().hashCode());
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