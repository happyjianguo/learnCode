/**
 *包名:com.pay.merchant.bean
 *描述:package com.pay.merchant.bean;
 */
package com.pay.merchant.bean;

import java.io.Serializable;
/**
 * MerchantFenrunOrgBean.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年4月26日
 * 描述:分润机构
 */
public class MerchantFenrunOrgBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String orgId;  //机构ID
	private String orgName;  //分润机构
	private String orgBin;  //卡bin
	private String orgPurposeAmt; //分润机构留底费率 _PURPOSE
	private String orgStat; //机构状态 0-正常  1-锁定
	private String createBy; //创建者
	private String createDate; //创建时间
	private String updateBy; //更新者
	private String updateDate; //更新时间
	
	public MerchantFenrunOrgBean(){
			
	}
	
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
	public String getOrgBin() {
		return orgBin;
	}
	public void setOrgBin(String orgBin) {
		this.orgBin = orgBin == null ? null : orgBin.trim();
	}
	public String getOrgPurposeAmt() {
		return orgPurposeAmt;
	}
	public void setOrgPurposeAmt(String orgPurposeAmt) {
		this.orgPurposeAmt = orgPurposeAmt == null ? null : orgPurposeAmt.trim();
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

