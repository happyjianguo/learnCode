/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.dao;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.sys.entity.Log;

/**
 * 日志DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface LogDao extends CrudDao<Log> {

}
