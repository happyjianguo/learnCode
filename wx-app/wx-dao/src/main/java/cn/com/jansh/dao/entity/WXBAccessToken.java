package cn.com.jansh.dao.entity;

/**
 * 微信AccessToken表
 * 
 *
 */
public class WXBAccessToken {
	private String appid; // 微信APPID
	private String accessToken; // 微信公众号AccessToken
	private String atUpdateTime; // 更新时间
	private String ticket; // 微信公众号AccessToken
	private String ttUpdateTime; // 更新时间

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAtUpdateTime() {
		return atUpdateTime;
	}

	public void setAtUpdateTime(String atUpdateTime) {
		this.atUpdateTime = atUpdateTime;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getTtUpdateTime() {
		return ttUpdateTime;
	}

	public void setTtUpdateTime(String ttUpdateTime) {
		this.ttUpdateTime = ttUpdateTime;
	}

	@Override
	public String toString() {
		return "WXBAccessTokenModel [appid=" + appid + ", accessToken=" + accessToken + ", atUpdateTime=" + atUpdateTime + ", ticket=" + ticket
				+ ", ttUpdateTime=" + ttUpdateTime + "]";
	}
}
