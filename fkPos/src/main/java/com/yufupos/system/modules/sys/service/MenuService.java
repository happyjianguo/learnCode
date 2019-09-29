package com.yufupos.system.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yufupos.system.common.service.CrudService;
import com.yufupos.system.modules.sys.dao.MenuDao;
import com.yufupos.system.modules.sys.entity.Menu;

@Service("menuService")
@Transactional(readOnly = true)
public class MenuService extends CrudService<MenuDao, Menu> implements
		MenuServiceIntf {

	@Autowired
	MenuDao menuDao;

	public List<Menu> findAllList(Menu menu) {
		return menuDao.findAllList(menu);
	}

	@Override
	public List<Menu> findByParentIdsLike(Menu menu) {
		return menuDao.findByParentIdsLike(menu);
	}

	@Override
	public List<Menu> findByUserId(Menu menu) {
		return menuDao.findByUserId(menu);
	}

	@Override
	public int insert(Menu menu) {
		return menuDao.insert(menu);
	}

	@Override
	public int update(Menu menu) {
		return menuDao.update(menu);
	}

	@Override
	public int updateParentIds(Menu menu) {
		return menuDao.updateParentIds(menu);
	}

	@Override
	public int updateSort(Menu menu) {
		return menuDao.updateSort(menu);
	}

	@Override
	public int deleteOut(Menu menu) {
		return menuDao.delete(menu);

	}

}
