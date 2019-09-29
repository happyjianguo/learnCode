package cn.yufu.posp.queryManager.domain.model;

import java.math.BigDecimal;

/**
 * CurTranLs entity. @author MyEclipse Persistence Tools
 */

public class CurTranLs implements java.io.Serializable {

	// Fields

	private String cardNo;// // 卡号
	private String expDate; // 有效期
	private BigDecimal tranType; // 交易类型
	private BigDecimal voidTranType; // 原交易类型
	private BigDecimal voidOldTranType; // 原原交易类型
	private String localSysDate; // 本地系统日期
	private String localSysDateS; // 本地系统日期
	private String localSysDateE; // 本地系统日期
	private String localSysTime; // 本地系统时间
	private String localSysTimeS; // 本地系统时间
	private String localSysTimeE; // 本地系统时间
	private String tranSysTime; // 交易系统时间，收单行填写，全程一致
	private Double tranAmt; // 交易金额
	private Double voidAmt; // 原交易金额
	private String terminalId; // 终端号
	private String merchantId; // 商户编号
	private String merchantName; // 商户名称
	private String mcc; // 商户类型
	private BigDecimal traceNo; // 交易流水号
	private BigDecimal voidTraceNo; // 原交易流水号
	private String tranRrn; // 系统参考号
	private String voidRrn; // 原系统参考号
	private BigDecimal hostLsNo; // 主机流水号
	private BigDecimal batchNo; // Pos批次号
	private String bankType; // 银行类型
	private String cardType; // 卡类型 -01-信用卡，02-借记卡
	private String ccyCode; // 交易货币，156-人民币
	private String icCsn;// IC卡序列号
	private String icCsn2;// IC卡序列号2
	private BigDecimal moduleId;// 处理模块号
	private String bankMid;// 银行商户号
	private String bankTid;// 银行终端号
	private String bankRrn;// 银行交易参考号
	private String bankSettleDate;// 银行清算日期 y4md格式
	private String VCardType;// 外卡卡种
	private BigDecimal accType;// 账户性质 1：借记卡 2：贷记卡 3：准贷记卡
	private String bankBatchNo;// 银行批次号
	private String trace1;// 目标机构流水号1--银行流水
	private String trace2;// 目标机构流水号2--银行系统参考号
	private String icInf;// IC卡数据域--55域
	private String pbocInf;// PBOC交易数据(对应交易58域信息)
	private String bankMcc;// 银行MCC--银行商户类型编码
	private String bankSysDate;// 银行系统日期
	private String bankSysTime;// 银行系统时间
	private String acqBankId; // 收单行机构编码
	private String acqHostId; // 收单行主机编码
	private String issBankId; // 发卡行机构编码
	private String sndBankId; // 发送行机构编码
	private String sndHostId; // 发送行主机编码
	private String rcvBankId; // 接收行机构编码
	private String rcvHostId; // 接收行主机编码
	private String authNo; // 授权码--银行的
	private String respCode; // 交易返回码 响应码
	private String respBankId; // 响应行行号
	private String respHostId; // 响应行主机号
	private String voidRespCode; // 原交易返回码 响应码
	private String voidRespBankId; // 原交易响应行行号
	private String voidRespHostId; // 原交易应行主机号
	private String idType; // 证件类型------------废弃
	private String personId; // 证件号码-----------废弃
	private String operNo; // 操作员号
	private String localLogicDate; // 本地逻辑日
	private String sndLogicDate; // 发送方逻辑日
	private String sndSettleDate; // 发送方清算日期
	private String sndSettleFlag; // 发送方对帐标志
	private String rcvLogicDate; // 接收方逻辑日
	private String rcvSettleDate; // 接收方清算日期
	private String rcvSettleFlag; // 接收方对帐标志
	private String agtLogicDate; // 代理方逻辑日
	private String agtSettleDate; // 代理方清算日期
	private String agtSettleFlag; // 代理方对帐标志
	private String advLogicDate; // 通知方逻辑日
	private String advSettleDate; // 通知方清算日期
	private String advSettleFlag; // 通知方对帐标志
	private String agentTranType; // 代收费费种
	private Double addAmount; // 附加金额---现在用作退货金额或已经确认的预授权完成金额使用20141015
	private String transBankId; // 转入机构标识(56域)
	private String transAcctNo; // 转入账户标识 -- 转账账号,卡号或企业代码
	private String tranFlag; // 交易标识: 8 -初登记 0 --交易完成. 1--已撤销. 2--已经确认的预授权
	private String advFlag; // 通知标志
	private String offlineFlag; // 离线标志
	private String safFlag; // 冲正标志
	private String edcErrFlag; // 错帐标志
	private String inputMode; // 输入方式 手输、刷卡
	private String manualFlag; // 是否手工授权
	private String track2; // 二磁道信息
	private String track3; // 三磁道信息
	private String addData; // 附加数据
	private String orgTranTime; // 原交易系统时间 MMDDhhmmss
	private String addResp; // 附加响应数据(44域)
	private String updateDate; // 流水记入日期
	private String updateTime; // 流水记入时间
	private BigDecimal ireserve1;
	private BigDecimal ireserve2;
	private String ireserve3;
	private String ireserve4;
	private String ireserve5;
	private String ireserve6;

	private String queryType;

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpDate() {
		return expDate;
	}

	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}

	public BigDecimal getTranType() {
		return tranType;
	}

	public void setTranType(BigDecimal tranType) {
		this.tranType = tranType;
	}

	public BigDecimal getVoidTranType() {
		return voidTranType;
	}

	public void setVoidTranType(BigDecimal voidTranType) {
		this.voidTranType = voidTranType;
	}

	public BigDecimal getVoidOldTranType() {
		return voidOldTranType;
	}

	public void setVoidOldTranType(BigDecimal voidOldTranType) {
		this.voidOldTranType = voidOldTranType;
	}

	public String getLocalSysDate() {
		return localSysDate;
	}

	public void setLocalSysDate(String localSysDate) {
		this.localSysDate = localSysDate;
	}

	public String getLocalSysDateS() {
		return localSysDateS;
	}

	public void setLocalSysDateS(String localSysDateS) {
		this.localSysDateS = localSysDateS;
	}

	public String getLocalSysDateE() {
		return localSysDateE;
	}

	public void setLocalSysDateE(String localSysDateE) {
		this.localSysDateE = localSysDateE;
	}

	public String getLocalSysTime() {
		return localSysTime;
	}

	public void setLocalSysTime(String localSysTime) {
		this.localSysTime = localSysTime;
	}

	public String getLocalSysTimeS() {
		return localSysTimeS;
	}

	public void setLocalSysTimeS(String localSysTimeS) {
		this.localSysTimeS = localSysTimeS;
	}

	public String getLocalSysTimeE() {
		return localSysTimeE;
	}

	public void setLocalSysTimeE(String localSysTimeE) {
		this.localSysTimeE = localSysTimeE;
	}

	public String getTranSysTime() {
		return tranSysTime;
	}

	public void setTranSysTime(String tranSysTime) {
		this.tranSysTime = tranSysTime;
	}

	public Double getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(Double tranAmt) {
		this.tranAmt = tranAmt;
	}

	public Double getVoidAmt() {
		return voidAmt;
	}

	public void setVoidAmt(Double voidAmt) {
		this.voidAmt = voidAmt;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public BigDecimal getTraceNo() {
		return traceNo;
	}

	public void setTraceNo(BigDecimal traceNo) {
		this.traceNo = traceNo;
	}

	public BigDecimal getVoidTraceNo() {
		return voidTraceNo;
	}

	public void setVoidTraceNo(BigDecimal voidTraceNo) {
		this.voidTraceNo = voidTraceNo;
	}

	public String getTranRrn() {
		return tranRrn;
	}

	public void setTranRrn(String tranRrn) {
		this.tranRrn = tranRrn;
	}

	public String getVoidRrn() {
		return voidRrn;
	}

	public void setVoidRrn(String voidRrn) {
		this.voidRrn = voidRrn;
	}

	public BigDecimal getHostLsNo() {
		return hostLsNo;
	}

	public void setHostLsNo(BigDecimal hostLsNo) {
		this.hostLsNo = hostLsNo;
	}

	public BigDecimal getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(BigDecimal batchNo) {
		this.batchNo = batchNo;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCcyCode() {
		return ccyCode;
	}

	public void setCcyCode(String ccyCode) {
		this.ccyCode = ccyCode;
	}

	public String getIcCsn() {
		return icCsn;
	}

	public void setIcCsn(String icCsn) {
		this.icCsn = icCsn;
	}

	public String getIcCsn2() {
		return icCsn2;
	}

	public void setIcCsn2(String icCsn2) {
		this.icCsn2 = icCsn2;
	}

	public BigDecimal getModuleId() {
		return moduleId;
	}

	public void setModuleId(BigDecimal moduleId) {
		this.moduleId = moduleId;
	}

	public String getBankMid() {
		return bankMid;
	}

	public void setBankMid(String bankMid) {
		this.bankMid = bankMid;
	}

	public String getBankTid() {
		return bankTid;
	}

	public void setBankTid(String bankTid) {
		this.bankTid = bankTid;
	}

	public String getBankRrn() {
		return bankRrn;
	}

	public void setBankRrn(String bankRrn) {
		this.bankRrn = bankRrn;
	}

	public String getBankSettleDate() {
		return bankSettleDate;
	}

	public void setBankSettleDate(String bankSettleDate) {
		this.bankSettleDate = bankSettleDate;
	}

	public String getVCardType() {
		return VCardType;
	}

	public void setVCardType(String vCardType) {
		VCardType = vCardType;
	}

	public BigDecimal getAccType() {
		return accType;
	}

	public void setAccType(BigDecimal accType) {
		this.accType = accType;
	}

	public String getBankBatchNo() {
		return bankBatchNo;
	}

	public void setBankBatchNo(String bankBatchNo) {
		this.bankBatchNo = bankBatchNo;
	}

	public String getTrace1() {
		return trace1;
	}

	public void setTrace1(String trace1) {
		this.trace1 = trace1;
	}

	public String getTrace2() {
		return trace2;
	}

	public void setTrace2(String trace2) {
		this.trace2 = trace2;
	}

	public String getIcInf() {
		return icInf;
	}

	public void setIcInf(String icInf) {
		this.icInf = icInf;
	}

	public String getPbocInf() {
		return pbocInf;
	}

	public void setPbocInf(String pbocInf) {
		this.pbocInf = pbocInf;
	}

	public String getBankMcc() {
		return bankMcc;
	}

	public void setBankMcc(String bankMcc) {
		this.bankMcc = bankMcc;
	}

	public String getBankSysDate() {
		return bankSysDate;
	}

	public void setBankSysDate(String bankSysDate) {
		this.bankSysDate = bankSysDate;
	}

	public String getBankSysTime() {
		return bankSysTime;
	}

	public void setBankSysTime(String bankSysTime) {
		this.bankSysTime = bankSysTime;
	}

	public String getAcqBankId() {
		return acqBankId;
	}

	public void setAcqBankId(String acqBankId) {
		this.acqBankId = acqBankId;
	}

	public String getAcqHostId() {
		return acqHostId;
	}

	public void setAcqHostId(String acqHostId) {
		this.acqHostId = acqHostId;
	}

	public String getIssBankId() {
		return issBankId;
	}

	public void setIssBankId(String issBankId) {
		this.issBankId = issBankId;
	}

	public String getSndBankId() {
		return sndBankId;
	}

	public void setSndBankId(String sndBankId) {
		this.sndBankId = sndBankId;
	}

	public String getSndHostId() {
		return sndHostId;
	}

	public void setSndHostId(String sndHostId) {
		this.sndHostId = sndHostId;
	}

	public String getRcvBankId() {
		return rcvBankId;
	}

	public void setRcvBankId(String rcvBankId) {
		this.rcvBankId = rcvBankId;
	}

	public String getRcvHostId() {
		return rcvHostId;
	}

	public void setRcvHostId(String rcvHostId) {
		this.rcvHostId = rcvHostId;
	}

	public String getAuthNo() {
		return authNo;
	}

	public void setAuthNo(String authNo) {
		this.authNo = authNo;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespBankId() {
		return respBankId;
	}

	public void setRespBankId(String respBankId) {
		this.respBankId = respBankId;
	}

	public String getRespHostId() {
		return respHostId;
	}

	public void setRespHostId(String respHostId) {
		this.respHostId = respHostId;
	}

	public String getVoidRespCode() {
		return voidRespCode;
	}

	public void setVoidRespCode(String voidRespCode) {
		this.voidRespCode = voidRespCode;
	}

	public String getVoidRespBankId() {
		return voidRespBankId;
	}

	public void setVoidRespBankId(String voidRespBankId) {
		this.voidRespBankId = voidRespBankId;
	}

	public String getVoidRespHostId() {
		return voidRespHostId;
	}

	public void setVoidRespHostId(String voidRespHostId) {
		this.voidRespHostId = voidRespHostId;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOperNo() {
		return operNo;
	}

	public void setOperNo(String operNo) {
		this.operNo = operNo;
	}

	public String getLocalLogicDate() {
		return localLogicDate;
	}

	public void setLocalLogicDate(String localLogicDate) {
		this.localLogicDate = localLogicDate;
	}

	public String getSndLogicDate() {
		return sndLogicDate;
	}

	public void setSndLogicDate(String sndLogicDate) {
		this.sndLogicDate = sndLogicDate;
	}

	public String getSndSettleDate() {
		return sndSettleDate;
	}

	public void setSndSettleDate(String sndSettleDate) {
		this.sndSettleDate = sndSettleDate;
	}

	public String getSndSettleFlag() {
		return sndSettleFlag;
	}

	public void setSndSettleFlag(String sndSettleFlag) {
		this.sndSettleFlag = sndSettleFlag;
	}

	public String getRcvLogicDate() {
		return rcvLogicDate;
	}

	public void setRcvLogicDate(String rcvLogicDate) {
		this.rcvLogicDate = rcvLogicDate;
	}

	public String getRcvSettleDate() {
		return rcvSettleDate;
	}

	public void setRcvSettleDate(String rcvSettleDate) {
		this.rcvSettleDate = rcvSettleDate;
	}

	public String getRcvSettleFlag() {
		return rcvSettleFlag;
	}

	public void setRcvSettleFlag(String rcvSettleFlag) {
		this.rcvSettleFlag = rcvSettleFlag;
	}

	public String getAgtLogicDate() {
		return agtLogicDate;
	}

	public void setAgtLogicDate(String agtLogicDate) {
		this.agtLogicDate = agtLogicDate;
	}

	public String getAgtSettleDate() {
		return agtSettleDate;
	}

	public void setAgtSettleDate(String agtSettleDate) {
		this.agtSettleDate = agtSettleDate;
	}

	public String getAgtSettleFlag() {
		return agtSettleFlag;
	}

	public void setAgtSettleFlag(String agtSettleFlag) {
		this.agtSettleFlag = agtSettleFlag;
	}

	public String getAdvLogicDate() {
		return advLogicDate;
	}

	public void setAdvLogicDate(String advLogicDate) {
		this.advLogicDate = advLogicDate;
	}

	public String getAdvSettleDate() {
		return advSettleDate;
	}

	public void setAdvSettleDate(String advSettleDate) {
		this.advSettleDate = advSettleDate;
	}

	public String getAdvSettleFlag() {
		return advSettleFlag;
	}

	public void setAdvSettleFlag(String advSettleFlag) {
		this.advSettleFlag = advSettleFlag;
	}

	public String getAgentTranType() {
		return agentTranType;
	}

	public void setAgentTranType(String agentTranType) {
		this.agentTranType = agentTranType;
	}

	public Double getAddAmount() {
		return addAmount;
	}

	public void setAddAmount(Double addAmount) {
		this.addAmount = addAmount;
	}

	public String getTransBankId() {
		return transBankId;
	}

	public void setTransBankId(String transBankId) {
		this.transBankId = transBankId;
	}

	public String getTransAcctNo() {
		return transAcctNo;
	}

	public void setTransAcctNo(String transAcctNo) {
		this.transAcctNo = transAcctNo;
	}

	public String getTranFlag() {
		return tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	public String getAdvFlag() {
		return advFlag;
	}

	public void setAdvFlag(String advFlag) {
		this.advFlag = advFlag;
	}

	public String getOfflineFlag() {
		return offlineFlag;
	}

	public void setOfflineFlag(String offlineFlag) {
		this.offlineFlag = offlineFlag;
	}

	public String getSafFlag() {
		return safFlag;
	}

	public void setSafFlag(String safFlag) {
		this.safFlag = safFlag;
	}

	public String getEdcErrFlag() {
		return edcErrFlag;
	}

	public void setEdcErrFlag(String edcErrFlag) {
		this.edcErrFlag = edcErrFlag;
	}

	public String getInputMode() {
		return inputMode;
	}

	public void setInputMode(String inputMode) {
		this.inputMode = inputMode;
	}

	public String getManualFlag() {
		return manualFlag;
	}

	public void setManualFlag(String manualFlag) {
		this.manualFlag = manualFlag;
	}

	public String getTrack2() {
		return track2;
	}

	public void setTrack2(String track2) {
		this.track2 = track2;
	}

	public String getTrack3() {
		return track3;
	}

	public void setTrack3(String track3) {
		this.track3 = track3;
	}

	public String getAddData() {
		return addData;
	}

	public void setAddData(String addData) {
		this.addData = addData;
	}

	public String getOrgTranTime() {
		return orgTranTime;
	}

	public void setOrgTranTime(String orgTranTime) {
		this.orgTranTime = orgTranTime;
	}

	public String getAddResp() {
		return addResp;
	}

	public void setAddResp(String addResp) {
		this.addResp = addResp;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getIreserve1() {
		return ireserve1;
	}

	public void setIreserve1(BigDecimal ireserve1) {
		this.ireserve1 = ireserve1;
	}

	public BigDecimal getIreserve2() {
		return ireserve2;
	}

	public void setIreserve2(BigDecimal ireserve2) {
		this.ireserve2 = ireserve2;
	}

	public String getIreserve3() {
		return ireserve3;
	}

	public void setIreserve3(String ireserve3) {
		this.ireserve3 = ireserve3;
	}

	public String getIreserve4() {
		return ireserve4;
	}

	public void setIreserve4(String ireserve4) {
		this.ireserve4 = ireserve4;
	}

	public String getIreserve5() {
		return ireserve5;
	}

	public void setIreserve5(String ireserve5) {
		this.ireserve5 = ireserve5;
	}

	public String getIreserve6() {
		return ireserve6;
	}

	public void setIreserve6(String ireserve6) {
		this.ireserve6 = ireserve6;
	}


}