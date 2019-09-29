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
        String flashdo = "errorPage";//���ҳ��

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
        	
        	//�жϹ���Ա�Ƿ����
        	if (userBean == null) {
        		String info = "����Ա��Ż�������󣡵�¼ϵͳʧ�ܣ�";
        		request.setAttribute("result", "-1");
        		request.setAttribute("info", StringUtils.outerToInner(info));
        		request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        	} else if(!userBean.getIsactive().equals("1")){
        		String info = "����Ա״̬��Ч����¼ϵͳʧ�ܣ�";
        		request.setAttribute("result", "-1");
        		request.setAttribute("info", StringUtils.outerToInner(info));
        		request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        	}else if(userBean.getMac().length()>0 &&mac.indexOf(userBean.getMac().trim().replaceAll("\\W", ""))<0){
        			String info = "�����ַ����ȷ���뵽ԭ��ַʹ�ã�";
        			request.setAttribute("result", "-1");
        			request.setAttribute("info", StringUtils.outerToInner(info));
        			request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        	} 
        	else {
        		String passwddays = userBean.getPasswddays().trim();//������Ч����
        		String today = DateUtils.getInstance().getToday();//��ȡϵͳʱ��
        		String lastintime = DateUtils.getInstance().getTime();
        		//���������������
        		if (passwddays.equals("-1")) {
        			//�������Ա��һ�ε�¼ϵͳ
        			if ("0".equals(userBean.getLastintime())) {
        				request.setAttribute("usercode", usercode);
        				request.setAttribute("firstLogin", "yes");//yes��ʾ�ù���Ա�ǵ�һ�ε�¼
        				response.setHeader("Pragma", "No-Cache");
        				response.setHeader("Cache-Control", "No-Cache");
        				response.setDateHeader("Expires", 0);
        				return mapping.findForward("login.jsp");
        			}
        			
        			flashdo = "index.jsp";
        			
        			userDao.setLoginflag(usercode, "1");//���õ�¼��ʾ��Ϊ�ѵ�¼״̬
        			userDao.setLastintime(usercode, lastintime);//��¼����Ա��¼ʱ��
        			
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
        		} else {//���������������Ч����
        			long nextdays = DateUtils.getInstance().compareTwoDate(passwddays, today);//����ʣ����Ч����
        			if (nextdays > 0) {
        				//�������Ա��һ�ε�¼ϵͳ
        				if ("0".equals(userBean.getLastintime())) {
        					request.setAttribute("usercode", usercode);
        					request.setAttribute("firstLogin", "yes");//yes��ʾ�ù���Ա�ǵ�һ�ε�¼
        					response.setHeader("Pragma", "No-Cache");
        					response.setHeader("Cache-Control", "No-Cache");
        					response.setDateHeader("Expires", 0);
        					return mapping.findForward("login.jsp");
        				}
        				
        				flashdo = "index.jsp";
        				
        				userDao.setLoginflag(usercode, "1");//���õ�¼��ʾ��Ϊ�ѵ�¼״̬
        				userDao.setLastintime(usercode,lastintime);//��¼����Ա��¼ʱ��
        				
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
        				String info = "�����ѹ��ڣ�����ϵ����Ա��";
        				request.setAttribute("result", "-1");
        				request.setAttribute("info", StringUtils.outerToInner(info));
        				request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        			}
        		}
        	}
        }else{
        	String info = "��֤��������������룡";
    		request.setAttribute("result", "-1");
    		request.setAttribute("info", StringUtils.outerToInner(info));
    		request.setAttribute("flushdo", "/jsppage/common/login.jsp");
        }
        return mapping.findForward(flashdo);
    }
}
