package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaConsoleMenuOptRelationId entity provides the base persistence
 * definition of the OaConsoleMenuOptRelationId entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractOaConsoleMenuOptRelationId implements
		java.io.Serializable {

	// Fields

	private String menuId;
	private String optNum;

	// Constructors

	/** default constructor */
	public AbstractOaConsoleMenuOptRelationId() {
	}

	/** full constructor */
	public AbstractOaConsoleMenuOptRelationId(String menuId, String optNum) {
		this.menuId = menuId;
		this.optNum = optNum;
	}

	// Property accessors

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getOptNum() {
		return this.optNum;
	}

	public void setOptNum(String optNum) {
		this.optNum = optNum;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOaConsoleMenuOptRelationId))
			return false;
		AbstractOaConsoleMenuOptRelationId castOther = (AbstractOaConsoleMenuOptRelationId) other;

		return ((this.getMenuId() == castOther.getMenuId()) || (this
				.getMenuId() != null
				&& castOther.getMenuId() != null && this.getMenuId().equals(
				castOther.getMenuId())))
				&& ((this.getOptNum() == castOther.getOptNum()) || (this
						.getOptNum() != null
						&& castOther.getOptNum() != null && this.getOptNum()
						.equals(castOther.getOptNum())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		result = 37 * result
				+ (getOptNum() == null ? 0 : this.getOptNum().hashCode());
		return result;
	}

}