package com.yufupos.system.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.sys.dao.MenuDao;
import com.yufupos.system.modules.sys.dao.RoleDao;
import com.yufupos.system.modules.sys.entity.Menu;
import com.yufupos.system.modules.sys.entity.Role;

@Service("roleService")
@Transactional(readOnly = true)
public class RoleService extends CrudService<MenuDao, Menu> implements
		RoleServiceIntf {

	@Autowired
	RoleDao roleDao;

	@Override
	public int delete(Role role) {
		return roleDao.delete(role);
	}

	@Override
	public int deleteRoleMenu(Role role) {
		return roleDao.deleteRoleMenu(role);
	}

	@Override
	public int deleteRoleOffice(Role role) {
		return roleDao.deleteRoleOffice(role);
	}

	@Override
	public List<Role> findAllList(Role role) {
		return roleDao.findAllList(role);
	}

	@Override
	public List<Role> findList(Role role) {
		return roleDao.findList(role);
	}

	@Override
	public Role getByEnname(Role role) {
		return roleDao.getByEnname(role);
	}

	@Override
	public Role getByName(Role role) {
		return roleDao.getByName(role);
	}

	@Override
	public int inser(Role role) {
		return roleDao.insert(role);
	}

	@Override
	public int insertRoleMenu(Role role) {
		return roleDao.insertRoleMenu(role);
	}

	@Override
	public int insertRoleOffice(Role role) {
		return roleDao.insertRoleOffice(role);
	}

	@Override
	public int update(Role role) {
		return roleDao.update(role);
	}

	@Override
	public Role getOut(String id) {
		return roleDao.get(id);
	}

	@Override
	public int insert(Role role) {
		return roleDao.insert(role);
	}

}
