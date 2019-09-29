package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.core.exception.AppException;
import cn.com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.CfQueryOrderTaskService;

/**
 * 刷新流水表状态-定时任务
 * @author duanmuyn
 *
 */
public class CfQueryOrderTask extends AbstractScheduledTask{

	private static final Logger logger = LogManager.getLogger(CfQueryOrderTask.class);
	
	@Autowired
	private CfQueryOrderTaskService CfQueryOrderTaskService;
	
	@Override
	public void exec() {
		logger.info("=======定时任务执行订单状态查询");
		try {
			CfQueryOrderTaskService.queryOrder();
		} catch (AppException e) {
			logger.error("异常信息：------{}",e);
		}
	}
	
}
