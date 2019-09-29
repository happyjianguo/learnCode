package cn.yufu.posp.keyManager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.BtsKey;

public interface TMKMasterKEYDaoHibernateHQL {
	/** 记录总数 */
	public int queryCount(BtsKey queryModel, UserData ud) throws OAException;

	/** 查找所有记录 */
	public List queryAllItem(BtsKey queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException;

	public BtsKey findItemByKey(BtsKey key, UserData ud) throws OAException;

	public void saveItem(BtsKey btsKey, UserData ud) throws OAException;

	public void createItem(BtsKey btsKey, UserData ud) throws OAException;
	//导出Excel和TxT文件的公共函数
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException;

	public void deleteItem(BtsKey key, UserData ud) throws OAException;


}
