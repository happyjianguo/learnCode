package cn.yufu.posp.bank.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;

public interface BanktypeDaoHibernateHQL {

	// 查找记录总数
	public int querySum(Banktype queryModel, UserData ud) throws OAException;

	// 查找所有记录
	public List queryAll(Banktype newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	// 删除一条记录
	public void deleteItem(List newKeys, UserData ud) throws OAException;

	// 显示一条记录
	public Banktype findItem(String newKey, UserData ud) throws OAException;

	// 修改一条记录
	public void saveItem(Banktype newModel, UserData ud) throws OAException;

	// 新建一条记录
	public void createItem(Banktype newModel, UserData ud) throws OAException;

}
