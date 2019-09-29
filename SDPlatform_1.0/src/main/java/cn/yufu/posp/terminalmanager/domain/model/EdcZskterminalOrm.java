/**
 *包名:cn.yufu.posp.terminalmanager.domain.model
 *描述:package cn.yufu.posp.terminalmanager.domain.model;
 */
package cn.yufu.posp.terminalmanager.domain.model;
/**
 * EdcZskterminalOrm.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年3月2日
 * 描述:专属卡终端
 */
public class EdcZskterminalOrm implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String merchantId; //系统商户号
	private String bankId; //银行标识
	private String terminalId; //系统终端号
	private String bankMerchantId; //银行商户号
	private String bankTerminalId; //银行终端号
	private String moduleId; //模块ID
	private String sysTrace; //系统流水号
	private String bankTrace; //银行流水号
	private String pinFmt; //PIN算法标识    1: ANSI X98格式（不带主账号）  2: ANSI X98算法（带主账号）
	private String encMethod; //加密算法 0: DES  6: 3DES  7: MD5
	private String macFlag; //MAC运算标志 0: 特例终端不做 1: 正常
	private String batchNo; //批次号
	private String pik; //工作密钥密文PIN_KEY-
	private String mak; //工作密钥密文MAC_KEY
	private String tmk; //终端主密钥KEK--存放终端主密钥
	private String pikTmk; //工作密钥密文PIN_KEY（终端签到获取）
	private String makTmk; //工作密钥密文MAC_KEY（终端签到获取）
	private String keyIndex; //密钥索引
	private String settStatus; //结帐状态 0: 正常交易状态 1: 需要结帐 2: 结帐进行中
	private String logonStatus; //受理机构POS终端签到状态  0: 签退状态   1: 已签到  2:签到异常
	private String flag; //开通标志  1－正常开通  0－未开通
	
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
