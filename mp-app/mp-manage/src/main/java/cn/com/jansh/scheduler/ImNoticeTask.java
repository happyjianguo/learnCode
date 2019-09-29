/**
 * ImNoticeTask.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:gll 2016年11月23日
 */
package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.scheduler.AbstractScheduledTask;

import cn.com.jansh.service.scheduler.ImNoticeTaskService;

/**
 * 公告定时任务
 * @author gll
 * @version 1.0
 */
public class ImNoticeTask extends AbstractScheduledTask {
	
	private static final Logger logger = LogManager.getLogger(ImNoticeTask.class);
	
	@Autowired
	private ImNoticeTaskService imNoticeTaskService;

	/**
	 * 定时任务默认的执行方法
	 */
	@Override
	public void exec() {
		logger.info("======公告定时刷新任务开始执行=========");
		imNoticeTaskService.updateNoticeByendtime();
	}

}
