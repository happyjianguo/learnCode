package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaGroupAccessId entity provides the base persistence definition of
 * the OaGroupAccessId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaGroupAccessId implements java.io.Serializable {

	// Fields

	private String clientId;
	private String groupId;
	private String screenId;
	private String menuId;

	// Constructors

	/** default constructor */
	public AbstractOaGroupAccessId() {
	}

	/** full constructor */
	public AbstractOaGroupAccessId(String clientId, String groupId,
			String screenId, String menuId) {
		this.clientId = clientId;
		this.groupId = groupId;
		this.screenId = screenId;
		this.menuId = menuId;
	}

	// Property accessors

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getScreenId() {
		return this.screenId;
	}

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getMenuId() {
		return this.menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOaGroupAccessId))
			return false;
		AbstractOaGroupAccessId castOther = (AbstractOaGroupAccessId) other;

		return ((this.getClientId() == castOther.getClientId()) || (this
				.getClientId() != null
				&& castOther.getClientId() != null && this.getClientId()
				.equals(castOther.getClientId())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null
						&& castOther.getGroupId() != null && this.getGroupId()
						.equals(castOther.getGroupId())))
				&& ((this.getScreenId() == castOther.getScreenId()) || (this
						.getScreenId() != null
						&& castOther.getScreenId() != null && this
						.getScreenId().equals(castOther.getScreenId())))
				&& ((this.getMenuId() == castOther.getMenuId()) || (this
						.getMenuId() != null
						&& castOther.getMenuId() != null && this.getMenuId()
						.equals(castOther.getMenuId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClientId() == null ? 0 : this.getClientId().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		result = 37 * result
				+ (getScreenId() == null ? 0 : this.getScreenId().hashCode());
		result = 37 * result
				+ (getMenuId() == null ? 0 : this.getMenuId().hashCode());
		return result;
	}

}