package cn.com.jansh.service.system;

import java.util.List;

import cn.com.jansh.entity.system.IMReSource;
import cn.com.jansh.entity.system.IMResourceRole;
/**
 *@author Mr.wong
 */
public interface ResourceRoleService {
	
	/**
	 *通过关联获取所有的资源 
	 */
	public List<IMReSource> queryResourceByIds(List<IMResourceRole> resourceRoles);
	/**
	 *增加关联
	 *
	 */
	public void addResourceRole(IMResourceRole resourceRole);
	/**
	 *批量增加增加关联
	 *
	 */
	public void addResourceRoles(String roleId, String[] resourceIds);
	/**
	 *批量删除关联
	 *
	 */
	public void deleteResourceRolesByRoleId(String roleId);
}
