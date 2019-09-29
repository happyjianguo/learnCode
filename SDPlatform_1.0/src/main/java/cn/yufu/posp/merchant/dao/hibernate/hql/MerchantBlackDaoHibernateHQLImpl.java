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
	 * 查询所有对象
	 */
	public List queryAll(MerchantBlackModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(MerchantBlackModel.class);

			// 按商户编号查询
			if (newQueryModel.getMerchantId() != null && !newQueryModel.getMerchantId().equals(""))
				dcr.add(Restrictions.eq("merchantId", newQueryModel.getMerchantId()));

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
			log.info("MerchantBlackDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("MerchantBlackDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询受理卡类时抛异常！");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(MerchantBlackModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from MerchantBlackModel o where 1=1");

			// 按商户编号查询
			if (queryModel.getMerchantId() != null && !queryModel.getMerchantId().equals(""))
				bufferHql.append(" and o.merchantId = '" + queryModel.getMerchantId() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MerchantBlackDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询受理卡类对象个数时抛异常！");
		}
		return count;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				MerchantBlackModel m = findItem(new String(newKeys.get(i) + ""), ud);
				StringBuffer bufferHql = new StringBuffer("delete from MERCHANT_Black  " + "where MERCHANT_ID='" + m.getMerchantId() + "'");
				String hql = bufferHql.toString();
				saveOrUpdateOrDeleteBySQL(hql, ud);
			}
			log.info("MerchantBlackDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MerchantBlackDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除受理卡类时抛异常！");
		}
	}

	/**
	 *显示一条记录
	 */
	public MerchantBlackModel findItem(String newKey, UserData ud) throws OAException {
		MerchantBlackModel model = null;
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(MerchantBlackModel.class);
			String[] params = newKey.split(",");
			cr.add(Restrictions.eq("merchantId", params[0]));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (MerchantBlackModel) list.get(0);
				model.setMerchantId(model.getMerchantId());
			}

			log.info("MerchantBlackDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询受理卡类对象时抛异常！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			String sql = new String("update MerchantBlackModel set merchant_Id='" + newModel.getMerchantId() + "',Reason='" + newModel.getReason()
					+ "',Remark=" + newModel.getRemark() + "," + "Status=" + newModel.getStatus() + ",Update_Oper=" + newModel.getUpdateOper() + ","
					+ "Update_Time= to_date(" + newModel.getUpdateTime() + ",'yyyy-MM-dd hh:mm:ss'),Version=to_date('" + newModel.getVersion()
					+ "','yyyy-MM-dd hh:mm:ss')");

			saveOrUpdate(newModel, ud);
			log.info("MerchantBlackDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改受理卡类时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(MerchantBlackModel newModel, UserData ud) throws OAException {
		// 验证商户号是否已经存在在数据库中
		MerchantBaseModel msm = findItemById(newModel.getMerchantId(), ud);
		if (msm == null) {
			throw new OAException("商户编号:" + newModel.getMerchantId() + " 的记录在数据库中不存在！");
		}

		// 验证编号是否已经存在
		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			String newKey = newModel.getMerchantId();
			MerchantBlackModel sm = findItem(newKey, ud);
			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {

				throw new OAException("编号:" + newModel.getMerchantId() + " 的记录已经在数据库中存在！");

			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("MerchantBlackDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantBlackDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("添加受理卡类时抛异常！");
		}
	}

	/**
	 * 封装商户名称
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
	 * 验证商户编号是否存在
	 */
	public MerchantBaseModel findItemById(String newKey, UserData ud) throws OAException {
		MerchantBaseModel model = null;
		try {
			log.info("MerchantDaoHibernateHQLImpl.findItemById()开始调用：验证商户编号是否唯一。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(MerchantBaseModel.class);
			cr.add(Restrictions.eq("merchantId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (MerchantBaseModel) list.get(0);

			log.info("MerchantDaoHibernateHQLImpl.findItemById()结束调用：验证商户编号是否唯一。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MerchantDaoHibernateHQLImpl.findItemById()验证商户编号是否唯一，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("验证商户编号唯一性时出错！");
		}
		return model;
	}
}
