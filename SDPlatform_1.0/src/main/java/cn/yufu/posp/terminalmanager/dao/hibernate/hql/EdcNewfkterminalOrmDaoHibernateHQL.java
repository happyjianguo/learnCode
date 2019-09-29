package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public interface EdcNewfkterminalOrmDaoHibernateHQL {
	/** ��¼���� */
	public int queryCount(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(EdcNewfkterminalOrm edcNewfkterminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public EdcNewfkterminalOrm findItemByKey(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

}
