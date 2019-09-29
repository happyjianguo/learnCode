package com.pay.system.menu.struts.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.pay.util.SystemConfig;

public class MenuJsonAction extends Action{
	private static final Logger logger = Logger.getLogger(MenuJsonAction.class);
	public ActionForward execute(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		String userId=(String)session.getAttribute("usercode");
		String fileName =SystemConfig.getValue("confurl")+"/WEB-INF/permissions/"+ userId + ".json";
		File file = new File(fileName);
		String jsonmenu="";
		if (file.isFile() && file.exists()) {
			FileInputStream fis = null;
			
			BufferedReader reader = null;
			try {
				fis = new FileInputStream(file);				
				reader = new BufferedReader(new InputStreamReader(fis,"gb2312"));				
				jsonmenu=reader.readLine();
				
			} catch (Exception e) {
				
			} finally {
				if(reader != null){
					try {
						reader.close();
					} catch (IOException e) {}
				}
				if(fis != null){
					try {
						fis.close();
					} catch (IOException e) {}
				}
			}
		} else {
			
		}	
		request.setAttribute("jsonmenu", jsonmenu);
        return mapping.findForward("menudata.jsp");
    }
}
