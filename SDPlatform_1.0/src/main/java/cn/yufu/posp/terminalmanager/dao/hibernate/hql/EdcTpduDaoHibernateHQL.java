package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.TPreTpdu;

public interface EdcTpduDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(TPreTpdu tPreTpdu, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(TPreTpdu tPreTpdu, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public TPreTpdu findItemByKey(String key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(TPreTpdu tPreTpdu, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(TPreTpdu tPreTpdu, UserData ud) throws OAException;

}
