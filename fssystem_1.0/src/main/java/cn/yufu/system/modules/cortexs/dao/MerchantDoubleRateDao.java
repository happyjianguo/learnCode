package cn.yufu.system.modules.cortexs.dao;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.MerchantDoubleRate;

/**
 * 商户双费率DAO接口
 * @author LLG
 * @version 2017-04-25
 */
@MyBatisDao
public interface MerchantDoubleRateDao extends CrudDao<MerchantDoubleRate> {
	
}