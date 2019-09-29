package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.MerchantBaseBo;
import cn.yufu.posp.merchant.domain.model.MerchantIdentity;

public class MerchantIdentityDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MerchantIdentityDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MerchantIdentityDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(MerchantIdentity newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantIdentity.class);

			// 按商户编号查询
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));
			if (newQueryModel.getClassType() != null && !newQueryModel.getClassType().equals(""))
				dcr.add(Restrictions.like("classType", newQueryModel.getClassType(),MatchMode.ANYWHERE));

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

			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(MerchantIdentity queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantIdentity o where 1=1");

			// 按商户编号查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");
			if (queryModel.getClassType() != null && !queryModel.getClassType().equals(""))
				bufferHql.append(" and o.classType = '" + queryModel.getClassType() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantIdentityDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.querySum()调用时出现异常。");
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
	public MerchantIdentity findItem(String newKey, UserData ud) throws OAException {
		MerchantIdentity model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(MerchantIdentity.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantIdentity) list.get(0);

			log.info("MerchantIdentityDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
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
			log.info("MerchantIdentityDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)调用开始：删除一条记录。");

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantIdentity mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// 删除基本信息
				delete(mbm, ud);
			}

			log.info("MerchantIdentityDaoHibernateHQLImpl.deleteItem(List newKeys, UserData ud)结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MerchantIdentity.deleteItem(List newKeys, UserData ud)删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除商户资料信息时抛异常！");
		}
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(MerchantIdentity newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);

			log.info("MerchantIdentityDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改商户资料时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(MerchantIdentity newModel, UserData ud) throws OAException {

		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("MerchantIdentityDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}

	/**
	 * 验证商户编号是否唯一
	 */
	public MerchantIdentity findItemById(String newKey, UserData ud) throws OAException {
		MerchantIdentity model = null;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.findItemById()开始调用：验证商户编号是否唯一。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantIdentity.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantIdentity) list.get(0);

			log.info("MerchantIdentityDaoHibernateHQLImpl.findItemById()结束调用：验证商户编号是否唯一。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.findItemById()验证商户编号是否唯一，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证商户编号唯一性时出错！");
		}
		return model;
	}

	public MerchantBaseBo findBaseInfoById(String merchantId, UserData ud) throws OAException {
		MerchantBaseBo model = null;
		try {
			log.info("MerchantIdentityDaoHibernateHQLImpl.findBaseInfoById()开始调用：验证商户信息是否存在。");
			List list = null;
			String hql = " from MerchantBaseBo where 1=1 and merchantId='" + merchantId + "'";
			list = findByHQL(hql, ud);
			if (list.size() > 0)
				model = (MerchantBaseBo) list.get(0);

			log.info("MerchantIdentityDaoHibernateHQLImpl.findBaseInfoById()结束调用：验证商户信息是否存在。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantIdentityDaoHibernateHQLImpl.findBaseInfoById()验证商户信息是否存在，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证商户信息是否存在");
		}
		return model;
	}

}
