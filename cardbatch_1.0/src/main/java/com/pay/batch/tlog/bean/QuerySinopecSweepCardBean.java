/**
 *����:com.pay.batch.tlog.bean
 *����:package com.pay.batch.tlog.bean;
 */
package com.pay.batch.tlog.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

/**
 * QuerySinopecSweepCardBean.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��5��17��
 * ����:��ʯ��ɨ��ˢ��ͳ��
 */
public class QuerySinopecSweepCardBean {
	private String tradingtype ; //�������� ;
	private String tradingnumber ; //���ױ��� ;
	private String tradingamt ; //���׽�� ;
	public QuerySinopecSweepCardBean(){
		
	}
	public QuerySinopecSweepCardBean(HashMap record) {
		//����java�������Ϊjavabean��ֵ
		RecordMethod  recordMethod=new RecordMethod(record,QuerySinopecSweepCardBean.class,this);
		recordMethod.recordset();
	}
	public String getTradingtype() {
		return tradingtype;
	}
	public void setTradingtype(String tradingtype) {
		this.tradingtype = tradingtype;
	}
	public String getTradingnumber() {
		return tradingnumber;
	}
	public void setTradingnumber(String tradingnumber) {
		this.tradingnumber = tradingnumber;
	}
	public String getTradingamt() {
		return tradingamt;
	}
	public void setTradingamt(String tradingamt) {
		this.tradingamt = tradingamt;
	}
	@Override
	public String toString() {
		return "QuerySinopecSweepCardBean [tradingtype=" + tradingtype + ", tradingnumber=" + tradingnumber
				+ ", tradingamt=" + tradingamt + "]";
	}
}
