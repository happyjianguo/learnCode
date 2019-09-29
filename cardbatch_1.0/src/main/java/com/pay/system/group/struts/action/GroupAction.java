//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.group.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pay.system.group.bean.GroupBean;
import com.pay.system.group.dao.GroupDao;
import com.pay.system.group.struts.form.GroupForm;
import com.pay.system.role.bean.RoleBean;
import com.pay.system.role.dao.RoleDao;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class GroupAction extends DispatchAction {
    private static final Logger logger = Logger.getLogger(GroupDao.class);

    /**
     * 
     * @TODO  ��ʾ����Ա���б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  11:08:01
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward getGroupList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        GroupDao groupDao = new GroupDao();
        List groupList = groupDao.getGroupList();
        if (groupList != null && !groupList.isEmpty()) {
            request.setAttribute("groupList", groupList);
        }
        return mapping.findForward("showGroupList");
    }

    /**
     * 
     * @TODO ��ӹ���Ա����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  15:47:53
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward addGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        GroupForm groupForm = (GroupForm) form;
        GroupBean groupBean = new GroupBean();
        try {
            BeanUtils.copyProperties(groupBean, groupForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        GroupDao groupDao = new GroupDao();
        String info = "";
        String flushdo = "/userGroup.do?method=getGroupList";
        int result = groupDao.addGroup(groupBean);
        if (result == -239) {
            info = "�ü�¼�Ѿ����ڣ����ʧ�ܣ�";
            request.setAttribute("result", "1");
        } else if (result >= 0) {
            info = "����Ա����ӳɹ���";
            request.setAttribute("result", "0");
        } else {
            info = "���ݿ�����쳣������Ա�����ʧ�ܣ�";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ׼���޸Ĺ���Ա����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-22  12:09:01
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward preModGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        GroupForm groupForm = (GroupForm) form;
        String teamno = ParamUtils.getParameter(request, "teamno");
        GroupDao groupDao = new GroupDao();
        GroupBean groupBean = groupDao.getGroupByTeamno(teamno);
        try {
            if (groupBean != null) {
                BeanUtils.copyProperties(groupForm, groupBean);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("modGroup.jsp");
    }

    /**
     * 
     * @TODO �޸Ĺ���Ա����Ϣ 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-22  12:19:09
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward modGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        GroupForm groupForm = (GroupForm) form;
        GroupBean groupBean = new GroupBean();
        try {
            BeanUtils.copyProperties(groupBean, groupForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        GroupDao groupDao = new GroupDao();
        String info = "";
        String flushdo = "/userGroup.do?method=getGroupList";
        int result = groupDao.updateGroup(groupBean);
        if (result >= 0) {
            info = "����Ա���޸ĳɹ���";
            request.setAttribute("result", "0");
        } else {
            info = "����Ա���޸�ʧ�ܣ�";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ���ݹ���Ա����ɾ������Ա����Ϣ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  17:17:32
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward deleteGroup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String teamno = ParamUtils.getParameter(request, "teamno");
        GroupDao groupDao = new GroupDao();
        String info = "";
        String flushdo = "/userGroup.do?method=getGroupList";
        int result = groupDao.deleteGroup(teamno);
        if (result >= 0) {
            info = "����Ա��ɾ���ɹ���";
            request.setAttribute("result", "0");
        } else {
            info = "����Ա��ɾ��ʧ�ܣ�";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ׼���������Ա��׼������Ա�б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-24  11:40:18
     * LastModified
     * History:
     * </pre>
     */
    /*
    public ActionForward preGrantUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String teamno = ParamUtils.getParameter(request, "teamno");
        request.setAttribute("teamno", teamno);

        GroupDao groupDao = new GroupDao();
        List groupUserList = groupDao.getGroupUserList(teamno);
        if (groupUserList != null && !groupUserList.isEmpty()) {
            String groupUsercodes = "";
            Iterator it = groupUserList.iterator();
            for (int i = 0; it.hasNext(); i++) {
                UserBean userBean = (UserBean) it.next();
                groupUsercodes = groupUsercodes + userBean.getUsercode() + "|";
            }
            groupUsercodes = groupUsercodes.substring(0, groupUsercodes.length() - 1);
            request.setAttribute("groupUsercodes", groupUsercodes);
        }

        UserDao userDao = new UserDao();
        List allUserList = userDao.showUserList();
        if (allUserList != null && !allUserList.isEmpty()) {
            request.setAttribute("allUserList", allUserList);
        }
        return mapping.findForward("grantUser.jsp");
    }
    */

    /**
     * 
     * @TODO �������Ա
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-24  16:23:38
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward grantUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        List usercodeList = new ArrayList();
        GroupDao groupDao = new GroupDao();

        String teamno = ParamUtils.getParameter(request, "teamno");
        GroupBean groupBean = groupDao.getGroupByTeamno(teamno);

        String teamname = StringUtils.outerToInner(groupBean.getTeamname());

        String[] usercodes = request.getParameterValues("usercodes");
        if (usercodes != null) {
            for (int i = 0; i < usercodes.length; i++) {
                usercodeList.add(usercodes[i]);
            }
        }

        String info = "";
        String flushdo = "/userGroup.do?method=getGroupList";
        int result = groupDao.grantUser(teamno, teamname, usercodeList);
        if (result >= 0) {
            info = "����Ա�����Ա����ɹ���";
            request.setAttribute("result", "0");
        } else {
            info = "����Ա�����Ա����ʧ�ܣ�";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ׼�������ɫ��׼����ɫ�б�
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-24  11:40:18
     * LastModified
     * History:
     * </pre>
     */
    /*
    public ActionForward preGrantRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String teamno = ParamUtils.getParameter(request, "teamno");
        request.setAttribute("teamno", teamno);

        GroupDao groupDao = new GroupDao();
        List groupRoleList = groupDao.getGroupRoleList(teamno);
        if (groupRoleList != null && !groupRoleList.isEmpty()) {
            String groupRolenos = "";
            Iterator it = groupRoleList.iterator();
            for (int i = 0; it.hasNext(); i++) {
                RoleBean roleBean = (RoleBean) it.next();
                groupRolenos = groupRolenos + roleBean.getRoleno() + "|";
            }
            groupRolenos = groupRolenos.substring(0, groupRolenos.length() - 1);
            request.setAttribute("groupRolenos", groupRolenos);
        }

        RoleDao roleDao = new RoleDao();
        List allRoleList = roleDao.getRoleList();
        if (allRoleList != null && !allRoleList.isEmpty()) {
            request.setAttribute("allRoleList", allRoleList);
        }
        return mapping.findForward("grantRole.jsp");
    }
    */

    /**
     * 
     * @TODO �����ɫ
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ��ת·��
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2007-12-24  16:23:38
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward grantRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        List rolenoList = new ArrayList();

        String teamno = ParamUtils.getParameter(request, "teamno");
        RoleDao roleDao = new RoleDao();
        RoleBean roleBean = null;

        String[] rolenos = request.getParameterValues("rolenos");
        if (rolenos != null) {
            for (int i = 0; i < rolenos.length; i++) {
                roleBean = roleDao.getRoleByRoleno(rolenos[i]);
                rolenoList.add(roleBean);
            }
        }
        GroupDao groupDao = new GroupDao();

        String info = "";
        String flushdo = "/userGroup.do?method=getGroupList";
        int result = groupDao.grantRole(teamno, rolenoList);
        if (result >= 0) {
            info = "����Ա���ɫ����ɹ���";
            request.setAttribute("result", "0");
        } else {
            info = "����Ա���ɫ����ʧ�ܣ�";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO  �鿴����Ա��Ȩ����Ϣ 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author �Ʊ�
     * @version 1.0
     * <pre>
     * Created on:2008-3-22  10:41:24
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward queryItems(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String teamno = ParamUtils.getParameter(request, "teamno");
        GroupDao groupDao = new GroupDao();
        List groupItemList = groupDao.getGroupItemList(teamno);
        if (groupItemList != null && !groupItemList.isEmpty()) {
            request.setAttribute("groupItemList", groupItemList);
        }
        return mapping.findForward("queryGroupItems.jsp");
    }
}
