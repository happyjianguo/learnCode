package cn.yufu.posp.merchant.dao.hibernate.hql;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.merchant.domain.model.TabBusRoleMenuModel;



public class TabBusRoleMenuDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements TabBusRoleMenuDaoHibernateHQL {

	private static final Log log = LogFactory.getLog("merchant");

	public TabBusRoleMenuDaoHibernateHQLImpl() {

	}

	/**
	 * 查询所有对象
	 */
	public List queryAll(TabBusRoleMenuModel newQueryModel, int startIndex, int maxresults, String sortfield, String sortType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()开始调用：通过查询条件分页查询信息。");

			DetachedCriteria dcr = DetachedCriteria.forClass(TabBusRoleMenuModel.class);

			//按业务ID查询
			 if (newQueryModel.getBusRoleId() != null &&!newQueryModel.getBusRoleId().equals(""))
			 dcr.add(Restrictions.eq("busRoleId",newQueryModel.getBusRoleId()));
			
		
			// 添加排序信息
			if (sortType != null && sortfield != null) {
				if (sortType.equals("asc"))
					dcr.addOrder(Order.asc(sortfield));
				else
					dcr.addOrder(Order.desc(sortfield));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("busRoleId"));
			}

			list = findBYCriteria(dcr, startIndex, maxresults, ud);

			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.querySum()通过查询条件分页查询信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/**
	 * 查询记录总数
	 */
	public int querySum(TabBusRoleMenuModel queryModel, UserData ud) throws OAException {
		int count = 0;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()开始调用：查询符合条件的数量。");

			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select count(*) from TabBusRoleMenuModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0)
				count = Integer.parseInt(list.get(0).toString());
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.querySum()结束调用：查询符合条件的数量。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.querySum()调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return count;
	}

	/**
	 *显示一条记录
	 */
	public TabBusRoleMenuModel findItem(String newKey, UserData ud) throws OAException {
		TabBusRoleMenuModel model = null;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findItem()开始调用：显示一条记录。");
			Session session = getSession();
			// 基本信息
			Criteria cr = session.createCriteria(TabBusRoleMenuModel.class);
			cr.add(Restrictions.eq("busRoleId", newKey));
			List list = findByCriteria(cr, ud);
			if (list.size() > 0){
				model = (TabBusRoleMenuModel) list.get(0);
			}
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findItem()结束调用：显示一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.findItem()显示一条记录，出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

		return model;
	}

	

	/**
	 *修改一条记录
	 */
	public void saveItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.saveItem()开始调用：修改一条记录。");

			update(newModel, ud);
			
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.saveItem()结束调用：修改一条记录。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.saveItem()修改一条记录出现异常。");
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException("修改业务角色菜单时抛异常！");

		}
	}

	/**
	 *新建一条记录
	 */
	public void createItem(TabBusRoleMenuModel newModel, UserData ud) throws OAException {	
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.createItem()开始调用：保存一个信息。");
			// 创建业务角色ID(busRoleId)
			String busRoleId = findItemBusRoleId(newModel,ud);
			newModel.setBusRoleId(busRoleId);
			save(newModel, ud);
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.createItem()结束调用：保存一个信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.createItem()保存一个信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(e.getMessage());
		}
	}
	
	
	/**
	 * 创建业务角色ID(busRoleId)
	 */
	public String findItemBusRoleId(TabBusRoleMenuModel queryModel, UserData ud) throws OAException {
		String busRoleId = "";
		int count = 0;
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findItemBusRoleId()开始调用：创建业务ID(businessid)。");
	
			// 此处的表名应用映射对象表示
			StringBuffer bufferHql = new StringBuffer("select MAX(busRoleId) from TabBusRoleMenuModel o where 1=1");

			String hql = bufferHql.toString();

			List list = findByHQL(hql, ud);
			if (list.size() > 0){
				if(list.get(0)!=null){
					count = Integer.parseInt(list.get(0).toString());
					busRoleId=new DecimalFormat("000").format(count+1);	
				}else{
					busRoleId="001";
				}
			}
			
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("TabBusRoleMenuDaoHibernateHQLImpl.findItemBusinessId()创建业务角色ID(busRoleId)调用时出现异常。");
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return busRoleId;
	}
	
	
	//获取业务角色菜单列表
	public List<TabBusRoleMenuModel> findBusRoleList(UserData ud) throws OAException {
		List<TabBusRoleMenuModel> busRoleList = new ArrayList<TabBusRoleMenuModel>();
		try {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findBusRoleList()开始调用：获取业务角色菜单列表。");
			StringBuffer bufferHql = new StringBuffer(" from TabBusRoleMenuModel  t");
			bufferHql.append(" order by t.busRoleId asc ");
			String hql = bufferHql.toString();
			busRoleList = findByHQL(hql, ud);
			log.info("查询语句==" + hql);
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findBusRoleList()结束调用：获取业务角色菜单列表。");
		} catch (Exception e) {
			log.info("TabBusRoleMenuDaoHibernateHQLImpl.findBusRoleList()调用出现异常：获取业务角色菜单列表。");
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e, e.fillInStackTrace());
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return busRoleList;
	}
	
	//验证业务角色中文名称的唯一性
	public List<TabBusRoleMenuModel> findBusRoleNameKey(TabBusRoleMenuModel tabBusRoleMenuModel, UserData ud) throws OAException {
		List<TabBusRoleMenuModel> tabBusRoleMenuModelList = new ArrayList<TabBusRoleMenuModel>();
		try {
			StringBuffer bufferHql = new StringBuffer(" from TabBusRoleMenuModel  t where 1=1");


		// 根据业务角色中文名称查询
		if (tabBusRoleMenuModel.getBusRoleName() != null && !tabBusRoleMenuModel.getBusRoleName().equals(""))
			bufferHql.append(" and t.busRoleName = '" + tabBusRoleMenuModel.getBusRoleName() + "'");

			String hql = bufferHql.toString();
			tabBusRoleMenuModelList = findByHQL(hql, ud);

		} catch (Exception e) {

			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error(e.getMessage(), e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return tabBusRoleMenuModelList;
	}
}
