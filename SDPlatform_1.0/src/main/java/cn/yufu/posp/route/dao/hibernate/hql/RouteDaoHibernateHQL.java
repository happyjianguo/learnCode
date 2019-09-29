package cn.yufu.posp.route.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public interface RouteDaoHibernateHQL

{
	// Àà±ð
	public int queryCount(Object newQueryModel, UserData ud) throws OAException;

	public List query(Object newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	public void deleteRoute(Object newKeys, UserData ud) throws OAException;

	public void create(Object newModel, UserData ud) throws OAException;

	public Object queryByKey(String newKey, UserData ud) throws OAException;

	public void save(Object newModel, UserData ud) throws OAException;

}
