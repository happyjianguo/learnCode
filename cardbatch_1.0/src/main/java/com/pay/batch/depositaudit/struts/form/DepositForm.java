package com.pay.batch.depositaudit.struts.form;

import org.apache.struts.action.ActionForm;

public class DepositForm extends ActionForm {
	private String verno_ctx;
	private String id;
	private String txnsrc;
	private String txncode;
	private String time;
	private String stan;
	private String pan_start;
	private String pan_end;
	private String pan_count;
	private String amt_each_crd;
	private String father_order;
	private String children_order;
	private String org_f_order;
	private String org_c_order;
	private String acct_period;
	private String pay_type;
	private String pay_desc;
	private String payer_name;
	private String sales_point;
	private String area;
	private String id_type;
	private String id_number;
	private String cell_phone;
	private String phone;
	private String address;
	private String cashin_type;
	private String ispaid;
	private String summary;
	private String operator;
	private String mrcht_id;
	private String batch_stat;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String descr;
	private String startdate;
	private String enddate;
	private String start_period;
	private String end_period;
	private String city_no;
	private String province;
	private String zone;
	
	private String curtxn;		
	private String amttxn;		
	private String rateset;		
	private String currbill;	
	private String crdproduct;	
	
	private String descrStartDate;	//审批日期开始
	private String descrEndDate;	//审批日期结束
	
	public String getCurtxn() {
		return curtxn;
	}

	public void setCurtxn(String curtxn) {
		this.curtxn = curtxn;
	}

	public String getAmttxn() {
		return amttxn;
	}

	public void setAmttxn(String amttxn) {
		this.amttxn = amttxn;
	}

	public String getRateset() {
		return rateset;
	}

	public void setRateset(String rateset) {
		this.rateset = rateset;
	}

	public String getCurrbill() {
		return currbill;
	}

	public void setCurrbill(String currbill) {
		this.currbill = currbill;
	}

	public String getCrdproduct() {
		return crdproduct;
	}

	public void setCrdproduct(String crdproduct) {
		this.crdproduct = crdproduct;
	}

	public String getCity_no() {
		return city_no;
	}

	public void setCity_no(String city_no) {
		this.city_no = city_no;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getStart_period() {
		return start_period;
	}

	public void setStart_period(String start_period) {
		this.start_period = start_period;
	}

	public String getEnd_period() {
		return end_period;
	}

	public void setEnd_period(String end_period) {
		this.end_period = end_period;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getVerno_ctx() {
		return verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		this.verno_ctx = verno_ctx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTxnsrc() {
		return txnsrc;
	}

	public void setTxnsrc(String txnsrc) {
		this.txnsrc = txnsrc;
	}

	public String getTxncode() {
		return txncode;
	}

	public void setTxncode(String txncode) {
		this.txncode = txncode;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getPan_start() {
		return pan_start;
	}

	public void setPan_start(String pan_start) {
		this.pan_start = pan_start;
	}

	public String getPan_end() {
		return pan_end;
	}

	public void setPan_end(String pan_end) {
		this.pan_end = pan_end;
	}

	public String getPan_count() {
		return pan_count;
	}

	public void setPan_count(String pan_count) {
		this.pan_count = pan_count;
	}

	public String getAmt_each_crd() {
		return amt_each_crd;
	}

	public void setAmt_each_crd(String amt_each_crd) {
		this.amt_each_crd = amt_each_crd;
	}

	public String getFather_order() {
		return father_order;
	}

	public void setFather_order(String father_order) {
		this.father_order = father_order;
	}

	public String getChildren_order() {
		return children_order;
	}

	public void setChildren_order(String children_order) {
		this.children_order = children_order;
	}

	public String getOrg_f_order() {
		return org_f_order;
	}

	public void setOrg_f_order(String org_f_order) {
		this.org_f_order = org_f_order;
	}

	public String getOrg_c_order() {
		return org_c_order;
	}

	public void setOrg_c_order(String org_c_order) {
		this.org_c_order = org_c_order;
	}

	public String getAcct_period() {
		return acct_period;
	}

	public void setAcct_period(String acct_period) {
		this.acct_period = acct_period;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getPay_desc() {
		return pay_desc;
	}

	public void setPay_desc(String pay_desc) {
		this.pay_desc = pay_desc;
	}

	public String getPayer_name() {
		return payer_name;
	}

	public void setPayer_name(String payer_name) {
		this.payer_name = payer_name;
	}

	public String getSales_point() {
		return sales_point;
	}

	public void setSales_point(String sales_point) {
		this.sales_point = sales_point;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getId_type() {
		return id_type;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCashin_type() {
		return cashin_type;
	}

	public void setCashin_type(String cashin_type) {
		this.cashin_type = cashin_type;
	}

	public String getIspaid() {
		return ispaid;
	}

	public void setIspaid(String ispaid) {
		this.ispaid = ispaid;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMrcht_id() {
		return mrcht_id;
	}

	public void setMrcht_id(String mrcht_id) {
		this.mrcht_id = mrcht_id;
	}

	public String getBatch_stat() {
		return batch_stat;
	}

	public void setBatch_stat(String batch_stat) {
		this.batch_stat = batch_stat;
	}

	public String getReserved1() {
		return reserved1;
	}

	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}

	public String getReserved2() {
		return reserved2;
	}

	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}

	public String getReserved3() {
		return reserved3;
	}

	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getDescrStartDate() {
		return descrStartDate;
	}

	public void setDescrStartDate(String descrStartDate) {
		this.descrStartDate = descrStartDate;
	}

	public String getDescrEndDate() {
		return descrEndDate;
	}

	public void setDescrEndDate(String descrEndDate) {
		this.descrEndDate = descrEndDate;
	}

}
