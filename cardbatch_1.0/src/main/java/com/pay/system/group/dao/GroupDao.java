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
 * @author �Ʊ�
 * @Date 2007-12-19
 * @Version 1.0
 */
public class GroupDao {

    private static final Logger logger = Logger.getLogger(GroupDao.class);
    private String sql = "";
    private int result = 0;

    /**
     * ����˵���� ����û�����Ϣ
     * @param   GroupBean Ҫ��ӵ��û������
     * @return  int result �жϷ���ִ�н��
     * @author  �Ʊ�
     * @version 1.0
     * <pre>
     * Created on :2007-12-19 ����10:44:47
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
     * @TODO ɾ���û�����Ϣ
     * 1��ɾ���û�����Ϣ���и���������Ϣ
     * 2���Ѹ��û����µ��û����������������Ϊ���ַ���
     * 3��ɾ���û��û����Ӧ���и��û������Ϣ
     * 4��ɾ���û����ɫ��Ӧ���и��û������Ϣ
     * 5��ɾ���û����ܶ�Ӧ���и��û����е������û��������Ϣ
     * @param  groupBean Ҫɾ�����û������
     * @return int result �жϷ���ִ�н��
     * @author �Ʊ�
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
                + StringUtils.outerToInner("����") + "' where teamno=?";
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
        //����ù��������Ѿ��������û�,�����û�Ȩ�޶�Ӧ����ɾ����Щ�û�������Ȩ����Ϣ
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
     * @TODO �����û����Ż���û��������Ϣ���޸��û���ǰʹ�ã� 
     *
     * @param teamno
     * @return
     * @author �Ʊ�
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
     * @TODO   �޸��û�����Ϣ
     * 1���޸��û�����Ϣ��
     * 2���޸��û��û����Ӧ��Ϣ��
     * 3���޸��û���Ϣ��
     * @param  groupBean Ҫ�޸ĵ��û������
     * @return int result �жϷ���ִ�н��
     * @author �Ʊ�
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
     * @TODO ����û����б���Ϣ
     *
     * @return �û����б�
     * @author �Ʊ�
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
     * @TODO ��ѯĳ���û���ӵ�е�����Ȩ�ޣ��鿴�û�Ȩ��ʱʹ�ã�
     *
     * @param  teamno �û�����
     * @return Ȩ����Ϣ�б�
     * @author �Ʊ�
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
     * @TODO �����û����Ż�ø��û����Ӧ���û���Ϣ�б�(���û�������û�ʱʹ��)
     *
     * @param teamno
     * @return �û���Ϣ�б�
     * @author �Ʊ�
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
     * @TODO  �����û����Ż�ø��û����Ӧ�Ľ�ɫ��Ϣ�б�(���û�������ɫʱʹ��)
     *
     * @param teamno
     * @return ��ɫ��Ϣ�б�
     * @author �Ʊ�
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
     * @TODO ���ݸ������·���Ľ�ɫ���ϻ�ø��������Ȩ�޼���
     *        ����������ɫʱʹ�ã�      
     * @param roleList
     * @return Ȩ�޼���
     * @author �Ʊ�
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
     * @TODO  ���û�������û�
     *  1�������û��������û�Ȩ�޶��ձ���ɾ�����û����������û���Ȩ����Ϣ
     *  2�������û�����ɾ���û��û����Ӧ���и���������û�
     *  3��ɾ���·�����û��б��е��û��������û����е���Ϣ,ͬʱɾ���·�����û��б��е��û�������Ȩ����Ϣ
     *  4�����û���Ϣ���аѸ���������û����������������Ϊ���ַ���
     *  5���Ѹ����·�����û���Ϣ���ϲ��뵽�û��û����Ӧ����
     *  6�������·�����û���Ϣ���û���ţ����û���Ϣ���а���Щ�û�����š�������Ϊ��ǰ�û���
     *  7���Ѹ��û���ӵ�е�Ȩ�޸�����û����е������û�
     *
     * @param teamno
     * @param teamname
     * @param usercodeList
     * @return ִ�н��
     * @author �Ʊ�
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
        /**��һ��*/
        RoleDao roleDao = new RoleDao();
        Vector usercodeVector = roleDao.getUsercodeByTeamno(teamno);
        //����ù��������Ѿ��������û�,��ɾ����Щ�û���ӵ�е�����Ȩ��
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
        /**�ڶ���*/
        String sql1 = "delete from user_group where teamno=?";
        String[] param2 = { teamno };
        sqlList.add(sql1);
        paramList.add(param2);

        /**������*/
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
        /**���Ĳ�*/
        String sql2 = "update user_userinfo set teamno='000',teamname=? where teamno=?";
        String[] param5 = { StringUtils.outerToInner("����"), teamno };
        sqlList.add(sql2);
        paramList.add(param5);
        /**���岽*/
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

        /**������*/
        String sql4 = "";
        Iterator it3 = usercodeList.iterator();
        while (it3.hasNext()) {
            usercode = (String) it3.next();
            sql4 = "update user_userinfo set teamno=?,teamname=? where usercode=?";
            String[] param7 = { teamno, teamname, usercode };
            sqlList.add(sql4);
            paramList.add(param7);
        }

        /**���߲�*/
        Iterator it4 = usercodeList.iterator();
        //����ù��������з����û�
        while (it4.hasNext()) {
            usercode = (String) it4.next();
            //��ø��û���ӵ�е�����Ȩ�޼���
            List itemList = getGroupItemList(teamno);
            //�����û����е�ÿ���û�������û����Ȩ��
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
     * @TODO  ���û�������ɫ
     *    roleList ��Ҫ��roleno��rolename
     *    
     * 1��ɾ�����û����������û���Ȩ����Ϣ 
     * 2�������û�����ɾ���û����ɫ��Ӧ���и��û�������н�ɫ
     * 3�����û����ɫ��Ӧ���в��������Ľ�ɫ����
     * 4�������û���������û�������û��������Ȩ��
     *
     * @param teamno
     * @param roleList
     * @return ִ�н��
     * @author �Ʊ�
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
        /**��һ��*/
        RoleDao roleDao = new RoleDao();
        Vector usercodeVector = roleDao.getUsercodeByTeamno(teamno);
        //����ù��������Ѿ��������û�,��ɾ����Щ�û���ӵ������Ȩ��
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
        /**�ڶ���*/
        sql = "delete from user_grouproleauth where teamno=?";
        String[] param2 = { teamno };
        sqlList.add(sql);
        paramList.add(param2);

        /**������*/
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
        /**���Ĳ�*/
        //����ù��������з����û�
        if (usercodeVector != null && !usercodeVector.isEmpty()) {
            //���ݸ����û��������µĽ�ɫ���ϣ���ø��û��������Ȩ�޼���
            List itemList = getItemListByRoleno(roleList);
            //�����û����е�ÿ���û�������û����Ȩ��
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
