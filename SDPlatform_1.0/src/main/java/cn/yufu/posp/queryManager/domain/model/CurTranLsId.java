package cn.yufu.posp.queryManager.domain.model;

import java.math.BigDecimal;

/**
 * CurTranLsId entity. @author MyEclipse Persistence Tools
 */

public class CurTranLsId implements java.io.Serializable {

	// Fields

	 private String cardNo;//                // ����               
	 private String expDate;                 // ��Ч��             
	 private BigDecimal tranType;            // ��������           
	 private BigDecimal voidTranType;        // ԭ��������         
	 private BigDecimal voidOldTranType;     // ԭԭ��������       
	 private String localSysDate;            // ����ϵͳ����       
	 private String localSysTime;            // ����ϵͳʱ��       
	 private String tranSysTime;             // ����ϵͳʱ��       
	 private Double tranAmt;                 // ���׽��           
	 private Double voidAmt;                 // ԭ���׽��         
	 private String terminalId;              // EDC�ն˺�          
	 private String merchantId;              // �̻����           
	 private String merchantName;            // �̻�����           
	 private String mcc;                     // �̻�����           
	 private BigDecimal traceNo;             // ������ˮ��         
	 private BigDecimal voidTraceNo;         // ԭ������ˮ��       
	 private String tranRrn;                 // ϵͳ�ο���         
	 private String voidRrn;                 // ԭϵͳ�ο���       
	 private BigDecimal hostLsNo;            // ����CARDPASS������?
	 private BigDecimal batchNo;             // Pos���κ�          
	 private String bankType;                // ��������           
	 private String cardType;                // ������             
	 private String ccyCode;                 // ����               
	 private String acqBankId;               // �յ��к�           
	 private String acqHostId;               // �յ���CARDPASS����?
	 private String issBankId;               // �����к�           
	 private String sndBankId;               // ���ͷ��к�         
	 private String sndHostId;               // ���ͷ�CARDPASS����?
	 private String rcvBankId;               // ���շ��к�         
	 private String rcvHostId;               // ���շ�CARDPASS����?
	 private String authNo;                  // ��Ȩ��             
	 private String respCode;                // ���׷�����         
	 private String respBankId;              // ��Ӧ���к�         
	 private String respHostId;              // ��Ӧ��CARDPASS����?
	 private String voidRespCode;            // ԭ���׷�����       
	 private String voidRespBankId;          // ԭ������Ӧ���к�   
	 private String voidRespHostId;          // ԭ������Ӧ��CARDPAS
	 private String idType;                  // ֤������           
	 private String personId;                // ֤����             
	 private String operNo;                  // ����Ա��           
	 private String localLogicDate;          // �����߼���         
	 private String sndLogicDate;            // ���ͷ��߼���       
	 private String sndSettleDate;           // ���ͷ���������     
	 private String sndSettleFlag;           // ���ͷ����ʱ�־     
	 private String rcvLogicDate;            // ���շ��߼���       
	 private String rcvSettleDate;           // ���շ���������     
	 private String rcvSettleFlag;           // ���շ����ʱ�־     
	 private String agtLogicDate;            // �����߼���       
	 private String agtSettleDate;           // ������������     
	 private String agtSettleFlag;           // �������ʱ�־     
	 private String advLogicDate;            // ֪ͨ���߼���       
	 private String advSettleDate;           // ֪ͨ����������     
	 private String advSettleFlag;           // ֪ͨ�����ʱ�־     
	 private String agentTranType;           // ���շѷ���         
	 private Double addAmount;               // ���ӽ��           
	 private String transBankId;             // ת�����к�         
	 private String transAcctNo;             // ת���ʺ�           
	 private String tranFlag;                // ���ױ�־           
	 private String advFlag;                 // ֪ͨ��־           
	 private String offlineFlag;             // ���߱�־           
	 private String safFlag;                 // ������־           
	 private String edcErrFlag;              // ���ʱ�־           
	 private String inputMode;               // �Ƿ�����           
	 private String manualFlag;              // �Ƿ��ֹ���Ȩ       
	 private String track2;                  // ���ŵ���Ϣ         
	 private String track3;                  // ���ŵ���Ϣ         
	 private String addData;                 // ��������           
	 private String orgTranTime;             // ��֯����ʱ��       
	 private String addResp;                 // ���ӷ�����         
	 private String updateDate;              // ��ˮ��������       
	 private String updateTime;              // ��ˮ����ʱ��       


	// Constructors

	/** default constructor */
	public CurTranLsId() {
	}

	/** minimal constructor */
	public CurTranLsId(BigDecimal tranType, String localSysDate,
			String localSysTime, Double tranAmt, BigDecimal hostLsNo,
			String bankType, String cardType, String ccyCode, String acqBankId,
			String acqHostId, String issBankId, String sndSettleFlag,
			String rcvSettleFlag, String agtSettleFlag, String advSettleFlag,
			String tranFlag) {
		this.tranType = tranType;
		this.localSysDate = localSysDate;
		this.localSysTime = localSysTime;
		this.tranAmt = tranAmt;
		this.hostLsNo = hostLsNo;
		this.bankType = bankType;
		this.cardType = cardType;
		this.ccyCode = ccyCode;
		this.acqBankId = acqBankId;
		this.acqHostId = acqHostId;
		this.issBankId = issBankId;
		this.sndSettleFlag = sndSettleFlag;
		this.rcvSettleFlag = rcvSettleFlag;
		this.agtSettleFlag = agtSettleFlag;
		this.advSettleFlag = advSettleFlag;
		this.tranFlag = tranFlag;
	}

	/** full constructor */
	public CurTranLsId(String cardNo, String expDate, BigDecimal tranType,
			BigDecimal voidTranType, BigDecimal voidOldTranType,
			String localSysDate, String localSysTime, String tranSysTime,
			Double tranAmt, Double voidAmt, String terminalId,
			String merchantId, String merchantName, String mcc,
			BigDecimal traceNo, BigDecimal voidTraceNo, String tranRrn,
			String voidRrn, BigDecimal hostLsNo, BigDecimal batchNo,
			String bankType, String cardType, String ccyCode, String acqBankId,
			String acqHostId, String issBankId, String sndBankId,
			String sndHostId, String rcvBankId, String rcvHostId,
			String authNo, String respCode, String respBankId,
			String respHostId, String voidRespCode, String voidRespBankId,
			String voidRespHostId, String idType, String personId,
			String operNo, String localLogicDate, String sndLogicDate,
			String sndSettleDate, String sndSettleFlag, String rcvLogicDate,
			String rcvSettleDate, String rcvSettleFlag, String agtLogicDate,
			String agtSettleDate, String agtSettleFlag, String advLogicDate,
			String advSettleDate, String advSettleFlag, String agentTranType,
			Double addAmount, String transBankId, String transAcctNo,
			String tranFlag, String advFlag, String offlineFlag,
			String safFlag, String edcErrFlag, String inputMode,
			String manualFlag, String track2, String track3, String addData,
			String orgTranTime, String addResp, String updateDate,
			String updateTime) {
		this.cardNo = cardNo;
		this.expDate = expDate;
		this.tranType = tranType;
		this.voidTranType = voidTranType;
		this.voidOldTranType = voidOldTranType;
		this.localSysDate = localSysDate;
		this.localSysTime = localSysTime;
		this.tranSysTime = tranSysTime;
		this.tranAmt = tranAmt;
		this.voidAmt = voidAmt;
		this.terminalId = terminalId;
		this.merchantId = merchantId;
		this.merchantName = merchantName;
		this.mcc = mcc;
		this.traceNo = traceNo;
		this.voidTraceNo = voidTraceNo;
		this.tranRrn = tranRrn;
		this.voidRrn = voidRrn;
		this.hostLsNo = hostLsNo;
		this.batchNo = batchNo;
		this.bankType = bankType;
		this.cardType = cardType;
		this.ccyCode = ccyCode;
		this.acqBankId = acqBankId;
		this.acqHostId = acqHostId;
		this.issBankId = issBankId;
		this.sndBankId = sndBankId;
		this.sndHostId = sndHostId;
		this.rcvBankId = rcvBankId;
		this.rcvHostId = rcvHostId;
		this.authNo = authNo;
		this.respCode = respCode;
		this.respBankId = respBankId;
		this.respHostId = respHostId;
		this.voidRespCode = voidRespCode;
		this.voidRespBankId = voidRespBankId;
		this.voidRespHostId = voidRespHostId;
		this.idType = idType;
		this.personId = personId;
		this.operNo = operNo;
		this.localLogicDate = localLogicDate;
		this.sndLogicDate = sndLogicDate;
		this.sndSettleDate = sndSettleDate;
		this.sndSettleFlag = sndSettleFlag;
		this.rcvLogicDate = rcvLogicDate;
		this.rcvSettleDate = rcvSettleDate;
		this.rcvSettleFlag = rcvSettleFlag;
		this.agtLogicDate = agtLogicDate;
		this.agtSettleDate = agtSettleDate;
		this.agtSettleFlag = agtSettleFlag;
		this.advLogicDate = advLogicDate;
		this.advSettleDate = advSettleDate;
		this.advSettleFlag = advSettleFlag;
		this.agentTranType = agentTranType;
		this.addAmount = addAmount;
		this.transBankId = transBankId;
		this.transAcctNo = transAcctNo;
		this.tranFlag = tranFlag;
		this.advFlag = advFlag;
		this.offlineFlag = offlineFlag;
		this.safFlag = safFlag;
		this.edcErrFlag = edcErrFlag;
		this.inputMode = inputMode;
		this.manualFlag = manualFlag;
		this.track2 = track2;
		this.track3 = track3;
		this.addData = addData;
		this.orgTranTime = orgTranTime;
		this.addResp = addResp;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpDate() {
		return this.expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public BigDecimal getTranType() {
		return this.tranType;
	}

	public void setTranType(BigDecimal tranType) {
		this.tranType = tranType;
	}

	public BigDecimal getVoidTranType() {
		return this.voidTranType;
	}

	public void setVoidTranType(BigDecimal voidTranType) {
		this.voidTranType = voidTranType;
	}

	public BigDecimal getVoidOldTranType() {
		return this.voidOldTranType;
	}

	public void setVoidOldTranType(BigDecimal voidOldTranType) {
		this.voidOldTranType = voidOldTranType;
	}

	public String getLocalSysDate() {
		return this.localSysDate;
	}

	public void setLocalSysDate(String localSysDate) {
		this.localSysDate = localSysDate;
	}

	public String getLocalSysTime() {
		return this.localSysTime;
	}

	public void setLocalSysTime(String localSysTime) {
		this.localSysTime = localSysTime;
	}

	public String getTranSysTime() {
		return this.tranSysTime;
	}

	public void setTranSysTime(String tranSysTime) {
		this.tranSysTime = tranSysTime;
	}

	public Double getTranAmt() {
		return this.tranAmt;
	}

	public void setTranAmt(Double tranAmt) {
		this.tranAmt = tranAmt;
	}

	public Double getVoidAmt() {
		return this.voidAmt;
	}

	public void setVoidAmt(Double voidAmt) {
		this.voidAmt = voidAmt;
	}

	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return this.merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public BigDecimal getTraceNo() {
		return this.traceNo;
	}

	public void setTraceNo(BigDecimal traceNo) {
		this.traceNo = traceNo;
	}

	public BigDecimal getVoidTraceNo() {
		return this.voidTraceNo;
	}

	public void setVoidTraceNo(BigDecimal voidTraceNo) {
		this.voidTraceNo = voidTraceNo;
	}

	public String getTranRrn() {
		return this.tranRrn;
	}

	public void setTranRrn(String tranRrn) {
		this.tranRrn = tranRrn;
	}

	public String getVoidRrn() {
		return this.voidRrn;
	}

	public void setVoidRrn(String voidRrn) {
		this.voidRrn = voidRrn;
	}

	public BigDecimal getHostLsNo() {
		return this.hostLsNo;
	}

	public void setHostLsNo(BigDecimal hostLsNo) {
		this.hostLsNo = hostLsNo;
	}

	public BigDecimal getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(BigDecimal batchNo) {
		this.batchNo = batchNo;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCcyCode() {
		return this.ccyCode;
	}

	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}

	public String getAcqBankId() {
		return this.acqBankId;
	}

	public void setAcqBankId(String acqBankId) {
		this.acqBankId = acqBankId;
	}

	public String getAcqHostId() {
		return this.acqHostId;
	}

	public void setAcqHostId(String acqHostId) {
		this.acqHostId = acqHostId;
	}

	public String getIssBankId() {
		return this.issBankId;
	}

	public void setIssBankId(String issBankId) {
		this.issBankId = issBankId;
	}

	public String getSndBankId() {
		return this.sndBankId;
	}

	public void setSndBankId(String sndBankId) {
		this.sndBankId = sndBankId;
	}

	public String getSndHostId() {
		return this.sndHostId;
	}

	public void setSndHostId(String sndHostId) {
		this.sndHostId = sndHostId;
	}

	public String getRcvBankId() {
		return this.rcvBankId;
	}

	public void setRcvBankId(String rcvBankId) {
		this.rcvBankId = rcvBankId;
	}

	public String getRcvHostId() {
		return this.rcvHostId;
	}

	public void setRcvHostId(String rcvHostId) {
		this.rcvHostId = rcvHostId;
	}

	public String getAuthNo() {
		return this.authNo;
	}

	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}

	public String getRespCode() {
		return this.respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespBankId() {
		return this.respBankId;
	}

	public void setRespBankId(String respBankId) {
		this.respBankId = respBankId;
	}

	public String getRespHostId() {
		return this.respHostId;
	}

	public void setRespHostId(String respHostId) {
		this.respHostId = respHostId;
	}

	public String getVoidRespCode() {
		return this.voidRespCode;
	}

	public void setVoidRespCode(String voidRespCode) {
		this.voidRespCode = voidRespCode;
	}

	public String getVoidRespBankId() {
		return this.voidRespBankId;
	}

	public void setVoidRespBankId(String voidRespBankId) {
		this.voidRespBankId = voidRespBankId;
	}

	public String getVoidRespHostId() {
		return this.voidRespHostId;
	}

	public void setVoidRespHostId(String voidRespHostId) {
		this.voidRespHostId = voidRespHostId;
	}

	public String getIdType() {
		return this.idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOperNo() {
		return this.operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getLocalLogicDate() {
		return this.localLogicDate;
	}

	public void setLocalLogicDate(String localLogicDate) {
		this.localLogicDate = localLogicDate;
	}

	public String getSndLogicDate() {
		return this.sndLogicDate;
	}

	public void setSndLogicDate(String sndLogicDate) {
		this.sndLogicDate = sndLogicDate;
	}

	public String getSndSettleDate() {
		return this.sndSettleDate;
	}

	public void setSndSettleDate(String sndSettleDate) {
		this.sndSettleDate = sndSettleDate;
	}

	public String getSndSettleFlag() {
		return this.sndSettleFlag;
	}

	public void setSndSettleFlag(String sndSettleFlag) {
		this.sndSettleFlag = sndSettleFlag;
	}

	public String getRcvLogicDate() {
		return this.rcvLogicDate;
	}

	public void setRcvLogicDate(String rcvLogicDate) {
		this.rcvLogicDate = rcvLogicDate;
	}

	public String getRcvSettleDate() {
		return this.rcvSettleDate;
	}

	public void setRcvSettleDate(String rcvSettleDate) {
		this.rcvSettleDate = rcvSettleDate;
	}

	public String getRcvSettleFlag() {
		return this.rcvSettleFlag;
	}

	public void setRcvSettleFlag(String rcvSettleFlag) {
		this.rcvSettleFlag = rcvSettleFlag;
	}

	public String getAgtLogicDate() {
		return this.agtLogicDate;
	}

	public void setAgtLogicDate(String agtLogicDate) {
		this.agtLogicDate = agtLogicDate;
	}

	public String getAgtSettleDate() {
		return this.agtSettleDate;
	}

	public void setAgtSettleDate(String agtSettleDate) {
		this.agtSettleDate = agtSettleDate;
	}

	public String getAgtSettleFlag() {
		return this.agtSettleFlag;
	}

	public void setAgtSettleFlag(String agtSettleFlag) {
		this.agtSettleFlag = agtSettleFlag;
	}

	public String getAdvLogicDate() {
		return this.advLogicDate;
	}

	public void setAdvLogicDate(String advLogicDate) {
		this.advLogicDate = advLogicDate;
	}

	public String getAdvSettleDate() {
		return this.advSettleDate;
	}

	public void setAdvSettleDate(String advSettleDate) {
		this.advSettleDate = advSettleDate;
	}

	public String getAdvSettleFlag() {
		return this.advSettleFlag;
	}

	public void setAdvSettleFlag(String advSettleFlag) {
		this.advSettleFlag = advSettleFlag;
	}

	public String getAgentTranType() {
		return this.agentTranType;
	}

	public void setAgentTranType(String agentTranType) {
		this.agentTranType = agentTranType;
	}

	public Double getAddAmount() {
		return this.addAmount;
	}

	public void setAddAmount(Double addAmount) {
		this.addAmount = addAmount;
	}

	public String getTransBankId() {
		return this.transBankId;
	}

	public void setTransBankId(String transBankId) {
		this.transBankId = transBankId;
	}

	public String getTransAcctNo() {
		return this.transAcctNo;
	}

	public void setTransAcctNo(String transAcctNo) {
		this.transAcctNo = transAcctNo;
	}

	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	public String getAdvFlag() {
		return this.advFlag;
	}

	public void setAdvFlag(String advFlag) {
		this.advFlag = advFlag;
	}

	public String getOfflineFlag() {
		return this.offlineFlag;
	}

	public void setOfflineFlag(String offlineFlag) {
		this.offlineFlag = offlineFlag;
	}

	public String getSafFlag() {
		return this.safFlag;
	}

	public void setSafFlag(String safFlag) {
		this.safFlag = safFlag;
	}

	public String getEdcErrFlag() {
		return this.edcErrFlag;
	}

	public void setEdcErrFlag(String edcErrFlag) {
		this.edcErrFlag = edcErrFlag;
	}

	public String getInputMode() {
		return this.inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public String getManualFlag() {
		return this.manualFlag;
	}

	public void setManualFlag(String manualFlag) {
		this.manualFlag = manualFlag;
	}

	public String getTrack2() {
		return this.track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getTrack3() {
		return this.track3;
	}

	public void setTrack3(String track3) {
		this.track3 = track3;
	}

	public String getAddData() {
		return this.addData;
	}

	public void setAddData(String addData) {
		this.addData = addData;
	}

	public String getOrgTranTime() {
		return this.orgTranTime;
	}

	public void setOrgTranTime(String orgTranTime) {
		this.orgTranTime = orgTranTime;
	}

	public String getAddResp() {
		return this.addResp;
	}

	public void setAddResp(String addResp) {
		this.addResp = addResp;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean equals(Object other) {
		return true;
	}

	public int hashCode() {
		return 1;
	}

}