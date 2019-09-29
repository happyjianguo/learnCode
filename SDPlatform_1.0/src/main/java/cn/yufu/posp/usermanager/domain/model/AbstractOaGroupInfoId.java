package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaGroupInfoId entity provides the base persistence definition of the
 * OaGroupInfoId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaGroupInfoId implements java.io.Serializable {

	// Fields

	private String clientId;
	private String groupId;

	// Constructors

	/** default constructor */
	public AbstractOaGroupInfoId() {
	}

	/** full constructor */
	public AbstractOaGroupInfoId(String clientId, String groupId) {
		this.clientId = clientId;
		this.groupId = groupId;
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

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOaGroupInfoId))
			return false;
		AbstractOaGroupInfoId castOther = (AbstractOaGroupInfoId) other;

		return ((this.getClientId() == castOther.getClientId()) || (this
				.getClientId() != null
				&& castOther.getClientId() != null && this.getClientId()
				.equals(castOther.getClientId())))
				&& ((this.getGroupId() == castOther.getGroupId()) || (this
						.getGroupId() != null
						&& castOther.getGroupId() != null && this.getGroupId()
						.equals(castOther.getGroupId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClientId() == null ? 0 : this.getClientId().hashCode());
		result = 37 * result
				+ (getGroupId() == null ? 0 : this.getGroupId().hashCode());
		return result;
	}

}