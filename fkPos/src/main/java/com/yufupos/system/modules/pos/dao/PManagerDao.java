package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.PManager;

/**
 * 商户经理信息DAO接口
 * @author llg
 * @version 2017-04-05
 */
@MyBatisDao
public interface PManagerDao extends CrudDao<PManager> {
	
}