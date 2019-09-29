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
	 *���ҿ���������
	 */
	public int queryCardTypeCount(Cardtype1 queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from Cardtype1 where 1=1 ");

			// �������Ͳ�Ѱ
			if (queryModel.getId().getQueryCardType() != null && !queryModel.getId().getQueryCardType().equals(""))
				bufferHql.append(" and id.cardType = '" + queryModel.getId().getQueryCardType() + "'");

			// �����Ʋ�ѯ
			if (queryModel.getId().getQueryTypeName() != null && !queryModel.getId().getQueryTypeName().equals(""))
				bufferHql.append(" and id.typeName like '%" + queryModel.getId().getQueryTypeName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeCount()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.queryCardTypeCount()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ���͸���ʱ�����쳣");
		}
		return count;

	}

	/**
	 *���һ���
	 */
	public List queryCardType(Cardtype1 queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardType()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(Cardtype1.class);

			// �������Ͳ�Ѱ
			if (queryModel.getId().getQueryCardType() != null && !queryModel.getId().getQueryCardType().equals(""))
				dcr.add(Restrictions.eq("id.cardType", queryModel.getId().getQueryCardType()));

			// �����Ʋ�ѯ
			if (queryModel.getId().getQueryTypeName() != null && !queryModel.getId().getQueryTypeName().equals(""))
				dcr.add(Restrictions.like("id.typeName", queryModel.getId().getQueryTypeName(), MatchMode.ANYWHERE));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("id.cardType"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardType�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.queryCardType()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ���п�����ʱ�����쳣");
		}
		return list;
	}

	/**
	 *ɾ��������
	 */
	public void deleteCardType(Cardtype1 newKeys, UserData ud) throws OAException {
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.deleteCardType()���ÿ�ʼ��ɾ�������͡�");
			delete(newKeys, ud);
			log.info("Card1TypeDaoHibernateHQLImpl.deleteCardType()�������ã�ɾ�������͡�");
		} catch (Exception e) {
			log.error("Card1TypeDaoHibernateHQLImpl.deleteCardType()ɾ�������������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ��������ʱ�����쳣");
		}
	}

	/**
	 *�½�������
	 */
	public void createCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.createCardType()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("Card1TypeDaoHibernateHQLImpl.createCardType()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.createCardType()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("���ӿ�����ʱ�����쳣");
		}
	}

	/**
	 *����һ��������
	 */
	public Cardtype1 queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		Cardtype1 officeSuppliesTypeModel = null;
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeByKey()��ʼ���ã�ͨ����Ż�����");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(Cardtype1.class);
			cr.add(Restrictions.eq("id.cardType", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (Cardtype1) list.get(0);

			log.info("Card1TypeDaoHibernateHQLImpl.queryCardTypeByKey()�������ã�ͨ����Ż�����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgByKey()ͨ����Ż����������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("����һ��������ʱ�����쳣");
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *�޸�
	 */
	public void saveCardType(Cardtype1 newModel, UserData ud) throws OAException {
		try {
			log.info("Card1TypeDaoHibernateHQLImpl.saveCardType()��ʼ���ã����������");
			update(newModel, ud);

			log.info("Card1TypeDaoHibernateHQLImpl.saveCardType()�������ã����������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("Card1TypeDaoHibernateHQLImpl.saveCardType()������������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸Ŀ�����ʱ�����쳣");
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
				throw new SQLException("��Ҫ�޸ĵ���Ϣ�����ڣ�");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
