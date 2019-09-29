package com.pay.query.bean;

import java.io.Serializable;
import java.util.HashMap;

import com.pay.util.RecordMethod;

public class CardBean implements Serializable {
	private String branch_id;// ��������
	private String crdproduct_id;// ����Ʒ
	private String classid;// ������:1: ��ǿ�2: ���ǿ�2: ���˿�
	private String pan;// ����
	private String pan_start;   // ��ʼ����
	private String pan_end;		// ��������
	private String seqno;// ��Ƭ���к�
	private String expdate;// ��Ƭ��Ч��
	private String effdate;// ��Ƭ��������
	private String cyclen;// ���ڳ��ȣ��죩
	private String cycbegin;// ���ڿ�ʼʱ��
	private String amtauth;// ÿ�����ڵ���Ȩ���
	private String amtrem;// ������ʣ����
	private String blkamt;// �����Ľ���Ȩ��δ���㣩
	private String statcode;// ��Ƭ״̬��
	private String batch;// �ƿ�����
	private String cvv;// CVVֵ
	private String lang;// ���Դ���
	private String title;// �ֿ��˳�ν
	private String firstname;// �ֿ�������
	private String lastname;// �ֿ�������
	private String renew;// �Ƿ�ִ�з���:0.��ִ��1.ִ��
	private String corp;// ��˾�����
	private String date_birth;// �ֿ�������
	private String embossname;// ����͹ӡ�ϵ�����
	private String additionalno;// ������������
	private String date_created;// �ƿ�����;//
	private String id;// ��Ƭ��Ψһ�Ա�ʶ������ֵ�������л�ȡ��
	private String pan_display;// ӡ�������ϵĿ���
	private String accdet_id;// Ĭ���˻�ID
	private String custdet_id;// �ͻ�ID
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
		// ����java�������Ϊjavabean��ֵ
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
