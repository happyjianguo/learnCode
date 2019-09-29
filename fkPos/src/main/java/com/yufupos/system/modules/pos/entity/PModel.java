package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 型号信息Entity
 * @author llg
 * @version 2017-04-05
 */
public class PModel extends DataEntity<PModel> {
	
	private static final long serialVersionUID = 1L;
	private String modelId;		// 型号
	private String factoryId;		// 厂商ID
	private String factoryName;		// 厂商名称
	private String modelStatus;		//型号状态
	
	
	public PModel() {
		super();
	}

	public PModel(String id){
		super(id);
	}

	@Length(min=1, max=64, message="型号长度必须介于 1 和 64 之间")
	@ExcelField(title="型号", align=2, sort=10)	
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Length(min=1, max=64, message="厂商ID长度必须介于 1 和 64 之间")
	@ExcelField(title="厂商ID", align=2, sort=20)	
	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}
	
	@Length(min=0, max=200, message="厂商名称长度必须介于 0 和 200 之间")
	@ExcelField(title="厂商名称", align=2, sort=30)	
	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}
	
	@Length(min=1, max=1, message="状态长度必须介于 1 和 1 之间")
	@ExcelField(title="状态", align=2, sort=30,dictType="MANAGER_STATUS")	
	public String getModelStatus() {
		return modelStatus;
	}

	public void setModelStatus(String modelStatus) {
		this.modelStatus = modelStatus;
	}	
	
}