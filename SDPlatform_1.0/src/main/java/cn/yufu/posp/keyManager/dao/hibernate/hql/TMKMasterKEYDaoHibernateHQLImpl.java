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
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()开始调用：根据条件查终端主密钥。");
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("from BtsKey t where 1=1");

			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals("")){
				bufferHql.append(" and t.merchantId= '" + queryModel.getMerchantId() + "'");
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}else{
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}
			
			// 按终端编号查询
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

			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()结束调用：根据条件查终端主密钥。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()根据条件查终端主密钥，出现异常");
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
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryCount()开始调用：根据条件查终端主密钥数。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from BtsKey  t where 1=1 ");

			// 按商户编号查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals("")){
				bufferHql.append(" and t.merchantId = '" + queryModel.getMerchantId() + "'");
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}else{
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}
			// 按终端编号查询
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + queryModel.getTerminalId() + "'");

			if (queryModel.getSettleFlag() != null && !queryModel.getSettleFlag().equals(""))
				bufferHql.append(" and t.settleFlag = '" + queryModel.getSettleFlag() + "'");
			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql + "");
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryCount()出现异常：根据条件查终端主密钥数。");
		} catch (Exception e) {

			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryCount()根据条件查终端主密钥数，出现异常");
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
			log.info("TMKMasterKEYDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条终端主密钥数记录。");

			StringBuffer bufferHql = new StringBuffer("from BtsKey  t where 1=1 ");
			if (btsKey.getMerchantId() != null && btsKey.getTerminalId() != null) {
				bufferHql.append(" and t.merchantId = '" + btsKey.getMerchantId() + "'");
				bufferHql.append(" and t.terminalId = '" + btsKey.getTerminalId() + "'");
			}

			String hql = bufferHql.toString();
			btsKeyList = findByHQL(hql, ud);
			if (btsKeyList.size() > 0)
				model = (BtsKey) btsKeyList.get(0);

			log.info("查询的记录数==" + btsKeyList.size());
			log.info("查询语句：hql==" + hql);
			log.info("TMKMasterKEYDaoHibernateHQLImpl.findItemByKey()结束调用：显示一条终端主密钥数记录。");
		} catch (Exception e) {

			log.info("TMKMasterKEYDaoHibernateHQLImpl.findItemByKey()显示一条终端主密钥数记录，出现异常。");
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
			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()开始调用：更新终端主密钥。");

			update(btsKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()结束调用：更新终端主密钥。");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()更新终端主密钥，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	@Override
	public void createItem(BtsKey btsKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.()开始调用：创建终端主密钥。");

			save(btsKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.()结束调用：创建终端主密钥。");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.()创建终端主密钥，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}
	//导出Excel和TxT文件的公共函数
	@Override
	public List queryExport(BtsKey queryModel, UserData ud) throws OAException {
		List<BtsKey> list = new ArrayList<BtsKey>();
		try {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()开始调用：根据条件查终端主密钥。");
			StringBuffer bufferHql = new StringBuffer("from BtsKey  t where 1=1 ");
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals("")){
				bufferHql.append(" and t.merchantId = '" + queryModel.getMerchantId() + "'");
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}else{
				bufferHql.append(" and exists(select 1 from MerchantBaseModel o where t.merchantId=o.merchantId)");
			}
			// 按终端编号查询
			if (queryModel.getTerminalId() != null && !queryModel.getTerminalId().equals(""))
				bufferHql.append(" and t.terminalId = '" + queryModel.getTerminalId() + "'");

			if (queryModel.getSettleFlag() != null && !queryModel.getSettleFlag().equals(""))
				bufferHql.append(" and t.settleFlag = '" + queryModel.getSettleFlag() + "'");
			
			bufferHql.append(" order by t.merchantId ");

			list = findByHQL(bufferHql.toString(), ud);

			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()结束调用：根据条件查终端主密钥。");
		} catch (Exception e) {
			log.info("TMKMasterKEYDaoHibernateHQLImpl.queryAllItem()根据条件查终端主密钥，出现异常");
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
