package com.pay.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SysMainAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
		String  flashdo = "index.jsp";
		String usercode=(String)request.getAttribute("usercode");
		if(usercode!=null&&!usercode.equals("")){
			flashdo = "index.jsp";
		}else{
			flashdo = "login.jsp";
		}
		return mapping.findForward(flashdo);
	}
}
