package cn.yufu.posp.usermanager.domain.model;

/**
 * OaUserInfoId entity. @author MyEclipse Persistence Tools
 */
public class OaUserInfoId extends AbstractOaUserInfoId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OaUserInfoId() {
	}

	/** full constructor */
	public OaUserInfoId(String clientId, String userId) {
		super(clientId, userId);
	}

}
