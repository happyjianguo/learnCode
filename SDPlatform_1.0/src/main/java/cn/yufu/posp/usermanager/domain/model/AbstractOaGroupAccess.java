package cn.yufu.posp.usermanager.domain.model;

import java.util.Date;

/**
 * AbstractOaGroupAccess entity provides the base persistence definition of the
 * OaGroupAccess entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaGroupAccess implements java.io.Serializable {

	// Fields

	private OaGroupAccessId id;
	private String useCorporateDataOnly;
	private String accessRight;
	private String createUser;
	private Date createTimestamp;
	private String updateUser;
	private Date updateTimestamp;

	// Constructors

	/** default constructor */
	public AbstractOaGroupAccess() {
	}

	/** minimal constructor */
	public AbstractOaGroupAccess(OaGroupAccessId id, String useCorporateDataOnly) {
		this.id = id;
		this.useCorporateDataOnly = useCorporateDataOnly;
	}

	/** full constructor */
	public AbstractOaGroupAccess(OaGroupAccessId id,
			String useCorporateDataOnly, String accessRight, String createUser,
			Date createTimestamp, String updateUser, Date updateTimestamp) {
		this.id = id;
		this.useCorporateDataOnly = useCorporateDataOnly;
		this.accessRight = accessRight;
		this.createUser = createUser;
		this.createTimestamp = createTimestamp;
		this.updateUser = updateUser;
		this.updateTimestamp = updateTimestamp;
	}

	// Property accessors

	public OaGroupAccessId getId() {
		return this.id;
	}

	public void setId(OaGroupAccessId id) {
		this.id = id;
	}

	public String getUseCorporateDataOnly() {
		return this.useCorporateDataOnly;
	}

	public void setUseCorporateDataOnly(String useCorporateDataOnly) {
		this.useCorporateDataOnly = useCorporateDataOnly;
	}

	public String getAccessRight() {
		return this.accessRight;
	}

	public void setAccessRight(String accessRight) {
		this.accessRight = accessRight;
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