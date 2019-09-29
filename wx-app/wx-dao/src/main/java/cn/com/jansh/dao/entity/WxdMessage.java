package cn.com.jansh.dao.entity;

public class WxdMessage {

	private String msgid;
	private String appid;
	private String msgname;
	private String mediaid;
	private String msgtype;
	private String content;
	private String updatetime;
	private String createtime;
	private String trans;
	public String getMsgid() {
		return msgid;
	}
	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}
	public String getMsgname() {
		return msgname;
	}
	public void setMsgname(String msgname) {
		this.msgname = msgname;
	}
	public String getMediaid() {
		return mediaid;
	}
	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getTrans() {
		return trans;
	}
	public void setTrans(String trans) {
		this.trans = trans;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@Override
	public String toString() {
		return "WxdMessage [msgid=" + msgid + ", appid=" + appid + ", msgname=" + msgname + ", mediaid="
				+ mediaid + ", msgtype=" + msgtype + ", content=" + content + ", updatetime=" + updatetime
				+ ", createtime=" + createtime + ", trans=" + trans + "]";
	}
	
	
}
