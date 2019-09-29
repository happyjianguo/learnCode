package cn.com.jansh.dao.entity;

public class WXDNetStation {

	private String netId; //网点编号
	private String netName; //网点名称
	private String parentNetId; //父网点编号
	private String netStatus; //网点类型
	private String netAddress; //网点地址
	private String phoneNo; //联系电话
	private String longitude; //经度
	private String latitude; //纬度
	private String descript; //描述

	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	public String getParentNetId() {
		return parentNetId;
	}
	public void setParentNetId(String parentNetId) {
		this.parentNetId = parentNetId;
	}
	public String getNetStatus() {
		return netStatus;
	}
	public void setNetStatus(String netStatus) {
		this.netStatus = netStatus;
	}
	public String getNetAddress() {
		return netAddress;
	}
	public void setNetAddress(String netAddress) {
		this.netAddress = netAddress;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
}
