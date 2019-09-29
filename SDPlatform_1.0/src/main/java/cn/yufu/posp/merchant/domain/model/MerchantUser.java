package cn.yufu.posp.merchant.domain.model;

/**
 * MerchantUser entity. @author MyEclipse Persistence Tools
 */

public class MerchantUser implements java.io.Serializable {

	// Fields

	private String loginId;
	private String loginName;
	private String loginPassword;
	private String status;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	private String merchantIds;

	// Constructors

	/** default constructor */
	public MerchantUser() {
	}

	/** minimal constructor */
	public MerchantUser(String loginId) {
		this.loginId = loginId;
	}

	/** full constructor */
	public MerchantUser(String loginId, String loginName, String loginPassword, String status, String updateOper, String updateDate, String updateTime) {
		this.loginId = loginId;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.status = status;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
	}

}