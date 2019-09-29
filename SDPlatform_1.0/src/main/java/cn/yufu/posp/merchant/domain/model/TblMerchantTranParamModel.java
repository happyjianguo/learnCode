package cn.yufu.posp.merchant.domain.model;

import java.math.BigDecimal;


public class TblMerchantTranParamModel implements java.io.Serializable{

	// Fields
	private String merchantId;
	private String merchantName;
	private BigDecimal nopasswdMaxamt;
	private String waveFlag;
	private String swipeFlag;
	private String tranBitmap;
	private String updateOper;
	private String updateDate;
	private String updateTime;
	private String flag;
	private String scanFlag;//扫码标识：1--支持；0--不支持
	
	public String getScanFlag() {
		return scanFlag;
	}
	public void setScanFlag(String scanFlag) {
		this.scanFlag = scanFlag;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public BigDecimal getNopasswdMaxamt() {
		return nopasswdMaxamt;
	}
	public void setNopasswdMaxamt(BigDecimal nopasswdMaxamt) {
		this.nopasswdMaxamt = nopasswdMaxamt;
	}
	public String getWaveFlag() {
		return waveFlag;
	}
	public void setWaveFlag(String waveFlag) {
		this.waveFlag = waveFlag;
	}
	public String getSwipeFlag() {
		return swipeFlag;
	}
	public void setSwipeFlag(String swipeFlag) {
		this.swipeFlag = swipeFlag;
	}
	public String getTranBitmap() {
		return tranBitmap;
	}
	public void setTranBitmap(String tranBitmap) {
		this.tranBitmap = tranBitmap;
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
}