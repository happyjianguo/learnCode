package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;



public class TabBusRoleMenuDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TabBusRoleMenuDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusRoleMenuDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(TabBusRoleMenuModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TabBusRoleMenuModel.class);

			//��ҵ��ID��ѯ
			 if (newQueryModel.getBusRoleId() != null &&!newQueryModel.getBusRoleId().equals(""))
			 dcr.add(Restrictions.eq("busRoleId",newQueryModel.getBusRoleId()));
			
		
			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("busRoleId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(TabBusRoleMenuModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TabBusRoleMenuModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *��ʾһ����¼
	 */
	public TabBusRoleMenuModel findItem(String newKey, UserData ud) throws OAException {
		TabBusRoleMenuModel model = null;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(TabBusRoleMenuModel.class);
			cr.add(Restrictions.eq("busRoleId", newKey));
			List list = findByCriteria(cr, ud);
			if (list.size() > 0){
				model = (TabBusRoleMenuModel) list.get(0);
			}
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);
			
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�ҵ���ɫ�˵�ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {	
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");
			// ����ҵ���ɫID(busRoleId)
			String busRoleId = findItemBusRoleId(newModel,ud);
			newModel.setBusRoleId(busRoleId);
			save(newModel, ud);
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}
	
	
	/**
	 * ����ҵ���ɫID(busRoleId)
	 */
	public String findItemBusRoleId(TabBusRoleMenuModel queryModel, UserData ud) throws OAException {
		String busRoleId = "";
		int count = 0;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findItemBusRoleId()��ʼ���ã�����ҵ��ID(businessid)��");
	
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select MAX(busRoleId) from TabBusRoleMenuModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0){
				if(list.get(0)!=null){
					count = Integer.parseInt(list.get(0).toString());
					busRoleId=new DecimalFormat("000").format(count+1);	
				}else{
					busRoleId="001";
				}
			}
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.findItemBusinessId()����ҵ���ɫID(busRoleId)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return busRoleId;
	}
	
	
	//��ȡҵ���ɫ�˵��б�
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException {
		List<TabBusRoleMenuModel> busRoleList = new ArrayList<TabBusRoleMenuModel>();
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findBusRoleList()��ʼ���ã���ȡҵ���ɫ�˵��б�");
			StringBuffer bufferHql = new StringBuffer(" from TabBusRoleMenuModel  t");
			bufferHql.append(" order by t.busRoleId asc ");
			String hql = bufferHql.toString();
			busRoleList = findByHQL(hql, ud);
			log.info("��ѯ���==" + hql);
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findBusRoleList()�������ã���ȡҵ���ɫ�˵��б�");
		} catch (Exception e) {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findBusRoleList()���ó����쳣����ȡҵ���ɫ�˵��б�");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return busRoleList;
	}
	
	//��֤ҵ���ɫ�������Ƶ�Ψһ��
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel tabBusRoleMenuModel, UserData ud) throws OAException {
		List<TabBusRoleMenuModel> tabBusRoleMenuModelList = new ArrayList<TabBusRoleMenuModel>();
		try {
			StringBuffer bufferHql = new StringBuffer(" from TabBusRoleMenuModel  t where 1=1");


		// ����ҵ���ɫ�������Ʋ�ѯ
		if (tabBusRoleMenuModel.getBusRoleName() != null && !tabBusRoleMenuModel.getBusRoleName().equals(""))
			bufferHql.append(" and t.busRoleName = '" + tabBusRoleMenuModel.getBusRoleName() + "'");

			String hql = bufferHql.toString();
			tabBusRoleMenuModelList = findByHQL(hql, ud);

		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return tabBusRoleMenuModelList;
	}
}
