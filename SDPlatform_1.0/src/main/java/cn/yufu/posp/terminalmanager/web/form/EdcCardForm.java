package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.posp.terminalmanager.domain.model.EdcCardId;
import cn.yufu.core.web.form.BaseForm;

public class EdcCardForm extends BaseForm {
	private EdcCardId id=new EdcCardId();

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
    
	public String getCardStat() {
		return cardStat;
	}

	public void setCardStat(String cardStat) {
		this.cardStat = cardStat;
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

	public String getCh_dateAndTime() {
		return ch_dateAndTime;
	}

	public void setCh_dateAndTime(String chDateAndTime) {
		ch_dateAndTime = chDateAndTime;
	}

	public EdcCardId getId() {
		return id;
	}

	public void setId(EdcCardId id) {
		this.id = id;
	}
	
}
