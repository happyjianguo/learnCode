/**
 *包名:cn.yufu.fs.entity
 *描述:package cn.yufu.fs.entity;
 */
package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * OrdersConsumption.java 版权所有(C) 2017 裕福控股有限公司 创建:gll 时间:2017年5月15日
 * 描述:订单消费明细及汇总
 */
public class OrdersConsumption {
	private String id;
	
	private String ordercode; // 订单号
	
	private String cardnumber;// 卡号

	private String merchantnumber;// 商户号
	
	private String merchantname;// 商户名称

	private String terminalnumber;// 终端号
	
	// 消费日期
	private String transactiondate;

	// 消费时间
	private String transactiontime;

	
	private BigDecimal transactionmoney;// 消费金额

	private BigDecimal cardaccountmoney;// 卡账户交易金额

	private BigDecimal trueaccountmoney;// 实名账户交易金额

	private BigDecimal integrationaccountmoney;// 积分账户消费金额

	private BigDecimal serialnumber;

	private BigDecimal lotno;

	private String referencenumber;

	private BigDecimal returnpoints;

	private String transactiontype;

	private Short transactionstatus;

	private Short cardtype;

	private String cardtypename;

	private String merchanttype;

	private String merchantsectors;

	private String province;

	private String city;

	private String area;

	private BigDecimal datasourceid;

	private Date adddate;

	private String settlementaccount;

	private BigDecimal perfee;

	private BigDecimal feeOrder;

	private String terminallocation;

	private String datasource;

	private Long tlogid;

	private String tlogadddate;

	private String settlementdate;

	private String isexclusive;

	private String consumetype;

	private Long stanorg;

	private String rrn;

	private String termtype;

	private Integer termseq;

	private String pan;

	private String aiid;

	private String txnsrc;

	private String txnsrcorg;

	private Integer txncode;

	private Integer txnstatus;

	private String curtxn;

	private Double amttxn;

	private Double amttxnfee;

	private Double amtprocfee;

	private Double amttxnorg;

	private Integer actype1;

	private String acnum1;

	private Integer actype2;

	private String acnum2;

	private String rspcode;

	private Integer reasoncode;

	private String aprvlcode;

	private Date sysdates;

	private Double ratebill;

	private Double rateset;

	private String adddata;

	private String subTxncode;

	private String flag;

	private String agetinegral;

	private String ordernumber;

	private BigDecimal checkFlag;

	private String clearbookId;

	private BigDecimal oldserialnumber;

	private BigDecimal oldlotno;

	private BigDecimal oldreferencenumber;

	private String oldtransdate;

	private String oldtranstime;

	private String businessId;

	private String kindId;

	private BigDecimal coBrandedAccountmoney;

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

	public String getCardnumber() {
		return cardnumber;
	}

	public void setCardnumber(String cardnumber) {
		this.cardnumber = cardnumber == null ? null : cardnumber.trim();
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

	public BigDecimal getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(BigDecimal serialnumber) {
		this.serialnumber = serialnumber;
	}

	public BigDecimal getLotno() {
		return lotno;
	}

	public void setLotno(BigDecimal lotno) {
		this.lotno = lotno;
	}

	public String getReferencenumber() {
		return referencenumber;
	}

	public void setReferencenumber(String referencenumber) {
		this.referencenumber = referencenumber == null ? null : referencenumber.trim();
	}

	public BigDecimal getReturnpoints() {
		return returnpoints;
	}

	public void setReturnpoints(BigDecimal returnpoints) {
		this.returnpoints = returnpoints;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype == null ? null : transactiontype.trim();
	}

	public Short getTransactionstatus() {
		return transactionstatus;
	}

	public void setTransactionstatus(Short transactionstatus) {
		this.transactionstatus = transactionstatus;
	}

	public Short getCardtype() {
		return cardtype;
	}

	public void setCardtype(Short cardtype) {
		this.cardtype = cardtype;
	}

	public String getCardtypename() {
		return cardtypename;
	}

	public void setCardtypename(String cardtypename) {
		this.cardtypename = cardtypename == null ? null : cardtypename.trim();
	}

	public String getMerchanttype() {
		return merchanttype;
	}

	public void setMerchanttype(String merchanttype) {
		this.merchanttype = merchanttype == null ? null : merchanttype.trim();
	}

	public String getMerchantsectors() {
		return merchantsectors;
	}

	public void setMerchantsectors(String merchantsectors) {
		this.merchantsectors = merchantsectors == null ? null : merchantsectors.trim();
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province == null ? null : province.trim();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area == null ? null : area.trim();
	}

	public BigDecimal getDatasourceid() {
		return datasourceid;
	}

	public void setDatasourceid(BigDecimal datasourceid) {
		this.datasourceid = datasourceid;
	}

	public Date getAdddate() {
		return adddate;
	}

	public void setAdddate(Date adddate) {
		this.adddate = adddate;
	}

	public String getSettlementaccount() {
		return settlementaccount;
	}

	public void setSettlementaccount(String settlementaccount) {
		this.settlementaccount = settlementaccount == null ? null : settlementaccount.trim();
	}

	public BigDecimal getPerfee() {
		return perfee;
	}

	public void setPerfee(BigDecimal perfee) {
		this.perfee = perfee;
	}

	public BigDecimal getFeeOrder() {
		return feeOrder;
	}

	public void setFeeOrder(BigDecimal feeOrder) {
		this.feeOrder = feeOrder;
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

	public String getTerminallocation() {
		return terminallocation;
	}

	public void setTerminallocation(String terminallocation) {
		this.terminallocation = terminallocation == null ? null : terminallocation.trim();
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource == null ? null : datasource.trim();
	}

	public Long getTlogid() {
		return tlogid;
	}

	public void setTlogid(Long tlogid) {
		this.tlogid = tlogid;
	}

	public String getTlogadddate() {
		return tlogadddate;
	}

	public void setTlogadddate(String tlogadddate) {
		this.tlogadddate = tlogadddate == null ? null : tlogadddate.trim();
	}

	public String getSettlementdate() {
		return settlementdate;
	}

	public void setSettlementdate(String settlementdate) {
		this.settlementdate = settlementdate == null ? null : settlementdate.trim();
	}

	public String getIsexclusive() {
		return isexclusive;
	}

	public void setIsexclusive(String isexclusive) {
		this.isexclusive = isexclusive == null ? null : isexclusive.trim();
	}

	public String getConsumetype() {
		return consumetype;
	}

	public void setConsumetype(String consumetype) {
		this.consumetype = consumetype == null ? null : consumetype.trim();
	}

	public Long getStanorg() {
		return stanorg;
	}

	public void setStanorg(Long stanorg) {
		this.stanorg = stanorg;
	}

	public String getRrn() {
		return rrn;
	}

	public void setRrn(String rrn) {
		this.rrn = rrn == null ? null : rrn.trim();
	}

	public String getTermtype() {
		return termtype;
	}

	public void setTermtype(String termtype) {
		this.termtype = termtype == null ? null : termtype.trim();
	}

	public Integer getTermseq() {
		return termseq;
	}

	public void setTermseq(Integer termseq) {
		this.termseq = termseq;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan == null ? null : pan.trim();
	}

	public String getAiid() {
		return aiid;
	}

	public void setAiid(String aiid) {
		this.aiid = aiid == null ? null : aiid.trim();
	}

	public String getTxnsrc() {
		return txnsrc;
	}

	public void setTxnsrc(String txnsrc) {
		this.txnsrc = txnsrc == null ? null : txnsrc.trim();
	}

	public String getTxnsrcorg() {
		return txnsrcorg;
	}

	public void setTxnsrcorg(String txnsrcorg) {
		this.txnsrcorg = txnsrcorg == null ? null : txnsrcorg.trim();
	}

	public Integer getTxncode() {
		return txncode;
	}

	public void setTxncode(Integer txncode) {
		this.txncode = txncode;
	}

	public Integer getTxnstatus() {
		return txnstatus;
	}

	public void setTxnstatus(Integer txnstatus) {
		this.txnstatus = txnstatus;
	}

	public String getCurtxn() {
		return curtxn;
	}

	public void setCurtxn(String curtxn) {
		this.curtxn = curtxn == null ? null : curtxn.trim();
	}

	public Double getAmttxn() {
		return amttxn;
	}

	public void setAmttxn(Double amttxn) {
		this.amttxn = amttxn;
	}

	public Double getAmttxnfee() {
		return amttxnfee;
	}

	public void setAmttxnfee(Double amttxnfee) {
		this.amttxnfee = amttxnfee;
	}

	public Double getAmtprocfee() {
		return amtprocfee;
	}

	public void setAmtprocfee(Double amtprocfee) {
		this.amtprocfee = amtprocfee;
	}

	public Double getAmttxnorg() {
		return amttxnorg;
	}

	public void setAmttxnorg(Double amttxnorg) {
		this.amttxnorg = amttxnorg;
	}

	public Integer getActype1() {
		return actype1;
	}

	public void setActype1(Integer actype1) {
		this.actype1 = actype1;
	}

	public String getAcnum1() {
		return acnum1;
	}

	public void setAcnum1(String acnum1) {
		this.acnum1 = acnum1 == null ? null : acnum1.trim();
	}

	public Integer getActype2() {
		return actype2;
	}

	public void setActype2(Integer actype2) {
		this.actype2 = actype2;
	}

	public String getAcnum2() {
		return acnum2;
	}

	public void setAcnum2(String acnum2) {
		this.acnum2 = acnum2 == null ? null : acnum2.trim();
	}

	public String getRspcode() {
		return rspcode;
	}

	public void setRspcode(String rspcode) {
		this.rspcode = rspcode == null ? null : rspcode.trim();
	}

	public Integer getReasoncode() {
		return reasoncode;
	}

	public void setReasoncode(Integer reasoncode) {
		this.reasoncode = reasoncode;
	}

	public String getAprvlcode() {
		return aprvlcode;
	}

	public void setAprvlcode(String aprvlcode) {
		this.aprvlcode = aprvlcode == null ? null : aprvlcode.trim();
	}

	public Date getSysdates() {
		return sysdates;
	}

	public void setSysdates(Date sysdates) {
		this.sysdates = sysdates;
	}

	public Double getRatebill() {
		return ratebill;
	}

	public void setRatebill(Double ratebill) {
		this.ratebill = ratebill;
	}

	public Double getRateset() {
		return rateset;
	}

	public void setRateset(Double rateset) {
		this.rateset = rateset;
	}

	public String getAdddata() {
		return adddata;
	}

	public void setAdddata(String adddata) {
		this.adddata = adddata == null ? null : adddata.trim();
	}

	public String getSubTxncode() {
		return subTxncode;
	}

	public void setSubTxncode(String subTxncode) {
		this.subTxncode = subTxncode == null ? null : subTxncode.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	public String getAgetinegral() {
		return agetinegral;
	}

	public void setAgetinegral(String agetinegral) {
		this.agetinegral = agetinegral == null ? null : agetinegral.trim();
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber == null ? null : ordernumber.trim();
	}

	public BigDecimal getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(BigDecimal checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String getClearbookId() {
		return clearbookId;
	}

	public void setClearbookId(String clearbookId) {
		this.clearbookId = clearbookId == null ? null : clearbookId.trim();
	}

	public BigDecimal getOldserialnumber() {
		return oldserialnumber;
	}

	public void setOldserialnumber(BigDecimal oldserialnumber) {
		this.oldserialnumber = oldserialnumber;
	}

	public BigDecimal getOldlotno() {
		return oldlotno;
	}

	public void setOldlotno(BigDecimal oldlotno) {
		this.oldlotno = oldlotno;
	}

	public BigDecimal getOldreferencenumber() {
		return oldreferencenumber;
	}

	public void setOldreferencenumber(BigDecimal oldreferencenumber) {
		this.oldreferencenumber = oldreferencenumber;
	}

	public String getOldtransdate() {
		return oldtransdate;
	}

	public void setOldtransdate(String oldtransdate) {
		this.oldtransdate = oldtransdate == null ? null : oldtransdate.trim();
	}

	public String getOldtranstime() {
		return oldtranstime;
	}

	public void setOldtranstime(String oldtranstime) {
		this.oldtranstime = oldtranstime == null ? null : oldtranstime.trim();
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId == null ? null : businessId.trim();
	}

	public String getKindId() {
		return kindId;
	}

	public void setKindId(String kindId) {
		this.kindId = kindId == null ? null : kindId.trim();
	}

	public BigDecimal getCoBrandedAccountmoney() {
		return coBrandedAccountmoney;
	}

	public void setCoBrandedAccountmoney(BigDecimal coBrandedAccountmoney) {
		this.coBrandedAccountmoney = coBrandedAccountmoney;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getMerchantname() {
		return merchantname;
	}

	public void setMerchantname(String merchantname) {
		this.merchantname = merchantname;
	}

	@Override
	public String toString() {
		return "OrdersConsumption [id=" + id + ", ordercode=" + ordercode + ", cardnumber=" + cardnumber
				+ ", merchantnumber=" + merchantnumber + ", merchantname=" + merchantname + ", terminalnumber="
				+ terminalnumber + ", transactiondate=" + transactiondate + ", transactiontime=" + transactiontime
				+ ", transactionmoney=" + transactionmoney + ", cardaccountmoney=" + cardaccountmoney
				+ ", trueaccountmoney=" + trueaccountmoney + ", integrationaccountmoney=" + integrationaccountmoney
				+ ", serialnumber=" + serialnumber + ", lotno=" + lotno + ", referencenumber=" + referencenumber
				+ ", returnpoints=" + returnpoints + ", transactiontype=" + transactiontype + ", transactionstatus="
				+ transactionstatus + ", cardtype=" + cardtype + ", cardtypename=" + cardtypename + ", merchanttype="
				+ merchanttype + ", merchantsectors=" + merchantsectors + ", province=" + province + ", city=" + city
				+ ", area=" + area + ", datasourceid=" + datasourceid + ", adddate=" + adddate + ", settlementaccount="
				+ settlementaccount + ", perfee=" + perfee + ", feeOrder=" + feeOrder + ", terminallocation="
				+ terminallocation + ", datasource=" + datasource + ", tlogid=" + tlogid + ", tlogadddate="
				+ tlogadddate + ", settlementdate=" + settlementdate + ", isexclusive=" + isexclusive + ", consumetype="
				+ consumetype + ", stanorg=" + stanorg + ", rrn=" + rrn + ", termtype=" + termtype + ", termseq="
				+ termseq + ", pan=" + pan + ", aiid=" + aiid + ", txnsrc=" + txnsrc + ", txnsrcorg=" + txnsrcorg
				+ ", txncode=" + txncode + ", txnstatus=" + txnstatus + ", curtxn=" + curtxn + ", amttxn=" + amttxn
				+ ", amttxnfee=" + amttxnfee + ", amtprocfee=" + amtprocfee + ", amttxnorg=" + amttxnorg + ", actype1="
				+ actype1 + ", acnum1=" + acnum1 + ", actype2=" + actype2 + ", acnum2=" + acnum2 + ", rspcode="
				+ rspcode + ", reasoncode=" + reasoncode + ", aprvlcode=" + aprvlcode + ", sysdates=" + sysdates
				+ ", ratebill=" + ratebill + ", rateset=" + rateset + ", adddata=" + adddata + ", subTxncode="
				+ subTxncode + ", flag=" + flag + ", agetinegral=" + agetinegral + ", ordernumber=" + ordernumber
				+ ", checkFlag=" + checkFlag + ", clearbookId=" + clearbookId + ", oldserialnumber=" + oldserialnumber
				+ ", oldlotno=" + oldlotno + ", oldreferencenumber=" + oldreferencenumber + ", oldtransdate="
				+ oldtransdate + ", oldtranstime=" + oldtranstime + ", businessId=" + businessId + ", kindId=" + kindId
				+ ", coBrandedAccountmoney=" + coBrandedAccountmoney + "]";
	}
}
