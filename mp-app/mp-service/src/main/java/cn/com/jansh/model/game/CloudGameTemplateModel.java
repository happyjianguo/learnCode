/**
 * CloudGameTemplateModel.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年12月29日
 */
package cn.com.jansh.model.game;

import org.springframework.web.multipart.MultipartFile;

import cn.com.jansh.entity.game.GameTemplate;

/**
 * 游戏模版model
 * @author gll
 * @version 1.0
 */
public class CloudGameTemplateModel {
	private String tempid;
	private String tempname;
	private String usetimes;
	private String gameid;
	private String createtime;
	private String updatetime;
	private String tempstatus;
	private String templatename;
	private String pictrueurl;
	private GameTemplate gameTemplate;
	/** 上传图片 */
	private MultipartFile myfiles;
	/** 模板分类id */
	private String tmptypeid;

	public String getTmptypeid() {
		return tmptypeid;
	}

	public void setTmptypeid(String tmptypeid) {
		this.tmptypeid = tmptypeid;
	}

	public MultipartFile getMyfiles() {
		return myfiles;
	}

	public void setMyfiles(MultipartFile myfiles) {
		this.myfiles = myfiles;
	}
	public String getTempid() {
		return tempid;
	}
	public void setTempid(String tempid) {
		this.tempid = tempid;
	}
	public String getTempname() {
		return tempname;
	}
	public void setTempname(String tempname) {
		this.tempname = tempname;
	}
	public String getUsetimes() {
		return usetimes;
	}
	public void setUsetimes(String usetimes) {
		this.usetimes = usetimes;
	}
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
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
	public String getTempstatus() {
		return tempstatus;
	}
	public void setTempstatus(String tempstatus) {
		this.tempstatus = tempstatus;
	}
	public GameTemplate getGameTemplate() {
		return gameTemplate;
	}
	public void setGameTemplate(GameTemplate gameTemplate) {
		this.gameTemplate = gameTemplate;
	}
	public String getTemplatename() {
		return templatename;
	}
	public void setTemplatename(String templatename) {
		this.templatename = templatename;
	}
	public String getPictrueurl() {
		return pictrueurl;
	}
	public void setPictrueurl(String pictrueurl) {
		this.pictrueurl = pictrueurl;
	}

	@Override
	public String toString() {
		return "CloudGameTemplateModel [tempid=" + tempid + ", tempname=" + tempname + ", usetimes=" + usetimes
				+ ", gameid=" + gameid + ", createtime=" + createtime + ", updatetime=" + updatetime + ", tempstatus="
				+ tempstatus + ", templatename=" + templatename + ", pictrueurl=" + pictrueurl + ", gameTemplate="
				+ gameTemplate + ", myfiles=" + myfiles + ", tmptypeid=" + tmptypeid + "]";
	}
}
