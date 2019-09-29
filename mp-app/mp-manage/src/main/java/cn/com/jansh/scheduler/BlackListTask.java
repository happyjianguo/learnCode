/**
 * BlackListTask.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年10月26日
 */
package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.BlackListTaskService;

/**
 * 黑名单定时任务
 * @author gll
 * @version 1.0
 */
public class BlackListTask extends AbstractScheduledTask {

	private static final Logger logger = LogManager.getLogger(BlackListTask.class);
	
	@Autowired
	private BlackListTaskService blackListTaskService;	//黑名单定时刷新service
	/**
	 * 定时任务默认执行的方法
	 */
	@Override
	public void exec() {
		logger.info("======黑名单定时刷新任务开始执行=========");
		blackListTaskService.updateBlackListByovertime();
	}
}
