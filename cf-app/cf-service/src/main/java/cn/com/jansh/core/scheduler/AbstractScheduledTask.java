/**
 * AbstractTask.java
 * 2016年1月14日 下午7:40:56
 * 
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 */
package cn.com.jansh.core.scheduler;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author nie
 *
 */
public abstract class AbstractScheduledTask {

	private static final Logger logger = LogManager.getLogger(AbstractScheduledTask.class);

	private String isStartRunning = "false";

	private String disableBatchKey = "scheduler.disable";

	private String enableSchedulerKey = "scheduler.enable";

	/**
	 * 定时任务自动执行方法
	 */
	public final void execute() {
		if (isEnableBatch()) {
			logger.info("ScheduledTask is enabled!");
			exec();
		} else {
			logger.info("ScheduledTask is disabled!");
		}
	}

	/**
	 * 定时任务业务方法
	 */
	public abstract void exec();

	/**
	 * 启动服务执行定时任务
	 */
	@PostConstruct
	public final void startExecute() {
		if ("true".equals(isStartRunning)) {
			logger.info("启动服务执行该定时任务");
			execute();
		} else {
			logger.info("启动服务不执行该定时任务");
		}
	}

	/**
	 * 根据环境变量cheduler.disable来判断系统是否运行跑批程序,
	 * 环境变量需要在jvm的参数中使用-Dscheduler.disable=true方式设置
	 * 
	 * @return
	 */
//	private static boolean isDisableBatch() {
//		try {
//			String tmp = System.getProperty(disableBatchKey);
//			logger.info("{} is {}", disableBatchKey, tmp);
//			if ((tmp != null) && (tmp.toLowerCase().equals("true"))) {
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception ee) {
//			return false;
//		}
//	}

	/**
	 * 根据环境变量scheduler.enable来判断系统是否运行跑批程序,
	 * 环境变量需要在jvm的参数中使用-Dscheduler.enable=true方式设置
	 * 
	 * @return
	 */
	private boolean isEnableBatch() {
		try {
			String tmp = System.getProperty(this.enableSchedulerKey);
			logger.info("{} is {}", enableSchedulerKey, tmp);
			if ("true".equals(tmp)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ee) {
			logger.error(ee);
			return false;
		}
	}

	public String getIsStartRunning() {
		return isStartRunning;
	}

	public void setIsStartRunning(String isStartRunning) {
		this.isStartRunning = isStartRunning;
	}

	public String getDisableBatchKey() {
		return disableBatchKey;
	}

	public void setDisableBatchKey(String disableBatchKey) {
		this.disableBatchKey = disableBatchKey;
	}

	public String getEnableSchedulerKey() {
		return enableSchedulerKey;
	}

	public void setEnableSchedulerKey(String enableSchedulerKey) {
		this.enableSchedulerKey = enableSchedulerKey;
	}
}
