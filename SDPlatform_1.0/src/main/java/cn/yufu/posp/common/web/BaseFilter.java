package cn.yufu.posp.common.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseFilter implements Filter{
	private static  ApplicationContext ctx = null;
	public Object getBean(String newBeanName)
	{	
		if(ctx==null){

			String[] paths = {"/spring/applicationContext.xml","/spring/applicationContext_logic_msg.xml"};
            ctx = new ClassPathXmlApplicationContext(paths);
        }        
		return ctx.getBean(newBeanName);		
	}
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
