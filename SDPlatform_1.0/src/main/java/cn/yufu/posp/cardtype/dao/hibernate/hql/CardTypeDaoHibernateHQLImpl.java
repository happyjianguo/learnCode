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
	 *���һ�������
	 */
	public int queryCardTypeCount(CardType queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("CardTypeDaoHibernateHQLImpl.queryJgCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from CardType where 1=1");

			// ������ID��ѯ
			if (queryModel.getCardId() != null && !queryModel.getCardId().equals(""))
				bufferHql.append(" and cardId = '" + queryModel.getCardId() + "'");

			// ��������
			if (queryModel.getCardName() != null && !queryModel.getCardName().equals(""))
				bufferHql.append(" and cardName like '%" + queryModel.getCardName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CardTypeDaoHibernateHQLImpl.queryJgCount()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.queryJgCount()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *���һ���
	 */
	public List queryCardType(CardType queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CardTypeDaoHibernateHQLImpl.queryCardType()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(CardType.class);

			// ������ID��ѯ
			if (queryModel.getCardId() != null && !queryModel.getCardId().equals(""))
				// dcr.add(Restrictions.idEq(queryModel.getCardId()));
				dcr.add(Restrictions.like("cardId", queryModel.getCardId(), MatchMode.ANYWHERE));
			// ������
			if (queryModel.getCardName() != null && !queryModel.getCardName().equals(""))
				dcr.add(Restrictions.like("cardName", queryModel.getCardName(), MatchMode.ANYWHERE));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("cardId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("CardTypeDaoHibernateHQLImpl.queryCardType()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.queryCardType()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *ɾ������
	 */
	public void deleteCardType(CardType newKeys, UserData ud) throws OAException {
		try {
			log.info("CardTypeDaoHibernateHQLImpl.deleteCardType()���ÿ�ʼ��ɾ�������͡�");

			delete(newKeys, ud);

			log.info("CardTypeDaoHibernateHQLImpl.deleteCardType()�������ã�ɾ�������͡�");
		} catch (Exception e) {
			log.error("CardTypeDaoHibernateHQLImpl.deleteCardType()ɾ�������������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *�½�����
	 */
	public void createCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeDaoHibernateHQLImpl.createCardType()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("CardTypeDaoHibernateHQLImpl.createCardType()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.createCardType()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *��KEY���һ���
	 */
	public CardType queryCardTypeByKey(String newKey, UserData ud) throws OAException {
		CardType officeSuppliesTypeModel = null;
		try {
			log.info("CardTypeDaoHibernateHQLImpl.queryCardTypeByKey()��ʼ���ã�ͨ����Ż�����");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(CardType.class);
			cr.add(Restrictions.eq("jgId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (CardType) list.get(0);

			log.info("CardTypeDaoHibernateHQLImpl.queryCardTypeByKey()�������ã�ͨ����Ż�����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.queryCardTypeByKey()ͨ����Ż����������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *�������
	 */
	public void saveCardType(CardType newModel, UserData ud) throws OAException {
		try {
			log.info("CardTypeDaoHibernateHQLImpl.saveCardType()��ʼ���ã����������");

			update(newModel, ud);

			log.info("CardTypeDaoHibernateHQLImpl.saveCardType()�������ã����������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardTypeDaoHibernateHQLImpl.saveCardType()������������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
