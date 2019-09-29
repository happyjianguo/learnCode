package cn.com.jansh.entity.wsfdn;

public class CzBill {
	
	private String iphone;
	private String orderid;
	private String bizstatus;
	
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getBizstatus() {
		return bizstatus;
	}
	public void setBizstatus(String bizstatus) {
		this.bizstatus = bizstatus;
	}
	@Override
	public String toString() {
		return "CzBill [iphone=" + iphone + ", orderid=" + orderid + ", bizstatus=" + bizstatus + "]";
	}
}
