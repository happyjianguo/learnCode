package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.core.web.form.BaseForm;

public class CardAcctForm extends BaseForm {
	
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

	/**��״̬--ҳ����ʾ��*/
	private String ch_cardStat;

	
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
