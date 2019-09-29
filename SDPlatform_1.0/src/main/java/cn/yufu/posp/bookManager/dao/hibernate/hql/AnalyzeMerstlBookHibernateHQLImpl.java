package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bookManager.domain.model.AnalyzeMerstlBook;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

/**
 * �̻������
 * 
 * @author King
 * 
 */
public class AnalyzeMerstlBookHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements AnalyzeMerstlBookHibernateHQL {
	private static final Log log = LogFactory.getLog("book");

	public List queryAll(AnalyzeMerstlBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("AnalyzeMerstlBookHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(AnalyzeMerstlBook.class);

			if (queryModel.getMerno() != null && !queryModel.getMerno().equals(""))
				dcr.add(Restrictions.eq("merno", queryModel.getMerno()));

			if (queryModel.getStldate() != null && !queryModel.getStldate().equals(""))
				dcr.add(Restrictions.eq("stldate", queryModel.getStldate()));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("id"));
			}

			list = findBYCriteria(dcr, startIndex, pageSize, ud);

			log.info("AnalyzeMerstlBookHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeMerstlBookHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(AnalyzeMerstlBook queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from AnalyzeMerstlBook o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getMerno() != null && !queryModel.getMerno().equals(""))
				bufferHql.append(" and o.merno = '" + queryModel.getMerno() + "'");

			if (queryModel.getStldate() != null && !queryModel.getStldate().equals(""))
				bufferHql.append(" and o.stldate = '" + queryModel.getStldate() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantDaoHibernateHQLImpl.queryCount()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantDaoHibernateHQLImpl.queryCount()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	public AnalyzeMerstlBook queryDetail(String id, UserData ud) throws OAException {
		AnalyzeMerstlBook model = null;
		try {
			log.info("AnalyzeMerstlBookHibernateHQLImpl.queryDetail()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(AnalyzeMerstlBook.class);
			cr.add(Restrictions.eq("id", id));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (AnalyzeMerstlBook) list.get(0);

			log.info("AnalyzeMerstlBookHibernateHQLImpl.queryDetail()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeMerstlBookHibernateHQLImpl.queryDetail()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

}
