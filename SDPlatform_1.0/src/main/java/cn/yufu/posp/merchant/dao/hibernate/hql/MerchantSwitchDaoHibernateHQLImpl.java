package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantSwitchModel;

public class MerchantSwitchDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantSwitchDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantSwitchDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantSwitchModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantSwitchModel.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getQueryMerId() != null && !newQueryModel.getQueryMerId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getQueryMerId()));

			// ����������
			if (newQueryModel.getQueryBank() != null && !newQueryModel.getQueryBank().equals(""))
				dcr.add(Restrictions.eq("bankType", newQueryModel.getQueryBank()));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("merchantId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			// ���ò���
			MerchantSwitchModel mc = null;
			for (int i = 0; i < list.size(); i++) {
				mc = (MerchantSwitchModel) list.get(i);
				mc.setBankId(mc.getBankType());
			}
			log.info("MerchantSwitchDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantlardDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ�̻�ת��ʱ���쳣��");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(MerchantSwitchModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantSwitchModel o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getQueryMerId() != null && !queryModel.getQueryMerId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getQueryMerId() + "'");

			// ���̻�״̬��ѯ
			if (queryModel.getQueryBank() != null && !queryModel.getQueryBank().equals(""))
				bufferHql.append(" and o.bankType = '" + queryModel.getQueryBank() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantSwitchDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ�̻�ת���������ʱ���쳣��");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantSwitchModel m = findItem(new String(newKeys.get(i) + ""), ud);
				StringBuffer bufferHql = new StringBuffer("delete from MERCHANT_SWITCH  where MERCHANT_ID='" + m.getMerchantId() + "' and" + " BANK_TYPE='"
						+ m.getBankType() + "'");
				String hql = bufferHql.toString();
				saveOrUpdateOrDeleteBySQL(hql, ud);

			}

			log.info("MerchantSwitchDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantSwitchDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ���̻�ת��ʱ���쳣��");
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public MerchantSwitchModel findItem(String newKey, UserData ud) throws OAException {
		MerchantSwitchModel model = null;
		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantSwitchModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("merchantId", params[0]));
			cr.add(Restrictions.eq("bankType", params[1]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (MerchantSwitchModel) list.get(0);
				model.setBankId(model.getBankType());
			}
			log.info("MerchantSwitchDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ�����̻�ת��ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantSwitchModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			String sql = new String("update MerchantSwitchModel set othMerchantId='" + newModel.getOthMerchantId() + "',othMcc='" + newModel.getOthMcc() + "'"
					+ " where merchantId='" + newModel.getMerchantId() + "' and" + " bankType='" + newModel.getBankId() + "'");
			/*
			 * DetachedCriteria dcr =
			 * DetachedCriteria.forClass(MerchantSwitchModel.class);
			 * 
			 * dcr.add(Restrictions.eq("merchantId", newModel.getMerchantId()));
			 * dcr.add(Restrictions.eq("bankType", newModel.getBankType()));
			 * dcr.add(Restrictions.eq("cardType", newModel.getCardType()));
			 */
			bulkUpdate(sql, ud);
			log.info("MerchantSwitchDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸��̻�ת��ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MerchantSwitchModel newModel, UserData ud) throws OAException {
		// ��֤�̻����Ƿ��Ѿ����������ݿ���
		MerchantBaseModel msm = findItemById(newModel.getMerchantId(), ud);
		if (msm == null) {
			throw new OAException("�̻����:" + newModel.getMerchantId() + " �ļ�¼�����ݿ��в����ڣ�");
		}

		// ��֤����Ƿ��Ѿ�����
		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey = newModel.getMerchantId() + "," + newModel.getBankType();
			MerchantSwitchModel sm = findItem(newKey, ud);
			log.info("MerchantSwitchDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {

				throw new OAException("���:" + newModel.getMerchantId() + ",�������ͣ�" + newModel.getBankType() + " �ļ�¼�Ѿ������ݿ��д��ڣ�");

			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("MerchantSwitchDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("MerchantSwitchDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantSwitchDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("���Ӽ�¼ʱ����");
		}
	}

	/**
	 * ��ѯ�̻�����
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		String merName = "";
		Session session = getSession();
		Criteria cr = session.createCriteria(MerchantBaseModel.class);
		cr.add(Restrictions.eq("merchantId", merchantId));
		List list = findByCriteria(cr, ud);
		if (list != null && list.size() > 0) {
			MerchantBaseModel m = (MerchantBaseModel) list.get(0);
			merName = m.getMerchantCname();
		}
		return merName;
	}

	/**
	 * ��֤�̻�����Ƿ����
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItemById()��ʼ���ã���֤�̻�����Ƿ�Ψһ��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findItemById()�������ã���֤�̻�����Ƿ�Ψһ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItemById()��֤�̻�����Ƿ�Ψһ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻����Ψһ��ʱ����");
		}
		return model;
	}
}
