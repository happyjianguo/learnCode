package cn.yufu.posp.terminalmanager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.terminalmanager.domain.model.CardAcct;

public class CardAcctDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CardAcctDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("cardAcct");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public CardAcctDaoHibernateHQLImpl() {
	}

	public void createItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctDaoHibernateHQLImpl.createItem()开始调用：保存一个卡账户管理信息。");

			save(cardAcct, ud);

			log.info("CardAcctDaoHibernateHQLImpl.createItem()结束调用：保存一个卡账户管理信息。");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.createItem()保存一个卡账户管理信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteItem(List keys, UserData ud) throws OAException {
		try {
			log.info("CardAcctDaoHibernateHQLImpl.deleteItem()开始调用：删除卡账户管理信息。");

			for (int i = 0; i < keys.size(); i++) {

				delete(findItemByKey((String) keys.get(i), ud), ud);
			}

			log.info("CardAcctDaoHibernateHQLImpl.deleteItem()结束调用：删除卡账户管理信息。");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.deleteItem()删除卡账户管理信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	@SuppressWarnings("unchecked")
	public CardAcct findItemByKey(String key, UserData ud) throws OAException {
		CardAcct model = null;
		try {
			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条卡账户记录。");

			StringBuffer bufferHql = new StringBuffer(" from CardAcct t where 1=1");

			// 按卡号查询
			if (key != null && !key.equals(""))
				bufferHql.append(" and t.cardNo = '" + key.trim() + "'");

			String hql = bufferHql.toString();
			List<CardAcct> cardAcctList = new ArrayList<CardAcct>();
			cardAcctList = findByHQL(hql, ud);
			if (cardAcctList.size() > 0)
				model = (CardAcct) cardAcctList.get(0);

			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()结束调用：显示一条卡账户记录。");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.findItemByKey()显示一条卡账户记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@SuppressWarnings("unchecked")
	public List queryAllItem(CardAcct cardAcct, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List<CardAcct> list = new ArrayList<CardAcct>();
		try {
			log.info("CardAcctDaoHibernateHQLImpl.queryAllItem()开始调用：查询符合条件的卡账户记录。");

			StringBuffer bufferHql = new StringBuffer(" from CardAcct t where 1=1");

			// 按卡号查询
			if (cardAcct.getCardNo() != null && !cardAcct.getCardNo().equals(""))
				bufferHql.append(" and t.cardNo = '" + cardAcct.getCardNo() + "'");

			// 按卡号状态查询
			if (cardAcct.getCardStat() != null && !cardAcct.getCardStat().equals(""))
				bufferHql.append(" and t.cardStat = '" + cardAcct.getCardStat() + "'");

			// 添加排序信息
			if (sortfield != null && sortType != null) {
				bufferHql.append(" order by t." + sortfield + " " + sortType + " ");
			} else {
				bufferHql.append(Order.desc(" order by t.cardNo desc "));
			}

			String hql = bufferHql.toString();
			List<CardAcct> cardAcctList = new ArrayList<CardAcct>();
			cardAcctList = findByHQL(hql, ud);
			int endIndex = startIndex + maxresults;
			if (endIndex > cardAcctList.size())
				endIndex = cardAcctList.size();

			list = cardAcctList.subList(startIndex, endIndex);

			log.info("查询语句：hql" + hql);
			log.info("CardAcctDaoHibernateHQLImpl.queryAllItem()结束调用：查询符合条件的卡账户记录。");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.queryAllItem()查询符合条件的卡账户记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int queryCount(CardAcct cardAcct, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("数CardAcctDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的卡账户记录数。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from CardAcct t where 1=1");

			// 按机构ID查询
			if (cardAcct.getCardNo() != null && !cardAcct.getCardNo().equals(""))
				bufferHql.append(" and t.cardNo = '" + cardAcct.getCardNo() + "'");

			// 按连接类型查询
			if (cardAcct.getCardStat() != null && !cardAcct.getCardStat().equals(""))
				bufferHql.append(" and t.cardStat = '" + cardAcct.getCardStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("数查询语句：hql" + hql);
			log.info("数CardAcctDaoHibernateHQLImpl.queryCount()结束调用：查询符合条件的卡账户记录数。");
		} catch (Exception e) {

			log.info("数CardAcctDaoHibernateHQLImpl.queryCount()查询符合条件的卡账户记录数，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public void saveItem(CardAcct cardAcct, UserData ud) throws OAException {
		try {
			log.info("CardAcctDaoHibernateHQLImpl.saveItem()开始调用：修改卡账户信息。");

			update(cardAcct, ud);

			log.info("CardAcctDaoHibernateHQLImpl.saveItem()结束调用：修改卡账户信息。");
		} catch (Exception e) {

			log.info("CardAcctDaoHibernateHQLImpl.saveItem()修改卡账户信息，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
