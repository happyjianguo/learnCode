package com.pay.batch.bflowlog.struts.form;

import org.apache.struts.action.ActionForm;

public class MakeCardForm extends ActionForm{
	private static final long serialVersionUID = 1L;
	private String verno_ctx;//
	private String id;//����id		
	private String txtime;// ����ʱ��
	private String stan;// ��ˮ��
	private String cust_order;// ������
	private String pan;// ����
	private String telphone;// �ֻ���
	private String address; // ��ϵ��ַ
	private String name;// ����
	private String batch;// ���κ�
	private String org_batch;// ԭ���κ�
	private String fee;// ������
	private String operator;// ������
	private String stat;// ״̬(00,δ�ƿ���01���ƿ���02�ƿ�ʧ��)
	private String acct_period;//
	private String reserved1;// �ƿ��ļ�·��
	private String reserved2;
	private String reserved3; // ��ע
	private String crd_expdate;// ����Ч��
	
	//��ѯ����
	private String queryCustOrder;
	private String queryPan;
	private String queryBatch;
	private String queryStat;
	
	public String getQueryCustOrder() {
		return queryCustOrder;
	}
	public void setQueryCustOrder(String queryCustOrder) {
		this.queryCustOrder = queryCustOrder;
	}
	public String getQueryPan() {
		return queryPan;
	}
	public void setQueryPan(String queryPan) {
		this.queryPan = queryPan;
	}
	public String getQueryBatch() {
		return queryBatch;
	}
	public void setQueryBatch(String queryBatch) {
		this.queryBatch = queryBatch;
	}
	public String getQueryStat() {
		return queryStat;
	}
	public void setQueryStat(String queryStat) {
		this.queryStat = queryStat;
	}
	public String getVerno_ctx() {
		return verno_ctx;
	}
	public void setVerno_ctx(String vernoCtx) {
		verno_ctx = vernoCtx;
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
	public String getCust_order() {
		return cust_order;
	}
	public void setCust_order(String custOrder) {
		cust_order = custOrder;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getOrg_batch() {
		return org_batch;
	}
	public void setOrg_batch(String orgBatch) {
		org_batch = orgBatch;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getAcct_period() {
		return acct_period;
	}
	public void setAcct_period(String acctPeriod) {
		acct_period = acctPeriod;
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
	public String getCrd_expdate() {
		return crd_expdate;
	}
	public void setCrd_expdate(String crdExpdate) {
		crd_expdate = crdExpdate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
