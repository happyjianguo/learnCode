package cn.com.jansh.mapper.system;

import java.util.List;

import cn.com.jansh.entity.system.IMResourceRole;

/**
 *@author Mr.wong
 */

public interface IMResourceRoleMapper {
	/**
	 * 增加关联
	 */
	public void insert(IMResourceRole resourceRole);
	/**
	 * 获取全部关联
	 */
	public List<IMResourceRole> selectAllByRoleId(String roleId);
	/**
	 * 通过 resourceId获取关联
	 */
	public IMResourceRole selectOneByResourceId(String resourceId);
	/**
	 * 更新关联
	 */
	public void update(IMResourceRole resourceRole);
	/**
	 * 根据roleId删除关联
	 */
	public void deleteByRoleId(String roleId);
	/**
	 * 根据resourceId删除关联
	 */
	public void deleteByResourceId(String resourceId);
}
