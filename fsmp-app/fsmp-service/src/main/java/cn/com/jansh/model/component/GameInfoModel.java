package cn.com.jansh.model.component;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.entity.weixin.TemplateinfEntity;


public class GameInfoModel {
	/** 游戏id */
    private String gameid;
    /** 公众号id */
    private String appid;
    /** 渠道 */
    private String channel;
    /**活动初始化 */
    private List<TemplateinfEntity> templateinflist;
    /** 游戏模板集合*/
	private List<ShowTemplateModel> gameTemps;
    /**活动模板id */
    private String tempid;
    /**活动标题 */
    private String gamename;
    /**游戏id */
    private String playname;
    /**游戏平台链接 */
    private String gameURI;
    /**活动开始时间*/
    private String begintime;
    /**游戏结束时间 */
    private String endtime;
    /**游戏总预算 */
    private String totalbudget;
    /**用户id */
    private String userid;
    
    private MultipartFile myfile;
	public String getGameid() {
		return gameid;
	}
	public String getAppid() {
		return appid;
	}
	public List<TemplateinfEntity> getTemplateinflist() {
		return templateinflist;
	}
	public String getTempid() {
		return tempid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setTemplateinflist(List<TemplateinfEntity> templateinflist) {
		this.templateinflist = templateinflist;
	}
	public void setTempid(String tempid) {
		this.tempid = tempid;
	}
	
	public MultipartFile getMyfile() {
		return myfile;
	}
	public void setMyfile(MultipartFile myfile) {
		this.myfile = myfile;
	}
	
	public String getGamename() {
		return gamename;
	}
	public void setGamename(String gamename) {
		this.gamename = gamename;
	}
	public String getPlayname() {
		return playname;
	}
	public void setPlayname(String playname) {
		this.playname = playname;
	}
	public String getGameURI() {
		return gameURI;
	}
	public void setGameURI(String gameURI) {
		this.gameURI = gameURI;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTotalbudget() {
		return totalbudget;
	}
	public void setTotalbudget(String totalbudget) {
		this.totalbudget = totalbudget;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public List<ShowTemplateModel> getGameTemps() {
		return gameTemps;
	}
	public void setGameTemps(List<ShowTemplateModel> gameTemps) {
		this.gameTemps = gameTemps;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "GameInfoModel [gameid=" + gameid + ", appid=" + appid + ", templateinflist=" + templateinflist
				+ ", tempid=" + tempid + "]";
	}
    

}
