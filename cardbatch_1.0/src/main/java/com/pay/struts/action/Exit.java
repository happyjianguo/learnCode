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
        	info = "��ʱ���˳��������µ�¼��";
        } else if (usercode != null && !lastintime.equals(userDao.getLastInTime(usercode))) {
        	info = "����Ա���������ط���¼��ǿ���˳���";
        } else {
        	info = "���˳��������µ�¼��";
        }
        if (usercode != null && usercode.intern() == "".intern())
            usercode = ParamUtils.getParameter(request, "usercode");

        userDao.setLoginflag(usercode, "0");//���õ�¼��ʾ��Ϊδ��¼״̬
        //�ڹ���Ա�˳�ʱע���ù���Ա��session����
        session.invalidate();

//        info = str + "���˳��������µ�¼��";
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
        //�˳���������һ������棬����ڵǳ��������µ�¼���޷��޸ĵ�¼״̬������
        return mapping.findForward("exitresultpage");
    }
}
