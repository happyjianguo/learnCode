//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.user.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import cn.yufu.system.common.shiro.UserUtils;

import com.pay.system.dept.bean.DeptBean;
import com.pay.system.dept.dao.DeptDao;
import com.pay.system.menu.bean.MenuBean;
import com.pay.system.menu.dao.MenuDao;
import com.pay.system.menu.dao.MenuPermissonDao;
import com.pay.system.role.dao.RoleDao;
import com.pay.system.user.bean.UserBean;
import com.pay.system.user.dao.UserDao;
import com.pay.system.user.struts.form.UserForm;
import com.pay.util.Constant;
import com.pay.util.DateUtils;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class UserAction extends DispatchAction {

    private static final Logger logger = Logger.getLogger(UserAction.class);

    /**
     * 
     * @TODO  显示管理员列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  11:08:01
     * LastModified:2009-8-19	zl
     * History:
     * </pre>
     */
    public ActionForward getUserList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        //      进入主页前，把查询条件设置为空
        HttpSession session = request.getSession();
        session.setAttribute("querySign", null);
        String dept_no_node = (String)session.getAttribute("dept_no_node");
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();
       DeptDao deptDao = new DeptDao();
		/** 分页条件部分 */
		// 获得查询结果总记录数
		int count = userDao.getCount(dept_no_node,roleno);
		// 构造分页对象
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
        List userList = userDao.showUserList(dept_no_node,roleno, pageBean);
        List roleList = roleDao.getRoleList(roleno, "");
        List deptList = null;
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        if (userList != null && !userList.isEmpty()) {
            request.setAttribute("userList", userList);
        }
        if (roleList != null && !roleList.isEmpty()) {
            request.setAttribute("roleList", roleList);
        }
        if (deptList != null && !deptList.isEmpty()) {
            request.setAttribute("deptList", deptList);
        }
        return mapping.findForward("showUserList");
    }

    /**
     * 
     * @TODO  获得查询条件，并且保存在session中 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-3-31  16:26:27
     * LastModified:2009-8-19	zl
     * History:
     * </pre>
     */
    public ActionForward queryAgentList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("querySign", "query");

        String usercodeQ = ParamUtils.getParameter(request, "usercodeQ");
        String rolenoQ = ParamUtils.getParameter(request, "rolenoQ");
        String deptnoQ = ParamUtils.getParameter(request, "deptnoQ");

        session.setAttribute("usercodeQ", usercodeQ);
        session.setAttribute("rolenoQ", rolenoQ);
        session.setAttribute("deptnoQ", deptnoQ);

        return mapping.findForward("getUserListQuery");
    }

    /**
     * 
     * @TODO  查询管理员信息列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-3-31  16:05:02
     * LastModified:2009-8-19	zl
     * History:
     * </pre>
     */
    public ActionForward getUserListQuery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        //获取查询条件
        HttpSession session = request.getSession();
        String usercodeQ = (String) session.getAttribute("usercodeQ");
        String rolenoQ = (String) session.getAttribute("rolenoQ");
        String deptnoQ = (String) session.getAttribute("deptnoQ");
        String dept_no_node = (String) session.getAttribute("dept_no_node");
        String roleno = (String) session.getAttribute("roleno");

        //把查询条件回写到FORM中        
        UserForm userForm = (UserForm) form;
        userForm.setUsercodeQ(usercodeQ.trim());
        userForm.setRolenoQ(rolenoQ.trim());
        userForm.setDeptnoQ(deptnoQ.trim());

        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();
        DeptDao deptDao = new DeptDao();
		/** 分页条件部分 */
		// 获得查询结果总记录数
		int count = userDao.getCount(usercodeQ.trim(), rolenoQ.trim(), deptnoQ.trim(),dept_no_node.trim(),roleno.trim());
		// 构造分页对象
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
        List userList = userDao.queryUserList(usercodeQ.trim(), rolenoQ.trim(), deptnoQ.trim(),dept_no_node.trim(),roleno.trim(),pageBean);
        List roleList = roleDao.getRoleList(roleno, "");
        List deptList = null;
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        if (userList != null && !userList.isEmpty()) {
            request.setAttribute("userList", userList);
        }
        if (roleList != null && !roleList.isEmpty()) {
            request.setAttribute("roleList", roleList);
        }
        if (deptList != null && !deptList.isEmpty()) {
            request.setAttribute("deptList", deptList);
        }
        return mapping.findForward("queryUserList");
    }

    /**
     * 
     * @TODO 添加管理员信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  15:47:53
     * LastModified:2009-8-20	zl
     * History:
     * </pre>
     */
    public ActionForward addUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String info = "";
        String flushdo = "/user.do?method=getUserList";

        //如果查询标志不为空
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        String querySign = (String) session.getAttribute("querySign");
	        if (querySign != null) {
	            flushdo = "/user.do?method=getUserListQuery";
	        }
	
	        UserForm userForm = (UserForm) form;
			
	        UserBean userBean = new UserBean();
	        try {
	            BeanUtils.copyProperties(userBean, userForm);
	        } catch (IllegalAccessException e) {
	            logger.error("IllegalAccessException", e);
	        } catch (InvocationTargetException e) {
	            logger.error("InvocationTargetException", e);
	        }
	        //设置管理员所属管理员组编号为000，组名为暂无表示没有分配管理员组
	//        userBean.setTeamno("000");
	//        userBean.setTeamname(StringUtils.outerToInner("暂无"));
	//        userBean.setRoleno(userForm.getRoleno());
	//        userBean.setIsactive(userForm.getIsactive());
	        userBean.setDeptno(userForm.getDeptno());
	        //设置登录标志为未登录
	        userBean.setLoginflag("0");
	        //设置上次登录时间
	        String lastintime = "0";
	        userBean.setLastintime(lastintime);
	        //设置密码有效期
	        String validdays = "";
	        if (userForm.getValiddays() != null) {
	            validdays = StringUtils.trimDbData(userForm.getValiddays());
	        }
	        userBean.setValiddays(validdays);
	        //设置密码失效日期
	        if (validdays.equals("-1")) {
	            userBean.setPasswddays("-1");
	        } else {
	            Calendar cal = Calendar.getInstance();
	            cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(validdays));
	            String passwddays = DateUtils.getInstance().format(cal.getTime());
	            userBean.setPasswddays(passwddays);
	        }
	
	        UserDao userDao = new UserDao();
	
	        int result = userDao.addUser(userBean);
	        /***	qhg add     20120619 添加生成权限配置文件			***/
            new MenuPermissonDao().createPerFile();           
            /***	qhg add_end 20120619 添加生成权限配置文件			***/
	        if (result == -239) {
	            info = "管理员编号重复，添加失败！";
	            request.setAttribute("result", "1");
	        } else if (result >= 0) {
	            info = "管理员添加成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "数据库操作异常，管理员添加失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO 查看管理员详细信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转页面
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-22  18:06:11
     * LastModified:2009-8-19	zl
     * History:
     * </pre>
     */
    public ActionForward queryUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        UserForm userForm = (UserForm) form;
        String usercode = ParamUtils.getParameter(request, "usercode");
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
        UserBean userBean = userDao.getUserInfo(usercode,roleno);
        
        try {
            if (userBean != null) {
                BeanUtils.copyProperties(userForm, userBean);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("queryUser.jsp");
    }

    /**
     * 
     * @TODO 准备添加管理员信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-22  12:09:01
     * LastModified:2009-8-20	zl
     * LastModified:2009-11-4	宁波（修改 超级管理员可以看到全部角色）
     * History:
     * </pre>
     */
    public ActionForward preAddUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String dept_no_node = (String)session.getAttribute("dept_no_node");
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
        RoleDao roleDao = new RoleDao();
        DeptDao deptDao = new DeptDao();      
        List  roleList = roleDao.getRoleList(roleno, "");
        List deptList = null;
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        request.setAttribute("roleno", roleno);
        request.setAttribute("roleList", roleList);
        request.setAttribute("deptList", deptList);
        return mapping.findForward("addUser.jsp");
    }
    
    /**
     * 
     * @TODO 准备修改管理员信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-22  12:09:01
     * LastModified:2009-8-20	zl
     * History:
     * </pre>
     */
    public ActionForward preModUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        String dept_no_node = (String)session.getAttribute("dept_no_node");
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
        UserForm userForm = (UserForm) form;
        String usercode = ParamUtils.getParameter(request, "usercode");
        UserDao userDao = new UserDao();
        RoleDao roleDao = new RoleDao();
        DeptDao deptDao = new DeptDao();
        UserBean userBean = userDao.getUserInfo(usercode,roleno);
        List roleList = null;
        List deptList = null;
    	roleList = roleDao.getRoleList(roleno, userBean.getDeptno());
        if(roleno!=null && roleno.equals("00")){
        	deptList = deptDao.getDeptList(roleno);
        	DeptBean deptBean = new DeptBean();
        	deptBean.setDeptno("000000000");
        	deptBean.setDeptname("超级管理员用户组");
       	deptList.add(deptBean);
        }else{
        	deptList = deptDao.getDeptListByUser(dept_no_node);
        }
        try {
            if (userBean != null) {
                BeanUtils.copyProperties(userForm, userBean);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        request.setAttribute("roleno", roleno);
        request.setAttribute("roleList", roleList);
        request.setAttribute("deptList", deptList);
        return mapping.findForward("modUser.jsp");
    }

    /**
     * 
     * @TODO 修改管理员信息 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-22  12:19:09
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public ActionForward modUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String info = "";
        String flushdo = "/user.do?method=getUserList";

        //如果查询标志不为空
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        String querySign = (String) session.getAttribute("querySign");
	        if (querySign != null) {
	            flushdo = "/user.do?method=getUserListQuery";
	        }
	
	        UserForm userForm = (UserForm) form;
	        UserBean userBean = new UserBean();
	        try {
	            BeanUtils.copyProperties(userBean, userForm);
	        } catch (IllegalAccessException e) {
	            logger.error("IllegalAccessException", e);
	        } catch (InvocationTargetException e) {
	            logger.error("InvocationTargetException", e);
	        }
	        //设置密码有效期
	        String validdays = "";
	        if (userForm.getValiddays() != null)
	            validdays = StringUtils.trimDbData(userForm.getValiddays());
	        userBean.setValiddays(validdays);
	        //设置密码失效日期
	        if (validdays.equals("-1")) {
	            userBean.setPasswddays("-1");
	        } else {
	            Calendar cal = Calendar.getInstance();
	            cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(validdays));
	            String passwddays = DateUtils.getInstance().format(cal.getTime());
	            userBean.setPasswddays(passwddays);
	        }
	
	        UserDao userDao = new UserDao();
	        int result = userDao.modifyUser(userBean);
	        if (result > 0) {
	            info = "管理员信息修改成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "管理员信息修改失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO 根据管理员编号删除管理员信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  17:17:32
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String info = "";
        String flushdo = "/user.do?method=getUserList";

        //如果查询标志不为空
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        String querySign = (String) session.getAttribute("querySign");
	        if (querySign != null) {
	            flushdo = "/user.do?method=getUserListQuery";
	        }
	
	        String usercode = ParamUtils.getParameter(request, "usercode");
	        UserDao userDao = new UserDao();
	
	        int result = userDao.deleteUser(usercode);
	        if (result >= 0) {
	            info = "管理员信息删除成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "管理员信息删除失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO 准备给管理员分配权限 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-25  16:54:03
     * LastModified
     * History:
     * </pre>
     */
    /*
    public ActionForward preGrantItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String usercodeAndTeamno = ParamUtils.getParameter(request, "usercodeAndTeamno").trim();
        String[] temp = usercodeAndTeamno.split("\\|");
        String usercode = temp[0];
        request.setAttribute("usercode", usercode);
        //该管理员所属管理员组
        String userTeamno = temp[1];

        UserDao userDao = new UserDao();
        List itemList = userDao.getUserItem(usercode);
        if (itemList != null && !itemList.isEmpty()) {
            String itemnos = "";
            Iterator it = itemList.iterator();
            for (int i = 0; it.hasNext(); i++) {
                MenuBean itemBean = (MenuBean) it.next();
                itemnos = itemnos + itemBean.getMenuno() + "|";
            }
            itemnos = itemnos.substring(0, itemnos.length() - 1);
            request.setAttribute("itemnos", itemnos);
        }
        //获得该管理员组的所有权限列表
        GroupDao groupDao = new GroupDao();
        List allItemList = groupDao.getGroupItemList(userTeamno);
        if (allItemList != null && !allItemList.isEmpty()) {
            request.setAttribute("allItemList", allItemList);
        }
        return mapping.findForward("grantItem.jsp");
    }
*/
    /**
     * 
     * @TODO 给管理员分配权限 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-25  17:07:40
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward grantItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String info = "";
        String flushdo = "/user.do?method=getUserList";

        //如果查询标志不为空
        HttpSession session = request.getSession();
        String querySign = (String) session.getAttribute("querySign");
        if (querySign != null) {
            flushdo = "/user.do?method=getUserListQuery";
        }

        List itemList = new ArrayList();
        MenuDao itemDao = new MenuDao();
        MenuBean itemBean = null;

        String usercode = ParamUtils.getParameter(request, "usercode").trim();
        String[] itemnos = request.getParameterValues("itemnos");
        if (itemnos != null) {
            for (int i = 0; i < itemnos.length; i++) {
//                itemBean = itemDao.getItemByItemno(itemnos[i]);
                itemList.add(itemBean);
            }
        }

        UserDao userDao = new UserDao();
        int result = userDao.grantItemToUser(usercode, itemList);
        if (result >= 0) {
            info = "管理员权限分配成功！";
            request.setAttribute("result", "0");
        } else {
            info = "管理员权限分配失败！";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO 准备修改管理员密码 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-29  10:47:03
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public ActionForward preModPassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String usercode = ParamUtils.getParameter(request, "usercode");

        request.setAttribute("usercode", usercode.trim());

        return mapping.findForward("modPassword.jsp");
    }

    /**
     * 
     * @TODO 修改管理员密码 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-28  18:04:51
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public ActionForward modPassword(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
        String usercode = ParamUtils.getParameter(request, "usercode");
        String passwd = ParamUtils.getParameter(request, "passwd");//原密码
        String passwdNew = ParamUtils.getParameter(request, "passwdNew");//新密码

        UserDao userDao = new UserDao();
        UserBean userBean = userDao.getUserInfo(usercode,roleno);

        String validdays = userBean.getValiddays();//密码有效期
        String passwddays = "-1";//密码失效日期
        //设置密码失效日期
        if (validdays.equals("-1")) {
            passwddays = "-1";
        } else {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(validdays));
            passwddays = DateUtils.getInstance().format(cal.getTime());
        }
        
		String lastintime = DateUtils.getInstance().getTime();
        userDao.setLastintime(usercode,lastintime);//记录管理员登录时间
        session.setAttribute("lastintime", lastintime);
        
        int result = userDao.modifyPassword(usercode, passwd, passwdNew, passwddays);

        if (result > 0) {
            request.setAttribute("result", "1");
        } else {
            request.setAttribute("result", "-1");
        }

        return mapping.findForward("modPasswdAjax.jsp");
    }

    /**
     * 
     * @TODO 核对管理员原始密码 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return -1原始密码错误 1原始密码正确
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-28  21:28:36
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward checkPasswd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String usercode = ParamUtils.getParameter(request, "usercode");
        String passwd = ParamUtils.getParameter(request, "passwd");//原密码
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();

        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        UserBean userBean = userDao.checkUser(usercode, passwd);
	
	        if (userBean == null) {
	            request.setAttribute("result", "-1");
	        } else {
	            request.setAttribute("result", "1");
	        }
        } else {
        	request.setAttribute("result", "-2");
        }
        return mapping.findForward("checkPasswdAjax.jsp");
    }

    /**
     * 
     * @TODO 检查新密码是否是弱密码 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-1-29  13:23:04
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward checkWeakPasswd(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	/*
        String passwdNew = ParamUtils.getParameter(request, "passwdNew");//新密码

        UserDao userDao = new UserDao();
        String passwd = userDao.checkWeakPasswd(passwdNew);
        if (passwd == null) {
            request.setAttribute("result", "1");
        } else {
            request.setAttribute("result", "-1");
        }
        */
        request.setAttribute("result", "1"); //不验证弱密码
        return mapping.findForward("checkWeakPasswdAjax.jsp");
    }

    /**
     * 
     * @TODO  重置管理员密码与密码失效日期 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2008-4-4  15:43:07
     * LastModified:2009-8-24	zl
     * History:
     * </pre>
     */
    public ActionForward resetUser(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
        String usercode = ParamUtils.getParameter(request, "usercode");
        UserDao userDao = new UserDao();
        UserBean userBean = userDao.getUserInfo(usercode,roleno);
        String validdays = userBean.getValiddays();
        String passwddays = "";
        String info = "";
        String flushdo = "/user.do?method=getUserList";
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        //      设置密码失效日期
	        if (validdays != null && validdays.intern() == ("-1").intern()) {
	            passwddays = "-1";
	        } else {
	            Calendar cal = Calendar.getInstance();
	            cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(validdays));
	            passwddays = DateUtils.getInstance().format(cal.getTime());
	        }
	        int result = userDao.resetUser(usercode, passwddays);
	        if (result >= 0) {
	            info = "管理员密码重置成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "管理员密码重置失败！";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "操作超时，请重新登录！");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }
    
//    public List getAdminDeptInfo(){
//    	List list = new ArrayList();
//    	DeptBean deptBean = new DeptBean();
//    	deptBean.setDeptno("000000000");
//    	deptBean.setDeptname("超级管理员用户组");
//        list.add(deptBean);
//        return list;
//    }
}
