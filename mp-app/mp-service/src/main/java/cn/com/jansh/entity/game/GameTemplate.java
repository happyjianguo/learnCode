package cn.com.jansh.entity.game;

/**
 * 活动模板实体类
 * 
 */
public class GameTemplate {
	private String tempid;
	private String tempname;
	private String usetimes;
	private String gameid;
	private String createtime;
	private String updatetime;
	private String tempstatus;
	private String templatename;
	private String pictrueurl;
	/** 模版分类id */
	private String tmptypeid;
	/** 模版分类name */
	private String tmptypename;
	
	public String getTmptypename() {
		return tmptypename;
	}
	public void setTmptypename(String tmptypename) {
		this.tmptypename = tmptypename;
	}
	public String getTmptypeid() {
		return tmptypeid;
	}
	public void setTmptypeid(String tmptypeid) {
		this.tmptypeid = tmptypeid;
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
	@Override
	public String toString() {
		return "GameTemplate [tempid=" + tempid + ", tempname=" + tempname + ", usetimes=" + usetimes + ", gameid="
				+ gameid + ", createtime=" + createtime + ", updatetime=" + updatetime + ", tempstatus=" + tempstatus
				+ ", templatename=" + templatename + ", pictrueurl=" + pictrueurl + ", tmptypeid=" + tmptypeid
				+ ", tmptypename=" + tmptypename + "]";
	}
}
