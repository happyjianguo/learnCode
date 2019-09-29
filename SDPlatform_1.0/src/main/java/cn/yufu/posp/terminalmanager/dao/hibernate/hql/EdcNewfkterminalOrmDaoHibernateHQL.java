package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcNewfkterminalOrm;

public interface EdcNewfkterminalOrmDaoHibernateHQL {
	/** 记录总数 */
	public int queryCount(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

	/** 查找所有记录 */
	public List queryAllItem(EdcNewfkterminalOrm edcNewfkterminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** 查找一条记录 */
	public EdcNewfkterminalOrm findItemByKey(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcNewfkterminalOrm edcNewfkterminalOrm, UserData ud) throws OAException;

}
