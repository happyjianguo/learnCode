package com.yufupos.system.modules.gen.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.gen.entity.GenScheme;

/**
 * 生成方案DAO接口
 * @author king
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenSchemeDao extends CrudDao<GenScheme> {
	
}
