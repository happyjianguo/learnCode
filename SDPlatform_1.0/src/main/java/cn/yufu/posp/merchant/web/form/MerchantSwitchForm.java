package cn.yufu.posp.merchant.web.form;

import cn.yufu.core.web.form.BaseForm;

public class MerchantSwitchForm extends BaseForm{
	
	private String merchantId;//商户编号
	private String bankType;//银行类型
	private String othMerchantId;//他行商户编号
	private String othMcc;//他行商户类型
	
	//商户名称
	private String merchantName;
	//银行类型名称
	private String bankTypeName;
	
	//查询条件
	private String queryMerId;
	private String queryBank;
	
	//参数
	private String bankId;
	
	
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getOthMerchantId() {
		return othMerchantId;
	}
	public void setOthMerchantId(String othMerchantId) {
		this.othMerchantId = othMerchantId;
	}
	public String getOthMcc() {
		return othMcc;
	}
	public void setOthMcc(String othMcc) {
		this.othMcc = othMcc;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getQueryMerId() {
		return queryMerId;
	}
	public void setQueryMerId(String queryMerId) {
		this.queryMerId = queryMerId;
	}
	public String getQueryBank() {
		return queryBank;
	}
	public void setQueryBank(String queryBank) {
		this.queryBank = queryBank;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankTypeName() {
		return bankTypeName;
	}
	public void setBankTypeName(String bankTypeName) {
		this.bankTypeName = bankTypeName;
	}
	
	
}
