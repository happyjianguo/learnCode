package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;

public class CurTranLsReportHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CurTranLsReportHibernateHQL {

	private static final Log log = LogFactory.getLog("query");

	@Override
	public int queryCount(CurTranLs queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("CurTranLsHibernateHQLImpl.queryCount开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from VCurTranLs where 1=1 ");

			// //按条件查询
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and tranType = " + queryModel.getTranType() + " ");
			if (queryModel.getLocalSysDateS() != null && !queryModel.getLocalSysDateS().equals(""))
				bufferHql.append(" and localSysDate >= " + queryModel.getLocalSysDateS() + " ");
			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
				bufferHql.append(" and localSysDate <= " + queryModel.getLocalSysDateE() + " ");
			if (queryModel.getLocalSysTimeS() != null && !queryModel.getLocalSysTimeS().equals(""))
				bufferHql.append(" and localSysTime >= " + queryModel.getLocalSysTimeS() + " ");
			if (queryModel.getLocalSysTimeE() != null && !queryModel.getLocalSysTimeE().equals(""))
				bufferHql.append(" and localSysTime <= " + queryModel.getLocalSysTimeE() + " ");
			if (queryModel.getMerchantName() != null && !queryModel.getMerchantName().equals(""))
				bufferHql.append(" and merchantName like '%" + queryModel.getMerchantName() + "%'");
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				bufferHql.append(" and traceNo = " + queryModel.getTraceNo() + "");
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				bufferHql.append(" and cardNo = " + queryModel.getCardNo() + "");
			// if(queryModel.getId().getMerchantId()!=null &&
			// !queryModel.getId().getMerchantId().equals("") )
			// bufferHql.append(" and id.merchantId like '%"+queryModel.getId().getMerchantId()+"%'");
			//			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CurTranLsHibernateHQLImpl.queryCount结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryCount调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return count;

	}

	public List query(CurTranLs queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query开始调用：通过查询条件分页查询信息。");
			list = this.getSession().getNamedQuery("curTranLsReport").setParameter(0, queryModel.getLocalSysDateS()).setParameter(1,
					queryModel.getLocalSysDateE()).setParameter(2, queryModel.getMerchantId()).list();
			log.info("CurTranLsHibernateHQLImpl.query结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.query通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询模板对象个数时抛异常！");
		}
		return list;
	}

	@Override
	public List queryExport(CurTranLs queryModel, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query开始调用：通过查询条件分页查询信息。");

			log.info("CurTranLsHibernateHQLImpl.query开始调用：通过查询条件分页查询信息。");
			list = this.getSession().getNamedQuery("curTranLsReport").setParameter(0, queryModel.getLocalSysDateS()).setParameter(1,
					queryModel.getLocalSysDateE()).setParameter(2, queryModel.getMerchantId()).list();
			log.info("CurTranLsHibernateHQLImpl.query结束调用：通过查询条件分页查询信息。");
			log.info("CurTranLsHibernateHQLImpl.query结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.query通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询模板对象个数时抛异常！");
		}
		return list;
	}
}
