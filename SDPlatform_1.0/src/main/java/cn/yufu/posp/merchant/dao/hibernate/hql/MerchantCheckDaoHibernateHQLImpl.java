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
import cn.yufu.posp.merchant.domain.model.MccParamModel;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantBaseModel;
import cn.yufu.posp.merchant.domain.model.MerchantExtraModel;
import cn.yufu.posp.merchant.domain.model.MerchantRefundModel;

public class MerchantCheckDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantCheckDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantCheckDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(MerchantBaseBo newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantBaseBo.class);
			dcr.add(Restrictions.eq("status", "C"));
			// 按商户编号查询
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

			// 按商户状态查询
			if (newQueryModel.getMerchantStat() != null && !newQueryModel.getMerchantStat().equals(""))
				dcr.add(Restrictions.eq("merchantStat", newQueryModel.getMerchantStat()));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("merchantId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(MerchantBaseBo queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantBaseBo o where 1=1 and status='C' ");

			// 按商户编号查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			// 按商户状态查询
			if (queryModel.getMerchantStat() != null && !queryModel.getMerchantStat().equals(""))
				bufferHql.append(" and o.merchantStat = '" + queryModel.getMerchantStat() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantCheckDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 * 查询商品类型名称
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMccsName(String mcc) {
		List<String> mccNameList = new ArrayList<String>();

		String sql = "select MCC_NAME from MCC_PARAM where MCC='" + mcc + "'";
		mccNameList = this.getHibernateTemplate().find(sql);
		return mccNameList;
	}

	/**
	 *显示一条记录
	 */
	public MerchantBaseBo findItem(String newKey, UserData ud) throws OAException {
		MerchantBaseBo model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			List list1 = null;
			List list2 = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(MerchantBaseBo.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseBo) list.get(0);

			log.info("MerchantCheckDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(MerchantBaseBo newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);

			log.info("MerchantCheckDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改商户资料时抛异常！");

		}
	}

	/**
	 * 验证商户编号是否唯一
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantCheckDaoHibernateHQLImpl.findItemById()开始调用：验证商户编号是否唯一。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantCheckDaoHibernateHQLImpl.findItemById()结束调用：验证商户编号是否唯一。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantCheckDaoHibernateHQLImpl.findItemById()验证商户编号是否唯一，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证商户编号唯一性时出错！");
		}
		return model;
	}

	/**
	 * 查询类型名称
	 */
	public String findMccName(String mcc, UserData ud) {
		String mccName = "";
		/*
		 * String sql = "select MCC_NAME from MCC_PARAM where mcc='"+mcc+"'";
		 * List list = findBySQL(sql, ud);
		 */
		MccParamModel mm = new MccParamModel();
		Session session = getSession();
		Criteria cr = session.createCriteria(MccParamModel.class);
		cr.add(Restrictions.eq("mcc", mcc));
		List list = findByCriteria(cr, ud);
		if (list != null && list.size() > 0) {
			mm = (MccParamModel) list.get(0);
			mccName = mm.getMccName();
		}
		return mccName;
	}

}
