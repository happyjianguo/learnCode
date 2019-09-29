package cn.com.jansh.mapper.system;

import java.util.List;

import cn.com.jansh.entity.system.IMRole;

/**
 * 角色持久层函数
 *@author Mr.wong
 */

public interface IMRoleMapper {
	/**
	 * 增加角色
	 */
	public void insert(IMRole role);
	/**
	 * 获取全部角色
	 */
	public List<IMRole> selectAll();
	/**
	 * 通过 角色名获取角色
	 */
	public IMRole selectOneByName(String roleName);
	/**
	 * 通过 id名获取角色
	 */
	public IMRole selectOneById(String roleId);
	/**
	 * 更新角色
	 */
	public void update(IMRole role);
	/**
	 * 删除角色
	 */
	public void delete(String roleId);
	/**
	 * 通过roleids获取角色
	 */
	public List<IMRole> selectRolelistByroleids(List<String> roleids);
}
