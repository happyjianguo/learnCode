package cn.yufu.posp.terminalmanager.domain.model;

import java.math.BigDecimal;

/**
 * CardAcctId entity. @author MyEclipse Persistence Tools
 */

public class CardAcct implements java.io.Serializable {

	// Fields
 
	
	private String cardNo; //����
	private String expire; //��Ч��
	private String cardStat; //��״̬
	private String masterCardNo; //Master����
	private String masterStat; //MAster��״̬
	private String attachFlag; //������־
	private String acctNo; //���ʺ�
	private int depositCnt; //������
	private double depositAmt; //�����
	private double cardAmt; //���ý��
	private int authCnt; //��������Ȩ����
	private double authAmt; //��������Ȩ���
	private double authTotal; //����Ȩ���
	private double balance; //�ʻ����
	private String owner; //�ֿ���
	private String personId; //���֤
	private String address; //��ϵ��ַ
	private String job; //������λ
	private String telephone; //��ϵ�绰
	private String updateOper; //�Ǽǹ�Ա
	private String updateDate; //ά������
	private String updateTime; //ά��ʱ��

	/**�����-ҳ����ʾ��*/
	private String ch_depositAmt;
	/**���ý��--ҳ����ʾ��*/
	private String ch_cardAmt;
	/**��������Ȩ���--ҳ����ʾ��*/
	private String ch_authAmt;
	/**����Ȩ���--ҳ����ʾ��*/
	private String ch_authTotal;
	/**�ʻ����--ҳ����ʾ��*/
	private String ch_balance; 
	
	/**��״̬--ҳ����ʾ��*/
	private String ch_cardStat;
	
	
	/**��double����ת��Ϊ�ַ���*/
	public static String Conversion(Double num){
		BigDecimal big = new BigDecimal(num);
		return String.valueOf(big.setScale(2, 2));
	}
	
	// Constructors

	public String getCh_depositAmt() {
		return ch_depositAmt;
	}

	public void setCh_depositAmt(Double num) {
		ch_depositAmt = Conversion(num);
	}

	public String getCh_cardAmt() {
		return ch_cardAmt;
	}

	public void setCh_cardAmt(Double num) {
		ch_cardAmt =Conversion(num);
	}

	public String getCh_authAmt() {
		return ch_authAmt;
	}

	public void setCh_authAmt(Double num) {
		ch_authAmt = Conversion(num);
	}

	public String getCh_authTotal() {
		return ch_authTotal;
	}

	public void setCh_authTotal(Double num) {
		ch_authTotal = Conversion(num);
	}

	public String getCh_balance() {
		return ch_balance;
	}

	public void setCh_balance(Double num) {
		ch_balance = Conversion(num);
	}

	public String getCh_cardStat() {
		return ch_cardStat;
	}

	public void setCh_cardStat(String chCardStat) {
		if(chCardStat!=null&&chCardStat!=""){
			if(chCardStat.equals("0"))
				this.ch_cardStat="����";
			if(chCardStat.equals("1"))
				this.ch_cardStat="����";
			if(chCardStat.equals("2"))
				this.ch_cardStat="ֹ��";
		}else
		ch_cardStat = chCardStat;
	}

	/** default constructor */
	public CardAcct() {
	}

	/** full constructor */
	public CardAcct(String cardNo, String expire, String cardStat,
			String masterCardNo, String masterStat, String attachFlag,
			String acctNo, int depositCnt, double depositAmt,
			double cardAmt, int authCnt, double authAmt,
			double authTotal, double balance, String owner, String personId,
			String address, String job, String telephone, String updateOper,
			String updateDate, String updateTime) {
		this.cardNo = cardNo;
		this.expire = expire;
		this.cardStat = cardStat;
		this.masterCardNo = masterCardNo;
		this.masterStat = masterStat;
		this.attachFlag = attachFlag;
		this.acctNo = acctNo;
		this.depositCnt = depositCnt;
		this.depositAmt = depositAmt;
		this.cardAmt = cardAmt;
		this.authCnt = authCnt;
		this.authAmt = authAmt;
		this.authTotal = authTotal;
		this.balance = balance;
		this.owner = owner;
		this.personId = personId;
		this.address = address;
		this.job = job;
		this.telephone = telephone;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	// Property accessors

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getExpire() {
		return this.expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}

	public String getCardStat() {
		return this.cardStat;
	}

	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
		setCh_cardStat(cardStat);
	}

	public String getMasterCardNo() {
		return this.masterCardNo;
	}

	public void setMasterCardNo(String masterCardNo) {
		this.masterCardNo = masterCardNo;
	}

	public String getMasterStat() {
		return this.masterStat;
	}

	public void setMasterStat(String masterStat) {
		this.masterStat = masterStat;
	}

	public String getAttachFlag() {
		return this.attachFlag;
	}

	public void setAttachFlag(String attachFlag) {
		this.attachFlag = attachFlag;
	}

	public String getAcctNo() {
		return this.acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public int getDepositCnt() {
		return this.depositCnt;
	}

	public void setDepositCnt(int depositCnt) {
		this.depositCnt = depositCnt;
	}

	public double getDepositAmt() {
		return this.depositAmt;
	}

	public void setDepositAmt(double depositAmt) {
		this.depositAmt = depositAmt;
		setCh_depositAmt(depositAmt);
	}

	public double getCardAmt() {
		return this.cardAmt;
	}

	public void setCardAmt(double cardAmt) {
		this.cardAmt = cardAmt;
		setCh_cardAmt(cardAmt);
	}

	public int getAuthCnt() {
		return this.authCnt;
	}

	public void setAuthCnt(int authCnt) {
		this.authCnt = authCnt;
	}

	public double getAuthAmt() {
		return this.authAmt;
	}

	public void setAuthAmt(double authAmt) {
		this.authAmt = authAmt;
		setCh_authAmt(authAmt);
	}

	public double getAuthTotal() {
		return this.authTotal;
	}

	public void setAuthTotal(double authTotal) {
		this.authTotal = authTotal;
		setCh_authTotal(authTotal);
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
		setCh_balance(balance);
	}

	public String getOwner() {
		return this.owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJob() {
		return this.job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUpdateOper() {
		return this.updateOper;
	}

	public void setUpdateOper(String updateOper) {
		this.updateOper = updateOper;
	}

	public String getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}