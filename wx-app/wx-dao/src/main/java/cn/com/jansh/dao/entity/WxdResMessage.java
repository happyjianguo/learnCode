package cn.com.jansh.dao.entity;

public class WxdResMessage {

	private String resmsgId;
	private String resType;
	private String resMsgName;
	private String resMsgKey;
	private String msgType;
	private String msgid;
	private String appid;
	private String upadteTime;
	
	public String getResmsgId() {
		return resmsgId;
	}
	public void setResmsgId(String resmsgId) {
		this.resmsgId = resmsgId;
	}
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getResMsgName() {
		return resMsgName;
	}
	public void setResMsgName(String resMsgName) {
		this.resMsgName = resMsgName;
	}
	public String getResMsgKey() {
		return resMsgKey;
	}
	public void setResMsgKey(String resMsgKey) {
		this.resMsgKey = resMsgKey;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getUpadteTime() {
		return upadteTime;
	}
	public void setUpadteTime(String upadteTime) {
		this.upadteTime = upadteTime;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@Override
	public String toString() {
		return "WxdResMessage [resmsgId=" + resmsgId + ", resType=" + resType + ", resMsgName=" + resMsgName
				+ ", resMsgKey=" + resMsgKey + ", msgType=" + msgType + ", msgid=" + msgid + ", appid="
				+ appid + ", upadteTime=" + upadteTime + "]";
	}
	
	
}
