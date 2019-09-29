package cn.yufu.posp.terminalmanager.web.form;

import cn.yufu.posp.terminalmanager.domain.model.EdcCardId;
import cn.yufu.core.web.form.BaseForm;

public class EdcCardForm extends BaseForm {
	private EdcCardId id=new EdcCardId();

	/**受理卡状态*/
	private String cardStat;
	

	/**维护柜员*/
	private String updateOper;
	
	/**创建日期  YYYYMMDD*/
	private String updateDate;
	
	/**创建时间  hhmmss*/
	private String updateTime;
	
	
	
	/**受理卡状态--页面显示用*/
	private String ch_cardStat;
	
	/**日前和时间组合  ---页面显示用---*/
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
