/**
 *包名:cn.yufu.fs.entity
 *描述:package cn.yufu.fs.entity;
 */
package cn.yufu.fs.entity;

import java.math.BigDecimal;

public class MerchantTranRecord {
	private String id;
	
	private String orderCode; // 订单号
	
	private String panStart;// 起始卡号
	
	private String panEnd;// 结束卡号
	
	private String pan;// 卡号
	
	private String cardBin;// 卡BIN
	
	private String merchantnumber;// 商户号
	
	private String merchantname;// 商户名称

	private String terminalnumber;// 终端号
	
	private String transactiondate;// 交易日期
	
	private String transactiondateStart;// 交易起始日期
	
	private String transactiondateEnd;// 交易截止日期

	private String transactiontime;// 交易时间

	private BigDecimal transactionmoney;// 消费金额

	private BigDecimal cardaccountmoney;// 卡账户交易金额

	private BigDecimal trueaccountmoney;// 实名账户交易金额

	private BigDecimal integrationaccountmoney;// 积分账户消费金额

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getMerchantnumber() {
		return merchantnumber;
	}

	public void setMerchantnumber(String merchantnumber) {
		this.merchantnumber = merchantnumber == null ? null : merchantnumber.trim();
	}

	public String getTerminalnumber() {
		return terminalnumber;
	}

	public void setTerminalnumber(String terminalnumber) {
		this.terminalnumber = terminalnumber == null ? null : terminalnumber.trim();
	}

	public String getPanStart() {
		return panStart;
	}

	public void setPanStart(String panStart) {
		this.panStart = panStart == null ? null : panStart.trim();
	}

	public String getPanEnd() {
		return panEnd;
	}

	public void setPanEnd(String panEnd) {
		this.panEnd = panEnd == null ? null : panEnd.trim();
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan == null ? null : pan.trim();
	}
	
	public String getCardBin() {
		return cardBin;
	}

	public void setCardBin(String cardBin) {
		this.cardBin = cardBin == null ? null : cardBin.trim();
	}

	public BigDecimal getTransactionmoney() {
		return transactionmoney;
	}

	public void setTransactionmoney(BigDecimal transactionmoney) {
		this.transactionmoney = transactionmoney;
	}

	public BigDecimal getCardaccountmoney() {
		return cardaccountmoney;
	}

	public void setCardaccountmoney(BigDecimal cardaccountmoney) {
		this.cardaccountmoney = cardaccountmoney;
	}

	public BigDecimal getTrueaccountmoney() {
		return trueaccountmoney;
	}

	public void setTrueaccountmoney(BigDecimal trueaccountmoney) {
		this.trueaccountmoney = trueaccountmoney;
	}

	public BigDecimal getIntegrationaccountmoney() {
		return integrationaccountmoney;
	}

	public void setIntegrationaccountmoney(BigDecimal integrationaccountmoney) {
		this.integrationaccountmoney = integrationaccountmoney;
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
		this.transactiontime = transactiontime == null ? null : transactiontime.trim();
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode == null ? null : orderCode.trim();
	}

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname == null ? null : merchantname.trim();
	}

	public String getTransactiondateStart() {
		return transactiondateStart;
	}

	public void setTransactiondateStart(String transactiondateStart) {
		this.transactiondateStart = transactiondateStart;
	}

	public String getTransactiondateEnd() {
		return transactiondateEnd;
	}

	public void setTransactiondateEnd(String transactiondateEnd) {
		this.transactiondateEnd = transactiondateEnd;
	}
	
}
