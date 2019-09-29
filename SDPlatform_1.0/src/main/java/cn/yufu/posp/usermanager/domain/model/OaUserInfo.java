package cn.yufu.posp.usermanager.domain.model;

import java.util.Date;

/**
 * OaUserInfo entity. @author MyEclipse Persistence Tools
 */
public class OaUserInfo extends AbstractOaUserInfo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public OaUserInfo() {
	}

	/** minimal constructor */
	public OaUserInfo(OaUserInfoId id, String groupId, String status,
			String userType, String password, Long passwordAttempts,
			Date lastPasswordReset, Date passwordExpiry) {
		super(id, groupId, status, userType, password, passwordAttempts,
				lastPasswordReset, passwordExpiry);
	}

	/** full constructor */
	public OaUserInfo(OaUserInfoId id, String groupId, Date acctExpiry,
			String status, String userType, String userRefNo, String password,
			Long passwordAttempts, Date lastPasswordReset, Date passwordExpiry,
			String createUser, Date createTimestamp, String updateUser,
			Date updateTimestamp, Long passwordAttemptsLimit, String userName,
			String cardType, String loginMac, String loginIp) {
		super(id, groupId, acctExpiry, status, userType, userRefNo, password,
				passwordAttempts, lastPasswordReset, passwordExpiry,
				createUser, createTimestamp, updateUser, updateTimestamp,
				passwordAttemptsLimit, userName, cardType, loginMac, loginIp);
	}

}
