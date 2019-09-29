package com.pay.bank.struts.form;

import org.apache.struts.action.ActionForm;

public class TBranchInfoForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String branch_code;
	private String branch_name;
	private String bank_code;
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
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
}
