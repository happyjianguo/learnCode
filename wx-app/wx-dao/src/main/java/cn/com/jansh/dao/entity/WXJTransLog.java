package cn.com.jansh.dao.entity;

public class WXJTransLog {
	
    private String logid;
    private String appid;
    private String fromusername;
    private String creagetime;
    private String msgtype;
    private String event;
    private String eventkey;
    private String textcontext;
    
	public String getLogid() {
		return logid;
	}
	public void setLogid(String logid) {
		this.logid = logid;
	}
	public String getAppid() {
		return appid;
	}
	public void setPlatformid(String appid) {
		this.appid = appid;
	}
	public String getFromusername() {
		return fromusername;
	}
	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}
	public String getCreagetime() {
		return creagetime;
	}
	public void setCreagetime(String creagetime) {
		this.creagetime = creagetime;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventkey() {
		return eventkey;
	}
	public void setEventkey(String eventkey) {
		this.eventkey = eventkey;
	}
	public String getTextcontext() {
		return textcontext;
	}
	public void setTextcontext(String textcontext) {
		this.textcontext = textcontext;
	}
	@Override
	public String toString() {
		return "WXTranslog [logid=" + logid + ", appid=" + appid + ", fromusername=" + fromusername
				+ ", creagetime=" + creagetime + ", msgtype=" + msgtype + ", event=" + event + ", eventkey=" + eventkey
				+ ", textcontext=" + textcontext + "]";
	}
    
    
}
