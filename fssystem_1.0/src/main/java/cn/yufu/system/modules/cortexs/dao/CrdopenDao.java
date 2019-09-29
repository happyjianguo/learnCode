package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Crdopen;

/**
 * 民生订单码表DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@MyBatisDao
public interface CrdopenDao extends CrudDao<Crdopen>{
	
	public List<Crdopen> getOneDate(Crdopen crdopen);
	
	public List<String> getOperator();

}