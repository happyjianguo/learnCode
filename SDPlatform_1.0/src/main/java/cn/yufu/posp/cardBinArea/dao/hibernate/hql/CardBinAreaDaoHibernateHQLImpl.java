package cn.yufu.posp.cardBinArea.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.Map;
import cn.yufu.posp.cardBinArea.domain.model.CardBinArea;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;

public class CardBinAreaDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl
		implements CardBinAreaDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("CardBinArea");

	public CardBinAreaDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(CardBinArea newQueryModel, int startIndex,
			int maxresults, String sortfield, String sortType, UserData ud)
			throws OAException {
		List list = null;
		try {
			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(CardBinArea.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getCardBin() != null
					&& !newQueryModel.getCardBin().equals(""))
				dcr.add(Restrictions.eq("cardBin", newQueryModel.getCardBin()));
			// ����������
			if (newQueryModel.getAreaCode() != null
					&& !newQueryModel.getAreaCode().equals(""))
				dcr.add(Restrictions
						.eq("areaCode", newQueryModel.getAreaCode()));
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
				dcr.addOrder(Order.asc("updateDate"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log
					.error("CardBinAreaDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ�������򿨿�BINʱ�����쳣");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(CardBinArea queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer(
					"select count(*) from CardBinArea o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getCardBin() != null
					&& !queryModel.getCardBin().equals(""))
				bufferHql.append(" and o.cardBin = '" + queryModel.getCardBin()
						+ "'");

			// ���̻�״̬��ѯ
			if (queryModel.getAreaCode() != null
					&& !queryModel.getAreaCode().equals(""))
				bufferHql.append(" and o.areaCode = '"
						+ queryModel.getAreaCode() + "'");
			
			// ���̻�״̬��ѯ
			if (queryModel.getStatus() != null
					&& !queryModel.getStatus().equals(""))
				bufferHql.append(" and o.status = '"
						+ queryModel.getStatus() + "'");
			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log
					.info("CardBinAreaDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
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
			log.info("CardBinAreaDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				CardBinArea m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("����Ҫɾ����Ϣʱ�����쳣��");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer(
							"delete from CARD_BIN_AREA  where card_bin='"
									+ m.getCardBin() + "' ");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("��Ҫɾ������Ϣ�����ڣ�");
				}
			}
			log.info("CardBinAreaDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log
					.error("CardBinAreaDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public CardBinArea findItem(String newKey, UserData ud) throws OAException {
		CardBinArea model = null;
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(CardBinArea.class);
			if(newKey!=null&&!"".equals(newKey)){				
				cr.add(Restrictions.eq("cardBin", newKey));
			}
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (CardBinArea) list.get(0);
			}
			log.info("CardBinAreaDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ���򿨿�BINʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(CardBinArea newModel, UserData ud) throws OAException {
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			log.info("�޸� cardBinArea=" + newModel.getCardBin());
			try {
				update(newModel, ud);
				log
						.info("CardBinAreaDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
			} catch (Exception e) {
				throw new OAException("�޸����򿨿�BINʱ�����쳣��");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("CardBinAreaDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(CardBinArea newModel, UserData ud)
			throws OAException {
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("�������м�¼ʱ�����쳣");
			}

			log.info("CardBinAreaDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log
					.error("CardBinAreaDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("�������м�¼ʱ�����쳣");
		}
	}

	@Override
	public void update(Object entity, UserData ud) {
		CardBinArea bi = (CardBinArea) entity;
		String sql = "update CardBinArea set areaCode='" + bi.getAreaCode()
				+ "', updateOper='" + bi.getUpdateOper() + "',updateDate='"
				+ bi.getUpdateDate() + "',status='" + bi.getStatus() + "'";
		sql=sql+" where cardBin='"+bi.getCardBin()+"' ";
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
			log.info("CardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()��ʼ���á�");
			String sql = " select area_code,area_name FROM area_code_info";
			list = findBySQL(sql, null);
			Object[] obj = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				areaMap.put((String) obj[0], (String) obj[1]);
			}
			log.info("CardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()�������á�");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("CardBinAreaDaoHibernateHQLImpl.getAreaCodeMap()��ѯ������Ϣ�������쳣��");
			throw new OAException("getAreaCodeMap�����쳣");
		}
		return areaMap;
	}
	/**
	 * ��֤�̻�����Ƿ�Ψһ
	 */
	public CardBinArea findItemById(String newKey, UserData ud) throws OAException {
		CardBinArea model = null;
		try {
			log.info("CardBinAreaDaoHibernateHQLImpl.findItemById()��ʼ���á�");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(CardBinArea.class);
			cr.add(Restrictions.eq("cardBin", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (CardBinArea) list.get(0);
			log.info("CardBinAreaDaoHibernateHQLImpl.findItemById()�������á�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("CardBinAreaDaoHibernateHQLImpl.findItemById()�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤��BINΨһ��ʱ����");
		}
		return model;
	}
}
