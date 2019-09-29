/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.dao;

import cn.yufu.system.common.persistence.TreeDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.sys.entity.Area;

/**
 * 区域DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
	
}
