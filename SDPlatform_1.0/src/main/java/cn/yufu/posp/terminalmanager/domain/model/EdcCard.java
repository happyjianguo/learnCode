package cn.yufu.posp.terminalmanager.domain.model;

/**
 * EdcCard entity. @author MyEclipse Persistence Tools
 */

public class EdcCard implements java.io.Serializable {

	// Fields

	private EdcCardId id;

	/**����״̬*/
	private String cardStat;
	

	/**ά����Ա*/
	private String updateOper;
	
	/**��������  YYYYMMDD*/
	private String updateDate;
	
	/**����ʱ��  hhmmss*/
	private String updateTime;
	
	
	
	/**����״̬--ҳ����ʾ��*/
	private String ch_cardStat;
	
	/**��ǰ��ʱ�����  ---ҳ����ʾ��---*/
    private String ch_dateAndTime;
    
    
	
	// Constructors

    public String getCh_cardStat() {
		return ch_cardStat;
	}

	public void setCh_cardStat(String chCardStat) {
		if(chCardStat!=null){
			if("Y".equals(chCardStat))
				ch_cardStat="����";
			if("N".equals(chCardStat))
				ch_cardStat="����";
		}else
		   ch_cardStat = chCardStat;
	}
	public String getCh_dateAndTime() {
		return ch_dateAndTime;
	}

	public void setCh_dateAndTime(String chDateAndTime,int tag) {
		if(chDateAndTime!=null){
			if(tag==1){//tag==1 ʱ Ϊ��ǰ
				if(ch_dateAndTime!=null)
				    ch_dateAndTime=chDateAndTime+" "+ch_dateAndTime;
				else
					ch_dateAndTime=chDateAndTime+" ";
			}
			if(tag==2){//tag==2 ʱ Ϊʱ��
				if(ch_dateAndTime!=null)
				    ch_dateAndTime=ch_dateAndTime+chDateAndTime;
				else
					ch_dateAndTime=chDateAndTime;
			}
		}else
			 ch_dateAndTime=null;
	}
	public String getCardStat() {
		return cardStat;
	}

	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
		setCh_cardStat(cardStat);
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
		setCh_dateAndTime(updateDate,1);
	}

	public String getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
		setCh_dateAndTime(updateTime,2);
	}
	
	/** default constructor */
	public EdcCard() {
	}

	/** full constructor */
	

	// Property accessors

	public EdcCardId getId() {
		return this.id;
	}

	public EdcCard(EdcCardId id, String cardStat, String updateOper,
			String updateDate, String updateTime) {
		super();
		this.id = id;
		this.cardStat = cardStat;
		this.updateOper = updateOper;
		this.updateDate = updateDate;
		this.updateTime = updateTime;
	}

	public void setId(EdcCardId id) {
		this.id = id;
	}

}