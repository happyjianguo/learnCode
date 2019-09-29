package com.pay.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

import com.pay.util.SystemConfig;

public class SysConfLoad extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        SystemConfig.load(context);
        PropertyConfigurator.configure(SystemConfig.getValue("confurl") + SystemConfig.getValue("logconfurl"));
        
        
  //      System.out.println("===========start=====");
    }
}
