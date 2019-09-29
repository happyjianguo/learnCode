package cn.yufu.posp.cardBinArea.web.form;

import cn.yufu.core.web.form.BaseForm;

public class EdcCardBinAreaForm extends BaseForm {
	private String cardBin;
	private String merchantId;
	private String terminalId;
	private String status;
	private String createOper;
	private String createDate;
	private String updateOper;
	private String updateDate;
	
	private String queryCardBin;
	private String queryMerchantId;
	private String queryTerminalId;
	private String queryStatus;
	
	private String merchantIds;
	
	private String edcCardBinAreaId;
	
	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateOper() {
		return createOper;
	}

	public void setCreateOper(String createOper) {
		this.createOper = createOper;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateOper() {
		return updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getQueryCardBin() {
		return queryCardBin;
	}

	public void setQueryCardBin(String queryCardBin) {
		this.queryCardBin = queryCardBin;
	}

	public String getQueryMerchantId() {
		return queryMerchantId;
	}

	public void setQueryMerchantId(String queryMerchantId) {
		this.queryMerchantId = queryMerchantId;
	}

	public String getQueryTerminalId() {
		return queryTerminalId;
	}

	public void setQueryTerminalId(String queryTerminalId) {
		this.queryTerminalId = queryTerminalId;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getMerchantIds() {
		return merchantIds;
	}

	public void setMerchantIds(String merchantIds) {
		this.merchantIds = merchantIds;
	}

	public String getEdcCardBinAreaId() {
		return edcCardBinAreaId;
	}

	public void setEdcCardBinAreaId(String edcCardBinAreaId) {
		this.edcCardBinAreaId = edcCardBinAreaId;
	}

}
