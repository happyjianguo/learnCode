package com.jansh.core.entity.sys;

public class IMUserLog {
	
	//日志Id
	private String logId;
	//用户Id
	private String userId;
	//交易名称
	private String transName;
	//交易时间
	private String transTime;
	//交易发起方IP地址
	private String remoteIP;
	//状态
	private String status;
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public String getTransTime() {
		return transTime;
	}
	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}
	public String getRemoteIP() {
		return remoteIP;
	}
	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "IMUserLog [logId=" + logId + ", userId=" + userId + ", transName=" + transName + ", transTime="
				+ transTime + ", remoteIP=" + remoteIP + ", status=" + status + "]";
	}
	
	
}
