package com.pay.system.group.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.pay.system.group.bean.GroupBean;
import com.pay.system.menu.bean.MenuBean;
import com.pay.system.role.bean.RoleBean;
import com.pay.system.role.dao.RoleDao;
import com.pay.system.user.bean.UserBean;
import com.pay.util.DbExec;
import com.pay.util.StringUtils;

/**
 * @author 黄斌
 * @Date 2007-12-19
 * @Version 1.0
 */
public class GroupDao {

    private static final Logger logger = Logger.getLogger(GroupDao.class);
    private String sql = "";
    private int result = 0;

    /**
     * 方法说明： 添加用户组信息
     * @param   GroupBean 要添加的用户组对象
     * @return  int result 判断方法执行结果
     * @author  黄斌
     * @version 1.0
     * <pre>
     * Created on :2007-12-19 上午10:44:47
     * LastModified:
     * History:
     * </pre>
     */
    public int addGroup(GroupBean groupBean) {
        sql = "insert into user_groupinfo (teamno,teamname,teamdescribe) values(?,?,?)";

        ArrayList params = new ArrayList();

        params.add(groupBean.getTeamno());
        params.add(groupBean.getTeamname());
        params.add(groupBean.getTeamdescribe());

        try {
            result = DbExec.execUpdate(sql, params);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + groupBean.toString(), e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + groupBean.toString(), e);
        }
        return result;
    }

    /**
     * 
     * @TODO 删除用户组信息
     * 1、删除用户组信息表中该组的相关信息
     * 2、把该用户组下的用户所属组号与组名改为空字符串
     * 3、删除用户用户组对应表中该用户组的信息
     * 4、删除用户组角色对应表中该用户组的信息
     * 5、删除用户功能对应表中该用户组中的所有用户的相关信息
     * @param  groupBean 要删除的用户组对象
     * @return int result 判断方法执行结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  10:39:42
     * LastModified
     * History:
     * </pre>
     */
    public int deleteGroup(String teamno) {
        if (teamno == null)
            return -1;
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        String sql1 = "delete from user_groupinfo where teamno=?";
        String[] param1 = { teamno };
        String sql2 = "update user_userinfo set teamno='000',teamname='"
                + StringUtils.outerToInner("暂无") + "' where teamno=?";
        String[] param2 = { teamno };
        String sql3 = "delete from user_group where teamno=?";
        String[] param3 = { teamno };
        String sql4 = "delete from user_grouproleauth where teamno=?";
        String[] param4 = { teamno };
        sqlList.add(sql1);
        paramList.add(param1);
        sqlList.add(sql2);
        paramList.add(param2);
        sqlList.add(sql3);
        paramList.add(param3);
        sqlList.add(sql4);
        paramList.add(param4);

        RoleDao roleDao = new RoleDao();
        Vector usercodeVector = roleDao.getUsercodeByTeamno(teamno);
        //如果该工作组中已经分配了用户,则在用户权限对应表中删除这些用户的所有权限信息
        if (usercodeVector != null && !usercodeVector.isEmpty()) {
            String usercode = "";
            for (int j = 0; j < usercodeVector.size(); j++) {
                HashMap record = (HashMap) usercodeVector.get(j);
                usercode = StringUtils.trimDbData((String) record.get("usercode"));
                sql = "delete from user_itemauth where usercode = ?";
                String[] param5 = { usercode };
                sqlList.add(sql);
                paramList.add(param5);
            }
        }

        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + teamno + "|", e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + teamno + "|", e);
        }
        return result;
    }

    /**
     * 
     * @TODO 根据用户组编号获得用户组相关信息（修改用户组前使用） 
     *
     * @param teamno
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-25  10:35:40
     * LastModified
     * History:
     * </pre>
     */
    public GroupBean getGroupByTeamno(String teamno) {
        GroupBean groupBean = null;
        sql = "select * from user_groupinfo where teamno=?";
        List condList = new ArrayList();
        condList.add(teamno);
        try {
            Vector groupVector = DbExec.execQuery(sql, condList);
            if (groupVector != null && !groupVector.isEmpty()) {
                groupBean = new GroupBean((HashMap) groupVector.get(0));
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + teamno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + teamno + "|", e);
        }
        return groupBean;
    }

    /**
     * 
     * @TODO   修改用户组信息
     * 1、修改用户组信息表
     * 2、修改用户用户组对应信息表
     * 3、修改用户信息表
     * @param  groupBean 要修改的用户组对象
     * @return int result 判断方法执行结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  10:41:54
     * LastModified
     * History:
     * </pre>
     */
    public int updateGroup(GroupBean groupBean) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();

        String sql1 = "update user_groupinfo set teamname=?,teamdescribe=? where teamno=?";
        String[] param1 = { groupBean.getTeamname(), groupBean.getTeamdescribe(),
                groupBean.getTeamno() };
        String sql2 = "update user_group set teamname=? where teamno=?";
        String[] param2 = { groupBean.getTeamname(), groupBean.getTeamno() };
        String sql3 = "update user_userinfo set teamname=? where teamno=?";
        String[] param3 = { groupBean.getTeamname(), groupBean.getTeamno() };

        sqlList.add(sql1);
        paramList.add(param1);
        sqlList.add(sql2);
        paramList.add(param2);
        sqlList.add(sql3);
        paramList.add(param3);

        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            logger.error("SQLException:" + groupBean.toString(), e);
        }catch (Exception e) {
            result = -1;
            logger.error("Exception:" + groupBean.toString(), e);
        }
        return result;
    }

    /**
     * 
     * @TODO 获得用户组列表信息
     *
     * @return 用户组列表
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  17:05:37
     * LastModified
     * History:
     * </pre>
     */
    public List getGroupList() {
        sql = "select * from user_groupinfo order by teamno";
        List list = new ArrayList();
        try {
            Vector groupVector = DbExec.execQuery(sql);
            if (groupVector != null && !groupVector.isEmpty()) {
                for (int i = 0; i < groupVector.size(); i++) {
                    GroupBean groupBean = new GroupBean((HashMap) groupVector.get(i));
                    list.add(groupBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException", e);
        }catch (Exception e) {
            logger.error("Exception", e);
        }
        return list;
    }

    /**
     * 
     * @TODO 查询某个用户组拥有的所有权限（查看用户权限时使用）
     *
     * @param  teamno 用户组编号
     * @return 权限信息列表
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-19  10:54:13
     * LastModified
     * History:
     * </pre>
     */
    public List getGroupItemList(String teamno) {
        sql = "select * from user_iteminfo where itemno in (select itemno from user_authority"
                + " where roleno in (select roleno from user_grouproleauth where teamno=?)) order by itemno";
        List condList = new ArrayList();
        condList.add(teamno);
        List list = new ArrayList();
        try {
            Vector itemVector = DbExec.execQuery(sql, condList);
            if (itemVector != null && !itemVector.isEmpty()) {
                for (int i = 0; i < itemVector.size(); i++) {
                    MenuBean itemBean = new MenuBean((HashMap) itemVector.get(i));
                    list.add(itemBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + teamno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + teamno + "|", e);
        }
        return list;
    }

    /**
     * 
     * @TODO 根据用户组编号获得该用户组对应的用户信息列表(给用户组分配用户时使用)
     *
     * @param teamno
     * @return 用户信息列表
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  18:12:53
     * LastModified
     * History:
     * </pre>
     */
    public List getGroupUserList(String teamno) {
        sql = "select * from user_userinfo where teamno=? and usercode in (select usercode from user_group where teamno=?) order by usercode";

        List list = new ArrayList();
        List condList = new ArrayList();
        condList.add(teamno);
        condList.add(teamno);
        try {
            Vector userVector = DbExec.execQuery(sql, condList);
            if (userVector != null && !userVector.isEmpty()) {
                for (int i = 0; i < userVector.size(); i++) {
                    UserBean userBean = new UserBean((HashMap) userVector.get(i));
                    list.add(userBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + teamno + "|", e);
        }catch (Exception e) {
            logger.error("Exception:" + teamno + "|", e);
        }
        return list;
    }

    /**
     * 
     * @TODO  根据用户组编号获得该用户组对应的角色信息列表(给用户组分配角色时使用)
     *
     * @param teamno
     * @return 角色信息列表
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  18:19:58
     * LastModified
     * History:
     * </pre>
     */
    public List getGroupRoleList(String teamno) {
        sql = "select * from user_roleinfo where roleno in (select roleno from user_grouproleauth where teamno=?) order by roleno";
        List condList = new ArrayList();
        condList.add(teamno);
        List list = new ArrayList();
        try {
            Vector roleVector = DbExec.execQuery(sql, condList);
            if (roleVector != null && !roleVector.isEmpty()) {
                for (int i = 0; i < roleVector.size(); i++) {
                    RoleBean roleBean = new RoleBean((HashMap) roleVector.get(i));
                    list.add(roleBean);
                }
            }
        } catch (SQLException e) {
            logger.error("SQLException:" + teamno + "|", e);
        } catch (Exception e) {
            logger.error("Exception:" + teamno + "|", e);
        }
        return list;
    }

    /**
     * 
     * @TODO 根据给该组新分配的角色集合获得该组的所有权限集合
     *        （给组分配角色时使用）      
     * @param roleList
     * @return 权限集合
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  22:01:15
     * LastModified
     * History:
     * </pre>
     */
    public List getItemListByRoleno(List roleList) {
        List list = new ArrayList();
        Iterator it = roleList.iterator();
        String roleno = "";
        sql = "select * from user_iteminfo where itemno in (select distinct(itemno) from user_authority where roleno in (";
        List condList = new ArrayList();
        while (it.hasNext()) {
            RoleBean roleBean = (RoleBean) it.next();
            roleno = roleBean.getRoleno();
            sql = sql + "?,";
            condList.add(roleno);
        }
        sql = sql.substring(0, sql.length() - 1) + "))";
        sql += " order by itemno";
        try {
            Vector itemVector = DbExec.execQuery(sql, condList);
            if (itemVector != null && !itemVector.isEmpty()) {
                for (int i = 0; i < itemVector.size(); i++) {
                    MenuBean itemBean = new MenuBean((HashMap) itemVector.get(i));
                    list.add(itemBean);
                }
            }
        } catch (SQLException e) {
            StringBuffer sb = new StringBuffer();
            while (it.hasNext()) {
                sb.append(roleno);
                sb.append("|");
            }

            logger.error("SQLException:" + new String(sb), e);
        }catch (Exception e) {
            StringBuffer sb = new StringBuffer();
            while (it.hasNext()) {
                sb.append(roleno);
                sb.append("|");
            }

            logger.error("Exception:" + new String(sb), e);
        }
        return list;
    }

    /**
     * 
     * @TODO  给用户组分配用户
     *  1、根据用户组编号在用户权限对照表中删除该用户组中所有用户的权限信息
     *  2、根据用户组编号删除用户用户组对应表中该组的所有用户
     *  3、删除新分配的用户列表中的用户在其他用户组中的信息,同时删除新分配的用户列表中的用户的所有权限信息
     *  4、在用户信息表中把该组的所有用户的组号与组名设置为空字符串
     *  5、把该组新分配的用户信息集合插入到用户用户组对应表中
     *  6、根据新分配的用户信息的用户编号，在用户信息表中把这些用户的组号、组名改为当前用户组
     *  7、把该用户组拥有的权限赋予该用户组中的所有用户
     *
     * @param teamno
     * @param teamname
     * @param usercodeList
     * @return 执行结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  20:27:04
     * LastModified
     * History:
     * </pre>
     */
    public int grantUser(String teamno, String teamname, List usercodeList) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        /**第一步*/
        RoleDao roleDao = new RoleDao();
        Vector usercodeVector = roleDao.getUsercodeByTeamno(teamno);
        //如果该工作组中已经分配了用户,则删除这些用户所拥有的所有权限
        if (usercodeVector != null && !usercodeVector.isEmpty()) {
            String usercodev = "";
            for (int j = 0; j < usercodeVector.size(); j++) {
                HashMap record = (HashMap) usercodeVector.get(j);
                usercodev = StringUtils.trimDbData((String) record.get("usercode"));
                sql = "delete from user_itemauth where usercode = ?";
                String[] param1 = { usercodev };
                sqlList.add(sql);
                paramList.add(param1);
            }
        }
        /**第二步*/
        String sql1 = "delete from user_group where teamno=?";
        String[] param2 = { teamno };
        sqlList.add(sql1);
        paramList.add(param2);

        /**第三步*/
        Iterator it = usercodeList.iterator();
        while (it.hasNext()) {
            String usercode = (String) it.next();
            sql = "delete from user_group where usercode=?";
            String[] param3 = { usercode };
            sqlList.add(sql);
            paramList.add(param3);
            String sqln = "delete from user_itemauth where usercode = ?";
            String[] param4 = { usercode };
            sqlList.add(sqln);
            paramList.add(param4);
        }
        /**第四步*/
        String sql2 = "update user_userinfo set teamno='000',teamname=? where teamno=?";
        String[] param5 = { StringUtils.outerToInner("暂无"), teamno };
        sqlList.add(sql2);
        paramList.add(param5);
        /**第五步*/
        String usercode = "";
        String sql3 = "";
        Iterator it2 = usercodeList.iterator();
        while (it2.hasNext()) {
            usercode = (String) it2.next();
            sql3 = "insert into user_group (usercode,teamno,teamname) values(?,?,?)";
            String[] param6 = { usercode, teamno, teamname };
            sqlList.add(sql3);
            paramList.add(param6);
        }

        /**第六步*/
        String sql4 = "";
        Iterator it3 = usercodeList.iterator();
        while (it3.hasNext()) {
            usercode = (String) it3.next();
            sql4 = "update user_userinfo set teamno=?,teamname=? where usercode=?";
            String[] param7 = { teamno, teamname, usercode };
            sqlList.add(sql4);
            paramList.add(param7);
        }

        /**第七步*/
        Iterator it4 = usercodeList.iterator();
        //如果该工作组中有分配用户
        while (it4.hasNext()) {
            usercode = (String) it4.next();
            //获得该用户组拥有的所有权限集合
            List itemList = getGroupItemList(teamno);
            //给该用户组中的每个用户赋予该用户组的权限
            Iterator it5 = itemList.iterator();
//            while (it5.hasNext()) {
//                ItemBean itemBean = (ItemBean) it5.next();
//                sql = "insert into user_itemauth (usercode,itemno,itemname,itemdesc) values(?,?,?,?)";
//                String[] param8 = { usercode, itemBean.getItemno(),
//                        StringUtils.outerToInner(itemBean.getItemname()),
//                        StringUtils.outerToInner(itemBean.getItemdescrip()) };
//                sqlList.add(sql);
//                paramList.add(param8);
//            }
        }
        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            StringBuffer sb = new StringBuffer();
            sb.append(teamno);
            sb.append("|");
            sb.append(teamname);
            sb.append("|");
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("|");
            }
            logger.error("SQLException:" + new String(sb), e);
        }catch (Exception e) {
            result = -1;
            StringBuffer sb = new StringBuffer();
            sb.append(teamno);
            sb.append("|");
            sb.append(teamname);
            sb.append("|");
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("|");
            }
            logger.error("Exception:" + new String(sb), e);
        }
        return result;
    }

    /**
     * @TODO  给用户组分配角色
     *    roleList 中要有roleno与rolename
     *    
     * 1、删除该用户组中所有用户的权限信息 
     * 2、根据用户组编号删除用户组角色对应表中该用户组的所有角色
     * 3、向用户组角色对应表中插入待分配的角色集合
     * 4、给该用户组的所有用户赋予该用户组的所有权限
     *
     * @param teamno
     * @param roleList
     * @return 执行结果
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-20  22:00:01
     * LastModified
     * History:
     * </pre>
     */
    public int grantRole(String teamno, List roleList) {
        List sqlList = new ArrayList();
        List paramList = new ArrayList();
        /**第一步*/
        RoleDao roleDao = new RoleDao();
        Vector usercodeVector = roleDao.getUsercodeByTeamno(teamno);
        //如果该工作组中已经分配了用户,则删除这些用户所拥有所有权限
        if (usercodeVector != null && !usercodeVector.isEmpty()) {
            String usercodev = "";
            for (int j = 0; j < usercodeVector.size(); j++) {
                HashMap record = (HashMap) usercodeVector.get(j);
                usercodev = StringUtils.trimDbData((String) record.get("usercode"));
                sql = "delete from user_itemauth where usercode = ?";
                String[] param1 = { usercodev };
                sqlList.add(sql);
                paramList.add(param1);
            }
        }
        /**第二步*/
        sql = "delete from user_grouproleauth where teamno=?";
        String[] param2 = { teamno };
        sqlList.add(sql);
        paramList.add(param2);

        /**第三步*/
        if (roleList.size() > 0) {
            Iterator it = roleList.iterator();
            while (it.hasNext()) {
                RoleBean roleBean = (RoleBean) it.next();
                sql = "insert into user_grouproleauth (teamno,roleno,rolename) values(?,?,?)";
                String[] param3 = { teamno, roleBean.getRoleno(),
                        StringUtils.outerToInner(roleBean.getRolename()) };
                sqlList.add(sql);
                paramList.add(param3);
            }
        }
        /**第四步*/
        //如果该工作组中有分配用户
        if (usercodeVector != null && !usercodeVector.isEmpty()) {
            //根据给该用户组分配的新的角色集合，获得该用户组的所有权限集合
            List itemList = getItemListByRoleno(roleList);
            //给该用户组中的每个用户赋予该用户组的权限
            String usercode = "";
            for (int j = 0; j < usercodeVector.size(); j++) {
                HashMap record = (HashMap) usercodeVector.get(j);
                usercode = (String) record.get("usercode");
                usercode = usercode.trim();

                Iterator it3 = itemList.iterator();
                /*
                while (it3.hasNext()) {
                    ItemBean itemBean = (ItemBean) it3.next();
                    sql = "insert into user_itemauth (usercode,itemno,itemname,itemdesc) values(?,?,?,?)";
                    String[] param4 = { usercode, itemBean.getItemno(),
                            StringUtils.outerToInner(itemBean.getItemname()),
                            StringUtils.outerToInner(itemBean.getItemdescrip()) };
                    sqlList.add(sql);
                    paramList.add(param4);
                }
                */
            }
        }

        try {
            result = DbExec.execBatch(sqlList, paramList);
        } catch (SQLException e) {
            result = -1;
            StringBuffer sb = new StringBuffer();
            Iterator it = roleList.iterator();
            sb.append(teamno);
            sb.append("|");
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("|");
            }
            logger.error("SQLException:" + new String(sb), e);
        }catch (Exception e) {
            result = -1;
            StringBuffer sb = new StringBuffer();
            Iterator it = roleList.iterator();
            sb.append(teamno);
            sb.append("|");
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append("|");
            }
            logger.error("Exception:" + new String(sb), e);
        }
        return result;
    }
}
