package cn.yufu.posp.merchant.domain.model;


/**
 * TabBusinessRatesModel entity. @author MyEclipse Persistence Tools
 */

public class TabTermIdBusRoleModel implements java.io.Serializable {

	// Fields
	private String merchantId;
	private String terminalId;
	private String busRoleId;
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
	public String getBusRoleId() {
		return busRoleId;
	}
	public void setBusRoleId(String busRoleId) {
		this.busRoleId = busRoleId;
	}
	@Override
	public String toString() {
		return "TabTermIdBusRoleModel [merchantId=" + merchantId + ", terminalId=" + terminalId + ", busRoleId="
				+ busRoleId + "]";
	}
	
}