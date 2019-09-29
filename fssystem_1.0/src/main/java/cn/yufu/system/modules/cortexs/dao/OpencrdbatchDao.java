package cn.yufu.system.modules.cortexs.dao;

import java.util.List;

import cn.yufu.system.common.persistence.CrudDao;
import cn.yufu.system.common.persistence.annotation.MyBatisDao;
import cn.yufu.system.modules.cortexs.entity.Opencrdbatch;

/**
 * 订单DAO接口
 * @author ZQK
 * @version 2017-09-08
 */
@MyBatisDao
public interface OpencrdbatchDao extends CrudDao<Opencrdbatch>{
    
	public List<String> getOperator();
	
	public List<String> getOrderByOper(String oper);
	
	public List<String> getOrderByOperDistinct(String oper);
	
}