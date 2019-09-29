package cn.yufu.posp.route.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.jgmanager.domain.model.JgModel;

public class RouteDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements RouteDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("route");

	/**
	 * @roseuid 44BAF72B0232
	 */
	public RouteDaoHibernateHQLImpl() {

	}

	/**
	 *查找机构总数
	 */
	public int queryCount(Object queryModel, UserData ud) throws OAException {
		int count = 0;
		List querylist = (List) queryModel;
		try {
			log.info("开始调用：查询符合条件路由的数量。");
			// System.out.println("__"+querylist.get(0)+"__");
			StringBuffer bufferHql = new StringBuffer("select count(*) from "
					+ ((String) querylist.get(0)).replaceAll("cn.yufu.posp.route.domain.model.", "") + " o where 1=1");
			bufferHql.append((StringBuffer) querylist.get(2));
			// 按机构ID查询
			// if(queryModel.getJgId()!=null && !queryModel.getJgId().equals("")
			// )
			// bufferHql.append(" and o.jgId = '"+queryModel.getJgId()+"'");

			// 按机构名
			// if(queryModel.getJgName()!=null &&
			// !queryModel.getJgName().equals("") )
			// bufferHql.append(" and o.jgName like '%"+queryModel.getJgName()+"%'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("queryJgCount(JgQueryModel queryModel, UserData ud)结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJgCount(JgQueryModel queryModel, UserData ud)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *查找机构
	 */
	public List query(Object queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		List querylist = (List) queryModel;
		try {
			log.info("queryJg(JgQueryModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud)开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(Class.forName((String) querylist.get(0)));
			// 组装查询条件
			List list1 = (List) querylist.get(1);
			for (int i = 0; i < list1.size(); i++) {
				dcr.add((Criterion) list1.get(i));
			}

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("id.updateDate"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("queryJg(JgQueryModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud)结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("queryJg(JgQueryModel queryModel, int startIndex, int length, String orderField, String orderType, UserData ud)通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *删除机构
	 * 
	 * @throws OAException
	 * @throws OAException
	 */
	public void deleteRoute(Object newKeys, UserData ud) throws OAException {
		try {
			log.info("调用开始：删除路由。");
			// System.out.println("aaa"+newKeys);
			delete(newKeys, ud);

			log.info("结束调用：删除路由。");
		} catch (Exception e) {
			log.error("JgDaoHibernateHQLImpl.deleteJg(List newKeys, UserData ud)删除机构，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *新建路由
	 */
	public void create(Object newModel, UserData ud) throws OAException {
		try {
			log.info("开始调用：新建路由。");

			save(newModel, ud);
			// 创建用户

			log.info("结束调用：新建路由。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.createJg()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *按KEY查找机构
	 */
	public Object queryByKey(String newKey, UserData ud) throws OAException {
		JgModel officeSuppliesTypeModel = null;
		try {
			log.info("JgDaoHibernateHQLImpl.queryJgByKey()开始调用：通过编号机构。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(JgModel.class);
			cr.add(Restrictions.eq("jgId", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				officeSuppliesTypeModel = (JgModel) list.get(0);

			log.info("JgDaoHibernateHQLImpl.queryJgByKey()结束调用：通过编号机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.queryJgByKey()通过编号机构，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return officeSuppliesTypeModel;
	}

	/**
	 *保存机构
	 */
	public void saveRoute(Object newModel, UserData ud) throws OAException {
		try {
			log.info("JgDaoHibernateHQLImpl.saveJg()开始调用：保存机构。");

			update(newModel, ud);

			log.info("JgDaoHibernateHQLImpl.saveJg()结束调用：保存机构。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("JgDaoHibernateHQLImpl.saveJg()保存机构出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			// throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
