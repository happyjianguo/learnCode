/**
 * CloudGameTemplateTypeModel.java
 * 版权所有(C) 2017 北京坚石诚信科技有限公司
 * 创建:gll 2017年2月15日
 */
package cn.com.jansh.model.game;

import java.util.List;

import cn.com.jansh.entity.game.CloudGameInitEntity;
import cn.com.jansh.entity.game.CloudGameTemplateTypeEntity;

/**
 * 游戏模版分类model
 * @author gll
 * @version 1.0
 */
public class CloudGameTemplateTypeModel {
	/** 游戏id */
	private String gameid;
	/** 游戏名称 */
	private String playname;
	/** 模版分类id */
	private String tmptypeid;
	/** 模版分类名称 */
	private String tmptypename;
	/** 模版分类集合 */
	private List<CloudGameTemplateTypeEntity> gameTemplateTypelist;
	/** 游戏列表 */
	private List<CloudGameInitEntity> game;
	
	public List<CloudGameInitEntity> getGame() {
		return game;
	}
	public void setGame(List<CloudGameInitEntity> game) {
		this.game = game;
	}
	public List<CloudGameTemplateTypeEntity> getGameTemplateTypelist() {
		return gameTemplateTypelist;
	}
	public void setGameTemplateTypelist(List<CloudGameTemplateTypeEntity> gameTemplateTypelist) {
		this.gameTemplateTypelist = gameTemplateTypelist;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getPlayname() {
		return playname;
	}
	public void setPlayname(String playname) {
		this.playname = playname;
	}
	public String getTmptypeid() {
		return tmptypeid;
	}
	public void setTmptypeid(String tmptypeid) {
		this.tmptypeid = tmptypeid;
	}
	public String getTmptypename() {
		return tmptypename;
	}
	public void setTmptypename(String tmptypename) {
		this.tmptypename = tmptypename;
	}
	@Override
	public String toString() {
		return "CloudGameTemplateTypeModel [gameid=" + gameid + ", playname=" + playname + ", tmptypeid=" + tmptypeid
				+ ", tmptypename=" + tmptypename + ", gameTemplateTypelist=" + gameTemplateTypelist + ", game=" + game
				+ "]";
	}
}
