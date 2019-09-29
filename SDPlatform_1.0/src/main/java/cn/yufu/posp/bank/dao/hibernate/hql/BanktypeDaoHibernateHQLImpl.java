package cn.yufu.posp.bank.dao.hibernate.hql;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.bank.domain.model.Banktype;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class BanktypeDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements BanktypeDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("bankInfo");

	public BanktypeDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(Banktype newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("BanktypeDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(Banktype.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getTypeName() != null && !newQueryModel.getTypeName().equals(""))
				dcr.add(Restrictions.like("typeName", "%" + newQueryModel.getTypeName() + "%"));
			// ����������
			if (newQueryModel.getBankType() != null && !newQueryModel.getBankType().equals(""))
				dcr.add(Restrictions.like("bankType",newQueryModel.getBankType() + "%"));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("bankType"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("BanktypeDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯ������������ʱ�����쳣");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(Banktype queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("BanktypeDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from Banktype o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getTypeName() != null && !queryModel.getTypeName().equals(""))
				bufferHql.append(" and o.typeName like '%" + queryModel.getTypeName() + "%'");

			// ���̻�״̬��ѯ
			if (queryModel.getBankType() != null && !queryModel.getBankType().equals(""))
				bufferHql.append(" and o.bankType like '" + queryModel.getBankType() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("BanktypeDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ�������͸���ʱ�����쳣");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("BanktypeDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				Banktype m = null;
				try {
					m = findItem(new String(newKeys.get(i) + ""), ud);
				} catch (Exception e) {
					throw new OAException("����Ҫɾ����Ϣʱ�����쳣��");
				}
				if (m != null) {
					StringBuffer bufferHql = new StringBuffer("delete from Banktype  where bank_type='" + m.getBankType()
							+ "'");
					String hql = bufferHql.toString();
					saveOrUpdateOrDeleteBySQL(hql, ud);
				} else {
					throw new OAException("��Ҫɾ������Ϣ�����ڣ�");
				}
			}
			log.info("BanktypeDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("BanktypeDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(e.getMessage());
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public Banktype findItem(String newKey, UserData ud) throws OAException {
		Banktype model = null;
		try {
			log.info("BanktypeDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(Banktype.class);
			cr.add(Restrictions.eq("bankType", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (Banktype) list.get(0);
			}
			log.info("BanktypeDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("BanktypeDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ��������ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(Banktype newModel, UserData ud) throws OAException {
		try {
			log.info("BanktypeDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");
			try {
				update(newModel, ud);
				log.info("BanktypeDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
			} catch (Exception e) {
				throw new OAException("�޸���������ʱ�����쳣��");
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BanktypeDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());
			throw new OAException(e.getMessage());

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(Banktype newModel, UserData ud) throws OAException {

		// Ψһ����֤
		try {
			log.info("BanktypeDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			Banktype sm = findItem(newModel.getBankType(), ud);
			log.info("BanktypeDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {
				throw new OAException("��������:" + newModel.getBankType() + " �ļ�¼�Ѿ������ݿ��д��ڣ����������룡");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("BanktypeDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			try {
				save(newModel, ud);
			} catch (Exception e) {
				throw new OAException("������������ʱ�����쳣");
			}

			log.info("BanktypeDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("BanktypeDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("������������ʱ�����쳣");
		}
	}

	
}
