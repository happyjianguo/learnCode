package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 厂商信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PFactory extends DataEntity<PFactory> {
	
	private static final long serialVersionUID = 1L;
	private String factoryName;		// 厂商名称
	private String factoryStatus;		// 公司状态
	private String manager;		// 联系经理
	private String managerMobile;		// 经理手机
	private String managerTel;		// 联系电话
	
	public PFactory() {
		super();
	}

	public PFactory(String id){
		super(id);
	}

	@Length(min=0, max=200, message="厂商名称长度必须介于 0 和 200 之间")
	@ExcelField(title="厂商名称", align=2, sort=20)	
	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	@Length(min=1, max=1, message="公司状态长度必须介于 1 和 1 之间")
	@ExcelField(title="公司状态", align=2, sort=30,dictType="FACTORY_STATUS")	
	public String getFactoryStatus() {
		return factoryStatus;
	}

	public void setFactoryStatus(String factoryStatus) {
		this.factoryStatus = factoryStatus;
	}
	
	@Length(min=0, max=50, message="联系经理长度必须介于 0 和 50 之间")
	@ExcelField(title="联系经理", align=2, sort=40)	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Length(min=0, max=15, message="经理手机长度必须介于 0 和 15 之间")
	@ExcelField(title="经理手机", align=2, sort=50)	
	public String getManagerMobile() {
		return managerMobile;
	}

	public void setManagerMobile(String managerMobile) {
		this.managerMobile = managerMobile;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	@ExcelField(title="联系电话", align=2, sort=60)	
	public String getManagerTel() {
		return managerTel;
	}

	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
}