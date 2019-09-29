package cn.yufu.posp.ruleManager.domain.model;

/**
 * TblStlmRegu entity. @author MyEclipse Persistence Tools
 */

public class TblStlmRegu implements java.io.Serializable {

	// Fields

	private String ruleNo;
	private String ruleLevel;
	private String startDate;
	private String endDate;
	private String channelNo;
	private String cardType;
	private String transCode;
	private String mcc;
	private String mchtNo;
	private String mchtFlg;
	private String g1CrDscFlg;
	private String g1CrDscRate1;
	private String g1CrDscAmt1;
	private String g1CrDscAmt2;
	private String g1CrDscSum1;
	private String g1CrDscSum2;
	private String g1CrDscFee;
	private String brhFlg;
	private String brhStlmFlg;
	private String g2CrDscFlg;
	private String g2CrDscRate1;
	private String g2CrDscAmt1;
	private String g2CrDscAmt2;
	private String g2CrDscSum1;
	private String g2CrDscSum2;
	private String g2CrDscFee;
	private String remark;
	private String operId;
	private String crtDatetime;
	private String updDatetime;
	private String mccName;

	// Constructors

	/** default constructor */
	public TblStlmRegu() {
	}

	/** minimal constructor */
	public TblStlmRegu(String ruleNo, String ruleLevel, String startDate, String endDate, String channelNo, String cardType, String transCode, String mcc,
			String mchtNo, String g1CrDscFlg, String g1CrDscRate1, String g1CrDscAmt1, String g1CrDscAmt2, String g1CrDscSum1, String g1CrDscSum2,
			String g1CrDscFee) {
		this.ruleNo = ruleNo;
		this.ruleLevel = ruleLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.channelNo = channelNo;
		this.cardType = cardType;
		this.transCode = transCode;
		this.mcc = mcc;
		this.mchtNo = mchtNo;
		this.g1CrDscFlg = g1CrDscFlg;
		this.g1CrDscRate1 = g1CrDscRate1;
		this.g1CrDscAmt1 = g1CrDscAmt1;
		this.g1CrDscAmt2 = g1CrDscAmt2;
		this.g1CrDscSum1 = g1CrDscSum1;
		this.g1CrDscSum2 = g1CrDscSum2;
		this.g1CrDscFee = g1CrDscFee;
	}

	/** full constructor */
	public TblStlmRegu(String ruleNo, String ruleLevel, String startDate, String endDate, String channelNo, String cardType, String transCode, String mcc,
			String mchtNo, String mchtFlg, String g1CrDscFlg, String g1CrDscRate1, String g1CrDscAmt1, String g1CrDscAmt2, String g1CrDscSum1,
			String g1CrDscSum2, String g1CrDscFee, String brhFlg, String brhStlmFlg, String g2CrDscFlg, String g2CrDscRate1, String g2CrDscAmt1,
			String g2CrDscAmt2, String g2CrDscSum1, String g2CrDscSum2, String g2CrDscFee, String remark, String operId, String crtDatetime, String updDatetime) {
		this.ruleNo = ruleNo;
		this.ruleLevel = ruleLevel;
		this.startDate = startDate;
		this.endDate = endDate;
		this.channelNo = channelNo;
		this.cardType = cardType;
		this.transCode = transCode;
		this.mcc = mcc;
		this.mchtNo = mchtNo;
		this.mchtFlg = mchtFlg;
		this.g1CrDscFlg = g1CrDscFlg;
		this.g1CrDscRate1 = g1CrDscRate1;
		this.g1CrDscAmt1 = g1CrDscAmt1;
		this.g1CrDscAmt2 = g1CrDscAmt2;
		this.g1CrDscSum1 = g1CrDscSum1;
		this.g1CrDscSum2 = g1CrDscSum2;
		this.g1CrDscFee = g1CrDscFee;
		this.brhFlg = brhFlg;
		this.brhStlmFlg = brhStlmFlg;
		this.g2CrDscFlg = g2CrDscFlg;
		this.g2CrDscRate1 = g2CrDscRate1;
		this.g2CrDscAmt1 = g2CrDscAmt1;
		this.g2CrDscAmt2 = g2CrDscAmt2;
		this.g2CrDscSum1 = g2CrDscSum1;
		this.g2CrDscSum2 = g2CrDscSum2;
		this.g2CrDscFee = g2CrDscFee;
		this.remark = remark;
		this.operId = operId;
		this.crtDatetime = crtDatetime;
		this.updDatetime = updDatetime;
	}

	// Property accessors

	public String getRuleNo() {
		return this.ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getRuleLevel() {
		return this.ruleLevel;
	}

	public void setRuleLevel(String ruleLevel) {
		this.ruleLevel = ruleLevel;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getChannelNo() {
		return this.channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getTransCode() {
		return this.transCode;
	}

	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}

	public String getMcc() {
		return this.mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getMchtNo() {
		return this.mchtNo;
	}

	public void setMchtNo(String mchtNo) {
		this.mchtNo = mchtNo;
	}

	public String getMchtFlg() {
		return this.mchtFlg;
	}

	public void setMchtFlg(String mchtFlg) {
		this.mchtFlg = mchtFlg;
	}

	public String getG1CrDscFlg() {
		return this.g1CrDscFlg;
	}

	public void setG1CrDscFlg(String g1CrDscFlg) {
		this.g1CrDscFlg = g1CrDscFlg;
	}

	public String getG1CrDscRate1() {
		return this.g1CrDscRate1;
	}

	public void setG1CrDscRate1(String g1CrDscRate1) {
		this.g1CrDscRate1 = g1CrDscRate1;
	}

	public String getG1CrDscAmt1() {
		return this.g1CrDscAmt1;
	}

	public void setG1CrDscAmt1(String g1CrDscAmt1) {
		this.g1CrDscAmt1 = g1CrDscAmt1;
	}

	public String getG1CrDscAmt2() {
		return this.g1CrDscAmt2;
	}

	public void setG1CrDscAmt2(String g1CrDscAmt2) {
		this.g1CrDscAmt2 = g1CrDscAmt2;
	}

	public String getG1CrDscSum1() {
		return this.g1CrDscSum1;
	}

	public void setG1CrDscSum1(String g1CrDscSum1) {
		this.g1CrDscSum1 = g1CrDscSum1;
	}

	public String getG1CrDscSum2() {
		return this.g1CrDscSum2;
	}

	public void setG1CrDscSum2(String g1CrDscSum2) {
		this.g1CrDscSum2 = g1CrDscSum2;
	}

	public String getG1CrDscFee() {
		return this.g1CrDscFee;
	}

	public void setG1CrDscFee(String g1CrDscFee) {
		this.g1CrDscFee = g1CrDscFee;
	}

	public String getBrhFlg() {
		return this.brhFlg;
	}

	public void setBrhFlg(String brhFlg) {
		this.brhFlg = brhFlg;
	}

	public String getBrhStlmFlg() {
		return this.brhStlmFlg;
	}

	public void setBrhStlmFlg(String brhStlmFlg) {
		this.brhStlmFlg = brhStlmFlg;
	}

	public String getG2CrDscFlg() {
		return this.g2CrDscFlg;
	}

	public void setG2CrDscFlg(String g2CrDscFlg) {
		this.g2CrDscFlg = g2CrDscFlg;
	}

	public String getG2CrDscRate1() {
		return this.g2CrDscRate1;
	}

	public void setG2CrDscRate1(String g2CrDscRate1) {
		this.g2CrDscRate1 = g2CrDscRate1;
	}

	public String getG2CrDscAmt1() {
		return this.g2CrDscAmt1;
	}

	public void setG2CrDscAmt1(String g2CrDscAmt1) {
		this.g2CrDscAmt1 = g2CrDscAmt1;
	}

	public String getG2CrDscAmt2() {
		return this.g2CrDscAmt2;
	}

	public void setG2CrDscAmt2(String g2CrDscAmt2) {
		this.g2CrDscAmt2 = g2CrDscAmt2;
	}

	public String getG2CrDscSum1() {
		return this.g2CrDscSum1;
	}

	public void setG2CrDscSum1(String g2CrDscSum1) {
		this.g2CrDscSum1 = g2CrDscSum1;
	}

	public String getG2CrDscSum2() {
		return this.g2CrDscSum2;
	}

	public void setG2CrDscSum2(String g2CrDscSum2) {
		this.g2CrDscSum2 = g2CrDscSum2;
	}

	public String getG2CrDscFee() {
		return this.g2CrDscFee;
	}

	public void setG2CrDscFee(String g2CrDscFee) {
		this.g2CrDscFee = g2CrDscFee;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOperId() {
		return this.operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

	public String getCrtDatetime() {
		return this.crtDatetime;
	}

	public void setCrtDatetime(String crtDatetime) {
		this.crtDatetime = crtDatetime;
	}

	public String getUpdDatetime() {
		return this.updDatetime;
	}

	public void setUpdDatetime(String updDatetime) {
		this.updDatetime = updDatetime;
	}

	public String getMccName() {
		return mccName;
	}

	public void setMccName(String mccName) {
		this.mccName = mccName;
	}

}