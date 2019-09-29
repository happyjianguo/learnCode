package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class CardProductBean implements Serializable {
	private String id;// 唯一标示符
	private String inst_id;// 机构编号（外键引用INST表）
	private String crdproduct;// 卡产品的标示符
	private String descr;// 描述
	private String crdformat_id;// 对应的卡片格式
	private String def_typeacc;// 默认账户类型
	private String prodnum;// 产品号码
	private String prodseq;// 产品序列

	public CardProductBean() {

	}

	public CardProductBean(HashMap record) {
		// 利用java反射机制为javabean赋值
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
