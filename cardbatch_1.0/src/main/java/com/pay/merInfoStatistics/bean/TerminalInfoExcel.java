package com.pay.merInfoStatistics.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

public class TerminalInfoExcel {
	
	private String id;  //�ն˱��
	private String merchantid;  //�����̻����
	private String address;  //�̻�װ����ַ
	private String detailaddress;  //���̼���̨��ַ
	private String model;  //POS�ͺ�
	private String type;  //POS����
	private String mobilenumber;  //����POS�ֻ���
	private String snnumber;  //POS��S/N��
	private String installdate;  //��װ����
	private String disabledate;  //ͣ������
	private String updatedate;  //��������
	private String name;  //��ϵ��
	private String phonenumber;  //�ŵ�绰
	private String status;  //POS��״̬
	private String deposite;  //POSѺ��Ԫ��
	
	public TerminalInfoExcel(HashMap record) {		
		// ����java�������Ϊjavabean��ֵ
		RecordMethod recordMethod = new RecordMethod(record,TerminalInfoExcel.class, this);
		recordMethod.recordset();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMerchantid() {
		return merchantid;
	}
	public void setMerchantid(String merchantid) {
		this.merchantid = merchantid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getSnnumber() {
		return snnumber;
	}
	public void setSnnumber(String snnumber) {
		this.snnumber = snnumber;
	}
	public String getInstalldate() {
		return installdate;
	}
	public void setInstalldate(String installdate) {
		this.installdate = installdate;
	}
	public String getDisabledate() {
		return disabledate;
	}
	public void setDisabledate(String disabledate) {
		this.disabledate = disabledate;
	}
	public String getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeposite() {
		return deposite;
	}
	public void setDeposite(String deposite) {
		this.deposite = deposite;
	}
	
}
