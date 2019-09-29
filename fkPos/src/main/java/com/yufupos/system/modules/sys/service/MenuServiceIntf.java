package com.yufupos.system.modules.sys.service;

import java.util.List;

import com.yufupos.system.modules.sys.entity.Menu;

public interface MenuServiceIntf {
	public Menu get(String id);

	public int insert(Menu menu);

	public int update(Menu menu);

	public List<Menu> findByParentIdsLike(Menu menu);

	public int updateParentIds(Menu menu);

	public int updateSort(Menu menu);

	public int deleteOut(Menu menu);

	public List<Menu> findAllList(Menu menu);

	public List<Menu> findByUserId(Menu menu);

}
