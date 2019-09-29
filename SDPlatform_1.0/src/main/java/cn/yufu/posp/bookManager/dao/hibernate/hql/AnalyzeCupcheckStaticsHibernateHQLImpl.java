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
			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(AnalyzeCupcheckStatics.class);

			if (queryModel.getFiledate() != null && !queryModel.getFiledate().equals(""))
				dcr.add(Restrictions.eq("filedate", queryModel.getFiledate()));

			if (queryModel.getCheckDate() != null && !queryModel.getCheckDate().equals(""))
				dcr.add(Restrictions.eq("checkDate", queryModel.getCheckDate()));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("bookid"));
			}

			list = findBYCriteria(dcr, startIndex, pageSize, ud);

			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckStaticsHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(AnalyzeCupcheckStatics queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from AnalyzeCupcheckStatics o where 1=1");

			if (queryModel.getFiledate() != null && !queryModel.getFiledate().equals(""))
				bufferHql.append(" and o.filedate = '" + queryModel.getFiledate() + "'");

			if (queryModel.getCheckDate() != null && !queryModel.getCheckDate().equals(""))
				bufferHql.append(" and o.checkDate = '" + queryModel.getCheckDate() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantDaoHibernateHQLImpl.queryCount()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantDaoHibernateHQLImpl.queryCount()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	public AnalyzeCupcheckStatics queryDetail(String id, UserData ud) throws OAException {
		AnalyzeCupcheckStatics model = null;
		try {
			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryDetail()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(AnalyzeCupcheckStatics.class);
			cr.add(Restrictions.eq("bookid", id));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (AnalyzeCupcheckStatics) list.get(0);
			log.info("AnalyzeCupcheckStaticsHibernateHQLImpl.queryDetail()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckStaticsHibernateHQLImpl.queryDetail()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

}
