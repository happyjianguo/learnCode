package com.pay.merchant.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class MerchantExportBean {
	private String mrchno; // �̻���
	private String name; // �̻�����
	private String mrch_snippet; // �̻�ժҪ
	private String type_yf; //�̻�����
	private String mrchstat; // �̻���״̬��

	private String acptbus;//IOS��ӦMCC��
	private String contact3; // ע���ַ��ϵ��
	private String telno1; // �ʼĵ�ַ��ϵ�绰
	private String add_date; // ��ʽΪYYYYMMDDHHMMSS
	private String disabled_date;//ͣ��ʱ��
	private String enable_date;	//����ʱ��
	//"�̻���","�̻�����","�̻�ժҪ","�̻�����","�̻�״̬","IOS��ӦMCC��","��ϵ��","�绰","����ʱ��","ͣ��ʱ��","����ʱ��"
	public MerchantExportBean() {

	}

	public MerchantExportBean(HashMap record) {
		// ����java�������Ϊjavabean��ֵ
		RecordMethod recordMethod = new RecordMethod(record,
				MerchantExportBean.class, this);
		recordMethod.recordset();
	}

	public String getMrchno() {
		return mrchno;
	}

	public void setMrchno(String mrchno) {
		this.mrchno = mrchno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType_yf() {
		return type_yf;
	}

	public void setType_yf(String typeYf) {
		type_yf = typeYf;
	}

	public String getMrchstat() {
		return mrchstat;
	}

	public void setMrchstat(String mrchstat) {
		this.mrchstat = mrchstat;
	}

	public String getAcptbus() {
		return acptbus;
	}

	public void setAcptbus(String acptbus) {
		this.acptbus = acptbus;
	}

	public String getContact3() {
		return contact3;
	}

	public void setContact3(String contact3) {
		this.contact3 = contact3;
	}

	public String getTelno1() {
		return telno1;
	}

	public void setTelno1(String telno1) {
		this.telno1 = telno1;
	}

	public String getAdd_date() {
		return add_date;
	}

	public void setAdd_date(String addDate) {
		add_date = addDate;
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

	@Override
	public String toString() {
		return "MerchantExportBean [mrchno=" + mrchno + ", name=" + name + ", type_yf=" + type_yf + ", mrchstat="
				+ mrchstat + ", mrch_snippet=" + mrch_snippet + ", acptbus=" + acptbus + ", contact3=" + contact3
				+ ", telno1=" + telno1 + ", add_date=" + add_date + ", disabled_date=" + disabled_date
				+ ", enable_date=" + enable_date + "]";
	}
}
