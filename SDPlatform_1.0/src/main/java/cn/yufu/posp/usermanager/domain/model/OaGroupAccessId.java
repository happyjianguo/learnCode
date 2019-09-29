package cn.yufu.posp.usermanager.domain.model;

/**
 * OaGroupAccessId entity. @author MyEclipse Persistence Tools
 */
public class OaGroupAccessId extends AbstractOaGroupAccessId implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OaGroupAccessId() {
	}

	/** full constructor */
	public OaGroupAccessId(String clientId, String groupId, String screenId,
			String menuId) {
		super(clientId, groupId, screenId, menuId);
	}

}
