package cn.com.jansh.model;
/**
 * 
 * @author panc
 *日志操作model
 */
public class LoggerModel {
private String logId;//日志Id
private String userId;//用户id
private String userName;//用户名
private String cName;//用户角色
private String transName;//操作名
private String transTime;//操作时间
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
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getcName() {
	return cName;
}
public void setcName(String cName) {
	this.cName = cName;
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
}
