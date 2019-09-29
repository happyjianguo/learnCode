package cn.yufu.posp.bookManager.domain.model;

import java.math.BigDecimal;

/**
 * AnalyzeCupcheckOk entity. @author MyEclipse Persistence Tools
 */

public class AnalyzeCupcheckOk implements java.io.Serializable {

	// Fields

	private String id;
	private BigDecimal checkFlag;
	private String checkDate;
	private String cardNo;
	private String expDate;
	private BigDecimal tranType;
	private BigDecimal voidTranType;
	private BigDecimal voidOldTranType;
	private String localSysDate;
	private String localSysTime;
	private String tranSysTime;
	private Double tranAmt;
	private Double voidAmt;
	private String terminalId;
	private String merchantId;
	private String merchantName;
	private String mcc;
	private BigDecimal traceNo;
	private BigDecimal voidTraceNo;
	private String tranRrn;
	private String voidRrn;
	private BigDecimal hostLsNo;
	private BigDecimal batchNo;
	private String bankType;
	private String cardType;
	private String ccyCode;
	private String icCsn;
	private String icCsn2;
	private BigDecimal moduleId;
	private String bankMid;
	private String bankTid;
	private String bankRrn;
	private String bankSettleDate;
	private String VCardType;
	private BigDecimal accType;
	private String bankBatchNo;
	private String trace1;
	private String trace2;
	private String icInf;
	private String pbocInf;
	private String bankMcc;
	private String bankSysDate;
	private String bankSysTime;
	private String acqBankId;
	private String acqHostId;
	private String issBankId;
	private String sndBankId;
	private String sndHostId;
	private String rcvBankId;
	private String rcvHostId;
	private String authNo;
	private String respCode;
	private String respBankId;
	private String respHostId;
	private String voidRespCode;
	private String voidRespBankId;
	private String voidRespHostId;
	private String idType;
	private String personId;
	private String operNo;
	private String localLogicDate;
	private String sndLogicDate;
	private String sndSettleDate;
	private String sndSettleFlag;
	private String rcvLogicDate;
	private String rcvSettleDate;
	private String rcvSettleFlag;
	private String agtLogicDate;
	private String agtSettleDate;
	private String agtSettleFlag;
	private String advLogicDate;
	private String advSettleDate;
	private String advSettleFlag;
	private String agentTranType;
	private Double addAmount;
	private String transBankId;
	private String transAcctNo;
	private String tranFlag;
	private String advFlag;
	private String offlineFlag;
	private String safFlag;
	private String edcErrFlag;
	private String inputMode;
	private String manualFlag;
	private String track2;
	private String track3;
	private String addData;
	private String orgTranTime;
	private String addResp;
	private String updateDate;
	private String updateTime;
	private BigDecimal ireserve1;
	private BigDecimal ireserve2;
	private String ireserve3;
	private String ireserve4;
	private String ireserve5;
	private String ireserve6;
	private String szpaygateid;
	private String szmerchcode;
	private String sztranno;
	private String szacqqno;
	private String comments;

	// Constructors

	/** default constructor */
	public AnalyzeCupcheckOk() {
	}

	/** minimal constructor */
	public AnalyzeCupcheckOk(String id, String cardNo, BigDecimal tranType, String localSysDate, String localSysTime, Double tranAmt, BigDecimal hostLsNo,
			String bankType, String cardType, String ccyCode, String acqBankId, String acqHostId, String issBankId, String sndSettleFlag, String rcvSettleFlag,
			String agtSettleFlag, String advSettleFlag, String tranFlag) {
		this.id = id;
		this.cardNo = cardNo;
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
	public AnalyzeCupcheckOk(String id, BigDecimal checkFlag, String checkDate, String cardNo, String expDate, BigDecimal tranType, BigDecimal voidTranType,
			BigDecimal voidOldTranType, String localSysDate, String localSysTime, String tranSysTime, Double tranAmt, Double voidAmt, String terminalId,
			String merchantId, String merchantName, String mcc, BigDecimal traceNo, BigDecimal voidTraceNo, String tranRrn, String voidRrn,
			BigDecimal hostLsNo, BigDecimal batchNo, String bankType, String cardType, String ccyCode, String icCsn, String icCsn2, BigDecimal moduleId,
			String bankMid, String bankTid, String bankRrn, String bankSettleDate, String VCardType, BigDecimal accType, String bankBatchNo, String trace1,
			String trace2, String icInf, String pbocInf, String bankMcc, String bankSysDate, String bankSysTime, String acqBankId, String acqHostId,
			String issBankId, String sndBankId, String sndHostId, String rcvBankId, String rcvHostId, String authNo, String respCode, String respBankId,
			String respHostId, String voidRespCode, String voidRespBankId, String voidRespHostId, String idType, String personId, String operNo,
			String localLogicDate, String sndLogicDate, String sndSettleDate, String sndSettleFlag, String rcvLogicDate, String rcvSettleDate,
			String rcvSettleFlag, String agtLogicDate, String agtSettleDate, String agtSettleFlag, String advLogicDate, String advSettleDate,
			String advSettleFlag, String agentTranType, Double addAmount, String transBankId, String transAcctNo, String tranFlag, String advFlag,
			String offlineFlag, String safFlag, String edcErrFlag, String inputMode, String manualFlag, String track2, String track3, String addData,
			String orgTranTime, String addResp, String updateDate, String updateTime, BigDecimal ireserve1, BigDecimal ireserve2, String ireserve3,
			String ireserve4, String ireserve5, String ireserve6, String szpaygateid, String szmerchcode, String sztranno, String szacqqno, String comments) {
		this.id = id;
		this.checkFlag = checkFlag;
		this.checkDate = checkDate;
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
		this.icCsn = icCsn;
		this.icCsn2 = icCsn2;
		this.moduleId = moduleId;
		this.bankMid = bankMid;
		this.bankTid = bankTid;
		this.bankRrn = bankRrn;
		this.bankSettleDate = bankSettleDate;
		this.VCardType = VCardType;
		this.accType = accType;
		this.bankBatchNo = bankBatchNo;
		this.trace1 = trace1;
		this.trace2 = trace2;
		this.icInf = icInf;
		this.pbocInf = pbocInf;
		this.bankMcc = bankMcc;
		this.bankSysDate = bankSysDate;
		this.bankSysTime = bankSysTime;
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
		this.ireserve1 = ireserve1;
		this.ireserve2 = ireserve2;
		this.ireserve3 = ireserve3;
		this.ireserve4 = ireserve4;
		this.ireserve5 = ireserve5;
		this.ireserve6 = ireserve6;
		this.szpaygateid = szpaygateid;
		this.szmerchcode = szmerchcode;
		this.sztranno = sztranno;
		this.szacqqno = szacqqno;
		this.comments = comments;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getCheckFlag() {
		return this.checkFlag;
	}

	public void setCheckFlag(BigDecimal checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

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

	public String getIcCsn() {
		return this.icCsn;
	}

	public void setIcCsn(String icCsn) {
		this.icCsn = icCsn;
	}

	public String getIcCsn2() {
		return this.icCsn2;
	}

	public void setIcCsn2(String icCsn2) {
		this.icCsn2 = icCsn2;
	}

	public BigDecimal getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(BigDecimal moduleId) {
		this.moduleId = moduleId;
	}

	public String getBankMid() {
		return this.bankMid;
	}

	public void setBankMid(String bankMid) {
		this.bankMid = bankMid;
	}

	public String getBankTid() {
		return this.bankTid;
	}

	public void setBankTid(String bankTid) {
		this.bankTid = bankTid;
	}

	public String getBankRrn() {
		return this.bankRrn;
	}

	public void setBankRrn(String bankRrn) {
		this.bankRrn = bankRrn;
	}

	public String getBankSettleDate() {
		return this.bankSettleDate;
	}

	public void setBankSettleDate(String bankSettleDate) {
		this.bankSettleDate = bankSettleDate;
	}

	public String getVCardType() {
		return this.VCardType;
	}

	public void setVCardType(String VCardType) {
		this.VCardType = VCardType;
	}

	public BigDecimal getAccType() {
		return this.accType;
	}

	public void setAccType(BigDecimal accType) {
		this.accType = accType;
	}

	public String getBankBatchNo() {
		return this.bankBatchNo;
	}

	public void setBankBatchNo(String bankBatchNo) {
		this.bankBatchNo = bankBatchNo;
	}

	public String getTrace1() {
		return this.trace1;
	}

	public void setTrace1(String trace1) {
		this.trace1 = trace1;
	}

	public String getTrace2() {
		return this.trace2;
	}

	public void setTrace2(String trace2) {
		this.trace2 = trace2;
	}

	public String getIcInf() {
		return this.icInf;
	}

	public void setIcInf(String icInf) {
		this.icInf = icInf;
	}

	public String getPbocInf() {
		return this.pbocInf;
	}

	public void setPbocInf(String pbocInf) {
		this.pbocInf = pbocInf;
	}

	public String getBankMcc() {
		return this.bankMcc;
	}

	public void setBankMcc(String bankMcc) {
		this.bankMcc = bankMcc;
	}

	public String getBankSysDate() {
		return this.bankSysDate;
	}

	public void setBankSysDate(String bankSysDate) {
		this.bankSysDate = bankSysDate;
	}

	public String getBankSysTime() {
		return this.bankSysTime;
	}

	public void setBankSysTime(String bankSysTime) {
		this.bankSysTime = bankSysTime;
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

	public BigDecimal getIreserve1() {
		return this.ireserve1;
	}

	public void setIreserve1(BigDecimal ireserve1) {
		this.ireserve1 = ireserve1;
	}

	public BigDecimal getIreserve2() {
		return this.ireserve2;
	}

	public void setIreserve2(BigDecimal ireserve2) {
		this.ireserve2 = ireserve2;
	}

	public String getIreserve3() {
		return this.ireserve3;
	}

	public void setIreserve3(String ireserve3) {
		this.ireserve3 = ireserve3;
	}

	public String getIreserve4() {
		return this.ireserve4;
	}

	public void setIreserve4(String ireserve4) {
		this.ireserve4 = ireserve4;
	}

	public String getIreserve5() {
		return this.ireserve5;
	}

	public void setIreserve5(String ireserve5) {
		this.ireserve5 = ireserve5;
	}

	public String getIreserve6() {
		return this.ireserve6;
	}

	public void setIreserve6(String ireserve6) {
		this.ireserve6 = ireserve6;
	}

	public String getSzpaygateid() {
		return this.szpaygateid;
	}

	public void setSzpaygateid(String szpaygateid) {
		this.szpaygateid = szpaygateid;
	}

	public String getSzmerchcode() {
		return this.szmerchcode;
	}

	public void setSzmerchcode(String szmerchcode) {
		this.szmerchcode = szmerchcode;
	}

	public String getSztranno() {
		return this.sztranno;
	}

	public void setSztranno(String sztranno) {
		this.sztranno = sztranno;
	}

	public String getSzacqqno() {
		return this.szacqqno;
	}

	public void setSzacqqno(String szacqqno) {
		this.szacqqno = szacqqno;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}