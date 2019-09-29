package cn.com.jansh.entity.wsfdn;

import java.util.List;

public class CzCurrBusiLogModel {
	
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private int count;//总页数
	private int page;
	private int pagesize;
	
	private String sysid;
	private String productno;
	private String bizstatus;
	
	private List<CzCurrBusiLog> loglist;
	
	public List<CzCurrBusiLog> getLoglist() {
		return loglist;
	}
	public void setLoglist(List<CzCurrBusiLog> loglist) {
		this.loglist = loglist;
	}
	public String getSysid() {
		return sysid;
	}
	public void setSysid(String sysid) {
		this.sysid = sysid;
	}
	public String getProductno() {
		return productno;
	}
	public void setProductno(String productno) {
		this.productno = productno;
	}
	public String getBizstatus() {
		return bizstatus;
	}
	public void setBizstatus(String bizstatus) {
		this.bizstatus = bizstatus;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CzCurrBusiLogModel [beginTime=" + beginTime + ", endTime=" + endTime + ", count=" + count + ", page="
				+ page + ", pagesize=" + pagesize + ", sysid=" + sysid + ", productno=" + productno + ", bizstatus="
				+ bizstatus + ", loglist=" + loglist + "]";
	}
}
