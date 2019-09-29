package cn.yufu.posp.sysparam.dao.hibernate.hql;

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
import cn.yufu.posp.sysparam.domain.model.TranModuleInf;

public class TranModuleDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TranModuleDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("sysparam");

	public TranModuleDaoHibernateHQLImpl() {

	}

	// =====物品表==========================================================

	/**
	 *查找记录总数
	 */
	public int querySum(TranModuleInf queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TranModuleDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TranModuleInf o where 1=1");
			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				bufferHql.append(" and o.moduleId = " + queryModel.getModuleId() + " ");
			if (queryModel.getFlag() != null && !queryModel.getFlag().equals(""))
				bufferHql.append(" and o.flag = " + queryModel.getFlag() + " ");
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				bufferHql.append(" and o.tranType = " + queryModel.getTranType() + " ");
			if (queryModel.getTranName() != null && !queryModel.getTranName().equals(""))
				bufferHql.append(" and o.tranName like '%" + queryModel.getTranName() + "%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TranModuleDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException("查询模板对象时抛异常！");
		}
		return count;

	}

	/**
	 *查找所有记录
	 */
	public List queryAll(TranModuleInf queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TranModuleDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TranModuleInf.class);

			if (queryModel.getModuleId() != null && !queryModel.getModuleId().equals(""))
				dcr.add(Restrictions.eq("moduleId", queryModel.getModuleId()));
			if (queryModel.getFlag() != null && !queryModel.getFlag().equals(""))
				dcr.add(Restrictions.eq("flag", queryModel.getFlag()));
			if (queryModel.getTranType() != null && !queryModel.getTranType().equals(""))
				dcr.add(Restrictions.eq("tranType", queryModel.getTranType()));
			if (queryModel.getTranName() != null && !queryModel.getTranName().equals(""))
				dcr.add(Restrictions.like("tranName", queryModel.getTranName(), MatchMode.ANYWHERE));

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
			TranModuleInf ms = new TranModuleInf();
			for (int i = 0; i < list.size(); i++) {
				ms = (TranModuleInf) list.get(i);
				ms.setModuleId(ms.getModuleId());

			}
			log.info("TranModuleDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
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
			log.info("TranModuleDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				delete(findItem((TranModuleInf) newKeys.get(i), ud), ud);

			}

			log.info("TranModuleDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("TranModuleDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException("删除模板对象时抛异常！");
		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(TranModuleInf newModel, UserData ud) throws OAException {
		try {
			log.info("TranModuleDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("TranModuleDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException("添加模板对象时抛异常！");
		}
	}

	/**
	 *显示一条记录
	 */
	public TranModuleInf findItem(TranModuleInf newKey, UserData ud) throws OAException {
		TranModuleInf model = null;
		try {
			log.info("TranModuleDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(TranModuleInf.class);
			cr.add(Restrictions.eq("moduleId", newKey.getModuleId()));
			cr.add(Restrictions.eq("tranType", newKey.getTranType()));
			cr.add(Restrictions.eq("voidOldTranType", newKey.getVoidOldTranType()));
			cr.add(Restrictions.eq("voidTranType", newKey.getVoidTranType()));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (TranModuleInf) list.get(0);

			log.info("TranModuleDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("查询单个模板对象时抛异常！");
		}
		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(TranModuleInf newModel, UserData ud) throws OAException {

		try {
			log.info("TranModuleDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);

			log.info("TranModuleDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TranModuleDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改模板对象时抛异常！");
		}
	}

}
