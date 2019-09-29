package cn.com.jansh.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.jansh.core.scheduler.AbstractScheduledTask;
import cn.com.jansh.service.scheduler.CfRefreshStatusService;

/**
 * 定时刷新供应商报价及接入者状态
 * (不在开始-结束时间范围内自动将状态改为关闭)
 * @author duanmuyn
 *
 */
public class CfRefreshStatusTask extends AbstractScheduledTask{

	@Autowired
	private CfRefreshStatusService CfRefreshStatusService;
	
	@Override
	public void exec() {
		CfRefreshStatusService.refreshStatus();
	}

}
