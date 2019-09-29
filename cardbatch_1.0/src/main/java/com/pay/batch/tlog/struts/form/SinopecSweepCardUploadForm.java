/**
 *����:com.pay.batch.tlog.struts.form
 *����:package com.pay.batch.tlog.struts.form;
 */
package com.pay.batch.tlog.struts.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

/**
 * SinopecSweepCardUploadForm.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��5��16��
 * ����:��ʯ��ɨ��ˢ��ͳ��
 */
public class SinopecSweepCardUploadForm extends ValidatorForm{
	private FormFile upload ;
	private String tradingType ; //�������� ;
	private String tradingNumber ; //���ױ��� ;
	private String tradingAmt ; //���׽�� ;
	/*Excel��ѯ����*/
	private String merchant_no ; //�̻��� ;
	private String termial_no ; //�ն˺� ;
	private String starttime ; //��ʼʱ�� ;
	private String endtime ; //����ʱ�� ;
	

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
