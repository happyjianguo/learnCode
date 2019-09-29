/**
* Copyright &copy; 2015 All rights reserved.
 */
package cn.yufu.system.modules.sys.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
	
}
