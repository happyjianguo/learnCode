package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * CAccount账户变更历史记录表Entity
 * @author 
 * @version 2016-08-24
 */
public class CAccountLog extends DataEntity<CAccountLog> {
	
	private static final long serialVersionUID = 1L;
	private String tranType;		//交易类型
	private String pan;				// 卡号
	private String accno;			// 账户号
	private String amtFlag;			//金额符号
	private String transferAmt;		// 调账金额
	private String beforeAvlbal;	// 调账前余额
	private String avlbal;			// 当前账户余额
	private String comments;		// 备注
	private String updateOper;		// 操作者
	private String ip;				// 操作ip
	private String updateLogDate;	// 日期
	private String updateLogTime;	// 时间
	
	public String getTranType() {
		return tranType;
	}
	public void setTranType(String tranType) {
		this.tranType = tranType;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getAmtFlag() {
		return amtFlag;
	}
	public void setAmtFlag(String amtFlag) {
		this.amtFlag = amtFlag;
	}
	public String getTransferAmt() {
		return transferAmt;
	}
	public void setTransferAmt(String transferAmt) {
		this.transferAmt = transferAmt;
	}
	public String getBeforeAvlbal() {
		return beforeAvlbal;
	}
	public void setBeforeAvlbal(String beforeAvlbal) {
		this.beforeAvlbal = beforeAvlbal;
	}
	public String getAvlbal() {
		return avlbal;
	}
	public void setAvlbal(String avlbal) {
		this.avlbal = avlbal;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUpdateOper() {
		return updateOper;
	}
	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUpdateLogDate() {
		return updateLogDate;
	}
	public void setUpdateLogDate(String updateLogDate) {
		this.updateLogDate = updateLogDate;
	}
	public String getUpdateLogTime() {
		return updateLogTime;
	}
	public void setUpdateLogTime(String updateLogTime) {
		this.updateLogTime = updateLogTime;
	}

}