package cn.com.jansh.util.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

public class AppContext {
	private static final Logger logger = LogManager.getLogger(AppContext.class);
	
	private static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext appContext) {
		logger.debug("set ApplicationContext...");
		applicationContext = appContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

}
