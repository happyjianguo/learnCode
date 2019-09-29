package cn.yufu.system.modules.cortexs.dao;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Crdrouting;

/**
 * 卡BIN信息DAO接口
 * @author LLG
 * @version 2017-04-25
 */
@MyBatisDao
public interface CrdroutingDao extends CrudDao<Crdrouting> {
	
}