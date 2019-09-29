//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl

package com.pay.system.menu.struts.action;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.pay.system.group.dao.GroupDao;
import com.pay.system.menu.bean.MenuBean;
import com.pay.system.menu.dao.MenuDao;
import com.pay.system.menu.struts.form.MenuForm;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

/** 
 * MyEclipse Struts
 * Creation date: 12-21-2007
 * 
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class MenuAction extends DispatchAction {

    private static final Logger logger = Logger.getLogger(GroupDao.class);

    /**
     * 
     * @TODO  显示权限列表
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
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward showItemList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {

        MenuDao itemDao = new MenuDao();
        List itemList = itemDao.getMenuList();
        if (itemList != null && !itemList.isEmpty()) {
            request.setAttribute("itemList", itemList);
        }
        return mapping.findForward("showItemList");
    }

    /**
     * 
     * @TODO 添加权限信息
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
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward addItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        MenuForm itemForm = (MenuForm) form;
        MenuBean itemBean = new MenuBean();
        try {
            BeanUtils.copyProperties(itemBean, itemForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        MenuDao itemDao = new MenuDao();

        String info = "";
        String flushdo = "/item.do?method=showItemList";
        //TODO权限编号重复， //itemDao.addItem(itemBean);
        int result = -1;//itemDao.addItem(itemBean);
        if (result == -239) {
            info = "权限编号重复，添加失败！";
            request.setAttribute("result", "1");
        } else if (result >= 0) {
            info = "权限添加成功！";
            request.setAttribute("result", "0");
        } else {
            info = "数据库操作异常，权限添加失败！";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO 准备修改功能信息
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
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward preModItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        MenuForm itemForm = (MenuForm) form;
        String itemno = ParamUtils.getParameter(request, "itemno");
        MenuDao itemDao = new MenuDao();
        MenuBean itemBean = itemDao.getMenuByMenuno(itemno);
        try {
            if (itemBean != null)
                BeanUtils.copyProperties(itemForm, itemBean);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        return mapping.findForward("modItem.jsp");
    }

    /**
     * 
     * @TODO 修改权限信息 
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
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward modItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        MenuForm itemForm = (MenuForm) form;
        MenuBean itemBean = new MenuBean();
        try {
            BeanUtils.copyProperties(itemBean, itemForm);
        } catch (IllegalAccessException e) {
            logger.error("IllegalAccessException", e);
        } catch (InvocationTargetException e) {
            logger.error("InvocationTargetException", e);
        }
        MenuDao itemDao = new MenuDao();

        String info = "";
        String flushdo = "/item.do?method=showItemList";
      //TODO权限编号重复， //itemDao.updateItem(itemBean);
        int result = -1;//itemDao.updateItem(itemBean);
        if (result >= 0) {
            info = "权限修改成功！";
            request.setAttribute("result", "0");
        } else {
            info = "权限修改失败！";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

    /**
     * 
     * @TODO 根据权限编号删除权限信息
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
     * LastModified
     * History:
     * </pre>
     */
    public ActionForward deleteItem(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String itemno = ParamUtils.getParameter(request, "itemno");
        MenuDao itemDao = new MenuDao();

        String info = "";
        String flushdo = "/item.do?method=showItemList";
//TODO //temDao.deleteItem(itemno);
        int result = -1;//itemDao.deleteItem(itemno);
        if (result >= 0) {
            info = "权限删除成功！";
            request.setAttribute("result", "0");
        } else {
            info = "权限删除失败！";
            request.setAttribute("result", "1");
        }
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", flushdo);

        return mapping.findForward("result.jsp");
    }

}
