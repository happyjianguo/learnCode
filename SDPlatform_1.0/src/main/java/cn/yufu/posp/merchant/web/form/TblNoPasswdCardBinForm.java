package cn.yufu.posp.merchant.web.form;
import java.math.BigDecimal;

import cn.yufu.core.web.form.BaseForm;

public class TblNoPasswdCardBinForm extends BaseForm{

	// Fields
	private String firstCardBin;
	private String lastCardBin;
	private String nopasswdMaxamt;
	private String flag;
	private String dataLength;
	private String cardBinInfo;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	public String getFirstCardBin() {
		return firstCardBin;
	}
	public void setFirstCardBin(String firstCardBin) {
		this.firstCardBin = firstCardBin;
	}
	public String getLastCardBin() {
		return lastCardBin;
	}
	public void setLastCardBin(String lastCardBin) {
		this.lastCardBin = lastCardBin;
	}
	public String getNopasswdMaxamt() {
		return nopasswdMaxamt;
	}
	public void setNopasswdMaxamt(String nopasswdMaxamt) {
		this.nopasswdMaxamt = nopasswdMaxamt;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	public String getDataLength() {
		return dataLength;
	}
	public void setDataLength(String dataLength) {
		this.dataLength = dataLength;
	}
	
	public String getCardBinInfo() {
		return cardBinInfo;
	}
	public void setCardBinInfo(String cardBinInfo) {
		this.cardBinInfo = cardBinInfo;
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
	
}