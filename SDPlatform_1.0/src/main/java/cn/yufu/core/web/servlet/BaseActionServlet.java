package cn.yufu.core.web.servlet;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;

/**
 * 继承Struts的ActionServlet.可增加本系统模块需要的特殊处理。
 */
public class BaseActionServlet extends ActionServlet {

	/**
	 * @roseuid 43BDE5380346
	 */
	public BaseActionServlet() {

	}

	/**
	 * super.init() ; //初始化本模块所需的处理
	 * 
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		super.init();
	}
}
