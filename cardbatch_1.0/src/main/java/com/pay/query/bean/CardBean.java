package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class CardBean implements Serializable {
	private String branch_id;// 机构代码
	private String crdproduct_id;// 卡产品
	private String classid;// 卡分类:1: 借记卡2: 贷记卡2: 记账卡
	private String pan;// 卡号
	private String pan_start;   // 开始卡号
	private String pan_end;		// 结束卡号
	private String seqno;// 卡片序列号
	private String expdate;// 卡片有效期
	private String effdate;// 卡片激活日期
	private String cyclen;// 周期长度（天）
	private String cycbegin;// 周期开始时间
	private String amtauth;// 每个周期的授权金额
	private String amtrem;// 本周期剩余金额
	private String blkamt;// 锁定的金额（授权而未清算）
	private String statcode;// 卡片状态码
	private String batch;// 制卡批次
	private String cvv;// CVV值
	private String lang;// 语言代码
	private String title;// 持卡人称谓
	private String firstname;// 持卡人名称
	private String lastname;// 持卡人姓氏
	private String renew;// 是否执行翻新:0.不执行1.执行
	private String corp;// 公司卡标记
	private String date_birth;// 持卡人生日
	private String embossname;// 卡面凸印上的姓名
	private String additionalno;// 附属卡的数量
	private String date_created;// 制卡日期;//
	private String id;// 卡片的唯一性标识符（数值从序列中获取）
	private String pan_display;// 印到卡面上的卡号
	private String accdet_id;// 默认账户ID
	private String custdet_id;// 客户ID
	private String avlbal;
	private String typeid;
	private String cust_name;
	private String id_num;
	private String mail_box;
	private String cell_phone;
	

	private String crdproduct_name;
	private String statcode_name;

	private String effdateStart;
	private String effdateEnd;
	
	public String getEffdateStart() {
		return effdateStart;
	}
	public void setEffdateStart(String effdateStart) {
		this.effdateStart = effdateStart;
	}
	public String getEffdateEnd() {
		return effdateEnd;
	}
	public void setEffdateEnd(String effdateEnd) {
		this.effdateEnd = effdateEnd;
	}
	public String getAvlbal() {
		return avlbal;
	}
	public void setAvlbal(String avlbal) {
		this.avlbal = avlbal;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String custName) {
		cust_name = custName;
	}
	public String getId_num() {
		return id_num;
	}
	public void setId_num(String idNum) {
		id_num = idNum;
	}
	public String getMail_box() {
		return mail_box;
	}
	public void setMail_box(String mailBox) {
		mail_box = mailBox;
	}
	public String getCell_phone() {
		return cell_phone;
	}
	public void setCell_phone(String cellPhone) {
		cell_phone = cellPhone;
	}
	public String getCrdproduct_name() {
		return crdproduct_name;
	}
	public void setCrdproduct_name(String crdproductName) {
		crdproduct_name = crdproductName;
	}
	public String getStatcode_name() {
		return statcode_name;
	}
	public void setStatcode_name(String statcodeName) {
		statcode_name = statcodeName;
	}
	public CardBean() {

	}
	public CardBean(HashMap record) {
		// 利用java反射机制为javabean赋值
		RecordMethod recordMethod = new RecordMethod(record, CardBean.class, this);
		recordMethod.recordset();
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branchId) {
		branch_id = branchId;
	}

	public String getCrdproduct_id() {
		return crdproduct_id;
	}

	public void setCrdproduct_id(String crdproductId) {
		crdproduct_id = crdproductId;
	}

	public String getClassid() {
		return classid;
	}

	public void setClassid(String classid) {
		this.classid = classid;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
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
	
	public String getSeqno() {
		return seqno;
	}

	public void setSeqno(String seqno) {
		this.seqno = seqno;
	}

	public String getExpdate() {
		return expdate;
	}

	public void setExpdate(String expdate) {
		this.expdate = expdate;
	}

	public String getEffdate() {
		return effdate;
	}

	public void setEffdate(String effdate) {
		this.effdate = effdate;
	}

	public String getCyclen() {
		return cyclen;
	}

	public void setCyclen(String cyclen) {
		this.cyclen = cyclen;
	}

	public String getCycbegin() {
		return cycbegin;
	}

	public void setCycbegin(String cycbegin) {
		this.cycbegin = cycbegin;
	}

	public String getAmtauth() {
		return amtauth;
	}

	public void setAmtauth(String amtauth) {
		this.amtauth = amtauth;
	}

	public String getAmtrem() {
		return amtrem;
	}

	public void setAmtrem(String amtrem) {
		this.amtrem = amtrem;
	}

	public String getBlkamt() {
		return blkamt;
	}

	public void setBlkamt(String blkamt) {
		this.blkamt = blkamt;
	}

	public String getStatcode() {
		return statcode;
	}

	public void setStatcode(String statcode) {
		this.statcode = statcode;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
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

	public String getRenew() {
		return renew;
	}

	public void setRenew(String renew) {
		this.renew = renew;
	}

	public String getCorp() {
		return corp;
	}

	public void setCorp(String corp) {
		this.corp = corp;
	}

	public String getDate_birth() {
		return date_birth;
	}

	public void setDate_birth(String dateBirth) {
		date_birth = dateBirth;
	}

	public String getEmbossname() {
		return embossname;
	}

	public void setEmbossname(String embossname) {
		this.embossname = embossname;
	}

	public String getAdditionalno() {
		return additionalno;
	}

	public void setAdditionalno(String additionalno) {
		this.additionalno = additionalno;
	}

	public String getDate_created() {
		return date_created;
	}

	public void setDate_created(String dateCreated) {
		date_created = dateCreated;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPan_display() {
		return pan_display;
	}

	public void setPan_display(String panDisplay) {
		pan_display = panDisplay;
	}

	public String getAccdet_id() {
		return accdet_id;
	}

	public void setAccdet_id(String accdetId) {
		accdet_id = accdetId;
	}

	public String getCustdet_id() {
		return custdet_id;
	}

	public void setCustdet_id(String custdetId) {
		custdet_id = custdetId;
	}

}
