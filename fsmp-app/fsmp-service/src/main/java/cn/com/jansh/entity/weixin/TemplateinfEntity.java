package cn.com.jansh.entity.weixin;
//模板实体类
public class TemplateinfEntity {
	private String tempid;//模板ID
	private String tempname;//模板名称
	private String usetimes;//使用次数
	private String createtime;//创建时间
	private String updatetime;//更新时间
	private String tempstatus;//模板状态
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
	
}
