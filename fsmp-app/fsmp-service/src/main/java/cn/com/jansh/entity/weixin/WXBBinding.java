package cn.com.jansh.entity.weixin;
/**
 * 卡号绑定
 * @author panc
 *
 */
public class WXBBinding {
	private Integer autoId; //记录编号
	private String openId; //用户OpenId
	private String cardType; //卡类型
	private String cardNum;//卡号
	private String bindStatus;//绑定状态
	private String noticeStatus;//动帐通知是否开通
	private String updateTime;//更新时间
	private String createTime;//创建时间
	public Integer getAutoId() {
		return autoId;
	}
	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getBindStatus() {
		return bindStatus;
	}
	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}
	public String getNoticeStatus() {
		return noticeStatus;
	}
	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
}
