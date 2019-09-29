package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.CfDcBatchRechargeTaskService;

/**
 * 定时任务-批量充值
 * @author duanmuyn
 *
 */
public class DcBatchRchargeTask extends AbstractScheduledTask {
	
	private static final Logger logger = LogManager.getLogger(DcBatchRchargeTask.class);
	
	@Autowired
	private CfDcBatchRechargeTaskService cfDcBatchRechargeTaskService;
	@Override
	public void exec() {
		logger.info("=======定时任务执行批量充值");
		cfDcBatchRechargeTaskService.order();
	}

}
