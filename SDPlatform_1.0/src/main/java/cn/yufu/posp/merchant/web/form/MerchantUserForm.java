package cn.yufu.posp.merchant.web.form;

import cn.yufu.core.web.form.BaseForm;

/**
 * MerchantUser entity. @author MyEclipse Persistence Tools
 */

public class MerchantUserForm extends BaseForm {

	// Fields

	private String loginId;
	private String loginName;
	private String loginPassword;
	private String status;
	private String updateOper;
	private String updateDate;
	private String updateTime;

	private String merchantIds;
	private String pwdsub;

	// Constructors

	/** default constructor */
	public MerchantUserForm() {
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

	public String getPwdsub() {
		return pwdsub;
	}

	public void setPwdsub(String pwdsub) {
		this.pwdsub = pwdsub;
	}

}