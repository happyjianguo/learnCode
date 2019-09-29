/**
 *包名:com.pay.batch.tlog.struts.form
 *描述:package com.pay.batch.tlog.struts.form;
 */
package com.pay.batch.tlog.struts.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

/**
 * SinopecSweepCardUploadForm.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年5月16日
 * 描述:中石化扫码刷卡统计
 */
public class SinopecSweepCardUploadForm extends ValidatorForm{
	private FormFile upload ;
	private String tradingType ; //交易类型 ;
	private String tradingNumber ; //交易笔数 ;
	private String tradingAmt ; //交易金额 ;
	/*Excel查询条件*/
	private String merchant_no ; //商户号 ;
	private String termial_no ; //终端号 ;
	private String starttime ; //开始时间 ;
	private String endtime ; //结束时间 ;
	

	@Override
	public String toString() {
		return "SinopecSweepCardUploadForm [upload=" + upload + ", tradingType=" + tradingType + ", tradingNumber="
				+ tradingNumber + ", tradingAmt=" + tradingAmt + ", merchant_no=" + merchant_no + ", termial_no="
				+ termial_no + ", starttime=" + starttime + ", endtime=" + endtime + "]";
	}

	public String getMerchant_no() {
		return merchant_no;
	}

	public void setMerchant_no(String merchant_no) {
		this.merchant_no = merchant_no;
	}

	public String getTermial_no() {
		return termial_no;
	}

	public void setTermial_no(String termial_no) {
		this.termial_no = termial_no;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTradingType() {
		return tradingType;
	}

	public void setTradingType(String tradingType) {
		this.tradingType = tradingType;
	}

	public String getTradingNumber() {
		return tradingNumber;
	}

	public void setTradingNumber(String tradingNumber) {
		this.tradingNumber = tradingNumber;
	}

	public String getTradingAmt() {
		return tradingAmt;
	}

	public void setTradingAmt(String tradingAmt) {
		this.tradingAmt = tradingAmt;
	}

	public FormFile getUpload() {
		return upload;
	}

	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
}
