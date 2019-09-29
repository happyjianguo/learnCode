package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcSwitch;

public interface EdcSwitchDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(EdcSwitch edcSwitch, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(EdcSwitch edcSwitch, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public EdcSwitch findItemByKey(EdcSwitch key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcSwitch edcSwitch, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcSwitch edcSwitch, UserData ud) throws OAException;

}
