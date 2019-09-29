package cn.yufu.posp.sysModule.dao.hibernate.hql;

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
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public class SysModuleOtherDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements SysModuleOtherDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("sys");

	public SysModuleOtherDaoHibernateHQLImpl() {

	}

	// =====��Ʒ��==========================================================

	/**
	 *���Ҽ�¼����
	 */
	public int querySum(SysModuleModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from SysModuleModel o where 1=1");

			// ��������ģ���ѯ
			if (queryModel.getQueryModuleId() != null && !queryModel.getQueryModuleId().equals(""))
				bufferHql.append(" and o.moduleId = " + queryModel.getQueryModuleId() + " ");

			// �����������ѯ
			if (queryModel.getQueryProgName() != null && !queryModel.getQueryProgName().equals(""))
				bufferHql.append(" and o.progName like '%" + queryModel.getQueryProgName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("SysModuleOtherDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯģ�����ʱ���쳣��");
		}
		return count;

	}

	/**
	 *�������м�¼
	 */
	public List queryAll(SysModuleModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(SysModuleModel.class);

			// ��������ģ���ѯ
			if (queryModel.getQueryModuleId() != null && !queryModel.getQueryModuleId().equals(""))
				dcr.add(Restrictions.eq("moduleId", queryModel.getQueryModuleId()));

			// �����������ѯ
			if (queryModel.getQueryProgName() != null && !queryModel.getQueryProgName().equals(""))
				dcr.add(Restrictions.like("progName", queryModel.getQueryProgName(), MatchMode.ANYWHERE));

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
			SysModuleModel ms = new SysModuleModel();
			for (int i = 0; i < list.size(); i++) {
				ms = (SysModuleModel) list.get(i);
				ms.setModuleId(ms.getModuleId());

			}
			log.info("SysModuleOtherDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
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
			log.info("SysModuleOtherDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				delete(findItem(new String(newKeys.get(i) + ""), ud), ud);

			}

			log.info("SysModuleOtherDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("SysModuleOtherDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ��ģ�����ʱ���쳣��");
		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException {
		// ��֤����Ƿ��Ѿ�����
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			SysModuleModel sm = findItem(newModel.getModuleId().toString(), ud);
			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {

				throw new OAException("ģ����  " + newModel.getModuleId().toString() + " �Ѿ������ݿ��д��ڣ�");

			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("���ģ�����ʱ���쳣��");
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public SysModuleModel findItem(String newKey, UserData ud) throws OAException {
		SysModuleModel model = null;
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(SysModuleModel.class);
			cr.add(Restrictions.eq("moduleId", BigDecimal.valueOf(Long.parseLong(newKey))));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (SysModuleModel) list.get(0);

			log.info("SysModuleOtherDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ����ģ�����ʱ���쳣��");
		}
		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException {

		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);

			log.info("SysModuleOtherDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�ģ�����ʱ���쳣��");
		}
	}

}
