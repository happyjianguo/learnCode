package cn.yufu.posp.logManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.logManager.domain.model.OperateLog;

public interface LogDaoHibernateHQL {

	// 查找记录总数
	public int querySum(OperateLog queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(OperateLog newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 显示一条记录
	public OperateLog findItem(String newKey, UserData ud) throws OAException;
	
	// 新建一条记录
	public void createItem(OperateLog newModel, UserData ud) throws OAException;

}
