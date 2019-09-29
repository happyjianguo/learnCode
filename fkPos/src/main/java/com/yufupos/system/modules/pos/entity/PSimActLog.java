package com.yufupos.system.modules.pos.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;
import com.yufupos.system.modules.sys.entity.User;

/**
 * SIM卡操作记录Entity
 * @author llg
 * @version 2017-04-05
 */
public class PSimActLog extends DataEntity<PSimActLog> {
	
	private static final long serialVersionUID = 1L;
	private String simId;		// SIM卡ID
	private String posSn;		// SN码
	private String simActType;		// 操作类型
	private String simActMsg;		// 操作原因
	private String beginCreateDate;		// 开始 创建时间
	private String endCreateDate;		// 结束 创建时间
	
	private List<User> queryCreateByName;
	
	public PSimActLog() {
		super();
	}

	public PSimActLog(String id){
		super(id);
	}

	@Length(min=1, max=64, message="SIM卡ID长度必须介于 1 和 64 之间")
	@ExcelField(title="SIM卡ID", align=2, sort=20)	
	public String getSimId() {
		return simId;
	}

	public void setSimId(String simId) {
		this.simId = simId;
	}
	
	@Length(min=0, max=50, message="SN码长度必须介于 0 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=30)	
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=10, message="操作类型长度必须介于 0 和 10 之间")
	@ExcelField(title="操作类型", align=2, sort=40,dictType="SIM_ACT_TYPE")	
	public String getSimActType() {
		return simActType;
	}

	public void setSimActType(String simActType) {
		this.simActType = simActType;
	}
	
	@Length(min=0, max=500, message="操作原因长度必须介于 0 和 500 之间")
	@ExcelField(title="操作原因", align=2, sort=50)	
	public String getSimActMsg() {
		return simActMsg;
	}

	public void setSimActMsg(String simActMsg) {
		this.simActMsg = simActMsg;
	}
	
	@ExcelField(title="创建时间", type=0, align=1, sort=999)
	public Date getCreateDate() {
		return createDate;
	}	
	
	public String getBeginCreateDate() {
		return beginCreateDate;
	}

	public void setBeginCreateDate(String beginCreateDate) {
		this.beginCreateDate = beginCreateDate;
	}
	
	public String getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(String endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public List<User> getQueryCreateByName() {
		return queryCreateByName;
	}

	public void setQueryCreateByName(List<User> queryCreateByName) {
		this.queryCreateByName = queryCreateByName;
	}
	
}