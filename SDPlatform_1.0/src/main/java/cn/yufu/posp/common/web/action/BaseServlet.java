package cn.yufu.posp.common.web.action;

import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BaseServlet extends HttpServlet
{

	public Object getBean(String newBeanName)
	{
		ApplicationContext ctx = 
            WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        return ctx.getBean(newBeanName);
	}

}
