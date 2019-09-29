package com.yufupos.system.modules.pos.entity;

import com.yufupos.system.common.persistence.DataEntity;
import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 商户终端入账银行账号信息Entity
 * @author ZQK
 * @version 2018-07-03
 */
public class MerchTerminalAccNo extends DataEntity<MerchTerminalAccNo> {
	private static final long serialVersionUID = 1L;
	private String mechno;			// 商户号
	private String termcode;		// 终端号
	private String accno;			// 商户结算账号
	private String accName;			// 商户结算账号名称
	
	public MerchTerminalAccNo() {
		super();
	}

	public MerchTerminalAccNo(String id){
		super(id);
	}
	
	@ExcelField(title="商户号", align=2, sort=30)	
	public String getMechno() {
		return mechno;
	}

	public void setMechno(String mechno) {
		this.mechno = mechno;
	}

	@ExcelField(title="终端号", align=2, sort=40)	
	public String getTermcode() {
		return termcode;
	}

	public void setTermcode(String termcode) {
		this.termcode = termcode;
	}

	@ExcelField(title="商户结算账号", align=2, sort=60)	
	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}
	
	@ExcelField(title="商户结算账号名称", align=2, sort=70)	
	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

}