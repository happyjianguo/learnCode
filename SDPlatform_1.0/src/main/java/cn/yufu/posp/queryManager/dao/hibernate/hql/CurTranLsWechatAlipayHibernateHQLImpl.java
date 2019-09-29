/**
 *包名:cn.yufu.posp.queryManager.dao.hibernate.hql
 *描述:package cn.yufu.posp.queryManager.dao.hibernate.hql;
 */
package cn.yufu.posp.queryManager.dao.hibernate.hql;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.queryManager.domain.model.TblExpCurTranLog;

/**
 * CurTranLsWechatAlipayHibernateHQLImpl.java
 * 版权所有(C) 2017 裕福控股有限公司
 * 创建:gll 
 * 时间:2017年7月21日
 * 描述:HQL实现
 */
public class CurTranLsWechatAlipayHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements CurTranLsWechatAlipayHibernateHQL {

	private static final Log log = LogFactory.getLog("query");
	
	@SuppressWarnings("rawtypes")
	@Override
	public int queryCount(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException {
		int count = 0;
		try {
			// 按车查询
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount开始调用：查询符合条件的数量。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblExpCurTranLog where 1=1 ");
			// //按条件查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and merchantId = " + queryModel.getMerchantId() + " ");
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and terminalId = " + queryModel.getTerminalId() + " ");
			if (startDate != null && !startDate.equals(""))
				bufferHql.append(" and sysTimeStamp >= " + startDate + "000000");
			if (endDate != null && !endDate.equals(""))
				bufferHql.append(" and sysTimeStamp <= " + endDate + "235959");
			if (queryModel.getTranRrn() != null && !queryModel.getTranRrn().equals(""))
				bufferHql.append(" and tranRrn = '" + queryModel.getTranRrn() + "' ");
			if (queryModel.getScanCode() != null && !queryModel.getScanCode().equals(""))
				bufferHql.append(" and scanCode = '" + queryModel.getScanCode() + "'");
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and tranType = '" + queryModel.getTranType() + "'");
			if (queryModel.getAcqRespCode() != null && !queryModel.getAcqRespCode().equals("")){
				if("200".equals(queryModel.getAcqRespCode().toString())){
					bufferHql.append(" and acqRespCode = '" + queryModel.getAcqRespCode() + "'");
				}else{
					bufferHql.append(" and acqRespCode != '" + new BigDecimal(200) + "'");
				}
			}
			String hql = bufferHql.toString();
			log.info("流水查询 HQL语句: " + hql );
			List list = findByHQL(hql, sessionUserData);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsWechatAlipayHibernateHQLImpl.queryCount调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return count;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List query(TblExpCurTranLog queryModel, int startIndex, int pageSize, String orderField, String orderType,
			UserData sessionUserData, String startDate, String endDate) throws OAException {
		List list = null;
		try {
//			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query开始调用：通过查询条件分页查询信息。");
//
//			DetachedCriteria dcr = DetachedCriteria.forClass(TblExpCurTranLog.class);
//			// //按名程序模板查询
//			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
//				dcr.add(Restrictions.eq("merchantId" ,queryModel.getMerchantId()));
//			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
//				dcr.add(Restrictions.eq("terminalId" ,queryModel.getTerminalId()));
//			if (startDate != null && !startDate.equals(""))
//				dcr.add(Restrictions.ge("sysTimeStamp" ,startDate+"000000"));
//			if (endDate != null && !endDate.equals(""))
//				dcr.add(Restrictions.le("sysTimeStamp" ,endDate+"235959"));
//			if (queryModel.getTranRrn() != null && !queryModel.getTranRrn().equals(""))
//				dcr.add(Restrictions.eq("tranRrn" ,queryModel.getTranRrn()));
//			if (queryModel.getScanCode() != null && !queryModel.getScanCode().equals(""))
//				dcr.add(Restrictions.eq("scanCode" ,queryModel.getScanCode()));
//			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
//				dcr.add(Restrictions.eq("tranType" ,queryModel.getTranType()));
//			if (queryModel.getAcqRespCode() != null && !queryModel.getAcqRespCode().equals("")){
//				if("200".equals(queryModel.getAcqRespCode().toString())){
//					dcr.add(Restrictions.eq("acqRespCode" ,queryModel.getAcqRespCode()));
//				}else{
//					dcr.add(Restrictions.not(Restrictions.in("acqRespCode",new Object[] {new BigDecimal(200)})));
//				}
//			}
//			// 添加排序信息
//			if (orderType != null && orderField != null) {
//				if (orderType.equals("asc"))
//					dcr.addOrder(Order.asc(orderField));
//				else
//					dcr.addOrder(Order.desc(orderField));
//			} else {
//				// 按 id 排序
////				dcr.addOrder(Order.desc("cardNo"));
//				dcr.addOrder(Order.desc("terminalId"));
//				dcr.addOrder(Order.desc("sysTimeStamp"));
//			}
//			list = findBYCriteria(dcr, startIndex, pageSize, sessionUserData);
//			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query结束调用：通过查询条件分页查询信息。");
			// 按车查询
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount开始调用：查询符合条件的数量。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select nvl(merchantId,' '),"
					+ "nvl(terminalId,' '),nvl(tranRrn,' '),nvl(tranType,0),nvl(sysOrderId,' '),"
					+ "nvl(sysVoidOrderId,' '),nvl(sysOrderDtl,' '),nvl(tranAmt,0.00),nvl(tranVoidAmt,0.00),"
					+ "nvl(sysTimeStamp,' '),nvl(acqRespMsg,' ') from TblExpCurTranLog where 1=1 ");
			// //按条件查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and merchantId = " + queryModel.getMerchantId() + " ");
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and terminalId = " + queryModel.getTerminalId() + " ");
			if (startDate != null && !startDate.equals(""))
				bufferHql.append(" and sysTimeStamp >= " + startDate + "000000");
			if (endDate != null && !endDate.equals(""))
				bufferHql.append(" and sysTimeStamp <= " + endDate + "235959");
			if (queryModel.getTranRrn() != null && !queryModel.getTranRrn().equals(""))
				bufferHql.append(" and tranRrn = '" + queryModel.getTranRrn() + "' ");
			if (queryModel.getScanCode() != null && !queryModel.getScanCode().equals(""))
				bufferHql.append(" and scanCode = '" + queryModel.getScanCode() + "'");
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and tranType = '" + queryModel.getTranType() + "'");
			if (queryModel.getAcqRespCode() != null && !queryModel.getAcqRespCode().equals("")){
				if("200".equals(queryModel.getAcqRespCode().toString())){
					bufferHql.append(" and acqRespCode = '" + queryModel.getAcqRespCode() + "'");
				}else{
					bufferHql.append(" and acqRespCode != '" + new BigDecimal(200) + "'");
				}
			}
			bufferHql.append(" order by terminalId , sysTimeStamp desc");
			String hql = bufferHql.toString();
			log.info("流水查询 HQL语句: " + hql );
//			list = findBySQL(hql, sessionUserData);
//			list = findByHQL(hql, startIndex, pageSize, sessionUserData);
			list = objectTo(findByHQL(hql, startIndex, pageSize, sessionUserData));
			
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsWechatAlipayHibernateHQLImpl.query通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询模板对象个数时抛异常！");
		}
		return list;
	}
	
	public List<TblExpCurTranLog> objectTo(List<Object> list ){
		List<TblExpCurTranLog> li = new LinkedList<TblExpCurTranLog>();
		for(int i=0;i<list.size(); i++){
			TblExpCurTranLog tblExpCurTranLog = new TblExpCurTranLog();
			Object[] objects=(Object[])list.get(i);
			tblExpCurTranLog.setMerchantId(objects[0].toString());
			tblExpCurTranLog.setTerminalId(objects[1].toString());
			tblExpCurTranLog.setTranRrn(objects[2].toString());
			tblExpCurTranLog.setTranType(new BigDecimal(objects[3].toString()));
			tblExpCurTranLog.setSysOrderId(objects[4].toString());
			tblExpCurTranLog.setSysVoidOrderId(objects[5].toString());
			tblExpCurTranLog.setSysOrderDtl(objects[6].toString());
			tblExpCurTranLog.setTranAmt(new Double(objects[7].toString()));
			tblExpCurTranLog.setTranVoidAmt(new Double(objects[8].toString()));
			tblExpCurTranLog.setSysTimeStamp(objects[9].toString());
			tblExpCurTranLog.setAcqRespMsg(objects[10].toString());
			li.add(tblExpCurTranLog);
		}
		return li;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List queryExport(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException {
		List list = null;
		try{
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query开始调用：通过查询条件分页查询信息。");

//			DetachedCriteria dcr = DetachedCriteria.forClass(TblExpCurTranLog.class);
//			// //按名程序模板查询
//			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
//				dcr.add(Restrictions.eq("merchantId" ,queryModel.getMerchantId()));
//			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
//				dcr.add(Restrictions.eq("terminalId" ,queryModel.getTerminalId()));
//			if (startDate != null && !startDate.equals(""))
//				dcr.add(Restrictions.ge("sysTimeStamp" ,startDate+"000000"));
//			if (endDate != null && !endDate.equals(""))
//				dcr.add(Restrictions.le("sysTimeStamp" ,endDate+"235959"));
//			if (queryModel.getTranRrn() != null && !queryModel.getTranRrn().equals(""))
//				dcr.add(Restrictions.eq("tranRrn" ,queryModel.getTranRrn()));
//			if (queryModel.getScanCode() != null && !queryModel.getScanCode().equals(""))
//				dcr.add(Restrictions.eq("scanCode" ,queryModel.getScanCode()));
//			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
//				dcr.add(Restrictions.eq("tranType" ,queryModel.getTranType()));
//			if (queryModel.getAcqRespCode() != null && !queryModel.getAcqRespCode().equals("")){
//				if("200".equals(queryModel.getAcqRespCode().toString())){
//					dcr.add(Restrictions.eq("acqRespCode" ,queryModel.getAcqRespCode()));
//				}else{
//					dcr.add(Restrictions.not(Restrictions.in("acqRespCode",new Object[] {new BigDecimal(200)})));
//				}
//			}
//			dcr.addOrder(Order.desc("terminalId"));
//			dcr.addOrder(Order.desc("sysTimeStamp"));
//			list = findBYCriteria(dcr, sessionUserData);
			// 按车查询
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount开始调用：查询符合条件的数量。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select nvl(merchantId,' '),"
					+ "nvl(terminalId,' '),nvl(tranRrn,' '),nvl(tranType,0),nvl(sysOrderId,' '),"
					+ "nvl(sysVoidOrderId,' '),nvl(sysOrderDtl,' '),nvl(tranAmt,0.00),nvl(tranVoidAmt,0.00),"
					+ "nvl(sysTimeStamp,' '),nvl(acqRespMsg,' ') from TblExpCurTranLog where 1=1 ");
			// //按条件查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and merchantId = " + queryModel.getMerchantId() + " ");
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and terminalId = " + queryModel.getTerminalId() + " ");
			if (startDate != null && !startDate.equals(""))
				bufferHql.append(" and sysTimeStamp >= " + startDate + "000000");
			if (endDate != null && !endDate.equals(""))
				bufferHql.append(" and sysTimeStamp <= " + endDate + "235959");
			if (queryModel.getTranRrn() != null && !queryModel.getTranRrn().equals(""))
				bufferHql.append(" and tranRrn = '" + queryModel.getTranRrn() + "' ");
			if (queryModel.getScanCode() != null && !queryModel.getScanCode().equals(""))
				bufferHql.append(" and scanCode = '" + queryModel.getScanCode() + "'");
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and tranType = '" + queryModel.getTranType() + "'");
			if (queryModel.getAcqRespCode() != null && !queryModel.getAcqRespCode().equals("")){
				if("200".equals(queryModel.getAcqRespCode().toString())){
					bufferHql.append(" and acqRespCode = '" + queryModel.getAcqRespCode() + "'");
				}else{
					bufferHql.append(" and acqRespCode != '" + new BigDecimal(200) + "'");
				}
			}
			bufferHql.append(" order by terminalId , sysTimeStamp desc");
			String hql = bufferHql.toString();
			log.info("流水查询 HQL语句: " + hql );
			list = objectTo(findByHQL(hql, sessionUserData));
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query结束调用：通过查询条件分页查询信息。");
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
