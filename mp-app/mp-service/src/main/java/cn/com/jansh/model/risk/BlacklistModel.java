package cn.com.jansh.model.risk;

import java.util.List;

import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.risk.BlacklistEntity;

/**
 * 黑名单 Model
 * 
 * @author gll
 *
 */
public class BlacklistModel {
	/*黑名单ID*/
	private String blackid;
	/*黑名单类型*/
	private String blacktype;
	/*黑名单值*/
	private String blackvalue;
	/*活动ID*/
	private String actionid;
	/** 黑名单时间 */
    private String playname;
	/*黑名单状态*/
	private String status;
	/*过期时间*/
	private String overtime;
	/*当天时间*/
	private String begintime;
	/*说明*/
	private String remark;
	/*黑名单列表*/
	private List<BlacklistEntity> list;
	/*游戏列表*/
	private List<CloudGameInitEntity> game;
	
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getBlackid() {
		return blackid;
	}
	public void setBlackid(String blackid) {
		this.blackid = blackid;
	}
	public String getBlacktype() {
		return blacktype;
	}
	public void setBlacktype(String blacktype) {
		this.blacktype = blacktype;
	}
	public String getBlackvalue() {
		return blackvalue;
	}
	public void setBlackvalue(String blackvalue) {
		this.blackvalue = blackvalue;
	}
	public String getActionid() {
		return actionid;
	}
	public void setActionid(String actionid) {
		this.actionid = actionid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOvertime() {
		return overtime;
	}
	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<BlacklistEntity> getList() {
		return list;
	}
	public void setList(List<BlacklistEntity> list) {
		this.list = list;
	}
	public List<CloudGameInitEntity> getGame() {
		return game;
	}
	public void setGame(List<CloudGameInitEntity> game) {
		this.game = game;
	}
	public String getPlayname() {
		return playname;
	}
	public void setPlayname(String playname) {
		this.playname = playname;
	}
	@Override
	public String toString() {
		return "BlacklistModel [blackid=" + blackid + ", blacktype=" + blacktype + ", blackvalue=" + blackvalue
				+ ", actionid=" + actionid + ", playname=" + playname + ", status=" + status + ", overtime=" + overtime
				+ ", begintime=" + begintime + ", remark=" + remark + ", list=" + list + ", game=" + game + "]";
	}
}
