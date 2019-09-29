package cn.yufu.posp.terminalmanager.domain.model;

/**
 * BanktypeId entity. @author MyEclipse Persistence Tools
 */

public class BanktypeId implements java.io.Serializable {

	// Fields

	private String bankType;
	private String typeName;
	private String typeEname;

	// Constructors

	/** default constructor */
	public BanktypeId() {
	}

	/** full constructor */
	public BanktypeId(String bankType, String typeName, String typeEname) {
		this.bankType = bankType;
		this.typeName = typeName;
		this.typeEname = typeEname;
	}

	// Property accessors

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
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
		if (!(other instanceof BanktypeId))
			return false;
		BanktypeId castOther = (BanktypeId) other;

		return ((this.getBankType() == castOther.getBankType()) || (this
				.getBankType() != null
				&& castOther.getBankType() != null && this.getBankType()
				.equals(castOther.getBankType())))
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
				+ (getBankType() == null ? 0 : this.getBankType().hashCode());
		result = 37 * result
				+ (getTypeName() == null ? 0 : this.getTypeName().hashCode());
		result = 37 * result
				+ (getTypeEname() == null ? 0 : this.getTypeEname().hashCode());
		return result;
	}

}