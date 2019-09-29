package cn.com.jansh.model;

import java.util.List;

public class loggerManageModel {
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private int count;//总页数
	private int page;
	private int pagesize;
	private List<LoggerModel> loglist;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
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
	public List<LoggerModel> getLoglist() {
		return loglist;
	}
	public void setLoglist(List<LoggerModel> loglist) {
		this.loglist = loglist;
	}

}
