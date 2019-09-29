package cn.yufu.posp.cardtype.domain.model;

/**
 * CardtypeId entity. @author MyEclipse Persistence Tools
 */

public class CardtypeId1 implements java.io.Serializable {

	// Fields

	private String cardType;
	private String typeName;
	private String typeEname;
	
	private String queryCardType;
	private String queryTypeName;

	// Constructors

	public String getQueryCardType() {
		return queryCardType;
	}

	public void setQueryCardType(String queryCardType) {
		this.queryCardType = queryCardType;
	}

	public String getQueryTypeName() {
		return queryTypeName;
	}

	public void setQueryTypeName(String queryTypeName) {
		this.queryTypeName = queryTypeName;
	}

	/** default constructor */
	public CardtypeId1() {
	}

	/** full constructor */
	public CardtypeId1(String cardType, String typeName, String typeEname) {
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
		if (!(other instanceof CardtypeId1))
			return false;
		CardtypeId1 castOther = (CardtypeId1) other;

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