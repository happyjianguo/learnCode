package cn.yufu.posp.merchant.domain.model;


/**
 * TabBusinessRatesModel entity. @author MyEclipse Persistence Tools
 */

public class TabBusinessRatesModel implements java.io.Serializable {

	// Fields
	private String businessid;
	private String businessname;
	private String rate;
	/**业务中文名称详细描述*/
	private String businessNameDetail;
	/**更新柜员*/
	private String updateOper;
	/**更新日期*/
	private String updateDate;
	/**更新时间*/
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