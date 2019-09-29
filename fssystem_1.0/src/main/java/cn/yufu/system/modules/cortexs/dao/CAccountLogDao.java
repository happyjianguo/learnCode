package cn.yufu.system.modules.cortexs.dao;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.CAccountLog;


/**
 * CAccountLog交易类型DAO接口
 * @author LLG
 * @version 2016-08-24
 */
@MyBatisDao
public interface CAccountLogDao extends CrudDao<CAccountLog> {
	
}