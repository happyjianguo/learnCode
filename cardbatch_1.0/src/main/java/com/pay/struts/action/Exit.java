package com.pay.struts.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.system.user.dao.UserDao;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class Exit extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String flag = ParamUtils.getParameter(request, "flag");
        UserDao userDao = new UserDao();
        HttpSession session = request.getSession();
        String usercode = "";
        String str = "";
        String info = "";
        String deptno = (String) session.getAttribute("deptno");
        String lastintime = (String) session.getAttribute("lastintime");
        if (session.getAttribute("usercode") != null) {
            usercode = (String) session.getAttribute("usercode");
        }
        
        if (deptno == null || deptno.equals("") || usercode == null || usercode.equals("")) {
        	info = "超时已退出，请重新登录！";
        } else if (usercode != null && !lastintime.equals(userDao.getLastInTime(usercode))) {
        	info = "管理员已在其他地方登录，强制退出！";
        } else {
        	info = "已退出，请重新登录！";
        }
        if (usercode != null && usercode.intern() == "".intern())
            usercode = ParamUtils.getParameter(request, "usercode");

        userDao.setLoginflag(usercode, "0");//设置登录标示符为未登录状态
        //在管理员退出时注销该管理员的session对象
        session.invalidate();

//        info = str + "已退出，请重新登录！";
        request.setAttribute("result", "0");
        request.setAttribute("info", StringUtils.outerToInner(info));
        request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        /*
        if ("logout1".intern() == flag.intern()) {
            return mapping.findForward("exit");
        } else {
            return mapping.findForward("exit");
        }
        */
        //退出过程增加一结果界面，解决在登出后再重新登录，无法修改登录状态的问题
        return mapping.findForward("exitresultpage");
    }
}
