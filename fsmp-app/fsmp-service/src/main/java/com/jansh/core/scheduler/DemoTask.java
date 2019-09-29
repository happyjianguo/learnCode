package com.jansh.core.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 获取AccessToken定时任务
 * 
 * @author nie
 *
 */
public class DemoTask extends AbstractScheduledTask {

	private static final Logger logger = LogManager.getLogger(DemoTask.class);

	@Override
	public void exec() {
		logger.info("=======定时任务执行DemoTask");
	}

}