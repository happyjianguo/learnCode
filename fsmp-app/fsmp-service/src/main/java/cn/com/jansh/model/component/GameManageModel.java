package cn.com.jansh.model.component;

import java.util.List;

import cn.com.jansh.entity.component.CloudgameinitEntity;
import cn.com.jansh.entity.wechat.AuthAccount;

public class GameManageModel {
	private String beginTime;//开始日期
	private String endTime;//结束日期
	private String gameid;//游戏id
	private int count;//总页数
	private int start;
	private int length;
	private List<ShowGameModel> showGameModels;
	private List<AuthAccount> wechats;
	private List<CloudgameinitEntity> cloudgames;
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
	public List<ShowGameModel> getShowGameModels() {
		return showGameModels;
	}
	public void setShowGameModels(List<ShowGameModel> showGameModels) {
		this.showGameModels = showGameModels;
	}
	public List<AuthAccount> getWechats() {
		return wechats;
	}
	public void setWechats(List<AuthAccount> wechats) {
		this.wechats = wechats;
	}
	public List<CloudgameinitEntity> getCloudgames() {
		return cloudgames;
	}
	public void setCloudgames(List<CloudgameinitEntity> cloudgames) {
		this.cloudgames = cloudgames;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	@Override
	public String toString() {
		return "GameManageModel [beginTime=" + beginTime + ", endTime=" + endTime + ", count=" + count + ", start="
				+ start + ", length=" + length + ", showGameModels=" + showGameModels + "]";
	}
}
