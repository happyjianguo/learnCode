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
import cn.yufu.posp.keyManager.domain.model.BtsKey;
import cn.yufu.posp.queryManager.domain.model.CurTranLs;
import cn.yufu.posp.terminalmanager.domain.model.EdcCard;

public class TMKMasterKEYDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TMKMasterKEYDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("key");

	@Override
	public List queryAllItem(BtsKey queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List<BtsKey> list = new ArrayList<BtsKey>();
		try {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()��ʼ���ã������������ն�����Կ��");
			// �˴��ı���Ӧ��ӳ������ʾ
			StringBuffer bufferHql = new StringBuffer("from BtsKey t where 1=1");

			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals("")){
				bufferHql.append(" and t.merchantId= '" + queryModel.getMerchantId() + "'");
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}else{
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}
			
			// ���ն˱�Ų�ѯ
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + queryModel.getTerminalId() + "'");

			if (queryModel.getSettleFlag() != null && !queryModel.getSettleFlag().equals(""))
				bufferHql.append(" and t.settleFlag = '" + queryModel.getSettleFlag() + "'");
			
			
			if (orderField != null && orderField != null) {
				if (orderField.equals("asc"))
					bufferHql.append(" order by "+orderField+" asc");
				else
					bufferHql.append(" order by "+orderField+" desc");
			} else {
				bufferHql.append(" order by t.merchantId ");
			}
			list = findByHQL(bufferHql.toString(), startIndex, pageSize, ud);

			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()�������ã������������ն�����Կ��");
		} catch (Exception e) {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()�����������ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public int queryCount(BtsKey queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryCount()��ʼ���ã������������ն�����Կ����");

			StringBuffer bufferHql = new StringBuffer("select count(*) from BtsKey  t where 1=1 ");

			// ���̻���Ų�ѯ
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals("")){
				bufferHql.append(" and t.merchantId = '" + queryModel.getMerchantId() + "'");
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}else{
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}
			// ���ն˱�Ų�ѯ
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + queryModel.getTerminalId() + "'");

			if (queryModel.getSettleFlag() != null && !queryModel.getSettleFlag().equals(""))
				bufferHql.append(" and t.settleFlag = '" + queryModel.getSettleFlag() + "'");
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("��ѯ���==" + hql + "");
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryCount()�����쳣�������������ն�����Կ����");
		} catch (Exception e) {

			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryCount()�����������ն�����Կ���������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public BtsKey findItemByKey(BtsKey btsKey, UserData ud) throws OAException {
		List<BtsKey> btsKeyList = new ArrayList<BtsKey>();
		BtsKey model = null;
		try {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.findItemByKey()��ʼ���ã���ʾһ���ն�����Կ����¼��");

			StringBuffer bufferHql = new StringBuffer("from BtsKey  t where 1=1 ");
			if (btsKey.getMerchantId() != null && btsKey.getTerminalId() != null) {
				bufferHql.append(" and t.merchantId = '" + btsKey.getMerchantId() + "'");
				bufferHql.append(" and t.terminalId = '" + btsKey.getTerminalId() + "'");
			}

			String hql = bufferHql.toString();
			btsKeyList = findByHQL(hql, ud);
			if (btsKeyList.size() > 0)
				model = (BtsKey) btsKeyList.get(0);

			log.info("��ѯ�ļ�¼��==" + btsKeyList.size());
			log.info("��ѯ��䣺hql==" + hql);
			log.info("TMKMasterKEYDaoHibernateHQLImpl.findItemByKey()�������ã���ʾһ���ն�����Կ����¼��");
		} catch (Exception e) {

			log.info("TMKMasterKEYDaoHibernateHQLImpl.findItemByKey()��ʾһ���ն�����Կ����¼�������쳣��");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	@Override
	public void saveItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()��ʼ���ã������ն�����Կ��");

			update(btsKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()�������ã������ն�����Կ��");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()�����ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	@Override
	public void createItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.()��ʼ���ã������ն�����Կ��");

			save(btsKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.()�������ã������ն�����Կ��");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.()�����ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}
	//����Excel��TxT�ļ��Ĺ�������
	@Override
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException {
		List<BtsKey> list = new ArrayList<BtsKey>();
		try {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()��ʼ���ã������������ն�����Կ��");
			StringBuffer bufferHql = new StringBuffer("from BtsKey  t where 1=1 ");
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals("")){
				bufferHql.append(" and t.merchantId = '" + queryModel.getMerchantId() + "'");
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}else{
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}
			// ���ն˱�Ų�ѯ
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + queryModel.getTerminalId() + "'");

			if (queryModel.getSettleFlag() != null && !queryModel.getSettleFlag().equals(""))
				bufferHql.append(" and t.settleFlag = '" + queryModel.getSettleFlag() + "'");
			
			bufferHql.append(" order by t.merchantId ");

			list = findByHQL(bufferHql.toString(), ud);

			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()�������ã������������ն�����Կ��");
		} catch (Exception e) {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()�����������ն�����Կ�������쳣");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	@Override
	public void deleteItem(BtsKey key, UserData ud) throws OAException {
		delete(key, ud);
	}

}
