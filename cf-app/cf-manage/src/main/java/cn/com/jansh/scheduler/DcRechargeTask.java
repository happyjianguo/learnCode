package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.CfDcRechargeTaskService;

/**
 * 单笔充值-定时任务
 * 
 * @author nie
 *
 */
public class DcRechargeTask extends AbstractScheduledTask {

	private static final Logger logger = LogManager.getLogger(DcRechargeTask.class);

	@Autowired
	private CfDcRechargeTaskService cfDcRechargeTaskService;
	
	@Override
	public void exec() {
		logger.info("=======定时任务执行单笔充值");
		cfDcRechargeTaskService.order();
	}
}