/**
 *包名:cn.yufu.posp.queryManager.domain.model
 *描述:package cn.yufu.posp.queryManager.domain.model;
 */
package cn.yufu.posp.queryManager.domain.model;

import java.math.BigDecimal;

/**
 * TbExpCurTranLog.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月20日
 * 描述:微信支付宝流水查询数据表对应实体
 */
@SuppressWarnings("serial")
public class TblExpCurTranLog implements java.io.Serializable {

	private String terminalId;//终端号
	private String merchantId;//商户号
	private String bankMid;//银行商户号
	private String bankTid;//银行终端号
	private String cardNo;//卡号
	private BigDecimal hostLsNo;//主机流水号
	private String tranRrn;//前置交易参考号
	private BigDecimal tranType;//交易类型
	private String condCode;//25域
	private String funcCode;//60.1域或60.3域
	private String sysOrderId;//前置系统订单号
	private String sysTimeStamp;//前置时间戳 
	private String sysVoidOrderId;//前置系统原订单号
	private String sysOrderDtl;//前置系统订单内容
	private Double tranAmt;//交易金额
	private Double tranVoidAmt;//原交易金额
	private String acqOrderId;//受理方系统订单号(聚合或钱包)
	private String acqUpOrderId;//受理方上游订单号
	private String acqTimeStamp;//受理方时间戳
	private BigDecimal acqRespCode;//受理方返回码
	private String acqRespMsg;//受理方返回消息
	private String payType;//支付类型
	private String scanCode;//聚合条码
	private String etcCardNo;//ETC卡号
	private String codeStat;//订单状态 ''00''--正常;''01''--失败;''02''--被使用'
	private String addDate;//订单生成时间
	private String expDate;//订单失效时间
	private String effDate;//订单完成时间
	private Double tries;//失败次数
	private String bussId;//业务标识
	private String reserve1;//保留字段1
	private String reserve2;//保留字段2
	
	@Override
	public String toString() {
		return "TblExpCurTranLog [terminalId=" + terminalId + ", merchantId=" + merchantId + ", bankMid=" + bankMid
				+ ", bankTid=" + bankTid + ", cardNo=" + cardNo + ", hostLsNo=" + hostLsNo + ", tranRrn=" + tranRrn
				+ ", tranType=" + tranType + ", condCode=" + condCode + ", funcCode=" + funcCode + ", sysOrderId="
				+ sysOrderId + ", sysTimeStamp=" + sysTimeStamp + ", sysVoidOrderId=" + sysVoidOrderId
				+ ", sysOrderDtl=" + sysOrderDtl + ", tranAmt=" + tranAmt + ", tranVoidAmt=" + tranVoidAmt
				+ ", acqOrderId=" + acqOrderId + ", acqUpOrderId=" + acqUpOrderId + ", acqTimeStamp=" + acqTimeStamp
				+ ", acqRespCode=" + acqRespCode + ", acqRespMsg=" + acqRespMsg + ", payType=" + payType + ", scanCode="
				+ scanCode + ", etcCardNo=" + etcCardNo + ", codeStat=" + codeStat + ", addDate=" + addDate
				+ ", expDate=" + expDate + ", effDate=" + effDate + ", tries=" + tries + ", bussId=" + bussId
				+ ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + "]";
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
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public BigDecimal getHostLsNo() {
		return hostLsNo;
	}
	public void setHostLsNo(BigDecimal hostLsNo) {
		this.hostLsNo = hostLsNo;
	}
	public String getTranRrn() {
		return tranRrn;
	}
	public void setTranRrn(String tranRrn) {
		this.tranRrn = tranRrn;
	}
	public BigDecimal getTranType() {
		return tranType;
	}
	public void setTranType(BigDecimal tranType) {
		this.tranType = tranType;
	}
	public String getCondCode() {
		return condCode;
	}
	public void setCondCode(String condCode) {
		this.condCode = condCode;
	}
	public String getFuncCode() {
		return funcCode;
	}
	public void setFuncCode(String funcCode) {
		this.funcCode = funcCode;
	}
	public String getSysOrderId() {
		return sysOrderId;
	}
	public void setSysOrderId(String sysOrderId) {
		this.sysOrderId = sysOrderId;
	}
	public String getSysTimeStamp() {
		return sysTimeStamp;
	}
	public void setSysTimeStamp(String sysTimeStamp) {
		this.sysTimeStamp = sysTimeStamp;
	}
	public String getSysVoidOrderId() {
		return sysVoidOrderId;
	}
	public void setSysVoidOrderId(String sysVoidOrderId) {
		this.sysVoidOrderId = sysVoidOrderId;
	}
	public String getSysOrderDtl() {
		return sysOrderDtl;
	}
	public void setSysOrderDtl(String sysOrderDtl) {
		this.sysOrderDtl = sysOrderDtl;
	}
	public Double getTranAmt() {
		return tranAmt;
	}
	public void setTranAmt(Double tranAmt) {
		this.tranAmt = tranAmt;
	}
	public Double getTranVoidAmt() {
		return tranVoidAmt;
	}
	public void setTranVoidAmt(Double tranVoidAmt) {
		this.tranVoidAmt = tranVoidAmt;
	}
	public String getAcqOrderId() {
		return acqOrderId;
	}
	public void setAcqOrderId(String acqOrderId) {
		this.acqOrderId = acqOrderId;
	}
	public String getAcqUpOrderId() {
		return acqUpOrderId;
	}
	public void setAcqUpOrderId(String acqUpOrderId) {
		this.acqUpOrderId = acqUpOrderId;
	}
	public String getAcqTimeStamp() {
		return acqTimeStamp;
	}
	public void setAcqTimeStamp(String acqTimeStamp) {
		this.acqTimeStamp = acqTimeStamp;
	}
	public BigDecimal getAcqRespCode() {
		return acqRespCode;
	}
	public void setAcqRespCode(BigDecimal acqRespCode) {
		this.acqRespCode = acqRespCode;
	}
	public String getAcqRespMsg() {
		return acqRespMsg;
	}
	public void setAcqRespMsg(String acqRespMsg) {
		this.acqRespMsg = acqRespMsg;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getScanCode() {
		return scanCode;
	}
	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}
	public String getEtcCardNo() {
		return etcCardNo;
	}
	public void setEtcCardNo(String etcCardNo) {
		this.etcCardNo = etcCardNo;
	}
	public String getCodeStat() {
		return codeStat;
	}
	public void setCodeStat(String codeStat) {
		this.codeStat = codeStat;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getEffDate() {
		return effDate;
	}
	public void setEffDate(String effDate) {
		this.effDate = effDate;
	}
	public Double getTries() {
		return tries;
	}
	public void setTries(Double tries) {
		this.tries = tries;
	}
	public String getBussId() {
		return bussId;
	}
	public void setBussId(String bussId) {
		this.bussId = bussId;
	}
	public String getReserve1() {
		return reserve1;
	}
	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}
	public String getReserve2() {
		return reserve2;
	}
	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}
}
