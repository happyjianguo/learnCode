package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class AcctBean implements Serializable {
	private String inst_id;// ����ID
	private String accno;// �˻���
	private String currcode;// ���Ҵ���
	private String branch_id;// Branch ID.�������BRANCH��
	private String typecode;// �˻�����
	private String classid;// �˻�����:1: ����˻�2: �����˻�.
	private String statcode;// ״̬��
	private String vipflag;// VIP ���:0: ��VIP1-9: VIP ����
	private String blkamt;// �����Ľ��
	private String avlbal;// ���ý��
	private String clrbal;// ������Ľ��
	private String unclrbal;// δ����Ľ��
	private String credit_limit;// ���ö��
	private String id;// �˻���Ψһ�Ա�ʶ������ֵ�������л�ȡ��
	private String custdet_id;// �˿�ID
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
		// ����java�������Ϊjavabean��ֵ
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
