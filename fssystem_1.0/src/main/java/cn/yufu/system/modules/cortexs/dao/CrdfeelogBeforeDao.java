package cn.yufu.system.modules.cortexs.dao;

import java.math.BigDecimal;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.CrdfeelogBefore;

/**
 * 扣款明细预收DAO接口
 * @author ZQK
 * @version 2017-11-08
 */
@MyBatisDao
public interface CrdfeelogBeforeDao extends CrudDao<CrdfeelogBefore> {
	
	public BigDecimal getFeeSum(CrdfeelogBefore crdfeelogBefore);
	
}