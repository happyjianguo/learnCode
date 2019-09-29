package cn.yufu.fs.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 交易统计报表
 * */
public class TranRecordTotal {
    private String id;	//主键

    private String merchantnumber;	//商户号
    
    private String merchantName;    //商户名称

    private String terminalnumber;	//终端号

    private String cardnumber;		//卡号

    private BigDecimal transactionmoney;	//交易金额
    
    private BigDecimal transactionCount; 	//交易笔数

    private BigDecimal cardaccountmoney;	//卡账户交易金额

    private BigDecimal trueaccountmoney;	//实名账户交易金额

    private BigDecimal integrationaccountmoney;	//积分账户消费金额
    
    private BigDecimal returnpoints;	//消费返还积分

    private BigDecimal serialnumber;	//流水号

    private BigDecimal lotno;	//批次号

    private String referencenumber;	//参考号

    private BigDecimal returnpoIntegers;	//消费返还积分

    private String transactiontype;	//交易类型

    private Integer transactionstatus;	//交易状态

    private Integer cardtype;	//卡类型

    private String cardtypename;	//卡类型名称

    private String merchanttype;	//商户类型

    private String merchantsectors;	//商户行业类别

    private String province;	//省

    private String city;	//市

    private String area;	//区

    private BigDecimal datasourceid;	//数据来源ID1:裕福旧库1，2：裕福旧库2，3：cortex库
    
    private String datasource;	//数据来源描述

    private Date adddate;	//添加日期

    private String settlementaccount;	//结算账号

    private BigDecimal perfee;	//单笔手续费

    private BigDecimal feeOrder;	//单笔手续费费率

    private String transactiondate;	//交易日期

    private String transactiontime;	//交易时间

    private String terminallocation;	//终端位置

    private Long tlogid;	//源表ID

    private String tlogadddate;	//源表数据添加日期

    private String settlementdate;	//结算日期（中石化）

    private String isexclusive;	//是否专属卡

    private String consumetype;	//中石化消费类型

    private Long stanorg;	//原始交易流水号

    private String rrn;	//主机流水号（参考号）

    private String termtype;	//终端类型

    private Integer termseq;	//终端索引号（根终端表有对应关系）（pos流水号）

    private String pan;	//卡号

    private String aiid;	//收单机构代码（万事达下属收单机构编号）

    private String txnsrc;

    private String txnsrcorg;

    private Integer txncode;

    private Integer txnstatus;

    private String curtxn;

    private BigDecimal amttxn;

    private BigDecimal amttxnfee;

    private BigDecimal amtprocfee;

    private BigDecimal amttxnorg;

    private Integer actype1;

    private String acnum1;

    private Integer actype2;

    private String acnum2;

    private String rspcode;

    private Integer reasoncode;

    private String aprvlcode;

    private Date sysdates;

    private BigDecimal ratebill;

    private BigDecimal rateset;

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

    private String businessId;	//当终端类型为多场景终端时，此字段为交易场景,编码3个字节,默认为000

    private String kindId;		//卡产品（格式化之前）
    
    private BigDecimal coBrandedAccountmoney;
    
    //查询条件
    private String queryType;	//查询类型
    
	private String transactiondateStart;// 交易起始日期
	
	private String transactiondateEnd;// 交易截止日期
	
	private String begainCardNo;	//开始卡号
	
	private String endCardNo;		//结束卡号
	
	private String isBanlance; 		//是否参与结算  0 不参与  1参与
	
	private List<String> cardTypeList;	//卡类型编号集合
	
    private String begainPan;  //开始卡号
    
    private String endPan;  	//结束卡号
	
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
    
    public BigDecimal getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(BigDecimal transactionCount) {
		this.transactionCount = transactionCount;
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

    public BigDecimal getReturnpoIntegers() {
        return returnpoIntegers;
    }

    public void setReturnpoIntegers(BigDecimal returnpoIntegers) {
        this.returnpoIntegers = returnpoIntegers;
    }

    public String getTransactiontype() {
        return transactiontype;
    }

    public void setTransactiontype(String transactiontype) {
        this.transactiontype = transactiontype == null ? null : transactiontype.trim();
    }

    public Integer getTransactionstatus() {
        return transactionstatus;
    }

    public void setTransactionstatus(Integer transactionstatus) {
        this.transactionstatus = transactionstatus;
    }

    public Integer getCardtype() {
        return cardtype;
    }

    public void setCardtype(Integer cardtype) {
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

    public BigDecimal getAmttxn() {
        return amttxn;
    }

    public void setAmttxn(BigDecimal amttxn) {
        this.amttxn = amttxn;
    }

    public BigDecimal getAmttxnfee() {
        return amttxnfee;
    }

    public void setAmttxnfee(BigDecimal amttxnfee) {
        this.amttxnfee = amttxnfee;
    }

    public BigDecimal getAmtprocfee() {
        return amtprocfee;
    }

    public void setAmtprocfee(BigDecimal amtprocfee) {
        this.amtprocfee = amtprocfee;
    }

    public BigDecimal getAmttxnorg() {
        return amttxnorg;
    }

    public void setAmttxnorg(BigDecimal amttxnorg) {
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

    public BigDecimal getRatebill() {
        return ratebill;
    }

    public void setRatebill(BigDecimal ratebill) {
        this.ratebill = ratebill;
    }

    public BigDecimal getRateset() {
        return rateset;
    }

    public void setRateset(BigDecimal rateset) {
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

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
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

	public String getBegainCardNo() {
		return begainCardNo;
	}

	public void setBegainCardNo(String begainCardNo) {
		this.begainCardNo = begainCardNo;
	}

	public String getEndCardNo() {
		return endCardNo;
	}

	public void setEndCardNo(String endCardNo) {
		this.endCardNo = endCardNo;
	}

	public String getIsBanlance() {
		return isBanlance;
	}

	public void setIsBanlance(String isBanlance) {
		this.isBanlance = isBanlance;
	}

	public List<String> getCardTypeList() {
		return cardTypeList;
	}

	public void setCardTypeList(List<String> cardTypeList) {
		this.cardTypeList = cardTypeList == null || cardTypeList.size() == 0 ? null : cardTypeList;
	}

	public BigDecimal getReturnpoints() {
		return returnpoints;
	}

	public void setReturnpoints(BigDecimal returnpoints) {
		this.returnpoints = returnpoints;
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