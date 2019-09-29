/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.dao;

import com.yufupos.system.common.persistence.TreeDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
