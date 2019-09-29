package cn.yufu.posp.usermanager.domain.model;

import java.util.Date;

/**
 * OaGroupInfo entity. @author MyEclipse Persistence Tools
 */
public class OaGroupInfo extends AbstractOaGroupInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OaGroupInfo() {
	}

	/** minimal constructor */
	public OaGroupInfo(OaGroupInfoId id) {
		super(id);
	}

	/** full constructor */
	public OaGroupInfo(OaGroupInfoId id, String groupDescription,
			String createUser, Date createTimestamp, String updateUser,
			Date updateTimestamp) {
		super(id, groupDescription, createUser, createTimestamp, updateUser,
				updateTimestamp);
	}

}
