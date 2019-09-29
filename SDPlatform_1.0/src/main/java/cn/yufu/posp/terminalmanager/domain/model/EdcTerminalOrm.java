package cn.yufu.posp.terminalmanager.domain.model;


/**
 * EdcTerminalOrm entity. @author MyEclipse Persistence Tools
 */

public class EdcTerminalOrm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	private String merchantId;
	private String merchanName;
	private String terminalId;
	private String moduleId;
	private String bankId;
	private String bankMerchantId;
	private String bankTerminalId;
	private String sysTrace;
	private String bankTrace;
	private String pinFmt;
	private String encMethod;
	private String macFlag;
	private String batchNo;
	private String pik;
	private String mak;
	private String tmk;
	private String pikTmk;
	private String makTmk;
	private String keyIndex;
	private String settStatus;
	private String logonStatus;
	private String flag;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
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

	public String getMerchanName() {
		return merchanName;
	}

	public void setMerchanName(String merchanName) {
		this.merchanName = merchanName;
	}

}