package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcBlack;

public interface EdcBlackDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(EdcBlack edcTerminal, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(EdcBlack edcTerminal, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public EdcBlack findItemByKey(EdcBlack edcTerminal, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcBlack edcTerminal, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcBlack edcTerminal, UserData ud) throws OAException;

}
