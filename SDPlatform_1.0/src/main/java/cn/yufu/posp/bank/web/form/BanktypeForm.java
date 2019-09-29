package cn.yufu.posp.bank.web.form;

import cn.yufu.core.web.form.BaseForm;

/**
 * Banktype entity. @author MyEclipse Persistence Tools
 */

public class BanktypeForm extends BaseForm implements java.io.Serializable {

	// Fields

	private String bankType;
	private String typeName;
	private String typeEname;

	// Property accessors

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeEname() {
		return this.typeEname;
	}

	public void setTypeEname(String typeEname) {
		this.typeEname = typeEname;
	}

}