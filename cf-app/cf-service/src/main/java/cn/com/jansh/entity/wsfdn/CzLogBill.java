package cn.com.jansh.entity.wsfdn;

public class CzLogBill {

	private String bizid;
	private String phone;
	private String ispno;
	private String productno;
	private String orderid;
	private String responsecode;
	private String resultcode;
	private String cporderno;
	private String sysid;
	private String systransno;
	private String createtime;
	private String updatetime;
	private String bizstatus;
	private String sysname;
	private String ordername;
	private String status;

	public String getBizid() {
		return bizid;
	}

	public void setBizid(String bizid) {
		this.bizid = bizid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIspno() {
		return ispno;
	}

	public void setIspno(String ispno) {
		this.ispno = ispno;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
		this.productno = productno;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getResponsecode() {
		return responsecode;
	}

	public void setResponsecode(String responsecode) {
		this.responsecode = responsecode;
	}

	public String getResultcode() {
		return resultcode;
	}

	public void setResultcode(String resultcode) {
		this.resultcode = resultcode;
	}

	public String getCporderno() {
		return cporderno;
	}

	public void setCporderno(String cporderno) {
		this.cporderno = cporderno;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getSystransno() {
		return systransno;
	}

	public void setSystransno(String systransno) {
		this.systransno = systransno;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getBizstatus() {
		return bizstatus;
	}

	public void setBizstatus(String bizstatus) {
		this.bizstatus = bizstatus;
	}

	public String getSysname() {
		return sysname;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public String getOrdername() {
		return ordername;
	}

	public void setOrdername(String ordername) {
		this.ordername = ordername;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "CzLogBill [bizid=" + bizid + ", phone=" + phone + ", ispno=" + ispno + ", productno=" + productno
				+ ", orderid=" + orderid + ", responsecode=" + responsecode + ", resultcode=" + resultcode
				+ ", cporderno=" + cporderno + ", sysid=" + sysid + ", systransno=" + systransno + ", createtime="
				+ createtime + ", updatetime=" + updatetime + ", bizstatus=" + bizstatus + ", sysname=" + sysname
				+ ", ordername=" + ordername + ", status=" + status + "]";
	}
}
