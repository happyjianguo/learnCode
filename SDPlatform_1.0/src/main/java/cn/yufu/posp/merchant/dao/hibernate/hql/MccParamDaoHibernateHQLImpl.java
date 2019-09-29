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
import cn.yufu.posp.merchant.domain.model.MccParamModel;

public class MccParamDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MccParamDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MccParamDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MccParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MccParamDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MccParamModel.class);

			// ��mcc��Ų�ѯ
			if (newQueryModel.getQueryMcc() != null && !newQueryModel.getQueryMcc().equals(""))
				dcr.add(Restrictions.eq("mcc", newQueryModel.getQueryMcc()));

			// ��MccName��ѯ
			if (newQueryModel.getQueryMccName() != null && !newQueryModel.getQueryMccName().equals(""))
				dcr.add(Restrictions.like("mccName", "%" + newQueryModel.getQueryMccName() + "%"));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("mcc"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("MccParamDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯMCC����ʱ����");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(MccParamModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MccParamDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MccParamModel o where 1=1");

			// �����ͱ�Ų�ѯ
			if (queryModel.getQueryMcc() != null && !queryModel.getQueryMcc().equals(""))
				bufferHql.append(" and o.mcc = '" + queryModel.getQueryMcc() + "'");

			// ��MccName
			if (queryModel.getQueryMccName() != null && !queryModel.getQueryMccName().equals(""))
				bufferHql.append(" and o.mccName like '%" + queryModel.getQueryMccName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MccParamDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯMCC�����ĸ���ʱ����");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MccParamDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				MccParamModel m = findItem(new String(newKeys.get(i) + ""), ud);
				this.delete(m, ud);

			}

			log.info("MccParamDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MccParamDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ��MCC����ʱ����");
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public MccParamModel findItem(String newKey, UserData ud) throws OAException {
		MccParamModel model = null;
		try {
			log.info("MccParamDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MccParamModel.class);
			cr.add(Restrictions.eq("mcc", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (MccParamModel) list.get(0);
			}
			log.info("MccParamDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯһ��MCC����ʱ����");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MccParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("MccParamDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			this.update(newModel, ud);
			log.info("MccParamDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�MCC����ʱ����");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MccParamModel newModel, UserData ud) throws OAException {

		// ��֤mcc����Ƿ��Ѿ�����
		try {
			log.info("MccParamDaoHibernateHQLImpl.createItem()��ʼ���ã���֤mcc����Ƿ��Ѿ����ڡ�");

			MccParamModel mp = this.findItem(newModel.getMcc(), ud);
			log.info("MccParamDaoHibernateHQLImpl.createItem()�������ã���֤mcc����Ƿ��Ѿ����ڡ�");
			if (mp != null) {
				throw new OAException("MCC���  " + newModel.getMcc() + " �Ѿ������ݿ��д��ڣ�");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("MccParamDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("MccParamDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("����MCC����ʱ����");
		}
	}

}
