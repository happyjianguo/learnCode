package cn.yufu.posp.cardBinArea.web.form;

import cn.yufu.core.web.form.BaseForm;

public class CardBinAreaForm extends BaseForm {
	private String cardBin;
	private String areaCode;
	private String status;
	private String createOper;
	private String createDate;
	private String updateOper;
	private String updateDate;

	// ²éÑ¯Ìõ¼þ
	private String queryCardBin;
	private String queryAreaCode;
	private String queryStatus;
	
	private String areaCodeName;

	
	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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

	public String getQueryAreaCode() {
		return queryAreaCode;
	}

	public void setQueryAreaCode(String queryAreaCode) {
		this.queryAreaCode = queryAreaCode;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getAreaCodeName() {
		return areaCodeName;
	}

	public void setAreaCodeName(String areaCodeName) {
		this.areaCodeName = areaCodeName;
	}

}
