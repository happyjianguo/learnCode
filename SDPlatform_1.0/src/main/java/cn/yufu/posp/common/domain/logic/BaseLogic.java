package cn.yufu.posp.common.domain.logic;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.yufu.core.domain.logic.Logic;

public class BaseLogic extends Logic
{
	
	protected static final Log logicLog = LogFactory.getLog("logic") ;
	protected static final Log exceptionLog = LogFactory.getLog("exception") ;
	protected static final Log debugLog = LogFactory.getLog("debug");
	
	private static  ApplicationContext ctx = null;
	
	public Object getBean(String newBeanName)
	{
		if(ctx==null){

			String[] paths = {"spring/applicationContext.xml","spring/applicationContext_dao_zy.xml","spring/applicationContext_dao_lzh.xml",
					"spring/applicationContext_dao_fdw.xml","spring/applicationContext_dao_sj.xml","spring/applicationContext_dao_tjy.xml"};
            ctx = new ClassPathXmlApplicationContext(paths);
        }        
		return ctx.getBean(newBeanName);
	}
}
