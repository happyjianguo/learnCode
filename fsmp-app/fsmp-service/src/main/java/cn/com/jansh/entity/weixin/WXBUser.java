package cn.com.jansh.entity.weixin;

/**
 * 微信平台用户表
 * 
 * @author panc
 *
 */
public class WXBUser {
	private String appid;
	private String openId;
	private String nickName;
	private String country;
	private String province;
	private String city;
	private String sex;
	private String groupId;
	private String remark;
	private String language;
	private String headimgUrl;
	private String unionId;
	private String subScribeTime;
	private String subScriptType;
	private String updateTime;
	private String createTime;
	private String address;
	private String groupName;
	private String bindStatus;
	private String noticeStatus;

	@Override
	public String toString() {
		return "WXBUser [appid=" + appid + ", openId=" + openId + ", nickName=" + nickName + ", country=" + country
				+ ", province=" + province + ", city=" + city + ", sex=" + sex + ", groupId=" + groupId + ", remark="
				+ remark + ", language=" + language + ", headimgUrl=" + headimgUrl + ", unionId=" + unionId
				+ ", subScribeTime=" + subScribeTime + ", subScriptType=" + subScriptType + ", updateTime=" + updateTime
				+ ", createTime=" + createTime + ", address=" + address + ", groupName=" + groupName + ", bindStatus="
				+ bindStatus + ", noticeStatus=" + noticeStatus + "]";
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getSubScriptType() {
		return subScriptType;
	}

	public void setSubScriptType(String subScriptType) {
		this.subScriptType = subScriptType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getSubScribeTime() {
		return subScribeTime;
	}

	public void setSubScribeTime(String subScribeTime) {
		this.subScribeTime = subScribeTime;
	}

	public String getAddress() {
		this.address = this.getCountry() + this.getProvince() + this.getCity();
		return address;
	}

	public void setAddress() {
		this.address = this.country + this.province + this.city;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

}
