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
import cn.yufu.posp.merchant.domain.model.PosReceiptInfoModel;

public class PosReceiptInfoDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements PosReceiptInfoDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public PosReceiptInfoDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(PosReceiptInfoModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(PosReceiptInfoModel.class);

			// ��״̬��ʶ��ѯ
			if (newQueryModel.getStatus() != null && !newQueryModel.getStatus().equals(""))
				dcr.add(Restrictions.eq("status", newQueryModel.getStatus()));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// ��״̬��ʶ ����
				dcr.addOrder(Order.asc("hotline"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			
			log.info("PosReceiptInfoDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("PosReceiptInfoDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯPOSǩ����Ҫ��������ʱ���쳣��");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(PosReceiptInfoModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from PosReceiptInfoModel o where 1=1");

			// ��״̬��ʶ��ѯ
			if (queryModel.getStatus() != null && !queryModel.getStatus().equals(""))
				bufferHql.append(" and o.status = '" + queryModel.getStatus() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			
			log.info("PosReceiptInfoDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯPOSǩ����Ҫ��������������ʱ���쳣��");
		}
		return count;
	}

	
	/**
	 *��ʾһ����¼
	 */
	public PosReceiptInfoModel findItem(String newKey, UserData ud) throws OAException {
		PosReceiptInfoModel model = null;
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			
			List list = null;
			Session session = getSession();
			
			// ������Ϣ
			Criteria cr = session.createCriteria(PosReceiptInfoModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("status",params[0]));
			
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (PosReceiptInfoModel) list.get(0);
				model.setStatus(model.getStatus());
			}

			log.info("PosReceiptInfoDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯPOSǩ����Ҫ�����������ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(PosReceiptInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("PosReceiptInfoDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			update(newModel, ud);
			
			log.info("PosReceiptInfoDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("PosReceiptInfoDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�POSǩ����Ҫ��������ʱ���쳣��");

		}
	}
	
	
	public void update(Object entity, UserData ud) {
		PosReceiptInfoModel newModel = (PosReceiptInfoModel) entity;
		String sql = new String("update PosReceiptInfoModel  set hotline='" + newModel.getHotline() + "',telSupport='" + newModel.getTelSupport()
				+ "',adInfo='" + newModel.getAdInfo() + "',adYesNoFlag='" + newModel.getAdYesNoFlag()+ "',status='" + newModel.getStatus() 
				+ "',createDate='" + newModel.getCreateDate() + "',Update_Oper='" + newModel.getUpdateOper()+ "',updateDate='" + newModel.getUpdateDate()
				+ "' where status='" + newModel.getStatus()  + "'");
		bulkUpdate(sql, ud);
	}

}
