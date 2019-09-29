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
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;

public class MerchantCardDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantCardDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCardDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantCardModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantCardModel.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getqMerId() != null && !newQueryModel.getqMerId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getqMerId()));

			// ����������
			if (newQueryModel.getqMerBank() != null && !newQueryModel.getqMerBank().equals(""))
				dcr.add(Restrictions.eq("bankType", newQueryModel.getqMerBank()));

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
			MerchantCardModel mc = new MerchantCardModel();
			for (int i = 0; i < list.size(); i++) {
				mc = (MerchantCardModel) list.get(i);
				mc.setQueryMerchant(mc.getMerchantId());
				mc.setQueryBankType(mc.getBankType());
				mc.setQueryCardType(mc.getCardType());
			}
			MerchantCardModel m = new MerchantCardModel();
			for (int i = 0; i < list.size(); i++) {
				m = (MerchantCardModel) list.get(i);

//				System.out.println(i + ": merchantId: " + m.getMerchantId());
//				System.out.println(i + ": bankType: " + m.getBankType());
//				System.out.println(i + ": cardType: " + m.getCardType());
			}
			log.info("MerchantCardDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantlardDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ������ʱ���쳣��");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(MerchantCardModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantCardModel o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getqMerId() != null && !queryModel.getqMerId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getqMerId() + "'");

			// ���̻�״̬��ѯ
			if (queryModel.getqMerBank() != null && !queryModel.getqMerBank().equals(""))
				bufferHql.append(" and o.bankType = '" + queryModel.getqMerBank() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantCardDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ������������ʱ���쳣��");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantCardModel m = findItem(new String(newKeys.get(i) + ""), ud);
				StringBuffer bufferHql = new StringBuffer("delete from MERCHANT_CARD  where MERCHANT_ID='" + m.getMerchantId() + "' and" + " BANK_TYPE='"
						+ m.getBankType() + "' and CARD_TYPE='" + m.getCardType() + "'");
				String hql = bufferHql.toString();
				saveOrUpdateOrDeleteBySQL(hql, ud);

			}

			log.info("MerchantCardDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantCardDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ��������ʱ���쳣��");
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public MerchantCardModel findItem(String newKey, UserData ud) throws OAException {
		MerchantCardModel model = null;
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantCardModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("merchantId", params[0]));
			cr.add(Restrictions.eq("bankType", params[1]));
			cr.add(Restrictions.eq("cardType", params[2]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (MerchantCardModel) list.get(0);
				model.setQueryMerchant(model.getMerchantId());
				model.setQueryBankType(model.getBankType());
				model.setQueryCardType(model.getCardType());
			}

			log.info("MerchantCardDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ���������ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantCardModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			String sql = new String("update MerchantCardModel set cardType='" + newModel.getCardType() + "',bankType='" + newModel.getBankType()
					+ "',cardDiscount=" + newModel.getCardDiscount() + "," + "cardPiece=" + newModel.getCardPiece() + ",cardFeeMax=" + newModel.getCardFeeMax()
					+ "," + "cardFeeMin=" + newModel.getCardFeeMin() + ",cardStat='" + newModel.getCardStat() + "'," + "updateOper='"
					+ newModel.getUpdateOper() + "',updateDate='" + newModel.getUpdateDate() + "'," + "updateTime='" + newModel.getUpdateTime()
					+ "' where merchantId='" + newModel.getQueryMerchant() + "' and" + " cardType='" + newModel.getQueryCardType() + "' and bankType='"
					+ newModel.getQueryBankType() + "'");
			/*
			 * DetachedCriteria dcr =
			 * DetachedCriteria.forClass(MerchantCardModel.class);
			 * 
			 * dcr.add(Restrictions.eq("merchantId", newModel.getMerchantId()));
			 * dcr.add(Restrictions.eq("bankType", newModel.getBankType()));
			 * dcr.add(Restrictions.eq("cardType", newModel.getCardType()));
			 */
			bulkUpdate(sql, ud);
			log.info("MerchantCardDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�������ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MerchantCardModel newModel, UserData ud) throws OAException {
		// ��֤�̻����Ƿ��Ѿ����������ݿ���
		MerchantBaseModel msm = findItemById(newModel.getMerchantId(), ud);
		if (msm == null) {
			throw new OAException("�̻����:" + newModel.getMerchantId() + " �ļ�¼�����ݿ��в����ڣ�");
		}

		// ��֤����Ƿ��Ѿ�����
		try {
			log.info("MerchantCardDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey = newModel.getMerchantId() + "," + newModel.getBankType() + "," + newModel.getCardType();
			MerchantCardModel sm = findItem(newKey, ud);
			log.info("MerchantCardDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {

				throw new OAException("���:" + newModel.getMerchantId() + ",�������ͣ�" + newModel.getBankType() + ",�����ͣ�" + newModel.getCardType()
						+ " �ļ�¼�Ѿ������ݿ��д��ڣ�");

			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("MerchantCardDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("MerchantCardDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCardDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("���������ʱ���쳣��");
		}
	}

	/**
	 * ��װ�̻�����
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
