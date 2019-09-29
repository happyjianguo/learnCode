package cn.com.jansh.model.wechat;

import java.util.List;

import cn.com.jansh.entity.wechat.AuthAccount;

/**
 * 前台展示订单信息model
 * @author Mr.wong
 * 
 */
public class WxManageModel {
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private int count;//总页数
	private int start;
	private int length;
	private List<AuthAccount> authAccounts;
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public List<AuthAccount> getAuthAccounts() {
		return authAccounts;
	}
	public void setAuthAccounts(List<AuthAccount> authAccounts) {
		this.authAccounts = authAccounts;
	}
}
