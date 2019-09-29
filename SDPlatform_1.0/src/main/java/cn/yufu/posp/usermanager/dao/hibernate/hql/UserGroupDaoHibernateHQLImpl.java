package cn.yufu.posp.usermanager.dao.hibernate.hql;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import cn.yufu.posp.common.common.exception.ExceptionConstants;
import cn.yufu.posp.common.common.exception.OAException;
import cn.yufu.posp.common.dao.hibernate.hql.OABaseDaoHibernateHQLImpl;
import cn.yufu.posp.common.domain.model.UserData;
import cn.yufu.posp.usermanager.domain.model.OaGroupAccess;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfo;
import cn.yufu.posp.usermanager.domain.model.OaGroupInfoId;

public class UserGroupDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements UserGroupDaoHibernateHQL {
	private static final Log log = LogFactory.getLog("user");

	public void createUserGroup(OaGroupInfo newModel, UserData ud) throws OAException {
		try {
			log.info("UserGroupDaoHibernateHQLImpl.createUserGroup()开始调用：创建角色信息。");

			save(newModel, ud);

			log.info("UserGroupDaoHibernateHQLImpl.createUserGroup()结束调用：创建角色信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.createUserGroup()创建角色信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		// TODO Auto-generated method stub

	}

	public void createUserGroupAccess(OaGroupAccess newModel, UserData ud) throws OAException {
		try {
			log.info("UserGroupDaoHibernateHQLImpl.createUserGroupAccess()开始调用：创建一个角色权限。");

			save(newModel, ud);

			log.info("UserGroupDaoHibernateHQLImpl.createUserGroupAccess()结束调用：创建一个角色权限。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.createUserGroupAccess()创建一个角色权限，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
	}

	public void deleteUserGroup(OaGroupInfoId newKeys, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserGroupDaoHibernateHQLImpl.deleteUserGroup()开始调用：删除角色信息。");
			List list = findByHQL("from OaGroupInfo where id.groupId='" + newKeys.getGroupId() + "' and id.clientId='" + newKeys.getClientId() + "'", ud);
			if (list.size() > 0) {
				delete(list.get(0), ud);
			}
			saveOrUpdateOrDeleteBySQL("delete from GROUP_ACCESS where CLIENT_ID='" + newKeys.getClientId() + "' and GROUP_ID='" + newKeys.getGroupId() + "'", ud);
			log.info("UserGroupDaoHibernateHQLImpl.deleteUserGroup()结束调用：删除角色信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.deleteUserGroup()删除角色信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	// 得到菜单列表
	public String getMenu(UserData ud) throws OAException {
		StringBuffer str = new StringBuffer();
		String sql = "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cm.menu_sort ,v.opt_num ";
		// if(!SystemConstants.mainMerchantId.equals(ud.getClientId())){
		// sql =
		// "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cast(t.menu_id as int),v.opt_num";
		// }
		List list = findBySQL(sql, ud);
		String nowid = "";
		Object[] obj = null;

		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			obj[0] = obj[0].toString().split(";")[obj[0].toString().split(";").length - 1];
			// 判断是否已经变换菜单
			if ("".equals(nowid)) {
				str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
				str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				nowid = (String) obj[0];
			} else {
				if (nowid.equals((String) obj[0])) {
					str.append("<input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				} else {
					// 结束上一个菜单列表的tr
					str.append("</td></tr>");
					// 开始新的tr
					str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
					str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
					nowid = (String) obj[0];
				}
			}

		}
		str.append("</td></tr>");
		return str.toString();
	}

	// 得到菜单列表,包括显示方式（已选，未选）
	public String getZjMenu(UserData ud) throws OAException {
		StringBuffer str = new StringBuffer();
		String sql = "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cm.menu_sort ,v.opt_num";
		// if(!SystemConstants.mainMerchantId.equals(ud.getClientId())){
		// sql =
		// "select cm.menu_id,t.opt_num,cm.menu_name,v.opt_name,cm.menu_parent_id,cm.menu_id from console_menu_opt_relation t,console_menu cm,console_opt v where t.menu_id=cm.menu_id and t.opt_num=v.opt_num  order by cast(t.menu_id as int),v.opt_num";
		// }
		List list = findBySQL(sql, ud);
		List list1 = queryGroupAccess(ud);
		HashMap acc = new HashMap();
		OaGroupAccess oga = null;
		for (int j = 0; j < list1.size(); j++) {
			oga = (OaGroupAccess) list1.get(j);
			acc.put(oga.getId().getMenuId() + "_" + oga.getId().getScreenId(), "yes");
		}
		String nowid = "";
		Object[] obj = null;
		String ifok = "";
		for (int i = 0; i < list.size(); i++) {
			obj = (Object[]) list.get(i);
			obj[0] = obj[0].toString().split(";")[obj[0].toString().split(";").length - 1];
			// 判断是否已经变换菜单
			if ("".equals(nowid)) {
				if (acc.get(obj[0] + "_" + obj[1]) != null) {
					ifok = "checked";
				} else {
					ifok = "";
				}
				str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
				str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " " + ifok + "/>" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				nowid = (String) obj[0];
			} else {
				if (nowid.equals((String) obj[0])) {
					if (acc.get(obj[0] + "_" + obj[1]) != null) {
						ifok = "checked";
					} else {
						ifok = "";
					}
					str.append("<input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " " + ifok + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
				} else {
					if (acc.get(obj[0] + "_" + obj[1]) != null) {
						ifok = "checked";
					} else {
						ifok = "";
					}
					// 结束上一个菜单列表的tr
					str.append("</td></tr>");
					// 开始新的tr
					str.append("<tr align=\"center\" onmouseover=\"rowOver(this)\" onmouseout=\"rowOut(this)\"><td>");
					str.append(obj[2] + ":</td><td><input type=\"checkbox\" name=\"text\" value=" + obj[0] + "_" + obj[1] + " " + ifok + " />" + obj[3] + "&nbsp;&nbsp;&nbsp;");
					nowid = (String) obj[0];
				}
			}

		}
		str.append("</td></tr>");
		return str.toString();
	}

	public List queryUserGroup(OaGroupInfo queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()开始调用：查询角色信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaGroupInfo.class);
			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));

			// 按机构ID查询
			if (queryModel.getCreateUser() != null && !queryModel.getCreateUser().equals(""))
				dcr.add(Restrictions.like("id.groupId", queryModel.getCreateUser(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("id.groupId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()结束调用：查询角色信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();
			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroup()查询角色信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;

	}

	// 查询角色权限
	public List queryGroupAccess(UserData ud) throws OAException {
		List list = null;
		try {
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()开始调用：查询角色权限。");
			String hql = "from OaGroupAccess o where o.id.groupId='" + ud.getGroupId() + "' and o.id.clientId='" + ud.getClientId() + "'";
			list = findByHQL(hql, ud);
			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroup()结束调用：查询角色权限。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroup()查询角色权限，调用出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public List getRoleRightList(String groupId, UserData ud) throws OAException {
		List list = null;
		try {
			log.info("UserGroupDaoHibernateHQLImpl.getRoleRightList()开始调用：查询角色权限。");
			String hql = "from OaGroupAccess o where o.id.groupId='" + groupId + "' and o.id.clientId = '" + ud.getClientId() + "' order by o.id.menuId";
			list = findByHQL(hql, ud);
			log.info("UserGroupDaoHibernateHQLImpl.getRoleRightList()结束调用：查询角色权限。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.getRoleRightList()查询角色权限，调用出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	public OaGroupInfo queryUserGroupByKey(OaGroupInfoId newKey, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		OaGroupInfo newModel = null;
		try {

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupByKey()开始调用：查询一个角色权限。");

			String hql = "from OaGroupInfo f where f.id.groupId = '" + newKey.getGroupId() + "' and f.id.clientId='" + newKey.getClientId() + "'";

			List list = findByHQL(hql, ud);
			if (list.size() > 0) {
				newModel = (OaGroupInfo) list.get(0);
			}

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupByKey()结束调用：查询一个角色权限。");
		} catch (Exception e) {
			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroupByKey()查询一个角色权限，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return newModel;
	}

	public int queryUserGroupCount(OaGroupInfo queryModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		int i = 0;
		List list = null;
		try {

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupCount()开始调用：查询角色权限总数。");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaGroupInfo.class);
			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));

			// 按机构ID查询
			if (queryModel.getCreateUser() != null && !queryModel.getCreateUser().equals(""))
				dcr.add(Restrictions.like("id.groupId", queryModel.getCreateUser(), MatchMode.ANYWHERE));

			list = findBYCriteria(dcr, ud);
			if (list != null) {
				i = list.size();
			}

			log.info("UserGroupDaoHibernateHQLImpl.queryUserGroupCount()结束调用：查询角色权限总数。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.queryUserGroupCount()查询角色权限总数，调用出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return i;
	}

	public void saveUserGroup(OaGroupInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserGroupDaoHibernateHQLImpl.saveUserGroup()开始调用：更新一个角色信息。");

			saveOrUpdate(newModel, ud);
			saveOrUpdateOrDeleteBySQL("delete from GROUP_ACCESS where CLIENT_ID='" + newModel.getId().getClientId() + "' and GROUP_ID='" + newModel.getId().getGroupId() + "'", ud);
			log.info("UserGroupDaoHibernateHQLImpl.saveUserGroup()结束调用：更新一个角色信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserGroupDaoHibernateHQLImpl.saveUserGroup()更新一个角色信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

}
