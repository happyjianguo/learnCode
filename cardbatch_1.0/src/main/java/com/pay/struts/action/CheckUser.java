package com.pay.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.system.dept.dao.DeptDao;
import com.pay.system.user.bean.UserBean;
import com.pay.system.user.dao.UserDao;
import com.pay.util.DateUtils;
import com.pay.util.ParamUtils;
import com.pay.util.StringUtils;

public class CheckUser extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.removeAttribute("usercode");
        session.removeAttribute("username");
        session.removeAttribute("roleno");
        session.removeAttribute("deptno");
        session.removeAttribute("deptname");
        session.removeAttribute("deptlevel");
        session.removeAttribute("dept_no_node");
        session.removeAttribute("deptlevel");
        session.removeAttribute("lastintime");
        String flashdo = "errorPage";//结果页面

        String captcha = ParamUtils.getParameter(request, "captcha").toLowerCase();
        String sessionCaptcha =(String) session.getAttribute("captcha");
        if(captcha.equals(sessionCaptcha)){
        	String usercode = ParamUtils.getParameter(request, "usercode");
        	String passwd = ParamUtils.getParameter(request, "passwd");
        	String mac = ParamUtils.getParameter(request, "mac").replaceAll("\\W", "");
        	UserDao userDao = new UserDao();
        	UserBean userBean = userDao.checkUser(usercode, passwd);
        	UserBean userInfoBean = null;
        	if(userBean!=null){
        		userInfoBean = userDao.getUserInfo(usercode,userBean.getRoleno());
        	}
        	
        	if (userInfoBean != null) {
        		session.setAttribute("usercode", StringUtils.outerToInner(userInfoBean.getUsercode().trim()));
        		session.setAttribute("username", StringUtils.outerToInner(userInfoBean.getUsername().trim()));
//          session.setAttribute("teamno", StringUtils.outerToInner(userInfoBean.getTeamno().trim()));
//          session.setAttribute("teamname", StringUtils.outerToInner(userInfoBean.getTeamname().trim()));
        		session.setAttribute("roleno", StringUtils.outerToInner(userInfoBean.getRoleno().trim()));
        		session.setAttribute("rolename", StringUtils.outerToInner(userInfoBean.getRolename().trim()));
        		session.setAttribute("deptno", StringUtils.outerToInner(userInfoBean.getDeptno().trim()));
        		session.setAttribute("deptname", StringUtils.outerToInner(userInfoBean.getDeptname().trim()));
        		session.setAttribute("deptlevel", StringUtils.outerToInner(userInfoBean.getDept_level().trim()));
        		DeptDao deptDao = new DeptDao();
        		String dept_no_node = deptDao.getDeptNoByUser(userInfoBean.getDeptno());
        		session.setAttribute("dept_no_node",dept_no_node);
        	}
        	
        	//判断管理员是否存在
        	if (userBean == null) {
        		String info = "管理员编号或密码错误！登录系统失败！";
        		request.setAttribute("result", "-1");
        		request.setAttribute("info", StringUtils.outerToInner(info));
        		request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        	} else if(!userBean.getIsactive().equals("1")){
        		String info = "管理员状态无效！登录系统失败！";
        		request.setAttribute("result", "-1");
        		request.setAttribute("info", StringUtils.outerToInner(info));
        		request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        	}else if(userBean.getMac().length()>0 &&mac.indexOf(userBean.getMac().trim().replaceAll("\\W", ""))<0){
        			String info = "物理地址不正确，请到原地址使用！";
        			request.setAttribute("result", "-1");
        			request.setAttribute("info", StringUtils.outerToInner(info));
        			request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        	} 
        	else {
        		String passwddays = userBean.getPasswddays().trim();//密码有效日期
        		String today = DateUtils.getInstance().getToday();//获取系统时间
        		String lastintime = DateUtils.getInstance().getTime();
        		//如果密码永不过期
        		if (passwddays.equals("-1")) {
        			//如果管理员第一次登录系统
        			if ("0".equals(userBean.getLastintime())) {
        				request.setAttribute("usercode", usercode);
        				request.setAttribute("firstLogin", "yes");//yes表示该管理员是第一次登录
        				response.setHeader("Pragma", "No-Cache");
        				response.setHeader("Cache-Control", "No-Cache");
        				response.setDateHeader("Expires", 0);
        				return mapping.findForward("login.jsp");
        			}
        			
        			flashdo = "index.jsp";
        			
        			userDao.setLoginflag(usercode, "1");//设置登录标示符为已登录状态
        			userDao.setLastintime(usercode, lastintime);//记录管理员登录时间
        			
        			request.setAttribute("usercode", userBean.getUsercode());
        			request.setAttribute("username", userBean.getUsername());
        			
        			request.setAttribute("roleno", userBean.getRoleno());
        			request.setAttribute("rolename", StringUtils.outerToInner(userInfoBean.getRolename().trim()));
        			request.setAttribute("deptno", StringUtils.outerToInner(userInfoBean.getDeptno().trim()));
        			request.setAttribute("deptname", StringUtils.outerToInner(userInfoBean.getDeptname().trim()));
        			request.setAttribute("currentDate", DateUtils.getInstance().getToday());
        			request.setAttribute("nextdays", "-1");
        			request.setAttribute("lastintime", StringUtils.outerToInner(lastintime));
        			session.setAttribute("lastintime", StringUtils.outerToInner(lastintime));
        		} else {//如果密码设置了有效期限
        			long nextdays = DateUtils.getInstance().compareTwoDate(passwddays, today);//密码剩余有效天数
        			if (nextdays > 0) {
        				//如果管理员第一次登录系统
        				if ("0".equals(userBean.getLastintime())) {
        					request.setAttribute("usercode", usercode);
        					request.setAttribute("firstLogin", "yes");//yes表示该管理员是第一次登录
        					response.setHeader("Pragma", "No-Cache");
        					response.setHeader("Cache-Control", "No-Cache");
        					response.setDateHeader("Expires", 0);
        					return mapping.findForward("login.jsp");
        				}
        				
        				flashdo = "index.jsp";
        				
        				userDao.setLoginflag(usercode, "1");//设置登录标示符为已登录状态
        				userDao.setLastintime(usercode,lastintime);//记录管理员登录时间
        				
        				request.setAttribute("nextdays", Long.toString(nextdays));
        				request.setAttribute("usercode", userBean.getUsercode());
        				request.setAttribute("username", userBean.getUsername());
//                    request.setAttribute("teamno", userBean.getTeamno());
//                    request.setAttribute("teamname", userBean.getTeamname());
        				request.setAttribute("roleno", userBean.getRoleno());
        				request.setAttribute("rolename", StringUtils.outerToInner(userInfoBean.getRolename().trim()));
        				request.setAttribute("deptno", StringUtils.outerToInner(userInfoBean.getDeptno().trim()));
        				request.setAttribute("deptname", StringUtils.outerToInner(userInfoBean.getDeptname().trim()));
        				request.setAttribute("currentDate", DateUtils.getInstance().getToday());
        				session.setAttribute("lastintime", StringUtils.outerToInner(lastintime));
        				request.setAttribute("lastintime", StringUtils.outerToInner(lastintime));
        			} else {
        				String info = "密码已过期！请联系管理员！";
        				request.setAttribute("result", "-1");
        				request.setAttribute("info", StringUtils.outerToInner(info));
        				request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        			}
        		}
        	}
        }else{
        	String info = "验证码错误，请重新输入！";
    		request.setAttribute("result", "-1");
    		request.setAttribute("info", StringUtils.outerToInner(info));
    		request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        }
        return mapping.findForward(flashdo);
    }
}
