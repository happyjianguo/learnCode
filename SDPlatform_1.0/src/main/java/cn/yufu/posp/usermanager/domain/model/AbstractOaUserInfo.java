package cn.yufu.posp.usermanager.domain.model;

import java.util.Date;

/**
 * AbstractOaUserInfo entity provides the base persistence definition of the
 * OaUserInfo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractOaUserInfo implements java.io.Serializable {

	// Fields
private String userId;
	public String getUserId() {
	return userId;
}

public void setUserId(String userId) {
	this.userId = userId;
}
private String _status;
	public String get_status() {
	return _status;
}

public void set_status(String _status) {
	if("A".equals(_status)){
		this._status ="使用中";
	}
	if("N".equals(_status)){
		this._status ="未启用";
	}
	if("S".equals(_status)){
		this._status ="暂时停用";
	}
	if("T".equals(_status)){
		this._status ="已终止";
	}
	
}
	private OaUserInfoId id;
	private String groupId;
	private Date acctExpiry;
	private String status;
	private String userType;
	private String userRefNo;
	private String password;
	private Long passwordAttempts;
	private Date lastPasswordReset;
	private Date passwordExpiry;
	private String createUser;
	private Date createTimestamp;
	private String updateUser;
	private Date updateTimestamp;
	private Long passwordAttemptsLimit;
	private String userName;
	private String cardType;
	private String loginMac;
	private String loginIp;

	// Constructors

	/** default constructor */
	public AbstractOaUserInfo() {
	}

	/** minimal constructor */
	public AbstractOaUserInfo(OaUserInfoId id, String groupId, String status,
			String userType, String password, Long passwordAttempts,
			Date lastPasswordReset, Date passwordExpiry) {
		this.id = id;
		this.groupId = groupId;
		this.status = status;
		this.userType = userType;
		this.password = password;
		this.passwordAttempts = passwordAttempts;
		this.lastPasswordReset = lastPasswordReset;
		this.passwordExpiry = passwordExpiry;
	}

	/** full constructor */
	public AbstractOaUserInfo(OaUserInfoId id, String groupId, Date acctExpiry,
			String status, String userType, String userRefNo, String password,
			Long passwordAttempts, Date lastPasswordReset, Date passwordExpiry,
			String createUser, Date createTimestamp, String updateUser,
			Date updateTimestamp, Long passwordAttemptsLimit, String userName,
			String cardType, String loginMac, String loginIp) {
		this.id = id;
		this.groupId = groupId;
		this.acctExpiry = acctExpiry;
		this.status = status;
		this.userType = userType;
		this.userRefNo = userRefNo;
		this.password = password;
		this.passwordAttempts = passwordAttempts;
		this.lastPasswordReset = lastPasswordReset;
		this.passwordExpiry = passwordExpiry;
		this.createUser = createUser;
		this.createTimestamp = createTimestamp;
		this.updateUser = updateUser;
		this.updateTimestamp = updateTimestamp;
		this.passwordAttemptsLimit = passwordAttemptsLimit;
		this.userName = userName;
		this.cardType = cardType;
		this.loginMac = loginMac;
		this.loginIp = loginIp;
	}

	// Property accessors

	public OaUserInfoId getId() {
		return this.id;
	}

	public void setId(OaUserInfoId id) {
		this.id = id;
	}

	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public Date getAcctExpiry() {
		return this.acctExpiry;
	}

	public void setAcctExpiry(Date acctExpiry) {
		this.acctExpiry = acctExpiry;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
		set_status(status);
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserRefNo() {
		return this.userRefNo;
	}

	public void setUserRefNo(String userRefNo) {
		this.userRefNo = userRefNo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getPasswordAttempts() {
		return this.passwordAttempts;
	}

	public void setPasswordAttempts(Long passwordAttempts) {
		this.passwordAttempts = passwordAttempts;
	}

	public Date getLastPasswordReset() {
		return this.lastPasswordReset;
	}

	public void setLastPasswordReset(Date lastPasswordReset) {
		this.lastPasswordReset = lastPasswordReset;
	}

	public Date getPasswordExpiry() {
		return this.passwordExpiry;
	}

	public void setPasswordExpiry(Date passwordExpiry) {
		this.passwordExpiry = passwordExpiry;
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

	public Long getPasswordAttemptsLimit() {
		return this.passwordAttemptsLimit;
	}

	public void setPasswordAttemptsLimit(Long passwordAttemptsLimit) {
		this.passwordAttemptsLimit = passwordAttemptsLimit;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getLoginMac() {
		return this.loginMac;
	}

	public void setLoginMac(String loginMac) {
		this.loginMac = loginMac;
	}

	public String getLoginIp() {
		return this.loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

}