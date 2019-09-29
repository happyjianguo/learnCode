package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bank.domain.model.BankInfoId;
import cn.yufu.posp.cardBinArea.domain.model.EdcCardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class EdcCardBinAreaDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements EdcCardBinAreaDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("EdcCardBinArea");

	public EdcCardBinAreaDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(EdcCardBinArea newQueryModel, int startIndex,
			int maxresults, String sortfield, String sortType, UserData ud)
			throws OAException {
		List list = null;
		try {
			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(EdcCardBinArea.class);

			if (newQueryModel.getCardBin() != null
					&& !newQueryModel.getCardBin().equals(""))
				dcr.add(Restrictions.eq("cardBin", newQueryModel.getCardBin()));
			
			if (newQueryModel.getMerchantId() != null
					&& !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions
						.eq("merchantId", newQueryModel.getMerchantId()));
			
			if (newQueryModel.getTerminalId() != null
					&& !newQueryModel.getTerminalId().equals(""))
				dcr.add(Restrictions
						.eq("terminalId", newQueryModel.getTerminalId()));
			
			if (newQueryModel.getStatus() != null
					&& !newQueryModel.getStatus().equals(""))
				dcr.add(Restrictions
						.eq("status", newQueryModel.getStatus()));			

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("cardBin"));
				dcr.addOrder(Order.asc("merchantId"));
				dcr.addOrder(Order.asc("terminalId"));

			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log
					.error("EdcCardBinAreaDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ�������򿨿�BINʱ�����쳣");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(EdcCardBinArea queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer(
					"select count(*) from EdcCardBinArea o where 1=1");

			if (queryModel.getCardBin() != null
					&& !queryModel.getCardBin().equals(""))
				bufferHql.append(" and o.cardBin = '" + queryModel.getCardBin()
						+ "'");

			if (queryModel.getMerchantId() != null
					&& !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '"
						+ queryModel.getMerchantId() + "'");

			if (queryModel.getTerminalId() != null
					&& !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and o.terminalId = '"
						+ queryModel.getTerminalId() + "'");
			
			
			if (queryModel.getStatus() != null
					&& !queryModel.getStatus().equals(""))
				bufferHql.append(" and o.status = '"
						+ queryModel.getStatus() + "'");
			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log
					.info("EdcCardBinAreaDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ���򿨿�BIN����ʱ�����쳣");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				EdcCardBinArea m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("����Ҫɾ����Ϣʱ�����쳣��");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer(
							"delete from EDC_CARD_BIN_AREA  where card_bin='"
									+ m.getCardBin() + "' and merchant_id='"
									+ m.getMerchantId() + "' and terminal_id='"
									+ m.getTerminalId() + "'");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("��Ҫɾ������Ϣ�����ڣ�");
				}
			}
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log
					.error("EdcCardBinAreaDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public EdcCardBinArea findItem(String newKey, UserData ud) throws OAException {
		EdcCardBinArea model = null;
		try {
			log.info("BankInfoDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(EdcCardBinArea.class);
			String[] params = newKey.split(",");
			log.info("param:" + params[0] + "\t" + params[1]+ "\t" + params[2]);
//			if (params[0] != null && !"".equals(params[0])) {
//				int i = params[0].length();
//				if (i < 11) {
//					for (int j = 1; j <= 11 - i; j++) {
//						params[0] += " ";
//					}
//				}
//			}
//			if (params[1] != null && !"".equals(params[1])) {
//				int i = params[1].length();
//				if (i < 2) {
//					for (int j = 1; j <= 2 - i; j++) {
//						params[1] += " ";
//					}
//				}
//			}
			cr.add(Restrictions.eq("cardBin", params[0]));
			cr.add(Restrictions.eq("merchantId", params[1]));
			cr.add(Restrictions.eq("terminalId", params[2]));

			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (EdcCardBinArea) list.get(0);
			}
			log.info("BankInfoDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BankInfoDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ���л�����Ϣʱ���쳣��");
		}

		return model;
	}


	/**
	 *�޸�һ����¼
	 */
	public void saveItem(EdcCardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			log.info("�޸� EdcCardBinArea=" + newModel.getCardBin());
			try {
				update(newModel, ud);
				log
						.info("EdcCardBinAreaDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
			} catch (Exception e) {
				throw new OAException("�޸����򿨿�BINʱ�����쳣��");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("EdcCardBinAreaDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(EdcCardBinArea newModel, UserData ud)
			throws OAException {
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("�������м�¼ʱ�����쳣");
			}

			log.info("EdcCardBinAreaDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log
					.error("EdcCardBinAreaDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("�������м�¼ʱ�����쳣");
		}
	}

	@Override
	public void update(Object entity, UserData ud) {
		EdcCardBinArea bi = (EdcCardBinArea) entity;
		String sql = "update EdcCardBinArea set updateOper='"
				+ bi.getUpdateOper() + "',updateDate='" + bi.getUpdateDate()
				+ "',status='" + bi.getStatus() + "'";
		sql = sql + " where cardBin='" + bi.getCardBin() + "' and merchantId='"
				+ bi.getMerchantId() + "' and terminalId='"
				+ bi.getTerminalId() + "' ";
		log.info("�޸�sql==" + sql);
		bulkUpdate(sql, ud);
	}
		
	/**
	 * ��ȡ�������MAP
	 * @return
	 * @throws OAException
	 */
	public Map getAreaCodeMap() throws OAException {
		List list = null;
		Map<String, String> areaMap = new HashMap<String, String>();
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()��ʼ���á�");
			String sql = " select area_code,area_name FROM area_code_info";
			list = findBySQL(sql, null);
			Object[] obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				areaMap.put((String) obj[0], (String) obj[1]);
			}
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()�������á�");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()��ѯ������Ϣ�������쳣��");
			throw new OAException("getAreaCodeMap�����쳣");
		}
		return areaMap;
	}
	/**
	 * ��֤�̻�����Ƿ�Ψһ
	 */
	public EdcCardBinArea findItemById(String newKey,String merchantId,String terminalId, UserData ud) throws OAException {
		EdcCardBinArea model = null;
		try {
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.findItemById()��ʼ���á�");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(EdcCardBinArea.class);
			cr.add(Restrictions.eq("cardBin", newKey));
			cr.add(Restrictions.eq("merchantId", merchantId));
			cr.add(Restrictions.eq("terminalId", terminalId));

			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (EdcCardBinArea) list.get(0);
			log.info("EdcCardBinAreaDaoHibernateHQLImpl.findItemById()�������á�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("EdcCardBinAreaDaoHibernateHQLImpl.findItemById()�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤��BINΨһ��ʱ����");
		}
		return model;
	}
}
