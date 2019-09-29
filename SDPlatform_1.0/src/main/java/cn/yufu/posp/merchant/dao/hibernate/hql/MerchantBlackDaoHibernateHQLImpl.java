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
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantBlackModel;

public class MerchantBlackDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantBlackDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantBlackDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(MerchantBlackModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantBlackModel.class);

			// ���̻���Ų�ѯ
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("merchantId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			// MerchantBlackModel mc = new MerchantBlackModel();
			// for (int i = 0; i < list.size(); i++) {
			// mc = (MerchantBlackModel) list.get(i);
			// mc.setQueryMerchant(mc.getMerchantId());
			// mc.setQueryBankType(mc.getBankType());
			// mc.setQueryCardType(mc.getCardType());
			// }
			// MerchantBlackModel m = new MerchantBlackModel();
			// for (int i = 0; i < list.size(); i++) {
			// m = (MerchantBlackModel) list.get(i);
			//
			// System.out.println(i + ": merchantId: " + m.getMerchantId());
			// System.out.println(i + ": bankType: " + m.getBankType());
			// System.out.println(i + ": cardType: " + m.getCardType());
			// }
			log.info("MerchantBlackDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantBlackDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ������ʱ���쳣��");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(MerchantBlackModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantBlackModel o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantBlackDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ������������ʱ���쳣��");
		}
		return count;
	}

	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			// ����ɾ���������ӱ�һͬɾ��

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantBlackModel m = findItem(new String(newKeys.get(i) + ""), ud);
				StringBuffer bufferHql = new StringBuffer("delete from MERCHANT_Black  " + "where MERCHANT_ID='" + m.getMerchantId() + "'");
				String hql = bufferHql.toString();
				saveOrUpdateOrDeleteBySQL(hql, ud);
			}
			log.info("MerchantBlackDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("MerchantBlackDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ��������ʱ���쳣��");
		}
	}

	/**
	 *��ʾһ����¼
	 */
	public MerchantBlackModel findItem(String newKey, UserData ud) throws OAException {
		MerchantBlackModel model = null;
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(MerchantBlackModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("merchantId", params[0]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (MerchantBlackModel) list.get(0);
				model.setMerchantId(model.getMerchantId());
			}

			log.info("MerchantBlackDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ���������ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			String sql = new String("update MerchantBlackModel set merchant_Id='" + newModel.getMerchantId() + "',Reason='" + newModel.getReason()
					+ "',Remark=" + newModel.getRemark() + "," + "Status=" + newModel.getStatus() + ",Update_Oper=" + newModel.getUpdateOper() + ","
					+ "Update_Time= to_date(" + newModel.getUpdateTime() + ",'yyyy-MM-dd hh:mm:ss'),Version=to_date('" + newModel.getVersion()
					+ "','yyyy-MM-dd hh:mm:ss')");

			saveOrUpdate(newModel, ud);
			log.info("MerchantBlackDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸�������ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		// ��֤�̻����Ƿ��Ѿ����������ݿ���
		MerchantBaseModel msm = findItemById(newModel.getMerchantId(), ud);
		if (msm == null) {
			throw new OAException("�̻����:" + newModel.getMerchantId() + " �ļ�¼�����ݿ��в����ڣ�");
		}

		// ��֤����Ƿ��Ѿ�����
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey = newModel.getMerchantId();
			MerchantBlackModel sm = findItem(newKey, ud);
			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {

				throw new OAException("���:" + newModel.getMerchantId() + " �ļ�¼�Ѿ������ݿ��д��ڣ�");

			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("���������ʱ���쳣��");
		}
	}

	/**
	 * ��װ�̻�����
	 */
	public String findMerchantName(String merchantId, UserData ud) {
		String merName = "";
		Session session = getSession();
		Criteria cr = session.createCriteria(MerchantBaseModel.class);
		cr.add(Restrictions.eq("merchantId", merchantId));
		List list = findByCriteria(cr, ud);
		if (list != null && list.size() > 0) {
			MerchantBaseModel m = (MerchantBaseModel) list.get(0);
			merName = m.getMerchantCname();
		}
		return merName;
	}

	/**
	 * ��֤�̻�����Ƿ����
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItemById()��ʼ���ã���֤�̻�����Ƿ�Ψһ��");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findItemById()�������ã���֤�̻�����Ƿ�Ψһ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItemById()��֤�̻�����Ƿ�Ψһ�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��֤�̻����Ψһ��ʱ����");
		}
		return model;
	}
}
