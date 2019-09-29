package cn.com.jansh.dao.entity;

public class WxbUser {

	private String openid;
    private String appid;
    private String nickname;
    private String sex;
    private String city;
    private String country;
    private String province;
    private String language;
    private String headimgurl;
    private String unionid;
    private String remark;
    private String groupid;
    private String subScribeTime;
    private String subscripttype;
    private String updatetime;
    private String createtime;
    
    public String getSubScribeTime() {
		return subScribeTime;
	}

	public void setSubScribeTime(String subScribeTime) {
		this.subScribeTime = subScribeTime;
	}

	public WxbUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxbUser(String openid, String nickname, String country, String province, String city, String sex,
			String groupid, String remark, String language, String headimgurl,String unionid,String subscripttype) {
		super();
		this.openid = openid;
		this.nickname = nickname;
		this.country = country;
		this.province = province;
		this.city = city;
		this.sex = sex;
		this.groupid = groupid;
		this.remark = remark;
		this.language = language;
		this.headimgurl = headimgurl;
		this.unionid = unionid;
		this.subscripttype = subscripttype;
	}
	
    
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getSubscripttype() {
		return subscripttype;
	}
	public void setSubscripttype(String subscripttype) {
		this.subscripttype = subscripttype;
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

	@Override
	public String toString() {
		return "WxbUser [openid=" + openid + ", appid=" + appid + ", nickname=" + nickname + ", sex=" + sex
				+ ", city=" + city + ", country=" + country + ", province=" + province + ", language=" + language
				+ ", headimgurl=" + headimgurl + ", unionid=" + unionid + ", remark=" + remark + ", groupid=" + groupid
				+ ", subscripttype=" + subscripttype + ", updatetime=" + updatetime + ", createtime=" + createtime
				+ "]";
	}
    
}