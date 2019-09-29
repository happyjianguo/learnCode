package cn.com.jansh.entity.system;

public class IMResourceRole {
	
	/**
	 * 资源key
	 */
	private String resourceId;
	/**
	 * 资源角色
	 */
	private String roleId;
	/**
	 * 状态
	 */
	private String status;
	/**
	 *更新时间
	 */
	private String updateTime;
	/**
	 * 创建时间
	 */
	private String createTime;


	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
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