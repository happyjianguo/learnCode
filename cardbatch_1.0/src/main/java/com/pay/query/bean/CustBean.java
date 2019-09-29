package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class CustBean implements Serializable{
	private String inst_id;// 机构ID
	private String custcode;// 顾客编号
	private String title;// 称谓
	private String firstname;// 名称
	private String lastname;// 姓氏
	private String typeid;// 顾客类型
	private String addrl0;// 地址行0
	private String addrl1;// 地址行1
	private String addrl2;// 地址行2
	private String addrl3;// 地址行3
	private String home_city;// 所在城市
	private String home_tel;// 联系电话
	private String work_addr1;// 工作地址行1
	private String work_addr2;// 工作地址行2
	private String work_addr3;// 工作地址行3
	private String work_city;// 工作城市
	private String work_tel;// 工作电话
	private String stmt_code;// 邮件寄送方式
	private String work_postcode;// 工作地址邮编
	private String postcode;// 家庭地址邮编
	private String po_box;// 邮箱
	private String collection_zone;//
	private String prof_code;//
	private String married;// 婚姻状况
	private String sex;// 性别
	private String id_number;// 身份证号
	private String date_accepted;// 数据建立的时间
	private String refuse_cheque;//
	private String num_bounced1;//
	private String num_bounced2;//
	private String memo;// 备忘信息
	private String usrdata1;//
	private String usrdata2;//
	private String usrdata3;//
	private String mailshot;//
	private String date_birth;// 出生日期
	private String prflang;//
	private String addrind;//
	private String email;// 电子邮箱
	private String fax;// 传真
	private String usrdata4;//
	private String cat_params;//
	private String id;// 唯一标识
	private String latin_title;//
	private String latin_firstname;//
	private String latin_lastname;//
	private String uc_firstname;//
	private String uc_lastname;//
	private String national_id;//
	private String work_county;//
	private String work_ctry;//
	private String home_county;//
	private String home_ctry;//
	private String mob_tel;// 移动电话
	public CustBean(){
		
	}
	public CustBean(HashMap record){
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, CustBean.class, this);
		recordMethod.recordset();
	
	}
	public String getInst_id() {
		return inst_id;
	}
	public void setInst_id(String instId) {
		inst_id = instId;
	}
	public String getCustcode() {
		return custcode;
	}
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getAddrl0() {
		return addrl0;
	}
	public void setAddrl0(String addrl0) {
		this.addrl0 = addrl0;
	}
	public String getAddrl1() {
		return addrl1;
	}
	public void setAddrl1(String addrl1) {
		this.addrl1 = addrl1;
	}
	public String getAddrl2() {
		return addrl2;
	}
	public void setAddrl2(String addrl2) {
		this.addrl2 = addrl2;
	}
	public String getAddrl3() {
		return addrl3;
	}
	public void setAddrl3(String addrl3) {
		this.addrl3 = addrl3;
	}
	public String getHome_city() {
		return home_city;
	}
	public void setHome_city(String homeCity) {
		home_city = homeCity;
	}
	public String getHome_tel() {
		return home_tel;
	}
	public void setHome_tel(String homeTel) {
		home_tel = homeTel;
	}
	public String getWork_addr1() {
		return work_addr1;
	}
	public void setWork_addr1(String workAddr1) {
		work_addr1 = workAddr1;
	}
	public String getWork_addr2() {
		return work_addr2;
	}
	public void setWork_addr2(String workAddr2) {
		work_addr2 = workAddr2;
	}
	public String getWork_addr3() {
		return work_addr3;
	}
	public void setWork_addr3(String workAddr3) {
		work_addr3 = workAddr3;
	}
	public String getWork_city() {
		return work_city;
	}
	public void setWork_city(String workCity) {
		work_city = workCity;
	}
	public String getWork_tel() {
		return work_tel;
	}
	public void setWork_tel(String workTel) {
		work_tel = workTel;
	}
	public String getStmt_code() {
		return stmt_code;
	}
	public void setStmt_code(String stmtCode) {
		stmt_code = stmtCode;
	}
	public String getWork_postcode() {
		return work_postcode;
	}
	public void setWork_postcode(String workPostcode) {
		work_postcode = workPostcode;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getPo_box() {
		return po_box;
	}
	public void setPo_box(String poBox) {
		po_box = poBox;
	}
	public String getCollection_zone() {
		return collection_zone;
	}
	public void setCollection_zone(String collectionZone) {
		collection_zone = collectionZone;
	}
	public String getProf_code() {
		return prof_code;
	}
	public void setProf_code(String profCode) {
		prof_code = profCode;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String idNumber) {
		id_number = idNumber;
	}
	public String getDate_accepted() {
		return date_accepted;
	}
	public void setDate_accepted(String dateAccepted) {
		date_accepted = dateAccepted;
	}
	public String getRefuse_cheque() {
		return refuse_cheque;
	}
	public void setRefuse_cheque(String refuseCheque) {
		refuse_cheque = refuseCheque;
	}
	public String getNum_bounced1() {
		return num_bounced1;
	}
	public void setNum_bounced1(String numBounced1) {
		num_bounced1 = numBounced1;
	}
	public String getNum_bounced2() {
		return num_bounced2;
	}
	public void setNum_bounced2(String numBounced2) {
		num_bounced2 = numBounced2;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUsrdata1() {
		return usrdata1;
	}
	public void setUsrdata1(String usrdata1) {
		this.usrdata1 = usrdata1;
	}
	public String getUsrdata2() {
		return usrdata2;
	}
	public void setUsrdata2(String usrdata2) {
		this.usrdata2 = usrdata2;
	}
	public String getUsrdata3() {
		return usrdata3;
	}
	public void setUsrdata3(String usrdata3) {
		this.usrdata3 = usrdata3;
	}
	public String getMailshot() {
		return mailshot;
	}
	public void setMailshot(String mailshot) {
		this.mailshot = mailshot;
	}
	public String getDate_birth() {
		return date_birth;
	}
	public void setDate_birth(String dateBirth) {
		date_birth = dateBirth;
	}
	public String getPrflang() {
		return prflang;
	}
	public void setPrflang(String prflang) {
		this.prflang = prflang;
	}
	public String getAddrind() {
		return addrind;
	}
	public void setAddrind(String addrind) {
		this.addrind = addrind;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getUsrdata4() {
		return usrdata4;
	}
	public void setUsrdata4(String usrdata4) {
		this.usrdata4 = usrdata4;
	}
	public String getCat_params() {
		return cat_params;
	}
	public void setCat_params(String catParams) {
		cat_params = catParams;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLatin_title() {
		return latin_title;
	}
	public void setLatin_title(String latinTitle) {
		latin_title = latinTitle;
	}
	public String getLatin_firstname() {
		return latin_firstname;
	}
	public void setLatin_firstname(String latinFirstname) {
		latin_firstname = latinFirstname;
	}
	public String getLatin_lastname() {
		return latin_lastname;
	}
	public void setLatin_lastname(String latinLastname) {
		latin_lastname = latinLastname;
	}
	public String getUc_firstname() {
		return uc_firstname;
	}
	public void setUc_firstname(String ucFirstname) {
		uc_firstname = ucFirstname;
	}
	public String getUc_lastname() {
		return uc_lastname;
	}
	public void setUc_lastname(String ucLastname) {
		uc_lastname = ucLastname;
	}
	public String getNational_id() {
		return national_id;
	}
	public void setNational_id(String nationalId) {
		national_id = nationalId;
	}
	public String getWork_county() {
		return work_county;
	}
	public void setWork_county(String workCounty) {
		work_county = workCounty;
	}
	public String getWork_ctry() {
		return work_ctry;
	}
	public void setWork_ctry(String workCtry) {
		work_ctry = workCtry;
	}
	public String getHome_county() {
		return home_county;
	}
	public void setHome_county(String homeCounty) {
		home_county = homeCounty;
	}
	public String getHome_ctry() {
		return home_ctry;
	}
	public void setHome_ctry(String homeCtry) {
		home_ctry = homeCtry;
	}
	public String getMob_tel() {
		return mob_tel;
	}
	public void setMob_tel(String mobTel) {
		mob_tel = mobTel;
	}

}
