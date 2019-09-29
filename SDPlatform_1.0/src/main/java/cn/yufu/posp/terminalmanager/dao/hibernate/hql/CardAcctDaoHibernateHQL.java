package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;

public interface CardAcctDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(CardAcct cardAcct, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(CardAcct cardAcct, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public CardAcct findItemByKey(String key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(CardAcct cardAcct, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(CardAcct cardAcct, UserData ud) throws OAException;

}
