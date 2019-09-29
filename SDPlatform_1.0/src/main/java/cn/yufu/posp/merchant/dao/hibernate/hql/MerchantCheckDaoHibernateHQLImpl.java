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
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.posp.merchant.domain.model.MerchantRefundModel;

public class MerchantCheckDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantCheckDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCheckDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantBaseBo newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantBaseBo.class);
			dcr.add(Restrictions.eq("status", "C"));
			// ���̻���Ų�ѯ
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

			// ���̻�״̬��ѯ
			if (newQueryModel.getMerchantStat() != null && !newQueryModel.getMerchantStat().equals(""))
				dcr.add(Restrictions.eq("merchantStat", newQueryModel.getMerchantStat()));

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

			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(MerchantBaseBo queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantBaseBo o where 1=1 and status='C' ");

			// ���̻���Ų�ѯ
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			// ���̻�״̬��ѯ
			if (queryModel.getMerchantStat() != null && !queryModel.getMerchantStat().equals(""))
				bufferHql.append(" and o.merchantStat = '" + queryModel.getMerchantStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
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
	public MerchantBaseBo findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseBo model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			List list1 = null;
			List list2 = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantBaseBo.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseBo) list.get(0);

			log.info("MerchantCheckDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantBaseBo newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);

			log.info("MerchantCheckDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸��̻�����ʱ���쳣��");

		}
	}

	/**
	 * ��֤�̻�����Ƿ�Ψһ
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.findItemById()��ʼ���ã���֤�̻�����Ƿ�Ψһ��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantCheckDaoHibernateHQLImpl.findItemById()�������ã���֤�̻�����Ƿ�Ψһ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.findItemById()��֤�̻�����Ƿ�Ψһ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻����Ψһ��ʱ����");
		}
		return model;
	}

	/**
	 * ��ѯ��������
	 */
	public String findMccName(String mcc, UserData ud) {
		String mccName = "";
		/*
		 * String sql = "select MCC_NAME from MCC_PARAM where mcc='"+mcc+"'";
		 * List list = findBySQL(sql, ud);
		 */
		MccParamModel mm = new MccParamModel();
		Session session = getSession();
		Criteria cr = session.createCriteria(MccParamModel.class);
		cr.add(Restrictions.eq("mcc", mcc));
		List list = findByCriteria(cr, ud);
		if (list != null && list.size() > 0) {
			mm = (MccParamModel) list.get(0);
			mccName = mm.getMccName();
		}
		return mccName;
	}

}
