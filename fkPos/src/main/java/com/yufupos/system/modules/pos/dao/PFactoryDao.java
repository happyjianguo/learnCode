package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.PFactory;

/**
 * 厂商信息DAO接口
 * @author llg
 * @version 2017-04-05
 */
@MyBatisDao
public interface PFactoryDao extends CrudDao<PFactory> {
	
}