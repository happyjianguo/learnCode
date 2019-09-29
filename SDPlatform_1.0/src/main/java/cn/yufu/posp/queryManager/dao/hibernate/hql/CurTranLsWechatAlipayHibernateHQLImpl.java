/**
 *����:cn.yufu.posp.queryManager.dao.hibernate.hql
 *����:package cn.yufu.posp.queryManager.dao.hibernate.hql;
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
 * ��Ȩ����(C) 2017 ԣ���ع����޹�˾
 * ����:gll 
 * ʱ��:2017��7��21��
 * ����:HQLʵ��
 */
public class CurTranLsWechatAlipayHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements CurTranLsWechatAlipayHibernateHQL {

	private static final Log log = LogFactory.getLog("query");
	
	@SuppressWarnings("rawtypes")
	@Override
	public int queryCount(TblExpCurTranLog queryModel, UserData sessionUserData, String startDate, String endDate) throws OAException {
		int count = 0;
		try {
			// ������ѯ
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount��ʼ���ã���ѯ����������������");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblExpCurTranLog where 1=1 ");
			// //��������ѯ
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
			log.info("��ˮ��ѯ HQL���: " + hql );
			List list = findByHQL(hql, sessionUserData);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsWechatAlipayHibernateHQLImpl.queryCount����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯģ�����ʱ���쳣��");
		}
		return count;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List query(TblExpCurTranLog queryModel, int startIndex, int pageSize, String orderField, String orderType,
			UserData sessionUserData, String startDate, String endDate) throws OAException {
		List list = null;
		try {
//			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
//
//			DetachedCriteria dcr = DetachedCriteria.forClass(TblExpCurTranLog.class);
//			// //��������ģ���ѯ
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
//			// ���������Ϣ
//			if (orderType != null && orderField != null) {
//				if (orderType.equals("asc"))
//					dcr.addOrder(Order.asc(orderField));
//				else
//					dcr.addOrder(Order.desc(orderField));
//			} else {
//				// �� id ����
////				dcr.addOrder(Order.desc("cardNo"));
//				dcr.addOrder(Order.desc("terminalId"));
//				dcr.addOrder(Order.desc("sysTimeStamp"));
//			}
//			list = findBYCriteria(dcr, startIndex, pageSize, sessionUserData);
//			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			// ������ѯ
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount��ʼ���ã���ѯ����������������");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select nvl(merchantId,' '),"
					+ "nvl(terminalId,' '),nvl(tranRrn,' '),nvl(tranType,0),nvl(sysOrderId,' '),"
					+ "nvl(sysVoidOrderId,' '),nvl(sysOrderDtl,' '),nvl(tranAmt,0.00),nvl(tranVoidAmt,0.00),"
					+ "nvl(sysTimeStamp,' '),nvl(acqRespMsg,' ') from TblExpCurTranLog where 1=1 ");
			// //��������ѯ
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
			log.info("��ˮ��ѯ HQL���: " + hql );
//			list = findBySQL(hql, sessionUserData);
//			list = findByHQL(hql, startIndex, pageSize, sessionUserData);
			list = objectTo(findByHQL(hql, startIndex, pageSize, sessionUserData));
			
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CurTranLsWechatAlipayHibernateHQLImpl.queryͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯģ��������ʱ���쳣��");
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
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

//			DetachedCriteria dcr = DetachedCriteria.forClass(TblExpCurTranLog.class);
//			// //��������ģ���ѯ
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
			// ������ѯ
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.queryCount��ʼ���ã���ѯ����������������");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select nvl(merchantId,' '),"
					+ "nvl(terminalId,' '),nvl(tranRrn,' '),nvl(tranType,0),nvl(sysOrderId,' '),"
					+ "nvl(sysVoidOrderId,' '),nvl(sysOrderDtl,' '),nvl(tranAmt,0.00),nvl(tranVoidAmt,0.00),"
					+ "nvl(sysTimeStamp,' '),nvl(acqRespMsg,' ') from TblExpCurTranLog where 1=1 ");
			// //��������ѯ
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
			log.info("��ˮ��ѯ HQL���: " + hql );
			list = objectTo(findByHQL(hql, sessionUserData));
			log.info("CurTranLsWechatAlipayHibernateHQLImpl.query�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
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
