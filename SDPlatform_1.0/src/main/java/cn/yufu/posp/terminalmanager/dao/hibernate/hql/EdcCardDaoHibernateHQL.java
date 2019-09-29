package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;

public interface EdcCardDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(EdcCard edcCard, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(EdcCard edcCard, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public EdcCard findItemByKey(EdcCard key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcCard edcCard, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcCard edcCard, UserData ud) throws OAException;

}
