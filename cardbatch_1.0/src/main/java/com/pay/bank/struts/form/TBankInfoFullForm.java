package com.pay.bank.struts.form;

import org.apache.struts.action.ActionForm;

public class TBankInfoFullForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String bank_code;
	private String bank_name;
	private String bank_short_name;
	private String logo;
	
	private String branch_code;
	private String branch_name;
	private String bankCodeAndBranchCode;
	
	private String queryBankCode;
	private String queryBankName;	
	private String queryLogo;

	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_short_name() {
		return bank_short_name;
	}
	public void setBank_short_name(String bank_short_name) {
		this.bank_short_name = bank_short_name;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getBranch_code() {
		return branch_code;
	}
	public void setBranch_code(String branch_code) {
		this.branch_code = branch_code;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getQueryBankCode() {
		return queryBankCode;
	}
	public void setQueryBankCode(String queryBankCode) {
		this.queryBankCode = queryBankCode;
	}
	public String getQueryBankName() {
		return queryBankName;
	}
	public void setQueryBankName(String queryBankName) {
		this.queryBankName = queryBankName;
	}
	public String getQueryLogo() {
		return queryLogo;
	}
	public void setQueryLogo(String queryLogo) {
		this.queryLogo = queryLogo;
	}
	public String getBankCodeAndBranchCode() {
		return bankCodeAndBranchCode;
	}
	public void setBankCodeAndBranchCode(String bankCodeAndBranchCode) {
		this.bankCodeAndBranchCode = bankCodeAndBranchCode;
	}
	
}
