package cn.yufu.system.modules.cortexs.entity;

import cn.yufu.system.common.persistence.DataEntity;

/**
 * TLOG交易类型Entity
 * @author LLG
 * @version 2016-08-24
 */
public class CAccount extends DataEntity<CAccount> {
	
	private static final long serialVersionUID = 1L;
	private String pan;				// 卡号
	private String accno;			// 账户号
	private String typecode;		// 账户类型
	private String currcode;		// 币种
	private String avlbal;			// 当前账户余额
	private String amtFlag;			//金额符号
	private String transferAmt;	//调账金额
	private String comments;		//备注
	private String update_date;			//日期
	private String update_time;	//时间
	private String update_oper;		//操作者
	private String ip;		//操作ip
				
	
	public CAccount() {
		super();
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



	public String getTypecode() {
		return typecode;
	}



	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}



	public String getCurrcode() {
		return currcode;
	}



	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}






	public String getAvlbal() {
		return avlbal;
	}



	public void setAvlbal(String avlbal) {
		this.avlbal = avlbal;
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



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public String getUpdate_date() {
		return update_date;
	}



	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}



	public String getUpdate_time() {
		return update_time;
	}



	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}



	public String getUpdate_oper() {
		return update_oper;
	}



	public void setUpdate_oper(String update_oper) {
		this.update_oper = update_oper;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}

}