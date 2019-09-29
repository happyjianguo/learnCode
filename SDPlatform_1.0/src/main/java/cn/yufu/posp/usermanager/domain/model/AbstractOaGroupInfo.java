package cn.yufu.posp.usermanager.domain.model;

import java.util.Date;

/**
 * AbstractOaGroupInfo entity provides the base persistence definition of the
 * OaGroupInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaGroupInfo implements java.io.Serializable {

	// Fields

	private OaGroupInfoId id;
	private String groupDescription;
	private String createUser;
	private Date createTimestamp;
	private String updateUser;
	private Date updateTimestamp;

	// Constructors

	/** default constructor */
	public AbstractOaGroupInfo() {
	}

	/** minimal constructor */
	public AbstractOaGroupInfo(OaGroupInfoId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractOaGroupInfo(OaGroupInfoId id, String groupDescription,
			String createUser, Date createTimestamp, String updateUser,
			Date updateTimestamp) {
		this.id = id;
		this.groupDescription = groupDescription;
		this.createUser = createUser;
		this.createTimestamp = createTimestamp;
		this.updateUser = updateUser;
		this.updateTimestamp = updateTimestamp;
	}

	// Property accessors

	public OaGroupInfoId getId() {
		return this.id;
	}

	public void setId(OaGroupInfoId id) {
		this.id = id;
	}

	public String getGroupDescription() {
		return this.groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTimestamp() {
		return this.createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}