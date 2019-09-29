package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;

public interface EdcCardDaoHibernateHQL

{
	/** 记录总数 */
	public int queryCount(EdcCard edcCard, UserData ud) throws OAException;

	/** 查找所有记录 */
	public List queryAllItem(EdcCard edcCard, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** 查找一条记录 */
	public EdcCard findItemByKey(EdcCard key, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcCard edcCard, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcCard edcCard, UserData ud) throws OAException;

}
