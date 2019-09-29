//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.dept.struts.action;

import java.lang.reflect.InvocationTargetException;
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

import com.pay.system.area.dao.AreaDao;
import com.pay.system.dept.bean.DeptBean;
import com.pay.system.dept.dao.DeptDao;
import com.pay.system.dept.struts.form.DeptForm;
import com.pay.system.group.dao.GroupDao;
import com.pay.util.Constant;
import com.pay.util.PageBean;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

/** 
 * MyEclipse Struts
 * Creation date: 9-16-2009
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class DeptAction extends DispatchAction {

    private static final Logger logger = Logger.getLogger(GroupDao.class);

    /**
     * 
     * @TODO  显示用户组列表
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author 黄斌
     * @version 1.0
     * <pre>
     * Created on:2007-12-21  11:08:01
     * LastModified:2009-8-25 zl
     * History:
     * </pre>
     */
    public ActionForward getDeptList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        DeptDao deptDao = new DeptDao();

		/** 分页条件部分 */
		// 获得查询结果总记录数
		int count = deptDao.getCount("");
		// 构造分页对象
		PageBean pageBean = new PageBean(count, Constant.getInstance().PageSize, ParamUtils
				.getIntParameter(request, "currentPage", 1));
		// 保存分页对象信息
		request.setAttribute("pageBean", pageBean);
        List deptList = deptDao.getDeptListPage("", pageBean);
        if (deptList != null && !deptList.isEmpty()) {
            request.setAttribute("deptList", deptList);
        }
        return mapping.findForward("showDeptList");
    }

    /**
     * 
     * @TODO 添加用户组信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward addDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DeptForm deptForm = (DeptForm) form;
        DeptBean deptBean = new DeptBean();
        try {
            BeanUtils.copyProperties(deptBean, deptForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        DeptDao deptDao = new DeptDao();

        String info = "";
        String flushdo = "/dept.do?method=getDeptList";
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("deptno") != null) {
	        int result = deptDao.addDept(deptBean);
	        if (result == -2) {
	            info = "用户组号重复，添加失败！";
	            request.setAttribute("result", "1");
	        }else if (result == -3) {
	            info = "用户组名称重复，添加失败！";
	            request.setAttribute("result", "1");
	        }  else if (result >= 0) {
	            info = "用户组添加成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "数据库操作异常，用户组添加失败！";
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
     * @TODO 准备修改用户组信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward preModDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DeptForm deptForm = (DeptForm) form;
        String deptno = ParamUtils.getParameter(request, "deptno");
        
        DeptDao deptDao = new DeptDao();
        AreaDao areaDao = new AreaDao();
        DeptBean deptBean = deptDao.getDeptByDeptno(deptno);
      //  List provList = areaDao.getAllProvinces();
      //  List areaList = areaDao.getAreaByProvId(deptBean.getProv_id());
        try {
            if (deptBean != null) {
                BeanUtils.copyProperties(deptForm, deptBean);
                //request.setAttribute("provList", provList);
               // request.setAttribute("areaList", areaList);
            }
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("modDept.jsp");
    }

    /**
     * 
     * @TODO 修改用户组信息 
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward modDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DeptForm deptForm = (DeptForm) form;
        DeptBean deptBean = new DeptBean();
        try {
            BeanUtils.copyProperties(deptBean, deptForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        DeptDao deptDao = new DeptDao();

        String info = "";
        String flushdo = "/dept.do?method=getDeptList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = deptDao.updateDept(deptBean);
	        if (result >= 0) {
	            info = "用户组修改成功！";
	            request.setAttribute("result", "0");
	        } else {
	            info = "用户组修改失败！";
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
     * @TODO 根据用户组编号删除用户组信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 跳转路径
     * @author zl
     * @version 1.0
     * <pre>
     * Created on:2009-9-16
     * LastModified:
     * History:
     * </pre>
     */
    public ActionForward deleteDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String deptno = ParamUtils.getParameter(request, "deptno");
        DeptDao deptDao = new DeptDao();

        String info = "";
        String flushdo = "/dept.do?method=getDeptList";
        HttpSession session = request.getSession();
        if (UserUtils.getUserName()!=null&&!"".equals(UserUtils.getUserName())) {
	        int result = deptDao.deleteDept(deptno);
	        if (result >= 0) {
	            info = "用户组删除成功！";
	            request.setAttribute("result", "0");
	        } else if (result == -2) {
	            info = "删除失败，该用户组已分配给其他管理员！";
	            request.setAttribute("result", "1");
	        } else if (result == -3) {
	            info = "删除失败，该用户组已分配给其他商户！";
	            request.setAttribute("result", "1");
	        } else if (result == -4) {
	            info = "删除失败，该用户组已存在下属用户组！";
	            request.setAttribute("result", "1");
	        } else {
	            info = "用户组删除失败！";
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

}
