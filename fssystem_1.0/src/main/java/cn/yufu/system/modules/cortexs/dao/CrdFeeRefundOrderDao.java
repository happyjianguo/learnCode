package cn.yufu.system.modules.cortexs.dao;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.CrdFeeRefundOrder;

/**
 * 新福卡退款DAO接口
 * @author ZQK
 * @version 2018-06-29
 */
@MyBatisDao
public interface CrdFeeRefundOrderDao extends CrudDao<CrdFeeRefundOrder>{
	
	/**退款总金额*/
	BigDecimal getRefundSum(CrdFeeRefundOrder record);
	
	int insert(CrdFeeRefundOrder record);
	
	int insertSelective(CrdFeeRefundOrder record);
}