package cn.yufu.posp.keyManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.BtsKey;

public interface TMKMasterKEYDaoHibernateHQL {
	/** ��¼���� */
	public int queryCount(BtsKey queryModel, UserData ud) throws OAException;

	/** �������м�¼ */
	public List queryAllItem(BtsKey queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	public BtsKey findItemByKey(BtsKey key, UserData ud) throws OAException;

	public void saveItem(BtsKey btsKey, UserData ud) throws OAException;

	public void createItem(BtsKey btsKey, UserData ud) throws OAException;
	//����Excel��TxT�ļ��Ĺ�������
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException;

	public void deleteItem(BtsKey key, UserData ud) throws OAException;


}
