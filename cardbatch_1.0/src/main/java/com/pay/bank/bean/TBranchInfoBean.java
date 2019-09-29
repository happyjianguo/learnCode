package com.pay.bank.bean;

import java.util.HashMap;

import com.pay.util.StringUtils;

public class TBranchInfoBean {
	private String branch_code;
	private String branch_name;
	private String bank_code;
	private String bankCodeAndBranchCode;
	
	public TBranchInfoBean(){
		
	}
	
	public TBranchInfoBean(HashMap record){
		if(record.get("branch_code") == null){
			this.setBranch_code("");
		} else {
			this.setBranch_code(StringUtils.innerToOuter((String)record.get("branch_code")).trim());
		}		
		if(record.get("branch_name") == null){
			this.setBranch_name("");
		} else {
			this.setBranch_name(StringUtils.innerToOuter((String)record.get("branch_name")).trim());
		}
		if(record.get("branch_name") == null){
			this.setBank_code("");
		} else {
			this.setBank_code(StringUtils.innerToOuter((String)record.get("bank_code")).trim());
		}
		if(record.get("bankcodeandbranchcode") == null){
			this.setBankCodeAndBranchCode("");
		} else {
			this.setBankCodeAndBranchCode(StringUtils.innerToOuter((String)record.get("bankcodeandbranchcode")).trim());
		}
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
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBankCodeAndBranchCode() {
		return bankCodeAndBranchCode;
	}

	public void setBankCodeAndBranchCode(String bankCodeAndBranchCode) {
		this.bankCodeAndBranchCode = bankCodeAndBranchCode;
	}
	
}
