package cn.yufu.posp.usermanager.web.form;

import java.util.Date;

import cn.yufu.core.web.form.BaseForm;

public class OaUserForm extends BaseForm{
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public Date getAcctExpiry() {
		return acctExpiry;
	}
	public void setAcctExpiry(Date acctExpiry) {
		this.acctExpiry = acctExpiry;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserRefNo() {
		return userRefNo;
	}
	public void setUserRefNo(String userRefNo) {
		this.userRefNo = userRefNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getPasswordAttempts() {
		return passwordAttempts;
	}
	public void setPasswordAttempts(Long passwordAttempts) {
		this.passwordAttempts = passwordAttempts;
	}
	public Date getLastPasswordReset() {
		return lastPasswordReset;
	}
	public void setLastPasswordReset(String lastPasswordReset) {
		//this.lastPasswordReset =  new Date();
		//if(lastPasswordReset!=null){
		//this.lastPasswordReset =  new Date(lastPasswordReset);
		//}
	}
	public String getPasswordExpiry() {
		return passwordExpiry;
	}
	public void setPasswordExpiry(String passwordExpiry) {
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.passwordExpiry =passwordExpiry;
		//if(passwordExpiry!=null){
			//this.passwordExpiry = new Date();
		//}
		
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
//	public Date getCreateTimestamp() {
//		return createTimestamp;
//	}
//	public void setCreateTimestamp(Date createTimestamp) {
//		this.createTimestamp = createTimestamp;
//	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
//	public Date getUpdateTimestamp() {
//		return updateTimestamp;
//	}
//	public void setUpdateTimestamp(Date updateTimestamp) {
//		this.updateTimestamp = updateTimestamp;
//	}
	public Long getPasswordAttemptsLimit() {
		return passwordAttemptsLimit;
	}
	public void setPasswordAttemptsLimit(Long passwordAttemptsLimit) {
		this.passwordAttemptsLimit = passwordAttemptsLimit;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getLoginMac() {
		return loginMac;
	}
	public void setLoginMac(String loginMac) {
		this.loginMac = loginMac;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	private String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	private String groupId;
	private String clientId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	private Date acctExpiry;
	private String status;
	private String userType;
	private String userRefNo;
	private String password;
	private String pwdsub;
	public String getPwdsub() {
		return pwdsub;
	}
	public void setPwdsub(String pwdsub) {
		this.pwdsub = pwdsub;
	}
	private Long passwordAttempts;
	private Date lastPasswordReset;
	private String passwordExpiry;
	private String createUser;

	private String updateUser;

	private Long passwordAttemptsLimit;
	private String userName;
	private String cardType;
	private String loginMac;
	private String loginIp;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
