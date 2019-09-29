package com.pay.system.struts.action;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.system.user.dao.UserDao;
import com.pay.util.ParamUtils;

public class CheckMenu extends Action {

    /** 
     * Method execute
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
    	String method = ParamUtils.getParameter(request, "method");
    	String forward = "";
    	if (method != null & method.equals("checkchild")) {
    		forward = checkChildMenu(request,response);
    	} else {
    		forward = checkMenuDirect(request,response);
    	}
        
        return mapping.findForward(forward);
    }
    
    //查找登录用户所点击菜单的子菜单项
    private String checkChildMenu(HttpServletRequest request,HttpServletResponse response) {
    	String forward = "left.jsp";
    	String menuId = ParamUtils.getParameter(request, "menuId");
    	HttpSession session = request.getSession(true);
    	
        String usercode = (String)session.getAttribute("usercode");
        UserDao userDao = new UserDao();

        List menuList = null;
        menuList = userDao.getUserMenuByParentMenuId(usercode, menuId);
        
        if (menuList != null) {
            request.setAttribute("menuList", menuList);
        }
    	return forward;
    }

    //查找子菜单项所对应的路径
    private String checkMenuDirect(HttpServletRequest request,HttpServletResponse response) {
    	String forward = "resultpage";
    	String menuno = ParamUtils.getParameter(request, "menuno").trim();
		forward = menuno + ".do";
		HttpSession session = request.getSession();
		if (session != null) {
			String usercode = (String) session.getAttribute("usercode");
				if (usercode != null && !usercode.equals("")) {
		        UserDao userDao = new UserDao();
		        String menu_level = userDao.getMenuLevelByParentMenuId(usercode, menuno);
				session.setAttribute("menu_level", menu_level);
			}
		}
    	return forward;
    }

}
