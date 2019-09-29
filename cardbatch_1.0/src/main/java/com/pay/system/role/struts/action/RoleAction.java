//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.role.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.pay.system.group.dao.GroupDao;
import com.pay.system.menu.bean.MenuBean;
import com.pay.system.menu.dao.MenuDao;
import com.pay.system.menu.dao.MenuPermissonDao;
import com.pay.system.role.bean.RoleBean;
import com.pay.system.role.dao.RoleDao;
import com.pay.system.role.struts.form.RoleForm;
import com.pay.util.Constant;
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
public class RoleAction extends DispatchAction {

    private static final Logger logger = Logger.getLogger(GroupDao.class);

    /**
     * 
     * @TODO  ��ʾ��ɫ�б�
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
     * LastModified:2009-8-25 zl
     * History:
     * </pre>
     */
    public ActionForward getRoleList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        RoleDao roleDao = new RoleDao();
        HttpSession session = request.getSession();
        String roleno = session.getAttribute("roleno") == null ? "00": (String) session.getAttribute("roleno");
     // �����ҳ����
        int count=roleDao.getCount(roleno, "");
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils.getIntParameter(request, "currentPage", 1));
        List roleList = roleDao.getRoleList(roleno.trim(),"",pageBean);
     // �����ҳ������Ϣ
		request.setAttribute("pageBean", pageBean);
        if (roleList != null && !roleList.isEmpty()) {
            request.setAttribute("roleList", roleList);
        }
        return mapping.findForward("showRoleList");
    }

    /**
     * 
     * @TODO ��ӽ�ɫ��Ϣ
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
     * LastModified:2009-8-25 zl
     * History:
     * </pre>
     */
    public ActionForward addRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        RoleForm roleForm = (RoleForm) form;
        RoleBean roleBean = new RoleBean();
        try {
            BeanUtils.copyProperties(roleBean, roleForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        RoleDao roleDao = new RoleDao();

        String info = "";
        String flushdo = "/role.do?method=getRoleList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = roleDao.addRole(roleBean);
	        if (result == -2) {
	            info = "��ɫ���ظ������ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }else if (result == -3) {
		            info = "��ɫ���ظ������ʧ�ܣ�";
		            request.setAttribute("result", "1");		        
	        } else if (result >= 0) {
	            info = "��ɫ��ӳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "���ݿ�����쳣����ɫ���ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ׼���޸Ľ�ɫ��Ϣ
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
     * LastModified:2009-8-25 zl
     * History:
     * </pre>
     */
    public ActionForward preModRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        RoleForm roleForm = (RoleForm) form;
        String roleno = ParamUtils.getParameter(request, "roleno");
        RoleDao roleDao = new RoleDao();
        RoleBean roleBean = roleDao.getRoleByRoleno(roleno);
        try {
            if (roleBean != null) {
                BeanUtils.copyProperties(roleForm, roleBean);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("modRole.jsp");
    }

    /**
     * 
     * @TODO �޸Ľ�ɫ��Ϣ 
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
     * LastModified:2009-8-25 zl
     * History:
     * </pre>
     */
    public ActionForward modRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        RoleForm roleForm = (RoleForm) form;
        RoleBean roleBean = new RoleBean();
        try {
            BeanUtils.copyProperties(roleBean, roleForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        RoleDao roleDao = new RoleDao();

        String info = "";
        String flushdo = "/role.do?method=getRoleList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = roleDao.updateRole(roleBean);
	        if (result >= 0) {
	            info = "��ɫ�޸ĳɹ���";
	            request.setAttribute("result", "0");
	        } else {
	            info = "��ɫ�޸�ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ���ݽ�ɫ���ɾ����ɫ��Ϣ
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
     * Updated on:2009-10-29  10:17:32 from ����
     * LastModified:2009-8-25 zl
     * History:
     * </pre>
     */
    public ActionForward deleteRole(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String roleno = ParamUtils.getParameter(request, "roleno");
        RoleDao roleDao = new RoleDao();

        String info = "";
        String flushdo = "/role.do?method=getRoleList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = roleDao.deleteRole(roleno);
	        if (result >= 0) {
	            info = "��ɫɾ���ɹ���";
	            request.setAttribute("result", "0");
	        } else if (result == -2) {
	            info = "�ý�ɫ�����ڹ���Ա������ɾ������";
	            request.setAttribute("result", "1");
	        } else {
	            info = "��ɫɾ��ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO ׼������Ȩ�ޣ�׼��Ȩ���б�
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
     * LastModified:2009-8-24	zl
     * LastModified:2009-11-13	������Ȩ�޷����м��� ���ܿ���ѡ�1 ������2 �޸� ��3 ɾ������
     * History:
     * </pre>
     */
    public ActionForward preGrantMenu(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
    	HashMap levelMap = new HashMap();
        String roleno = ParamUtils.getParameter(request, "roleno");
        request.setAttribute("roleno", roleno);
        
        RoleDao roleDao = new RoleDao();
        MenuDao menuDao = new MenuDao();
        List menuList = roleDao.getRoleMenuList(roleno);
        if (menuList != null && !menuList.isEmpty()) {
            String menunos = "";
            Iterator it = menuList.iterator();
            for (int i = 0; it.hasNext(); i++) {
                MenuBean menuBean = (MenuBean) it.next();
                menunos = menunos + menuBean.getMenuno() + "|";
                if(menuBean.getMenu_level()!=null && menuBean.getMenu_level().length()>0){
                	levelMap.put(menuBean.getMenuno(), menuBean.getMenu_level());
                }
            }
            menunos = menunos.substring(0, menunos.length() - 1);
            request.setAttribute("menunos", menunos);
            request.setAttribute("levelMap", levelMap);
        }

        List allMenuList = menuDao.getMenuList();
        if (allMenuList != null && !allMenuList.isEmpty()) {
            request.setAttribute("allMenuList", allMenuList);
        }
        return mapping.findForward("grantMenu.jsp");
    }

    /**
     * 
     * @TODO ����Ȩ��
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
     * LastModified:2009-8-24	zl
     * LastModified:2009-11-13	������Ȩ�޷����м��� ���ܿ���ѡ�1 ������2 �޸� ��3 ɾ������
     * History:
     * </pre>
     */
    public ActionForward grantMenu(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        List menuList = new ArrayList();
        List levelList = new ArrayList();
        MenuDao menuDao = new MenuDao();
        MenuBean menuBean = null;
       
       
        String roleno = ParamUtils.getParameter(request, "roleno");
        String[] menunos = request.getParameterValues("menunos");
        if (menunos != null) {
            for (int i = 0; i < menunos.length; i++) {
                menuBean = menuDao.getMenuByMenuno(menunos[i]);
            	String level = "";
            	String[] levelStr = request.getParameterValues(menunos[i]);
                if(levelStr!=null && levelStr.length !=0){
                	for(int y =0;y<levelStr.length;y++){
                		level+=levelStr[y];
                	}
                }else{
                	
                }
                menuBean.setMenu_level(level);
                menuList.add(menuBean);
                
            }
        }
        RoleDao roleDao = new RoleDao();

        String info = "";
        String flushdo = "/role.do?method=getRoleList";
        HttpSession session = request.getSession();
        String usercode =(String)session.getAttribute("usercode");
        
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = roleDao.grantMenu(roleno, menuList,levelList);
	        if (result >= 0) {
	            info = "��ɫȨ�޷���ɹ���";
	            request.setAttribute("result", "0");
	            /***	qhg add     20120619 �������Ȩ�������ļ�			***/
	            new MenuPermissonDao().createPerFile();           
	            
	            /***	qhg add_end 20120619 �������Ȩ�������ļ�			***/
	        } else {
	            info = "��ɫȨ�޷���ʧ�ܣ�";
	            request.setAttribute("result", "1");
	        }
	        request.setAttribute("info", StringUtils.outerToInner(info));
	        request.setAttribute("flushdo", flushdo);
        } else {
        	request.setAttribute("info", "������ʱ�������µ�¼��");
	        request.setAttribute("flushdo", flushdo);
            request.setAttribute("result", "1");
        }
        return mapping.findForward("result.jsp");
    }
}
