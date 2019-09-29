package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.MerchTerminalAccNo;

/**
 * 商户终端入账银行账号信息Dao
 * @author ZQK
 * @version 2018-07-03
 */
@MyBatisDao
public interface MerchTerminalAccNoDao extends CrudDao<MerchTerminalAccNo> {
	
}