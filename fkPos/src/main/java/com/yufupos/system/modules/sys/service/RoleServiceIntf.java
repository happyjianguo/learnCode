package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.modules.sys.entity.Role;

public interface RoleServiceIntf {
	public Role getOut(String id);

	public Role getByName(Role role);

	public Role getByEnname(Role role);

	public List<Role> findList(Role role);

	public int inser(Role role);

	public int update(Role role);

	public int deleteRoleMenu(Role role);

	public int insertRoleMenu(Role role);

	public int deleteRoleOffice(Role role);

	public int insertRoleOffice(Role role);

	public int delete(Role role);

	public List<Role> findAllList(Role role);
	
	public int insert(Role role);

}
