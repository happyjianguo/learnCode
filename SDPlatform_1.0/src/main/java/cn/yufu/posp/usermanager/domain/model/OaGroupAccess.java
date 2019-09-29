package cn.yufu.posp.usermanager.domain.model;

import java.util.Date;

/**
 * OaGroupAccess entity. @author MyEclipse Persistence Tools
 */
public class OaGroupAccess extends AbstractOaGroupAccess implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OaGroupAccess() {
	}

	/** minimal constructor */
	public OaGroupAccess(OaGroupAccessId id, String useCorporateDataOnly) {
		super(id, useCorporateDataOnly);
	}

	/** full constructor */
	public OaGroupAccess(OaGroupAccessId id, String useCorporateDataOnly,
			String accessRight, String createUser, Date createTimestamp,
			String updateUser, Date updateTimestamp) {
		super(id, useCorporateDataOnly, accessRight, createUser,
				createTimestamp, updateUser, updateTimestamp);
	}

}
