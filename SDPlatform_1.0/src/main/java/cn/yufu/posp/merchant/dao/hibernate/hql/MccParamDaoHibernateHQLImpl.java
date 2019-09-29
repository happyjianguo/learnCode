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
import cn.yufu.posp.merchant.domain.model.MccParamModel;

public class MccParamDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements MccParamDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public MccParamDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(MccParamModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("MccParamDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(MccParamModel.class);

			// 按mcc编号查询
			if (newQueryModel.getQueryMcc() != null && !newQueryModel.getQueryMcc().equals(""))
				dcr.add(Restrictions.eq("mcc", newQueryModel.getQueryMcc()));

			// 按MccName查询
			if (newQueryModel.getQueryMccName() != null && !newQueryModel.getQueryMccName().equals(""))
				dcr.add(Restrictions.like("mccName", "%" + newQueryModel.getQueryMccName() + "%"));

			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("mcc"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("MccParamDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询MCC参数时报错！");
		}
		return list;
	}

	/**
	 * 查询所有对象的个数
	 */
	public int querySum(MccParamModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("MccParamDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from MccParamModel o where 1=1");

			// 按类型编号查询
			if (queryModel.getQueryMcc() != null && !queryModel.getQueryMcc().equals(""))
				bufferHql.append(" and o.mcc = '" + queryModel.getQueryMcc() + "'");

			// 按MccName
			if (queryModel.getQueryMccName() != null && !queryModel.getQueryMccName().equals(""))
				bufferHql.append(" and o.mccName like '%" + queryModel.getQueryMccName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("MccParamDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询MCC参数的个数时报错！");
		}
		return count;
	}

	/**
	 * 删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("MccParamDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				MccParamModel m = findItem(new String(newKeys.get(i) + ""), ud);
				this.delete(m, ud);

			}

			log.info("MccParamDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("MccParamDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除MCC参数时报错！");
		}
	}

	/**
	 *显示一条记录
	 */
	public MccParamModel findItem(String newKey, UserData ud) throws OAException {
		MccParamModel model = null;
		try {
			log.info("MccParamDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();

			// 基本信息
			Criteria cr = session.createCriteria(MccParamModel.class);
			cr.add(Restrictions.eq("mcc", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0) {
				model = (MccParamModel) list.get(0);
			}
			log.info("MccParamDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询一条MCC参数时报错！");
		}

		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(MccParamModel newModel, UserData ud) throws OAException {
		try {
			log.info("MccParamDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			this.update(newModel, ud);
			log.info("MccParamDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改MCC参数时报错！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(MccParamModel newModel, UserData ud) throws OAException {

		// 验证mcc编号是否已经存在
		try {
			log.info("MccParamDaoHibernateHQLImpl.createItem()开始调用：验证mcc编号是否已经存在。");

			MccParamModel mp = this.findItem(newModel.getMcc(), ud);
			log.info("MccParamDaoHibernateHQLImpl.createItem()结束调用：验证mcc编号是否已经存在。");
			if (mp != null) {
				throw new OAException("MCC编号  " + newModel.getMcc() + " 已经在数据库中存在！");
			}
		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("MccParamDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("MccParamDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("MccParamDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("增加MCC参数时报错！");
		}
	}

}
