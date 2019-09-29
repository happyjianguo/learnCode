package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 商户经理信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PManager extends DataEntity<PManager> {
	
	private static final long serialVersionUID = 1L;
	private String managerName;		// 商户经理名称
	private String managerStatus;		// 状态
	private String managerArea;		// 区域
	private String managerRole;		// 角色
	private String fatherId;		// 上级ID
	private String managerTel;		// 联系电话
	private String managerEmail;		// 邮箱
	
	public PManager() {
		super();
	}

	public PManager(String id){
		super(id);
	}

	@Length(min=0, max=200, message="商户经理名称长度必须介于 0 和 200 之间")
	@ExcelField(title="商户经理名称", align=2, sort=20)	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	@ExcelField(title="状态", align=2, sort=30,dictType="MANAGER_STATUS")	
	public String getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(String managerStatus) {
		this.managerStatus = managerStatus;
	}
	
	@Length(min=0, max=20, message="区域长度必须介于 0 和 20 之间")
	@ExcelField(title="区域", align=2, sort=40,dictType="MERCHANT_AREA")	
	public String getManagerArea() {
		return managerArea;
	}

	public void setManagerArea(String managerArea) {
		this.managerArea = managerArea;
	}
	
	@Length(min=1, max=1, message="角色长度必须介于 1 和 1 之间")
	@ExcelField(title="角色", align=2, sort=50,dictType="MANAGER_ROLE")	
	public String getManagerRole() {
		return managerRole;
	}

	public void setManagerRole(String managerRole) {
		this.managerRole = managerRole;
	}
	
	@Length(min=0, max=64, message="上级ID长度必须介于 0 和 64 之间")
	@ExcelField(title="上级", align=2, sort=60)	
	public String getFatherId() {
		return fatherId;
	}

	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	@ExcelField(title="联系电话", align=2, sort=70)	
	public String getManagerTel() {
		return managerTel;
	}

	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	
	@Length(min=0, max=50, message="邮箱长度必须介于 0 和 50 之间")
	@ExcelField(title="邮箱", align=2, sort=80)	
	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
}