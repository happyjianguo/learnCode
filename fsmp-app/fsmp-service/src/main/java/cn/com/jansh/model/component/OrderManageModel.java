package cn.com.jansh.model.component;

import java.util.List;

import cn.com.jansh.entity.component.CloudgameparamEntity;

/**
 * 前台展示订单信息model
 * @author Mr.wong
 * 
 */
public class OrderManageModel {
	private String userId;//用户id
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private String status;//充值状态
	private String sendstatus ;//充值失败状态
	
	private boolean rechargeFlag = false;// 批量补充标志
	private int queryCount;//查询补充订单个数
	private int chargeCount;//补充个数
	private int validateCount;//补充订单成功个数
	
	private String prizetype;//奖品类型
	private List<CloudorgvirgoodModel> vrList;//套餐类型集合
	private String vrid;//选中套餐类型
	private List<CloudgameparamEntity> gpList;// 活动参数集合
	private String gpid;//选中的活动id
	private String phone;//手机号
	private int count;//总页数
	private int start;
	private int length;
	private List<ShowOrderModel> showOrderModels;
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
	public String getSendstatus() {
		return sendstatus;
	}
	public void setSendstatus(String sendstatus) {
		this.sendstatus = sendstatus;
	}
	public List<ShowOrderModel> getShowOrderModels() {
		return showOrderModels;
	}
	public void setShowOrderModels(List<ShowOrderModel> showOrderModels) {
		this.showOrderModels = showOrderModels;
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
	
	public String getPrizetype() {
		return prizetype;
	}
	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
	}
	public List<CloudorgvirgoodModel> getVrList() {
		return vrList;
	}
	public void setVrList(List<CloudorgvirgoodModel> vrList) {
		this.vrList = vrList;
	}
	public String getVrid() {
		return vrid;
	}
	public void setVrid(String vrid) {
		this.vrid = vrid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<CloudgameparamEntity> getGpList() {
		return gpList;
	}
	public void setGpList(List<CloudgameparamEntity> gpList) {
		this.gpList = gpList;
	}
	public String getGpid() {
		return gpid;
	}
	public void setGpid(String gpid) {
		this.gpid = gpid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getQueryCount() {
		return queryCount;
	}
	public void setQueryCount(int queryCount) {
		this.queryCount = queryCount;
	}
	public int getChargeCount() {
		return chargeCount;
	}
	public void setChargeCount(int chargeCount) {
		this.chargeCount = chargeCount;
	}
	public int getValidateCount() {
		return validateCount;
	}
	public void setValidateCount(int validateCount) {
		this.validateCount = validateCount;
	}
	public boolean isRechargeFlag() {
		return rechargeFlag;
	}
	public void setRechargeFlag(boolean rechargeFlag) {
		this.rechargeFlag = rechargeFlag;
	}
	@Override
	public String toString() {
		return "OrderManageModel [userId=" + userId + ", beginTime=" + beginTime + ", endTime=" + endTime
				+ ", sendstatus=" + sendstatus + ", prizetype=" + prizetype + ", count=" + count + ", start=" + start
				+ ", length=" + length + ", showOrderModels=" + showOrderModels + "]";
	}
}
