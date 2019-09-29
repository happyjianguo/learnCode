package cn.yufu.posp.client.dao.hibernate.hql;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.client.domain.model.OaClientComputerInfoModel;
import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;

public class OaClientComputerInfoDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements OaClientComputerInfoDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("errctl");

	public OaClientComputerInfoDaoHibernateHQLImpl() {

	}

	// =====物品表==========================================================

	/**
	 *查找记录总数
	 */
	public int querySum(OaClientComputerInfoModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from OaClientComputerInfoModel o where 1=1");

			// 按名称查询
			if (queryModel.getMacAddr() != null && !queryModel.getMacAddr().equals(""))
				bufferHql.append(" and o.macAddr like '%" + queryModel.getMacAddr() + "%'");

			// 按类别 查询
			// if(queryModel.getSort_id()!=null &&
			// !queryModel.getSort_id().equals("") )
			// bufferHql.append(" and o.officeSuppliesType.id = "+queryModel.getSort_id()+"");
			//			
			// 按部门查询
			// if(ud.getCsbh()!=null && !ud.getCsbh().equals("") )
			// bufferHql.append(" and o.department_id = '"+ud.getCsbh()+"'");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;

	}

	/**
	 *查找所有记录
	 */
	public List queryAll(OaClientComputerInfoModel queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud)
			throws OAException {
		List list = null;
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.queryAll()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(OaClientComputerInfoModel.class);

			// 按名称查询
			if (queryModel.getMacAddr() != null && !queryModel.getMacAddr().equals(""))
				dcr.add(Restrictions.like("macAddr", queryModel.getMacAddr(), MatchMode.ANYWHERE));

			// 按类别 查询
			// if(queryModel.getSort_id()!=null &&
			// !queryModel.getSort_id().equals("") )
			// dcr.add(Restrictions.eq("officeSuppliesType.id",queryModel.getSort_id()));

			// 按部门查询
			// if(ud.getCsbh()!=null && !ud.getCsbh().equals("") )
			// dcr.add(Restrictions.eq("department_id",ud.getCsbh()));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("userId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.queryAll()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.queryAll()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 *删除一条记录
	 */
	public void deleteItem(List newKeys, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.deleteItem()调用开始：删除一条记录。");

			// 这种删法，会连子表一同删除

			for (int i = 0; i < newKeys.size(); i++) {
				delete(findItem(new String(newKeys.get(i) + ""), ud), ud);

			}

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.deleteItem()结束调用：删除一条记录。");
		} catch (Exception e) {
			log.error("OaClientComputerInfoDaoHibernateHQLImpl.deleteItem()删除一条记录，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");

			save(newModel, ud);

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	/**
	 *显示一条记录
	 */
	public OaClientComputerInfoModel findItem(String newKey, UserData ud) throws OAException {
		OaClientComputerInfoModel model = null;
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			List list = null;
			Session session = getSession();
			Criteria cr = session.createCriteria(OaClientComputerInfoModel.class);
			cr.add(Restrictions.eq("macAddr", newKey));
			list = findByCriteria(cr, ud);
			if (list.size() > 0)
				model = (OaClientComputerInfoModel) list.get(0);

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return model;
	}

	/**
	 *修改一条记录
	 */
	public void saveItem(OaClientComputerInfoModel newModel, UserData ud) throws OAException {
		try {
			log.info("OaClientComputerInfoDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);

			log.info("OaClientComputerInfoDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("OaClientComputerInfoDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

}
