package cn.com.jansh.entity.weixin;
/**
 * 微信自动回复消息
 * @author panc
 *
 */
public class WXDResMessage {
	private String resmsgId; // 智能回复id
	private String resType; // 智能回复类型
	private String resMsgname; // 智能回复消息名称
	private String resMsgKey; // 请求关键字
	private String msgType; // 应答类型
	private String msgId; // 应答素材ID
	private String msgName;//应答素材名
	private String createTime; // 创建时间
	private String updateTime;// 更新时间
	private String appid;// 平台ID
	

	public String getMsgName() {
		return msgName;
	}

	public void setMsgName(String msgName) {
		this.msgName = msgName;
	}

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

	public String getResMsgname() {
		return resMsgname;
	}

	public void setResMsgname(String resMsgname) {
		this.resMsgname = resMsgname;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "WXDResMessage [resmsgId=" + resmsgId + ", resType=" + resType + ", resMsgname=" + resMsgname
				+ ", resMsgKey=" + resMsgKey + ", msgType=" + msgType + ", msgId=" + msgId + ", msgName=" + msgName
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + ", appid=" + appid + "]";
	}
}
