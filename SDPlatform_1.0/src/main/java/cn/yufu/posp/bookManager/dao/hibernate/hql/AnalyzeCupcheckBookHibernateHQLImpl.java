package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckBook;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class AnalyzeCupcheckBookHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements AnalyzeCupcheckBookHibernateHQL {

	private static final Log log = LogFactory.getLog("book");

	public List queryAll(AnalyzeCupcheckBook queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("AnalyzeCupcheckBookHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(AnalyzeCupcheckBook.class);

			if (queryModel.getFiledate() != null && !queryModel.getFiledate().equals(""))
				dcr.add(Restrictions.eq("filedate", queryModel.getFiledate()));

			if (queryModel.getCheckdate() != null && !queryModel.getCheckdate().equals(""))
				dcr.add(Restrictions.eq("checkdate", queryModel.getCheckdate()));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("bookid"));
			}

			list = findBYCriteria(dcr, startIndex, pageSize, ud);

			log.info("AnalyzeCupcheckBookHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckBookHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(AnalyzeCupcheckBook queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from AnalyzeCupcheckBook o where 1=1");

			if (queryModel.getFiledate() != null && !queryModel.getFiledate().equals(""))
				bufferHql.append(" and o.filedate = '" + queryModel.getFiledate() + "'");

			if (queryModel.getCheckdate() != null && !queryModel.getCheckdate().equals(""))
				bufferHql.append(" and o.checkdate = '" + queryModel.getCheckdate() + "'");

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

	public AnalyzeCupcheckBook queryDetail(String id, UserData ud) throws OAException {
		AnalyzeCupcheckBook model = null;
		try {
			log.info("AnalyzeCupcheckBookHibernateHQLImpl.queryDetail()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(AnalyzeCupcheckBook.class);
			cr.add(Restrictions.eq("bookid", id));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (AnalyzeCupcheckBook) list.get(0);
			log.info("AnalyzeCupcheckBookHibernateHQLImpl.queryDetail()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckBookHibernateHQLImpl.queryDetail()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

}
