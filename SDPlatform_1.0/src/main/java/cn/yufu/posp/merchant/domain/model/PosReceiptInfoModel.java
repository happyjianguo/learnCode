package cn.yufu.posp.merchant.domain.model;


/**
 * PosReceiptInfo entity. @author MyEclipse Persistence Tools
 */

public class PosReceiptInfoModel implements java.io.Serializable {

	// Fields
	private String hotline;
	private String telSupport;
	private String adInfo;
	private String adYesNoFlag;
	private String status;
	private String createDate;
	private String updateOper;
	private String updateDate;

	

	public String getHotline() {
		return this.hotline;
	}
	public void setHotline(String hotline) {
		this.hotline = hotline;
	}

	public String getTelSupport() {
		return this.telSupport;
	}
	public void setTelSupport(String telSupport) {
		this.telSupport = telSupport;
	}

	public String getAdInfo() {
		return this.adInfo;
	}
	public void setAdInfo(String adInfo) {
		this.adInfo = adInfo;
	}

	public String getAdYesNoFlag() {
		return this.adYesNoFlag;
	}
	public void setAdYesNoFlag(String adYesNoFlag) {
		this.adYesNoFlag = adYesNoFlag;
	}

	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateDate() {
		return this.createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
		
	public String getUpdateOper() {
		return this.updateOper;
	}
	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}