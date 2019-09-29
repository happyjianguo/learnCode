package cn.com.jansh.entity.system;

public class IMUserRole {
	
	private String roleid;
	private String userid;
	private String createtime;
	private String updatetime;
	
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	@Override
	public String toString() {
		return "IMUserRole [roleid=" + roleid + ", userid=" + userid + ", createtime=" + createtime + ", updatetime="
				+ updatetime + "]";
	}
	
	
}
