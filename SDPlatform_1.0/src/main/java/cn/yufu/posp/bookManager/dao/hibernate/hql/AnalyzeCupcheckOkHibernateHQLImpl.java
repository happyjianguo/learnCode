package cn.yufu.posp.bookManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bookManager.domain.model.AnalyzeCupcheckOk;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class AnalyzeCupcheckOkHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements AnalyzeCupcheckOkHibernateHQL {

	private static final Log log = LogFactory.getLog("book");

	public List queryAll(AnalyzeCupcheckOk queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("AnalyzeCupcheckOkHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(AnalyzeCupcheckOk.class);

			if (queryModel.getCheckDate() != null && !queryModel.getCheckDate().equals(""))
				dcr.add(Restrictions.eq("checkDate", queryModel.getCheckDate()));
			if (queryModel.getCheckFlag() != null && !queryModel.getCheckFlag().equals(""))
				dcr.add(Restrictions.eq("checkFlag", queryModel.getCheckFlag()));
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", queryModel.getMerchantId()));
			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
				dcr.add(Restrictions.eq("localSysDate", queryModel.getLocalSysDate()));
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				dcr.add(Restrictions.eq("terminalId", queryModel.getTerminalId()));
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				dcr.add(Restrictions.like("cardNo", queryModel.getCardNo()+"%"));
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				dcr.add(Restrictions.eq("tranType", queryModel.getTranType()));
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				dcr.add(Restrictions.eq("traceNo", queryModel.getTraceNo()));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("id"));
			}

			list = findBYCriteria(dcr, startIndex, pageSize, ud);

			log.info("AnalyzeCupcheckOkHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckOkHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(AnalyzeCupcheckOk queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.queryCount()开始调用：查询符合条件的数量。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from AnalyzeCupcheckOk o where 1=1");

			if (queryModel.getCheckDate() != null && !queryModel.getCheckDate().equals(""))
				bufferHql.append(" and o.checkDate = '" + queryModel.getCheckDate() + "'");
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");
			if (queryModel.getCheckFlag() != null && !queryModel.getCheckFlag().equals(""))
				bufferHql.append(" and o.checkFlag = '" + queryModel.getCheckFlag() + "'");
			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
				bufferHql.append(" and o.localSysDate = '" + queryModel.getLocalSysDate() + "'");
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and o.terminalId = '" + queryModel.getTerminalId() + "'");
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				bufferHql.append(" and o.cardNo like '" + queryModel.getCardNo() + "%'");
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and o.tranType = '" + queryModel.getTranType() + "'");
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				bufferHql.append(" and o.traceNo = '" + queryModel.getTraceNo() + "'");

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

	public AnalyzeCupcheckOk queryDetail(String id, UserData ud) throws OAException {
		AnalyzeCupcheckOk model = null;
		try {
			log.info("AnalyzeCupcheckOkHibernateHQLImpl.queryDetail()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(AnalyzeCupcheckOk.class);
			cr.add(Restrictions.eq("id", id));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (AnalyzeCupcheckOk) list.get(0);
			log.info("AnalyzeCupcheckOkHibernateHQLImpl.queryDetail()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("AnalyzeCupcheckOkHibernateHQLImpl.queryDetail()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

}
