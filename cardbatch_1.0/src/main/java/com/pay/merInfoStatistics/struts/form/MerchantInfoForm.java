package com.pay.merInfoStatistics.struts.form;

import org.apache.struts.action.ActionForm;

public class MerchantInfoForm extends ActionForm {

	private static final long serialVersionUID = -9203266884712009277L;
	
	private String id;
	
	private String name;
	
	private String fullname;
	
	private String type;
	
	private String status;
	
	private String province;
	
	private String city;
	
	private String area;
	
	//！！！！！！！！！！！！ query ！！！！！！！！！！
	
	private String qid;
	
	private String qname;
	
	private String qfullname;
	
	private String qtype;
	
	private String qstatus;
	
	private String qprovince;
	
	private String qcity;
	
	private String qarea;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public String getQname() {
		return qname;
	}

	public void setQname(String qname) {
		this.qname = qname;
	}

	public String getQfullname() {
		return qfullname;
	}

	public void setQfullname(String qfullname) {
		this.qfullname = qfullname;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public String getQstatus() {
		return qstatus;
	}

	public void setQstatus(String qstatus) {
		this.qstatus = qstatus;
	}

	public String getQprovince() {
		return qprovince;
	}

	public void setQprovince(String qprovince) {
		this.qprovince = qprovince;
	}

	public String getQcity() {
		return qcity;
	}

	public void setQcity(String qcity) {
		this.qcity = qcity;
	}

	public String getQarea() {
		return qarea;
	}

	public void setQarea(String qarea) {
		this.qarea = qarea;
	}
	
}
