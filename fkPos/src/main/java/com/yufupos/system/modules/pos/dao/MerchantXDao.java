package com.yufupos.system.modules.pos.dao;

import java.util.Date;
import java.util.List;

import com.yufupos.system.common.persistence.CrudDao;
import com.yufupos.system.common.persistence.annotation.MyBatisDao;
import com.yufupos.system.modules.pos.entity.MerchantBase;
import com.yufupos.system.modules.pos.entity.MerchantX;

/**
 * 商户补充信息DAO接口
 * @author llg
 * @version 2017-04-05
 */
@MyBatisDao
public interface MerchantXDao extends CrudDao<MerchantBase> {
	
	void disableMerchant(MerchantBase merchantBase);
	
	List<MerchantX> getMerchantById(String merchantId);
	
	void usableMerchant(MerchantBase merchantBase);
	
	public void updateTypeYf(String merchantId,Integer typeYf);
	
	public void updateCreateDate(String merchantId,Date createDate);
	
}