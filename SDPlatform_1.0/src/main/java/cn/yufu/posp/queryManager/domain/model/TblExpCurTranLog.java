/**
 *����:cn.yufu.posp.queryManager.domain.model
 *����:package cn.yufu.posp.queryManager.domain.model;
 */
package cn.yufu.posp.queryManager.domain.model;

import java.math.BigDecimal;

/**
 * TbExpCurTranLog.java
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��20��
 * ����:΢��֧������ˮ��ѯ���ݱ��Ӧʵ��
 */
@SuppressWarnings("serial")
public class TblExpCurTranLog implements java.io.Serializable {

	private String terminalId;//�ն˺�
	private String merchantId;//�̻���
	private String bankMid;//�����̻���
	private String bankTid;//�����ն˺�
	private String cardNo;//����
	private BigDecimal hostLsNo;//������ˮ��
	private String tranRrn;//ǰ�ý��ײο���
	private BigDecimal tranType;//��������
	private String condCode;//25��
	private String funcCode;//60.1���60.3��
	private String sysOrderId;//ǰ��ϵͳ������
	private String sysTimeStamp;//ǰ��ʱ��� 
	private String sysVoidOrderId;//ǰ��ϵͳԭ������
	private String sysOrderDtl;//ǰ��ϵͳ��������
	private Double tranAmt;//���׽��
	private Double tranVoidAmt;//ԭ���׽��
	private String acqOrderId;//����ϵͳ������(�ۺϻ�Ǯ��)
	private String acqUpOrderId;//�������ζ�����
	private String acqTimeStamp;//����ʱ���
	private BigDecimal acqRespCode;//����������
	private String acqRespMsg;//����������Ϣ
	private String payType;//֧������
	private String scanCode;//�ۺ�����
	private String etcCardNo;//ETC����
	private String codeStat;//����״̬ ''00''--����;''01''--ʧ��;''02''--��ʹ��'
	private String addDate;//��������ʱ��
	private String expDate;//����ʧЧʱ��
	private String effDate;//�������ʱ��
	private Double tries;//ʧ�ܴ���
	private String bussId;//ҵ���ʶ
	private String reserve1;//�����ֶ�1
	private String reserve2;//�����ֶ�2
	
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
