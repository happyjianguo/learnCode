package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;
import cn.yufu.posp.merchant.domain.model.MerchantRefundModel;
import cn.yufu.posp.merchant.domain.model.MerchantUser;
import cn.yufu.posp.merchant.domain.model.MerchantUserRela;

public class MerchantUserDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantUserDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantUserDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(MerchantUser newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantUser.class);

			// 按商户编号查询
			// if (newQueryModel.getMerchantId() != null &&
			// !newQueryModel.getMerchantId().equals(""))
			// dcr.add(Restrictions.eq("merchantId",
			// newQueryModel.getMerchantId()));
			//
			// // 按商户状态查询
			// if (newQueryModel.getMerchantStat() != null &&
			// !newQueryModel.getMerchantStat().equals(""))
			// dcr.add(Restrictions.eq("merchantStat",
			// newQueryModel.getMerchantStat()));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("loginId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("MerchantDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(MerchantUser queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantUser o where 1=1");

			// 按商户编号查询
			// if (queryModel.getMerchantId() != null &&
			// !queryModel.getMerchantId().equals(""))
			// bufferHql.append(" and o.merchantId = '" +
			// queryModel.getMerchantId() + "'");
			//
			// // 按商户状态查询
			// if (queryModel.getMerchantStat() != null &&
			// !queryModel.getMerchantStat().equals(""))
			// bufferHql.append(" and o.merchantStat = '" +
			// queryModel.getMerchantStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *显示一条记录
	 */
	public MerchantUser findItem(String newKey, UserData ud) throws OAException {
		MerchantUser model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(MerchantUser.class);
			cr.add(Restrictions.eq("loginId", newKey));
			List list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantUser) list.get(0);
			Criteria cr1 = session.createCriteria(MerchantUserRela.class);
			cr1.add(Restrictions.eq("merchantUserId", newKey));
			List userRelaList = findByCriteria(cr1, ud);
			MerchantUserRela userRela = new MerchantUserRela();
			if (userRelaList.size() > 0) {
				userRela = (MerchantUserRela) userRelaList.get(0);
				model.setMerchantIds(userRela.getMerchantId());
			}
			log.info("MerchantDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)调用开始：删除一条记录。");
			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantUser mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// 删除基本信息
				delete(mbm, ud);
				String hql = " from MerchantUserRela where merchantUserId ='" + newKeys.get(i)+"'";
				delete(hql, ud);
			}

			log.info("MerchantDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MerchantUser.deleteItem(List newKeys, UserData ud)删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除商户资料信息时抛异常！");
		}
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(MerchantUser newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);
			String[] merchantIds = newModel.getMerchantIds().split(",");
			MerchantUserRela userRela = new MerchantUserRela();
			delete(findUserRelaItem(newModel.getLoginId(), ud), ud);
			for (int i = 0; i < merchantIds.length; i++) {
				userRela = new MerchantUserRela();
				userRela.setMerchantUserId(newModel.getLoginId());
				userRela.setMerchantId(merchantIds[i]);
				save(userRela, ud);
			}
			log.info("MerchantDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改商户资料时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(MerchantUser newModel, UserData ud) throws OAException {

		try {
			log.info("MerchantDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");
			Transaction t = this.getSession().beginTransaction();

			save(newModel, ud);
			String[] merchantIds = newModel.getMerchantIds().split(",");
			for (int i = 0; i < merchantIds.length; i++) {
				MerchantUserRela userRela = new MerchantUserRela();
				userRela.setMerchantId(merchantIds[i]);
				userRela.setMerchantUserId(newModel.getLoginId());
				save(userRela, ud);
			}
			t.commit();
			log.info("MerchantDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}
	
	/**
	 *显示一条记录
	 */
	public MerchantUserRela findUserRelaItem(String newKey, UserData ud) throws OAException {
		MerchantUserRela model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantUserRela.class);
			cr.add(Restrictions.eq("merchantUserId", newKey));
			List userRelaList = findByCriteria(cr, ud);
			if (userRelaList.size() > 0) {
				model = (MerchantUserRela) userRelaList.get(0);
			}
			log.info("MerchantDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}
}
