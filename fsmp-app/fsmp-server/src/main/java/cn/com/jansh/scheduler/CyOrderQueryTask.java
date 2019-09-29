/**
 * CyOrderQueryTask.java
 * 版权所有(C) 2016 北京坚石诚信科技有限公司
 * 创建:Mr.wong 2016年11月10日
 */
package cn.com.jansh.scheduler;

import org.springframework.beans.factory.annotation.Autowired;

import com.jansh.core.scheduler.AbstractScheduledTask;

import cn.com.jansh.service.component.CyInterfaceService;

/**
 * 定时任务-银联支付结果查询
 * @author Mr.wong
 * @version 1.0
 */
public class CyOrderQueryTask extends AbstractScheduledTask {

	@Autowired
	private CyInterfaceService cyService;
	
	@Override
	public void exec() {
		cyService.tradeCheck();
	}

}
