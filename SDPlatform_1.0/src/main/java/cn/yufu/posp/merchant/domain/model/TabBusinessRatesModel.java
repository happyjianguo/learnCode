package cn.yufu.posp.merchant.domain.model;


/**
 * TabBusinessRatesModel entity. @author MyEclipse Persistence Tools
 */

public class TabBusinessRatesModel implements java.io.Serializable {

	// Fields
	private String businessid;
	private String businessname;
	private String rate;
	/**ҵ������������ϸ����*/
	private String businessNameDetail;
	/**���¹�Ա*/
	private String updateOper;
	/**��������*/
	private String updateDate;
	/**����ʱ��*/
	private String updateTime;
	
	public String getBusinessid() {
		return businessid;
	}
	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	public String getBusinessname() {
		return businessname;
	}
	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getBusinessNameDetail() {
		return businessNameDetail;
	}
	public void setBusinessNameDetail(String businessNameDetail) {
		this.businessNameDetail = businessNameDetail;
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