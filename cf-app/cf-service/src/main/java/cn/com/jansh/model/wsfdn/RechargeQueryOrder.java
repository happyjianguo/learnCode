package cn.com.jansh.model.wsfdn;

public class RechargeQueryOrder {
	
	//接入者订单号对应单笔充值表id
	private String cporder;
	
	//接入者id
	private String sysid;
	
	//平台订单号对应流水号
	private String sporder;
	
	//手机号
	private String phone;
	
	//订单时间
	private String ordertime;
	
	//价格
	private String apprice;
	
	//充值类型
	private String ipstype;
	
	public String getIpstype() {
		return ipstype;
	}
	public void setIpstype(String ipstype) {
		this.ipstype = ipstype;
	}
	public String getApprice() {
		return apprice;
	}
	public void setApprice(String apprice) {
		this.apprice = apprice;
	}
	private String status;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSporder() {
		return sporder;
	}
	public void setSporder(String sporder) {
		this.sporder = sporder;
	}
	public String getCporder() {
		return cporder;
	}
	public void setCporder(String cporder) {
		this.cporder = cporder;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
}
