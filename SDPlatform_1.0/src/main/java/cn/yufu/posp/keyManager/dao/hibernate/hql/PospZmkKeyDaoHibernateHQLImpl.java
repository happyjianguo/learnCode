package cn.yufu.posp.keyManager.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;

public class PospZmkKeyDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements PospZmkKeyDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("key");

	public List queryAllItem(PospZmkKey queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List<PospZmkKey> list = new ArrayList<PospZmkKey>();
		try {
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryAllItem()��ʼ���ã������������ն�����Կ��");
			DetachedCriteria dcr = DetachedCriteria.forClass(PospZmkKey.class);

			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				dcr.add(Restrictions.eq("moduleId", queryModel.getModuleId()));


			// ���������Ϣ
			if (orderField != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				dcr.addOrder(Order.desc("moduleId"));
			}

			list = findBYCriteria(dcr, startIndex, pageSize, ud);

			log.info("PospZmkKeyDaoHibernateHQLImpl.queryAllItem()�������ã������������ն�����Կ��");
		} catch (Exception e) {
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryAllItem()�����������ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(PospZmkKey queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryCount()��ʼ���ã������������ն�����Կ����");

			StringBuffer bufferHql = new StringBuffer("select count(*) from PospZmkKey  t where 1=1 ");

			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				bufferHql.append(" and t.moduleId = '" + queryModel.getModuleId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql + "");
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryCount()�����쳣�������������ն�����Կ����");
		} catch (Exception e) {

			log.info("PospZmkKeyDaoHibernateHQLImpl.queryCount()�����������ն�����Կ���������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public PospZmkKey findItemByKey(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		List<PospZmkKey> pospZmkKeyList = new ArrayList<PospZmkKey>();
		PospZmkKey model = null;
		try {
			log.info("PospZmkKeyDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ���ն�����Կ����¼��");

			StringBuffer bufferHql = new StringBuffer("from PospZmkKey  t where 1=1 ");
			if (pospZmkKey.getModuleId() != null && pospZmkKey.getModuleId() != null) {
				bufferHql.append(" and t.moduleId = '" + pospZmkKey.getModuleId() + "'");
			}

			String hql = bufferHql.toString();
			pospZmkKeyList = findByHQL(hql, ud);
			if (pospZmkKeyList.size() > 0)
				model = (PospZmkKey) pospZmkKeyList.get(0);

			log.info("��ѯ�ļ�¼��==" + pospZmkKeyList.size());
			log.info("��ѯ��䣺hql==" + hql);
			log.info("PospZmkKeyDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ���ն�����Կ����¼��");
		} catch (Exception e) {

			log.info("PospZmkKeyDaoHibernateHQLImpl.findItemByKey()��ʾһ���ն�����Կ����¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	public void saveItem(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()��ʼ���ã������ն�����Կ��");

			update(pospZmkKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()�������ã������ն�����Կ��");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()�����ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public void createItem(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.()��ʼ���ã������ն�����Կ��");

			save(pospZmkKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.()�������ã������ն�����Կ��");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.()�����ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}



}
