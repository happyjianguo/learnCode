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
import cn.yufu.posp.merchant.domain.model.MerchantCardModel;
import cn.yufu.posp.merchant.domain.model.TblNoPasswdCardBinModel;

public class TblNoPasswdCardBinDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TblNoPasswdCardBinDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TblNoPasswdCardBinDaoHibernateHQLImpl() {

	}

	/**
	 * ��ѯ���ж���
	 */
	public List queryAll(TblNoPasswdCardBinModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblNoPasswdCardBinModel.class);

			// ����BIN��ѯ
			if (newQueryModel.getQueryCardBin()!= null && !newQueryModel.getQueryCardBin().equals(""))
				dcr.add(Restrictions.eq("cardBinInfo", newQueryModel.getQueryCardBin()));
			// ���������Ϣ
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// �� id ����
				dcr.addOrder(Order.asc("cardBinInfo"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ��Ȩ���ܿ�BIN��Ϣʱ���쳣��");
		}
		return list;
	}

	/**
	 * ��ѯ���ж���ĸ���
	 */
	public int querySum(TblNoPasswdCardBinModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.querySum()��ʼ���ã���ѯ����������������");

			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblNoPasswdCardBinModel o where 1=1");

			// ����BIN��ѯ
			if (queryModel.getQueryCardBin()!= null && !queryModel.getQueryCardBin().equals(""))
				bufferHql.append(" and o.cardBinInfo = '"+ queryModel.getQueryCardBin() +"'");
			
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.querySum()�������ã���ѯ����������������");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.querySum()����ʱ�����쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ��Ȩ���ܿ�BIN��Ϣ�������ʱ���쳣��");
		}
		return count;
	}

	/**
	 *��ʾһ����¼
	 */
	public TblNoPasswdCardBinModel findItem(String newKey, UserData ud) throws OAException {
		TblNoPasswdCardBinModel model = null;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.findItem()��ʼ���ã���ʾһ����¼��");
			List list = null;
			Session session = getSession();

			// ������Ϣ
			Criteria cr = session.createCriteria(TblNoPasswdCardBinModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("cardBinInfo", params[0]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (TblNoPasswdCardBinModel) list.get(0);
			}

			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.findItem()�������ã���ʾһ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.findItem()��ʾһ����¼�������쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("��ѯ��Ȩ���ܿ�BIN��Ϣ����ʱ���쳣��");
		}

		return model;
	}

	/**
	 *�޸�һ����¼
	 */
	public void saveItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.saveItem()��ʼ���ã��޸�һ����¼��");

			saveOrUpdate(newModel, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.saveItem()�������ã��޸�һ����¼��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.saveItem()�޸�һ����¼�����쳣��");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("�޸���Ȩ���ܿ�BIN��Ϣʱ���쳣��");

		}
	}

	/**
	 *�½�һ����¼
	 */
	public void createItem(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		// ��֤��BIN�Ƿ��Ѿ�����
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()��ʼ���ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			String newKey ="";
			String length ="0";
			
			newKey = newModel.getFirstCardBin().substring(0, 9);
			List listCardBin = queryAllCardBin(newKey, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if(listCardBin.size()>0){
				length=((TblNoPasswdCardBinModel)listCardBin.get(0)).getDataLength().trim();
				
				if("9".equals(newModel.getDataLength())){
					throw new OAException("����Ϣ:" + newModel.getFirstCardBin()+ " �ļ�¼�����ݿ��д��ڣ�");			
				}
				if("10".equals(newModel.getDataLength())){
					
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						throw new OAException("����Ϣ:" + newModel.getFirstCardBin()+ " �ļ�¼�����ݿ��д��ڣ�");	
					}	
						
					if("10".equals(length)){
						throw new OAException("����Ϣ:" + newModel.getFirstCardBin()+ " �ļ�¼�����ݿ��д��ڣ�");	
					}
				}
				if("11".equals(newModel.getDataLength())){
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						throw new OAException("����Ϣ:" + newModel.getFirstCardBin()+ " �ļ�¼�����ݿ��д��ڣ�");	
					}	
					if(!"11".equals(length)){
						throw new OAException("����Ϣ:" + newModel.getFirstCardBin()+ " �ļ�¼�����ݿ��д��ڣ�");	
					}
				}
			}
						
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()��ʼ���ã�����һ����Ϣ��");

			save(newModel, ud);

			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()�������ã�����һ����Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()����һ����Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());

			throw new OAException("�����Ȩ���ܿ�BIN��Ϣʱ���쳣��");
		}
	}

	
	/**
	 * ��ѯ���ж���
	 */
	public List queryAllCardBin(String queryCardBin, UserData ud) throws OAException {
		List listCardBin = null;
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()��ʼ���ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblNoPasswdCardBinModel.class);

			// ����BIN��ѯ
			if (queryCardBin!= null && !queryCardBin.equals(""))
				dcr.add(Restrictions.like("firstCardBin", "%" + queryCardBin + "%"));
			listCardBin = findBYCriteria(dcr, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()�������ã�ͨ����ѯ������ҳ��ѯ��Ϣ��");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.queryAll()ͨ����ѯ������ҳ��ѯ��Ϣ�������쳣��");
			log.error(e, e.fillInStackTrace());
			throw new OAException("��ѯ��Ȩ���ܿ�BIN��Ϣʱ���쳣��");
		}
		return listCardBin;
	}

	
	/**
	 * ɾ��һ����¼
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.deleteItem()���ÿ�ʼ��ɾ��һ����¼��");

			for (int i = 0; i < newKeys.size(); i++) {
				TblNoPasswdCardBinModel m = findItem(new String(newKeys.get(i) + ""), ud);
				StringBuffer bufferHql = new StringBuffer("delete from TBL_NO_PASSWD_CARDBIN  where FIRST_CARD_BIN='" + m.getFirstCardBin() + "'");
				String hql = bufferHql.toString();
				saveOrUpdateOrDeleteBySQL(hql, ud);

			}

			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.deleteItem()�������ã�ɾ��һ����¼��");
		} catch (Exception e) {
			log.error("TblNoPasswdCardBinDaoHibernateHQLImpl.deleteItem()ɾ��һ����¼�������쳣��");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("ɾ����Ȩ���ܿ�BIN��Ϣʱ���쳣��");
		}
	}
	
	public String findfirstCardBinByKey(TblNoPasswdCardBinModel newModel, UserData ud) throws OAException {
		String result ="0";
		String length ="0";
		try {		
			String newKey ="";
			newKey = newModel.getFirstCardBin().substring(0, 9);
			List listCardBin = queryAllCardBin(newKey, ud);
			log.info("TblNoPasswdCardBinDaoHibernateHQLImpl.createItem()�������ã���֤��Ϣ�Ƿ��Ѿ����ڡ�");
			if(listCardBin.size()>0){
				length=((TblNoPasswdCardBinModel)listCardBin.get(0)).getDataLength().trim();
				
				if("9".equals(newModel.getDataLength())){
					result="-1";
					return result;
				}
				if("10".equals(newModel.getDataLength())){	
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						result="-1";
						return result;
					}	
					if(!"10".equals(length)){
						result="-1";
						return result;
					}
				}
				if("11".equals(newModel.getDataLength())){
					if(((TblNoPasswdCardBinModel)listCardBin.get(0)).getFirstCardBin().equals(newModel.getFirstCardBin())){
						result="-1";
						return result;
					}
					if(!"11".equals(length)){
						result="-1";
						return result;
					}
				}
			}
			
		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return result;
	}
}
