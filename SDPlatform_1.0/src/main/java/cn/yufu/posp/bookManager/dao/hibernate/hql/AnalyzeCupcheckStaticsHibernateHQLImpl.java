package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckStatics;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class AnalyzeCupcheckStaticsHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements AnalyzeCupcheckStaticsHibernateHQL {

	private static final Log log = LogFactory.getLog("book");

	public List queryAll(AnalyzeCupcheckStatics queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(AnalyzeCupcheckStatics.class);

			if (queryModel.getFiledate() != null && !queryModel.getFiledate().equals(""))
				dcr.add(Restrictions.eq("filedate", queryModel.getFiledate()));

			if (queryModel.getCheckDate() != null && !queryModel.getCheckDate().equals(""))
				dcr.add(Restrictions.eq("checkDate", queryModel.getCheckDate()));

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

			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckStaticsHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(AnalyzeCupcheckStatics queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.queryCount()��ʼ���ã���ѯ����������������");

			StringBuffer bufferHql = new StringBuffer("select count(*) from AnalyzeCupcheckStatics o where 1=1");

			if (queryModel.getFiledate() != null && !queryModel.getFiledate().equals(""))
				bufferHql.append(" and o.filedate = '" + queryModel.getFiledate() + "'");

			if (queryModel.getCheckDate() != null && !queryModel.getCheckDate().equals(""))
				bufferHql.append(" and o.checkDate = '" + queryModel.getCheckDate() + "'");

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

	public AnalyzeCupcheckStatics queryDetail(String id, UserData ud) throws OAException {
		AnalyzeCupcheckStatics model = null;
		try {
			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryDetail()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(AnalyzeCupcheckStatics.class);
			cr.add(Restrictions.eq("bookid", id));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (AnalyzeCupcheckStatics) list.get(0);
			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryDetail()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckStaticsHibernateHQLImpl.queryDetail()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

}
