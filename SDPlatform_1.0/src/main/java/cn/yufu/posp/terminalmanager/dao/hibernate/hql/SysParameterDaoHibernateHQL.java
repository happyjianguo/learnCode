package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.SysParameter;

public interface SysParameterDaoHibernateHQL

{
	/** ��¼���� */
	public int queryCount(SysParameter sysParameter, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(SysParameter sysParameter, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** ����һ����¼ */
	public SysParameter findItemByKey(SysParameter key, UserData ud) throws OAException;

	/** ɾ����¼ */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** ���һ����¼ */
	public void createItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** �޸� / ���� ��¼ */
	public void saveItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** ����ö��ֵ */
	public List queryAllItem(SysParameter sysParameter, UserData ud) throws OAException;

	/** ����һ��ö��ֵ */
	public SysParameter findItemByKeyValue(SysParameter key, UserData ud) throws OAException;

}
