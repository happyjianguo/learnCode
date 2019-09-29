/**
* Copyright &copy; 2015 All rights reserved.
 */
package com.yufupos.system.modules.sys.dao;

import java.util.List;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.sys.entity.Dict;

/**
 * 字典DAO接口
 * @author king
 * @version 2014-05-16
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {

	public List<String> findTypeList(Dict dict);
}
