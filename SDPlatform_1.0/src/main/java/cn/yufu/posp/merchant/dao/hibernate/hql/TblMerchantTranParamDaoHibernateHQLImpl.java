package cn.yufu.posp.merchant.dao.hibernate.hql;

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
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.TblMerchantTranParamModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;

public class TblMerchantTranParamDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TblMerchantTranParamDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TblMerchantTranParamDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(TblMerchantTranParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblMerchantTranParamModel.class);

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
			
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblMerchantTranParamDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ�̻����ײ���ʱ���쳣��");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(TblMerchantTranParamModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblMerchantTranParamModel o where 1=1");

			// ���̻���Ų�ѯ
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ�̻����ײ����������ʱ���쳣��");
		}
		return count;
	}

	/**
	 *��ʾһ����¼
	 */
	public TblMerchantTranParamModel findItem(String newKey, UserData ud) throws OAException {
		TblMerchantTranParamModel model = null;
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(TblMerchantTranParamModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("merchantId", params[0]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (TblMerchantTranParamModel) list.get(0);
				model.setMerchantId(model.getMerchantId());
			}

			log.info("TblMerchantTranParamDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ�̻����ײ�������ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			saveOrUpdate(newModel, ud);
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblMerchantTranParamDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸��̻����ײ���ʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(TblMerchantTranParamModel newModel, UserData ud) throws OAException {
		// ��֤�̻�����Ƿ��Ѿ����������ݿ���
		MerchantBaseModel msm = findItemById(newModel.getMerchantId(), ud);
		if (msm == null) {
			throw new OAException("�̻����:" + newModel.getMerchantId() + " �ļ�¼�����ݿ��в����ڣ�");
		}
		// ��֤����Ƿ��Ѿ����ں����
		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey = newModel.getMerchantId();
			TblMerchantTranParamModel sm = findItem(newKey, ud);
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if (sm != null) {
				throw new OAException("�̻����:" + newModel.getMerchantId() + " �ļ�¼�Ѿ����̻����ײ������д��ڣ�");
			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");
			save(newModel, ud);
			log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblMerchantTranParamDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("����̻����ײ���ʱ���쳣��");
		}
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
	
	
	public String checkMerchantId(String merchantId, UserData ud) throws OAException {
		int returnFlagOne=0;
		int returnFlagTwo=0;
		String returnFlag="";
		// ��֤�̻�����Ƿ��Ѿ����������ݿ���
			MerchantBaseModel msm = findItemById(merchantId, ud);
			if (msm != null) {
				returnFlagOne=1;
			}else{
				returnFlagOne=0;
			}
			// ��֤����Ƿ��Ѿ����ں����
			try {
				log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				TblMerchantTranParamModel sm = findItem(merchantId, ud);
				log.info("TblMerchantTranParamDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
				if (sm != null) {
					returnFlagTwo=0;
				}else{
					returnFlagTwo=1;
				}

			} catch (Exception e) {
				throw new OAException(e.getMessage());
			}
			if(returnFlagOne==1&&returnFlagTwo==1){
				returnFlag="1";//��ȷ
			}
			if(returnFlagOne==0){
				returnFlag="2";//���󣺴��̻����̻����в�����
			}
			if(returnFlagTwo==0){
				returnFlag="3";//���󣺴��̻����̻����ײ������д���
			}	
		return returnFlag;
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
}
