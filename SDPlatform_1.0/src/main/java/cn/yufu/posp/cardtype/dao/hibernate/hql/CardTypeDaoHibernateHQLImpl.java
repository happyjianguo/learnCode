package cn.yufu.posp.cardtype.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.cardtype.domain.model.CardType;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class CardTypeDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CardTypeDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public CardTypeDaoHibernateHQLImpl() {

	}

	/**
	 *查找机构总数
	 */
	public int queryCardTypeCount(CardType queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("CardTypeDaoHibernateHQLImpl.queryJgCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from CardType where 1=1");

			// 按机构ID查询
			if (queryModel.getCardId() != null && !queryModel.getCardId().equals(""))
				bufferHql.append(" and cardId = '" + queryModel.getCardId() + "'");

			// 按机构名
			if (queryModel.getCardName() != null && !queryModel.getCardName().equals(""))
				bufferHql.append(" and cardName like '%" + queryModel.getCardName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CardTypeDaoHibernateHQLImpl.queryJgCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.queryJgCount()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *查找机构
	 */
	public List queryCardType(CardType queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CardTypeDaoHibernateHQLImpl.queryCardType()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(CardType.class);

			// 卡类型ID查询
			if (queryModel.getCardId() != null && !queryModel.getCardId().equals(""))
				// dcr.add(Restrictions.idEq(queryModel.getCardId()));
				dcr.add(Restrictions.like("cardId", queryModel.getCardId(), MatchMode.ANYWHERE));
			// 按卡名
			if (queryModel.getCardName() != null && !queryModel.getCardName().equals(""))
				dcr.add(Restrictions.like("cardName", queryModel.getCardName(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("cardId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("CardTypeDaoHibernateHQLImpl.queryCardType()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.queryCardType()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *删除机构
	 */
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException {
		try {
			log.info("CardTypeDaoHibernateHQLImpl.deleteCardType()调用开始：删除卡类型。");

			delete(newKeys, ud);

			log.info("CardTypeDaoHibernateHQLImpl.deleteCardType()结束调用：删除卡类型。");
		} catch (Exception e) {
			log.error("CardTypeDaoHibernateHQLImpl.deleteCardType()删除机构，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *新建机构
	 */
	public void createCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeDaoHibernateHQLImpl.createCardType()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("CardTypeDaoHibernateHQLImpl.createCardType()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.createCardType()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *按KEY查找机构
	 */
	public CardType queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		CardType officeSuppliesTypeModel = null;
		try {
			log.info("CardTypeDaoHibernateHQLImpl.queryCardTypeByKey()开始调用：通过编号机构。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(CardType.class);
			cr.add(Restrictions.eq("jgId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (CardType) list.get(0);

			log.info("CardTypeDaoHibernateHQLImpl.queryCardTypeByKey()结束调用：通过编号机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.queryCardTypeByKey()通过编号机构，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *保存机构
	 */
	public void saveCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeDaoHibernateHQLImpl.saveCardType()开始调用：保存机构。");

			update(newModel, ud);

			log.info("CardTypeDaoHibernateHQLImpl.saveCardType()结束调用：保存机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.saveCardType()保存机构出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
