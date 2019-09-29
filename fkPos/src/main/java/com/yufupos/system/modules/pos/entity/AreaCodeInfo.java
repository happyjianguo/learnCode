package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 收单地址Entity
 * @author llg
 * @version 2017-04-19
 */
public class AreaCodeInfo extends DataEntity<AreaCodeInfo> {
	
	private static final long serialVersionUID = 1L;
	private String areaCode;		// 地区编码
	private String areaName;		// 中文名称
	private String parentAreaCode;		// 父地区编码
	
	public AreaCodeInfo() {
		super();
	}

	public AreaCodeInfo(String id){
		super(id);
	}

	@Length(min=1, max=4, message="地区编码长度必须介于 1 和 4 之间")
	@ExcelField(title="地区编码", align=2, sort=10)	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	@Length(min=1, max=50, message="中文名称长度必须介于 1 和 50 之间")
	@ExcelField(title="中文名称", align=2, sort=20)	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	@Length(min=0, max=4, message="父地区编码长度必须介于 0 和 4 之间")
	@ExcelField(title="父地区编码", align=2, sort=30)	
	public String getParentAreaCode() {
		return parentAreaCode;
	}

	public void setParentAreaCode(String parentAreaCode) {
		this.parentAreaCode = parentAreaCode;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
}