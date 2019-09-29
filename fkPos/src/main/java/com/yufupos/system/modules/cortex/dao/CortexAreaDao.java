package com.yufupos.system.modules.cortex.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.cortex.entity.CortexArea;

/**
 * 省市区信息DAO接口
 * @author llg
 * @version 2017-04-19
 */
@MyBatisDao
public interface CortexAreaDao extends CrudDao<CortexArea> {
	
}