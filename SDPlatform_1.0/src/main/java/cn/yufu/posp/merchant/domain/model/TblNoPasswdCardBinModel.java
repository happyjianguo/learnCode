package cn.yufu.posp.merchant.domain.model;

import java.math.BigDecimal;


public class TblNoPasswdCardBinModel implements java.io.Serializable{

	// Fields
	private String firstCardBin;
	private String lastCardBin;
	private BigDecimal nopasswdMaxamt;
	private String flag;
	private String dataLength;
	private String cardBinInfo;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	
	//ø®BIN–≈œ¢≤È—Ø
	private String queryCardBin;
	
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
	public BigDecimal getNopasswdMaxamt() {
		return nopasswdMaxamt;
	}
	public void setNopasswdMaxamt(BigDecimal nopasswdMaxamt) {
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
	public String getQueryCardBin() {
		return queryCardBin;
	}
	public void setQueryCardBin(String queryCardBin) {
		this.queryCardBin = queryCardBin;
	}
}