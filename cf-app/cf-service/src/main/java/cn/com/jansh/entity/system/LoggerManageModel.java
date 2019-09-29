package cn.com.jansh.entity.system;

import java.util.List;

public class LoggerManageModel {
	private String userId;//用户id
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private int count;//总页数
	private int start;
	private int length;
	private List<OperLog> loglist;
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
	public List<OperLog> getLoglist() {
		return loglist;
	}
	public void setLoglist(List<OperLog> loglist) {
		this.loglist = loglist;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public String toString() {
		return "loggerManageModel [beginTime=" + beginTime + ", endTime=" + endTime + ", count=" + count + ", start="
				+ start + ", length=" + length + ", loglist=" + loglist + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
