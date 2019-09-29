/**
 *����:cn.yufu.posp.terminalmanager.dao.hibernate.hql
 *����:package cn.yufu.posp.terminalmanager.dao.hibernate.hql;
 */
package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmDaoHibernateHQL.java
 * ��Ȩ����(C) 2018 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2018��3��2��
 * ����:ר�����ն�
 */
public interface EdcZskterminalOrmDaoHibernateHQL {
	/** ��¼���� */
	public int queryCount(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(EdcZskterminalOrm edcZskterminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public EdcZskterminalOrm findItemByKey(EdcZskterminalOrm key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;
	}
