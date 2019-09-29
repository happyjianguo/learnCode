package cn.yufu.system.modules.cortexs.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Crdfeelog;

/**
 * 扣款明细DAO接口
 * @author ZQK
 * @version 2017-07-31
 */
@MyBatisDao
public interface CrdfeelogDao extends CrudDao<Crdfeelog> {
	
	/**消费总金额，不包含已退款的消费*/
	public BigDecimal getConsumeSum(Crdfeelog crdfeelog);
	
	/**退款总金额*/
	public BigDecimal getRefundSum(Crdfeelog crdfeelog);
	
	public List<Crdfeelog> selectByPrimaryKey(Crdfeelog crdfeelog);
	
	public int refund(Crdfeelog crdfeelog);
	
}