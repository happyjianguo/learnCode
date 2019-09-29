package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class AcctBean implements Serializable {
	private String inst_id;// 机构ID
	private String accno;// 账户号
	private String currcode;// 货币代码
	private String branch_id;// Branch ID.外键引用BRANCH表
	private String typecode;// 账户类型
	private String classid;// 账户类型:1: 借记账户2: 记账账户.
	private String statcode;// 状态码
	private String vipflag;// VIP 标记:0: 非VIP1-9: VIP 级别
	private String blkamt;// 锁定的金额
	private String avlbal;// 可用金额
	private String clrbal;// 已清算的金额
	private String unclrbal;// 未清算的金额
	private String credit_limit;// 信用额度
	private String id;// 账户的唯一性标识符（数值从序列中获取）
	private String custdet_id;// 顾客ID
	private String typecode_name;
	private String statcode_name;

	public String getTypecode_name() {
		return typecode_name;
	}
	public void setTypecode_name(String typecodeName) {
		typecode_name = typecodeName;
	}
	public String getStatcode_name() {
		return statcode_name;
	}
	public void setStatcode_name(String statcodeName) {
		statcode_name = statcodeName;
	}
	public AcctBean() {

	}
	public AcctBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, AcctBean.class, this);
		recordMethod.recordset();

	}
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String instId) {
		inst_id = instId;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getCurrcode() {
		return currcode;
	}
	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}
	public String getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(String branchId) {
		branch_id = branchId;
	}
	public String getTypecode() {
		return typecode;
	}
	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
	public String getStatcode() {
		return statcode;
	}
	public void setStatcode(String statcode) {
		this.statcode = statcode;
	}
	public String getVipflag() {
		return vipflag;
	}
	public void setVipflag(String vipflag) {
		this.vipflag = vipflag;
	}
	public String getBlkamt() {
		return blkamt;
	}
	public void setBlkamt(String blkamt) {
		this.blkamt = blkamt;
	}
	public String getAvlbal() {
		return avlbal;
	}
	public void setAvlbal(String avlbal) {
		this.avlbal = avlbal;
	}
	public String getClrbal() {
		return clrbal;
	}
	public void setClrbal(String clrbal) {
		this.clrbal = clrbal;
	}
	public String getUnclrbal() {
		return unclrbal;
	}
	public void setUnclrbal(String unclrbal) {
		this.unclrbal = unclrbal;
	}
	public String getCredit_limit() {
		return credit_limit;
	}
	public void setCredit_limit(String creditLimit) {
		credit_limit = creditLimit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustdet_id() {
		return custdet_id;
	}
	public void setCustdet_id(String custdetId) {
		custdet_id = custdetId;
	}

}
