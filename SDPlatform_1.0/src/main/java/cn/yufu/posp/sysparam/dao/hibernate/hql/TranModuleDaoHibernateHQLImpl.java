package cn.yufu.posp.sysparam.dao.hibernate.hql;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;

public class TranModuleDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TranModuleDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("sysparam");

	public TranModuleDaoHibernateHQLImpl() {

	}

	// =====��Ʒ��==========================================================

	/**
	 *���Ҽ�¼����
	 */
	public int querySum(TranModuleInf queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TranModuleDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TranModuleInf o where 1=1");
			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				bufferHql.append(" and o.moduleId = " + queryModel.getModuleId() + " ");
			if (queryModel.getFlag() != null && !queryModel.getFlag().equals(""))
				bufferHql.append(" and o.flag = " + queryModel.getFlag() + " ");
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and o.tranType = " + queryModel.getTranType() + " ");
			if (queryModel.getTranName() != null && !queryModel.getTranName().equals(""))
				bufferHql.append(" and o.tranName like '%" + queryModel.getTranName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TranModuleDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯģ�����ʱ���쳣��");
		}
		return count;

	}

	/**
	 *�������м�¼
	 */
	public List queryAll(TranModuleInf queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TranModuleDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TranModuleInf.class);

			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				dcr.add(Restrictions.eq("moduleId", queryModel.getModuleId()));
			if (queryModel.getFlag() != null && !queryModel.getFlag().equals(""))
				dcr.add(Restrictions.eq("flag", queryModel.getFlag()));
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				dcr.add(Restrictions.eq("tranType", queryModel.getTranType()));
			if (queryModel.getTranName() != null && !queryModel.getTranName().equals(""))
				dcr.add(Restrictions.like("tranName", queryModel.getTranName(), MatchMode.ANYWHERE));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("moduleId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			TranModuleInf ms = new TranModuleInf();
			for (int i = 0; i < list.size(); i++) {
				ms = (TranModuleInf) list.get(i);
				ms.setModuleId(ms.getModuleId());

			}
			log.info("TranModuleDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("��ѯģ��������ʱ���쳣��");
		}
		return list;
	}

	/**
	 *ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("TranModuleDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				delete(findItem((TranModuleInf) newKeys.get(i), ud), ud);

			}

			log.info("TranModuleDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("TranModuleDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ��ģ�����ʱ���쳣��");
		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(TranModuleInf newModel, UserData ud) throws OAException {
		try {
			log.info("TranModuleDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("TranModuleDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("���ģ�����ʱ���쳣��");
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public TranModuleInf findItem(TranModuleInf newKey, UserData ud) throws OAException {
		TranModuleInf model = null;
		try {
			log.info("TranModuleDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(TranModuleInf.class);
			cr.add(Restrictions.eq("moduleId", newKey.getModuleId()));
			cr.add(Restrictions.eq("tranType", newKey.getTranType()));
			cr.add(Restrictions.eq("voidOldTranType", newKey.getVoidOldTranType()));
			cr.add(Restrictions.eq("voidTranType", newKey.getVoidTranType()));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (TranModuleInf) list.get(0);

			log.info("TranModuleDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ����ģ�����ʱ���쳣��");
		}
		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(TranModuleInf newModel, UserData ud) throws OAException {

		try {
			log.info("TranModuleDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);

			log.info("TranModuleDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�ģ�����ʱ���쳣��");
		}
	}

}
