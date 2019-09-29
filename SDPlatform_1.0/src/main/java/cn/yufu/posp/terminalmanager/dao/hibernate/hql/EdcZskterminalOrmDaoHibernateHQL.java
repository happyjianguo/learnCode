/**
 *包名:cn.yufu.posp.terminalmanager.dao.hibernate.hql
 *描述:package cn.yufu.posp.terminalmanager.dao.hibernate.hql;
 */
package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcZskterminalOrm;

/**
 * EdcZskterminalOrmDaoHibernateHQL.java
 * 版权所有(C) 2018 裕福控股有限公司
 * 创建:gll 
 * 时间:2018年3月2日
 * 描述:专属卡终端
 */
public interface EdcZskterminalOrmDaoHibernateHQL {
	/** 记录总数 */
	public int queryCount(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** 查找所有记录 */
	public List queryAllItem(EdcZskterminalOrm edcZskterminalOrm, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** 查找一条记录 */
	public EdcZskterminalOrm findItemByKey(EdcZskterminalOrm key, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcZskterminalOrm edcZskterminalOrm, UserData ud) throws OAException;
	}
