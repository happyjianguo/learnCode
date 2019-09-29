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
 * @TODO ʵ���û���ҵ���߼�����
 * 
 * @author �Ʊ�
 * @created on 2007-12-20 17:26:36
 * @version 1.0
 */
public class UserDao {
	private static final Logger logger = Logger.getLogger(UserDao.class);
	private String sql = "";
	private int result = 0;

	/**
	 * 
	 * @TODO �˶Ե�¼�û�
	 * 
	 * @param usercode
	 * @param passwd
	 * @return ��¼�û�����
	 * @author �Ʊ�
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
	 * @TODO ����û�
	 * 
	 * @param userBean
	 * @return ִ�н��
	 * @author �Ʊ�
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
	 * @TODO �޸��û�
	 * 
	 * @param userBean
	 * @return ִ�н��
	 * @author �Ʊ�
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
	 * @TODO ɾ���û���Ϣ 1��ɾ���û���Ϣ���е������Ϣ 2��ɾ���û��û����Ӧ��Ϣ���������Ϣ 3��ɾ���û����ܶ�Ӧ��Ϣ���и��û���������Ϣ
	 * 
	 * @param usercode
	 * @return ִ�н��
	 * @author �Ʊ�
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
	 * @TODO ��������û���Ϣ����
	 * 
	 * @return �û���Ϣ����
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
	 * @TODO ��������û���Ϣ�б�
	 * 
	 * @return �û���Ϣ�б�
	 * @author �Ʊ�
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
	 * @TODO ��ѯ�û���Ϣ����
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
	 * @TODO ��ѯ�û���Ϣ
	 * 
	 * @param usercodeQ
	 * @param teamnoQ
	 * @return
	 * @author �Ʊ�
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
	 * @TODO �鿴�û���ϸ��Ϣ
	 * 
	 * @param usercode
	 * @return �û�����
	 * @author �Ʊ�
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
	 * @TODO �����û�������û�ѡ���һ���˵���Ų�ѯ���û���Ӧ�Ķ�������Ȩ��
	 * 
	 * @param usercode
	 * @param menuId
	 * @return Ȩ���б�
	 * @author �Ʊ�
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
	 * @TODO ��ȡ�û����߱�־
	 * 
	 * @param userCode
	 * @return
	 * @author �Ʊ�
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
	 * @TODO �޸��û����߱�־
	 * 
	 * @param userBean
	 * @param loginflag
	 *            0�����ߡ�1����
	 * @return ����ִ�н��
	 * @author �Ʊ�
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
	 * @TODO �޸��û����һ�ε�¼ʱ��
	 * 
	 * @param userCode
	 * @return
	 * @author �Ʊ�
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
	 * @TODO ���û�����Ȩ�ޣ����Ȩ��Ϊ���û������û���ģ� 1��ɾ���û����ܶ�Ӧ��Ϣ���и��û�������Ȩ����Ϣ
	 *       2�����û����ܶ�Ӧ������Ӹ����û��·��������Ȩ����Ϣ
	 * 
	 * @param usercode
	 * @param itemList
	 * @return
	 * @author �Ʊ�
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
		 * ��һ��
		 */
		sql = "delete from user_itemauth where usercode=?";
		String[] param1 = { usercode };
		sqlList.add(sql);
		paramList.add(param1);
		/**
		 * �ڶ���
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
	 * @TODO �޸��û�����
	 * 
	 * @param usercode
	 * @param passwd
	 *            ԭ����
	 * @param passwdNew
	 *            ������
	 * @param passwddays
	 *            ����ʧЧ����
	 * @return
	 * @author �Ʊ�
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
	 * @TODO �˶�������
	 * 
	 * @param passwdNew
	 * @return �������뷵�ظ������ַ��������򷵻�NULL
	 * @author �Ʊ�
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
	 * @TODO �����û��������������ʱ��
	 * 
	 * @param usercode
	 * @param passwddays
	 * @return
	 * @author �Ʊ�
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

		params.add("96e79218965eb72c92a549dd5a330112");// ��������:MD5���ܺ������1
		params.add("0");// ���һ�ε�¼ʱ��Ϊ0����ʾ���û�û�е�¼��.
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
	 * @TODO �ж�ϵͳ����Աֻ���޸ı����û�
	 * 
	 * @param usercode
	 * @param passwddays
	 * @return
	 * @author ����
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
	 * @TODO ��ȡ�û���¼ʱ��
	 * 
	 * @param usercode
	 * @return �û���¼ʱ��
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
	 * @TODO �����û�������û�ѡ���һ���˵���Ų�ѯ���û����ܵ���ɾ��Ȩ��
	 * 
	 * @param usercode
	 * @param menuno
	 * @return Ȩ���б�
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
	 * �û�����Ʒӳ���û�LIST����ӳ���˵��û�����USER LIST�С� 
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
	 * �û�����Ʒӳ���û�LIST����ӳ���˵��û�����USER LIST�С� 
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
