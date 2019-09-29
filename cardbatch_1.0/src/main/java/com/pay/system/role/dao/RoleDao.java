package com.pay.system.role.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.menu.bean.MenuBean;
import com.pay.system.role.bean.RoleBean;
import com.pay.util.DbExec;
import com.pay.util.PageBean;

/**
 * 
 * @TODO 角色类的增、删、改、查功能 
 *
 * @author 黄斌
 * @created on 2007-12-20  11:00:37
 * @version 1.0
 */
public class RoleDao {
    private static final Logger logger = Logger.getLogger(RoleDao.class);
    private String sql = "";
    private int result = 0;

    /**
     * 
     * @TODO 添加角色信息
     *
     * @param roleBean
     * @return 添加结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  11:02:12
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public int checkrole(RoleBean roleBean){
    	result=0;
    	ArrayList paramscheck = new ArrayList();
        sql="select role_no from Monroleinfo where role_no=?";
        paramscheck.add(roleBean.getRoleno());
        try{
        	 Vector roleVector = DbExec.execQuery(sql, paramscheck);
             if (roleVector != null && !roleVector.isEmpty()) {
            	 result=-2;
                 return result;
             }
        }catch(Exception e){
        	 result = -1;
             logger.error("SQLException:" + roleBean, e);
        }
        paramscheck = new ArrayList();
        sql="select role_no from Monroleinfo where role_name=?";
        paramscheck.add(roleBean.getRolename());
        try{
        	 Vector roleVector = DbExec.execQuery(sql, paramscheck);
             if (roleVector != null && !roleVector.isEmpty()) {
            	 result=-3;
                 return result;
             }
        }catch(Exception e){
        	 result = -1;
             logger.error("SQLException:" + roleBean, e);
        }
        return result;
    }
    
    public int addRole(RoleBean roleBean) {
    	
    	result=checkrole(roleBean);
    	if(result<0) return result;
    	
    	sql = "insert into Monroleinfo (role_no,role_name,role_desc) values(?,?,?)";

        ArrayList params = new ArrayList();
        params.add(roleBean.getRoleno());
        params.add(roleBean.getRolename());
        params.add(roleBean.getRoledesc());
        
        

        try {
            result = DbExec.execUpdate(sql, params);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + roleBean, e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + roleBean, e);
        }
        return result;
    }

    /**
     * 
     * @TODO 根据角色编号获得相关角色信息 (修改角色前使用)
     *
     * @param roleno
     * @return 角色对象
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  11:25:10
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public RoleBean getRoleByRoleno(String roleno) {
        sql = "select * from Monroleinfo where role_no=?";
        List condList = new ArrayList();
        condList.add(roleno);
        RoleBean roleBean = null;
        try {
            Vector roleVector = DbExec.execQuery(sql, condList);
            if (roleVector != null && !roleVector.isEmpty()) {
                roleBean = new RoleBean((HashMap) roleVector.get(0));
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + roleno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + roleno + "|", e);
        }
        return roleBean;
    }

    /**
     * 
     * @TODO 修改角色信息
     *
     * @param roleBean
     * @return 修改结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  11:05:49
     * LastModified
     * History:
     * </pre>
     */
    public int updateRole(RoleBean roleBean) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        String sql1 = "update Monroleinfo set role_name=?,role_desc=? where role_no=?";
        String[] param1 = { roleBean.getRolename(), roleBean.getRoledesc(), roleBean.getRoleno() };

        sqlList.add(sql1);
        paramList.add(param1);

        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + roleBean.toString(), e);
        } catch (Exception e) {
            result = -1;
            logger.error("Exception:" + roleBean.toString(), e);
        }
        return result;
    }

    /**
     * 
     * @TODO 获得角色信息列表
     *
     * @return 角色列表
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  11:19:22
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public List getRoleList(String role_no, String dept_no) {
    	if(role_no!=null && role_no.equals("00")){
    		sql = "select * from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else if (dept_no != null && !dept_no.equals("000000000") && !dept_no.equals("")) {//如果用户组查询条件不为空且不是超级管理员用户组，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}else{
    		sql = "select * from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else {//如果角色不是超级管理员，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}
    	
        List list = new ArrayList();
        try {
            Vector roleVector = DbExec.execQuery(sql);            
            if (roleVector != null && !roleVector.isEmpty()) {
                for (int i = 0; i < roleVector.size(); i++) {
                    RoleBean roleBean = new RoleBean((HashMap) roleVector.get(i));
                    list.add(roleBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }
    public List getRoleListjs(String role_no, String dept_no) {
    	if(role_no!=null && role_no.equals("00")){
    		sql = "select * from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else if (dept_no != null && !dept_no.equals("000000000") && !dept_no.equals("")) {//如果用户组查询条件不为空且不是超级管理员用户组，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}else{
    		sql = "select * from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else {//如果角色不是超级管理员，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}
    	
        List list = new ArrayList();
        try {
            Vector roleVector = DbExec.execQuery(sql);            
            if (roleVector != null && !roleVector.isEmpty()) {
                for (int i = 0; i < roleVector.size(); i++) {
                    RoleBean roleBean = new RoleBean((HashMap) roleVector.get(i));
                    list.add(roleBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }
    public List getRoleList(String role_no, String dept_no,PageBean pageBean) {
    	if(role_no!=null && role_no.equals("00")){
    		sql = "select * from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else if (dept_no != null && !dept_no.equals("000000000") && !dept_no.equals("")) {//如果用户组查询条件不为空且不是超级管理员用户组，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}else{
    		sql = "select * from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else {//如果角色不是超级管理员，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}
        
        List list = new ArrayList();
        try {
            Vector roleVector = DbExec.execQuery(sql,pageBean.getStart(), pageBean.getPageSize());
            if (roleVector != null && !roleVector.isEmpty()) {
                for (int i = 0; i < roleVector.size(); i++) {
                    RoleBean roleBean = new RoleBean((HashMap) roleVector.get(i));
                    list.add(roleBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }
    public int getCount(String role_no, String dept_no) {
    	int count=0;
    	if(role_no!=null && role_no.equals("00")){
    		sql = "select count(*) as numbers  from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else if (dept_no != null && !dept_no.equals("000000000") && !dept_no.equals("")) {//如果用户组查询条件不为空且不是超级管理员用户组，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}else{
    		sql = "select count(*) as numbers from Monroleinfo";
    		if (dept_no != null && dept_no.equals("000000000")) {//如果用户组查询条件为超级管理员用户组，则只查询超级管理员角色
    			sql += " where role_no = '00'";
    		} else {//如果角色不是超级管理员，则只查询非超级管理员角色
    			sql += " where role_no != '00'";
    		}
    		sql += " order by role_no";
    	}       
        
        try {
            Vector roleVector = DbExec.execQuery(sql);
            if (roleVector != null && !roleVector.isEmpty()) {
            	HashMap map = (HashMap) roleVector.get(0);
				count = Integer.parseInt(((String) map.get("numbers")).trim());                
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return count;
    }
    /**
     * 
     * @TODO 删除角色信息（如果该角色被分配给某些用户组）
     *        1、删除用户功能对应信息表中相关信息
     *        2、删除用户组角色对应表中相关信息
     *        3、删除角色表中相关信息
     *        4、删除角色功能对应表中相关信息 
     * @param roleno
     * @return 执行结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  13:46:50
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public int deleteRole(String roleno) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        Vector usercodeVector = getUsercodeByRoleno(roleno);
        //如果该角色已经被分配给某些用户
        if (usercodeVector != null && !usercodeVector.isEmpty()) {
        	result = -2;
        } else {
	        String sql1 = "delete from Monroleinfo where role_no=?";
	        String[] param3 = { roleno };
	        String sql2 = "delete from Monrolemenu where role_no=?";
	        String[] param4 = { roleno };
	        sqlList.add(sql1);
	        paramList.add(param3);
	        sqlList.add(sql2);
	        paramList.add(param4);
	
	        try {
	            result = DbExec.execBatch(sqlList, paramList);
	        } catch (SQLException e) {
	            result = -1;
	            logger.error("SQLException:" + roleno + "|", e);
	        }catch (Exception e) {
	            result = -1;
	            logger.error("Exception:" + roleno + "|", e);
	        }
        }
        return result;
    }

    /**
     * 
     * @TODO 判断该角色是否被分配个某个用户组
     *
     * @param roleno
     * @return 用户组编号集合
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  15:11:52
     * LastModified
     * History:
     * </pre>
     */
    public Vector getTeamnoByRoleno(String roleno) {
        sql = "select teamno from user_grouproleauth where roleno=? order by 1";
        List condList = new ArrayList();
        condList.add(roleno);
        Vector teamnoVector = null;
        try {
            teamnoVector = DbExec.execQuery(sql, condList);
        } catch (SQLException e) {
            logger.error("SQLException:" + roleno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + roleno + "|", e);
        }
        return teamnoVector;
    }

    /**
     * 
     * @TODO 判断该用户组中是否已经被分配了用户 
     *  
     * @param teamno
     * @return 用户编号集合
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-26  9:10:30
     * LastModified
     * History:
     * </pre>
     */
    public Vector getUsercodeByTeamno(String teamno) {
        sql = "select usercode from user_group where teamno=? order by 1";
        List condList = new ArrayList();
        condList.add(teamno);
        Vector usercodeVector = null;
        try {
            usercodeVector = DbExec.execQuery(sql, condList);
        } catch (SQLException e) {
            logger.error("SQLException:" + teamno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + teamno + "|", e);
        }
        return usercodeVector;
    }

    /**
     * 
     * @TODO 判断该角色是否已经被分配了用户 
     *  
     * @param roleno
     * @return 用户编号集合
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-26  9:10:30
     * LastModified:2009-8-24	zl
     * LastModified:2009-10-29	宁波 (修改原因：sql语句错误，where 后面的查询字段不是 user_roleno 应该是 role_no)
     * History:
     * </pre>
     */
    public Vector getUsercodeByRoleno(String roleno) {
        sql = "select user_code from Monuserinfo where role_no =? order by 1";
        List condList = new ArrayList();
        condList.add(roleno);
        Vector usercodeVector = null;
        try {
            usercodeVector = DbExec.execQuery(sql, condList);
        } catch (SQLException e) {
            logger.error("SQLException:" + roleno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + roleno + "|", e);
        }
        return usercodeVector;
    }

    /**
     * 
     * @TODO 给角色分配权限
     *  1、判断该角色是否属于某个用户组（如果属于某个用户组则执行2、5功能,否则只执行3、4功能）
     *  2、删除用户功能对应表中该角色的功能权限
     *  3、将角色功能对应表中该角色的所有权限删除
     *  4、向角色功能对应表中插入新的权限集合数据
     *  5、向用户功能权限对应表中插入该角色拥有的新的权限功能信息
     *
     * @param roleno
     * @param itemList
     * @return 执行结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  15:35:16
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public int grantMenu(String roleno, List menuList,List levelList) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        
        //将角色菜单对应表中该角色的所有菜单删除
        String sql1 = "delete from Monrolemenu where role_no=?";
        String[] param2 = { roleno };
        sqlList.add(sql1);
        paramList.add(param2);
        //向角色菜单对应表中插入新的权限集合数据
        String sql2 = "";
        Iterator it = menuList.iterator();
        while (it.hasNext()) {
            MenuBean menuBean = (MenuBean) it.next();
            sql2 = "insert into Monrolemenu (role_no,menu_no,menu_level) values(?,?,?)";
            String[] param3 = { roleno, menuBean.getMenuno(),menuBean.getMenu_level() };
            sqlList.add(sql2);
            paramList.add(param3);
        }
        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            StringBuffer sb = new StringBuffer();
            sb.append(roleno);
            sb.append("|");
            while (it.hasNext()) {
                MenuBean menuBean = (MenuBean) it.next();
                sb.append(menuBean.getMenuno());
                sb.append("|");
                sb.append(menuBean.getMenuname());
                sb.append("|&");
            }
            logger.error("SQLException:" + new String(sb), e);
        }catch (Exception e) {
            result = -1;
            StringBuffer sb = new StringBuffer();
            sb.append(roleno);
            sb.append("|");
            while (it.hasNext()) {
                MenuBean menuBean = (MenuBean) it.next();
                sb.append(menuBean.getMenuno());
                sb.append("|");
                sb.append(menuBean.getMenuname());
                sb.append("|&");
            }
            logger.error("Exception:" + new String(sb), e);
        }
        return result;
    }

    /**
     * 
     * @TODO 查询某个角色拥有的所有权限功能 
     *
     * @param roleno
     * @return 权限功能集合
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  16:10:19
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public List getRoleMenuList(String roleno) {
        sql = "select m.menu_no,m.menu_level,i.menu_name,i.menu_desc  from Monrolemenu m left join Monmenuinfo i on (m.menu_no = i.menu_no) where m.role_no = ? order by m.menu_no";
        List condList = new ArrayList();
        condList.add(roleno);
        List list = new ArrayList();
        try {
            Vector menuVector = DbExec.execQuery(sql, condList);
            if (menuVector != null && !menuVector.isEmpty()) {
                for (int i = 0; i < menuVector.size(); i++) {
                    MenuBean menuBean = new MenuBean((HashMap) menuVector.get(i));
                    list.add(menuBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + roleno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + roleno + "|", e);
        }
        return list;
    }

    /**
     * 
     * @TODO 获得权限列表（某个角色拥有的权限与现在还没有分配给任何角色的权限） 
     *       
     * @param roleno
     * @return 可用来分配的权限功能集合
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-24  17:39:43
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public List getAllotRoleMenuList(String roleno) {
        sql = "select * from Monmenuinfo where menu_no not in (select menu_no from Monrolemenu where role_no = ?) order by menu_no";
        List condList = new ArrayList();
        condList.add(roleno);
        List list = new ArrayList();
        try {
            Vector menuVector = DbExec.execQuery(sql, condList);
            if (menuVector != null && !menuVector.isEmpty()) {
                for (int i = 0; i < menuVector.size(); i++) {
                    MenuBean menuBean = new MenuBean((HashMap) menuVector.get(i));
                    list.add(menuBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + roleno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + roleno + "|", e);
        }	
        return list;
    }
    
	/**
	 * 校验userCode是否重复
	 * @param mrchNo
	 * @return
	 */
	public String checkUserCode( String userCode) {
		String str="0";
		if(userCode!=null&&userCode.trim().length()>0){
			sql = "SELECT t.user_code FROM monuserinfo t WHERE t.user_code='"+userCode.trim()+"'";			
			try {
				Vector vector = DbExec.execQuery(sql);
				if (vector != null && !vector.isEmpty()) {
					str = "1";
				}
			} catch (SQLException e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			} catch (Exception e) {
				e.printStackTrace();
	            logger.error("SQLException", e);
			}
		}
		return str;
	}
}
