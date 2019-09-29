package com.yufupos.system.modules.pos.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * MCCEntity
 * @author llg
 * @version 2017-04-19
 */
public class MccParam extends DataEntity<MccParam> {
	
	private static final long serialVersionUID = 1L;
	private String mcc;		// mcc
	private String mccName;		// MCC名称
	private String mccEname;		// MCC英文名
	private String mccType;		// 类型
	
	public MccParam() {
		super();
	}

	public MccParam(String id){
		super(id);
	}

	@Length(min=1, max=4, message="mcc长度必须介于 1 和 4 之间")
	@ExcelField(title="mcc", align=2, sort=10)	
	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}
	
	@Length(min=1, max=30, message="MCC名称长度必须介于 1 和 30 之间")
	@ExcelField(title="MCC名称", align=2, sort=20)	
	public String getMccName() {
		return mccName;
	}

	public void setMccName(String mccName) {
		this.mccName = mccName;
	}
	
	@Length(min=0, max=30, message="MCC英文名长度必须介于 0 和 30 之间")
	@ExcelField(title="MCC英文名", align=2, sort=30)	
	public String getMccEname() {
		return mccEname;
	}

	public void setMccEname(String mccEname) {
		this.mccEname = mccEname;
	}
	
	@Length(min=1, max=1, message="类型长度必须介于 1 和 1 之间")
	@ExcelField(title="类型", align=2, sort=40)	
	public String getMccType() {
		return mccType;
	}

	public void setMccType(String mccType) {
		this.mccType = mccType;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
}