package cn.yufu.posp.usermanager.domain.model;

/**
 * AbstractOaUserInfoId entity provides the base persistence definition of the
 * OaUserInfoId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaUserInfoId implements java.io.Serializable {

	// Fields

	private String clientId;
	private String userId;

	// Constructors

	/** default constructor */
	public AbstractOaUserInfoId() {
	}

	/** full constructor */
	public AbstractOaUserInfoId(String clientId, String userId) {
		this.clientId = clientId;
		this.userId = userId;
	}

	// Property accessors

	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractOaUserInfoId))
			return false;
		AbstractOaUserInfoId castOther = (AbstractOaUserInfoId) other;

		return ((this.getClientId() == castOther.getClientId()) || (this
				.getClientId() != null
				&& castOther.getClientId() != null && this.getClientId()
				.equals(castOther.getClientId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClientId() == null ? 0 : this.getClientId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}