package com.pay.system.user.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.menu.bean.MenuBean;
import com.pay.system.user.bean.UserBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * 
 * @TODO 实现用户的业务逻辑操作
 * 
 * @author 黄斌
 * @created on 2007-12-20 17:26:36
 * @version 1.0
 */
public class UserDao {
	private static final Logger logger = Logger.getLogger(UserDao.class);
	private String sql = "";
	private int result = 0;

	/**
	 * 
	 * @TODO 核对登录用户
	 * 
	 * @param usercode
	 * @param passwd
	 * @return 登录用户对象
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-20  17:26:02
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public UserBean checkUser(String usercode, String passwd) {
		UserBean userBean = null;

		sql = "select * from Monuserinfo where user_code=? and user_passwd=?";// and user_isactive=?";

		ArrayList params = new ArrayList();
		params.add(usercode);
		params.add(passwd);
		//params.add("1");
		try {
			Vector userVector = DbExec.execQuery(sql, params);
			if (userVector != null && !userVector.isEmpty()) {
				userBean = new UserBean((HashMap) userVector.get(0));
			}
		} catch (SQLException e) {
			logger.error(usercode + "|" + passwd + "|", e);
		}catch (Exception e) {
			logger.error(usercode + "|" + passwd + "|", e);
		}
		return userBean;
	}

	/**
	 * 
	 * @TODO 添加用户
	 * 
	 * @param userBean
	 * @return 执行结果
	 * @author 黄斌
	 * @version 1.0
	 * 
	 * <pre>
	 * Created on:2007-12-20  17:27:24
	 * LastModified:2009-8-21	zl
	 * History:
	 * </pre>
	 */
	public int addUser(UserBean userBean) {
		sql = "insert into Monuserinfo (user_code,user_name,user_passwd,dept_no,role_no,user_address,user_postalcode,user_mail,user_fax," +
				"user_phone,user_loginflag,user_lastintime,user_pwdinvldday,user_validdays,user_isactive,user_mac) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		ArrayList params = new ArrayList();

		params.add( (userBean.getUsercode()) );
		params.add( (userBean.getUsername()) );
		params.add( (userBean.getPasswd()) );
		params.add( (userBean.getDeptno()) );
		params.add( (userBean.getRoleno()) );
		params.add( (userBean.getAddress()) );
		params.add( (userBean.getPostalcode()) );
		params.add( (userBean.getMail()) );
		params.add( (userBean.getFax()) );
		params.add( (userBean.getPhone()) );
		params.add( (userBean.getLoginflag()) );
		params.add( (userBean.getLastintime()) );
		params.add( (userBean.getPasswddays()) );
		params.add( (userBean.getValiddays()) );
		params.add( (userBean.getIsactive()) );
		params.add( (userBean.getMac()));
		try {
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			logger.error(userBean.toString(), e);
			result = -1;
		}catch (Exception e) {
			logger.error(userBean.toString(), e);
			result = -1;
		}
		return result;
	}

	/**
	 * 
	 * @TODO 修改用户
	 * 
	 * @param userBean
	 * @return 执行结果
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-20  17:27:46
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public int modifyUser(UserBean userBean) {
		sql = "update Monuserinfo set user_name=?,user_passwd=?,dept_no=?,role_no=?,user_address=?,user_postalcode=?,user_mail=?,user_fax=?,user_phone=?," +
				"user_pwdinvldday=?,user_validdays=?,user_isactive=?,user_mac=?  where user_code=?";

		ArrayList params = new ArrayList();

		params.add(userBean.getUsername());
		params.add(userBean.getPasswd());
		
		
		
		params.add(userBean.getDeptno());
		params.add(userBean.getRoleno());
		params.add(userBean.getAddress());
		params.add(userBean.getPostalcode());
		params.add(userBean.getMail());
		params.add(userBean.getFax());
		params.add(userBean.getPhone());
		params.add(userBean.getPasswddays());
		params.add(userBean.getValiddays());
		params.add((userBean.getIsactive()));
		params.add(userBean.getMac());
		params.add(userBean.getUsercode());
		try {
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			result = -1;
			logger.error(userBean.toString(), e);
		}catch (Exception e) {
			result = -1;
			logger.error(userBean.toString(), e);
		}
		return result;
	}

	/**
	 * 
	 * @TODO 删除用户信息 1、删除用户信息表中的相关信息 2、删除用户用户组对应信息表中相关信息 3、删除用户功能对应信息表中该用户的所有信息
	 * 
	 * @param usercode
	 * @return 执行结果
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-20  17:28:08
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public int deleteUser(String usercode) {
		List sqlList = new ArrayList();
		List paramList = new ArrayList();
		String sql1 = "delete from Monuserinfo where user_code=?";
		String[] param1 = { usercode };
//		String sql2 = "delete from Monuserrole where user_code=?";
//		String[] param2 = { usercode };

		sqlList.add(sql1);
		paramList.add(param1);
//		sqlList.add(sql2);
//		paramList.add(param2);

		try {
			result = DbExec.execBatch(sqlList, paramList);
		} catch (SQLException e) {
			result = -1;
			logger.error(usercode + "|", e);
		}catch (Exception e) {
			result = -1;
			logger.error(usercode + "|", e);
		}
		return result;
	}

	/**
	 * 
	 * @TODO 获得所有用户信息总数
	 * 
	 * @return 用户信息总数
	 * @author zl
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2009-11-18
	 * LastModified:
	 * History:
	 * </pre>
	 */
	public int getCount(String dept_no_node,String roleno) {
		int count = 0;
		sql = "select count(*) as numbers from Monuserinfo a,Monroleinfo b,Mondeptinfo c where a.role_no=b.role_no and a.dept_no=c.dept_no ";
		if(roleno!=null && roleno.equals("00")){
			sql = sql + " order by user_code";
		}else{
			sql = sql + "and a.dept_no in ("+dept_no_node+") order by user_code";
		}
		
		
		List list = new ArrayList();
		try {
			Vector userVector = DbExec.execQuery(sql);
			if (userVector != null && !userVector.isEmpty()) {
				HashMap map = (HashMap) userVector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return count;
	}

	/**
	 * 
	 * @TODO 获得所有用户信息列表
	 * 
	 * @return 用户信息列表
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-20  17:30:00
	 * LastModified:2009-8-19	zl
	 * History:
	 * </pre>
	 */
	public List showUserList(String dept_no_node,String roleno,PageBean pageBean) {
		sql = "select a.*,b.role_name,c.dept_name from Monuserinfo a,Monroleinfo b,Mondeptinfo c where a.role_no=b.role_no and a.dept_no=c.dept_no ";
		if(roleno!=null && roleno.equals("00")){
			sql = sql + " order by user_code";
		}else{
			sql = sql + "and a.dept_no in ("+dept_no_node+") order by user_code";
		}
		
		
		List list = new ArrayList();
		try {
			Vector userVector = DbExec.execQuery(sql, pageBean.getStart(), pageBean.getPageSize());
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector
							.get(i));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	/**
	 * 
	 * @TODO 查询用户信息总数
	 * 
	 * @param usercodeQ
	 * @param teamnoQ
	 * @return
	 * @author zl
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2009-11-18
	 * LastModified:
	 * History:
	 * </pre>
	 */
	public int getCount(String usercodeQ, String rolenoQ, String deptnoQ,String dept_no_node,String role) {
		int count = 0;
		List condList = new ArrayList();
		List list = new ArrayList();
		condList.add("%" + usercodeQ + "%");
		condList.add("%" + rolenoQ + "%");
		condList.add("%" + deptnoQ + "%");
		sql = "select count(*) as numbers from Monuserinfo a,Monroleinfo b,Mondeptinfo c where a.role_no=b.role_no and a.dept_no=c.dept_no and a.user_code like ? and a.role_no like ? and a.dept_no like ? ";
		if(role!=null && role.equals("00")){
			sql = sql + " order by a.user_code";
		}else{
			sql = sql + "and a.dept_no in ("+dept_no_node+") order by a.user_code";
		}
		try {
			Vector userVector = DbExec.execQuery(sql, condList);
			if (userVector != null && !userVector.isEmpty()) {
				HashMap map = (HashMap) userVector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());
			}
		} catch (SQLException e) {
			logger.error(usercodeQ + "|" + rolenoQ + "|", e);
		}catch (Exception e) {
			logger.error(usercodeQ + "|" + rolenoQ + "|", e);
		}
		return count;
	}

	/**
	 * 
	 * @TODO 查询用户信息
	 * 
	 * @param usercodeQ
	 * @param teamnoQ
	 * @return
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-3-31  16:18:46
	 * LastModified:2009-8-19	zl
	 * History:
	 * </pre>
	 */
	public List queryUserList(String usercodeQ, String rolenoQ, String deptnoQ,String dept_no_node,String role,PageBean pageBean) {
		List condList = new ArrayList();
		List list = new ArrayList();
		condList.add("%" + usercodeQ + "%");
		condList.add("%" + rolenoQ + "%");
		condList.add("%" + deptnoQ + "%");
		sql = "select a.*,b.role_name,c.dept_name from Monuserinfo a,Monroleinfo b,Mondeptinfo c where a.role_no=b.role_no and a.dept_no=c.dept_no and a.user_code like ? and a.role_no like ? and a.dept_no like ? ";
		if(role!=null && role.equals("00")){
			sql = sql + " order by a.user_code";
		}else{
			sql = sql + "and a.dept_no in ("+dept_no_node+") order by a.user_code";
		}
		try {
			Vector userVector = DbExec.execQuery(sql, condList, pageBean.getStart(), pageBean.getPageSize());
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector
							.get(i));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			logger.error(usercodeQ + "|" + rolenoQ + "|", e);
		}catch (Exception e) {
			logger.error(usercodeQ + "|" + rolenoQ + "|", e);
		}
		return list;
	}

	/**
	 * 
	 * @TODO 查看用户详细信息
	 * 
	 * @param usercode
	 * @return 用户对象
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-20  17:33:09
	 * LastModified:2009-8-19	zl
	 * History:
	 * </pre>
	 */
	public UserBean getUserInfo(String usercode,String roleno) {
	
			sql = "select a.*,b.role_name,c.dept_name,c.dept_level from Monuserinfo a,Monroleinfo b,Mondeptinfo c where a.role_no=b.role_no and a.dept_no=c.dept_no and user_code=?";

		ArrayList params = new ArrayList();
		params.add(usercode);

		UserBean userBean = null;

		try {
			Vector userVector = DbExec.execQuery(sql, params);
			if (userVector != null && !userVector.isEmpty()) {
				userBean = new UserBean((HashMap) userVector.get(0));
			}
		} catch (SQLException e) {
			logger.error(usercode + "|", e);
		}catch (Exception e) {
			logger.error(usercode + "|", e);
		}
		return userBean;
	}

	/**
	 * 
	 * @TODO 根据用户编号与用户选择的一级菜单编号查询该用户对应的二级功能权限
	 * 
	 * @param usercode
	 * @param menuId
	 * @return 权限列表
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-20  17:34:05
	 * LastModified:2009-8-21	zl
	 * History:
	 * </pre>
	 */
	public List getUserMenuByParentMenuId(String usercode, String menuId) {
		sql = "select c.* from Monuserinfo a,Monrolemenu b,Monmenuinfo c " +
				"where a.role_no=b.role_no and b.menu_no=c.menu_no and a.user_code=? and b.menu_no like ? order by b.menu_no";

		List list = new ArrayList();
		
		List params = new ArrayList();
		params.add(usercode);
		params.add(menuId + "%");
		try {
			Vector menuVector = DbExec.execQuery(sql, params);
			if (menuVector != null && !menuVector.isEmpty()) {
				for (int i = 0; i < menuVector.size(); i++) {
					MenuBean menuBean = new MenuBean((HashMap) menuVector.get(i));
					list.add(menuBean);
				}
			}
		} catch (SQLException e) {
			logger.error(usercode + "|" + menuId + "|", e);
		} catch (Exception e) {
			logger.error(usercode + "|" + menuId + "|", e);
		}
		return list;
	}

	/**
	 * 
	 * @TODO 获取用户在线标志
	 * 
	 * @param userCode
	 * @return
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-4-17  11:10:34
	 * LastModified
	 * History:
	 * </pre>
	 */
	public String getUserLoginflag(String userCode) {
		String loginflag = "";

		sql = "select user_loginflag from Monuserinfo where user_code=?";

		ArrayList params = new ArrayList();
		params.add(userCode);

		try {
			Vector vector = DbExec.execQuery(sql, params);
			if (vector != null && !vector.isEmpty()) {
				loginflag = (String) ((HashMap) vector.get(0)).get("loginflag");
			}
		} catch (SQLException e) {
			logger.error(userCode + "|", e);
		}catch (Exception e) {
			logger.error(userCode + "|", e);
		}
		return loginflag;
	}

	/**
	 * 
	 * @TODO 修改用户在线标志
	 * 
	 * @param userBean
	 * @param loginflag
	 *            0不在线、1在线
	 * @return 方法执行结果
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2007-12-19  18:20:08
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public int setLoginflag(String userCode, String loginflag) {
		sql = "update Monuserinfo set user_loginflag =? where user_code=?";

		ArrayList params = new ArrayList();

		params.add(loginflag);
		params.add(userCode);

		try {
			DbExec.execUpdate(sql, params);
			result = 0;
		} catch (SQLException e) {
			result = -1;
			logger.error(userCode + "|" + loginflag + "|", e);
		}catch (Exception e) {
			result = -1;
			logger.error(userCode + "|" + loginflag + "|", e);
		}
		return result;
	}

	/**
	 * 
	 * @TODO 修改用户最后一次登录时间
	 * 
	 * @param userCode
	 * @return
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-1-25  16:06:45
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public int setLastintime(String userCode, String lastintime) {
		sql = "update Monuserinfo set user_lastintime=? where user_code=?";

		ArrayList params = new ArrayList();

		params.add(lastintime);
		params.add(userCode);

		try {
			DbExec.execUpdate(sql, params);
			result = 0;
		} catch (SQLException e) {
			result = -1;
			logger.error(userCode + "|", e);
		}catch (Exception e) {
			result = -1;
			logger.error(userCode + "|", e);
		}
		return result;
	}

	/**
	 * 
	 * @TODO 给用户分配权限（最大权限为该用户所属用户组的） 1、删除用户功能对应信息表中该用户的所有权限信息
	 *       2、在用户功能对应表中添加给该用户新分配的所有权限信息
	 * 
	 * @param usercode
	 * @param itemList
	 * @return
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-1-25  16:19:40
	 * LastModified
	 * History:
	 * </pre>
	 */
	public int grantItemToUser(String usercode, List itemList) {
		if (usercode == null)
			return -1;
		List sqlList = new ArrayList();
		List paramList = new ArrayList();
		/**
		 * 第一步
		 */
		sql = "delete from user_itemauth where usercode=?";
		String[] param1 = { usercode };
		sqlList.add(sql);
		paramList.add(param1);
		/**
		 * 第二步
		 */
		Iterator it = itemList.iterator();
//		while (it.hasNext()) {
//			ItemBean itemBean = (ItemBean) it.next();
//			sql = "insert into user_itemauth (usercode,itemno,itemname,itemdesc) values(?,?,?,?)";
//
//			String[] param2 = { usercode, itemBean.getItemno(),
//					StringUtils.outerToInner(itemBean.getItemname()),
//					StringUtils.outerToInner(itemBean.getItemdescrip()) };
//			sqlList.add(sql);
//			paramList.add(param2);
//		}
//		try {
//			result = DbExec.execBatch(sqlList, paramList);
//		} catch (SQLException e) {
//			result = -1;
//			StringBuffer sb = new StringBuffer();
//			sb.append(usercode);
//			sb.append("|");
//			while (it.hasNext()) {
//				ItemBean itemBean = (ItemBean) it.next();
//				sb.append(itemBean.getItemno());
//				sb.append("|");
//				sb.append(itemBean.getItemname());
//				sb.append("|");
//				sb.append(itemBean.getItemdescrip());
//				sb.append("|");
//			}
//			logger.error(new String(sb), e);
//		}
		return result;
	}

	/**
	 * 
	 * @TODO 修改用户密码
	 * 
	 * @param usercode
	 * @param passwd
	 *            原密码
	 * @param passwdNew
	 *            新密码
	 * @param passwddays
	 *            密码失效日期
	 * @return
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-1-28  17:28:49
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public int modifyPassword(String usercode, String passwd, String passwdNew,
			String passwddays) {
		sql = "update Monuserinfo set user_passwd=?,user_pwdinvldday=? where user_code=? and user_passwd=?";

		ArrayList params = new ArrayList();

		params.add(passwdNew);
		params.add(passwddays);
		params.add(usercode);
		params.add(passwd);

		try {
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			result = -1;
			logger.error(usercode + "|" + passwd + "|" + passwdNew + "|"
					+ passwddays, e);
		}catch (Exception e) {
			result = -1;
			logger.error(usercode + "|" + passwd + "|" + passwdNew + "|"
					+ passwddays, e);
		}
		return result;
	}

	/**
	 * 
	 * @TODO 核对弱密码
	 * 
	 * @param passwdNew
	 * @return 是若密码返回该密码字符串，否则返回NULL
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-1-29  11:39:13
	 * LastModified
	 * History:
	 * </pre>
	 */
	public String checkWeakPasswd(String passwdNew) {
		String weakPasswd = null;
		sql = "select passwd from user_weak_passwd where passwd=?";
		List condList = new ArrayList();
		condList.add(passwdNew);
		try {
			Vector vector = DbExec.execQuery(sql, condList);
			if (vector != null && !vector.isEmpty()) {
				HashMap map = (HashMap) vector.get(0);
				weakPasswd = (String) map.get("passwd");
			}
		} catch (SQLException e) {
			logger.error(passwdNew + "|", e);
		}catch (Exception e) {
			logger.error(passwdNew + "|", e);
		}
		return weakPasswd;
	}

	/**
	 * 
	 * @TODO 重置用户密码与密码过期时间
	 * 
	 * @param usercode
	 * @param passwddays
	 * @return
	 * @author 黄斌
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-4-4  15:32:24
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public int resetUser(String usercode, String passwddays) {
		sql = "update Monuserinfo set user_passwd=?,user_lastintime=?,user_pwdinvldday=? where user_code=?";

		ArrayList params = new ArrayList();

		params.add("96e79218965eb72c92a549dd5a330112");// 重置密码:MD5加密后的六个1
		params.add("0");// 最后一次登录时间为0，表示该用户没有登录过.
		params.add(passwddays);
		params.add(usercode);

		try {
			result = DbExec.execUpdate(sql, params);
		} catch (SQLException e) {
			result = -1;
			logger.error(usercode + "|" + passwddays + "|", e);
		}catch (Exception e) {
			result = -1;
			logger.error(usercode + "|" + passwddays + "|", e);
		}

		return result;
	}
	
	/**
	 * 
	 * @TODO 判断系统管理员只能修改本的用户
	 * 
	 * @param usercode
	 * @param passwddays
	 * @return
	 * @author 宁波
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2008-4-4  15:32:24
	 * LastModified:2009-8-24	zl
	 * History:
	 * </pre>
	 */
	public boolean checkDeptNo(String user_dept_no,String dept_no){
		if(user_dept_no!=null && dept_no !=null){
			if(user_dept_no.equals(dept_no)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @TODO 获取用户登录时间
	 * 
	 * @param usercode
	 * @return 用户登录时间
	 * @author zl
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2009-11-10	
	 * LastModified:
	 * History:
	 * </pre>
	 */
	public String getLastInTime(String usercode) {
		String time = "";

		sql = "select user_lastintime from Monuserinfo where user_code=?";

		ArrayList params = new ArrayList();
		params.add(usercode);
		try {
			Vector userVector = DbExec.execQuery(sql, params);
			if (userVector != null && !userVector.isEmpty()) {
				time = ((String) ((HashMap) userVector.get(0)).get("user_lastintime")).trim();
			}
		} catch (SQLException e) {
			logger.error(usercode + "|", e);
		}catch (Exception e) {
			logger.error(usercode + "|", e);
		}
		return time;
	}

	/**
	 * 
	 * @TODO 根据用户编号与用户选择的一级菜单编号查询该用户功能的增删改权限
	 * 
	 * @param usercode
	 * @param menuno
	 * @return 权限列表
	 * @author zl
	 * @version 1.0
	 * 
	 *          <pre>
	 * Created on:2009-11-19
	 * LastModified:
	 * History:
	 * </pre>
	 */
	public String getMenuLevelByParentMenuId(String usercode, String menuno) {
		String menu_level = "";
		sql = "select b.menu_level from Monuserinfo a,Monrolemenu b " +
				"where a.role_no=b.role_no and a.user_code=? and b.menu_no = ?";

		List list = new ArrayList();
		
		List params = new ArrayList();
		params.add(usercode);
		params.add(menuno);
		try {
			Vector menuVector = DbExec.execQuery(sql, params);
			if (menuVector != null && !menuVector.isEmpty()) {
				HashMap map = (HashMap) menuVector.get(0);
				menu_level = (String) map.get("menu_level");
				if (menu_level == null) menu_level = "";
			}
		} catch (SQLException e) {
			logger.error(usercode + "|" + menuno + "|", e);
		}catch (Exception e) {
			logger.error(usercode + "|" + menuno + "|", e);
		}
		return menu_level;
	}
	public List showAllUserList() {
		sql = "select a.* from Monuserinfo a ";
		
		
		List list = new ArrayList();
		try {
			Vector userVector = DbExec.execQuery(sql);
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector.get(i));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
	public List showAllUserOfFkList() {
		sql = "select a.login_name as user_code,a.name as user_name FROM sys_user a";
		List list = new ArrayList();
		try {
			Vector userVector = DbExec.execQueryFk(sql);
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector.get(i));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
	/**
	 * 用户卡产品映射用户LIST：已映射了的用户不再USER LIST中。 
	 * @return
	 */
	public List showCrdproductUserList() {
		sql = "select a.user_code,a.user_name from Monuserinfo a where a.user_code not in (select user_code from monuser_crdproduct) ";		
		List list = new ArrayList();
		try {
			Vector userVector = DbExec.execQuery(sql);
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector.get(i));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	public String getExistsUserListStr() {
		sql = "select a.user_code from monuser_crdproduct a ";		
		String str="";
		try {
			Vector userVector = DbExec.execQuery(sql);
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector.get(i));
					if(i!=userVector.size()-1){
						str=str+userBean.getUsercode()+"','";						
					}else{
						str=str+userBean.getUsercode();						
					}
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return "'"+str+"'";
	}
	
	/**
	 * 用户卡产品映射用户LIST：已映射了的用户不再USER LIST中。 
	 * @return
	 */
	public List showCrdproductUserOfFkList() {
		String existsUserListStr=this.getExistsUserListStr(); 
		sql = "select a.login_name as user_code,a.name as user_name FROM sys_user a  where a.login_name not in ("+existsUserListStr+") ";		
		List list = new ArrayList();
		try {
			Vector userVector = DbExec.execQueryFk(sql);
			if (userVector != null && !userVector.isEmpty()) {
				for (int i = 0; i < userVector.size(); i++) {
					UserBean userBean = new UserBean((HashMap) userVector.get(i));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			logger.error(e);
		}catch (Exception e) {
			logger.error(e);
		}
		return list;
	}
}
