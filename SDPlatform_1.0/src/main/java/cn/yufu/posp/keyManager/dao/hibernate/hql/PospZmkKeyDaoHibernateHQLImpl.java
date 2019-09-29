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
import cn.yufu.posp.keyManager.domain.model.PospZmkKey;

public class PospZmkKeyDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements PospZmkKeyDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("key");

	public List queryAllItem(PospZmkKey queryModel, int startIndex, int pageSize, String orderField, String orderType, UserData ud) throws OAException {
		List<PospZmkKey> list = new ArrayList<PospZmkKey>();
		try {
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryAllItem()开始调用：根据条件查终端主密钥。");
			DetachedCriteria dcr = DetachedCriteria.forClass(PospZmkKey.class);

			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				dcr.add(Restrictions.eq("moduleId", queryModel.getModuleId()));


			// 添加排序信息
			if (orderField != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				dcr.addOrder(Order.desc("moduleId"));
			}

			list = findBYCriteria(dcr, startIndex, pageSize, ud);

			log.info("PospZmkKeyDaoHibernateHQLImpl.queryAllItem()结束调用：根据条件查终端主密钥。");
		} catch (Exception e) {
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryAllItem()根据条件查终端主密钥，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public int queryCount(PospZmkKey queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryCount()开始调用：根据条件查终端主密钥数。");

			StringBuffer bufferHql = new StringBuffer("select count(*) from PospZmkKey  t where 1=1 ");

			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				bufferHql.append(" and t.moduleId = '" + queryModel.getModuleId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());

			log.info("查询语句==" + hql + "");
			log.info("PospZmkKeyDaoHibernateHQLImpl.queryCount()出现异常：根据条件查终端主密钥数。");
		} catch (Exception e) {

			log.info("PospZmkKeyDaoHibernateHQLImpl.queryCount()根据条件查终端主密钥数，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	public PospZmkKey findItemByKey(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		List<PospZmkKey> pospZmkKeyList = new ArrayList<PospZmkKey>();
		PospZmkKey model = null;
		try {
			log.info("PospZmkKeyDaoHibernateHQLImpl.findItemByKey()开始调用：显示一条终端主密钥数记录。");

			StringBuffer bufferHql = new StringBuffer("from PospZmkKey  t where 1=1 ");
			if (pospZmkKey.getModuleId() != null && pospZmkKey.getModuleId() != null) {
				bufferHql.append(" and t.moduleId = '" + pospZmkKey.getModuleId() + "'");
			}

			String hql = bufferHql.toString();
			pospZmkKeyList = findByHQL(hql, ud);
			if (pospZmkKeyList.size() > 0)
				model = (PospZmkKey) pospZmkKeyList.get(0);

			log.info("查询的记录数==" + pospZmkKeyList.size());
			log.info("查询语句：hql==" + hql);
			log.info("PospZmkKeyDaoHibernateHQLImpl.findItemByKey()结束调用：显示一条终端主密钥数记录。");
		} catch (Exception e) {

			log.info("PospZmkKeyDaoHibernateHQLImpl.findItemByKey()显示一条终端主密钥数记录，出现异常。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	public void saveItem(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()开始调用：更新终端主密钥。");

			update(pospZmkKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()结束调用：更新终端主密钥。");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.saveItem()更新终端主密钥，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public void createItem(PospZmkKey pospZmkKey, UserData ud) throws OAException {
		try {
			log.info("EdcBlackDaoHibernateHQLImpl.()开始调用：创建终端主密钥。");

			save(pospZmkKey, ud);

			log.info("EdcBlackDaoHibernateHQLImpl.()结束调用：创建终端主密钥。");
		} catch (Exception e) {

			log.info("EdcBlackDaoHibernateHQLImpl.()创建终端主密钥，出现异常");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}



}
