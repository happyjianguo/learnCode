package com.yufupos.system.modules.pos.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;
import com.yufupos.system.modules.sys.entity.User;

/**
 * POS机操作记录Entity
 * @author llg
 * @version 2017-04-05
 */
public class PPosActLog extends DataEntity<PPosActLog> {
	
	private static final long serialVersionUID = 1L;
	private String posSn;		// SN码
	private String merchantId;		// 商户编号
	private String terminalId;		// 终端号
	private String posActType;		// 操作类型
	private String posActMsg;		// 操作原因
	private String beginCreateDate;		// 开始 创建时间
	private String endCreateDate;		// 结束 创建时间
	
	private List<User> queryCreateByName;
	
	public PPosActLog() {
		super();
	}

	public PPosActLog(String id){
		super(id);
	}

	@Length(min=1, max=50, message="SN码长度必须介于 1 和 50 之间")
	@ExcelField(title="SN码", align=2, sort=20)	
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn;
	}
	
	@Length(min=0, max=15, message="商户编号长度必须介于 0 和 15 之间")
	@ExcelField(title="商户编号", align=2, sort=30)	
	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	@Length(min=0, max=16, message="终端号长度必须介于 0 和 16 之间")
	@ExcelField(title="终端号", align=2, sort=40)	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	
	@Length(min=0, max=10, message="操作类型长度必须介于 0 和 10 之间")
	@ExcelField(title="操作类型", align=2, sort=50,dictType="POS_ACT_TYPE")	
	public String getPosActType() {
		return posActType;
	}

	public void setPosActType(String posActType) {
		this.posActType = posActType;
	}
	
	@Length(min=0, max=500, message="操作原因长度必须介于 0 和 500 之间")
	@ExcelField(title="操作原因", align=2, sort=60)	
	public String getPosActMsg() {
		return posActMsg;
	}

	public void setPosActMsg(String posActMsg) {
		this.posActMsg = posActMsg;
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