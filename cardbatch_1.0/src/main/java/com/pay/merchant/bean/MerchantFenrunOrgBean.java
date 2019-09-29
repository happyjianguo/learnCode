/**
 *����:com.pay.merchant.bean
 *����:package com.pay.merchant.bean;
 */
package com.pay.merchant.bean;

import java.io.Serializable;
/**
 * MerchantFenrunOrgBean.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��4��26��
 * ����:�������
 */
public class MerchantFenrunOrgBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String orgId;  //����ID
	private String orgName;  //�������
	private String orgBin;  //��bin
	private String orgPurposeAmt; //����������׷��� _PURPOSE
	private String orgStat; //����״̬ 0-����  1-����
	private String createBy; //������
	private String createDate; //����ʱ��
	private String updateBy; //������
	private String updateDate; //����ʱ��
	
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

