package cn.yufu.posp.ruleManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;

public interface StlmReguDaoHibernateHQL {

	// 查找记录总数
	public int querySum(TblStlmRegu queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(TblStlmRegu newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public TblStlmRegu findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(TblStlmRegu newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(TblStlmRegu newModel, UserData ud) throws OAException;

}
