package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.core.web.form.BaseForm;

public class CardAcctForm extends BaseForm {
	
	private String cardNo; //卡号
	private String expire; //有效期
	private String cardStat; //卡状态
	private String masterCardNo; //Master卡号
	private String masterStat; //MAster卡状态
	private String attachFlag; //附属标志
	private String acctNo; //卡帐号
	private int depositCnt; //存款次数
	private double depositAmt; //存款金额
	private double cardAmt; //信用金额
	private int authCnt; //当日已授权次数
	private double authAmt; //当日已授权金额
	private double authTotal; //已授权金额
	private double balance; //帐户余额
	private String owner; //持卡人
	private String personId; //身份证
	private String address; //联系地址
	private String job; //工作单位
	private String telephone; //联系电话
	private String updateOper; //登记柜员
	private String updateDate; //维护日期
	private String updateTime; //维护时间

	/**卡状态--页面显示用*/
	private String ch_cardStat;

	
	/**存款金额-页面显示用*/
	private String ch_depositAmt;
	/**信用金额--页面显示用*/
	private String ch_cardAmt;
	/**当日已授权金额--页面显示用*/
	private String ch_authAmt;
	/**已授权金额--页面显示用*/
	private String ch_authTotal;
	/**帐户余额--页面显示用*/
	private String ch_balance; 
	
	
	public String getCh_depositAmt() {
		return ch_depositAmt;
	}

	public void setCh_depositAmt(String chDepositAmt) {
		ch_depositAmt = chDepositAmt;
	}

	public String getCh_cardAmt() {
		return ch_cardAmt;
	}

	public void setCh_cardAmt(String chCardAmt) {
		ch_cardAmt = chCardAmt;
	}

	public String getCh_authAmt() {
		return ch_authAmt;
	}

	public void setCh_authAmt(String chAuthAmt) {
		ch_authAmt = chAuthAmt;
	}

	public String getCh_authTotal() {
		return ch_authTotal;
	}

	public void setCh_authTotal(String chAuthTotal) {
		ch_authTotal = chAuthTotal;
	}

	public String getCh_balance() {
		return ch_balance;
	}

	public void setCh_balance(String chBalance) {
		ch_balance = chBalance;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getCardStat() {
		return cardStat;
	}

	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
	}

	public String getMasterCardNo() {
		return masterCardNo;
	}

	public void setMasterCardNo(String masterCardNo) {
		this.masterCardNo = masterCardNo;
	}

	public String getMasterStat() {
		return masterStat;
	}

	public void setMasterStat(String masterStat) {
		this.masterStat = masterStat;
	}

	public String getAttachFlag() {
		return attachFlag;
	}

	public void setAttachFlag(String attachFlag) {
		this.attachFlag = attachFlag;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public int getDepositCnt() {
		return depositCnt;
	}

	public void setDepositCnt(int depositCnt) {
		this.depositCnt = depositCnt;
	}

	public double getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(double depositAmt) {
		this.depositAmt = depositAmt;
	}

	public double getCardAmt() {
		return cardAmt;
	}

	public void setCardAmt(double cardAmt) {
		this.cardAmt = cardAmt;
	}

	public int getAuthCnt() {
		return authCnt;
	}

	public void setAuthCnt(int authCnt) {
		this.authCnt = authCnt;
	}

	public double getAuthAmt() {
		return authAmt;
	}

	public void setAuthAmt(double authAmt) {
		this.authAmt = authAmt;
	}

	public double getAuthTotal() {
		return authTotal;
	}

	public void setAuthTotal(double authTotal) {
		this.authTotal = authTotal;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUpdateOper() {
		return updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCh_cardStat() {
		return ch_cardStat;
	}

	public void setCh_cardStat(String chCardStat) {
		ch_cardStat = chCardStat;
	}
	
	
}
