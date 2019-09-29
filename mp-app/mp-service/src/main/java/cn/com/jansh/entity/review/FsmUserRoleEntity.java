/**
 * FsmUserRoleEntity.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:解亮亮 2016年10月26日
 */
package cn.com.jansh.entity.review;

/**
 * 用户端角色权限
 * @author xieliangliang
 * @version 1.0
 */
public class FsmUserRoleEntity {
	
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
