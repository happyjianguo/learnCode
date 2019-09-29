/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
