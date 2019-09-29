package cn.com.jansh.entity.system;

/**
 *
 * @author Mr.wong
 *
 */
public class IMRole {
	/**
	 * 角色id
	 */
	private String roleId;
	/**
	 * 角色名
	 */
	private String roleName;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 *角色下的资源id 
	 */
	private String reSourceIds;

	
	public String getReSourceIds() {
		return reSourceIds;
	}

	public void setReSourceIds(String reSourceIds) {
		this.reSourceIds = reSourceIds;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
