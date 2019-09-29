package com.yufupos.system.modules.pos.entity;

import org.hibernate.validator.constraints.Length;

import com.yufupos.system.common.utils.excel.annotation.ExcelField;

/**
 * 终端POS机关联entity
 * 
 * @author llg
 * @version 2017-04-05
 */
public class EdcTerminalPosExcel {
	private static final long serialVersionUID = 1L;
	
	private String terminalId; 	 	// 终端号
	private String posSn; 		 	//SN码
	private String outstockDate; 	// 出库时间
	private String outstockBy; 		// 出库者\
	private String setAddr; 		//安装地点
	
	public EdcTerminalPosExcel() {
		super();
	}

	@Length(min = 0, max = 16, message = "终端号长度必须介于 0 和 16 之间")
	@ExcelField(title = "终端号", align = 2, sort = 10)
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId == null ? null : terminalId.trim();
	}

	@Length(min = 1, max = 50, message = "SN码长度必须介于 1 和 50 之间")
	@ExcelField(title = "SN码", align = 2, sort = 20)
	public String getPosSn() {
		return posSn;
	}

	public void setPosSn(String posSn) {
		this.posSn = posSn == null ? null : posSn.trim();
	}

	@Length(min = 0, max = 8, message = "出库时间长度必须介于 0 和 8 之间")
	@ExcelField(title = "出库时间", align = 2, sort = 30)
	public String getOutstockDate() {
		return outstockDate;
	}

	public void setOutstockDate(String outstockDate) {
		this.outstockDate = outstockDate == null ? null : outstockDate.trim();
	}

	@Length(min = 0, max = 64, message = "出库者长度必须介于 0 和 64 之间")
	@ExcelField(title = "出库者", align = 2, sort = 40)
	public String getOutstockBy() {
		return outstockBy;
	}

	public void setOutstockBy(String outstockBy) {
		this.outstockBy = outstockBy == null ? null : outstockBy.trim();
	}

	@Length(min = 0, max = 30, message = "安装地点长度必须介于 0 和 30 之间")
	@ExcelField(title = "安装地点", align = 2, sort = 50)
	public String getSetAddr() {
		return setAddr;
	}

	public void setSetAddr(String setAddr) {
		this.setAddr = setAddr == null ? null : setAddr.trim();
	}

}