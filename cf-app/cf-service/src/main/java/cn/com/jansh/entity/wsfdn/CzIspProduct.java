package cn.com.jansh.entity.wsfdn;

public class CzIspProduct {
	
	private String ispproid;
	private String ispno;
	private String ispname;
	private String productno;
	private String cpordernos;
	private String remark;
	
	public String getIspproid() {
		return ispproid;
	}
	public void setIspproid(String ispproid) {
		this.ispproid = ispproid;
	}
	public String getIspno() {
		return ispno;
	}
	public void setIspno(String ispno) {
		this.ispno = ispno;
	}
	public String getIspname() {
		return ispname;
	}
	public void setIspname(String ispname) {
		this.ispname = ispname;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public String getCpordernos() {
		return cpordernos;
	}
	public void setCpordernos(String cpordernos) {
		this.cpordernos = cpordernos;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "CzIspProduct [ispproid=" + ispproid + ", ispno=" + ispno + ", ispname=" + ispname + ", productno="
				+ productno + ", cpordernos=" + cpordernos + ", remark=" + remark + "]";
	}
	
}
