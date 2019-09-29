package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.List;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.EdcTerminal;

public interface EdcTerminalDaoHibernateHQL

{
	/** 记录总数 */
	public int queryCount(EdcTerminal edcTerminal, UserData ud) throws OAException;

	/** 查找所有记录 */
	public List queryAllItem(EdcTerminal edcTerminal, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException;

	/** 查找一条记录 */
	public EdcTerminal findItemByKey(EdcTerminal edcTerminal, UserData ud) throws OAException;

	/** 删除记录 */
	public void deleteItem(List keys, UserData ud) throws OAException;

	/** 添加一条记录 */
	public void createItem(EdcTerminal edcTerminal, UserData ud) throws OAException;

	/** 修改 / 保存 记录 */
	public void saveItem(EdcTerminal edcTerminal, UserData ud) throws OAException;
	
	public List<EdcTerminal> queryTerminalListByMerchantId(String merchantId,String cardBin, UserData ud) throws OAException;
	/**
	 * 判断所有的终端是否都是该商户的
	 * @param merchantId
	 * @param terminals
	 * @param ud
	 * @return	true：有不是该商户的终端；false：都是该商户的终端
	 * @throws OAException
	 */
	public boolean checkTerminalIsNotMechants(String merchantId, String terminals,
			UserData ud) throws OAException;
}
