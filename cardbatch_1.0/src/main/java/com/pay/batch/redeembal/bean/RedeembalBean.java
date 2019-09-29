package com.pay.batch.redeembal.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class RedeembalBean {
	
	private String id;
	private String txncode;
	private String rb_type;
	private String txtime;
	private String stan;
	private String pan;
	private String amt;
	private String father_order;
	private String children_order;
	
	private String sales_point;
	private String area;
	private String operator;
	private String summary;
	private String mrcht_id;
	private String ispaid;
	private String batch_stat;
	private String acct_period;
	private String descr_u;
	private String descr_t;
	private String id_type;
	private String id_number;
	private String cell_phone;
	private String phone;
	private String bank_name;
	private String branch_name;
	private String bank_pan;
	private String card_acceptor_name;
	private String card_acceptor_id;
	private String interest;
	private String fee;
	private String act1;
	private String act2;
	private String act3;
	private String act4;
	private String act5;
	private String act6;
	private String act7;
	private String act8;
	private String act9;
	
	
	

	private String descr;
	
	

	private String starttime;
	private String endtime;

	private String start_period;
	private String end_period;
	
	
	private String verno_ctx;
	
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

	public String getAct1() {
		return act1;
	}

	public void setAct1(String act1) {
		this.act1 = act1;
	}

	public String getAct2() {
		return act2;
	}

	public void setAct2(String act2) {
		this.act2 = act2;
	}

	public String getAct3() {
		return act3;
	}

	public void setAct3(String act3) {
		this.act3 = act3;
	}

	public String getAct4() {
		return act4;
	}

	public void setAct4(String act4) {
		this.act4 = act4;
	}

	public String getAct5() {
		return act5;
	}

	public void setAct5(String act5) {
		this.act5 = act5;
	}

	public String getAct6() {
		return act6;
	}

	public void setAct6(String act6) {
		this.act6 = act6;
	}

	public String getAct7() {
		return act7;
	}

	public void setAct7(String act7) {
		this.act7 = act7;
	}

	public String getAct8() {
		return act8;
	}

	public void setAct8(String act8) {
		this.act8 = act8;
	}

	public String getAct9() {
		return act9;
	}

	public void setAct9(String act9) {
		this.act9 = act9;
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
	public String getDescr_u() {
		return descr_u;
	}

	public void setDescr_u(String descr_u) {
		//this.descr_u = descr_u;
	}

	public String getDescr_t() {
		return descr_t;
	}

	public void setDescr_t(String descr_t) {
		//this.descr_t = descr_t;
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

	public RedeembalBean() {

	}

	public RedeembalBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record,
				RedeembalBean.class, this);
		recordMethod.recordset();

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
//		if(sales_point.contains(",")){
//			this.sales_point = sales_point.split(",", -1)[1];
//		}else{
//			this.sales_point = sales_point;
//		}
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
//		if(operator.contains(",")){
//			this.operator = operator.split(",", -1)[1];
//		}else{
//			this.operator = operator;
//		}
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
		if (descr.trim().length()>0&&descr.contains(",")) {
			
			this.descr_u = descr.split(",", -1)[0];
			this.descr_t = descr.split(",", -1)[1];
			
		}
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
