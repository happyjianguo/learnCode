package cn.com.jansh.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.CfReportOrderTaskService;

/**
 * 欧非对账接口-定时任务
 * @author chengcheng
 *
 */
public class CfReportOrderTask extends AbstractScheduledTask{

	private static final Logger logger = LogManager.getLogger(CfReportOrderTask.class);
	@Autowired
	private CfReportOrderTaskService cfReportOrderTaskService;	
	
	@Override
	public void exec() {
		logger.info("=======定时任务执行报表统计接口查询");
		cfReportOrderTaskService.queryReportByAcidIspno();
	}
} 
