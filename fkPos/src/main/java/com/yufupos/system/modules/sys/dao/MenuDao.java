/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.dao;

import java.util.List;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);
	
	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);
	
}
