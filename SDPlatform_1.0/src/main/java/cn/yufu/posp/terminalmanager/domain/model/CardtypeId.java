package cn.yufu.posp.terminalmanager.domain.model;

/**
 * CardtypeId entity. @author MyEclipse Persistence Tools
 */

public class CardtypeId implements java.io.Serializable {

	// Fields

	private String cardType;
	private String typeName;
	private String typeEname;

	// Constructors

	/** default constructor */
	public CardtypeId() {
	}

	/** full constructor */
	public CardtypeId(String cardType, String typeName, String typeEname) {
		this.cardType = cardType;
		this.typeName = typeName;
		this.typeEname = typeEname;
	}

	// Property accessors

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeEname() {
		return this.typeEname;
	}

	public void setTypeEname(String typeEname) {
		this.typeEname = typeEname;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof CardtypeId))
			return false;
		CardtypeId castOther = (CardtypeId) other;

		return ((this.getCardType() == castOther.getCardType()) || (this
				.getCardType() != null
				&& castOther.getCardType() != null && this.getCardType()
				.equals(castOther.getCardType())))
				&& ((this.getTypeName() == castOther.getTypeName()) || (this
						.getTypeName() != null
						&& castOther.getTypeName() != null && this
						.getTypeName().equals(castOther.getTypeName())))
				&& ((this.getTypeEname() == castOther.getTypeEname()) || (this
						.getTypeEname() != null
						&& castOther.getTypeEname() != null && this
						.getTypeEname().equals(castOther.getTypeEname())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCardType() == null ? 0 : this.getCardType().hashCode());
		result = 37 * result
				+ (getTypeName() == null ? 0 : this.getTypeName().hashCode());
		result = 37 * result
				+ (getTypeEname() == null ? 0 : this.getTypeEname().hashCode());
		return result;
	}

}