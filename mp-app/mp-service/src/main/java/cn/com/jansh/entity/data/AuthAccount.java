/**
 * AuthorizationAccountInfo.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年10月18日
 */
package cn.com.jansh.entity.data;

/**
 * 授权公众号实体
 * 
 * @author Mr.wong
 * @version 1.0
 */
public class AuthAccount {

	// 机构id
	private String orgid;
	// 公众号id
	private String appid;
	// 接口调用权限
	private String auth_access_token;
	// 接口刷新权限
	private String auth_refresh_token;
	// 授权码
	private String auth_code;
	// 创建时间
	private String createtime;
	// 更新时间
	private String updatetime;
	// 状态
	private String status;

	// 公众号昵称
	private String nickName;
	// 头像
	private String headImg;
	// 公众号类型
	private String serviceType;
	// 授权方式
	private String verifyType;
	// 公众号原始号
	private String userName;
	// 授权方公众号所设置的微信号
	private String alias;
	// 二维码图片
	private String qrcodeUrl;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

	public AuthAccount() {
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAuth_access_token() {
		return auth_access_token;
	}

	public void setAuth_access_token(String auth_access_token) {
		this.auth_access_token = auth_access_token;
	}

	public String getAuth_refresh_token() {
		return auth_refresh_token;
	}

	public void setAuth_refresh_token(String auth_refresh_token) {
		this.auth_refresh_token = auth_refresh_token;
	}

	public String getAuth_code() {
		return auth_code;
	}

	public void setAuth_code(String auth_code) {
		this.auth_code = auth_code;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AuthAccount [orgid=" + orgid + ", appid=" + appid + ", auth_access_token=" + auth_access_token
				+ ", auth_refresh_token=" + auth_refresh_token + ", auth_code=" + auth_code + ", createtime="
				+ createtime + ", updatetime=" + updatetime + ", status=" + status + ", nickName=" + nickName
				+ ", headImg=" + headImg + ", serviceType=" + serviceType + ", verifyType=" + verifyType + ", userName="
				+ userName + ", alias=" + alias + ", qrcodeUrl=" + qrcodeUrl + "]";
	}

}
