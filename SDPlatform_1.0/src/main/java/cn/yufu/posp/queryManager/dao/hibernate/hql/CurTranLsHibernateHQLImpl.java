package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;

public class CurTranLsHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements CurTranLsHibernateHQL {

	private static final Log log = LogFactory.getLog("query");

	@Override
	public int queryCount(CurTranLs queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("CurTranLsHibernateHQLImpl.queryCount开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			//41 //冲正不显示
			StringBuffer bufferHql = new StringBuffer("select count(*) from CurTranLs where 1=1 and tranType!='41' ");

			// //按条件查询
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and tranType = " + queryModel.getTranType() + " ");
//			if (queryModel.getLocalSysDateS() != null && !queryModel.getLocalSysDateS().equals(""))
//				bufferHql.append(" and localSysDate >= " + queryModel.getLocalSysDateS() + " ");
//			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
//				bufferHql.append(" and localSysDate <= " + queryModel.getLocalSysDateE() + " ");
//			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
//				bufferHql.append(" and localSysDate = " + queryModel.getLocalSysDate() + " ");
			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
				bufferHql.append("and localSysDate <=" + queryModel.getLocalSysDateE() + " ");
			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
				bufferHql.append(" and localSysDate >= " + queryModel.getLocalSysDate() + "");
			if (queryModel.getLocalSysTimeS() != null && !queryModel.getLocalSysTimeS().equals(""))
				bufferHql.append(" and localSysTime >= " + queryModel.getLocalSysTimeS() + " ");
			if (queryModel.getLocalSysTimeE() != null && !queryModel.getLocalSysTimeE().equals(""))
				bufferHql.append(" and localSysTime <= " + queryModel.getLocalSysTimeE() + " ");
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and merchantId = '" + queryModel.getMerchantId() + "' ");
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				bufferHql.append(" and traceNo = '" + queryModel.getTraceNo() + "'");
			if (queryModel.getBatchNo() != null && !queryModel.getBatchNo().equals(""))
				bufferHql.append(" and batchNo = '" + queryModel.getBatchNo() + "'");
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				bufferHql.append(" and cardNo = '" + queryModel.getCardNo() + "'");
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and terminalId = '" + queryModel.getTerminalId() + "'");
			if (queryModel.getBankBatchNo() != null && !queryModel.getBankBatchNo().equals(""))
				bufferHql.append(" and bankBatchNo = '" + queryModel.getBankBatchNo() + "'");
			if (queryModel.getTrace1() != null && !queryModel.getTrace1().equals(""))
				bufferHql.append(" and trace1 = '" + queryModel.getTrace1() + "'");
			if (queryModel.getTrace2() != null && !queryModel.getTrace2().equals(""))
				bufferHql.append(" and trace2 = '" + queryModel.getTrace2() + "'");
			//查询正常数据  返回码为00 交易表示为 0.1.2 
			if("0".equals(queryModel.getQueryType())){
				bufferHql.append(" and respCode = '00' and tranFlag in (0,1,2) ");
			}else if ("1".equals(queryModel.getQueryType())){
				//dcr.add(Restrictions.or(Restrictions.not(Restrictions.in("respCode", new Object[]{"00 "})),Restrictions.in("tranFlag", new Object[]{"8"})));
				bufferHql.append(" and (respCode !='00' or tranFlag in (8) ) ");
			}
			String hql = bufferHql.toString();
			log.info("流水查询 HQL语句: " + hql );
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

			DetachedCriteria dcr = DetachedCriteria.forClass(CurTranLs.class);
			//冲正不显示
			dcr.add(Restrictions.not(Restrictions.in("tranType", new Object[] {new BigDecimal(41)})));

			// //按名程序模板查询
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				dcr.add(Restrictions.eq("tranType", queryModel.getTranType()));
//			if (queryModel.getLocalSysDateS() != null && !queryModel.getLocalSysDateS().equals(""))
//				dcr.add(Restrictions.ge("localSysDate", queryModel.getLocalSysDateS()));
			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
				dcr.add(Restrictions.le("localSysDate", queryModel.getLocalSysDateE()));
			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
				dcr.add(Restrictions.ge("localSysDate", queryModel.getLocalSysDate()));
//				dcr.add(Restrictions.eq("localSysDate", queryModel.getLocalSysDate()));
			if (queryModel.getLocalSysTimeS() != null && !queryModel.getLocalSysTimeS().equals(""))
				dcr.add(Restrictions.ge("localSysTime", queryModel.getLocalSysTimeS()));
			if (queryModel.getLocalSysTimeE() != null && !queryModel.getLocalSysTimeE().equals(""))
				dcr.add(Restrictions.le("localSysTime", queryModel.getLocalSysTimeE()));
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", queryModel.getMerchantId()));
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				dcr.add(Restrictions.eq("traceNo", queryModel.getTraceNo()));
			if (queryModel.getBatchNo() != null && !queryModel.getBatchNo().equals(""))
				dcr.add(Restrictions.eq("batchNo", queryModel.getBatchNo()));
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				dcr.add(Restrictions.eq("terminalId", queryModel.getTerminalId()));
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				dcr.add(Restrictions.like("cardNo", queryModel.getCardNo()+"%"));
			if (queryModel.getBankBatchNo() != null && !queryModel.getBankBatchNo().equals(""))
				dcr.add(Restrictions.eq("bankBatchNo", queryModel.getBankBatchNo()));
			if (queryModel.getTrace1() != null && !queryModel.getTrace1().equals(""))
				dcr.add(Restrictions.eq("trace1", queryModel.getTrace1()));
			if (queryModel.getTrace2() != null && !queryModel.getTrace2().equals(""))
				dcr.add(Restrictions.eq("trace2", queryModel.getTrace2()));
			//交易类型
			if("0".equals(queryModel.getQueryType())){
				dcr.add(Restrictions.eq("respCode", "00 "));
				dcr.add(Restrictions.in("tranFlag", new Object[]{"0","1","2"}));
			}else if ("1".equals(queryModel.getQueryType())){
				dcr.add(Restrictions.or(Restrictions.not(Restrictions.in("respCode", new Object[]{"00 "})),Restrictions.in("tranFlag", new Object[]{"8"})));
			}
			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
					dcr.addOrder(Order.desc("localSysTime"));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.desc("traceNo"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
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
	public CurTranLs queryDetail(CurTranLs queryModel, UserData ud) throws OAException {
		CurTranLs model = new CurTranLs();
		try {
			log.info("CurTranLsHibernateHQLImpl.queryDetail开始调用：查询符合条件的数量。");

			DetachedCriteria dcr = DetachedCriteria.forClass(CurTranLs.class);
			// //按条件查询
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				dcr.add(Restrictions.eq("traceNo", queryModel.getTraceNo()));
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", queryModel.getMerchantId()));
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				dcr.add(Restrictions.eq("terminalId", queryModel.getTerminalId()));
			if (queryModel.getBatchNo() != null && !queryModel.getBatchNo().equals(""))
				dcr.add(Restrictions.eq("batchNo", queryModel.getBatchNo()));
			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
				dcr.add(Restrictions.eq("localSysDate", queryModel.getLocalSysDate()));
			if (queryModel.getLocalSysTime() != null && !queryModel.getLocalSysTime().equals(""))
				dcr.add(Restrictions.eq("localSysTime", queryModel.getLocalSysTime()));
			
			List list = findBYCriteria(dcr, ud);
			if (list.size() > 0)
				model = (CurTranLs) list.get(0);
			log.info("CurTranLsHibernateHQLImpl.queryDetail结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsHibernateHQLImpl.queryDetail调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return model;
	}

	@Override
	public List queryExport(CurTranLs queryModel, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("CurTranLsHibernateHQLImpl.query开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(CurTranLs.class);
			//冲正不显示
			dcr.add(Restrictions.not(Restrictions.in("tranType", new Object[] {new BigDecimal(41)})));

			// //按名程序模板查询
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				dcr.add(Restrictions.eq("tranType", queryModel.getTranType()));
//			if (queryModel.getLocalSysDateS() != null && !queryModel.getLocalSysDateS().equals(""))
//				dcr.add(Restrictions.ge("localSysDate", queryModel.getLocalSysDateS()));
//			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
//				dcr.add(Restrictions.le("localSysDate", queryModel.getLocalSysDateE()));
//			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
//				dcr.add(Restrictions.eq("localSysDate", queryModel.getLocalSysDate()));
			if (queryModel.getLocalSysDateE() != null && !queryModel.getLocalSysDateE().equals(""))
				dcr.add(Restrictions.le("localSysDate", queryModel.getLocalSysDateE()));
			if (queryModel.getLocalSysDate() != null && !queryModel.getLocalSysDate().equals(""))
				dcr.add(Restrictions.ge("localSysDate", queryModel.getLocalSysDate()));
			if (queryModel.getLocalSysTimeS() != null && !queryModel.getLocalSysTimeS().equals(""))
				dcr.add(Restrictions.ge("localSysTime", queryModel.getLocalSysTimeS()));
			if (queryModel.getLocalSysTimeE() != null && !queryModel.getLocalSysTimeE().equals(""))
				dcr.add(Restrictions.le("localSysTime", queryModel.getLocalSysTimeE()));
			if (queryModel.getMerchantName() != null && !queryModel.getMerchantName().equals(""))
				dcr.add(Restrictions.like("merchantName", queryModel.getMerchantName(), MatchMode.ANYWHERE));
			if (queryModel.getTraceNo() != null && !queryModel.getTraceNo().equals(""))
				dcr.add(Restrictions.eq("traceNo", queryModel.getTraceNo()));
			if (queryModel.getCardNo() != null && !queryModel.getCardNo().equals(""))
				dcr.add(Restrictions.like("cardNo", queryModel.getCardNo()+"%"));
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", queryModel.getMerchantId()));
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				dcr.add(Restrictions.eq("terminalId", queryModel.getTerminalId()));
			if (queryModel.getBankBatchNo() != null && !queryModel.getBankBatchNo().equals(""))
				dcr.add(Restrictions.eq("bankBatchNo", queryModel.getBankBatchNo()));
			if (queryModel.getTrace1() != null && !queryModel.getTrace1().equals(""))
				dcr.add(Restrictions.eq("trace1", queryModel.getTrace1()));
			if (queryModel.getTrace2() != null && !queryModel.getTrace2().equals(""))
				dcr.add(Restrictions.eq("trace2", queryModel.getTrace2()));
			if("0".equals(queryModel.getQueryType())){
				dcr.add(Restrictions.eq("respCode", "00 "));
				dcr.add(Restrictions.in("tranFlag", new Object[]{"0","1","2"}));
			}else if ("1".equals(queryModel.getQueryType())){
				dcr.add(Restrictions.or(Restrictions.not(Restrictions.in("respCode", new Object[]{"00 "})),Restrictions.in("tranFlag", new Object[]{"8"})));
			}
			dcr.addOrder(Order.desc("traceNo"));
			list = findBYCriteria(dcr, ud);
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
