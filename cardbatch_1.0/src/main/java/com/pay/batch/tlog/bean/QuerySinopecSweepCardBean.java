/**
 *包名:com.pay.batch.tlog.bean
 *描述:package com.pay.batch.tlog.bean;
 */
package com.pay.batch.tlog.bean;

import java.util.HashMap;

import com.pay.util.RecordMethod;

/**
 * QuerySinopecSweepCardBean.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年5月17日
 * 描述:中石化扫码刷卡统计
 */
public class QuerySinopecSweepCardBean {
	private String tradingtype ; //交易类型 ;
	private String tradingnumber ; //交易笔数 ;
	private String tradingamt ; //交易金额 ;
	public QuerySinopecSweepCardBean(){
		
	}
	public QuerySinopecSweepCardBean(HashMap record) {
		//利用java反射机制为javabean赋值
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
