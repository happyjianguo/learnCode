package cn.yufu.posp.ruleManager.dao.hibernate.hql;

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
import cn.yufu.posp.ruleManager.domain.model.TblStlmRegu;

public class StlmReguDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements StlmReguDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("rule");

	public StlmReguDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(TblStlmRegu queryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("StlmReguDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TblStlmRegu.class);

			if (queryModel.getMchtNo() != null && !queryModel.getMchtNo().equals(""))
				dcr.add(Restrictions.like("mchtNo", queryModel.getMchtNo()+"%"));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("ruleNo"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("StlmReguDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(TblStlmRegu queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("StlmReguDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TblStlmRegu o where 1=1");

			if (queryModel.getMchtNo() != null && !queryModel.getMchtNo().equals(""))
				bufferHql.append(" and o.mchtNo = '" + queryModel.getMchtNo() + "'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("StlmReguDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *显示一条记录
	 */
	public TblStlmRegu findItem(String newKey, UserData ud) throws OAException {
		TblStlmRegu model = null;
		// MerchantExtraModel extraModel = new MerchantExtraModel();
		// MerchantRefundModel fundModel = new MerchantRefundModel();
		try {
			log.info("StlmReguDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(TblStlmRegu.class);
			cr.add(Restrictions.eq("ruleNo", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (TblStlmRegu) list.get(0);

			log.info("StlmReguDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("StlmReguDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
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
			log.info("StlmReguDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			for (int i = 0; i < newKeys.size(); i++) {

				TblStlmRegu mbm = findItem(new String(newKeys.get(i) + ""), ud);
				// 删除基本信息
				delete(mbm, ud);

			}

			log.info("StlmReguDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("TblStlmRegu.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除商户资料信息时抛异常！");
		}
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(TblStlmRegu newModel, UserData ud) throws OAException {
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("StlmReguDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");
			update(newModel, ud);
			t.commit();
			log.info("StlmReguDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("StlmReguDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());
			t.rollback();
			throw new OAException("修改商户资料时抛异常！");

		}
	}

	/**
	 * 查询商品类型名称
	 */
	@SuppressWarnings("unchecked")
	public String getMccsName(String mcc) {
		List<String> mccNameList = new ArrayList<String>();

		String sql = "select MCC_NAME from MCC_PARAM where MCC='" + mcc + "'";
		mccNameList = this.getHibernateTemplate().find(sql);
		return mccNameList.get(0);
	}
	
	/**
	 *新建一条记录
	 */
	public void createItem(TblStlmRegu newModel, UserData ud) throws OAException {
		// 验证编号是否已经存在-基本信息
		try {
			log.info("SysModuleDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			TblStlmRegu sm = findItem(newModel.getRuleNo(), ud);
			log.info("SysModuleDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {
				throw new OAException("商户编号  " + newModel.getRuleNo() + " 已经在数据库中存在！");
			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}
		Transaction t = this.getSession().beginTransaction();
		try {
			log.info("StlmReguDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");
			save(newModel, ud);
			t.commit();
			log.info("StlmReguDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("StlmReguDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(e.getMessage());
		}
	}
}
