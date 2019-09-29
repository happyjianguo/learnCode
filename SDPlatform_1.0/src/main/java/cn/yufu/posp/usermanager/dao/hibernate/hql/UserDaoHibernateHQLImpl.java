package cn.yufu.posp.usermanager.dao.hibernate.hql;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
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
import cn.yufu.posp.usermanager.domain.model.OaUserInfo;
import cn.yufu.posp.usermanager.domain.model.OaUserInfoId;
import cn.yufu.posp.usermanager.domain.model.Test;

public class UserDaoHibernateHQLImpl extends OABaseDaoHibernateHQLImpl implements OaUserHibernateHQL {
	private static final Log log = LogFactory.getLog("user");

	public void createUser(OaUserInfo newModel, UserData ud) throws OAException {
		try {
			log.info("UserDaoHibernateHQLImpl.createUser()开始调用：创建一个用户信息。");

			save(newModel, ud);

			log.info("UserDaoHibernateHQLImpl.createUser()结束调用：创建一个用户信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.createUser()创建一个用户信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public void deleteUser(OaUserInfoId newKeys, UserData ud) throws OAException {
		try {
			log.info("UserDaoHibernateHQLImpl.saveuser()开始调用：删除一个用户信息。");
			List list = findByHQL("from OaUserInfo where id.userId='" + newKeys.getUserId() + "' and id.clientId='" + newKeys.getClientId() + "'", ud);
			if (list.size() > 0)
				delete(list.get(0), ud);
			log.info("UserDaoHibernateHQLImpl.saveuser()结束调用：删除一个用户信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.saveuser()删除一个用户信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public List queryUser(OaUserInfo queryModel, int startIndex, int maxresults, String orderField, String orderType, UserData ud) throws OAException {

		List list = null;
		try {
			log.info("UserDaoHibernateHQLImpl.queryUser()开始调用：通过查询条件分页查询信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaUserInfo.class);

			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));
			// 按机构ID查询
			if (queryModel.getUserId() != null && !queryModel.getUserId().equals(""))
				dcr.add(Restrictions.like("id.userId", queryModel.getUserId(), MatchMode.ANYWHERE));

			// 按机构名
			if (queryModel.getUserName() != null && !queryModel.getUserName().equals(""))
				dcr.add(Restrictions.like("userName", queryModel.getUserName(), MatchMode.ANYWHERE));

			// 添加排序信息
			if (orderType != null && orderField != null) {
				if (orderType.equals("asc"))
					dcr.addOrder(Order.asc(orderField));
				else
					dcr.addOrder(Order.desc(orderField));
			} else {
				// 按 id 排序
				dcr.addOrder(Order.asc("id.userId"));
			}
			list = findBYCriteria(dcr, startIndex, maxresults, ud);
			log.info("UserDaoHibernateHQLImpl.queryUser()结束调用：通过查询条件分页查询信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.queryUser()通过查询条件分页查询信息，调用出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list;
	}

	/*
	 * public List queryMenu(UserData ud) throws OAException {
	 * 
	 * List list = null; try { //log.info(
	 * "queryAllValidUser(Long inforId, UserData ud)开始调用：根据提交人的ID，步骤编号和InforId得到最后一条意见。"
	 * ); String hql = "from Test o"; list = findByHQL(hql, ud); } catch
	 * (Exception e) {
	 * 
	 * throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS); } return
	 * list; }
	 */

	// 给权限的数字转换成字母
	public String getZmString(String str) {
		String rstr = str;
		rstr = rstr.replaceAll(";", "");
		rstr = rstr.replaceAll("0", "0");
		rstr = rstr.replaceAll("1", "1");
		rstr = rstr.replaceAll("2", "2");
		rstr = rstr.replaceAll("3", "3");
		rstr = rstr.replaceAll("4", "4");
		rstr = rstr.replaceAll("5", "5");
		rstr = rstr.replaceAll("6", "6");
		rstr = rstr.replaceAll("7", "7");
		rstr = rstr.replaceAll("8", "8");
		rstr = rstr.replaceAll("9", "9");
		return rstr;
	}

	// 查询角色权限
	public HashMap queryGroupAccess(UserData ud) throws OAException {
		HashMap hm = new HashMap();
		List list = null;
		try {
			log.info("UserDaoHibernateHQLImpl.queryGroupAccess()开始调用：查询角色权限。");
			String hql = "from OaGroupAccess o where o.id.groupId='" + ud.getGroupId() + "' and o.id.clientId='" + ud.getClientId() + "'";
			list = findByHQL(hql, ud);
			OaGroupAccess oga = null;
			String str[] = null;
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					oga = (OaGroupAccess) list.get(i);
					str = oga.getId().getMenuId().split(";");
					hm.put(getZmString("x" + str[str.length - 1] + "x" + oga.getId().getScreenId()), "yes");
				}
			}
			log.info("UserDaoHibernateHQLImpl.queryGroupAccess()结束调用：查询角色权限。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.queryGroupAccess()查询角色权限，调用出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return hm;
	}

	public List queryZjMenu(UserData ud) throws OAException {
		List list = null;
		List list1 = new ArrayList();

		try {
			log.info("UserDaoHibernateHQLImpl.queryZjMenu()开始调用：查询菜单信息。");
			// String hql =
			// "select t.id,t.pid,t.name,t.url from test t,group_access o where o.group_id='"+ud.getGroupId()+"' and o.client_id='"+ud.getClientId()+"' and o.menu_id like '%;'||t.id||';%' group by t.id,t.pid,t.name,t.url";
			String hql = "select distinct m.MENU_ID,m.MENU_PARENT_ID,m.MENU_NAME,m.MENU_URL,m.MENU_SORT from GROUP_ACCESS r,CONSOLE_MENU m where r.group_id='" + ud.getGroupId()
					+ "'and  r.menu_id=m.menu_id  and r.client_id='" + ud.getClientId() + "' order by m.MENU_SORT";
			// System.out.println(hql);
			list = findBySQL(hql, ud);
			Object[] obj = null;
			Test t = null;
			for (int i = 0; i < list.size(); i++) {
				obj = (Object[]) list.get(i);
				t = new Test();
				t.setId((String) obj[0]);
				t.setPid((String) obj[1]);
				t.setName((String) obj[2]);
				t.setUrl((String) obj[3]);
				list1.add(t);
			}
			log.info("UserDaoHibernateHQLImpl.queryZjMenu()结束调用：查询菜单信息。");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("UserDaoHibernateHQLImpl.queryZjMenu()查询菜单信息，出现异常。");
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return list1;

	}

	public OaUserInfo queryUserByKey(OaUserInfoId newKey, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		OaUserInfo newModel = null;
		try {

			log.info("UserDaoHibernateHQLImpl.queryUserByKey()开始调用：按主键查询。");

			String hql = "from OaUserInfo f where f.id.userId = '" + newKey.getUserId() + "' and f.id.clientId='" + newKey.getClientId() + "'";
			// String hql = "from OaUserInfo f where f.id.userId = '" +
			// newKey.getUserId()+"'";

			List list = findByHQL(hql, ud);
			if (list.size() > 0) {
				newModel = (OaUserInfo) list.get(0);
			}

			log.info("UserDaoHibernateHQLImpl.queryUserByKey()结束调用：按主键查询。");
		} catch (Exception e) {
			log.error("UserDaoHibernateHQLImpl.queryUserByKey()按主键查询，出现异常。");
			log.error(e, e.fillInStackTrace());
			if (log.isDebugEnabled())
				e.printStackTrace();
			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return newModel;
	}

	public int queryUserCount(OaUserInfo queryModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		int i = 0;
		List list = null;
		try {
			log.info("UserDaoHibernateHQLImpl.queryUserCount()开始调用：查询用户信息。");
			DetachedCriteria dcr = DetachedCriteria.forClass(OaUserInfo.class);
			dcr.add(Restrictions.eq("id.clientId", ud.getClientId()));

			// 按机构ID查询
			if (queryModel.getUserId() != null && !queryModel.getUserId().equals(""))
				dcr.add(Restrictions.like("id.userId", queryModel.getUserId(), MatchMode.ANYWHERE));

			// 按机构名
			if (queryModel.getUserName() != null && !queryModel.getUserName().equals(""))
				dcr.add(Restrictions.like("userName", queryModel.getUserName(), MatchMode.ANYWHERE));

			list = findBYCriteria(dcr, ud);
			if (list != null) {
				i = list.size();
			}
			log.info("UserDaoHibernateHQLImpl.queryUserCount()结束调用：查询用户信息。");
		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.queryUserCount()调用出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}
		return i;
	}

	public void saveUser(OaUserInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		try {
			log.info("UserDaoHibernateHQLImpl.saveUser()开始调用：更新一个用户信息。");

			update(newModel, ud);

			log.info("UserDaoHibernateHQLImpl.saveUser()结束调用：更新一个用户信息。");

		} catch (Exception e) {
			if (log.isDebugEnabled())
				e.printStackTrace();

			log.error("UserDaoHibernateHQLImpl.saveUser()更新一个用户信息，出现异常。");
			log.error(e, e.fillInStackTrace());

			throw new OAException(ExceptionConstants.OA_COMMON_DBACCESS);
		}

	}

	public String userLogin(OaUserInfo newModel, UserData ud) throws OAException {
		// TODO Auto-generated method stub
		List list = findByHQL("from OaUserInfo where id=" + newModel.getId().getUserId() + " and password=" + newModel.getPassword(), ud);
		if (list != null) {
			// 得到权限信息列表

		} else {
			// 密码不正确
		}
		return null;
	}

	public void getFile(String groupid) throws Exception {

		String filename = "D:\\" + groupid + ".xml";
		File write = new File(filename);
		FileWriter fw = new FileWriter(write);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			String fenhang = "\r\n";
			StringBuffer bufferWriter = new StringBuffer();
			bufferWriter.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
			bufferWriter.append(fenhang);
			bufferWriter.append("<root text=\"ROOT\" open-action=\"true\">");
			bufferWriter.append(fenhang);
			bufferWriter.append("<menu id=\"XT\" text=\"系统管理\">");
			bufferWriter.append(fenhang);
			bufferWriter.append("<menu id=\"xt1\" href=\"javascript:goframe('../../User.do?method=queryUser')\" text=\"用户管理\" target=\"body\" />");
			bufferWriter.append(fenhang);
			bufferWriter.append("<menu id=\"xt2\" href=\"javascript:goframe('../../UserGroup.do?method=queryUserGroup')\" text=\"角色管理\" target=\"body\" />");
			bufferWriter.append(fenhang);
			bufferWriter.append("</menu>");
			bufferWriter.append(fenhang);
			bufferWriter.append("</root>");

			bw.write(bufferWriter.toString());
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.close();
			fw.close();
		}
	}

	public static void main(String args[]) {
		try {
			new UserDaoHibernateHQLImpl().getFile("llll");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 查询角色权限
	public List queryGroupAccessList(UserData ud) {
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

		}
		return list;
	}
}
