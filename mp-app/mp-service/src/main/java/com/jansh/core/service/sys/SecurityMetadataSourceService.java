package com.jansh.core.service.sys;

import java.util.List;

import com.jansh.core.entity.sys.SysResourceRole;
import com.jansh.core.menu.MenuAuths;

public interface SecurityMetadataSourceService {

	/**
	 * 查询所有资源及权限信息
	 * 
	 * @return
	 */
	public List<SysResourceRole> queryRoleResource();
	
	/**
	 * 查询所有菜单信息
	 * 
	 * @return
	 */
	public List<MenuAuths> queryMenu();
}
