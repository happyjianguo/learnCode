package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLsWarn;

public class CurTranLsWarnHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CurTranLsWarnHibernateHQL {

	private static final Log log = LogFactory.getLog("query");

	@Override
	public int queryCount(CurTranLsWarn queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("CurTranLsHibernateHQLImpl.queryCount��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from CurTranLsWarn where 1=1 ");

			// //��������ѯ
			if (queryModel.getCardno() != null && !queryModel.getCardno().equals(""))
				bufferHql.append(" and cardno = '" + queryModel.getCardno() + "' ");
			if (queryModel.getMerno() != null && !queryModel.getMerno().equals(""))
				bufferHql.append(" and merno = '" + queryModel.getMerno() + "' ");
			if (queryModel.getTranceno() != null && !queryModel.getTranceno().equals(""))
				bufferHql.append(" and tranceno = '" + queryModel.getTranceno() + "' ");
			if (queryModel.getRulecode() != null && !queryModel.getRulecode().equals(""))
				bufferHql.append(" and rulecode = '" + queryModel.getRulecode() + "' ");
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CurTranLsHibernateHQLImpl.queryCount�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryCount����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯģ�����ʱ���쳣��");
		}
		return count;

	}

	public List query(CurTranLsWarn queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(CurTranLsWarn.class);
			// dcr.add(Expression.not(Expression.in("cardNo", new
			// Object[]{"7689100100000202119"})));
			//			
			// //��������ģ���ѯ
			if (queryModel.getCardno() != null && !queryModel.getCardno().equals(""))
				dcr.add(Restrictions.eq("cardno", queryModel.getCardno()));
			if (queryModel.getMerno() != null && !queryModel.getMerno().equals(""))
				dcr.add(Restrictions.eq("merno", queryModel.getMerno()));
			if (queryModel.getTranceno() != null && !queryModel.getTranceno().equals(""))
				dcr.add(Restrictions.eq("tranceno", queryModel.getTranceno()));
			if (queryModel.getRulecode() != null && !queryModel.getRulecode().equals(""))
				dcr.add(Restrictions.eq("rulecode", queryModel.getRulecode()));
			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.desc("tranceno"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("CurTranLsHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯģ��������ʱ���쳣��");
		}
		return list;
	}

	@Override
	public List queryExport(CurTranLsWarn queryModel, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(CurTranLsWarn.class);
			// dcr.add(Expression.not(Expression.in("cardNo", new
			// Object[]{"7689100100000202119"})));
			// ��������ģ���ѯ
			if (queryModel.getCardno() != null && !queryModel.getCardno().equals(""))
				dcr.add(Restrictions.eq("cardno", queryModel.getCardno()));
			if (queryModel.getMerno() != null && !queryModel.getMerno().equals(""))
				dcr.add(Restrictions.eq("merno", queryModel.getMerno()));
			if (queryModel.getTranceno() != null && !queryModel.getTranceno().equals(""))
				dcr.add(Restrictions.eq("tranceno", queryModel.getTranceno()));
			if (queryModel.getRulecode() != null && !queryModel.getRulecode().equals(""))
				dcr.add(Restrictions.eq("rulecode", queryModel.getRulecode()));
			list = findBYCriteria(dcr, ud);
			log.info("CurTranLsHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯģ��������ʱ���쳣��");
		}
		return list;
	}
}
