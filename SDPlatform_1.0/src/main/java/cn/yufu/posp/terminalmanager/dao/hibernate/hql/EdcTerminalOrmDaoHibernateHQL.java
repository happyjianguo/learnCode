package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminalOrm;

public interface EdcTerminalOrmDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(EdcTerminalOrm edcTerminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public EdcTerminalOrm findItemByKey(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(EdcTerminalOrm edcTerminalOrm, UserData ud) throws OAException;

	public EdcTerminalOrm queryModualBy(String merchantId, String terminalId) throws OAException;

	/** У����������Ψһ��PKEY */
	public String checkEdcTerminalOrmPKEY(String merchantId,String terminalId,String moduleId) throws OAException;

	/**У������Ψһ��ORM */
	public String checkEdcTerminalOrmORM(String bankMerchantId,String bankTerminalId,String moduleId) throws OAException;
	
}
