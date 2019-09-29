package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.MSettleBillRecord;

/**
 * TLOG交易类型DAO接口
 * @author ZQK
 * @version 2017-07-03
 */
@MyBatisDao
public interface MSettleBillRecordDao extends CrudDao<MSettleBillRecord>{
	
	public List<String> getModifyModule(MSettleBillRecord mSettleBillRecord);
	
}
