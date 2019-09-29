package cn.yufu.posp.client.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class OaClientComputerInfoDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements OaClientComputerInfoDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("errctl");

	public OaClientComputerInfoDaoHibernateHQLImpl() {

	}

	// =====��Ʒ��==========================================================

	/**
	 *���Ҽ�¼����
	 */
	public int querySum(OaClientComputerInfoModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from OaClientComputerInfoModel o where 1=1");

			// �����Ʋ�ѯ
			if (queryModel.getMacAddr() != null && !queryModel.getMacAddr().equals(""))
				bufferHql.append(" and o.macAddr like '%" + queryModel.getMacAddr() + "%'");

			// ����� ��ѯ
			// if(queryModel.getSort_id()!=null &&
			// !queryModel.getSort_id().equals("") )
			// bufferHql.append(" and o.officeSuppliesType.id = "+queryModel.getSort_id()+"");
			//			
			// �����Ų�ѯ
			// if(ud.getCsbh()!=null && !ud.getCsbh().equals("") )
			// bufferHql.append(" and o.department_id = '"+ud.getCsbh()+"'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *�������м�¼
	 */
	public List queryAll(OaClientComputerInfoModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud)
			throws OAException {
		List list = null;
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(OaClientComputerInfoModel.class);

			// �����Ʋ�ѯ
			if (queryModel.getMacAddr() != null && !queryModel.getMacAddr().equals(""))
				dcr.add(Restrictions.like("macAddr", queryModel.getMacAddr(), MatchMode.ANYWHERE));

			// ����� ��ѯ
			// if(queryModel.getSort_id()!=null &&
			// !queryModel.getSort_id().equals("") )
			// dcr.add(Restrictions.eq("officeSuppliesType.id",queryModel.getSort_id()));

			// �����Ų�ѯ
			// if(ud.getCsbh()!=null && !ud.getCsbh().equals("") )
			// dcr.add(Restrictions.eq("department_id",ud.getCsbh()));

			// ���������Ϣ
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("userId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				delete(findItem(new String(newKeys.get(i) + ""), ud), ud);

			}

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("OaClientComputerInfoDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public OaClientComputerInfoModel findItem(String newKey, UserData ud) throws OAException {
		OaClientComputerInfoModel model = null;
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(OaClientComputerInfoModel.class);
			cr.add(Restrictions.eq("macAddr", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (OaClientComputerInfoModel) list.get(0);

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
