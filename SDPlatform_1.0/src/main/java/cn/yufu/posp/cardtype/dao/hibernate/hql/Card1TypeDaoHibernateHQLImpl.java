package cn.yufu.posp.cardtype.dao.hibernate.hql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.cardtype.domain.model.Cardtype1;
import cn.yufu.posp.cardtype.domain.model.CardtypeId1;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class Card1TypeDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements Card1TypeDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("cardType");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public Card1TypeDaoHibernateHQLImpl() {

	}

	/**
	 *查找卡类型总数
	 */
	public int queryCardTypeCount(Cardtype1 queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from Cardtype1 where 1=1 ");

			// 按卡类型查寻
			if (queryModel.getId().getQueryCardType() != null && !queryModel.getId().getQueryCardType().equals(""))
				bufferHql.append(" and id.cardType = '" + queryModel.getId().getQueryCardType() + "'");

			// 按名称查询
			if (queryModel.getId().getQueryTypeName() != null && !queryModel.getId().getQueryTypeName().equals(""))
				bufferHql.append(" and id.typeName like '%" + queryModel.getId().getQueryTypeName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.queryCardTypeCount()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询类型个数时出现异常");
		}
		return count;

	}

	/**
	 *查找机构
	 */
	public List queryCardType(Cardtype1 queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardType()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(Cardtype1.class);

			// 按卡类型查寻
			if (queryModel.getId().getQueryCardType() != null && !queryModel.getId().getQueryCardType().equals(""))
				dcr.add(Restrictions.eq("id.cardType", queryModel.getId().getQueryCardType()));

			// 按名称查询
			if (queryModel.getId().getQueryTypeName() != null && !queryModel.getId().getQueryTypeName().equals(""))
				dcr.add(Restrictions.like("id.typeName", queryModel.getId().getQueryTypeName(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("id.cardType"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardType结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.queryCardType()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询所有卡类型时出现异常");
		}
		return list;
	}

	/**
	 *删除卡类型
	 */
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException {
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.deleteCardType()调用开始：删除卡类型。");
			delete(newKeys, ud);
			log.info("Card1TypeDaoHibernateHQLImpl.deleteCardType()结束调用：删除卡类型。");
		} catch (Exception e) {
			log.error("Card1TypeDaoHibernateHQLImpl.deleteCardType()删除机构，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除卡类型时出现异常");
		}
	}

	/**
	 *新建卡类型
	 */
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.createCardType()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("Card1TypeDaoHibernateHQLImpl.createCardType()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.createCardType()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("增加卡类型时出现异常");
		}
	}

	/**
	 *查找一条卡类型
	 */
	public Cardtype1 queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		Cardtype1 officeSuppliesTypeModel = null;
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeByKey()开始调用：通过编号机构。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(Cardtype1.class);
			cr.add(Restrictions.eq("id.cardType", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (Cardtype1) list.get(0);

			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeByKey()结束调用：通过编号机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgByKey()通过编号机构，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查找一条卡类型时出现异常");
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *修改
	 */
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.saveCardType()开始调用：保存机构。");
			update(newModel, ud);

			log.info("Card1TypeDaoHibernateHQLImpl.saveCardType()结束调用：保存机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.saveCardType()保存机构出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改卡类型时出现异常");
		}
	}

	@Override
	public void update(Object entity, UserData ud) {

		Cardtype1 ct = (Cardtype1) entity;
		CardtypeId1 cc = ct.getId();
		String sql = "update cardtype set TYPE_NAME='" + cc.getTypeName() + "',TYPE_ENAME='" + cc.getTypeEname() + "' where CARD_TYPE='" + cc.getCardType()
				+ "'";
		System.out.println("sql==" + sql);

		try {
			Connection conn = this.getSession().connection();
			PreparedStatement pstm = conn.prepareStatement(sql);
			int i = pstm.executeUpdate();
			if (i == 0) {
				throw new SQLException("您要修改的信息不存在！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
