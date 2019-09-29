/**
 * TestCodeVerify.java
 * 版权所有(C) 2015 北京坚石诚信科技有限公司
 * 创建:YangFan 2015-7-16
 */
package cn.com.jansh.ebank.ife.ibank.entity;

import java.io.Serializable;

/**
 *	功能: 数据库TallyFlowList表映射类<br>
 * @author YangFan
 * @version 1.0
 */
public class TallyFlowList implements Serializable {

	private static final long serialVersionUID = 2095749670381921873L;
	
	private long tallyId;
	private long businessId;
	private int businessType;
	private String accNo;
	private long credit;
	private String tradeCode;
	private byte tradeState;
	
	private String cltSeqNo;
	private String cltDate;
	private String cltTime;
	
	private String hostSeqNo;
	private String hostDate;
	private String hostTime;
	private String hostRespond;
	private String hostErrCode;
	private String hostErrMsg;
	
	private String cltSeqNoUndo;
	private String cltDateUndo;
	private String cltTimeUndo;
	
	private String hostSeqNoUndo;
	private String hostDateUndo;
	private String hostTimeUndo;
	private String hostRespondUndo;
	private String hostErrCodeUndo;
	private String hostErrMsgUndo;

	private byte tallyMode;
	private byte chkState;
	private String chkMsg;
	
	public long getTallyId() {
		return tallyId;
	}
	public void setTallyId(long tallyId) {
		this.tallyId = tallyId;
	}
	public long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}
	public int getBusinessType() {
		return businessType;
	}
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}
	public long getCredit() {
		return credit;
	}
	public void setCredit(long credit) {
		this.credit = credit;
	}
	public String getTradeCode() {
		return tradeCode;
	}
	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}
	public byte getTradeState() {
		return tradeState;
	}
	public void setTradeState(byte tradeState) {
		this.tradeState = tradeState;
	}
	public String getCltSeqNo() {
		return cltSeqNo;
	}
	public void setCltSeqNo(String cltSeqNo) {
		this.cltSeqNo = cltSeqNo;
	}
	public String getHostSeqNo() {
		return hostSeqNo;
	}
	public void setHostSeqNo(String hostSeqNo) {
		this.hostSeqNo = hostSeqNo;
	}
	public String getHostRespond() {
		return hostRespond;
	}
	public void setHostRespond(String hostRespond) {
		this.hostRespond = hostRespond;
	}
	public String getHostErrCode() {
		return hostErrCode;
	}
	public void setHostErrCode(String hostErrCode) {
		this.hostErrCode = hostErrCode;
	}
	public String getHostErrMsg() {
		return hostErrMsg;
	}
	public void setHostErrMsg(String hostErrMsg) {
		this.hostErrMsg = hostErrMsg;
	}
	public String getCltSeqNoUndo() {
		return cltSeqNoUndo;
	}
	public void setCltSeqNoUndo(String cltSeqNoUndo) {
		this.cltSeqNoUndo = cltSeqNoUndo;
	}
	public String getHostSeqNoUndo() {
		return hostSeqNoUndo;
	}
	public void setHostSeqNoUndo(String hostSeqNoUndo) {
		this.hostSeqNoUndo = hostSeqNoUndo;
	}
	public String getHostRespondUndo() {
		return hostRespondUndo;
	}
	public void setHostRespondUndo(String hostRespondUndo) {
		this.hostRespondUndo = hostRespondUndo;
	}
	public String getHostErrCodeUndo() {
		return hostErrCodeUndo;
	}
	public void setHostErrCodeUndo(String hostErrCodeUndo) {
		this.hostErrCodeUndo = hostErrCodeUndo;
	}
	public String getHostErrMsgUndo() {
		return hostErrMsgUndo;
	}
	public void setHostErrMsgUndo(String hostErrMsgUndo) {
		this.hostErrMsgUndo = hostErrMsgUndo;
	}
	public byte getTallyMode() {
		return tallyMode;
	}
	public void setTallyMode(byte tallyMode) {
		this.tallyMode = tallyMode;
	}
	public byte getChkState() {
		return chkState;
	}
	public void setChkState(byte chkState) {
		this.chkState = chkState;
	}
	public String getChkMsg() {
		return chkMsg;
	}
	public void setChkMsg(String chkMsg) {
		this.chkMsg = chkMsg;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getCltDate() {
		return cltDate;
	}
	public void setCltDate(String cltDate) {
		this.cltDate = cltDate;
	}
	public String getCltTime() {
		return cltTime;
	}
	public void setCltTime(String cltTime) {
		this.cltTime = cltTime;
	}
	public String getHostDate() {
		return hostDate;
	}
	public void setHostDate(String hostDate) {
		this.hostDate = hostDate;
	}
	public String getHostTime() {
		return hostTime;
	}
	public void setHostTime(String hostTime) {
		this.hostTime = hostTime;
	}
	public String getCltDateUndo() {
		return cltDateUndo;
	}
	public void setCltDateUndo(String cltDateUndo) {
		this.cltDateUndo = cltDateUndo;
	}
	public String getCltTimeUndo() {
		return cltTimeUndo;
	}
	public void setCltTimeUndo(String cltTimeUndo) {
		this.cltTimeUndo = cltTimeUndo;
	}
	public String getHostDateUndo() {
		return hostDateUndo;
	}
	public void setHostDateUndo(String hostDateUndo) {
		this.hostDateUndo = hostDateUndo;
	}
	public String getHostTimeUndo() {
		return hostTimeUndo;
	}
	public void setHostTimeUndo(String hostTimeUndo) {
		this.hostTimeUndo = hostTimeUndo;
	}
	
}
