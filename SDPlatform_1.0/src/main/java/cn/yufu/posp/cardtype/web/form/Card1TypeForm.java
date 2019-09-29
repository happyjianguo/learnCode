package cn.yufu.posp.cardtype.web.form;

import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

public class Card1TypeForm extends BaseForm {
	
	private String cardType;
	private String typeName;
	private String typeEname;
	
	private String queryCardType;
	private String queryTypeName;
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeEname() {
		return typeEname;
	}
	public void setTypeEname(String typeEname) {
		this.typeEname = typeEname;
	}
	public String getQueryCardType() {
		return queryCardType;
	}
	public void setQueryCardType(String queryCardType) {
		this.queryCardType = queryCardType;
	}
	public String getQueryTypeName() {
		return queryTypeName;
	}
	public void setQueryTypeName(String queryTypeName) {
		this.queryTypeName = queryTypeName;
	}
	
	
}
