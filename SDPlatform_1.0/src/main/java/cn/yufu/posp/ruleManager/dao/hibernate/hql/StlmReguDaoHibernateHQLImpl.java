package cn.yufu.posp.ruleManager.dao.hibernate.hql;

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
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;

public class StlmReguDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements StlmReguDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("rule");

	public StlmReguDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(TblStlmRegu queryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("StlmReguDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblStlmRegu.class);

			if (queryModel.getMchtNo() != null && !queryModel.getMchtNo().equals(""))
				dcr.add(Restrictions.like("mchtNo", queryModel.getMchtNo()+"%"));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("ruleNo"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("StlmReguDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(TblStlmRegu queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("StlmReguDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblStlmRegu o where 1=1");

			if (queryModel.getMchtNo() != null && !queryModel.getMchtNo().equals(""))
				bufferHql.append(" and o.mchtNo = '" + queryModel.getMchtNo() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("StlmReguDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *��ʾһ����¼
	 */
	public TblStlmRegu findItem(String newKey, UserData ud) throws OAException {
		TblStlmRegu model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("StlmReguDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(TblStlmRegu.class);
			cr.add(Restrictions.eq("ruleNo", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (TblStlmRegu) list.get(0);

			log.info("StlmReguDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
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
			log.info("StlmReguDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			for (int i = 0; i < newKeys.size(); i++) {

				TblStlmRegu mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// ɾ��������Ϣ
				delete(mbm, ud);

			}

			log.info("StlmReguDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("TblStlmRegu.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ���̻�������Ϣʱ���쳣��");
		}
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(TblStlmRegu newModel, UserData ud) throws OAException {
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("StlmReguDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			update(newModel, ud);
			t.commit();
			log.info("StlmReguDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("StlmReguDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			t.rollback();
			throw new OAException("�޸��̻�����ʱ���쳣��");

		}
	}

	/**
	 * ��ѯ��Ʒ��������
	 */
	@SuppressWarnings("unchecked")
	public String getMccsName(String mcc) {
		List<String> mccNameList = new ArrayList<String>();

		String sql = "select MCC_NAME from MCC_PARAM where MCC='" + mcc + "'";
		mccNameList = this.getHibernateTemplate().find(sql);
		return mccNameList.get(0);
	}
	
	/**
	 *�½�һ����¼
	 */
	public void createItem(TblStlmRegu newModel, UserData ud) throws OAException {
		// ��֤����Ƿ��Ѿ�����-������Ϣ
		try {
			log.info("SysModuleDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			TblStlmRegu sm = findItem(newModel.getRuleNo(), ud);
			log.info("SysModuleDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {
				throw new OAException("�̻����  " + newModel.getRuleNo() + " �Ѿ������ݿ��д��ڣ�");
			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("StlmReguDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");
			save(newModel, ud);
			t.commit();
			log.info("StlmReguDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("StlmReguDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}
}
