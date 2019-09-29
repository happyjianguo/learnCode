package cn.com.jansh.model.system;

import java.util.List;

import cn.com.jansh.entity.system.IMReSource;
import cn.com.jansh.entity.system.IMResourceRole;
import cn.com.jansh.entity.system.IMRole;

/**
 * 角色model
 * @author gll
 *
 */
public class RoleManageModel {
	
	/**
	 * 角色id
	 */
	private String roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
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
	/**
	 * 资源（字符串，逗号分离）
	 */
	private String resourceId;

	/**
	 * 资源key
	 */
	private String[] resourceIds;
	
	/**imroleresource
	 * 资源列表
	 */
	
	/**
	 * 菜单id用于树形复选框
	 */
	private String[] menuid;
	
	private List<IMReSource> imresourcdlist;
	
	private String resourceList;
	
	/**
	 * 资源角色关联表
	 */
	private List<IMResourceRole> imresourcelist;
	
	public String[] getMenuid() {
		return menuid;
	}

	public void setMenuid(String[] menuid) {
		this.menuid = menuid;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	public List<IMResourceRole> getImresourcelist() {
		return imresourcelist;
	}

	public void setImresourcelist(List<IMResourceRole> imresourcelist) {
		this.imresourcelist = imresourcelist;
	}

	/**
	 * 角色列表
	 */
	private List<IMRole> rolelist;

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

	public String[] getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String[] resourceIds) {
		this.resourceIds = resourceIds;
	}

	public List<IMReSource> getImresourcdlist() {
		return imresourcdlist;
	}

	public void setImresourcdlist(List<IMReSource> imresourcdlist) {
		this.imresourcdlist = imresourcdlist;
	}

	public List<IMRole> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<IMRole> rolelist) {
		this.rolelist = rolelist;
	}

	public String getResourceList() {
		return resourceList;
	}

	public void setResourceList(String resourceList) {
		this.resourceList = resourceList;
	}

}
