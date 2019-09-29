package cn.com.jansh.util.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AppContextRegister implements ApplicationContextAware {
	
	private static final Logger logger = LogManager.getLogger(AppContextRegister.class);
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		logger.debug("setApplicationContext...");
		AppContext.setApplicationContext(applicationContext);
		
	}

}
