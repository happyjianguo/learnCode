package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;
import cn.yufu.posp.merchant.domain.model.MerchantRefundModel;
import cn.yufu.posp.merchant.domain.model.MerchantUser;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;

public class MerchantUserDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantUserDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantUserDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantUser newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantUser.class);

			// ���̻���Ų�ѯ
			// if (newQueryModel.getMerchantId() != null &&
			// !newQueryModel.getMerchantId().equals(""))
			// dcr.add(Restrictions.eq("merchantId",
			// newQueryModel.getMerchantId()));
			//
			// // ���̻�״̬��ѯ
			// if (newQueryModel.getMerchantStat() != null &&
			// !newQueryModel.getMerchantStat().equals(""))
			// dcr.add(Restrictions.eq("merchantStat",
			// newQueryModel.getMerchantStat()));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("loginId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("MerchantDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(MerchantUser queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantUser o where 1=1");

			// ���̻���Ų�ѯ
			// if (queryModel.getMerchantId() != null &&
			// !queryModel.getMerchantId().equals(""))
			// bufferHql.append(" and o.merchantId = '" +
			// queryModel.getMerchantId() + "'");
			//
			// // ���̻�״̬��ѯ
			// if (queryModel.getMerchantStat() != null &&
			// !queryModel.getMerchantStat().equals(""))
			// bufferHql.append(" and o.merchantStat = '" +
			// queryModel.getMerchantStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *��ʾһ����¼
	 */
	public MerchantUser findItem(String newKey, UserData ud) throws OAException {
		MerchantUser model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantUser.class);
			cr.add(Restrictions.eq("loginId", newKey));
			List list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantUser) list.get(0);
			Criteria cr1 = session.createCriteria(MerchantUserRela.class);
			cr1.add(Restrictions.eq("merchantUserId", newKey));
			List userRelaList = findByCriteria(cr1, ud);
			MerchantUserRela userRela = new MerchantUserRela();
			if (userRelaList.size() > 0) {
				userRela = (MerchantUserRela) userRelaList.get(0);
				model.setMerchantIds(userRela.getMerchantId());
			}
			log.info("MerchantDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)���ÿ�ʼ��ɾ��һ����¼��");
			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantUser mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// ɾ��������Ϣ
				delete(mbm, ud);
				String hql = " from MerchantUserRela where merchantUserId ='" + newKeys.get(i)+"'";
				delete(hql, ud);
			}

			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantUser.deleteItem(List newKeys, UserData ud)ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ���̻�������Ϣʱ���쳣��");
		}
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantUser newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);
			String[] merchantIds = newModel.getMerchantIds().split(",");
			MerchantUserRela userRela = new MerchantUserRela();
			delete(findUserRelaItem(newModel.getLoginId(), ud), ud);
			for (int i = 0; i < merchantIds.length; i++) {
				userRela = new MerchantUserRela();
				userRela.setMerchantUserId(newModel.getLoginId());
				userRela.setMerchantId(merchantIds[i]);
				save(userRela, ud);
			}
			log.info("MerchantDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸��̻�����ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MerchantUser newModel, UserData ud) throws OAException {

		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");
			Transaction t = this.getSession().beginTransaction();

			save(newModel, ud);
			String[] merchantIds = newModel.getMerchantIds().split(",");
			for (int i = 0; i < merchantIds.length; i++) {
				MerchantUserRela userRela = new MerchantUserRela();
				userRela.setMerchantId(merchantIds[i]);
				userRela.setMerchantUserId(newModel.getLoginId());
				save(userRela, ud);
			}
			t.commit();
			log.info("MerchantDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}
	
	/**
	 *��ʾһ����¼
	 */
	public MerchantUserRela findUserRelaItem(String newKey, UserData ud) throws OAException {
		MerchantUserRela model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantUserRela.class);
			cr.add(Restrictions.eq("merchantUserId", newKey));
			List userRelaList = findByCriteria(cr, ud);
			if (userRelaList.size() > 0) {
				model = (MerchantUserRela) userRelaList.get(0);
			}
			log.info("MerchantDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}
}
