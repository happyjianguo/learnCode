package cn.yufu.posp.keyManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;

public interface PospZmkKeyDaoHibernateHQL {
	/** 记录总数 */
	public int queryCount(PospZmkKey queryModel, UserData ud) throws OAException;

	/** 查找所有记录 */
	public List queryAllItem(PospZmkKey queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	public PospZmkKey findItemByKey(PospZmkKey key, UserData ud) throws OAException;

	public void saveItem(PospZmkKey btsKey, UserData ud) throws OAException;

	public void createItem(PospZmkKey btsKey, UserData ud) throws OAException;



}
