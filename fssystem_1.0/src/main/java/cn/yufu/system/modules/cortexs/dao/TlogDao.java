package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Tlog;

/**
 * 交易流水DAO接口
 * @author LLG
 * @version 2016-08-24
 */
@MyBatisDao
public interface TlogDao extends CrudDao<Tlog> {
	public Tlog findSum(Tlog tlog);
	public List<Tlog> findExportList(Tlog tlog);
}