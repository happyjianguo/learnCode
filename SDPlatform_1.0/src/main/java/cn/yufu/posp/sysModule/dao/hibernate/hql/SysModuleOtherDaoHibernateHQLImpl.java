package cn.yufu.posp.sysModule.dao.hibernate.hql;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.sysModule.domain.model.SysModuleModel;

public class SysModuleOtherDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements SysModuleOtherDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("sys");

	public SysModuleOtherDaoHibernateHQLImpl() {

	}

	// =====物品表==========================================================

	/**
	 *查找记录总数
	 */
	public int querySum(SysModuleModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from SysModuleModel o where 1=1");

			// 按名程序模板查询
			if (queryModel.getQueryModuleId() != null && !queryModel.getQueryModuleId().equals(""))
				bufferHql.append(" and o.moduleId = " + queryModel.getQueryModuleId() + " ");

			// 按类程序名查询
			if (queryModel.getQueryProgName() != null && !queryModel.getQueryProgName().equals(""))
				bufferHql.append(" and o.progName like '%" + queryModel.getQueryProgName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("SysModuleOtherDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return count;

	}

	/**
	 *查找所有记录
	 */
	public List queryAll(SysModuleModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(SysModuleModel.class);

			// 按名程序模板查询
			if (queryModel.getQueryModuleId() != null && !queryModel.getQueryModuleId().equals(""))
				dcr.add(Restrictions.eq("moduleId", queryModel.getQueryModuleId()));

			// 按类程序名查询
			if (queryModel.getQueryProgName() != null && !queryModel.getQueryProgName().equals(""))
				dcr.add(Restrictions.like("progName", queryModel.getQueryProgName(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("moduleId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			SysModuleModel ms = new SysModuleModel();
			for (int i = 0; i < list.size(); i++) {
				ms = (SysModuleModel) list.get(i);
				ms.setModuleId(ms.getModuleId());

			}
			log.info("SysModuleOtherDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("查询模板对象个数时抛异常！");
		}
		return list;
	}

	/**
	 *删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				delete(findItem(new String(newKeys.get(i) + ""), ud), ud);

			}

			log.info("SysModuleOtherDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("SysModuleOtherDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除模板对象时抛异常！");
		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(SysModuleModel newModel, UserData ud) throws OAException {
		// 验证编号是否已经存在
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()开始调用：验证信息是否已经存在。");
			SysModuleModel sm = findItem(newModel.getModuleId().toString(), ud);
			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()结束调用：验证信息是否已经存在。");
			if (sm != null) {

				throw new OAException("模板编号  " + newModel.getModuleId().toString() + " 已经在数据库中存在！");

			}

		} catch (Exception e) {
			throw new OAException(e.getMessage());
		}

		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("SysModuleOtherDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("添加模板对象时抛异常！");
		}
	}

	/**
	 *显示一条记录
	 */
	public SysModuleModel findItem(String newKey, UserData ud) throws OAException {
		SysModuleModel model = null;
		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(SysModuleModel.class);
			cr.add(Restrictions.eq("moduleId", BigDecimal.valueOf(Long.parseLong(newKey))));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (SysModuleModel) list.get(0);

			log.info("SysModuleOtherDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询单个模板对象时抛异常！");
		}
		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(SysModuleModel newModel, UserData ud) throws OAException {

		try {
			log.info("SysModuleOtherDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);

			log.info("SysModuleOtherDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("SysModuleOtherDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改模板对象时抛异常！");
		}
	}

}
