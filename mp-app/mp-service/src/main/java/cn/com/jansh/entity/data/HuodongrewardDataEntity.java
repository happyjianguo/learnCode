package cn.com.jansh.entity.data;

import java.util.List;

/**
 * 活动获奖情况Entity
 * @author gll
 * @version 1.0
 */
public class HuodongrewardDataEntity {

	private String prizeparamid;// 奖品设置ID
	private String prizeid;// 奖品ID
	private String gameid;// 活动ID
	private String prizename;// 奖品名称
	private String prizetype;// 奖品类型
	private String prizetotalnum;// 奖品总数量
	private String prizenum;// 已领取奖品数量
	private String winrate;// 中奖概率
	private String prizeposition;// 中奖位置
	private String createtime;// 创建时间
	private String updatetime;// 更新时间
	private List<AwarddetialEntity> list;
	public String getPrizeparamid() {
		return prizeparamid;
	}
	public void setPrizeparamid(String prizeparamid) {
		this.prizeparamid = prizeparamid;
	}
	public String getPrizeid() {
		return prizeid;
	}
	public void setPrizeid(String prizeid) {
		this.prizeid = prizeid;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getPrizename() {
		return prizename;
	}
	public void setPrizename(String prizename) {
		this.prizename = prizename;
	}
	public String getPrizetype() {
		return prizetype;
	}
	public void setPrizetype(String prizetype) {
		this.prizetype = prizetype;
	}
	public String getPrizetotalnum() {
		return prizetotalnum;
	}
	public void setPrizetotalnum(String prizetotalnum) {
		this.prizetotalnum = prizetotalnum;
	}
	public String getPrizenum() {
		return prizenum;
	}
	public void setPrizenum(String prizenum) {
		this.prizenum = prizenum;
	}
	public String getWinrate() {
		return winrate;
	}
	public void setWinrate(String winrate) {
		this.winrate = winrate;
	}
	public String getPrizeposition() {
		return prizeposition;
	}
	public void setPrizeposition(String prizeposition) {
		this.prizeposition = prizeposition;
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
	public List<AwarddetialEntity> getList() {
		return list;
	}
	public void setList(List<AwarddetialEntity> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "HuodongrewardDataEntity [prizeparamid=" + prizeparamid + ", prizeid=" + prizeid + ", gameid=" + gameid
				+ ", prizename=" + prizename + ", prizetype=" + prizetype + ", prizetotalnum=" + prizetotalnum
				+ ", prizenum=" + prizenum + ", winrate=" + winrate + ", prizeposition=" + prizeposition
				+ ", createtime=" + createtime + ", updatetime=" + updatetime + ", list=" + list + "]";
	}
}
