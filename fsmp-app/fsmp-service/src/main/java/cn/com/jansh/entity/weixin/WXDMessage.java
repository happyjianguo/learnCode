package cn.com.jansh.entity.weixin;

/**
 * 微信消息表
 * 
 * @author panc
 *
 */
public class WXDMessage {
	private String msgId;
	private String msgName;
	private String mediaId;
	private String mediaName;
	private String msgType;
	private String appid;
	private String content;
	private String createTime;
	private String updateTime;
	@Override
	public String toString() {
		return "WXDMessage [msgId=" + msgId + ", msgName=" + msgName + ", mediaId=" + mediaId + ", mediaName="
				+ mediaName + ", msgType=" + msgType + ", appid=" + appid + ", content=" + content + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
