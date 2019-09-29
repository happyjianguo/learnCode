package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class CardProductBean implements Serializable {
	private String id;// Ψһ��ʾ��
	private String inst_id;// ������ţ��������INST��
	private String crdproduct;// ����Ʒ�ı�ʾ��
	private String descr;// ����
	private String crdformat_id;// ��Ӧ�Ŀ�Ƭ��ʽ
	private String def_typeacc;// Ĭ���˻�����
	private String prodnum;// ��Ʒ����
	private String prodseq;// ��Ʒ����

	public CardProductBean() {

	}

	public CardProductBean(HashMap record) {
		// ����java�������Ϊjavabean��ֵ
		RecordMethod recordMethod = new RecordMethod(record, CardProductBean.class, this);
		recordMethod.recordset();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String instId) {
		inst_id = instId;
	}
	public String getCrdproduct() {
		return crdproduct;
	}
	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getCrdformat_id() {
		return crdformat_id;
	}
	public void setCrdformat_id(String crdformatId) {
		crdformat_id = crdformatId;
	}
	public String getDef_typeacc() {
		return def_typeacc;
	}
	public void setDef_typeacc(String defTypeacc) {
		def_typeacc = defTypeacc;
	}
	public String getProdnum() {
		return prodnum;
	}
	public void setProdnum(String prodnum) {
		this.prodnum = prodnum;
	}
	public String getProdseq() {
		return prodseq;
	}
	public void setProdseq(String prodseq) {
		this.prodseq = prodseq;
	}

}
