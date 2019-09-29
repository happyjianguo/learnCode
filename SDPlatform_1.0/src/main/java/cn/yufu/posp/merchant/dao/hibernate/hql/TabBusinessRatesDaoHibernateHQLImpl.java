package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.text.DecimalFormat;
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
import cn.yufu.posp.merchant.domain.model.TabBusinessRatesModel;


public class TabBusinessRatesDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TabBusinessRatesDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusinessRatesDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(TabBusinessRatesModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TabBusinessRatesModel.class);

			//��ҵ��ID��ѯ
			 if (newQueryModel.getBusinessid() != null &&!newQueryModel.getBusinessid().equals(""))
			 dcr.add(Restrictions.eq("businessid",newQueryModel.getBusinessid()));
			
		
			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("businessid"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.querySum()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * ��ѯ��¼����
	 */
	public int querySum(TabBusinessRatesModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TabBusinessRatesModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TabBusinessRatesDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *��ʾһ����¼
	 */
	public TabBusinessRatesModel findItem(String newKey, UserData ud) throws OAException {
		TabBusinessRatesModel model = null;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			Session session = getSession();
			// ������Ϣ
			Criteria cr = session.createCriteria(TabBusinessRatesModel.class);
			cr.add(Restrictions.eq("businessid", newKey));
			List list = findByCriteria(cr, ud);
			if (list.size() > 0){
				model = (TabBusinessRatesModel) list.get(0);
			}
			log.info("TabBusinessRatesDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}


	/**
	 *�޸�һ����¼
	 */
	public void saveItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);
			
			log.info("TabBusinessRatesDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�ҵ�񳡾�����ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(TabBusinessRatesModel newModel, UserData ud) throws OAException {	
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");
			// ����ҵ��ID(businessid)
			String businessid = findItemBusinessId(newModel,ud);
			newModel.setBusinessid(businessid);
			save(newModel, ud);
			log.info("TabBusinessRatesDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}
	
	
	
	/**
	 * ����ҵ��ID(businessid)
	 */
	public String findItemBusinessId(TabBusinessRatesModel queryModel, UserData ud) throws OAException {
		String businessid = "";
		int count = 0;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.findItemBusinessId()��ʼ���ã�����ҵ��ID(businessid)��");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select MAX(businessid) from TabBusinessRatesModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0){
				if(list.get(0)!=null){
					count = Integer.parseInt(list.get(0).toString());
					businessid=new DecimalFormat("000").format(count+1);	
				}else{
					businessid="001";
				}
			}
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusinessRatesDaoHibernateHQLImpl.findItemBusinessId()����ҵ��ID(businessid)����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return businessid;
	}
	
	//��ѯҵ���¼
	public List<TabBusinessRatesModel> queryAllTabBusinessRates() throws OAException {
		List list = null;
		try {
			log.info("TabBusinessRatesDaoHibernateHQLImpl.queryAllTabBusinessRates()��ʼ���ã�ͨ����ѯҵ���¼��");
			DetachedCriteria dcr = DetachedCriteria.forClass(TabBusinessRatesModel.class);
			// ��ҵ�� ID ����
			dcr.addOrder(Order.asc("businessid"));
			list = findBYCriteria(dcr, null);
			log.info("TabBusinessRatesDaoHibernateHQLImpl.queryAllTabBusinessRates()�������ã�ͨ����ѯҵ���¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TabBusinessRatesDaoHibernateHQLImpl.queryAllTabBusinessRates()ͨ����ѯҵ���¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯҵ���¼����");
		}
		return list;
	}
}
