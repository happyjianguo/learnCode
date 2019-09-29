package cn.com.jansh.model.component;

import java.util.List;

import cn.com.jansh.entity.system.IMUserN;

public class RechargeManageModel {
	private String userId;//用户id
	private String orgid;//机构id
	private List<IMUserN> users;//用户
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private String status ;//充值状态
	private int count;//总页数
	private int start;
	private int length;
	private List<ShowRechargeModel> showRechargeModels;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<ShowRechargeModel> getShowRechargeModels() {
		return showRechargeModels;
	}
	public void setShowRechargeModels(List<ShowRechargeModel> showRechargeModels) {
		this.showRechargeModels = showRechargeModels;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrgid() {
		return orgid;
	}
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}
	public List<IMUserN> getUsers() {
		return users;
	}
	public void setUsers(List<IMUserN> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "RechargeManageModel [userId=" + userId + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", status=" + status + ", count=" + count + ", start=" + start + ", length=" + length
				+ ", showRechargeModels=" + showRechargeModels + "]";
	}
}
