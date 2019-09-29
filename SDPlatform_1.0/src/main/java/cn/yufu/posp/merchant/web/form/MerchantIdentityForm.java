package cn.yufu.posp.merchant.web.form;

import java.util.Date;

import org.apache.struts.upload.FormFile;

import cn.yufu.core.web.form.BaseForm;

/**
 * MerchantIdentity entity. @author MyEclipse Persistence Tools
 */

public class MerchantIdentityForm extends BaseForm {

	// Fields

	private String merchantId;
	private String merchantCname;
	private String biCardNo;
	private String biCardDate;
	private String biCardPic;
	private String taxCardNo;
	private String taxCardDate;
	private String taxCardPic;
	private String orgCardNo;
	private String orgCardDate;
	private String orgCardPic;
	private String shareName;
	private String shareCardType;
	private String shareCardNo;
	private String shareCardDate;
	private String shareCardTel;
	private String legalName;
	private String legalCardType;
	private String legalCardNo;
	private String legalCardDate;
	private String legalCardTel;
	private String attnName;
	private String attnCardType;
	private String attnCardNo;
	private String attnCardDate;
	private String attnCardTel;
	private String classType;
	private String reason;
	private String remark;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	private String status;
	private String warn;
	private FormFile biFile;
	private FormFile taxFile;
	private FormFile orgFile;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantCname() {
		return merchantCname;
	}

	public void setMerchantCname(String merchantCname) {
		this.merchantCname = merchantCname;
	}

	public String getBiCardNo() {
		return biCardNo;
	}

	public void setBiCardNo(String biCardNo) {
		this.biCardNo = biCardNo;
	}

	public String getBiCardDate() {
		return biCardDate;
	}

	public void setBiCardDate(String biCardDate) {
		this.biCardDate = biCardDate;
	}

	public String getBiCardPic() {
		return biCardPic;
	}

	public void setBiCardPic(String biCardPic) {
		this.biCardPic = biCardPic;
	}

	public String getTaxCardNo() {
		return taxCardNo;
	}

	public void setTaxCardNo(String taxCardNo) {
		this.taxCardNo = taxCardNo;
	}

	public String getTaxCardDate() {
		return taxCardDate;
	}

	public void setTaxCardDate(String taxCardDate) {
		this.taxCardDate = taxCardDate;
	}

	public String getTaxCardPic() {
		return taxCardPic;
	}

	public void setTaxCardPic(String taxCardPic) {
		this.taxCardPic = taxCardPic;
	}

	public String getOrgCardNo() {
		return orgCardNo;
	}

	public void setOrgCardNo(String orgCardNo) {
		this.orgCardNo = orgCardNo;
	}

	public String getOrgCardDate() {
		return orgCardDate;
	}

	public void setOrgCardDate(String orgCardDate) {
		this.orgCardDate = orgCardDate;
	}

	public String getOrgCardPic() {
		return orgCardPic;
	}

	public void setOrgCardPic(String orgCardPic) {
		this.orgCardPic = orgCardPic;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareCardType() {
		return shareCardType;
	}

	public void setShareCardType(String shareCardType) {
		this.shareCardType = shareCardType;
	}

	public String getShareCardNo() {
		return shareCardNo;
	}

	public void setShareCardNo(String shareCardNo) {
		this.shareCardNo = shareCardNo;
	}

	public String getShareCardDate() {
		return shareCardDate;
	}

	public void setShareCardDate(String shareCardDate) {
		this.shareCardDate = shareCardDate;
	}

	public String getShareCardTel() {
		return shareCardTel;
	}

	public void setShareCardTel(String shareCardTel) {
		this.shareCardTel = shareCardTel;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalCardType() {
		return legalCardType;
	}

	public void setLegalCardType(String legalCardType) {
		this.legalCardType = legalCardType;
	}

	public String getLegalCardNo() {
		return legalCardNo;
	}

	public void setLegalCardNo(String legalCardNo) {
		this.legalCardNo = legalCardNo;
	}

	public String getLegalCardDate() {
		return legalCardDate;
	}

	public void setLegalCardDate(String legalCardDate) {
		this.legalCardDate = legalCardDate;
	}

	public String getLegalCardTel() {
		return legalCardTel;
	}

	public void setLegalCardTel(String legalCardTel) {
		this.legalCardTel = legalCardTel;
	}

	public String getAttnName() {
		return attnName;
	}

	public void setAttnName(String attnName) {
		this.attnName = attnName;
	}

	public String getAttnCardType() {
		return attnCardType;
	}

	public void setAttnCardType(String attnCardType) {
		this.attnCardType = attnCardType;
	}

	public String getAttnCardNo() {
		return attnCardNo;
	}

	public void setAttnCardNo(String attnCardNo) {
		this.attnCardNo = attnCardNo;
	}

	public String getAttnCardDate() {
		return attnCardDate;
	}

	public void setAttnCardDate(String attnCardDate) {
		this.attnCardDate = attnCardDate;
	}

	public String getAttnCardTel() {
		return attnCardTel;
	}

	public void setAttnCardTel(String attnCardTel) {
		this.attnCardTel = attnCardTel;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWarn() {
		return warn;
	}

	public void setWarn(String warn) {
		this.warn = warn;
	}

	public FormFile getBiFile() {
		return biFile;
	}

	public void setBiFile(FormFile biFile) {
		this.biFile = biFile;
	}

	public FormFile getTaxFile() {
		return taxFile;
	}

	public void setTaxFile(FormFile taxFile) {
		this.taxFile = taxFile;
	}

	public FormFile getOrgFile() {
		return orgFile;
	}

	public void setOrgFile(FormFile orgFile) {
		this.orgFile = orgFile;
	}

}