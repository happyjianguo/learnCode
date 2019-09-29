package com.yufupos.system.modules.pos.dao;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.MerchantBase;

/**
 * 商户基本信息DAO接口
 * @author llg
 * @version 2017-04-19
 */
@MyBatisDao
public interface MerchantBaseDao extends CrudDao<MerchantBase> {
	
	void disableMerchant(MerchantBase merchantBase);
	
	void usableMerchant(MerchantBase merchantBase);
	
}