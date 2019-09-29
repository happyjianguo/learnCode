package com.pay.merchant.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class Merchant_xBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String inst_id;
	private String mrchno;
	private String mrcht_name;
	private String address;
	private String add_date;
	private String city_no;
	private String province;
	private String zone;
	private String state;
	private String type_yf;
	private String agent;
	private String id_type;
	private String id_no;
	private String id_validity;
	private String bus_lic_no;
	private String bus_lic_validity;
	private String tax_id;
	private String tax_id_validity;
	private String org_id;
	private String org_validity;
	private String bus_lic_pic;
	private String tax_id_pic;
	private String org_id_pic;
	//InputStream 是为了保存上传的文件流使用的
//	private InputStream bus_input;
//	private InputStream tax_input;
//	private InputStream org_input;
	private String telno1;
	private String postcode;
	private String contact3;
	private String accno;
	private String merchant_id;
	private String legal_rep;		//法定代表人（负责人）姓名
	private String lr_id_type;		//有效证件类型
	private String lr_id_no;		//号码
	private String lr_id_validity;	//有效期
	private String man_name;		//控股股东或实际控制人姓名
	private String id_type1;		//控股股东或实际控制人证件种类
	private String id_no1;		//控股股东或实际控制人证件号码
	private String id_deadline1;	//控股股东或实际控制人证件有效期截止日
	private String acc_x_name;	
	private String settlement_person;	//结算员
	private String merchant_email;	//邮箱
	private String is_send_billdetail;	//是否发送对账明细
	private String merchant_x_operate;	//企业经营范围
	private String merchant_x_type;	//商户分类:11：自然人，12：单位，默认单位
	private BigDecimal merchant_x_reg_amt;	//注册资本金--NUMBER(18,2)
	private String merchant_x_code;	//注册资本金币种:人民币-RMB，美元-USD，日元-JPY，欧元-EUR，英镑-GBP，德国马克-DEM，瑞士法郎-CHF，法国法郎-FRF，默认：人民币-RMB
	private String combine_flag;	//“节假日结算日期是否合并”，0不合并1合并，默认1合并
	
	
	private String fs_cycle;//结算周期
	private String fs_cycle_param;//结算周期参数
	private String last_settle_date;//上次结算日期
	private String is_consump_category;//是否按照不同消费场景结算:0.否；1.是
	private String amt_consump_category;//结算金额是否按照场景合并结算或分别结算（结算场景都要显示，只是结算金额是否要合并）:0.否；1.是
	private String is_fmrchno_decide;//是否按父商户结算:0.否；1.是
	private String fs_kp_cycle;
	private String fs_kp_cycle_param;	
	private String last_kp_date;
	
	private String is_bj;//是否属于北京，0否，1是
	private String is_card_type_group;//是否按照卡类别分组，0否，1是
	private String is_kp;//是否开票，0否，1是
	private String disabled_date;//停用时间
	private String enable_date;	//启用时间
	
	private String merchant_org;	//商户机构
	private String merchant_fenrunorg;	//分润机构
	
	//商户摘要信息
	private String mrch_snippet;
	//普卡专属卡是否合并打款
	private String merge_money_flag;
	
	public Merchant_xBean() {
		
	}
	public Merchant_xBean (HashMap record) throws Exception{
		for (Object key : record.keySet()) {
			//System.out.println("--------key=[" + key + "]" + record.get(key));
			
			Class[] cargs = new Class[1];
			String realArgs = "";
			cargs[0] = realArgs.getClass();
			Method method = this.getClass().getMethod(
					"set"
							+ StringUtils.toUpperCaseFirstOne(((String) key)
									.toLowerCase()), cargs);
			Object arglist[] = new Object[1];
			if (record.get(key) == null) {
				arglist[0] = "";
			} else {
				arglist[0] = StringUtils.innerToOuter((String) record.get(key))
						.trim();
			}
			method.invoke(this, arglist);
		}
		
	}
	
	
	public String getCombine_flag() {
		return combine_flag;
	}
	public void setCombine_flag(String combine_flag) {
		this.combine_flag = combine_flag;
	}
	public String getMerchant_fenrunorg() {
		return merchant_fenrunorg;
	}
	public void setMerchant_fenrunorg(String merchant_fenrunorg) {
		this.merchant_fenrunorg = (merchant_fenrunorg == null || merchant_fenrunorg.trim().length()==0)?"":merchant_fenrunorg.trim();
	}
	public String getLast_kp_date() {
		return last_kp_date;
	}
	public void setLast_kp_date(String lastKpDate) {
		last_kp_date = lastKpDate;
	}
	public String getFs_kp_cycle() {
		return fs_kp_cycle;
	}
	public void setFs_kp_cycle(String fsKpCycle) {
		fs_kp_cycle = fsKpCycle;
	}
	public String getFs_kp_cycle_param() {
		return fs_kp_cycle_param;
	}
	public void setFs_kp_cycle_param(String fsKpCycleParam) {
		fs_kp_cycle_param = fsKpCycleParam;
	}
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}
	public String getMrchno() {
		return mrchno;
	}
	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}
	public String getMrcht_name() {
		return mrcht_name;
	}
	public void setMrcht_name(String mrcht_name) {
		this.mrcht_name = mrcht_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAdd_date() {
		return add_date;
	}
	public void setAdd_date(String add_date) {
		this.add_date = add_date;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType_yf() {
		return type_yf;
	}
	public void setType_yf(String type_yf) {
		this.type_yf = type_yf;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getId_no() {
		return id_no;
	}
	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
	public String getId_validity() {
		return id_validity;
	}
	public void setId_validity(String id_validity) {
		this.id_validity = id_validity;
	}
	public String getBus_lic_no() {
		return bus_lic_no;
	}
	public void setBus_lic_no(String bus_lic_no) {
		this.bus_lic_no = bus_lic_no;
	}
	public String getBus_lic_validity() {
		return bus_lic_validity;
	}
	public void setBus_lic_validity(String bus_lic_validity) {
		this.bus_lic_validity = bus_lic_validity;
	}
	public String getTax_id() {
		return tax_id;
	}
	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}
	public String getTax_id_validity() {
		return tax_id_validity;
	}
	public void setTax_id_validity(String tax_id_validity) {
		this.tax_id_validity = tax_id_validity;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}
	public String getOrg_validity() {
		return org_validity;
	}
	public void setOrg_validity(String org_validity) {
		this.org_validity = org_validity;
	}
	public String getBus_lic_pic() {
		return bus_lic_pic;
	}
	public void setBus_lic_pic(String bus_lic_pic) {
		this.bus_lic_pic = bus_lic_pic;
	}
	public String getTax_id_pic() {
		return tax_id_pic;
	}
	public void setTax_id_pic(String tax_id_pic) {
		this.tax_id_pic = tax_id_pic;
	}
	public String getOrg_id_pic() {
		return org_id_pic;
	}
	public void setOrg_id_pic(String org_id_pic) {
		this.org_id_pic = org_id_pic;
	}
	public String getTelno1() {
		return telno1;
	}
	public void setTelno1(String telno1) {
		this.telno1 = telno1;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getContact3() {
		return contact3;
	}
	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}
	public String getLegal_rep() {
		return legal_rep;
	}
	public void setLegal_rep(String legalRep) {
		legal_rep = legalRep;
	}
	public String getLr_id_type() {
		return lr_id_type;
	}
	public void setLr_id_type(String lrIdType) {
		lr_id_type = lrIdType;
	}
	public String getLr_id_no() {
		return lr_id_no;
	}
	public void setLr_id_no(String lrIdNo) {
		lr_id_no = lrIdNo;
	}
	public String getLr_id_validity() {
		return lr_id_validity;
	}
	public void setLr_id_validity(String lrIdValidity) {
		lr_id_validity = lrIdValidity;
	}
//	public InputStream getBus_input() {
//		return bus_input;
//	}
//	public InputStream getTax_input() {
//		return tax_input;
//	}
//	public InputStream getOrg_input() {
//		return org_input;
//	}
//	public void setBus_input(InputStream bus_input) {
//		this.bus_input = bus_input;
//	}
//	public void setTax_input(InputStream tax_input) {
//		this.tax_input = tax_input;
//	}
//	public void setOrg_input(InputStream org_input) {
//		this.org_input = org_input;
//	}
	public String getFs_cycle() {
		return fs_cycle;
	}
	public void setFs_cycle(String fsCycle) {
		fs_cycle = fsCycle;
	}
	public String getFs_cycle_param() {
		return fs_cycle_param;
	}
	public void setFs_cycle_param(String fsCycleParam) {
		fs_cycle_param = fsCycleParam;
	}
	public String getLast_settle_date() {
		return last_settle_date;
	}
	public void setLast_settle_date(String lastSettleDate) {
		last_settle_date = lastSettleDate;
	}
	public String getIs_consump_category() {
		return is_consump_category;
	}
	public void setIs_consump_category(String isConsumpCategory) {
		is_consump_category = isConsumpCategory;
	}
	public String getAmt_consump_category() {
		return amt_consump_category;
	}
	public void setAmt_consump_category(String amtConsumpCategory) {
		amt_consump_category = amtConsumpCategory;
	}
	public String getIs_fmrchno_decide() {
		return is_fmrchno_decide;
	}
	public void setIs_fmrchno_decide(String isFmrchnoDecide) {
		is_fmrchno_decide = isFmrchnoDecide;
	}
	public String getIs_bj() {
		return is_bj;
	}
	public void setIs_bj(String isBj) {
		is_bj = isBj;
	}
	public String getIs_card_type_group() {
		return is_card_type_group;
	}
	public void setIs_card_type_group(String isCardTypeGroup) {
		is_card_type_group = isCardTypeGroup;
	}
	public String getIs_kp() {
		return is_kp;
	}
	public void setIs_kp(String isKp) {
		is_kp = isKp;
	}
	public String getDisabled_date() {
		return disabled_date;
	}
	public void setDisabled_date(String disabledDate) {
		disabled_date = disabledDate;
	}
	public String getEnable_date() {
		return enable_date;
	}
	public void setEnable_date(String enableDate) {
		enable_date = enableDate;
	}
	public String getMrch_snippet() {
		return mrch_snippet;
	}
	public void setMrch_snippet(String mrch_snippet) {
		this.mrch_snippet = mrch_snippet;
	}
	public String getMerge_money_flag() {
		return merge_money_flag;
	}
	public void setMerge_money_flag(String merge_money_flag) {
		this.merge_money_flag = merge_money_flag;
	}
	public String getMerchant_org() {
		return merchant_org;
	}
	public void setMerchant_org(String merchant_org) {
		this.merchant_org = (merchant_org == null || merchant_org.trim().length()==0)?"":merchant_org.trim();
	}
	public String getMan_name() {
		return man_name;
	}
	public void setMan_name(String man_name) {
		this.man_name = man_name;
	}
	public String getId_type1() {
		return id_type1;
	}
	public void setId_type1(String id_type1) {
		this.id_type1 = id_type1;
	}
	public String getId_no1() {
		return id_no1;
	}
	public void setId_no1(String id_no1) {
		this.id_no1 = id_no1;
	}
	public String getId_deadline1() {
		return id_deadline1;
	}
	public void setId_deadline1(String id_deadline1) {
		this.id_deadline1 = id_deadline1;
	}
	public String getAcc_x_name() {
		return acc_x_name;
	}
	public void setAcc_x_name(String acc_x_name) {
		this.acc_x_name = acc_x_name;
	}
	public String getSettlement_person() {
		return settlement_person;
	}
	public void setSettlement_person(String settlement_person) {
		this.settlement_person = settlement_person;
	}
	public String getMerchant_email() {
		return merchant_email;
	}
	public void setMerchant_email(String merchant_email) {
		this.merchant_email = merchant_email;
	}
	public String getIs_send_billdetail() {
		return is_send_billdetail;
	}
	public void setIs_send_billdetail(String is_send_billdetail) {
		this.is_send_billdetail = is_send_billdetail;
	}
	public String getMerchant_x_operate() {
		return merchant_x_operate;
	}
	public void setMerchant_x_operate(String merchant_x_operate) {
		this.merchant_x_operate = merchant_x_operate;
	}
	public String getMerchant_x_type() {
		return merchant_x_type;
	}
	public void setMerchant_x_type(String merchant_x_type) {
		this.merchant_x_type = merchant_x_type;
	}
	public BigDecimal getMerchant_x_reg_amt() {
		return merchant_x_reg_amt;
	}
	public void setMerchant_x_reg_amt(BigDecimal merchant_x_reg_amt) {
		this.merchant_x_reg_amt = merchant_x_reg_amt;
	}
	public String getMerchant_x_code() {
		return merchant_x_code;
	}
	public void setMerchant_x_code(String merchant_x_code) {
		this.merchant_x_code = merchant_x_code;
	}
}
