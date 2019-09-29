package com.pay.merchant.struts.form;

import org.apache.struts.action.ActionForm;

public class MerchantOrgForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	
	private String orgId;  //机构ID
	private String orgName;  //分润机构
	private String orgRatio;  //分润比例
	private String orgSingleAmt; //每笔分润金额
	private String orgStat; //机构状态 0-正常  1-锁定
	private String createBy; //创建者
	private String createDate; //创建时间
	private String updateBy; //更新者
	private String updateDate; //更新时间
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : orgId.trim();
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}
	public String getOrgRatio() {
		return orgRatio;
	}
	public void setOrgRatio(String orgRatio) {
		this.orgRatio = orgRatio == null ? null : orgRatio.trim();
	}
	public String getOrgSingleAmt() {
		return orgSingleAmt;
	}
	public void setOrgSingleAmt(String orgSingleAmt) {
		this.orgSingleAmt = orgSingleAmt == null ? null : orgSingleAmt.trim();
	}
	public String getOrgStat() {
		return orgStat;
	}
	public void setOrgStat(String orgStat) {
		this.orgStat = orgStat == null ? null : orgStat.trim();
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate == null ? null : createDate.trim();
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy == null ? null : updateBy.trim();
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate == null ? null : updateDate.trim();
	}
	
}