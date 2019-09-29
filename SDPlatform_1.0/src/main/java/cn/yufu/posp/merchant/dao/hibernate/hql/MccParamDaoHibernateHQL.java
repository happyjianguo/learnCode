package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MccParamModel;

public interface MccParamDaoHibernateHQL {

	// 查找记录总数
	public int querySum(MccParamModel queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(MccParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public MccParamModel findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(MccParamModel newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(MccParamModel newModel, UserData ud) throws OAException;
	// 查询所有的记录，无条件查询
	// public List findAllById(MccParamModel newModel, UserData ud) throws
	// OAException;
}
