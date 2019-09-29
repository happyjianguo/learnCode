/**
 * DefaultAccount.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年1月9日
 */
package cn.com.jansh.entity.wechat;

/**
 * 默认公众号Entity
 * 
 * @author gll
 * @version 1.0
 */
public class DefaultAccount {

	/*公众号appid*/
	private String appid;
	/*用户id*/
	private String userid;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "DefaultAccount [appid=" + appid + ", userid=" + userid + "]";
	}
}
