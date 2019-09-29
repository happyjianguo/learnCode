package com.pay.merchant.bean;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.pay.util.StringUtils;

public class FullMerchantBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 商户基础信息表
	private String inst_id; // 商户所属机构机构号
	private String mrchno; // 商户号
	private String head_office; // 商户报表打印抬头
	private String name; // 商户名称
	private String trading_as; // 交易名称
	private String address1; // 通讯地址行1
	private String address2; // 通讯地址行2
	private String city; // 通讯地址所在城市
	private String state; // 通讯地址所在州
	private String postcode; // 邮政编码
	private String countrycode; // 国家代码
	private String phy_address1; // 实际地址行1
	private String phy_address2; // 实际地址行2
	private String phy_city; // 实际所在城市
	private String phy_state; // 实际所在州
	private String phy_postcode; // 实际地址邮编
	private String phy_countrycode; // 实际地址国家代码
	private String reg_address1; // 注册地址行1
	private String reg_address2; // 注册地址行2
	private String reg_city; // 注册地址所在城市
	private String reg_state; // 注册地址所在州（可以保存省份简写）
	private String reg_postcode; // 注册地址邮编
	private String reg_countrycode; // 注册地址国家代码
	private String currcode; // 商户货币类型的ISO三字节编码
	private String mrchtype; // 商户类型（已停用）
	private String acptbus; // ISO对应的MCC码
	private String contact1; // 邮寄地址联系人
	private String contact2; // 实际地址联系人
	private String contact3; // 注册地址联系人
	private String telno1; // 邮寄地址联系电话
	private String telno2; // 实际地址联系电话
	private String telno3; // 注册地址联系电话
	private String faxno; // 传真号码
	private String email; // 电子邮箱
	private String telex; // 电报
	private String sortcode; // 排序编号
	private String brncode; // 分支机构的编号（需要从BRANCH表中获取，对应的Branch.instcode，需要关联本表的inst_id）
	private String accno; // 商户银行账户号
	private String accnm; // 商户银行账户名
	private String taxcode; // 税收编号（需要从TAX表中获取tax.taxcode）
	private String stmtfreq; // 发送账单、状态信息的频率
	private String stmtto; // 是否将账单、状态信息发送给商户。以及发送的类型：0- 不发送 ;1- 汇总;2- 明细;3-
							// 汇总及明细
	private String stmtto_ho; // 是否包含抬头
	private String paymtype; // 支付方式
	private String paymto; // 收款方
	private String posflag; // 支付标识，默认值为 1
	private String vipflag; // 否为VIP
	private String msc_charge; // 是否计算商户手续费
	private String msc_calc; // 手续费计算方式
	private String msc_table; // 费率表
	private String msc; // 收费比例
	private String contrno; // 银行和商户间的联系号码
	private String contrdate; // 银行和商户间联系的起始日期
	private String contrcnx; // 银行和商户间联系的结束日期
	private String contrsign; // 银行和商户的合同签署方（需要从official表中选取official.OFFICIAL）
	private String official; // 署名方（需要从official表中选取official.OFFICIAL）
	private String mrchstat; // 商户的状态：
	private String rnc; // 商户的注册编号
	private String taxreg; // 商户的税务编号
	private String retenamt; //
	private String retenpc; //
	private String zonegeog; //
	private String zonecomer; //
	private String zonepostal; //
	private String usrdata1; //
	private String usrdata2; //
	private String usrdata3; //
	private String memo; //
	private String cycbegin; //
	private String cyclen; //
	private String no_imprntrs; //
	private String conaccno; //
	private String concur; //
	private String payoffset; //
	private String allowinst; //
	private String pppymntattr; //
	private String cat_params; //
	private String id; //
	
	// 商户账户信息表
	private String merchant_id; //
	// private String acc_currcode; // 与商户基础信息表保持一致
	private String date_last_stmt; //
	private String closing_bal; //
	private String current_bal; //
	private String last_post_bal; //
	private String last_post_com; //
	private String last_post_tax; //

	// 商户交易汇总信息表
	// private String currcode; //
	private String btchntmtd; //
	private String btchntpm; //
	private String btchcntytd; //
	private String btchdrmtd; //
	private String btchdrpm; //
	private String btchdrytd; //
	private String btchcrmtd;
	private String btchcrpm;
	private String btchcrytd;
	private String btchcommtd;
	private String btchcompm;
	private String btchcomytd;
	private String btchcntpm;
	private String btchcntmtd;

	// 商户补充信息表
	// private String mx_inst_id; //目前使用固定值(可在页面固定写)
	// private String mx_mrchno; //
	private String mrcht_name; //
	private String address; //
	private String add_date; // 格式为YYYYMMDDHHMMSS
	private String city_no;
	private String province;
	private String zone;
	private String mx_state; //
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
	private String mx_telno1;
	private String mx_postcode;
	private String mx_contact3;
	private String mx_accno;
	private String legal_rep;		//法定代表人（负责人）姓名
	private String lr_id_type;		//有效证件类型
	private String lr_id_no;		//号码
	private String lr_id_validity;	//有效期
	private String man_name;		//控股股东或实际控制人姓名
	private String id_type1;		//控股股东或实际控制人证件种类
	private String id_no1;		//控股股东或实际控制人证件号码
	private String id_deadline1;	//控股股东或实际控制人证件有效期截止日
	private String acc_x_name;	//营业执照名称
	private String settlement_person;	//结算员
	private String merchant_email;	//邮箱
	private String is_send_billdetail;	//是否发送对账明细
	
	private String merchant_x_operate;	//企业经营范围
	private String merchant_x_type;	//商户分类:11：自然人，12：单位，默认单位
	private String merchant_x_reg_amt;	//注册资本金--NUMBER(18,2)
//	private BigDecimal merchant_x_reg_amt;	//注册资本金--NUMBER(18,2)
	private String merchant_x_code;	//注册资本金币种:人民币-RMB，美元-USD，日元-JPY，欧元-EUR，英镑-GBP，德国马克-DEM，瑞士法郎-CHF，法国法郎-FRF，默认：人民币-RMB
	private String combine_flag;	//“节假日结算日期是否合并”，0不合并1合并，默认1合并
	
	private String type_yf_desc;

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
	
	private String mrchnoSelQ;

	// 账户信息补充表
	private String ma_id;
	// private String ma_inst_id; //目前使用固定值(可在页面固定写)
	// private String ma_mrchno;
	private String ma_accno;
	private String acc_name;
	private String bank_no;
	private String bank_name;
	private String acc_add_date; //
	private String acc_nick_name; // 商户帐号名称
	private String short_nick_name; // 商户帐号简称
	private String individual;
	private String last_settle_date;
	private String is_bj_acct;	//是否是北京开户行:0.否；1.是
	private String bis;			//结算银行 (bank for international settlements)
	
	//结算信息
	private String fs_cycle;//结算周期
	private String fs_cycle_param;//结算周期参数
	//private String last_settle_date;//上次结算日期
	private String is_consump_category;//是否按照不同消费场景结算:0.否；1.是
	private String amt_consump_category;//结算金额是否按照场景合并结算或分别结算（结算场景都要显示，只是结算金额是否要合并）:0.否；1.是
	private String is_fmrchno_decide;//是否按父商户结算:0.否；1.是
	
	private String Verno_ctx;

	private String classify;
	
	//普卡专属卡是否合并打款
	private String merge_money_flag;
	//商户摘要信息
	private String mrch_snippet;
	
	public FullMerchantBean() {

	}

	public FullMerchantBean(HashMap record) throws Exception {
		

		for (Object key : record.keySet()) {
			//System.out.println("--------key=[" + key + "]" + record.get(key));
			
			Class[] cargs = new Class[1];
			String realArgs = "";
			cargs[0] = realArgs.getClass();
			Method method =null;
			if (((String)key).equalsIgnoreCase("state_1")) {
				method = this.getClass().getMethod(
						"setMx_state", cargs);
			} else if (((String)key).equalsIgnoreCase("accno_1")){
				method = this.getClass().getMethod(
						"setMa_accno", cargs);
			} else {
				method = this.getClass().getMethod("set"+ StringUtils.toUpperCaseFirstOne(((String) key).toLowerCase()), cargs);
			}
			Object arglist[] = new Object[1];
			if (record.get(key) == null) {
				arglist[0] = "";
			} else {
				if (
					((String)key).equalsIgnoreCase("tax_id_validity")
					||((String)key).equalsIgnoreCase("bus_lic_validity")
					||((String)key).equalsIgnoreCase("id_validity")
					||((String)key).equalsIgnoreCase("lr_id_validity")
					||((String)key).equalsIgnoreCase("id_deadline1")
					||((String)key).equalsIgnoreCase("cycbegin")
					||((String)key).equalsIgnoreCase("acc_add_date")
					||((String)key).equalsIgnoreCase("contrdate")
					||((String)key).equalsIgnoreCase("last_settle_date")
					||((String)key).equalsIgnoreCase("last_kp_date")
					||((String)key).equalsIgnoreCase("date_last_stmt")
					||((String)key).equalsIgnoreCase("contrcnx")
					||((String)key).equalsIgnoreCase("org_validity")
					||((String)key).equalsIgnoreCase("disabled_date")
					||((String)key).equalsIgnoreCase("enable_date")) {
					SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy H:m:s");
					Date date = null;
					try {
						date = format.parse((String) record.get(key));
					} catch (ParseException e) {
						e.printStackTrace();
					}
					SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					
					arglist[0] = StringUtils.innerToOuter(format1.format(date))
							.trim();
				} else {
					arglist[0] = StringUtils.innerToOuter((String) record.get(key))
						.trim();
				}
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

	public String getLast_kp_date() {
		return last_kp_date;
	}

	public void setLast_kp_date(String lastKpDate) {
		last_kp_date = lastKpDate;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getMrchnoSelQ() {
		return mrchnoSelQ;
	}

	public void setMrchnoSelQ(String mrchnoSelQ) {
		this.mrchnoSelQ = mrchnoSelQ;
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
	public String getAcc_nick_name() {
		return acc_nick_name;
	}
	
	public String getVerno_ctx() {
		return Verno_ctx;
	}

	public void setVerno_ctx(String verno_ctx) {
		Verno_ctx = verno_ctx;
	}

	public String getInst_id() {
		return inst_id;
	}

	public String getMrchno() {
		return mrchno;
	}

	public String getHead_office() {
		return head_office;
	}

	public String getName() {
		return name;
	}

	public String getTrading_as() {
		return trading_as;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public String getPhy_address1() {
		return phy_address1;
	}

	public String getPhy_address2() {
		return phy_address2;
	}

	public String getPhy_city() {
		return phy_city;
	}

	public String getPhy_state() {
		return phy_state;
	}

	public String getPhy_postcode() {
		return phy_postcode;
	}

	public String getPhy_countrycode() {
		return phy_countrycode;
	}

	public String getReg_address1() {
		return reg_address1;
	}

	public String getReg_address2() {
		return reg_address2;
	}

	public String getReg_city() {
		return reg_city;
	}

	public String getReg_state() {
		return reg_state;
	}

	public String getReg_postcode() {
		return reg_postcode;
	}

	public String getReg_countrycode() {
		return reg_countrycode;
	}

	public String getCurrcode() {
		return currcode;
	}

	public String getMrchtype() {
		return mrchtype;
	}

	public String getAcptbus() {
		return acptbus;
	}

	public String getContact1() {
		return contact1;
	}

	public String getContact2() {
		return contact2;
	}

	public String getContact3() {
		return contact3;
	}

	public String getTelno1() {
		return telno1;
	}

	public String getTelno2() {
		return telno2;
	}

	public String getTelno3() {
		return telno3;
	}

	public String getFaxno() {
		return faxno;
	}

	public String getEmail() {
		return email;
	}

	public String getTelex() {
		return telex;
	}

	public String getSortcode() {
		return sortcode;
	}

	public String getBrncode() {
		return brncode;
	}

	public String getAccno() {
		return accno;
	}

	public String getAccnm() {
		return accnm;
	}

	public String getTaxcode() {
		return taxcode;
	}

	public String getStmtfreq() {
		return stmtfreq;
	}

	public String getStmtto() {
		return stmtto;
	}

	public String getStmtto_ho() {
		return stmtto_ho;
	}

	public String getPaymtype() {
		return paymtype;
	}

	public String getPaymto() {
		return paymto;
	}

	public String getPosflag() {
		return posflag;
	}

	public String getVipflag() {
		return vipflag;
	}

	public String getMsc_charge() {
		return msc_charge;
	}

	public String getMsc_calc() {
		return msc_calc;
	}

	public String getMsc_table() {
		return msc_table;
	}

	public String getMsc() {
		return msc;
	}

	public String getContrno() {
		return contrno;
	}

	public String getContrdate() {
		return contrdate;
	}

	public String getContrcnx() {
		return contrcnx;
	}

	public String getContrsign() {
		return contrsign;
	}

	public String getOfficial() {
		return official;
	}

	public String getMrchstat() {
		return mrchstat;
	}

	public String getRnc() {
		return rnc;
	}

	public String getTaxreg() {
		return taxreg;
	}

	public String getRetenamt() {
		return retenamt;
	}

	public String getRetenpc() {
		return retenpc;
	}

	public String getZonegeog() {
		return zonegeog;
	}

	public String getZonecomer() {
		return zonecomer;
	}

	public String getZonepostal() {
		return zonepostal;
	}

	public String getUsrdata1() {
		return usrdata1;
	}

	public String getUsrdata2() {
		return usrdata2;
	}

	public String getUsrdata3() {
		return usrdata3;
	}

	public String getMemo() {
		return memo;
	}

	public String getCycbegin() {
		return cycbegin;
	}

	public String getCyclen() {
		return cyclen;
	}

	public String getNo_imprntrs() {
		return no_imprntrs;
	}

	public String getConaccno() {
		return conaccno;
	}

	public String getConcur() {
		return concur;
	}

	public String getPayoffset() {
		return payoffset;
	}

	public String getAllowinst() {
		return allowinst;
	}

	public String getPppymntattr() {
		return pppymntattr;
	}

	public String getCat_params() {
		return cat_params;
	}

	public String getId() {
		return id;
	}

	public String getMerchant_id() {
		return merchant_id;
	}

	public String getDate_last_stmt() {
		return date_last_stmt;
	}

	public String getClosing_bal() {
		return closing_bal;
	}

	public String getCurrent_bal() {
		return current_bal;
	}

	public String getLast_post_bal() {
		return last_post_bal;
	}

	public String getLast_post_com() {
		return last_post_com;
	}

	public String getLast_post_tax() {
		return last_post_tax;
	}

	public String getBtchntmtd() {
		return btchntmtd;
	}

	public String getBtchntpm() {
		return btchntpm;
	}

	public String getBtchcntytd() {
		return btchcntytd;
	}

	public String getBtchdrmtd() {
		return btchdrmtd;
	}

	public String getBtchdrpm() {
		return btchdrpm;
	}

	public String getBtchdrytd() {
		return btchdrytd;
	}

	public String getBtchcrmtd() {
		return btchcrmtd;
	}

	public String getBtchcrpm() {
		return btchcrpm;
	}

	public String getBtchcrytd() {
		return btchcrytd;
	}

	public String getBtchcommtd() {
		return btchcommtd;
	}

	public String getBtchcompm() {
		return btchcompm;
	}

	public String getBtchcomytd() {
		return btchcomytd;
	}

	public String getMrcht_name() {
		return mrcht_name;
	}

	public String getAddress() {
		return address;
	}

	public String getAdd_date() {
		return add_date;
	}

	public String getCity_no() {
		return city_no;
	}

	public String getProvince() {
		return province;
	}

	public String getZone() {
		return zone;
	}

	public String getMx_state() {
		return mx_state;
	}

	public String getType_yf() {
		return type_yf;
	}

	public String getAgent() {
		return agent;
	}

	public String getId_type() {
		return id_type;
	}

	public String getId_no() {
		return id_no;
	}

	public String getId_validity() {
		return id_validity;
	}

	public String getBus_lic_no() {
		return bus_lic_no;
	}

	public String getBus_lic_validity() {
		return bus_lic_validity;
	}

	public String getTax_id() {
		return tax_id;
	}

	public String getTax_id_validity() {
		return tax_id_validity;
	}

	public String getOrg_id() {
		return org_id;
	}

	public String getOrg_validity() {
		return org_validity;
	}

	public String getBus_lic_pic() {
		return bus_lic_pic;
	}

	public String getTax_id_pic() {
		return tax_id_pic;
	}

	public String getOrg_id_pic() {
		return org_id_pic;
	}

	public String getMx_telno1() {
		return mx_telno1;
	}

	public String getMx_postcode() {
		return mx_postcode;
	}

	public String getMx_contact3() {
		return mx_contact3;
	}

	public String getMx_accno() {
		return mx_accno;
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
	
	
	
	
	public String getMa_id() {
		return ma_id;
	}

	/**
	 * public String getMa_inst_id() { return ma_inst_id; }
	 * 
	 * public String getMa_mrchno() { return ma_mrchno; }
	 */
	public String getMa_accno() {
		return ma_accno;
	}

	public String getAcc_name() {
		return acc_name;
	}

	public String getBank_no() {
		return bank_no;
	}

	public String getBank_name() {
		return bank_name;
	}

	public String getAcc_add_date() {
		return acc_add_date;
	}

	public String getShort_nick_name() {
		return short_nick_name;
	}

	public String getIndividual() {
		return individual;
	}

	public String getLast_settle_date() {
		return last_settle_date;
	}

	public void setInst_id(String inst_id) {
		this.inst_id = inst_id;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}

	public void setHead_office(String head_office) {
		this.head_office = head_office;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTrading_as(String trading_as) {
		this.trading_as = trading_as;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public void setPhy_address1(String phy_address1) {
		this.phy_address1 = phy_address1;
	}

	public void setPhy_address2(String phy_address2) {
		this.phy_address2 = phy_address2;
	}

	public void setPhy_city(String phy_city) {
		this.phy_city = phy_city;
	}

	public void setPhy_state(String phy_state) {
		this.phy_state = phy_state;
	}

	public void setPhy_postcode(String phy_postcode) {
		this.phy_postcode = phy_postcode;
	}

	public void setPhy_countrycode(String phy_countrycode) {
		this.phy_countrycode = phy_countrycode;
	}

	public void setReg_address1(String reg_address1) {
		this.reg_address1 = reg_address1;
	}

	public void setReg_address2(String reg_address2) {
		this.reg_address2 = reg_address2;
	}

	public void setReg_city(String reg_city) {
		this.reg_city = reg_city;
	}

	public void setReg_state(String reg_state) {
		this.reg_state = reg_state;
	}

	public void setReg_postcode(String reg_postcode) {
		this.reg_postcode = reg_postcode;
	}

	public void setReg_countrycode(String reg_countrycode) {
		this.reg_countrycode = reg_countrycode;
	}

	public void setCurrcode(String currcode) {
		this.currcode = currcode;
	}

	public void setMrchtype(String mrchtype) {
		this.mrchtype = mrchtype;
	}

	public void setAcptbus(String acptbus) {
		this.acptbus = acptbus;
	}

	public void setContact1(String contact1) {
		this.contact1 = contact1;
	}

	public void setContact2(String contact2) {
		this.contact2 = contact2;
	}

	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}

	public void setTelno1(String telno1) {
		this.telno1 = telno1;
	}

	public void setTelno2(String telno2) {
		this.telno2 = telno2;
	}

	public void setTelno3(String telno3) {
		this.telno3 = telno3;
	}

	public void setFaxno(String faxno) {
		this.faxno = faxno;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelex(String telex) {
		this.telex = telex;
	}

	public void setSortcode(String sortcode) {
		this.sortcode = sortcode;
	}

	public void setBrncode(String brncode) {
		this.brncode = brncode;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public void setAccnm(String accnm) {
		this.accnm = accnm;
	}

	public void setTaxcode(String taxcode) {
		this.taxcode = taxcode;
	}

	public void setStmtfreq(String stmtfreq) {
		this.stmtfreq = stmtfreq;
	}

	public void setStmtto(String stmtto) {
		this.stmtto = stmtto;
	}

	public void setStmtto_ho(String stmtto_ho) {
		this.stmtto_ho = stmtto_ho;
	}

	public void setPaymtype(String paymtype) {
		this.paymtype = paymtype;
	}

	public void setPaymto(String paymto) {
		this.paymto = paymto;
	}

	public void setPosflag(String posflag) {
		this.posflag = posflag;
	}

	public void setVipflag(String vipflag) {
		this.vipflag = vipflag;
	}

	public void setMsc_charge(String msc_charge) {
		this.msc_charge = msc_charge;
	}

	public void setMsc_calc(String msc_calc) {
		this.msc_calc = msc_calc;
	}

	public void setMsc_table(String msc_table) {
		this.msc_table = msc_table;
	}

	public void setMsc(String msc) {
		this.msc = msc;
	}

	public void setContrno(String contrno) {
		this.contrno = contrno;
	}

	public void setContrdate(String contrdate) {
		this.contrdate = contrdate;
	}

	public void setContrcnx(String contrcnx) {
		this.contrcnx = contrcnx;
	}

	public void setContrsign(String contrsign) {
		this.contrsign = contrsign;
	}

	public void setOfficial(String official) {
		this.official = official;
	}

	public void setMrchstat(String mrchstat) {
		this.mrchstat = mrchstat;
	}

	public void setRnc(String rnc) {
		this.rnc = rnc;
	}

	public void setTaxreg(String taxreg) {
		this.taxreg = taxreg;
	}

	public void setRetenamt(String retenamt) {
		this.retenamt = retenamt;
	}

	public void setRetenpc(String retenpc) {
		this.retenpc = retenpc;
	}

	public void setZonegeog(String zonegeog) {
		this.zonegeog = zonegeog;
	}

	public void setZonecomer(String zonecomer) {
		this.zonecomer = zonecomer;
	}

	public void setZonepostal(String zonepostal) {
		this.zonepostal = zonepostal;
	}

	public void setUsrdata1(String usrdata1) {
		this.usrdata1 = usrdata1;
	}

	public void setUsrdata2(String usrdata2) {
		this.usrdata2 = usrdata2;
	}

	public void setUsrdata3(String usrdata3) {
		this.usrdata3 = usrdata3;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setCycbegin(String cycbegin) {
		this.cycbegin = cycbegin;
	}

	public void setCyclen(String cyclen) {
		this.cyclen = cyclen;
	}

	public void setNo_imprntrs(String no_imprntrs) {
		this.no_imprntrs = no_imprntrs;
	}

	public void setConaccno(String conaccno) {
		this.conaccno = conaccno;
	}

	public void setConcur(String concur) {
		this.concur = concur;
	}

	public void setPayoffset(String payoffset) {
		this.payoffset = payoffset;
	}

	public void setAllowinst(String allowinst) {
		this.allowinst = allowinst;
	}

	public void setPppymntattr(String pppymntattr) {
		this.pppymntattr = pppymntattr;
	}

	public void setCat_params(String cat_params) {
		this.cat_params = cat_params;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMerchant_id(String merchant_id) {
		this.merchant_id = merchant_id;
	}

	public void setDate_last_stmt(String date_last_stmt) {
		this.date_last_stmt = date_last_stmt;
	}

	public void setClosing_bal(String closing_bal) {
		this.closing_bal = closing_bal;
	}

	public void setCurrent_bal(String current_bal) {
		this.current_bal = current_bal;
	}

	public void setLast_post_bal(String last_post_bal) {
		this.last_post_bal = last_post_bal;
	}

	public void setLast_post_com(String last_post_com) {
		this.last_post_com = last_post_com;
	}

	public void setLast_post_tax(String last_post_tax) {
		this.last_post_tax = last_post_tax;
	}

	public void setBtchntmtd(String btchntmtd) {
		this.btchntmtd = btchntmtd;
	}

	public void setBtchntpm(String btchntpm) {
		this.btchntpm = btchntpm;
	}

	public void setBtchcntytd(String btchcntytd) {
		this.btchcntytd = btchcntytd;
	}

	public void setBtchdrmtd(String btchdrmtd) {
		this.btchdrmtd = btchdrmtd;
	}

	public void setBtchdrpm(String btchdrpm) {
		this.btchdrpm = btchdrpm;
	}

	public void setBtchdrytd(String btchdrytd) {
		this.btchdrytd = btchdrytd;
	}

	public void setBtchcrmtd(String btchcrmtd) {
		this.btchcrmtd = btchcrmtd;
	}

	public void setBtchcrpm(String btchcrpm) {
		this.btchcrpm = btchcrpm;
	}

	public void setBtchcrytd(String btchcrytd) {
		this.btchcrytd = btchcrytd;
	}

	public void setBtchcommtd(String btchcommtd) {
		this.btchcommtd = btchcommtd;
	}

	public void setBtchcompm(String btchcompm) {
		this.btchcompm = btchcompm;
	}

	public void setBtchcomytd(String btchcomytd) {
		this.btchcomytd = btchcomytd;
	}

	public String getBtchcntpm() {
		return btchcntpm;
	}

	public void setBtchcntpm(String btchcntpm) {
		this.btchcntpm = btchcntpm;
	}

	public String getBtchcntmtd() {
		return btchcntmtd;
	}

	public void setBtchcntmtd(String btchcntmtd) {
		this.btchcntmtd = btchcntmtd;
	}

	/**
	 * public void setMx_inst_id(String mx_inst_id) { this.mx_inst_id =
	 * mx_inst_id; }
	 * 
	 * public void setMx_mrchno(String mx_mrchno) { this.mx_mrchno = mx_mrchno;
	 * }
	 */
	public void setMrcht_name(String mrcht_name) {
		this.mrcht_name = mrcht_name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAdd_date(String add_date) {
		this.add_date = add_date;
	}

	public void setCity_no(String city_no) {
		this.city_no = city_no;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public void setMx_state(String mx_state) {
		this.mx_state = mx_state;
	}

	public void setType_yf(String type_yf) {
		this.type_yf = type_yf;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public void setId_type(String id_type) {
		this.id_type = id_type;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public void setId_validity(String id_validity) {
		this.id_validity = id_validity;
	}

	public void setBus_lic_no(String bus_lic_no) {
		this.bus_lic_no = bus_lic_no;
	}

	public void setBus_lic_validity(String bus_lic_validity) {
		this.bus_lic_validity = bus_lic_validity;
	}

	public void setTax_id(String tax_id) {
		this.tax_id = tax_id;
	}

	public void setTax_id_validity(String tax_id_validity) {
		this.tax_id_validity = tax_id_validity;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public void setOrg_validity(String org_validity) {
		this.org_validity = org_validity;
	}

	public void setBus_lic_pic(String bus_lic_pic) {
		this.bus_lic_pic = bus_lic_pic;
	}

	public void setTax_id_pic(String tax_id_pic) {
		this.tax_id_pic = tax_id_pic;
	}

	public void setOrg_id_pic(String org_id_pic) {
		this.org_id_pic = org_id_pic;
	}

	public void setMx_telno1(String mx_telno1) {
		this.mx_telno1 = mx_telno1;
	}

	public void setMx_postcode(String mx_postcode) {
		this.mx_postcode = mx_postcode;
	}

	public void setMx_contact3(String mx_contact3) {
		this.mx_contact3 = mx_contact3;
	}

	public void setMx_accno(String mx_accno) {
		this.mx_accno = mx_accno;
	}

	public void setMa_id(String ma_id) {
		this.ma_id = ma_id;
	}

	/**
	 * public void setMa_inst_id(String ma_inst_id) { this.ma_inst_id =
	 * ma_inst_id; }
	 * 
	 * public void setMa_mrchno(String ma_mrchno) { this.ma_mrchno = ma_mrchno;
	 * }
	 */
	public void setMa_accno(String ma_accno) {
		this.ma_accno = ma_accno;
	}

	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}

	public void setBank_no(String bank_no) {
		this.bank_no = bank_no;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public void setAcc_add_date(String acc_add_date) {
		this.acc_add_date = acc_add_date;
	}

	public void setAcc_nick_name(String acc_nick_name) {
		this.acc_nick_name = acc_nick_name;
	}

	public void setShort_nick_name(String short_nick_name) {
		this.short_nick_name = short_nick_name;
	}

	public void setIndividual(String individual) {
		this.individual = individual;
	}

	public void setLast_settle_date(String last_settle_date) {
		this.last_settle_date = last_settle_date;
	}

	//结算周期
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

	public String getIs_bj_acct() {
		return is_bj_acct;
	}

	public void setIs_bj_acct(String isBjAcct) {
		is_bj_acct = isBjAcct;
	}

	public String getBis() {
		return bis;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType_yf_desc() {
		return type_yf_desc;
	}

	public void setType_yf_desc(String typeYfDesc) {
		type_yf_desc = typeYfDesc;
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

	public String getMerge_money_flag() {
		return merge_money_flag;
	}

	public void setMerge_money_flag(String merge_money_flag) {
		this.merge_money_flag = merge_money_flag;
	}

	public String getMrch_snippet() {
		return mrch_snippet;
	}

	public void setMrch_snippet(String mrch_snippet) {
		this.mrch_snippet = mrch_snippet;
	}

	public String getMerchant_org() {
		return merchant_org;
	}

	public void setMerchant_org(String merchant_org) {
		this.merchant_org = (merchant_org == null || merchant_org.trim().length()==0)?"":merchant_org.trim();
	}
	public String getMerchant_fenrunorg() {
		return merchant_fenrunorg;
	}
	public void setMerchant_fenrunorg(String merchant_fenrunorg) {
		this.merchant_fenrunorg = (merchant_fenrunorg == null || merchant_fenrunorg.trim().length()==0)?"":merchant_fenrunorg.trim();
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

	public String getMerchant_x_reg_amt() {
		return merchant_x_reg_amt;
	}

	public void setMerchant_x_reg_amt(String merchant_x_reg_amt) {
		this.merchant_x_reg_amt = merchant_x_reg_amt;
	}

	public String getMerchant_x_code() {
		return merchant_x_code;
	}

	public void setMerchant_x_code(String merchant_x_code) {
		this.merchant_x_code = merchant_x_code;
	}
}
