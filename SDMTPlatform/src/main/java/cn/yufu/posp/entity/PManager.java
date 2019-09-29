package cn.yufu.posp.entity;

import java.util.Date;



/**
 * 商户经理信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PManager{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String id;	//商户经理id
	private String managerName;		// 商户经理名称
	private String managerStatus;		// 状态
	private String managerArea;		// 区域
	private String managerRole;		// 角色
	private String fatherId;		// 上级ID
	private String managerTel;		// 联系电话
	private String managerEmail;		// 邮箱
	private String remarks;	// 备注
	private String createBy;	// 创建者
	private Date createDate;	// 创建日期
	private String updateBy;	// 更新者
	private Date updateDate;	// 更新日期
	private String delFlag; 	// 删除标记（0：正常；1：删除；2：审核）
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerStatus() {
		return managerStatus;
	}
	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}
	public String getManagerArea() {
		return managerArea;
	}
	public void setManagerArea(String managerArea) {
		this.managerArea = managerArea;
	}
	public String getManagerRole() {
		return managerRole;
	}
	public void setManagerRole(String managerRole) {
		this.managerRole = managerRole;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	public String getManagerTel() {
		return managerTel;
	}
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	@Override
	public String toString() {
		return "PManager [id=" + id + ", managerName=" + managerName + ", managerStatus=" + managerStatus
				+ ", managerArea=" + managerArea + ", managerRole=" + managerRole + ", fatherId=" + fatherId
				+ ", managerTel=" + managerTel + ", managerEmail=" + managerEmail + ", remarks=" + remarks
				+ ", createBy=" + createBy + ", createDate=" + createDate + ", updateBy=" + updateBy + ", updateDate="
				+ updateDate + ", delFlag=" + delFlag + "]";
	}
}