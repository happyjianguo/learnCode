/**
 *����:cn.yufu.posp.terminalmanager.domain.model
 *����:package cn.yufu.posp.terminalmanager.domain.model;
 */
package cn.yufu.posp.terminalmanager.domain.model;
/**
 * EdcZskterminalOrm.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��3��2��
 * ����:ר�����ն�
 */
public class EdcZskterminalOrm implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String merchantId; //ϵͳ�̻���
	private String bankId; //���б�ʶ
	private String terminalId; //ϵͳ�ն˺�
	private String bankMerchantId; //�����̻���
	private String bankTerminalId; //�����ն˺�
	private String moduleId; //ģ��ID
	private String sysTrace; //ϵͳ��ˮ��
	private String bankTrace; //������ˮ��
	private String pinFmt; //PIN�㷨��ʶ    1: ANSI X98��ʽ���������˺ţ�  2: ANSI X98�㷨�������˺ţ�
	private String encMethod; //�����㷨 0: DES  6: 3DES  7: MD5
	private String macFlag; //MAC�����־ 0: �����ն˲��� 1: ����
	private String batchNo; //���κ�
	private String pik; //������Կ����PIN_KEY-
	private String mak; //������Կ����MAC_KEY
	private String tmk; //�ն�����ԿKEK--����ն�����Կ
	private String pikTmk; //������Կ����PIN_KEY���ն�ǩ����ȡ��
	private String makTmk; //������Կ����MAC_KEY���ն�ǩ����ȡ��
	private String keyIndex; //��Կ����
	private String settStatus; //����״̬ 0: ��������״̬ 1: ��Ҫ���� 2: ���ʽ�����
	private String logonStatus; //�������POS�ն�ǩ��״̬  0: ǩ��״̬   1: ��ǩ��  2:ǩ���쳣
	private String flag; //��ͨ��־  1��������ͨ  0��δ��ͨ
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getBankMerchantId() {
		return bankMerchantId;
	}
	public void setBankMerchantId(String bankMerchantId) {
		this.bankMerchantId = bankMerchantId;
	}
	public String getBankTerminalId() {
		return bankTerminalId;
	}
	public void setBankTerminalId(String bankTerminalId) {
		this.bankTerminalId = bankTerminalId;
	}
	public String getModuleId() {
		return moduleId;
	}
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
	public String getSysTrace() {
		return sysTrace;
	}
	public void setSysTrace(String sysTrace) {
		this.sysTrace = sysTrace;
	}
	public String getBankTrace() {
		return bankTrace;
	}
	public void setBankTrace(String bankTrace) {
		this.bankTrace = bankTrace;
	}
	public String getPinFmt() {
		return pinFmt;
	}
	public void setPinFmt(String pinFmt) {
		this.pinFmt = pinFmt;
	}
	public String getEncMethod() {
		return encMethod;
	}
	public void setEncMethod(String encMethod) {
		this.encMethod = encMethod;
	}
	public String getMacFlag() {
		return macFlag;
	}
	public void setMacFlag(String macFlag) {
		this.macFlag = macFlag;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getPik() {
		return pik;
	}
	public void setPik(String pik) {
		this.pik = pik;
	}
	public String getMak() {
		return mak;
	}
	public void setMak(String mak) {
		this.mak = mak;
	}
	public String getTmk() {
		return tmk;
	}
	public void setTmk(String tmk) {
		this.tmk = tmk;
	}
	public String getPikTmk() {
		return pikTmk;
	}
	public void setPikTmk(String pikTmk) {
		this.pikTmk = pikTmk;
	}
	public String getMakTmk() {
		return makTmk;
	}
	public void setMakTmk(String makTmk) {
		this.makTmk = makTmk;
	}
	public String getKeyIndex() {
		return keyIndex;
	}
	public void setKeyIndex(String keyIndex) {
		this.keyIndex = keyIndex;
	}
	public String getSettStatus() {
		return settStatus;
	}
	public void setSettStatus(String settStatus) {
		this.settStatus = settStatus;
	}
	public String getLogonStatus() {
		return logonStatus;
	}
	public void setLogonStatus(String logonStatus) {
		this.logonStatus = logonStatus;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}
