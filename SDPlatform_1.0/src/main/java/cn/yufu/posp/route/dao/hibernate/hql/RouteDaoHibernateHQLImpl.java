package cn.yufu.posp.route.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public class RouteDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements RouteDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("route");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public RouteDaoHibernateHQLImpl() {

	}

	/**
	 *���һ�������
	 */
	public int queryCount(Object queryModel, UserData ud) throws OAException {
		int count = 0;
		List querylist = (List) queryModel;
		try {
			log.info("��ʼ���ã���ѯ��������·�ɵ�������");
			// System.out.println("__"+querylist.get(0)+"__");
			StringBuffer bufferHql = new StringBuffer("select count(*) from "
					+ ((String) querylist.get(0)).replaceAll("cn.yufu.posp.route.domain.model.", "") + " o where 1=1");
			bufferHql.append((StringBuffer) querylist.get(2));
			// ������ID��ѯ
			// if(queryModel.getJgId()!=null && !queryModel.getJgId().equals("")
			// )
			// bufferHql.append(" and o.jgId = '"+queryModel.getJgId()+"'");

			// ��������
			// if(queryModel.getJgName()!=null &&
			// !queryModel.getJgName().equals("") )
			// bufferHql.append(" and o.jgName like '%"+queryModel.getJgName()+"%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("queryJgCount(JgQueryModel queryModel, UserData ud)�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJgCount(JgQueryModel queryModel, UserData ud)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *���һ���
	 */
	public List query(Object queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		List querylist = (List) queryModel;
		try {
			log.info("queryJg(JgQueryModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud)��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(Class.forName((String) querylist.get(0)));
			// ��װ��ѯ����
			List list1 = (List) querylist.get(1);
			for (int i = 0; i < list1.size(); i++) {
				dcr.add((Criterion) list1.get(i));
			}

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("id.updateDate"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("queryJg(JgQueryModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud)�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJg(JgQueryModel queryModel, int startIndex, int length, String orderField, String orderType, UserData ud)ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *ɾ������
	 * 
	 * @throws OAException
	 * @throws OAException
	 */
	public void deleteRoute(Object newKeys, UserData ud) throws OAException {
		try {
			log.info("���ÿ�ʼ��ɾ��·�ɡ�");
			// System.out.println("aaa"+newKeys);
			delete(newKeys, ud);

			log.info("�������ã�ɾ��·�ɡ�");
		} catch (Exception e) {
			log.error("JgDaoHibernateHQLImpl.deleteJg(List newKeys, UserData ud)ɾ�������������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *�½�·��
	 */
	public void create(Object newModel, UserData ud) throws OAException {
		try {
			log.info("��ʼ���ã��½�·�ɡ�");

			save(newModel, ud);
			// �����û�

			log.info("�������ã��½�·�ɡ�");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.createJg()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *��KEY���һ���
	 */
	public Object queryByKey(String newKey, UserData ud) throws OAException {
		JgModel officeSuppliesTypeModel = null;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJgByKey()��ʼ���ã�ͨ����Ż�����");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(JgModel.class);
			cr.add(Restrictions.eq("jgId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (JgModel) list.get(0);

			log.info("JgDaoHibernateHQLImpl.queryJgByKey()�������ã�ͨ����Ż�����");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgByKey()ͨ����Ż����������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *�������
	 */
	public void saveRoute(Object newModel, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.saveJg()��ʼ���ã����������");

			update(newModel, ud);

			log.info("JgDaoHibernateHQLImpl.saveJg()�������ã����������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.saveJg()������������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			// throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
