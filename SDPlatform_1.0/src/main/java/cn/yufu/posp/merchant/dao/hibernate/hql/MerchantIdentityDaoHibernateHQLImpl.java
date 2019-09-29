package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;

public class MerchantIdentityDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantIdentityDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantIdentityDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantIdentity newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantIdentity.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));
			if (newQueryModel.getClassType() != null && !newQueryModel.getClassType().equals(""))
				dcr.add(Restrictions.like("classType", newQueryModel.getClassType(),MatchMode.ANYWHERE));

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

			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(MerchantIdentity queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantIdentity o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");
			if (queryModel.getClassType() != null && !queryModel.getClassType().equals(""))
				bufferHql.append(" and o.classType = '" + queryModel.getClassType() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 * ��ѯ��Ʒ��������
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMccsName(String mcc) {
		List<String> mccNameList = new ArrayList<String>();

		String sql = "select MCC_NAME from MCC_PARAM where MCC='" + mcc + "'";
		mccNameList = this.getHibernateTemplate().find(sql);
		return mccNameList;
	}

	/**
	 *��ʾһ����¼
	 */
	public MerchantIdentity findItem(String newKey, UserData ud) throws OAException {
		MerchantIdentity model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantIdentity.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantIdentity) list.get(0);

			log.info("MerchantIdentityDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
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
			log.info("MerchantIdentityDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)���ÿ�ʼ��ɾ��һ����¼��");

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantIdentity mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// ɾ��������Ϣ
				delete(mbm, ud);
			}

			log.info("MerchantIdentityDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantIdentity.deleteItem(List newKeys, UserData ud)ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ���̻�������Ϣʱ���쳣��");
		}
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantIdentity newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);

			log.info("MerchantIdentityDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸��̻�����ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MerchantIdentity newModel, UserData ud) throws OAException {

		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("MerchantIdentityDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

	/**
	 * ��֤�̻�����Ƿ�Ψһ
	 */
	public MerchantIdentity findItemById(String newKey, UserData ud) throws OAException {
		MerchantIdentity model = null;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.findItemById()��ʼ���ã���֤�̻�����Ƿ�Ψһ��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantIdentity.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantIdentity) list.get(0);

			log.info("MerchantIdentityDaoHibernateHQLImpl.findItemById()�������ã���֤�̻�����Ƿ�Ψһ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.findItemById()��֤�̻�����Ƿ�Ψһ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻����Ψһ��ʱ����");
		}
		return model;
	}

	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {
		MerchantBaseBo model = null;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.findBaseInfoById()��ʼ���ã���֤�̻���Ϣ�Ƿ���ڡ�");
			List list = null;
			String hql = " from MerchantBaseBo where 1=1 and merchantId='" + merchantId + "'";
			list = findByHQL(hql, ud);
			if (list.size() > 0)
				model = (MerchantBaseBo) list.get(0);

			log.info("MerchantIdentityDaoHibernateHQLImpl.findBaseInfoById()�������ã���֤�̻���Ϣ�Ƿ���ڡ�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.findBaseInfoById()��֤�̻���Ϣ�Ƿ���ڣ������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻���Ϣ�Ƿ����");
		}
		return model;
	}

}
