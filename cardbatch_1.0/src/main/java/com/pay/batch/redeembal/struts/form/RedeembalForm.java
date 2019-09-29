package com.pay.batch.redeembal.struts.form;

import org.apache.struts.action.ActionForm;

public class RedeembalForm extends ActionForm {
	private String verno_ctx;
	private String id;
	private String txtime;
	private String stan;
	private String pan;
	private String amt;
	private String father_order;
	private String children_order;
	private String acct_period;
	private String sales_point;
	private String area;
	private String id_type;
	private String id_number;
	private String cell_phone;
	private String phone;
	private String rb_type;
	private String bank_name;
	private String branch_name;
	private String bank_pan;
	private String card_acceptor_name;
	private String card_acceptor_id;
	private String interest;
	private String fee;
	private String ispaid;
	private String summary;
	private String operator;
	private String mrcht_id;
	private String batch_stat;
	private String descr;
	private String txncode;

	private String starttime;
	private String endtime;

	private String start_period;
	private String end_period;
	private String city_no;
	private String province;
	private String zone;
	
	private String curtxn;		//	交易币种
	private String amttxn;		//	交易金额
	private String rateset;		//	交易汇率
	private String currbill;	//	清算币种
	private String crdproduct;	//	卡类型
	
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

	public String getTxtime() {
		return txtime;
	}

	public void setTxtime(String txtime) {
		this.txtime = txtime;
	}

	public String getStan() {
		return stan;
	}

	public void setStan(String stan) {
		this.stan = stan;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
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

	public String getAcct_period() {
		return acct_period;
	}

	public void setAcct_period(String acct_period) {
		this.acct_period = acct_period;
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

	public String getRb_type() {
		return rb_type;
	}

	public void setRb_type(String rb_type) {
		this.rb_type = rb_type;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getBank_pan() {
		return bank_pan;
	}

	public void setBank_pan(String bank_pan) {
		this.bank_pan = bank_pan;
	}

	public String getCard_acceptor_name() {
		return card_acceptor_name;
	}

	public void setCard_acceptor_name(String card_acceptor_name) {
		this.card_acceptor_name = card_acceptor_name;
	}

	public String getCard_acceptor_id() {
		return card_acceptor_id;
	}

	public void setCard_acceptor_id(String card_acceptor_id) {
		this.card_acceptor_id = card_acceptor_id;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
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

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getTxncode() {
		return txncode;
	}

	public void setTxncode(String txncode) {
		this.txncode = txncode;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
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
