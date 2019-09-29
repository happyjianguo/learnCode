package cn.yufu.posp.merchant.domain.model;

import cn.yufu.core.common.util.Util;

/**
 * MerchantIdentity entity. @author MyEclipse Persistence Tools
 */

public class MerchantIdentity implements java.io.Serializable {

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
		return shareCardType==null?shareCardType:shareCardType.trim();
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
		return legalCardType==null?legalCardType:legalCardType.trim();
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
		return attnCardType==null?attnCardType:attnCardType.trim();
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
		return classType==null?classType:classType.trim();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("商户Id：").append(this.merchantId).append("<br>商户名：").append(this.merchantCname);
		if (!Util.stringIsEmpty(this.biCardNo)) {
			sb.append("<br>营业证件号码：").append(this.biCardNo);
		}
		if (!Util.stringIsEmpty(this.biCardDate)) {
			sb.append("<br>营业证件有效期：").append(this.biCardDate);
		}
		if (!Util.stringIsEmpty(this.taxCardNo)) {
			sb.append("<br>税务证件号码：").append(this.taxCardNo);
		}
		if (!Util.stringIsEmpty(this.taxCardDate)) {
			sb.append("<br>税务证件有效期：").append(this.taxCardDate);
		}
		if (!Util.stringIsEmpty(this.orgCardNo)) {
			sb.append("<br>机构证件号码：").append(this.orgCardNo);
		}
		if (!Util.stringIsEmpty(this.orgCardNo)) {
			sb.append("<br>机构证件有效期：").append(this.orgCardNo);
		}
		if (!Util.stringIsEmpty(this.shareName)) {
			sb.append("<br>股东姓名：").append(this.shareName);
		}
		if (!Util.stringIsEmpty(this.shareCardType)) {
			sb.append("<br>股东证件类型：").append(this.shareCardType);
		}
		if (!Util.stringIsEmpty(this.shareCardNo)) {
			sb.append("<br>股东证件号码：").append(this.shareCardNo);
		}
		if (!Util.stringIsEmpty(this.shareCardDate)) {
			sb.append("<br>股东证件有效期：").append(this.shareCardDate);
		}
		if (!Util.stringIsEmpty(this.shareCardTel)) {
			sb.append("<br>联系方式：").append(this.shareCardTel);
		}
		if (!Util.stringIsEmpty(this.legalName)) {
			sb.append("<br>法定姓名：").append(this.legalName);
		}
		if (!Util.stringIsEmpty(this.legalCardType)) {
			sb.append("<br>法定证件类型：").append(this.legalCardType);
		}
		if (!Util.stringIsEmpty(this.legalCardNo)) {
			sb.append("<br>法定证件号码：").append(this.legalCardNo);
		}
		if (!Util.stringIsEmpty(this.legalCardDate)) {
			sb.append("<br>法定证件有效期：").append(this.legalCardDate);
		}
		if (!Util.stringIsEmpty(this.legalCardTel)) {
			sb.append("<br>法定方式：").append(this.legalCardTel);
		}
		if (!Util.stringIsEmpty(this.attnName)) {
			sb.append("<br>经办人姓名：").append(this.attnName);
		}
		if (!Util.stringIsEmpty(this.attnCardType)) {
			sb.append("<br>经办人证件类型：").append(this.attnCardType);
		}
		if (!Util.stringIsEmpty(this.attnCardNo)) {
			sb.append("<br>经办人证件号码：").append(this.attnCardNo);
		}
		if (!Util.stringIsEmpty(this.legalCardDate)) {
			sb.append("<br>经办人证件有效期：").append(this.legalCardDate);
		}
		if (!Util.stringIsEmpty(this.attnCardTel)) {
			sb.append("<br>联系方式：").append(this.attnCardTel);
		}
		if (!Util.stringIsEmpty(this.classType)) {
			sb.append("<br>商户等级：").append(this.classType);
		}
		return sb.toString();
	}

}