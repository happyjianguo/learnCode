package cn.yufu.bak.entity;

import java.math.BigDecimal;

/**
 * 实时交易流水 
 * @author Administrator ZQK 2018-01-25
 * */
public class ViewTLogRealTime {

	private String merchantnumber;	//商户号
	
	private String merchantName; //商户名称

	private String terminalnumber;	//终端号

	private String terminallocation;	//终端位置

	private String cardnumber;	//卡号
	
	private String orderId;		//支付清单单号 
	
	private String orgOrderId;	//支付原清算单号
	
	private String cashSource;	//充值来源

	private BigDecimal transactionmoney;	//交易金额

	private Long serialnumber;	//流水号

	private String lotno;	//批次号

	private String referencenumber;	//参考号

	private String transactiondate;	//交易日期

	private String transactiontime;	//交易时间

	private String kindId;	//卡产品

	private String datasource;	//来源数据库名

	private String tlogid;

	private String consumetype;	//中石化消费类型  	90购物  91加油
	
	private String tranTypeDesc;  	//交易类型描述
	
    private String begainTrueName;  //开始实名日期
    
    private String endTrueName;  //结束实名日期
    
    private BigDecimal amttxn;	//手续费
    
    private String begainPan;  //开始卡号
    
    private String endPan;  	//结束卡号

	public String getMerchantnumber() {
		return merchantnumber;
	}

	public void setMerchantnumber(String merchantnumber) {
		this.merchantnumber = merchantnumber == null ? null : merchantnumber.trim();
	}
	
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getTerminalnumber() {
		return terminalnumber;
	}

	public void setTerminalnumber(String terminalnumber) {
		this.terminalnumber = terminalnumber == null ? null : terminalnumber.trim();
	}

	public String getTerminallocation() {
		return terminallocation;
	}

	public void setTerminallocation(String terminallocation) {
		this.terminallocation = terminallocation == null ? null : terminallocation.trim();
	}

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber == null ? null : cardnumber.trim();
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId == null ? null : orderId.trim();
	}

	public String getOrgOrderId() {
		return orgOrderId;
	}

	public void setOrgOrderId(String orgOrderId) {
		this.orgOrderId = orgOrderId == null ? null : orgOrderId.trim();
	}

	public String getCashSource() {
		return cashSource;
	}

	public void setCashSource(String cashSource) {
		this.cashSource = cashSource == null ? null : cashSource.trim();
	}

	public BigDecimal getTransactionmoney() {
		return transactionmoney;
	}

	public void setTransactionmoney(BigDecimal transactionmoney) {
		this.transactionmoney = transactionmoney;
	}

	public Long getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(Long serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getLotno() {
		return lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno == null ? null : lotno.trim();
	}

	public String getReferencenumber() {
		return referencenumber;
	}

	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber == null ? null : referencenumber.trim();
	}

	public String getTransactiondate() {
		return transactiondate;
	}

	public void setTransactiondate(String transactiondate) {
		this.transactiondate = transactiondate == null ? null : transactiondate.trim();
	}

	public String getTransactiontime() {
		return transactiontime;
	}

	public void setTransactiontime(String transactiontime) {
		this.transactiontime = transactiontime;
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId == null ? null : kindId.trim();
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource == null ? null : datasource.trim();
	}

	public String getTlogid() {
		return tlogid;
	}

	public void setTlogid(String tlogid) {
		this.tlogid = tlogid;
	}

	public String getConsumetype() {
		return consumetype;
	}

	public void setConsumetype(String consumetype) {
		this.consumetype = consumetype;
	}

	public String getTranTypeDesc() {
		return tranTypeDesc;
	}

	public void setTranTypeDesc(String tranTypeDesc) {
		this.tranTypeDesc = tranTypeDesc;
	}

	public String getBegainTrueName() {
		return begainTrueName;
	}

	public void setBegainTrueName(String begainTrueName) {
		this.begainTrueName = begainTrueName;
	}

	public String getEndTrueName() {
		return endTrueName;
	}

	public void setEndTrueName(String endTrueName) {
		this.endTrueName = endTrueName;
	}

	public BigDecimal getAmttxn() {
		return amttxn;
	}

	public void setAmttxn(BigDecimal amttxn) {
		this.amttxn = amttxn;
	}

	public String getBegainPan() {
		return begainPan;
	}

	public void setBegainPan(String begainPan) {
		this.begainPan = begainPan;
	}

	public String getEndPan() {
		return endPan;
	}

	public void setEndPan(String endPan) {
		this.endPan = endPan;
	}
	
}