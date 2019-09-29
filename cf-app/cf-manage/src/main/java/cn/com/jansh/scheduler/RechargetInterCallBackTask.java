package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.RechargetInterCallBackService;

/**
 * 接口定时回调
 * @author duanmuyn
 *
 */
public class RechargetInterCallBackTask extends AbstractScheduledTask{

	private static final Logger logger = LogManager.getLogger(RechargetInterCallBackTask.class);
	
	@Autowired
	private RechargetInterCallBackService rechargetInterCallBackService;
	
	@Override
	public void exec() {
		logger.info("接口定时回调-----");
		rechargetInterCallBackService.CallBack();
	}
	
}
